package androidx.datastore.preferences.protobuf;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/* JADX INFO: renamed from: androidx.datastore.preferences.protobuf.a0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0938a0 extends AbstractMap {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final /* synthetic */ int f11825r = 0;
    public final int l;
    public boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public volatile g0 f11828p;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public List f11826m = Collections.emptyList();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Map f11827n = Collections.emptyMap();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Map f11829q = Collections.emptyMap();

    public C0938a0(int i7) {
        this.l = i7;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int b(java.lang.Comparable r5) {
        /*
            r4 = this;
            java.util.List r0 = r4.f11826m
            int r0 = r0.size()
            int r1 = r0 + (-1)
            if (r1 < 0) goto L21
            java.util.List r2 = r4.f11826m
            java.lang.Object r2 = r2.get(r1)
            androidx.datastore.preferences.protobuf.e0 r2 = (androidx.datastore.preferences.protobuf.e0) r2
            java.lang.Comparable r2 = r2.l
            int r2 = r5.compareTo(r2)
            if (r2 <= 0) goto L1e
            int r0 = r0 + 1
        L1c:
            int r4 = -r0
            return r4
        L1e:
            if (r2 != 0) goto L21
            return r1
        L21:
            r0 = 0
        L22:
            if (r0 > r1) goto L43
            int r2 = r0 + r1
            int r2 = r2 / 2
            java.util.List r3 = r4.f11826m
            java.lang.Object r3 = r3.get(r2)
            androidx.datastore.preferences.protobuf.e0 r3 = (androidx.datastore.preferences.protobuf.e0) r3
            java.lang.Comparable r3 = r3.l
            int r3 = r5.compareTo(r3)
            if (r3 >= 0) goto L3c
            int r2 = r2 + (-1)
            r1 = r2
            goto L22
        L3c:
            if (r3 <= 0) goto L42
            int r2 = r2 + 1
            r0 = r2
            goto L22
        L42:
            return r2
        L43:
            int r0 = r0 + 1
            goto L1c
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.protobuf.C0938a0.b(java.lang.Comparable):int");
    }

    public final void c() {
        if (this.o) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        c();
        if (!this.f11826m.isEmpty()) {
            this.f11826m.clear();
        }
        if (this.f11827n.isEmpty()) {
            return;
        }
        this.f11827n.clear();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return b(comparable) >= 0 || this.f11827n.containsKey(comparable);
    }

    public final Map.Entry d(int i7) {
        return (Map.Entry) this.f11826m.get(i7);
    }

    public final Iterable e() {
        return this.f11827n.isEmpty() ? d0.f11835b : this.f11827n.entrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        if (this.f11828p == null) {
            this.f11828p = new g0(0, this);
        }
        return this.f11828p;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0938a0)) {
            return super.equals(obj);
        }
        C0938a0 c0938a0 = (C0938a0) obj;
        int size = size();
        if (size != c0938a0.size()) {
            return false;
        }
        int size2 = this.f11826m.size();
        if (size2 != c0938a0.f11826m.size()) {
            return ((AbstractSet) entrySet()).equals(c0938a0.entrySet());
        }
        for (int i7 = 0; i7 < size2; i7++) {
            if (!d(i7).equals(c0938a0.d(i7))) {
                return false;
            }
        }
        if (size2 != size) {
            return this.f11827n.equals(c0938a0.f11827n);
        }
        return true;
    }

    public final SortedMap f() {
        c();
        if (this.f11827n.isEmpty() && !(this.f11827n instanceof TreeMap)) {
            TreeMap treeMap = new TreeMap();
            this.f11827n = treeMap;
            this.f11829q = treeMap.descendingMap();
        }
        return (SortedMap) this.f11827n;
    }

    @Override // java.util.AbstractMap, java.util.Map
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public final Object put(Comparable comparable, Object obj) {
        c();
        int iB = b(comparable);
        if (iB >= 0) {
            return ((e0) this.f11826m.get(iB)).setValue(obj);
        }
        c();
        boolean zIsEmpty = this.f11826m.isEmpty();
        int i7 = this.l;
        if (zIsEmpty && !(this.f11826m instanceof ArrayList)) {
            this.f11826m = new ArrayList(i7);
        }
        int i10 = -(iB + 1);
        if (i10 >= i7) {
            return f().put(comparable, obj);
        }
        if (this.f11826m.size() == i7) {
            e0 e0Var = (e0) this.f11826m.remove(i7 - 1);
            f().put(e0Var.l, e0Var.f11837m);
        }
        this.f11826m.add(i10, new e0(this, comparable, obj));
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int iB = b(comparable);
        return iB >= 0 ? ((e0) this.f11826m.get(iB)).f11837m : this.f11827n.get(comparable);
    }

    public final Object h(int i7) {
        c();
        Object obj = ((e0) this.f11826m.remove(i7)).f11837m;
        if (!this.f11827n.isEmpty()) {
            Iterator it = f().entrySet().iterator();
            List list = this.f11826m;
            Map.Entry entry = (Map.Entry) it.next();
            list.add(new e0(this, (Comparable) entry.getKey(), entry.getValue()));
            it.remove();
        }
        return obj;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        int size = this.f11826m.size();
        int iHashCode = 0;
        for (int i7 = 0; i7 < size; i7++) {
            iHashCode += ((e0) this.f11826m.get(i7)).hashCode();
        }
        return this.f11827n.size() > 0 ? iHashCode + this.f11827n.hashCode() : iHashCode;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        c();
        Comparable comparable = (Comparable) obj;
        int iB = b(comparable);
        if (iB >= 0) {
            return h(iB);
        }
        if (this.f11827n.isEmpty()) {
            return null;
        }
        return this.f11827n.remove(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.f11827n.size() + this.f11826m.size();
    }
}
