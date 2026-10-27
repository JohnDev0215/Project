package defpackage;

import android.graphics.Insets;
import android.graphics.Rect;

/* JADX INFO: compiled from: Insets.java */
/* JADX INFO: loaded from: classes.dex */
public final class n01 {
    public static final n01 e = new n01(0, 0, 0, 0);
    public final int a;
    public final int b;
    public final int c;
    public final int d;

    public n01(int i, int i2, int i3, int i4) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }

    public static n01 a(n01 n01Var, n01 n01Var2) {
        return b(Math.max(n01Var.a, n01Var2.a), Math.max(n01Var.b, n01Var2.b), Math.max(n01Var.c, n01Var2.c), Math.max(n01Var.d, n01Var2.d));
    }

    public static n01 b(int i, int i2, int i3, int i4) {
        return (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) ? e : new n01(i, i2, i3, i4);
    }

    public static n01 c(Rect rect) {
        return b(rect.left, rect.top, rect.right, rect.bottom);
    }

    public static n01 d(Insets insets) {
        return b(insets.left, insets.top, insets.right, insets.bottom);
    }

    public final Insets e() {
        return Insets.of(this.a, this.b, this.c, this.d);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || n01.class != obj.getClass()) {
            return false;
        }
        n01 n01Var = (n01) obj;
        return this.d == n01Var.d && this.a == n01Var.a && this.c == n01Var.c && this.b == n01Var.b;
    }

    public final int hashCode() {
        return (((((this.a * 31) + this.b) * 31) + this.c) * 31) + this.d;
    }

    public final String toString() {
        return "Insets{left=" + this.a + ", top=" + this.b + ", right=" + this.c + ", bottom=" + this.d + '}';
    }
}
