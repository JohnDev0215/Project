package defpackage;

import J.N;
import android.webkit.HttpAuthHandler;
import org.chromium.android_webview.AwHttpAuthHandler;

/* JADX INFO: renamed from: Kb, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0181Kb extends HttpAuthHandler {
    public AwHttpAuthHandler a;

    public C0181Kb(AwHttpAuthHandler awHttpAuthHandler) {
        this.a = awHttpAuthHandler;
    }

    @Override // android.webkit.HttpAuthHandler
    public void cancel() {
        AwHttpAuthHandler awHttpAuthHandler = this.a;
        long j = awHttpAuthHandler.a;
        if (j != 0) {
            N.MylO8zte(j, awHttpAuthHandler);
            awHttpAuthHandler.a = 0L;
        }
    }

    @Override // android.webkit.HttpAuthHandler
    public void proceed(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        AwHttpAuthHandler awHttpAuthHandler = this.a;
        long j = awHttpAuthHandler.a;
        if (j != 0) {
            N.MWR2vWXA(j, awHttpAuthHandler, str, str2);
            awHttpAuthHandler.a = 0L;
        }
    }

    @Override // android.webkit.HttpAuthHandler
    public boolean useHttpAuthUsernamePassword() {
        return this.a.b;
    }
}
