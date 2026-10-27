package p;

import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class wa implements Executor {
    public final /* synthetic */ int c;

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        switch (this.c) {
            case 0:
                new Thread(runnable).start();
                break;
            case 1:
                runnable.run();
                break;
            case 2:
                runnable.run();
                break;
            default:
                runnable.run();
                break;
        }
    }
}
