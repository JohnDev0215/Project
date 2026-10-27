package com.airbnb.lottie.v.k0;

import c.f;
import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public abstract class c implements Closeable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String[] f933a = new String[128];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f934b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int[] f935c = new int[32];
    String[] d = new String[32];
    int[] e = new int[32];
    boolean f;
    boolean g;

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final String[] f936a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final f f937b;

        private a(String[] strArr, f fVar) {
            this.f936a = strArr;
            this.f937b = fVar;
        }

        public static a a(String... strArr) {
            try {
                c.d[] dVarArr = new c.d[strArr.length];
                c.a aVar = new c.a();
                for (int i = 0; i < strArr.length; i++) {
                    c.B(aVar, strArr[i]);
                    aVar.q();
                    dVarArr[i] = aVar.t();
                }
                return new a((String[]) strArr.clone(), f.d(dVarArr));
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }
    }

    public enum b {
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

    static {
        for (int i = 0; i <= 31; i++) {
            f933a[i] = String.format("\\u%04x", Integer.valueOf(i));
        }
        String[] strArr = f933a;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
    }

    c() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:16:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void B(c.b r7, java.lang.String r8) {
        /*
            java.lang.String[] r0 = com.airbnb.lottie.v.k0.c.f933a
            r1 = 34
            r7.e(r1)
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
            r7.a(r8, r4, r3)
        L2e:
            r7.h(r5)
            int r4 = r3 + 1
        L33:
            int r3 = r3 + 1
            goto Ld
        L36:
            if (r4 >= r2) goto L3b
            r7.a(r8, r4, r2)
        L3b:
            r7.e(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.v.k0.c.B(c.b, java.lang.String):void");
    }

    public static c v(c.c cVar) {
        return new e(cVar);
    }

    public abstract void A();

    final com.airbnb.lottie.v.k0.b C(String str) throws com.airbnb.lottie.v.k0.b {
        throw new com.airbnb.lottie.v.k0.b(str + " at path " + o());
    }

    public abstract void k();

    public abstract void l();

    public abstract void m();

    public abstract void n();

    public final String o() {
        return d.a(this.f934b, this.f935c, this.d, this.e);
    }

    public abstract boolean p();

    public abstract boolean q();

    public abstract double r();

    public abstract int s();

    public abstract String t();

    public abstract String u();

    public abstract b w();

    final void x(int i) {
        int i2 = this.f934b;
        int[] iArr = this.f935c;
        if (i2 == iArr.length) {
            if (i2 == 256) {
                throw new com.airbnb.lottie.v.k0.a("Nesting too deep at " + o());
            }
            this.f935c = Arrays.copyOf(iArr, iArr.length * 2);
            String[] strArr = this.d;
            this.d = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
            int[] iArr2 = this.e;
            this.e = Arrays.copyOf(iArr2, iArr2.length * 2);
        }
        int[] iArr3 = this.f935c;
        int i3 = this.f934b;
        this.f934b = i3 + 1;
        iArr3[i3] = i;
    }

    public abstract int y(a aVar);

    public abstract void z();
}
