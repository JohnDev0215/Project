package r5;

import j5.C1551i;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

/* JADX INFO: renamed from: r5.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C2298b implements Closeable, Flushable {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final Pattern f21858w = Pattern.compile("-?(?:0|[1-9][0-9]*)(?:\\.[0-9]+)?(?:[eE][-+]?[0-9]+)?");

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final String[] f21859x = new String[128];

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final String[] f21860y;
    public final Writer l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int[] f21861m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f21862n;
    public C1551i o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String f21863p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public String f21864q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f21865r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f21866s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f21867t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public String f21868u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f21869v;

    static {
        for (int i7 = 0; i7 <= 31; i7++) {
            f21859x[i7] = String.format("\\u%04x", Integer.valueOf(i7));
        }
        String[] strArr = f21859x;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        String[] strArr2 = (String[]) strArr.clone();
        f21860y = strArr2;
        strArr2[60] = "\\u003c";
        strArr2[62] = "\\u003e";
        strArr2[38] = "\\u0026";
        strArr2[61] = "\\u003d";
        strArr2[39] = "\\u0027";
    }

    public C2298b(Writer writer) {
        int[] iArr = new int[32];
        this.f21861m = iArr;
        this.f21862n = 0;
        if (iArr.length == 0) {
            this.f21861m = Arrays.copyOf(iArr, 0);
        }
        int[] iArr2 = this.f21861m;
        int i7 = this.f21862n;
        this.f21862n = i7 + 1;
        iArr2[i7] = 6;
        this.f21866s = 2;
        this.f21869v = true;
        Objects.requireNonNull(writer, "out == null");
        this.l = writer;
        z(C1551i.d);
    }

    public final void C(int i7) {
        if (i7 == 0) {
            throw null;
        }
        this.f21866s = i7;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void F(java.lang.String r8) throws java.io.IOException {
        /*
            r7 = this;
            boolean r0 = r7.f21867t
            if (r0 == 0) goto L7
            java.lang.String[] r0 = r5.C2298b.f21860y
            goto L9
        L7:
            java.lang.String[] r0 = r5.C2298b.f21859x
        L9:
            java.io.Writer r7 = r7.l
            r1 = 34
            r7.write(r1)
            int r2 = r8.length()
            r3 = 0
            r4 = r3
        L16:
            if (r3 >= r2) goto L41
            char r5 = r8.charAt(r3)
            r6 = 128(0x80, float:1.8E-43)
            if (r5 >= r6) goto L25
            r5 = r0[r5]
            if (r5 != 0) goto L32
            goto L3e
        L25:
            r6 = 8232(0x2028, float:1.1535E-41)
            if (r5 != r6) goto L2c
            java.lang.String r5 = "\\u2028"
            goto L32
        L2c:
            r6 = 8233(0x2029, float:1.1537E-41)
            if (r5 != r6) goto L3e
            java.lang.String r5 = "\\u2029"
        L32:
            if (r4 >= r3) goto L39
            int r6 = r3 - r4
            r7.write(r8, r4, r6)
        L39:
            r7.write(r5)
            int r4 = r3 + 1
        L3e:
            int r3 = r3 + 1
            goto L16
        L41:
            if (r4 >= r2) goto L47
            int r2 = r2 - r4
            r7.write(r8, r4, r2)
        L47:
            r7.write(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: r5.C2298b.F(java.lang.String):void");
    }

    public void G(double d) throws IOException {
        N();
        if (this.f21866s == 1 || !(Double.isNaN(d) || Double.isInfinite(d))) {
            b();
            this.l.append((CharSequence) Double.toString(d));
        } else {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + d);
        }
    }

    public void I(long j3) throws IOException {
        N();
        b();
        this.l.write(Long.toString(j3));
    }

    public void K(Number number) throws IOException {
        if (number == null) {
            v();
            return;
        }
        N();
        String string = number.toString();
        if (!string.equals("-Infinity") && !string.equals("Infinity") && !string.equals("NaN")) {
            Class<?> cls = number.getClass();
            if (cls != Integer.class && cls != Long.class && cls != Double.class && cls != Float.class && cls != Byte.class && cls != Short.class && cls != BigDecimal.class && cls != BigInteger.class && cls != AtomicInteger.class && cls != AtomicLong.class && !f21858w.matcher(string).matches()) {
                throw new IllegalArgumentException("String created by " + cls + " is not a valid JSON number: " + string);
            }
        } else if (this.f21866s != 1) {
            throw new IllegalArgumentException("Numeric values must be finite, but was ".concat(string));
        }
        b();
        this.l.append((CharSequence) string);
    }

    public void L(String str) throws IOException {
        if (str == null) {
            v();
            return;
        }
        N();
        b();
        F(str);
    }

    public void M(boolean z6) throws IOException {
        N();
        b();
        this.l.write(z6 ? "true" : "false");
    }

    public final void N() throws IOException {
        if (this.f21868u != null) {
            int iY = y();
            if (iY == 5) {
                this.l.write(this.f21864q);
            } else if (iY != 3) {
                throw new IllegalStateException("Nesting problem.");
            }
            q();
            this.f21861m[this.f21862n - 1] = 4;
            F(this.f21868u);
            this.f21868u = null;
        }
    }

    public final void b() throws IOException {
        int iY = y();
        if (iY == 1) {
            this.f21861m[this.f21862n - 1] = 2;
            q();
            return;
        }
        Writer writer = this.l;
        if (iY == 2) {
            writer.append((CharSequence) this.f21864q);
            q();
        } else {
            if (iY == 4) {
                writer.append((CharSequence) this.f21863p);
                this.f21861m[this.f21862n - 1] = 5;
                return;
            }
            if (iY != 6) {
                if (iY != 7) {
                    throw new IllegalStateException("Nesting problem.");
                }
                if (this.f21866s != 1) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
            }
            this.f21861m[this.f21862n - 1] = 7;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.l.close();
        int i7 = this.f21862n;
        if (i7 > 1 || (i7 == 1 && this.f21861m[i7 - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.f21862n = 0;
    }

    @Override // java.io.Flushable
    public void flush() throws IOException {
        if (this.f21862n == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.l.flush();
    }

    public void g() throws IOException {
        N();
        b();
        int i7 = this.f21862n;
        int[] iArr = this.f21861m;
        if (i7 == iArr.length) {
            this.f21861m = Arrays.copyOf(iArr, i7 * 2);
        }
        int[] iArr2 = this.f21861m;
        int i10 = this.f21862n;
        this.f21862n = i10 + 1;
        iArr2[i10] = 1;
        this.l.write(91);
    }

    public void i() throws IOException {
        N();
        b();
        int i7 = this.f21862n;
        int[] iArr = this.f21861m;
        if (i7 == iArr.length) {
            this.f21861m = Arrays.copyOf(iArr, i7 * 2);
        }
        int[] iArr2 = this.f21861m;
        int i10 = this.f21862n;
        this.f21862n = i10 + 1;
        iArr2[i10] = 3;
        this.l.write(123);
    }

    public final void j(char c, int i7, int i10) throws IOException {
        int iY = y();
        if (iY != i10 && iY != i7) {
            throw new IllegalStateException("Nesting problem.");
        }
        if (this.f21868u != null) {
            throw new IllegalStateException("Dangling name: " + this.f21868u);
        }
        this.f21862n--;
        if (iY == i10) {
            q();
        }
        this.l.write(c);
    }

    public void k() throws IOException {
        j(']', 1, 2);
    }

    public void m() throws IOException {
        j('}', 3, 5);
    }

    public void o(String str) {
        Objects.requireNonNull(str, "name == null");
        if (this.f21868u != null) {
            throw new IllegalStateException("Already wrote a name, expecting a value.");
        }
        int iY = y();
        if (iY != 3 && iY != 5) {
            throw new IllegalStateException("Please begin an object before writing a name.");
        }
        this.f21868u = str;
    }

    public final void q() throws IOException {
        if (this.f21865r) {
            return;
        }
        String str = this.o.f17015a;
        Writer writer = this.l;
        writer.write(str);
        int i7 = this.f21862n;
        for (int i10 = 1; i10 < i7; i10++) {
            writer.write(this.o.f17016b);
        }
    }

    public C2298b v() throws IOException {
        if (this.f21868u != null) {
            if (!this.f21869v) {
                this.f21868u = null;
                return this;
            }
            N();
        }
        b();
        this.l.write("null");
        return this;
    }

    public final int y() {
        int i7 = this.f21862n;
        if (i7 != 0) {
            return this.f21861m[i7 - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    public final void z(C1551i c1551i) {
        Objects.requireNonNull(c1551i);
        this.o = c1551i;
        this.f21864q = ",";
        if (c1551i.c) {
            this.f21863p = ": ";
            if (c1551i.f17015a.isEmpty()) {
                this.f21864q = ", ";
            }
        } else {
            this.f21863p = ":";
        }
        this.f21865r = this.o.f17015a.isEmpty() && this.o.f17016b.isEmpty();
    }
}
