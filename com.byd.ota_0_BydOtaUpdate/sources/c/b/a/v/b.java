package c.b.a.v;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import java.util.Objects;

/* JADX INFO: compiled from: JsonWriter.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements Closeable, Flushable {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String[] f3038c = new String[128];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String[] f3039d;
    public String E3;
    public String F3;
    public boolean G3;
    public boolean H3;
    public String I3;
    public boolean J3;
    public final Writer s;
    public int[] x = new int[32];
    public int y = 0;

    static {
        for (int i2 = 0; i2 <= 31; i2++) {
            f3038c[i2] = String.format("\\u%04x", Integer.valueOf(i2));
        }
        String[] strArr = f3038c;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        String[] strArr2 = (String[]) strArr.clone();
        f3039d = strArr2;
        strArr2[60] = "\\u003c";
        strArr2[62] = "\\u003e";
        strArr2[38] = "\\u0026";
        strArr2[61] = "\\u003d";
        strArr2[39] = "\\u0027";
    }

    public b(Writer writer) {
        S(6);
        this.F3 = ":";
        this.J3 = true;
        Objects.requireNonNull(writer, "out == null");
        this.s = writer;
    }

    public final b H(int i2, int i3, String str) throws IOException {
        int iR = R();
        if (iR != i3 && iR != i2) {
            throw new IllegalStateException("Nesting problem.");
        }
        if (this.I3 != null) {
            throw new IllegalStateException("Dangling name: " + this.I3);
        }
        this.y--;
        if (iR == i3) {
            O();
        }
        this.s.write(str);
        return this;
    }

    public b I() throws IOException {
        return H(1, 2, "]");
    }

    public b J() throws IOException {
        return H(3, 5, "}");
    }

    public final boolean K() {
        return this.J3;
    }

    public final boolean L() {
        return this.H3;
    }

    public boolean M() {
        return this.G3;
    }

    public b N(String str) throws IOException {
        Objects.requireNonNull(str, "name == null");
        if (this.I3 != null) {
            throw new IllegalStateException();
        }
        if (this.y == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.I3 = str;
        return this;
    }

    public final void O() throws IOException {
        if (this.E3 == null) {
            return;
        }
        this.s.write("\n");
        int i2 = this.y;
        for (int i3 = 1; i3 < i2; i3++) {
            this.s.write(this.E3);
        }
    }

    public b P() throws IOException {
        if (this.I3 != null) {
            if (!this.J3) {
                this.I3 = null;
                return this;
            }
            e0();
        }
        d();
        this.s.write("null");
        return this;
    }

    public final b Q(int i2, String str) throws IOException {
        d();
        S(i2);
        this.s.write(str);
        return this;
    }

    public final int R() {
        int i2 = this.y;
        if (i2 != 0) {
            return this.x[i2 - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    public final void S(int i2) {
        int i3 = this.y;
        int[] iArr = this.x;
        if (i3 == iArr.length) {
            int[] iArr2 = new int[i3 * 2];
            System.arraycopy(iArr, 0, iArr2, 0, i3);
            this.x = iArr2;
        }
        int[] iArr3 = this.x;
        int i4 = this.y;
        this.y = i4 + 1;
        iArr3[i4] = i2;
    }

    public final void T(int i2) {
        this.x[this.y - 1] = i2;
    }

    public final void U(boolean z) {
        this.H3 = z;
    }

    public final void V(String str) {
        if (str.length() == 0) {
            this.E3 = null;
            this.F3 = ":";
        } else {
            this.E3 = str;
            this.F3 = ": ";
        }
    }

    public final void W(boolean z) {
        this.G3 = z;
    }

    public final void X(boolean z) {
        this.J3 = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void Y(java.lang.String r9) throws java.io.IOException {
        /*
            r8 = this;
            boolean r0 = r8.H3
            if (r0 == 0) goto L7
            java.lang.String[] r0 = c.b.a.v.b.f3039d
            goto L9
        L7:
            java.lang.String[] r0 = c.b.a.v.b.f3038c
        L9:
            java.io.Writer r1 = r8.s
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
            java.io.Writer r6 = r8.s
            int r7 = r3 - r4
            r6.write(r9, r4, r7)
        L3b:
            java.io.Writer r4 = r8.s
            r4.write(r5)
            int r4 = r3 + 1
        L42:
            int r3 = r3 + 1
            goto L16
        L45:
            if (r4 >= r1) goto L4d
            java.io.Writer r0 = r8.s
            int r1 = r1 - r4
            r0.write(r9, r4, r1)
        L4d:
            java.io.Writer r9 = r8.s
            r9.write(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.b.a.v.b.Y(java.lang.String):void");
    }

    public b Z(long j) throws IOException {
        e0();
        d();
        this.s.write(Long.toString(j));
        return this;
    }

    public b a0(Boolean bool) throws IOException {
        if (bool == null) {
            return P();
        }
        e0();
        d();
        this.s.write(bool.booleanValue() ? "true" : "false");
        return this;
    }

    public b b0(Number number) throws IOException {
        if (number == null) {
            return P();
        }
        e0();
        String string = number.toString();
        if (this.G3 || !(string.equals("-Infinity") || string.equals("Infinity") || string.equals("NaN"))) {
            d();
            this.s.append((CharSequence) string);
            return this;
        }
        throw new IllegalArgumentException("Numeric values must be finite, but was " + number);
    }

    public final void c() throws IOException {
        int iR = R();
        if (iR == 5) {
            this.s.write(44);
        } else if (iR != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        O();
        T(4);
    }

    public b c0(String str) throws IOException {
        if (str == null) {
            return P();
        }
        e0();
        d();
        Y(str);
        return this;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.s.close();
        int i2 = this.y;
        if (i2 > 1 || (i2 == 1 && this.x[i2 - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.y = 0;
    }

    public final void d() throws IOException {
        int iR = R();
        if (iR == 1) {
            T(2);
            O();
            return;
        }
        if (iR == 2) {
            this.s.append(',');
            O();
        } else {
            if (iR == 4) {
                this.s.append((CharSequence) this.F3);
                T(5);
                return;
            }
            if (iR != 6) {
                if (iR != 7) {
                    throw new IllegalStateException("Nesting problem.");
                }
                if (!this.G3) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
            }
            T(7);
        }
    }

    public b d0(boolean z) throws IOException {
        e0();
        d();
        this.s.write(z ? "true" : "false");
        return this;
    }

    public final void e0() throws IOException {
        if (this.I3 != null) {
            c();
            Y(this.I3);
            this.I3 = null;
        }
    }

    public void flush() throws IOException {
        if (this.y == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.s.flush();
    }

    public b s() throws IOException {
        e0();
        return Q(1, "[");
    }

    public b v() throws IOException {
        e0();
        return Q(3, "{");
    }
}
