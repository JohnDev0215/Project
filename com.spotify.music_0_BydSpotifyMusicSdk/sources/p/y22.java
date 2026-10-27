package p;

import io.reactivex.rxjava3.disposables.Disposable;

/* JADX INFO: loaded from: classes.dex */
public final class y22 implements zc1 {
    public final a42 b;
    public final f32 c;
    public final aj d;
    public Disposable e = Disposable.e(io.reactivex.rxjava3.internal.functions.k.b);

    public y22(a42 a42Var, f32 f32Var, aj ajVar) {
        this.b = a42Var;
        this.c = f32Var;
        this.d = ajVar;
    }

    @Override // p.zc1
    public final void a() {
        this.e.dispose();
    }
}
