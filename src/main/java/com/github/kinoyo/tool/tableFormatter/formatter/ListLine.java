package com.github.kinoyo.tool.tableFormatter.formatter;

import com.github.kinoyo.tool.tableFormatter.util.LambdaUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Setter
@Getter
public class ListLine extends Line {

    private List<Line> lines;

    private String endList;

    public ListLine(String line) {
        super(line);
        this.lines = new ArrayList<>();
    }

    public void addLineToList(Line line) {
        this.lines.add(line);
    }

    @Override
    public void write(Writer writer) throws IOException {
        super.write(writer);
        this.lines.stream().sorted().forEachOrdered(LambdaUtils.handlingConsumerWrapper(line -> {
            line.write(writer);
        }, IOException.class));
        writer.write(this.endList);
        writer.write('\n');
    }
}
