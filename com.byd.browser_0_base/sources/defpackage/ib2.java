package defpackage;

import android.os.Handler;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: RequestExecutor.java */
/* JADX INFO: loaded from: classes.dex */
public final class ib2<T> implements Runnable {
    public final Callable<T> e;
    public final wx<T> f;
    public final Handler g;

    /* JADX INFO: compiled from: RequestExecutor.java */
    public class a implements Runnable {
        public final /* synthetic */ wx e;
        public final /* synthetic */ Object f;

        public a(wx wxVar, Object obj) {
            this.e = wxVar;
            this.f = obj;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public final void run() {
            this.e.accept(this.f);
        }
    }

    public ib2(Handler handler, so0 so0Var, to0 to0Var) {
        this.e = so0Var;
        this.f = to0Var;
        this.g = handler;
    }

    @Override // java.lang.Runnable
    public final void run() {
        T tCall;
        try {
            tCall = this.e.call();
        } catch (Exception unused) {
            tCall = null;
        }
        this.g.post(new a(this.f, tCall));
    }
}
