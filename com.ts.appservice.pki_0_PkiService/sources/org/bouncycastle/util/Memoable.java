package org.bouncycastle.util;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public interface Memoable {
    Memoable copy();

    void reset(Memoable memoable);
}
