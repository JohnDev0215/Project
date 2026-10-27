package f.k0.k;

import java.util.Arrays;

/* JADX INFO: compiled from: Settings.java */
/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f5420a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int[] f5421b = new int[10];

    void a() {
        this.f5420a = 0;
        Arrays.fill(this.f5421b, 0);
    }

    int b(int i2) {
        return this.f5421b[i2];
    }

    int c() {
        if ((this.f5420a & 2) != 0) {
            return this.f5421b[1];
        }
        return -1;
    }

    int d() {
        if ((this.f5420a & 128) != 0) {
            return this.f5421b[7];
        }
        return 65535;
    }

    int e(int i2) {
        return (this.f5420a & 16) != 0 ? this.f5421b[4] : i2;
    }

    int f(int i2) {
        return (this.f5420a & 32) != 0 ? this.f5421b[5] : i2;
    }

    boolean g(int i2) {
        return ((1 << i2) & this.f5420a) != 0;
    }

    void h(m mVar) {
        for (int i2 = 0; i2 < 10; i2++) {
            if (mVar.g(i2)) {
                i(i2, mVar.b(i2));
            }
        }
    }

    m i(int i2, int i3) {
        if (i2 >= 0) {
            int[] iArr = this.f5421b;
            if (i2 < iArr.length) {
                this.f5420a = (1 << i2) | this.f5420a;
                iArr[i2] = i3;
            }
        }
        return this;
    }

    int j() {
        return Integer.bitCount(this.f5420a);
    }
}
