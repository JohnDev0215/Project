package g1;

/* JADX INFO: renamed from: g1.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1278e extends o {
    public final Object c;
    public final C1280g d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C1279f f14716e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final C1280g f14717f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final C1279f f14718g;

    public C1278e(Object obj) {
        super(obj);
        this.c = obj;
        this.d = new C1280g(obj, -2, this);
        this.f14716e = new C1279f(obj, 0, this);
        this.f14717f = new C1280g(obj, -1, this);
        this.f14718g = new C1279f(obj, 1, this);
    }

    @Override // g1.o
    public final Object a() {
        return this.c;
    }
}
