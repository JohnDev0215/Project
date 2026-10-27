package g.a.e.b.e;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final p f5675a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g f5676b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f5677c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f5678d;

    public q(int i2, g.a.b.n nVar) {
        if (i2 < 2) {
            throw new IllegalArgumentException("height must be >= 2");
        }
        Objects.requireNonNull(nVar, "digest == null");
        g gVar = new g(new i(nVar));
        this.f5676b = gVar;
        this.f5677c = i2;
        this.f5678d = a();
        this.f5675a = b.b(b().getAlgorithmName(), c(), g(), gVar.d().c(), i2);
    }

    public final int a() {
        int i2 = 2;
        while (true) {
            int i3 = this.f5677c;
            if (i2 > i3) {
                throw new IllegalStateException("should never happen...");
            }
            if ((i3 - i2) % 2 == 0) {
                return i2;
            }
            i2++;
        }
    }

    public g.a.b.n b() {
        return this.f5676b.d().a();
    }

    public int c() {
        return this.f5676b.d().b();
    }

    public int d() {
        return this.f5677c;
    }

    public int e() {
        return this.f5678d;
    }

    public g f() {
        return this.f5676b;
    }

    public int g() {
        return this.f5676b.d().d();
    }
}
