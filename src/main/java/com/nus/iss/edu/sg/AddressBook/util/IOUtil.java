package com.nus.iss.edu.sg.AddressBook.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.logging.Logger;
import java.util.Set;
import java.util.logging.Level;

public class IOUtil {
    private static Logger logger = Logger.getLogger(IOUtil.class.getName());

    public static void createDir(String path){
        logger.log(Level.INFO,"create directory");
        File dir = new File(path);
        dir.mkdir();
        String osName = System.getProperty("os.name");
        
        // To handle for non-windows users
        if(!osName.contains("Windows")){
            try{
                String perm = "rwzrwz---";
                Set<PosixFilePermission> permission = PosixFilePermissions.fromString(perm);
                Files.setPosixFilePermissions(dir.toPath(),permission);
                
            }catch(IOException e){
                logger.log(Level.SEVERE,"Error creating directory!");
            }
        }
    }
}
