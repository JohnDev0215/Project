package p;

import io.reactivex.rxjava3.disposables.Disposable;

/* JADX INFO: loaded from: classes.dex */
public final class as2 implements zc1 {
    public final rd1 b;
    public String c;
    public Disposable d = Disposable.e(io.reactivex.rxjava3.internal.functions.k.b);

    public as2(rd1 rd1Var) {
        this.b = rd1Var;
    }

    @Override // p.zc1
    public final void a() {
        if (this.d.isDisposed()) {
            return;
        }
        this.d.dispose();
    }
}
