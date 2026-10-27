package org.bouncycastle.crypto;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public interface StreamCipher {
    String getAlgorithmName();

    void init(boolean z, CipherParameters cipherParameters);

    int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3);

    void reset();

    byte returnByte(byte b2);
}
