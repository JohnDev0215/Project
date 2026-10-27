package p;

import android.os.Looper;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes.dex */
public abstract class ks2 {
    public static final ExecutorService a = f60.c("awaitEvenIfOnMainThread task continuation executor");

    public static Object a(b33 b33Var) throws InterruptedException, TimeoutException {
        boolean z;
        CountDownLatch countDownLatch = new CountDownLatch(1);
        b33Var.a(a, new af2(12, countDownLatch));
        if (Looper.getMainLooper() == Looper.myLooper()) {
            countDownLatch.await(3L, TimeUnit.SECONDS);
        } else {
            countDownLatch.await(4L, TimeUnit.SECONDS);
        }
        if (b33Var.d()) {
            return b33Var.c();
        }
        if (b33Var.d) {
            throw new CancellationException("Task is already canceled");
        }
        synchronized (b33Var.a) {
            z = b33Var.c;
        }
        if (z) {
            throw new IllegalStateException(b33Var.b());
        }
        throw new TimeoutException();
    }
}
