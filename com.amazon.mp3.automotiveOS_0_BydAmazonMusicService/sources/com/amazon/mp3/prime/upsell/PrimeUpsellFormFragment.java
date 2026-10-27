package com.amazon.mp3.prime.upsell;

import android.content.Context;
import android.net.Uri;
import android.net.http.SslError;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.NonNull;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.fragment.BaseFragment;
import com.amazon.mp3.fragment.BaseWebFragment;
import com.amazon.mp3.prime.upsell.PrimeUpsellActivity;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.store.html5.bridge.JavascriptBridge;
import com.amazon.mp3.store.html5.bridge.Message;
import com.amazon.mp3.store.html5.map.MAPCookiesCallback;
import com.amazon.mp3.store.html5.map.MAPCookiesUtil;
import com.amazon.mp3.upsellweb.UpsellResultType;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UniqueCodeUtil;
import com.amazon.mp3.util.WebViewUtil;
import com.amazon.mp3.web.BasicWebEvent;
import com.amazon.mp3.web.webtarget.WebTarget;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.validator.ValidatorRegistry;
import java.nio.charset.StandardCharsets;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public class PrimeUpsellFormFragment extends BaseWebFragment {
    private String mUrl;
    private static final String TAG = PrimeUpsellFormFragment.class.getSimpleName();
    public static final int PRIME_UPSELL_EXTERNAL_BROWSER_REQUEST_CODE = UniqueCodeUtil.nextUniqueCode();
    private static final BasicWebEvent mExplorePrimeMusicWebEvent = new BasicWebEvent(PrimeUpsellActivity.UpsellWebEventEnum.EXPLORE_PRIME_MUSIC.name());
    private static final BasicWebEvent mCustomerUpgradedWebEvent = new BasicWebEvent(PrimeUpsellActivity.UpsellWebEventEnum.CUSTOMER_UPGRADED.name());
    private static final BasicWebEvent mCustomerDeferredWebEvent = new BasicWebEvent(PrimeUpsellActivity.UpsellWebEventEnum.CUSTOMER_DEFERRED.name());
    private static final BasicWebEvent mSignupErrorWebEvent = new BasicWebEvent(PrimeUpsellActivity.UpsellWebEventEnum.SIGNUP_ERROR.name());
    private static final BasicWebEvent mContinueSignUpWebEvent = new BasicWebEvent(PrimeUpsellActivity.UpsellWebEventEnum.CONTINUE_TO_SIGN_UP.name());
    private long mCloudTrackCountJobId = -1;
    private long mDeviceTrackCountJobId = -1;
    private long mRefreshPrimeStatusJobId = -1;
    private boolean mCookiesFetched = false;
    private boolean clearHistory = false;
    private final Runnable mBridgeTimeoutRunnable = new Runnable() { // from class: com.amazon.mp3.prime.upsell.PrimeUpsellFormFragment.2
        @Override // java.lang.Runnable
        public void run() {
            Log.warning(PrimeUpsellFormFragment.TAG, "Error loading FTU webview: Timeout or 404.");
            PrimeUpsellFormFragment.this.invokeGeneralErrorCallback();
        }
    };

    public static PrimeUpsellFormFragment newInstance(final WebTarget webTarget) {
        PrimeUpsellFormFragment primeUpsellFormFragment = new PrimeUpsellFormFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("com.amazon.mp3.extra.WebTargetKey.EXTRA_WEB_TARGET", webTarget);
        primeUpsellFormFragment.setArguments(bundle);
        return primeUpsellFormFragment;
    }

    @Override // com.amazon.mp3.fragment.BaseWebFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewOnCreateView = super.onCreateView(inflater, container, savedInstanceState);
        this.mUrl = getWebTarget().getUrl();
        return viewOnCreateView;
    }

    private WebTarget getWebTarget() {
        WebTarget webTarget = (WebTarget) getArguments().getSerializable("com.amazon.mp3.extra.WebTargetKey.EXTRA_WEB_TARGET");
        if (webTarget != null) {
            return webTarget;
        }
        throw new IllegalArgumentException("Cannot start Prime Upsell Form Fragment without a web target");
    }

    @Override // com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Context applicationContext = getApplication().getApplicationContext();
        MAPCookiesUtil.fetchCookies(new CookiesCallback(applicationContext), true);
        startDataJobsIfNeeded(applicationContext);
    }

    private class CookiesCallback extends MAPCookiesCallback {
        CookiesCallback(final Context context) {
            super(context);
        }

        @Override // com.amazon.mp3.store.html5.map.MAPCookiesCallback, com.amazon.identity.auth.device.api.Callback
        public void onError(Bundle errorBundle) {
            super.onError(errorBundle);
            PrimeUpsellFormFragment.this.invokeGeneralErrorCallback();
        }

        @Override // com.amazon.mp3.store.html5.map.MAPCookiesCallback, com.amazon.identity.auth.device.api.Callback
        public void onSuccess(Bundle successBundle) {
            super.onSuccess(successBundle);
            PrimeUpsellFormFragment.this.mCookiesFetched = true;
            PrimeUpsellFormFragment.this.executePurchaseCheckAndLoadWebView();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadWebViewAfterDelay() {
        AmazonApplication.getDefaultHandler().postDelayed(new Runnable() { // from class: com.amazon.mp3.prime.upsell.PrimeUpsellFormFragment.1
            @Override // java.lang.Runnable
            public void run() {
                PrimeUpsellFormFragment primeUpsellFormFragment = PrimeUpsellFormFragment.this;
                primeUpsellFormFragment.loadWebView(primeUpsellFormFragment.mUrl);
            }
        }, 101L);
    }

    private void startDataJobsIfNeeded(final Context context) {
        this.mRefreshPrimeStatusJobId = addJob(new RefreshPrimeStatusJob());
        if (PrimeUpsellSettingsUtil.hasCloudTracks(context) == null) {
            Log.verbose(TAG, "Fetching cloud track count");
            this.mCloudTrackCountJobId = addJob(new GetCloudTrackCountJob());
        }
        if (PrimeUpsellSettingsUtil.hasDeviceTracks(context) == null) {
            Log.verbose(TAG, "Fetching device track count");
            this.mDeviceTrackCountJobId = addJob(new GetDeviceTrackCountJob());
        }
    }

    @Override // com.amazon.mp3.service.job.JobSessionFragment
    protected void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
        super.onJobFinished(jobId, job, resultCode, resultBundle);
        if (jobId == this.mCloudTrackCountJobId) {
            this.mCloudTrackCountJobId = -1L;
            if (resultCode == 1) {
                PrimeUpsellSettingsUtil.setHasCloudTracks(getApplication().getApplicationContext(), ((GetCloudTrackCountJob) job).getCloudTrackCount() > 0);
            }
        } else if (jobId == this.mDeviceTrackCountJobId) {
            this.mDeviceTrackCountJobId = -1L;
            if (resultCode == 1) {
                PrimeUpsellSettingsUtil.setHasDeviceTracks(getApplication().getApplicationContext(), ((long) ((GetDeviceTrackCountJob) job).getDeviceTrackCount()) > 0);
            }
        } else if (jobId == this.mRefreshPrimeStatusJobId) {
            this.mRefreshPrimeStatusJobId = -1L;
        }
        loadWebView(this.mUrl);
    }

    @Override // com.amazon.mp3.fragment.BaseWebFragment
    protected void initWebView() {
        super.initWebView();
        this.mWebView.addJavascriptInterface(new HashChangeListener(), "hcl");
        this.mWebView.setOverScrollMode(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadWebView(final String url) {
        if (isReadyToLoadWebview()) {
            if (jobExecutionErrorsOccurred()) {
                Log.error(TAG, "An error occurred executing webview jobs, finishing with error.");
                invokeGeneralErrorCallback();
            }
            this.mHandler.removeCallbacks(this.mBridgeTimeoutRunnable);
            this.mHandler.postDelayed(this.mBridgeTimeoutRunnable, 30000L);
            Log.debug(TAG, "Webview navigating to %s", url);
            this.mWebView.loadUrl(url);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invokeGeneralErrorCallback() {
        this.mWebEventPublisher.onError(new Exception("invokeGeneralErrorCallback in PrimeUpsellFormFragment called"));
    }

    private boolean isReadyToLoadWebview() {
        return this.mCloudTrackCountJobId == -1 && this.mDeviceTrackCountJobId == -1 && this.mRefreshPrimeStatusJobId == -1 && this.mCookiesFetched;
    }

    private boolean jobExecutionErrorsOccurred() {
        return PrimeUpsellSettingsUtil.hasCloudTracks(getActivity()) == null || PrimeUpsellSettingsUtil.hasDeviceTracks(getActivity()) == null;
    }

    @Override // com.amazon.mp3.fragment.BaseWebFragment
    protected WebViewClient createWebViewClient() {
        return new WebViewClient() { // from class: com.amazon.mp3.prime.upsell.PrimeUpsellFormFragment.3
            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView view, String url) {
                if (PrimeUpsellFormFragment.this.clearHistory) {
                    ((BaseWebFragment) PrimeUpsellFormFragment.this).mWebView.clearHistory();
                    PrimeUpsellFormFragment.this.clearHistory = false;
                }
                if (url.contains("music/prime/splash")) {
                    PrimeUpsellFormFragment.this.sendUiPageViewMetric("PrimeUpsellFTU");
                }
                super.onPageFinished(view, url);
                ((BaseWebFragment) PrimeUpsellFormFragment.this).mProgressBar.setVisibility(8);
                ((BaseWebFragment) PrimeUpsellFormFragment.this).mWebView.setVisibility(0);
                invokeUrlHashChangeListener(view);
            }

            private void invokeUrlHashChangeListener(final WebView view) {
                view.loadUrl("javascript:window.onhashchange = function() { hcl.onHashChange(window.location.href) };");
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return PrimeUpsellFormFragment.this.handleURLChange(url);
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                Log.error(PrimeUpsellFormFragment.TAG, "Error %d loading FTU webview", Integer.valueOf(errorCode));
                invokeErrorCallbacks();
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                invokeErrorCallbacks();
                handler.cancel();
                Log.warning(PrimeUpsellFormFragment.TAG, "SSL Error: " + error);
                WebViewUtil.handleSslError(view.getContext(), view.getSettings());
            }

            private void invokeErrorCallbacks() {
                ((BaseFragment) PrimeUpsellFormFragment.this).mHandler.removeCallbacks(PrimeUpsellFormFragment.this.mBridgeTimeoutRunnable);
                ((BaseWebFragment) PrimeUpsellFormFragment.this).mWebEventPublisher.onError(new Exception("invokeErrorCallbacks in createWebViewClient in PrimeUpsellFormFragment called"));
            }
        };
    }

    @Override // com.amazon.mp3.fragment.BaseWebFragment
    protected JavascriptBridge createJavascriptBridge() {
        return new JavascriptBridge("FTUJsBridge");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean handleURLChange(String url) {
        if (this.mWebEventPublisher != null && !TextUtils.isEmpty(url)) {
            if (AccountDetailUtil.get().isRowPrimeOnlyMarketplace()) {
                return handleRowPrimeOnlyUrl(url);
            }
            if (url.contains("#startPlaying")) {
                this.mHandler.removeCallbacks(this.mBridgeTimeoutRunnable);
                this.mWebEventPublisher.onNext(mExplorePrimeMusicWebEvent);
                return true;
            }
            if (url.contains("#notnow")) {
                this.mWebEventPublisher.onNext(mCustomerDeferredWebEvent);
                return true;
            }
            if (url.contains("/gp/dmusic/device/mp3/primeftu/thankyou")) {
                if (url.contains("#continue")) {
                    this.mWebEventPublisher.onNext(mCustomerUpgradedWebEvent);
                    return true;
                }
                if (!url.contains("subscribeResult%3DSUCCESS")) {
                    if (url.contains("cancelledSignUp=1")) {
                        this.mWebEventPublisher.onNext(mCustomerDeferredWebEvent);
                        return true;
                    }
                    this.mWebEventPublisher.onNext(mSignupErrorWebEvent);
                    return true;
                }
            } else if (url.contains("prime/pipeline/signup")) {
                sendUiClickMetric("PrimeFTU");
            }
        }
        return false;
    }

    private boolean handleRowPrimeOnlyUrl(final String url) {
        if (url.contains("#startPlaying")) {
            this.mWebEventPublisher.onNext(mExplorePrimeMusicWebEvent);
            return true;
        }
        if (url.contains("#notnow")) {
            sendUiClickMetric("noThanksPrimeFTU");
            this.mWebEventPublisher.onNext(mCustomerDeferredWebEvent);
            return true;
        }
        String queryParameter = Uri.parse(url).getQueryParameter("redirectURL");
        if (TextUtils.isEmpty(queryParameter)) {
            sendUiClickMetric("noThanksPrimeFTU");
            this.mWebEventPublisher.onNext(mCustomerDeferredWebEvent);
            return true;
        }
        String str = new String(Base64.decode(queryParameter, 0), StandardCharsets.UTF_8);
        if (TextUtils.isEmpty(str)) {
            this.mWebEventPublisher.onNext(mSignupErrorWebEvent);
            return true;
        }
        if (str.contains("/gp/dmusic/device/mp3/primeftu/thankyou")) {
            if (str.contains(UpsellResultType.CONTINUE.toString())) {
                this.mWebEventPublisher.onNext(mContinueSignUpWebEvent);
            } else {
                this.mWebEventPublisher.onNext(mCustomerUpgradedWebEvent);
            }
            return true;
        }
        if (str.contains("/gp/dmusic/cloudplayer/web")) {
            sendUiClickMetric("noThanksPrimeFTU");
            this.mWebEventPublisher.onNext(mCustomerDeferredWebEvent);
            return true;
        }
        Log.warning(TAG, "Unable to handle prime-only-ROW Url: " + url);
        return false;
    }

    public void resetWebTarget(@NonNull WebTarget webTarget) {
        this.clearHistory = true;
        String url = webTarget.getUrl();
        this.mUrl = url;
        loadWebView(url);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.prime.upsell.PrimeUpsellFormFragment$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$prime$upsell$FtuMessageType;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$store$html5$bridge$Message$Type;

        static {
            int[] iArr = new int[Message.Type.values().length];
            $SwitchMap$com$amazon$mp3$store$html5$bridge$Message$Type = iArr;
            try {
                iArr[Message.Type.Broadcast.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            int[] iArr2 = new int[FtuMessageType.values().length];
            $SwitchMap$com$amazon$mp3$prime$upsell$FtuMessageType = iArr2;
            try {
                iArr2[FtuMessageType.INITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$upsell$FtuMessageType[FtuMessageType.NOT_NOW.ordinal()] = 2;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$upsell$FtuMessageType[FtuMessageType.CONTINUE.ordinal()] = 3;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$upsell$FtuMessageType[FtuMessageType.START_PLAYING.ordinal()] = 4;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$upsell$FtuMessageType[FtuMessageType.EMIT_METRIC.ordinal()] = 5;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    @Override // com.amazon.mp3.fragment.BaseWebFragment
    protected void handleMessage(final Message message) {
        if (AnonymousClass5.$SwitchMap$com$amazon$mp3$store$html5$bridge$Message$Type[message.getType().ordinal()] != 1) {
            return;
        }
        int i = AnonymousClass5.$SwitchMap$com$amazon$mp3$prime$upsell$FtuMessageType[FtuMessageType.fromString(message.getName()).ordinal()];
        if (i == 1) {
            this.mHandler.removeCallbacks(this.mBridgeTimeoutRunnable);
            return;
        }
        if (i == 2) {
            this.mWebEventPublisher.onNext(mCustomerDeferredWebEvent);
            return;
        }
        if (i == 3) {
            this.mWebEventPublisher.onNext(mCustomerUpgradedWebEvent);
            return;
        }
        if (i == 4) {
            this.mWebEventPublisher.onNext(mExplorePrimeMusicWebEvent);
        } else if (i == 5) {
            emitMetric(message.getOptions());
        } else {
            Log.debug(TAG, "Unhandled broadcast from ftu: %s", message.getName());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executePurchaseCheckAndLoadWebView() {
        new AsyncTask<Void, Void, Boolean>() { // from class: com.amazon.mp3.prime.upsell.PrimeUpsellFormFragment.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Boolean doInBackground(Void... objects) {
                Log.debug(PrimeUpsellFormFragment.TAG, "Executing purchase validation");
                return Boolean.valueOf(ValidatorRegistry.getInstance().getValidator("Purchase").isAllowed(true));
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(final Boolean isPurchaseAllowed) {
                Log.debug(PrimeUpsellFormFragment.TAG, "Received purchase validation result:" + isPurchaseAllowed);
                if (isPurchaseAllowed.booleanValue()) {
                    PrimeUpsellFormFragment.this.loadWebViewAfterDelay();
                } else if (PrimeUpsellFormFragment.this.getActivity() != null) {
                    PrimeUpsellFormFragment.this.getActivity().finish();
                }
            }
        }.execute(new Void[0]);
    }

    private void emitMetric(final JSONObject options) {
        try {
            String string = options.getString("eventType");
            String string2 = options.getString("identifier");
            if ("uiPageView".equals(string)) {
                sendUiPageViewMetric(string2, options.getString(ParserUtil.TAG_QUERY_PARAM_NAME));
            } else if ("uiClick".equals(string)) {
                sendUiClickMetric(string2, options.getString("ref"));
            }
        } catch (JSONException unused) {
            Log.warning(TAG, "Received broadcast to emit metric, but unable to parse the broadcast: " + options.toString());
        }
    }

    private class HashChangeListener {
        private HashChangeListener() {
        }

        @JavascriptInterface
        public void onHashChange(String url) {
            PrimeUpsellFormFragment.this.handleURLChange(url);
        }
    }
}
