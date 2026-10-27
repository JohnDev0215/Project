package com.google.common.collect;

import com.google.common.collect.a;
import com.google.common.collect.e;
import defpackage.oj2;
import defpackage.xi3;
import defpackage.z7;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: ImmutableMap.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c<K, V> implements Map<K, V>, Serializable {
    public transient d<Map.Entry<K, V>> e;
    public transient d<K> f;
    public transient com.google.common.collect.a<V> g;

    /* JADX INFO: compiled from: ImmutableMap.java */
    public static class a<K, V> {
        public Object[] a;
        public int b = 0;
        public C0040a c;

        /* JADX INFO: renamed from: com.google.common.collect.c$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ImmutableMap.java */
        public static final class C0040a {
            public final Object a;
            public final Object b;
            public final Object c;

            public C0040a(Object obj, Object obj2, Object obj3) {
                this.a = obj;
                this.b = obj2;
                this.c = obj3;
            }

            public final IllegalArgumentException a() {
                Object obj = this.a;
                String strValueOf = String.valueOf(obj);
                String strValueOf2 = String.valueOf(this.b);
                String strValueOf3 = String.valueOf(obj);
                String strValueOf4 = String.valueOf(this.c);
                StringBuilder sb = new StringBuilder(strValueOf4.length() + strValueOf3.length() + strValueOf2.length() + strValueOf.length() + 39);
                sb.append("Multiple entries with same key: ");
                sb.append(strValueOf);
                sb.append("=");
                sb.append(strValueOf2);
                sb.append(" and ");
                sb.append(strValueOf3);
                sb.append("=");
                sb.append(strValueOf4);
                return new IllegalArgumentException(sb.toString());
            }
        }

        public a(int i) {
            this.a = new Object[i * 2];
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:71:0x0186  */
        /* JADX WARN: Type inference failed for: r15v0 */
        /* JADX WARN: Type inference failed for: r3v10, types: [java.lang.Object[]] */
        /* JADX WARN: Type inference failed for: r3v2, types: [int[]] */
        /* JADX WARN: Type inference failed for: r3v8 */
        /* JADX WARN: Type inference failed for: r4v14 */
        /* JADX WARN: Type inference failed for: r4v15, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r4v18 */
        /* JADX WARN: Type inference failed for: r4v19 */
        /* JADX WARN: Type inference failed for: r4v2 */
        /* JADX WARN: Type inference failed for: r4v22 */
        /* JADX WARN: Type inference failed for: r4v25 */
        /* JADX WARN: Type inference failed for: r4v26 */
        /* JADX WARN: Type inference failed for: r4v8 */
        /* JADX WARN: Type inference failed for: r4v9 */
        /* JADX WARN: Type inference failed for: r6v10 */
        /* JADX WARN: Type inference failed for: r6v4, types: [java.lang.Object[]] */
        /* JADX WARN: Type inference failed for: r6v8, types: [java.lang.Object[]] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final com.google.common.collect.e a() {
            /*
                Method dump skipped, instruction units count: 437
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.c.a.a():com.google.common.collect.e");
        }

        public final void b(Object obj, Object obj2) {
            int i = (this.b + 1) * 2;
            Object[] objArr = this.a;
            if (i > objArr.length) {
                this.a = Arrays.copyOf(objArr, a.AbstractC0038a.a(objArr.length, i));
            }
            if (obj == null) {
                String strValueOf = String.valueOf(obj2);
                StringBuilder sb = new StringBuilder(strValueOf.length() + 24);
                sb.append("null key in entry: null=");
                sb.append(strValueOf);
                throw new NullPointerException(sb.toString());
            }
            if (obj2 == null) {
                String strValueOf2 = String.valueOf(obj);
                StringBuilder sb2 = new StringBuilder(strValueOf2.length() + 26);
                sb2.append("null value in entry: ");
                sb2.append(strValueOf2);
                sb2.append("=null");
                throw new NullPointerException(sb2.toString());
            }
            Object[] objArr2 = this.a;
            int i2 = this.b;
            int i3 = i2 * 2;
            objArr2[i3] = obj;
            objArr2[i3 + 1] = obj2;
            this.b = i2 + 1;
        }
    }

    /* JADX INFO: compiled from: ImmutableMap.java */
    public static class b<K, V> implements Serializable {
        private static final long serialVersionUID = 0;
        public final Object[] e;
        public final Object[] f;

        public b(c<K, V> cVar) {
            Object[] objArr = new Object[cVar.size()];
            Object[] objArr2 = new Object[cVar.size()];
            e.a aVarA = cVar.e;
            if (aVarA == null) {
                aVarA = cVar.a();
                cVar.e = aVarA;
            }
            xi3<Map.Entry<K, V>> it = aVarA.iterator();
            int i = 0;
            while (it.hasNext()) {
                Map.Entry<K, V> next = it.next();
                objArr[i] = next.getKey();
                objArr2[i] = next.getValue();
                i++;
            }
            this.e = objArr;
            this.f = objArr2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final Object readResolve() {
            Object[] objArr = this.e;
            boolean z = objArr instanceof d;
            Object[] objArr2 = this.f;
            if (!z) {
                a aVar = new a(objArr.length);
                for (int i = 0; i < objArr.length; i++) {
                    aVar.b(objArr[i], objArr2[i]);
                }
                return aVar.a();
            }
            d dVar = (d) objArr;
            a aVar2 = new a(dVar.size());
            Iterator it = dVar.iterator();
            xi3 it2 = ((com.google.common.collect.a) objArr2).iterator();
            while (it.hasNext()) {
                aVar2.b(it.next(), it2.next());
            }
            return aVar2.a();
        }
    }

    public abstract e.a a();

    public abstract e.b b();

    public abstract e.c c();

    @Override // java.util.Map
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final boolean containsKey(Object obj) {
        return get(obj) != null;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        e.c cVarC = this.g;
        if (cVarC == null) {
            cVarC = c();
            this.g = cVarC;
        }
        return cVarC.contains(obj);
    }

    @Override // java.util.Map
    public final Set entrySet() {
        d<Map.Entry<K, V>> dVar = this.e;
        if (dVar != null) {
            return dVar;
        }
        e.a aVarA = a();
        this.e = aVarA;
        return aVarA;
    }

    @Override // java.util.Map
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    @Override // java.util.Map
    public abstract V get(Object obj);

    @Override // java.util.Map
    public final V getOrDefault(Object obj, V v) {
        V v2 = get(obj);
        return v2 != null ? v2 : v;
    }

    @Override // java.util.Map
    public final int hashCode() {
        e.a aVarA = this.e;
        if (aVarA == null) {
            aVarA = a();
            this.e = aVarA;
        }
        return z7.H(aVarA);
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.Map
    public final Set keySet() {
        d<K> dVar = this.f;
        if (dVar != null) {
            return dVar;
        }
        e.b bVarB = b();
        this.f = bVarB;
        return bVarB;
    }

    @Override // java.util.Map
    @Deprecated
    public final V put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public final void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public final V remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        int size = size();
        oj2.b(size, "size");
        StringBuilder sb = new StringBuilder((int) Math.min(((long) size) * 8, 1073741824L));
        sb.append('{');
        boolean z = true;
        for (Map.Entry entry : entrySet()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(entry.getKey());
            sb.append('=');
            sb.append(entry.getValue());
            z = false;
        }
        sb.append('}');
        return sb.toString();
    }

    @Override // java.util.Map
    public final Collection values() {
        com.google.common.collect.a<V> aVar = this.g;
        if (aVar != null) {
            return aVar;
        }
        e.c cVarC = c();
        this.g = cVarC;
        return cVarC;
    }

    public Object writeReplace() {
        return new b(this);
    }
}
