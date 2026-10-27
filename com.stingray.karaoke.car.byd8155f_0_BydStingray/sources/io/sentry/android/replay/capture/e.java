package io.sentry.android.replay.capture;

import m7.q;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class e extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f16368m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ f f16369n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ e(f fVar, int i7) {
        super(1);
        this.f16368m = i7;
        this.f16369n = fVar;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f16368m) {
            case 0:
                k kVar = (k) obj;
                A7.m.f(kVar, "segment");
                if (kVar instanceof i) {
                    f fVar = this.f16369n;
                    fVar.f16374w.add(kVar);
                    fVar.n(fVar.j() + 1);
                }
                break;
            default:
                k kVar2 = (k) obj;
                A7.m.f(kVar2, "segment");
                if (kVar2 instanceof i) {
                    f fVar2 = this.f16369n;
                    fVar2.f16374w.add(kVar2);
                    fVar2.n(fVar2.j() + 1);
                }
                break;
        }
        return q.f19267a;
    }
}
