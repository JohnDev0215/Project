package com.bydux.api.common.tools.util;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ListUtils {
    public static final String DEFAULT_JOIN_SEPARATOR = ",";

    private ListUtils() {
        throw new AssertionError();
    }

    public static <V> boolean addDistinctEntry(List<V> list, V v7) {
        if (list == null || list.contains(v7)) {
            return false;
        }
        return list.add(v7);
    }

    public static <V> int addDistinctList(List<V> list, List<V> list2) {
        if (list == null || isEmpty(list2)) {
            return 0;
        }
        int size = list.size();
        for (V v7 : list2) {
            if (!list.contains(v7)) {
                list.add(v7);
            }
        }
        return list.size() - size;
    }

    public static <V> boolean addListNotNullValue(List<V> list, V v7) {
        if (list == null || v7 == null) {
            return false;
        }
        return list.add(v7);
    }

    public static <V> int distinctList(List<V> list) {
        int i8 = 0;
        if (isEmpty(list)) {
            return 0;
        }
        int size = list.size();
        int size2 = list.size();
        while (i8 < size2) {
            int i9 = i8 + 1;
            int i10 = i9;
            while (i10 < size2) {
                if (list.get(i8).equals(list.get(i10))) {
                    list.remove(i10);
                    size2 = list.size();
                    i10--;
                }
                i10++;
            }
            i8 = i9;
        }
        return size - list.size();
    }

    public static <V> V getLast(List<V> list, V v7) {
        if (list == null) {
            return null;
        }
        return (V) ArrayUtils.getLast(list.toArray(), v7, true);
    }

    public static <V> V getNext(List<V> list, V v7) {
        if (list == null) {
            return null;
        }
        return (V) ArrayUtils.getNext(list.toArray(), v7, true);
    }

    public static <V> int getSize(List<V> list) {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public static <V> List<V> invertList(List<V> list) {
        if (isEmpty(list)) {
            return list;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (int size = list.size() - 1; size >= 0; size--) {
            arrayList.add(list.get(size));
        }
        return arrayList;
    }

    public static <V> boolean isEmpty(List<V> list) {
        return list == null || list.size() == 0;
    }

    public static <V> boolean isEquals(ArrayList<V> arrayList, ArrayList<V> arrayList2) {
        if (arrayList == null) {
            return arrayList2 == null;
        }
        if (arrayList2 == null || arrayList.size() != arrayList2.size()) {
            return false;
        }
        for (int i8 = 0; i8 < arrayList.size(); i8++) {
            if (!ObjectUtils.isEquals(arrayList.get(i8), arrayList2.get(i8))) {
                return false;
            }
        }
        return true;
    }

    public static String join(List<String> list) {
        return join(list, DEFAULT_JOIN_SEPARATOR);
    }

    public static String join(List<String> list, char c8) {
        return join(list, new String(new char[]{c8}));
    }

    public static String join(List<String> list, String str) {
        return list == null ? "" : TextUtils.join(str, list);
    }
}
