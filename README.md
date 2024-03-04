# Java and Maven sample

A fully working sample Maven project that download files from FTP (File Transfer Protocol).<br>
**FTP** (File Transfer Protocol) is a standard network protocol used for the transfer of files from one host to another over a TCP-based network, such as the Internet.<br>

This is targeted at people without Maven experience.<br>

To build it, you will need to download and unpack the latest (or recent) version of Maven (https://maven.apache.org/download.cgi) and put the mvn command on your path.<br>
Then, you will need to install a **Java 1.8** (or higher) JDK (not JRE!), and make sure you can run java from the command line. Now you can run mvn clean install and Maven will compile your project, an put the results it in a jar file in the target directory.

How you run this code is up to you, but usually you would start by using an IDE like **NetBeans, Intellij IDEA, or Eclipse**.<br>

The Maven dependencies may lag behind the official releases a bit.<br>

If you notice some problems with this setup, please open an issue.<br>

A couple of Maven commands<br>
Once you have configured your project in your IDE you can build it from there. However if you prefer you can use maven from the command line. In that case you could be interested in this short list of commands:<br>

**mvn compile**: it will just compile the code of your application and tell you if there are errors<br>
**mvn test**: it will compile the code of your application and your tests. It will then run your tests (if you wrote any) and let you know if some fails<br>
**mvn install**: it will do everything mvn test does and then if everything looks file it will install the library or the application into your local maven repository (typically under /.m2).<br>
In this way you could use this library from other projects you want to build on the same machine<br>


# myFTPBackupApp

Repository of myFTPBackuppApp - **myFTPBackupApp downloads and backup your folders and files that are extracted from FTP(e.g FileZilla Client).**<br>

In order to do so you have to add dependency **commons.net** at pom.xml like this :<br>

To add Apache Commons Net as a dependency in your Java project using Maven, you need to follow these steps:<br>

Update pom.xml: Open your pom.xml file located in the root directory of your Maven project.<br>

Add Dependency: Inside the dependencies section of your **pom.xml** file, add the dependency for Apache Commons Net. You can add the following XML snippet:<br>


**Copy code**

-- 

<dependencies>
    <dependency>
        <groupId>commons-net</groupId>
        <artifactId>commons-net</artifactId>
        <version>3.8.0</version>
    </dependency>
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-lang3</artifactId>
        <version>3.12.0</version>
    </dependency>
</dependencies>


    <properties>
        <maven.compiler.source>18</maven.compiler.source>
        <maven.compiler.target>18</maven.compiler.target>
    </properties>

</project>
This will add the **Apache Commons Net** library to your project with version 3.8.0.<br>
Make sure to use the appropriate version that fits your requirements. You can find the latest version number from Maven Central Repository.

Save the pom.xml: Save the pom.xml file after adding the dependency.

Update Maven Project: If you're using an IDE like IntelliJ IDEA or Eclipse, it will automatically detect the changes in the pom.xml file and download the dependencies. If you're using the command line, navigate to your project directory and run:

**Copy Code**
**mvn clean install**<br>

This command will download the Apache Commons Net dependency and build your project.<br>

After these steps, you can start using Apache Commons Net classes in your Java code. Make sure to import the required classes in your Java files as needed.<br>

Execute program in main ( FTPDownloader )<br>


Insert your credentials at:<br>

public class FtpFolderDownloadService {

    public static void FTPFolderDownloader() {
    
    where : 

        String server = "ftp.server";
        int port = 21;
        String user = "username";
        String pass = "password";

        //" Insert remote Folder Path of FTP Account
        String remoteFolderPath = "/remoteFolderPath";

        //" Insert location of path you want to store the folders/files ( locally )
        String localDirPath = "C://localDirectionPath";
