package defpackage;

import defpackage.qh0;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: Struct.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class hv2 {
    public final int a;

    public hv2(int i) {
        this.a = i;
    }

    public abstract void a(qh0 qh0Var);

    public final ByteBuffer b() {
        qh0 qh0Var = new qh0(null, this.a);
        a(qh0Var);
        qh0.a aVar = qh0Var.b;
        aVar.b.position(0);
        aVar.b.limit(aVar.d);
        ByteBuffer byteBuffer = aVar.b;
        if (aVar.c.isEmpty()) {
            return byteBuffer;
        }
        throw new UnsupportedOperationException("Handles are discarded.");
    }

    public final wi2 c(k00 k00Var, yg1 yg1Var) {
        t50 t50Var = yg1Var.a;
        qh0 qh0Var = new qh0(k00Var, this.a + t50Var.a);
        qh0Var.g(t50Var);
        qh0Var.b(0, 8);
        qh0Var.b(yg1Var.b, 12);
        int i = yg1Var.c;
        qh0Var.b(i, 16);
        if ((i & 3) != 0) {
            qh0Var.f(24, yg1Var.d);
        }
        a(qh0Var);
        qh0.a aVar = qh0Var.b;
        aVar.b.position(0);
        aVar.b.limit(aVar.d);
        return new wi2(new gq0(aVar.b, aVar.c), yg1Var);
    }
}
