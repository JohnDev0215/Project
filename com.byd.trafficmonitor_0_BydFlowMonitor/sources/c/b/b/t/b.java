package c.b.b.t;

import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import java.util.Objects;

/* JADX INFO: compiled from: JsonWriter.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements Closeable, Flushable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String[] f4309b = new String[128];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String[] f4310c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Writer f4311d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int[] f4312e = new int[32];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4313f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f4314g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f4315h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f4316i;
    public boolean j;
    public String k;
    public boolean l;

    static {
        for (int i2 = 0; i2 <= 31; i2++) {
            f4309b[i2] = String.format("\\u%04x", Integer.valueOf(i2));
        }
        String[] strArr = f4309b;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        String[] strArr2 = (String[]) strArr.clone();
        f4310c = strArr2;
        strArr2[60] = "\\u003c";
        strArr2[62] = "\\u003e";
        strArr2[38] = "\\u0026";
        strArr2[61] = "\\u003d";
        strArr2[39] = "\\u0027";
    }

    public b(Writer writer) {
        c0(6);
        this.f4315h = ":";
        this.l = true;
        Objects.requireNonNull(writer, "out == null");
        this.f4311d = writer;
    }

    public b O() throws IOException {
        o0();
        return a0(1, "[");
    }

    public b P() throws IOException {
        o0();
        return a0(3, "{");
    }

    public final b Q(int i2, int i3, String str) throws IOException {
        int iB0 = b0();
        if (iB0 != i3 && iB0 != i2) {
            throw new IllegalStateException("Nesting problem.");
        }
        if (this.k != null) {
            throw new IllegalStateException("Dangling name: " + this.k);
        }
        this.f4313f--;
        if (iB0 == i3) {
            Y();
        }
        this.f4311d.write(str);
        return this;
    }

    public b R() throws IOException {
        return Q(1, 2, "]");
    }

    public b S() throws IOException {
        return Q(3, 5, "}");
    }

    public final boolean T() {
        return this.l;
    }

    public final boolean U() {
        return this.j;
    }

    public boolean V() {
        return this.f4316i;
    }

    public b W(String str) throws IOException {
        if (str == null) {
            return Z();
        }
        o0();
        z();
        this.f4311d.append((CharSequence) str);
        return this;
    }

    public b X(String str) throws IOException {
        Objects.requireNonNull(str, "name == null");
        if (this.k != null) {
            throw new IllegalStateException();
        }
        if (this.f4313f == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.k = str;
        return this;
    }

    public final void Y() throws IOException {
        if (this.f4314g == null) {
            return;
        }
        this.f4311d.write(BydAdbHelper.COMMAND_LINE_END);
        int i2 = this.f4313f;
        for (int i3 = 1; i3 < i2; i3++) {
            this.f4311d.write(this.f4314g);
        }
    }

    public b Z() throws IOException {
        if (this.k != null) {
            if (!this.l) {
                this.k = null;
                return this;
            }
            o0();
        }
        z();
        this.f4311d.write("null");
        return this;
    }

    public final b a0(int i2, String str) throws IOException {
        z();
        c0(i2);
        this.f4311d.write(str);
        return this;
    }

    public final int b0() {
        int i2 = this.f4313f;
        if (i2 != 0) {
            return this.f4312e[i2 - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    public final void c0(int i2) {
        int i3 = this.f4313f;
        int[] iArr = this.f4312e;
        if (i3 == iArr.length) {
            int[] iArr2 = new int[i3 * 2];
            System.arraycopy(iArr, 0, iArr2, 0, i3);
            this.f4312e = iArr2;
        }
        int[] iArr3 = this.f4312e;
        int i4 = this.f4313f;
        this.f4313f = i4 + 1;
        iArr3[i4] = i2;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f4311d.close();
        int i2 = this.f4313f;
        if (i2 > 1 || (i2 == 1 && this.f4312e[i2 - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.f4313f = 0;
    }

    public final void d0(int i2) {
        this.f4312e[this.f4313f - 1] = i2;
    }

    public final void e0(boolean z) {
        this.j = z;
    }

    public final void f0(String str) {
        if (str.length() == 0) {
            this.f4314g = null;
            this.f4315h = ":";
        } else {
            this.f4314g = str;
            this.f4315h = ": ";
        }
    }

    public void flush() throws IOException {
        if (this.f4313f == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.f4311d.flush();
    }

    public final void g0(boolean z) {
        this.f4316i = z;
    }

    public final void h0(boolean z) {
        this.l = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void i0(java.lang.String r9) throws java.io.IOException {
        /*
            r8 = this;
            boolean r0 = r8.j
            if (r0 == 0) goto L7
            java.lang.String[] r0 = c.b.b.t.b.f4310c
            goto L9
        L7:
            java.lang.String[] r0 = c.b.b.t.b.f4309b
        L9:
            java.io.Writer r1 = r8.f4311d
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
            java.io.Writer r6 = r8.f4311d
            int r7 = r3 - r4
            r6.write(r9, r4, r7)
        L3b:
            java.io.Writer r4 = r8.f4311d
            r4.write(r5)
            int r4 = r3 + 1
        L42:
            int r3 = r3 + 1
            goto L16
        L45:
            if (r4 >= r1) goto L4d
            java.io.Writer r0 = r8.f4311d
            int r1 = r1 - r4
            r0.write(r9, r4, r1)
        L4d:
            java.io.Writer r9 = r8.f4311d
            r9.write(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.b.b.t.b.i0(java.lang.String):void");
    }

    public b j0(long j) throws IOException {
        o0();
        z();
        this.f4311d.write(Long.toString(j));
        return this;
    }

    public b k0(Boolean bool) throws IOException {
        if (bool == null) {
            return Z();
        }
        o0();
        z();
        this.f4311d.write(bool.booleanValue() ? "true" : "false");
        return this;
    }

    public b l0(Number number) throws IOException {
        if (number == null) {
            return Z();
        }
        o0();
        String string = number.toString();
        if (this.f4316i || !(string.equals("-Infinity") || string.equals("Infinity") || string.equals("NaN"))) {
            z();
            this.f4311d.append((CharSequence) string);
            return this;
        }
        throw new IllegalArgumentException("Numeric values must be finite, but was " + number);
    }

    public b m0(String str) throws IOException {
        if (str == null) {
            return Z();
        }
        o0();
        z();
        i0(str);
        return this;
    }

    public b n0(boolean z) throws IOException {
        o0();
        z();
        this.f4311d.write(z ? "true" : "false");
        return this;
    }

    public final void o0() throws IOException {
        if (this.k != null) {
            w();
            i0(this.k);
            this.k = null;
        }
    }

    public final void w() throws IOException {
        int iB0 = b0();
        if (iB0 == 5) {
            this.f4311d.write(44);
        } else if (iB0 != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        Y();
        d0(4);
    }

    public final void z() throws IOException {
        int iB0 = b0();
        if (iB0 == 1) {
            d0(2);
            Y();
            return;
        }
        if (iB0 == 2) {
            this.f4311d.append(',');
            Y();
        } else {
            if (iB0 == 4) {
                this.f4311d.append((CharSequence) this.f4315h);
                d0(5);
                return;
            }
            if (iB0 != 6) {
                if (iB0 != 7) {
                    throw new IllegalStateException("Nesting problem.");
                }
                if (!this.f4316i) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
            }
            d0(7);
        }
    }
}
