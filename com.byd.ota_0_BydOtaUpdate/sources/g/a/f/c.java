package g.a.f;

import g.a.b.a0.y;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static char[] f5724a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final byte[] f5725b;

    public c(byte[] bArr) {
        this.f5725b = a(bArr);
    }

    public static byte[] a(byte[] bArr) {
        y yVar = new y(160);
        yVar.update(bArr, 0, bArr.length);
        byte[] bArr2 = new byte[yVar.getDigestSize()];
        yVar.doFinal(bArr2, 0);
        return bArr2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof c) {
            return a.a(((c) obj).f5725b, this.f5725b);
        }
        return false;
    }

    public int hashCode() {
        return a.z(this.f5725b);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 != this.f5725b.length; i2++) {
            if (i2 > 0) {
                stringBuffer.append(":");
            }
            stringBuffer.append(f5724a[(this.f5725b[i2] >>> 4) & 15]);
            stringBuffer.append(f5724a[this.f5725b[i2] & 15]);
        }
        return stringBuffer.toString();
    }
}
