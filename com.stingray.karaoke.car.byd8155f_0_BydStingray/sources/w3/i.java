package W3;

/* JADX INFO: loaded from: classes.dex */
public final class i implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f9788a = 1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final V3.b f9789b;
    public final boolean c;
    public final V3.e d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f9790e;

    public i(String str, V3.b bVar, V3.b bVar2, V3.d dVar, boolean z6) {
        this.f9789b = bVar;
        this.d = bVar2;
        this.f9790e = dVar;
        this.c = z6;
    }

    @Override // W3.b
    public final Q3.c a(O3.j jVar, O3.a aVar, X3.b bVar) {
        switch (this.f9788a) {
            case 0:
                return new Q3.n(jVar, bVar, this);
            default:
                return new Q3.o(jVar, bVar, this);
        }
    }

    public String toString() {
        switch (this.f9788a) {
            case 0:
                return "RectangleShape{position=" + this.d + ", size=" + ((V3.a) this.f9790e) + '}';
            default:
                return super.toString();
        }
    }

    public i(String str, V3.e eVar, V3.a aVar, V3.b bVar, boolean z6) {
        this.d = eVar;
        this.f9790e = aVar;
        this.f9789b = bVar;
        this.c = z6;
    }
}
