package p;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class y23 implements Executor {
    public final /* synthetic */ int c;
    public final Handler e;

    public y23(int i) {
        this.c = i;
        if (i == 1) {
            this.e = new Handler(Looper.getMainLooper());
        } else if (i != 2) {
            this.e = new qz2(Looper.getMainLooper());
        } else {
            this.e = new Handler(Looper.getMainLooper());
        }
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        Handler handler = this.e;
        switch (this.c) {
            case 0:
                handler.post(runnable);
                break;
            case 1:
                handler.post(runnable);
                break;
            default:
                handler.post(runnable);
                break;
        }
    }
}
