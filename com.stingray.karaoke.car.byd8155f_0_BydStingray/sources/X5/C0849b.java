package X5;

import Q5.C0611q1;
import T.C0753m;
import T.C0761q;
import java.io.Serializable;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2788q;

/* JADX INFO: renamed from: X5.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0849b implements InterfaceC2788q {
    public static final C0849b l = new C0849b();

    @Override // z7.InterfaceC2788q
    public final Object e(Object obj, Object obj2, Object obj3, Object obj4, Serializable serializable) {
        int i7;
        g0.o oVar = (g0.o) obj;
        String str = (String) obj2;
        InterfaceC2772a interfaceC2772a = (InterfaceC2772a) obj3;
        C0761q c0761q = (C0761q) obj4;
        int iIntValue = ((Number) serializable).intValue();
        A7.m.f(oVar, "mod");
        A7.m.f(interfaceC2772a, "triggerAnimation");
        if ((iIntValue & 6) == 0) {
            i7 = (c0761q.f(oVar) ? 4 : 2) | iIntValue;
        } else {
            i7 = iIntValue;
        }
        if ((iIntValue & 48) == 0) {
            i7 |= c0761q.f(str) ? 32 : 16;
        }
        if ((iIntValue & 384) == 0) {
            i7 |= c0761q.h(interfaceC2772a) ? 256 : 128;
        }
        if ((i7 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
        } else {
            c0761q.Q(765523256);
            boolean z6 = (i7 & 896) == 256;
            Object objG = c0761q.G();
            if (z6 || objG == C0753m.f8270a) {
                objG = new C0611q1(interfaceC2772a, 5);
                c0761q.a0(objG);
            }
            c0761q.p(false);
            l9.l.o(oVar, str, null, (InterfaceC2782k) objG, c0761q, i7 & 126);
        }
        return m7.q.f19267a;
    }
}
