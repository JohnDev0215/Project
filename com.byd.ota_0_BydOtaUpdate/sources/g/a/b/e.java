package g.a.b;

import org.bouncycastle.crypto.DataLengthException;

/* JADX INFO: loaded from: classes.dex */
public interface e {
    int a(byte[] bArr, int i2, byte[] bArr2, int i3) throws IllegalStateException, DataLengthException;

    int b();

    String getAlgorithmName();

    void init(boolean z, i iVar) throws IllegalArgumentException;

    void reset();
}
