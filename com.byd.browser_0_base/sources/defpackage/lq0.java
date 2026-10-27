package defpackage;

import android.content.Context;
import android.view.View;
import defpackage.ny0;

/* JADX INFO: compiled from: FullScreenIncognitoReauthCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public final class lq0 extends ky0 {
    public final bi1 e;
    public final qy0 f;
    public final xr1 g;
    public my0 h;

    public lq0(Context context, ny0 ny0Var, ny0.a aVar, Runnable runnable, bi1 bi1Var, qy0 qy0Var, xr1 xr1Var) {
        super(context, ny0Var, aVar, runnable);
        this.g = xr1Var;
        this.e = bi1Var;
        this.f = qy0Var;
    }

    @Override // defpackage.jy0
    public final void a() {
        qy0 qy0Var = this.f;
        qy0Var.getClass();
        b(new py0(0, qy0Var), true);
        View view = this.d;
        bi1 bi1Var = this.e;
        my0 my0Var = new my0(bi1Var, view, this.g);
        this.h = my0Var;
        bi1Var.i(1, 3, my0Var.b, false);
    }

    @Override // defpackage.jy0
    public final void hide(int i) {
        my0 my0Var = this.h;
        my0Var.a.b(i, my0Var.b);
        this.c.c();
    }
}
