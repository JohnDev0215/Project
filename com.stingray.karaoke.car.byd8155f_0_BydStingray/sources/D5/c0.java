package d5;

/* JADX INFO: loaded from: classes.dex */
public final class c0 extends AbstractC1073B {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final c0 f13408t = new c0();
    public final transient Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final transient Object[] f13409p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final transient int f13410q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final transient int f13411r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final transient c0 f13412s;

    public c0() {
        this.o = null;
        this.f13409p = new Object[0];
        this.f13410q = 0;
        this.f13411r = 0;
        this.f13412s = this;
    }

    @Override // d5.K
    public final f0 c() {
        return new f0(this, this.f13409p, this.f13410q, this.f13411r);
    }

    @Override // d5.K
    public final g0 d() {
        return new g0(this, new h0(this.f13409p, this.f13410q, this.f13411r));
    }

    @Override // d5.K, java.util.Map
    public final Object get(Object obj) {
        Object objH = i0.h(this.o, this.f13409p, this.f13411r, this.f13410q, obj);
        if (objH == null) {
            return null;
        }
        return objH;
    }

    @Override // java.util.Map
    public final int size() {
        return this.f13411r;
    }

    public c0(int i7, Object[] objArr) {
        this.f13409p = objArr;
        this.f13411r = i7;
        this.f13410q = 0;
        int iT = i7 >= 2 ? M.t(i7) : 0;
        Object objG = i0.g(objArr, i7, iT, 0);
        if (!(objG instanceof Object[])) {
            this.o = objG;
            Object objG2 = i0.g(objArr, i7, iT, 1);
            if (!(objG2 instanceof Object[])) {
                this.f13412s = new c0(objG2, objArr, i7, this);
                return;
            }
            throw ((J) ((Object[]) objG2)[2]).a();
        }
        throw ((J) ((Object[]) objG)[2]).a();
    }

    public c0(Object obj, Object[] objArr, int i7, c0 c0Var) {
        this.o = obj;
        this.f13409p = objArr;
        this.f13410q = 1;
        this.f13411r = i7;
        this.f13412s = c0Var;
    }
}
