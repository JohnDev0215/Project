package p2;

import android.os.Process;
import java.util.concurrent.ThreadFactory;

/* JADX INFO: compiled from: PriorityThreadFactory.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class f implements ThreadFactory {

    /* JADX INFO: compiled from: PriorityThreadFactory.java */
    public class a extends Thread {
        public a(Runnable runnable) {
            super(runnable);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            int iA = f.this.a();
            Process.setThreadPriority(iA);
            super.run();
            int threadPriority = Process.getThreadPriority(Process.myTid());
            if (threadPriority != iA) {
                e.a("PriorityThreadFactory", "[ expectPriority: %d, curPriority: %d ] Thread priority is changed, be careful don't change the thread priority in #run() !", Integer.valueOf(iA), Integer.valueOf(threadPriority));
            }
        }
    }

    public abstract int a();

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        return new a(runnable);
    }
}
