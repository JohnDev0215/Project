package com.byd.auto_emanual.util;

import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ListUtil {
    public static <T> String valueToString(List<T> list) {
        if (isNullOrEmpty(list)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString()).append(",");
        }
        return sb.toString();
    }

    public static <T> boolean isNullOrEmpty(List<T> list) {
        return list == null || list.size() == 0;
    }

    public static <T> boolean isIndexLegal(List<T> list, int index) {
        return list != null && index >= 0 && index < list.size();
    }
}
