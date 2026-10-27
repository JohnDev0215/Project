package org.apache.commons.compress.utils;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public class Lists {
    public static <E> ArrayList<E> newArrayList() {
        return new ArrayList<>();
    }

    public static <E> ArrayList<E> newArrayList(Iterator<? extends E> it) {
        ArrayList<E> arrayListNewArrayList = newArrayList();
        Iterators.addAll(arrayListNewArrayList, it);
        return arrayListNewArrayList;
    }

    private Lists() {
    }
}
