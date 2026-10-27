package org.bouncycastle.crypto.tls;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public interface TlsCipher {
    byte[] decodeCiphertext(long j, short s, byte[] bArr, int i, int i2);

    byte[] encodePlaintext(long j, short s, byte[] bArr, int i, int i2);

    int getPlaintextLimit(int i);
}
