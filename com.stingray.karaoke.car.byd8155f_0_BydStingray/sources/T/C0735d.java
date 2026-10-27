package T;

import S8.AbstractC0727y;
import S8.C0722t;
import U.C0800a;
import V8.C0819h;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import n7.AbstractC1996k;
import q7.C2195i;
import q7.InterfaceC2194h;
import u.AbstractC2457E;
import u.C2453A;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: T.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0735d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Y f8237a = new Y("provider");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Y f8238b = new Y("provider");
    public static final Y c = new Y("compositionLocalMap");
    public static final Y d = new Y("providers");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Y f8239e = new Y("reference");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final C1.b f8240f = new C1.b(4);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Object f8241g = new Object();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final G f8242h = new G();

    public static final int A(int i7) {
        switch (i7) {
            case 0:
                return 0;
            case 1:
            case 2:
            case 4:
                return 1;
            case 3:
            case 5:
            case 6:
                return 2;
            default:
                return 3;
        }
    }

    public static final X8.c B(C0761q c0761q) {
        C2195i c2195i = C2195i.l;
        C0722t c0722t = C0722t.f8137m;
        InterfaceC2194h interfaceC2194hH = c0761q.f8306b.h();
        return AbstractC0727y.a(interfaceC2194hH.v(new S8.Y((S8.W) interfaceC2194hH.F(c0722t))).v(c2195i));
    }

    public static final long C() {
        return Thread.currentThread().getId();
    }

    public static final void D(B0 b02, E0.Y y10) {
        int i7;
        int iC;
        int iO;
        int i10 = b02.f8172t;
        int i11 = b02.f8173u;
        while (i10 < i11) {
            Object objW = b02.w(i10);
            if (objW instanceof InterfaceC0749k) {
                y10.h(b02.o() - b02.E(b02.f8158b, b02.p(i10)), -1, -1, (InterfaceC0749k) objW);
            }
            int iE = b02.E(b02.f8158b, b02.p(i10));
            int i12 = i10 + 1;
            int iF = b02.f(b02.f8158b, b02.p(i12));
            int i13 = iE;
            while (i13 < iF) {
                int i14 = i13 - iE;
                Object obj = b02.c[b02.g(i13)];
                boolean z6 = obj instanceof v0;
                S s6 = C0753m.f8270a;
                if (z6) {
                    v0 v0Var = (v0) obj;
                    InterfaceC0769u0 interfaceC0769u0 = v0Var.f8385a;
                    if (interfaceC0769u0 instanceof C0755n) {
                        i7 = i11;
                    } else {
                        int iG = b02.g(b02.F(i10, i14));
                        Object[] objArr = b02.c;
                        i7 = i11;
                        Object obj2 = objArr[iG];
                        objArr[iG] = s6;
                        if (obj != obj2) {
                            y("Slot table is out of sync");
                            throw null;
                        }
                        int iO2 = b02.o() - i14;
                        C0733c c0733c = v0Var.f8386b;
                        if (c0733c == null || !c0733c.a()) {
                            iC = -1;
                            iO = -1;
                        } else {
                            iC = b02.c(c0733c);
                            iO = b02.o() - b02.f(b02.f8158b, b02.p(b02.q(iC) + iC));
                        }
                        y10.h(iO2, iC, iO, interfaceC0769u0);
                    }
                } else {
                    i7 = i11;
                    if (obj instanceof C0754m0) {
                        int iG2 = b02.g(b02.F(i10, i14));
                        Object[] objArr2 = b02.c;
                        Object obj3 = objArr2[iG2];
                        objArr2[iG2] = s6;
                        if (obj != obj3) {
                            y("Slot table is out of sync");
                            throw null;
                        }
                        ((C0754m0) obj).d();
                    } else {
                        continue;
                    }
                }
                i13++;
                i11 = i7;
            }
            i10 = i12;
        }
    }

    public static final V.d E() {
        l3.P p10 = H0.f8186b;
        V.d dVar = (V.d) p10.p();
        if (dVar != null) {
            return dVar;
        }
        V.d dVar2 = new V.d(new C0759p[0]);
        p10.D(dVar2);
        return dVar2;
    }

    public static final D F(G0 g02, InterfaceC2772a interfaceC2772a) {
        l3.P p10 = H0.f8185a;
        return new D(g02, interfaceC2772a);
    }

    public static final D G(InterfaceC2772a interfaceC2772a) {
        l3.P p10 = H0.f8185a;
        return new D(null, interfaceC2772a);
    }

    public static final int H(int i7, ArrayList arrayList) {
        int size = arrayList.size() - 1;
        int i10 = 0;
        while (i10 <= size) {
            int i11 = (i10 + size) >>> 1;
            int iG = A7.m.g(((L) arrayList.get(i11)).f8200b, i7);
            if (iG < 0) {
                i10 = i11 + 1;
            } else {
                if (iG <= 0) {
                    return i11;
                }
                size = i11 - 1;
            }
        }
        return -(i10 + 1);
    }

    public static final T I(InterfaceC2194h interfaceC2194h) {
        T t10 = (T) interfaceC2194h.F(S.f8221m);
        if (t10 != null) {
            return t10;
        }
        throw new IllegalStateException("A MonotonicFrameClock is not available in this CoroutineContext. Callers should supply an appropriate MonotonicFrameClock using withContext.");
    }

    public static final void J(C0761q c0761q, InterfaceC2785n interfaceC2785n) {
        A7.m.d(interfaceC2785n, "null cannot be cast to non-null type kotlin.Function2<androidx.compose.runtime.Composer, kotlin.Int, kotlin.Unit>");
        A7.E.e(2, interfaceC2785n);
        interfaceC2785n.invoke(c0761q, 1);
    }

    public static List K(B0 b02, int i7, B0 b03, boolean z6, boolean z10, boolean z11) {
        List list;
        boolean zB;
        int i10;
        int i11;
        int i12;
        int iQ = b02.q(i7);
        int i13 = i7 + iQ;
        int iF = b02.f(b02.f8158b, b02.p(i7));
        int iF2 = b02.f(b02.f8158b, b02.p(i13));
        int i14 = iF2 - iF;
        boolean z12 = i7 >= 0 && (b02.f8158b[(b02.p(i7) * 5) + 1] & 201326592) != 0;
        b03.r(iQ);
        b03.s(i14, b03.f8172t);
        if (b02.f8161g < i13) {
            b02.u(i13);
        }
        if (b02.f8165k < iF2) {
            b02.v(iF2, i13);
        }
        int[] iArr = b03.f8158b;
        int i15 = b03.f8172t;
        int i16 = i15 * 5;
        AbstractC1996k.C(i16, i7 * 5, i13 * 5, b02.f8158b, iArr);
        Object[] objArr = b03.c;
        int i17 = b03.f8163i;
        AbstractC1996k.D(i17, iF, iF2, b02.c, objArr);
        int i18 = b03.f8174v;
        iArr[i16 + 2] = i18;
        int i19 = i15 - i7;
        int i20 = i15 + iQ;
        int iF3 = i17 - b03.f(iArr, i15);
        int i21 = b03.f8166m;
        int i22 = b03.l;
        int length = objArr.length;
        boolean z13 = z12;
        int i23 = i21;
        int i24 = i15;
        while (i24 < i20) {
            if (i24 != i15) {
                int i25 = (i24 * 5) + 2;
                iArr[i25] = iArr[i25] + i19;
            }
            int i26 = i20;
            int iF4 = b03.f(iArr, i24) + iF3;
            if (i23 < i24) {
                i11 = i15;
                i12 = 0;
            } else {
                i11 = i15;
                i12 = b03.f8165k;
            }
            iArr[(i24 * 5) + 4] = B0.h(iF4, i12, i22, length);
            if (i24 == i23) {
                i23++;
            }
            i24++;
            i15 = i11;
            i20 = i26;
        }
        int i27 = i15;
        int i28 = i20;
        b03.f8166m = i23;
        int iP = p(b02.d, i7, b02.n());
        int iP2 = p(b02.d, i13, b02.n());
        if (iP < iP2) {
            ArrayList arrayList = b02.d;
            ArrayList arrayList2 = new ArrayList(iP2 - iP);
            for (int i29 = iP; i29 < iP2; i29++) {
                C0733c c0733c = (C0733c) arrayList.get(i29);
                c0733c.f8235a += i19;
                arrayList2.add(c0733c);
            }
            b03.d.addAll(p(b03.d, b03.f8172t, b03.n()), arrayList2);
            arrayList.subList(iP, iP2).clear();
            list = arrayList2;
        } else {
            list = n7.u.l;
        }
        if (!list.isEmpty()) {
            HashMap map = b02.f8159e;
            HashMap map2 = b03.f8159e;
            if (map != null && map2 != null) {
                int size = list.size();
                for (int i30 = 0; i30 < size; i30++) {
                }
            }
        }
        int i31 = b03.f8174v;
        b03.G(i18);
        int iX = b02.x(b02.f8158b, i7);
        if (!z11) {
            i10 = 1;
            zB = false;
        } else if (z6) {
            boolean z14 = iX >= 0;
            if (z14) {
                b02.H();
                b02.a(iX - b02.f8172t);
                b02.H();
            }
            b02.a(i7 - b02.f8172t);
            boolean zA = b02.A();
            if (z14) {
                b02.D();
                b02.i();
                b02.D();
                b02.i();
            }
            zB = zA;
            i10 = 1;
        } else {
            zB = b02.B(i7, iQ);
            i10 = 1;
            b02.C(iF, i14, i7 - 1);
        }
        if (zB) {
            y("Unexpectedly removed anchors");
            throw null;
        }
        b03.o += o(iArr, i27) ? i10 : q(iArr, i27);
        if (z10) {
            b03.f8172t = i28;
            b03.f8163i = i17 + i14;
        }
        if (z13) {
            b03.L(i18);
        }
        return list;
    }

    public static final C0730a0 L(float f10) {
        int i7 = AbstractC0731b.f8233b;
        return new C0730a0(f10);
    }

    public static final C0732b0 M(int i7) {
        int i10 = AbstractC0731b.f8233b;
        return new C0732b0(i7);
    }

    public static final C0734c0 N(long j3) {
        int i7 = AbstractC0731b.f8233b;
        return new C0734c0(j3);
    }

    public static final C0738e0 O(Object obj, G0 g02) {
        int i7 = AbstractC0731b.f8233b;
        return new C0738e0(obj, g02);
    }

    public static final Object P(InterfaceC0744h0 interfaceC0744h0, AbstractC0750k0 abstractC0750k0) {
        A7.m.d(abstractC0750k0, "null cannot be cast to non-null type androidx.compose.runtime.CompositionLocal<kotlin.Any?>");
        b0.e eVar = (b0.e) interfaceC0744h0;
        Object objB = eVar.get(abstractC0750k0);
        if (objB == null) {
            objB = abstractC0750k0.b();
        }
        return ((T0) objB).a(eVar);
    }

    public static final C0757o Q(C0761q c0761q) {
        c0761q.N(206, f8239e);
        if (c0761q.f8303O) {
            B0 b02 = c0761q.f8296H;
            int i7 = b02.f8174v;
            int iP = b02.p(i7);
            int[] iArr = b02.f8158b;
            int i10 = (iP * 5) + 1;
            int i11 = iArr[i10];
            if ((i11 & 134217728) == 0) {
                iArr[i10] = i11 | 134217728;
                if (!j(iArr, iP)) {
                    b02.L(b02.x(b02.f8158b, i7));
                }
            }
        }
        Object objZ = c0761q.z();
        C0755n c0755n = objZ instanceof C0755n ? (C0755n) objZ : null;
        if (c0755n == null) {
            c0755n = new C0755n(new C0757o(c0761q, c0761q.f8304P, c0761q.f8316p, c0761q.B, c0761q.f8309g.f8368A));
            c0761q.b0(c0755n);
        }
        InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
        C0757o c0757o = c0755n.l;
        c0757o.f8284f.setValue(interfaceC0744h0M);
        c0761q.p(false);
        return c0757o;
    }

    public static final X R(Object obj, C0761q c0761q) {
        Object objG = c0761q.G();
        if (objG == C0753m.f8270a) {
            objG = O(obj, S.f8224q);
            c0761q.a0(objG);
        }
        X x8 = (X) objG;
        x8.setValue(obj);
        return x8;
    }

    public static final void S(B0 b02, E0.Y y10) {
        int iO;
        int[] iArr = b02.f8158b;
        int i7 = b02.f8172t;
        int iF = b02.f(iArr, b02.p(b02.q(i7) + i7));
        for (int iF2 = b02.f(b02.f8158b, b02.p(b02.f8172t)); iF2 < iF; iF2++) {
            Object obj = b02.c[b02.g(iF2)];
            int iC = -1;
            if (obj instanceof InterfaceC0749k) {
                int iO2 = b02.o() - iF2;
                InterfaceC0749k interfaceC0749k = (InterfaceC0749k) obj;
                C2453A c2453a = (C2453A) y10.f1603f;
                if (c2453a == null) {
                    int i10 = AbstractC2457E.f22585a;
                    c2453a = new C2453A();
                    y10.f1603f = c2453a;
                }
                c2453a.f22580b[c2453a.d(interfaceC0749k)] = interfaceC0749k;
                y10.h(iO2, -1, -1, interfaceC0749k);
            }
            if (obj instanceof v0) {
                int iO3 = b02.o() - iF2;
                v0 v0Var = (v0) obj;
                C0733c c0733c = v0Var.f8386b;
                if (c0733c == null || !c0733c.a()) {
                    iO = -1;
                } else {
                    iC = b02.c(c0733c);
                    iO = b02.o() - b02.f(b02.f8158b, b02.p(b02.q(iC) + iC));
                }
                y10.h(iO3, iC, iO, v0Var.f8385a);
            }
            if (obj instanceof C0754m0) {
                ((C0754m0) obj).d();
            }
        }
        b02.A();
    }

    public static final void T(boolean z6) {
        if (z6) {
            return;
        }
        y("Check failed");
        throw null;
    }

    public static final int U(ArrayList arrayList, int i7, int i10) {
        int size = arrayList.size() - 1;
        int i11 = 0;
        while (i11 <= size) {
            int i12 = (i11 + size) >>> 1;
            int i13 = ((C0733c) arrayList.get(i12)).f8235a;
            if (i13 < 0) {
                i13 += i10;
            }
            int iG = A7.m.g(i13, i7);
            if (iG < 0) {
                i11 = i12 + 1;
            } else {
                if (iG <= 0) {
                    return i12;
                }
                size = i12 - 1;
            }
        }
        return -(i11 + 1);
    }

    public static final void V(C0761q c0761q, Object obj, InterfaceC2785n interfaceC2785n) {
        if (c0761q.f8303O || !A7.m.a(c0761q.G(), obj)) {
            c0761q.a0(obj);
            c0761q.b(obj, interfaceC2785n);
        }
    }

    public static final C0819h W(InterfaceC2772a interfaceC2772a) {
        return new C0819h(new O0(interfaceC2772a, null));
    }

    public static final void X(String str) {
        throw new IllegalArgumentException(str);
    }

    public static final void Y(String str) {
        throw new IllegalStateException(str);
    }

    public static final int Z(int i7) {
        int i10 = 306783378 & i7;
        int i11 = 613566756 & i7;
        return (i7 & (-920350135)) | (i11 >> 1) | i10 | ((i10 << 1) & i11);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:50:? A[RETURN, SYNTHETIC] */
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
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void a(T.C0752l0 r10, z7.InterfaceC2785n r11, T.C0761q r12, int r13) {
        /*
            Method dump skipped, instruction units count: 262
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: T.C0735d.a(T.l0, z7.n, T.q, int):void");
    }

    public static final b0.e a0(C0752l0[] c0752l0Arr, InterfaceC0744h0 interfaceC0744h0, InterfaceC0744h0 interfaceC0744h02) {
        b0.e eVar = b0.e.o;
        b0.d dVar = new b0.d(eVar);
        dVar.f12194r = eVar;
        for (C0752l0 c0752l0 : c0752l0Arr) {
            AbstractC0750k0 abstractC0750k0 = c0752l0.f8266a;
            if (c0752l0.f8269f || !((b0.e) interfaceC0744h0).containsKey(abstractC0750k0)) {
                dVar.put(abstractC0750k0, abstractC0750k0.c(c0752l0, (T0) ((b0.e) interfaceC0744h02).get(abstractC0750k0)));
            }
        }
        return dVar.a();
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
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
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void b(T.C0752l0[] r7, z7.InterfaceC2785n r8, T.C0761q r9, int r10) {
        /*
            r0 = -1390796515(0xffffffffad1a211d, float:-8.761239E-12)
            r9.S(r0)
            T.h0 r0 = r9.m()
            T.Y r1 = T.C0735d.f8238b
            r2 = 201(0xc9, float:2.82E-43)
            r9.N(r2, r1)
            boolean r1 = r9.f8303O
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L25
            b0.e r1 = b0.e.o
            b0.e r1 = a0(r7, r0, r1)
            b0.e r0 = r9.Z(r0, r1)
            r9.f8297I = r2
        L23:
            r1 = r3
            goto L72
        L25:
            T.y0 r1 = r9.f8294F
            int r4 = r1.f8393g
            java.lang.Object r1 = r1.g(r4, r3)
            java.lang.String r4 = "null cannot be cast to non-null type androidx.compose.runtime.PersistentCompositionLocalMap"
            A7.m.d(r1, r4)
            T.h0 r1 = (T.InterfaceC0744h0) r1
            T.y0 r5 = r9.f8294F
            int r6 = r5.f8393g
            java.lang.Object r5 = r5.g(r6, r2)
            A7.m.d(r5, r4)
            T.h0 r5 = (T.InterfaceC0744h0) r5
            b0.e r4 = a0(r7, r0, r5)
            boolean r6 = r9.x()
            if (r6 == 0) goto L63
            boolean r6 = r9.f8324x
            if (r6 != 0) goto L63
            boolean r5 = r5.equals(r4)
            if (r5 != 0) goto L56
            goto L63
        L56:
            int r0 = r9.f8313k
            T.y0 r4 = r9.f8294F
            int r4 = r4.l()
            int r4 = r4 + r0
            r9.f8313k = r4
            r0 = r1
            goto L23
        L63:
            b0.e r0 = r9.Z(r0, r4)
            boolean r4 = r9.f8324x
            if (r4 != 0) goto L71
            boolean r1 = A7.m.a(r0, r1)
            if (r1 != 0) goto L23
        L71:
            r1 = r2
        L72:
            if (r1 == 0) goto L7b
            boolean r4 = r9.f8303O
            if (r4 != 0) goto L7b
            r9.E(r0)
        L7b:
            boolean r4 = r9.f8322v
            E0.v r5 = r9.f8323w
            r5.b(r4)
            r9.f8322v = r1
            r9.f8298J = r0
            T.Y r1 = T.C0735d.c
            r4 = 202(0xca, float:2.83E-43)
            r9.L(r1, r4, r0, r3)
            int r0 = r10 >> 3
            r0 = r0 & 14
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r8.invoke(r9, r0)
            r9.p(r3)
            r9.p(r3)
            int r0 = r5.a()
            if (r0 == 0) goto La5
            goto La6
        La5:
            r2 = r3
        La6:
            r9.f8322v = r2
            r0 = 0
            r9.f8298J = r0
            T.m0 r9 = r9.r()
            if (r9 == 0) goto Lb9
            F0.W r0 = new F0.W
            r1 = 5
            r0.<init>(r7, r8, r10, r1)
            r9.d = r0
        Lb9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: T.C0735d.b(T.l0[], z7.n, T.q, int):void");
    }

    public static final void c(Object obj, Object obj2, InterfaceC2782k interfaceC2782k, C0761q c0761q) {
        boolean zF = c0761q.f(obj) | c0761q.f(obj2);
        Object objG = c0761q.G();
        if (zF || objG == C0753m.f8270a) {
            objG = new E(interfaceC2782k);
            c0761q.a0(objG);
        }
    }

    public static final void d(Object obj, InterfaceC2782k interfaceC2782k, C0761q c0761q) {
        boolean zF = c0761q.f(obj);
        Object objG = c0761q.G();
        if (zF || objG == C0753m.f8270a) {
            objG = new E(interfaceC2782k);
            c0761q.a0(objG);
        }
    }

    public static final void e(Object[] objArr, InterfaceC2782k interfaceC2782k, C0761q c0761q) {
        boolean zF = false;
        for (Object obj : Arrays.copyOf(objArr, objArr.length)) {
            zF |= c0761q.f(obj);
        }
        Object objG = c0761q.G();
        if (zF || objG == C0753m.f8270a) {
            c0761q.a0(new E(interfaceC2782k));
        }
    }

    public static final void f(C0761q c0761q, Object obj, InterfaceC2785n interfaceC2785n) {
        InterfaceC2194h interfaceC2194hH = c0761q.f8306b.h();
        boolean zF = c0761q.f(obj);
        Object objG = c0761q.G();
        if (zF || objG == C0753m.f8270a) {
            objG = new O(interfaceC2194hH, interfaceC2785n);
            c0761q.a0(objG);
        }
    }

    public static final void g(Object obj, Object obj2, InterfaceC2785n interfaceC2785n, C0761q c0761q) {
        InterfaceC2194h interfaceC2194hH = c0761q.f8306b.h();
        boolean zF = c0761q.f(obj) | c0761q.f(obj2);
        Object objG = c0761q.G();
        if (zF || objG == C0753m.f8270a) {
            objG = new O(interfaceC2194hH, interfaceC2785n);
            c0761q.a0(objG);
        }
    }

    public static final void h(Object[] objArr, InterfaceC2785n interfaceC2785n, C0761q c0761q) {
        InterfaceC2194h interfaceC2194hH = c0761q.f8306b.h();
        boolean zF = false;
        for (Object obj : Arrays.copyOf(objArr, objArr.length)) {
            zF |= c0761q.f(obj);
        }
        Object objG = c0761q.G();
        if (zF || objG == C0753m.f8270a) {
            c0761q.a0(new O(interfaceC2194hH, interfaceC2785n));
        }
    }

    public static final void i(InterfaceC2772a interfaceC2772a, C0761q c0761q) {
        C0800a c0800a = c0761q.f8300L.f8944b;
        c0800a.getClass();
        U.v vVar = U.v.c;
        U.D d10 = c0800a.d;
        d10.W(vVar);
        com.bumptech.glide.c.C0(d10, 0, interfaceC2772a);
        int i7 = d10.f8941j;
        int i10 = vVar.f8934a;
        int iP = U.D.P(d10, i10);
        int i11 = vVar.f8935b;
        if (i7 == iP && d10.f8942k == U.D.P(d10, i11)) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        int i12 = 0;
        for (int i13 = 0; i13 < i10; i13++) {
            if (((1 << i13) & d10.f8941j) != 0) {
                if (i12 > 0) {
                    sb.append(", ");
                }
                sb.append(vVar.b(i13));
                i12++;
            }
        }
        String string = sb.toString();
        StringBuilder sbK = M5.b.k(string, "StringBuilder().apply(builderAction).toString()");
        int i14 = 0;
        for (int i15 = 0; i15 < i11; i15++) {
            if (((1 << i15) & d10.f8942k) != 0) {
                if (i12 > 0) {
                    sbK.append(", ");
                }
                sbK.append(vVar.c(i15));
                i14++;
            }
        }
        String string2 = sbK.toString();
        A7.m.e(string2, "StringBuilder().apply(builderAction).toString()");
        StringBuilder sb2 = new StringBuilder("Error while pushing ");
        sb2.append(vVar);
        sb2.append(". Not all arguments were provided. Missing ");
        M5.b.p(sb2, i12, " int arguments (", string, ") and ");
        M5.b.t(sb2, i14, " object arguments (", string2, ").");
        throw null;
    }

    public static final boolean j(int[] iArr, int i7) {
        return (iArr[(i7 * 5) + 1] & 67108864) != 0;
    }

    public static final int k(int[] iArr, int i7) {
        return iArr[(i7 * 5) + 4];
    }

    public static final int l(int[] iArr, int i7) {
        return iArr[(i7 * 5) + 3];
    }

    public static final boolean m(int[] iArr, int i7) {
        return (iArr[(i7 * 5) + 1] & 268435456) != 0;
    }

    public static final boolean n(int[] iArr, int i7) {
        return (iArr[(i7 * 5) + 1] & 536870912) != 0;
    }

    public static final boolean o(int[] iArr, int i7) {
        return (iArr[(i7 * 5) + 1] & TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED) != 0;
    }

    public static final int p(ArrayList arrayList, int i7, int i10) {
        int iU = U(arrayList, i7, i10);
        return iU >= 0 ? iU : -(iU + 1);
    }

    public static final int q(int[] iArr, int i7) {
        return iArr[(i7 * 5) + 1] & 67108863;
    }

    public static final int r(int[] iArr, int i7) {
        return iArr[(i7 * 5) + 2];
    }

    public static final void s(ArrayList arrayList, int i7, int i10) {
        int iH = H(i7, arrayList);
        if (iH < 0) {
            iH = -(iH + 1);
        }
        while (iH < arrayList.size() && ((L) arrayList.get(iH)).f8200b < i10) {
            arrayList.remove(iH);
        }
    }

    public static final int t(int[] iArr, int i7) {
        int i10 = i7 * 5;
        return A(iArr[i10 + 1] >> 28) + iArr[i10 + 4];
    }

    public static final void u(int i7, int i10, int[] iArr) {
        T(i10 >= 0);
        iArr[(i7 * 5) + 3] = i10;
    }

    public static final void v(int i7, int i10, int[] iArr) {
        T(i10 >= 0 && i10 < 67108863);
        int i11 = (i7 * 5) + 1;
        iArr[i11] = i10 | (iArr[i11] & (-67108864));
    }

    public static final X w(V8.O o, Object obj, InterfaceC2194h interfaceC2194h, C0761q c0761q, int i7, int i10) {
        if ((i10 & 2) != 0) {
            interfaceC2194h = C2195i.l;
        }
        boolean zH = c0761q.h(interfaceC2194h) | c0761q.h(o);
        Object objG = c0761q.G();
        Object obj2 = C0753m.f8270a;
        if (zH || objG == obj2) {
            objG = new N0(interfaceC2194h, o, null);
            c0761q.a0(objG);
        }
        InterfaceC2785n interfaceC2785n = (InterfaceC2785n) objG;
        Object objG2 = c0761q.G();
        if (objG2 == obj2) {
            objG2 = O(obj, S.f8224q);
            c0761q.a0(objG2);
        }
        X x8 = (X) objG2;
        boolean zH2 = c0761q.h(interfaceC2785n);
        Object objG3 = c0761q.G();
        if (zH2 || objG3 == obj2) {
            objG3 = new J0(interfaceC2785n, x8, null);
            c0761q.a0(objG3);
        }
        g(o, interfaceC2194h, (InterfaceC2785n) objG3, c0761q);
        return x8;
    }

    public static final X x(V8.d0 d0Var, C0761q c0761q, int i7) {
        return w(d0Var, d0Var.getValue(), C2195i.l, c0761q, (i7 & 14) | ((i7 << 3) & 896), 0);
    }

    public static final void y(String str) {
        throw new C0751l(android.hardware.bydauto.ac.tsimpl.a.i("Compose Runtime internal error. Unexpected or incorrect use of the Compose internal runtime API (", str, "). Please report to Google or use https://goo.gle/compose-feedback"));
    }

    public static final void z(String str) {
        throw new C0751l(android.hardware.bydauto.ac.tsimpl.a.i("Compose Runtime internal error. Unexpected or incorrect use of the Compose internal runtime API (", str, "). Please report to Google or use https://goo.gle/compose-feedback"));
    }
}
