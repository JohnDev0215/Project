package u;

import androidx.datastore.preferences.protobuf.g0;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: renamed from: u.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C2465e extends C2458F implements Map {
    public g0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public C2462b f22596p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public C2464d f22597q;

    @Override // java.util.Map
    public final Set entrySet() {
        g0 g0Var = this.o;
        if (g0Var != null) {
            return g0Var;
        }
        g0 g0Var2 = new g0(2, this);
        this.o = g0Var2;
        return g0Var2;
    }

    @Override // java.util.Map
    public final Set keySet() {
        C2462b c2462b = this.f22596p;
        if (c2462b != null) {
            return c2462b;
        }
        C2462b c2462b2 = new C2462b(this);
        this.f22596p = c2462b2;
        return c2462b2;
    }

    public final boolean l(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!super.containsKey(it.next())) {
                return false;
            }
        }
        return true;
    }

    public final boolean n(Collection collection) {
        int i7 = this.f22587n;
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            super.remove(it.next());
        }
        return i7 != this.f22587n;
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        c(map.size() + this.f22587n);
        for (Map.Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public final Collection values() {
        C2464d c2464d = this.f22597q;
        if (c2464d != null) {
            return c2464d;
        }
        C2464d c2464d2 = new C2464d(this);
        this.f22597q = c2464d2;
        return c2464d2;
    }
}
