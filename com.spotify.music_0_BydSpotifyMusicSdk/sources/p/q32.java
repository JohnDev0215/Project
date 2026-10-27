package p;

import java.util.concurrent.ThreadFactory;

/* JADX INFO: loaded from: classes.dex */
public final class q32 implements ThreadFactory {
    public String c;
    public int e;

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        return new p32(runnable, this.c, this.e);
    }
}
