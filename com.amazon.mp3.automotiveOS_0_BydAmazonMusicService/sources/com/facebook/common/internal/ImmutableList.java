package com.facebook.common.internal;

import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: loaded from: classes5.dex */
public class ImmutableList<E> extends ArrayList<E> {
    private ImmutableList(final int capacity) {
        super(capacity);
    }

    public static <E> ImmutableList<E> of(E... elements) {
        ImmutableList<E> immutableList = new ImmutableList<>(elements.length);
        Collections.addAll(immutableList, elements);
        return immutableList;
    }
}
