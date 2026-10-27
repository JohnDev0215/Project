package com.amplifyframework.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class ForEach {

    @FunctionalInterface
    public interface Mapping<I, O> {
        O apply(I i);
    }

    private ForEach() {
    }

    public static <I, O> List<O> inCollection(Collection<I> collection, Mapping<I, O> mapping) {
        ArrayList arrayList = new ArrayList();
        Iterator<I> it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(mapping.apply(it.next()));
        }
        return Immutable.of(arrayList);
    }

    public static <I, O> List<O> inArray(I[] iArr, Mapping<I, O> mapping) {
        ArrayList arrayList = new ArrayList();
        for (I i : iArr) {
            arrayList.add(mapping.apply(i));
        }
        return Immutable.of(arrayList);
    }
}
