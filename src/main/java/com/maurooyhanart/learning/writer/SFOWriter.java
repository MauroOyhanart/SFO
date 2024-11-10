package com.maurooyhanart.learning.writer;

import com.maurooyhanart.learning.SFOFileOperator;

/**
 * Writes to sources, either in text or in bytes.
 */
public abstract class SFOWriter {

    public SFOWriter() {
    }

    public abstract void writeBytes(byte[] bytes, int bytesRead);

    public abstract void writeBytes(byte[] bytes);

    public abstract void writeChars(char[] chars, int charsRead) ;

    public abstract void writeChars(char[] chars);

    public abstract void end();
}
