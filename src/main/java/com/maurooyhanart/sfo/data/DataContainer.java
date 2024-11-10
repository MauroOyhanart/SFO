package com.maurooyhanart.sfo.data;

public class DataContainer {
    private final int size;
    private char[] chars;
    private byte[] bytes;

    public DataContainer(int size) {
        this.size = size;
    }

    public char[] getChars() {
        if (this.chars == null)
            this.chars = new char[size];
        return this.chars;
    }

    public byte[] getBytes() {
        if (this.bytes == null)
            this.bytes = new byte[size];
        return this.bytes;
    }

    /**
     *
     * @param chars the data array to hold
     */
    public void put(char[] chars) {
        this.chars = chars;
    }

    /**
     *
     * @param bytes the byte array to hold
     */
    public void put(byte[] bytes) {
        this.bytes = bytes;
    }


}
