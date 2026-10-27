package com.bydux.api.common.tools.util;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ListUtils {
    public static final String DEFAULT_JOIN_SEPARATOR = ",";

    private ListUtils() {
        throw new AssertionError();
    }

    public static <V> int getSize(List<V> sourceList) {
        if (sourceList == null) {
            return 0;
        }
        return sourceList.size();
    }

    public static <V> boolean isEmpty(List<V> sourceList) {
        return sourceList == null || sourceList.size() == 0;
    }

    public static <V> boolean isEquals(ArrayList<V> actual, ArrayList<V> expected) {
        if (actual == null) {
            return expected == null;
        }
        if (expected == null || actual.size() != expected.size()) {
            return false;
        }
        for (int i = 0; i < actual.size(); i++) {
            if (!ObjectUtils.isEquals(actual.get(i), expected.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static String join(List<String> list) {
        return join(list, DEFAULT_JOIN_SEPARATOR);
    }

    public static String join(List<String> list, char separator) {
        return join(list, new String(new char[]{separator}));
    }

    public static String join(List<String> list, String separator) {
        return list == null ? "" : TextUtils.join(separator, list);
    }

    public static <V> boolean addDistinctEntry(List<V> sourceList, V entry) {
        if (sourceList == null || sourceList.contains(entry)) {
            return false;
        }
        return sourceList.add(entry);
    }

    public static <V> int addDistinctList(List<V> sourceList, List<V> entryList) {
        if (sourceList == null || isEmpty(entryList)) {
            return 0;
        }
        int size = sourceList.size();
        for (V v : entryList) {
            if (!sourceList.contains(v)) {
                sourceList.add(v);
            }
        }
        return sourceList.size() - size;
    }

    public static <V> int distinctList(List<V> sourceList) {
        int i = 0;
        if (isEmpty(sourceList)) {
            return 0;
        }
        int size = sourceList.size();
        int size2 = sourceList.size();
        while (i < size2) {
            int i2 = i + 1;
            int i3 = i2;
            while (i3 < size2) {
                if (sourceList.get(i).equals(sourceList.get(i3))) {
                    sourceList.remove(i3);
                    size2 = sourceList.size();
                    i3--;
                }
                i3++;
            }
            i = i2;
        }
        return size - sourceList.size();
    }

    public static <V> boolean addListNotNullValue(List<V> sourceList, V value) {
        if (sourceList == null || value == null) {
            return false;
        }
        return sourceList.add(value);
    }

    public static <V> V getLast(List<V> list, V v) {
        if (list == null) {
            return null;
        }
        return (V) ArrayUtils.getLast(list.toArray(), v, true);
    }

    public static <V> V getNext(List<V> list, V v) {
        if (list == null) {
            return null;
        }
        return (V) ArrayUtils.getNext(list.toArray(), v, true);
    }

    public static <V> List<V> invertList(List<V> sourceList) {
        if (isEmpty(sourceList)) {
            return sourceList;
        }
        ArrayList arrayList = new ArrayList(sourceList.size());
        for (int size = sourceList.size() - 1; size >= 0; size--) {
            arrayList.add(sourceList.get(size));
        }
        return arrayList;
    }
}
