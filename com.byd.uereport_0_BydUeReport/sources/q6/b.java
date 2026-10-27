package q6;

import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import java.util.Objects;

/* JADX INFO: compiled from: JsonWriter.java */
/* JADX INFO: loaded from: classes2.dex */
public class b implements Closeable, Flushable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String[] f7467d = new String[128];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String[] f7468e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Writer f7469f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int[] f7470g = new int[32];

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f7471h = 0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f7472i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public String f7473j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f7474k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f7475l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f7476m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f7477n;

    static {
        for (int i8 = 0; i8 <= 31; i8++) {
            f7467d[i8] = String.format("\\u%04x", Integer.valueOf(i8));
        }
        String[] strArr = f7467d;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        String[] strArr2 = (String[]) strArr.clone();
        f7468e = strArr2;
        strArr2[60] = "\\u003c";
        strArr2[62] = "\\u003e";
        strArr2[38] = "\\u0026";
        strArr2[61] = "\\u003d";
        strArr2[39] = "\\u0027";
    }

    public b(Writer writer) {
        F(6);
        this.f7473j = ":";
        this.f7477n = true;
        Objects.requireNonNull(writer, "out == null");
        this.f7469f = writer;
    }

    public b B() throws IOException {
        if (this.f7476m != null) {
            if (!this.f7477n) {
                this.f7476m = null;
                return this;
            }
            Z();
        }
        g();
        this.f7469f.write("null");
        return this;
    }

    public final b C(int i8, String str) throws IOException {
        g();
        F(i8);
        this.f7469f.write(str);
        return this;
    }

    public final int E() {
        int i8 = this.f7471h;
        if (i8 != 0) {
            return this.f7470g[i8 - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    public final void F(int i8) {
        int i9 = this.f7471h;
        int[] iArr = this.f7470g;
        if (i9 == iArr.length) {
            int[] iArr2 = new int[i9 * 2];
            System.arraycopy(iArr, 0, iArr2, 0, i9);
            this.f7470g = iArr2;
        }
        int[] iArr3 = this.f7470g;
        int i10 = this.f7471h;
        this.f7471h = i10 + 1;
        iArr3[i10] = i8;
    }

    public final void I(int i8) {
        this.f7470g[this.f7471h - 1] = i8;
    }

    public final void L(boolean z7) {
        this.f7475l = z7;
    }

    public final void M(String str) {
        if (str.length() == 0) {
            this.f7472i = null;
            this.f7473j = ":";
        } else {
            this.f7472i = str;
            this.f7473j = ": ";
        }
    }

    public final void N(boolean z7) {
        this.f7474k = z7;
    }

    public final void O(boolean z7) {
        this.f7477n = z7;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void P(java.lang.String r9) throws java.io.IOException {
        /*
            r8 = this;
            boolean r0 = r8.f7475l
            if (r0 == 0) goto L7
            java.lang.String[] r0 = q6.b.f7468e
            goto L9
        L7:
            java.lang.String[] r0 = q6.b.f7467d
        L9:
            java.io.Writer r1 = r8.f7469f
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
            java.io.Writer r6 = r8.f7469f
            int r7 = r3 - r4
            r6.write(r9, r4, r7)
        L3b:
            java.io.Writer r4 = r8.f7469f
            r4.write(r5)
            int r4 = r3 + 1
        L42:
            int r3 = r3 + 1
            goto L16
        L45:
            if (r4 >= r1) goto L4d
            java.io.Writer r0 = r8.f7469f
            int r1 = r1 - r4
            r0.write(r9, r4, r1)
        L4d:
            java.io.Writer r9 = r8.f7469f
            r9.write(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: q6.b.P(java.lang.String):void");
    }

    public b R(long j8) throws IOException {
        Z();
        g();
        this.f7469f.write(Long.toString(j8));
        return this;
    }

    public b T(Boolean bool) throws IOException {
        if (bool == null) {
            return B();
        }
        Z();
        g();
        this.f7469f.write(bool.booleanValue() ? "true" : "false");
        return this;
    }

    public b V(Number number) throws IOException {
        if (number == null) {
            return B();
        }
        Z();
        String string = number.toString();
        if (this.f7474k || !(string.equals("-Infinity") || string.equals("Infinity") || string.equals("NaN"))) {
            g();
            this.f7469f.append((CharSequence) string);
            return this;
        }
        throw new IllegalArgumentException("Numeric values must be finite, but was " + number);
    }

    public b X(String str) throws IOException {
        if (str == null) {
            return B();
        }
        Z();
        g();
        P(str);
        return this;
    }

    public b Y(boolean z7) throws IOException {
        Z();
        g();
        this.f7469f.write(z7 ? "true" : "false");
        return this;
    }

    public final void Z() throws IOException {
        if (this.f7476m != null) {
            e();
            P(this.f7476m);
            this.f7476m = null;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f7469f.close();
        int i8 = this.f7471h;
        if (i8 > 1 || (i8 == 1 && this.f7470g[i8 - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.f7471h = 0;
    }

    public final void e() throws IOException {
        int iE = E();
        if (iE == 5) {
            this.f7469f.write(44);
        } else if (iE != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        x();
        I(4);
    }

    public void flush() throws IOException {
        if (this.f7471h == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.f7469f.flush();
    }

    public final void g() throws IOException {
        int iE = E();
        if (iE == 1) {
            I(2);
            x();
            return;
        }
        if (iE == 2) {
            this.f7469f.append(',');
            x();
        } else {
            if (iE == 4) {
                this.f7469f.append((CharSequence) this.f7473j);
                I(5);
                return;
            }
            if (iE != 6) {
                if (iE != 7) {
                    throw new IllegalStateException("Nesting problem.");
                }
                if (!this.f7474k) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
            }
            I(7);
        }
    }

    public b i() throws IOException {
        Z();
        return C(1, "[");
    }

    public b j() throws IOException {
        Z();
        return C(3, "{");
    }

    public final b m(int i8, int i9, String str) throws IOException {
        int iE = E();
        if (iE != i9 && iE != i8) {
            throw new IllegalStateException("Nesting problem.");
        }
        if (this.f7476m != null) {
            throw new IllegalStateException("Dangling name: " + this.f7476m);
        }
        this.f7471h--;
        if (iE == i9) {
            x();
        }
        this.f7469f.write(str);
        return this;
    }

    public b n() throws IOException {
        return m(1, 2, "]");
    }

    public b o() throws IOException {
        return m(3, 5, "}");
    }

    public final boolean q() {
        return this.f7477n;
    }

    public final boolean r() {
        return this.f7475l;
    }

    public boolean t() {
        return this.f7474k;
    }

    public b v(String str) throws IOException {
        if (str == null) {
            return B();
        }
        Z();
        g();
        this.f7469f.append((CharSequence) str);
        return this;
    }

    public b w(String str) throws IOException {
        Objects.requireNonNull(str, "name == null");
        if (this.f7476m != null) {
            throw new IllegalStateException();
        }
        if (this.f7471h == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.f7476m = str;
        return this;
    }

    public final void x() throws IOException {
        if (this.f7472i == null) {
            return;
        }
        this.f7469f.write(BydAdbHelper.COMMAND_LINE_END);
        int i8 = this.f7471h;
        for (int i9 = 1; i9 < i8; i9++) {
            this.f7469f.write(this.f7472i);
        }
    }
}
