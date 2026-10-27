package defpackage;

import J.N;
import java.util.Iterator;
import java.util.List;
import org.chromium.chrome.browser.cookies.CanonicalCookie;

/* JADX INFO: compiled from: CookiesFetcher.java */
/* JADX INFO: loaded from: classes.dex */
public final class g00 extends i9<List<CanonicalCookie>> {
    /* JADX WARN: Can't wrap try/catch for region: R(5:9|(5:59|10|53|11|12)|57|34|49) */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:57:0x006b A[EXC_TOP_SPLITTER, PHI: r2 r5 r6
  0x006b: PHI (r2v5 java.util.ArrayList) = (r2v2 java.util.ArrayList), (r2v3 java.util.ArrayList), (r2v9 java.util.ArrayList) binds: [B:26:0x005a, B:33:0x0069, B:12:0x0044] A[DONT_GENERATE, DONT_INLINE]
  0x006b: PHI (r5v6 java.io.DataInputStream) = (r5v3 java.io.DataInputStream), (r5v4 java.io.DataInputStream), (r5v9 java.io.DataInputStream) binds: [B:26:0x005a, B:33:0x0069, B:12:0x0044] A[DONT_GENERATE, DONT_INLINE]
  0x006b: PHI (r6v8 java.io.FileInputStream) = (r6v5 java.io.FileInputStream), (r6v6 java.io.FileInputStream), (r6v11 java.io.FileInputStream) binds: [B:26:0x005a, B:33:0x0069, B:12:0x0044] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    @Override // defpackage.i9
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List<org.chromium.chrome.browser.cookies.CanonicalCookie> b() {
        /*
            r8 = this;
            java.lang.String r8 = "IOException during Cooke Restore"
            java.lang.String r0 = "Error restoring cookies."
            java.lang.String r1 = "cr_CookiesFetcher"
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r3 = 0
            ct r4 = ct.c.a     // Catch: java.lang.Throwable -> L50 java.io.IOException -> L5d
            r5 = 2
            javax.crypto.Cipher r4 = r4.a(r5)     // Catch: java.lang.Throwable -> L50 java.io.IOException -> L5d
            if (r4 != 0) goto L17
            goto L77
        L17:
            java.io.File r5 = new java.io.File     // Catch: java.lang.Throwable -> L50 java.io.IOException -> L5d
            java.lang.String r6 = org.chromium.chrome.browser.cookies.CookiesFetcher.b()     // Catch: java.lang.Throwable -> L50 java.io.IOException -> L5d
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L50 java.io.IOException -> L5d
            boolean r6 = r5.exists()     // Catch: java.lang.Throwable -> L50 java.io.IOException -> L5d
            if (r6 != 0) goto L27
            goto L77
        L27:
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L50 java.io.IOException -> L5d
            r6.<init>(r5)     // Catch: java.lang.Throwable -> L50 java.io.IOException -> L5d
            java.io.DataInputStream r5 = new java.io.DataInputStream     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L4e
            javax.crypto.CipherInputStream r7 = new javax.crypto.CipherInputStream     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L4e
            r7.<init>(r6, r4)     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L4e
            r5.<init>(r7)     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L4e
            java.util.ArrayList r2 = org.chromium.chrome.browser.cookies.CanonicalCookie.a(r5)     // Catch: java.lang.Throwable -> L48 java.io.IOException -> L4a
            h00 r3 = new h00     // Catch: java.lang.Throwable -> L48 java.io.IOException -> L4a
            r3.<init>()     // Catch: java.lang.Throwable -> L48 java.io.IOException -> L4a
            si2 r4 = defpackage.i9.f     // Catch: java.lang.Throwable -> L48 java.io.IOException -> L4a
            r3.c(r4)     // Catch: java.lang.Throwable -> L48 java.io.IOException -> L4a
            defpackage.a8.s(r6)
            goto L6b
        L48:
            r3 = move-exception
            goto L54
        L4a:
            r3 = move-exception
            goto L61
        L4c:
            r4 = move-exception
            goto L52
        L4e:
            r4 = move-exception
            goto L5f
        L50:
            r4 = move-exception
            r6 = r3
        L52:
            r5 = r3
            r3 = r4
        L54:
            android.util.Log.w(r1, r0, r3)     // Catch: java.lang.Throwable -> L78
            defpackage.a8.s(r6)
            if (r5 == 0) goto L77
            goto L6b
        L5d:
            r4 = move-exception
            r6 = r3
        L5f:
            r5 = r3
            r3 = r4
        L61:
            java.lang.String r4 = "IOException during Cookie Restore"
            android.util.Log.w(r1, r4, r3)     // Catch: java.lang.Throwable -> L78
            defpackage.a8.s(r6)
            if (r5 == 0) goto L77
        L6b:
            r5.close()     // Catch: java.lang.Throwable -> L6f java.io.IOException -> L74
            goto L77
        L6f:
            r8 = move-exception
            android.util.Log.w(r1, r0, r8)
            goto L77
        L74:
            android.util.Log.w(r1, r8)
        L77:
            return r2
        L78:
            r2 = move-exception
            defpackage.a8.s(r6)
            if (r5 == 0) goto L8a
            r5.close()     // Catch: java.lang.Throwable -> L82 java.io.IOException -> L87
            goto L8a
        L82:
            r8 = move-exception
            android.util.Log.w(r1, r0, r8)
            goto L8a
        L87:
            android.util.Log.w(r1, r8)
        L8a:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.g00.b():java.lang.Object");
    }

    @Override // defpackage.i9
    public final void l(List<CanonicalCookie> list) {
        for (Iterator<CanonicalCookie> it = list.iterator(); it.hasNext(); it = it) {
            CanonicalCookie next = it.next();
            N.McZsSVxA(next.a, next.b, next.c, next.d, next.e, next.f, next.g, next.h, next.i, next.j, next.k, next.l, next.m, next.n, next.o, next.p);
        }
    }
}
