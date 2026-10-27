package com.amazon.mp3.util;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class ArrayUtil {
    public static <T> List<T> convertToListExcludingNulls(final T... t) {
        ArrayList arrayList = new ArrayList();
        if (t != null) {
            for (T t2 : t) {
                if (t2 != null) {
                    arrayList.add(t2);
                }
            }
        }
        return arrayList;
    }
}
