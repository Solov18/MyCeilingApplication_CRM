package ru.gb.diplom.util;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class CSVUtils {



    public static List<String[]> reader(String url){
        CSVReader reader;
        try {
            reader = new CSVReader(new FileReader(url));
            List<String[]> myEntries = reader.readAll();
            reader.close();
            return myEntries;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public static List buildListFromCSV(String filename, Class aClass) {
        Path fullPath = Paths.get(System.getProperty("user.home"),"Documents", filename);
        try {
            List beans = new CsvToBeanBuilder(new FileReader(fullPath.toString()))
                    .withThrowExceptions(true).withType(aClass)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withQuoteChar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build().parse();
            return beans;
        } catch (IllegalStateException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }


    public static void exportListToCSV(String filename, List beans) {
        Path fullPath = Paths.get(System.getProperty("user.home"),"Documents", filename);
        try {
            Writer writer = new FileWriter(fullPath.toString());
            StatefulBeanToCsvBuilder beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER);
            try {
                beanToCsv.build().write(beans);
                writer.close();
            } catch (CsvDataTypeMismatchException e) {
                e.printStackTrace();
            } catch (CsvRequiredFieldEmptyException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
