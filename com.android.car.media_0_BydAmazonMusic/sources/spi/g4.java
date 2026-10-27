package spi;

import java.io.IOException;

/* JADX INFO: compiled from: Gson.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g4 extends nb<Number> {
    @Override // spi.nb
    public final Number a(o5 o5Var) throws IOException {
        if (o5Var.v() != s5.NULL) {
            return Long.valueOf(o5Var.o());
        }
        o5Var.r();
        return null;
    }

    @Override // spi.nb
    public final void b(v5 v5Var, Number number) throws IOException {
        Number number2 = number;
        if (number2 == null) {
            v5Var.i();
        } else {
            v5Var.q(number2.toString());
        }
    }
}
