package N0;

import a.AbstractC0904a;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import n0.AbstractC1861K;
import n0.C1865O;
import n0.C1891t;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class A extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f4306m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final A f4294n = new A(1, 0);
    public static final A o = new A(1, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final A f4295p = new A(1, 2);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final A f4296q = new A(1, 3);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final A f4297r = new A(1, 4);

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final A f4298s = new A(1, 5);

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final A f4299t = new A(1, 6);

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final A f4300u = new A(1, 7);

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final A f4301v = new A(1, 8);

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final A f4302w = new A(1, 9);

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final A f4303x = new A(1, 10);

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final A f4304y = new A(1, 11);

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static final A f4305z = new A(1, 12);

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public static final A f4286A = new A(1, 13);
    public static final A B = new A(1, 14);

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public static final A f4287C = new A(1, 15);

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public static final A f4288D = new A(1, 16);

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public static final A f4289E = new A(1, 17);

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public static final A f4290F = new A(1, 18);

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public static final A f4291G = new A(1, 19);

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public static final A f4292H = new A(1, 20);

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public static final A f4293I = new A(1, 21);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ A(int i7, int i10) {
        super(i7);
        this.f4306m = i10;
    }

    /* JADX WARN: Type inference failed for: r10v5, types: [A7.o, z7.k] */
    /* JADX WARN: Type inference failed for: r11v3, types: [A7.o, z7.k] */
    /* JADX WARN: Type inference failed for: r3v31, types: [A7.o, z7.k] */
    /* JADX WARN: Type inference failed for: r3v39, types: [A7.o, z7.k] */
    /* JADX WARN: Type inference failed for: r3v62, types: [A7.o, z7.k] */
    /* JADX WARN: Type inference failed for: r4v40, types: [A7.o, z7.k] */
    /* JADX WARN: Type inference failed for: r5v24, types: [A7.o, z7.k] */
    /* JADX WARN: Type inference failed for: r5v31, types: [A7.o, z7.k] */
    /* JADX WARN: Type inference failed for: r6v13, types: [A7.o, z7.k] */
    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        int i7 = 0;
        List list = null;
        d = null;
        D d = null;
        mVar = null;
        a1.m mVar = null;
        c1865o = null;
        C1865O c1865o = null;
        oVar = null;
        Y0.o oVar = null;
        i = null;
        I i10 = null;
        i = null;
        I i11 = null;
        c0416k = null;
        C0416k c0416k = null;
        c0417l = null;
        C0417l c0417l = null;
        m = null;
        M m10 = null;
        n = null;
        N n10 = null;
        d = null;
        D d10 = null;
        uVar = null;
        u uVar = null;
        list = null;
        switch (this.f4306m) {
            case 0:
                A7.m.d(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>");
                List list2 = (List) obj;
                Object obj2 = list2.get(1);
                io.sentry.internal.debugmeta.c cVar = C.f4308a;
                Boolean bool = Boolean.FALSE;
                List list3 = (A7.m.a(obj2, bool) || obj2 == null) ? null : (List) ((InterfaceC2782k) cVar.f16570n).b(obj2);
                Object obj3 = list2.get(2);
                List list4 = (A7.m.a(obj3, bool) || obj3 == null) ? null : (List) ((InterfaceC2782k) cVar.f16570n).b(obj3);
                Object obj4 = list2.get(0);
                String str = obj4 != null ? (String) obj4 : null;
                A7.m.c(str);
                if (list3 == null || list3.isEmpty()) {
                    list3 = null;
                }
                if (list4 == null || list4.isEmpty()) {
                    list4 = null;
                }
                Object obj5 = list2.get(3);
                if (!A7.m.a(obj5, bool) && obj5 != null) {
                    list = (List) ((InterfaceC2782k) cVar.f16570n).b(obj5);
                }
                return new C0411f(str, list3, list4, list);
            case 1:
                A7.m.d(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
                List list5 = (List) obj;
                ArrayList arrayList = new ArrayList(list5.size());
                int size = list5.size();
                while (i7 < size) {
                    Object obj6 = list5.get(i7);
                    C0409d c0409d = (A7.m.a(obj6, Boolean.FALSE) || obj6 == null) ? null : (C0409d) ((InterfaceC2782k) C.f4309b.f16570n).b(obj6);
                    A7.m.c(c0409d);
                    arrayList.add(c0409d);
                    i7++;
                }
                return arrayList;
            case 2:
                A7.m.d(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
                List list6 = (List) obj;
                Object obj7 = list6.get(0);
                EnumC0413h enumC0413h = obj7 != null ? (EnumC0413h) obj7 : null;
                A7.m.c(enumC0413h);
                Object obj8 = list6.get(2);
                Integer num = obj8 != null ? (Integer) obj8 : null;
                A7.m.c(num);
                int iIntValue = num.intValue();
                Object obj9 = list6.get(3);
                Integer num2 = obj9 != null ? (Integer) obj9 : null;
                A7.m.c(num2);
                int iIntValue2 = num2.intValue();
                Object obj10 = list6.get(4);
                String str2 = obj10 != null ? (String) obj10 : null;
                A7.m.c(str2);
                switch (enumC0413h.ordinal()) {
                    case 0:
                        Object obj11 = list6.get(1);
                        io.sentry.internal.debugmeta.c cVar2 = C.f4312g;
                        if (!A7.m.a(obj11, Boolean.FALSE) && obj11 != null) {
                            uVar = (u) ((InterfaceC2782k) cVar2.f16570n).b(obj11);
                        }
                        A7.m.c(uVar);
                        return new C0409d(uVar, iIntValue, iIntValue2, str2);
                    case 1:
                        Object obj12 = list6.get(1);
                        io.sentry.internal.debugmeta.c cVar3 = C.f4313h;
                        if (!A7.m.a(obj12, Boolean.FALSE) && obj12 != null) {
                            d10 = (D) ((InterfaceC2782k) cVar3.f16570n).b(obj12);
                        }
                        A7.m.c(d10);
                        return new C0409d(d10, iIntValue, iIntValue2, str2);
                    case 2:
                        Object obj13 = list6.get(1);
                        io.sentry.internal.debugmeta.c cVar4 = C.c;
                        if (!A7.m.a(obj13, Boolean.FALSE) && obj13 != null) {
                            n10 = (N) ((InterfaceC2782k) cVar4.f16570n).b(obj13);
                        }
                        A7.m.c(n10);
                        return new C0409d(n10, iIntValue, iIntValue2, str2);
                    case 3:
                        Object obj14 = list6.get(1);
                        io.sentry.internal.debugmeta.c cVar5 = C.d;
                        if (!A7.m.a(obj14, Boolean.FALSE) && obj14 != null) {
                            m10 = (M) ((InterfaceC2782k) cVar5.f16570n).b(obj14);
                        }
                        A7.m.c(m10);
                        return new C0409d(m10, iIntValue, iIntValue2, str2);
                    case 4:
                        Object obj15 = list6.get(1);
                        io.sentry.internal.debugmeta.c cVar6 = C.f4310e;
                        if (!A7.m.a(obj15, Boolean.FALSE) && obj15 != null) {
                            c0417l = (C0417l) ((InterfaceC2782k) cVar6.f16570n).b(obj15);
                        }
                        A7.m.c(c0417l);
                        return new C0409d(c0417l, iIntValue, iIntValue2, str2);
                    case 5:
                        Object obj16 = list6.get(1);
                        io.sentry.internal.debugmeta.c cVar7 = C.f4311f;
                        if (!A7.m.a(obj16, Boolean.FALSE) && obj16 != null) {
                            c0416k = (C0416k) ((InterfaceC2782k) cVar7.f16570n).b(obj16);
                        }
                        A7.m.c(c0416k);
                        return new C0409d(c0416k, iIntValue, iIntValue2, str2);
                    case 6:
                        Object obj17 = list6.get(1);
                        String str3 = obj17 != null ? (String) obj17 : null;
                        A7.m.c(str3);
                        return new C0409d(str3, iIntValue, iIntValue2, str2);
                    default:
                        throw new A9.k();
                }
            case 3:
                A7.m.d(obj, "null cannot be cast to non-null type kotlin.Float");
                return new Y0.a(((Float) obj).floatValue());
            case 4:
                A7.m.d(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>");
                List list7 = (List) obj;
                Object obj18 = list7.get(0);
                String str4 = obj18 != null ? (String) obj18 : null;
                A7.m.c(str4);
                Object obj19 = list7.get(1);
                io.sentry.internal.debugmeta.c cVar8 = C.f4314i;
                if (!A7.m.a(obj19, Boolean.FALSE) && obj19 != null) {
                    i11 = (I) ((InterfaceC2782k) cVar8.f16570n).b(obj19);
                }
                return new C0416k(str4, i11);
            case 5:
                if (A7.m.a(obj, Boolean.FALSE)) {
                    return new C1891t(C1891t.f19452k);
                }
                A7.m.d(obj, "null cannot be cast to non-null type kotlin.Int");
                return new C1891t(AbstractC1861K.c(((Integer) obj).intValue()));
            case 6:
                A7.m.d(obj, "null cannot be cast to non-null type kotlin.Int");
                return new S0.A(((Integer) obj).intValue());
            case 7:
                A7.m.d(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>");
                List list8 = (List) obj;
                Object obj20 = list8.get(0);
                String str5 = obj20 != null ? (String) obj20 : null;
                A7.m.c(str5);
                Object obj21 = list8.get(1);
                io.sentry.internal.debugmeta.c cVar9 = C.f4314i;
                if (!A7.m.a(obj21, Boolean.FALSE) && obj21 != null) {
                    i10 = (I) ((InterfaceC2782k) cVar9.f16570n).b(obj21);
                }
                return new C0417l(str5, i10);
            case 8:
                A7.m.d(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
                List list9 = (List) obj;
                ArrayList arrayList2 = new ArrayList(list9.size());
                int size2 = list9.size();
                while (i7 < size2) {
                    Object obj22 = list9.get(i7);
                    U0.a aVar = (A7.m.a(obj22, Boolean.FALSE) || obj22 == null) ? null : (U0.a) ((InterfaceC2782k) C.f4323t.f16570n).b(obj22);
                    A7.m.c(aVar);
                    arrayList2.add(aVar);
                    i7++;
                }
                return new U0.b(arrayList2);
            case 9:
                A7.m.d(obj, "null cannot be cast to non-null type kotlin.String");
                String str6 = (String) obj;
                U0.c.f8959a.getClass();
                Locale localeForLanguageTag = Locale.forLanguageTag(str6);
                if (A7.m.a(localeForLanguageTag.toLanguageTag(), "und")) {
                    h6.n.j("Locale", "The language tag " + str6 + " is not well-formed. Locale is resolved to Undetermined. Note that underscore '_' is not a valid subtag delimiter and must be replaced with '-'.");
                }
                return new U0.a(localeForLanguageTag);
            case 10:
                if (A7.m.a(obj, Boolean.FALSE)) {
                    return new m0.c(9205357640488583168L);
                }
                A7.m.d(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>");
                List list10 = (List) obj;
                Object obj23 = list10.get(0);
                Float f10 = obj23 != null ? (Float) obj23 : null;
                A7.m.c(f10);
                float fFloatValue = f10.floatValue();
                Object obj24 = list10.get(1);
                Float f11 = obj24 != null ? (Float) obj24 : null;
                A7.m.c(f11);
                return new m0.c(h6.e.a(fFloatValue, f11.floatValue()));
            case 11:
                A7.m.d(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>");
                List list11 = (List) obj;
                Object obj25 = list11.get(0);
                Y0.i iVar = obj25 != null ? (Y0.i) obj25 : null;
                A7.m.c(iVar);
                Object obj26 = list11.get(1);
                Y0.k kVar = obj26 != null ? (Y0.k) obj26 : null;
                A7.m.c(kVar);
                Object obj27 = list11.get(2);
                a1.n[] nVarArr = a1.m.f11041b;
                B b10 = C.f4320q;
                Boolean bool2 = Boolean.FALSE;
                a1.m mVar2 = ((A7.m.a(obj27, bool2) && b10 == null) || obj27 == null) ? null : (a1.m) b10.f4307m.b(obj27);
                A7.m.c(mVar2);
                Object obj28 = list11.get(3);
                Y0.o oVar2 = Y0.o.c;
                io.sentry.internal.debugmeta.c cVar10 = C.l;
                if (!A7.m.a(obj28, bool2) && obj28 != null) {
                    oVar = (Y0.o) ((InterfaceC2782k) cVar10.f16570n).b(obj28);
                }
                return new u(iVar.f10618a, kVar.f10621a, mVar2.f11042a, oVar, null, null, 0, Integer.MIN_VALUE, null);
            case 12:
                A7.m.d(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
                List list12 = (List) obj;
                Object obj29 = list12.get(0);
                int i12 = C1891t.l;
                B b11 = C.f4319p;
                Boolean bool3 = Boolean.FALSE;
                C1891t c1891t = ((A7.m.a(obj29, bool3) && b11 == null) || obj29 == null) ? null : (C1891t) b11.f4307m.b(obj29);
                A7.m.c(c1891t);
                Object obj30 = list12.get(1);
                B b12 = C.f4321r;
                m0.c cVar11 = ((A7.m.a(obj30, bool3) && b12 == null) || obj30 == null) ? null : (m0.c) b12.f4307m.b(obj30);
                A7.m.c(cVar11);
                Object obj31 = list12.get(2);
                Float f12 = obj31 != null ? (Float) obj31 : null;
                A7.m.c(f12);
                return new C1865O(c1891t.f19453a, cVar11.f18880a, f12.floatValue());
            case 13:
                A7.m.d(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>");
                List list13 = (List) obj;
                Object obj32 = list13.get(0);
                int i13 = C1891t.l;
                B b13 = C.f4319p;
                Boolean bool4 = Boolean.FALSE;
                C1891t c1891t2 = ((A7.m.a(obj32, bool4) && b13 == null) || obj32 == null) ? null : (C1891t) b13.f4307m.b(obj32);
                A7.m.c(c1891t2);
                Object obj33 = list13.get(1);
                a1.n[] nVarArr2 = a1.m.f11041b;
                B b14 = C.f4320q;
                a1.m mVar3 = ((A7.m.a(obj33, bool4) && b14 == null) || obj33 == null) ? null : (a1.m) b14.f4307m.b(obj33);
                A7.m.c(mVar3);
                Object obj34 = list13.get(2);
                S0.A a10 = S0.A.f7795m;
                S0.A a11 = (A7.m.a(obj34, bool4) || obj34 == null) ? null : (S0.A) ((InterfaceC2782k) C.f4317m.f16570n).b(obj34);
                Object obj35 = list13.get(3);
                S0.w wVar = obj35 != null ? (S0.w) obj35 : null;
                Object obj36 = list13.get(4);
                S0.x xVar = obj36 != null ? (S0.x) obj36 : null;
                Object obj37 = list13.get(6);
                String str7 = obj37 != null ? (String) obj37 : null;
                Object obj38 = list13.get(7);
                a1.m mVar4 = ((A7.m.a(obj38, bool4) && b14 == null) || obj38 == null) ? null : (a1.m) b14.f4307m.b(obj38);
                A7.m.c(mVar4);
                Object obj39 = list13.get(8);
                Y0.a aVar2 = (A7.m.a(obj39, bool4) || obj39 == null) ? null : (Y0.a) ((InterfaceC2782k) C.f4318n.f16570n).b(obj39);
                Object obj40 = list13.get(9);
                Y0.n nVar = (A7.m.a(obj40, bool4) || obj40 == null) ? null : (Y0.n) ((InterfaceC2782k) C.f4316k.f16570n).b(obj40);
                Object obj41 = list13.get(10);
                U0.b bVar = U0.b.f8957n;
                U0.b bVar2 = (A7.m.a(obj41, bool4) || obj41 == null) ? null : (U0.b) ((InterfaceC2782k) C.f4322s.f16570n).b(obj41);
                Object obj42 = list13.get(11);
                C1891t c1891t3 = ((A7.m.a(obj42, bool4) && b13 == null) || obj42 == null) ? null : (C1891t) b13.f4307m.b(obj42);
                A7.m.c(c1891t3);
                Object obj43 = list13.get(12);
                Y0.j jVar = (A7.m.a(obj43, bool4) || obj43 == null) ? null : (Y0.j) ((InterfaceC2782k) C.f4315j.f16570n).b(obj43);
                Object obj44 = list13.get(13);
                C1865O c1865o2 = C1865O.d;
                io.sentry.internal.debugmeta.c cVar12 = C.o;
                if (!A7.m.a(obj44, bool4) && obj44 != null) {
                    c1865o = (C1865O) ((InterfaceC2782k) cVar12.f16570n).b(obj44);
                }
                return new D(c1891t2.f19453a, mVar3.f11042a, a11, wVar, xVar, null, str7, mVar4.f11042a, aVar2, nVar, bVar2, c1891t3.f19453a, jVar, c1865o, 49184);
            case 14:
                A7.m.d(obj, "null cannot be cast to non-null type kotlin.Int");
                return new Y0.j(((Integer) obj).intValue());
            case 15:
                A7.m.d(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Float>");
                List list14 = (List) obj;
                return new Y0.n(((Number) list14.get(0)).floatValue(), ((Number) list14.get(1)).floatValue());
            case 16:
                A7.m.d(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
                List list15 = (List) obj;
                Object obj45 = list15.get(0);
                a1.n[] nVarArr3 = a1.m.f11041b;
                B b15 = C.f4320q;
                Boolean bool5 = Boolean.FALSE;
                a1.m mVar5 = ((A7.m.a(obj45, bool5) && b15 == null) || obj45 == null) ? null : (a1.m) b15.f4307m.b(obj45);
                A7.m.c(mVar5);
                Object obj46 = list15.get(1);
                if ((!A7.m.a(obj46, bool5) || b15 != null) && obj46 != null) {
                    mVar = (a1.m) b15.f4307m.b(obj46);
                }
                A7.m.c(mVar);
                return new Y0.o(mVar5.f11042a, mVar.f11042a);
            case 17:
                A7.m.d(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>");
                List list16 = (List) obj;
                Object obj47 = list16.get(0);
                io.sentry.internal.debugmeta.c cVar13 = C.f4313h;
                Boolean bool6 = Boolean.FALSE;
                D d11 = (A7.m.a(obj47, bool6) || obj47 == null) ? null : (D) ((InterfaceC2782k) cVar13.f16570n).b(obj47);
                Object obj48 = list16.get(1);
                D d12 = (A7.m.a(obj48, bool6) || obj48 == null) ? null : (D) ((InterfaceC2782k) cVar13.f16570n).b(obj48);
                Object obj49 = list16.get(2);
                D d13 = (A7.m.a(obj49, bool6) || obj49 == null) ? null : (D) ((InterfaceC2782k) cVar13.f16570n).b(obj49);
                Object obj50 = list16.get(3);
                if (!A7.m.a(obj50, bool6) && obj50 != null) {
                    d = (D) ((InterfaceC2782k) cVar13.f16570n).b(obj50);
                }
                return new I(d11, d12, d13, d);
            case 18:
                A7.m.d(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
                List list17 = (List) obj;
                Object obj51 = list17.get(0);
                Integer num3 = obj51 != null ? (Integer) obj51 : null;
                A7.m.c(num3);
                int iIntValue3 = num3.intValue();
                Object obj52 = list17.get(1);
                Integer num4 = obj52 != null ? (Integer) obj52 : null;
                A7.m.c(num4);
                return new K(AbstractC0904a.q(iIntValue3, num4.intValue()));
            case 19:
                if (A7.m.a(obj, Boolean.FALSE)) {
                    return new a1.m(a1.m.c);
                }
                A7.m.d(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
                List list18 = (List) obj;
                Object obj53 = list18.get(0);
                Float f13 = obj53 != null ? (Float) obj53 : null;
                A7.m.c(f13);
                float fFloatValue2 = f13.floatValue();
                Object obj54 = list18.get(1);
                a1.n nVar2 = obj54 != null ? (a1.n) obj54 : null;
                A7.m.c(nVar2);
                return new a1.m(AbstractC0904a.O(fFloatValue2, nVar2.f11043a));
            case 20:
                String str8 = obj != null ? (String) obj : null;
                A7.m.c(str8);
                return new M(str8);
            default:
                String str9 = obj != null ? (String) obj : null;
                A7.m.c(str9);
                return new N(str9);
        }
    }
}
