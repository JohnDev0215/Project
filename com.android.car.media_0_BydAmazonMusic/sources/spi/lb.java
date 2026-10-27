package spi;

import java.io.EOFException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Objects;

/* JADX INFO: compiled from: TreeTypeAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class lb<T> extends nb<T> {
    public final q5<T> a;
    public final h5<T> b;
    public final j4 c;
    public final vb<T> d;
    public final ob e;
    public final lb<T>.b f = new b();
    public nb<T> g;

    /* JADX INFO: compiled from: TreeTypeAdapter.java */
    public final class b {
        private b(lb lbVar) {
        }
    }

    public lb(q5<T> q5Var, h5<T> h5Var, j4 j4Var, vb<T> vbVar, ob obVar) {
        this.a = q5Var;
        this.b = h5Var;
        this.c = j4Var;
        this.d = vbVar;
        this.e = obVar;
    }

    @Override // spi.nb
    public final T a(o5 o5Var) {
        boolean z;
        Object objA;
        if (this.b == null) {
            nb<T> nbVarF = this.g;
            if (nbVarF == null) {
                nbVarF = this.c.f(this.e, this.d);
                this.g = nbVarF;
            }
            return nbVarF.a(o5Var);
        }
        try {
            try {
                o5Var.v();
                z = false;
                try {
                    objA = qb.X.a(o5Var);
                } catch (EOFException e) {
                    e = e;
                    if (!z) {
                        throw new r5(e);
                    }
                    objA = k5.a;
                }
            } catch (IOException e2) {
                throw new j5(e2);
            } catch (NumberFormatException e3) {
                throw new r5(e3);
            } catch (m6 e4) {
                throw new r5(e4);
            }
        } catch (EOFException e5) {
            e = e5;
            z = true;
        }
        Objects.requireNonNull(objA);
        if (objA instanceof k5) {
            return null;
        }
        h5<T> h5Var = this.b;
        Type type = this.d.b;
        return (T) h5Var.a();
    }

    @Override // spi.nb
    public final void b(v5 v5Var, T t) throws IOException {
        q5<T> q5Var = this.a;
        if (q5Var == null) {
            nb<T> nbVarF = this.g;
            if (nbVarF == null) {
                nbVarF = this.c.f(this.e, this.d);
                this.g = nbVarF;
            }
            nbVarF.b(v5Var, t);
            return;
        }
        if (t == null) {
            v5Var.i();
        } else {
            Type type = this.d.b;
            ia.a(q5Var.a(), v5Var);
        }
    }
}
