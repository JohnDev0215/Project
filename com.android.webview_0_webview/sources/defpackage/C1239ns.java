package defpackage;

import android.os.Handler;
import android.os.HandlerThread;

/* JADX INFO: renamed from: ns, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1239ns {
    public static final C1239ns e = new C1239ns();
    public final HandlerThread a;
    public final Handler b;
    public C1574ty c;
    public boolean d;

    public C1239ns() {
        HandlerThread handlerThread = new HandlerThread("seed_holder");
        this.a = handlerThread;
        handlerThread.start();
        this.b = new Handler(this.a.getLooper());
    }
}
