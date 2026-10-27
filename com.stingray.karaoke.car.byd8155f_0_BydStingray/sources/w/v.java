package w;

import x.A0;
import x.x0;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class v extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f23143m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ x0 f23144n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ v(x0 x0Var, int i7) {
        super(1);
        this.f23143m = i7;
        this.f23144n = x0Var;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f23143m) {
            case 0:
                return Boolean.valueOf(!A7.m.a(obj, this.f23144n.d.getValue()));
            case 1:
                return new A0(this.f23144n, 0);
            default:
                return new A0(this.f23144n, 1);
        }
    }
}
