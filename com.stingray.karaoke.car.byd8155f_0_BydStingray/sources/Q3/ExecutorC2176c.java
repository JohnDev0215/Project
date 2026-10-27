package q3;

import java.util.concurrent.Executor;

/* JADX INFO: renamed from: q3.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ExecutorC2176c implements Executor {
    public final /* synthetic */ int l;

    private final void a(Runnable runnable) {
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        switch (this.l) {
            case 0:
                runnable.run();
                break;
        }
    }
}
