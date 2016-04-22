package com.kamakarov.musicshelf.utils;

import java.util.List;

public class StringUtil {
    public static String concatenateWithComma(List<String> stringList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stringList.size(); i++) {
            sb.append(stringList.get(i));
            if (i != stringList.size() - 1) {
                //if not last
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
