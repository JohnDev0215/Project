package defpackage;

import android.os.Process;
import java.util.concurrent.ThreadFactory;

/* JADX INFO: compiled from: RequestExecutor.java */
/* JADX INFO: loaded from: classes.dex */
public final class gb2 implements ThreadFactory {
    public final String a = "fonts-androidx";
    public final int b = 10;

    /* JADX INFO: compiled from: RequestExecutor.java */
    public static class a extends Thread {
        public final int e;

        public a(Runnable runnable, String str, int i) {
            super(runnable, str);
            this.e = i;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            Process.setThreadPriority(this.e);
            super.run();
        }
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        return new a(runnable, this.a, this.b);
    }
}
