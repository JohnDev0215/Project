package defpackage;

import android.graphics.Insets;

/* JADX INFO: compiled from: Insets.java */
/* JADX INFO: loaded from: classes.dex */
public final class o7 {
    public static final o7 a = new o7(0, 0, 0, 0);
    public final int b;
    public final int c;
    public final int d;
    public final int e;

    public o7(int i, int i2, int i3, int i4) {
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = i4;
    }

    public static o7 a(int i, int i2, int i3, int i4) {
        return (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) ? a : new o7(i, i2, i3, i4);
    }

    public static o7 b(Insets insets) {
        return a(insets.left, insets.top, insets.right, insets.bottom);
    }

    public Insets c() {
        return Insets.of(this.b, this.c, this.d, this.e);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || o7.class != obj.getClass()) {
            return false;
        }
        o7 o7Var = (o7) obj;
        return this.e == o7Var.e && this.b == o7Var.b && this.d == o7Var.d && this.c == o7Var.c;
    }

    public int hashCode() {
        return (((((this.b * 31) + this.c) * 31) + this.d) * 31) + this.e;
    }

    public String toString() {
        return "Insets{left=" + this.b + ", top=" + this.c + ", right=" + this.d + ", bottom=" + this.e + '}';
    }
}
