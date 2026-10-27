package org.bouncycastle.crypto;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public interface CharToByteConverter {
    byte[] convert(char[] cArr);

    String getType();
}
