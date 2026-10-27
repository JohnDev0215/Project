package e1;

import F0.V;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: e1.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1189b extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f13764m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ v f13765n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1189b(v vVar, int i7) {
        super(1);
        this.f13764m = i7;
        this.f13765n = vVar;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f13764m) {
            case 0:
                v vVar = this.f13765n;
                vVar.show();
                return new V(vVar, 7);
            default:
                v vVar2 = this.f13765n;
                if (vVar2.f13813p.f13809a) {
                    vVar2.o.a();
                }
                return m7.q.f19267a;
        }
    }
}
