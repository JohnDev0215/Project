package spi;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public final class eh implements m0 {
    public final ae<a, Object> a;
    public final b b;
    public final Map<Class<?>, NavigableMap<Integer, Integer>> c;
    public final Map<Class<?>, l0<?>> d;
    public final int e;
    public int f;

    public static final class a implements al {
        public final b a;
        public int b;
        public Class<?> c;

        public a(b bVar) {
            this.a = bVar;
        }

        @Override // spi.al
        public final void a() {
            this.a.c(this);
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.b == aVar.b && this.c == aVar.c;
        }

        public final int hashCode() {
            int i = this.b * 31;
            Class<?> cls = this.c;
            return i + (cls != null ? cls.hashCode() : 0);
        }

        public final String toString() {
            StringBuilder sbK = e.k("Key{size=");
            sbK.append(this.b);
            sbK.append("array=");
            sbK.append(this.c);
            sbK.append('}');
            return sbK.toString();
        }
    }

    public static final class b extends g1<a> {
        @Override // spi.g1
        public final al a() {
            return new a(this);
        }

        public final a d(int i, Class<?> cls) {
            a aVarB = b();
            aVarB.b = i;
            aVarB.c = cls;
            return aVarB;
        }
    }

    @VisibleForTesting
    public eh() {
        this.a = new ae<>();
        this.b = new b();
        this.c = new HashMap();
        this.d = new HashMap();
        this.e = 4194304;
    }

    public eh(int i) {
        this.a = new ae<>();
        this.b = new b();
        this.c = new HashMap();
        this.d = new HashMap();
        this.e = i;
    }

    @Override // spi.m0
    public final synchronized void a(int i) {
        try {
            if (i >= 40) {
                synchronized (this) {
                    g(0);
                }
            } else if (i >= 20 || i == 15) {
                g(this.e / 2);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // spi.m0
    public final synchronized void b() {
        g(0);
    }

    @Override // spi.m0
    public final synchronized <T> void c(T t) {
        Class<?> cls = t.getClass();
        l0<T> l0VarH = h(cls);
        int iB = l0VarH.b(t);
        int iA = l0VarH.a() * iB;
        int iIntValue = 1;
        if (iA <= this.e / 2) {
            a aVarD = this.b.d(iB, cls);
            this.a.b(aVarD, t);
            NavigableMap<Integer, Integer> navigableMapJ = j(cls);
            Integer num = navigableMapJ.get(Integer.valueOf(aVarD.b));
            Integer numValueOf = Integer.valueOf(aVarD.b);
            if (num != null) {
                iIntValue = 1 + num.intValue();
            }
            navigableMapJ.put(numValueOf, Integer.valueOf(iIntValue));
            this.f += iA;
            g(this.e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x002f A[Catch: all -> 0x0046, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0013, B:7:0x0017, B:13:0x0023, B:18:0x002f, B:20:0x0040, B:19:0x003a), top: B:26:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x003a A[Catch: all -> 0x0046, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0013, B:7:0x0017, B:13:0x0023, B:18:0x002f, B:20:0x0040, B:19:0x003a), top: B:26:0x0001 }] */
    @Override // spi.m0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized <T> T d(int r6, java.lang.Class<T> r7) {
        /*
            r5 = this;
            monitor-enter(r5)
            java.util.NavigableMap r0 = r5.j(r7)     // Catch: java.lang.Throwable -> L46
            java.lang.Integer r1 = java.lang.Integer.valueOf(r6)     // Catch: java.lang.Throwable -> L46
            java.lang.Object r0 = r0.ceilingKey(r1)     // Catch: java.lang.Throwable -> L46
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch: java.lang.Throwable -> L46
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L2c
            int r3 = r5.f     // Catch: java.lang.Throwable -> L46
            if (r3 == 0) goto L20
            int r4 = r5.e     // Catch: java.lang.Throwable -> L46
            int r4 = r4 / r3
            r3 = 2
            if (r4 < r3) goto L1e
            goto L20
        L1e:
            r3 = r2
            goto L21
        L20:
            r3 = r1
        L21:
            if (r3 != 0) goto L2d
            int r3 = r0.intValue()     // Catch: java.lang.Throwable -> L46
            int r4 = r6 * 8
            if (r3 > r4) goto L2c
            goto L2d
        L2c:
            r1 = r2
        L2d:
            if (r1 == 0) goto L3a
            spi.eh$b r6 = r5.b     // Catch: java.lang.Throwable -> L46
            int r0 = r0.intValue()     // Catch: java.lang.Throwable -> L46
            spi.eh$a r6 = r6.d(r0, r7)     // Catch: java.lang.Throwable -> L46
            goto L40
        L3a:
            spi.eh$b r0 = r5.b     // Catch: java.lang.Throwable -> L46
            spi.eh$a r6 = r0.d(r6, r7)     // Catch: java.lang.Throwable -> L46
        L40:
            java.lang.Object r6 = r5.i(r6, r7)     // Catch: java.lang.Throwable -> L46
            monitor-exit(r5)
            return r6
        L46:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.eh.d(int, java.lang.Class):java.lang.Object");
    }

    @Override // spi.m0
    public final synchronized Object e() {
        return i(this.b.d(8, byte[].class), byte[].class);
    }

    public final void f(int i, Class<?> cls) {
        NavigableMap<Integer, Integer> navigableMapJ = j(cls);
        Integer num = navigableMapJ.get(Integer.valueOf(i));
        if (num == null) {
            throw new NullPointerException("Tried to decrement empty size, size: " + i + ", this: " + this);
        }
        int iIntValue = num.intValue();
        Integer numValueOf = Integer.valueOf(i);
        if (iIntValue == 1) {
            navigableMapJ.remove(numValueOf);
        } else {
            navigableMapJ.put(numValueOf, Integer.valueOf(num.intValue() - 1));
        }
    }

    public final void g(int i) {
        while (this.f > i) {
            Object objD = this.a.d();
            dl.b(objD);
            l0 l0VarH = h(objD.getClass());
            this.f -= l0VarH.a() * l0VarH.b(objD);
            f(l0VarH.b(objD), objD.getClass());
            if (Log.isLoggable(l0VarH.getTag(), 2)) {
                String tag = l0VarH.getTag();
                StringBuilder sbK = e.k("evicted: ");
                sbK.append(l0VarH.b(objD));
                Log.v(tag, sbK.toString());
            }
        }
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public final <T> l0<T> h(Class<T> cls) {
        l0<T> m2Var = (l0) this.d.get(cls);
        if (m2Var == null) {
            if (cls.equals(int[].class)) {
                m2Var = new zf();
            } else {
                if (!cls.equals(byte[].class)) {
                    StringBuilder sbK = e.k("No array pool found for: ");
                    sbK.append(cls.getSimpleName());
                    throw new IllegalArgumentException(sbK.toString());
                }
                m2Var = new m2();
            }
            this.d.put((Class<?>) cls, (l0<?>) m2Var);
        }
        return m2Var;
    }

    public final <T> T i(a aVar, Class<T> cls) {
        l0<T> l0VarH = h(cls);
        T t = (T) this.a.a(aVar);
        if (t != null) {
            this.f -= l0VarH.a() * l0VarH.b(t);
            f(l0VarH.b(t), cls);
        }
        if (t != null) {
            return t;
        }
        if (Log.isLoggable(l0VarH.getTag(), 2)) {
            String tag = l0VarH.getTag();
            StringBuilder sbK = e.k("Allocated ");
            sbK.append(aVar.b);
            sbK.append(" bytes");
            Log.v(tag, sbK.toString());
        }
        return l0VarH.newArray(aVar.b);
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public final NavigableMap<Integer, Integer> j(Class<?> cls) {
        NavigableMap<Integer, Integer> navigableMap = this.c.get(cls);
        if (navigableMap != null) {
            return navigableMap;
        }
        TreeMap treeMap = new TreeMap();
        this.c.put(cls, treeMap);
        return treeMap;
    }
}
