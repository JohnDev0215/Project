package p;

import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class x71 extends z71 {
    public static final w71 u = new w71();
    public static final r71 v = new r71("closed");
    public final ArrayList r;
    public String s;
    public i71 t;

    public x71() {
        super(u);
        this.r = new ArrayList();
        this.t = n71.c;
    }

    @Override // p.z71
    public final void E() {
        ArrayList arrayList = this.r;
        if (arrayList.isEmpty() || this.s != null) {
            throw new IllegalStateException();
        }
        if (!(O0() instanceof b71)) {
            throw new IllegalStateException();
        }
        arrayList.remove(arrayList.size() - 1);
    }

    @Override // p.z71
    public final z71 E0() {
        P0(n71.c);
        return this;
    }

    @Override // p.z71
    public final void I0(long j) {
        P0(new r71(Long.valueOf(j)));
    }

    @Override // p.z71
    public final void J() {
        ArrayList arrayList = this.r;
        if (arrayList.isEmpty() || this.s != null) {
            throw new IllegalStateException();
        }
        if (!(O0() instanceof p71)) {
            throw new IllegalStateException();
        }
        arrayList.remove(arrayList.size() - 1);
    }

    @Override // p.z71
    public final void J0(Boolean bool) {
        if (bool == null) {
            P0(n71.c);
        } else {
            P0(new r71(bool));
        }
    }

    @Override // p.z71
    public final void K0(Number number) {
        if (number == null) {
            P0(n71.c);
            return;
        }
        if (!this.n) {
            double dDoubleValue = number.doubleValue();
            if (Double.isNaN(dDoubleValue) || Double.isInfinite(dDoubleValue)) {
                throw new IllegalArgumentException("JSON forbids NaN and infinities: " + number);
            }
        }
        P0(new r71(number));
    }

    @Override // p.z71
    public final void L0(String str) {
        if (str == null) {
            P0(n71.c);
        } else {
            P0(new r71(str));
        }
    }

    @Override // p.z71
    public final void M0(boolean z) {
        P0(new r71(Boolean.valueOf(z)));
    }

    public final i71 O0() {
        return (i71) this.r.get(r0.size() - 1);
    }

    public final void P0(i71 i71Var) {
        if (this.s != null) {
            if (!(i71Var instanceof n71) || this.f214p) {
                p71 p71Var = (p71) O0();
                String str = this.s;
                p71Var.getClass();
                p71Var.c.put(str, i71Var);
            }
            this.s = null;
            return;
        }
        if (this.r.isEmpty()) {
            this.t = i71Var;
            return;
        }
        i71 i71VarO0 = O0();
        if (!(i71VarO0 instanceof b71)) {
            throw new IllegalStateException();
        }
        b71 b71Var = (b71) i71VarO0;
        b71Var.getClass();
        b71Var.c.add(i71Var);
    }

    @Override // p.z71
    public final void X(String str) {
        if (str == null) {
            throw new NullPointerException("name == null");
        }
        if (this.r.isEmpty() || this.s != null) {
            throw new IllegalStateException();
        }
        if (!(O0() instanceof p71)) {
            throw new IllegalStateException();
        }
        this.s = str;
    }

    @Override // p.z71
    public final void c() {
        b71 b71Var = new b71();
        P0(b71Var);
        this.r.add(b71Var);
    }

    @Override // p.z71, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        ArrayList arrayList = this.r;
        if (!arrayList.isEmpty()) {
            throw new IOException("Incomplete document");
        }
        arrayList.add(v);
    }

    @Override // p.z71, java.io.Flushable
    public final void flush() {
    }

    @Override // p.z71
    public final void i() {
        p71 p71Var = new p71();
        P0(p71Var);
        this.r.add(p71Var);
    }
}
