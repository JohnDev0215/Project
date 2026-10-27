package n2;

import android.util.Pair;
import u2.C2492C;
import u2.C2516t;
import u2.C2521y;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class N implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Q f19570m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Pair f19571n;
    public final /* synthetic */ C2516t o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ C2521y f19572p;

    public /* synthetic */ N(Q q4, Pair pair, C2516t c2516t, C2521y c2521y, int i7) {
        this.l = i7;
        this.f19570m = q4;
        this.f19571n = pair;
        this.o = c2516t;
        this.f19572p = c2521y;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                o2.e eVar = (o2.e) this.f19570m.f19579m.f10764i;
                Pair pair = this.f19571n;
                eVar.u(((Integer) pair.first).intValue(), (C2492C) pair.second, this.o, this.f19572p);
                break;
            case 1:
                o2.e eVar2 = (o2.e) this.f19570m.f19579m.f10764i;
                Pair pair2 = this.f19571n;
                eVar2.B(((Integer) pair2.first).intValue(), (C2492C) pair2.second, this.o, this.f19572p);
                break;
            default:
                o2.e eVar3 = (o2.e) this.f19570m.f19579m.f10764i;
                Pair pair3 = this.f19571n;
                eVar3.v(((Integer) pair3.first).intValue(), (C2492C) pair3.second, this.o, this.f19572p);
                break;
        }
    }
}
