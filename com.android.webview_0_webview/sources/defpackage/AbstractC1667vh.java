package defpackage;

import android.content.Context;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: renamed from: vh, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1667vh {
    public static Context g;
    public static InterfaceC0222Mh h;
    public final C0007Ah a;
    public final String b;
    public final Object c;
    public volatile int d = -1;
    public volatile Object e;
    public static final Object f = new Object();
    public static final AtomicInteger i = new AtomicInteger();

    public AbstractC1667vh(C0007Ah c0007Ah, String str, Object obj, AbstractC1777xh abstractC1777xh) {
        if (c0007Ah.a == null && c0007Ah.b == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        }
        if (c0007Ah.a != null && c0007Ah.b != null) {
            throw new IllegalArgumentException("Must pass one of SharedPreferences file name or ContentProvider URI");
        }
        this.a = c0007Ah;
        this.b = str;
        this.c = obj;
    }

    public static void c(Context context) {
        synchronized (f) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                context = applicationContext;
            }
            if (g != context) {
                C1006jh.c();
                C0043Ch.b();
                C1447rh.b();
                i.incrementAndGet();
                g = context;
                h = AbstractC0205Lh.a(C1832yh.s);
            }
        }
    }

    public static AbstractC1667vh e(C0007Ah c0007Ah, String str, boolean z) {
        return new C1887zh(c0007Ah, str, Boolean.valueOf(z));
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0035 A[Catch: all -> 0x00a6, TryCatch #0 {, blocks: (B:5:0x000b, B:7:0x000f, B:9:0x0013, B:11:0x0019, B:24:0x0037, B:26:0x0045, B:28:0x005f, B:30:0x0066, B:40:0x0090, B:41:0x0093, B:34:0x0073, B:36:0x007d, B:37:0x0082, B:38:0x0087, B:42:0x0097, B:14:0x0020, B:23:0x0035, B:17:0x0027, B:20:0x002e, B:43:0x009c, B:44:0x00a3, B:45:0x00a4), top: B:52:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0045 A[Catch: all -> 0x00a6, TryCatch #0 {, blocks: (B:5:0x000b, B:7:0x000f, B:9:0x0013, B:11:0x0019, B:24:0x0037, B:26:0x0045, B:28:0x005f, B:30:0x0066, B:40:0x0090, B:41:0x0093, B:34:0x0073, B:36:0x007d, B:37:0x0082, B:38:0x0087, B:42:0x0097, B:14:0x0020, B:23:0x0035, B:17:0x0027, B:20:0x002e, B:43:0x009c, B:44:0x00a3, B:45:0x00a4), top: B:52:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0090 A[Catch: all -> 0x00a6, TryCatch #0 {, blocks: (B:5:0x000b, B:7:0x000f, B:9:0x0013, B:11:0x0019, B:24:0x0037, B:26:0x0045, B:28:0x005f, B:30:0x0066, B:40:0x0090, B:41:0x0093, B:34:0x0073, B:36:0x007d, B:37:0x0082, B:38:0x0087, B:42:0x0097, B:14:0x0020, B:23:0x0035, B:17:0x0027, B:20:0x002e, B:43:0x009c, B:44:0x00a3, B:45:0x00a4), top: B:52:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0093 A[Catch: all -> 0x00a6, TryCatch #0 {, blocks: (B:5:0x000b, B:7:0x000f, B:9:0x0013, B:11:0x0019, B:24:0x0037, B:26:0x0045, B:28:0x005f, B:30:0x0066, B:40:0x0090, B:41:0x0093, B:34:0x0073, B:36:0x007d, B:37:0x0082, B:38:0x0087, B:42:0x0097, B:14:0x0020, B:23:0x0035, B:17:0x0027, B:20:0x002e, B:43:0x009c, B:44:0x00a3, B:45:0x00a4), top: B:52:0x000b }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object a() {
        /*
            r7 = this;
            java.util.concurrent.atomic.AtomicInteger r0 = defpackage.AbstractC1667vh.i
            int r0 = r0.get()
            int r1 = r7.d
            if (r1 >= r0) goto La9
            monitor-enter(r7)
            int r1 = r7.d     // Catch: java.lang.Throwable -> La6
            if (r1 >= r0) goto La4
            android.content.Context r1 = defpackage.AbstractC1667vh.g     // Catch: java.lang.Throwable -> La6
            if (r1 == 0) goto L9c
            Ah r1 = r7.a     // Catch: java.lang.Throwable -> La6
            boolean r1 = r1.f     // Catch: java.lang.Throwable -> La6
            if (r1 == 0) goto L27
            java.lang.Object r1 = r7.h()     // Catch: java.lang.Throwable -> La6
            if (r1 == 0) goto L20
            goto L37
        L20:
            java.lang.Object r1 = r7.g()     // Catch: java.lang.Throwable -> La6
            if (r1 == 0) goto L35
            goto L37
        L27:
            java.lang.Object r1 = r7.g()     // Catch: java.lang.Throwable -> La6
            if (r1 == 0) goto L2e
            goto L37
        L2e:
            java.lang.Object r1 = r7.h()     // Catch: java.lang.Throwable -> La6
            if (r1 == 0) goto L35
            goto L37
        L35:
            java.lang.Object r1 = r7.c     // Catch: java.lang.Throwable -> La6
        L37:
            Mh r2 = defpackage.AbstractC1667vh.h     // Catch: java.lang.Throwable -> La6
            java.lang.Object r2 = r2.get()     // Catch: java.lang.Throwable -> La6
            Gh r2 = (defpackage.AbstractC0115Gh) r2     // Catch: java.lang.Throwable -> La6
            boolean r3 = r2.b()     // Catch: java.lang.Throwable -> La6
            if (r3 == 0) goto L97
            java.lang.Object r1 = r2.a()     // Catch: java.lang.Throwable -> La6
            sh r1 = (defpackage.C1502sh) r1     // Catch: java.lang.Throwable -> La6
            Ah r2 = r7.a     // Catch: java.lang.Throwable -> La6
            android.net.Uri r2 = r2.b     // Catch: java.lang.Throwable -> La6
            Ah r3 = r7.a     // Catch: java.lang.Throwable -> La6
            java.lang.String r3 = r3.a     // Catch: java.lang.Throwable -> La6
            Ah r4 = r7.a     // Catch: java.lang.Throwable -> La6
            java.lang.String r4 = r4.d     // Catch: java.lang.Throwable -> La6
            java.lang.String r5 = r7.b     // Catch: java.lang.Throwable -> La6
            java.util.Objects.requireNonNull(r1)     // Catch: java.lang.Throwable -> La6
            r6 = 0
            if (r2 == 0) goto L64
            java.lang.String r3 = r2.toString()     // Catch: java.lang.Throwable -> La6
            goto L66
        L64:
            if (r3 == 0) goto L8e
        L66:
            java.util.Map r1 = r1.a     // Catch: java.lang.Throwable -> La6
            java.lang.Object r1 = r1.get(r3)     // Catch: java.lang.Throwable -> La6
            java.util.Map r1 = (java.util.Map) r1     // Catch: java.lang.Throwable -> La6
            if (r1 != 0) goto L71
            goto L8e
        L71:
            if (r4 == 0) goto L87
            java.lang.String r2 = java.lang.String.valueOf(r5)     // Catch: java.lang.Throwable -> La6
            int r3 = r2.length()     // Catch: java.lang.Throwable -> La6
            if (r3 == 0) goto L82
            java.lang.String r5 = r4.concat(r2)     // Catch: java.lang.Throwable -> La6
            goto L87
        L82:
            java.lang.String r5 = new java.lang.String     // Catch: java.lang.Throwable -> La6
            r5.<init>(r4)     // Catch: java.lang.Throwable -> La6
        L87:
            java.lang.Object r1 = r1.get(r5)     // Catch: java.lang.Throwable -> La6
            r6 = r1
            java.lang.String r6 = (java.lang.String) r6     // Catch: java.lang.Throwable -> La6
        L8e:
            if (r6 != 0) goto L93
            java.lang.Object r1 = r7.c     // Catch: java.lang.Throwable -> La6
            goto L97
        L93:
            java.lang.Object r1 = r7.f(r6)     // Catch: java.lang.Throwable -> La6
        L97:
            r7.e = r1     // Catch: java.lang.Throwable -> La6
            r7.d = r0     // Catch: java.lang.Throwable -> La6
            goto La4
        L9c:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> La6
            java.lang.String r1 = "Must call PhenotypeFlag.init() first"
            r0.<init>(r1)     // Catch: java.lang.Throwable -> La6
            throw r0     // Catch: java.lang.Throwable -> La6
        La4:
            monitor-exit(r7)     // Catch: java.lang.Throwable -> La6
            goto La9
        La6:
            r0 = move-exception
            monitor-exit(r7)     // Catch: java.lang.Throwable -> La6
            throw r0
        La9:
            java.lang.Object r0 = r7.e
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC1667vh.a():java.lang.Object");
    }

    public final String b() {
        return d(this.a.d);
    }

    public final String d(String str) {
        if (str != null && str.isEmpty()) {
            return this.b;
        }
        String strValueOf = String.valueOf(str);
        String strValueOf2 = String.valueOf(this.b);
        return strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf);
    }

    public abstract Object f(Object obj);

    /* JADX WARN: Code restructure failed: missing block: B:52:0x00eb, code lost:
    
        r7 = true;
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00fd A[Catch: all -> 0x0109, TryCatch #3 {, blocks: (B:35:0x00bc, B:37:0x00c0, B:42:0x00c7, B:44:0x00cb, B:45:0x00d5, B:59:0x0101, B:60:0x0103, B:48:0x00db, B:50:0x00e1, B:57:0x00fd, B:54:0x00ee), top: B:110:0x00bc, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0107  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object g() {
        /*
            Method dump skipped, instruction units count: 398
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC1667vh.g():java.lang.Object");
    }

    public final Object h() {
        InterfaceC0097Fh interfaceC0097Fh;
        C0007Ah c0007Ah = this.a;
        if (!c0007Ah.e && ((interfaceC0097Fh = c0007Ah.i) == null || ((Boolean) interfaceC0097Fh.a(g)).booleanValue())) {
            C1447rh c1447rhC = C1447rh.c(g);
            C0007Ah c0007Ah2 = this.a;
            Object objA = c1447rhC.a(c0007Ah2.e ? null : d(c0007Ah2.c));
            if (objA != null) {
                return f(objA);
            }
        }
        return null;
    }
}
