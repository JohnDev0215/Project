package n3;

import z7.InterfaceC2782k;

/* JADX INFO: renamed from: n3.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1951p extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f19888m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C1930E f19889n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1951p(C1930E c1930e, int i7) {
        super(1);
        this.f19888m = i7;
        this.f19889n = c1930e;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f19888m) {
            case 0:
                A7.m.f((y) obj, "destination");
                return Boolean.valueOf(!this.f19889n.f19809m.containsKey(Integer.valueOf(r2.f19917q)));
            default:
                A7.m.f((y) obj, "destination");
                return Boolean.valueOf(!this.f19889n.f19809m.containsKey(Integer.valueOf(r2.f19917q)));
        }
    }
}
