/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ciir.proteus.parse;
import java.io.*;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shiridori-hacohen on 8/28/14. Fit to his own mischievous purposes by Ben Zif on 12/2/2014
 */
public class AnnotationReader {
    
    List<String[]> theList = null;
    String title = null;

    public AnnotationReader(File directory, File file) {
        title = file.getName();
        theList = readTabbedValuesFromDirectory(directory, file);
    }

    ;
    /**
     * Reads lines from a file
     * @param filename
     * @return list of lines (in order of appearance)
     */
    public static List<String[]> readTabbedValuesFromDirectory(File directory, File file) {

        List<String[]> lines = new ArrayList<String[]>();
        BufferedReader in = null;
        FileReader fr = null;

        if (directory.isDirectory()) {
            for (File f : directory.listFiles()) {
              
                if (f.getName().equals(file.getName())) {
                    
                    try {
                    
                        fr = new FileReader(file);
                        in = new BufferedReader(fr);

                        String line = null;
                        while ((line = in.readLine()) != null) {

                            String[] lineSplit = line.split("\\t");
                            lines.add(lineSplit);
                        }

                    } catch (IOException e) {
                        System.out.println(e);
                    } finally {
                        safeClose(in);
                    }
                }
            }
        }
        return lines;
    }
   

    public static List<String[]> readTabbedValuesFromFile(String file) {

        List<String[]> lines = new ArrayList<String[]>();
        BufferedReader in = null;
        FileReader fr = null;

        try {
            fr = new FileReader(file);
            in = new BufferedReader(fr);

            String line = null;
            while ((line = in.readLine()) != null) {

                String[] lineSplit = line.split("\\t");
                lines.add(lineSplit);
            }

        } catch (IOException e) {
            System.out.println(e);
        } finally {
            safeClose(in);
        }

        return lines;
    }

    public static void writeLine(BufferedWriter writer, String text) throws IOException {
        writer.write(text);
        writer.newLine();
    }

    public static BufferedWriter openWriter(String filename) throws IOException {
        FileWriter fileOut = new FileWriter(filename);
        return new BufferedWriter(fileOut);

    }

    public static ObjectOutputStream openOOS(String filename) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(filename);
        return new ObjectOutputStream(fileOut);

    }

    public static ObjectInputStream openOIS(String cacheTitlePath) throws IOException {
        FileInputStream cacheIn = new FileInputStream(cacheTitlePath);
        return new ObjectInputStream(cacheIn);
    }

    public static void writeTabbedValues(BufferedWriter writer, String column1, String column2) throws IOException {
        writer.write(column1);
        writer.write(StringUtils.TAB);
        writer.write(column2);
        writer.newLine();
    }

    public static void writeTabbedValues(BufferedWriter writer, String[] columns) throws IOException {
        for (int i = 0; i < columns.length; i++) {
            writer.write(columns[i]);
            if (i < columns.length - 1) {
                writer.write(StringUtils.TAB);
            } else {
                writer.newLine();
            }
        }
    }

    public static boolean isExistsFile(String fileName) {
        File temp = new File(fileName);
        if (!temp.exists()) {
            return false;  // I hate this style, but it's easier than the alternative
        } else {
            return true;
        }
    }

    /**
     * Try to close file (if not null). If fails, do nothing
     *
     * @param file
     */
    public static void safeClose(BufferedReader file) {
        try {
            if (file != null) {
                file.close();
            }
        } catch (IOException e) {
            System.err.print(e);            // report, and do nothing
        }
    }

    /**
     * Try to close file (if not null). If fails, do nothing
     *
     * @param file
     */
    public static void safeClose(BufferedWriter file) {
        try {
            if (file != null) {
                file.close();
            }
        } catch (IOException e) {
            System.err.print(e);             // report, and do nothing
        }
    }

    /**
     * Try to close file (if not null). If fails, do nothing
     *
     * @param file
     */
    public static void safeClose(ObjectInputStream file) {
        try {
            if (file != null) {
                file.close();
            }
        } catch (IOException e) {
            System.err.print(e);             // report, and do nothing
        }
    }

    /**
     * Try to close file (if not null). If fails, do nothing
     *
     * @param file
     */
    public static void safeClose(ObjectOutputStream file) {
        try {
            if (file != null) {
                file.close();
            }
        } catch (IOException e) {
            System.err.print(e);             // report, and do nothing
        }
    }
}
