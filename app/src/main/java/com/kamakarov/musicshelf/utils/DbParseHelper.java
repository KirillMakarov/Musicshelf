package com.kamakarov.musicshelf.utils;

import java.util.List;

public class DbParseHelper {

    private static final String DELIMETER = "__,__";

    public static String parseStringArrayToString(String[] array) {
        if (array == null || array.length == 0) return null;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i != array.length - 1)
                sb.append(DELIMETER);
        }
        return sb.toString();
    }

    public static String parseStringArrayToString(List<String> list) {
        if (list == null || list.isEmpty()) return null;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i != list.size() - 1)
                sb.append(DELIMETER);
        }
        return sb.toString();
    }

    public static String[] parseStringToStringArray(String str) {
        if (str == null) return null;

        String[] strArray = str.split(DELIMETER);
        String[] result = new String[strArray.length];
        for (int i = 0; i < strArray.length; i++)
            result[i] = strArray[i].trim();
        return result;
    }
}
