package spi;

import java.util.Objects;

/* JADX INFO: compiled from: InsetsOEMV1.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a5 {
    public final int a;
    public final int b;
    public final int c;
    public final int d;

    public a5() {
        this.d = 0;
        this.c = 0;
        this.b = 0;
        this.a = 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || a5.class != obj.getClass()) {
            return false;
        }
        a5 a5Var = (a5) obj;
        return this.a == a5Var.a && this.b == a5Var.b && this.c == a5Var.c && this.d == a5Var.d;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(this.a), Integer.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.d));
    }

    public final String toString() {
        StringBuilder sbK = c.k("{ left: ");
        sbK.append(this.a);
        sbK.append(", right: ");
        sbK.append(this.b);
        sbK.append(", top: ");
        sbK.append(this.c);
        sbK.append(", bottom: ");
        sbK.append(this.d);
        sbK.append(" }");
        return sbK.toString();
    }

    public a5(int i, int i2, int i3, int i4) {
        this.a = i;
        this.b = i3;
        this.c = i2;
        this.d = i4;
    }
}
