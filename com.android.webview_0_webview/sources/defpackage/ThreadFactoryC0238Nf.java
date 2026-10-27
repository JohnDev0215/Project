package defpackage;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: renamed from: Nf, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ThreadFactoryC0238Nf implements ThreadFactory {
    public final String a;
    public final AtomicInteger b = new AtomicInteger();
    public final ThreadFactory c = Executors.defaultThreadFactory();

    public ThreadFactoryC0238Nf(String str) {
        Cif.g(str, "Name must not be null");
        this.a = str;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        Thread threadNewThread = this.c.newThread(new RunnableC0256Of(runnable, 0));
        String str = this.a;
        int andIncrement = this.b.getAndIncrement();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 13);
        sb.append(str);
        sb.append("[");
        sb.append(andIncrement);
        sb.append("]");
        threadNewThread.setName(sb.toString());
        return threadNewThread;
    }
}
