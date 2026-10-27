package defpackage;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: renamed from: Ul, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0370Ul extends LinkedHashMap {
    public static final C0370Ul t;
    public boolean s;

    static {
        C0370Ul c0370Ul = new C0370Ul();
        t = c0370Ul;
        c0370Ul.s = false;
    }

    public C0370Ul() {
        this.s = true;
    }

    public final void a() {
        if (!this.s) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public void clear() {
        a();
        super.clear();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public Set entrySet() {
        return isEmpty() ? Collections.emptySet() : super.entrySet();
    }

    /* JADX WARN: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    @Override // java.util.AbstractMap, java.util.Map
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean equals(java.lang.Object r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof java.util.Map
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L5e
            java.util.Map r7 = (java.util.Map) r7
            if (r6 != r7) goto Lc
        La:
            r7 = 1
            goto L5b
        Lc:
            int r0 = r6.size()
            int r3 = r7.size()
            if (r0 == r3) goto L18
        L16:
            r7 = 0
            goto L5b
        L18:
            java.util.Set r0 = r6.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L20:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto La
            java.lang.Object r3 = r0.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r4 = r3.getKey()
            boolean r4 = r7.containsKey(r4)
            if (r4 != 0) goto L37
            goto L16
        L37:
            java.lang.Object r4 = r3.getValue()
            java.lang.Object r3 = r3.getKey()
            java.lang.Object r3 = r7.get(r3)
            boolean r5 = r4 instanceof byte[]
            if (r5 == 0) goto L54
            boolean r5 = r3 instanceof byte[]
            if (r5 == 0) goto L54
            byte[] r4 = (byte[]) r4
            byte[] r3 = (byte[]) r3
            boolean r3 = java.util.Arrays.equals(r4, r3)
            goto L58
        L54:
            boolean r3 = r4.equals(r3)
        L58:
            if (r3 != 0) goto L20
            goto L16
        L5b:
            if (r7 == 0) goto L5e
            goto L5f
        L5e:
            r1 = 0
        L5f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0370Ul.equals(java.lang.Object):boolean");
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int hashCode() {
        int iD = 0;
        for (Map.Entry entry : entrySet()) {
            Object key = entry.getKey();
            int iD2 = key instanceof byte[] ? AbstractC0119Gl.d((byte[]) key) : key.hashCode();
            Object value = entry.getValue();
            iD += (value instanceof byte[] ? AbstractC0119Gl.d((byte[]) value) : value.hashCode()) ^ iD2;
        }
        return iD;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public Object put(Object obj, Object obj2) {
        a();
        AbstractC0119Gl.a(obj);
        if (obj2 != null) {
            return super.put(obj, obj2);
        }
        throw null;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public void putAll(Map map) {
        a();
        for (Object obj : map.keySet()) {
            AbstractC0119Gl.a(obj);
            AbstractC0119Gl.a(map.get(obj));
        }
        super.putAll(map);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public Object remove(Object obj) {
        a();
        return super.remove(obj);
    }

    public C0370Ul(Map map) {
        super(map);
        this.s = true;
    }
}
