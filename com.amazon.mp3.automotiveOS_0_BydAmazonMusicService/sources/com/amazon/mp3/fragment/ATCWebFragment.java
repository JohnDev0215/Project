package com.amazon.mp3.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.core.os.EnvironmentCompat;
import com.amazon.mp3.account.DeviceAttributes;
import com.amazon.mp3.account.SubscriptionType;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.activity.ATCWebActivity;
import com.amazon.mp3.ftu.FTUSettingsUtil;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.store.html5.bridge.JavascriptBridge;
import com.amazon.mp3.store.html5.bridge.Message;
import com.amazon.mp3.store.html5.map.MAPCookiesManager;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.util.WebViewUtil;
import com.amazon.mp3.web.WebEvent;
import com.amazon.mp3.web.webtarget.WebTarget;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.time.StopWatch;
import org.json.JSONException;
import org.json.JSONObject;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/* JADX INFO: loaded from: classes3.dex */
public class ATCWebFragment extends BaseWebFragment {
    private String refMarker;
    private StopWatch renderLatencyMetricsStopWatch;
    private StopWatch requestLatencyMetricsStopWatch;
    private CountDownTimer timeoutTimer;
    private static final String TAG = ATCWebFragment.class.getSimpleName();
    private static final WebEvent mCustomerCompletedWebEvent = new WebEvent() { // from class: com.amazon.mp3.fragment.ATCWebFragment.1
        @Override // com.amazon.mp3.web.WebEvent
        public String id() {
            return "CUSTOMER_COMPLETED_ATC";
        }
    };
    private static final WebEvent mCustomerCompletedATCStartProactivePlayback = new WebEvent() { // from class: com.amazon.mp3.fragment.ATCWebFragment.2
        @Override // com.amazon.mp3.web.WebEvent
        public String id() {
            return "CUSTOMER_COMPLETED_ATC_START_PROACTIVE_PLAYBACK";
        }
    };
    private static final WebEvent mLoadErrorWebEvent = new WebEvent() { // from class: com.amazon.mp3.fragment.ATCWebFragment.3
        @Override // com.amazon.mp3.web.WebEvent
        public String id() {
            return "ATC_LOAD_TIMED_OUT";
        }
    };
    private String token = "";
    private String customerFlow = EnvironmentCompat.MEDIA_UNKNOWN;

    public static ATCWebFragment newInstance(final WebTarget webTarget, final ATCWebActivity.CustomerFlow customerFlow, final String refMarker) {
        ATCWebFragment aTCWebFragment = new ATCWebFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("com.amazon.mp3.extra.WebTargetKey.EXTRA_WEB_TARGET", webTarget);
        bundle.putSerializable("com.amazon.mp3.activity.EXTRA_CUSTOMER_FLOW", customerFlow);
        bundle.putString("com.amazon.mp3.activity.EXTRA_REF_MARKER", refMarker);
        aTCWebFragment.setArguments(bundle);
        return aTCWebFragment;
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.timeoutTimer == null) {
            TimeUnit timeUnit = TimeUnit.SECONDS;
            CountDownTimer countDownTimer = new CountDownTimer(timeUnit.toMillis(10L), timeUnit.toMillis(10L)) { // from class: com.amazon.mp3.fragment.ATCWebFragment.4
                @Override // android.os.CountDownTimer
                public void onTick(final long l) {
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    MetricsLogger.sendEvent(FlexEvent.builder("atcLoadTimedOut").build());
                    Log.warning(ATCWebFragment.TAG, "Timed out while trying to load Artist Taste Collection page");
                    ATCWebFragment.this.mWebEventPublisher.onNext(ATCWebFragment.mLoadErrorWebEvent);
                }
            };
            this.timeoutTimer = countDownTimer;
            countDownTimer.start();
        }
        if (this.requestLatencyMetricsStopWatch == null) {
            StopWatch stopWatch = new StopWatch();
            this.requestLatencyMetricsStopWatch = stopWatch;
            stopWatch.start();
        }
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        CountDownTimer countDownTimer = this.timeoutTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        super.onDestroy();
    }

    @Override // com.amazon.mp3.fragment.BaseFragment
    public boolean onBackPressed() {
        this.mJavascriptBridge.broadcast("atc_back");
        return true;
    }

    @Override // com.amazon.mp3.fragment.BaseWebFragment
    protected void initWebView() {
        super.initWebView();
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.setOverScrollMode(2);
        this.mWebView.setWebChromeClient(new WebChromeClient() { // from class: com.amazon.mp3.fragment.ATCWebFragment.5
            @Override // android.webkit.WebChromeClient
            public void onProgressChanged(WebView view, int newProgress) {
                ATCWebFragment.this.onWebChromeClientProgressChanged(view, newProgress);
            }
        });
        WebView.setWebContentsDebuggingEnabled(false);
        generateTokenObservable().subscribe();
        loadWebView();
    }

    private void loadWebView() {
        WebTarget webTarget = (WebTarget) getArguments().getSerializable("com.amazon.mp3.extra.WebTargetKey.EXTRA_WEB_TARGET");
        if (webTarget == null) {
            this.mWebEventPublisher.onError(new Exception("Cannot call webview loader fragment without a web target."));
        }
        this.mWebView.loadUrl(webTarget.getUrl());
    }

    @Override // com.amazon.mp3.fragment.BaseWebFragment, androidx.fragment.app.Fragment
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        if (getArguments() != null) {
            ATCWebActivity.CustomerFlow customerFlow = (ATCWebActivity.CustomerFlow) getArguments().get("com.amazon.mp3.activity.EXTRA_CUSTOMER_FLOW");
            if (customerFlow != null) {
                this.customerFlow = customerFlow.toString();
            }
            String string = getArguments().getString("com.amazon.mp3.activity.EXTRA_REF_MARKER");
            if (string != null) {
                this.refMarker = string;
            }
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override // com.amazon.mp3.fragment.BaseWebFragment
    protected WebViewClient createWebViewClient() {
        return new WebViewClient() { // from class: com.amazon.mp3.fragment.ATCWebFragment.6
            @Override // android.webkit.WebViewClient
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (ATCWebFragment.this.requestLatencyMetricsStopWatch != null) {
                    ATCWebFragment.this.requestLatencyMetricsStopWatch.stop();
                    MetricsLogger.sendEvent(FlexEvent.builder("atcRequestLatency").withFlexNum1(Float.valueOf(ATCWebFragment.this.requestLatencyMetricsStopWatch.getTime())).build());
                }
                if (ATCWebFragment.this.renderLatencyMetricsStopWatch == null) {
                    ATCWebFragment.this.renderLatencyMetricsStopWatch = new StopWatch();
                    ATCWebFragment.this.renderLatencyMetricsStopWatch.start();
                }
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (!ATCWebFragment.this.mWebView.hasFocus()) {
                    ATCWebFragment.this.mWebView.requestFocus(130);
                }
                if (ATCWebFragment.this.renderLatencyMetricsStopWatch == null || !ATCWebFragment.this.renderLatencyMetricsStopWatch.isStarted()) {
                    return;
                }
                ATCWebFragment.this.renderLatencyMetricsStopWatch.stop();
                MetricsLogger.sendEvent(FlexEvent.builder("atcRenderLatency").withFlexNum1(Float.valueOf(ATCWebFragment.this.renderLatencyMetricsStopWatch.getTime())).build());
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.cancel();
                Log.warning(ATCWebFragment.TAG, "SSL Error: " + error);
                WebViewUtil.handleSslError(view.getContext(), view.getSettings());
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                ATCWebFragment.this.mWebEventPublisher.onNext(ATCWebFragment.mLoadErrorWebEvent);
            }
        };
    }

    protected void onWebChromeClientProgressChanged(WebView view, int newProgress) {
        if (newProgress > 70) {
            CountDownTimer countDownTimer = this.timeoutTimer;
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
            this.mProgressBar.setVisibility(8);
            this.mWebView.setVisibility(0);
        }
    }

    @Override // com.amazon.mp3.fragment.BaseWebFragment
    protected JavascriptBridge createJavascriptBridge() {
        return new JavascriptBridge("ATCClient");
    }

    @Override // com.amazon.mp3.fragment.BaseWebFragment
    protected void handleMessage(final Message message) {
        if (message.getName().equals("atc_completion_message")) {
            try {
                if (message.getOptions().getString("atcSkipped").equals("true")) {
                    FTUSettingsUtil.setCustomerSkippedATC(getContext(), true);
                }
            } catch (Exception unused) {
                Log.error(TAG, "Error retrieving options");
            }
            this.mWebEventPublisher.onNext(mCustomerCompletedWebEvent);
            return;
        }
        if (message.getName().equals("atc_start_proactive_playback")) {
            this.mWebEventPublisher.onNext(mCustomerCompletedATCStartProactivePlayback);
        } else if (message.getName().equals("cookies_requested")) {
            sendAuthBroadcast();
        } else if (message.getName().equals("cookie_refresh_request")) {
            generateTokenObservable().doOnNext(new Action1<String>() { // from class: com.amazon.mp3.fragment.ATCWebFragment.7
                @Override // rx.functions.Action1
                public void call(String s) {
                    ATCWebFragment.this.sendAuthBroadcast();
                }
            }).subscribe();
        }
    }

    private Observable<String> generateTokenObservable() {
        return Observable.fromCallable(new Callable<String>() { // from class: com.amazon.mp3.fragment.ATCWebFragment.8
            @Override // java.util.concurrent.Callable
            public String call() throws Exception {
                try {
                    ATCWebFragment.this.token = MAPCookiesManager.getCookiesManager().getToken();
                    ATCWebFragment.this.sendAuthBroadcast();
                } catch (Exception unused) {
                    ATCWebFragment.this.mWebEventPublisher.onError(new Exception("Error retrieving token from MAPCookiesManager"));
                }
                return ATCWebFragment.this.token;
            }
        }).cache().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAuthBroadcast() {
        ThreadUtils.callOnMainThread(new Runnable() { // from class: com.amazon.mp3.fragment.ATCWebFragment.9
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ATCWebFragment aTCWebFragment = ATCWebFragment.this;
                    aTCWebFragment.mJavascriptBridge.broadcast("atc_auth", aTCWebFragment.createAuthJson(aTCWebFragment.getActivity()));
                } catch (Exception unused) {
                    ATCWebFragment aTCWebFragment2 = ATCWebFragment.this;
                    PublishSubject<WebEvent> publishSubject = aTCWebFragment2.mWebEventPublisher;
                    if (publishSubject != null) {
                        publishSubject.onError(new Exception("Error creating JSON for webview authentication"));
                    } else if (aTCWebFragment2.getActivity() != null) {
                        ATCWebFragment.this.getActivity().finish();
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject createAuthJson(final Context context) throws JSONException {
        String deviceType = AccountCredentialStorage.get(getContext()).getDeviceType();
        DeviceAttributes deviceAttributes = new DeviceAttributes(context);
        String customerId = AccountDetailUtil.get(context).getCustomerId();
        String customerSubscriptions = getCustomerSubscriptions();
        String deviceId = deviceAttributes.getDeviceId();
        String homeMarketPlaceId = AccountDetailUtil.get(context).getHomeMarketPlaceId();
        AccountDetailUtil.get(context);
        String musicTerritoryOfResidence = AccountDetailUtil.getMusicTerritoryOfResidence();
        String string = getResources().getConfiguration().locale.toString();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("customerId", customerId);
        jSONObject.put("customerSubscriptions", customerSubscriptions);
        jSONObject.put(Splash.PARAMS_DEVICE_ID, deviceId);
        jSONObject.put("oauthToken", this.token);
        jSONObject.put(Splash.PARAMS_DEVICE_TYPE, deviceType);
        jSONObject.put("marketplaceId", homeMarketPlaceId);
        jSONObject.put(Splash.PARAMS_MUSIC_TERRITORY, musicTerritoryOfResidence);
        jSONObject.put(Splash.PARAMS_LOCALE, string);
        jSONObject.put("customerFlow", this.customerFlow);
        jSONObject.put("sendSkipSignal", "allow");
        jSONObject.put("refMarker", encodeURL(this.refMarker));
        return jSONObject;
    }

    private String getCustomerSubscriptions() {
        ArrayList<SubscriptionType> subscriptions = UserSubscriptionUtil.getSubscriptions();
        ArrayList arrayList = new ArrayList();
        Iterator<SubscriptionType> it = subscriptions.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().name());
        }
        return TextUtils.join(",", arrayList);
    }

    private String encodeURL(String text) {
        try {
            return URLEncoder.encode(text, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException unused) {
            throw new AssertionError("UTF-8 is unknown");
        }
    }
}
