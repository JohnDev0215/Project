package e0;

import z7.InterfaceC2782k;

/* JADX INFO: renamed from: e0.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1161a extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f13690m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f13691n;
    public final /* synthetic */ InterfaceC2782k o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1161a(InterfaceC2782k interfaceC2782k, InterfaceC2782k interfaceC2782k2, int i7) {
        super(1);
        this.f13690m = i7;
        this.f13691n = interfaceC2782k;
        this.o = interfaceC2782k2;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        int i7;
        switch (this.f13690m) {
            case 0:
                C1171k c1171k = (C1171k) obj;
                synchronized (AbstractC1174n.f13721b) {
                    i7 = AbstractC1174n.d;
                    AbstractC1174n.d = i7 + 1;
                }
                return new C1163c(i7, c1171k, this.f13691n, this.o);
            case 1:
                this.f13691n.b(obj);
                this.o.b(obj);
                return m7.q.f19267a;
            default:
                this.f13691n.b(obj);
                this.o.b(obj);
                return m7.q.f19267a;
        }
    }
}
