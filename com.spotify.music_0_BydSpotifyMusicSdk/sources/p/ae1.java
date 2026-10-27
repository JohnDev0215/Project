package p;

import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ae1 {
    public final int a;
    public final oh1 b;
    public final zb0 c;
    public int d;
    public int e;
    public int f;

    public ae1(int i) {
        this.a = i;
        if (i <= 0) {
            throw new IllegalArgumentException("maxSize <= 0".toString());
        }
        this.b = new oh1();
        this.c = new zb0();
    }

    public void a(Object obj, Object obj2, Object obj3) {
        v03.j(obj, "key");
        v03.j(obj2, "oldValue");
    }

    public final Object b(Object obj) {
        v03.j(obj, "key");
        synchronized (this.c) {
            oh1 oh1Var = this.b;
            oh1Var.getClass();
            Object obj2 = ((LinkedHashMap) oh1Var.e).get(obj);
            if (obj2 != null) {
                this.e++;
                return obj2;
            }
            this.f++;
            return null;
        }
    }

    public final Object c(Object obj, Object obj2) {
        Object objPut;
        v03.j(obj, "key");
        synchronized (this.c) {
            this.d += d(obj, obj2);
            oh1 oh1Var = this.b;
            oh1Var.getClass();
            objPut = ((LinkedHashMap) oh1Var.e).put(obj, obj2);
            if (objPut != null) {
                this.d -= d(obj, objPut);
            }
        }
        if (objPut != null) {
            a(obj, objPut, obj2);
        }
        f(this.a);
        return objPut;
    }

    public final int d(Object obj, Object obj2) {
        int iE = e(obj, obj2);
        if (iE >= 0) {
            return iE;
        }
        throw new IllegalStateException(("Negative size: " + obj + '=' + obj2).toString());
    }

    public int e(Object obj, Object obj2) {
        v03.j(obj, "key");
        v03.j(obj2, "value");
        return 1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x008e, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x009a, code lost:
    
        throw new java.lang.IllegalStateException("LruCache.sizeOf() is reporting inconsistent results!".toString());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f(int r7) {
        /*
            r6 = this;
        L0:
            p.zb0 r0 = r6.c
            monitor-enter(r0)
            int r1 = r6.d     // Catch: java.lang.Throwable -> L18
            if (r1 < 0) goto L8f
            p.oh1 r1 = r6.b     // Catch: java.lang.Throwable -> L18
            java.lang.Object r1 = r1.e     // Catch: java.lang.Throwable -> L18
            java.util.LinkedHashMap r1 = (java.util.LinkedHashMap) r1     // Catch: java.lang.Throwable -> L18
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L18
            if (r1 == 0) goto L1b
            int r1 = r6.d     // Catch: java.lang.Throwable -> L18
            if (r1 != 0) goto L8f
            goto L1b
        L18:
            r7 = move-exception
            goto L9b
        L1b:
            int r1 = r6.d     // Catch: java.lang.Throwable -> L18
            if (r1 <= r7) goto L8d
            p.oh1 r1 = r6.b     // Catch: java.lang.Throwable -> L18
            java.lang.Object r1 = r1.e     // Catch: java.lang.Throwable -> L18
            java.util.LinkedHashMap r1 = (java.util.LinkedHashMap) r1     // Catch: java.lang.Throwable -> L18
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L18
            if (r1 == 0) goto L2c
            goto L8d
        L2c:
            p.oh1 r1 = r6.b     // Catch: java.lang.Throwable -> L18
            java.lang.Object r1 = r1.e     // Catch: java.lang.Throwable -> L18
            java.util.LinkedHashMap r1 = (java.util.LinkedHashMap) r1     // Catch: java.lang.Throwable -> L18
            java.util.Set r1 = r1.entrySet()     // Catch: java.lang.Throwable -> L18
            java.lang.String r2 = "map.entries"
            p.v03.i(r1, r2)     // Catch: java.lang.Throwable -> L18
            boolean r2 = r1 instanceof java.util.List     // Catch: java.lang.Throwable -> L18
            r3 = 0
            if (r2 == 0) goto L50
            java.util.List r1 = (java.util.List) r1     // Catch: java.lang.Throwable -> L18
            boolean r2 = r1.isEmpty()     // Catch: java.lang.Throwable -> L18
            if (r2 == 0) goto L4a
        L48:
            r1 = r3
            goto L5f
        L4a:
            r2 = 0
            java.lang.Object r1 = r1.get(r2)     // Catch: java.lang.Throwable -> L18
            goto L5f
        L50:
            java.util.Iterator r1 = r1.iterator()     // Catch: java.lang.Throwable -> L18
            boolean r2 = r1.hasNext()     // Catch: java.lang.Throwable -> L18
            if (r2 != 0) goto L5b
            goto L48
        L5b:
            java.lang.Object r1 = r1.next()     // Catch: java.lang.Throwable -> L18
        L5f:
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch: java.lang.Throwable -> L18
            if (r1 != 0) goto L65
            monitor-exit(r0)
            return
        L65:
            java.lang.Object r2 = r1.getKey()     // Catch: java.lang.Throwable -> L18
            java.lang.Object r1 = r1.getValue()     // Catch: java.lang.Throwable -> L18
            p.oh1 r4 = r6.b     // Catch: java.lang.Throwable -> L18
            r4.getClass()     // Catch: java.lang.Throwable -> L18
            java.lang.String r5 = "key"
            p.v03.j(r2, r5)     // Catch: java.lang.Throwable -> L18
            java.lang.Object r4 = r4.e     // Catch: java.lang.Throwable -> L18
            java.util.LinkedHashMap r4 = (java.util.LinkedHashMap) r4     // Catch: java.lang.Throwable -> L18
            r4.remove(r2)     // Catch: java.lang.Throwable -> L18
            int r4 = r6.d     // Catch: java.lang.Throwable -> L18
            int r5 = r6.d(r2, r1)     // Catch: java.lang.Throwable -> L18
            int r4 = r4 - r5
            r6.d = r4     // Catch: java.lang.Throwable -> L18
            monitor-exit(r0)
            r6.a(r2, r1, r3)
            goto L0
        L8d:
            monitor-exit(r0)
            return
        L8f:
            java.lang.String r7 = "LruCache.sizeOf() is reporting inconsistent results!"
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L18
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L18
            r1.<init>(r7)     // Catch: java.lang.Throwable -> L18
            throw r1     // Catch: java.lang.Throwable -> L18
        L9b:
            monitor-exit(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: p.ae1.f(int):void");
    }

    public final String toString() {
        String str;
        synchronized (this.c) {
            try {
                int i = this.e;
                int i2 = this.f + i;
                str = "LruCache[maxSize=" + this.a + ",hits=" + this.e + ",misses=" + this.f + ",hitRate=" + (i2 != 0 ? (i * 100) / i2 : 0) + "%]";
            } catch (Throwable th) {
                throw th;
            }
        }
        return str;
    }
}
