package Q7;

import o8.C2058c;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class F extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f7174m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C2058c f7175n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ F(C2058c c2058c, int i7) {
        super(1);
        this.f7174m = i7;
        this.f7175n = c2058c;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f7174m) {
            case 0:
                C2058c c2058c = (C2058c) obj;
                A7.m.f(c2058c, "it");
                return Boolean.valueOf(!c2058c.d() && A7.m.a(c2058c.e(), this.f7175n));
            default:
                R7.h hVar = (R7.h) obj;
                A7.m.f(hVar, "it");
                return hVar.e(this.f7175n);
        }
    }
}
