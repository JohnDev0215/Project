package d5;

/* JADX INFO: loaded from: classes.dex */
public final class j0 extends M {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final Object[] f13432t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final j0 f13433u;
    public final transient Object[] o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final transient int f13434p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final transient Object[] f13435q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final transient int f13436r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final transient int f13437s;

    static {
        Object[] objArr = new Object[0];
        f13432t = objArr;
        f13433u = new j0(0, 0, 0, objArr, objArr);
    }

    public j0(int i7, int i10, int i11, Object[] objArr, Object[] objArr2) {
        this.o = objArr;
        this.f13434p = i7;
        this.f13435q = objArr2;
        this.f13436r = i10;
        this.f13437s = i11;
    }

    @Override // d5.AbstractC1075D, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        if (obj != null) {
            Object[] objArr = this.f13435q;
            if (objArr.length != 0) {
                int iU = AbstractC1095q.u(obj);
                while (true) {
                    int i7 = iU & this.f13436r;
                    Object obj2 = objArr[i7];
                    if (obj2 == null) {
                        return false;
                    }
                    if (obj2.equals(obj)) {
                        return true;
                    }
                    iU = i7 + 1;
                }
            }
        }
        return false;
    }

    @Override // d5.AbstractC1075D
    public final int d(int i7, Object[] objArr) {
        Object[] objArr2 = this.o;
        int i10 = this.f13437s;
        System.arraycopy(objArr2, 0, objArr, i7, i10);
        return i7 + i10;
    }

    @Override // d5.M, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.f13434p;
    }

    @Override // d5.AbstractC1075D
    public final Object[] l() {
        return this.o;
    }

    @Override // d5.AbstractC1075D
    public final int m() {
        return this.f13437s;
    }

    @Override // d5.AbstractC1075D
    public final int q() {
        return 0;
    }

    @Override // d5.AbstractC1075D
    public final boolean r() {
        return false;
    }

    @Override // d5.AbstractC1075D
    /* JADX INFO: renamed from: s */
    public final s0 iterator() {
        return a().listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.f13437s;
    }

    @Override // d5.M
    public final I w() {
        return I.t(this.f13437s, this.o);
    }
}
