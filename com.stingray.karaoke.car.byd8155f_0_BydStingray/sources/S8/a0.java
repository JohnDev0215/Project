package S8;

import q7.InterfaceC2189c;

/* JADX INFO: loaded from: classes.dex */
public final class a0 extends C0712i {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final d0 f8102t;

    public a0(InterfaceC2189c interfaceC2189c, d0 d0Var) {
        super(1, interfaceC2189c);
        this.f8102t = d0Var;
    }

    @Override // S8.C0712i
    public final Throwable p(d0 d0Var) {
        Throwable thC;
        d0 d0Var2 = this.f8102t;
        d0Var2.getClass();
        Object obj = d0.l.get(d0Var2);
        return (!(obj instanceof c0) || (thC = ((c0) obj).c()) == null) ? obj instanceof C0720q ? ((C0720q) obj).f8130a : d0Var.y() : thC;
    }

    @Override // S8.C0712i
    public final String z() {
        return "AwaitContinuation";
    }
}
