package g.a.b.i0;

import java.security.SecureRandom;
import org.bouncycastle.crypto.InvalidCipherTextException;

/* JADX INFO: loaded from: classes.dex */
public class h implements a {
    @Override // g.a.b.i0.a
    public int a(byte[] bArr) throws InvalidCipherTextException {
        int length = bArr.length;
        while (length > 0 && bArr[length - 1] == 0) {
            length--;
        }
        return bArr.length - length;
    }

    @Override // g.a.b.i0.a
    public int b(byte[] bArr, int i2) {
        int length = bArr.length - i2;
        while (i2 < bArr.length) {
            bArr[i2] = 0;
            i2++;
        }
        return length;
    }

    @Override // g.a.b.i0.a
    public void c(SecureRandom secureRandom) throws IllegalArgumentException {
    }
}
