package defpackage;

import android.os.Handler;
import android.os.Looper;
import defpackage.gh3;
import defpackage.uo0;

/* JADX INFO: compiled from: CallbackWithHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class rl {
    public final a8 a;
    public final Handler b;

    public rl(gh3.a aVar, Handler handler) {
        this.a = aVar;
        this.b = handler;
    }

    public final void a(uo0.a aVar) {
        int i = aVar.b;
        boolean z = i == 0;
        Handler handler = this.b;
        a8 a8Var = this.a;
        if (z) {
            handler.post(new pl(a8Var, aVar.a));
        } else {
            handler.post(new ql(a8Var, i));
        }
    }

    public rl(ft0 ft0Var) {
        Handler handler;
        this.a = ft0Var;
        if (Looper.myLooper() == null) {
            handler = new Handler(Looper.getMainLooper());
        } else {
            handler = new Handler();
        }
        this.b = handler;
    }
}
