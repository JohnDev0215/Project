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

    public static <V> boolean addDistinctEntry(List<V> list, V v3) {
        if (list == null || list.contains(v3)) {
            return false;
        }
        return list.add(v3);
    }

    public static <V> int addDistinctList(List<V> list, List<V> list2) {
        if (list == null || isEmpty(list2)) {
            return 0;
        }
        int size = list.size();
        for (V v3 : list2) {
            if (!list.contains(v3)) {
                list.add(v3);
            }
        }
        return list.size() - size;
    }

    public static <V> boolean addListNotNullValue(List<V> list, V v3) {
        if (list == null || v3 == null) {
            return false;
        }
        return list.add(v3);
    }

    public static <V> int distinctList(List<V> list) {
        int i3 = 0;
        if (isEmpty(list)) {
            return 0;
        }
        int size = list.size();
        int size2 = list.size();
        while (i3 < size2) {
            int i4 = i3 + 1;
            int i5 = i4;
            while (i5 < size2) {
                if (list.get(i3).equals(list.get(i5))) {
                    list.remove(i5);
                    size2 = list.size();
                    i5--;
                }
                i5++;
            }
            i3 = i4;
        }
        return size - list.size();
    }

    public static <V> V getLast(List<V> list, V v3) {
        if (list == null) {
            return null;
        }
        return (V) ArrayUtils.getLast(list.toArray(), v3, true);
    }

    public static <V> V getNext(List<V> list, V v3) {
        if (list == null) {
            return null;
        }
        return (V) ArrayUtils.getNext(list.toArray(), v3, true);
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
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            if (!ObjectUtils.isEquals(arrayList.get(i3), arrayList2.get(i3))) {
                return false;
            }
        }
        return true;
    }

    public static String join(List<String> list) {
        return join(list, DEFAULT_JOIN_SEPARATOR);
    }

    public static String join(List<String> list, char c3) {
        return join(list, new String(new char[]{c3}));
    }

    public static String join(List<String> list, String str) {
        return list == null ? "" : TextUtils.join(str, list);
    }
}
