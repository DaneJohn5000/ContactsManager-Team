package com.contact.util;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

/*
*  Manages file operations
* */
public class File {
    protected String directory;
    protected String name;
    protected Path dataFile;
    protected Path dataDirectory;


    public File(String directory, String name) {
        this.directory = directory;
        this.name = name;
        this.dataFile = Paths.get("./"+ this.directory + "/" + this.name);
        this.dataDirectory = Paths.get("./"+ this.directory);
    }


    // read the file opened on creation.
    public List<String> read() {

        if (Files.exists(this.dataFile)) {
            try {
                return Files.readAllLines(this.dataFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            return (create()) ? read() : null;
        }
        return null;
    }

    //return false if error
    public boolean create() {

        if (Files.notExists(this.dataDirectory)) {
            try {
                Files.createDirectories(this.dataDirectory);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        if (!Files.exists(this.dataFile)) {

            try {
                Files.createFile(this.dataFile);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    // overwrite the exising file on save.
    public boolean overwrite(List<String> data) {
        try {
            Files.write(dataFile, data);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
