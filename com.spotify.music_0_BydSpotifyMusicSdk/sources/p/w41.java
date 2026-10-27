package p;

import android.graphics.Insets;

/* JADX INFO: loaded from: classes.dex */
public final class w41 {
    public static final w41 e = new w41(0, 0, 0, 0);
    public final int a;
    public final int b;
    public final int c;
    public final int d;

    public w41(int i, int i2, int i3, int i4) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }

    public static w41 a(int i, int i2, int i3, int i4) {
        return (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) ? e : new w41(i, i2, i3, i4);
    }

    public static w41 b(Insets insets) {
        return a(insets.left, insets.top, insets.right, insets.bottom);
    }

    public final Insets c() {
        return v41.a(this.a, this.b, this.c, this.d);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || w41.class != obj.getClass()) {
            return false;
        }
        w41 w41Var = (w41) obj;
        return this.d == w41Var.d && this.a == w41Var.a && this.c == w41Var.c && this.b == w41Var.b;
    }

    public final int hashCode() {
        return (((((this.a * 31) + this.b) * 31) + this.c) * 31) + this.d;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Insets{left=");
        sb.append(this.a);
        sb.append(", top=");
        sb.append(this.b);
        sb.append(", right=");
        sb.append(this.c);
        sb.append(", bottom=");
        return u4.k(sb, this.d, '}');
    }
}
