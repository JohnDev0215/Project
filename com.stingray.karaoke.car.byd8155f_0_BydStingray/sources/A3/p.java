package A3;

import A.I0;
import C0.C0102i;
import C0.InterfaceC0103j;
import C0.O;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import T.C0735d;
import T.C0754m0;
import T.C0761q;
import T.InterfaceC0744h0;
import android.content.Context;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import coil.compose.AsyncImagePainter;
import coil.compose.ContentPainterElement;
import h2.AbstractC1347d;
import h8.AbstractC1386c;
import n0.C1877f;
import n0.C1883l;
import r0.AbstractC2268a;
import s0.C2337d;
import z3.C2763a;

/* JADX INFO: loaded from: classes.dex */
public abstract class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final o f397a = new o();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final u f398b = new u();

    /* JADX WARN: Removed duplicated region for block: B:94:0x011c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void a(final A3.q r22, final g0.o r23, final z7.InterfaceC2782k r24, final A3.x r25, final g0.c r26, final C0.InterfaceC0103j r27, final float r28, final n0.C1883l r29, final int r30, final boolean r31, T.C0761q r32, final int r33, final int r34) {
        /*
            Method dump skipped, instruction units count: 668
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: A3.p.a(A3.q, g0.o, z7.k, A3.x, g0.c, C0.j, float, n0.l, int, boolean, T.q, int, int):void");
    }

    public static final void b(Object obj, g0.o oVar, AbstractC2268a abstractC2268a, AbstractC2268a abstractC2268a2, C0761q c0761q, int i7, int i10) {
        O o = C0102i.f902a;
        c0761q.R(1693837359);
        AbstractC2268a abstractC2268a3 = (i10 & 8) != 0 ? null : abstractC2268a;
        g0.g gVar = g0.b.f14664p;
        u uVar = f398b;
        z3.i iVar = (z3.i) c0761q.k(w.f405a);
        if (iVar == null) {
            Context context = (Context) c0761q.k(AndroidCompositionLocals_androidKt.f11688b);
            z3.i iVarE = C2763a.f24379b;
            if (iVarE == null) {
                synchronized (C2763a.f24378a) {
                    iVarE = C2763a.f24379b;
                    if (iVarE == null) {
                        context.getApplicationContext();
                        iVarE = AbstractC1386c.e(context);
                        C2763a.f24379b = iVarE;
                    }
                }
            }
            iVar = iVarE;
        }
        int i11 = i7 << 3;
        int i12 = (i7 & 112) | 2392584 | (i11 & 7168) | (29360128 & i11) | (234881024 & i11) | (i11 & 1879048192);
        int i13 = ((i7 >> 27) & 14) | 48;
        c0761q.R(-1481548872);
        q qVar = new q(obj, uVar, iVar);
        L3.e eVar = z.f409b;
        a(qVar, oVar, (abstractC2268a3 == null && abstractC2268a2 == null && abstractC2268a2 == null) ? AsyncImagePainter.f12629F : new y(abstractC2268a3, abstractC2268a2, abstractC2268a2, 0), null, gVar, o, 1.0f, null, 1, true, c0761q, (i12 & 112) | ((i12 >> 3) & 896) | ((i13 << 15) & 458752) | 1572864, 0);
        c0761q.p(false);
        c0761q.p(false);
    }

    public static final void c(g0.o oVar, AsyncImagePainter asyncImagePainter, g0.c cVar, InterfaceC0103j interfaceC0103j, float f10, C1883l c1883l, boolean z6, C0761q c0761q, int i7) {
        g0.o oVar2;
        int i10;
        c0761q.S(777774312);
        if ((i7 & 14) == 0) {
            oVar2 = oVar;
            i10 = (c0761q.f(oVar2) ? 4 : 2) | i7;
        } else {
            oVar2 = oVar;
            i10 = i7;
        }
        if ((i7 & 112) == 0) {
            i10 |= c0761q.f(asyncImagePainter) ? 32 : 16;
        }
        if ((i7 & 896) == 0) {
            i10 |= c0761q.f(null) ? 256 : 128;
        }
        if ((i7 & 7168) == 0) {
            i10 |= c0761q.f(cVar) ? 2048 : 1024;
        }
        if ((57344 & i7) == 0) {
            i10 |= c0761q.f(interfaceC0103j) ? 16384 : 8192;
        }
        if ((458752 & i7) == 0) {
            i10 |= c0761q.c(f10) ? 131072 : 65536;
        }
        if ((3670016 & i7) == 0) {
            i10 |= c0761q.f(c1883l) ? 1048576 : 524288;
        }
        if ((29360128 & i7) == 0) {
            i10 |= c0761q.g(z6) ? 8388608 : 4194304;
        }
        if ((i10 & 23967451) == 4793490 && c0761q.x()) {
            c0761q.K();
        } else {
            L3.e eVar = z.f409b;
            g0.o oVarK = (z6 ? h6.q.n(oVar) : oVar2).k(new ContentPainterElement(asyncImagePainter, cVar, interfaceC0103j, f10, c1883l));
            d dVar = d.f385a;
            c0761q.R(544976794);
            int i11 = c0761q.f8304P;
            g0.o oVarD = g0.a.d(c0761q, oVarK);
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i = C0160j.f1678b;
            c0761q.R(1405779621);
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(new I0(c0159i, 1));
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, dVar, C0160j.f1679e);
            C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
            C0735d.V(c0761q, oVarD, C0160j.c);
            C0158h c0158h = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i11))) {
                AbstractC1347d.l(i11, c0761q, i11, c0158h);
            }
            M5.b.n(c0761q, true, false, false);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new b(oVar, asyncImagePainter, cVar, interfaceC0103j, f10, c1883l, z6, i7);
        }
    }

    public static void d(String str) {
        throw new IllegalArgumentException("Unsupported type: " + str + ". " + android.hardware.bydauto.ac.tsimpl.a.i("If you wish to display this ", str, ", use androidx.compose.foundation.Image."));
    }

    public static final void e(K3.i iVar) {
        Object obj = iVar.f3543b;
        if (obj instanceof K3.h) {
            throw new IllegalArgumentException("Unsupported type: ImageRequest.Builder. Did you forget to call ImageRequest.Builder.build()?");
        }
        if (obj instanceof C1877f) {
            d("ImageBitmap");
            throw null;
        }
        if (obj instanceof C2337d) {
            d("ImageVector");
            throw null;
        }
        if (obj instanceof AbstractC2268a) {
            d("Painter");
            throw null;
        }
        if (iVar.c != null) {
            throw new IllegalArgumentException("request.target must be null.");
        }
    }
}
