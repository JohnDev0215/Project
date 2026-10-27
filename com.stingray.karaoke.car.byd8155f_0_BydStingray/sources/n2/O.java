package n2;

import android.util.Pair;
import u2.C2492C;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class O implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Q f19573m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Pair f19574n;

    public /* synthetic */ O(Q q4, Pair pair, int i7) {
        this.l = i7;
        this.f19573m = q4;
        this.f19574n = pair;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                o2.e eVar = (o2.e) this.f19573m.f19579m.f10764i;
                Pair pair = this.f19574n;
                eVar.b(((Integer) pair.first).intValue(), (C2492C) pair.second);
                break;
            case 1:
                o2.e eVar2 = (o2.e) this.f19573m.f19579m.f10764i;
                Pair pair2 = this.f19574n;
                eVar2.n(((Integer) pair2.first).intValue(), (C2492C) pair2.second);
                break;
            default:
                o2.e eVar3 = (o2.e) this.f19573m.f19579m.f10764i;
                Pair pair3 = this.f19574n;
                eVar3.C(((Integer) pair3.first).intValue(), (C2492C) pair3.second);
                break;
        }
    }
}
