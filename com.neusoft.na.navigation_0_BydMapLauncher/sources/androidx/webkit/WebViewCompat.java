package androidx.webkit;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Looper;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.webkit.internal.WebMessagePortImpl;
import androidx.webkit.internal.WebViewFeatureInternal;
import androidx.webkit.internal.WebViewGlueCommunicator;
import androidx.webkit.internal.WebViewProviderAdapter;
import androidx.webkit.internal.WebViewProviderFactory;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.Executor;
import org.chromium.support_lib_boundary.WebViewProviderBoundaryInterface;

/* JADX INFO: loaded from: classes.dex */
public class WebViewCompat {
    private static final Uri WILDCARD_URI = Uri.parse("*");
    private static final Uri EMPTY_URI = Uri.parse("");

    public interface VisualStateCallback {
        void onComplete(long j);
    }

    private WebViewCompat() {
    }

    public static void postVisualStateCallback(WebView webView, long j, final VisualStateCallback visualStateCallback) {
        WebViewFeatureInternal feature = WebViewFeatureInternal.getFeature("VISUAL_STATE_CALLBACK");
        if (feature.isSupportedByFramework()) {
            webView.postVisualStateCallback(j, new WebView.VisualStateCallback() { // from class: androidx.webkit.WebViewCompat.1
                @Override // android.webkit.WebView.VisualStateCallback
                public void onComplete(long j2) {
                    visualStateCallback.onComplete(j2);
                }
            });
        } else {
            if (feature.isSupportedByWebView()) {
                checkThread(webView);
                getProvider(webView).insertVisualStateCallback(j, visualStateCallback);
                return;
            }
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
    }

    public static void startSafeBrowsing(Context context, ValueCallback<Boolean> valueCallback) {
        WebViewFeatureInternal feature = WebViewFeatureInternal.getFeature("START_SAFE_BROWSING");
        if (feature.isSupportedByFramework()) {
            WebView.startSafeBrowsing(context, valueCallback);
        } else {
            if (feature.isSupportedByWebView()) {
                getFactory().getStatics().initSafeBrowsing(context, valueCallback);
                return;
            }
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
    }

    public static void setSafeBrowsingWhitelist(List<String> list, ValueCallback<Boolean> valueCallback) {
        WebViewFeatureInternal feature = WebViewFeatureInternal.getFeature("SAFE_BROWSING_WHITELIST");
        if (feature.isSupportedByFramework()) {
            WebView.setSafeBrowsingWhitelist(list, valueCallback);
        } else {
            if (feature.isSupportedByWebView()) {
                getFactory().getStatics().setSafeBrowsingWhitelist(list, valueCallback);
                return;
            }
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
    }

    public static Uri getSafeBrowsingPrivacyPolicyUrl() {
        WebViewFeatureInternal feature = WebViewFeatureInternal.getFeature("SAFE_BROWSING_PRIVACY_POLICY_URL");
        if (feature.isSupportedByFramework()) {
            return WebView.getSafeBrowsingPrivacyPolicyUrl();
        }
        if (feature.isSupportedByWebView()) {
            return getFactory().getStatics().getSafeBrowsingPrivacyPolicyUrl();
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static PackageInfo getCurrentWebViewPackage(Context context) {
        return WebView.getCurrentWebViewPackage();
    }

    private static PackageInfo getLoadedWebViewPackageInfo() throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, InvocationTargetException {
        return (PackageInfo) Class.forName("android.webkit.WebViewFactory").getMethod("getLoadedPackageInfo", new Class[0]).invoke(null, new Object[0]);
    }

    private static PackageInfo getNotYetLoadedWebViewPackageInfo(Context context) {
        try {
            String str = (String) Class.forName("android.webkit.WebViewUpdateService").getMethod("getCurrentWebViewPackageName", new Class[0]).invoke(null, new Object[0]);
            if (str == null) {
                return null;
            }
            return context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException | ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return null;
        }
    }

    private static WebViewProviderAdapter getProvider(WebView webView) {
        return new WebViewProviderAdapter(createProvider(webView));
    }

    public static WebMessagePortCompat[] createWebMessageChannel(WebView webView) {
        WebViewFeatureInternal feature = WebViewFeatureInternal.getFeature("CREATE_WEB_MESSAGE_CHANNEL");
        if (feature.isSupportedByFramework()) {
            return WebMessagePortImpl.portsToCompat(webView.createWebMessageChannel());
        }
        if (feature.isSupportedByWebView()) {
            return getProvider(webView).createWebMessageChannel();
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static void postWebMessage(WebView webView, WebMessageCompat webMessageCompat, Uri uri) {
        if (WILDCARD_URI.equals(uri)) {
            uri = EMPTY_URI;
        }
        WebViewFeatureInternal feature = WebViewFeatureInternal.getFeature("POST_WEB_MESSAGE");
        if (feature.isSupportedByFramework()) {
            webView.postWebMessage(WebMessagePortImpl.compatToFrameworkMessage(webMessageCompat), uri);
        } else {
            if (feature.isSupportedByWebView()) {
                getProvider(webView).postWebMessage(webMessageCompat, uri);
                return;
            }
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
    }

    public static WebViewClient getWebViewClient(WebView webView) {
        WebViewFeatureInternal feature = WebViewFeatureInternal.getFeature("GET_WEB_VIEW_CLIENT");
        if (feature.isSupportedByFramework()) {
            return webView.getWebViewClient();
        }
        if (feature.isSupportedByWebView()) {
            return getProvider(webView).getWebViewClient();
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static WebChromeClient getWebChromeClient(WebView webView) {
        WebViewFeatureInternal feature = WebViewFeatureInternal.getFeature("GET_WEB_CHROME_CLIENT");
        if (feature.isSupportedByFramework()) {
            return webView.getWebChromeClient();
        }
        if (feature.isSupportedByWebView()) {
            return getProvider(webView).getWebChromeClient();
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static WebViewRenderProcess getWebViewRenderProcess(WebView webView) {
        if (WebViewFeatureInternal.getFeature("GET_WEB_VIEW_RENDERER").isSupportedByWebView()) {
            return getProvider(webView).getWebViewRenderProcess();
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static void setWebViewRenderProcessClient(WebView webView, Executor executor, WebViewRenderProcessClient webViewRenderProcessClient) {
        if (WebViewFeatureInternal.getFeature("WEB_VIEW_RENDERER_CLIENT_BASIC_USAGE").isSupportedByWebView()) {
            getProvider(webView).setWebViewRenderProcessClient(executor, webViewRenderProcessClient);
            return;
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static void setWebViewRenderProcessClient(WebView webView, WebViewRenderProcessClient webViewRenderProcessClient) {
        if (WebViewFeatureInternal.getFeature("WEB_VIEW_RENDERER_CLIENT_BASIC_USAGE").isSupportedByWebView()) {
            getProvider(webView).setWebViewRenderProcessClient(null, webViewRenderProcessClient);
            return;
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static WebViewRenderProcessClient getWebViewRenderProcessClient(WebView webView) {
        if (WebViewFeatureInternal.getFeature("WEB_VIEW_RENDERER_CLIENT_BASIC_USAGE").isSupportedByWebView()) {
            return getProvider(webView).getWebViewRenderProcessClient();
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static boolean isMultiProcessEnabled() {
        if (WebViewFeatureInternal.getFeature("MULTI_PROCESS_QUERY").isSupportedByWebView()) {
            return getFactory().getStatics().isMultiProcessEnabled();
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    private static WebViewProviderFactory getFactory() {
        return WebViewGlueCommunicator.getFactory();
    }

    private static WebViewProviderBoundaryInterface createProvider(WebView webView) {
        return getFactory().createWebView(webView);
    }

    private static void checkThread(WebView webView) {
        if (webView.getWebViewLooper() != Looper.myLooper()) {
            throw new RuntimeException("A WebView method was called on thread '" + Thread.currentThread().getName() + "'. All WebView methods must be called on the same thread. (Expected Looper " + webView.getWebViewLooper() + " called on " + Looper.myLooper() + ", FYI main Looper is " + Looper.getMainLooper() + ")");
        }
    }
}
