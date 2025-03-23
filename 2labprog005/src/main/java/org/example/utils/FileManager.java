package org.example.utils;

import java.io.*;
import java.util.Scanner;
/**
 * Manages file operations such as reading from and writing to a CSV file.
 * This class provides methods to obtain a  Scanner for reading
 * and a  PrintWriter for writing.
 */
public class FileManager {
    //The file path of the CSV file used for storage.
    private final String csvFilePath;
    /**
     * Constructs a FileManager with the specified file path.
     *filePath The path to the CSV file.
     */

    public FileManager(String filePath) {
        this.csvFilePath = filePath;
    }

    public Scanner getScanner() throws FileNotFoundException {
        return new Scanner(new File(csvFilePath));
    }
    /**
     * Returns a  PrintWriter  to write data to the CSV file.
     * This method overwrites the existing content in the file.
       PrintWriter instance for writing to the file.
     * throws IOException If an error occurs while opening the file.
     */

    public PrintWriter getWriter() throws IOException {
        System.out.println("DEBUG: Writing to file -> " + csvFilePath);

        return new PrintWriter(new FileWriter(csvFilePath, false));
    }
}
