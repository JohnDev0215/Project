package com.amazon.mp3.library.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.dialog.NetworkErrorDialog;
import com.amazon.mp3.library.fragment.AlbumListFragment;
import com.amazon.mp3.playback.PlayStateMutationReason;
import com.amazon.mp3.playback.service.streaming.StreamingBlueMoonErrorHandler;
import com.amazon.mp3.prime.DeviceAuthorizationManager;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UniqueCodeUtil;
import com.amazon.mp3.util.WebViewMgmt;
import com.amazon.mp3.util.WebViewUtil;
import com.amazon.mp3.view.MusicSourceToggle;
import com.amazon.music.metrics.mts.event.types.StorePageType;

/* JADX INFO: loaded from: classes3.dex */
public class LibraryDeviceAuthorizationWebViewActivity extends BaseActivity {
    public static final int REQUEST_CODE = UniqueCodeUtil.nextUniqueCode();
    private ActionBar mActionBar;
    private WebViewMgmt mWebViewMgmt;
    private WebViewUtil.WebViewProgressBar mWebViewProgressBar;
    private boolean mReturnToPrevious = false;
    private boolean mFromPlayback = false;
    private final WebViewMgmt.OnWebViewtMgmt mOnWebViewMgmt = new WebViewManager();

    /* JADX INFO: Access modifiers changed from: private */
    public int getWebViewId() {
        return R.id.DeviceAuthorizationWebView;
    }

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, (Class<?>) LibraryDeviceAuthorizationWebViewActivity.class);
        intent.putExtra("com.amazon.mp3.library.EXTRA_SOURCE_ID", "cirrus");
        return intent;
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        Bundle extras;
        Log.debug("BlueMoonLibraryDeviceAuthorizationWebViewActivity", "onCreate");
        super.onCreate(savedInstanceState);
        StreamingBlueMoonErrorHandler.hideNotification(getApplicationContext());
        this.mActionBar = getSupportActionBar();
        if (!ConnectivityUtil.hasAnyInternetConnection()) {
            showFragmentDialog(0, NetworkErrorDialog.create(this, createNetworkDialogCallback()));
        }
        View viewInflate = getLayoutInflater().inflate(R.layout.action_bar_bluemoon_view, (ViewGroup) null, false);
        MusicSourceToggle musicSourceToggle = (MusicSourceToggle) viewInflate.findViewById(R.id.SourceToggle);
        if (getIntent().getBooleanExtra("com.amazon.mp3.library.EXTRA_FROM_PRIME", false)) {
            ((TextView) viewInflate.findViewById(R.id.title_text)).setText(R.string.dmusic_main_launcher_name);
            musicSourceToggle.setVisibility(8);
        }
        setContentView(R.layout.library_device_authorization_webview_activity);
        createWebViewMgmt();
        WebViewMgmt webViewMgmt = this.mWebViewMgmt;
        if (webViewMgmt != null) {
            webViewMgmt.onCreate(savedInstanceState);
        }
        Intent intent = getIntent();
        if (intent != null && (extras = intent.getExtras()) != null) {
            this.mReturnToPrevious = extras.getBoolean("com.amazon.mp3.util.RETURN_TO_PREVIOUS", false);
            this.mFromPlayback = extras.getBoolean("com.amazon.mp3.util.FROM_PLAYBACK", false);
        }
        MusicSourceToggle.SourceChangedListener sourceChangedListener = new MusicSourceToggle.SourceChangedListener() { // from class: com.amazon.mp3.library.activity.LibraryDeviceAuthorizationWebViewActivity.1
            @Override // com.amazon.mp3.view.MusicSourceToggle.SourceChangedListener
            public void onSourceToggled(final MusicSourceToggle.Source source) {
                Intent startIntent = MusicHomeActivity.getStartIntent(LibraryDeviceAuthorizationWebViewActivity.this);
                startIntent.putExtra("com.amazon.mp3.fragment.extra", AlbumListFragment.class.getSimpleName());
                startIntent.putExtra("com.amazon.mp3.library.EXTRA_SOURCE_ID", "cirrus-local");
                LibraryDeviceAuthorizationWebViewActivity.this.finish();
                LibraryDeviceAuthorizationWebViewActivity.this.startActivity(startIntent);
            }
        };
        musicSourceToggle.setSource(MusicSourceToggle.Source.CLOUD);
        musicSourceToggle.setOnSourceChangedListener(sourceChangedListener);
        if (getApplicationContext().getPackageName().equals("com.amazon.mp3.automotiveOS")) {
            removeHeaderView();
        } else {
            setHeaderView(viewInflate);
        }
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return getResources().getAssets();
    }

    @Override // com.amazon.mp3.activity.BaseActivity, android.app.Activity, android.content.ComponentCallbacks2
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        WebViewMgmt webViewMgmt = this.mWebViewMgmt;
        if (webViewMgmt != null) {
            webViewMgmt.onTrimMemory(level);
        }
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        WebViewMgmt webViewMgmt = this.mWebViewMgmt;
        if (webViewMgmt != null) {
            webViewMgmt.onResume();
        }
        if (AccountCredentialUtil.get().isSignedOut()) {
            finish();
        } else {
            MetricsLogger.webViewStart(StorePageType.BM_DEVICE_AUTH);
        }
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        hideProgressBarDialog();
        super.onPause();
        WebViewMgmt webViewMgmt = this.mWebViewMgmt;
        if (webViewMgmt != null) {
            webViewMgmt.onPause();
        }
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        removeHeaderView();
        this.mWebViewMgmt = null;
        super.onDestroy();
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, android.app.Activity
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        this.mWebViewMgmt.onPostCreate(savedInstanceState);
    }

    @Override // com.amazon.mp3.activity.BaseActivity, android.app.Activity
    protected Dialog onCreateDialog(int id) {
        if (WebViewUtil.isWebViewError(id) && ConnectivityUtil.hasAnyInternetConnection()) {
            return WebViewUtil.createDeviceAuthorizationErrorDialog(this, id);
        }
        return super.onCreateDialog(id);
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

    private void setHeaderView(View customView) {
        if (customView == null) {
            return;
        }
        this.mActionBar.setDisplayShowCustomEnabled(true);
        this.mActionBar.setCustomView(customView);
    }

    private void removeHeaderView() {
        this.mActionBar.setDisplayShowCustomEnabled(false);
    }

    private void createWebViewMgmt() {
        this.mWebViewMgmt = new WebViewMgmt(this.mOnWebViewMgmt, this);
    }

    class WebViewManager implements WebViewMgmt.OnWebViewtMgmt {
        @Override // com.amazon.mp3.util.WebViewMgmt.OnWebViewtMgmt
        public void onAboutToLoadUrl() {
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
        public void onStopGenerateNonceJob() {
        }

        WebViewManager() {
        }

        @Override // com.amazon.mp3.util.WebViewMgmt.OnWebViewtMgmt
        public boolean onPageFinishedResultCancel() {
            LibraryDeviceAuthorizationWebViewActivity.this.setResult(0);
            LibraryDeviceAuthorizationWebViewActivity.this.finish();
            return true;
        }

        @Override // com.amazon.mp3.util.WebViewMgmt.OnWebViewtMgmt
        public void onPageFinished(WebView view, String url) {
            MetricsLogger.webViewFinish(StorePageType.BM_DEVICE_AUTH);
            LibraryDeviceAuthorizationWebViewActivity.this.hideProgressBarDialog();
        }

        @Override // com.amazon.mp3.util.WebViewMgmt.OnWebViewtMgmt
        public int onGetWebViewId() {
            return LibraryDeviceAuthorizationWebViewActivity.this.getWebViewId();
        }

        @Override // com.amazon.mp3.util.WebViewMgmt.OnWebViewtMgmt
        public String onGetUrl() {
            return LibraryDeviceAuthorizationWebViewActivity.this.retrieveURL();
        }

        @Override // com.amazon.mp3.util.WebViewMgmt.OnWebViewtMgmt
        public long onAddJob(Job job) {
            return LibraryDeviceAuthorizationWebViewActivity.this.addJob(job);
        }

        @Override // com.amazon.mp3.util.WebViewMgmt.OnWebViewtMgmt
        public void onStartGenerateNonceJob() {
            LibraryDeviceAuthorizationWebViewActivity.this.showProgressBarDialog();
        }

        @Override // com.amazon.mp3.util.WebViewMgmt.OnWebViewtMgmt
        public void onStartAccountDetailsJob() {
            LibraryDeviceAuthorizationWebViewActivity.this.showProgressBarDialog();
        }

        @Override // com.amazon.mp3.util.WebViewMgmt.OnWebViewtMgmt
        public void onStopAccountDetailsJob() {
            LibraryDeviceAuthorizationWebViewActivity libraryDeviceAuthorizationWebViewActivity = LibraryDeviceAuthorizationWebViewActivity.this;
            if (libraryDeviceAuthorizationWebViewActivity.mFromPlayback) {
                if (DeviceAuthorizationManager.getInstance().isAuthorized()) {
                    libraryDeviceAuthorizationWebViewActivity.sendBroadcast(new Intent("com.amazon.mp3.playerservicecommand.togglepause"));
                    return;
                }
                Intent intent = new Intent("com.amazon.mp3.playerservicecommand.stop");
                intent.putExtra("com.amazon.mp3.playstate_mutation_reason", PlayStateMutationReason.DEVICE_NOT_AUTHORIZED);
                libraryDeviceAuthorizationWebViewActivity.sendBroadcast(intent);
            }
        }

        @Override // com.amazon.mp3.util.WebViewMgmt.OnWebViewtMgmt
        public boolean onFinishActivity() {
            if (DeviceAuthorizationManager.getInstance().cannotTransferDevice() || DeviceAuthorizationManager.getInstance().deviceTransferLimitReached() || !LibraryDeviceAuthorizationWebViewActivity.this.mReturnToPrevious) {
                return true;
            }
            LibraryDeviceAuthorizationWebViewActivity.this.finish();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String retrieveURL() {
        return WebViewUtil.retrieveDeviceAuthorizationURL(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showProgressBarDialog() {
        if (ConnectivityUtil.hasAnyInternetConnection()) {
            if (this.mWebViewProgressBar == null) {
                this.mWebViewProgressBar = WebViewUtil.createWebViewProgressBar(this);
            }
            this.mWebViewProgressBar.showProgressBar();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideProgressBarDialog() {
        WebViewUtil.WebViewProgressBar webViewProgressBar = this.mWebViewProgressBar;
        if (webViewProgressBar != null) {
            webViewProgressBar.dismissProgressBar();
        }
    }

    private NetworkErrorDialog.INetworkErrorDialogHandler createNetworkDialogCallback() {
        return new NetworkErrorDialog.INetworkErrorDialogHandler() { // from class: com.amazon.mp3.library.activity.LibraryDeviceAuthorizationWebViewActivity.2
            @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
            public void onRetryConnection() {
                Intent intent = LibraryDeviceAuthorizationWebViewActivity.this.getIntent();
                LibraryDeviceAuthorizationWebViewActivity.this.finish();
                LibraryDeviceAuthorizationWebViewActivity.this.startActivity(intent);
            }

            @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
            public void onDialogDismiss() {
                LibraryDeviceAuthorizationWebViewActivity.this.finish();
            }

            @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
            public void onConnectionErrorDialogCancel() {
                LibraryDeviceAuthorizationWebViewActivity.this.finish();
            }
        };
    }
}
