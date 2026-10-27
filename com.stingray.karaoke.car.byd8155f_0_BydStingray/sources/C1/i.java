package C1;

import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import s3.b0;
import u.C2458F;

/* JADX INFO: loaded from: classes.dex */
public abstract class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b0 f942a = new b0(16);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ThreadPoolExecutor f943b;
    public static final Object c;
    public static final C2458F d;

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 10000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque(), new m(0));
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        f943b = threadPoolExecutor;
        c = new Object();
        d = new C2458F(0);
    }

    public static String a(int i7, List list) {
        StringBuilder sb = new StringBuilder();
        for (int i10 = 0; i10 < list.size(); i10++) {
            sb.append(((e) list.get(i10)).f934e);
            sb.append("-");
            sb.append(i7);
            if (i10 < list.size() - 1) {
                sb.append(";");
            }
        }
        return sb.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:56:0x00a5, code lost:
    
        throw r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0019, code lost:
    
        r8 = move-exception;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static C1.h b(java.lang.String r8, android.content.Context r9, java.util.List r10, int r11) {
        /*
            r0 = 1
            java.lang.String r1 = "getFontSync"
            h6.e.f(r1)
            s3.b0 r1 = C1.i.f942a
            java.lang.Object r2 = r1.g(r8)     // Catch: java.lang.Throwable -> L19
            android.graphics.Typeface r2 = (android.graphics.Typeface) r2     // Catch: java.lang.Throwable -> L19
            if (r2 == 0) goto L1c
            C1.h r8 = new C1.h     // Catch: java.lang.Throwable -> L19
            r8.<init>(r2)     // Catch: java.lang.Throwable -> L19
            android.os.Trace.endSection()
            return r8
        L19:
            r8 = move-exception
            goto La2
        L1c:
            C1.j r10 = C1.d.a(r9, r10)     // Catch: java.lang.Throwable -> L19 android.content.pm.PackageManager.NameNotFoundException -> L98
            int r2 = r10.l     // Catch: java.lang.Throwable -> L19
            r3 = 0
            java.lang.Object r10 = r10.f944m
            java.util.List r10 = (java.util.List) r10
            r4 = -3
            if (r2 == 0) goto L30
            if (r2 == r0) goto L2e
        L2c:
            r2 = r4
            goto L50
        L2e:
            r2 = -2
            goto L50
        L30:
            java.lang.Object r2 = r10.get(r3)     // Catch: java.lang.Throwable -> L19
            C1.k[] r2 = (C1.k[]) r2     // Catch: java.lang.Throwable -> L19
            if (r2 == 0) goto L4f
            int r5 = r2.length     // Catch: java.lang.Throwable -> L19
            if (r5 != 0) goto L3c
            goto L4f
        L3c:
            int r5 = r2.length     // Catch: java.lang.Throwable -> L19
            r6 = r3
        L3e:
            if (r6 >= r5) goto L4d
            r7 = r2[r6]     // Catch: java.lang.Throwable -> L19
            int r7 = r7.f947e     // Catch: java.lang.Throwable -> L19
            if (r7 == 0) goto L4b
            if (r7 >= 0) goto L49
            goto L2c
        L49:
            r2 = r7
            goto L50
        L4b:
            int r6 = r6 + r0
            goto L3e
        L4d:
            r2 = r3
            goto L50
        L4f:
            r2 = r0
        L50:
            if (r2 == 0) goto L5b
            C1.h r8 = new C1.h     // Catch: java.lang.Throwable -> L19
            r8.<init>(r2)     // Catch: java.lang.Throwable -> L19
            android.os.Trace.endSection()
            return r8
        L5b:
            int r2 = r10.size()     // Catch: java.lang.Throwable -> L19
            if (r2 <= r0) goto L77
            m6.b r0 = y1.d.f24116a     // Catch: java.lang.Throwable -> L19
            java.lang.String r0 = "TypefaceCompat.createFromFontInfoWithFallback"
            h6.e.f(r0)     // Catch: java.lang.Throwable -> L19
            m6.b r0 = y1.d.f24116a     // Catch: java.lang.Throwable -> L72
            android.graphics.Typeface r9 = r0.m(r9, r10, r11)     // Catch: java.lang.Throwable -> L72
            android.os.Trace.endSection()     // Catch: java.lang.Throwable -> L19
            goto L81
        L72:
            r8 = move-exception
            android.os.Trace.endSection()     // Catch: java.lang.Throwable -> L19
            throw r8     // Catch: java.lang.Throwable -> L19
        L77:
            java.lang.Object r10 = r10.get(r3)     // Catch: java.lang.Throwable -> L19
            C1.k[] r10 = (C1.k[]) r10     // Catch: java.lang.Throwable -> L19
            android.graphics.Typeface r9 = y1.d.a(r9, r10, r11)     // Catch: java.lang.Throwable -> L19
        L81:
            if (r9 == 0) goto L8f
            r1.k(r8, r9)     // Catch: java.lang.Throwable -> L19
            C1.h r8 = new C1.h     // Catch: java.lang.Throwable -> L19
            r8.<init>(r9)     // Catch: java.lang.Throwable -> L19
            android.os.Trace.endSection()
            return r8
        L8f:
            C1.h r8 = new C1.h     // Catch: java.lang.Throwable -> L19
            r8.<init>(r4)     // Catch: java.lang.Throwable -> L19
            android.os.Trace.endSection()
            return r8
        L98:
            C1.h r8 = new C1.h     // Catch: java.lang.Throwable -> L19
            r9 = -1
            r8.<init>(r9)     // Catch: java.lang.Throwable -> L19
            android.os.Trace.endSection()
            return r8
        La2:
            android.os.Trace.endSection()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: C1.i.b(java.lang.String, android.content.Context, java.util.List, int):C1.h");
    }
}
