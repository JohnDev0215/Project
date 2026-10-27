package p;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

/* JADX INFO: loaded from: classes.dex */
public final class nz2 extends hu0 {
    public static final os1 e;
    public final os1 b;
    public final hu0 c;
    public final Map d;

    static {
        String str = os1.e;
        e = u11.z("/", false);
    }

    public nz2(os1 os1Var, hu0 hu0Var, LinkedHashMap linkedHashMap) {
        this.b = os1Var;
        this.c = hu0Var;
        this.d = linkedHashMap;
    }

    @Override // p.hu0
    public final pd2 a(os1 os1Var) throws IOException {
        throw new IOException("zip file systems are read-only");
    }

    @Override // p.hu0
    public final void b(os1 os1Var, os1 os1Var2) throws IOException {
        v03.j(os1Var, "source");
        v03.j(os1Var2, "target");
        throw new IOException("zip file systems are read-only");
    }

    @Override // p.hu0
    public final void c(os1 os1Var) throws IOException {
        throw new IOException("zip file systems are read-only");
    }

    @Override // p.hu0
    public final void d(os1 os1Var) throws IOException {
        v03.j(os1Var, "path");
        throw new IOException("zip file systems are read-only");
    }

    @Override // p.hu0
    public final List g(os1 os1Var) throws IOException {
        v03.j(os1Var, "dir");
        os1 os1Var2 = e;
        os1Var2.getClass();
        mz2 mz2Var = (mz2) this.d.get(f.b(os1Var2, os1Var, true));
        if (mz2Var != null) {
            List listN0 = ou.n0(mz2Var.h);
            v03.h(listN0);
            return listN0;
        }
        throw new IOException("not a directory: " + os1Var);
    }

    @Override // p.hu0
    public final x50 i(os1 os1Var) throws Throwable {
        x50 x50VarE;
        Throwable th;
        v03.j(os1Var, "path");
        os1 os1Var2 = e;
        os1Var2.getClass();
        mz2 mz2Var = (mz2) this.d.get(f.b(os1Var2, os1Var, true));
        Throwable th2 = null;
        if (mz2Var == null) {
            return null;
        }
        boolean z = mz2Var.b;
        x50 x50Var = new x50(!z, z, null, z ? null : Long.valueOf(mz2Var.d), null, mz2Var.f, null);
        long j = mz2Var.g;
        if (j == -1) {
            return x50Var;
        }
        b81 b81VarJ = this.c.j(this.b);
        try {
            w02 w02VarD = v03.d(b81VarJ.i(j));
            try {
                x50VarE = okio.internal.b.e(w02VarD, x50Var);
                v03.h(x50VarE);
                try {
                    w02VarD.close();
                    th = null;
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (Throwable th4) {
                try {
                    w02VarD.close();
                } catch (Throwable th5) {
                    kotlin.a.a(th4, th5);
                }
                th = th4;
                x50VarE = null;
            }
        } catch (Throwable th6) {
            if (b81VarJ != null) {
                try {
                    b81VarJ.close();
                } catch (Throwable th7) {
                    kotlin.a.a(th6, th7);
                }
            }
            x50VarE = null;
            th2 = th6;
        }
        if (th != null) {
            throw th;
        }
        v03.h(x50VarE);
        try {
            b81VarJ.close();
        } catch (Throwable th8) {
            th2 = th8;
        }
        if (th2 != null) {
            throw th2;
        }
        v03.h(x50VarE);
        return x50VarE;
    }

    @Override // p.hu0
    public final b81 j(os1 os1Var) {
        v03.j(os1Var, "file");
        throw new UnsupportedOperationException("not implemented yet!");
    }

    @Override // p.hu0
    public final pd2 k(os1 os1Var) throws IOException {
        v03.j(os1Var, "file");
        throw new IOException("zip file systems are read-only");
    }

    @Override // p.hu0
    public final de2 l(os1 os1Var) throws Throwable {
        Throwable th;
        w02 w02VarD;
        v03.j(os1Var, "file");
        os1 os1Var2 = e;
        os1Var2.getClass();
        mz2 mz2Var = (mz2) this.d.get(f.b(os1Var2, os1Var, true));
        if (mz2Var == null) {
            throw new FileNotFoundException("no such file: " + os1Var);
        }
        b81 b81VarJ = this.c.j(this.b);
        try {
            w02VarD = v03.d(b81VarJ.i(mz2Var.g));
            try {
                b81VarJ.close();
                th = null;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            if (b81VarJ != null) {
                try {
                    b81VarJ.close();
                } catch (Throwable th4) {
                    kotlin.a.a(th3, th4);
                }
            }
            th = th3;
            w02VarD = null;
        }
        if (th != null) {
            throw th;
        }
        v03.h(w02VarD);
        okio.internal.b.e(w02VarD, null);
        int i = mz2Var.e;
        long j = mz2Var.d;
        if (i == 0) {
            return new zu0(w02VarD, j, true);
        }
        return new zu0(new m41(v03.d(new zu0(w02VarD, mz2Var.c, true)), new Inflater(true)), j, false);
    }
}
