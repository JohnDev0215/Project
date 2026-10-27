package defpackage;

import android.R;
import android.content.Context;
import android.os.Trace;
import java.util.Objects;
import org.chromium.android_webview.common.AwResource;

/* JADX INFO: renamed from: rb, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1441rb implements Runnable {
    public final /* synthetic */ int s;
    public final /* synthetic */ Context t;
    public final /* synthetic */ C1551tb u;

    public RunnableC1441rb(C1551tb c1551tb, int i, Context context) {
        this.u = c1551tb;
        this.s = i;
        this.t = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        C1551tb c1551tb = this.u;
        int i = this.s;
        Context context = this.t;
        Objects.requireNonNull(c1551tb);
        Trace.beginSection("WebViewChromiumAwInit.setUpResources");
        try {
            AbstractC0733en.a(i);
            AwResource.b = context.getResources();
            AwResource.a = R.array.config_keySystemUuidMapping;
            Trace.endSection();
        } catch (Throwable th) {
            try {
                Trace.endSection();
            } catch (Throwable th2) {
                AbstractC0279Pk.a.a(th, th2);
            }
            throw th;
        }
    }
}
