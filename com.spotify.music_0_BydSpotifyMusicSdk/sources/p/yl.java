package p;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class yl implements Cloneable {
    public final int c;
    public final int e;
    public final int k;
    public final int[] l;

    public yl(int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            throw new IllegalArgumentException("Both dimensions must be greater than 0");
        }
        this.c = i;
        this.e = i2;
        int i3 = (i + 31) / 32;
        this.k = i3;
        this.l = new int[i3 * i2];
    }

    public final Object clone() {
        int[] iArr = (int[]) this.l.clone();
        return new yl(this.c, this.e, this.k, iArr);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof yl)) {
            return false;
        }
        yl ylVar = (yl) obj;
        return this.c == ylVar.c && this.e == ylVar.e && this.k == ylVar.k && Arrays.equals(this.l, ylVar.l);
    }

    public final int hashCode() {
        int i = this.c;
        return Arrays.hashCode(this.l) + (((((((i * 31) + i) * 31) + this.e) * 31) + this.k) * 31);
    }

    public final String toString() {
        int i = this.c;
        int i2 = this.e;
        StringBuilder sb = new StringBuilder((i + 1) * i2);
        for (int i3 = 0; i3 < i2; i3++) {
            for (int i4 = 0; i4 < i; i4++) {
                sb.append(((this.l[(i4 / 32) + (this.k * i3)] >>> (i4 & 31)) & 1) != 0 ? "X " : "  ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public yl(int i, int i2, int i3, int[] iArr) {
        this.c = i;
        this.e = i2;
        this.k = i3;
        this.l = iArr;
    }
}
