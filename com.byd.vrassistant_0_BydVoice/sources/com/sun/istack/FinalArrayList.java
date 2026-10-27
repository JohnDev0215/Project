package com.sun.istack;

import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: classes3.dex */
public final class FinalArrayList<T> extends ArrayList<T> {
    public FinalArrayList(int i) {
        super(i);
    }

    public FinalArrayList() {
    }

    public FinalArrayList(Collection<? extends T> collection) {
        super(collection);
    }
}
