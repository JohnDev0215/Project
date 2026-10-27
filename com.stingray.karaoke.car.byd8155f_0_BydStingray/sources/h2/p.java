package h2;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f15229a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long[] f15230b;

    public p() {
        this(32);
    }

    public void a(long j3) {
        int i7 = this.f15229a;
        long[] jArr = this.f15230b;
        if (i7 == jArr.length) {
            this.f15230b = Arrays.copyOf(jArr, i7 * 2);
        }
        long[] jArr2 = this.f15230b;
        int i10 = this.f15229a;
        this.f15229a = i10 + 1;
        jArr2[i10] = j3;
    }

    public void b(long j3) {
        if (d(j3)) {
            return;
        }
        int i7 = this.f15229a;
        long[] jArr = this.f15230b;
        if (i7 >= jArr.length) {
            long[] jArrCopyOf = Arrays.copyOf(jArr, Math.max(i7 + 1, jArr.length * 2));
            A7.m.e(jArrCopyOf, "copyOf(this, newSize)");
            this.f15230b = jArrCopyOf;
        }
        this.f15230b[i7] = j3;
        if (i7 >= this.f15229a) {
            this.f15229a = i7 + 1;
        }
    }

    public void c(long[] jArr) {
        int length = this.f15229a + jArr.length;
        long[] jArr2 = this.f15230b;
        if (length > jArr2.length) {
            this.f15230b = Arrays.copyOf(jArr2, Math.max(jArr2.length * 2, length));
        }
        System.arraycopy(jArr, 0, this.f15230b, this.f15229a, jArr.length);
        this.f15229a = length;
    }

    public boolean d(long j3) {
        int i7 = this.f15229a;
        for (int i10 = 0; i10 < i7; i10++) {
            if (this.f15230b[i10] == j3) {
                return true;
            }
        }
        return false;
    }

    public long e(int i7) {
        if (i7 >= 0 && i7 < this.f15229a) {
            return this.f15230b[i7];
        }
        StringBuilder sbJ = M5.b.j("Invalid index ", i7, ", size is ");
        sbJ.append(this.f15229a);
        throw new IndexOutOfBoundsException(sbJ.toString());
    }

    public void f(int i7) {
        int i10 = this.f15229a;
        if (i7 < i10) {
            int i11 = i10 - 1;
            while (i7 < i11) {
                long[] jArr = this.f15230b;
                int i12 = i7 + 1;
                jArr[i7] = jArr[i12];
                i7 = i12;
            }
            this.f15229a--;
        }
    }

    public p(int i7) {
        this.f15230b = new long[i7];
    }
}
