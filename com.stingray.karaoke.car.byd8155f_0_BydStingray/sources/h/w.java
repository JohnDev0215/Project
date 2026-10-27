package h;

import n7.C1995j;

/* JADX INFO: loaded from: classes.dex */
public final class w implements InterfaceC1331c {
    public final p l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ x f15143m;

    public w(x xVar, p pVar) {
        A7.m.f(pVar, "onBackPressedCallback");
        this.f15143m = xVar;
        this.l = pVar;
    }

    /* JADX WARN: Type inference failed for: r4v1, types: [A7.j, z7.a] */
    @Override // h.InterfaceC1331c
    public final void cancel() {
        x xVar = this.f15143m;
        C1995j c1995j = xVar.f15145b;
        p pVar = this.l;
        c1995j.remove(pVar);
        if (A7.m.a(xVar.c, pVar)) {
            pVar.a();
            xVar.c = null;
        }
        pVar.f15132b.remove(this);
        ?? r42 = pVar.c;
        if (r42 != 0) {
            r42.a();
        }
        pVar.c = null;
    }
}
