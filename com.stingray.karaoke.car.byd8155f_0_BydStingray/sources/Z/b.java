package Z;

import P8.i;
import W.e;
import Y.c;
import a0.C0906b;
import java.util.Iterator;
import n7.AbstractC1993h;

/* JADX INFO: loaded from: classes.dex */
public final class b extends AbstractC1993h implements e {
    public static final b o;
    public final Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f10807m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final c f10808n;

    static {
        C0906b c0906b = C0906b.f11026a;
        o = new b(c0906b, c0906b, c.f10586n);
    }

    public b(Object obj, Object obj2, c cVar) {
        this.l = obj;
        this.f10807m = obj2;
        this.f10808n = cVar;
    }

    @Override // n7.AbstractC1986a, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return this.f10808n.containsKey(obj);
    }

    @Override // n7.AbstractC1986a
    public final int d() {
        c cVar = this.f10808n;
        cVar.getClass();
        return cVar.f10587m;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new i(this.l, this.f10808n);
    }
}
