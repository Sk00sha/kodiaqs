package org.example.readers;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.example.dataframe.DataFrame;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

public interface Reader {
    public DataFrame read();
}
