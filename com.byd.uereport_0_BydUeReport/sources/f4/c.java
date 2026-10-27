package f4;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.byd.mvp.ui.jsbridge.BridgeWebView;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Iterator;

/* JADX INFO: compiled from: BridgeWebViewClient.java */
/* JADX INFO: loaded from: classes2.dex */
public class c extends WebViewClient {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public BridgeWebView f4959a;

    public c(BridgeWebView bridgeWebView) {
        this.f4959a = bridgeWebView;
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) throws Throwable {
        super.onPageFinished(webView, str);
        b.e(webView, "WebViewJavascriptBridge.js");
        BridgeWebView bridgeWebView = this.f4959a;
        if (bridgeWebView != null) {
            if (bridgeWebView.getStartupMessage() != null) {
                Iterator<f> it = this.f4959a.getStartupMessage().iterator();
                while (it.hasNext()) {
                    this.f4959a.b(it.next());
                }
                this.f4959a.setStartupMessage(null);
            }
            this.f4959a.i();
        }
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i8, String str, String str2) {
        super.onReceivedError(webView, i8, str, str2);
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        String strName = Charset.defaultCharset().name();
        if (str != null) {
            try {
                if (str.contains("https://") || str.contains("http://")) {
                    str = URLEncoder.encode(str, Charset.defaultCharset().name());
                }
            } catch (UnsupportedEncodingException e8) {
                e8.printStackTrace();
            }
        }
        str = URLDecoder.decode(str, strName);
        if (str.startsWith("yy://return/")) {
            this.f4959a.e(str);
            return true;
        }
        if (!str.startsWith("yy://")) {
            return super.shouldOverrideUrlLoading(webView, str);
        }
        this.f4959a.c();
        return true;
    }
}
