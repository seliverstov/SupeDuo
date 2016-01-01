package it.jaschke.alexandria.services;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.content.LocalBroadcastManager;
import it.jaschke.alexandria.ListOfBooks;
import it.jaschke.alexandria.data.AlexandriaContract;
import it.jaschke.alexandria.sync.SyncAdapter;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 */
public class BookService extends IntentService {

    private final String TAG = BookService.class.getSimpleName();

    public static final String FETCH_BOOK = "it.jaschke.alexandria.services.action.FETCH_BOOK";
    public static final String DELETE_BOOK = "it.jaschke.alexandria.services.action.DELETE_BOOK";

    public static final String EAN = "it.jaschke.alexandria.services.extra.EAN";
    public static final String RESULT = "it.jaschke.alexandria.services.extra.RESULT";

    public BookService() {
        super("Alexandria");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (FETCH_BOOK.equals(action)) {
                final String ean = intent.getStringExtra(EAN);
                fetchBook(ean);
            } else if (DELETE_BOOK.equals(action)) {
                final String ean = intent.getStringExtra(EAN);
                deleteBook(ean);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void deleteBook(String ean) {
        if(ean!=null) {
            getContentResolver().delete(AlexandriaContract.BookEntry.buildBookUri(Long.parseLong(ean)), null, null);
        }
    }

    /**
     * Handle action fetchBook in the provided background thread with the provided
     * parameters.
     */
    private void fetchBook(String ean) {

        if(ean.length()!=13){
            return;
        }

        Cursor bookEntry = getContentResolver().query(
                AlexandriaContract.BookEntry.buildBookUri(Long.parseLong(ean)),
                null, // leaving "columns" null just returns all the columns.
                null, // cols for "where" clause
                null, // values for "where" clause
                null  // sort order
        );

        if(bookEntry==null || bookEntry.getCount()>0){
            if (bookEntry!=null) bookEntry.close();
            Intent messageIntent = new Intent(ListOfBooks.MESSAGE_EVENT);
            messageIntent.putExtra(ListOfBooks.MESSAGE_KEY,ean);
            messageIntent.putExtra(ListOfBooks.MESSAGE_IN_LIST,true);
            LocalBroadcastManager.getInstance(this.getApplicationContext()).sendBroadcast(messageIntent);
            return;
        }else{
            bookEntry.close();
        }

        ContentValues values= new ContentValues();
        values.put(AlexandriaContract.BookEntry._ID, ean);
        values.put(AlexandriaContract.BookEntry.TITLE, ean);
        values.put(AlexandriaContract.BookEntry.CREATED_AT, System.currentTimeMillis());
        values.put(AlexandriaContract.BookEntry.IS_NEW, 1);
        getContentResolver().insert(AlexandriaContract.BookEntry.CONTENT_URI, values);

        SyncAdapter.syncNow(this);

    }
}