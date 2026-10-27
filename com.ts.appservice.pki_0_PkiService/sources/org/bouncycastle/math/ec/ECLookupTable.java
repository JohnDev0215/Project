package org.bouncycastle.math.ec;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public interface ECLookupTable {
    int getSize();

    ECPoint lookup(int i);
}
