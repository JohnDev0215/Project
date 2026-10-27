package A;

import E0.AbstractC0156f;
import F0.ViewOnDragListenerC0235w0;
import K.C0331b0;
import K.C0333c0;
import K.C0351l0;
import K.C0365x;
import N0.AbstractC0418m;
import N0.C0409d;
import N0.C0411f;
import Q.C0540x0;
import Q.EnumC0542y0;
import T.C0732b0;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.DragEvent;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.lifecycle.C0971i;
import androidx.lifecycle.EnumC0981t;
import e0.C1178r;
import h8.AbstractC1387d;
import i.C1395b;
import j0.C1536d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CancellationException;
import l0.C1727r;
import n0.AbstractC1874c;
import n0.AbstractC1887p;
import n0.C1863M;
import n0.C1865O;
import n0.C1870U;
import n0.C1878g;
import n0.C1891t;
import n0.InterfaceC1889r;
import n3.C1945j;
import o3.C2042o;
import t.C2419c;
import t.C2422f;
import w.C2579j;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: A.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0022k extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f192m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f193n;
    public final /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f194p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0022k(Object obj, Object obj2, Object obj3, int i7) {
        super(1);
        this.f192m = i7;
        this.f193n = obj;
        this.o = obj2;
        this.f194p = obj3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v97, types: [A7.o, z7.k] */
    /* JADX WARN: Type inference failed for: r1v33, types: [c0.a, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v100, types: [java.lang.Object, m7.f] */
    /* JADX WARN: Type inference failed for: r2v103, types: [java.lang.Object, m7.f] */
    /* JADX WARN: Type inference failed for: r2v96, types: [java.lang.Object, m7.f] */
    /* JADX WARN: Type inference failed for: r3v59, types: [java.lang.Object, m7.f] */
    /* JADX WARN: Type inference failed for: r7v17, types: [java.lang.Object, m7.f] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        N0.I iA;
        N0.I iA2;
        N0.I iA3;
        Object obj2;
        m7.i iVar;
        Integer numC;
        List listJ;
        boolean zBooleanValue;
        switch (this.f192m) {
            case 0:
                float fFloatValue = ((Number) obj).floatValue();
                C0030o c0030o = (C0030o) this.f193n;
                float f10 = c0030o.f222A ? 1.0f : -1.0f;
                S0 s02 = c0030o.f230z;
                long jD = s02.d(s02.g(f10 * fFloatValue));
                S0 s03 = ((P0) this.f194p).f88a;
                float f11 = s02.f(s02.d(S0.a(s03, s03.f105h, jD, 1))) * f10;
                if (Math.abs(f11) < Math.abs(fFloatValue)) {
                    S8.W w10 = (S8.W) this.o;
                    CancellationException cancellationException = new CancellationException("Scroll animation cancelled because scroll was not consumed (" + f11 + " < " + fFloatValue + ')');
                    cancellationException.initCause(null);
                    w10.g(cancellationException);
                }
                return m7.q.f19267a;
            case 1:
                C0.U u4 = (C0.U) obj;
                D.J j3 = (D.J) this.f193n;
                boolean z6 = j3.f1031A;
                C0.M m10 = (C0.M) this.f194p;
                C0.V v10 = (C0.V) this.o;
                if (z6) {
                    C0.U.f(u4, v10, m10.O(j3.f1032y), m10.O(j3.f1033z));
                } else {
                    C0.U.d(u4, v10, m10.O(j3.f1032y), m10.O(j3.f1033z));
                }
                return m7.q.f19267a;
            case 2:
                C0.U u10 = (C0.U) obj;
                D.K k10 = (D.K) this.f193n;
                boolean z10 = k10.f1035C;
                C0.M m11 = (C0.M) this.f194p;
                C0.V v11 = (C0.V) this.o;
                if (z10) {
                    C0.U.f(u10, v11, m11.O(k10.f1036y), m11.O(k10.f1037z));
                } else {
                    C0.U.d(u10, v11, m11.O(k10.f1036y), m11.O(k10.f1037z));
                }
                return m7.q.f19267a;
            case 3:
                D.M m12 = (D.M) this.f194p;
                D.L l = m12.f1040y;
                C0.M m13 = (C0.M) this.o;
                C0.U.d((C0.U) obj, (C0.V) this.f193n, m13.O(l.b(m13.getLayoutDirection())), m13.O(m12.f1040y.d()));
                return m7.q.f19267a;
            case 4:
                T0.y yVar = (T0.y) obj;
                ((T.X) this.o).setValue(yVar);
                T.X x8 = (T.X) this.f194p;
                boolean zA = A7.m.a((String) x8.getValue(), yVar.f8481a.l);
                C0411f c0411f = yVar.f8481a;
                x8.setValue(c0411f.l);
                if (!zA) {
                    ((InterfaceC2782k) this.f193n).b(c0411f.l);
                }
                return m7.q.f19267a;
            case 5:
                p0.d dVar = (p0.d) obj;
                C0331b0 c0331b0 = (C0331b0) this.f193n;
                K.G0 g0D = c0331b0.d();
                if (g0D != null) {
                    InterfaceC1889r interfaceC1889rM = dVar.F().m();
                    long j10 = ((N0.K) c0331b0.f3239x.getValue()).f4357a;
                    long j11 = ((N0.K) c0331b0.f3240y.getValue()).f4357a;
                    long j12 = c0331b0.f3238w;
                    boolean zB = N0.K.b(j10);
                    T0.s sVar = (T0.s) this.f194p;
                    N0.H h10 = g0D.f3161a;
                    N0.G g7 = h10.f4348a;
                    C1878g c1878g = c0331b0.f3237v;
                    if (!zB) {
                        c1878g.e(j12);
                        int iB = sVar.b(N0.K.e(j10));
                        int iB2 = sVar.b(N0.K.d(j10));
                        if (iB != iB2) {
                            interfaceC1889rM.f(h10.k(iB, iB2), c1878g);
                        }
                    } else if (N0.K.b(j11)) {
                        long j13 = ((T0.y) this.o).f8482b;
                        if (!N0.K.b(j13)) {
                            c1878g.e(j12);
                            int iB3 = sVar.b(N0.K.e(j13));
                            int iB4 = sVar.b(N0.K.d(j13));
                            if (iB3 != iB4) {
                                interfaceC1889rM.f(h10.k(iB3, iB4), c1878g);
                            }
                        }
                    } else {
                        long jC = g7.f4341b.c();
                        C1891t c1891t = new C1891t(jC);
                        if (jC == 16) {
                            c1891t = null;
                        }
                        long j14 = c1891t != null ? c1891t.f19453a : C1891t.f19445b;
                        c1878g.e(C1891t.b(C1891t.d(j14) * 0.2f, j14));
                        int iB5 = sVar.b(N0.K.e(j11));
                        int iB6 = sVar.b(N0.K.d(j11));
                        if (iB5 != iB6) {
                            interfaceC1889rM.f(h10.k(iB5, iB6), c1878g);
                        }
                    }
                    long j15 = h10.c;
                    float f12 = (int) (j15 >> 32);
                    N0.o oVar = h10.f4349b;
                    boolean z11 = ((f12 > oVar.d ? 1 : (f12 == oVar.d ? 0 : -1)) < 0 || oVar.c || (((float) ((int) (j15 & 4294967295L))) > oVar.f4393e ? 1 : (((float) ((int) (j15 & 4294967295L))) == oVar.f4393e ? 0 : -1)) < 0) && !C7.a.F(g7.f4343f, 3);
                    if (z11) {
                        m0.d dVarA = h6.f.a(0L, h6.j.f((int) (j15 >> 32), (int) (j15 & 4294967295L)));
                        interfaceC1889rM.m();
                        InterfaceC1889r.q(interfaceC1889rM, dVarA);
                    }
                    N0.D d = g7.f4341b.f4358a;
                    Y0.j jVar = d.f4333m;
                    Y0.m mVar = d.f4324a;
                    if (jVar == null) {
                        jVar = Y0.j.f10619b;
                    }
                    Y0.j jVar2 = jVar;
                    C1865O c1865o = d.f4334n;
                    if (c1865o == null) {
                        c1865o = C1865O.d;
                    }
                    C1865O c1865o2 = c1865o;
                    p0.e eVar = d.f4335p;
                    if (eVar == null) {
                        eVar = p0.g.f20641a;
                    }
                    p0.e eVar2 = eVar;
                    try {
                        AbstractC1887p abstractC1887pB = mVar.b();
                        Y0.l lVar = Y0.l.f10622a;
                        if (abstractC1887pB != null) {
                            N0.o.h(oVar, interfaceC1889rM, abstractC1887pB, mVar != lVar ? mVar.c() : 1.0f, c1865o2, jVar2, eVar2);
                        } else {
                            N0.o.g(oVar, interfaceC1889rM, mVar != lVar ? mVar.a() : C1891t.f19445b, c1865o2, jVar2, eVar2);
                        }
                        if (z11) {
                            interfaceC1889rM.k();
                        }
                    } catch (Throwable th) {
                        if (z11) {
                            interfaceC1889rM.k();
                        }
                        throw th;
                    }
                }
                return m7.q.f19267a;
            case 6:
                T0.E e10 = (T0.E) ((A7.z) this.f194p).l;
                T0.y yVarN = ((M.t) this.f193n).n((List) obj);
                if (e10 != null) {
                    e10.a(null, yVarN);
                }
                ((C0365x) this.o).b(yVarN);
                return m7.q.f19267a;
            case 7:
                C0351l0 c0351l0 = (C0351l0) obj;
                C0409d c0409d = (C0409d) this.o;
                Object obj3 = c0409d.f4371a;
                N0.I iA4 = ((AbstractC0418m) obj3).a();
                N0.D dC = null;
                N0.D d10 = iA4 != null ? iA4.f4352a : null;
                C0732b0 c0732b0 = ((C0333c0) this.f194p).f3242a;
                N0.D dC2 = (!((c0732b0.i() & 1) != 0) || (iA3 = ((AbstractC0418m) obj3).a()) == null) ? null : iA3.f4353b;
                ((K.J0) this.f193n).getClass();
                if (d10 != null) {
                    dC2 = d10.c(dC2);
                }
                N0.D dC3 = ((c0732b0.i() & 2) == 0 || (iA2 = ((AbstractC0418m) obj3).a()) == null) ? null : iA2.c;
                if (dC2 != null) {
                    dC3 = dC2.c(dC3);
                }
                if ((c0732b0.i() & 4) != 0 && (iA = ((AbstractC0418m) obj3).a()) != null) {
                    dC = iA.d;
                }
                if (dC3 != null) {
                    dC = dC3.c(dC);
                }
                if (dC != null) {
                    c0351l0.f3335a.a(dC, c0409d.f4372b, c0409d.c);
                }
                return m7.q.f19267a;
            case 8:
                return new C0540x0((EnumC0542y0) obj, (a1.b) this.f193n, (InterfaceC2782k) this.o, (InterfaceC2782k) this.f194p);
            case 9:
                C0.U u11 = (C0.U) obj;
                boolean zV = ((C0.M) this.f193n).v();
                R.y yVar2 = (R.y) this.o;
                float fC = zV ? yVar2.f7338y.d().c(yVar2.f7338y.f7330g.getValue()) : yVar2.f7338y.e();
                EnumC0017h0 enumC0017h0 = yVar2.f7337A;
                float f13 = enumC0017h0 == EnumC0017h0.f181m ? fC : 0.0f;
                if (enumC0017h0 != EnumC0017h0.l) {
                    fC = 0.0f;
                }
                C0.U.d(u11, (C0.V) this.f194p, C7.a.W(f13), C7.a.W(fC));
                return m7.q.f19267a;
            case 10:
                final T.X x9 = (T.X) this.f194p;
                ?? r12 = new androidx.lifecycle.K() { // from class: c0.a
                    @Override // androidx.lifecycle.K
                    public final void a(Object obj4) {
                        x9.setValue(obj4);
                    }
                };
                C0971i c0971i = (C0971i) this.f193n;
                androidx.lifecycle.J.a("observe");
                androidx.lifecycle.A a10 = (androidx.lifecycle.A) this.o;
                if (a10.g().O0() != EnumC0981t.l) {
                    androidx.lifecycle.F f14 = new androidx.lifecycle.F(c0971i, a10, r12);
                    C2422f c2422f = c0971i.f11927b;
                    C2419c c2419cA = c2422f.a(r12);
                    if (c2419cA != null) {
                        obj2 = c2419cA.f22419m;
                    } else {
                        C2419c c2419c = new C2419c(r12, f14);
                        c2422f.o++;
                        C2419c c2419c2 = c2422f.f22423m;
                        if (c2419c2 == null) {
                            c2422f.l = c2419c;
                            c2422f.f22423m = c2419c;
                        } else {
                            c2419c2.f22420n = c2419c;
                            c2419c.o = c2419c2;
                            c2422f.f22423m = c2419c;
                        }
                        obj2 = null;
                    }
                    androidx.lifecycle.G g10 = (androidx.lifecycle.G) obj2;
                    if (g10 != null && !g10.d(a10)) {
                        throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
                    }
                    if (g10 == null) {
                        a10.g().M0(f14);
                    }
                }
                return new F0.X(c0971i, 8, r12);
            case 11:
                d0.h hVar = (d0.h) this.f193n;
                LinkedHashMap linkedHashMap = hVar.f13269b;
                Object obj4 = this.o;
                if (linkedHashMap.containsKey(obj4)) {
                    throw new IllegalArgumentException(("Key " + obj4 + " was used multiple times ").toString());
                }
                hVar.f13268a.remove(obj4);
                LinkedHashMap linkedHashMap2 = hVar.f13269b;
                d0.f fVar = (d0.f) this.f194p;
                linkedHashMap2.put(obj4, fVar);
                return new d0.g(fVar, hVar, obj4, 0);
            case 12:
                InterfaceC1889r interfaceC1889rM2 = ((p0.d) obj).F().m();
                d1.o oVar2 = (d1.o) this.f193n;
                if (oVar2.getView().getVisibility() != 8) {
                    oVar2.f13299F = true;
                    AndroidComposeView androidComposeView = ((E0.F) this.o).f1487t;
                    if (androidComposeView == null) {
                        androidComposeView = null;
                    }
                    if (androidComposeView != null) {
                        Canvas canvasA = AbstractC1874c.a(interfaceC1889rM2);
                        androidComposeView.getAndroidViewsHandler$ui_release().getClass();
                        ((d1.o) this.f194p).draw(canvasA);
                    }
                    oVar2.f13299F = false;
                }
                return m7.q.f19267a;
            case 13:
                h.x xVar = (h.x) this.f193n;
                androidx.lifecycle.A a11 = (androidx.lifecycle.A) this.o;
                C1395b c1395b = (C1395b) this.f194p;
                xVar.a(a11, c1395b);
                return new F0.V(c1395b, 9);
            case 14:
                h.x xVar2 = (h.x) this.f193n;
                androidx.lifecycle.A a12 = (androidx.lifecycle.A) this.o;
                i.g gVar = (i.g) this.f194p;
                xVar2.a(a12, gVar);
                return new F0.V(gVar, 10);
            case 15:
                io.sentry.android.replay.viewhierarchy.d dVar2 = (io.sentry.android.replay.viewhierarchy.d) obj;
                A7.m.f(dVar2, "node");
                if (dVar2.d && dVar2.f16455a > 0 && dVar2.f16456b > 0) {
                    Rect rect = dVar2.f16457e;
                    if (rect == null) {
                        return Boolean.FALSE;
                    }
                    boolean z12 = dVar2 instanceof io.sentry.android.replay.viewhierarchy.b;
                    int i7 = 1;
                    int i10 = 0;
                    io.sentry.android.replay.s sVar2 = (io.sentry.android.replay.s) this.f193n;
                    if (z12) {
                        List listJ2 = AbstractC1387d.j(rect);
                        sVar2.getClass();
                        Rect rect2 = new Rect(rect);
                        RectF rectF = new RectF(rect2);
                        ((Matrix) sVar2.f16431u.getValue()).mapRect(rectF);
                        rectF.round(rect2);
                        ((Canvas) sVar2.f16430t.getValue()).drawBitmap((Bitmap) this.o, rect2, new Rect(0, 0, 1, 1), (Paint) null);
                        iVar = new m7.i(listJ2, Integer.valueOf(((Bitmap) sVar2.f16429s.getValue()).getPixel(0, 0)));
                    } else {
                        if (dVar2 instanceof io.sentry.android.replay.viewhierarchy.c) {
                            io.sentry.android.replay.viewhierarchy.c cVar = (io.sentry.android.replay.viewhierarchy.c) dVar2;
                            io.sentry.android.replay.util.b bVar = cVar.f16451g;
                            int iIntValue = ((bVar == null || (numC = bVar.c()) == null) && (numC = cVar.f16452h) == null) ? -16777216 : numC.intValue();
                            if (bVar == null) {
                                listJ = AbstractC1387d.j(rect);
                            } else {
                                ArrayList arrayList = new ArrayList();
                                int iB7 = bVar.b();
                                int i11 = 0;
                                while (i11 < iB7) {
                                    int iG = (int) bVar.g(i11, bVar.a(i11));
                                    int iH = bVar.h(i11);
                                    int iD = bVar.d(i11);
                                    int iG2 = (int) bVar.g(i11, (iD - iH) + (iH > 0 ? i7 : i10));
                                    if (iG2 == 0 && iD > 0) {
                                        iG2 = ((int) bVar.g(i11, iD - 1)) + i7;
                                    }
                                    int iE = bVar.e(i11);
                                    int i12 = bVar.i(i11);
                                    Rect rect3 = new Rect();
                                    int i13 = rect.left + cVar.f16453i + iG;
                                    rect3.left = i13;
                                    rect3.right = (iG2 - iG) + i13;
                                    int i14 = rect.top + cVar.f16454j + iE;
                                    rect3.top = i14;
                                    rect3.bottom = (i12 - iE) + i14;
                                    arrayList.add(rect3);
                                    i11++;
                                    i7 = 1;
                                    i10 = 0;
                                }
                                listJ = arrayList;
                            }
                            iVar = new m7.i(listJ, Integer.valueOf(iIntValue));
                        } else {
                            iVar = new m7.i(AbstractC1387d.j(rect), -16777216);
                        }
                    }
                    List list = (List) iVar.l;
                    ((Paint) sVar2.f16428r.getValue()).setColor(((Number) iVar.f19259m).intValue());
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        ((Canvas) this.f194p).drawRoundRect(new RectF((Rect) it.next()), 10.0f, 10.0f, (Paint) sVar2.f16428r.getValue());
                    }
                }
                return Boolean.TRUE;
            case 16:
                E0.s0 s0Var = (E0.s0) obj;
                C1536d c1536d = (C1536d) s0Var;
                if (((ViewOnDragListenerC0235w0) AbstractC0156f.u((C1536d) this.o).getDragAndDropManager()).f2292b.contains(c1536d)) {
                    DragEvent dragEvent = (DragEvent) ((e7.f) this.f194p).f14480m;
                    if (h6.m.e(c1536d, h6.e.a(dragEvent.getX(), dragEvent.getY()))) {
                        ((A7.z) this.f193n).l = s0Var;
                        return E0.r0.f1698n;
                    }
                }
                return E0.r0.l;
            case 17:
                C1727r c1727r = (C1727r) obj;
                if (A7.m.a(c1727r, (C1727r) this.f193n)) {
                    zBooleanValue = false;
                } else {
                    if (A7.m.a(c1727r, ((androidx.compose.ui.focus.b) this.o).f11569f)) {
                        throw new IllegalStateException("Focus search landed at the root.");
                    }
                    zBooleanValue = ((Boolean) ((A7.o) this.f194p).b(c1727r)).booleanValue();
                }
                return Boolean.valueOf(zBooleanValue);
            case 18:
                C1945j c1945j = (C1945j) this.o;
                C1178r c1178r = (C1178r) this.f193n;
                c1178r.add(c1945j);
                return new d0.g((C2042o) this.f194p, c1945j, c1178r, 1);
            case 19:
                return new d0.g((C1178r) this.f193n, this.o, (C2579j) this.f194p);
            case 20:
                C1863M c1863m = (C1863M) obj;
                x.q0 q0Var = (x.q0) this.f193n;
                c1863m.a(q0Var != null ? ((Number) q0Var.getValue()).floatValue() : 1.0f);
                x.q0 q0Var2 = (x.q0) this.o;
                c1863m.i(q0Var2 != null ? ((Number) q0Var2.getValue()).floatValue() : 1.0f);
                c1863m.j(q0Var2 != null ? ((Number) q0Var2.getValue()).floatValue() : 1.0f);
                x.q0 q0Var3 = (x.q0) this.f194p;
                c1863m.o(q0Var3 != null ? ((C1870U) q0Var3.getValue()).f19423a : C1870U.f19422b);
                return m7.q.f19267a;
            default:
                int iOrdinal = ((w.w) obj).ordinal();
                C1870U c1870u = null;
                w.G g11 = (w.G) this.f194p;
                if (iOrdinal == 0) {
                    w.P p10 = g11.f23069a;
                } else if (iOrdinal == 1) {
                    c1870u = (C1870U) this.f193n;
                } else {
                    if (iOrdinal != 2) {
                        throw new A9.k();
                    }
                    w.P p11 = g11.f23069a;
                }
                return new C1870U(c1870u != null ? c1870u.f19423a : C1870U.f19422b);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public C0022k(C1727r c1727r, androidx.compose.ui.focus.b bVar, InterfaceC2782k interfaceC2782k) {
        super(1);
        this.f192m = 17;
        this.f193n = c1727r;
        this.o = bVar;
        this.f194p = (A7.o) interfaceC2782k;
    }
}
