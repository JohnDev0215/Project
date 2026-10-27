package e0;

import java.util.ConcurrentModificationException;
import java.util.Map;

/* JADX INFO: renamed from: e0.y, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1185y implements Map.Entry, B7.d {
    public final Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f13753m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C1186z f13754n;

    public C1185y(C1186z c1186z) {
        this.f13754n = c1186z;
        Map.Entry entry = c1186z.o;
        A7.m.c(entry);
        this.l = entry.getKey();
        Map.Entry entry2 = c1186z.o;
        A7.m.c(entry2);
        this.f13753m = entry2.getValue();
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.l;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.f13753m;
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        C1186z c1186z = this.f13754n;
        if (c1186z.l.b().d != c1186z.f13756n) {
            throw new ConcurrentModificationException();
        }
        Object obj2 = this.f13753m;
        c1186z.l.put(this.l, obj);
        this.f13753m = obj;
        return obj2;
    }
}
