package com.msys;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "FTP server initialization started." );
        MsysFtpServer.initialize();
        System.out.println( "FTP server initialization finished." );
    }
}
