package org.bouncycastle.crypto.tls;

import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
public interface TlsCipher {
    byte[] decodeCiphertext(short s, byte[] bArr, int i, int i2) throws IOException;

    byte[] encodePlaintext(short s, byte[] bArr, int i, int i2) throws IOException;
}
