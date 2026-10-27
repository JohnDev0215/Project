package a.h.h;

import android.os.Process;
import java.util.concurrent.ThreadFactory;

/* JADX INFO: loaded from: classes.dex */
public class j implements ThreadFactory {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f767a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f768b;

    public static class a extends Thread {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f769a;

        public a(Runnable runnable, String str, int i) {
            super(runnable, str);
            this.f769a = i;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Process.setThreadPriority(this.f769a);
            super.run();
        }
    }

    public j(String str, int i) {
        this.f767a = str;
        this.f768b = i;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        return new a(runnable, this.f767a, this.f768b);
    }
}
