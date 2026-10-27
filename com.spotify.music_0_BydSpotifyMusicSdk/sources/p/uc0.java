package p;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class uc0 {
    public static final ThreadLocal d = new ThreadLocal();
    public final int a;
    public final gh2 b;
    public volatile int c = 0;

    public uc0(gh2 gh2Var, int i) {
        this.b = gh2Var;
        this.a = i;
    }

    public final int a(int i) {
        dk1 dk1VarD = d();
        int iA = dk1VarD.a(16);
        if (iA == 0) {
            return 0;
        }
        ByteBuffer byteBuffer = dk1VarD.b;
        int i2 = iA + dk1VarD.a;
        return byteBuffer.getInt((i * 4) + byteBuffer.getInt(i2) + i2 + 4);
    }

    public final int b() {
        dk1 dk1VarD = d();
        int iA = dk1VarD.a(16);
        if (iA == 0) {
            return 0;
        }
        int i = iA + dk1VarD.a;
        return dk1VarD.b.getInt(dk1VarD.b.getInt(i) + i);
    }

    public final int c() {
        dk1 dk1VarD = d();
        int iA = dk1VarD.a(4);
        if (iA != 0) {
            return dk1VarD.b.getInt(iA + dk1VarD.a);
        }
        return 0;
    }

    public final dk1 d() {
        ThreadLocal threadLocal = d;
        dk1 dk1Var = (dk1) threadLocal.get();
        if (dk1Var == null) {
            dk1Var = new dk1();
            threadLocal.set(dk1Var);
        }
        ek1 ek1Var = (ek1) this.b.e;
        int iA = ek1Var.a(6);
        if (iA != 0) {
            int i = iA + ek1Var.a;
            int i2 = (this.a * 4) + ek1Var.b.getInt(i) + i + 4;
            int i3 = ek1Var.b.getInt(i2) + i2;
            ByteBuffer byteBuffer = ek1Var.b;
            dk1Var.b = byteBuffer;
            if (byteBuffer != null) {
                dk1Var.a = i3;
                int i4 = i3 - byteBuffer.getInt(i3);
                dk1Var.c = i4;
                dk1Var.d = dk1Var.b.getShort(i4);
            } else {
                dk1Var.a = 0;
                dk1Var.c = 0;
                dk1Var.d = 0;
            }
        }
        return dk1Var;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", id:");
        sb.append(Integer.toHexString(c()));
        sb.append(", codepoints:");
        int iB = b();
        for (int i = 0; i < iB; i++) {
            sb.append(Integer.toHexString(a(i)));
            sb.append(" ");
        }
        return sb.toString();
    }
}
