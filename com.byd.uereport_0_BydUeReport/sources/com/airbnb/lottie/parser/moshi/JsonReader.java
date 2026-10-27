package com.airbnb.lottie.parser.moshi;

import i7.b;
import i7.d;
import i7.f;
import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;
import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
public abstract class JsonReader implements Closeable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String[] f2514d = new String[128];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2515e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int[] f2516f = new int[32];

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String[] f2517g = new String[32];

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int[] f2518h = new int[32];

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f2519i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f2520j;

    public enum Token {
        BEGIN_ARRAY,
        END_ARRAY,
        BEGIN_OBJECT,
        END_OBJECT,
        NAME,
        STRING,
        NUMBER,
        BOOLEAN,
        NULL,
        END_DOCUMENT
    }

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String[] f2521a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final f f2522b;

        public a(String[] strArr, f fVar) {
            this.f2521a = strArr;
            this.f2522b = fVar;
        }

        public static a a(String... strArr) {
            try {
                ByteString[] byteStringArr = new ByteString[strArr.length];
                b bVar = new b();
                for (int i8 = 0; i8 < strArr.length; i8++) {
                    JsonReader.I(bVar, strArr[i8]);
                    bVar.q();
                    byteStringArr[i8] = bVar.v();
                }
                return new a((String[]) strArr.clone(), f.d(byteStringArr));
            } catch (IOException e8) {
                throw new AssertionError(e8);
            }
        }
    }

    static {
        for (int i8 = 0; i8 <= 31; i8++) {
            f2514d[i8] = String.format("\\u%04x", Integer.valueOf(i8));
        }
        String[] strArr = f2514d;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void I(i7.c r7, java.lang.String r8) throws java.io.IOException {
        /*
            java.lang.String[] r0 = com.airbnb.lottie.parser.moshi.JsonReader.f2514d
            r1 = 34
            r7.H(r1)
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
            r7.f(r8, r4, r3)
        L2e:
            r7.f0(r5)
            int r4 = r3 + 1
        L33:
            int r3 = r3 + 1
            goto Ld
        L36:
            if (r4 >= r2) goto L3b
            r7.f(r8, r4, r2)
        L3b:
            r7.H(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.moshi.JsonReader.I(i7.c, java.lang.String):void");
    }

    public static JsonReader w(d dVar) {
        return new z1.b(dVar);
    }

    public final void B(int i8) {
        int i9 = this.f2515e;
        int[] iArr = this.f2516f;
        if (i9 == iArr.length) {
            if (i9 == 256) {
                throw new JsonDataException("Nesting too deep at " + a());
            }
            this.f2516f = Arrays.copyOf(iArr, iArr.length * 2);
            String[] strArr = this.f2517g;
            this.f2517g = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
            int[] iArr2 = this.f2518h;
            this.f2518h = Arrays.copyOf(iArr2, iArr2.length * 2);
        }
        int[] iArr3 = this.f2516f;
        int i10 = this.f2515e;
        this.f2515e = i10 + 1;
        iArr3[i10] = i8;
    }

    public abstract int C(a aVar) throws IOException;

    public abstract void E() throws IOException;

    public abstract void F() throws IOException;

    public final JsonEncodingException L(String str) throws JsonEncodingException {
        throw new JsonEncodingException(str + " at path " + a());
    }

    public final String a() {
        return z1.a.a(this.f2515e, this.f2516f, this.f2517g, this.f2518h);
    }

    public abstract void g() throws IOException;

    public abstract void i() throws IOException;

    public abstract void j() throws IOException;

    public abstract void m() throws IOException;

    public abstract boolean n() throws IOException;

    public abstract boolean o() throws IOException;

    public abstract double q() throws IOException;

    public abstract int r() throws IOException;

    public abstract String t() throws IOException;

    public abstract String v() throws IOException;

    public abstract Token x() throws IOException;
}
