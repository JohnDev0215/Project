package defpackage;

import defpackage.s4;
import defpackage.y11;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: AndroidFontLookup_Internal.java */
/* JADX INFO: loaded from: classes.dex */
public final class v4 {
    public static final a a = new a();

    /* JADX INFO: compiled from: AndroidFontLookup_Internal.java */
    public static final class b extends hv2 {
        public static final t50[] b;
        public static final t50 c;

        static {
            t50 t50Var = new t50(8, 0);
            b = new t50[]{t50Var};
            c = t50Var;
        }

        public b(int i) {
            super(8);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(c);
        }
    }

    /* JADX INFO: compiled from: AndroidFontLookup_Internal.java */
    public static final class c extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public Map<String, c92> b;

        static {
            t50 t50Var = new t50(16, 0);
            c = new t50[]{t50Var};
            d = t50Var;
        }

        public c(int i) {
            super(16);
        }

        public static c d(gq0 gq0Var) {
            e70 e70VarC = gq2.c(gq0Var);
            try {
                c cVar = new c(e70VarC.c(c).b);
                e70 e70VarT = e70VarC.t(8, false);
                e70VarT.j();
                e70 e70VarT2 = e70VarT.t(8, false);
                int i = e70VarT2.k(-1).b;
                String[] strArr = new String[i];
                for (int i2 = 0; i2 < i; i2++) {
                    strArr[i2] = C0175b.g(i2, 8, 8, e70VarT2, false);
                }
                e70 e70VarT3 = e70VarT.t(16, false);
                int i3 = e70VarT3.k(i).b;
                c92[] c92VarArr = new c92[i3];
                for (int i4 = 0; i4 < i3; i4++) {
                    c92VarArr[i4] = c92.d(C0173a.g(i4, 8, 8, e70VarT3, false));
                }
                cVar.b = new HashMap();
                for (int i5 = 0; i5 < i; i5++) {
                    cVar.b.put(strArr[i5], c92VarArr[i5]);
                }
                return cVar;
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0 qh0VarT = qh0Var.t(d);
            if (this.b == null) {
                qh0VarT.q(8, false);
                return;
            }
            qh0VarT.f(8, ((long) qh0VarT.b.d) - ((long) (qh0VarT.a + 8)));
            qh0 qh0VarT2 = qh0VarT.t(n3.f);
            int size = this.b.size();
            String[] strArr = new String[size];
            c92[] c92VarArr = new c92[size];
            int i = 0;
            for (Map.Entry<String, c92> entry : this.b.entrySet()) {
                strArr[i] = entry.getKey();
                c92VarArr[i] = entry.getValue();
                i++;
            }
            qh0 qh0VarR = qh0VarT2.r(size, 8);
            for (int iF = 0; iF < size; iF = C0175b.f(iF, 8, 8, qh0VarR, strArr[iF], false, iF, 1)) {
            }
            qh0 qh0VarR2 = qh0VarT2.r(size, 16);
            for (int i2 = 0; i2 < size; i2++) {
                qh0VarR2.k(c92VarArr[i2], (i2 * 8) + 8, false);
            }
        }
    }

    /* JADX INFO: compiled from: AndroidFontLookup_Internal.java */
    public static class d extends cm2 implements gh1 {
        public final s4.a e;

        public d(e eVar) {
            this.e = eVar;
        }

        @Override // defpackage.gh1
        public final boolean a(gq0 gq0Var) {
            try {
                wi2 wi2VarA = gq0Var.a();
                if (!wi2VarA.d.c(2, 2)) {
                    return false;
                }
                c cVarD = c.d(wi2VarA.f());
                ((e) this.e).c(cVarD.b);
                return true;
            } catch (q80 unused) {
                return false;
            }
        }
    }

    /* JADX INFO: compiled from: AndroidFontLookup_Internal.java */
    public static class e implements s4.a {
        public final k00 e;
        public final gh1 f;
        public final long g;

        public e(k00 k00Var, gh1 gh1Var, long j) {
            this.e = k00Var;
            this.f = gh1Var;
            this.g = j;
        }

        @Override // defpackage.sl
        public final void c(Map<String, c92> map) {
            c cVar = new c(0);
            cVar.b = map;
            this.f.a(cVar.c(this.e, new yg1(2, 2, this.g)));
        }
    }

    /* JADX INFO: compiled from: AndroidFontLookup_Internal.java */
    public static final class f extends hv2 {
        public static final t50[] b;
        public static final t50 c;

        static {
            t50 t50Var = new t50(8, 0);
            b = new t50[]{t50Var};
            c = t50Var;
        }

        public f(int i) {
            super(8);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(c);
        }
    }

    /* JADX INFO: compiled from: AndroidFontLookup_Internal.java */
    public static final class g extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public String[] b;

        static {
            t50 t50Var = new t50(16, 0);
            c = new t50[]{t50Var};
            d = t50Var;
        }

        public g(int i) {
            super(16);
        }

        public static g d(gq0 gq0Var) {
            e70 e70VarC = gq2.c(gq0Var);
            try {
                g gVar = new g(e70VarC.c(c).b);
                e70 e70VarT = e70VarC.t(8, false);
                int i = e70VarT.k(-1).b;
                gVar.b = new String[i];
                for (int i2 = 0; i2 < i; i2++) {
                    gVar.b[i2] = C0175b.g(i2, 8, 8, e70VarT, false);
                }
                return gVar;
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0 qh0VarT = qh0Var.t(d);
            String[] strArr = this.b;
            if (strArr == null) {
                qh0VarT.q(8, false);
                return;
            }
            qh0 qh0VarR = qh0VarT.r(strArr.length, 8);
            int iF = 0;
            while (true) {
                String[] strArr2 = this.b;
                if (iF >= strArr2.length) {
                    return;
                }
                iF = C0175b.f(iF, 8, 8, qh0VarR, strArr2[iF], false, iF, 1);
            }
        }
    }

    /* JADX INFO: compiled from: AndroidFontLookup_Internal.java */
    public static class h extends cm2 implements gh1 {
        public final s4.b e;

        public h(i iVar) {
            this.e = iVar;
        }

        @Override // defpackage.gh1
        public final boolean a(gq0 gq0Var) {
            try {
                wi2 wi2VarA = gq0Var.a();
                if (!wi2VarA.d.c(0, 6)) {
                    return false;
                }
                g gVarD = g.d(wi2VarA.f());
                ((i) this.e).c(gVarD.b);
                return true;
            } catch (q80 unused) {
                return false;
            }
        }
    }

    /* JADX INFO: compiled from: AndroidFontLookup_Internal.java */
    public static class i implements s4.b {
        public final k00 e;
        public final gh1 f;
        public final long g;

        public i(k00 k00Var, gh1 gh1Var, long j) {
            this.e = k00Var;
            this.f = gh1Var;
            this.g = j;
        }

        @Override // defpackage.sl
        public final void c(String[] strArr) {
            g gVar = new g(0);
            gVar.b = strArr;
            this.f.a(gVar.c(this.e, new yg1(0, 6, this.g)));
        }
    }

    /* JADX INFO: compiled from: AndroidFontLookup_Internal.java */
    public static final class j extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public String b;

        static {
            t50 t50Var = new t50(16, 0);
            c = new t50[]{t50Var};
            d = t50Var;
        }

        public j(int i) {
            super(16);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(d).m(this.b, 8, false);
        }
    }

    /* JADX INFO: compiled from: AndroidFontLookup_Internal.java */
    public static final class k extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public c92 b;

        static {
            t50 t50Var = new t50(16, 0);
            c = new t50[]{t50Var};
            d = t50Var;
        }

        public k(int i) {
            super(16);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(d).k(this.b, 8, true);
        }
    }

    /* JADX INFO: compiled from: AndroidFontLookup_Internal.java */
    public static class l extends cm2 implements gh1 {
        public final s4.c e;

        public l(m mVar) {
            this.e = mVar;
        }

        @Override // defpackage.gh1
        public final boolean a(gq0 gq0Var) {
            try {
                wi2 wi2VarA = gq0Var.a();
                if (!wi2VarA.d.c(1, 6)) {
                    return false;
                }
                e70 e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(k.c);
                    c92 c92VarD = c92.d(e70Var.t(8, true));
                    e70Var.a();
                    ((m) this.e).c(c92VarD);
                    return true;
                } catch (Throwable th) {
                    e70Var.a();
                    throw th;
                }
            } catch (q80 unused) {
                return false;
            }
        }
    }

    /* JADX INFO: compiled from: AndroidFontLookup_Internal.java */
    public static class m implements s4.c {
        public final k00 e;
        public final gh1 f;
        public final long g;

        public m(k00 k00Var, gh1 gh1Var, long j) {
            this.e = k00Var;
            this.f = gh1Var;
            this.g = j;
        }

        @Override // defpackage.sl
        public final void c(c92 c92Var) {
            k kVar = new k(0);
            kVar.b = c92Var;
            this.f.a(kVar.c(this.e, new yg1(1, 6, this.g)));
        }
    }

    /* JADX INFO: compiled from: AndroidFontLookup_Internal.java */
    public static final class n extends y11.a implements s4 {
        public n(k00 k00Var, w9 w9Var) {
            super(k00Var, w9Var);
        }

        @Override // defpackage.s4
        public final void K(e eVar) {
            b bVar = new b(0);
            y11.a.C0171a c0171a = this.e;
            c0171a.f.R0(bVar.c(c0171a.e, new yg1(2, 1, 0L)), new d(eVar));
        }

        @Override // defpackage.s4
        public final void c0(String str, m mVar) {
            j jVar = new j(0);
            jVar.b = str;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.R0(jVar.c(c0171a.e, new yg1(1, 1, 0L)), new l(mVar));
        }

        @Override // defpackage.s4
        public final void k0(i iVar) {
            f fVar = new f(0);
            y11.a.C0171a c0171a = this.e;
            c0171a.f.R0(fVar.c(c0171a.e, new yg1(0, 1, 0L)), new h(iVar));
        }

        @Override // y11.c
        public final y11.a.C0171a u0() {
            return this.e;
        }
    }

    /* JADX INFO: compiled from: AndroidFontLookup_Internal.java */
    public static final class o extends y11.d<s4> {
        public o(k00 k00Var, s4 s4Var) {
            super(k00Var, s4Var);
        }

        @Override // defpackage.hh1
        public final boolean R0(gq0 gq0Var, gh1 gh1Var) {
            wi2 wi2VarA;
            yg1 yg1Var;
            e70 e70Var;
            try {
                wi2VarA = gq0Var.a();
                yg1Var = wi2VarA.d;
            } catch (q80 e) {
                System.err.println(e.toString());
                return false;
            }
            if (!yg1Var.b(yg1Var.a(4) ? 5 : 1)) {
                return false;
            }
            int i = yg1Var.b;
            k00 k00Var = this.e;
            if (i == -1) {
                return z11.a(k00Var, v4.a, wi2VarA, gh1Var);
            }
            Object obj = this.f;
            if (i == 0) {
                e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(f.b);
                    e70Var.a();
                    ((s4) obj).k0(new i(k00Var, gh1Var, yg1Var.d));
                    return true;
                } finally {
                }
            }
            if (i == 1) {
                e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(j.c);
                    String strW = e70Var.w(8, false);
                    e70Var.a();
                    ((s4) obj).c0(strW, new m(k00Var, gh1Var, yg1Var.d));
                    return true;
                } finally {
                }
            }
            if (i != 2) {
                return false;
            }
            e70Var = new e70(wi2VarA.f());
            e70Var.b();
            try {
                e70Var.c(b.b);
                e70Var.a();
                ((s4) obj).K(new e(k00Var, gh1Var, yg1Var.d));
                return true;
            } finally {
            }
            System.err.println(e.toString());
            return false;
        }

        @Override // defpackage.gh1
        public final boolean a(gq0 gq0Var) {
            try {
                wi2 wi2VarA = gq0Var.a();
                yg1 yg1Var = wi2VarA.d;
                int i = 4;
                if (!yg1Var.a(4)) {
                    i = 0;
                }
                if (!yg1Var.b(i) || yg1Var.b != -2) {
                    return false;
                }
                e70 e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(ao0.d);
                    ad2 ad2VarB = ad2.b(e70Var);
                    e70Var.a();
                    return ad2VarB.a == 0 && ad2VarB.b.b <= 0;
                } catch (Throwable th) {
                    e70Var.a();
                    throw th;
                }
            } catch (q80 e) {
                System.err.println(e.toString());
                return false;
            }
        }
    }

    /* JADX INFO: compiled from: AndroidFontLookup_Internal.java */
    public class a extends y11.b<s4, Object> {
        @Override // y11.b
        public final y11.c d(k00 k00Var, w9 w9Var) {
            return new n(k00Var, w9Var);
        }

        @Override // y11.b
        public final y11.d e(k00 k00Var, y11 y11Var) {
            return new o(k00Var, (s4) y11Var);
        }

        @Override // y11.b
        public final String f() {
            return "blink.mojom.AndroidFontLookup";
        }

        @Override // y11.b
        public final void g() {
        }
    }
}
