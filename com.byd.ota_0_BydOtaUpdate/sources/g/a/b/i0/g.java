package g.a.b.i0;

import java.security.SecureRandom;
import org.bouncycastle.crypto.InvalidCipherTextException;

/* JADX INFO: loaded from: classes.dex */
public class g implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public SecureRandom f4995a = null;

    @Override // g.a.b.i0.a
    public int a(byte[] bArr) throws InvalidCipherTextException {
        int i2 = bArr[bArr.length - 1] & 255;
        if (i2 <= bArr.length) {
            return i2;
        }
        throw new InvalidCipherTextException("pad block corrupted");
    }

    @Override // g.a.b.i0.a
    public int b(byte[] bArr, int i2) {
        byte length = (byte) (bArr.length - i2);
        while (i2 < bArr.length - 1) {
            SecureRandom secureRandom = this.f4995a;
            if (secureRandom == null) {
                bArr[i2] = 0;
            } else {
                bArr[i2] = (byte) secureRandom.nextInt();
            }
            i2++;
        }
        bArr[i2] = length;
        return length;
    }

    @Override // g.a.b.i0.a
    public void c(SecureRandom secureRandom) throws IllegalArgumentException {
        this.f4995a = secureRandom;
    }
}
