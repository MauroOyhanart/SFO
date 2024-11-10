package com.maurooyhanart.learning;

import java.io.CharArrayReader;
import java.io.IOException;

/*
    ~Objective: perform common Java IO operations only using the java.io package~
    Classes and interfaces we can use are below:
    File
    FileInputStream, BufferedInputStream, DataInputStream, ObjectInputStream
    FileOutputStream, BufferedOutputStream, DataOutputStream, ObjectOutputStream

    FileReader, BufferedReader, Reader
    FileWriter, BufferedWriter, Writer, PrintWriter

    Serializable

    Exceptions we can use are below:
    IOException, FileNotFoundException, EOFException,
    UnsupportedEncodingException, StreamCorruptedException, UTFDataFormatException

    ____ About the java.io package ____

    There are two main abstractions here: streams and readers/writers

    Streams deal with raw bytes. It's possible to read and write with a stream, but raw bytes only.

    Readers and Writers provide abstractions to work with characters. That's it.


    Then, both streams and readers/writers can be buffered.


    ____ /About the java.io package ____


    A simple app can be made from this:
    - file reader
    - file writer

    SFO -> Small and Simple File Operator
    Args:
        --from path -> the path to read from
        --to path -> the path to write to
        --char -> operates on a char level
        --log -> log program execution to stdout

     If no --from is specified, it will read from stdin
     If no --to is specified, it will write to stdout
 */
public class Main {
    public static void main(String[] args) {
        String fromPath = null, toPath = null;
        boolean charRead = false;
        boolean log = false;

        if (args.length > 0) {
            int i = 0;
            while (i < args.length) {
                switch (args[i]) {
                    case "--from":
                        if (!checkBounds(args, i, "--from")) return;
                        i++;
                        if (args[i].startsWith("--")) {
                            System.err.println("Specify the path after the " + "--from" + " option");
                            return;
                        }
                        fromPath = args[i];
                        break;
                    case "--to":
                        if (!checkBounds(args, i, "--to")) return;
                        i++;
                        if (args[i].startsWith("--")) {
                            System.err.println("Specify the path after the " + "--to" + " option");
                            return;
                        }
                        toPath = args[i];
                        break;
                    case "--char":
                        charRead = true;
                        break;
                    case "--log":
                        log = true;
                    default:
                        break;
                }
                i++;
            }

        }

        SFOController sfoController = new SFOController(fromPath, toPath, charRead, log);
        sfoController.initiate();
    }

    private static boolean checkBounds(String[] args, int i, String option) {
        if (args.length == i+1) {
            System.err.println("Specify the path after the " + option + " option");
            return false;
        }
        return true;
    }
}