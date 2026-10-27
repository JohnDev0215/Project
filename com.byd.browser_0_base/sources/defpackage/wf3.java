package defpackage;

import java.util.concurrent.Executor;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: TrustedWebActivityClientWrappers.java */
/* JADX INFO: loaded from: classes.dex */
public final class wf3 {
    public static final e8 a;

    /* JADX INFO: compiled from: TrustedWebActivityClientWrappers.java */
    public interface a {
    }

    /* JADX INFO: compiled from: TrustedWebActivityClientWrappers.java */
    public interface b {
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [e8] */
    static {
        final int i = 1;
        a = new Executor() { // from class: e8
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                switch (i) {
                    case 1:
                        PostTask.c(rh3.c, runnable);
                        break;
                    default:
                        new Thread(runnable).start();
                        break;
                }
            }
        };
    }
}
