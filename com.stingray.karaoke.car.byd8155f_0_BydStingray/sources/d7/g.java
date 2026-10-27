package d7;

import k7.C1707a;

/* JADX INFO: loaded from: classes.dex */
public final class g implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ b7.h f13500m;

    public /* synthetic */ g(b7.h hVar, int i7) {
        this.l = i7;
        this.f13500m = hVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                this.f13500m.N0("error", new a("No transports available"));
                break;
            default:
                b7.h hVar = this.f13500m;
                hVar.getClass();
                if (hVar.f13513A.size() != 0) {
                    String str = (String) hVar.f13513A.get(0);
                    hVar.f13523L = 1;
                    o oVarB1 = hVar.b1(str);
                    l.a1(hVar, oVarB1);
                    C1707a.a(new m(oVarB1, 0));
                } else {
                    C1707a.b(new g(hVar, 0));
                }
                break;
        }
    }
}
