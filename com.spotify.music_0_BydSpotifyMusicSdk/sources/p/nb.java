package p;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class nb extends kd2 implements Map {
    public ib l;
    public kb m;
    public mb n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public nb(nb nbVar) {
        super(0);
        if (nbVar != null) {
            int i = nbVar.k;
            b(this.k + i);
            if (this.k != 0) {
                for (int i2 = 0; i2 < i; i2++) {
                    put(nbVar.f(i2), nbVar.i(i2));
                }
            } else if (i > 0) {
                rb.j0(0, 0, i, nbVar.c, this.c);
                rb.k0(0, 0, i << 1, nbVar.e, this.e);
                this.k = i;
            }
        }
    }

    @Override // p.kd2, java.util.Map
    public final boolean containsKey(Object obj) {
        return super.containsKey(obj);
    }

    @Override // p.kd2, java.util.Map
    public final boolean containsValue(Object obj) {
        return super.containsValue(obj);
    }

    @Override // java.util.Map
    public final Set entrySet() {
        ib ibVar = this.l;
        if (ibVar != null) {
            return ibVar;
        }
        ib ibVar2 = new ib(this, 0);
        this.l = ibVar2;
        return ibVar2;
    }

    @Override // p.kd2, java.util.Map
    public final Object get(Object obj) {
        return super.get(obj);
    }

    public final boolean j(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!super.containsKey(it.next())) {
                return false;
            }
        }
        return true;
    }

    public final boolean k(Collection collection) {
        int i = this.k;
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            super.remove(it.next());
        }
        return i != this.k;
    }

    @Override // java.util.Map
    public final Set keySet() {
        kb kbVar = this.m;
        if (kbVar != null) {
            return kbVar;
        }
        kb kbVar2 = new kb(this);
        this.m = kbVar2;
        return kbVar2;
    }

    public final boolean l(Collection collection) {
        int i = this.k;
        for (int i2 = i - 1; i2 >= 0; i2--) {
            if (!collection.contains(f(i2))) {
                g(i2);
            }
        }
        return i != this.k;
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        b(map.size() + this.k);
        for (Map.Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // p.kd2, java.util.Map
    public final Object remove(Object obj) {
        return super.remove(obj);
    }

    @Override // java.util.Map
    public final Collection values() {
        mb mbVar = this.n;
        if (mbVar != null) {
            return mbVar;
        }
        mb mbVar2 = new mb(this);
        this.n = mbVar2;
        return mbVar2;
    }
}
