package j4;

import java.util.concurrent.ThreadFactory;

/* JADX INFO: renamed from: j4.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class ThreadFactoryC1541b implements ThreadFactory {
    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        return new C1540a(runnable);
    }
}
