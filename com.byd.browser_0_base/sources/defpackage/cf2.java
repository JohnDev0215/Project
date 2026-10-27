package defpackage;

import android.app.Activity;
import android.graphics.Bitmap;
import org.chromium.chrome.browser.feedback.ScreenshotTask;

/* JADX INFO: compiled from: ScreenshotTask.java */
/* JADX INFO: loaded from: classes.dex */
public final class cf2 implements Runnable {
    public final /* synthetic */ Activity e;
    public final /* synthetic */ ScreenshotTask f;

    public cf2(ScreenshotTask screenshotTask, Activity activity) {
        this.f = screenshotTask;
        this.e = activity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        uh3.b(this.e.getWindow().getDecorView().getRootView(), 600, Bitmap.Config.ARGB_8888);
        this.f.a();
    }
}
