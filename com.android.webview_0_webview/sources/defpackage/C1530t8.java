package defpackage;

import J.N;
import android.webkit.PacProcessor;
import com.android.webview.chromium.WebViewChromiumFactoryProvider;
import org.chromium.base.JNIUtils;

/* JADX INFO: renamed from: t8, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1530t8 implements PacProcessor {
    public C0337So a;

    public C1530t8(AbstractC1420r8 abstractC1420r8) {
        JNIUtils.b = WebViewChromiumFactoryProvider.class.getClassLoader();
        try {
            C1569tt.l.b();
            N.MNlWqe6a();
            this.a = AbstractC0319Ro.a;
        } catch (C1734wt e) {
            throw new RuntimeException("Error initializing WebView library", e);
        }
    }

    public String findProxyForUrl(String str) {
        C0337So c0337So = this.a;
        return N.ME9i0MKK(c0337So.a, c0337So, str);
    }

    public boolean setProxyScript(String str) {
        C0337So c0337So = this.a;
        return N.MEjwJwuS(c0337So.a, c0337So, str);
    }
}
