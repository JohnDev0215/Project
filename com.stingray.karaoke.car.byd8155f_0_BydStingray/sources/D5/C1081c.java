package d5;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/* JADX INFO: renamed from: d5.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C1081c implements Iterator {
    public final /* synthetic */ int l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Iterator f13406m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f13407n;
    public final /* synthetic */ Object o;

    public C1081c(C1090l c1090l) {
        this.o = c1090l;
        Collection collection = c1090l.f13439m;
        this.f13407n = collection;
        this.f13406m = collection instanceof List ? ((List) collection).listIterator() : collection.iterator();
    }

    public void a() {
        C1090l c1090l = (C1090l) this.o;
        c1090l.d();
        if (c1090l.f13439m != ((Collection) this.f13407n)) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        switch (this.l) {
            case 0:
                break;
            case 1:
                break;
            default:
                a();
                break;
        }
        return this.f13406m.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        switch (this.l) {
            case 0:
                Map.Entry entry = (Map.Entry) this.f13406m.next();
                this.f13407n = (Collection) entry.getValue();
                return ((C1082d) this.o).b(entry);
            case 1:
                Map.Entry entry2 = (Map.Entry) this.f13406m.next();
                this.f13407n = entry2;
                return entry2.getKey();
            default:
                a();
                return this.f13406m.next();
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        switch (this.l) {
            case 0:
                if (!(((Collection) this.f13407n) != null)) {
                    throw new IllegalStateException("no calls to next() since the last call to remove()");
                }
                this.f13406m.remove();
                ((C1082d) this.o).o.f13401p -= ((Collection) this.f13407n).size();
                ((Collection) this.f13407n).clear();
                this.f13407n = null;
                return;
            case 1:
                Map.Entry entry = (Map.Entry) this.f13407n;
                if (!(entry != null)) {
                    throw new IllegalStateException("no calls to next() since the last call to remove()");
                }
                Collection collection = (Collection) entry.getValue();
                this.f13406m.remove();
                ((C1083e) this.o).f13417m.f13401p -= collection.size();
                collection.clear();
                this.f13407n = null;
                return;
            default:
                this.f13406m.remove();
                C1090l c1090l = (C1090l) this.o;
                Y y10 = c1090l.f13441p;
                y10.f13401p--;
                c1090l.l();
                return;
        }
    }

    public C1081c(C1090l c1090l, ListIterator listIterator) {
        this.o = c1090l;
        this.f13407n = c1090l.f13439m;
        this.f13406m = listIterator;
    }

    public C1081c(C1083e c1083e, Iterator it) {
        this.f13406m = it;
        this.o = c1083e;
    }

    public C1081c(C1082d c1082d) {
        this.o = c1082d;
        this.f13406m = c1082d.f13414n.entrySet().iterator();
    }
}
