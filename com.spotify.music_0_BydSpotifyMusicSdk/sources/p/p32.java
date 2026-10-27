package p;

import android.os.Process;

/* JADX INFO: loaded from: classes.dex */
public final class p32 extends Thread {
    public final int c;

    public p32(Runnable runnable, String str, int i) {
        super(runnable, str);
        this.c = i;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        Process.setThreadPriority(this.c);
        super.run();
    }
}
