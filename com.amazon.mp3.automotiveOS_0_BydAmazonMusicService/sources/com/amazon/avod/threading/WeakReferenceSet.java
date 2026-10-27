package com.amazon.avod.threading;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes2.dex */
public class WeakReferenceSet {
    public static <T> Set<T> build() {
        return Collections.newSetFromMap(new WeakHashMap());
    }
}
