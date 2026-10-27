package c9;

/* JADX INFO: loaded from: classes.dex */
public final class G {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public H0.b f12504a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public D f12505b;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public t f12506e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public B3.m f12508g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public H f12509h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public H f12510i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public H f12511j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public long f12512k;
    public long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public g9.e f12513m;
    public int c = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public A7.D f12507f = new A7.D(5, false);

    public static void b(H h10, String str) {
        if (h10 != null) {
            if (h10.f12518r != null) {
                throw new IllegalArgumentException(str.concat(".body != null").toString());
            }
            if (h10.f12519s != null) {
                throw new IllegalArgumentException(str.concat(".networkResponse != null").toString());
            }
            if (h10.f12520t != null) {
                throw new IllegalArgumentException(str.concat(".cacheResponse != null").toString());
            }
            if (h10.f12521u != null) {
                throw new IllegalArgumentException(str.concat(".priorResponse != null").toString());
            }
        }
    }

    public final H a() {
        int i7 = this.c;
        if (i7 < 0) {
            throw new IllegalStateException(("code < 0: " + this.c).toString());
        }
        H0.b bVar = this.f12504a;
        if (bVar == null) {
            throw new IllegalStateException("request == null");
        }
        D d = this.f12505b;
        if (d == null) {
            throw new IllegalStateException("protocol == null");
        }
        String str = this.d;
        if (str != null) {
            return new H(bVar, d, str, i7, this.f12506e, this.f12507f.m(), this.f12508g, this.f12509h, this.f12510i, this.f12511j, this.f12512k, this.l, this.f12513m);
        }
        throw new IllegalStateException("message == null");
    }

    public final void c(u uVar) {
        A7.m.f(uVar, "headers");
        this.f12507f = uVar.q();
    }
}
