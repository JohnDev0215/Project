package H;

import E0.AbstractC0156f;
import E0.InterfaceC0163m;
import E0.d0;
import android.graphics.Rect;
import android.view.View;
import m7.q;
import s7.AbstractC2410c;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class j implements a {
    public final /* synthetic */ InterfaceC0163m l;

    public j(InterfaceC0163m interfaceC0163m) {
        this.l = interfaceC0163m;
    }

    @Override // H.a
    public final Object k0(d0 d0Var, InterfaceC2772a interfaceC2772a, AbstractC2410c abstractC2410c) {
        View viewV = AbstractC0156f.v(this.l);
        long jN = d0Var.N(0L);
        m0.d dVar = (m0.d) interfaceC2772a.a();
        m0.d dVarH = dVar != null ? dVar.h(jN) : null;
        if (dVarH != null) {
            viewV.requestRectangleOnScreen(new Rect((int) dVarH.f18882a, (int) dVarH.f18883b, (int) dVarH.c, (int) dVarH.d), false);
        }
        return q.f19267a;
    }
}
