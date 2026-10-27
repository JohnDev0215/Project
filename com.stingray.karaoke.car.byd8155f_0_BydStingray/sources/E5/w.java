package E5;

import java.io.IOException;
import java.util.Arrays;
import r9.C2319i;

/* JADX INFO: loaded from: classes.dex */
public final class w extends x {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final String[] f1833t = new String[128];

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final C2319i f1834r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public String f1835s;

    static {
        for (int i7 = 0; i7 <= 31; i7++) {
            f1833t[i7] = String.format("\\u%04x", Integer.valueOf(i7));
        }
        String[] strArr = f1833t;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
    }

    public w(C2319i c2319i) {
        int[] iArr = new int[32];
        this.f1836m = iArr;
        this.f1837n = new String[32];
        this.o = new int[32];
        this.f1839q = -1;
        this.f1834r = c2319i;
        this.l = 1;
        iArr[0] = 6;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void F(r9.C2319i r7, java.lang.String r8) {
        /*
            java.lang.String[] r0 = E5.w.f1833t
            r1 = 34
            r7.X(r1)
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
            r7.d0(r8, r4, r3)
        L2e:
            r7.c0(r5)
            int r4 = r3 + 1
        L33:
            int r3 = r3 + 1
            goto Ld
        L36:
            if (r4 >= r2) goto L3b
            r7.d0(r8, r4, r2)
        L3b:
            r7.X(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: E5.w.F(r9.i, java.lang.String):void");
    }

    public final void C(char c, int i7, int i10) {
        int i11;
        int i12 = this.l;
        int i13 = this.f1839q;
        if (i12 == i13 && ((i11 = this.f1836m[i12 - 1]) == i7 || i11 == i10)) {
            this.f1839q = ~i13;
            return;
        }
        y();
        int i14 = this.l;
        int[] iArr = this.f1836m;
        if (i14 == iArr.length) {
            if (i14 == 256) {
                throw new A9.k("Nesting too deep at " + j() + ": circular reference?");
            }
            this.f1836m = Arrays.copyOf(iArr, iArr.length * 2);
            String[] strArr = this.f1837n;
            this.f1837n = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
            int[] iArr2 = this.o;
            this.o = Arrays.copyOf(iArr2, iArr2.length * 2);
        }
        int[] iArr3 = this.f1836m;
        int i15 = this.l;
        this.l = i15 + 1;
        iArr3[i15] = i7;
        this.o[i15] = 0;
        this.f1834r.X(c);
    }

    public final void G() {
        if (this.f1835s != null) {
            int iO = o();
            C2319i c2319i = this.f1834r;
            if (iO == 5) {
                c2319i.X(44);
            } else if (iO != 3) {
                throw new IllegalStateException("Nesting problem.");
            }
            this.f1836m[this.l - 1] = 4;
            F(c2319i, this.f1835s);
            this.f1835s = null;
        }
    }

    @Override // E5.x
    public final w b() {
        if (this.f1838p) {
            throw new IllegalStateException("Array cannot be used as a map key in JSON at path " + j());
        }
        G();
        C('[', 1, 2);
        return this;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        int i7 = this.l;
        if (i7 > 1 || (i7 == 1 && this.f1836m[i7 - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.l = 0;
    }

    @Override // java.io.Flushable
    public final void flush() {
        if (this.l == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
    }

    @Override // E5.x
    public final w g() {
        if (this.f1838p) {
            throw new IllegalStateException("Object cannot be used as a map key in JSON at path " + j());
        }
        G();
        C('{', 3, 5);
        return this;
    }

    @Override // E5.x
    public final w i() {
        this.f1838p = false;
        z('}', 3, 5);
        return this;
    }

    @Override // E5.x
    public final w k(String str) {
        if (str == null) {
            throw new NullPointerException("name == null");
        }
        if (this.l == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        int iO = o();
        if ((iO != 3 && iO != 5) || this.f1835s != null || this.f1838p) {
            throw new IllegalStateException("Nesting problem.");
        }
        this.f1835s = str;
        this.f1837n[this.l - 1] = str;
        return this;
    }

    @Override // E5.x
    public final w m() {
        if (this.f1838p) {
            throw new IllegalStateException("null cannot be used as a map key in JSON at path " + j());
        }
        if (this.f1835s != null) {
            this.f1835s = null;
            return this;
        }
        y();
        this.f1834r.c0("null");
        int[] iArr = this.o;
        int i7 = this.l - 1;
        iArr[i7] = iArr[i7] + 1;
        return this;
    }

    @Override // E5.x
    public final w q(long j3) {
        if (this.f1838p) {
            this.f1838p = false;
            k(Long.toString(j3));
            return this;
        }
        G();
        y();
        this.f1834r.c0(Long.toString(j3));
        int[] iArr = this.o;
        int i7 = this.l - 1;
        iArr[i7] = iArr[i7] + 1;
        return this;
    }

    @Override // E5.x
    public final w v(String str) {
        if (str == null) {
            m();
            return this;
        }
        if (this.f1838p) {
            this.f1838p = false;
            k(str);
            return this;
        }
        G();
        y();
        F(this.f1834r, str);
        int[] iArr = this.o;
        int i7 = this.l - 1;
        iArr[i7] = iArr[i7] + 1;
        return this;
    }

    public final void y() {
        int iO = o();
        int i7 = 2;
        if (iO != 1) {
            C2319i c2319i = this.f1834r;
            if (iO == 2) {
                c2319i.X(44);
            } else if (iO == 4) {
                c2319i.c0(":");
                i7 = 5;
            } else {
                if (iO == 9) {
                    throw new IllegalStateException("Sink from valueSink() was not closed");
                }
                if (iO != 6) {
                    if (iO != 7) {
                        throw new IllegalStateException("Nesting problem.");
                    }
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
                i7 = 7;
            }
        }
        this.f1836m[this.l - 1] = i7;
    }

    public final void z(char c, int i7, int i10) {
        int iO = o();
        if (iO != i10 && iO != i7) {
            throw new IllegalStateException("Nesting problem.");
        }
        if (this.f1835s != null) {
            throw new IllegalStateException("Dangling name: " + this.f1835s);
        }
        int i11 = this.l;
        int i12 = ~this.f1839q;
        if (i11 == i12) {
            this.f1839q = i12;
            return;
        }
        int i13 = i11 - 1;
        this.l = i13;
        this.f1837n[i13] = null;
        int[] iArr = this.o;
        int i14 = i11 - 2;
        iArr[i14] = iArr[i14] + 1;
        this.f1834r.X(c);
    }
}
