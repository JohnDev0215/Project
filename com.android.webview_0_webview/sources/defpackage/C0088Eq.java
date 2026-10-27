package defpackage;

import android.os.StrictMode;
import java.util.Objects;
import org.chromium.base.BuildInfo;

/* JADX INFO: renamed from: Eq, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0088Eq {
    public static C0088Eq a;

    public static void a() {
        if (BuildInfo.b()) {
            StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
            Es.j("/data/local/tmp/webview-command-line");
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
        } else {
            Es.i(null);
        }
        C0088Eq c0088Eq = a;
        if (c0088Eq == null) {
            try {
                a = (C0088Eq) Class.forName("org.chromium.android_webview.common.CommandLineUtilInternal").newInstance();
            } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException unused) {
                a = new C0088Eq();
            }
            c0088Eq = a;
        }
        Es.e();
        Objects.requireNonNull(c0088Eq);
    }
}
