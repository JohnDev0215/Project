package i3;

import android.os.Handler;
import android.os.HandlerThread;

/* JADX INFO: compiled from: ThreadPoolManager.java */
/* JADX INFO: loaded from: classes.dex */
public class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private HandlerThread f7006a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f7007b;

    private void b() {
        h3.i.b("ThreadPoolManager").b("start", new Object[0]);
        HandlerThread handlerThread = new HandlerThread("MediaHandlerThread");
        this.f7006a = handlerThread;
        handlerThread.start();
        this.f7007b = new Handler(this.f7006a.getLooper());
        h3.i.b("ThreadPoolManager").b("getMediaHandler start thread", new Object[0]);
    }

    public synchronized Handler a() {
        h3.i.b("ThreadPoolManager").b("getMediaHandler", new Object[0]);
        if (this.f7007b == null) {
            b();
        }
        return this.f7007b;
    }
}
