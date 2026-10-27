package com.amazon.mp3.library.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.playback.service.streaming.StreamingBlueMoonErrorHandler;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.UniqueCodeUtil;
import com.amazon.mp3.util.WebViewMgmt;
import com.amazon.mp3.util.WebViewUtil;
import com.amazon.music.metrics.mts.event.types.StorePageType;

/* JADX INFO: loaded from: classes3.dex */
public class BaseAccountValidationWebView extends BaseActivity {
    public static final int RESULT_NETWORK_ERROR = UniqueCodeUtil.nextUniqueCode() * (-1);
    private final WebViewMgmt.OnWebViewtMgmt mOnWebViewMgmt = new WebViewManager();
    private WebViewMgmt mWebViewMgmt;

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    @Override // android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        return false;
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, (Class<?>) BaseAccountValidationWebView.class);
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library_account_validate_webview_activity);
        WebViewMgmt webViewMgmt = new WebViewMgmt(this.mOnWebViewMgmt, this);
        this.mWebViewMgmt = webViewMgmt;
        webViewMgmt.onCreate(savedInstanceState);
        StreamingBlueMoonErrorHandler.hideNotification(getApplicationContext());
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, android.app.Activity
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        this.mWebViewMgmt.onPostCreate(savedInstanceState);
    }

    @Override // com.amazon.mp3.activity.BaseActivity, android.app.Activity
    protected Dialog onCreateDialog(int id) {
        if (WebViewUtil.isWebViewError(id)) {
            return WebViewUtil.createAccountValidationErrorDialog(this, WebViewUtil.FlowType.LIBRARY, id);
        }
        return super.onCreateDialog(id);
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        WebViewMgmt webViewMgmt = this.mWebViewMgmt;
        if (webViewMgmt != null) {
            webViewMgmt.onResume();
        }
        MetricsLogger.webViewStart(StorePageType.BM_ACCOUNT_VALIDATION);
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        WebViewMgmt webViewMgmt = this.mWebViewMgmt;
        if (webViewMgmt != null) {
            webViewMgmt.onPause();
        }
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        this.mWebViewMgmt = null;
        super.onDestroy();
    }

    @Override // com.amazon.mp3.activity.BaseActivity, android.app.Activity, android.content.ComponentCallbacks2
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        WebViewMgmt webViewMgmt = this.mWebViewMgmt;
        if (webViewMgmt != null) {
            webViewMgmt.onTrimMemory(level);
        }
    }

    @Override // com.amazon.mp3.service.job.JobSessionActivity
    protected void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
        super.onJobFinished(jobId, job, resultCode, resultBundle);
        this.mWebViewMgmt.onJobFinished(jobId, job, resultCode, resultBundle);
    }

    @Override // com.amazon.mp3.service.job.JobSessionActivity
    protected void onSessionConnected(long sessionId) {
        super.onSessionConnected(sessionId);
        this.mWebViewMgmt.onSessionConnected(sessionId);
    }

    class WebViewManager implements WebViewMgmt.OnWebViewtMgmt {
        @Override // com.amazon.mp3.util.WebViewMgmt.OnWebViewtMgmt
        public boolean onFinishActivity() {
            return true;
        }

        @Override // com.amazon.mp3.util.WebViewMgmt.OnWebViewtMgmt
        public int onGetWebViewId() {
            return R.id.AccountValidateWebView;
        }

        @Override // com.amazon.mp3.util.WebViewMgmt.OnWebViewtMgmt
        public void onPageFinishedResultOk() {
        }

        @Override // com.amazon.mp3.util.WebViewMgmt.OnWebViewtMgmt
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
        }

        @Override // com.amazon.mp3.util.WebViewMgmt.OnWebViewtMgmt
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        }

        @Override // com.amazon.mp3.util.WebViewMgmt.OnWebViewtMgmt
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        }

        @Override // com.amazon.mp3.util.WebViewMgmt.OnWebViewtMgmt
        public void onStartGenerateNonceJob() {
        }

        @Override // com.amazon.mp3.util.WebViewMgmt.OnWebViewtMgmt
        public void onStopGenerateNonceJob() {
        }

        WebViewManager() {
        }

        @Override // com.amazon.mp3.util.WebViewMgmt.OnWebViewtMgmt
        public boolean onPageFinishedResultCancel() {
            BaseAccountValidationWebView.this.setResult(0);
            BaseAccountValidationWebView.this.finish();
            return true;
        }

        @Override // com.amazon.mp3.util.WebViewMgmt.OnWebViewtMgmt
        public void onPageFinished(WebView view, String url) {
            BaseAccountValidationWebView.this.setSpinnerVisibile(false);
            MetricsLogger.webViewFinish(StorePageType.BM_ACCOUNT_VALIDATION);
        }

        @Override // com.amazon.mp3.util.WebViewMgmt.OnWebViewtMgmt
        public String onGetUrl() {
            return WebViewUtil.retrieveAccountValidationURL(BaseAccountValidationWebView.this, WebViewUtil.FlowType.LIBRARY, false);
        }

        @Override // com.amazon.mp3.util.WebViewMgmt.OnWebViewtMgmt
        public long onAddJob(Job job) {
            return BaseAccountValidationWebView.this.addJob(job);
        }

        @Override // com.amazon.mp3.util.WebViewMgmt.OnWebViewtMgmt
        public void onAboutToLoadUrl() {
            BaseAccountValidationWebView.this.setSpinnerVisibile(true);
        }

        @Override // com.amazon.mp3.util.WebViewMgmt.OnWebViewtMgmt
        public void onStartAccountDetailsJob() {
            BaseAccountValidationWebView.this.setSpinnerVisibile(true);
        }

        @Override // com.amazon.mp3.util.WebViewMgmt.OnWebViewtMgmt
        public void onStopAccountDetailsJob() {
            BaseAccountValidationWebView.this.setSpinnerVisibile(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSpinnerVisibile(final boolean visible) {
        View view = (View) findViewById(R.id.progress).getParent();
        if (visible) {
            view.setVisibility(0);
        } else {
            view.setVisibility(8);
        }
    }
}
