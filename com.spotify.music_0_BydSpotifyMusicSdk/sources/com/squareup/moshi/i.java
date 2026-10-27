package com.squareup.moshi;

import java.io.IOException;
import p.io;

/* JADX INFO: loaded from: classes.dex */
final class i extends m {
    private static final String[] u = new String[128];
    private final io r;
    private String s = ":";
    private String t;

    static {
        for (int i = 0; i <= 31; i++) {
            u[i] = String.format("\\u%04x", Integer.valueOf(i));
        }
        String[] strArr = u;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
    }

    public i(io ioVar) {
        if (ioVar == null) {
            throw new NullPointerException("sink == null");
        }
        this.r = ioVar;
        O0(6);
    }

    private void Y0() {
        int iM0 = M0();
        if (iM0 == 5) {
            this.r.R(44);
        } else if (iM0 != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        b1();
        P0(4);
    }

    private void Z0() {
        int iM0 = M0();
        int i = 2;
        if (iM0 == 1) {
            b1();
        } else if (iM0 == 2) {
            this.r.R(44);
            b1();
        } else if (iM0 == 4) {
            this.r.s0(this.s);
            i = 5;
        } else {
            if (iM0 == 9) {
                throw new IllegalStateException("Sink from valueSink() was not closed");
            }
            i = 7;
            if (iM0 != 6) {
                if (iM0 != 7) {
                    throw new IllegalStateException("Nesting problem.");
                }
                if (!this.n) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
            }
        }
        P0(i);
    }

    private m a1(int i, int i2, char c) {
        int iM0 = M0();
        if (iM0 != i2 && iM0 != i) {
            throw new IllegalStateException("Nesting problem.");
        }
        if (this.t != null) {
            throw new IllegalStateException("Dangling name: " + this.t);
        }
        int i3 = this.c;
        int i4 = this.q;
        if (i3 == (~i4)) {
            this.q = ~i4;
            return this;
        }
        int i5 = i3 - 1;
        this.c = i5;
        this.k[i5] = null;
        int[] iArr = this.l;
        int i6 = i3 - 2;
        iArr[i6] = iArr[i6] + 1;
        if (iM0 == i2) {
            b1();
        }
        this.r.R(c);
        return this;
    }

    private void b1() {
        if (this.m == null) {
            return;
        }
        this.r.R(10);
        int i = this.c;
        for (int i2 = 1; i2 < i; i2++) {
            this.r.s0(this.m);
        }
    }

    private m c1(int i, int i2, char c) {
        int i3 = this.c;
        int i4 = this.q;
        if (i3 == i4) {
            int[] iArr = this.e;
            if (iArr[i3 - 1] == i || iArr[i3 - 1] == i2) {
                this.q = ~i4;
                return this;
            }
        }
        Z0();
        q();
        O0(i);
        this.l[this.c - 1] = 0;
        this.r.R(c);
        return this;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void d1(p.io r7, java.lang.String r8) {
        /*
            java.lang.String[] r0 = com.squareup.moshi.i.u
            r1 = 34
            r7.R(r1)
            int r2 = r8.length()
            r3 = 0
            r4 = r3
        Ld:
            if (r3 >= r2) goto L36
            char r5 = r8.charAt(r3)
            r6 = 128(0x80, float:1.8E-43)
            if (r5 >= r6) goto L1c
            r5 = r0[r5]
            if (r5 != 0) goto L29
            goto L33
        L1c:
            r6 = 8232(0x2028, float:1.1535E-41)
            if (r5 != r6) goto L23
            java.lang.String r5 = "\\u2028"
            goto L29
        L23:
            r6 = 8233(0x2029, float:1.1537E-41)
            if (r5 != r6) goto L33
            java.lang.String r5 = "\\u2029"
        L29:
            if (r4 >= r3) goto L2e
            r7.r(r8, r4, r3)
        L2e:
            r7.s0(r5)
            int r4 = r3 + 1
        L33:
            int r3 = r3 + 1
            goto Ld
        L36:
            if (r4 >= r2) goto L3b
            r7.r(r8, r4, r2)
        L3b:
            r7.R(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.i.d1(p.io, java.lang.String):void");
    }

    private void e1() {
        if (this.t != null) {
            Y0();
            d1(this.r, this.t);
            this.t = null;
        }
    }

    @Override // com.squareup.moshi.m
    public m E() {
        return a1(1, 2, ']');
    }

    @Override // com.squareup.moshi.m
    public m J0(String str) {
        if (str == null) {
            throw new NullPointerException("name == null");
        }
        if (this.c == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        int iM0 = M0();
        if ((iM0 != 3 && iM0 != 5) || this.t != null || this.f70p) {
            throw new IllegalStateException("Nesting problem.");
        }
        this.t = str;
        this.k[this.c - 1] = str;
        return this;
    }

    @Override // com.squareup.moshi.m
    public m K0() {
        if (this.f70p) {
            throw new IllegalStateException("null cannot be used as a map key in JSON at path " + E0());
        }
        if (this.t != null) {
            if (!this.o) {
                this.t = null;
                return this;
            }
            e1();
        }
        Z0();
        this.r.s0("null");
        int[] iArr = this.l;
        int i = this.c - 1;
        iArr[i] = iArr[i] + 1;
        return this;
    }

    @Override // com.squareup.moshi.m
    public void Q0(String str) {
        super.Q0(str);
        this.s = !str.isEmpty() ? ": " : ":";
    }

    @Override // com.squareup.moshi.m
    public m T0(double d) {
        if (!this.n && (Double.isNaN(d) || Double.isInfinite(d))) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + d);
        }
        if (this.f70p) {
            this.f70p = false;
            return J0(Double.toString(d));
        }
        e1();
        Z0();
        this.r.s0(Double.toString(d));
        int[] iArr = this.l;
        int i = this.c - 1;
        iArr[i] = iArr[i] + 1;
        return this;
    }

    @Override // com.squareup.moshi.m
    public m U0(long j) {
        if (this.f70p) {
            this.f70p = false;
            return J0(Long.toString(j));
        }
        e1();
        Z0();
        this.r.s0(Long.toString(j));
        int[] iArr = this.l;
        int i = this.c - 1;
        iArr[i] = iArr[i] + 1;
        return this;
    }

    @Override // com.squareup.moshi.m
    public m V0(Number number) {
        if (number == null) {
            return K0();
        }
        String string = number.toString();
        if (!this.n && (string.equals("-Infinity") || string.equals("Infinity") || string.equals("NaN"))) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + number);
        }
        if (this.f70p) {
            this.f70p = false;
            return J0(string);
        }
        e1();
        Z0();
        this.r.s0(string);
        int[] iArr = this.l;
        int i = this.c - 1;
        iArr[i] = iArr[i] + 1;
        return this;
    }

    @Override // com.squareup.moshi.m
    public m W0(String str) {
        if (str == null) {
            return K0();
        }
        if (this.f70p) {
            this.f70p = false;
            return J0(str);
        }
        e1();
        Z0();
        d1(this.r, str);
        int[] iArr = this.l;
        int i = this.c - 1;
        iArr[i] = iArr[i] + 1;
        return this;
    }

    @Override // com.squareup.moshi.m
    public m X() {
        this.f70p = false;
        return a1(3, 5, '}');
    }

    @Override // com.squareup.moshi.m
    public m X0(boolean z) {
        if (this.f70p) {
            throw new IllegalStateException("Boolean cannot be used as a map key in JSON at path " + E0());
        }
        e1();
        Z0();
        this.r.s0(z ? "true" : "false");
        int[] iArr = this.l;
        int i = this.c - 1;
        iArr[i] = iArr[i] + 1;
        return this;
    }

    @Override // com.squareup.moshi.m
    public m b() {
        if (!this.f70p) {
            e1();
            return c1(1, 2, '[');
        }
        throw new IllegalStateException("Array cannot be used as a map key in JSON at path " + E0());
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.r.close();
        int i = this.c;
        if (i > 1 || (i == 1 && this.e[i - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.c = 0;
    }

    @Override // java.io.Flushable
    public void flush() {
        if (this.c == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.r.flush();
    }

    @Override // com.squareup.moshi.m
    public m i() {
        if (!this.f70p) {
            e1();
            return c1(3, 5, '{');
        }
        throw new IllegalStateException("Object cannot be used as a map key in JSON at path " + E0());
    }
}
