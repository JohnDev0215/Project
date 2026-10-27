package p;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class z71 implements Closeable, Flushable {
    public static final String[] q = new String[128];
    public final Writer c;
    public int[] e;
    public int k;
    public String l;
    public String m;
    public boolean n;
    public String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f214p;

    static {
        for (int i = 0; i <= 31; i++) {
            q[i] = String.format("\\u%04x", Integer.valueOf(i));
        }
        String[] strArr = q;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        String[] strArr2 = (String[]) strArr.clone();
        strArr2[60] = "\\u003c";
        strArr2[62] = "\\u003e";
        strArr2[38] = "\\u0026";
        strArr2[61] = "\\u003d";
        strArr2[39] = "\\u0027";
    }

    public z71(Writer writer) {
        int[] iArr = new int[32];
        this.e = iArr;
        this.k = 0;
        if (iArr.length == 0) {
            this.e = Arrays.copyOf(iArr, 0);
        }
        int[] iArr2 = this.e;
        int i = this.k;
        this.k = i + 1;
        iArr2[i] = 6;
        this.m = ":";
        this.f214p = true;
        if (writer == null) {
            throw new NullPointerException("out == null");
        }
        this.c = writer;
    }

    public void E() throws IOException {
        q(1, 2, ']');
    }

    public z71 E0() throws IOException {
        if (this.o != null) {
            if (!this.f214p) {
                this.o = null;
                return this;
            }
            N0();
        }
        b();
        this.c.write("null");
        return this;
    }

    public final int G0() {
        int i = this.k;
        if (i != 0) {
            return this.e[i - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void H0(java.lang.String r9) throws java.io.IOException {
        /*
            r8 = this;
            java.lang.String[] r0 = p.z71.q
            java.io.Writer r1 = r8.c
            r2 = 34
            r1.write(r2)
            int r3 = r9.length()
            r4 = 0
            r5 = r4
        Lf:
            if (r4 >= r3) goto L3a
            char r6 = r9.charAt(r4)
            r7 = 128(0x80, float:1.8E-43)
            if (r6 >= r7) goto L1e
            r6 = r0[r6]
            if (r6 != 0) goto L2b
            goto L37
        L1e:
            r7 = 8232(0x2028, float:1.1535E-41)
            if (r6 != r7) goto L25
            java.lang.String r6 = "\\u2028"
            goto L2b
        L25:
            r7 = 8233(0x2029, float:1.1537E-41)
            if (r6 != r7) goto L37
            java.lang.String r6 = "\\u2029"
        L2b:
            if (r5 >= r4) goto L32
            int r7 = r4 - r5
            r1.write(r9, r5, r7)
        L32:
            r1.write(r6)
            int r5 = r4 + 1
        L37:
            int r4 = r4 + 1
            goto Lf
        L3a:
            if (r5 >= r3) goto L40
            int r3 = r3 - r5
            r1.write(r9, r5, r3)
        L40:
            r1.write(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p.z71.H0(java.lang.String):void");
    }

    public void I0(long j) throws IOException {
        N0();
        b();
        this.c.write(Long.toString(j));
    }

    public void J() throws IOException {
        q(3, 5, '}');
    }

    public void J0(Boolean bool) throws IOException {
        if (bool == null) {
            E0();
            return;
        }
        N0();
        b();
        this.c.write(bool.booleanValue() ? "true" : "false");
    }

    public void K0(Number number) throws IOException {
        if (number == null) {
            E0();
            return;
        }
        N0();
        String string = number.toString();
        if (this.n || !(string.equals("-Infinity") || string.equals("Infinity") || string.equals("NaN"))) {
            b();
            this.c.append((CharSequence) string);
        } else {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + number);
        }
    }

    public void L0(String str) throws IOException {
        if (str == null) {
            E0();
            return;
        }
        N0();
        b();
        H0(str);
    }

    public void M0(boolean z) throws IOException {
        N0();
        b();
        this.c.write(z ? "true" : "false");
    }

    public final void N0() throws IOException {
        if (this.o != null) {
            int iG0 = G0();
            if (iG0 == 5) {
                this.c.write(44);
            } else if (iG0 != 3) {
                throw new IllegalStateException("Nesting problem.");
            }
            o0();
            this.e[this.k - 1] = 4;
            H0(this.o);
            this.o = null;
        }
    }

    public void X(String str) {
        if (str == null) {
            throw new NullPointerException("name == null");
        }
        if (this.o != null) {
            throw new IllegalStateException();
        }
        if (this.k == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.o = str;
    }

    public final void b() throws IOException {
        int iG0 = G0();
        if (iG0 == 1) {
            this.e[this.k - 1] = 2;
            o0();
            return;
        }
        Writer writer = this.c;
        if (iG0 == 2) {
            writer.append(',');
            o0();
        } else {
            if (iG0 == 4) {
                writer.append((CharSequence) this.m);
                this.e[this.k - 1] = 5;
                return;
            }
            if (iG0 != 6) {
                if (iG0 != 7) {
                    throw new IllegalStateException("Nesting problem.");
                }
                if (!this.n) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
            }
            this.e[this.k - 1] = 7;
        }
    }

    public void c() throws IOException {
        N0();
        b();
        int i = this.k;
        int[] iArr = this.e;
        if (i == iArr.length) {
            this.e = Arrays.copyOf(iArr, i * 2);
        }
        int[] iArr2 = this.e;
        int i2 = this.k;
        this.k = i2 + 1;
        iArr2[i2] = 1;
        this.c.write(91);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.c.close();
        int i = this.k;
        if (i > 1 || (i == 1 && this.e[i - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.k = 0;
    }

    public void flush() throws IOException {
        if (this.k == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.c.flush();
    }

    public void i() throws IOException {
        N0();
        b();
        int i = this.k;
        int[] iArr = this.e;
        if (i == iArr.length) {
            this.e = Arrays.copyOf(iArr, i * 2);
        }
        int[] iArr2 = this.e;
        int i2 = this.k;
        this.k = i2 + 1;
        iArr2[i2] = 3;
        this.c.write(123);
    }

    public final void o0() throws IOException {
        if (this.l == null) {
            return;
        }
        Writer writer = this.c;
        writer.write(10);
        int i = this.k;
        for (int i2 = 1; i2 < i; i2++) {
            writer.write(this.l);
        }
    }

    public final void q(int i, int i2, char c) throws IOException {
        int iG0 = G0();
        if (iG0 != i2 && iG0 != i) {
            throw new IllegalStateException("Nesting problem.");
        }
        if (this.o != null) {
            throw new IllegalStateException("Dangling name: " + this.o);
        }
        this.k--;
        if (iG0 == i2) {
            o0();
        }
        this.c.write(c);
    }
}
