package com.amazon.mp3.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import androidx.appcompat.widget.Toolbar;
import com.amazon.identity.auth.device.api.MAPAndroidWebViewClient;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.det.DetUploader;
import com.amazon.mp3.det.report.CustomerServiceReport;
import com.amazon.mp3.environment.MusicTerritory;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.library.dialog.NetworkFailureWithSettingsDialog;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.EmailUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.mp3.util.StringUtil;
import com.amazon.mp3.util.WebViewUtil;
import com.amazon.mp3.util.health.AppHealthEventMonitor;
import com.amazon.mp3.util.health.AppHealthUtil;
import com.amazon.music.destination.ContactUsDestination;
import java.util.Locale;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
public class CantileverActivity extends BaseActivity {
    private static final String TAG = "CantileverActivity";
    private AppHealthEventMonitor mAppHealthEventMonitor;
    private ProgressBar mProgressBar;
    private Toolbar mToolbar;
    private String mUrl;
    private WebView mWebView;
    private boolean mOnThankYouPage = false;
    private boolean mIsResume = false;

    @Override // com.amazon.mp3.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent newIntent) {
        super.onNewIntent(newIntent);
        if (isChatRedirect(newIntent)) {
            decideURLToLoad(getIntent().getBooleanExtra("com.amazon.mp3.extra.CantileverActivity.EXTRA_LAUNCH_FEEDBACK_FORM", false), true);
            loadWebView();
        }
    }

    private boolean isChatRedirect(Intent intent) {
        return intent.hasExtra("target") && ContactUsDestination.CHAT.equals(intent.getStringExtra("target"));
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!ConnectivityUtil.hasAnyInternetConnection(this)) {
            startActivity(new Intent(this, (Class<?>) NetworkFailureWithSettingsDialog.class));
            finish();
        }
        setContentView(R.layout.fragment_cantilever);
        this.mProgressBar = (ProgressBar) findViewById(R.id.ProgressBar);
        this.mWebView = (WebView) findViewById(R.id.WebView);
        this.mToolbar = (Toolbar) findViewById(R.id.toolbar);
        boolean booleanExtra = getIntent().getBooleanExtra("com.amazon.mp3.extra.CantileverActivity.EXTRA_LAUNCH_FEEDBACK_FORM", false);
        decideURLToLoad(booleanExtra, isChatRedirect(getIntent()));
        initAppHealthEventMonitor();
        initActionBar(booleanExtra);
        initWebView();
    }

    private void decideURLToLoad(boolean launchFeedbackForm, boolean isChatRedirect) {
        if (launchFeedbackForm) {
            this.mUrl = EndpointsProvider.get().getCantileverEndpoint() + "csad/contactus/feedback/off/MusicCantilever/CantileverMusicForAndroid";
            return;
        }
        this.mUrl = EndpointsProvider.get().getCantileverEndpoint();
        String stringPref = SettingsUtil.getStringPref(getApplicationContext(), R.string.setting_key_dev_cantilever_endpoint);
        if (!StringUtil.isNullOrWhiteSpaces(stringPref)) {
            this.mUrl = stringPref;
        }
        String str = isChatRedirect ? "?isChatRedirect=true" : "";
        this.mUrl += "csad/workflow/3b5fce32-529e-4ac4-a3de-f3713f52b60b";
        String str2 = TAG;
        Log.debug(str2, "Appending isChatRedirect param:" + str);
        this.mUrl += str;
        Log.debug(str2, "final URL:" + this.mUrl);
    }

    private void initAppHealthEventMonitor() {
        this.mAppHealthEventMonitor = new AppHealthUtil(PreferenceManager.getDefaultSharedPreferences(this));
    }

    private void initActionBar(final boolean launchFeedbackForm) {
        setSupportActionBar(this.mToolbar);
        if (launchFeedbackForm) {
            getSupportActionBar().setTitle(R.string.dmusic_logging_email_logfile_title);
        } else {
            getSupportActionBar().setTitle(R.string.dmusic_setting_help_and_feedback_title);
        }
        this.mToolbar.setNavigationIcon(R.drawable.btn_close);
        this.mToolbar.setNavigationContentDescription(R.string.close_button_content_description);
        this.mToolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.activity.CantileverActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                CantileverActivity.this.finish();
            }
        });
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        loadWebView();
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        WebView webView = this.mWebView;
        if (webView == null || TextUtils.isEmpty(webView.getOriginalUrl())) {
            return;
        }
        this.mIsResume = true;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        this.mIsResume = false;
        if (this.mWebView.canGoBack() && !this.mOnThankYouPage) {
            this.mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    private void initWebView() {
        this.mWebView.setWebViewClient(new CantileverWebViewClient(this));
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.getSettings().setDomStorageEnabled(true);
        this.mWebView.setOverScrollMode(2);
        this.mWebView.getSettings().setSupportMultipleWindows(true);
        this.mWebView.setWebChromeClient(new WebChromeClient() { // from class: com.amazon.mp3.activity.CantileverActivity.2
            @Override // android.webkit.WebChromeClient
            public boolean onCreateWindow(WebView view, boolean dialog, boolean userGesture, Message resultMsg) {
                WebView.HitTestResult hitTestResult = view.getHitTestResult();
                String extra = hitTestResult.getExtra();
                if (!TextUtils.isEmpty(extra) && CantileverActivity.this.isSupportedUrlForForum(extra)) {
                    CantileverActivity.this.loadUrlOnWebView(extra);
                    return true;
                }
                if (extra.matches("^(|http|https)://(|[\\w\\.-]+\\.)amazon\\.(com|com.au|com.br|ca|cn|de|es|fr|co.id|in|it|co.jp|com.mx|nl|ru|co.uk)($|/.*)") || hitTestResult.getType() != 7) {
                    return true;
                }
                view.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(extra)));
                return false;
            }
        });
        this.mWebView.getSettings().setUserAgentString(this.mWebView.getSettings().getUserAgentString() + "(CLVR)");
        setCantileverCookies(this);
    }

    private void loadWebView() {
        this.mWebView.loadUrl(this.mUrl);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadUrlOnWebView(String url) {
        this.mWebView.loadUrl(url);
    }

    private String getBaseUrl(final String completeUrl) {
        return HttpUrl.get(completeUrl).topPrivateDomain();
    }

    private String getAmazonAppInfo(String baserUrl) {
        String str;
        String str2;
        if (PlatformUtil.isFireOS()) {
            str2 = "FireOS";
            str = "amazon";
        } else {
            str = "google-play";
            str2 = "Android";
        }
        return String.format("amzn-app-info=name=%s&os=%s&store=%s; Path=/; domain=%s", "music", str2.toLowerCase(Locale.ENGLISH), str, baserUrl);
    }

    private class CantileverWebViewClient extends MAPAndroidWebViewClient {
        public CantileverWebViewClient(final Activity activity) {
            super(activity);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView view, String rawUrl) {
            super.onPageFinished(view, rawUrl);
            CantileverActivity.this.mProgressBar.setVisibility(8);
            CantileverActivity.this.mWebView.setVisibility(0);
            Uri uri = Uri.parse(rawUrl);
            if (uri.getPath().contains("thankyou")) {
                CantileverActivity.this.mOnThankYouPage = true;
                CantileverActivity.this.mAppHealthEventMonitor.customerSupportContacted();
                uploadLogs(uri.getQueryParameter("dtsSessionId"));
                return;
            }
            CantileverActivity.this.mOnThankYouPage = false;
        }

        private void uploadLogs(String dtsSessionId) {
            Context applicationContext = CantileverActivity.this.getApplicationContext();
            if (dtsSessionId == null) {
                Log.warning(CantileverActivity.TAG, "No DTS Session ID found in thankyou url");
            } else {
                Log.info(CantileverActivity.TAG, "DTS Session ID found: " + dtsSessionId);
            }
            EmailUtil.getDebugInformation(applicationContext);
            DetUploader.submitReport(applicationContext, new CustomerServiceReport(applicationContext), dtsSessionId);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(final WebView view, final int errorCode, final String description, final String failingUrl) {
            CantileverActivity.this.mAppHealthEventMonitor.customerSupportContacted();
            if (errorCode == -6 || errorCode == -2) {
                CantileverActivity.this.startActivity(new Intent(CantileverActivity.this, (Class<?>) NetworkFailureWithSettingsDialog.class));
                CantileverActivity.this.finish();
                return;
            }
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        @Override // com.amazon.identity.auth.device.api.MAPAndroidWebViewClient, android.webkit.WebViewClient
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            CantileverActivity.this.mAppHealthEventMonitor.customerSupportContacted();
            handler.cancel();
            Log.warning(CantileverActivity.TAG, "SSL Error: " + error);
            WebViewUtil.handleSslError(view.getContext(), view.getSettings());
        }

        @Override // com.amazon.identity.auth.device.api.MAPAndroidWebViewClient, android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (CantileverActivity.this.mIsResume) {
                Log.debug(CantileverActivity.TAG, "Resuming on: " + url);
                CantileverActivity.this.mIsResume = false;
                return true;
            }
            Log.debug(CantileverActivity.TAG, "Url being loaded: " + url);
            if (url.contains("webViewComplete")) {
                Log.debug(CantileverActivity.TAG, "Exit button pressed on Survey thankyou page or url was to amazon root.");
                CantileverActivity.this.finish();
                return true;
            }
            if (url.matches("^(|http|https)://(|[\\w\\.-]+\\.)amazon\\.(com|com.au|com.br|ca|cn|de|es|fr|co.id|in|it|co.jp|com.mx|nl|ru|co.uk)($|/.*)")) {
                CantileverActivity.this.addAmazonAppInfoCookie(url);
            }
            if (url.matches("^https?://[a-zA-Z\\d\\.-]+\\.amazon(\\.com|\\.co\\.jp|\\.co\\.uk|\\.cn)/((ap|csad|message-us|gp/help/survey)).*") || CantileverActivity.this.isSupportedUrlForForum(url)) {
                return false;
            }
            if (url.matches("^(|http|https)://(|[\\w\\.-]+\\.)amazon\\.(com|com.au|com.br|ca|cn|de|es|fr|co.id|in|it|co.jp|com.mx|nl|ru|co.uk)($|/.*)")) {
                return true;
            }
            view.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAmazonAppInfoCookie(String url) {
        if (TextUtils.isEmpty(url)) {
            Log.warning(TAG, "URL to add the app info cookie cannot be empty");
            return;
        }
        CookieManager cookieManager = CookieManager.getInstance();
        String baseUrl = getBaseUrl(url);
        cookieManager.setCookie(baseUrl, getAmazonAppInfo(baseUrl));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSupportedUrlForForum(String url) {
        if (TextUtils.isEmpty(url)) {
            return false;
        }
        return url.matches("^https?://([\\w\\.-]+\\.)?amazonforum\\.com/.*") || url.matches("^https?://[\\w\\.-]+\\.salesforce\\.com/services/authcallback/.*") || (url.matches("^https?://(|[\\w\\.-]+\\.)amazon\\.(com|com.au|com.br|ca|cn|de|es|fr|co.id|in|it|co.jp|com.mx|nl|ru|co.uk)(/hz/help/survey/submit/.*|/inlinehmd.*|/help/.*/forum.*|/gp/redirect\\.html.*|/ap.*|/gp/help/customer/display\\.html.*)") && !url.matches("^https?://music.amazon\\.(com|com.au|com.br|ca|cn|de|es|fr|co.id|in|it|co.jp|com.mx|nl|ru|co.uk)/.*"));
    }

    private void setCantileverCookies(final Context context) {
        AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setCookie(this.mUrl, String.format("appName=%s; Path=/csad", context.getPackageName()));
        cookieManager.setCookie(this.mUrl, String.format("appVersionCode=%s; Path=/csad", getAppVersionCode(context)));
        cookieManager.setCookie(this.mUrl, String.format("osVersion=%s; Path=/csad", Integer.valueOf(Build.VERSION.SDK_INT)));
        if (PlatformUtil.isFireOS()) {
            cookieManager.setCookie(this.mUrl, String.format("osName=%s; Path=/csad", "FireOS"));
        } else {
            cookieManager.setCookie(this.mUrl, String.format("osName=%s; Path=/csad", "Android"));
        }
        cookieManager.setCookie(this.mUrl, String.format("deviceName=%s; Path=/csad", Build.MODEL));
        cookieManager.setCookie(this.mUrl, String.format("deviceType=%s; Path=/csad", accountCredentialStorage.getDeviceType()));
        cookieManager.setCookie(this.mUrl, String.format("deviceSerialNumber=%s; Path=/csad", accountCredentialStorage.getDeviceId()));
        cookieManager.setCookie(this.mUrl, String.format("manufacturer=%s; Path=/csad", Build.MANUFACTURER));
        cookieManager.setCookie(this.mUrl, String.format("connectivity=%s; Path=/csad", getTypeName(context)));
        cookieManager.setCookie(this.mUrl, String.format("appMarketplace=%s; Path=/csad", AccountDetailUtil.get(this).getHomeMarketPlaceId()));
        cookieManager.setCookie(this.mUrl, String.format("appCountry=%s; Path=/csad", AccountDetailUtil.getMusicTerritoryOfResidence()));
        cookieManager.setCookie(this.mUrl, String.format("theme=%s; Path=/csad", "dark"));
        cookieManager.setCookie(this.mUrl, String.format("serviceName=%s; Path=/csad", "MusicCantilever"));
        cookieManager.setCookie(this.mUrl, String.format("clientName=%s; Path=/csad", "CantileverMusicForAndroid"));
        if (AmazonApplication.BETA && !SettingsUtil.getBooleanPref(context, R.string.setting_key_dev_debug_contact_us_pref)) {
            Log.debug(TAG, "Setting isBeta: true");
            cookieManager.setCookie(this.mUrl, String.format("isBeta=%s; Path=/csad", "true"));
        } else {
            Log.debug(TAG, "Setting isBeta: fasle");
            cookieManager.setCookie(this.mUrl, String.format("isBeta=%s; Path=/csad", "false"));
        }
        cookieManager.setCookie(this.mUrl, String.format("isChatSupported=%s; Path=/csad", "true"));
        String musicTerritoryOfResidence = AccountDetailUtil.getMusicTerritoryOfResidence();
        if ((MusicTerritory.AUSTRALIA.equalsIgnoreCase(musicTerritoryOfResidence) || MusicTerritory.NEW_ZEALAND.equalsIgnoreCase(musicTerritoryOfResidence)) && getResources().getConfiguration().locale.toString().equals("en_NZ")) {
            cookieManager.setCookie(this.mUrl, String.format("displayLocale=%s; Path=/csad", "en_AU"));
        } else {
            cookieManager.setCookie(this.mUrl, String.format("displayLocale=%s; Path=/csad", getResources().getConfiguration().locale));
        }
    }

    private static String getTypeName(final Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo == null ? "" : activeNetworkInfo.getTypeName();
    }

    private static String getAppVersionCode(final Context context) {
        int i;
        try {
            i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.warning(TAG, "Package name not found", e);
            i = -1;
        }
        return String.valueOf(i);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, (Class<?>) CantileverActivity.class);
    }
}
