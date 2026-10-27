package Y2;

import java.util.List;
import r2.C2280b;

/* JADX INFO: loaded from: classes.dex */
public final class c extends m2.h implements d {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public d f10633p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public long f10634q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ int f10635r = 1;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Object f10636s;

    public /* synthetic */ c() {
    }

    @Override // Y2.d
    public final int a(long j3) {
        d dVar = this.f10633p;
        dVar.getClass();
        return dVar.a(j3 - this.f10634q);
    }

    @Override // Y2.d
    public final long d(int i7) {
        d dVar = this.f10633p;
        dVar.getClass();
        return dVar.d(i7) + this.f10634q;
    }

    @Override // m2.h
    public final void f() {
        this.f15673m = 0;
        this.f18960n = 0L;
        this.o = false;
        this.f10633p = null;
    }

    @Override // Y2.d
    public final List g(long j3) {
        d dVar = this.f10633p;
        dVar.getClass();
        return dVar.g(j3 - this.f10634q);
    }

    @Override // Y2.d
    public final int h() {
        d dVar = this.f10633p;
        dVar.getClass();
        return dVar.h();
    }

    @Override // m2.h
    public final void i() {
        switch (this.f10635r) {
            case 0:
                ((C2280b) this.f10636s).l(this);
                break;
            default:
                G2.a aVar = (G2.a) this.f10636s;
                aVar.getClass();
                Z2.h hVar = (Z2.h) aVar.f2649m;
                hVar.getClass();
                f();
                hVar.f10881b.add(this);
                break;
        }
    }

    public c(C2280b c2280b) {
        this.f10636s = c2280b;
    }
}
