package defpackage;

import kotlin.jvm.internal.SourceDebugExtension;

/* JADX INFO: compiled from: LruCache.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nLruCache.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LruCache.kt\nandroidx/collection/LruCache\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 LockExt.kt\nandroidx/collection/internal/LockExtKt\n+ 4 Lock.jvm.kt\nandroidx/collection/internal/Lock\n+ 5 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,355:1\n1#2:356\n23#3,3:357\n23#3,3:361\n23#3,3:365\n23#3,3:369\n23#3,3:373\n23#3,3:377\n23#3,3:381\n23#3,3:385\n23#3,3:389\n23#3,3:393\n23#3,3:397\n23#3,3:401\n23#3,3:405\n23#3,3:409\n23#3,3:415\n26#4:360\n26#4:364\n26#4:368\n26#4:372\n26#4:376\n26#4:380\n26#4:384\n26#4:388\n26#4:392\n26#4:396\n26#4:400\n26#4:404\n26#4:408\n26#4:412\n26#4:418\n1855#5,2:413\n*S KotlinDebug\n*F\n+ 1 LruCache.kt\nandroidx/collection/LruCache\n*L\n65#1:357,3\n78#1:361,3\n95#1:365,3\n122#1:369,3\n151#1:373,3\n180#1:377,3\n255#1:381,3\n262#1:385,3\n268#1:389,3\n274#1:393,3\n279#1:397,3\n284#1:401,3\n289#1:405,3\n299#1:409,3\n308#1:415,3\n65#1:360\n78#1:364\n95#1:368\n122#1:372\n151#1:376\n180#1:380\n255#1:384\n262#1:388\n268#1:392\n274#1:396\n279#1:400\n284#1:404\n289#1:408\n299#1:412\n308#1:418\n300#1:413,2\n*E\n"})
public class ua1<K, V> {
    public final int a;
    public final va1<K, V> b;
    public final a8 c;
    public int d;
    public int e;
    public int f;

    public ua1(int i) {
        this.a = i;
        if (!(i > 0)) {
            throw new IllegalArgumentException("maxSize <= 0".toString());
        }
        this.b = new va1<>();
        this.c = new a8();
    }

    public V a(K k) {
        i21.e(k, "key");
        return null;
    }

    public final V b(K k) {
        V vA;
        i21.e(k, "key");
        synchronized (this.c) {
            va1<K, V> va1Var = this.b;
            va1Var.getClass();
            V v = va1Var.a.get(k);
            if (v != null) {
                this.e++;
                return v;
            }
            this.f++;
            V vA2 = a(k);
            if (vA2 == null) {
                return null;
            }
            synchronized (this.c) {
                vA = this.b.a(k, vA2);
                if (vA != null) {
                    this.b.a(k, vA);
                } else {
                    this.d += d(k, vA2);
                    vi3 vi3Var = vi3.a;
                }
            }
            if (vA != null) {
                return vA;
            }
            f(this.a);
            return vA2;
        }
    }

    public final V c(K k, V v) {
        V vA;
        i21.e(k, "key");
        i21.e(v, "value");
        synchronized (this.c) {
            this.d += d(k, v);
            vA = this.b.a(k, v);
            if (vA != null) {
                this.d -= d(k, vA);
            }
            vi3 vi3Var = vi3.a;
        }
        f(this.a);
        return vA;
    }

    public final int d(K k, V v) {
        int iE = e(k, v);
        if (iE >= 0) {
            return iE;
        }
        throw new IllegalStateException(("Negative size: " + k + '=' + v).toString());
    }

    public int e(K k, V v) {
        i21.e(k, "key");
        i21.e(v, "value");
        return 1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x0088, code lost:
    
        return;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f(int r6) {
        /*
            r5 = this;
        L0:
            a8 r0 = r5.c
            monitor-enter(r0)
            int r1 = r5.d     // Catch: java.lang.Throwable -> L95
            r2 = 0
            if (r1 < 0) goto L18
            va1<K, V> r1 = r5.b     // Catch: java.lang.Throwable -> L95
            java.util.LinkedHashMap<K, V> r1 = r1.a     // Catch: java.lang.Throwable -> L95
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L95
            if (r1 == 0) goto L16
            int r1 = r5.d     // Catch: java.lang.Throwable -> L95
            if (r1 != 0) goto L18
        L16:
            r1 = 1
            goto L19
        L18:
            r1 = r2
        L19:
            if (r1 == 0) goto L89
            int r1 = r5.d     // Catch: java.lang.Throwable -> L95
            if (r1 <= r6) goto L87
            va1<K, V> r1 = r5.b     // Catch: java.lang.Throwable -> L95
            java.util.LinkedHashMap<K, V> r1 = r1.a     // Catch: java.lang.Throwable -> L95
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L95
            if (r1 == 0) goto L2a
            goto L87
        L2a:
            va1<K, V> r1 = r5.b     // Catch: java.lang.Throwable -> L95
            java.util.LinkedHashMap<K, V> r1 = r1.a     // Catch: java.lang.Throwable -> L95
            java.util.Set r1 = r1.entrySet()     // Catch: java.lang.Throwable -> L95
            java.lang.String r3 = "map.entries"
            defpackage.i21.d(r1, r3)     // Catch: java.lang.Throwable -> L95
            boolean r3 = r1 instanceof java.util.List     // Catch: java.lang.Throwable -> L95
            if (r3 == 0) goto L49
            java.util.List r1 = (java.util.List) r1     // Catch: java.lang.Throwable -> L95
            boolean r3 = r1.isEmpty()     // Catch: java.lang.Throwable -> L95
            if (r3 == 0) goto L44
            goto L53
        L44:
            java.lang.Object r1 = r1.get(r2)     // Catch: java.lang.Throwable -> L95
            goto L59
        L49:
            java.util.Iterator r1 = r1.iterator()     // Catch: java.lang.Throwable -> L95
            boolean r2 = r1.hasNext()     // Catch: java.lang.Throwable -> L95
            if (r2 != 0) goto L55
        L53:
            r1 = 0
            goto L59
        L55:
            java.lang.Object r1 = r1.next()     // Catch: java.lang.Throwable -> L95
        L59:
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch: java.lang.Throwable -> L95
            if (r1 != 0) goto L5f
            monitor-exit(r0)
            return
        L5f:
            java.lang.Object r2 = r1.getKey()     // Catch: java.lang.Throwable -> L95
            java.lang.Object r1 = r1.getValue()     // Catch: java.lang.Throwable -> L95
            va1<K, V> r3 = r5.b     // Catch: java.lang.Throwable -> L95
            r3.getClass()     // Catch: java.lang.Throwable -> L95
            java.lang.String r4 = "key"
            defpackage.i21.e(r2, r4)     // Catch: java.lang.Throwable -> L95
            java.util.LinkedHashMap<K, V> r3 = r3.a     // Catch: java.lang.Throwable -> L95
            r3.remove(r2)     // Catch: java.lang.Throwable -> L95
            int r3 = r5.d     // Catch: java.lang.Throwable -> L95
            int r2 = r5.d(r2, r1)     // Catch: java.lang.Throwable -> L95
            int r3 = r3 - r2
            r5.d = r3     // Catch: java.lang.Throwable -> L95
            monitor-exit(r0)
            java.lang.String r0 = "oldValue"
            defpackage.i21.e(r1, r0)
            goto L0
        L87:
            monitor-exit(r0)
            return
        L89:
            java.lang.String r5 = "LruCache.sizeOf() is reporting inconsistent results!"
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L95
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L95
            r6.<init>(r5)     // Catch: java.lang.Throwable -> L95
            throw r6     // Catch: java.lang.Throwable -> L95
        L95:
            r5 = move-exception
            monitor-exit(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ua1.f(int):void");
    }

    public final String toString() {
        String str;
        synchronized (this.c) {
            int i = this.e;
            int i2 = this.f + i;
            str = "LruCache[maxSize=" + this.a + ",hits=" + this.e + ",misses=" + this.f + ",hitRate=" + (i2 != 0 ? (i * 100) / i2 : 0) + "%]";
        }
        return str;
    }
}
