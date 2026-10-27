package Y;

import java.util.Iterator;
import java.util.Map;
import n7.AbstractC1992g;

/* JADX INFO: loaded from: classes.dex */
public final class g extends AbstractC1992g {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final e f10597m;

    public /* synthetic */ g(int i7, e eVar) {
        this.l = i7;
        this.f10597m = eVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        switch (this.l) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        switch (this.l) {
            case 0:
                this.f10597m.clear();
                break;
            default:
                this.f10597m.clear();
                break;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        switch (this.l) {
            case 0:
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                if ((entry != null ? entry : null) == null) {
                    return false;
                }
                Object key = entry.getKey();
                e eVar = this.f10597m;
                Object obj2 = eVar.get(key);
                return obj2 != null ? obj2.equals(entry.getValue()) : entry.getValue() == null && eVar.containsKey(entry.getKey());
            default:
                return this.f10597m.containsKey(obj);
        }
    }

    @Override // n7.AbstractC1992g
    public final int d() {
        switch (this.l) {
            case 0:
                e eVar = this.f10597m;
                eVar.getClass();
                return eVar.f10593q;
            default:
                e eVar2 = this.f10597m;
                eVar2.getClass();
                return eVar2.f10593q;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        switch (this.l) {
            case 0:
                return new O8.h(this.f10597m);
            default:
                n[] nVarArr = new n[8];
                for (int i7 = 0; i7 < 8; i7++) {
                    nVarArr[i7] = new o(1);
                }
                return new h(this.f10597m, nVarArr);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        switch (this.l) {
            case 0:
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                if ((entry != null ? entry : null) == null) {
                    return false;
                }
                return this.f10597m.remove(entry.getKey(), entry.getValue());
            default:
                e eVar = this.f10597m;
                if (!eVar.containsKey(obj)) {
                    return false;
                }
                eVar.remove(obj);
                return true;
        }
    }
}
