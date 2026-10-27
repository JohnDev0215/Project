package G7;

/* JADX INFO: loaded from: classes.dex */
public final class d extends b {
    public static final d o = new d(1, 0, 1);

    @Override // G7.b
    public final boolean equals(Object obj) {
        if (obj instanceof d) {
            if (!isEmpty() || !((d) obj).isEmpty()) {
                d dVar = (d) obj;
                if (this.l == dVar.l) {
                    if (this.f2712m == dVar.f2712m) {
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override // G7.b
    public final int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return this.f2712m + (this.l * 31);
    }

    @Override // G7.b
    public final boolean isEmpty() {
        return this.l > this.f2712m;
    }

    @Override // G7.b
    public final String toString() {
        return this.l + ".." + this.f2712m;
    }
}
