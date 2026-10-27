package p;

import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class db implements Executor {
    public final /* synthetic */ int c;

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        switch (this.c) {
            case 1:
                eb.w().f.g.execute(runnable);
                break;
            case 2:
                runnable.run();
                break;
            case 3:
                runnable.run();
                break;
            case 4:
                runnable.run();
                break;
            default:
                runnable.run();
                break;
        }
    }
}
