This program cleans a Stack Exchange thread and stores the cleaned html file in the $PATH_TO_FOLDER/html/ folder.
Use case: Convert html file to ebook formats like azw3 and epub using softwares like Calibre.
This software was needed because many existing applications could not extract information properly from Stack Exchange threads.

On UNIX:
1) Go to the folder
2) Compile with javac -cp .:jsoup-1.9.2.jar StackExchangeRead.java WriteToHTMLFile.java
3) Run with java -cp .:$PATH_TO_FOLDER/jsoup-1.9.2.jar:$PATH_TO_FOLDER StackExchangeRead

On Windows:
1) Go to the folder
2) Compile with javac -cp .;jsoup-1.9.2.jar StackExchangeRead.java WriteToHTMLFile.java
3) Run with java -cp .;$PATH_TO_FOLDER/jsoup-1.9.2.jar;$PATH_TO_FOLDER StackExchangeRead

Linux users may be able to add a shortcut to the run command as follows:
1) Open ~/.bash_aliases
2) Type 'alias stackextohtml="java -cp.:~/git/stackexchangetohtml/jsoup-1.9.2.jar:~/git/stackexchangetohtml/ StackExchangeRead
3) Save the file
4) Type 'source ~/.bash_aliases'
5) The command 'stackextohtml' in the command prompt should now work with the same functionality

*Remember to compile upon every pull request if there are changes
