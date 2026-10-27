package com.android.hotspot2.osulogin;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import java.net.MalformedURLException;
import java.net.URL;

/* JADX INFO: loaded from: classes.dex */
public class OsuLoginActivity extends Activity {
    private ConnectivityManager mCm;
    private String mHostName;
    private Network mNetwork;
    private ConnectivityManager.NetworkCallback mNetworkCallback;
    private ProgressBar mProgressBar;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private String mUrl;
    private WebView mWebView;
    private WifiManager mWifiManager;
    private boolean mForceDisconnect = true;
    boolean mRedirectResponseReceived = false;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d("OsuLogin", "onCreate: Opening OSU Web View");
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService("wifi");
        this.mWifiManager = wifiManager;
        if (wifiManager == null) {
            Log.e("OsuLogin", "Cannot get wifi service");
            finishAndRemoveTask();
            return;
        }
        if (getIntent() == null) {
            Log.e("OsuLogin", "Intent is null");
            finishAndRemoveTask();
            return;
        }
        Network network = (Network) getIntent().getParcelableExtra("android.net.wifi.extra.OSU_NETWORK");
        this.mNetwork = network;
        if (network == null) {
            Log.e("OsuLogin", "Cannot get the network instance for OSU from intent");
            finishAndRemoveTask();
            return;
        }
        String stringExtra = getIntent().getStringExtra("android.net.wifi.extra.URL");
        this.mUrl = stringExtra;
        if (stringExtra == null) {
            Log.e("OsuLogin", "Cannot get OSU server url from intent");
            finishAndRemoveTask();
            return;
        }
        String host = getHost(stringExtra);
        this.mHostName = host;
        if (host == null) {
            Log.e("OsuLogin", "Cannot get host from the url");
            finishAndRemoveTask();
            return;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService("connectivity");
        this.mCm = connectivityManager;
        if (connectivityManager == null) {
            Log.e("OsuLogin", "Cannot get connectivity service");
            finishAndRemoveTask();
            return;
        }
        if (!connectivityManager.bindProcessToNetwork(this.mNetwork)) {
            Log.e("OsuLogin", "Network is no longer valid");
            finishAndRemoveTask();
            return;
        }
        NetworkCapabilities networkCapabilities = this.mCm.getNetworkCapabilities(this.mNetwork);
        if (networkCapabilities == null || !networkCapabilities.hasTransport(1)) {
            Log.e("OsuLogin", "WiFi is not supported for the Network");
            finishAndRemoveTask();
            return;
        }
        getActionBar().setDisplayShowHomeEnabled(false);
        getActionBar().setElevation(0.0f);
        getActionBar().setTitle(getString(R.string.action_bar_label));
        getActionBar().setSubtitle("");
        setContentView(R.layout.osu_web_view);
        this.mNetworkCallback = new ConnectivityManager.NetworkCallback() { // from class: com.android.hotspot2.osulogin.OsuLoginActivity.1
            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onLost(Network network2) {
                Log.d("OsuLogin", "Lost for the current Network, close the browser");
                OsuLoginActivity.this.mForceDisconnect = false;
                OsuLoginActivity osuLoginActivity = OsuLoginActivity.this;
                if (!osuLoginActivity.mRedirectResponseReceived) {
                    osuLoginActivity.showSignUpFailedToast();
                }
                if (OsuLoginActivity.this.mNetwork.equals(network2)) {
                    OsuLoginActivity.this.finishAndRemoveTask();
                }
            }
        };
        this.mCm.registerNetworkCallback(new NetworkRequest.Builder().addTransportType(1).removeCapability(14).build(), this.mNetworkCallback);
        WebView webView = (WebView) findViewById(R.id.webview);
        this.mWebView = webView;
        webView.clearCache(true);
        WebSettings settings = this.mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setMixedContentMode(2);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        this.mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        this.mWebView.setWebViewClient(new OsuWebViewClient());
        this.mWebView.setWebChromeClient(new WebChromeClient() { // from class: com.android.hotspot2.osulogin.OsuLoginActivity.2
            @Override // android.webkit.WebChromeClient
            public void onProgressChanged(WebView webView2, int i) {
                OsuLoginActivity.this.mProgressBar.setProgress(i);
            }
        });
        Log.d("OsuLogin", "OSU Web View to " + this.mUrl);
        this.mWebView.loadUrl(this.mUrl);
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        this.mSwipeRefreshLayout = swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.android.hotspot2.osulogin.-$$Lambda$OsuLoginActivity$utd7sjVqarzYe7IGZp_jikgo8fA
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                this.f$0.lambda$onCreate$0$OsuLoginActivity();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreate$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreate$0$OsuLoginActivity() {
        this.mWebView.reload();
        this.mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && this.mWebView.canGoBack()) {
            this.mWebView.goBack();
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        ConnectivityManager.NetworkCallback networkCallback = this.mNetworkCallback;
        if (networkCallback != null) {
            this.mCm.unregisterNetworkCallback(networkCallback);
            this.mNetworkCallback = null;
        }
        WifiManager wifiManager = this.mWifiManager;
        if (wifiManager != null && this.mForceDisconnect) {
            wifiManager.disconnect();
            this.mWifiManager = null;
        }
        super.onDestroy();
    }

    private String getHost(String str) {
        try {
            return new URL(str).getHost();
        } catch (MalformedURLException unused) {
            Log.e("OsuLogin", "Invalid URL " + str);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getHeaderSubtitle(String str) {
        try {
            URL url = new URL(str);
            return url.getProtocol() + "://" + url.getHost();
        } catch (MalformedURLException unused) {
            Log.e("OsuLogin", "Invalid URL " + str);
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSignUpFailedToast() {
        Toast.makeText(getApplicationContext(), R.string.sign_up_failed, 0).show();
    }

    private class OsuWebViewClient extends WebViewClient {
        boolean mPageError;

        private OsuWebViewClient() {
            this.mPageError = false;
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            OsuLoginActivity.this.getActionBar().setSubtitle(OsuLoginActivity.this.getHeaderSubtitle(str));
            OsuLoginActivity.this.mProgressBar.setVisibility(0);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            OsuLoginActivity.this.mProgressBar.setVisibility(4);
            OsuLoginActivity.this.mSwipeRefreshLayout.setRefreshing(false);
            if (this.mPageError) {
                OsuLoginActivity osuLoginActivity = OsuLoginActivity.this;
                if (osuLoginActivity.mRedirectResponseReceived) {
                    osuLoginActivity.mForceDisconnect = false;
                }
                OsuLoginActivity.this.finishAndRemoveTask();
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            if (webResourceRequest.getUrl().toString().startsWith("http://127.0.0.1")) {
                OsuLoginActivity.this.mRedirectResponseReceived = true;
                webView.stopLoading();
            }
            if (webResourceRequest.isForMainFrame()) {
                this.mPageError = true;
                Log.e("OsuLogin", "onReceived Error for MainFrame: " + webResourceError.getErrorCode());
            }
        }
    }
}
