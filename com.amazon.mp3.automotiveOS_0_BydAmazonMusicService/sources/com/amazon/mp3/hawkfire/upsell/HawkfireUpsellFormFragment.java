package com.amazon.mp3.hawkfire.upsell;

import android.content.Context;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.amazon.mp3.elf.ExplicitLanguageManager;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.fragment.AuthenticatedWebViewFragment;
import com.amazon.mp3.fragment.BaseWebFragment;
import com.amazon.mp3.hawkfire.upsell.UnlimitedUpsellActivity;
import com.amazon.mp3.inapplandingpage.InAppLandingPageDeeplinkHandler;
import com.amazon.mp3.inapplandingpage.InAppLandingPageDeeplinkHandlerException;
import com.amazon.mp3.store.html5.bridge.JavascriptBridge;
import com.amazon.mp3.store.html5.bridge.Message;
import com.amazon.mp3.store.html5.map.MAPCookiesCallback;
import com.amazon.mp3.store.html5.map.MAPCookiesUtil;
import com.amazon.mp3.upsellweb.OfferPageRedirectPath;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.WebViewUtil;
import com.amazon.mp3.video.stories.VideoType;
import com.amazon.mp3.video.stories.metrics.VideoMetricsEventsFactory;
import com.amazon.mp3.video.stories.metrics.VideoMetricsUtil;
import com.amazon.mp3.web.BasicWebEvent;
import com.amazon.mp3.web.WebEvent;
import com.amazon.mp3.web.webtarget.WebTarget;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.PlaybackEntityType;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.PlaybackSourceType;
import com.amazon.music.metrics.mts.event.types.SelectionSourceType;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.share.InviteShareProvider;
import com.amazon.music.validator.ValidatorRegistry;
import java.util.UUID;
import java.util.concurrent.Callable;
import org.json.JSONException;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/* JADX INFO: loaded from: classes3.dex */
public class HawkfireUpsellFormFragment extends AuthenticatedWebViewFragment {
    private WebChromeClient.CustomViewCallback customVideoViewCallback;
    private View mVideoCustomView;
    private Subscription purchaseCheckSubscription;
    private static final String TAG = HawkfireUpsellFormFragment.class.getSimpleName();
    private static final BasicWebEvent mCustomerUpgradedWebEvent = new BasicWebEvent(UnlimitedUpsellActivity.UpsellWebEventEnum.CUSTOMER_UPGRADED.name());
    private static final BasicWebEvent mCustomerDeferredWebEvent = new BasicWebEvent(UnlimitedUpsellActivity.UpsellWebEventEnum.CUSTOMER_DEFERRED.name());
    private static final BasicWebEvent mListenNowWebEvent = new BasicWebEvent(UnlimitedUpsellActivity.UpsellWebEventEnum.LISTEN_NOW.name());

    public static HawkfireUpsellFormFragment newInstance(final WebTarget webTarget) {
        HawkfireUpsellFormFragment hawkfireUpsellFormFragment = new HawkfireUpsellFormFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("com.amazon.mp3.extra.WebTargetKey.EXTRA_WEB_TARGET", webTarget);
        hawkfireUpsellFormFragment.setArguments(bundle);
        return hawkfireUpsellFormFragment;
    }

    @Override // com.amazon.mp3.fragment.AuthenticatedWebViewFragment, com.amazon.mp3.fragment.BaseWebFragment
    protected void initWebView() {
        super.initWebView();
        this.mWebView.setWebChromeClient(new WebChromeClient() { // from class: com.amazon.mp3.hawkfire.upsell.HawkfireUpsellFormFragment.1
            @Override // android.webkit.WebChromeClient
            public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback) {
                if (((BaseWebFragment) HawkfireUpsellFormFragment.this).mVideoFrameContainer == null) {
                    HawkfireUpsellFormFragment.this.mVideoCustomView = view;
                    ((BaseWebFragment) HawkfireUpsellFormFragment.this).mWebView.setVisibility(8);
                    ((BaseWebFragment) HawkfireUpsellFormFragment.this).mVideoFrameContainer.setVisibility(0);
                    ((BaseWebFragment) HawkfireUpsellFormFragment.this).mVideoFrameContainer.addView(view);
                    HawkfireUpsellFormFragment.this.customVideoViewCallback = callback;
                    return;
                }
                callback.onCustomViewHidden();
            }

            @Override // android.webkit.WebChromeClient
            public void onHideCustomView() {
                super.onHideCustomView();
                if (HawkfireUpsellFormFragment.this.mVideoCustomView == null) {
                    return;
                }
                ((BaseWebFragment) HawkfireUpsellFormFragment.this).mWebView.setVisibility(0);
                ((BaseWebFragment) HawkfireUpsellFormFragment.this).mVideoFrameContainer.setVisibility(8);
                HawkfireUpsellFormFragment.this.mVideoCustomView.setVisibility(8);
                ((BaseWebFragment) HawkfireUpsellFormFragment.this).mVideoFrameContainer.removeView(HawkfireUpsellFormFragment.this.mVideoCustomView);
                HawkfireUpsellFormFragment.this.customVideoViewCallback.onCustomViewHidden();
                HawkfireUpsellFormFragment.this.mVideoCustomView = null;
            }
        });
        this.mWebView.addJavascriptInterface(new ShareInviteUrl(), "shareUrl");
        this.mWebView.addJavascriptInterface(new UiClick(), "uiClick");
        this.mWebView.addJavascriptInterface(new PlaybackTerminated(), "playbackTerminated");
    }

    @Override // com.amazon.mp3.fragment.AuthenticatedWebViewFragment
    protected void onCookiesError() {
        this.mWebEventPublisher.onError(new Exception("onCookiesError in HawkfireUpsellFormFragment"));
    }

    @Override // com.amazon.mp3.fragment.BaseWebFragment
    protected WebViewClient createWebViewClient() {
        return new WebViewClient() { // from class: com.amazon.mp3.hawkfire.upsell.HawkfireUpsellFormFragment.2
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(final WebView view, final String url) {
                if (InAppLandingPageDeeplinkHandler.isInAppUrl(url)) {
                    return HawkfireUpsellFormFragment.this.handleInAppUrl(url);
                }
                if (url.contains(OfferPageRedirectPath.SUCCESS.toString()) && !url.contains(OfferPageRedirectPath.SUCCESS_LISTEN.toString())) {
                    ((BaseWebFragment) HawkfireUpsellFormFragment.this).mWebEventPublisher.onNext(HawkfireUpsellFormFragment.mCustomerUpgradedWebEvent);
                    return true;
                }
                if (url.contains(OfferPageRedirectPath.NOT_NOW.toString())) {
                    ((BaseWebFragment) HawkfireUpsellFormFragment.this).mWebEventPublisher.onNext(HawkfireUpsellFormFragment.mCustomerDeferredWebEvent);
                    return true;
                }
                return HawkfireUpsellFormFragment.this.urlDomainChanged(url);
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                ((BaseWebFragment) HawkfireUpsellFormFragment.this).mProgressBar.setVisibility(8);
                ((BaseWebFragment) HawkfireUpsellFormFragment.this).mWebView.setVisibility(0);
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.cancel();
                Log.warning(HawkfireUpsellFormFragment.TAG, "SSL Error: " + error);
                WebViewUtil.handleSslError(view.getContext(), view.getSettings());
            }
        };
    }

    @Override // com.amazon.mp3.fragment.BaseWebFragment
    protected JavascriptBridge createJavascriptBridge() {
        return new JavascriptBridge("DMSubsJSBridge");
    }

    @Override // com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MAPCookiesUtil.fetchCookies(new CookiesCallback(getApplication().getApplicationContext()), true);
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        Subscription subscription = this.purchaseCheckSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean handleInAppUrl(final String url) {
        try {
            InAppLandingPageDeeplinkHandler.handleInAppUrl(url);
            return true;
        } catch (InAppLandingPageDeeplinkHandlerException unused) {
            Log.error(TAG, "Error occurred while handling in-app URL");
            return false;
        }
    }

    private class CookiesCallback extends MAPCookiesCallback {
        CookiesCallback(final Context context) {
            super(context);
        }

        @Override // com.amazon.mp3.store.html5.map.MAPCookiesCallback, com.amazon.identity.auth.device.api.Callback
        public void onError(Bundle errorBundle) {
            super.onError(errorBundle);
            ((BaseWebFragment) HawkfireUpsellFormFragment.this).mWebEventPublisher.onError(new Exception("invokeGeneralErrorCallback in " + HawkfireUpsellFormFragment.TAG + " called"));
        }

        @Override // com.amazon.mp3.store.html5.map.MAPCookiesCallback, com.amazon.identity.auth.device.api.Callback
        public void onSuccess(Bundle successBundle) {
            super.onSuccess(successBundle);
            HawkfireUpsellFormFragment.this.executePurchaseCheckAndLoadWebView();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executePurchaseCheckAndLoadWebView() {
        this.purchaseCheckSubscription = Observable.fromCallable(new Callable() { // from class: com.amazon.mp3.hawkfire.upsell.HawkfireUpsellFormFragment$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return HawkfireUpsellFormFragment.lambda$executePurchaseCheckAndLoadWebView$0();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.hawkfire.upsell.HawkfireUpsellFormFragment$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$executePurchaseCheckAndLoadWebView$1((Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$executePurchaseCheckAndLoadWebView$0() throws Exception {
        return Boolean.valueOf(ValidatorRegistry.getInstance().getValidator("Purchase").isAllowed(true));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$executePurchaseCheckAndLoadWebView$1(Boolean bool) {
        if (bool.booleanValue() || getActivity() == null) {
            return;
        }
        getActivity().finish();
    }

    @Override // com.amazon.mp3.fragment.BaseWebFragment
    protected void handleMessage(final Message message) {
        if (message.getType() != Message.Type.Broadcast) {
            return;
        }
        try {
            int i = AnonymousClass3.$SwitchMap$com$amazon$mp3$upsellweb$OfferPageRedirectPath[OfferPageRedirectPath.fromString(message.getOptions().getString("route")).ordinal()];
            if (i == 1) {
                this.mWebEventPublisher.onNext(mCustomerUpgradedWebEvent);
            } else if (i == 2) {
                this.mWebEventPublisher.onNext(mListenNowWebEvent);
            } else if (i == 3 || i == 4) {
                this.mWebEventPublisher.onNext(mCustomerDeferredWebEvent);
            } else {
                Log.debug(TAG, "Unhandled broadcast from Hawkfire upsell");
            }
        } catch (JSONException e) {
            Log.warning(TAG, "Could not get route from message received from Hawkfire upsell", e);
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.hawkfire.upsell.HawkfireUpsellFormFragment$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$upsellweb$OfferPageRedirectPath;

        static {
            int[] iArr = new int[OfferPageRedirectPath.values().length];
            $SwitchMap$com$amazon$mp3$upsellweb$OfferPageRedirectPath = iArr;
            try {
                iArr[OfferPageRedirectPath.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$upsellweb$OfferPageRedirectPath[OfferPageRedirectPath.SUCCESS_LISTEN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$upsellweb$OfferPageRedirectPath[OfferPageRedirectPath.NOT_NOW.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$upsellweb$OfferPageRedirectPath[OfferPageRedirectPath.HOME.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$mp3$upsellweb$OfferPageRedirectPath[OfferPageRedirectPath.UNKNOWN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    @Override // com.amazon.mp3.fragment.BaseWebFragment
    public boolean handleBackPress() {
        if (!this.mWebView.canGoBack()) {
            return false;
        }
        PublishSubject<WebEvent> publishSubject = this.mWebEventPublisher;
        if (publishSubject != null) {
            publishSubject.onNext(mCustomerDeferredWebEvent);
        }
        this.mWebView.goBack();
        return true;
    }

    private class ShareInviteUrl {
        private ShareInviteUrl() {
        }

        @JavascriptInterface
        public void onClick(final String url, final String message, final String title) {
            new InviteShareProvider(HawkfireUpsellFormFragment.this.getContext(), EndpointsProvider.get().getMusicDomain(), title, message, url, null).startShare();
        }
    }

    private class UiClick {
        private UiClick() {
        }

        @JavascriptInterface
        public void onClick(final String pageType, final String actionType) {
            UiClickEvent uiClickEventBuild = UiClickEvent.builder(actionType).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis()).withPageSubType(pageType).withCustomPageType("InAppCampaign").build();
            if (uiClickEventBuild != null) {
                MetricsHolder.getManager().handleEvent(uiClickEventBuild);
            }
        }
    }

    private class PlaybackTerminated {
        private PlaybackTerminated() {
        }

        @JavascriptInterface
        public void onPlaybackTerminated(final String videoIdentifier, final long initialPlaybackDelayMilliseconds, final long playbackDurationMilliseconds, final long durationSeconds, final long trackLengthMilliseconds, final String terminationReason, final long trackProgressMilliseconds, String errorMessage) {
            String str = errorMessage != null ? errorMessage : "";
            Context context = HawkfireUpsellFormFragment.this.getContext();
            VideoMetricsEventsFactory videoMetricsEventsFactory = VideoMetricsEventsFactory.INSTANCE;
            UUID uuidRandomUUID = UUID.randomUUID();
            PlaybackPageType playbackPageType = PlaybackPageType.MOBILE_WEB;
            TerminationReason terminationReason2 = HawkfireUpsellFormFragment.this.getTerminationReason(terminationReason);
            String string = VideoType.VIDEO_STORY.toString();
            PlaybackSourceType playbackSourceType = PlaybackSourceType.STREAM;
            VideoMetricsUtil videoMetricsUtil = VideoMetricsUtil.INSTANCE;
            MTSEvent mTSEventCreateVideoPlaybackTerminatedEvent = videoMetricsEventsFactory.createVideoPlaybackTerminatedEvent("playbackTerminated", initialPlaybackDelayMilliseconds, videoIdentifier, uuidRandomUUID, playbackPageType, trackLengthMilliseconds, playbackDurationMilliseconds, durationSeconds, trackProgressMilliseconds, terminationReason2, str, "HTML5Player", string, playbackSourceType, videoMetricsUtil.getResourceType(), PlaybackEntityType.VIDEO, SelectionSourceType.SONGS, videoMetricsUtil.getContentSubscriptionMode(), videoMetricsUtil.getNetworkProvider(context).getMetricsName(), false, false, videoIdentifier, ExplicitLanguageManager.isExplicitFilterEnabled());
            if (mTSEventCreateVideoPlaybackTerminatedEvent != null) {
                MetricsHolder.getManager().handleEvent(mTSEventCreateVideoPlaybackTerminatedEvent);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public TerminationReason getTerminationReason(String reason) {
        for (TerminationReason terminationReason : TerminationReason.values()) {
            if (terminationReason.name().equals(reason)) {
                return terminationReason;
            }
        }
        return TerminationReason.ACTION_BUTTON_CLICKED;
    }
}
