package com.github.kinoyo.tool.tableFormatter.formatter;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class TableFormatter {

    private static final Pattern MAP_LINE_START = Pattern.compile("^\\s*.* = \\{$");

    private static final Pattern MAP_LINE_END = Pattern.compile("^\\s*\\},?$");

    private LinkedList<ListLine> listLines;

    @Getter
    private Writable result;

    public TableFormatter() {
        listLines = new LinkedList<>();
    }

    public static void main(String[] args) {
        final TableFormatter tableFormatter = new TableFormatter();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(args[0]), Charset.forName("UTF-8"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tableFormatter.doNextLine(line);
            }
        } catch (FileNotFoundException e) {
            log.error("Unable to open the file {}", args[0]);
        } catch (IOException e) {
            log.error("An error was thrown when reading the file {}", args[0]);
        }

        if (!tableFormatter.hasResult()) {
            log.error("Was the full table to be sorted in the input file ?");
            return;
        }

        try (Writer writer = Files.newBufferedWriter(Paths.get(args[1]), Charset.forName("UTF-8"), StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {
            tableFormatter.getResult().write(writer);
            writer.flush();
        } catch (IOException e) {
            log.error("An error was thrown when writing in the file {}", args[1]);
        }
    }

    public void doNextLine(String line) {
        Matcher matcherStart = MAP_LINE_START.matcher(line);
        Matcher matcherEnd = MAP_LINE_END.matcher(line);
        if (matcherStart.matches()) {
            final ListLine oldListLine = listLines.peekLast();
            final ListLine tmpListLine = new ListLine(line);
            if (null != oldListLine) {
                oldListLine.addLineToList(tmpListLine);
            }
            listLines.addLast(tmpListLine);
        } else if (matcherEnd.matches()) {
            final ListLine oldListLine = listLines.pollLast();
            oldListLine.setEndList(line);
            result = oldListLine;
        } else {
            final ListLine oldListLine = listLines.peekLast();
            final Line tmpLine = new Line(line);
            if (null != oldListLine) {
                oldListLine.addLineToList(tmpLine);
            }
        }
    }

    public boolean hasResult() {
        return null != this.result;
    }
}
