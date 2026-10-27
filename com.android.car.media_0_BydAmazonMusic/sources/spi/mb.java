package spi;

import java.io.IOException;

/* JADX INFO: compiled from: TypeAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class mb extends nb<Object> {
    public final /* synthetic */ nb a;

    public mb(nb nbVar) {
        this.a = nbVar;
    }

    @Override // spi.nb
    public final Object a(o5 o5Var) throws IOException {
        if (o5Var.v() != s5.NULL) {
            return this.a.a(o5Var);
        }
        o5Var.r();
        return null;
    }

    @Override // spi.nb
    public final void b(v5 v5Var, Object obj) throws IOException {
        if (obj == null) {
            v5Var.i();
        } else {
            this.a.b(v5Var, obj);
        }
    }
}
