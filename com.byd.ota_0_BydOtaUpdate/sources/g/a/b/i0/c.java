package g.a.b.i0;

import java.security.SecureRandom;
import org.bouncycastle.crypto.InvalidCipherTextException;

/* JADX INFO: loaded from: classes.dex */
public class c implements a {
    @Override // g.a.b.i0.a
    public int a(byte[] bArr) throws InvalidCipherTextException {
        int length = bArr.length - 1;
        while (length > 0 && bArr[length] == 0) {
            length--;
        }
        if (bArr[length] == -128) {
            return bArr.length - length;
        }
        throw new InvalidCipherTextException("pad block corrupted");
    }

    @Override // g.a.b.i0.a
    public int b(byte[] bArr, int i2) {
        int length = bArr.length - i2;
        bArr[i2] = -128;
        while (true) {
            i2++;
            if (i2 >= bArr.length) {
                return length;
            }
            bArr[i2] = 0;
        }
    }

    @Override // g.a.b.i0.a
    public void c(SecureRandom secureRandom) throws IllegalArgumentException {
    }
}
