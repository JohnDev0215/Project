package defpackage;

import defpackage.t90;
import defpackage.y11;

/* JADX INFO: compiled from: DigitalGoods_Internal.java */
/* JADX INFO: loaded from: classes3.dex */
public final class aa0 {
    public static final a a = new a();

    /* JADX INFO: compiled from: DigitalGoods_Internal.java */
    public static final class b extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public String b;

        static {
            t50 t50Var = new t50(16, 0);
            c = new t50[]{t50Var};
            d = t50Var;
        }

        public b(int i) {
            super(16);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(d).m(this.b, 8, false);
        }
    }

    /* JADX INFO: compiled from: DigitalGoods_Internal.java */
    public static final class c extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public int b;

        static {
            t50 t50Var = new t50(16, 0);
            c = new t50[]{t50Var};
            d = t50Var;
        }

        public c(int i) {
            super(16);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(d).b(this.b, 8);
        }
    }

    /* JADX INFO: compiled from: DigitalGoods_Internal.java */
    public static class d extends cm2 implements gh1 {
        public final t90.a e;

        public d(e eVar) {
            this.e = eVar;
        }

        @Override // defpackage.gh1
        public final boolean a(gq0 gq0Var) {
            try {
                wi2 wi2VarA = gq0Var.a();
                if (!wi2VarA.d.c(3, 2)) {
                    return false;
                }
                e70 e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(c.c);
                    int iP = e70Var.p(8);
                    e70Var.a();
                    ((e) this.e).c(Integer.valueOf(iP));
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

    /* JADX INFO: compiled from: DigitalGoods_Internal.java */
    public static class e implements t90.a {
        public final k00 e;
        public final gh1 f;
        public final long g;

        public e(k00 k00Var, gh1 gh1Var, long j) {
            this.e = k00Var;
            this.f = gh1Var;
            this.g = j;
        }

        @Override // defpackage.sl
        public final void c(Integer num) {
            c cVar = new c(0);
            cVar.b = num.intValue();
            this.f.a(cVar.c(this.e, new yg1(3, 2, this.g)));
        }
    }

    /* JADX INFO: compiled from: DigitalGoods_Internal.java */
    public static final class f extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public String[] b;

        static {
            t50 t50Var = new t50(16, 0);
            c = new t50[]{t50Var};
            d = t50Var;
        }

        public f(int i) {
            super(16);
        }

        public static f d(gq0 gq0Var) {
            e70 e70VarC = gq2.c(gq0Var);
            try {
                f fVar = new f(e70VarC.c(c).b);
                e70 e70VarT = e70VarC.t(8, false);
                int i = e70VarT.k(-1).b;
                fVar.b = new String[i];
                for (int i2 = 0; i2 < i; i2++) {
                    fVar.b[i2] = C0175b.g(i2, 8, 8, e70VarT, false);
                }
                return fVar;
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

    /* JADX INFO: compiled from: DigitalGoods_Internal.java */
    public static final class g extends hv2 {
        public static final t50[] d;
        public static final t50 e;
        public int b;
        public a31[] c;

        static {
            t50 t50Var = new t50(24, 0);
            d = new t50[]{t50Var};
            e = t50Var;
        }

        public g(int i) {
            super(24);
        }

        public static g d(gq0 gq0Var) {
            e70 e70VarC = gq2.c(gq0Var);
            try {
                g gVar = new g(e70VarC.c(d).b);
                gVar.b = e70VarC.p(8);
                e70 e70VarT = e70VarC.t(16, false);
                int i = e70VarT.k(-1).b;
                gVar.c = new a31[i];
                for (int i2 = 0; i2 < i; i2++) {
                    gVar.c[i2] = a31.d(C0173a.g(i2, 8, 8, e70VarT, false));
                }
                return gVar;
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0 qh0VarT = qh0Var.t(e);
            qh0VarT.b(this.b, 8);
            a31[] a31VarArr = this.c;
            if (a31VarArr == null) {
                qh0VarT.q(16, false);
                return;
            }
            qh0 qh0VarR = qh0VarT.r(a31VarArr.length, 16);
            int i = 0;
            while (true) {
                a31[] a31VarArr2 = this.c;
                if (i >= a31VarArr2.length) {
                    return;
                }
                qh0VarR.k(a31VarArr2[i], (i * 8) + 8, false);
                i++;
            }
        }
    }

    /* JADX INFO: compiled from: DigitalGoods_Internal.java */
    public static class h extends cm2 implements gh1 {
        public final t90.b e;

        public h(i iVar) {
            this.e = iVar;
        }

        @Override // defpackage.gh1
        public final boolean a(gq0 gq0Var) {
            try {
                wi2 wi2VarA = gq0Var.a();
                if (!wi2VarA.d.c(0, 2)) {
                    return false;
                }
                g gVarD = g.d(wi2VarA.f());
                ((i) this.e).a(Integer.valueOf(gVarD.b), gVarD.c);
                return true;
            } catch (q80 unused) {
                return false;
            }
        }
    }

    /* JADX INFO: compiled from: DigitalGoods_Internal.java */
    public static class i implements t90.b {
        public final k00 a;
        public final gh1 b;
        public final long c;

        public i(k00 k00Var, gh1 gh1Var, long j) {
            this.a = k00Var;
            this.b = gh1Var;
            this.c = j;
        }

        public final void a(Object obj, Object obj2) {
            g gVar = new g(0);
            gVar.b = ((Integer) obj).intValue();
            gVar.c = (a31[]) obj2;
            this.b.a(gVar.c(this.a, new yg1(0, 2, this.c)));
        }
    }

    /* JADX INFO: compiled from: DigitalGoods_Internal.java */
    public static final class j extends hv2 {
        public static final t50[] b;
        public static final t50 c;

        static {
            t50 t50Var = new t50(8, 0);
            b = new t50[]{t50Var};
            c = t50Var;
        }

        public j(int i) {
            super(8);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(c);
        }
    }

    /* JADX INFO: compiled from: DigitalGoods_Internal.java */
    public static final class k extends hv2 {
        public static final t50[] d;
        public static final t50 e;
        public int b;
        public r72[] c;

        static {
            t50 t50Var = new t50(24, 0);
            d = new t50[]{t50Var};
            e = t50Var;
        }

        public k(int i) {
            super(24);
        }

        public static k d(gq0 gq0Var) {
            e70 e70VarC = gq2.c(gq0Var);
            try {
                k kVar = new k(e70VarC.c(d).b);
                kVar.b = e70VarC.p(8);
                e70 e70VarT = e70VarC.t(16, false);
                int i = e70VarT.k(-1).b;
                kVar.c = new r72[i];
                for (int i2 = 0; i2 < i; i2++) {
                    kVar.c[i2] = r72.d(C0173a.g(i2, 8, 8, e70VarT, false));
                }
                return kVar;
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0 qh0VarT = qh0Var.t(e);
            qh0VarT.b(this.b, 8);
            r72[] r72VarArr = this.c;
            if (r72VarArr == null) {
                qh0VarT.q(16, false);
                return;
            }
            qh0 qh0VarR = qh0VarT.r(r72VarArr.length, 16);
            int i = 0;
            while (true) {
                r72[] r72VarArr2 = this.c;
                if (i >= r72VarArr2.length) {
                    return;
                }
                qh0VarR.k(r72VarArr2[i], (i * 8) + 8, false);
                i++;
            }
        }
    }

    /* JADX INFO: compiled from: DigitalGoods_Internal.java */
    public static class l extends cm2 implements gh1 {
        public final t90.c e;

        public l(m mVar) {
            this.e = mVar;
        }

        @Override // defpackage.gh1
        public final boolean a(gq0 gq0Var) {
            try {
                wi2 wi2VarA = gq0Var.a();
                if (!wi2VarA.d.c(2, 2)) {
                    return false;
                }
                k kVarD = k.d(wi2VarA.f());
                ((m) this.e).a(Integer.valueOf(kVarD.b), kVarD.c);
                return true;
            } catch (q80 unused) {
                return false;
            }
        }
    }

    /* JADX INFO: compiled from: DigitalGoods_Internal.java */
    public static class m implements t90.c {
        public final k00 a;
        public final gh1 b;
        public final long c;

        public m(k00 k00Var, gh1 gh1Var, long j) {
            this.a = k00Var;
            this.b = gh1Var;
            this.c = j;
        }

        public final void a(Object obj, Object obj2) {
            k kVar = new k(0);
            kVar.b = ((Integer) obj).intValue();
            kVar.c = (r72[]) obj2;
            this.b.a(kVar.c(this.a, new yg1(2, 2, this.c)));
        }
    }

    /* JADX INFO: compiled from: DigitalGoods_Internal.java */
    public static final class n extends hv2 {
        public static final t50[] b;
        public static final t50 c;

        static {
            t50 t50Var = new t50(8, 0);
            b = new t50[]{t50Var};
            c = t50Var;
        }

        public n(int i) {
            super(8);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(c);
        }
    }

    /* JADX INFO: compiled from: DigitalGoods_Internal.java */
    public static final class o extends hv2 {
        public static final t50[] d;
        public static final t50 e;
        public int b;
        public r72[] c;

        static {
            t50 t50Var = new t50(24, 0);
            d = new t50[]{t50Var};
            e = t50Var;
        }

        public o(int i) {
            super(24);
        }

        public static o d(gq0 gq0Var) {
            e70 e70VarC = gq2.c(gq0Var);
            try {
                o oVar = new o(e70VarC.c(d).b);
                oVar.b = e70VarC.p(8);
                e70 e70VarT = e70VarC.t(16, false);
                int i = e70VarT.k(-1).b;
                oVar.c = new r72[i];
                for (int i2 = 0; i2 < i; i2++) {
                    oVar.c[i2] = r72.d(C0173a.g(i2, 8, 8, e70VarT, false));
                }
                return oVar;
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0 qh0VarT = qh0Var.t(e);
            qh0VarT.b(this.b, 8);
            r72[] r72VarArr = this.c;
            if (r72VarArr == null) {
                qh0VarT.q(16, false);
                return;
            }
            qh0 qh0VarR = qh0VarT.r(r72VarArr.length, 16);
            int i = 0;
            while (true) {
                r72[] r72VarArr2 = this.c;
                if (i >= r72VarArr2.length) {
                    return;
                }
                qh0VarR.k(r72VarArr2[i], (i * 8) + 8, false);
                i++;
            }
        }
    }

    /* JADX INFO: compiled from: DigitalGoods_Internal.java */
    public static class p extends cm2 implements gh1 {
        public final t90.d e;

        public p(q qVar) {
            this.e = qVar;
        }

        @Override // defpackage.gh1
        public final boolean a(gq0 gq0Var) {
            try {
                wi2 wi2VarA = gq0Var.a();
                if (!wi2VarA.d.c(1, 2)) {
                    return false;
                }
                o oVarD = o.d(wi2VarA.f());
                ((q) this.e).a(Integer.valueOf(oVarD.b), oVarD.c);
                return true;
            } catch (q80 unused) {
                return false;
            }
        }
    }

    /* JADX INFO: compiled from: DigitalGoods_Internal.java */
    public static class q implements t90.d {
        public final k00 a;
        public final gh1 b;
        public final long c;

        public q(k00 k00Var, gh1 gh1Var, long j) {
            this.a = k00Var;
            this.b = gh1Var;
            this.c = j;
        }

        public final void a(Object obj, Object obj2) {
            o oVar = new o(0);
            oVar.b = ((Integer) obj).intValue();
            oVar.c = (r72[]) obj2;
            this.b.a(oVar.c(this.a, new yg1(1, 2, this.c)));
        }
    }

    /* JADX INFO: compiled from: DigitalGoods_Internal.java */
    public static final class r extends y11.a implements t90 {
        public r(k00 k00Var, w9 w9Var) {
            super(k00Var, w9Var);
        }

        @Override // defpackage.t90
        public final void B(String[] strArr, i iVar) {
            f fVar = new f(0);
            fVar.b = strArr;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.R0(fVar.c(c0171a.e, new yg1(0, 1, 0L)), new h(iVar));
        }

        @Override // defpackage.t90
        public final void L(m mVar) {
            j jVar = new j(0);
            y11.a.C0171a c0171a = this.e;
            c0171a.f.R0(jVar.c(c0171a.e, new yg1(2, 1, 0L)), new l(mVar));
        }

        @Override // defpackage.t90
        public final void d0(q qVar) {
            n nVar = new n(0);
            y11.a.C0171a c0171a = this.e;
            c0171a.f.R0(nVar.c(c0171a.e, new yg1(1, 1, 0L)), new p(qVar));
        }

        @Override // defpackage.t90
        public final void n0(String str, e eVar) {
            b bVar = new b(0);
            bVar.b = str;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.R0(bVar.c(c0171a.e, new yg1(3, 1, 0L)), new d(eVar));
        }

        @Override // y11.c
        public final y11.a.C0171a u0() {
            return this.e;
        }
    }

    /* JADX INFO: compiled from: DigitalGoods_Internal.java */
    public static final class s extends y11.d<t90> {
        public s(k00 k00Var, t90 t90Var) {
            super(k00Var, t90Var);
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
                return z11.a(k00Var, aa0.a, wi2VarA, gh1Var);
            }
            Object obj = this.f;
            if (i == 0) {
                ((t90) obj).B(f.d(wi2VarA.f()).b, new i(k00Var, gh1Var, yg1Var.d));
                return true;
            }
            if (i == 1) {
                e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(n.b);
                    e70Var.a();
                    ((t90) obj).d0(new q(k00Var, gh1Var, yg1Var.d));
                    return true;
                } finally {
                }
            }
            if (i == 2) {
                e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(j.b);
                    e70Var.a();
                    ((t90) obj).L(new m(k00Var, gh1Var, yg1Var.d));
                    return true;
                } finally {
                }
            }
            if (i != 3) {
                return false;
            }
            e70Var = new e70(wi2VarA.f());
            e70Var.b();
            try {
                e70Var.c(b.c);
                String strW = e70Var.w(8, false);
                e70Var.a();
                ((t90) obj).n0(strW, new e(k00Var, gh1Var, yg1Var.d));
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

    /* JADX INFO: compiled from: DigitalGoods_Internal.java */
    public class a extends y11.b<t90, Object> {
        @Override // y11.b
        public final y11.c d(k00 k00Var, w9 w9Var) {
            return new r(k00Var, w9Var);
        }

        @Override // y11.b
        public final y11.d e(k00 k00Var, y11 y11Var) {
            return new s(k00Var, (t90) y11Var);
        }

        @Override // y11.b
        public final String f() {
            return "payments.mojom.DigitalGoods";
        }

        @Override // y11.b
        public final void g() {
        }
    }
}
