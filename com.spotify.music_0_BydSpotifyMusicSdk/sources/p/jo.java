package p;

import com.apollographql.apollo3.exception.JsonDataException;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class jo implements a81 {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final String[] f140p;
    public final io c;
    public int k;
    public String o;
    public final String e = null;
    public final int[] l = new int[256];
    public final String[] m = new String[256];
    public final int[] n = new int[256];

    static {
        String[] strArr = new String[128];
        for (int i = 0; i < 32; i++) {
            StringBuilder sb = new StringBuilder("\\u00");
            byte b = (byte) i;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("0123456789abcdef".charAt(b >>> 4));
            sb2.append("0123456789abcdef".charAt(b & 15));
            sb.append(sb2.toString());
            strArr[i] = sb.toString();
        }
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        f140p = strArr;
    }

    public jo(fo foVar) {
        this.c = foVar;
        X(6);
    }

    @Override // p.a81
    public final a81 A(int i) {
        q(String.valueOf(i));
        return this;
    }

    public final void E() {
        String str = this.e;
        if (str == null) {
            return;
        }
        io ioVar = this.c;
        ioVar.R(10);
        int i = this.k;
        for (int i2 = 1; i2 < i; i2++) {
            ioVar.s0(str);
        }
    }

    @Override // p.a81
    public final a81 G(double d) {
        if (!Double.isNaN(d) && !Double.isInfinite(d)) {
            q(String.valueOf(d));
            return this;
        }
        throw new IllegalArgumentException(("Numeric values must be finite, but was " + d).toString());
    }

    public final int J() {
        int i = this.k;
        if (i == 0) {
            throw new IllegalStateException("JsonWriter is closed.".toString());
        }
        return this.l[i - 1];
    }

    @Override // p.a81
    public final a81 N(String str) {
        v03.j(str, "value");
        o0();
        b();
        yn.o(this.c, str);
        int i = this.k - 1;
        int[] iArr = this.n;
        iArr[i] = iArr[i] + 1;
        return this;
    }

    public final void X(int i) {
        int i2 = this.k;
        int[] iArr = this.l;
        if (i2 != iArr.length) {
            this.k = i2 + 1;
            iArr[i2] = i;
        } else {
            throw new JsonDataException("Nesting too deep at " + i() + ": circular reference?");
        }
    }

    public final void b() {
        int iJ = J();
        int[] iArr = this.l;
        if (iJ == 1) {
            iArr[this.k - 1] = 2;
            E();
            return;
        }
        io ioVar = this.c;
        if (iJ == 2) {
            ioVar.R(44);
            E();
        } else if (iJ == 4) {
            String str = this.e;
            ioVar.s0((str == null || str.length() == 0) ? ":" : ": ");
            iArr[this.k - 1] = 5;
        } else if (iJ == 6) {
            iArr[this.k - 1] = 7;
        } else {
            if (iJ != 7) {
                throw new IllegalStateException("Nesting problem.");
            }
            throw new IllegalStateException("JSON must have only one top-level value.".toString());
        }
    }

    public final void c(String str, int i, int i2) {
        int iJ = J();
        if (iJ != i2 && iJ != i) {
            throw new IllegalStateException("Nesting problem.".toString());
        }
        if (this.o != null) {
            throw new IllegalStateException(("Dangling name: " + this.o).toString());
        }
        int i3 = this.k;
        int i4 = i3 - 1;
        this.k = i4;
        this.m[i4] = null;
        int i5 = i3 - 2;
        int[] iArr = this.n;
        iArr[i5] = iArr[i5] + 1;
        if (iJ == i2) {
            E();
        }
        this.c.s0(str);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.c.close();
        int i = this.k;
        if (i > 1 || (i == 1 && this.l[i - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.k = 0;
    }

    @Override // p.a81
    public final a81 f() {
        o0();
        b();
        X(3);
        this.n[this.k - 1] = 0;
        this.c.s0("{");
        return this;
    }

    @Override // p.a81
    public final a81 h() {
        c("]", 1, 2);
        return this;
    }

    public final String i() {
        String str;
        int i = this.k;
        int[] iArr = this.l;
        v03.j(iArr, "stack");
        String[] strArr = this.m;
        v03.j(strArr, "pathNames");
        int[] iArr2 = this.n;
        v03.j(iArr2, "pathIndices");
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = iArr[i2];
            if (i3 == 1 || i3 == 2) {
                arrayList.add(Integer.valueOf(iArr2[i2]));
            } else if ((i3 == 3 || i3 == 4 || i3 == 5) && (str = strArr[i2]) != null) {
                arrayList.add(str);
            }
        }
        return ou.h0(arrayList, ".", null, null, null, 62);
    }

    @Override // p.a81
    public final a81 i0() {
        q("null");
        return this;
    }

    @Override // p.a81
    public final a81 j() {
        o0();
        b();
        X(1);
        this.n[this.k - 1] = 0;
        this.c.s0("[");
        return this;
    }

    @Override // p.a81
    public final a81 k() {
        c("}", 3, 5);
        return this;
    }

    @Override // p.a81
    public final a81 l0(String str) {
        int i = this.k;
        if (i == 0) {
            throw new IllegalStateException("JsonWriter is closed.".toString());
        }
        if (this.o != null) {
            throw new IllegalStateException("Nesting problem.".toString());
        }
        this.o = str;
        this.m[i - 1] = str;
        return this;
    }

    @Override // p.a81
    public final a81 m0(o71 o71Var) {
        v03.j(o71Var, "value");
        q(o71Var.toString());
        return this;
    }

    @Override // p.a81
    public final a81 n0(boolean z) {
        q(z ? "true" : "false");
        return this;
    }

    public final void o0() {
        if (this.o != null) {
            int iJ = J();
            io ioVar = this.c;
            if (iJ == 5) {
                ioVar.R(44);
            } else if (iJ != 3) {
                throw new IllegalStateException("Nesting problem.".toString());
            }
            E();
            this.l[this.k - 1] = 4;
            String str = this.o;
            v03.h(str);
            yn.o(ioVar, str);
            this.o = null;
        }
    }

    public final void q(String str) {
        v03.j(str, "value");
        o0();
        b();
        this.c.s0(str);
        int i = this.k - 1;
        int[] iArr = this.n;
        iArr[i] = iArr[i] + 1;
    }

    @Override // p.a81
    public final a81 z(long j) {
        q(String.valueOf(j));
        return this;
    }
}
