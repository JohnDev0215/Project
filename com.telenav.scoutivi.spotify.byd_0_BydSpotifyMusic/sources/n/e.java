package n;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Objects;

/* JADX INFO: compiled from: LruCache.java */
/* JADX INFO: loaded from: classes.dex */
public class e<K, V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final LinkedHashMap<K, V> f8456a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f8457b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f8458c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f8459d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f8460e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f8461f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f8462g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f8463h;

    public e(int i7) {
        if (i7 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.f8458c = i7;
        this.f8456a = new LinkedHashMap<>(0, 0.75f, true);
    }

    private int e(K k7, V v6) {
        int iF = f(k7, v6);
        if (iF >= 0) {
            return iF;
        }
        throw new IllegalStateException("Negative size: " + k7 + "=" + v6);
    }

    protected V a(K k7) {
        return null;
    }

    protected void b(boolean z6, K k7, V v6, V v7) {
    }

    public final V c(K k7) {
        V vPut;
        Objects.requireNonNull(k7, "key == null");
        synchronized (this) {
            V v6 = this.f8456a.get(k7);
            if (v6 != null) {
                this.f8462g++;
                return v6;
            }
            this.f8463h++;
            V vA = a(k7);
            if (vA == null) {
                return null;
            }
            synchronized (this) {
                this.f8460e++;
                vPut = this.f8456a.put(k7, vA);
                if (vPut != null) {
                    this.f8456a.put(k7, vPut);
                } else {
                    this.f8457b += e(k7, vA);
                }
            }
            if (vPut != null) {
                b(false, k7, vA, vPut);
                return vPut;
            }
            g(this.f8458c);
            return vA;
        }
    }

    public final V d(K k7, V v6) {
        V vPut;
        if (k7 == null || v6 == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            this.f8459d++;
            this.f8457b += e(k7, v6);
            vPut = this.f8456a.put(k7, v6);
            if (vPut != null) {
                this.f8457b -= e(k7, vPut);
            }
        }
        if (vPut != null) {
            b(false, k7, vPut, v6);
        }
        g(this.f8458c);
        return vPut;
    }

    protected int f(K k7, V v6) {
        return 1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0070, code lost:
    
        throw new java.lang.IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void g(int r5) {
        /*
            r4 = this;
        L0:
            monitor-enter(r4)
            int r0 = r4.f8457b     // Catch: java.lang.Throwable -> L71
            if (r0 < 0) goto L52
            java.util.LinkedHashMap<K, V> r0 = r4.f8456a     // Catch: java.lang.Throwable -> L71
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> L71
            if (r0 == 0) goto L11
            int r0 = r4.f8457b     // Catch: java.lang.Throwable -> L71
            if (r0 != 0) goto L52
        L11:
            int r0 = r4.f8457b     // Catch: java.lang.Throwable -> L71
            if (r0 <= r5) goto L50
            java.util.LinkedHashMap<K, V> r0 = r4.f8456a     // Catch: java.lang.Throwable -> L71
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> L71
            if (r0 == 0) goto L1e
            goto L50
        L1e:
            java.util.LinkedHashMap<K, V> r0 = r4.f8456a     // Catch: java.lang.Throwable -> L71
            java.util.Set r0 = r0.entrySet()     // Catch: java.lang.Throwable -> L71
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Throwable -> L71
            java.lang.Object r0 = r0.next()     // Catch: java.lang.Throwable -> L71
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0     // Catch: java.lang.Throwable -> L71
            java.lang.Object r1 = r0.getKey()     // Catch: java.lang.Throwable -> L71
            java.lang.Object r0 = r0.getValue()     // Catch: java.lang.Throwable -> L71
            java.util.LinkedHashMap<K, V> r2 = r4.f8456a     // Catch: java.lang.Throwable -> L71
            r2.remove(r1)     // Catch: java.lang.Throwable -> L71
            int r2 = r4.f8457b     // Catch: java.lang.Throwable -> L71
            int r3 = r4.e(r1, r0)     // Catch: java.lang.Throwable -> L71
            int r2 = r2 - r3
            r4.f8457b = r2     // Catch: java.lang.Throwable -> L71
            int r2 = r4.f8461f     // Catch: java.lang.Throwable -> L71
            r3 = 1
            int r2 = r2 + r3
            r4.f8461f = r2     // Catch: java.lang.Throwable -> L71
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L71
            r2 = 0
            r4.b(r3, r1, r0, r2)
            goto L0
        L50:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L71
            return
        L52:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L71
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L71
            r0.<init>()     // Catch: java.lang.Throwable -> L71
            java.lang.Class r1 = r4.getClass()     // Catch: java.lang.Throwable -> L71
            java.lang.String r1 = r1.getName()     // Catch: java.lang.Throwable -> L71
            r0.append(r1)     // Catch: java.lang.Throwable -> L71
            java.lang.String r1 = ".sizeOf() is reporting inconsistent results!"
            r0.append(r1)     // Catch: java.lang.Throwable -> L71
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L71
            r5.<init>(r0)     // Catch: java.lang.Throwable -> L71
            throw r5     // Catch: java.lang.Throwable -> L71
        L71:
            r5 = move-exception
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L71
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: n.e.g(int):void");
    }

    public final synchronized String toString() {
        int i7;
        int i8;
        i7 = this.f8462g;
        i8 = this.f8463h + i7;
        return String.format(Locale.US, "LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", Integer.valueOf(this.f8458c), Integer.valueOf(this.f8462g), Integer.valueOf(this.f8463h), Integer.valueOf(i8 != 0 ? (i7 * 100) / i8 : 0));
    }
}
