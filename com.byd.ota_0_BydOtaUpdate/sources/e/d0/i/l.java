package e.d0.i;

import java.util.Arrays;

/* JADX INFO: compiled from: Settings.java */
/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3520a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int[] f3521b = new int[10];

    public void a() {
        this.f3520a = 0;
        Arrays.fill(this.f3521b, 0);
    }

    public int b(int i2) {
        return this.f3521b[i2];
    }

    public int c() {
        if ((this.f3520a & 2) != 0) {
            return this.f3521b[1];
        }
        return -1;
    }

    public int d() {
        if ((this.f3520a & 128) != 0) {
            return this.f3521b[7];
        }
        return 65535;
    }

    public int e(int i2) {
        return (this.f3520a & 16) != 0 ? this.f3521b[4] : i2;
    }

    public int f(int i2) {
        return (this.f3520a & 32) != 0 ? this.f3521b[5] : i2;
    }

    public boolean g(int i2) {
        return ((1 << i2) & this.f3520a) != 0;
    }

    public void h(l lVar) {
        for (int i2 = 0; i2 < 10; i2++) {
            if (lVar.g(i2)) {
                i(i2, lVar.b(i2));
            }
        }
    }

    public l i(int i2, int i3) {
        int[] iArr = this.f3521b;
        if (i2 >= iArr.length) {
            return this;
        }
        this.f3520a = (1 << i2) | this.f3520a;
        iArr[i2] = i3;
        return this;
    }

    public int j() {
        return Integer.bitCount(this.f3520a);
    }
}
