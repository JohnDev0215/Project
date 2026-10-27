package p;

/* JADX INFO: loaded from: classes.dex */
public final class i51 extends g51 {
    public static final i51 l = new i51(1, 0, 1);

    public final boolean a(int i) {
        return this.c <= i && i <= this.e;
    }

    @Override // p.g51
    public final boolean equals(Object obj) {
        if (obj instanceof i51) {
            if (!isEmpty() || !((i51) obj).isEmpty()) {
                i51 i51Var = (i51) obj;
                if (this.c == i51Var.c) {
                    if (this.e == i51Var.e) {
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override // p.g51
    public final int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (this.c * 31) + this.e;
    }

    @Override // p.g51
    public final boolean isEmpty() {
        return this.c > this.e;
    }

    @Override // p.g51
    public final String toString() {
        return this.c + ".." + this.e;
    }
}
