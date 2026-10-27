package com.google.common.collect;

import defpackage.xi3;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: compiled from: RegularImmutableMap.java */
/* JADX INFO: loaded from: classes.dex */
public final class e<K, V> extends com.google.common.collect.c<K, V> {
    public static final e k = new e(0, null, new Object[0]);
    private static final long serialVersionUID = 0;
    public final transient Object h;
    public final transient Object[] i;
    public final transient int j;

    /* JADX INFO: compiled from: RegularImmutableMap.java */
    public static class a<K, V> extends d<Map.Entry<K, V>> {
        public final transient com.google.common.collect.c<K, V> g;
        public final transient Object[] h;
        public final transient int i = 0;
        public final transient int j;

        /* JADX INFO: renamed from: com.google.common.collect.e$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: RegularImmutableMap.java */
        public class C0041a extends com.google.common.collect.b<Map.Entry<K, V>> {
            public C0041a() {
            }

            @Override // java.util.List
            public final Object get(int i) {
                a aVar = a.this;
                int i2 = aVar.j;
                int i3 = i * 2;
                int i4 = aVar.i;
                Object[] objArr = aVar.h;
                Object obj = objArr[i4 + i3];
                Objects.requireNonNull(obj);
                Object obj2 = objArr[i3 + (i4 ^ 1)];
                Objects.requireNonNull(obj2);
                return new AbstractMap.SimpleImmutableEntry(obj, obj2);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
            public final int size() {
                return a.this.j;
            }
        }

        public a(com.google.common.collect.c cVar, Object[] objArr, int i) {
            this.g = cVar;
            this.h = objArr;
            this.j = i;
        }

        @Override // com.google.common.collect.a
        public final int a(int i, Object[] objArr) {
            return p().a(i, objArr);
        }

        @Override // com.google.common.collect.a, java.util.AbstractCollection, java.util.Collection
        public final boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            return value != null && value.equals(this.g.get(key));
        }

        @Override // com.google.common.collect.d, com.google.common.collect.a, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        /* JADX INFO: renamed from: f */
        public final xi3<Map.Entry<K, V>> iterator() {
            return p().listIterator(0);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final int size() {
            return this.j;
        }

        @Override // com.google.common.collect.d
        public final com.google.common.collect.b<Map.Entry<K, V>> u() {
            return new C0041a();
        }
    }

    /* JADX INFO: compiled from: RegularImmutableMap.java */
    public static final class b<K> extends d<K> {
        public final transient com.google.common.collect.c<K, ?> g;
        public final transient com.google.common.collect.b<K> h;

        public b(com.google.common.collect.c cVar, c cVar2) {
            this.g = cVar;
            this.h = cVar2;
        }

        @Override // com.google.common.collect.a
        public final int a(int i, Object[] objArr) {
            return this.h.a(i, objArr);
        }

        @Override // com.google.common.collect.a, java.util.AbstractCollection, java.util.Collection
        public final boolean contains(Object obj) {
            return this.g.get(obj) != null;
        }

        @Override // com.google.common.collect.d, com.google.common.collect.a, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        /* JADX INFO: renamed from: f */
        public final xi3<K> iterator() {
            return this.h.listIterator(0);
        }

        @Override // com.google.common.collect.d
        public final com.google.common.collect.b<K> p() {
            return this.h;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final int size() {
            return this.g.size();
        }
    }

    /* JADX INFO: compiled from: RegularImmutableMap.java */
    public static final class c extends com.google.common.collect.b<Object> {
        public final transient Object[] g;
        public final transient int h;
        public final transient int i;

        public c(int i, int i2, Object[] objArr) {
            this.g = objArr;
            this.h = i;
            this.i = i2;
        }

        @Override // java.util.List
        public final Object get(int i) {
            Object obj = this.g[(i * 2) + this.h];
            Objects.requireNonNull(obj);
            return obj;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public final int size() {
            return this.i;
        }
    }

    public e(int i, Object obj, Object[] objArr) {
        this.h = obj;
        this.i = objArr;
        this.j = i;
    }

    @Override // com.google.common.collect.c
    public final a a() {
        return new a(this, this.i, this.j);
    }

    @Override // com.google.common.collect.c
    public final b b() {
        return new b(this, new c(0, this.j, this.i));
    }

    @Override // com.google.common.collect.c
    public final c c() {
        return new c(1, this.j, this.i);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001e  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x001e A[EDGE_INSN: B:45:0x001e->B:10:0x001e BREAK  A[LOOP:0: B:17:0x0039->B:23:0x004f], EDGE_INSN: B:47:0x001e->B:10:0x001e BREAK  A[LOOP:1: B:27:0x0064->B:33:0x007b], EDGE_INSN: B:49:0x001e->B:10:0x001e BREAK  A[LOOP:2: B:35:0x008a->B:44:0x00a0]] */
    @Override // com.google.common.collect.c, java.util.Map
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final V get(java.lang.Object r9) {
        /*
            r8 = this;
            r0 = 0
            if (r9 != 0) goto L4
            goto L1e
        L4:
            r1 = 1
            java.lang.Object[] r2 = r8.i
            int r3 = r8.j
            if (r3 != r1) goto L21
            r8 = 0
            r8 = r2[r8]
            java.util.Objects.requireNonNull(r8)
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L1e
            r8 = r2[r1]
            java.util.Objects.requireNonNull(r8)
            goto L9c
        L1e:
            r8 = r0
            goto L9c
        L21:
            java.lang.Object r8 = r8.h
            if (r8 != 0) goto L26
            goto L1e
        L26:
            boolean r3 = r8 instanceof byte[]
            r4 = -1
            if (r3 == 0) goto L52
            r3 = r8
            byte[] r3 = (byte[]) r3
            int r8 = r3.length
            int r5 = r8 + (-1)
            int r8 = r9.hashCode()
            int r8 = defpackage.n3.o1(r8)
        L39:
            r8 = r8 & r5
            r4 = r3[r8]
            r6 = 255(0xff, float:3.57E-43)
            r4 = r4 & r6
            if (r4 != r6) goto L42
            goto L1e
        L42:
            r6 = r2[r4]
            boolean r6 = r9.equals(r6)
            if (r6 == 0) goto L4f
            r8 = r4 ^ 1
            r8 = r2[r8]
            goto L9c
        L4f:
            int r8 = r8 + 1
            goto L39
        L52:
            boolean r3 = r8 instanceof short[]
            if (r3 == 0) goto L7e
            r3 = r8
            short[] r3 = (short[]) r3
            int r8 = r3.length
            int r5 = r8 + (-1)
            int r8 = r9.hashCode()
            int r8 = defpackage.n3.o1(r8)
        L64:
            r8 = r8 & r5
            short r4 = r3[r8]
            r6 = 65535(0xffff, float:9.1834E-41)
            r4 = r4 & r6
            if (r4 != r6) goto L6e
            goto L1e
        L6e:
            r6 = r2[r4]
            boolean r6 = r9.equals(r6)
            if (r6 == 0) goto L7b
            r8 = r4 ^ 1
            r8 = r2[r8]
            goto L9c
        L7b:
            int r8 = r8 + 1
            goto L64
        L7e:
            int[] r8 = (int[]) r8
            int r3 = r8.length
            int r3 = r3 - r1
            int r5 = r9.hashCode()
            int r5 = defpackage.n3.o1(r5)
        L8a:
            r5 = r5 & r3
            r6 = r8[r5]
            if (r6 != r4) goto L90
            goto L1e
        L90:
            r7 = r2[r6]
            boolean r7 = r9.equals(r7)
            if (r7 == 0) goto La0
            r8 = r6 ^ 1
            r8 = r2[r8]
        L9c:
            if (r8 != 0) goto L9f
            return r0
        L9f:
            return r8
        La0:
            int r5 = r5 + 1
            goto L8a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.e.get(java.lang.Object):java.lang.Object");
    }

    @Override // java.util.Map
    public final int size() {
        return this.j;
    }
}
