package androidx.compose.ui.viewinterop;

import E0.C0158h;
import E0.C0160j;
import E0.F;
import E0.InterfaceC0161k;
import E0.V;
import F0.AbstractC0227s0;
import T.C0735d;
import T.C0753m;
import T.C0754m0;
import T.C0757o;
import T.C0761q;
import T.InterfaceC0744h0;
import a1.b;
import a1.k;
import a2.AbstractC0911e;
import android.content.Context;
import android.view.View;
import androidx.compose.ui.focus.FocusTargetNode$FocusTargetElement;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.lifecycle.A;
import d0.m;
import d1.l;
import g0.n;
import g0.o;
import h2.AbstractC1347d;
import l0.C1727r;
import l9.d;
import t3.e;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    /* JADX WARN: Removed duplicated region for block: B:34:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void a(z7.InterfaceC2782k r12, g0.o r13, z7.InterfaceC2782k r14, T.C0761q r15, int r16, int r17) {
        /*
            r7 = r15
            r8 = r16
            r0 = -1783766393(0xffffffff95ade287, float:-7.023154E-26)
            r15.S(r0)
            r0 = r8 & 6
            r9 = r12
            if (r0 != 0) goto L19
            boolean r0 = r15.h(r12)
            if (r0 == 0) goto L16
            r0 = 4
            goto L17
        L16:
            r0 = 2
        L17:
            r0 = r0 | r8
            goto L1a
        L19:
            r0 = r8
        L1a:
            r1 = r8 & 48
            r10 = r13
            if (r1 != 0) goto L2b
            boolean r1 = r15.f(r13)
            if (r1 == 0) goto L28
            r1 = 32
            goto L2a
        L28:
            r1 = 16
        L2a:
            r0 = r0 | r1
        L2b:
            r1 = r17 & 4
            if (r1 == 0) goto L33
            r0 = r0 | 384(0x180, float:5.38E-43)
        L31:
            r2 = r14
            goto L44
        L33:
            r2 = r8 & 384(0x180, float:5.38E-43)
            if (r2 != 0) goto L31
            r2 = r14
            boolean r3 = r15.h(r14)
            if (r3 == 0) goto L41
            r3 = 256(0x100, float:3.59E-43)
            goto L43
        L41:
            r3 = 128(0x80, float:1.8E-43)
        L43:
            r0 = r0 | r3
        L44:
            r3 = r0 & 147(0x93, float:2.06E-43)
            r4 = 146(0x92, float:2.05E-43)
            if (r3 != r4) goto L56
            boolean r3 = r15.x()
            if (r3 != 0) goto L51
            goto L56
        L51:
            r15.K()
            r3 = r2
            goto L75
        L56:
            d1.a r3 = d1.C1057a.f13278q
            if (r1 == 0) goto L5c
            r11 = r3
            goto L5d
        L5c:
            r11 = r2
        L5d:
            r1 = r0 & 14
            r1 = r1 | 3072(0xc00, float:4.305E-42)
            r2 = r0 & 112(0x70, float:1.57E-43)
            r1 = r1 | r2
            r2 = 57344(0xe000, float:8.0356E-41)
            int r0 = r0 << 6
            r0 = r0 & r2
            r6 = r1 | r0
            r2 = 0
            r0 = r12
            r1 = r13
            r4 = r11
            r5 = r15
            b(r0, r1, r2, r3, r4, r5, r6)
            r3 = r11
        L75:
            T.m0 r6 = r15.r()
            if (r6 == 0) goto L89
            G.H r7 = new G.H
            r0 = r7
            r1 = r12
            r2 = r13
            r4 = r16
            r5 = r17
            r0.<init>(r1, r2, r3, r4, r5)
            r6.d = r7
        L89:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.viewinterop.a.a(z7.k, g0.o, z7.k, T.q, int, int):void");
    }

    public static final void b(InterfaceC2782k interfaceC2782k, o oVar, InterfaceC2782k interfaceC2782k2, InterfaceC2782k interfaceC2782k3, InterfaceC2782k interfaceC2782k4, C0761q c0761q, int i7) {
        int i10;
        e eVar;
        A a10;
        InterfaceC0744h0 interfaceC0744h0;
        k kVar;
        InterfaceC2782k interfaceC2782k5;
        c0761q.S(-180024211);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(interfaceC2782k) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(oVar) ? 32 : 16;
        }
        int i11 = i10 | 384;
        if ((i7 & 3072) == 0) {
            i11 |= c0761q.h(interfaceC2782k3) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i11 |= c0761q.h(interfaceC2782k4) ? 16384 : 8192;
        }
        if ((i11 & 9363) == 9362 && c0761q.x()) {
            c0761q.K();
            interfaceC2782k5 = interfaceC2782k2;
        } else {
            int i12 = c0761q.f8304P;
            o oVarK = oVar.k(FocusGroupPropertiesElement.f11696a);
            FocusTargetNode$FocusTargetElement focusTargetNode$FocusTargetElement = new V() { // from class: androidx.compose.ui.focus.FocusTargetNode$FocusTargetElement
                public final boolean equals(Object obj) {
                    return obj == this;
                }

                public final int hashCode() {
                    return 1739042953;
                }

                @Override // E0.V
                public final n i() {
                    return new C1727r();
                }

                @Override // E0.V
                public final /* bridge */ /* synthetic */ void l(n nVar) {
                }
            };
            o oVarD = g0.a.d(c0761q, oVarK.k(focusTargetNode$FocusTargetElement).k(FocusTargetPropertiesElement.f11697a).k(focusTargetNode$FocusTargetElement));
            b bVar = (b) c0761q.k(AbstractC0227s0.f2259f);
            k kVar2 = (k) c0761q.k(AbstractC0227s0.l);
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            A a11 = (A) c0761q.k(AbstractC0911e.f11054a);
            e eVar2 = (e) c0761q.k(AndroidCompositionLocals_androidKt.f11689e);
            c0761q.Q(608726777);
            int i13 = i11 & 14;
            int i14 = c0761q.f8304P;
            Context context = (Context) c0761q.k(AndroidCompositionLocals_androidKt.f11688b);
            C0757o c0757oQ = C0735d.Q(c0761q);
            d0.k kVar3 = (d0.k) c0761q.k(m.f13274a);
            View view = (View) c0761q.k(AndroidCompositionLocals_androidKt.f11690f);
            boolean zH = c0761q.h(context) | ((((i13 & 14) ^ 6) > 4 && c0761q.f(interfaceC2782k)) || (i13 & 6) == 4) | c0761q.h(c0757oQ) | c0761q.h(kVar3) | c0761q.d(i14) | c0761q.h(view);
            Object objG = c0761q.G();
            if (zH || objG == C0753m.f8270a) {
                eVar = eVar2;
                a10 = a11;
                interfaceC0744h0 = interfaceC0744h0M;
                kVar = kVar2;
                Object lVar = new l(context, interfaceC2782k, c0757oQ, kVar3, i14, view);
                c0761q.a0(lVar);
                objG = lVar;
            } else {
                eVar = eVar2;
                a10 = a11;
                interfaceC0744h0 = interfaceC0744h0M;
                kVar = kVar2;
            }
            InterfaceC2772a interfaceC2772a = (InterfaceC2772a) objG;
            c0761q.L(null, 125, null, 1);
            c0761q.f8317q = true;
            if (c0761q.f8303O) {
                c0761q.l(interfaceC2772a);
            } else {
                c0761q.d0();
            }
            InterfaceC0161k.f1681b.getClass();
            C0735d.V(c0761q, interfaceC0744h0, C0160j.d);
            C0735d.V(c0761q, oVarD, d1.k.f13316p);
            C0735d.V(c0761q, bVar, d1.k.f13317q);
            C0735d.V(c0761q, a10, d1.k.f13318r);
            C0735d.V(c0761q, eVar, d1.k.f13319s);
            C0735d.V(c0761q, kVar, d1.k.f13320t);
            C0158h c0158h = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i12))) {
                AbstractC1347d.l(i12, c0761q, i12, c0158h);
            }
            C0735d.V(c0761q, interfaceC2782k4, d1.k.f13315n);
            C0735d.V(c0761q, interfaceC2782k3, d1.k.o);
            c0761q.p(true);
            c0761q.p(false);
            interfaceC2782k5 = null;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new b0.a(interfaceC2782k, oVar, interfaceC2782k5, interfaceC2782k3, interfaceC2782k4, i7, 1);
        }
    }

    public static final d1.o c(F f10) {
        d1.o oVar = f10.f1488u;
        if (oVar != null) {
            return oVar;
        }
        d.i0("Required value was null.");
        throw null;
    }
}
