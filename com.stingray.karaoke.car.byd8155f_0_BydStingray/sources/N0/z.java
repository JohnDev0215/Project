package N0;

import d0.C1053b;
import java.util.ArrayList;
import java.util.List;
import n0.AbstractC1861K;
import n0.C1865O;
import n0.C1891t;
import n7.AbstractC1999n;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class z extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f4443m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final z f4431n = new z(2, 0);
    public static final z o = new z(2, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final z f4432p = new z(2, 2);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final z f4433q = new z(2, 3);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final z f4434r = new z(2, 4);

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final z f4435s = new z(2, 5);

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final z f4436t = new z(2, 6);

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final z f4437u = new z(2, 7);

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final z f4438v = new z(2, 8);

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final z f4439w = new z(2, 9);

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final z f4440x = new z(2, 10);

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final z f4441y = new z(2, 11);

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static final z f4442z = new z(2, 12);

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public static final z f4423A = new z(2, 13);
    public static final z B = new z(2, 14);

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public static final z f4424C = new z(2, 15);

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public static final z f4425D = new z(2, 16);

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public static final z f4426E = new z(2, 17);

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public static final z f4427F = new z(2, 18);

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public static final z f4428G = new z(2, 19);

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public static final z f4429H = new z(2, 20);

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public static final z f4430I = new z(2, 21);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ z(int i7, int i10) {
        super(i7);
        this.f4443m = i10;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        int i7 = 0;
        switch (this.f4443m) {
            case 0:
                C1053b c1053b = (C1053b) obj;
                C0411f c0411f = (C0411f) obj2;
                String str = c0411f.l;
                io.sentry.internal.debugmeta.c cVar = C.f4308a;
                List listB = c0411f.b();
                io.sentry.internal.debugmeta.c cVar2 = C.f4308a;
                Object objA = C.a(listB, cVar2, c1053b);
                Object obj3 = c0411f.f4374n;
                if (obj3 == null) {
                    obj3 = n7.u.l;
                }
                return AbstractC1999n.z(str, objA, C.a(obj3, cVar2, c1053b), C.a(c0411f.o, cVar2, c1053b));
            case 1:
                C1053b c1053b2 = (C1053b) obj;
                List list = (List) obj2;
                ArrayList arrayList = new ArrayList(list.size());
                int size = list.size();
                while (i7 < size) {
                    arrayList.add(C.a((C0409d) list.get(i7), C.f4309b, c1053b2));
                    i7++;
                }
                return arrayList;
            case 2:
                C1053b c1053b3 = (C1053b) obj;
                C0409d c0409d = (C0409d) obj2;
                Object obj4 = c0409d.f4371a;
                EnumC0413h enumC0413h = obj4 instanceof u ? EnumC0413h.l : obj4 instanceof D ? EnumC0413h.f4376m : obj4 instanceof N ? EnumC0413h.f4377n : obj4 instanceof M ? EnumC0413h.o : obj4 instanceof C0417l ? EnumC0413h.f4378p : obj4 instanceof C0416k ? EnumC0413h.f4379q : EnumC0413h.f4380r;
                int iOrdinal = enumC0413h.ordinal();
                Object objA2 = c0409d.f4371a;
                switch (iOrdinal) {
                    case 0:
                        A7.m.d(objA2, "null cannot be cast to non-null type androidx.compose.ui.text.ParagraphStyle");
                        objA2 = C.a((u) objA2, C.f4312g, c1053b3);
                        break;
                    case 1:
                        A7.m.d(objA2, "null cannot be cast to non-null type androidx.compose.ui.text.SpanStyle");
                        objA2 = C.a((D) objA2, C.f4313h, c1053b3);
                        break;
                    case 2:
                        A7.m.d(objA2, "null cannot be cast to non-null type androidx.compose.ui.text.VerbatimTtsAnnotation");
                        objA2 = C.a((N) objA2, C.c, c1053b3);
                        break;
                    case 3:
                        A7.m.d(objA2, "null cannot be cast to non-null type androidx.compose.ui.text.UrlAnnotation");
                        objA2 = C.a((M) objA2, C.d, c1053b3);
                        break;
                    case 4:
                        A7.m.d(objA2, "null cannot be cast to non-null type androidx.compose.ui.text.LinkAnnotation.Url");
                        objA2 = C.a((C0417l) objA2, C.f4310e, c1053b3);
                        break;
                    case 5:
                        A7.m.d(objA2, "null cannot be cast to non-null type androidx.compose.ui.text.LinkAnnotation.Clickable");
                        objA2 = C.a((C0416k) objA2, C.f4311f, c1053b3);
                        break;
                    case 6:
                        io.sentry.internal.debugmeta.c cVar3 = C.f4308a;
                        break;
                    default:
                        throw new A9.k();
                }
                return AbstractC1999n.z(enumC0413h, objA2, Integer.valueOf(c0409d.f4372b), Integer.valueOf(c0409d.c), c0409d.d);
            case 3:
                return Float.valueOf(((Y0.a) obj2).f10605a);
            case 4:
                C0416k c0416k = (C0416k) obj2;
                return AbstractC1999n.z(c0416k.f4383a, C.a(c0416k.f4384b, C.f4314i, (C1053b) obj));
            case 5:
                long j3 = ((C1891t) obj2).f19453a;
                return j3 == 16 ? Boolean.FALSE : Integer.valueOf(AbstractC1861K.E(j3));
            case 6:
                return Integer.valueOf(((S0.A) obj2).l);
            case 7:
                C0417l c0417l = (C0417l) obj2;
                return AbstractC1999n.z(c0417l.f4385a, C.a(c0417l.f4386b, C.f4314i, (C1053b) obj));
            case 8:
                C1053b c1053b4 = (C1053b) obj;
                List list2 = ((U0.b) obj2).l;
                ArrayList arrayList2 = new ArrayList(list2.size());
                int size2 = list2.size();
                while (i7 < size2) {
                    arrayList2.add(C.a((U0.a) list2.get(i7), C.f4323t, c1053b4));
                    i7++;
                }
                return arrayList2;
            case 9:
                return ((U0.a) obj2).f8956a.toLanguageTag();
            case 10:
                long j10 = ((m0.c) obj2).f18880a;
                if (m0.c.b(j10, 9205357640488583168L)) {
                    return Boolean.FALSE;
                }
                Float fValueOf = Float.valueOf(m0.c.d(j10));
                io.sentry.internal.debugmeta.c cVar4 = C.f4308a;
                return AbstractC1999n.z(fValueOf, Float.valueOf(m0.c.e(j10)));
            case 11:
                C1053b c1053b5 = (C1053b) obj;
                u uVar = (u) obj2;
                Y0.i iVar = new Y0.i(uVar.f4409a);
                io.sentry.internal.debugmeta.c cVar5 = C.f4308a;
                Y0.k kVar = new Y0.k(uVar.f4410b);
                Object objA3 = C.a(new a1.m(uVar.c), C.f4320q, c1053b5);
                Y0.o oVar = Y0.o.c;
                return AbstractC1999n.z(iVar, kVar, objA3, C.a(uVar.d, C.l, c1053b5));
            case 12:
                C1053b c1053b6 = (C1053b) obj;
                C1865O c1865o = (C1865O) obj2;
                return AbstractC1999n.z(C.a(new C1891t(c1865o.f19402a), C.f4319p, c1053b6), C.a(new m0.c(c1865o.f19403b), C.f4321r, c1053b6), Float.valueOf(c1865o.c));
            case 13:
                C1053b c1053b7 = (C1053b) obj;
                D d = (D) obj2;
                C1891t c1891t = new C1891t(d.f4324a.a());
                B b10 = C.f4319p;
                Object objA4 = C.a(c1891t, b10, c1053b7);
                a1.m mVar = new a1.m(d.f4325b);
                B b11 = C.f4320q;
                Object objA5 = C.a(mVar, b11, c1053b7);
                S0.A a10 = S0.A.f7795m;
                Object objA6 = C.a(d.c, C.f4317m, c1053b7);
                Object objA7 = C.a(new a1.m(d.f4329h), b11, c1053b7);
                Object objA8 = C.a(d.f4330i, C.f4318n, c1053b7);
                Object objA9 = C.a(d.f4331j, C.f4316k, c1053b7);
                U0.b bVar = U0.b.f8957n;
                Object objA10 = C.a(d.f4332k, C.f4322s, c1053b7);
                Object objA11 = C.a(new C1891t(d.l), b10, c1053b7);
                Object objA12 = C.a(d.f4333m, C.f4315j, c1053b7);
                C1865O c1865o2 = C1865O.d;
                return AbstractC1999n.z(objA4, objA5, objA6, d.d, d.f4326e, -1, d.f4328g, objA7, objA8, objA9, objA10, objA11, objA12, C.a(d.f4334n, C.o, c1053b7));
            case 14:
                return Integer.valueOf(((Y0.j) obj2).f10620a);
            case 15:
                Y0.n nVar = (Y0.n) obj2;
                return AbstractC1999n.z(Float.valueOf(nVar.f10623a), Float.valueOf(nVar.f10624b));
            case 16:
                C1053b c1053b8 = (C1053b) obj;
                Y0.o oVar2 = (Y0.o) obj2;
                a1.m mVar2 = new a1.m(oVar2.f10625a);
                B b12 = C.f4320q;
                return AbstractC1999n.z(C.a(mVar2, b12, c1053b8), C.a(new a1.m(oVar2.f10626b), b12, c1053b8));
            case 17:
                C1053b c1053b9 = (C1053b) obj;
                I i10 = (I) obj2;
                D d10 = i10.f4352a;
                io.sentry.internal.debugmeta.c cVar6 = C.f4313h;
                return AbstractC1999n.z(C.a(d10, cVar6, c1053b9), C.a(i10.f4353b, cVar6, c1053b9), C.a(i10.c, cVar6, c1053b9), C.a(i10.d, cVar6, c1053b9));
            case 18:
                long j11 = ((K) obj2).f4357a;
                int i11 = K.c;
                Integer numValueOf = Integer.valueOf((int) (j11 >> 32));
                io.sentry.internal.debugmeta.c cVar7 = C.f4308a;
                return AbstractC1999n.z(numValueOf, Integer.valueOf((int) (j11 & 4294967295L)));
            case 19:
                long j12 = ((a1.m) obj2).f11042a;
                if (a1.m.a(j12, a1.m.c)) {
                    return Boolean.FALSE;
                }
                Float fValueOf2 = Float.valueOf(a1.m.c(j12));
                io.sentry.internal.debugmeta.c cVar8 = C.f4308a;
                return AbstractC1999n.z(fValueOf2, new a1.n(a1.m.b(j12)));
            case 20:
                String str2 = ((M) obj2).f4360a;
                io.sentry.internal.debugmeta.c cVar9 = C.f4308a;
                return str2;
            default:
                String str3 = ((N) obj2).f4361a;
                io.sentry.internal.debugmeta.c cVar10 = C.f4308a;
                return str3;
        }
    }
}
