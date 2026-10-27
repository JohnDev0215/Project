package defpackage;

/* JADX INFO: compiled from: Range.java */
/* JADX INFO: loaded from: classes3.dex */
public final class y82 {
    public int a;
    public int b;

    public y82(int i, int i2) {
        this.a = Math.min(i, i2);
        this.b = Math.max(i, i2);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof y82)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        y82 y82Var = (y82) obj;
        return this.a == y82Var.a && this.b == y82Var.b;
    }

    public final int hashCode() {
        return (this.b * 31) + (this.a * 11);
    }

    public final String toString() {
        return "[ " + this.a + ", " + this.b + " ]";
    }
}
