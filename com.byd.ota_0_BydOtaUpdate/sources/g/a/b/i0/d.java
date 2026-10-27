package g.a.b.i0;

import java.security.SecureRandom;
import org.bouncycastle.crypto.InvalidCipherTextException;

/* JADX INFO: loaded from: classes.dex */
public class d implements a {
    @Override // g.a.b.i0.a
    public int a(byte[] bArr) throws InvalidCipherTextException {
        int i2 = bArr[bArr.length - 1] & 255;
        byte b2 = (byte) i2;
        boolean z = (i2 > bArr.length) | (i2 == 0);
        for (int i3 = 0; i3 < bArr.length; i3++) {
            z |= (bArr.length - i3 <= i2) & (bArr[i3] != b2);
        }
        if (z) {
            throw new InvalidCipherTextException("pad block corrupted");
        }
        return i2;
    }

    @Override // g.a.b.i0.a
    public int b(byte[] bArr, int i2) {
        byte length = (byte) (bArr.length - i2);
        while (i2 < bArr.length) {
            bArr[i2] = length;
            i2++;
        }
        return length;
    }

    @Override // g.a.b.i0.a
    public void c(SecureRandom secureRandom) throws IllegalArgumentException {
    }
}
