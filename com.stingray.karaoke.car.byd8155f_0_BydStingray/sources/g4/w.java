package g4;

import l3.P;

/* JADX INFO: loaded from: classes.dex */
public final class w implements x, B4.b {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final P f14958p = B4.d.a(20, new Y4.e(21));
    public final B4.e l = new B4.e();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public x f14959m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f14960n;
    public boolean o;

    public final synchronized void a() {
        this.l.a();
        if (!this.f14960n) {
            throw new IllegalStateException("Already unlocked");
        }
        this.f14960n = false;
        if (this.o) {
            e();
        }
    }

    @Override // g4.x
    public final int b() {
        return this.f14959m.b();
    }

    @Override // B4.b
    public final B4.e c() {
        return this.l;
    }

    @Override // g4.x
    public final Class d() {
        return this.f14959m.d();
    }

    @Override // g4.x
    public final synchronized void e() {
        this.l.a();
        this.o = true;
        if (!this.f14960n) {
            this.f14959m.e();
            this.f14959m = null;
            f14958p.B(this);
        }
    }

    @Override // g4.x
    public final Object get() {
        return this.f14959m.get();
    }
}
