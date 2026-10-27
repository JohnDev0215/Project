package com.airbnb.lottie.parser.moshi;

import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;
import okio.ByteString;
import okio.d;
import okio.f;

/* JADX INFO: loaded from: classes.dex */
public abstract class JsonReader implements Closeable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String[] f3044b = new String[128];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f3045c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int[] f3046d = new int[32];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    String[] f3047e = new String[32];
    int[] f = new int[32];
    boolean g;
    boolean h;

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
        final String[] f3049a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final f f3050b;

        private a(String[] strArr, f fVar) {
            this.f3049a = strArr;
            this.f3050b = fVar;
        }

        public static a a(String... strArr) {
            try {
                ByteString[] byteStringArr = new ByteString[strArr.length];
                okio.b bVar = new okio.b();
                for (int i = 0; i < strArr.length; i++) {
                    JsonReader.C(bVar, strArr[i]);
                    bVar.s();
                    byteStringArr[i] = bVar.v();
                }
                return new a((String[]) strArr.clone(), f.d(byteStringArr));
            } catch (IOException e2) {
                throw new AssertionError(e2);
            }
        }
    }

    static {
        for (int i = 0; i <= 31; i++) {
            f3044b[i] = String.format("\\u%04x", Integer.valueOf(i));
        }
        String[] strArr = f3044b;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
    }

    JsonReader() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:16:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void C(okio.c r7, java.lang.String r8) throws java.io.IOException {
        /*
            java.lang.String[] r0 = com.airbnb.lottie.parser.moshi.JsonReader.f3044b
            r1 = 34
            r7.i(r1)
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
            r7.c(r8, r4, r3)
        L2e:
            r7.f(r5)
            int r4 = r3 + 1
        L33:
            int r3 = r3 + 1
            goto Ld
        L36:
            if (r4 >= r2) goto L3b
            r7.c(r8, r4, r2)
        L3b:
            r7.i(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.moshi.JsonReader.C(okio.c, java.lang.String):void");
    }

    public static JsonReader w(d dVar) {
        return new b(dVar);
    }

    public abstract void A() throws IOException;

    public abstract void B() throws IOException;

    final JsonEncodingException D(String str) throws JsonEncodingException {
        throw new JsonEncodingException(str + " at path " + p());
    }

    public abstract void l() throws IOException;

    public abstract void m() throws IOException;

    public abstract void n() throws IOException;

    public abstract void o() throws IOException;

    public final String p() {
        return com.airbnb.lottie.parser.moshi.a.a(this.f3045c, this.f3046d, this.f3047e, this.f);
    }

    public abstract boolean q() throws IOException;

    public abstract boolean r() throws IOException;

    public abstract double s() throws IOException;

    public abstract int t() throws IOException;

    public abstract String u() throws IOException;

    public abstract String v() throws IOException;

    public abstract Token x() throws IOException;

    final void y(int i) {
        int i2 = this.f3045c;
        int[] iArr = this.f3046d;
        if (i2 == iArr.length) {
            if (i2 == 256) {
                throw new JsonDataException("Nesting too deep at " + p());
            }
            this.f3046d = Arrays.copyOf(iArr, iArr.length * 2);
            String[] strArr = this.f3047e;
            this.f3047e = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
            int[] iArr2 = this.f;
            this.f = Arrays.copyOf(iArr2, iArr2.length * 2);
        }
        int[] iArr3 = this.f3046d;
        int i3 = this.f3045c;
        this.f3045c = i3 + 1;
        iArr3[i3] = i;
    }

    public abstract int z(a aVar) throws IOException;
}
