package p;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class va implements Executor {
    public final Executor e;
    public Runnable k;
    public final /* synthetic */ int c = 0;
    public final Object l = new Object();
    public final ArrayDeque m = new ArrayDeque();

    public va(wa waVar) {
        this.e = waVar;
    }

    public final void a() {
        switch (this.c) {
            case 0:
                synchronized (this.l) {
                    try {
                        Runnable runnable = (Runnable) this.m.poll();
                        this.k = runnable;
                        if (runnable != null) {
                            this.e.execute(runnable);
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                    break;
                }
                return;
            default:
                synchronized (this.l) {
                    Object objPoll = this.m.poll();
                    Runnable runnable2 = (Runnable) objPoll;
                    this.k = runnable2;
                    if (objPoll != null) {
                        this.e.execute(runnable2);
                    }
                    break;
                }
                return;
        }
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        switch (this.c) {
            case 0:
                synchronized (this.l) {
                    try {
                        this.m.add(new ua(this, 0, runnable));
                        if (this.k == null) {
                            a();
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                    break;
                }
                return;
            default:
                v03.j(runnable, "command");
                synchronized (this.l) {
                    this.m.offer(new ua(this, runnable));
                    if (this.k == null) {
                        a();
                    }
                    break;
                }
                return;
        }
    }
}
