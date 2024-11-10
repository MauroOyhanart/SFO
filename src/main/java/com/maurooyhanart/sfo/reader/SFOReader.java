package com.maurooyhanart.sfo.reader;

/**
 * Provides reading methods for both bytes and chars.
 */
public abstract class SFOReader {
    protected int bytesRead;
    protected int charsRead;

    public SFOReader() {
        this.bytesRead = 0;
        this.charsRead = 0;
    }

    /**
     * @param size
     * @return a {@code size} sized array when there's data, or a 0 sized array when there's no more to read.
     */
    public abstract byte[] readBytes(int size);

    /**
     * @param size
     * @return a {@code size} sized array when there's data, or a 0 sized array when there's no more to read.
     */
    public abstract char[] readChars(int size);

    public int getBytesRead() {
        return this.bytesRead;
    }

    public int getCharsRead() {
        return this.charsRead;
    }

    public abstract void end();
}
