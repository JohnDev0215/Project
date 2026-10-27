package defpackage;

/* JADX INFO: compiled from: Ranges.kt */
/* JADX INFO: loaded from: classes.dex */
public final class o11 extends m11 {
    static {
        new o11(1, 0);
    }

    public o11(int i, int i2) {
        super(i, i2, 1);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof o11) {
            if (!isEmpty() || !((o11) obj).isEmpty()) {
                o11 o11Var = (o11) obj;
                if (this.e == o11Var.e) {
                    if (this.f == o11Var.f) {
                    }
                }
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return this.f + (this.e * 31);
    }

    public final boolean isEmpty() {
        return this.e > this.f;
    }

    public final String toString() {
        return this.e + ".." + this.f;
    }
}
