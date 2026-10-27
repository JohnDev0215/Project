package p;

/* JADX INFO: loaded from: classes.dex */
public final class gs extends q61 implements fs {
    public final js m;

    public gs(kotlinx.coroutines.g gVar) {
        this.m = gVar;
    }

    @Override // p.fs
    public final boolean a(Throwable th) {
        return k().x(th);
    }

    @Override // p.ry0
    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        l((Throwable) obj);
        return lq2.a;
    }

    @Override // p.t61
    public final void l(Throwable th) {
        ((kotlinx.coroutines.g) this.m).t(k());
    }
}
