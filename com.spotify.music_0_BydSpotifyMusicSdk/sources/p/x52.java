package p;

/* JADX INFO: loaded from: classes.dex */
public final class x52 implements io.reactivex.rxjava3.functions.f {
    public final p61 a;

    public x52(uf2 uf2Var) {
        this.a = uf2Var;
    }

    @Override // io.reactivex.rxjava3.functions.f
    public final void cancel() {
        this.a.i(null);
    }
}
