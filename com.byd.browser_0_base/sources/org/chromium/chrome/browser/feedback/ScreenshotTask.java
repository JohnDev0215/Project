package org.chromium.chrome.browser.feedback;

import J.N;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import defpackage.ze2;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.app.ChromeActivity;

/* JADX INFO: loaded from: classes.dex */
public final class ScreenshotTask implements ze2 {
    public final Activity a;
    public boolean b;
    public Runnable c;
    public final int d;

    public ScreenshotTask() {
        throw null;
    }

    public ScreenshotTask(Activity activity) {
        this.a = activity;
        this.d = 0;
    }

    public final void a() {
        this.b = true;
        Runnable runnable = this.c;
        if (runnable != null) {
            runnable.run();
        }
        this.c = null;
    }

    public final boolean b(Activity activity) {
        if (activity == null) {
            return false;
        }
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getRootView().getWindowVisibleDisplayFrame(rect);
        N.MJ03E235(this, ((ChromeActivity) activity).O, rect.width(), rect.height());
        return true;
    }

    @CalledByNative
    public final void onBytesReceived(byte[] bArr) {
        if (bArr != null) {
            BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        }
        a();
    }
}
