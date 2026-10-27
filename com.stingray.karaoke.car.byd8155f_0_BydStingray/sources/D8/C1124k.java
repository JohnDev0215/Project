package d8;

import o8.C2061f;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: d8.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1124k extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f13599m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C1127n f13600n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1124k(C1127n c1127n, int i7) {
        super(1);
        this.f13599m = i7;
        this.f13600n = c1127n;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f13599m) {
            case 0:
                C2061f c2061f = (C2061f) obj;
                A7.m.f(c2061f, "it");
                return C1127n.v(this.f13600n, c2061f);
            default:
                C2061f c2061f2 = (C2061f) obj;
                A7.m.f(c2061f2, "it");
                return C1127n.w(this.f13600n, c2061f2);
        }
    }
}
