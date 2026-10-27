package b.c.a.t;

import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import java.util.Objects;

/* JADX INFO: compiled from: JsonWriter.java */
/* JADX INFO: loaded from: classes3.dex */
public class b implements Closeable, Flushable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String[] f2328a = new String[128];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String[] f2329b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Writer f2330c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int[] f2331d = new int[32];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2332e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f2333f;
    public String g;
    public boolean h;
    public boolean i;
    public String j;
    public boolean k;

    static {
        for (int i = 0; i <= 31; i++) {
            f2328a[i] = String.format("\\u%04x", Integer.valueOf(i));
        }
        String[] strArr = f2328a;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        String[] strArr2 = (String[]) strArr.clone();
        f2329b = strArr2;
        strArr2[60] = "\\u003c";
        strArr2[62] = "\\u003e";
        strArr2[38] = "\\u0026";
        strArr2[61] = "\\u003d";
        strArr2[39] = "\\u0027";
    }

    public b(Writer writer) {
        v(6);
        this.g = ":";
        this.k = true;
        Objects.requireNonNull(writer, "out == null");
        this.f2330c = writer;
    }

    public final void A(boolean z) {
        this.k = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void B(java.lang.String r9) throws java.io.IOException {
        /*
            r8 = this;
            boolean r0 = r8.i
            if (r0 == 0) goto L7
            java.lang.String[] r0 = b.c.a.t.b.f2329b
            goto L9
        L7:
            java.lang.String[] r0 = b.c.a.t.b.f2328a
        L9:
            java.io.Writer r1 = r8.f2330c
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
            java.io.Writer r6 = r8.f2330c
            int r7 = r3 - r4
            r6.write(r9, r4, r7)
        L3b:
            java.io.Writer r4 = r8.f2330c
            r4.write(r5)
            int r4 = r3 + 1
        L42:
            int r3 = r3 + 1
            goto L16
        L45:
            if (r4 >= r1) goto L4d
            java.io.Writer r0 = r8.f2330c
            int r1 = r1 - r4
            r0.write(r9, r4, r1)
        L4d:
            java.io.Writer r9 = r8.f2330c
            r9.write(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: b.c.a.t.b.B(java.lang.String):void");
    }

    public b C(long j) throws IOException {
        J();
        c();
        this.f2330c.write(Long.toString(j));
        return this;
    }

    public b E(Boolean bool) throws IOException {
        if (bool == null) {
            return s();
        }
        J();
        c();
        this.f2330c.write(bool.booleanValue() ? "true" : "false");
        return this;
    }

    public b F(Number number) throws IOException {
        if (number == null) {
            return s();
        }
        J();
        String string = number.toString();
        if (this.h || !(string.equals("-Infinity") || string.equals("Infinity") || string.equals("NaN"))) {
            c();
            this.f2330c.append((CharSequence) string);
            return this;
        }
        throw new IllegalArgumentException("Numeric values must be finite, but was " + number);
    }

    public b G(String str) throws IOException {
        if (str == null) {
            return s();
        }
        J();
        c();
        B(str);
        return this;
    }

    public b H(boolean z) throws IOException {
        J();
        c();
        this.f2330c.write(z ? "true" : "false");
        return this;
    }

    public final void J() throws IOException {
        if (this.j != null) {
            a();
            B(this.j);
            this.j = null;
        }
    }

    public final void a() throws IOException {
        int iU = u();
        if (iU == 5) {
            this.f2330c.write(44);
        } else if (iU != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        q();
        w(4);
    }

    public final void c() throws IOException {
        int iU = u();
        if (iU == 1) {
            w(2);
            q();
            return;
        }
        if (iU == 2) {
            this.f2330c.append(',');
            q();
        } else {
            if (iU == 4) {
                this.f2330c.append((CharSequence) this.g);
                w(5);
                return;
            }
            if (iU != 6) {
                if (iU != 7) {
                    throw new IllegalStateException("Nesting problem.");
                }
                if (!this.h) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
            }
            w(7);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f2330c.close();
        int i = this.f2332e;
        if (i > 1 || (i == 1 && this.f2331d[i - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.f2332e = 0;
    }

    public b e() throws IOException {
        J();
        return t(1, "[");
    }

    public b f() throws IOException {
        J();
        return t(3, "{");
    }

    public void flush() throws IOException {
        if (this.f2332e == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.f2330c.flush();
    }

    public final b g(int i, int i2, String str) throws IOException {
        int iU = u();
        if (iU != i2 && iU != i) {
            throw new IllegalStateException("Nesting problem.");
        }
        if (this.j != null) {
            throw new IllegalStateException("Dangling name: " + this.j);
        }
        this.f2332e--;
        if (iU == i2) {
            q();
        }
        this.f2330c.write(str);
        return this;
    }

    public b h() throws IOException {
        return g(1, 2, "]");
    }

    public b i() throws IOException {
        return g(3, 5, "}");
    }

    public final boolean j() {
        return this.k;
    }

    public final boolean k() {
        return this.i;
    }

    public boolean l() {
        return this.h;
    }

    public b n(String str) throws IOException {
        if (str == null) {
            return s();
        }
        J();
        c();
        this.f2330c.append((CharSequence) str);
        return this;
    }

    public b o(String str) throws IOException {
        Objects.requireNonNull(str, "name == null");
        if (this.j != null) {
            throw new IllegalStateException();
        }
        if (this.f2332e == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.j = str;
        return this;
    }

    public final void q() throws IOException {
        if (this.f2333f == null) {
            return;
        }
        this.f2330c.write(BydAdbHelper.COMMAND_LINE_END);
        int i = this.f2332e;
        for (int i2 = 1; i2 < i; i2++) {
            this.f2330c.write(this.f2333f);
        }
    }

    public b s() throws IOException {
        if (this.j != null) {
            if (!this.k) {
                this.j = null;
                return this;
            }
            J();
        }
        c();
        this.f2330c.write("null");
        return this;
    }

    public final b t(int i, String str) throws IOException {
        c();
        v(i);
        this.f2330c.write(str);
        return this;
    }

    public final int u() {
        int i = this.f2332e;
        if (i != 0) {
            return this.f2331d[i - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    public final void v(int i) {
        int i2 = this.f2332e;
        int[] iArr = this.f2331d;
        if (i2 == iArr.length) {
            int[] iArr2 = new int[i2 * 2];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            this.f2331d = iArr2;
        }
        int[] iArr3 = this.f2331d;
        int i3 = this.f2332e;
        this.f2332e = i3 + 1;
        iArr3[i3] = i;
    }

    public final void w(int i) {
        this.f2331d[this.f2332e - 1] = i;
    }

    public final void x(boolean z) {
        this.i = z;
    }

    public final void y(String str) {
        if (str.length() == 0) {
            this.f2333f = null;
            this.g = ":";
        } else {
            this.f2333f = str;
            this.g = ": ";
        }
    }

    public final void z(boolean z) {
        this.h = z;
    }
}
