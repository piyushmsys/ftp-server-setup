package com.msys;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Properties;

public class MsysFtpServer {

    private static final String SERVER_CONF_PATH = "application.properties";

    public static void initialize(){
        Properties serverProperties = readServerConf();
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(serverProperties.getProperty("ftp.server.host"),
                    Integer.parseInt(serverProperties.getProperty("ftp.server.port")));
            ftpClient.login(serverProperties.getProperty("ftp.server.username"),
                    serverProperties.getProperty("ftp.server.password"));
            ftpClient.enterLocalPassiveMode();

            String[] directoryRequired = serverProperties.getProperty("ftp.server.subdirectories").split(",");
            for(String directoryName : directoryRequired){
                ftpClient.makeDirectory(directoryName);
            }
            System.out.println("Following sub directory created : " + Arrays.toString(directoryRequired));
        } catch (IOException ex) {
            System.err.println("Error: " + ex.getMessage());
            throw new RuntimeException(ex);
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                System.err.println("Unable to disconnect ftp server connection : "+ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

     private static Properties readServerConf(){
        Properties properties = new Properties();
        try {
            properties.load(Files.newInputStream(Paths.get(SERVER_CONF_PATH)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }
}
