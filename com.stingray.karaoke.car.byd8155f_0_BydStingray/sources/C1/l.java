package C1;

import android.os.Process;

/* JADX INFO: loaded from: classes.dex */
public final class l extends Thread {
    public final int l;

    public l(Runnable runnable) {
        super(runnable, "fonts-androidx");
        this.l = 10;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        Process.setThreadPriority(this.l);
        super.run();
    }
}
