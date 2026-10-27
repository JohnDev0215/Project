package u2;

import B2.C0075o;
import F0.E0;
import android.content.Context;
import android.net.Uri;
import e2.C1192A;
import e2.C1207o;
import e2.C1208p;
import e2.C1211t;
import e2.C1212u;
import e2.C1214w;
import e2.C1215x;
import e2.C1216y;
import e2.C1217z;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import io.sentry.C1458d0;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import k2.InterfaceC1606g;

/* JADX INFO: renamed from: u2.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2514q implements InterfaceC2491B {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g9.e f22889a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final M.t f22890b;
    public io.sentry.hints.i c;
    public final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f22891e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f22892f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final float f22893g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final float f22894h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f22895i;

    public C2514q(Context context, C0075o c0075o) {
        M.t tVar = new M.t(context);
        this.f22890b = tVar;
        io.sentry.hints.i iVar = new io.sentry.hints.i(14);
        this.c = iVar;
        g9.e eVar = new g9.e(c0075o, iVar);
        this.f22889a = eVar;
        if (tVar != ((M.t) eVar.f15044e)) {
            eVar.f15044e = tVar;
            ((HashMap) eVar.c).clear();
            ((HashMap) eVar.d).clear();
        }
        this.d = -9223372036854775807L;
        this.f22891e = -9223372036854775807L;
        this.f22892f = -9223372036854775807L;
        this.f22893g = -3.4028235E38f;
        this.f22894h = -3.4028235E38f;
        this.f22895i = true;
    }

    public static InterfaceC2491B d(Class cls, M.t tVar) {
        try {
            return (InterfaceC2491B) cls.getConstructor(InterfaceC1606g.class).newInstance(tVar);
        } catch (Exception e10) {
            throw new IllegalStateException(e10);
        }
    }

    @Override // u2.InterfaceC2491B
    public final void a(boolean z6) {
        this.f22895i = z6;
        g9.e eVar = this.f22889a;
        eVar.f15042a = z6;
        C0075o c0075o = (C0075o) eVar.f15043b;
        synchronized (c0075o) {
            c0075o.f595m = z6;
        }
        Iterator it = ((HashMap) eVar.d).values().iterator();
        while (it.hasNext()) {
            ((InterfaceC2491B) it.next()).a(z6);
        }
    }

    @Override // u2.InterfaceC2491B
    public final void b(io.sentry.hints.i iVar) {
        this.c = iVar;
        g9.e eVar = this.f22889a;
        eVar.f15045f = iVar;
        C0075o c0075o = (C0075o) eVar.f15043b;
        synchronized (c0075o) {
            c0075o.f596n = iVar;
        }
        Iterator it = ((HashMap) eVar.d).values().iterator();
        while (it.hasNext()) {
            ((InterfaceC2491B) it.next()).b(iVar);
        }
    }

    @Override // u2.InterfaceC2491B
    public final AbstractC2498a c(e2.F f10) {
        int i7;
        long j3;
        List list;
        d5.I i10;
        Uri uri;
        String str;
        C1211t c1211t;
        String str2;
        C1192A c1192a;
        E0 e02;
        e2.F f11 = f10;
        f11.f13879b.getClass();
        String scheme = f11.f13879b.f13845a.getScheme();
        if (scheme != null && scheme.equals("ssai")) {
            throw null;
        }
        if (Objects.equals(f11.f13879b.f13846b, "application/x-image-uri")) {
            long j10 = f11.f13879b.f13850h;
            int i11 = AbstractC1343A.f15195a;
            throw null;
        }
        C1192A c1192a2 = f11.f13879b;
        int iB = AbstractC1343A.B(c1192a2.f13845a, c1192a2.f13846b);
        if (f11.f13879b.f13850h != -9223372036854775807L) {
            C0075o c0075o = (C0075o) this.f22889a.f15043b;
            synchronized (c0075o) {
                c0075o.o = 1;
            }
        }
        try {
            g9.e eVar = this.f22889a;
            HashMap map = (HashMap) eVar.d;
            InterfaceC2491B interfaceC2491B = (InterfaceC2491B) map.get(Integer.valueOf(iB));
            if (interfaceC2491B == null) {
                interfaceC2491B = (InterfaceC2491B) eVar.b(iB).get();
                interfaceC2491B.b((io.sentry.hints.i) eVar.f15045f);
                interfaceC2491B.a(eVar.f15042a);
                map.put(Integer.valueOf(iB), interfaceC2491B);
            }
            C1216y c1216yA = f11.c.a();
            C1217z c1217z = f11.c;
            if (c1217z.f14376a == -9223372036854775807L) {
                c1216yA.f14367a = this.d;
            }
            if (c1217z.d == -3.4028235E38f) {
                c1216yA.d = this.f22893g;
            }
            if (c1217z.f14378e == -3.4028235E38f) {
                c1216yA.f14369e = this.f22894h;
            }
            if (c1217z.f14377b == -9223372036854775807L) {
                c1216yA.f14368b = this.f22891e;
            }
            if (c1217z.c == -9223372036854775807L) {
                c1216yA.c = this.f22892f;
            }
            C1217z c1217z2 = new C1217z(c1216yA);
            if (!c1217z2.equals(f11.c)) {
                E0 e03 = new E0();
                List listEmptyList = Collections.emptyList();
                d5.d0 d0Var = d5.d0.f13415p;
                e2.B b10 = e2.B.d;
                C1212u c1212u = new C1212u();
                C1214w c1214w = f11.f13880e;
                c1212u.f14340a = c1214w.f14350b;
                c1212u.f14341b = c1214w.d;
                c1212u.c = c1214w.f14351e;
                c1212u.d = c1214w.f14352f;
                c1212u.f14342e = c1214w.f14353g;
                String str3 = f11.f13878a;
                e2.I i12 = f11.d;
                f11.c.a();
                e2.B b11 = f11.f13881f;
                C1192A c1192a3 = f11.f13879b;
                if (c1192a3 != null) {
                    String str4 = c1192a3.f13848f;
                    String str5 = c1192a3.f13846b;
                    Uri uri2 = c1192a3.f13845a;
                    List list2 = c1192a3.f13847e;
                    d5.I i13 = c1192a3.f13849g;
                    C1215x c1215x = c1192a3.c;
                    if (c1215x != null) {
                        E0 e04 = new E0();
                        e04.d = c1215x.f14361a;
                        e04.f2016e = c1215x.f14362b;
                        e04.f2017f = c1215x.c;
                        e04.f2014a = c1215x.d;
                        e04.f2015b = c1215x.f14363e;
                        e04.c = c1215x.f14364f;
                        e04.f2018g = c1215x.f14365g;
                        e04.f2019h = c1215x.f14366h;
                        e02 = e04;
                    } else {
                        e02 = new E0();
                    }
                    C1211t c1211t2 = c1192a3.d;
                    j3 = c1192a3.f13850h;
                    str2 = str4;
                    c1211t = c1211t2;
                    str = str5;
                    uri = uri2;
                    list = list2;
                    i10 = i13;
                    e03 = e02;
                } else {
                    j3 = -9223372036854775807L;
                    list = listEmptyList;
                    i10 = d0Var;
                    uri = null;
                    str = null;
                    c1211t = null;
                    str2 = null;
                }
                C1216y c1216yA2 = c1217z2.a();
                AbstractC1346c.j(((Uri) e03.f2016e) == null || ((UUID) e03.d) != null);
                if (uri != null) {
                    c1192a = new C1192A(uri, str, ((UUID) e03.d) != null ? new C1215x(e03) : null, c1211t, list, str2, i10, j3);
                } else {
                    c1192a = null;
                }
                if (str3 == null) {
                    str3 = "";
                }
                String str6 = str3;
                C1214w c1214w2 = new C1214w(c1212u);
                C1217z c1217z3 = new C1217z(c1216yA2);
                if (i12 == null) {
                    i12 = e2.I.f13914K;
                }
                f11 = new e2.F(str6, c1214w2, c1192a, c1217z3, i12, b11);
            }
            AbstractC2498a abstractC2498aC = interfaceC2491B.c(f11);
            d5.I i14 = f11.f13879b.f13849g;
            if (!i14.isEmpty()) {
                AbstractC2498a[] abstractC2498aArr = new AbstractC2498a[i14.size() + 1];
                abstractC2498aArr[0] = abstractC2498aC;
                int i15 = 0;
                while (i15 < i14.size()) {
                    if (this.f22895i) {
                        C1207o c1207o = new C1207o();
                        c1207o.f14246m = e2.L.l(((e2.E) i14.get(i15)).f13868b);
                        c1207o.d = ((e2.E) i14.get(i15)).c;
                        c1207o.f14239e = ((e2.E) i14.get(i15)).d;
                        c1207o.f14240f = ((e2.E) i14.get(i15)).f13869e;
                        c1207o.f14238b = ((e2.E) i14.get(i15)).f13870f;
                        c1207o.f14237a = ((e2.E) i14.get(i15)).f13871g;
                        V v10 = new V(this.f22890b, new C1458d0(this, 25, new C1208p(c1207o)));
                        v10.f22750f = true;
                        abstractC2498aArr[i15 + 1] = v10.c(e2.F.c(((e2.E) i14.get(i15)).f13867a.toString()));
                        i7 = 1;
                    } else {
                        M.t tVar = this.f22890b;
                        tVar.getClass();
                        i7 = 1;
                        abstractC2498aArr[i15 + 1] = new k0((e2.E) i14.get(i15), tVar, new l9.n(15));
                    }
                    i15 += i7;
                }
                abstractC2498aC = new M(abstractC2498aArr);
            }
            AbstractC2498a c2503f = abstractC2498aC;
            C1214w c1214w3 = f11.f13880e;
            long j11 = c1214w3.f14350b;
            if (j11 != 0 || c1214w3.d != Long.MIN_VALUE || c1214w3.f14352f) {
                c2503f = new C2503f(c2503f, j11, c1214w3.d, !c1214w3.f14353g, c1214w3.f14351e, c1214w3.f14352f);
            }
            f11.f13879b.getClass();
            if (f11.f13879b.d != null) {
                AbstractC1346c.z("DMediaSourceFactory", "Playing media without ads. Configure ad support by calling setAdsLoaderProvider and setAdViewProvider.");
            }
            return c2503f;
        } catch (ClassNotFoundException e10) {
            throw new IllegalStateException(e10);
        }
    }
}
