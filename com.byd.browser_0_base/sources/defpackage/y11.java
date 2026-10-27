package defpackage;

import java.io.Closeable;

/* JADX INFO: compiled from: Interface.java */
/* JADX INFO: loaded from: classes3.dex */
public interface y11 extends fx, Closeable {

    /* JADX INFO: compiled from: Interface.java */
    public static abstract class b<I extends y11, P extends c> {
        public final P a(ch1 ch1Var, int i) {
            xc2 xc2Var = new xc2(ch1Var);
            k00 k00VarW0 = ch1Var.w0();
            P p = (P) d(k00VarW0, new w9(k00VarW0, xc2Var));
            i80 i80Var = new i80();
            i80Var.e.add(p);
            xc2Var.e.i = i80Var;
            xc2Var.q();
            p.u0().g = i;
            return p;
        }

        public final xc2 b(y11 y11Var, ch1 ch1Var) {
            xc2 xc2Var = new xc2(ch1Var);
            k00 k00VarW0 = ch1Var.w0();
            xc2Var.e.i = y11Var;
            xc2Var.f = e(k00VarW0, y11Var);
            xc2Var.q();
            return xc2Var;
        }

        public final void c(kj0 kj0Var, f21 f21Var) {
            b(kj0Var, f21Var.f0());
        }

        public abstract c d(k00 k00Var, w9 w9Var);

        public abstract d<I> e(k00 k00Var, I i);

        public abstract String f();

        public abstract void g();
    }

    /* JADX INFO: compiled from: Interface.java */
    public interface c extends y11 {
        a.C0171a u0();
    }

    /* JADX INFO: compiled from: Interface.java */
    public static abstract class d<I extends y11> implements hh1 {
        public final k00 e;
        public final I f;

        public d(k00 k00Var, I i) {
            this.e = k00Var;
            this.f = i;
        }

        @Override // defpackage.gh1, java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
            this.f.close();
        }
    }

    void close();

    /* JADX INFO: compiled from: Interface.java */
    public static abstract class a implements c {
        public final C0171a e;

        public a(k00 k00Var, hh1 hh1Var) {
            this.e = new C0171a(k00Var, hh1Var);
        }

        @Override // defpackage.y11, java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
            this.e.close();
        }

        @Override // defpackage.fx
        public final void q(ki1 ki1Var) {
            this.e.getClass();
        }

        /* JADX INFO: renamed from: y11$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: Interface.java */
        public static class C0171a implements Closeable, fx {
            public final k00 e;
            public final hh1 f;
            public int g;

            public C0171a(k00 k00Var, hh1 hh1Var) {
                this.e = k00Var;
                this.f = hh1Var;
            }

            @Override // java.io.Closeable, java.lang.AutoCloseable
            public final void close() {
                this.f.close();
            }

            @Override // defpackage.fx
            public final void q(ki1 ki1Var) {
            }
        }
    }
}
