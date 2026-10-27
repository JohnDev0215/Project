package B8;

import B5.K;
import j8.Z;
import n7.AbstractC1998m;
import p8.AbstractC2136k;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class r extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ s f800m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ K f801n;
    public final /* synthetic */ AbstractC2136k o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f802p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f803q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Z f804r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(s sVar, K k10, AbstractC2136k abstractC2136k, int i7, int i10, Z z6) {
        super(0);
        this.f800m = sVar;
        this.f801n = k10;
        this.o = abstractC2136k;
        this.f802p = i7;
        this.f803q = i10;
        this.f804r = z6;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        return AbstractC1998m.z0(((i) this.f800m.f805a.f779a).f764e.g(this.f801n, this.o, this.f802p, this.f803q, this.f804r));
    }
}
