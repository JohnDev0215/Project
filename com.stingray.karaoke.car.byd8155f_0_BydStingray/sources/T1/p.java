package T1;

/* JADX INFO: loaded from: classes.dex */
public final class p implements o {
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f8508m = -1;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f8509n = -1;

    public p(int i7) {
        this.l = i7;
    }

    @Override // T1.o
    public final boolean a(CharSequence charSequence, int i7, int i10, w wVar) {
        int i11 = this.l;
        if (i7 > i11 || i11 >= i10) {
            return i10 <= i11;
        }
        this.f8508m = i7;
        this.f8509n = i10;
        return false;
    }

    @Override // T1.o
    public final Object c() {
        return this;
    }
}
