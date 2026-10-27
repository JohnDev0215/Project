package org.bouncycastle.util;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public interface Selector<T> extends Cloneable {
    Object clone();

    boolean match(T t);
}
