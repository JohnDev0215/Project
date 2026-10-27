package defpackage;

import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;

/* JADX INFO: renamed from: Dq, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0070Dq {
    public final Queue a = new ConcurrentLinkedQueue();
    public final InterfaceC0052Cq b;

    public C0070Dq(InterfaceC0052Cq interfaceC0052Cq) {
        this.b = interfaceC0052Cq;
    }

    public void a(Runnable runnable) {
        this.a.add(runnable);
        if (this.b.a()) {
            PostTask.b(AbstractC0538bE.a, new Runnable(this) { // from class: Bq
                public final C0070Dq s;

                {
                    this.s = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    this.s.b();
                }
            });
        }
    }

    public void b() {
        Queue queue = this.a;
        if (queue == null || queue.isEmpty()) {
            return;
        }
        Runnable runnable = (Runnable) this.a.poll();
        while (runnable != null) {
            runnable.run();
            runnable = (Runnable) this.a.poll();
        }
    }

    public Object c(FutureTask futureTask) {
        if (!this.b.a()) {
            throw new RuntimeException("Must be started before we block!");
        }
        if (ThreadUtils.j()) {
            throw new IllegalStateException("This method should only be called off the UI thread");
        }
        a(futureTask);
        try {
            return futureTask.get(4L, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            throw new RuntimeException("Probable deadlock detected due to WebView API being called on incorrect thread while the UI thread is blocked.", e);
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    public Object d(Callable callable) {
        return c(new FutureTask(callable));
    }
}
