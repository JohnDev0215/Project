package defpackage;

import android.webkit.TracingController;
import android.webkit.WebViewDelegate;
import com.android.webview.chromium.WebViewChromiumFactoryProvider;

/* JADX INFO: renamed from: p8, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1311p8 {
    public static TracingController a(WebViewChromiumFactoryProvider webViewChromiumFactoryProvider, C1551tb c1551tb) {
        return new W8(new K8(c1551tb.p.a, c1551tb.d()));
    }

    public static String b(WebViewDelegate webViewDelegate) {
        return webViewDelegate.getDataDirectorySuffix();
    }
}
