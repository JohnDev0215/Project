package defpackage;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import defpackage.l93;

/* JADX INFO: compiled from: ThreadedInputConnectionFactory.java */
/* JADX INFO: loaded from: classes3.dex */
public final class k93 implements Runnable {
    public final /* synthetic */ View e;
    public final /* synthetic */ l93.a f;
    public final /* synthetic */ int g;
    public final /* synthetic */ l93 h;

    public k93(l93 l93Var, View view, l93.a aVar, int i) {
        this.h = l93Var;
        this.e = view;
        this.f = aVar;
        this.g = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        l93 l93Var = this.h;
        if (l93Var.a.d(l93Var.b)) {
            return;
        }
        l93.a aVar = this.f;
        int i = this.g;
        if (i <= 0) {
            aVar.getClass();
            z7.c();
            if (aVar.a) {
                return;
            }
            Log.w("cr_Ime", "onRegisterProxyViewFailure");
            return;
        }
        int i2 = i - 1;
        View view = this.e;
        Handler handler = view.getHandler();
        if (handler == null) {
            return;
        }
        handler.post(new k93(l93Var, view, aVar, i2));
    }
}
