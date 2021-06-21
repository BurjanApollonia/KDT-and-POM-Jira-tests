package com.codecool.jira.KDT_and_POM_Jira_tests.POM;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class CSVDataReaders {

    public static String[][] getCSVData(String fileName, char sep) throws CsvValidationException {
        String csvFile = fileName;
        CSVReader reader = null;
        String[] line;
        List<String[]>  arrlist = new ArrayList<String[]>();
        String[][] datafromCSV = null;
        try {
            reader = new CSVReader(new FileReader(csvFile));

            while ((line = reader.readNext()) != null) {
                arrlist.add(line);
            }
            int nrows = arrlist.size();
            int ncols = arrlist.get(0).length;
            datafromCSV = new String[nrows][ncols];
            for(int i = 0 ; i < nrows ; i++) {
                String[] eachRow = arrlist.get(i);
                for(int j = 0 ; j < ncols ; j++) {
                    datafromCSV[i][j] = eachRow[j];
                }
            }
        } catch (IOException e) {   e.printStackTrace();	}
        return datafromCSV;
    }
}
