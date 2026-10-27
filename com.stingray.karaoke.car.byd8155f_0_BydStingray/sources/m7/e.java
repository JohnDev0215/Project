package m7;

/* JADX INFO: loaded from: classes.dex */
public final class e implements Comparable {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final e f19254p = new e(2, 1, 0);
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f19255m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f19256n;
    public final int o;

    public e(int i7, int i10, int i11) {
        this.l = i7;
        this.f19255m = i10;
        this.f19256n = i11;
        if (i7 >= 0 && i7 < 256 && i10 >= 0 && i10 < 256 && i11 >= 0 && i11 < 256) {
            this.o = (i7 << 16) + (i10 << 8) + i11;
            return;
        }
        throw new IllegalArgumentException(("Version components are out of range: " + i7 + '.' + i10 + '.' + i11).toString());
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        e eVar = (e) obj;
        A7.m.f(eVar, "other");
        return this.o - eVar.o;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        e eVar = obj instanceof e ? (e) obj : null;
        return eVar != null && this.o == eVar.o;
    }

    public final int hashCode() {
        return this.o;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.l);
        sb.append('.');
        sb.append(this.f19255m);
        sb.append('.');
        sb.append(this.f19256n);
        return sb.toString();
    }
}
