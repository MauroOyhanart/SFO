package com.maurooyhanart.learning.writer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Writes to standard output.
 */
public class SFOStandardOutputWriter extends SFOWriter{
    private final Writer writer;
    public SFOStandardOutputWriter() {
        super();
        this.writer = new OutputStreamWriter(System.out);
    }

    @Override
    public void writeBytes(byte[] bytes, int bytesRead) {
        byte[] exactBytes = new byte[bytesRead];
        System.arraycopy(bytes, 0, exactBytes, 0, bytesRead);
        System.out.writeBytes(exactBytes);
        BufferedWriter x;
    }

    @Override
    public void writeBytes(byte[] bytes) {

    }

    @Override
    public void writeChars(char[] chars, int charsRead) {
        char[] exactChars = new char[charsRead];
        System.arraycopy(chars, 0, exactChars, 0, charsRead);
        try {
            writer.write(exactChars);
        } catch (IOException e) {
            log("could not write to stdout: " + e.getMessage());
        }
    }

    @Override
    public void writeChars(char[] chars) {

    }

    @Override
    public void end() {
        try {
            this.writer.close();
        } catch (IOException e) {
            log("Could not close resource: " + e.getMessage());
        }
    }

    private void log(Object text) {
        System.out.println("SFOStandardOutputWriter: " + text);
    }
}
