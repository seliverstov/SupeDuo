## SupeDuo
###Project 3 "Super Duo!" of [Udacity Android Developer Nanodegree](https://www.udacity.com/course/android-developer-nanodegree--nd801)

## Alexandria: A book list and barcode scanner app.

### Install
```
$ git clone https://github.com/seliverstov/SupeDuo
$ cd SupeDuo/alexandria
$ gradle installDebug
```

### Improvements

* Application was redesigned and mostly rebuilt from scratch. 
* First of all, I did merge "List of Books" and "Scan/Add Book" screens in to a single screen. Now, your main screen is "List of Books" and you can use "FloatingActionButton" at the bottom of the screen to scan some books, or type 10 or 13 digits ISBN number in "SearchView" and app will query Google Books and will add this book to your library.
* Settings menu where you could chose your start screen is no longer needed. But I  use it to selected "SearchView" initial state (Expanded or Collapsed) and choose sort order for the list of books.
* Add scan functionality with "zxing" library
* NavigationDrawler now using to access "Settings", "About" and as additional option to open scanner. 
* You can add books to your library when you are offline by scanning or typing ISBN number. When network connection will be available, these books will be fetched from web by SyncAdapter
* Add RTL support and content description
* Database version was upgraded to 2

## FootballScores: An app that tracks current and future football matches

### Install
```
$ git clone https://github.com/seliverstov/SupeDuo
$ cd SupeDuo/footballscores
```
Go to `app/src/main/res/values`, open `strings.xml`file in text editor and put your api key to. 

```
<string name="api_key" translatable="false">PUT_YOUR_API_KEY_HERE</string>
```
then return to project's root folder and run
```
$ gradle installDebug
```
###Improvements

* Application was mostly rebuilt from scratch.
* Add collection widget
* Add SyncAdapetr for data updates
* Add RTL support and content description
* Database version was upgraded to 3

##License

The contents of this repository are covered under the [MIT License](http://choosealicense.com/licenses/mit/).
