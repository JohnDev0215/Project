package g.a.b;

import org.bouncycastle.crypto.InvalidCipherTextException;

/* JADX INFO: loaded from: classes.dex */
public interface x {
    byte[] a(byte[] bArr, int i2, int i3);

    byte[] b(byte[] bArr, int i2, int i3) throws InvalidCipherTextException;

    String getAlgorithmName();

    void init(boolean z, i iVar);
}
