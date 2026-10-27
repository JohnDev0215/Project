package com.google.gson.stream;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: compiled from: JsonWriter.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements Closeable, Flushable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String[] f4164b = new String[128];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String[] f4165c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Writer f4166d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int[] f4167e = new int[32];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f4168f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private String f4169g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private String f4170h;
    private boolean i;
    private boolean j;
    private String k;
    private boolean l;

    static {
        for (int i = 0; i <= 31; i++) {
            f4164b[i] = String.format("\\u%04x", Integer.valueOf(i));
        }
        String[] strArr = f4164b;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        String[] strArr2 = (String[]) strArr.clone();
        f4165c = strArr2;
        strArr2[60] = "\\u003c";
        strArr2[62] = "\\u003e";
        strArr2[38] = "\\u0026";
        strArr2[61] = "\\u003d";
        strArr2[39] = "\\u0027";
    }

    public b(Writer writer) {
        p(6);
        this.f4170h = ":";
        this.l = true;
        Objects.requireNonNull(writer, "out == null");
        this.f4166d = writer;
    }

    private void B() throws IOException {
        if (this.k != null) {
            a();
            v(this.k);
            this.k = null;
        }
    }

    private void a() throws IOException {
        int iO = o();
        if (iO == 5) {
            this.f4166d.write(44);
        } else if (iO != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        l();
        q(4);
    }

    private void b() throws IOException {
        int iO = o();
        if (iO == 1) {
            q(2);
            l();
            return;
        }
        if (iO == 2) {
            this.f4166d.append(',');
            l();
        } else {
            if (iO == 4) {
                this.f4166d.append((CharSequence) this.f4170h);
                q(5);
                return;
            }
            if (iO != 6) {
                if (iO != 7) {
                    throw new IllegalStateException("Nesting problem.");
                }
                if (!this.i) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
            }
            q(7);
        }
    }

    private b e(int i, int i2, char c2) throws IOException {
        int iO = o();
        if (iO != i2 && iO != i) {
            throw new IllegalStateException("Nesting problem.");
        }
        if (this.k != null) {
            throw new IllegalStateException("Dangling name: " + this.k);
        }
        this.f4168f--;
        if (iO == i2) {
            l();
        }
        this.f4166d.write(c2);
        return this;
    }

    private void l() throws IOException {
        if (this.f4169g == null) {
            return;
        }
        this.f4166d.write(10);
        int i = this.f4168f;
        for (int i2 = 1; i2 < i; i2++) {
            this.f4166d.write(this.f4169g);
        }
    }

    private b n(int i, char c2) throws IOException {
        b();
        p(i);
        this.f4166d.write(c2);
        return this;
    }

    private int o() {
        int i = this.f4168f;
        if (i != 0) {
            return this.f4167e[i - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    private void p(int i) {
        int i2 = this.f4168f;
        int[] iArr = this.f4167e;
        if (i2 == iArr.length) {
            this.f4167e = Arrays.copyOf(iArr, i2 * 2);
        }
        int[] iArr2 = this.f4167e;
        int i3 = this.f4168f;
        this.f4168f = i3 + 1;
        iArr2[i3] = i;
    }

    private void q(int i) {
        this.f4167e[this.f4168f - 1] = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void v(java.lang.String r9) throws java.io.IOException {
        /*
            r8 = this;
            boolean r0 = r8.j
            if (r0 == 0) goto L7
            java.lang.String[] r0 = com.google.gson.stream.b.f4165c
            goto L9
        L7:
            java.lang.String[] r0 = com.google.gson.stream.b.f4164b
        L9:
            java.io.Writer r1 = r8.f4166d
            r2 = 34
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
            java.io.Writer r6 = r8.f4166d
            int r7 = r3 - r4
            r6.write(r9, r4, r7)
        L3b:
            java.io.Writer r4 = r8.f4166d
            r4.write(r5)
            int r4 = r3 + 1
        L42:
            int r3 = r3 + 1
            goto L16
        L45:
            if (r4 >= r1) goto L4d
            java.io.Writer r0 = r8.f4166d
            int r1 = r1 - r4
            r0.write(r9, r4, r1)
        L4d:
            java.io.Writer r9 = r8.f4166d
            r9.write(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.stream.b.v(java.lang.String):void");
    }

    public b A(boolean z) throws IOException {
        B();
        b();
        this.f4166d.write(z ? "true" : "false");
        return this;
    }

    public b c() throws IOException {
        B();
        return n(1, '[');
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f4166d.close();
        int i = this.f4168f;
        if (i > 1 || (i == 1 && this.f4167e[i - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.f4168f = 0;
    }

    public b d() throws IOException {
        B();
        return n(3, '{');
    }

    public b f() throws IOException {
        return e(1, 2, ']');
    }

    public void flush() throws IOException {
        if (this.f4168f == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.f4166d.flush();
    }

    public b g() throws IOException {
        return e(3, 5, '}');
    }

    public final boolean h() {
        return this.l;
    }

    public final boolean i() {
        return this.j;
    }

    public boolean j() {
        return this.i;
    }

    public b k(String str) throws IOException {
        Objects.requireNonNull(str, "name == null");
        if (this.k != null) {
            throw new IllegalStateException();
        }
        if (this.f4168f == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.k = str;
        return this;
    }

    public b m() throws IOException {
        if (this.k != null) {
            if (!this.l) {
                this.k = null;
                return this;
            }
            B();
        }
        b();
        this.f4166d.write("null");
        return this;
    }

    public final void r(boolean z) {
        this.j = z;
    }

    public final void s(String str) {
        if (str.length() == 0) {
            this.f4169g = null;
            this.f4170h = ":";
        } else {
            this.f4169g = str;
            this.f4170h = ": ";
        }
    }

    public final void t(boolean z) {
        this.i = z;
    }

    public final void u(boolean z) {
        this.l = z;
    }

    public b w(long j) throws IOException {
        B();
        b();
        this.f4166d.write(Long.toString(j));
        return this;
    }

    public b x(Boolean bool) throws IOException {
        if (bool == null) {
            return m();
        }
        B();
        b();
        this.f4166d.write(bool.booleanValue() ? "true" : "false");
        return this;
    }

    public b y(Number number) throws IOException {
        if (number == null) {
            return m();
        }
        B();
        String string = number.toString();
        if (this.i || !(string.equals("-Infinity") || string.equals("Infinity") || string.equals("NaN"))) {
            b();
            this.f4166d.append((CharSequence) string);
            return this;
        }
        throw new IllegalArgumentException("Numeric values must be finite, but was " + number);
    }

    public b z(String str) throws IOException {
        if (str == null) {
            return m();
        }
        B();
        b();
        v(str);
        return this;
    }
}
