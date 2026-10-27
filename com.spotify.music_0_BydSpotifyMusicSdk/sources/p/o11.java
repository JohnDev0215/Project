package p;

/* JADX INFO: loaded from: classes.dex */
public abstract class o11 {
    public static final char[] c = "0123456789abcdef".toCharArray();

    public abstract byte[] a();

    public abstract int b();

    public abstract long c();

    public abstract int d();

    public abstract boolean e(o11 o11Var);

    public final boolean equals(Object obj) {
        if (!(obj instanceof o11)) {
            return false;
        }
        o11 o11Var = (o11) obj;
        return d() == o11Var.d() && e(o11Var);
    }

    public byte[] f() {
        return a();
    }

    public final int hashCode() {
        if (d() >= 32) {
            return b();
        }
        byte[] bArrF = f();
        int i = bArrF[0] & 255;
        for (int i2 = 1; i2 < bArrF.length; i2++) {
            i |= (bArrF[i2] & 255) << (i2 * 8);
        }
        return i;
    }

    public final String toString() {
        byte[] bArrF = f();
        StringBuilder sb = new StringBuilder(bArrF.length * 2);
        for (byte b : bArrF) {
            char[] cArr = c;
            sb.append(cArr[(b >> 4) & 15]);
            sb.append(cArr[b & 15]);
        }
        return sb.toString();
    }
}
