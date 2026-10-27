package g.a.b.j0;

/* JADX INFO: loaded from: classes.dex */
public class d extends c {
    public static boolean d(byte[] bArr, int i2) {
        boolean z = false;
        for (int i3 = i2; i3 != i2 + 8; i3++) {
            if (bArr[i3] != bArr[i3 + 8]) {
                z = true;
            }
        }
        return z;
    }

    public static boolean e(byte[] bArr, int i2) {
        int i3 = i2;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        while (true) {
            boolean z4 = true;
            if (i3 == i2 + 8) {
                break;
            }
            int i4 = i3 + 8;
            z |= bArr[i3] != bArr[i4];
            int i5 = i3 + 16;
            z2 |= bArr[i3] != bArr[i5];
            if (bArr[i4] == bArr[i5]) {
                z4 = false;
            }
            z3 |= z4;
            i3++;
        }
        return z && z2 && z3;
    }

    public static boolean f(byte[] bArr, int i2) {
        return bArr.length == 16 ? d(bArr, i2) : e(bArr, i2);
    }

    public static boolean g(byte[] bArr, int i2, int i3) {
        while (i2 < i3) {
            if (c.b(bArr, i2)) {
                return true;
            }
            i2 += 8;
        }
        return false;
    }
}
