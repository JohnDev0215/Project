package spi;

import java.io.Reader;
import java.util.Iterator;
import java.util.Map;
import spi.y5;

/* JADX INFO: compiled from: JsonTreeReader.java */
/* JADX INFO: loaded from: classes2.dex */
public final class t5 extends o5 {
    public static final a u = new a();
    public static final Object v = new Object();
    public Object[] q;
    public int r;
    public String[] s;
    public int[] t;

    /* JADX INFO: compiled from: JsonTreeReader.java */
    public static class a extends Reader {
        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
            throw new AssertionError();
        }

        @Override // java.io.Reader
        public final int read(char[] cArr, int i, int i2) {
            throw new AssertionError();
        }
    }

    public t5(i5 i5Var) {
        super(u);
        this.q = new Object[32];
        this.r = 0;
        this.s = new String[32];
        this.t = new int[32];
        F(i5Var);
    }

    private String k() {
        StringBuilder sbK = c.k(" at path ");
        sbK.append(h());
        return sbK.toString();
    }

    @Override // spi.o5
    public final void A() {
        if (v() == s5.NAME) {
            p();
            this.s[this.r - 2] = "null";
        } else {
            E();
            int i = this.r;
            if (i > 0) {
                this.s[i - 1] = "null";
            }
        }
        int i2 = this.r;
        if (i2 > 0) {
            int[] iArr = this.t;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
        }
    }

    public final void C(s5 s5Var) {
        if (v() == s5Var) {
            return;
        }
        throw new IllegalStateException("Expected " + s5Var + " but was " + v() + k());
    }

    public final Object D() {
        return this.q[this.r - 1];
    }

    public final Object E() {
        Object[] objArr = this.q;
        int i = this.r - 1;
        this.r = i;
        Object obj = objArr[i];
        objArr[i] = null;
        return obj;
    }

    public final void F(Object obj) {
        int i = this.r;
        Object[] objArr = this.q;
        if (i == objArr.length) {
            Object[] objArr2 = new Object[i * 2];
            int[] iArr = new int[i * 2];
            String[] strArr = new String[i * 2];
            System.arraycopy(objArr, 0, objArr2, 0, i);
            System.arraycopy(this.t, 0, iArr, 0, this.r);
            System.arraycopy(this.s, 0, strArr, 0, this.r);
            this.q = objArr2;
            this.t = iArr;
            this.s = strArr;
        }
        Object[] objArr3 = this.q;
        int i2 = this.r;
        this.r = i2 + 1;
        objArr3[i2] = obj;
    }

    @Override // spi.o5
    public final void a() {
        C(s5.BEGIN_ARRAY);
        F(((g5) D()).iterator());
        this.t[this.r - 1] = 0;
    }

    @Override // spi.o5
    public final void b() {
        C(s5.BEGIN_OBJECT);
        F(new y5.b.a((y5.b) ((l5) D()).a.entrySet()));
    }

    @Override // spi.o5, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.q = new Object[]{v};
        this.r = 1;
    }

    @Override // spi.o5
    public final void e() {
        C(s5.END_ARRAY);
        E();
        E();
        int i = this.r;
        if (i > 0) {
            int[] iArr = this.t;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // spi.o5
    public final void f() {
        C(s5.END_OBJECT);
        E();
        E();
        int i = this.r;
        if (i > 0) {
            int[] iArr = this.t;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // spi.o5
    public final String h() {
        StringBuilder sb = new StringBuilder();
        sb.append('$');
        int i = 0;
        while (i < this.r) {
            Object[] objArr = this.q;
            if (objArr[i] instanceof g5) {
                i++;
                if (objArr[i] instanceof Iterator) {
                    sb.append('[');
                    sb.append(this.t[i]);
                    sb.append(']');
                }
            } else if (objArr[i] instanceof l5) {
                i++;
                if (objArr[i] instanceof Iterator) {
                    sb.append('.');
                    String[] strArr = this.s;
                    if (strArr[i] != null) {
                        sb.append(strArr[i]);
                    }
                }
            }
            i++;
        }
        return sb.toString();
    }

    @Override // spi.o5
    public final boolean i() {
        s5 s5VarV = v();
        return (s5VarV == s5.END_OBJECT || s5VarV == s5.END_ARRAY) ? false : true;
    }

    @Override // spi.o5
    public final boolean l() {
        C(s5.BOOLEAN);
        boolean zB = ((n5) E()).b();
        int i = this.r;
        if (i > 0) {
            int[] iArr = this.t;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return zB;
    }

    @Override // spi.o5
    public final double m() {
        s5 s5VarV = v();
        s5 s5Var = s5.NUMBER;
        if (s5VarV != s5Var && s5VarV != s5.STRING) {
            throw new IllegalStateException("Expected " + s5Var + " but was " + s5VarV + k());
        }
        n5 n5Var = (n5) D();
        double dDoubleValue = n5Var.a instanceof Number ? n5Var.c().doubleValue() : Double.parseDouble(n5Var.d());
        if (!this.b && (Double.isNaN(dDoubleValue) || Double.isInfinite(dDoubleValue))) {
            throw new NumberFormatException("JSON forbids NaN and infinities: " + dDoubleValue);
        }
        E();
        int i = this.r;
        if (i > 0) {
            int[] iArr = this.t;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return dDoubleValue;
    }

    @Override // spi.o5
    public final int n() {
        s5 s5VarV = v();
        s5 s5Var = s5.NUMBER;
        if (s5VarV != s5Var && s5VarV != s5.STRING) {
            throw new IllegalStateException("Expected " + s5Var + " but was " + s5VarV + k());
        }
        n5 n5Var = (n5) D();
        int iIntValue = n5Var.a instanceof Number ? n5Var.c().intValue() : Integer.parseInt(n5Var.d());
        E();
        int i = this.r;
        if (i > 0) {
            int[] iArr = this.t;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return iIntValue;
    }

    @Override // spi.o5
    public final long o() {
        s5 s5VarV = v();
        s5 s5Var = s5.NUMBER;
        if (s5VarV != s5Var && s5VarV != s5.STRING) {
            throw new IllegalStateException("Expected " + s5Var + " but was " + s5VarV + k());
        }
        n5 n5Var = (n5) D();
        long jLongValue = n5Var.a instanceof Number ? n5Var.c().longValue() : Long.parseLong(n5Var.d());
        E();
        int i = this.r;
        if (i > 0) {
            int[] iArr = this.t;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return jLongValue;
    }

    @Override // spi.o5
    public final String p() {
        C(s5.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) D()).next();
        String str = (String) entry.getKey();
        this.s[this.r - 1] = str;
        F(entry.getValue());
        return str;
    }

    @Override // spi.o5
    public final void r() {
        C(s5.NULL);
        E();
        int i = this.r;
        if (i > 0) {
            int[] iArr = this.t;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // spi.o5
    public final String t() {
        s5 s5VarV = v();
        s5 s5Var = s5.STRING;
        if (s5VarV == s5Var || s5VarV == s5.NUMBER) {
            String strD = ((n5) E()).d();
            int i = this.r;
            if (i > 0) {
                int[] iArr = this.t;
                int i2 = i - 1;
                iArr[i2] = iArr[i2] + 1;
            }
            return strD;
        }
        throw new IllegalStateException("Expected " + s5Var + " but was " + s5VarV + k());
    }

    @Override // spi.o5
    public final String toString() {
        return t5.class.getSimpleName();
    }

    @Override // spi.o5
    public final s5 v() {
        if (this.r == 0) {
            return s5.END_DOCUMENT;
        }
        Object objD = D();
        if (objD instanceof Iterator) {
            boolean z = this.q[this.r - 2] instanceof l5;
            Iterator it = (Iterator) objD;
            if (!it.hasNext()) {
                return z ? s5.END_OBJECT : s5.END_ARRAY;
            }
            if (z) {
                return s5.NAME;
            }
            F(it.next());
            return v();
        }
        if (objD instanceof l5) {
            return s5.BEGIN_OBJECT;
        }
        if (objD instanceof g5) {
            return s5.BEGIN_ARRAY;
        }
        if (!(objD instanceof n5)) {
            if (objD instanceof k5) {
                return s5.NULL;
            }
            if (objD == v) {
                throw new IllegalStateException("JsonReader is closed");
            }
            throw new AssertionError();
        }
        Object obj = ((n5) objD).a;
        if (obj instanceof String) {
            return s5.STRING;
        }
        if (obj instanceof Boolean) {
            return s5.BOOLEAN;
        }
        if (obj instanceof Number) {
            return s5.NUMBER;
        }
        throw new AssertionError();
    }
}
