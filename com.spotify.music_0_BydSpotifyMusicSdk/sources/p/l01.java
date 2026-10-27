package p;

/* JADX INFO: loaded from: classes.dex */
public final class l01 {
    public static final l01 g;
    public final int[] a;
    public final int[] b;
    public final m01 c;
    public final int d;
    public final int e;
    public final int f;

    static {
        new l01(4201, 4096, 1);
        new l01(1033, 1024, 1);
        new l01(67, 64, 1);
        new l01(19, 16, 1);
        g = new l01(285, 256, 0);
        new l01(301, 256, 1);
    }

    public l01(int i, int i2, int i3) {
        this.e = i;
        this.d = i2;
        this.f = i3;
        this.a = new int[i2];
        this.b = new int[i2];
        int i4 = 1;
        int i5 = 1;
        for (int i6 = 0; i6 < i2; i6++) {
            this.a[i6] = i5;
            i5 <<= 1;
            if (i5 >= i2) {
                i5 = (i5 ^ i) & (i2 - 1);
            }
        }
        for (int i7 = 0; i7 < i2 - 1; i7++) {
            this.b[this.a[i7]] = i7;
        }
        this.c = new m01(this, new int[]{0});
        int[] iArr = {1};
        if (iArr.length == 0) {
            throw new IllegalArgumentException();
        }
        int length = iArr.length;
        if (length <= 1 || iArr[0] != 0) {
            return;
        }
        while (i4 < length && iArr[i4] == 0) {
            i4++;
        }
        if (i4 == length) {
            return;
        }
        int[] iArr2 = new int[length - i4];
        System.arraycopy(iArr, i4, iArr2, 0, iArr2.length);
    }

    public final int a(int i, int i2) {
        if (i == 0 || i2 == 0) {
            return 0;
        }
        int[] iArr = this.b;
        return this.a[(iArr[i] + iArr[i2]) % (this.d - 1)];
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("GF(0x");
        sb.append(Integer.toHexString(this.e));
        sb.append(',');
        return u4.k(sb, this.d, ')');
    }
}
