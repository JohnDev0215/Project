package defpackage;

import defpackage.kw;
import org.chromium.chrome.browser.tasks.tab_management.q1;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class jt1 implements kw.a {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ jt1(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // kw.a
    public final void a(kw kwVar) {
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 1:
                yc3 yc3Var = (yc3) obj;
                yc3Var.getClass();
                float fB = kwVar.b();
                yc3Var.D = fB;
                yc3Var.E = fB;
                break;
            case 2:
                um2 um2Var = (um2) obj;
                um2Var.getClass();
                float fB2 = kwVar.b();
                if (um2Var.x != null) {
                    float fMin = Math.min(um2Var.e, um2Var.f) * 0.7f;
                    float fAbs = Math.abs(fB2) < 1.0f ? 1.0f : (Math.abs(fB2 / fMin) * (-0.3f)) + 1.0f;
                    float fS = um2Var.x.s();
                    float fR = um2Var.x.r() / 2.0f;
                    float f = 1.0f - fAbs;
                    um2Var.x.m(m61.h, fS * f);
                    um2Var.x.m(m61.i, fR * f);
                    um2Var.x.m(m61.g, fAbs);
                    um2Var.x.m(m61.s, fAbs);
                    um2Var.x.m(m61.p, Math.abs(fB2) >= 1.0f ? 1.0f - Math.abs(a8.o(fB2 / fMin, -1.0f, 1.0f)) : 1.0f);
                }
                break;
            default:
                q1 q1Var = (q1) obj;
                q1Var.getClass();
                q1Var.G = kwVar.b();
                break;
        }
    }
}
