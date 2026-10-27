package defpackage;

import android.content.Intent;
import java.util.concurrent.FutureTask;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.base.SplitCompatIntentService;

/* JADX INFO: compiled from: TracingNotificationServiceImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public class we3 extends SplitCompatIntentService.a {
    @Override // org.chromium.chrome.browser.base.SplitCompatIntentService.a
    public final void a(Intent intent) {
        d73 d73Var = rh3.a;
        ve3 ve3Var = new ve3(0, intent);
        Object obj = PostTask.a;
        FutureTask futureTask = new FutureTask(ve3Var, null);
        PostTask.d(d73Var, futureTask);
        try {
            futureTask.get();
        } catch (Exception e) {
            throw new uv(e);
        }
    }
}
