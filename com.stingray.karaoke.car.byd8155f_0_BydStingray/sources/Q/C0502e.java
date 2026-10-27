package Q;

import V8.InterfaceC0818g;
import e0.C1178r;
import q7.InterfaceC2189c;

/* JADX INFO: renamed from: Q.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0502e implements InterfaceC0818g {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C1178r f5864m;

    public /* synthetic */ C0502e(C1178r c1178r, int i7) {
        this.l = i7;
        this.f5864m = c1178r;
    }

    @Override // V8.InterfaceC0818g
    public final Object f(Object obj, InterfaceC2189c interfaceC2189c) {
        switch (this.l) {
            case 0:
                C.j jVar = (C.j) obj;
                boolean z6 = jVar instanceof C.h;
                C1178r c1178r = this.f5864m;
                if (z6) {
                    c1178r.add(jVar);
                } else if (jVar instanceof C.i) {
                    c1178r.remove(((C.i) jVar).f831a);
                } else if (jVar instanceof C.d) {
                    c1178r.add(jVar);
                } else if (jVar instanceof C.e) {
                    c1178r.remove(((C.e) jVar).f826a);
                } else if (jVar instanceof C.m) {
                    c1178r.add(jVar);
                } else if (jVar instanceof C.n) {
                    c1178r.remove(((C.n) jVar).f835a);
                } else if (jVar instanceof C.l) {
                    c1178r.remove(((C.l) jVar).f833a);
                }
                break;
            default:
                C.j jVar2 = (C.j) obj;
                boolean z10 = jVar2 instanceof C.m;
                C1178r c1178r2 = this.f5864m;
                if (z10) {
                    c1178r2.add(jVar2);
                } else if (jVar2 instanceof C.n) {
                    c1178r2.remove(((C.n) jVar2).f835a);
                } else if (jVar2 instanceof C.l) {
                    c1178r2.remove(((C.l) jVar2).f833a);
                } else if (jVar2 instanceof C.b) {
                    c1178r2.add(jVar2);
                } else if (jVar2 instanceof C.c) {
                    c1178r2.remove(((C.c) jVar2).f825a);
                } else if (jVar2 instanceof C.a) {
                    c1178r2.remove(((C.a) jVar2).f824a);
                }
                break;
        }
        return m7.q.f19267a;
    }
}
