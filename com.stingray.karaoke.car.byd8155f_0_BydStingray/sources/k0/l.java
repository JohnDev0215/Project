package K0;

import C0.c0;
import E0.d0;
import F0.AbstractC0207i;
import L0.o;
import L0.p;
import S8.AbstractC0727y;
import T.C0735d;
import T.C0738e0;
import T.S;
import android.graphics.Point;
import android.view.ScrollCaptureTarget;
import android.view.View;
import java.util.function.Consumer;
import n0.AbstractC1861K;
import p7.C2111a;
import q7.InterfaceC2194h;
import x3.AbstractC2673a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0738e0 f3503a = C0735d.O(Boolean.FALSE, S.f8224q);

    public final void a(View view, p pVar, InterfaceC2194h interfaceC2194h, Consumer<ScrollCaptureTarget> consumer) {
        V.d dVar = new V.d(new m[16]);
        io.sentry.config.a.p0(pVar.a(), 0, new k(1, dVar, V.d.class, "add", "add(Ljava/lang/Object;)Z", 8, 0));
        dVar.q(new C2111a(new InterfaceC2782k[]{d.o, d.f3489p}, 0));
        m mVar = (m) (dVar.l() ? null : dVar.l[dVar.f9279n - 1]);
        if (mVar == null) {
            return;
        }
        X8.c cVarA = AbstractC0727y.a(interfaceC2194h);
        o oVar = mVar.f3504a;
        a1.i iVar = mVar.c;
        f fVar = new f(oVar, iVar, cVarA, this);
        d0 d0Var = mVar.d;
        m0.d dVarO = c0.h(d0Var).o(d0Var, true);
        long jD = AbstractC2673a.d(iVar.f11035a, iVar.f11036b);
        ScrollCaptureTarget scrollCaptureTargetK = AbstractC0207i.k(view, AbstractC1861K.A(C7.a.X(dVarO)), new Point((int) (jD >> 32), (int) (jD & 4294967295L)), fVar);
        scrollCaptureTargetK.setScrollBounds(AbstractC1861K.A(iVar));
        consumer.accept(scrollCaptureTargetK);
    }
}
