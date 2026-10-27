package d5;

import a.AbstractC0904a;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: renamed from: d5.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1096s extends AbstractSet {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C1098u f13453m;

    public /* synthetic */ C1096s(C1098u c1098u, int i7) {
        this.l = i7;
        this.f13453m = c1098u;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        switch (this.l) {
            case 0:
                this.f13453m.clear();
                break;
            default:
                this.f13453m.clear();
                break;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        switch (this.l) {
            case 0:
                C1098u c1098u = this.f13453m;
                Map mapC = c1098u.c();
                if (mapC != null) {
                    return mapC.entrySet().contains(obj);
                }
                if (obj instanceof Map.Entry) {
                    Map.Entry entry = (Map.Entry) obj;
                    int iE = c1098u.e(entry.getKey());
                    if (iE != -1 && AbstractC0904a.A(c1098u.k()[iE], entry.getValue())) {
                        return true;
                    }
                }
                return false;
            default:
                return this.f13453m.containsKey(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        switch (this.l) {
            case 0:
                C1098u c1098u = this.f13453m;
                Map mapC = c1098u.c();
                return mapC != null ? mapC.entrySet().iterator() : new r(c1098u, 1);
            default:
                C1098u c1098u2 = this.f13453m;
                Map mapC2 = c1098u2.c();
                return mapC2 != null ? mapC2.keySet().iterator() : new r(c1098u2, 0);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        switch (this.l) {
            case 0:
                C1098u c1098u = this.f13453m;
                Map mapC = c1098u.c();
                if (mapC != null) {
                    return mapC.entrySet().remove(obj);
                }
                if (obj instanceof Map.Entry) {
                    Map.Entry entry = (Map.Entry) obj;
                    if (!c1098u.g()) {
                        int iD = c1098u.d();
                        Object key = entry.getKey();
                        Object value = entry.getValue();
                        Object obj2 = c1098u.l;
                        Objects.requireNonNull(obj2);
                        int iR = AbstractC1095q.r(key, value, iD, obj2, c1098u.i(), c1098u.j(), c1098u.k());
                        if (iR != -1) {
                            c1098u.f(iR, iD);
                            c1098u.f13460q--;
                            c1098u.f13459p += 32;
                            return true;
                        }
                    }
                }
                return false;
            default:
                C1098u c1098u2 = this.f13453m;
                Map mapC2 = c1098u2.c();
                return mapC2 != null ? mapC2.keySet().remove(obj) : c1098u2.h(obj) != C1098u.f13456u;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        switch (this.l) {
        }
        return this.f13453m.size();
    }
}
