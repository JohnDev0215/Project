package b.b.a.t;

import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import java.util.Objects;

/* JADX INFO: compiled from: JsonWriter.java */
/* JADX INFO: loaded from: classes2.dex */
public class b implements Closeable, Flushable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String[] f502b = new String[128];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String[] f503c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Writer f504d;
    public int[] e = new int[32];
    public int f = 0;
    public String g;
    public String h;
    public boolean i;
    public boolean j;
    public String k;
    public boolean l;

    static {
        for (int i = 0; i <= 31; i++) {
            f502b[i] = String.format("\\u%04x", Integer.valueOf(i));
        }
        String[] strArr = f502b;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        String[] strArr2 = (String[]) strArr.clone();
        f503c = strArr2;
        strArr2[60] = "\\u003c";
        strArr2[62] = "\\u003e";
        strArr2[38] = "\\u0026";
        strArr2[61] = "\\u003d";
        strArr2[39] = "\\u0027";
    }

    public b(Writer writer) {
        q(6);
        this.h = ":";
        this.l = true;
        Objects.requireNonNull(writer, "out == null");
        this.f504d = writer;
    }

    public b A(String str) throws IOException {
        if (str == null) {
            return n();
        }
        C();
        b();
        w(str);
        return this;
    }

    public b B(boolean z) throws IOException {
        C();
        b();
        this.f504d.write(z ? "true" : "false");
        return this;
    }

    public final void C() throws IOException {
        if (this.k != null) {
            a();
            w(this.k);
            this.k = null;
        }
    }

    public final void a() throws IOException {
        int iP = p();
        if (iP == 5) {
            this.f504d.write(44);
        } else if (iP != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        m();
        r(4);
    }

    public final void b() throws IOException {
        int iP = p();
        if (iP == 1) {
            r(2);
            m();
            return;
        }
        if (iP == 2) {
            this.f504d.append(',');
            m();
        } else {
            if (iP == 4) {
                this.f504d.append((CharSequence) this.h);
                r(5);
                return;
            }
            if (iP != 6) {
                if (iP != 7) {
                    throw new IllegalStateException("Nesting problem.");
                }
                if (!this.i) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
            }
            r(7);
        }
    }

    public b c() throws IOException {
        C();
        return o(1, "[");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f504d.close();
        int i = this.f;
        if (i > 1 || (i == 1 && this.e[i - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.f = 0;
    }

    public b d() throws IOException {
        C();
        return o(3, "{");
    }

    public final b e(int i, int i2, String str) throws IOException {
        int iP = p();
        if (iP != i2 && iP != i) {
            throw new IllegalStateException("Nesting problem.");
        }
        if (this.k != null) {
            throw new IllegalStateException("Dangling name: " + this.k);
        }
        this.f--;
        if (iP == i2) {
            m();
        }
        this.f504d.write(str);
        return this;
    }

    public b f() throws IOException {
        return e(1, 2, "]");
    }

    public void flush() throws IOException {
        if (this.f == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.f504d.flush();
    }

    public b g() throws IOException {
        return e(3, 5, "}");
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
        if (str == null) {
            return n();
        }
        C();
        b();
        this.f504d.append((CharSequence) str);
        return this;
    }

    public b l(String str) throws IOException {
        Objects.requireNonNull(str, "name == null");
        if (this.k != null) {
            throw new IllegalStateException();
        }
        if (this.f == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.k = str;
        return this;
    }

    public final void m() throws IOException {
        if (this.g == null) {
            return;
        }
        this.f504d.write(BydAdbHelper.COMMAND_LINE_END);
        int i = this.f;
        for (int i2 = 1; i2 < i; i2++) {
            this.f504d.write(this.g);
        }
    }

    public b n() throws IOException {
        if (this.k != null) {
            if (!this.l) {
                this.k = null;
                return this;
            }
            C();
        }
        b();
        this.f504d.write("null");
        return this;
    }

    public final b o(int i, String str) throws IOException {
        b();
        q(i);
        this.f504d.write(str);
        return this;
    }

    public final int p() {
        int i = this.f;
        if (i != 0) {
            return this.e[i - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    public final void q(int i) {
        int i2 = this.f;
        int[] iArr = this.e;
        if (i2 == iArr.length) {
            int[] iArr2 = new int[i2 * 2];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            this.e = iArr2;
        }
        int[] iArr3 = this.e;
        int i3 = this.f;
        this.f = i3 + 1;
        iArr3[i3] = i;
    }

    public final void r(int i) {
        this.e[this.f - 1] = i;
    }

    public final void s(boolean z) {
        this.j = z;
    }

    public final void t(String str) {
        if (str.length() == 0) {
            this.g = null;
            this.h = ":";
        } else {
            this.g = str;
            this.h = ": ";
        }
    }

    public final void u(boolean z) {
        this.i = z;
    }

    public final void v(boolean z) {
        this.l = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void w(java.lang.String r9) throws java.io.IOException {
        /*
            r8 = this;
            boolean r0 = r8.j
            if (r0 == 0) goto L7
            java.lang.String[] r0 = b.b.a.t.b.f503c
            goto L9
        L7:
            java.lang.String[] r0 = b.b.a.t.b.f502b
        L9:
            java.io.Writer r1 = r8.f504d
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
            java.io.Writer r6 = r8.f504d
            int r7 = r3 - r4
            r6.write(r9, r4, r7)
        L3b:
            java.io.Writer r4 = r8.f504d
            r4.write(r5)
            int r4 = r3 + 1
        L42:
            int r3 = r3 + 1
            goto L16
        L45:
            if (r4 >= r1) goto L4d
            java.io.Writer r0 = r8.f504d
            int r1 = r1 - r4
            r0.write(r9, r4, r1)
        L4d:
            java.io.Writer r9 = r8.f504d
            r9.write(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.a.t.b.w(java.lang.String):void");
    }

    public b x(long j) throws IOException {
        C();
        b();
        this.f504d.write(Long.toString(j));
        return this;
    }

    public b y(Boolean bool) throws IOException {
        if (bool == null) {
            return n();
        }
        C();
        b();
        this.f504d.write(bool.booleanValue() ? "true" : "false");
        return this;
    }

    public b z(Number number) throws IOException {
        if (number == null) {
            return n();
        }
        C();
        String string = number.toString();
        if (this.i || !(string.equals("-Infinity") || string.equals("Infinity") || string.equals("NaN"))) {
            b();
            this.f504d.append((CharSequence) string);
            return this;
        }
        throw new IllegalArgumentException("Numeric values must be finite, but was " + number);
    }
}
