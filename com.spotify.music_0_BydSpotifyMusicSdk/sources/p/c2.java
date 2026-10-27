package p;

import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class c2 {
    public static final c2 d = new c2(null, null);
    public final Runnable a;
    public final Executor b;
    public c2 c;

    public c2(Runnable runnable, Executor executor) {
        this.a = runnable;
        this.b = executor;
    }
}
