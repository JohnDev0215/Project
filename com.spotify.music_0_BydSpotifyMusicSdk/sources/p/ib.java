package p;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ib extends AbstractSet {
    public final /* synthetic */ int c;
    public final /* synthetic */ Map e;

    public /* synthetic */ ib(Map map, int i) {
        this.c = i;
        this.e = map;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        switch (this.c) {
            case 1:
                Map.Entry entry = (Map.Entry) obj;
                if (contains(entry)) {
                    return false;
                }
                ((ud2) this.e).put((Comparable) entry.getKey(), entry.getValue());
                return true;
            default:
                return super.add(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        switch (this.c) {
            case 1:
                ((ud2) this.e).clear();
                break;
            default:
                super.clear();
                break;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        switch (this.c) {
            case 1:
                Map.Entry entry = (Map.Entry) obj;
                Object objG = ((ud2) this.e).get(entry.getKey());
                Object value = entry.getValue();
                return objG == value || (objG != null && objG.equals(value));
            default:
                return super.contains(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator iterator() {
        int i = this.c;
        Map map = this.e;
        switch (i) {
            case 0:
                return new lb((nb) map);
            default:
                return new yd2((ud2) map);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        switch (this.c) {
            case 1:
                Map.Entry entry = (Map.Entry) obj;
                if (!contains(entry)) {
                    return false;
                }
                ((ud2) this.e).remove(entry.getKey());
                return true;
            default:
                return super.remove(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        int i = this.c;
        Map map = this.e;
        switch (i) {
            case 0:
                return ((nb) map).k;
            default:
                return ((ud2) map).size();
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ib(ud2 ud2Var) {
        this(ud2Var, 1);
        this.c = 1;
    }
}
