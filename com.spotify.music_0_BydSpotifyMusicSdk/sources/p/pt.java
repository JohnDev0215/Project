package p;

import android.content.Context;
import io.reactivex.rxjava3.disposables.Disposable;

/* JADX INFO: loaded from: classes.dex */
public final class pt implements zc1 {
    public final Context b;
    public final ut c;
    public final vx1 d;
    public final ad1 e;
    public Disposable f = Disposable.e(io.reactivex.rxjava3.internal.functions.k.b);

    public pt(Context context, ut utVar, vx1 vx1Var, ad1 ad1Var) {
        this.b = context;
        this.c = utVar;
        this.d = vx1Var;
        this.e = ad1Var;
    }

    @Override // p.zc1
    public final void a() {
        if (this.f.isDisposed()) {
            return;
        }
        this.f.dispose();
    }
}
