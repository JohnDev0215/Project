package b.a;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static int f374a = -1;

    /* JADX WARN: Removed duplicated region for block: B:11:0x0034 A[PHI: r1
  0x0034: PHI (r1v6 int) = (r1v5 int), (r1v7 int) binds: [B:7:0x002c, B:9:0x002f] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int a(android.content.Context r3) {
        /*
            int r0 = b.a.a.f374a
            r1 = -1
            if (r0 != r1) goto L53
            r0 = 0
            java.lang.String r1 = "a.a.a"
            java.lang.Class.forName(r1)     // Catch: java.lang.Exception -> L37
            android.content.Context r3 = r3.getApplicationContext()     // Catch: java.lang.Exception -> L37
            a.a.a r3 = a.a.a.a(r3)     // Catch: java.lang.Exception -> L37
            int r3 = r3.b()     // Catch: java.lang.Exception -> L37
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "uiStyle : "
            r1.append(r2)
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            com.byd.util.w.c(r1)
            r1 = 1
            if (r3 == r1) goto L34
            r1 = 2
            if (r3 == r1) goto L34
            b.a.a.f374a = r0
            goto L53
        L34:
            b.a.a.f374a = r1
            goto L53
        L37:
            r3 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "UiStyleConfig exception : "
            r1.append(r2)
            java.lang.String r3 = r3.getMessage()
            r1.append(r3)
            java.lang.String r3 = r1.toString()
            com.byd.util.w.c(r3)
            b.a.a.f374a = r0
            return r0
        L53:
            int r3 = b.a.a.f374a
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.a(android.content.Context):int");
    }
}
