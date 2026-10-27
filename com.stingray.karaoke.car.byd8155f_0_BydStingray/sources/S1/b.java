package S1;

import A7.m;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import m7.i;
import n7.AbstractC1998m;
import n7.AbstractC2000o;
import n7.z;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map f7864a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final P1.a f7865b;

    public b(Map map, boolean z6) {
        m.f(map, "preferencesMap");
        this.f7864a = map;
        this.f7865b = new P1.a(z6);
    }

    public final Map a() {
        i iVar;
        Set<Map.Entry> setEntrySet = this.f7864a.entrySet();
        int iQ = z.q(AbstractC2000o.J(setEntrySet, 10));
        if (iQ < 16) {
            iQ = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iQ);
        for (Map.Entry entry : setEntrySet) {
            Object value = entry.getValue();
            if (value instanceof byte[]) {
                Object key = entry.getKey();
                byte[] bArr = (byte[]) value;
                byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
                m.e(bArrCopyOf, "copyOf(this, size)");
                iVar = new i(key, bArrCopyOf);
            } else {
                iVar = new i(entry.getKey(), entry.getValue());
            }
            linkedHashMap.put(iVar.l, iVar.f19259m);
        }
        Map mapUnmodifiableMap = Collections.unmodifiableMap(linkedHashMap);
        m.e(mapUnmodifiableMap, "unmodifiableMap(map)");
        return mapUnmodifiableMap;
    }

    public final void b() {
        if (this.f7865b.f5266a.get()) {
            throw new IllegalStateException("Do mutate preferences once returned to DataStore.");
        }
    }

    public final Object c(d dVar) {
        m.f(dVar, "key");
        Object obj = this.f7864a.get(dVar);
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
        m.e(bArrCopyOf, "copyOf(this, size)");
        return bArrCopyOf;
    }

    public final void d(d dVar, Object obj) {
        m.f(dVar, "key");
        e(dVar, obj);
    }

    public final void e(d dVar, Object obj) {
        m.f(dVar, "key");
        b();
        Map map = this.f7864a;
        if (obj == null) {
            b();
            map.remove(dVar);
            return;
        }
        if (obj instanceof Set) {
            Set setUnmodifiableSet = Collections.unmodifiableSet(AbstractC1998m.D0((Set) obj));
            m.e(setUnmodifiableSet, "unmodifiableSet(set.toSet())");
            map.put(dVar, setUnmodifiableSet);
        } else {
            if (!(obj instanceof byte[])) {
                map.put(dVar, obj);
                return;
            }
            byte[] bArr = (byte[]) obj;
            byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
            m.e(bArrCopyOf, "copyOf(this, size)");
            map.put(dVar, bArrCopyOf);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0060  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof S1.b
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            S1.b r6 = (S1.b) r6
            java.util.Map r0 = r6.f7864a
            java.util.Map r5 = r5.f7864a
            r2 = 1
            if (r0 != r5) goto L10
            return r2
        L10:
            int r0 = r0.size()
            int r3 = r5.size()
            if (r0 == r3) goto L1b
            return r1
        L1b:
            java.util.Map r6 = r6.f7864a
            boolean r0 = r6.isEmpty()
            if (r0 == 0) goto L25
        L23:
            r1 = r2
            goto L63
        L25:
            java.util.Set r6 = r6.entrySet()
            java.util.Iterator r6 = r6.iterator()
        L2d:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L23
            java.lang.Object r0 = r6.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            java.lang.Object r3 = r0.getKey()
            java.lang.Object r3 = r5.get(r3)
            if (r3 == 0) goto L60
            java.lang.Object r0 = r0.getValue()
            boolean r4 = r0 instanceof byte[]
            if (r4 == 0) goto L5b
            boolean r4 = r3 instanceof byte[]
            if (r4 == 0) goto L60
            byte[] r0 = (byte[]) r0
            byte[] r3 = (byte[]) r3
            boolean r0 = java.util.Arrays.equals(r0, r3)
            if (r0 == 0) goto L60
            r0 = r2
            goto L61
        L5b:
            boolean r0 = A7.m.a(r0, r3)
            goto L61
        L60:
            r0 = r1
        L61:
            if (r0 != 0) goto L2d
        L63:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: S1.b.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        Iterator it = this.f7864a.entrySet().iterator();
        int iHashCode = 0;
        while (it.hasNext()) {
            Object value = ((Map.Entry) it.next()).getValue();
            iHashCode += value instanceof byte[] ? Arrays.hashCode((byte[]) value) : value.hashCode();
        }
        return iHashCode;
    }

    public final String toString() {
        return AbstractC1998m.f0(this.f7864a.entrySet(), ",\n", "{\n", "\n}", a.f7863m, 24);
    }

    public /* synthetic */ b(boolean z6) {
        this(new LinkedHashMap(), z6);
    }
}
