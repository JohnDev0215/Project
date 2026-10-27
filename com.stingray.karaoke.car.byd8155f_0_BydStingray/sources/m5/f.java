package M5;

import A7.m;
import C.k;
import Q.M;
import T.C0753m;
import T.C0761q;
import g0.o;
import z7.InterfaceC2772a;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class f implements InterfaceC2786o {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f4207m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f4208n;
    public final /* synthetic */ InterfaceC2772a o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ L0.g f4209p;

    public f(boolean z6, String str, L0.g gVar, InterfaceC2772a interfaceC2772a) {
        this.l = 0;
        this.f4207m = z6;
        this.f4208n = str;
        this.f4209p = gVar;
        this.o = interfaceC2772a;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        switch (this.l) {
            case 0:
                o oVar = (o) obj;
                C0761q c0761q = (C0761q) obj2;
                ((Number) obj3).intValue();
                m.f(oVar, "$this$composed");
                c0761q.Q(1805196808);
                c0761q.Q(-1859366852);
                Object objG = c0761q.G();
                if (objG == C0753m.f8270a) {
                    objG = new k();
                    c0761q.a0(objG);
                }
                c0761q.p(false);
                o oVarC = androidx.compose.foundation.a.c(oVar, (k) objG, null, this.f4207m, this.f4208n, this.f4209p, this.o);
                c0761q.p(false);
                return oVarC;
            case 1:
                o oVar2 = (o) obj;
                C0761q c0761q2 = (C0761q) obj2;
                ((Number) obj3).intValue();
                m.f(oVar2, "$this$composed");
                c0761q2.Q(1220498369);
                c0761q2.Q(-703511237);
                Object objG2 = c0761q2.G();
                if (objG2 == C0753m.f8270a) {
                    objG2 = new k();
                    c0761q2.a0(objG2);
                }
                c0761q2.p(false);
                o oVarB = g0.a.b(oVar2, new e((k) objG2, (androidx.compose.material3.c) null, this.f4207m, this.f4208n, this.f4209p, this.o));
                c0761q2.p(false);
                return oVarB;
            default:
                o oVar3 = (o) obj;
                C0761q c0761q3 = (C0761q) obj2;
                ((Number) obj3).intValue();
                m.f(oVar3, "$this$composed");
                c0761q3.Q(704811330);
                c0761q3.Q(706019606);
                Object objG3 = c0761q3.G();
                if (objG3 == C0753m.f8270a) {
                    objG3 = new k();
                    c0761q3.a0(objG3);
                }
                c0761q3.p(false);
                o oVarB2 = g0.a.b(oVar3, new e((k) objG3, M.b(6), this.f4207m, this.f4208n, this.f4209p, this.o));
                c0761q3.p(false);
                return oVarB2;
        }
    }

    public /* synthetic */ f(boolean z6, String str, InterfaceC2772a interfaceC2772a, L0.g gVar, int i7) {
        this.l = i7;
        this.f4207m = z6;
        this.f4208n = str;
        this.o = interfaceC2772a;
        this.f4209p = gVar;
    }
}
