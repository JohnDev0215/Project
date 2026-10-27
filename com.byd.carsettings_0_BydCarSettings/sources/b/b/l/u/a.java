package b.b.l.u;

/* JADX INFO: compiled from: InstanceFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public class a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d f2043a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Class<? extends T> f2044b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f2045c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object[] f2046d = new Object[0];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Class<?>[] f2047e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public b f2048f;
    public boolean g;

    public a(d dVar) {
        this.f2043a = dVar;
    }

    public T a() {
        this.g = false;
        return (T) this.f2043a.n(this);
    }

    public b b() {
        return this.f2048f;
    }

    public Class<? extends T> c() {
        return this.f2044b;
    }

    public Class<?>[] d() {
        return this.f2047e;
    }

    public Object[] e() {
        return this.f2046d;
    }

    public String f() {
        return this.f2045c;
    }

    public boolean g() {
        return this.g;
    }

    public a<T> h(b bVar) {
        this.f2048f = bVar;
        return this;
    }

    public a<T> i(Class<? extends T> cls) {
        this.f2044b = cls;
        return this;
    }

    public a<T> j(Object... objArr) {
        this.f2046d = objArr;
        return this;
    }

    public a<T> k(String str) {
        this.f2045c = str;
        return this;
    }
}
