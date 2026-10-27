package com.byd.auto_emanual.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.byd.auto_emanual.ExecutorManager;
import com.byd.auto_emanual.MApp;
import com.byd.auto_emanual.R;
import com.byd.auto_emanual.js.DiLinkDev;
import com.byd.auto_emanual.util.AutoInfoUtil;
import com.byd.auto_emanual.util.MLog;
import com.byd.auto_emanual.util.ResourceUtil;
import com.byd.auto_emanual.util.SharedPrefUtil;
import com.byd.auto_emanual.util.Utils;
import com.byd.auto_emanual.util.ViewUtil;
import com.byd.auto_emanual.util.WebSettingsUtil;
import java.io.File;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class ManualFragment extends BaseFragment {
    public static final String LOCAL_FILE_PREFIX = "file://";
    private static final String TAG = "ManualFragment";
    public static final String ZIP_IN_TYPE = "zipInType";
    public static final String ZIP_IN_TYPE_DIVISION = ",";
    private String basePath;
    private Locale locale;
    private int orientation;
    private PageData pageData;
    private View view;
    private WebSettings webSettings;
    private WebView webView;
    private final DiLinkDev deLinkDev = new DiLinkDev();
    private final String HTTP_FILE_PREFIX = "http";
    private String homeHtml = "/home.html";

    static /* synthetic */ boolean lambda$initWebView$0(View view) {
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.manual_fragment, container, false);
        this.orientation = getResources().getConfiguration().orientation;
        initWebView(this.view);
        initNetState();
        this.locale = getResources().getConfiguration().getLocales().get(0);
        return this.view;
    }

    private void initNetState() {
        this.netErrorState = (RelativeLayout) this.view.findViewById(R.id.net_error_state);
        this.netLoadingState = (RelativeLayout) this.view.findViewById(R.id.net_loading_state);
        updateNetState();
    }

    private void initWebView(View view) {
        WebView webView = (WebView) view.findViewById(R.id.web_view);
        this.webView = webView;
        WebSettings settings = webView.getSettings();
        this.webSettings = settings;
        settings.setBlockNetworkImage(false);
        this.webSettings.setLoadsImagesAutomatically(true);
        this.webSettings.setDefaultTextEncodingName("utf-8");
        this.webSettings.setAllowFileAccess(true);
        this.webSettings.setAllowUniversalAccessFromFileURLs(false);
        this.webSettings.setAllowFileAccessFromFileURLs(false);
        this.webSettings.setJavaScriptEnabled(true);
        this.webView.setBackgroundColor(0);
        this.webView.setOverScrollMode(2);
        this.webView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.byd.auto_emanual.fragment.-$$Lambda$ManualFragment$1vEu5RdElqTd3Xd4ReVvg3uKthQ
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view2) {
                return ManualFragment.lambda$initWebView$0(view2);
            }
        });
        this.webView.addJavascriptInterface(this.deLinkDev, DiLinkDev.JS_INTERFACE_NAME);
        this.webView.setWebViewClient(new WebViewClient() { // from class: com.byd.auto_emanual.fragment.ManualFragment.1
            String tmpUrl;

            @Override // android.webkit.WebViewClient
            public void onLoadResource(WebView view2, String url) {
                MLog.d(ManualFragment.TAG, "onLoadResource basePath = " + ManualFragment.this.basePath + ";;; url = " + url);
                this.tmpUrl = url;
                if (ManualFragment.this.basePath != null && url != null && ManualFragment.this.basePath.startsWith(ManualFragment.LOCAL_FILE_PREFIX) && url.startsWith("http")) {
                    url = url.replace(ManualFragment.this.pageData.getZipInfo().getServerFilepath(), ManualFragment.this.basePath);
                }
                super.onLoadResource(view2, url);
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView view2, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view2, request, error);
                MLog.e(ManualFragment.TAG, "onReceivedError url = " + view2.getUrl() + ", request.getUrl() = " + request.getUrl() + ", error = " + error.getErrorCode() + " " + ((Object) error.getDescription()));
                if (ManualFragment.this.pageData != null && view2.getUrl() != null && ManualFragment.this.pageData.getZipInfo() != null && ManualFragment.this.pageData.getZipInfo().getTargetFile() != null && view2.getUrl().startsWith(ManualFragment.LOCAL_FILE_PREFIX)) {
                    String str = ManualFragment.this.pageData.getZipInfo().getTargetFile() + ManualFragment.this.homeHtml;
                    if (!new File(str).exists()) {
                        MLog.e(ManualFragment.TAG, "homeFilePath : " + str + " not exists.");
                        if (ExecutorManager.getManualRunnable() != null) {
                            ExecutorManager.getManualRunnable().deleteIfExists(ManualFragment.this.pageData);
                        }
                        ManualFragment.this.basePath = null;
                    }
                }
                onLoadResource(view2, this.tmpUrl);
            }
        });
        updateWebViewByConfig(getResources().getConfiguration());
    }

    public void loadUrlFromSearch(String url) {
        MLog.v(TAG, "loadUrlFromSearch " + url + ", " + this.webView);
        if (this.webView == null || TextUtils.isEmpty(url)) {
            return;
        }
        this.webView.loadUrl("javascript:setHref('" + url + "')");
    }

    @Override // com.byd.auto_emanual.fragment.BaseFragment
    public void hideNetLoading() {
        super.hideNetLoading();
    }

    @Override // com.byd.auto_emanual.fragment.BaseFragment, com.byd.auto_emanual.fragment.IView
    public void loadData() {
        super.loadData();
        this.presenter.loadData(Page.MANUAL);
        loadJsonData();
    }

    private void loadJsonData() {
        new Presenter(null).loadData(Page.JSON_DATA);
    }

    private void loadJsonDataByConfig() {
        ExecutorManager.removeJsonTask();
        loadJsonData();
    }

    @Override // com.byd.auto_emanual.fragment.BaseFragment, com.byd.auto_emanual.fragment.IView
    public void showError() {
        if (this.basePath != null || (this.webView.getUrl() != null && !this.webView.getUrl().equals("about:blank"))) {
            hideNetLoading();
            Toast.makeText(this.context, R.string.state_net_error, 0).show();
        } else {
            super.showError();
        }
        MLog.i(TAG, "showError");
    }

    @Override // com.byd.auto_emanual.fragment.BaseFragment, com.byd.auto_emanual.fragment.IView
    public void showData(PageData pageData) {
        super.showData(pageData);
        this.pageData = pageData;
        if (TextUtils.isEmpty(pageData.getZipInfo().getTargetFile())) {
            this.basePath = this.pageData.getZipInfo().getServerFilepath();
        } else {
            this.basePath = LOCAL_FILE_PREFIX + this.pageData.getZipInfo().getTargetFile();
        }
        String str = this.basePath + this.homeHtml;
        String url = this.webView.getUrl();
        String str2 = TAG;
        MLog.d(str2, "showData \n url = " + url + "\n path = " + str);
        WebSettingsUtil.updateAccessFromFileURLs(MApp.context, this.webSettings, str, pageData.getZipInfo().getServerFilepath());
        if (TextUtils.isEmpty(url) || "about:blank".equals(url)) {
            if (this.basePath != null) {
                this.webView.loadUrl(str);
            }
            MLog.d(str2, "showData 1");
        } else if (!str.equals(url) && url.startsWith(LOCAL_FILE_PREFIX) && !str.startsWith(LOCAL_FILE_PREFIX)) {
            this.webView.loadUrl(str);
            MLog.d(str2, "showData 2");
        } else {
            MLog.d(str2, "showData 3");
        }
        MLog.i(str2, "zipInType " + (SharedPrefUtil.load(this.context, ZIP_IN_TYPE) + " : " + AutoInfoUtil.getAutoType(this.context)));
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Locale locale = newConfig.getLocales().get(0);
        if (!locale.equals(this.locale)) {
            this.locale = locale;
        }
        updateWebViewByConfig(newConfig);
        updateNetState();
    }

    private void updateWebViewByConfig(Configuration newConfig) {
        int i = newConfig.orientation;
        this.orientation = i;
        this.deLinkDev.setOrientation(i);
        this.webView.loadUrl("javascript:setOrientation(" + this.orientation + ")");
        int i2 = (newConfig.uiMode & 48) == 32 ? 1 : 0;
        this.deLinkDev.setTheme(i2);
        this.webView.loadUrl("javascript:setTheme(" + i2 + ")");
        String standardLanguageCode = Utils.getStandardLanguageCode();
        this.deLinkDev.setLanguage(standardLanguageCode);
        this.webView.loadUrl("javascript:setLanguage(" + standardLanguageCode + ")");
        MLog.d(TAG, "updateWebViewByConfig orientation = " + this.orientation + ", theme = " + i2 + ", language = " + standardLanguageCode);
    }

    public void updateNetState() {
        int dimensionPixelOffset;
        int dimensionPixelOffset2;
        TextView textView = (TextView) this.view.findViewById(R.id.error_text);
        textView.setText(R.string.state_net_error);
        textView.setTextColor(ResourceUtil.getColor(this.context, R.attr.state_text));
        ((ImageView) this.view.findViewById(R.id.error_icon)).setImageDrawable(ResourceUtil.getDrawable(this.context, R.attr.state_net_error));
        TextView textView2 = (TextView) this.view.findViewById(R.id.loading_text);
        textView2.setText(R.string.state_loading);
        textView2.setTextColor(ResourceUtil.getColor(this.context, R.attr.state_text));
        this.loadingIcon = (ImageView) this.view.findViewById(R.id.loading_icon);
        this.loadingIcon.setImageDrawable(ResourceUtil.getDrawable(this.context, R.attr.loading_54));
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.netErrorState.getLayoutParams();
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.netLoadingState.getLayoutParams();
        if (ViewUtil.isPort(this.orientation)) {
            dimensionPixelOffset = this.context.getResources().getDimensionPixelOffset(R.dimen.state_net_error_top_port);
            dimensionPixelOffset2 = this.context.getResources().getDimensionPixelOffset(R.dimen.state_loading_top_port);
        } else {
            dimensionPixelOffset = this.context.getResources().getDimensionPixelOffset(R.dimen.state_net_error_top);
            dimensionPixelOffset2 = this.context.getResources().getDimensionPixelOffset(R.dimen.state_loading_top);
        }
        layoutParams.setMargins(layoutParams.leftMargin, dimensionPixelOffset, layoutParams.rightMargin, layoutParams.bottomMargin);
        layoutParams2.setMargins(layoutParams2.leftMargin, dimensionPixelOffset2, layoutParams2.rightMargin, layoutParams2.bottomMargin);
    }

    public static String zipRequireDownloadKey() {
        return "require_download_" + Utils.getCurrentLanguage();
    }

    public static String zipRequireDownloadSizeKey() {
        return "require_download_size_" + Utils.getCurrentLanguage();
    }

    public static String zipRequireDownloadResourceLanguage() {
        return "require_download_resource_language_" + Utils.getCurrentLanguage();
    }

    public static String zipRequireDownloadMultiResourceLanguage() {
        return "require_download_multi_resource_language_" + Utils.getCurrentLanguage();
    }

    public static String zipRequireUpdateKey() {
        return "require_update_" + Utils.getCurrentLanguage();
    }
}
