package com.kamakarov.musicshelf.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StringUtilTest {
    @Test
    public void concatenateWithCommaOneElement() {
        List<String> stringList = new ArrayList<>();
        stringList.add("element");
        String result = StringUtil.concatenateWithComma(stringList);
        assertEquals("element", result);
    }

    @Test
    public void concatenateWithCommaEmptyList() {
        List<String> strings = new ArrayList<>();
        String result = StringUtil.concatenateWithComma(strings);
        assertEquals("", result);
    }

    @Test
    public void concatenateWithCommaFewElements() {
        List<String> strings = new ArrayList<>();
        strings.add("one");
        strings.add("two");
        strings.add("three");

        String result = StringUtil.concatenateWithComma(strings);

        assertEquals("one, two, three", result);
    }
}
