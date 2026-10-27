package s;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Objects;

/* JADX INFO: compiled from: LruCache.java */
/* JADX INFO: loaded from: classes.dex */
public class e<K, V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedHashMap<K, V> f4861a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4862b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4863c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4864d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4865e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4866f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4867g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f4868h;

    public e(int i3) {
        if (i3 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.f4863c = i3;
        this.f4861a = new LinkedHashMap<>(0, 0.75f, true);
    }

    public V a(K k3) {
        return null;
    }

    public void b(boolean z3, K k3, V v3, V v4) {
    }

    public final V c(K k3) {
        V vPut;
        Objects.requireNonNull(k3, "key == null");
        synchronized (this) {
            V v3 = this.f4861a.get(k3);
            if (v3 != null) {
                this.f4867g++;
                return v3;
            }
            this.f4868h++;
            V vA = a(k3);
            if (vA == null) {
                return null;
            }
            synchronized (this) {
                this.f4865e++;
                vPut = this.f4861a.put(k3, vA);
                if (vPut != null) {
                    this.f4861a.put(k3, vPut);
                } else {
                    this.f4862b += e(k3, vA);
                }
            }
            if (vPut != null) {
                b(false, k3, vA, vPut);
                return vPut;
            }
            g(this.f4863c);
            return vA;
        }
    }

    public final V d(K k3, V v3) {
        V vPut;
        if (k3 == null || v3 == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            this.f4864d++;
            this.f4862b += e(k3, v3);
            vPut = this.f4861a.put(k3, v3);
            if (vPut != null) {
                this.f4862b -= e(k3, vPut);
            }
        }
        if (vPut != null) {
            b(false, k3, vPut, v3);
        }
        g(this.f4863c);
        return vPut;
    }

    public final int e(K k3, V v3) {
        int iF = f(k3, v3);
        if (iF >= 0) {
            return iF;
        }
        throw new IllegalStateException("Negative size: " + k3 + "=" + v3);
    }

    public int f(K k3, V v3) {
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
            int r0 = r4.f4862b     // Catch: java.lang.Throwable -> L71
            if (r0 < 0) goto L52
            java.util.LinkedHashMap<K, V> r0 = r4.f4861a     // Catch: java.lang.Throwable -> L71
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> L71
            if (r0 == 0) goto L11
            int r0 = r4.f4862b     // Catch: java.lang.Throwable -> L71
            if (r0 != 0) goto L52
        L11:
            int r0 = r4.f4862b     // Catch: java.lang.Throwable -> L71
            if (r0 <= r5) goto L50
            java.util.LinkedHashMap<K, V> r0 = r4.f4861a     // Catch: java.lang.Throwable -> L71
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> L71
            if (r0 == 0) goto L1e
            goto L50
        L1e:
            java.util.LinkedHashMap<K, V> r0 = r4.f4861a     // Catch: java.lang.Throwable -> L71
            java.util.Set r0 = r0.entrySet()     // Catch: java.lang.Throwable -> L71
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Throwable -> L71
            java.lang.Object r0 = r0.next()     // Catch: java.lang.Throwable -> L71
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0     // Catch: java.lang.Throwable -> L71
            java.lang.Object r1 = r0.getKey()     // Catch: java.lang.Throwable -> L71
            java.lang.Object r0 = r0.getValue()     // Catch: java.lang.Throwable -> L71
            java.util.LinkedHashMap<K, V> r2 = r4.f4861a     // Catch: java.lang.Throwable -> L71
            r2.remove(r1)     // Catch: java.lang.Throwable -> L71
            int r2 = r4.f4862b     // Catch: java.lang.Throwable -> L71
            int r3 = r4.e(r1, r0)     // Catch: java.lang.Throwable -> L71
            int r2 = r2 - r3
            r4.f4862b = r2     // Catch: java.lang.Throwable -> L71
            int r2 = r4.f4866f     // Catch: java.lang.Throwable -> L71
            r3 = 1
            int r2 = r2 + r3
            r4.f4866f = r2     // Catch: java.lang.Throwable -> L71
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
        throw new UnsupportedOperationException("Method not decompiled: s.e.g(int):void");
    }

    public final synchronized String toString() {
        int i3;
        int i4;
        i3 = this.f4867g;
        i4 = this.f4868h + i3;
        return String.format(Locale.US, "LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", Integer.valueOf(this.f4863c), Integer.valueOf(this.f4867g), Integer.valueOf(this.f4868h), Integer.valueOf(i4 != 0 ? (i3 * 100) / i4 : 0));
    }
}
