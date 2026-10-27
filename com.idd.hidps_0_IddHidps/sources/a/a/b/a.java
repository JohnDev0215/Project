package a.a.b;

import android.os.Handler;
import android.os.HandlerThread;

/* JADX INFO: loaded from: classes.dex */
public class a extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static a f4a;

    private a(HandlerThread handlerThread) {
        super(handlerThread.getLooper());
    }

    public static a a() {
        if (f4a == null) {
            synchronized (a.class) {
                HandlerThread handlerThread = new HandlerThread("background");
                handlerThread.start();
                f4a = new a(handlerThread);
            }
        }
        return f4a;
    }
}
