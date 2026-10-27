package p;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes.dex */
public final class cl0 {
    public final int a;
    public final int b;
    public final long c;
    public final byte[] d;

    public cl0(byte[] bArr, int i, int i2) {
        this(-1L, bArr, i, i2);
    }

    public static cl0 a(long j, ByteOrder byteOrder) {
        long[] jArr = {j};
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[gl0.C[4]]);
        byteBufferWrap.order(byteOrder);
        byteBufferWrap.putInt((int) jArr[0]);
        return new cl0(byteBufferWrap.array(), 4, 1);
    }

    public static cl0 b(el0 el0Var, ByteOrder byteOrder) {
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[gl0.C[5]]);
        byteBufferWrap.order(byteOrder);
        el0 el0Var2 = new el0[]{el0Var}[0];
        byteBufferWrap.putInt((int) el0Var2.a);
        byteBufferWrap.putInt((int) el0Var2.b);
        return new cl0(byteBufferWrap.array(), 5, 1);
    }

    public static cl0 c(int i, ByteOrder byteOrder) {
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[gl0.C[3]]);
        byteBufferWrap.order(byteOrder);
        byteBufferWrap.putShort((short) new int[]{i}[0]);
        return new cl0(byteBufferWrap.array(), 3, 1);
    }

    public final double d(ByteOrder byteOrder) throws Throwable {
        Object objG = g(byteOrder);
        if (objG == null) {
            throw new NumberFormatException("NULL can't be converted to a double value");
        }
        if (objG instanceof String) {
            return Double.parseDouble((String) objG);
        }
        if (objG instanceof long[]) {
            if (((long[]) objG).length == 1) {
                return r5[0];
            }
            throw new NumberFormatException("There are more than one component");
        }
        if (objG instanceof int[]) {
            if (((int[]) objG).length == 1) {
                return r5[0];
            }
            throw new NumberFormatException("There are more than one component");
        }
        if (objG instanceof double[]) {
            double[] dArr = (double[]) objG;
            if (dArr.length == 1) {
                return dArr[0];
            }
            throw new NumberFormatException("There are more than one component");
        }
        if (!(objG instanceof el0[])) {
            throw new NumberFormatException("Couldn't find a double value");
        }
        el0[] el0VarArr = (el0[]) objG;
        if (el0VarArr.length != 1) {
            throw new NumberFormatException("There are more than one component");
        }
        el0 el0Var = el0VarArr[0];
        return el0Var.a / el0Var.b;
    }

    public final int e(ByteOrder byteOrder) throws Throwable {
        Object objG = g(byteOrder);
        if (objG == null) {
            throw new NumberFormatException("NULL can't be converted to a integer value");
        }
        if (objG instanceof String) {
            return Integer.parseInt((String) objG);
        }
        if (objG instanceof long[]) {
            long[] jArr = (long[]) objG;
            if (jArr.length == 1) {
                return (int) jArr[0];
            }
            throw new NumberFormatException("There are more than one component");
        }
        if (!(objG instanceof int[])) {
            throw new NumberFormatException("Couldn't find a integer value");
        }
        int[] iArr = (int[]) objG;
        if (iArr.length == 1) {
            return iArr[0];
        }
        throw new NumberFormatException("There are more than one component");
    }

    public final String f(ByteOrder byteOrder) throws Throwable {
        Object objG = g(byteOrder);
        if (objG == null) {
            return null;
        }
        if (objG instanceof String) {
            return (String) objG;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        if (objG instanceof long[]) {
            long[] jArr = (long[]) objG;
            while (i < jArr.length) {
                sb.append(jArr[i]);
                i++;
                if (i != jArr.length) {
                    sb.append(",");
                }
            }
            return sb.toString();
        }
        if (objG instanceof int[]) {
            int[] iArr = (int[]) objG;
            while (i < iArr.length) {
                sb.append(iArr[i]);
                i++;
                if (i != iArr.length) {
                    sb.append(",");
                }
            }
            return sb.toString();
        }
        if (objG instanceof double[]) {
            double[] dArr = (double[]) objG;
            while (i < dArr.length) {
                sb.append(dArr[i]);
                i++;
                if (i != dArr.length) {
                    sb.append(",");
                }
            }
            return sb.toString();
        }
        if (!(objG instanceof el0[])) {
            return null;
        }
        el0[] el0VarArr = (el0[]) objG;
        while (i < el0VarArr.length) {
            sb.append(el0VarArr[i].a);
            sb.append('/');
            sb.append(el0VarArr[i].b);
            i++;
            if (i != el0VarArr.length) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    /* JADX WARN: Can't wrap try/catch for region: R(9:86|(2:88|(2:89|(2:91|(2:177|93)(1:94))(2:176|95)))|96|(2:98|(6:179|100|106|157|107|111)(3:101|(2:103|181)(2:104|180)|105))|178|106|157|107|111) */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x012e, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x012f, code lost:
    
        android.util.Log.e("ExifInterface", "IOException occurred while closing InputStream", r0);
     */
    /* JADX WARN: Type inference failed for: r14v20, types: [int[], java.io.Serializable] */
    /* JADX WARN: Type inference failed for: r14v21, types: [java.io.Serializable, long[]] */
    /* JADX WARN: Type inference failed for: r14v22, types: [java.io.Serializable, p.el0[]] */
    /* JADX WARN: Type inference failed for: r14v23, types: [int[], java.io.Serializable] */
    /* JADX WARN: Type inference failed for: r14v24, types: [int[], java.io.Serializable] */
    /* JADX WARN: Type inference failed for: r14v25, types: [java.io.Serializable, p.el0[]] */
    /* JADX WARN: Type inference failed for: r14v26, types: [double[], java.io.Serializable] */
    /* JADX WARN: Type inference failed for: r14v27, types: [double[], java.io.Serializable] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.io.Serializable g(java.nio.ByteOrder r14) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 408
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.cl0.g(java.nio.ByteOrder):java.io.Serializable");
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("(");
        sb.append(gl0.B[this.a]);
        sb.append(", data length:");
        return u4.l(sb, this.d.length, ")");
    }

    public cl0(long j, byte[] bArr, int i, int i2) {
        this.a = i;
        this.b = i2;
        this.c = j;
        this.d = bArr;
    }
}
