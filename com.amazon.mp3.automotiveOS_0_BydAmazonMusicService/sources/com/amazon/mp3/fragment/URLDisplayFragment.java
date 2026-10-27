package com.amazon.mp3.fragment;

import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.WebViewUtil;
import com.amazon.mp3.web.webtarget.WebTarget;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: URLDisplayFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0007¢\u0006\u0004\b\r\u0010\u000eJ\u0006\u0010\u0003\u001a\u00020\u0002J\b\u0010\u0004\u001a\u00020\u0002H\u0016J\n\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0014R\"\u0010\t\u001a\n \b*\u0004\u0018\u00010\u00070\u00078\u0014X\u0094\u0004¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/fragment/URLDisplayFragment;", "Lcom/amazon/mp3/fragment/WebViewFragment;", "", "canGoBack", "onBackPressed", "Landroid/webkit/WebViewClient;", "createWebViewClient", "", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "getTAG", "()Ljava/lang/String;", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class URLDisplayFragment extends WebViewFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String TAG = URLDisplayFragment.class.getSimpleName();

    @Override // com.amazon.mp3.fragment.WebViewFragment
    protected String getTAG() {
        return this.TAG;
    }

    /* JADX INFO: compiled from: URLDisplayFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0017¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/fragment/URLDisplayFragment$Companion;", "", "()V", "newInstance", "Lcom/amazon/mp3/fragment/URLDisplayFragment;", "webTarget", "Lcom/amazon/mp3/web/webtarget/WebTarget;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public URLDisplayFragment newInstance(WebTarget webTarget) {
            Intrinsics.checkNotNullParameter(webTarget, "webTarget");
            URLDisplayFragment uRLDisplayFragment = new URLDisplayFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("com.amazon.mp3.extra.WebTargetKey.EXTRA_WEB_TARGET", webTarget);
            uRLDisplayFragment.setArguments(bundle);
            return uRLDisplayFragment;
        }
    }

    public final boolean canGoBack() {
        return this.mWebView.canGoBack();
    }

    @Override // com.amazon.mp3.fragment.BaseFragment
    public boolean onBackPressed() {
        if (!this.mWebView.canGoBack()) {
            return true;
        }
        this.mWebView.goBack();
        return false;
    }

    @Override // com.amazon.mp3.fragment.BaseWebFragment
    protected WebViewClient createWebViewClient() {
        return new WebViewClient() { // from class: com.amazon.mp3.fragment.URLDisplayFragment.createWebViewClient.1
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(url, "url");
                return false;
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView view, String url) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(url, "url");
                super.onPageFinished(view, url);
                URLDisplayFragment.this.mProgressBar.setVisibility(8);
                URLDisplayFragment.this.mWebView.setVisibility(0);
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(handler, "handler");
                Intrinsics.checkNotNullParameter(error, "error");
                handler.cancel();
                Log.warning(URLDisplayFragment.this.getTAG(), Intrinsics.stringPlus("SSL Error: ", error));
                WebViewUtil.handleSslError(view.getContext(), view.getSettings());
            }
        };
    }
}
