package b.a.c.u;

/* JADX INFO: compiled from: InstanceFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public class a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d f279a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Class<? extends T> f280b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f281c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object[] f282d = new Object[0];
    public Class<?>[] e;
    public b f;
    public boolean g;

    public a(d dVar) {
        this.f279a = dVar;
    }

    public T a() {
        this.g = false;
        return (T) this.f279a.n(this);
    }

    public b b() {
        return this.f;
    }

    public Class<? extends T> c() {
        return this.f280b;
    }

    public Class<?>[] d() {
        return this.e;
    }

    public Object[] e() {
        return this.f282d;
    }

    public String f() {
        return this.f281c;
    }

    public boolean g() {
        return this.g;
    }

    public a<T> h(b bVar) {
        this.f = bVar;
        return this;
    }

    public a<T> i(Class<? extends T> cls) {
        this.f280b = cls;
        return this;
    }

    public a<T> j(Object... objArr) {
        this.f282d = objArr;
        return this;
    }

    public a<T> k(String str) {
        this.f281c = str;
        return this;
    }
}
