package F8;

import java.util.ArrayDeque;

/* JADX INFO: loaded from: classes.dex */
public class J {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f2441a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f2442b;
    public final G8.b c;
    public final G8.e d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final G8.f f2443e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2444f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ArrayDeque f2445g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public O8.i f2446h;

    public J(boolean z6, boolean z10, G8.b bVar, G8.e eVar, G8.f fVar) {
        A7.m.f(bVar, "typeSystemContext");
        A7.m.f(eVar, "kotlinTypePreparator");
        A7.m.f(fVar, "kotlinTypeRefiner");
        this.f2441a = z6;
        this.f2442b = z10;
        this.c = bVar;
        this.d = eVar;
        this.f2443e = fVar;
    }

    public final void a() {
        ArrayDeque arrayDeque = this.f2445g;
        A7.m.c(arrayDeque);
        arrayDeque.clear();
        O8.i iVar = this.f2446h;
        A7.m.c(iVar);
        iVar.clear();
    }

    public final void b() {
        if (this.f2445g == null) {
            this.f2445g = new ArrayDeque(4);
        }
        if (this.f2446h == null) {
            this.f2446h = new O8.i();
        }
    }

    public final a0 c(I8.c cVar) {
        A7.m.f(cVar, "type");
        return this.d.v(cVar);
    }

    public final AbstractC0287w d(I8.c cVar) {
        A7.m.f(cVar, "type");
        this.f2443e.getClass();
        return (AbstractC0287w) cVar;
    }
}
