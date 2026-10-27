package p;

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
public final class ud2 extends AbstractMap {
    public static final /* synthetic */ int o = 0;
    public final int c;
    public boolean l;
    public volatile ib m;
    public List e = Collections.emptyList();
    public Map k = Collections.emptyMap();
    public Map n = Collections.emptyMap();

    public ud2(int i) {
        this.c = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int a(java.lang.Comparable r5) {
        /*
            r4 = this;
            java.util.List r0 = r4.e
            int r0 = r0.size()
            int r1 = r0 + (-1)
            if (r1 < 0) goto L21
            java.util.List r2 = r4.e
            java.lang.Object r2 = r2.get(r1)
            p.xd2 r2 = (p.xd2) r2
            java.lang.Comparable r2 = r2.c
            int r2 = r5.compareTo(r2)
            if (r2 <= 0) goto L1e
            int r0 = r0 + 1
        L1c:
            int r1 = -r0
            goto L47
        L1e:
            if (r2 != 0) goto L21
            goto L47
        L21:
            r0 = 0
        L22:
            if (r0 > r1) goto L44
            int r2 = r0 + r1
            int r2 = r2 / 2
            java.util.List r3 = r4.e
            java.lang.Object r3 = r3.get(r2)
            p.xd2 r3 = (p.xd2) r3
            java.lang.Comparable r3 = r3.c
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
            r1 = r2
            goto L47
        L44:
            int r0 = r0 + 1
            goto L1c
        L47:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p.ud2.a(java.lang.Comparable):int");
    }

    public final void b() {
        if (this.l) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final void clear() {
        b();
        if (!this.e.isEmpty()) {
            this.e.clear();
        }
        if (this.k.isEmpty()) {
            return;
        }
        this.k.clear();
    }

    @Override // java.util.AbstractMap, java.util.Map
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public final boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return a(comparable) >= 0 || this.k.containsKey(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public final Set entrySet() {
        if (this.m == null) {
            this.m = new ib(this);
        }
        return this.m;
    }

    @Override // java.util.AbstractMap, java.util.Map
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ud2)) {
            return super.equals(obj);
        }
        ud2 ud2Var = (ud2) obj;
        int size = size();
        if (size != ud2Var.size()) {
            return false;
        }
        int size2 = this.e.size();
        if (size2 != ud2Var.e.size()) {
            return entrySet().equals(ud2Var.entrySet());
        }
        for (int i = 0; i < size2; i++) {
            if (!h(i).equals(ud2Var.h(i))) {
                return false;
            }
        }
        if (size2 != size) {
            return this.k.equals(ud2Var.k);
        }
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public final Object get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int iA = a(comparable);
        return iA >= 0 ? ((xd2) this.e.get(iA)).e : this.k.get(comparable);
    }

    public final Map.Entry h(int i) {
        return (Map.Entry) this.e.get(i);
    }

    public final Iterable i() {
        return this.k.isEmpty() ? p02.e : this.k.entrySet();
    }

    public final SortedMap j() {
        b();
        if (this.k.isEmpty() && !(this.k instanceof TreeMap)) {
            TreeMap treeMap = new TreeMap();
            this.k = treeMap;
            this.n = treeMap.descendingMap();
        }
        return (SortedMap) this.k;
    }

    @Override // java.util.AbstractMap, java.util.Map
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public final int hashCode() {
        int size = this.e.size();
        int iHashCode = 0;
        for (int i = 0; i < size; i++) {
            iHashCode += ((xd2) this.e.get(i)).hashCode();
        }
        return this.k.size() > 0 ? iHashCode + this.k.hashCode() : iHashCode;
    }

    @Override // java.util.AbstractMap, java.util.Map
    /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
    public final Object put(Comparable comparable, Object obj) {
        b();
        int iA = a(comparable);
        if (iA >= 0) {
            return ((xd2) this.e.get(iA)).setValue(obj);
        }
        b();
        boolean zIsEmpty = this.e.isEmpty();
        int i = this.c;
        if (zIsEmpty && !(this.e instanceof ArrayList)) {
            this.e = new ArrayList(i);
        }
        int i2 = -(iA + 1);
        if (i2 >= i) {
            return j().put(comparable, obj);
        }
        if (this.e.size() == i) {
            xd2 xd2Var = (xd2) this.e.remove(i - 1);
            j().put(xd2Var.c, xd2Var.e);
        }
        this.e.add(i2, new xd2(this, comparable, obj));
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public final Object remove(Object obj) {
        b();
        Comparable comparable = (Comparable) obj;
        int iA = a(comparable);
        if (iA >= 0) {
            return n(iA);
        }
        if (this.k.isEmpty()) {
            return null;
        }
        return this.k.remove(comparable);
    }

    public final Object n(int i) {
        b();
        Object obj = ((xd2) this.e.remove(i)).e;
        if (!this.k.isEmpty()) {
            Iterator it = j().entrySet().iterator();
            List list = this.e;
            Map.Entry entry = (Map.Entry) it.next();
            list.add(new xd2(this, (Comparable) entry.getKey(), entry.getValue()));
            it.remove();
        }
        return obj;
    }

    @Override // java.util.AbstractMap, java.util.Map
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public final int size() {
        return this.k.size() + this.e.size();
    }
}
