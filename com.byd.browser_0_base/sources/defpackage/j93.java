package defpackage;

import android.os.Handler;
import android.view.View;
import defpackage.l93;

/* JADX INFO: compiled from: ThreadedInputConnectionFactory.java */
/* JADX INFO: loaded from: classes3.dex */
public final class j93 implements Runnable {
    public final /* synthetic */ View e;
    public final /* synthetic */ l93 f;

    /* JADX INFO: compiled from: ThreadedInputConnectionFactory.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            j93 j93Var = j93.this;
            l93 l93Var = j93Var.f;
            l93.a aVar = l93Var.d;
            View view = j93Var.e;
            Handler handler = view.getHandler();
            if (handler == null) {
                return;
            }
            handler.post(new k93(l93Var, view, aVar, 1));
        }
    }

    public j93(l93 l93Var, View view) {
        this.f = l93Var;
        this.e = view;
    }

    @Override // java.lang.Runnable
    public final void run() {
        l93 l93Var = this.f;
        l93Var.b.onWindowFocusChanged(true);
        l93Var.a.d(this.e);
        l93Var.getClass();
        l93.b.a.post(new a());
    }
}
