# FTP-server-setup
Apache FTP server configuration on the local system

### Steps to follow on Windows PC

* [Download](https://mina.apache.org/ftpserver-project/download_1_1.html) Apache FTP server binary(You can choose os compatible binary)
* Extract it and run the FTP server using the below commands
```
    # Go to the apache home directory (here you will find bin, common, res directory, and few files)
    # Execute the below command to start the FTP server
    .\bin\ftpd.bat .\res\conf\ftpd-typical.xml
    # Above command will start FTP server on port ```2121``` with a sample jks file provided by apache
```
* Get server details
    * Your system IP will be your server host (localhost may not work), you can find your system IP using ```ipconfig``` command
* For more details on the configuration you can look into [ftpd-typical.xml]() and [users.properties]() file
* Update server configuration in application.properties file(If you have not change any conf in apache conf folder file just change server host with your system ip)
* Update ```ftp.server.subdirectories``` key in application.properties if required
* Run ```com.msys.App.Java``` and you are ready with FTP server setup


### Steps to upload or download file
* Please make sure your FTP server is running
* Use the below command to establish a connection with FTP server (cmd or PowerShell can be used)
``` 
    # Below command will open FTP console for u
    ftp
    # Use this command to establish the connection (please change host and port if required)
    # open <host> <port>
    open 192.168.0.106 2121
    

```
* List of FTP commands can be checked [here](https://learn.microsoft.com/en-us/windows-server/administration/windows-commands/ftp)
* Use the below command to upload/download the file from FTP server
* We will use [put](https://learn.microsoft.com/en-us/windows-server/administration/windows-commands/ftp-put) command to upload file to remote server
* We will use [get](https://learn.microsoft.com/en-us/windows-server/administration/windows-commands/ftp-get) command to upload file to remote server
* Alternative command for put is [send](https://learn.microsoft.com/en-us/windows-server/administration/windows-commands/ftp-send_1) and for get is [recv](https://learn.microsoft.com/en-us/windows-server/administration/windows-commands/ftp-recv)
```
    # Please make sure you have connected to ftp server
    # put <source file path> <remote file path> (Prefer to use absolute file path)
    # To upload file to the subdirectory please mention the subdirectory in remote 
    put pom.xml pom.xml
    # get <remote file path> <locafile path>
```
* Sample command for file download and upload
```
    # Uploading pom.xml from current directory to apache FTP home directory
    put pom.xml pom.xml
    # Upload readme from other directories to raw subdirectory of FTP home dir
    put D:\apache-ftpserver-1.1.4\res\conf\README.txt raw\README.txt
    # You can upload the same file with other names like below
    put D:\apache-ftpserver-1.1.4\res\conf\README.txt raw\readme.txt
```