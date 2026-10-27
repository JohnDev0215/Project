package com.amazon.mp3.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.VisibleForTesting;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.DiscoverCustomerHomeJob;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.library.job.CirrusGenerateNonce;
import com.amazon.mp3.net.cirrus.BlueMoonExceptionsUtil;
import com.amazon.mp3.prime.DeviceAuthorizationManager;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.util.WebViewUtil;
import com.visualon.OSMPUtils.voOSType;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public class WebViewMgmt {
    private final Activity mActivity;
    private String mComponentName;
    private final OnWebViewtMgmt mOnWebViewMgmt;
    private WebView mWebView;
    private long mAccountDetailsJobId = -1;
    private long mGenerateNonceJobId = -1;
    private int mNumberTimesTokenInvalid = 0;
    private boolean mErrorLoadingURL = false;
    private boolean mWebViewTimeOutStarted = false;
    private Runnable mWebViewTimeOutRunnable = null;
    private final Handler mHandler = AmazonApplication.getDefaultHandler();
    private final WebViewUtil.WebViewResult mWebViewResult = new WebViewUtil.WebViewResult() { // from class: com.amazon.mp3.util.WebViewMgmt.4
        @Override // com.amazon.mp3.util.WebViewUtil.WebViewResult
        public void done() {
            WebViewMgmt.this.mOnWebViewMgmt.onPageFinishedResultOk();
            WebViewMgmt.this.startAccountDetailsJob(-1);
        }

        @Override // com.amazon.mp3.util.WebViewUtil.WebViewResult
        public void cancelled() {
            WebViewMgmt.this.mOnWebViewMgmt.onPageFinishedResultCancel();
        }

        @Override // com.amazon.mp3.util.WebViewUtil.WebViewResult
        public void error(WebViewUtil.WebViewReasonType webViewReasonType, Integer error) {
            if (webViewReasonType == WebViewUtil.WebViewReasonType.NONE || error == null) {
                return;
            }
            if (webViewReasonType == WebViewUtil.WebViewReasonType.TOKEN) {
                WebViewMgmt.access$1304(WebViewMgmt.this);
                if (WebViewMgmt.this.mNumberTimesTokenInvalid < 2) {
                    WebViewMgmt.this.startGenerateNonceJob();
                    return;
                }
            }
            WebViewMgmt.this.setResult(error.intValue());
            WebViewMgmt.this.showErrorDialog(error.intValue());
        }
    };

    public interface OnWebViewtMgmt {
        void onAboutToLoadUrl();

        long onAddJob(Job job);

        boolean onFinishActivity();

        String onGetUrl();

        int onGetWebViewId();

        void onPageFinished(WebView view, String url);

        boolean onPageFinishedResultCancel();

        void onPageFinishedResultOk();

        void onPageStarted(WebView view, String url, Bitmap favicon);

        void onReceivedError(WebView view, int errorCode, String description, String failingUrl);

        void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error);

        void onStartAccountDetailsJob();

        void onStartGenerateNonceJob();

        void onStopAccountDetailsJob();

        void onStopGenerateNonceJob();
    }

    public void onPause() {
    }

    public void onPostCreate(Bundle savedInstanceState) {
    }

    public void onResume() {
    }

    static /* synthetic */ int access$1304(WebViewMgmt webViewMgmt) {
        int i = webViewMgmt.mNumberTimesTokenInvalid + 1;
        webViewMgmt.mNumberTimesTokenInvalid = i;
        return i;
    }

    public WebViewMgmt(OnWebViewtMgmt onWebViewtMgmt, Activity activity) {
        this.mComponentName = null;
        this.mOnWebViewMgmt = onWebViewtMgmt;
        this.mActivity = activity;
        if (activity != null) {
            this.mComponentName = activity.getComponentName().toShortString();
        } else {
            this.mComponentName = "UnknownComponent";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getComponentName() {
        return this.mComponentName;
    }

    public void onCreate(Bundle savedInstanceState) {
        initializeWebView();
    }

    public void onSessionConnected(long sessionId) {
        startGenerateNonceJob();
    }

    public void onTrimMemory(int level) {
        if (this.mWebView != null) {
            Log.info("BlueMoon_WebViewMgmt", "On trim memory called %d", Integer.valueOf(level));
            this.mWebView.freeMemory();
        }
    }

    public void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
        boolean zOnFinishActivity;
        if (this.mAccountDetailsJobId == jobId) {
            Log.debug("BlueMoon_WebViewMgmt", "onJobFinished(%s): get account details job finished", getComponentName());
            this.mAccountDetailsJobId = -1L;
            BlueMoonExceptionsUtil.disableSelectedBlueMoonException(this.mActivity);
            Log.debug("BlueMoon_WebViewMgmt", "onJobFinished(%s): account valdation is %s and device authorization is %s", getComponentName(), Boolean.valueOf(WebViewUtil.isAccountValidationValid(this.mActivity)), Boolean.valueOf(DeviceAuthorizationManager.getInstance().isAuthorized()));
            OnWebViewtMgmt onWebViewtMgmt = this.mOnWebViewMgmt;
            if (onWebViewtMgmt != null) {
                onWebViewtMgmt.onStopAccountDetailsJob();
                zOnFinishActivity = this.mOnWebViewMgmt.onFinishActivity();
            } else {
                zOnFinishActivity = true;
            }
            if (zOnFinishActivity) {
                if (!WebViewUtil.isAccountValidationValid(this.mActivity)) {
                    Log.debug("BlueMoon_WebViewMgmt", "onJobFinished(%s): show library device", getComponentName());
                    this.mActivity.setResult(0);
                } else {
                    this.mActivity.setResult(-1);
                }
                this.mActivity.finish();
                return;
            }
            return;
        }
        if (this.mGenerateNonceJobId == jobId) {
            Log.debug("BlueMoon_WebViewMgmt", "onJobFinished(%s): show library device", getComponentName());
            this.mGenerateNonceJobId = -1L;
            Log.debug("BlueMoon_WebViewMgmt", "onJobFinished: generate nonce job finished");
            if (resultCode != 1) {
                Log.debug("BlueMoon_WebViewMgmt", "onJobFinished(%s): generate nonce max requeted", getComponentName());
                int i = this.mNumberTimesTokenInvalid + 1;
                this.mNumberTimesTokenInvalid = i;
                if (i >= 2) {
                    Log.debug("BlueMoon_WebViewMgmt", "onJobFinished(%s): generate nonce max requeted", getComponentName());
                    int i2 = WebViewUtil.RESULT_WEBVIEW_MAX_TOKEN_REQUESTED;
                    setResult(i2);
                    showErrorDialog(i2);
                    return;
                }
                Log.debug("BlueMoon_WebViewMgmt", "onJobFinished(%s): generate nonce again", getComponentName());
                startGenerateNonceJob();
                return;
            }
            OnWebViewtMgmt onWebViewtMgmt2 = this.mOnWebViewMgmt;
            if (onWebViewtMgmt2 != null) {
                onWebViewtMgmt2.onStopGenerateNonceJob();
                this.mOnWebViewMgmt.onAboutToLoadUrl();
                String strOnGetUrl = this.mOnWebViewMgmt.onGetUrl();
                if (checkDomainRestriction(strOnGetUrl)) {
                    Log.debug("BlueMoon_WebViewMgmt", "onJobFinished(%s): start loading urlStr", getComponentName());
                    Log.debugBuildOnly("BlueMoon_WebViewMgmt", "%s", strOnGetUrl);
                    this.mWebView.loadUrl(strOnGetUrl);
                } else {
                    Dialog dialogCreateRedirectionError = createRedirectionError(this.mActivity);
                    if (dialogCreateRedirectionError == null) {
                        this.mActivity.finish();
                    } else {
                        dialogCreateRedirectionError.show();
                    }
                }
            }
        }
    }

    @VisibleForTesting(otherwise = 2)
    public boolean checkDomainRestriction(final String urlStr) {
        if (urlStr != null) {
            try {
                URL url = new URL(urlStr);
                if (!"https".equalsIgnoreCase(url.getProtocol())) {
                    return false;
                }
                if (urlStr.contains(Environment.WEBVIEW.get().toHost())) {
                    return true;
                }
                String host = url.getHost();
                if (host == null) {
                    return false;
                }
                Iterator<String> it = EndpointsProvider.get().getSupportedDomains().iterator();
                while (it.hasNext()) {
                    if (host.endsWith(it.next())) {
                        return true;
                    }
                }
            } catch (MalformedURLException unused) {
                Log.debug("BlueMoon_WebViewMgmt", "checkDomainRestriction(%s): URL thrown MalformedURLException", getComponentName());
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean didWebViewTimeOut() {
        if (!this.mWebViewTimeOutStarted) {
            Log.debug("BlueMoon_WebViewMgmt", "didWebViewTimeOut(): the webview timeout timer was never started");
            return false;
        }
        if (this.mWebViewTimeOutRunnable != null) {
            Log.debug("BlueMoon_WebViewMgmt", "didWebViewTimeOut(): stopping the webview timeout timer");
            this.mHandler.removeCallbacks(this.mWebViewTimeOutRunnable);
            this.mWebViewTimeOutStarted = false;
            this.mWebViewTimeOutRunnable = null;
            return false;
        }
        Log.error("BlueMoon_WebViewMgmt", "didWebViewTimeOut(): the webview timed out");
        return true;
    }

    private class Injectable {
        private Injectable() {
        }

        @JavascriptInterface
        public void startTimeoutEvent() {
            Log.debug("BlueMoon_WebViewMgmt", "JS called startTimeoutEvent()");
            WebViewMgmt.this.mWebViewTimeOutStarted = true;
            WebViewMgmt.this.mWebViewTimeOutRunnable = new Runnable() { // from class: com.amazon.mp3.util.WebViewMgmt.Injectable.1
                @Override // java.lang.Runnable
                public void run() {
                    Log.error("BlueMoon_WebViewMgmt", "startTimeoutEvent.run(%s): the web view timeout", WebViewMgmt.this.getComponentName());
                    WebViewMgmt.this.mWebView.stopLoading();
                    if (WebViewMgmt.this.mWebViewTimeOutRunnable != null) {
                        WebViewMgmt.this.processReceivedError();
                        WebViewMgmt.this.mWebViewTimeOutStarted = false;
                        WebViewMgmt.this.mWebViewTimeOutRunnable = null;
                    }
                }
            };
            WebViewMgmt.this.mHandler.postDelayed(WebViewMgmt.this.mWebViewTimeOutRunnable, 30000L);
        }
    }

    private void initializeWebView() {
        OnWebViewtMgmt onWebViewtMgmt = this.mOnWebViewMgmt;
        if (onWebViewtMgmt == null) {
            return;
        }
        WebView webView = (WebView) this.mActivity.findViewById(onWebViewtMgmt.onGetWebViewId());
        this.mWebView = webView;
        webView.setBackgroundColor(0);
        this.mWebView.setVisibility(4);
        this.mWebView.addJavascriptInterface(new Injectable(), "amznBlueMoon");
        this.mWebView.setScrollBarStyle(voOSType.VOOSMP_SRC_FFAUDIO_WMA);
        WebSettings settings = this.mWebView.getSettings();
        settings.setSavePassword(false);
        settings.setSaveFormData(false);
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(false);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setCacheMode(2);
        this.mWebView.requestFocus(130);
        this.mWebView.setOnTouchListener(new View.OnTouchListener() { // from class: com.amazon.mp3.util.WebViewMgmt.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if ((action != 0 && action != 1) || v.hasFocus()) {
                    return false;
                }
                v.requestFocus();
                return false;
            }
        });
        this.mWebView.setWebViewClient(new WebViewClient() { // from class: com.amazon.mp3.util.WebViewMgmt.2
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (!WebViewUtil.shouldOpenBrowser(url)) {
                    return false;
                }
                WebViewUtil.startBrowserActivity(view.getContext(), url);
                return true;
            }

            @Override // android.webkit.WebViewClient
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.debug("BlueMoon_WebViewMgmt", "onPageStarted(%s)", WebViewMgmt.this.getComponentName());
                WebViewMgmt.this.mOnWebViewMgmt.onPageStarted(view, url, favicon);
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView view, String url) {
                Log.debug("BlueMoon_WebViewMgmt", "onPageFinished(%s)", WebViewMgmt.this.getComponentName());
                if (WebViewMgmt.this.mErrorLoadingURL) {
                    return;
                }
                if (WebViewMgmt.this.mWebView.getVisibility() != 0) {
                    WebViewMgmt.this.mWebView.setVisibility(0);
                }
                WebViewMgmt.this.mOnWebViewMgmt.onPageFinished(view, url);
                WebViewMgmt.this.processPageFinished(url);
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Log.debug("BlueMoon_WebViewMgmt", "onReceivedError(%s): error description = %s", WebViewMgmt.this.getComponentName(), description);
                WebViewMgmt.this.mErrorLoadingURL = true;
                WebViewMgmt.this.mOnWebViewMgmt.onReceivedError(view, errorCode, description, failingUrl);
                if (WebViewMgmt.this.mWebView != null) {
                    WebViewMgmt.this.mWebView.setVisibility(8);
                }
                if (WebViewMgmt.this.didWebViewTimeOut()) {
                    return;
                }
                WebViewMgmt.this.processReceivedError();
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedSslError(final WebView view, SslErrorHandler handler, SslError error) {
                WebViewMgmt.this.mOnWebViewMgmt.onReceivedSslError(view, handler, error);
                WebViewMgmt.this.showErrorDialog(WebViewUtil.RESULT_WEBVIEW_SSL_ERROR);
                handler.cancel();
                Log.warning("BlueMoon_WebViewMgmt", "SSL Error: " + error);
                WebViewMgmt.this.mHandler.postDelayed(new Runnable() { // from class: com.amazon.mp3.util.WebViewMgmt.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        WebViewUtil.handleSslError(view.getContext(), view.getSettings());
                    }
                }, 500L);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startGenerateNonceJob() {
        if (AmazonApplication.getCapabilities().shouldCloudBeSupported()) {
            Log.debug("BlueMoon_WebViewMgmt", "startGenerateNonceJob(%s): start generate nonce job", getComponentName());
            if (this.mGenerateNonceJobId == -1) {
                Log.debug("BlueMoon_WebViewMgmt", "startGenerateNonceJob: start generate nonce job");
                long jAddJob = addJob(new CirrusGenerateNonce());
                this.mGenerateNonceJobId = jAddJob;
                if (jAddJob != -1) {
                    this.mOnWebViewMgmt.onStartGenerateNonceJob();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startAccountDetailsJob(int resultCode) {
        setResult(resultCode);
        if (AmazonApplication.getCapabilities().shouldCloudBeSupported() && this.mAccountDetailsJobId == -1) {
            Log.debug("BlueMoon_WebViewMgmt", "startAccountDetailsJob(%s): start get accounts detail job", getComponentName());
            long jAddJob = addJob(new DiscoverCustomerHomeJob());
            this.mAccountDetailsJobId = jAddJob;
            if (jAddJob != -1) {
                this.mOnWebViewMgmt.onStartAccountDetailsJob();
            }
        }
    }

    private long addJob(Job job) {
        OnWebViewtMgmt onWebViewtMgmt = this.mOnWebViewMgmt;
        if (onWebViewtMgmt == null) {
            return -1L;
        }
        return onWebViewtMgmt.onAddJob(job);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processPageFinished(String url) {
        WebViewUtil.processPageFinished(url, didWebViewTimeOut(), this.mWebViewResult);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processReceivedError() {
        int i = WebViewUtil.RESULT_WEBVIEW_NETWORK_ERROR;
        setResult(i);
        showErrorDialog(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setResult(int result) {
        this.mActivity.setResult(result);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showErrorDialog(final int id) {
        this.mHandler.postDelayed(new Runnable() { // from class: com.amazon.mp3.util.WebViewMgmt.5
            @Override // java.lang.Runnable
            public void run() {
                if (WebViewMgmt.this.mActivity == null || WebViewMgmt.this.mActivity.isFinishing()) {
                    return;
                }
                WebViewMgmt.this.mActivity.showDialog(id);
            }
        }, 100L);
    }

    private static Dialog createRedirectionError(final Activity activity) {
        BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(activity);
        bauhausDialogBuilder.setTitle(R.string.dmusic_shared_blue_moon_web_view_redirection_error_title);
        bauhausDialogBuilder.setMessage(R.string.dmusic_shared_blue_moon_web_view_redirection_error_message);
        bauhausDialogBuilder.setPositiveButton(R.string.dmusic_shared_blue_moon_web_view_redirection_error_message_ok, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.util.WebViewMgmt.6
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                activity.finish();
                if (AccountCredentialUtil.get().isSignedIn()) {
                    Activity activity2 = activity;
                    activity2.startActivity(MusicHomeActivity.getStartIntent(activity2, LastViewedScreenUtil.LastViewedSource.DEVICE));
                }
            }
        });
        bauhausDialogBuilder.setNegativeButton(R.string.dmusic_shared_blue_moon_web_view_redirection_error_message_contact, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.util.WebViewMgmt.7
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                EmailUtil.contactCustomerSupport(activity);
                activity.finish();
            }
        });
        return bauhausDialogBuilder.create();
    }
}
