package com.github.kinoyo.tool.tableFormatter.util;

@FunctionalInterface
public interface ThrowingConsumer<T, E extends Exception> {
    void accept(T t) throws E;
}
