# SupeDuo

## Alexandria
Application was redesigned and mostly rebuilt from scratch. You can see commit history in dev repository for this app: https://github.com/seliverstov/alexandria
* First of all, I did merge "List of Books" and "Scan/Add Book" screens in to a single screen. Now, your main screen is "List of Books" and you can use "FloatingActionButton" at the bottom of the screen to scan some books, or type 10 or 13 digits ISBN number in "SearchView" and app will query Google Books and will add this book to your library.
* Settings menu where you could chose your start screen is no longer needed. But I  use it to selected "SearchView" initial state (Expanded or Collapsed) and choose sort order for the list of books.
* Add scan functionality with "zxing" library
* NavigationDrawler now using to access "Settings", "About" and as additional option to open scanner. 
* You can add books to your library when you are offline by scanning or typing ISBN number. When network connection will be available, these books will be fetched from web by SyncAdapter
* Add RTL support and content description
* Database version was upgraded to 2

## Football Scores
First of all, put your API key to strings.xml file to item "api_key".  You can see commit history in dev repository for this app: https://github.com/seliverstov/Football_Scores-master
* Application was mostly rebuilt from scratch.
* Add collection widget
* Add SyncAdapetr for data updates
* Add RTL support and content description
* Database version was upgraded to 3
