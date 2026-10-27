package p;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes.dex */
public final class yx2 {
    public final ob2 a;
    public final wk0 b;
    public final Handler c = new Handler(Looper.getMainLooper());
    public final xx2 d = new xx2(0, this);

    public yx2(ExecutorService executorService) {
        ob2 ob2Var = new ob2(executorService);
        this.a = ob2Var;
        this.b = new wk0(ob2Var);
    }

    public final void a(Runnable runnable) {
        this.a.execute(runnable);
    }
}
