package g.a.b.h0;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;

/* JADX INFO: loaded from: classes.dex */
public interface a {
    void a(byte[] bArr, int i2, int i3);

    byte[] b();

    int doFinal(byte[] bArr, int i2) throws InvalidCipherTextException, IllegalStateException;

    int getOutputSize(int i2);

    g.a.b.e getUnderlyingCipher();

    int getUpdateOutputSize(int i2);

    void init(boolean z, g.a.b.i iVar) throws IllegalArgumentException;

    int processByte(byte b2, byte[] bArr, int i2) throws DataLengthException;

    int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) throws DataLengthException;
}
