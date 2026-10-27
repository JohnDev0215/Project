package R5;

import A7.m;
import D.AbstractC0133o;
import D.C0138u;
import D.L;
import F0.C0221p;
import F0.C0223q;
import H7.InterfaceC0321f;
import Q8.o;
import T.C0753m;
import T.C0761q;
import T.S;
import com.stingray.karaoke.car.utils.LayoutMode;
import e2.W;
import g1.AbstractC1274a;
import k6.AbstractC1692D;
import k6.C1696d;
import l9.l;
import l9.n;
import m7.q;
import n0.AbstractC1861K;
import n0.C1863M;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class c implements InterfaceC2786o {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f7516m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f7517n;

    public /* synthetic */ c(int i7, Object obj, boolean z6) {
        this.l = i7;
        this.f7517n = obj;
        this.f7516m = z6;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        L l;
        InterfaceC2782k interfaceC2782k;
        InterfaceC2782k interfaceC2782k2;
        InterfaceC2782k interfaceC2782k3;
        InterfaceC2772a interfaceC2772a;
        InterfaceC2782k interfaceC2782k4;
        switch (this.l) {
            case 0:
                L l10 = (L) obj;
                C0761q c0761q = (C0761q) obj2;
                int iIntValue = ((Number) obj3).intValue();
                m.f(l10, "contentPadding");
                if ((iIntValue & 6) == 0) {
                    iIntValue |= c0761q.f(l10) ? 4 : 2;
                }
                if ((iIntValue & 19) == 18 && c0761q.x()) {
                    c0761q.K();
                } else {
                    h6.d dVar = (h6.d) this.f7517n;
                    C1696d c1696d = dVar.l;
                    String str = (String) com.bumptech.glide.c.R(c1696d.f18250g, c0761q, 0).getValue();
                    boolean zBooleanValue = ((Boolean) com.bumptech.glide.c.R(c1696d.f18249f, c0761q, 0).getValue()).booleanValue();
                    c0761q.Q(-17227337);
                    boolean zH = c0761q.h(dVar);
                    Object objG = c0761q.G();
                    S s6 = C0753m.f8270a;
                    if (zH || objG == s6) {
                        C0223q c0223q = new C0223q(1, dVar, h6.d.class, "setShowDisplayInfo", "setShowDisplayInfo(Z)V", 0, 13);
                        c0761q.a0(c0223q);
                        objG = c0223q;
                    }
                    c0761q.p(false);
                    InterfaceC2782k interfaceC2782k5 = (InterfaceC2782k) ((InterfaceC0321f) objG);
                    boolean zBooleanValue2 = ((Boolean) com.bumptech.glide.c.R(c1696d.f18251h, c0761q, 0).getValue()).booleanValue();
                    c0761q.Q(-17222154);
                    boolean zH2 = c0761q.h(dVar);
                    Object objG2 = c0761q.G();
                    if (zH2 || objG2 == s6) {
                        l = l10;
                        C0223q c0223q2 = new C0223q(1, dVar, h6.d.class, "setForceDriveMode", "setForceDriveMode(Z)V", 0, 15);
                        c0761q.a0(c0223q2);
                        objG2 = c0223q2;
                    } else {
                        l = l10;
                    }
                    c0761q.p(false);
                    InterfaceC2782k interfaceC2782k6 = (InterfaceC2782k) ((InterfaceC0321f) objG2);
                    boolean zBooleanValue3 = ((Boolean) com.bumptech.glide.c.R(c1696d.f18252i, c0761q, 0).getValue()).booleanValue();
                    c0761q.Q(-17217261);
                    boolean zH3 = c0761q.h(dVar);
                    Object objG3 = c0761q.G();
                    if (zH3 || objG3 == s6) {
                        interfaceC2782k = interfaceC2782k6;
                        C0223q c0223q3 = new C0223q(1, dVar, h6.d.class, "setForceParked", "setForceParked(Z)V", 0, 16);
                        c0761q.a0(c0223q3);
                        objG3 = c0223q3;
                    } else {
                        interfaceC2782k = interfaceC2782k6;
                    }
                    c0761q.p(false);
                    InterfaceC2782k interfaceC2782k7 = (InterfaceC2782k) ((InterfaceC0321f) objG3);
                    boolean zBooleanValue4 = ((Boolean) com.bumptech.glide.c.R(c1696d.f18253j, c0761q, 0).getValue()).booleanValue();
                    c0761q.Q(-17211490);
                    boolean zH4 = c0761q.h(dVar);
                    Object objG4 = c0761q.G();
                    if (zH4 || objG4 == s6) {
                        interfaceC2782k2 = interfaceC2782k7;
                        C0223q c0223q4 = new C0223q(1, dVar, h6.d.class, "setForcedPassengerPresent", "setForcedPassengerPresent(Z)V", 0, 17);
                        c0761q.a0(c0223q4);
                        objG4 = c0223q4;
                    } else {
                        interfaceC2782k2 = interfaceC2782k7;
                    }
                    c0761q.p(false);
                    InterfaceC2782k interfaceC2782k8 = (InterfaceC2782k) ((InterfaceC0321f) objG4);
                    boolean zBooleanValue5 = ((Boolean) com.bumptech.glide.c.R(dVar.f15569m, c0761q, 0).getValue()).booleanValue();
                    c0761q.Q(-17206119);
                    boolean zH5 = c0761q.h(dVar);
                    Object objG5 = c0761q.G();
                    if (zH5 || objG5 == s6) {
                        interfaceC2782k3 = interfaceC2782k8;
                        C0221p c0221p = new C0221p(0, dVar, h6.d.class, "toggleForceDarkTheme", "toggleForceDarkTheme()V", 0, 23);
                        c0761q.a0(c0221p);
                        objG5 = c0221p;
                    } else {
                        interfaceC2782k3 = interfaceC2782k8;
                    }
                    c0761q.p(false);
                    InterfaceC2772a interfaceC2772a2 = (InterfaceC2772a) ((InterfaceC0321f) objG5);
                    c0761q.Q(-17204046);
                    boolean zH6 = c0761q.h(dVar);
                    Object objG6 = c0761q.G();
                    if (zH6 || objG6 == s6) {
                        interfaceC2772a = interfaceC2772a2;
                        C0223q c0223q5 = new C0223q(1, dVar, h6.d.class, "setIsDarkMode", "setIsDarkMode(Ljava/lang/Boolean;)V", 0, 18);
                        c0761q.a0(c0223q5);
                        objG6 = c0223q5;
                    } else {
                        interfaceC2772a = interfaceC2772a2;
                    }
                    c0761q.p(false);
                    InterfaceC2782k interfaceC2782k9 = (InterfaceC2782k) ((InterfaceC0321f) objG6);
                    boolean zBooleanValue6 = ((Boolean) com.bumptech.glide.c.R(dVar.o, c0761q, 0).getValue()).booleanValue();
                    c0761q.Q(-17197480);
                    boolean zH7 = c0761q.h(dVar);
                    Object objG7 = c0761q.G();
                    if (zH7 || objG7 == s6) {
                        interfaceC2782k4 = interfaceC2782k9;
                        C0223q c0223q6 = new C0223q(1, dVar, h6.d.class, "setLogBoxVisibility", "setLogBoxVisibility(Z)V", 0, 19);
                        c0761q.a0(c0223q6);
                        objG7 = c0223q6;
                    } else {
                        interfaceC2782k4 = interfaceC2782k9;
                    }
                    c0761q.p(false);
                    InterfaceC2782k interfaceC2782k10 = (InterfaceC2782k) ((InterfaceC0321f) objG7);
                    boolean zBooleanValue7 = ((Boolean) com.bumptech.glide.c.R(dVar.f15571p, c0761q, 0).getValue()).booleanValue();
                    c0761q.Q(-17191749);
                    boolean zH8 = c0761q.h(dVar);
                    Object objG8 = c0761q.G();
                    if (zH8 || objG8 == s6) {
                        C0223q c0223q7 = new C0223q(1, dVar, h6.d.class, "setLogDetailVisibility", "setLogDetailVisibility(Z)V", 0, 20);
                        c0761q.a0(c0223q7);
                        objG8 = c0223q7;
                    }
                    c0761q.p(false);
                    InterfaceC2782k interfaceC2782k11 = (InterfaceC2782k) ((InterfaceC0321f) objG8);
                    String str2 = (String) com.bumptech.glide.c.R(c1696d.f18255m, c0761q, 0).getValue();
                    c0761q.Q(-17187018);
                    boolean zH9 = c0761q.h(dVar);
                    Object objG9 = c0761q.G();
                    if (zH9 || objG9 == s6) {
                        C0223q c0223q8 = new C0223q(1, dVar, h6.d.class, "setForcedDeviceId", "setForcedDeviceId(Ljava/lang/String;)V", 0, 21);
                        c0761q.a0(c0223q8);
                        objG9 = c0223q8;
                    }
                    c0761q.p(false);
                    InterfaceC2782k interfaceC2782k12 = (InterfaceC2782k) ((InterfaceC0321f) objG9);
                    boolean zBooleanValue8 = ((Boolean) com.bumptech.glide.c.R(dVar.f15572q, c0761q, 0).getValue()).booleanValue();
                    c0761q.Q(-17181379);
                    boolean zH10 = c0761q.h(dVar);
                    Object objG10 = c0761q.G();
                    if (zH10 || objG10 == s6) {
                        C0223q c0223q9 = new C0223q(1, dVar, h6.d.class, "setIsScoringDebugEnabled", "setIsScoringDebugEnabled(Z)V", 0, 14);
                        c0761q.a0(c0223q9);
                        objG10 = c0223q9;
                    }
                    c0761q.p(false);
                    l.j(null, str, zBooleanValue, interfaceC2782k5, zBooleanValue2, interfaceC2782k, zBooleanValue3, interfaceC2782k2, zBooleanValue4, interfaceC2782k3, zBooleanValue5, interfaceC2772a, interfaceC2782k4, this.f7516m, zBooleanValue6, interfaceC2782k10, zBooleanValue7, interfaceC2782k11, str2, interfaceC2782k12, zBooleanValue8, (InterfaceC2782k) ((InterfaceC0321f) objG10), l, c0761q, 0, 0, (iIntValue << 6) & 896);
                }
                break;
            default:
                final C0138u c0138u = (C0138u) obj;
                C0761q c0761q2 = (C0761q) obj2;
                int iIntValue2 = ((Number) obj3).intValue();
                m.f(c0138u, "$this$BoxWithConstraints");
                if ((iIntValue2 & 6) == 0) {
                    iIntValue2 |= c0761q2.f(c0138u) ? 4 : 2;
                }
                if ((iIntValue2 & 19) == 18 && c0761q2.x()) {
                    c0761q2.K();
                } else {
                    final float fA = (c0138u.a() * 16.0f) / 9.0f;
                    final float fB = c0138u.b() / fA;
                    c0761q2.Q(-1117475718);
                    if (o.z0("byd8155f", "byd8155f", false)) {
                        l6.d.a(0, c0761q2);
                    }
                    c0761q2.p(false);
                    g0.l lVar = g0.l.f14684a;
                    long j3 = l6.d.c(c0761q2).f18704g;
                    n nVar = AbstractC1861K.f19381a;
                    g0.o oVarC = androidx.compose.foundation.layout.c.c(androidx.compose.foundation.a.b(lVar, j3, nVar), 1.0f);
                    c0761q2.Q(-1117466512);
                    boolean zC = ((iIntValue2 & 14) == 4) | c0761q2.c(fB) | c0761q2.c(fA);
                    Object objG11 = c0761q2.G();
                    if (zC || objG11 == C0753m.f8270a) {
                        objG11 = new InterfaceC2782k() { // from class: Y5.g
                            @Override // z7.InterfaceC2782k
                            public final Object b(Object obj4) {
                                C1863M c1863m = (C1863M) obj4;
                                m.f(c1863m, "$this$graphicsLayer");
                                float f10 = fB;
                                if (f10 > 1.0f) {
                                    c1863m.i(f10);
                                    c1863m.j(f10);
                                    c1863m.o(AbstractC1861K.j(0.5f, 1.0f));
                                } else {
                                    c1863m.r(c1863m.getDensity() * ((fA - c0138u.b()) / 2));
                                }
                                return q.f19267a;
                            }
                        };
                        c0761q2.a0(objG11);
                    }
                    c0761q2.p(false);
                    AbstractC1274a.u(androidx.compose.ui.graphics.a.a(oVarC, (InterfaceC2782k) objG11), (W) this.f7517n, this.f7516m, c0761q2, 0);
                    if (c0761q2.k(AbstractC1692D.f18238a) == LayoutMode.WIDE_ULTRA) {
                        AbstractC0133o.a(androidx.compose.foundation.a.b(androidx.compose.foundation.layout.c.c(lVar, 1.0f), l6.d.c(c0761q2).f18697b0, nVar), c0761q2, 0);
                    }
                }
                break;
        }
        return q.f19267a;
    }
}
