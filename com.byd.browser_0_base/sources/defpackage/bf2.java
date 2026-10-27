package defpackage;

import org.chromium.chrome.browser.feedback.ScreenshotTask;

/* JADX INFO: compiled from: ScreenshotTask.java */
/* JADX INFO: loaded from: classes.dex */
public final class bf2 implements Runnable {
    public final /* synthetic */ ScreenshotTask e;

    public bf2(ScreenshotTask screenshotTask) {
        this.e = screenshotTask;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.e.a();
    }
}
