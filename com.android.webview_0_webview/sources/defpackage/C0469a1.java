package defpackage;

import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: renamed from: a1, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0469a1 extends AbstractC0525b1 {
    public final Object a = new Object();
    public final ExecutorService b = Executors.newFixedThreadPool(2, new Z0(this));

    @Override // defpackage.AbstractC0525b1
    public boolean a() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }
}
