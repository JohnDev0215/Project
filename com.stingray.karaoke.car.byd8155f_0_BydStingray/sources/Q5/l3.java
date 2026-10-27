package Q5;

import T.C0753m;
import T.C0761q;
import com.stingray.drivemodedetector.R;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;
import z7.InterfaceC2789r;

/* JADX INFO: loaded from: classes.dex */
public final class l3 implements InterfaceC2789r {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f6949m;

    public /* synthetic */ l3(InterfaceC2772a interfaceC2772a, int i7) {
        this.l = i7;
        this.f6949m = interfaceC2772a;
    }

    @Override // z7.InterfaceC2789r
    public final Object k(g0.o oVar, Integer num, Boolean bool, final InterfaceC2772a interfaceC2772a, Object obj, Integer num2) {
        int i7;
        int i10;
        int i11;
        switch (this.l) {
            case 0:
                C0761q c0761q = (C0761q) obj;
                int iIntValue = num2.intValue();
                A7.m.f(oVar, "tileModifier");
                A7.m.f(interfaceC2772a, "onClick");
                if ((iIntValue & 6) == 0) {
                    i7 = (c0761q.f(oVar) ? 4 : 2) | iIntValue;
                } else {
                    i7 = iIntValue;
                }
                if ((iIntValue & 3072) == 0) {
                    i7 |= c0761q.h(interfaceC2772a) ? 2048 : 1024;
                }
                if ((i7 & 9219) == 9218 && c0761q.x()) {
                    c0761q.K();
                } else {
                    String strX = AbstractC2673a.X(R.string.Global_Faq, c0761q);
                    c0761q.Q(876484726);
                    boolean z6 = (i7 & 7168) == 2048;
                    final InterfaceC2772a interfaceC2772a2 = this.f6949m;
                    boolean zF = z6 | c0761q.f(interfaceC2772a2);
                    Object objG = c0761q.G();
                    if (zF || objG == C0753m.f8270a) {
                        final int i12 = 0;
                        objG = new InterfaceC2772a() { // from class: Q5.k3
                            @Override // z7.InterfaceC2772a
                            public final Object a() {
                                switch (i12) {
                                    case 0:
                                        interfaceC2772a.a();
                                        interfaceC2772a2.a();
                                        break;
                                    case 1:
                                        interfaceC2772a.a();
                                        interfaceC2772a2.a();
                                        break;
                                    default:
                                        interfaceC2772a.a();
                                        interfaceC2772a2.a();
                                        break;
                                }
                                return m7.q.f19267a;
                            }
                        };
                        c0761q.a0(objG);
                    }
                    c0761q.p(false);
                    com.bumptech.glide.c.u(oVar, strX, null, (InterfaceC2772a) objG, c0761q, i7 & 14);
                }
                break;
            case 1:
                C0761q c0761q2 = (C0761q) obj;
                int iIntValue2 = num2.intValue();
                A7.m.f(oVar, "tileModifier");
                A7.m.f(interfaceC2772a, "onClick");
                if ((iIntValue2 & 6) == 0) {
                    i10 = (c0761q2.f(oVar) ? 4 : 2) | iIntValue2;
                } else {
                    i10 = iIntValue2;
                }
                if ((iIntValue2 & 3072) == 0) {
                    i10 |= c0761q2.h(interfaceC2772a) ? 2048 : 1024;
                }
                if ((i10 & 9219) == 9218 && c0761q2.x()) {
                    c0761q2.K();
                } else {
                    String strX2 = AbstractC2673a.X(R.string.Global_ContactUs, c0761q2);
                    c0761q2.Q(876493340);
                    boolean z10 = (i10 & 7168) == 2048;
                    final InterfaceC2772a interfaceC2772a3 = this.f6949m;
                    boolean zF2 = z10 | c0761q2.f(interfaceC2772a3);
                    Object objG2 = c0761q2.G();
                    if (zF2 || objG2 == C0753m.f8270a) {
                        final int i13 = 1;
                        objG2 = new InterfaceC2772a() { // from class: Q5.k3
                            @Override // z7.InterfaceC2772a
                            public final Object a() {
                                switch (i13) {
                                    case 0:
                                        interfaceC2772a.a();
                                        interfaceC2772a3.a();
                                        break;
                                    case 1:
                                        interfaceC2772a.a();
                                        interfaceC2772a3.a();
                                        break;
                                    default:
                                        interfaceC2772a.a();
                                        interfaceC2772a3.a();
                                        break;
                                }
                                return m7.q.f19267a;
                            }
                        };
                        c0761q2.a0(objG2);
                    }
                    c0761q2.p(false);
                    com.bumptech.glide.c.u(oVar, strX2, null, (InterfaceC2772a) objG2, c0761q2, i10 & 14);
                }
                break;
            default:
                C0761q c0761q3 = (C0761q) obj;
                int iIntValue3 = num2.intValue();
                A7.m.f(oVar, "tileModifier");
                A7.m.f(interfaceC2772a, "onClick");
                if ((iIntValue3 & 6) == 0) {
                    i11 = (c0761q3.f(oVar) ? 4 : 2) | iIntValue3;
                } else {
                    i11 = iIntValue3;
                }
                if ((iIntValue3 & 3072) == 0) {
                    i11 |= c0761q3.h(interfaceC2772a) ? 2048 : 1024;
                }
                if ((i11 & 9219) == 9218 && c0761q3.x()) {
                    c0761q3.K();
                } else {
                    String strX3 = AbstractC2673a.X(R.string.Global_About, c0761q3);
                    c0761q3.Q(876501848);
                    boolean z11 = (i11 & 7168) == 2048;
                    final InterfaceC2772a interfaceC2772a4 = this.f6949m;
                    boolean zF3 = z11 | c0761q3.f(interfaceC2772a4);
                    Object objG3 = c0761q3.G();
                    if (zF3 || objG3 == C0753m.f8270a) {
                        final int i14 = 2;
                        objG3 = new InterfaceC2772a() { // from class: Q5.k3
                            @Override // z7.InterfaceC2772a
                            public final Object a() {
                                switch (i14) {
                                    case 0:
                                        interfaceC2772a.a();
                                        interfaceC2772a4.a();
                                        break;
                                    case 1:
                                        interfaceC2772a.a();
                                        interfaceC2772a4.a();
                                        break;
                                    default:
                                        interfaceC2772a.a();
                                        interfaceC2772a4.a();
                                        break;
                                }
                                return m7.q.f19267a;
                            }
                        };
                        c0761q3.a0(objG3);
                    }
                    c0761q3.p(false);
                    com.bumptech.glide.c.u(oVar, strX3, null, (InterfaceC2772a) objG3, c0761q3, i11 & 14);
                }
                break;
        }
        return m7.q.f19267a;
    }
}
