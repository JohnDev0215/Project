package com.amazon.mp3.fragment;

import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.amazon.mp3.activity.CookieConsentActivity;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.WebViewUtil;
import com.amazon.mp3.web.BasicWebEvent;
import com.amazon.mp3.web.webtarget.WebTarget;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: CookieConsentFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\n \b*\u0004\u0018\u00010\u00040\u0004X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/mp3/fragment/CookieConsentFragment;", "Lcom/amazon/mp3/fragment/WebViewFragment;", "()V", "CONSENT_ACCEPTED", "", "CONSENT_CUSTOMIZED", "CONSENT_DENIED", "TAG", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "mConsentAcceptedEvent", "Lcom/amazon/mp3/web/BasicWebEvent;", "mConsentCustomizedEvent", "mConsentDeniedEvent", "createWebViewClient", "Landroid/webkit/WebViewClient;", "onBackPressed", "", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CookieConsentFragment extends WebViewFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String TAG = CookieConsentFragment.class.getSimpleName();
    private final BasicWebEvent mConsentAcceptedEvent = new BasicWebEvent(CookieConsentActivity.CookieConsentWebEventEnum.CONSENT_ACCEPTED.name());
    private final BasicWebEvent mConsentDeniedEvent = new BasicWebEvent(CookieConsentActivity.CookieConsentWebEventEnum.CONSENT_DENIED.name());
    private final BasicWebEvent mConsentCustomizedEvent = new BasicWebEvent(CookieConsentActivity.CookieConsentWebEventEnum.CONSENT_CUSTOMIZED.name());
    private final String CONSENT_ACCEPTED = "Accepted";
    private final String CONSENT_DENIED = "Denied";
    private final String CONSENT_CUSTOMIZED = "Customize";

    @Override // com.amazon.mp3.fragment.WebViewFragment
    protected String getTAG() {
        return this.TAG;
    }

    /* JADX INFO: compiled from: CookieConsentFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0017¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/fragment/CookieConsentFragment$Companion;", "", "()V", "newInstance", "Lcom/amazon/mp3/fragment/CookieConsentFragment;", "webTarget", "Lcom/amazon/mp3/web/webtarget/WebTarget;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public CookieConsentFragment newInstance(WebTarget webTarget) {
            Intrinsics.checkNotNullParameter(webTarget, "webTarget");
            CookieConsentFragment cookieConsentFragment = new CookieConsentFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("com.amazon.mp3.extra.WebTargetKey.EXTRA_WEB_TARGET", webTarget);
            cookieConsentFragment.setArguments(bundle);
            return cookieConsentFragment;
        }
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
        return new WebViewClient() { // from class: com.amazon.mp3.fragment.CookieConsentFragment.createWebViewClient.1
            @Override // android.webkit.WebViewClient
            public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
                super.doUpdateVisitedHistory(view, url, isReload);
                if (!(url != null && StringsKt__StringsKt.contains$default((CharSequence) url, (CharSequence) CookieConsentFragment.this.CONSENT_ACCEPTED, false, 2, (Object) null))) {
                    if (!(url != null && StringsKt__StringsKt.contains$default((CharSequence) url, (CharSequence) CookieConsentFragment.this.CONSENT_DENIED, false, 2, (Object) null))) {
                        if (url != null && StringsKt__StringsKt.contains$default((CharSequence) url, (CharSequence) CookieConsentFragment.this.CONSENT_CUSTOMIZED, false, 2, (Object) null)) {
                            CookieConsentFragment cookieConsentFragment = CookieConsentFragment.this;
                            cookieConsentFragment.mWebEventPublisher.onNext(cookieConsentFragment.mConsentCustomizedEvent);
                            return;
                        }
                        return;
                    }
                    CookieConsentFragment cookieConsentFragment2 = CookieConsentFragment.this;
                    cookieConsentFragment2.mWebEventPublisher.onNext(cookieConsentFragment2.mConsentDeniedEvent);
                    return;
                }
                CookieConsentFragment cookieConsentFragment3 = CookieConsentFragment.this;
                cookieConsentFragment3.mWebEventPublisher.onNext(cookieConsentFragment3.mConsentAcceptedEvent);
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView view, String url) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(url, "url");
                super.onPageFinished(view, url);
                CookieConsentFragment.this.mProgressBar.setVisibility(8);
                CookieConsentFragment.this.mWebView.setVisibility(0);
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(handler, "handler");
                Intrinsics.checkNotNullParameter(error, "error");
                handler.cancel();
                Log.warning(CookieConsentFragment.this.getTAG(), Intrinsics.stringPlus("SSL Error: ", error));
                WebViewUtil.handleSslError(view.getContext(), view.getSettings());
            }
        };
    }
}
