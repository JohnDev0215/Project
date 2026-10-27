package d5;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.SortedMap;

/* JADX INFO: loaded from: classes.dex */
public final class Y extends AbstractC1093o implements Serializable {
    public transient Map o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public transient int f13401p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public transient X f13402q;

    @Override // d5.AbstractC1093o
    public final C1082d a() {
        C1082d c1084f = this.f13447n;
        if (c1084f == null) {
            Map map = this.o;
            c1084f = map instanceof NavigableMap ? new C1084f(this, (NavigableMap) map) : map instanceof SortedMap ? new C1087i(this, (SortedMap) map) : new C1082d(this, map);
            this.f13447n = c1084f;
        }
        return c1084f;
    }

    public final void b() {
        Map map = this.o;
        Iterator it = map.values().iterator();
        while (it.hasNext()) {
            ((Collection) it.next()).clear();
        }
        map.clear();
        this.f13401p = 0;
    }
}
