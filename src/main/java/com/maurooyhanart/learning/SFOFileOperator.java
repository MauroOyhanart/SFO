package com.maurooyhanart.learning;

import java.io.File;
import java.io.IOException;

public class SFOFileOperator {
    private final File file;

    public SFOFileOperator(String path) {
        this.file = new File(path);
    }

    public boolean isWritableFile() {
        if (!file.exists()) {
            log("File does not exist. Creating it");
            try {
                file.createNewFile();
            } catch (IOException e) {
                log("Could not create file: " + e.getMessage());
                return false;
            }
        }
        if (!file.canWrite()) {
            log("Cannot write to this file");
            return false;
        }
        return true;
    }

    public boolean isReadableFile() {
        if (!file.isFile()) {
            log("Provided read path is not a file or does not exist");
            return false;
        }
        if (!file.exists()) {
            log("File does not exist!");
            return false;
        }
        if (!file.canRead()) {
            log("Cannot read from this file");
            return false;
        }
        return true;
    }

    public String getPath() {
        return this.file.getPath();
    }

    public File getFile() {
        return this.file;
    }

    private void log(Object text) {
        if (SFOController.isLog())
            System.out.println("SFOFileOperator: " + text);
    }
}
