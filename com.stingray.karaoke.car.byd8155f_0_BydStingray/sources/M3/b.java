package M3;

import A3.o;
import A9.k;
import K3.j;
import K3.p;

/* JADX INFO: loaded from: classes.dex */
public final class b implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final o f4191a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final j f4192b;
    public final int c;
    public final boolean d;

    public b(o oVar, j jVar, int i7, boolean z6) {
        this.f4191a = oVar;
        this.f4192b = jVar;
        this.c = i7;
        this.d = z6;
        if (i7 <= 0) {
            throw new IllegalArgumentException("durationMillis must be > 0.");
        }
    }

    @Override // M3.f
    public final void a() {
        this.f4191a.getClass();
        j jVar = this.f4192b;
        boolean z6 = jVar instanceof p;
        new D3.a(jVar.a(), jVar.b().f3560w, this.c, (z6 && ((p) jVar).f3582g) ? false : true, this.d);
        if (!z6 && !(jVar instanceof K3.e)) {
            throw new k();
        }
    }
}
