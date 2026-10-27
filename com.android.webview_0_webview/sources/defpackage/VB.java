package defpackage;

import android.os.Handler;
import android.os.HandlerThread;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class VB {
    public static final Handler a;

    static {
        HandlerThread handlerThread = new HandlerThread("InputConnectionHandlerThread", 5);
        handlerThread.start();
        a = new Handler(handlerThread.getLooper());
    }
}
