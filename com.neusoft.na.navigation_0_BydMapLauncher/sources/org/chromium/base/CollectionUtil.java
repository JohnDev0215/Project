package org.chromium.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public final class CollectionUtil {
    private CollectionUtil() {
    }

    @SafeVarargs
    public static <E> HashSet<E> newHashSet(E... eArr) {
        HashSet<E> hashSet = new HashSet<>(eArr.length);
        Collections.addAll(hashSet, eArr);
        return hashSet;
    }

    @SafeVarargs
    public static <E> ArrayList<E> newArrayList(E... eArr) {
        ArrayList<E> arrayList = new ArrayList<>(eArr.length);
        Collections.addAll(arrayList, eArr);
        return arrayList;
    }

    public static <E> ArrayList<E> newArrayList(Iterable<E> iterable) {
        ArrayList<E> arrayList = new ArrayList<>();
        Iterator<E> it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }
}
