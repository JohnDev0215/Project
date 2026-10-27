package R7;

import F8.AbstractC0287w;
import K7.K;
import Q7.N;
import h6.r;
import java.util.Map;
import o8.C2058c;

/* JADX INFO: loaded from: classes.dex */
public final class j implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final N7.i f7537a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C2058c f7538b;
    public final Map c;
    public final Object d;

    public j(N7.i iVar, C2058c c2058c, Map map) {
        A7.m.f(c2058c, "fqName");
        this.f7537a = iVar;
        this.f7538b = c2058c;
        this.c = map;
        this.d = r.n(m7.g.l, new K(this, 15));
    }

    @Override // R7.b
    public final C2058c a() {
        return this.f7538b;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object, m7.f] */
    @Override // R7.b
    public final AbstractC0287w b() {
        Object value = this.d.getValue();
        A7.m.e(value, "<get-type>(...)");
        return (AbstractC0287w) value;
    }

    @Override // R7.b
    public final Map c() {
        return this.c;
    }

    @Override // R7.b
    public final N g() {
        return N.c;
    }
}
