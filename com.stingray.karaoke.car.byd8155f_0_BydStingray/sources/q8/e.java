package Q8;

import e0.C1157D;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import n7.AbstractC1989d;
import n7.AbstractC1998m;

/* JADX INFO: loaded from: classes.dex */
public final class e extends AbstractC1989d {
    public final /* synthetic */ int l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f7222m;

    public e(List list) {
        this.f7222m = list;
    }

    @Override // n7.AbstractC1986a, java.util.Collection, java.util.List
    public /* bridge */ boolean contains(Object obj) {
        switch (this.l) {
            case 0:
                if (obj instanceof String) {
                    return super.contains((String) obj);
                }
                return false;
            default:
                return super.contains(obj);
        }
    }

    @Override // n7.AbstractC1986a
    public final int d() {
        switch (this.l) {
            case 0:
                return ((f) this.f7222m).f7223a.groupCount() + 1;
            default:
                return ((List) this.f7222m).size();
        }
    }

    @Override // java.util.List
    public final Object get(int i7) {
        switch (this.l) {
            case 0:
                String strGroup = ((f) this.f7222m).f7223a.group(i7);
                return strGroup == null ? "" : strGroup;
            default:
                return ((List) this.f7222m).get(AbstractC1998m.Q(i7, this));
        }
    }

    @Override // n7.AbstractC1989d, java.util.List
    public /* bridge */ int indexOf(Object obj) {
        switch (this.l) {
            case 0:
                if (obj instanceof String) {
                    return super.indexOf((String) obj);
                }
                return -1;
            default:
                return super.indexOf(obj);
        }
    }

    @Override // n7.AbstractC1989d, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator iterator() {
        switch (this.l) {
            case 1:
                return new C1157D(this, 0);
            default:
                return super.iterator();
        }
    }

    @Override // n7.AbstractC1989d, java.util.List
    public /* bridge */ int lastIndexOf(Object obj) {
        switch (this.l) {
            case 0:
                if (obj instanceof String) {
                    return super.lastIndexOf((String) obj);
                }
                return -1;
            default:
                return super.lastIndexOf(obj);
        }
    }

    @Override // n7.AbstractC1989d, java.util.List
    public ListIterator listIterator() {
        switch (this.l) {
            case 1:
                return new C1157D(this, 0);
            default:
                return super.listIterator();
        }
    }

    public e(f fVar) {
        this.f7222m = fVar;
    }

    @Override // n7.AbstractC1989d, java.util.List
    public ListIterator listIterator(int i7) {
        switch (this.l) {
            case 1:
                return new C1157D(this, i7);
            default:
                return super.listIterator(i7);
        }
    }
}
