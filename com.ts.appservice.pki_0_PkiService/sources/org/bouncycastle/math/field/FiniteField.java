package org.bouncycastle.math.field;

import java.math.BigInteger;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public interface FiniteField {
    BigInteger getCharacteristic();

    int getDimension();
}
