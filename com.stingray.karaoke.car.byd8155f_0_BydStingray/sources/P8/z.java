package p8;

import androidx.datastore.preferences.protobuf.g0;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public final class z extends AbstractMap {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final /* synthetic */ int f21002q = 0;
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public List f21003m = Collections.emptyList();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Map f21004n = Collections.emptyMap();
    public boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public volatile g0 f21005p;

    public z(int i7) {
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
            java.util.List r0 = r4.f21003m
            int r0 = r0.size()
            int r1 = r0 + (-1)
            if (r1 < 0) goto L21
            java.util.List r2 = r4.f21003m
            java.lang.Object r2 = r2.get(r1)
            p8.D r2 = (p8.C2116D) r2
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
            java.util.List r3 = r4.f21003m
            java.lang.Object r3 = r3.get(r2)
            p8.D r3 = (p8.C2116D) r3
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
        throw new UnsupportedOperationException("Method not decompiled: p8.z.b(java.lang.Comparable):int");
    }

    public final void c() {
        if (this.o) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        c();
        if (!this.f21003m.isEmpty()) {
            this.f21003m.clear();
        }
        if (this.f21004n.isEmpty()) {
            return;
        }
        this.f21004n.clear();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return b(comparable) >= 0 || this.f21004n.containsKey(comparable);
    }

    public final Iterable d() {
        return this.f21004n.isEmpty() ? AbstractC2115C.f20947b : this.f21004n.entrySet();
    }

    public final SortedMap e() {
        c();
        if (this.f21004n.isEmpty() && !(this.f21004n instanceof TreeMap)) {
            this.f21004n = new TreeMap();
        }
        return (SortedMap) this.f21004n;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        if (this.f21005p == null) {
            this.f21005p = new g0(1, this);
        }
        return this.f21005p;
    }

    @Override // java.util.AbstractMap, java.util.Map
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public final Object put(Comparable comparable, Object obj) {
        c();
        int iB = b(comparable);
        if (iB >= 0) {
            return ((C2116D) this.f21003m.get(iB)).setValue(obj);
        }
        c();
        boolean zIsEmpty = this.f21003m.isEmpty();
        int i7 = this.l;
        if (zIsEmpty && !(this.f21003m instanceof ArrayList)) {
            this.f21003m = new ArrayList(i7);
        }
        int i10 = -(iB + 1);
        if (i10 >= i7) {
            return e().put(comparable, obj);
        }
        if (this.f21003m.size() == i7) {
            C2116D c2116d = (C2116D) this.f21003m.remove(i7 - 1);
            e().put(c2116d.l, c2116d.f20948m);
        }
        this.f21003m.add(i10, new C2116D(this, comparable, obj));
        return null;
    }

    public final Object g(int i7) {
        c();
        Object obj = ((C2116D) this.f21003m.remove(i7)).f20948m;
        if (!this.f21004n.isEmpty()) {
            Iterator it = e().entrySet().iterator();
            List list = this.f21003m;
            Map.Entry entry = (Map.Entry) it.next();
            list.add(new C2116D(this, (Comparable) entry.getKey(), entry.getValue()));
            it.remove();
        }
        return obj;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int iB = b(comparable);
        return iB >= 0 ? ((C2116D) this.f21003m.get(iB)).f20948m : this.f21004n.get(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        c();
        Comparable comparable = (Comparable) obj;
        int iB = b(comparable);
        if (iB >= 0) {
            return g(iB);
        }
        if (this.f21004n.isEmpty()) {
            return null;
        }
        return this.f21004n.remove(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.f21004n.size() + this.f21003m.size();
    }
}
