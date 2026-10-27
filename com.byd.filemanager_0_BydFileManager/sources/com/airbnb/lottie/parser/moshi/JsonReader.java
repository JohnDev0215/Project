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
    private static final String[] f2658b = new String[128];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f2659c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int[] f2660d = new int[32];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    String[] f2661e = new String[32];
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
        final String[] f2663a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final f f2664b;

        private a(String[] strArr, f fVar) {
            this.f2663a = strArr;
            this.f2664b = fVar;
        }

        public static a a(String... strArr) {
            try {
                ByteString[] byteStringArr = new ByteString[strArr.length];
                okio.b bVar = new okio.b();
                for (int i = 0; i < strArr.length; i++) {
                    JsonReader.B(bVar, strArr[i]);
                    bVar.r();
                    byteStringArr[i] = bVar.u();
                }
                return new a((String[]) strArr.clone(), f.d(byteStringArr));
            } catch (IOException e2) {
                throw new AssertionError(e2);
            }
        }
    }

    static {
        for (int i = 0; i <= 31; i++) {
            f2658b[i] = String.format("\\u%04x", Integer.valueOf(i));
        }
        String[] strArr = f2658b;
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
    public static void B(okio.c r7, java.lang.String r8) throws java.io.IOException {
        /*
            java.lang.String[] r0 = com.airbnb.lottie.parser.moshi.JsonReader.f2658b
            r1 = 34
            r7.h(r1)
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
            r7.b(r8, r4, r3)
        L2e:
            r7.e(r5)
            int r4 = r3 + 1
        L33:
            int r3 = r3 + 1
            goto Ld
        L36:
            if (r4 >= r2) goto L3b
            r7.b(r8, r4, r2)
        L3b:
            r7.h(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.moshi.JsonReader.B(okio.c, java.lang.String):void");
    }

    public static JsonReader v(d dVar) {
        return new b(dVar);
    }

    public abstract void A() throws IOException;

    final JsonEncodingException C(String str) throws JsonEncodingException {
        throw new JsonEncodingException(str + " at path " + o());
    }

    public abstract void k() throws IOException;

    public abstract void l() throws IOException;

    public abstract void m() throws IOException;

    public abstract void n() throws IOException;

    public final String o() {
        return com.airbnb.lottie.parser.moshi.a.a(this.f2659c, this.f2660d, this.f2661e, this.f);
    }

    public abstract boolean p() throws IOException;

    public abstract boolean q() throws IOException;

    public abstract double r() throws IOException;

    public abstract int s() throws IOException;

    public abstract String t() throws IOException;

    public abstract String u() throws IOException;

    public abstract Token w() throws IOException;

    final void x(int i) {
        int i2 = this.f2659c;
        int[] iArr = this.f2660d;
        if (i2 == iArr.length) {
            if (i2 == 256) {
                throw new JsonDataException("Nesting too deep at " + o());
            }
            this.f2660d = Arrays.copyOf(iArr, iArr.length * 2);
            String[] strArr = this.f2661e;
            this.f2661e = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
            int[] iArr2 = this.f;
            this.f = Arrays.copyOf(iArr2, iArr2.length * 2);
        }
        int[] iArr3 = this.f2660d;
        int i3 = this.f2659c;
        this.f2659c = i3 + 1;
        iArr3[i3] = i;
    }

    public abstract int y(a aVar) throws IOException;

    public abstract void z() throws IOException;
}
