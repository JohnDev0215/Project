package androidx.lifecycle;

import S8.AbstractC0727y;
import S8.C0712i;
import S8.InterfaceC0725w;
import a2.C0908b;

/* JADX INFO: loaded from: classes.dex */
public final class Q implements InterfaceC0986y {
    public final /* synthetic */ EnumC0980s l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ A7.z f11945m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC0725w f11946n;
    public final /* synthetic */ EnumC0980s o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ C0712i f11947p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ b9.c f11948q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ C0908b f11949r;

    public Q(EnumC0980s enumC0980s, A7.z zVar, InterfaceC0725w interfaceC0725w, EnumC0980s enumC0980s2, C0712i c0712i, b9.c cVar, C0908b c0908b) {
        this.l = enumC0980s;
        this.f11945m = zVar;
        this.f11946n = interfaceC0725w;
        this.o = enumC0980s2;
        this.f11947p = c0712i;
        this.f11948q = cVar;
        this.f11949r = c0908b;
    }

    @Override // androidx.lifecycle.InterfaceC0986y
    public final void b(A a10, EnumC0980s enumC0980s) {
        A7.z zVar = this.f11945m;
        if (enumC0980s == this.l) {
            zVar.l = AbstractC0727y.w(this.f11946n, null, null, new P(this.f11948q, this.f11949r, null), 3);
            return;
        }
        if (enumC0980s == this.o) {
            S8.W w10 = (S8.W) zVar.l;
            if (w10 != null) {
                w10.g(null);
            }
            zVar.l = null;
        }
        if (enumC0980s == EnumC0980s.ON_DESTROY) {
            this.f11947p.resumeWith(m7.q.f19267a);
        }
    }
}
