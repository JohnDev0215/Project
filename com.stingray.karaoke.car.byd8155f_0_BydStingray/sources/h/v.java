package h;

import X5.F;
import androidx.lifecycle.A;
import androidx.lifecycle.EnumC0980s;
import androidx.lifecycle.InterfaceC0986y;

/* JADX INFO: loaded from: classes.dex */
public final class v implements InterfaceC0986y, InterfaceC1331c {
    public final A9.b l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final p f15141m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public w f15142n;
    public final /* synthetic */ x o;

    public v(x xVar, A9.b bVar, p pVar) {
        A7.m.f(pVar, "onBackPressedCallback");
        this.o = xVar;
        this.l = bVar;
        this.f15141m = pVar;
        bVar.M0(this);
    }

    @Override // androidx.lifecycle.InterfaceC0986y
    public final void b(A a10, EnumC0980s enumC0980s) {
        if (enumC0980s != EnumC0980s.ON_START) {
            if (enumC0980s != EnumC0980s.ON_STOP) {
                if (enumC0980s == EnumC0980s.ON_DESTROY) {
                    cancel();
                    return;
                }
                return;
            } else {
                w wVar = this.f15142n;
                if (wVar != null) {
                    wVar.cancel();
                    return;
                }
                return;
            }
        }
        x xVar = this.o;
        p pVar = this.f15141m;
        xVar.getClass();
        A7.m.f(pVar, "onBackPressedCallback");
        xVar.f15145b.addLast(pVar);
        w wVar2 = new w(xVar, pVar);
        pVar.f15132b.add(wVar2);
        xVar.e();
        pVar.c = new F(0, xVar, x.class, "updateEnabledCallbacks", "updateEnabledCallbacks()V", 0, 7);
        this.f15142n = wVar2;
    }

    @Override // h.InterfaceC1331c
    public final void cancel() {
        this.l.V0(this);
        this.f15141m.f15132b.remove(this);
        w wVar = this.f15142n;
        if (wVar != null) {
            wVar.cancel();
        }
        this.f15142n = null;
    }
}
