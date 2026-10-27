package W3;

/* JADX INFO: loaded from: classes.dex */
public final class a implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final V3.e f9758a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final V3.a f9759b;
    public final boolean c;
    public final boolean d;

    public a(String str, V3.e eVar, V3.a aVar, boolean z6, boolean z10) {
        this.f9758a = eVar;
        this.f9759b = aVar;
        this.c = z6;
        this.d = z10;
    }

    @Override // W3.b
    public final Q3.c a(O3.j jVar, O3.a aVar, X3.b bVar) {
        return new Q3.f(jVar, bVar, this);
    }
}
