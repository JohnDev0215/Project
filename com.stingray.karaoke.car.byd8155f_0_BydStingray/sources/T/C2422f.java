package t;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: t.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C2422f implements Iterable {
    public C2419c l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public C2419c f22423m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final WeakHashMap f22424n = new WeakHashMap();
    public int o = 0;

    public C2419c a(Object obj) {
        C2419c c2419c = this.l;
        while (c2419c != null && !c2419c.l.equals(obj)) {
            c2419c = c2419c.f22420n;
        }
        return c2419c;
    }

    public Object d(Object obj) {
        C2419c c2419cA = a(obj);
        if (c2419cA == null) {
            return null;
        }
        this.o--;
        WeakHashMap weakHashMap = this.f22424n;
        if (!weakHashMap.isEmpty()) {
            Iterator it = weakHashMap.keySet().iterator();
            while (it.hasNext()) {
                ((AbstractC2421e) it.next()).a(c2419cA);
            }
        }
        C2419c c2419c = c2419cA.o;
        if (c2419c != null) {
            c2419c.f22420n = c2419cA.f22420n;
        } else {
            this.l = c2419cA.f22420n;
        }
        C2419c c2419c2 = c2419cA.f22420n;
        if (c2419c2 != null) {
            c2419c2.o = c2419c;
        } else {
            this.f22423m = c2419c;
        }
        c2419cA.f22420n = null;
        c2419cA.o = null;
        return c2419cA.f22419m;
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0048, code lost:
    
        if (r1.hasNext() != false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0050, code lost:
    
        if (((t.C2418b) r6).hasNext() != false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0054, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:?, code lost:
    
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            r0 = 1
            if (r6 != r5) goto L4
            return r0
        L4:
            boolean r1 = r6 instanceof t.C2422f
            r2 = 0
            if (r1 != 0) goto La
            return r2
        La:
            t.f r6 = (t.C2422f) r6
            int r1 = r5.o
            int r3 = r6.o
            if (r1 == r3) goto L13
            return r2
        L13:
            java.util.Iterator r5 = r5.iterator()
            java.util.Iterator r6 = r6.iterator()
        L1b:
            r1 = r5
            t.b r1 = (t.C2418b) r1
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L44
            r3 = r6
            t.b r3 = (t.C2418b) r3
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L44
            java.lang.Object r1 = r1.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r3 = r3.next()
            if (r1 != 0) goto L3b
            if (r3 != 0) goto L43
        L3b:
            if (r1 == 0) goto L1b
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L1b
        L43:
            return r2
        L44:
            boolean r5 = r1.hasNext()
            if (r5 != 0) goto L53
            t.b r6 = (t.C2418b) r6
            boolean r5 = r6.hasNext()
            if (r5 != 0) goto L53
            goto L54
        L53:
            r0 = r2
        L54:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: t.C2422f.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        Iterator it = iterator();
        int iHashCode = 0;
        while (true) {
            C2418b c2418b = (C2418b) it;
            if (!c2418b.hasNext()) {
                return iHashCode;
            }
            iHashCode += ((Map.Entry) c2418b.next()).hashCode();
        }
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        C2418b c2418b = new C2418b(this.l, this.f22423m, 0);
        this.f22424n.put(c2418b, Boolean.FALSE);
        return c2418b;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[");
        Iterator it = iterator();
        while (true) {
            C2418b c2418b = (C2418b) it;
            if (!c2418b.hasNext()) {
                sb.append("]");
                return sb.toString();
            }
            sb.append(((Map.Entry) c2418b.next()).toString());
            if (c2418b.hasNext()) {
                sb.append(", ");
            }
        }
    }
}
