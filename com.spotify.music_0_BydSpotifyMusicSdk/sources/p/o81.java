package p;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class o81 {
    public final HashMap a = new HashMap();
    public final int b = 64;
    public final int c;

    public o81(int i) {
        this.c = i;
    }

    public static String a(int i, String str) {
        if (str == null) {
            return str;
        }
        String strTrim = str.trim();
        return strTrim.length() > i ? strTrim.substring(0, i) : strTrim;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0038, code lost:
    
        if (r1.equals(r0) != false) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized boolean b(java.lang.String r4, java.lang.String r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            if (r4 == 0) goto L48
            int r0 = r3.c     // Catch: java.lang.Throwable -> L1f
            java.lang.String r4 = a(r0, r4)     // Catch: java.lang.Throwable -> L1f
            java.util.HashMap r0 = r3.a     // Catch: java.lang.Throwable -> L1f
            int r0 = r0.size()     // Catch: java.lang.Throwable -> L1f
            int r1 = r3.b     // Catch: java.lang.Throwable -> L1f
            r2 = 0
            if (r0 < r1) goto L21
            java.util.HashMap r0 = r3.a     // Catch: java.lang.Throwable -> L1f
            boolean r0 = r0.containsKey(r4)     // Catch: java.lang.Throwable -> L1f
            if (r0 == 0) goto L1d
            goto L21
        L1d:
            monitor-exit(r3)
            return r2
        L1f:
            r4 = move-exception
            goto L50
        L21:
            int r0 = r3.c     // Catch: java.lang.Throwable -> L1f
            java.lang.String r0 = a(r0, r5)     // Catch: java.lang.Throwable -> L1f
            java.util.HashMap r1 = r3.a     // Catch: java.lang.Throwable -> L1f
            java.lang.Object r1 = r1.get(r4)     // Catch: java.lang.Throwable -> L1f
            java.lang.String r1 = (java.lang.String) r1     // Catch: java.lang.Throwable -> L1f
            if (r1 != 0) goto L34
            if (r0 != 0) goto L3c
            goto L3a
        L34:
            boolean r1 = r1.equals(r0)     // Catch: java.lang.Throwable -> L1f
            if (r1 == 0) goto L3c
        L3a:
            monitor-exit(r3)
            return r2
        L3c:
            java.util.HashMap r1 = r3.a     // Catch: java.lang.Throwable -> L1f
            if (r5 != 0) goto L42
            java.lang.String r0 = ""
        L42:
            r1.put(r4, r0)     // Catch: java.lang.Throwable -> L1f
            monitor-exit(r3)
            r4 = 1
            return r4
        L48:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException     // Catch: java.lang.Throwable -> L1f
            java.lang.String r5 = "Custom attribute key must not be null."
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L1f
            throw r4     // Catch: java.lang.Throwable -> L1f
        L50:
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: p.o81.b(java.lang.String, java.lang.String):boolean");
    }

    public final synchronized void c(Map map) {
        try {
            for (Map.Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                if (str == null) {
                    throw new IllegalArgumentException("Custom attribute key must not be null.");
                }
                String strA = a(this.c, str);
                if (this.a.size() < this.b || this.a.containsKey(strA)) {
                    String str2 = (String) entry.getValue();
                    this.a.put(strA, str2 == null ? "" : a(this.c, str2));
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }
}
