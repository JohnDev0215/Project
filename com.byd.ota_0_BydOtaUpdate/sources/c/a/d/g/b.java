package c.a.d.g;

import c.a.b.d.e;
import c.a.d.f.g;

/* JADX INFO: compiled from: KeyEventListener.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements g.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2487a = "b";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c.a.a.a f2488b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c.a.d.a f2489c;

    public b(c.a.a.a aVar, c.a.d.a aVar2) {
        this.f2488b = aVar;
        this.f2489c = aVar2;
    }

    @Override // c.a.d.f.g.b
    public void a() {
        e.c(f2487a, "Intent.ACTION_USER_PRESENT");
    }

    @Override // c.a.d.f.g.b
    public void b() {
        e.c(f2487a, "onHomePressed");
        if (this.f2488b.k()) {
            return;
        }
        this.f2489c.n();
    }

    @Override // c.a.d.f.g.b
    public void c() {
        e.c(f2487a, "Intent.ACTION_SCREEN_ON");
    }

    @Override // c.a.d.f.g.b
    public void d() {
        e.c(f2487a, "onHomeLongPressed");
    }

    @Override // c.a.d.f.g.b
    public void e() {
        e.c(f2487a, "Intent.ACTION_SCREEN_OFF");
    }
}
