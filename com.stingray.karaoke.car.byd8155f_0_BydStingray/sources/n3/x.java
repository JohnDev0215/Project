package n3;

import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class x extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f19911m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ v f19912n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ x(v vVar, int i7) {
        super(1);
        this.f19911m = i7;
        this.f19912n = vVar;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f19911m) {
            case 0:
                A7.m.f((String) obj, "key");
                return Boolean.valueOf(!this.f19912n.c().contains(r2));
            default:
                A7.m.f((String) obj, "key");
                return Boolean.valueOf(!this.f19912n.c().contains(r2));
        }
    }
}
