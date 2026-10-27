package p;

import io.reactivex.rxjava3.disposables.Disposable;

/* JADX INFO: loaded from: classes.dex */
public final class xg2 implements io.reactivex.rxjava3.functions.f {
    public final /* synthetic */ mn2 a;
    public final /* synthetic */ zg2 b;
    public final /* synthetic */ Disposable c;

    public xg2(String str, mn2 mn2Var, zg2 zg2Var, Disposable disposable) {
        this.a = mn2Var;
        this.b = zg2Var;
        this.c = disposable;
    }

    @Override // io.reactivex.rxjava3.functions.f
    public final void cancel() {
        this.a.getClass();
        this.b.a.size();
        this.c.dispose();
        zg2 zg2Var = this.b;
        mn2 mn2Var = this.a;
        synchronized (zg2Var) {
            zg2Var.a.remove(mn2Var);
            mn2Var.getClass();
            zg2Var.a.size();
        }
    }
}
