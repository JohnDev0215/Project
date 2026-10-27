package defpackage;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Ru extends FutureTask {
    public final /* synthetic */ Su s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Ru(Su su, Callable callable) {
        super(callable);
        this.s = su;
    }

    @Override // java.util.concurrent.FutureTask
    public void done() {
        try {
            Su.a(this.s, get());
        } catch (InterruptedException e) {
            Ts.f("AsyncTask", e.toString(), new Object[0]);
        } catch (CancellationException unused) {
            Su.a(this.s, null);
        } catch (ExecutionException e2) {
            throw new RuntimeException("An error occurred while executing doInBackground()", e2.getCause());
        }
    }
}
