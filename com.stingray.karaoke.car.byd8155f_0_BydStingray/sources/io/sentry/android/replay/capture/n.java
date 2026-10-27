package io.sentry.android.replay.capture;

import m7.q;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class n extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f16382m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ o f16383n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ n(o oVar, int i7) {
        super(1);
        this.f16382m = i7;
        this.f16383n = oVar;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f16382m) {
            case 0:
                k kVar = (k) obj;
                A7.m.f(kVar, "segment");
                if (kVar instanceof i) {
                    i iVar = (i) kVar;
                    o oVar = this.f16383n;
                    i.a(iVar, oVar.f16385t);
                    oVar.n(oVar.j() + 1);
                    oVar.p(iVar.f16376a.f15874F);
                }
                break;
            default:
                k kVar2 = (k) obj;
                A7.m.f(kVar2, "segment");
                if (kVar2 instanceof i) {
                    o oVar2 = this.f16383n;
                    i.a((i) kVar2, oVar2.f16385t);
                    oVar2.n(oVar2.j() + 1);
                }
                break;
        }
        return q.f19267a;
    }
}
