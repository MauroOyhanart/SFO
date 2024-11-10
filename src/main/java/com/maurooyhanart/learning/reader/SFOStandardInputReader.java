package com.maurooyhanart.learning.reader;

import com.maurooyhanart.learning.SFOController;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Provides read methods from standard input.
 */
public class SFOStandardInputReader extends SFOReader {
    private final Reader reader;

    public SFOStandardInputReader() {
        super();
        this.reader = new InputStreamReader(System.in);
    }

    @Override
    public byte[] readBytes(int size) {
        byte[] bytes = new byte[size];
        try {
            this.bytesRead = System.in.read(bytes);
            if (bytesRead != -1) {
                return bytes;
            } else {
                log("Stdin: EOF reached");
                return new byte[0];
            }
        } catch (IOException ioe) {
            log("Could not read from stdin: " + ioe.getMessage());
        }
        return bytes;
    }

    @Override
    public char[] readChars(int size) {
        char[] chars = new char[size];

        try {
            this.charsRead = reader.read(chars);
            if (this.charsRead == -1) {
                log("EOF reached in stdin");
                return new char[0];
            }
        } catch (IOException e) {
            log("Could not read from stdin: " + e.getMessage());
        }

        return chars;
    }

    @Override
    public void end() {
        try {
            this.reader.close();
        } catch (IOException e) {
            log("Could not close resources");
        }
    }

    private void log(Object text) {
        if (SFOController.isLog())
            System.out.println("SFOStandardInputReader: " + text);
    }
}
