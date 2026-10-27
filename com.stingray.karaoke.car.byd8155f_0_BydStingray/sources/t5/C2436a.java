package t5;

import java.util.Arrays;

/* JADX INFO: renamed from: t5.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2436a implements Cloneable {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final int[] f22464n = new int[0];

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f22465m = 0;
    public int[] l = f22464n;

    public final void a(boolean z6) {
        c(this.f22465m + 1);
        if (z6) {
            int[] iArr = this.l;
            int i7 = this.f22465m;
            int i10 = i7 / 32;
            iArr[i10] = (1 << (i7 & 31)) | iArr[i10];
        }
        this.f22465m++;
    }

    public final void b(int i7, int i10) {
        if (i10 < 0 || i10 > 32) {
            throw new IllegalArgumentException("Num bits must be between 0 and 32");
        }
        int i11 = this.f22465m;
        c(i11 + i10);
        for (int i12 = i10 - 1; i12 >= 0; i12--) {
            if (((1 << i12) & i7) != 0) {
                int[] iArr = this.l;
                int i13 = i11 / 32;
                iArr[i13] = iArr[i13] | (1 << (i11 & 31));
            }
            i11++;
        }
        this.f22465m = i11;
    }

    public final void c(int i7) {
        if (i7 > this.l.length * 32) {
            int[] iArr = new int[(((int) Math.ceil(i7 / 0.75f)) + 31) / 32];
            int[] iArr2 = this.l;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            this.l = iArr;
        }
    }

    public final Object clone() {
        int[] iArr = (int[]) this.l.clone();
        int i7 = this.f22465m;
        C2436a c2436a = new C2436a();
        c2436a.l = iArr;
        c2436a.f22465m = i7;
        return c2436a;
    }

    public final boolean d(int i7) {
        return (this.l[i7 / 32] & (1 << (i7 & 31))) != 0;
    }

    public final int e() {
        return (this.f22465m + 7) / 8;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C2436a)) {
            return false;
        }
        C2436a c2436a = (C2436a) obj;
        return this.f22465m == c2436a.f22465m && Arrays.equals(this.l, c2436a.l);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.l) + (this.f22465m * 31);
    }

    public final String toString() {
        int i7 = this.f22465m;
        StringBuilder sb = new StringBuilder((i7 / 8) + i7 + 1);
        for (int i10 = 0; i10 < this.f22465m; i10++) {
            if ((i10 & 7) == 0) {
                sb.append(' ');
            }
            sb.append(d(i10) ? 'X' : '.');
        }
        return sb.toString();
    }
}
