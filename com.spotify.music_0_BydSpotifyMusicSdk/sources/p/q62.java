package p;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class q62 implements Iterable {
    public m62 c;
    public m62 e;
    public final WeakHashMap k = new WeakHashMap();
    public int l = 0;

    public m62 a(Object obj) {
        m62 m62Var = this.c;
        while (m62Var != null && !m62Var.c.equals(obj)) {
            m62Var = m62Var.k;
        }
        return m62Var;
    }

    public Object b(Object obj) {
        m62 m62VarA = a(obj);
        if (m62VarA == null) {
            return null;
        }
        this.l--;
        WeakHashMap weakHashMap = this.k;
        if (!weakHashMap.isEmpty()) {
            Iterator it = weakHashMap.keySet().iterator();
            while (it.hasNext()) {
                ((p62) it.next()).a(m62VarA);
            }
        }
        m62 m62Var = m62VarA.l;
        if (m62Var != null) {
            m62Var.k = m62VarA.k;
        } else {
            this.c = m62VarA.k;
        }
        m62 m62Var2 = m62VarA.k;
        if (m62Var2 != null) {
            m62Var2.l = m62Var;
        } else {
            this.e = m62Var;
        }
        m62VarA.k = null;
        m62VarA.l = null;
        return m62VarA.e;
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0048, code lost:
    
        if (r3.hasNext() != false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0050, code lost:
    
        if (((p.o62) r7).hasNext() != false) goto L28;
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
    public final boolean equals(java.lang.Object r7) {
        /*
            r6 = this;
            r0 = 1
            if (r7 != r6) goto L4
            return r0
        L4:
            boolean r1 = r7 instanceof p.q62
            r2 = 0
            if (r1 != 0) goto La
            return r2
        La:
            p.q62 r7 = (p.q62) r7
            int r1 = r6.l
            int r3 = r7.l
            if (r1 == r3) goto L13
            return r2
        L13:
            java.util.Iterator r1 = r6.iterator()
            java.util.Iterator r7 = r7.iterator()
        L1b:
            r3 = r1
            p.o62 r3 = (p.o62) r3
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L44
            r4 = r7
            p.o62 r4 = (p.o62) r4
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L44
            java.lang.Object r3 = r3.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r4 = r4.next()
            if (r3 != 0) goto L3b
            if (r4 != 0) goto L43
        L3b:
            if (r3 == 0) goto L1b
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L1b
        L43:
            return r2
        L44:
            boolean r1 = r3.hasNext()
            if (r1 != 0) goto L53
            p.o62 r7 = (p.o62) r7
            boolean r7 = r7.hasNext()
            if (r7 != 0) goto L53
            goto L54
        L53:
            r0 = r2
        L54:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p.q62.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        Iterator it = iterator();
        int iHashCode = 0;
        while (true) {
            o62 o62Var = (o62) it;
            if (!o62Var.hasNext()) {
                return iHashCode;
            }
            iHashCode += ((Map.Entry) o62Var.next()).hashCode();
        }
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        l62 l62Var = new l62(this.c, this.e, 0);
        this.k.put(l62Var, Boolean.FALSE);
        return l62Var;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[");
        Iterator it = iterator();
        while (true) {
            o62 o62Var = (o62) it;
            if (!o62Var.hasNext()) {
                sb.append("]");
                return sb.toString();
            }
            sb.append(((Map.Entry) o62Var.next()).toString());
            if (o62Var.hasNext()) {
                sb.append(", ");
            }
        }
    }
}
