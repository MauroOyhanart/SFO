package com.maurooyhanart.learning.reader;

import com.maurooyhanart.learning.SFOController;
import com.maurooyhanart.learning.SFOFileOperator;

import java.io.*;

/**
 * <p>Provides read methods for file data sources.</p>
 *
 * Uses a {@code FileInputStream} to read raw bytes, and
 * wraps it in an {@code InputStreamReader} to read characters.
 *
 */
public class SFOFileReader extends SFOReader {
    private final SFOFileOperator fileOperator;
    private final BufferedInputStream fInputStream;
    private final Reader fInputStreamReader;

    public SFOFileReader(SFOFileOperator fileOperator) throws FileNotFoundException {
        super();
        this.fileOperator = fileOperator;
        this.fInputStream = new BufferedInputStream(new FileInputStream(fileOperator.getPath()));
        this.fInputStreamReader = new InputStreamReader(fInputStream);
    }

    /**
     * @param size
     * @return a {@code size} sized array when there's data, or a 0 sized array when there's no more to read.
     */
    @Override
    public byte[] readBytes(int size) {
        byte[] bytes =  new byte[size];

        try {
            this.bytesRead = fInputStream.read(bytes);
        } catch (IOException e) {
            log("SFOFileReader: IO Error occurred when reading from file: " + e.getMessage());
            return new byte[0];
        }

        if (this.bytesRead != -1) {
            log("SFOFileReader: read " + this.bytesRead + " bytes");
        } else {
            log("SFOFileReader: End of file reached");
            return new byte[0];
        }
        return bytes;
    }

    /**
     * @param size
     * @return a {@code size} sized array when there's data, or a 0 sized array when there's no more to read.
     */
    @Override
    public char[] readChars(int size) {
        char[] chars = new char[size];
        try {
            this.charsRead = fInputStreamReader.read(chars);
            if (this.charsRead != -1) {
                log("SFOFileReader: read " + this.charsRead + " chars");
            } else {
                log("SFOFileReader: EOF reached");
                return new char[0];
            }
        } catch (IOException e) {
            log("Could not read chars");
            this.charsRead = -1;
            return new char[0];
        }
        return chars;
    }

    @Override
    public void end() {
        try {
            this.fInputStream.close();
            this.fInputStreamReader.close();
            log("closed read resources");
        } catch (IOException e) {
            log("SFOFileReader: Could not close resource: " + e.getMessage());
        }
    }

    private void log(Object text) {
        if (SFOController.isLog())
            System.out.println("SFOFileReader: " + text);
    }
}
