package defpackage;

import android.app.Activity;
import android.graphics.Bitmap;
import org.chromium.chrome.browser.share.screenshot.EditorScreenshotTask;

/* JADX INFO: compiled from: EditorScreenshotTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class eg0 implements Runnable {
    public final /* synthetic */ Activity e;
    public final /* synthetic */ EditorScreenshotTask f;

    public eg0(EditorScreenshotTask editorScreenshotTask, Activity activity) {
        this.f = editorScreenshotTask;
        this.e = activity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f.a(uh3.b(this.e.getWindow().getDecorView().getRootView(), 0, Bitmap.Config.ARGB_8888));
    }
}
