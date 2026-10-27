package g.a.b;

import org.bouncycastle.crypto.DataLengthException;

/* JADX INFO: loaded from: classes.dex */
public interface t {
    int doFinal(byte[] bArr, int i2) throws IllegalStateException, DataLengthException;

    String getAlgorithmName();

    int getMacSize();

    void init(i iVar) throws IllegalArgumentException;

    void reset();

    void update(byte b2) throws IllegalStateException;

    void update(byte[] bArr, int i2, int i3) throws IllegalStateException, DataLengthException;
}
