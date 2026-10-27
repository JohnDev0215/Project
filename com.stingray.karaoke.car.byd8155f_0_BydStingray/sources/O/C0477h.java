package O;

import Q.C0497b0;
import Q.C0523o0;
import Q.U;
import T.C0753m;
import T.C0761q;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: O.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0477h extends A7.o implements InterfaceC2786o {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f4966m = 1;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f4967n;
    public final /* synthetic */ Object o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0477h(U u4, boolean z6) {
        super(3);
        this.f4967n = z6;
        this.o = u4;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        switch (this.f4966m) {
            case 0:
                g0.o oVar = (g0.o) obj;
                C0761q c0761q = (C0761q) obj2;
                ((Number) obj3).intValue();
                c0761q.Q(-196777734);
                long j3 = ((S) c0761q.k(T.f4941a)).f4939a;
                boolean zE = c0761q.e(j3);
                InterfaceC2772a interfaceC2772a = (InterfaceC2772a) this.o;
                boolean zF = zE | c0761q.f(interfaceC2772a);
                boolean z6 = this.f4967n;
                boolean zG = zF | c0761q.g(z6);
                Object objG = c0761q.G();
                if (zG || objG == C0753m.f8270a) {
                    objG = new C0476g(j3, interfaceC2772a, z6);
                    c0761q.a0(objG);
                }
                g0.o oVarB = androidx.compose.ui.draw.a.b(oVar, (InterfaceC2782k) objG);
                c0761q.p(false);
                return oVarB;
            default:
                int iIntValue = ((Number) obj3).intValue();
                C0497b0.f5820a.b((C0523o0) obj, null, this.f4967n, (U) this.o, null, null, 0.0f, 0.0f, (C0761q) obj2, (iIntValue & 14) | 100663296);
                return m7.q.f19267a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0477h(InterfaceC2772a interfaceC2772a, boolean z6) {
        super(3);
        this.o = interfaceC2772a;
        this.f4967n = z6;
    }
}
