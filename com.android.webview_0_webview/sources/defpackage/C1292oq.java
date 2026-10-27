package defpackage;

import android.os.Handler;
import android.os.Message;
import android.view.View;

/* JADX INFO: renamed from: oq, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1292oq implements Handler.Callback {
    public View a;
    public final /* synthetic */ C1347pq b;

    public C1292oq(C1347pq c1347pq, View view) {
        this.b = c1347pq;
        this.a = view;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message.what == 1) {
            this.b.b = false;
            this.a.sendAccessibilityEvent(4096);
            return true;
        }
        StringBuilder sbF = Z7.f("AccessibilityInjector: unhandled message: ");
        sbF.append(message.what);
        throw new IllegalStateException(sbF.toString());
    }
}
