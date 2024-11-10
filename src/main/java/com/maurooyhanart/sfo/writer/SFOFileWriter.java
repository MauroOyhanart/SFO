package com.maurooyhanart.sfo.writer;

import com.maurooyhanart.sfo.SFOController;
import com.maurooyhanart.sfo.SFOFileOperator;

import java.io.*;

/**
 * Writes to a file.
 */
public class SFOFileWriter extends SFOWriter{
    private final SFOFileOperator fileOperator;
    private final FileOutputStream fileOutputStream;
    private final Writer outputStreamWriter;

    public SFOFileWriter(SFOFileOperator fileOperator) throws FileNotFoundException {
        super();
        this.fileOperator = fileOperator;
        this.fileOutputStream = new FileOutputStream(fileOperator.getPath(), true);
        this.outputStreamWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
    }

    @Override
    public void writeBytes(byte[] bytes, int bytesRead) {
        log("writing bytes");
        try {
            byte[] exactBytes = new byte[bytesRead];
            System.arraycopy(bytes, 0, exactBytes, 0, bytesRead);
            fileOutputStream.write(exactBytes);
        } catch (IOException e) {
            log("Could not write bytes to file: " + e.getMessage());
        }
    }

    @Override
    public void writeBytes(byte[] bytes) {

    }

    @Override
    public void writeChars(char[] chars, int charsRead) {
        log("writing chars into file..");
        char[] exactChars = new char[charsRead];
        log("its " + charsRead + " charsRead");
        System.arraycopy(chars, 0, exactChars, 0, charsRead);
        try {
            outputStreamWriter.write(exactChars);
            outputStreamWriter.flush();
        } catch (IOException e) {
            log("Could not write to file: " + e.getMessage());
        }
    }

    @Override
    public void writeChars(char[] chars) {

    }

    @Override
    public void end() {
        try {
            this.outputStreamWriter.close();
            log("closed write resources");
        } catch (IOException e) {
            log("SFOFileWriter: Could not close resource: " + e.getMessage());
        }
    }

    private void log(Object text) {
        if (SFOController.isLog())
            System.out.println("SFOFileWriter: " + text);
    }
}
