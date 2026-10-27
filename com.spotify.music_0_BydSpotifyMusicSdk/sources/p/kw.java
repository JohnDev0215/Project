package p;

import java.util.concurrent.ThreadFactory;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class kw implements ThreadFactory {
    public final /* synthetic */ String c;

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, this.c);
        thread.setPriority(10);
        return thread;
    }
}
