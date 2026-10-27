package p;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class g51 implements Iterable, g81 {
    public final int c;
    public final int e;
    public final int k;

    public g51(int i, int i2, int i3) {
        if (i3 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if (i3 == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
        }
        this.c = i;
        this.e = v03.t(i, i2, i3);
        this.k = i3;
    }

    public boolean equals(Object obj) {
        if (obj instanceof g51) {
            if (!isEmpty() || !((g51) obj).isEmpty()) {
                g51 g51Var = (g51) obj;
                if (this.c != g51Var.c || this.e != g51Var.e || this.k != g51Var.k) {
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (((this.c * 31) + this.e) * 31) + this.k;
    }

    public boolean isEmpty() {
        int i = this.k;
        int i2 = this.e;
        int i3 = this.c;
        if (i > 0) {
            if (i3 <= i2) {
                return false;
            }
        } else if (i3 >= i2) {
            return false;
        }
        return true;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new h51(this.c, this.e, this.k);
    }

    public String toString() {
        StringBuilder sb;
        int i = this.e;
        int i2 = this.c;
        int i3 = this.k;
        if (i3 > 0) {
            sb = new StringBuilder();
            sb.append(i2);
            sb.append("..");
            sb.append(i);
            sb.append(" step ");
            sb.append(i3);
        } else {
            sb = new StringBuilder();
            sb.append(i2);
            sb.append(" downTo ");
            sb.append(i);
            sb.append(" step ");
            sb.append(-i3);
        }
        return sb.toString();
    }
}
