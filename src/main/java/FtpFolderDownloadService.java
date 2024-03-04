
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.*;

public class FtpFolderDownloadService {

    public static void FTPFolderDownloader() {

        //"Insert FTP Credentials"

        String server = "ftp.server";
        int port = 21;
        String user = "username";
        String pass = "password";

        //" Insert remote Folder Path of FTP Account
        String remoteFolderPath = "/remoteFolderPath";

        //" Insert location of path you want to store the folders/files ( locally )
        String localDirPath = "C://localDirectionPath";

        // Create FTPClient
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);


            // If local directory doesn't exist, create it
            File localDir = new File(localDirPath);
            if (!localDir.exists()) {
                localDir.mkdirs();
            }

            FTPFile[] files = ftpClient.listFiles(remoteFolderPath);
            for (FTPFile file : files) {


                // Check if file  is  "." or ".." in order to pass
                if ((file.getName().equals("."))
                        || file.getName().equals("..")) {
                    continue;
                }

                if (file.getName().endsWith(".zip")) {
                    continue;
                }


                // Check whether FTP file is a directory or file in order to download accordingly
                if (!file.isDirectory()) {

                    if ((file.getName().equals("."))
                            || file.getName().equals("..")) {
                        continue;
                    }


                    // remote file path
                    String remoteFilePath = remoteFolderPath + "/" + file.getName();

                    // local file path
                    String localFilePath = localDirPath + "/" + file.getName();

                    // download file
                    downloadFile(ftpClient, remoteFilePath, localFilePath);
                    System.out.println("Downloaded: " + remoteFilePath);
                } else {
                    String remoteSubDirPath = remoteFolderPath + "/" + file.getName();
                    String localSubDirPath = localDirPath + "/" + file.getName();
                    File localSubDir = new File(localSubDirPath);
                    localSubDir.mkdirs();
                    downloadFolder(ftpClient, remoteFolderPath + "/" + file.getName(), localDirPath + "/" + file.getName());
                }
            }

            System.out.println("Files downloaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void downloadFolder(FTPClient ftpClient, String remoteDirPath, String localDirPath) throws IOException {


        FTPFile[] files = ftpClient.listFiles(remoteDirPath);

        for (FTPFile file : files) {
            if ((file.getName().equals("."))
                    || file.getName().equals("..")) {
                continue;
            }

            if (
                    file.getName().endsWith(".zip")) {
                continue;
            }

            if (file.isDirectory()) {
                String remoteSubDirPath = remoteDirPath + "/" + file.getName();
                String localSubDirPath = localDirPath + "/" + file.getName();
                File localSubDir = new File(localSubDirPath);
                localSubDir.mkdirs();
                downloadFolder(ftpClient, remoteSubDirPath, localSubDirPath);
            } else {
                String remoteFilePath = remoteDirPath + "/" + file.getName();
                String localFilePath = localDirPath + "/" + file.getName();
                downloadFile(ftpClient, remoteFilePath, localFilePath);
                System.out.println("Downloaded: " + remoteFilePath);
            }
        }
    }

    private static void downloadFile(FTPClient ftpClient, String remoteFilePath, String localFilePath) throws IOException {


        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(localFilePath));
        try {

            ftpClient.retrieveFile(remoteFilePath, outputStream);
        } finally {
            outputStream.close();
        }
    }
}