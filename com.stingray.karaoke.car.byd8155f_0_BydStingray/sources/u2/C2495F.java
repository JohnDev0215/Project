package u2;

import java.io.IOException;

/* JADX INFO: renamed from: u2.F, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C2495F implements h2.g {
    public final /* synthetic */ B5.u l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C2516t f22668m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C2521y f22669n;
    public final /* synthetic */ IOException o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ boolean f22670p;

    public /* synthetic */ C2495F(B5.u uVar, C2516t c2516t, C2521y c2521y, IOException iOException, boolean z6) {
        this.l = uVar;
        this.f22668m = c2516t;
        this.f22669n = c2521y;
        this.o = iOException;
        this.f22670p = z6;
    }

    @Override // h2.g
    public final void accept(Object obj) {
        InterfaceC2497H interfaceC2497H = (InterfaceC2497H) obj;
        B5.u uVar = this.l;
        interfaceC2497H.P(uVar.f735m, (C2492C) uVar.f736n, this.f22668m, this.f22669n, this.o, this.f22670p);
    }
}
