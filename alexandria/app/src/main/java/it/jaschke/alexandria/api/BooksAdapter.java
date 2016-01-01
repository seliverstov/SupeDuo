package it.jaschke.alexandria.api;


import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.skyfishjy.CursorRecyclerViewAdapter;
import com.squareup.picasso.Picasso;

import it.jaschke.alexandria.R;
import it.jaschke.alexandria.data.AlexandriaContract;

/**
 * Created by saj on 11/01/15.
 */
public class BooksAdapter extends CursorRecyclerViewAdapter<BooksAdapter.ViewHolder> {
    private static final String TAG = BooksAdapter.class.getSimpleName();
    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final ImageView bookCover;
        public final TextView bookTitle;
        public final TextView bookSubTitle;
        public final CardView cardView;
        public final ProgressBar loading;

        public ViewHolder(View view) {
            super(view);
            bookCover = (ImageView) view.findViewById(R.id.fullBookCover);
            bookTitle = (TextView) view.findViewById(R.id.listBookTitle);
            bookSubTitle = (TextView) view.findViewById(R.id.listBookSubTitle);
            cardView = (CardView) view.findViewById(R.id.card_view);
            loading = (ProgressBar) view.findViewById(R.id.listBookLoading);
        }
    }

    public BooksAdapter(Context context, Cursor c) {
        super(context, c);
        mContext = context;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
        String imgUrl = cursor.getString(cursor.getColumnIndex(AlexandriaContract.BookEntry.IMAGE_URL));
        if (imgUrl!=null) {
            Uri url = Uri.parse(imgUrl);
            Picasso.with(mContext).load(url).placeholder(R.drawable.no_cover).error(R.drawable.no_cover).into(viewHolder.bookCover);
        }else{
            viewHolder.bookCover.setImageDrawable(ContextCompat.getDrawable(mContext,R.drawable.ic_launcher));
        }

        final String ean = cursor.getString(cursor.getColumnIndex(AlexandriaContract.BookEntry._ID));

        final String bookTitle = cursor.getString(cursor.getColumnIndex(AlexandriaContract.BookEntry.TITLE));

        //final String bookSubTitle = cursor.getString(cursor.getColumnIndex(AlexandriaContract.BookEntry.SUBTITLE));

        final int is_new = cursor.getInt(cursor.getColumnIndex(AlexandriaContract.BookEntry.IS_NEW));

        final String ISBNTitle = mContext.getString(R.string.isbn_title);

        final String loadingTitle = mContext.getString(R.string.loading_title);

        final String bookNotLoadedMsg = mContext.getString(R.string.book_not_loaded_yet);

        if (is_new!=1) {
            viewHolder.bookTitle.setText(bookTitle);
            viewHolder.bookSubTitle.setText(String.format(ISBNTitle,ean));
            viewHolder.loading.setVisibility(View.GONE);
            viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Callback callback = (Callback) mContext;
                    callback.onItemSelected(ean);
                }
            });
        }else{
            viewHolder.bookTitle.setText(String.format(ISBNTitle,ean));
            viewHolder.bookSubTitle.setText(loadingTitle);
            viewHolder.loading.setVisibility(View.VISIBLE);
            viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,String.format(bookNotLoadedMsg,ean),Toast.LENGTH_SHORT).show();
                }
            });
        }


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }
}
