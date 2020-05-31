CS COMMUNITY BASED LEARNING (A LAN based Java Project where JAVAFX has been used for the GUI)

Our project has 6 packages. util package consists of all of our utility classes that are – Comment, Question, Answer, CommentList, QuestionList, NetworkUtil and UserInfo. Main server class is located in the server package which name is ServerMain. After the connection is established with a client a new thread opens named ClientHandler and the rest of the communication with the client is done with this thread. 
All the data files are located in the ‘file’ package and images in the ‘image’ package. 
Client parts main class is ShowGui that extends Application. Most of the fxml files are loaded in this class. All fxml files are organized in another folder under the client package named fxmlFiles. All the request sent to the server are done by the thread ‘SendThread’ and ‘SignThread’. All the things that are sent by the server are received by the ‘ReceiveListThread’ .
For the designing fxml is mainly used. Apart from that an external library is used to beautify the GUI. The library name is ‘jfoenix-9.0.0’ which is included in the libraries folder with the project src folder. Besides a CSS style-sheet is also used.

Instructions for Building and Running from Intellij:

You have to just import the ProjectRoot folder from the import project settings.  Then you are done :D

Instructions for compiling from CMD in windows:

You must compile with JDK 9
If you create a folder named “class” in the ProjectRoot folder to store the class files and start CMD (windows terminal) from src folder, give the following commands:

At first compile util classes 
javac -d "..\class" util\*.java

Then server classes :
javac -d "..\class" server\*.java

Then client classes but we must tell the compiler where Jfoenix library exists while compiling the classes of the client package.
So..
javac -d "..\class" -cp "..\library\jfoenix-9.0.0.jar;..\class" client\*.java

Finally, you have done compiling.

Instructions for running the project from CMD :

This command will start the server:
java -cp ..\class server.ServerMain

this command should start the client and the GUI :
java -cp ..\class client.ShowGui

But here are some issues. The compiler throws some exception saying location not set as the fxml files are not found. These issues are for the difference in structure of the relative path of any files between Intellij and terminal.
