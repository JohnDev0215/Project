package defpackage;

import defpackage.f83;
import defpackage.y11;
import org.chromium.gfx.mojom.Rect;

/* JADX INFO: compiled from: TextFragmentReceiver_Internal.java */
/* JADX INFO: loaded from: classes.dex */
public final class g83 {
    public static final a a = new a();

    /* JADX INFO: compiled from: TextFragmentReceiver_Internal.java */
    public static final class b extends y11.a implements f83 {
        public b(k00 k00Var, w9 w9Var) {
            super(k00Var, w9Var);
        }

        @Override // defpackage.f83
        public final void T(h hVar) {
            e eVar = new e(0);
            y11.a.C0171a c0171a = this.e;
            c0171a.f.R0(eVar.c(c0171a.e, new yg1(5, 1, 0L)), new g(hVar));
        }

        @Override // defpackage.f83
        public final void c1(f83.b bVar) {
            i iVar = new i(0);
            y11.a.C0171a c0171a = this.e;
            c0171a.f.R0(iVar.c(c0171a.e, new yg1(3, 1, 0L)), new k(bVar));
        }

        @Override // defpackage.f83
        public final void cancel() {
            d dVar = new d(0);
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(dVar.c(c0171a.e, new yg1(0)));
        }

        @Override // defpackage.f83
        public final void k(f83.c cVar) {
            m mVar = new m(0);
            y11.a.C0171a c0171a = this.e;
            c0171a.f.R0(mVar.c(c0171a.e, new yg1(4, 1, 0L)), new o(cVar));
        }

        @Override // defpackage.f83
        public final void p() {
            q qVar = new q(0);
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(qVar.c(c0171a.e, new yg1(2)));
        }

        @Override // defpackage.f83
        public final void r0(f83.d dVar) {
            r rVar = new r(0);
            y11.a.C0171a c0171a = this.e;
            c0171a.f.R0(rVar.c(c0171a.e, new yg1(1, 1, 0L)), new t(dVar));
        }

        @Override // y11.c
        public final y11.a.C0171a u0() {
            return this.e;
        }
    }

    /* JADX INFO: compiled from: TextFragmentReceiver_Internal.java */
    public static final class c extends y11.d<f83> {
        public c(k00 k00Var, f83 f83Var) {
            super(k00Var, f83Var);
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
                return z11.a(k00Var, g83.a, wi2VarA, gh1Var);
            }
            Object obj = this.f;
            if (i == 1) {
                e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(r.b);
                    e70Var.a();
                    ((f83) obj).r0(new u(k00Var, gh1Var, yg1Var.d));
                    return true;
                } finally {
                }
            }
            if (i == 3) {
                e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(i.b);
                    e70Var.a();
                    ((f83) obj).c1(new l(k00Var, gh1Var, yg1Var.d));
                    return true;
                } finally {
                }
            }
            if (i == 4) {
                e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(m.b);
                    e70Var.a();
                    ((f83) obj).k(new p(k00Var, gh1Var, yg1Var.d));
                    return true;
                } finally {
                }
            }
            if (i != 5) {
                return false;
            }
            e70Var = new e70(wi2VarA.f());
            e70Var.b();
            try {
                e70Var.c(e.b);
                e70Var.a();
                ((f83) obj).T(new h(k00Var, gh1Var, yg1Var.d));
                return true;
            } finally {
            }
            System.err.println(e.toString());
            return false;
        }

        @Override // defpackage.gh1
        public final boolean a(gq0 gq0Var) {
            wi2 wi2VarA;
            yg1 yg1Var;
            int i;
            e70 e70Var;
            try {
                wi2VarA = gq0Var.a();
                yg1Var = wi2VarA.d;
                i = 4;
                if (!yg1Var.a(4)) {
                    i = 0;
                }
            } catch (q80 e) {
                System.err.println(e.toString());
                return false;
            }
            if (!yg1Var.b(i)) {
                return false;
            }
            int i2 = yg1Var.b;
            if (i2 == -2) {
                e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(ao0.d);
                    ad2 ad2VarB = ad2.b(e70Var);
                    e70Var.a();
                    return ad2VarB.a == 0 && ad2VarB.b.b <= 0;
                } finally {
                }
            }
            Object obj = this.f;
            if (i2 == 0) {
                e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(d.b);
                    e70Var.a();
                    ((f83) obj).cancel();
                    return true;
                } finally {
                }
            }
            if (i2 != 2) {
                return false;
            }
            e70Var = new e70(wi2VarA.f());
            e70Var.b();
            try {
                e70Var.c(q.b);
                e70Var.a();
                ((f83) obj).p();
                return true;
            } finally {
            }
            System.err.println(e.toString());
            return false;
        }
    }

    /* JADX INFO: compiled from: TextFragmentReceiver_Internal.java */
    public static final class d extends hv2 {
        public static final t50[] b;
        public static final t50 c;

        static {
            t50 t50Var = new t50(8, 0);
            b = new t50[]{t50Var};
            c = t50Var;
        }

        public d(int i) {
            super(8);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(c);
        }
    }

    /* JADX INFO: compiled from: TextFragmentReceiver_Internal.java */
    public static final class e extends hv2 {
        public static final t50[] b;
        public static final t50 c;

        static {
            t50 t50Var = new t50(8, 0);
            b = new t50[]{t50Var};
            c = t50Var;
        }

        public e(int i) {
            super(8);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(c);
        }
    }

    /* JADX INFO: compiled from: TextFragmentReceiver_Internal.java */
    public static final class f extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public Rect b;

        static {
            t50 t50Var = new t50(16, 0);
            c = new t50[]{t50Var};
            d = t50Var;
        }

        public f(int i) {
            super(16);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(d).k(this.b, 8, false);
        }
    }

    /* JADX INFO: compiled from: TextFragmentReceiver_Internal.java */
    public static class g extends cm2 implements gh1 {
        public final f83.a e;

        public g(h hVar) {
            this.e = hVar;
        }

        @Override // defpackage.gh1
        public final boolean a(gq0 gq0Var) {
            try {
                wi2 wi2VarA = gq0Var.a();
                if (!wi2VarA.d.c(5, 2)) {
                    return false;
                }
                e70 e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(f.c);
                    Rect rectD = Rect.d(e70Var.t(8, false));
                    e70Var.a();
                    ((h) this.e).c(rectD);
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

    /* JADX INFO: compiled from: TextFragmentReceiver_Internal.java */
    public static class h implements f83.a {
        public final k00 e;
        public final gh1 f;
        public final long g;

        public h(k00 k00Var, gh1 gh1Var, long j) {
            this.e = k00Var;
            this.f = gh1Var;
            this.g = j;
        }

        @Override // defpackage.sl
        public final void c(Rect rect) {
            f fVar = new f(0);
            fVar.b = rect;
            this.f.a(fVar.c(this.e, new yg1(5, 2, this.g)));
        }
    }

    /* JADX INFO: compiled from: TextFragmentReceiver_Internal.java */
    public static final class i extends hv2 {
        public static final t50[] b;
        public static final t50 c;

        static {
            t50 t50Var = new t50(8, 0);
            b = new t50[]{t50Var};
            c = t50Var;
        }

        public i(int i) {
            super(8);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(c);
        }
    }

    /* JADX INFO: compiled from: TextFragmentReceiver_Internal.java */
    public static final class j extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public String[] b;

        static {
            t50 t50Var = new t50(16, 0);
            c = new t50[]{t50Var};
            d = t50Var;
        }

        public j(int i) {
            super(16);
        }

        public static j d(gq0 gq0Var) {
            e70 e70VarC = gq2.c(gq0Var);
            try {
                j jVar = new j(e70VarC.c(c).b);
                e70 e70VarT = e70VarC.t(8, false);
                int i = e70VarT.k(-1).b;
                jVar.b = new String[i];
                for (int i2 = 0; i2 < i; i2++) {
                    jVar.b[i2] = C0175b.g(i2, 8, 8, e70VarT, false);
                }
                return jVar;
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

    /* JADX INFO: compiled from: TextFragmentReceiver_Internal.java */
    public static class k extends cm2 implements gh1 {
        public final f83.b e;

        public k(f83.b bVar) {
            this.e = bVar;
        }

        @Override // defpackage.gh1
        public final boolean a(gq0 gq0Var) {
            try {
                wi2 wi2VarA = gq0Var.a();
                if (!wi2VarA.d.c(3, 2)) {
                    return false;
                }
                this.e.c(j.d(wi2VarA.f()).b);
                return true;
            } catch (q80 unused) {
                return false;
            }
        }
    }

    /* JADX INFO: compiled from: TextFragmentReceiver_Internal.java */
    public static class l implements f83.b {
        public final k00 e;
        public final gh1 f;
        public final long g;

        public l(k00 k00Var, gh1 gh1Var, long j) {
            this.e = k00Var;
            this.f = gh1Var;
            this.g = j;
        }

        @Override // defpackage.sl
        public final void c(String[] strArr) {
            j jVar = new j(0);
            jVar.b = strArr;
            this.f.a(jVar.c(this.e, new yg1(3, 2, this.g)));
        }
    }

    /* JADX INFO: compiled from: TextFragmentReceiver_Internal.java */
    public static final class m extends hv2 {
        public static final t50[] b;
        public static final t50 c;

        static {
            t50 t50Var = new t50(8, 0);
            b = new t50[]{t50Var};
            c = t50Var;
        }

        public m(int i) {
            super(8);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(c);
        }
    }

    /* JADX INFO: compiled from: TextFragmentReceiver_Internal.java */
    public static final class n extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public String[] b;

        static {
            t50 t50Var = new t50(16, 0);
            c = new t50[]{t50Var};
            d = t50Var;
        }

        public n(int i) {
            super(16);
        }

        public static n d(gq0 gq0Var) {
            e70 e70VarC = gq2.c(gq0Var);
            try {
                n nVar = new n(e70VarC.c(c).b);
                e70 e70VarT = e70VarC.t(8, false);
                int i = e70VarT.k(-1).b;
                nVar.b = new String[i];
                for (int i2 = 0; i2 < i; i2++) {
                    nVar.b[i2] = C0175b.g(i2, 8, 8, e70VarT, false);
                }
                return nVar;
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

    /* JADX INFO: compiled from: TextFragmentReceiver_Internal.java */
    public static class o extends cm2 implements gh1 {
        public final f83.c e;

        public o(f83.c cVar) {
            this.e = cVar;
        }

        @Override // defpackage.gh1
        public final boolean a(gq0 gq0Var) {
            try {
                wi2 wi2VarA = gq0Var.a();
                if (!wi2VarA.d.c(4, 2)) {
                    return false;
                }
                this.e.c(n.d(wi2VarA.f()).b);
                return true;
            } catch (q80 unused) {
                return false;
            }
        }
    }

    /* JADX INFO: compiled from: TextFragmentReceiver_Internal.java */
    public static class p implements f83.c {
        public final k00 e;
        public final gh1 f;
        public final long g;

        public p(k00 k00Var, gh1 gh1Var, long j) {
            this.e = k00Var;
            this.f = gh1Var;
            this.g = j;
        }

        @Override // defpackage.sl
        public final void c(String[] strArr) {
            n nVar = new n(0);
            nVar.b = strArr;
            this.f.a(nVar.c(this.e, new yg1(4, 2, this.g)));
        }
    }

    /* JADX INFO: compiled from: TextFragmentReceiver_Internal.java */
    public static final class q extends hv2 {
        public static final t50[] b;
        public static final t50 c;

        static {
            t50 t50Var = new t50(8, 0);
            b = new t50[]{t50Var};
            c = t50Var;
        }

        public q(int i) {
            super(8);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(c);
        }
    }

    /* JADX INFO: compiled from: TextFragmentReceiver_Internal.java */
    public static final class r extends hv2 {
        public static final t50[] b;
        public static final t50 c;

        static {
            t50 t50Var = new t50(8, 0);
            b = new t50[]{t50Var};
            c = t50Var;
        }

        public r(int i) {
            super(8);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(c);
        }
    }

    /* JADX INFO: compiled from: TextFragmentReceiver_Internal.java */
    public static final class s extends hv2 {
        public static final t50[] e;
        public static final t50 f;
        public String b;
        public int c;
        public int d;

        static {
            t50 t50Var = new t50(24, 0);
            e = new t50[]{t50Var};
            f = t50Var;
        }

        public s(int i) {
            super(24);
        }

        public static s d(gq0 gq0Var) {
            e70 e70VarC = gq2.c(gq0Var);
            try {
                s sVar = new s(e70VarC.c(e).b);
                boolean z = false;
                sVar.b = e70VarC.w(8, false);
                int iP = e70VarC.p(16);
                sVar.c = iP;
                if (!(iP >= -1 && iP <= 14)) {
                    throw new q80("Invalid enum value.");
                }
                sVar.c = iP;
                int iP2 = e70VarC.p(20);
                sVar.d = iP2;
                if (iP2 >= 0 && iP2 <= 1) {
                    z = true;
                }
                if (!z) {
                    throw new q80("Invalid enum value.");
                }
                sVar.d = iP2;
                return sVar;
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0 qh0VarT = qh0Var.t(f);
            qh0VarT.m(this.b, 8, false);
            qh0VarT.b(this.c, 16);
            qh0VarT.b(this.d, 20);
        }
    }

    /* JADX INFO: compiled from: TextFragmentReceiver_Internal.java */
    public static class t extends cm2 implements gh1 {
        public final f83.d e;

        public t(f83.d dVar) {
            this.e = dVar;
        }

        @Override // defpackage.gh1
        public final boolean a(gq0 gq0Var) {
            try {
                wi2 wi2VarA = gq0Var.a();
                if (!wi2VarA.d.c(1, 2)) {
                    return false;
                }
                s sVarD = s.d(wi2VarA.f());
                this.e.d(sVarD.b, Integer.valueOf(sVarD.c), Integer.valueOf(sVarD.d));
                return true;
            } catch (q80 unused) {
                return false;
            }
        }
    }

    /* JADX INFO: compiled from: TextFragmentReceiver_Internal.java */
    public static class u implements f83.d {
        public final k00 e;
        public final gh1 f;
        public final long g;

        public u(k00 k00Var, gh1 gh1Var, long j) {
            this.e = k00Var;
            this.f = gh1Var;
            this.g = j;
        }

        @Override // defpackage.tl
        public final void d(String str, Integer num, Integer num2) {
            s sVar = new s(0);
            sVar.b = str;
            sVar.c = num.intValue();
            sVar.d = num2.intValue();
            this.f.a(sVar.c(this.e, new yg1(1, 2, this.g)));
        }
    }

    /* JADX INFO: compiled from: TextFragmentReceiver_Internal.java */
    public class a extends y11.b<f83, Object> {
        @Override // y11.b
        public final y11.c d(k00 k00Var, w9 w9Var) {
            return new b(k00Var, w9Var);
        }

        @Override // y11.b
        public final y11.d e(k00 k00Var, y11 y11Var) {
            return new c(k00Var, (f83) y11Var);
        }

        @Override // y11.b
        public final String f() {
            return "blink.mojom.TextFragmentReceiver";
        }

        @Override // y11.b
        public final void g() {
        }
    }
}
