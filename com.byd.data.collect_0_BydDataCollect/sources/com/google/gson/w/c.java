package com.google.gson.w;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import java.util.Objects;

/* JADX INFO: compiled from: JsonWriter.java */
/* JADX INFO: loaded from: classes.dex */
public class c implements Closeable, Flushable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String[] f4621b = new String[128];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String[] f4622c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Writer f4623d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int[] f4624e = new int[32];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f4625f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private String f4626g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private String f4627h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f4628i;
    private boolean j;
    private String k;
    private boolean l;

    static {
        for (int i2 = 0; i2 <= 31; i2++) {
            f4621b[i2] = String.format("\\u%04x", Integer.valueOf(i2));
        }
        String[] strArr = f4621b;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        String[] strArr2 = (String[]) strArr.clone();
        f4622c = strArr2;
        strArr2[60] = "\\u003c";
        strArr2[62] = "\\u003e";
        strArr2[38] = "\\u0026";
        strArr2[61] = "\\u003d";
        strArr2[39] = "\\u0027";
    }

    public c(Writer writer) {
        b0(6);
        this.f4627h = ":";
        this.l = true;
        Objects.requireNonNull(writer, "out == null");
        this.f4623d = writer;
    }

    private c C(int i2, int i3, String str) throws IOException {
        int iA0 = a0();
        if (iA0 != i3 && iA0 != i2) {
            throw new IllegalStateException("Nesting problem.");
        }
        if (this.k != null) {
            throw new IllegalStateException("Dangling name: " + this.k);
        }
        this.f4625f--;
        if (iA0 == i3) {
            X();
        }
        this.f4623d.write(str);
        return this;
    }

    private void X() throws IOException {
        if (this.f4626g == null) {
            return;
        }
        this.f4623d.write("\n");
        int i2 = this.f4625f;
        for (int i3 = 1; i3 < i2; i3++) {
            this.f4623d.write(this.f4626g);
        }
    }

    private c Z(int i2, String str) throws IOException {
        d();
        b0(i2);
        this.f4623d.write(str);
        return this;
    }

    private void a() throws IOException {
        int iA0 = a0();
        if (iA0 == 5) {
            this.f4623d.write(44);
        } else if (iA0 != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        X();
        c0(4);
    }

    private int a0() {
        int i2 = this.f4625f;
        if (i2 != 0) {
            return this.f4624e[i2 - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    private void b0(int i2) {
        int i3 = this.f4625f;
        int[] iArr = this.f4624e;
        if (i3 == iArr.length) {
            int[] iArr2 = new int[i3 * 2];
            System.arraycopy(iArr, 0, iArr2, 0, i3);
            this.f4624e = iArr2;
        }
        int[] iArr3 = this.f4624e;
        int i4 = this.f4625f;
        this.f4625f = i4 + 1;
        iArr3[i4] = i2;
    }

    private void c0(int i2) {
        this.f4624e[this.f4625f - 1] = i2;
    }

    private void d() throws IOException {
        int iA0 = a0();
        if (iA0 == 1) {
            c0(2);
            X();
            return;
        }
        if (iA0 == 2) {
            this.f4623d.append(',');
            X();
        } else {
            if (iA0 == 4) {
                this.f4623d.append((CharSequence) this.f4627h);
                c0(5);
                return;
            }
            if (iA0 != 6) {
                if (iA0 != 7) {
                    throw new IllegalStateException("Nesting problem.");
                }
                if (!this.f4628i) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
            }
            c0(7);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void h0(java.lang.String r9) throws java.io.IOException {
        /*
            r8 = this;
            boolean r0 = r8.j
            if (r0 == 0) goto L7
            java.lang.String[] r0 = com.google.gson.w.c.f4622c
            goto L9
        L7:
            java.lang.String[] r0 = com.google.gson.w.c.f4621b
        L9:
            java.io.Writer r1 = r8.f4623d
            java.lang.String r2 = "\""
            r1.write(r2)
            int r1 = r9.length()
            r3 = 0
            r4 = r3
        L16:
            if (r3 >= r1) goto L45
            char r5 = r9.charAt(r3)
            r6 = 128(0x80, float:1.8E-43)
            if (r5 >= r6) goto L25
            r5 = r0[r5]
            if (r5 != 0) goto L32
            goto L42
        L25:
            r6 = 8232(0x2028, float:1.1535E-41)
            if (r5 != r6) goto L2c
            java.lang.String r5 = "\\u2028"
            goto L32
        L2c:
            r6 = 8233(0x2029, float:1.1537E-41)
            if (r5 != r6) goto L42
            java.lang.String r5 = "\\u2029"
        L32:
            if (r4 >= r3) goto L3b
            java.io.Writer r6 = r8.f4623d
            int r7 = r3 - r4
            r6.write(r9, r4, r7)
        L3b:
            java.io.Writer r4 = r8.f4623d
            r4.write(r5)
            int r4 = r3 + 1
        L42:
            int r3 = r3 + 1
            goto L16
        L45:
            if (r4 >= r1) goto L4d
            java.io.Writer r0 = r8.f4623d
            int r1 = r1 - r4
            r0.write(r9, r4, r1)
        L4d:
            java.io.Writer r9 = r8.f4623d
            r9.write(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.w.c.h0(java.lang.String):void");
    }

    private void n0() throws IOException {
        if (this.k != null) {
            a();
            h0(this.k);
            this.k = null;
        }
    }

    public c R() {
        return C(1, 2, "]");
    }

    public c S() {
        return C(3, 5, "}");
    }

    public final boolean T() {
        return this.l;
    }

    public final boolean U() {
        return this.j;
    }

    public boolean V() {
        return this.f4628i;
    }

    public c W(String str) {
        Objects.requireNonNull(str, "name == null");
        if (this.k != null) {
            throw new IllegalStateException();
        }
        if (this.f4625f == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.k = str;
        return this;
    }

    public c Y() throws IOException {
        if (this.k != null) {
            if (!this.l) {
                this.k = null;
                return this;
            }
            n0();
        }
        d();
        this.f4623d.write("null");
        return this;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f4623d.close();
        int i2 = this.f4625f;
        if (i2 > 1 || (i2 == 1 && this.f4624e[i2 - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.f4625f = 0;
    }

    public final void d0(boolean z) {
        this.j = z;
    }

    public final void e0(String str) {
        if (str.length() == 0) {
            this.f4626g = null;
            this.f4627h = ":";
        } else {
            this.f4626g = str;
            this.f4627h = ": ";
        }
    }

    public final void f0(boolean z) {
        this.f4628i = z;
    }

    public void flush() throws IOException {
        if (this.f4625f == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.f4623d.flush();
    }

    public final void g0(boolean z) {
        this.l = z;
    }

    public c i0(long j) throws IOException {
        n0();
        d();
        this.f4623d.write(Long.toString(j));
        return this;
    }

    public c j() throws IOException {
        n0();
        return Z(1, "[");
    }

    public c j0(Boolean bool) throws IOException {
        if (bool == null) {
            return Y();
        }
        n0();
        d();
        this.f4623d.write(bool.booleanValue() ? "true" : "false");
        return this;
    }

    public c k0(Number number) throws IOException {
        if (number == null) {
            return Y();
        }
        n0();
        String string = number.toString();
        if (this.f4628i || !(string.equals("-Infinity") || string.equals("Infinity") || string.equals("NaN"))) {
            d();
            this.f4623d.append((CharSequence) string);
            return this;
        }
        throw new IllegalArgumentException("Numeric values must be finite, but was " + number);
    }

    public c l0(String str) throws IOException {
        if (str == null) {
            return Y();
        }
        n0();
        d();
        h0(str);
        return this;
    }

    public c m0(boolean z) throws IOException {
        n0();
        d();
        this.f4623d.write(z ? "true" : "false");
        return this;
    }

    public c y() throws IOException {
        n0();
        return Z(3, "{");
    }
}
