package T7;

import z7.InterfaceC2772a;

/* JADX INFO: renamed from: T7.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0787f implements InterfaceC2772a {
    public final /* synthetic */ E8.o l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Q7.O f8835m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ AbstractC0789h f8836n;

    public C0787f(AbstractC0789h abstractC0789h, E8.o oVar, Q7.O o) {
        this.f8836n = abstractC0789h;
        this.l = oVar;
        this.f8835m = o;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        return new C0788g(this.f8836n, this.l, this.f8835m);
    }
}
