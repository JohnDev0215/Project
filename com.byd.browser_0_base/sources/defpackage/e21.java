package defpackage;

import defpackage.c21;
import defpackage.y11;
import java.util.HashMap;

/* JADX INFO: compiled from: InterfaceRegistry.java */
/* JADX INFO: loaded from: classes3.dex */
public final class e21 implements b21 {
    public final HashMap e = new HashMap();

    /* JADX INFO: compiled from: InterfaceRegistry.java */
    public static class a<I extends y11> {
        public final y11.b<I, ? extends y11.c> a;
        public final a21<I> b;

        public a(y11.b<I, ? extends y11.c> bVar, a21<I> a21Var) {
            this.a = bVar;
            this.b = a21Var;
        }
    }

    public static e21 C(dh1 dh1Var) {
        e21 e21Var = new e21();
        xc2 xc2Var = new xc2(dh1Var);
        xc2Var.e.i = e21Var;
        xc2Var.f = new c21.d(dh1Var.f, e21Var);
        xc2Var.q();
        return e21Var;
    }

    @Override // defpackage.b21
    public final void O(String str, ch1 ch1Var) {
        a aVar = (a) this.e.get(str);
        if (aVar == null) {
            return;
        }
        y11 y11VarD = aVar.b.d();
        if (y11VarD == null) {
            ch1Var.close();
        } else {
            aVar.a.b(y11VarD, ch1Var);
        }
    }

    @Override // defpackage.y11, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.e.clear();
    }

    @Override // defpackage.fx
    public final void q(ki1 ki1Var) {
        close();
    }

    public final <I extends y11> void t(y11.b<I, ? extends y11.c> bVar, a21<I> a21Var) {
        this.e.put(bVar.f(), new a(bVar, a21Var));
    }
}
