package defpackage;

import java.util.concurrent.Executor;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class f8 implements Executor {
    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        f8 f8Var = i9.e;
        PostTask.c(d73.g, runnable);
    }
}
