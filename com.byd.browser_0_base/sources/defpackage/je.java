package defpackage;

import defpackage.qh0;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: BatteryStatus.java */
/* JADX INFO: loaded from: classes3.dex */
public final class je extends hv2 {
    public static final t50[] f;
    public static final t50 g;
    public boolean b;
    public double c;
    public double d;
    public double e;

    static {
        t50 t50Var = new t50(40, 0);
        f = new t50[]{t50Var};
        g = t50Var;
    }

    public je(int i) {
        super(40);
        this.b = true;
        this.c = 0.0d;
        this.d = Double.POSITIVE_INFINITY;
        this.e = 1.0d;
    }

    public static je d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            je jeVar = new je(e70Var.c(f).b);
            jeVar.b = e70Var.d(8, 0);
            jeVar.c = e70Var.m(16);
            jeVar.d = e70Var.m(24);
            jeVar.e = e70Var.m(32);
            return jeVar;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(g);
        qh0VarT.d(8, 0, this.b);
        double d = this.c;
        qh0.a aVar = qh0VarT.b;
        ByteBuffer byteBuffer = aVar.b;
        int i = qh0VarT.a;
        byteBuffer.putDouble(i + 16, d);
        aVar.b.putDouble(i + 24, this.d);
        aVar.b.putDouble(i + 32, this.e);
    }

    public je() {
        this(0);
    }
}
