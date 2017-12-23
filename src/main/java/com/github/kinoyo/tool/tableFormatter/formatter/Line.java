package com.github.kinoyo.tool.tableFormatter.formatter;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.Writer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Setter
@Getter
public class Line implements Writable, Comparable<Line> {

    private static final Pattern PATTERN_LIST = Pattern.compile("^\\s*\".+\",? -- \\[(\\d+)\\]$");

    private static final Pattern PATTERN_MAP = Pattern.compile("^\\s*\\[\"(.+)\"\\] = .*$");

    private String line;

    public Line(String line) {
        this.line = line;
    }

    public void write(Writer writer) throws IOException {
        writer.write(this.line);
        writer.write('\n');
    }

    @Override
    public int compareTo(Line line) {
        if (null == line) {
            return 1;
        }
        final int result;
        if (null == this.line && null == line.line) {
            result = 0;
        } else if (null == this.line) {
            result = -1;
        } else if (null == line.line) {
            result = 1;
        } else {
            Matcher matcherList = PATTERN_LIST.matcher(this.line);
            Matcher matcherMap = PATTERN_MAP.matcher(this.line);
            if (matcherList.matches()) {
                Integer order1 = Integer.valueOf(matcherList.group(1));
                Matcher matcherLine = PATTERN_LIST.matcher(line.line);
                Integer order2 = matcherLine.matches() ? Integer.valueOf(matcherLine.group(1)) : 0;
                result = order1.compareTo(order2);
            } else if (matcherMap.matches()) {
                String order1 = matcherMap.group(1);
                Matcher matcherLine = PATTERN_MAP.matcher(line.line);
                String order2 = matcherLine.matches() ? matcherLine.group(1) : "";
                result = order1.compareTo(order2);
            } else {
                // This case shouldn't occur.
                result = 0;
            }
        }

        return result;
    }
}
