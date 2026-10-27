package p;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class xl implements Cloneable {
    public int e = 0;
    public int[] c = new int[1];

    public final void a(boolean z) {
        e(this.e + 1);
        if (z) {
            int[] iArr = this.c;
            int i = this.e;
            int i2 = i / 32;
            iArr[i2] = (1 << (i & 31)) | iArr[i2];
        }
        this.e++;
    }

    public final Object clone() {
        int[] iArr = (int[]) this.c.clone();
        int i = this.e;
        xl xlVar = new xl();
        xlVar.c = iArr;
        xlVar.e = i;
        return xlVar;
    }

    public final void d(int i, int i2) {
        if (i2 < 0 || i2 > 32) {
            throw new IllegalArgumentException("Num bits must be between 0 and 32");
        }
        e(this.e + i2);
        while (i2 > 0) {
            boolean z = true;
            if (((i >> (i2 - 1)) & 1) != 1) {
                z = false;
            }
            a(z);
            i2--;
        }
    }

    public final void e(int i) {
        int[] iArr = this.c;
        if (i > (iArr.length << 5)) {
            int[] iArr2 = new int[(i + 31) / 32];
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            this.c = iArr2;
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof xl)) {
            return false;
        }
        xl xlVar = (xl) obj;
        return this.e == xlVar.e && Arrays.equals(this.c, xlVar.c);
    }

    public final boolean f(int i) {
        return ((1 << (i & 31)) & this.c[i / 32]) != 0;
    }

    public final int g() {
        return (this.e + 7) / 8;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.c) + (this.e * 31);
    }

    public final String toString() {
        int i = this.e;
        StringBuilder sb = new StringBuilder((i / 8) + i + 1);
        for (int i2 = 0; i2 < this.e; i2++) {
            if ((i2 & 7) == 0) {
                sb.append(' ');
            }
            sb.append(f(i2) ? 'X' : '.');
        }
        return sb.toString();
    }
}
