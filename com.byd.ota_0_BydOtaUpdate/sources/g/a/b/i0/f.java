package g.a.b.i0;

import java.security.SecureRandom;
import org.bouncycastle.crypto.InvalidCipherTextException;

/* JADX INFO: loaded from: classes.dex */
public class f implements a {
    @Override // g.a.b.i0.a
    public int a(byte[] bArr) throws InvalidCipherTextException {
        byte b2 = bArr[bArr.length - 1];
        int length = bArr.length - 1;
        while (length > 0 && bArr[length - 1] == b2) {
            length--;
        }
        return bArr.length - length;
    }

    @Override // g.a.b.i0.a
    public int b(byte[] bArr, int i2) {
        int length = bArr.length - i2;
        int i3 = 255;
        if (i2 <= 0 ? (bArr[bArr.length - 1] & 1) != 0 : (bArr[i2 - 1] & 1) != 0) {
            i3 = 0;
        }
        byte b2 = (byte) i3;
        while (i2 < bArr.length) {
            bArr[i2] = b2;
            i2++;
        }
        return length;
    }

    @Override // g.a.b.i0.a
    public void c(SecureRandom secureRandom) throws IllegalArgumentException {
    }
}
