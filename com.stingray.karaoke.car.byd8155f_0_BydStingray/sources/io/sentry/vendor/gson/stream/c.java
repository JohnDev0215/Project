package io.sentry.vendor.gson.stream;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class c implements Closeable, Flushable {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final String[] f16972t = new String[128];
    public final Writer l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int[] f16973m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f16974n;
    public String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String f16975p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f16976q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public String f16977r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final boolean f16978s;

    static {
        for (int i7 = 0; i7 <= 31; i7++) {
            f16972t[i7] = String.format("\\u%04x", Integer.valueOf(i7));
        }
        String[] strArr = f16972t;
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

    public c(Writer writer) {
        int[] iArr = new int[32];
        this.f16973m = iArr;
        this.f16974n = 0;
        if (iArr.length == 0) {
            this.f16973m = Arrays.copyOf(iArr, 0);
        }
        int[] iArr2 = this.f16973m;
        int i7 = this.f16974n;
        this.f16974n = i7 + 1;
        iArr2[i7] = 6;
        this.f16975p = ":";
        this.f16978s = true;
        this.l = writer;
    }

    public final void b() throws IOException {
        int iK = k();
        if (iK == 1) {
            this.f16973m[this.f16974n - 1] = 2;
            i();
            return;
        }
        Writer writer = this.l;
        if (iK == 2) {
            writer.append(',');
            i();
        } else {
            if (iK == 4) {
                writer.append((CharSequence) this.f16975p);
                this.f16973m[this.f16974n - 1] = 5;
                return;
            }
            if (iK != 6) {
                if (iK != 7) {
                    throw new IllegalStateException("Nesting problem.");
                }
                if (!this.f16976q) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
            }
            this.f16973m[this.f16974n - 1] = 7;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.l.close();
        int i7 = this.f16974n;
        if (i7 > 1 || (i7 == 1 && this.f16973m[i7 - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.f16974n = 0;
    }

    @Override // java.io.Flushable
    public final void flush() throws IOException {
        if (this.f16974n == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.l.flush();
    }

    public final void g(char c, int i7, int i10) throws IOException {
        int iK = k();
        if (iK != i10 && iK != i7) {
            throw new IllegalStateException("Nesting problem.");
        }
        if (this.f16977r != null) {
            throw new IllegalStateException("Dangling name: " + this.f16977r);
        }
        this.f16974n--;
        if (iK == i10) {
            i();
        }
        this.l.write(c);
    }

    public final void i() throws IOException {
        if (this.o == null) {
            return;
        }
        Writer writer = this.l;
        writer.write(10);
        int i7 = this.f16974n;
        for (int i10 = 1; i10 < i7; i10++) {
            writer.write(this.o);
        }
    }

    public final void j() throws IOException {
        if (this.f16977r != null) {
            if (!this.f16978s) {
                this.f16977r = null;
                return;
            }
            o();
        }
        b();
        this.l.write("null");
    }

    public final int k() {
        int i7 = this.f16974n;
        if (i7 != 0) {
            return this.f16973m[i7 - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m(java.lang.String r8) throws java.io.IOException {
        /*
            r7 = this;
            java.lang.String[] r0 = io.sentry.vendor.gson.stream.c.f16972t
            java.io.Writer r7 = r7.l
            r1 = 34
            r7.write(r1)
            int r2 = r8.length()
            r3 = 0
            r4 = r3
        Lf:
            if (r3 >= r2) goto L3a
            char r5 = r8.charAt(r3)
            r6 = 128(0x80, float:1.8E-43)
            if (r5 >= r6) goto L1e
            r5 = r0[r5]
            if (r5 != 0) goto L2b
            goto L37
        L1e:
            r6 = 8232(0x2028, float:1.1535E-41)
            if (r5 != r6) goto L25
            java.lang.String r5 = "\\u2028"
            goto L2b
        L25:
            r6 = 8233(0x2029, float:1.1537E-41)
            if (r5 != r6) goto L37
            java.lang.String r5 = "\\u2029"
        L2b:
            if (r4 >= r3) goto L32
            int r6 = r3 - r4
            r7.write(r8, r4, r6)
        L32:
            r7.write(r5)
            int r4 = r3 + 1
        L37:
            int r3 = r3 + 1
            goto Lf
        L3a:
            if (r4 >= r2) goto L40
            int r2 = r2 - r4
            r7.write(r8, r4, r2)
        L40:
            r7.write(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.vendor.gson.stream.c.m(java.lang.String):void");
    }

    public final void o() throws IOException {
        if (this.f16977r != null) {
            int iK = k();
            if (iK == 5) {
                this.l.write(44);
            } else if (iK != 3) {
                throw new IllegalStateException("Nesting problem.");
            }
            i();
            this.f16973m[this.f16974n - 1] = 4;
            m(this.f16977r);
            this.f16977r = null;
        }
    }
}
