package com.amazon.music.inappmessaging.internal.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.CookieManager;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.Nullable;
import com.amazon.music.inappmessaging.external.IAMWebViewDeeplinkHandler;
import com.amazon.music.inappmessaging.external.IAMWebViewDeeplinkHandlerException;
import com.amazon.music.inappmessaging.external.InAppMessageBroadcasts;
import com.amazon.music.inappmessaging.external.events.EventType;
import com.amazon.music.inappmessaging.external.events.IAMEventSender;
import com.amazon.music.inappmessaging.internal.model.Splash;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Subscriber;

/* JADX INFO: loaded from: classes4.dex */
public class IamWebViewManager {
    private static final String ACCESS_COOKIE = "x-amz-access-token=%s;";
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) IamWebViewManager.class);
    private static IamWebViewManager iamWebViewManager;
    private final Context context;
    private final WebView webView;
    private Map<String, WebViewEntry> webViewMap = new HashMap();

    public interface DismissListener {
        void onDismiss(boolean shouldEmitDismissedEvent);
    }

    private final class WebViewEntry {
        private DismissListener dismissListener;
        private String errorMessage;
        private boolean isDismissed;
        private boolean isError;
        private boolean isFinishedLoading;
        private final List<Subscriber<? super WebView>> subscribers = new ArrayList();
        private WebView webView;

        public WebViewEntry(final WebView webView) {
            this.webView = webView;
        }
    }

    private final class IamWebViewClient extends WebViewClient {
        private final IAMWebViewDeeplinkHandler deeplinkHandler;
        private final String iamRef;

        public IamWebViewClient(IAMWebViewDeeplinkHandler deeplinkHandler, String iamRef) {
            this.deeplinkHandler = deeplinkHandler;
            this.iamRef = iamRef;
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(final WebView view, final String url) {
            WebViewEntry webViewEntry = (WebViewEntry) IamWebViewManager.this.webViewMap.get(url);
            if (webViewEntry != null) {
                webViewEntry.isFinishedLoading = true;
                IamWebViewManager.LOG.debug("Loading finished, notifying {} subscribers", Integer.valueOf(webViewEntry.subscribers.size()));
                for (Subscriber subscriber : webViewEntry.subscribers) {
                    if (webViewEntry.isError) {
                        subscriber.onError(new WebViewHttpException(webViewEntry.errorMessage));
                    } else {
                        subscriber.onNext(view);
                    }
                    subscriber.onCompleted();
                }
                webViewEntry.subscribers.clear();
            }
            super.onPageFinished(view, url);
        }

        @Override // android.webkit.WebViewClient
        @TargetApi(23)
        public void onReceivedHttpError(final WebView view, final WebResourceRequest request, final WebResourceResponse errorResponse) {
            super.onReceivedHttpError(view, request, errorResponse);
            WebViewEntry webViewEntry = (WebViewEntry) IamWebViewManager.this.webViewMap.get(view.getUrl());
            if (webViewEntry == null || view.getUrl() == null || request.getUrl() == null || !view.getUrl().equals(request.getUrl().toString())) {
                return;
            }
            IamWebViewManager.this.processError(view, webViewEntry, errorResponse.getStatusCode(), errorResponse.getReasonPhrase(), String.valueOf(request.getUrl()));
        }

        @Override // android.webkit.WebViewClient
        @TargetApi(23)
        public void onReceivedError(final WebView view, final WebResourceRequest request, final WebResourceError webResourceError) {
            super.onReceivedError(view, request, webResourceError);
            WebViewEntry webViewEntry = (WebViewEntry) IamWebViewManager.this.webViewMap.get(view.getUrl());
            if (webViewEntry == null || view.getUrl() == null || request.getUrl() == null || !view.getUrl().equals(request.getUrl().toString())) {
                return;
            }
            IamWebViewManager.this.processError(view, webViewEntry, webResourceError.getErrorCode(), String.valueOf(webResourceError.getDescription()), String.valueOf(request.getUrl()));
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(final WebView view, final int errorCode, final String description, final String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            WebViewEntry webViewEntry = (WebViewEntry) IamWebViewManager.this.webViewMap.get(view.getUrl());
            if (webViewEntry == null || view.getUrl() == null || !view.getUrl().equals(failingUrl)) {
                return;
            }
            IamWebViewManager.this.processError(view, webViewEntry, errorCode, description, failingUrl);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Uri uri = Uri.parse(url);
            String queryParameter = uri.getQueryParameter("deeplink");
            String queryParameter2 = uri.getQueryParameter("dismiss");
            String queryParameter3 = uri.getQueryParameter("clickId");
            if (queryParameter3 != null) {
                IAMEventSender.withType(EventType.CAMPAIGN_CLICK).withIamRef(this.iamRef).withClickId(queryParameter3).send();
            }
            if (!StringUtils.isEmpty(queryParameter)) {
                try {
                    this.deeplinkHandler.handle(Uri.parse(queryParameter));
                    if (queryParameter3 == null) {
                        return true;
                    }
                    dismissView(view, queryParameter3);
                    return true;
                } catch (IAMWebViewDeeplinkHandlerException e) {
                    IamWebViewManager.LOG.error("Could not handle deeplink due to an exception: ", (Throwable) e);
                    IAMEventSender.withType(EventType.ERROR_OTHER).withDescription("Could not handle deeplink " + queryParameter).send();
                    return false;
                }
            }
            if ("true".equals(queryParameter2)) {
                return dismissView(view, queryParameter3);
            }
            return false;
        }

        private boolean dismissView(final WebView view, final String clickId) {
            if (!clickId.equals("cta")) {
                IAMEventSender.withType(EventType.CAMPAIGN_DISMISSED).withIamRef(this.iamRef).withClickId(clickId).send();
            }
            IamWebViewManager.LOG.debug("Dismissing webview");
            WebViewEntry webViewEntry = (WebViewEntry) IamWebViewManager.this.webViewMap.get(view.getOriginalUrl());
            if (webViewEntry == null || webViewEntry.dismissListener == null) {
                return false;
            }
            webViewEntry.dismissListener.onDismiss(false);
            IamWebViewManager.this.context.sendBroadcast(new Intent(InAppMessageBroadcasts.SPLASH_DISMISSED));
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processError(final WebView view, final WebViewEntry entry, final int errorCode, final String description, final String failingUrl) {
        view.stopLoading();
        entry.isError = true;
        entry.errorMessage = "WR error: " + errorCode + " decr: " + description + " url: " + failingUrl;
    }

    private void clearWebViewContent(WebView view) {
        LOG.debug("clearWebViewContent()");
        view.loadUrl("about:blank");
        view.clearHistory();
    }

    public synchronized void onWebViewDismissed(final String url) {
        WebViewEntry webViewEntry = this.webViewMap.get(url);
        if (webViewEntry != null) {
            webViewEntry.isDismissed = true;
            if (webViewEntry.webView != null) {
                clearWebViewContent(webViewEntry.webView);
            }
            webViewEntry.webView = null;
            webViewEntry.dismissListener = null;
        }
    }

    public static synchronized IamWebViewManager get(final Context context) {
        if (iamWebViewManager == null) {
            iamWebViewManager = new IamWebViewManager(context);
        }
        return iamWebViewManager;
    }

    private IamWebViewManager(final Context context) {
        this.context = context;
        this.webView = new WebView(context);
    }

    public Observable<WebView> loadWebView(final Splash splash, final IAMWebViewDeeplinkHandler deeplinkHandler, final boolean isExplicitCampaign) {
        String str = splash.generatedUrl;
        LOG.debug("Splash page URL to render: " + str);
        setCookies(splash);
        final WebViewEntry webViewEntry = this.webViewMap.get(str);
        if (webViewEntry != null && (!webViewEntry.isDismissed || !isExplicitCampaign)) {
            if (webViewEntry.isDismissed) {
                return Observable.create(new Observable.OnSubscribe<WebView>() { // from class: com.amazon.music.inappmessaging.internal.view.IamWebViewManager.1
                    @Override // rx.functions.Action1
                    public void call(final Subscriber<? super WebView> subscriber) {
                        subscriber.onCompleted();
                    }
                });
            }
        } else {
            this.webView.getSettings().setJavaScriptEnabled(true);
            this.webView.setWebViewClient(new IamWebViewClient(deeplinkHandler, splash.iamRef));
            this.webView.setBackgroundColor(0);
            this.webView.loadUrl(str);
            webViewEntry = new WebViewEntry(this.webView);
            this.webViewMap.put(str, webViewEntry);
        }
        return Observable.create(new Observable.OnSubscribe<WebView>() { // from class: com.amazon.music.inappmessaging.internal.view.IamWebViewManager.2
            @Override // rx.functions.Action1
            public void call(final Subscriber<? super WebView> subscriber) {
                if (webViewEntry.isFinishedLoading) {
                    IamWebViewManager.LOG.debug("Loading finished already, notifying subscriber");
                    subscriber.onNext(webViewEntry.webView);
                    subscriber.onCompleted();
                } else {
                    IamWebViewManager.LOG.debug("Loading not finished, queuing subscriber");
                    webViewEntry.subscribers.add(subscriber);
                }
            }
        });
    }

    private void setCookies(final Splash splash) {
        CookieManager.getInstance().setCookie(splash.generatedUrl, String.format(ACCESS_COOKIE, splash.authTokenProvider));
    }

    @Nullable
    public WebView getWebView(final String url, final DismissListener dismissListener) {
        WebViewEntry webViewEntry = this.webViewMap.get(url);
        if (webViewEntry == null || webViewEntry.isDismissed) {
            return null;
        }
        webViewEntry.dismissListener = dismissListener;
        return webViewEntry.webView;
    }
}
