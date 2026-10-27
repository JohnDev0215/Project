package com.byd.cleardata.d;

/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private long f2600a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f2601b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f2602c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f2603d;

    class a implements c.a.e<g> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f2604a;

        a(String str) {
            this.f2604a = str;
        }

        @Override // c.a.e
        public void a(c.a.d<g> dVar) {
            try {
                dVar.c(new g(com.byd.cleardata.i.e.j(this.f2604a), com.byd.cleardata.i.e.B(this.f2604a)));
                dVar.a();
            } catch (Throwable th) {
                dVar.b(th);
            }
        }
    }

    public g(long j, long j2) {
        this.f2600a = j;
        this.f2601b = j2;
        c();
    }

    private void c() {
        long j = this.f2601b - this.f2600a;
        this.f2603d = (Long.compare(j, 0L) == 0 || Long.compare(this.f2601b, 0L) == 0) ? 0.0f : (j * 1.0f) / this.f2601b;
        this.f2602c = com.byd.cleardata.i.e.k(j) + "/" + com.byd.cleardata.i.e.k(this.f2601b);
    }

    public static c.a.c<g> d(String str) {
        return c.a.c.c(new a(str));
    }

    public float a() {
        return this.f2603d;
    }

    public String b() {
        return this.f2602c;
    }
}
