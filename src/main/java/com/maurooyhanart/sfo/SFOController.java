package com.maurooyhanart.sfo;

import com.maurooyhanart.sfo.data.DataContainer;
import com.maurooyhanart.sfo.reader.SFOFileReader;
import com.maurooyhanart.sfo.reader.SFOReader;
import com.maurooyhanart.sfo.reader.SFOStandardInputReader;
import com.maurooyhanart.sfo.writer.SFOFileWriter;
import com.maurooyhanart.sfo.writer.SFOStandardOutputWriter;
import com.maurooyhanart.sfo.writer.SFOWriter;

import java.io.FileNotFoundException;

public class SFOController {
    private final String from;
    private final String to;
    private final boolean charRead;
    private static boolean log;

    private final int size = 4096;

    public SFOController(String from, String to, boolean charRead, boolean log) {
        this.from = from;
        this.to = to;
        this.charRead = charRead;
        SFOController.log = log;

        String text = "Recognized args: " + from + " -> " + to;
        if (!charRead) {
            log(text);
        } else log(text + ", charRead=" + charRead);
    }

    public void initiate() {
        log("Initiated");
        SFOReader reader;
        SFOWriter writer;

        //Build reader
        if (from != null) {
            SFOFileOperator fileOperatorFrom = new SFOFileOperator(from);
            try {
                reader = new SFOFileReader(fileOperatorFrom);
            } catch (FileNotFoundException e) {
                logErr("Error creating file reader: " + e.getMessage());
                return;
            }
        } else { //[[stdin]]
            reader = new SFOStandardInputReader();
        }

        //Build writer
        if (to != null) {
            SFOFileOperator fileOperatorTo = new SFOFileOperator(to);
            try {
                writer = new SFOFileWriter(fileOperatorTo);
            } catch (FileNotFoundException e) {
                logErr("Error creating file writer: " + e.getMessage());
                return;
            }
        } else {  //[[stdout]]
            writer = new SFOStandardOutputWriter();
        }


        operate(reader, writer);
    }

    private void operate(SFOReader reader, SFOWriter writer) {
        DataContainer dataContainer = new DataContainer(size);

        //Application logic. Main while
        while (true) {
            if (charRead) {
                dataContainer.put(reader.readChars(size));
                if (reader.getCharsRead() == -1) break;
                writer.writeChars(dataContainer.getChars(), reader.getCharsRead());
            } else {
                dataContainer.put(reader.readBytes(size));
                if (reader.getBytesRead() == -1) break;
                writer.writeBytes(dataContainer.getBytes(), reader.getBytesRead());
            }
        }

        reader.end();
        writer.end();
    }

    private void log(Object text) {
        if (isLog())
            System.out.println("SFOController: " + text);
    }

    private void logErr(Object text) {
        System.err.println("SFOController: " + text);
    }

    public static boolean isLog() {
        return log;
    }
}


