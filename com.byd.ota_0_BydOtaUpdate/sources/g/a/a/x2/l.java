package g.a.a.x2;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class l {
    public int a(g.a.d.b.d dVar) {
        return (dVar.u() + 7) / 8;
    }

    public int b(g.a.d.b.e eVar) {
        return (eVar.f() + 7) / 8;
    }

    public byte[] c(BigInteger bigInteger, int i2) {
        byte[] byteArray = bigInteger.toByteArray();
        if (i2 < byteArray.length) {
            byte[] bArr = new byte[i2];
            System.arraycopy(byteArray, byteArray.length - i2, bArr, 0, i2);
            return bArr;
        }
        if (i2 <= byteArray.length) {
            return byteArray;
        }
        byte[] bArr2 = new byte[i2];
        System.arraycopy(byteArray, 0, bArr2, i2 - byteArray.length, byteArray.length);
        return bArr2;
    }
}
