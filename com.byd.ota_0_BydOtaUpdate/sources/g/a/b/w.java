package g.a.b;

import org.bouncycastle.crypto.DataLengthException;

/* JADX INFO: loaded from: classes.dex */
public interface w {
    String getAlgorithmName();

    void init(boolean z, i iVar) throws IllegalArgumentException;

    int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) throws DataLengthException;

    void reset();
}
