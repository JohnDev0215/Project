package defpackage;

import android.content.Context;
import org.chromium.content.browser.TracingControllerAndroidImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Gy implements DD {
    public final /* synthetic */ Hy a;

    public Gy(Hy hy) {
        this.a = hy;
    }

    @Override // defpackage.DD
    public void a() {
        Context context = Is.a;
        this.a.s.k = new TracingControllerAndroidImpl(context);
        TracingControllerAndroidImpl tracingControllerAndroidImpl = this.a.s.k;
        context.registerReceiver(tracingControllerAndroidImpl.b, tracingControllerAndroidImpl.c);
    }

    @Override // defpackage.DD
    public void b() {
    }
}
