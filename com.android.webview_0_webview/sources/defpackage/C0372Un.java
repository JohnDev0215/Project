package defpackage;

import android.os.Trace;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: renamed from: Un, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0372Un {
    public final WindowAndroid a;

    public C0372Un(WindowAndroid windowAndroid) {
        Trace.beginSection("WindowAndroidWrapper.constructor");
        try {
            this.a = windowAndroid;
            new C0248Np(this, new RunnableC0354Tn(windowAndroid, null));
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
