package org.apache.commons.compress.utils;

import java.util.Collections;
import java.util.HashSet;

/* JADX INFO: loaded from: classes4.dex */
public class Sets {
    private Sets() {
    }

    @SafeVarargs
    public static <E> HashSet<E> newHashSet(E... eArr) {
        HashSet<E> hashSet = new HashSet<>(eArr.length);
        Collections.addAll(hashSet, eArr);
        return hashSet;
    }
}
