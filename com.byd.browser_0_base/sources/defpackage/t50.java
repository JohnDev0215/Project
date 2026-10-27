package defpackage;

/* JADX INFO: compiled from: DataHeader.java */
/* JADX INFO: loaded from: classes3.dex */
public final class t50 {
    public final int a;
    public final int b;

    public t50(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || t50.class != obj.getClass()) {
            return false;
        }
        t50 t50Var = (t50) obj;
        return this.b == t50Var.b && this.a == t50Var.a;
    }

    public final int hashCode() {
        return ((this.b + 31) * 31) + this.a;
    }
}
