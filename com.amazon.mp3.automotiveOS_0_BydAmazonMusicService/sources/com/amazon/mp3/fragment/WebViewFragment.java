package com.amazon.mp3.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.amazon.mp3.store.html5.bridge.JavascriptBridge;
import com.amazon.mp3.store.html5.bridge.Message;
import com.amazon.mp3.store.html5.map.ApplicationContextCookie;
import com.amazon.mp3.store.html5.map.MAPCookiesCallback;
import com.amazon.mp3.store.html5.map.MAPCookiesUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.web.webtarget.WebTarget;
import com.google.common.net.InternetDomainName;
import java.net.URL;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WebViewFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001:\u0001\u0019B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\b\u001a\u0004\u0018\u00010\tH\u0014J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000b\u001a\u00020\u0004H\u0002J\u0012\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014J\b\u0010\u0010\u001a\u00020\rH\u0014J\u0010\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0004H\u0002J\b\u0010\u0013\u001a\u00020\rH\u0002J\b\u0010\u0014\u001a\u00020\rH\u0004J$\u0010\u0015\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\u00042\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\r0\u0017H\u0002R\u001c\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u001a"}, d2 = {"Lcom/amazon/mp3/fragment/WebViewFragment;", "Lcom/amazon/mp3/fragment/BaseWebFragment;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "createJavascriptBridge", "Lcom/amazon/mp3/store/html5/bridge/JavascriptBridge;", "extractTopPrivateDomainFromURL", "webViewURL", "handleMessage", "", "message", "Lcom/amazon/mp3/store/html5/bridge/Message;", "initWebView", "injectCookiesAndLoadWebViewUrl", "urlString", "loadWebView", "onCookiesError", "setContextCookiesForWebViewURL", "callback", "Lkotlin/Function1;", "", "CookiesCallback", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class WebViewFragment extends BaseWebFragment {
    private final String TAG = WebViewFragment.class.getSimpleName();

    @Override // com.amazon.mp3.fragment.BaseWebFragment
    protected void handleMessage(Message message) {
    }

    protected String getTAG() {
        return this.TAG;
    }

    @Override // com.amazon.mp3.fragment.BaseWebFragment
    protected void initWebView() {
        super.initWebView();
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.setOverScrollMode(2);
        WebView.setWebContentsDebuggingEnabled(false);
        loadWebView();
    }

    private final void loadWebView() {
        WebTarget webTarget = (WebTarget) requireArguments().getSerializable("com.amazon.mp3.extra.WebTargetKey.EXTRA_WEB_TARGET");
        if (webTarget == null) {
            this.mWebEventPublisher.onError(new Exception("Cannot call webView loader fragment without a webTarget."));
            return;
        }
        String url = webTarget.getUrl();
        Intrinsics.checkNotNullExpressionValue(url, "webTarget.url");
        injectCookiesAndLoadWebViewUrl(url);
    }

    private final void injectCookiesAndLoadWebViewUrl(final String urlString) {
        MAPCookiesUtil.fetchCookies(new CookiesCallback(this, getApplication().getApplicationContext(), urlString, new Function1<Boolean, Unit>() { // from class: com.amazon.mp3.fragment.WebViewFragment$injectCookiesAndLoadWebViewUrl$cookiesCallback$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) {
                if (z) {
                    this.this$0.mWebView.loadUrl(urlString);
                } else {
                    this.this$0.mWebEventPublisher.onError(new Exception("Cannot call webView loader fragment without a webTarget."));
                }
            }
        }), true);
    }

    /* JADX INFO: compiled from: WebViewFragment.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B-\b\u0000\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\u0010\nJ\u0010\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\rH\u0016R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/fragment/WebViewFragment$CookiesCallback;", "Lcom/amazon/mp3/store/html5/map/MAPCookiesCallback;", "context", "Landroid/content/Context;", "urlString", "", "callback", "Lkotlin/Function1;", "", "", "(Lcom/amazon/mp3/fragment/WebViewFragment;Landroid/content/Context;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "onError", "errorBundle", "Landroid/os/Bundle;", "onSuccess", "successBundle", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class CookiesCallback extends MAPCookiesCallback {
        private final Function1<Boolean, Unit> callback;
        final /* synthetic */ WebViewFragment this$0;
        private final String urlString;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public CookiesCallback(WebViewFragment this$0, Context context, String urlString, Function1<? super Boolean, Unit> callback) {
            super(context);
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(urlString, "urlString");
            Intrinsics.checkNotNullParameter(callback, "callback");
            this.this$0 = this$0;
            this.urlString = urlString;
            this.callback = callback;
        }

        @Override // com.amazon.mp3.store.html5.map.MAPCookiesCallback, com.amazon.identity.auth.device.api.Callback
        public void onError(Bundle errorBundle) {
            Intrinsics.checkNotNullParameter(errorBundle, "errorBundle");
            super.onError(errorBundle);
            this.this$0.onCookiesError();
        }

        @Override // com.amazon.mp3.store.html5.map.MAPCookiesCallback, com.amazon.identity.auth.device.api.Callback
        public void onSuccess(Bundle successBundle) {
            Intrinsics.checkNotNullParameter(successBundle, "successBundle");
            super.onSuccess(successBundle);
            Log.warning(this.this$0.getTAG(), "Cookies injected succesfully");
            this.this$0.setContextCookiesForWebViewURL(this.urlString, this.callback);
        }
    }

    protected final void onCookiesError() {
        this.mWebEventPublisher.onError(new Exception("onCookiesError in CookieConsentFragment"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setContextCookiesForWebViewURL(String webViewURL, final Function1<? super Boolean, Unit> callback) {
        final String strExtractTopPrivateDomainFromURL = extractTopPrivateDomainFromURL(webViewURL);
        if (strExtractTopPrivateDomainFromURL != null) {
            final String cookieString = new ApplicationContextCookie().getCookieString();
            final CookieManager cookieManager = CookieManager.getInstance();
            Intrinsics.checkNotNullExpressionValue(cookieManager, "getInstance()");
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.mp3.fragment.WebViewFragment$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    WebViewFragment.m1075setContextCookiesForWebViewURL$lambda1(cookieManager, strExtractTopPrivateDomainFromURL, cookieString, callback);
                }
            });
            return;
        }
        this.mWebEventPublisher.onError(new Exception("Failed to fetch the WebView's domain for the given URL"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setContextCookiesForWebViewURL$lambda-1, reason: not valid java name */
    public static final void m1075setContextCookiesForWebViewURL$lambda1(CookieManager cookieManager, String str, String cookieString, final Function1 callback) {
        Intrinsics.checkNotNullParameter(cookieManager, "$cookieManager");
        Intrinsics.checkNotNullParameter(cookieString, "$cookieString");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        cookieManager.setCookie(str, cookieString, new ValueCallback() { // from class: com.amazon.mp3.fragment.WebViewFragment$$ExternalSyntheticLambda1
            @Override // android.webkit.ValueCallback
            public final void onReceiveValue(Object obj) {
                WebViewFragment.m1076setContextCookiesForWebViewURL$lambda1$lambda0(callback, (Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setContextCookiesForWebViewURL$lambda-1$lambda-0, reason: not valid java name */
    public static final void m1076setContextCookiesForWebViewURL$lambda1$lambda0(Function1 tmp0, Boolean bool) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(bool);
    }

    private final String extractTopPrivateDomainFromURL(String webViewURL) {
        try {
            String string = InternetDomainName.from(new URL(webViewURL).getHost()).topPrivateDomain().toString();
            Intrinsics.checkNotNullExpressionValue(string, "from(URL(webViewURL).hos…rivateDomain().toString()");
            return Intrinsics.stringPlus(".", string);
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // com.amazon.mp3.fragment.BaseWebFragment
    protected JavascriptBridge createJavascriptBridge() {
        return new JavascriptBridge("DMSubsJSBridge");
    }
}
