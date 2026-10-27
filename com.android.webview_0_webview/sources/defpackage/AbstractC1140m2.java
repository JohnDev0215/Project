package defpackage;

import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;

/* JADX INFO: renamed from: m2, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1140m2 {
    public final void a(int i, Handler handler) {
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
        handler.post(new RunnableC1084l2(this, i));
    }

    public final void b(Typeface typeface, Handler handler) {
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
        handler.post(new RunnableC1028k2(this, typeface));
    }

    public abstract void c(Typeface typeface);
}
