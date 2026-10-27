package com.amazon.mp3.store.html5.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.activity.SignInFlowLauncherActivity;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.actionbar.StoreMenuVisibilityListener;
import com.amazon.mp3.actionbar.view.ActionBarStoreTitleView;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.bottomnavbar.BottomNavigationBarContainer;
import com.amazon.mp3.capability.DevModeCapabilities;
import com.amazon.mp3.download.service.AutoDownloadPurchaseReceiver;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.item.ItemWrapper$ItemType;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.prime.DeviceAuthorizationManager;
import com.amazon.mp3.service.metrics.BaseMetricsRecorder;
import com.amazon.mp3.service.metrics.FirstTimeUseMetrics;
import com.amazon.mp3.service.metrics.IMetricsRecorder;
import com.amazon.mp3.service.metrics.LoginMetrics;
import com.amazon.mp3.store.fragment.StoreBaseFragment;
import com.amazon.mp3.store.html5.bridge.Message;
import com.amazon.mp3.store.html5.bridge.MessageType;
import com.amazon.mp3.store.html5.util.StoreUrls;
import com.amazon.mp3.store.html5.util.StoreUtil;
import com.amazon.mp3.store.html5.webview.StoreWebView;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.StringUtil;
import com.amazon.mp3.util.UniqueCodeUtil;
import com.amazon.mp3.util.WebViewUtil;
import com.amazon.music.validator.ValidatorRegistry;

/* JADX INFO: loaded from: classes4.dex */
public class StoreFragment extends StoreBaseFragment {
    private boolean mClearingCacheAndReloading;
    private StoreFragmentMessageHandler mMessageHandler;
    protected BaseActivity mParentActivity;
    private String mShowDetailPageAsin;
    private Message mSignInMessage;
    private ActionBarStoreTitleView mStoreTitleView;
    private FrameLayout mWebContainer;
    private StoreWebView mWebView;
    private StoreFragmentWebViewClient mWebViewClient;
    private static final String TAG = StoreFragment.class.getSimpleName();
    private static final int DEVICE_AUTHORIZATION_FLOW_CODE = UniqueCodeUtil.nextUniqueCode();
    private static final DevModeCapabilities sDevModeCapabilities = AmazonApplication.getDevModeCapabilities();
    private static final int STORE_NETWORK_ERROR_ID = UniqueCodeUtil.nextUniqueCode();
    private static final BroadcastReceiver sPlaybackReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.store.html5.activity.StoreFragment.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(final Context context, final Intent intent) {
            if ("action_stop".equals(intent.getAction())) {
                StoreUtil.requestAudioFocusFromStore();
            }
        }
    };
    private static final BroadcastReceiver sConnectivityReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.store.html5.activity.StoreFragment.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(final Context context, final Intent intent) {
            if (intent != null) {
                boolean z = !intent.getBooleanExtra("noConnectivity", false);
                String str = StoreFragment.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("connectivity has changed, network is: ");
                sb.append(z ? "up" : "down");
                Log.verbose(str, sb.toString());
                if (StoreWebView.instanceCreated()) {
                    StoreWebView.getInstance(context).setNetworkAvailable(z);
                }
            }
        }
    };
    private boolean mHasConnectionError = false;
    private boolean mSearchActive = false;
    private ItemWrapper$ItemType mShowDetailPageItemType = ItemWrapper$ItemType.SONG;
    private final BroadcastReceiver mCredentialsChangedReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.store.html5.activity.StoreFragment.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(final Context context, final Intent intent) {
            StoreWebView storeWebView = StoreFragment.this.mWebView;
            StoreFragmentMessageHandler storeFragmentMessageHandler = StoreFragment.this.mMessageHandler;
            if (storeWebView == null || storeFragmentMessageHandler == null || storeWebView.getState() != StoreWebView.State.Loaded) {
                return;
            }
            storeFragmentMessageHandler.updateCredentials();
        }
    };

    @Override // com.amazon.mp3.store.fragment.StoreBaseFragment
    protected long getRetryThrottleDuration() {
        return 10000L;
    }

    public static StoreFragment newInstance(Bundle bundle) {
        StoreFragment storeFragment = new StoreFragment();
        storeFragment.setArguments(bundle);
        return storeFragment;
    }

    @Override // com.amazon.mp3.store.fragment.StoreBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mParentActivity = (BaseActivity) getActivity();
        StoreUrls.setReftag(getIntent().getStringExtra("com.amazon.mp3.extra.REFTAG"));
        registerReceiver(this.mCredentialsChangedReceiver, new IntentFilter("com.amazon.mp3.account.credentials.ACTION_NEW_CREDENTIALS"));
        this.mWebViewClient = new StoreFragmentWebViewClient(this);
        this.mMessageHandler = new StoreFragmentMessageHandler(this, this.mWebViewClient);
        registerReceiver(sConnectivityReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        registerReceiver(sPlaybackReceiver, new IntentFilter("action_stop"));
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.store_html5_host_activity, (ViewGroup) null);
        FragmentActivity activity = getActivity();
        ActionBarStoreTitleView actionBarStoreTitleView = new ActionBarStoreTitleView(activity, activity.getComponentName());
        this.mStoreTitleView = actionBarStoreTitleView;
        actionBarStoreTitleView.addStoreMenuVisibilityListener(new StoreMenuVisibilityListener() { // from class: com.amazon.mp3.store.html5.activity.StoreFragment.1
            @Override // com.amazon.mp3.actionbar.StoreMenuVisibilityListener
            public void toggleStoreMenuVisibility(final boolean isVisible) {
                StoreUtil.broadcastRefineMenuVisibility(StoreFragment.this.mParentActivity, isVisible);
            }
        });
        if (AmazonApplication.getDevModeCapabilities().isStoreDebugModeEnabled()) {
            setHasOptionsMenu(true);
        }
        return viewInflate;
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        attachWebView();
        Bundle arguments = getArguments();
        this.mStoreTitleView.setTitle(arguments.getString("com.amazon.mp3.store.TITLE"));
        this.mStoreTitleView.setSubtitle(null);
        this.mStoreTitleView.toggleStoreMenuVisibility(false);
        this.mStoreTitleView.setRefineMenuAvailable(false);
        StoreUtil.navigateFromBundle(this.mParentActivity, arguments);
    }

    protected Intent getIntent() {
        return this.mParentActivity.getIntent();
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        StoreUtil.broadcastStoreHostVisibilityChanged(this.mParentActivity, false);
        detachWebView();
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (!ConnectivityUtil.hasAnyInternetConnection()) {
            StoreWebView storeWebView = this.mWebView;
            if (storeWebView != null) {
                storeWebView.loadBlankAndClearHistory();
            }
            showConnectionErrorDialog();
        }
        new StoreAccessValidationAndLoadWebViewAsyncTask().execute(new Void[0]);
        StoreUtil.broadcastStoreHostVisibilityChanged(this.mParentActivity, true);
        BottomNavigationBarContainer bottomNavigationBarContainer = BottomNavigationBarContainer.getInstance();
        if (bottomNavigationBarContainer.isEnabled()) {
            bottomNavigationBarContainer.setBottomBarTab(LastViewedScreenUtil.LastViewedScreen.STORE);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.mActionBarProvider.setHeaderView(this.mStoreTitleView);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        this.mActionBarProvider.removeHeaderView();
        setLeftButtonForStore();
        super.onStop();
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.mCredentialsChangedReceiver);
        unregisterReceiver(sConnectivityReceiver);
        unregisterReceiver(sPlaybackReceiver);
        this.mWebViewClient.clearBridgeTimeout();
        this.mMessageHandler = null;
        this.mWebViewClient = null;
        StoreWebView.destroyWebViewIfNotAttached();
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        if (StoreWebView.instanceCreated()) {
            StoreWebView.getInstance(this.mParentActivity).clearView();
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_debug_store, menu);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPrepareOptionsMenu(final Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(final MenuItem item) {
        super.onOptionsItemSelected(item);
        FragmentActivity activity = getActivity();
        switch (item.getItemId()) {
            case R.id.MenuStoreDebugReload /* 2131427646 */:
                clearCacheAndReloadHome();
                break;
            case R.id.MenuStoreDebugUrl /* 2131427647 */:
                DebugDialogs.showUrlDialog(activity, StoreWebView.getInstance(activity).getUrl());
                break;
        }
        return true;
    }

    @Override // com.amazon.mp3.fragment.BaseFragment
    public boolean onSearchRequested() {
        this.mStoreTitleView.onSearchRequested();
        return true;
    }

    @Override // com.amazon.mp3.store.fragment.StoreBaseFragment, com.amazon.mp3.store.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
    public void onConnectionErrorDialogCancel() {
        startActivity(LibraryActivityFactory.getLibraryHomeStartIntent(this.mParentActivity, null));
    }

    @Override // com.amazon.mp3.store.fragment.StoreBaseFragment, com.amazon.mp3.store.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
    public void onRetryConnection() {
        if (!ConnectivityUtil.hasAnyInternetConnection()) {
            showConnectionErrorDialog();
            return;
        }
        StoreWebView storeWebView = this.mWebView;
        if (storeWebView != null) {
            storeWebView.loadUrlAndClearHistory(StoreUrls.getBaseUrl(), true);
        }
    }

    @Override // com.amazon.mp3.fragment.BaseFragment
    public boolean onBackPressed() {
        StoreWebView storeWebView = this.mWebView;
        boolean z = true;
        if (storeWebView == null) {
            z = false;
        } else if (this.mSearchActive) {
            storeWebView.getJavascriptBridge().broadcast(MessageType.EndSearch.getName());
            this.mSearchActive = false;
        } else if (storeWebView.canGoBack()) {
            this.mWebView.goBack();
        } else {
            this.mWebView.loadUrlAndClearHistory(StoreUrls.getBaseUrl());
            z = false;
        }
        return !z ? super.onBackPressed() : z;
    }

    public void onTrimMemory(int level) {
        if (this.mWebView != null) {
            Log.info(TAG, "On trim memory called %d", Integer.valueOf(level));
            this.mWebView.freeMemory();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        StoreFragmentMessageHandler storeFragmentMessageHandler;
        Message message;
        if (requestCode == DEVICE_AUTHORIZATION_FLOW_CODE) {
            processDeviceAuthorizationFlowCode(this.mParentActivity, this.mShowDetailPageItemType == ItemWrapper$ItemType.ALBUM, this.mShowDetailPageAsin);
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != SignInFlowLauncherActivity.REQUEST_CODE || (storeFragmentMessageHandler = this.mMessageHandler) == null || (message = this.mSignInMessage) == null) {
            return;
        }
        storeFragmentMessageHandler.respondToSignInRequest(message, resultCode != 0);
    }

    @Override // com.amazon.mp3.store.fragment.StoreBaseFragment
    public void showConnectionErrorDialog() {
        Log.debug(TAG, "stack for connection error dialog", new Throwable());
        this.mHasConnectionError = true;
        StoreWebView storeWebView = this.mWebView;
        if (storeWebView != null) {
            storeWebView.setState(StoreWebView.State.Failed);
        }
        if (sDevModeCapabilities.isStoreDebugModeEnabled()) {
            DebugDialogs.showDebugModeConnectionErrorDialog(this.mParentActivity);
        } else {
            super.showConnectionErrorDialog();
        }
    }

    public void updateHeaderTitles(String title, String subtitle, boolean drillOut, boolean refineAvailable) {
        if (!this.mStoreTitleView.searchHasFocus()) {
            this.mStoreTitleView.collapseSearchBar();
        }
        if (!StringUtil.isNullOrWhiteSpaces(title)) {
            this.mStoreTitleView.setTitle(title);
        }
        this.mStoreTitleView.setSubtitle(subtitle);
        this.mStoreTitleView.setRefineMenuAvailable(refineAvailable);
        if (drillOut) {
            this.mActionBarProvider.requestHomeButtonAsBack();
        } else {
            setLeftButtonForStore();
        }
    }

    public ActionBarStoreTitleView getTitleView() {
        return this.mStoreTitleView;
    }

    void setSearchActive(boolean active) {
        this.mSearchActive = active;
    }

    void showDetailPage(ItemWrapper$ItemType itemType, String asin) {
        this.mShowDetailPageItemType = itemType;
        this.mShowDetailPageAsin = asin;
        if (!DeviceAuthorizationManager.getInstance().isAuthorized()) {
            setCirrusDownloadPreference(this.mParentActivity, false);
            AmazonApplication.getBackgroundHandler().postAtFrontOfQueue(new Runnable() { // from class: com.amazon.mp3.store.html5.activity.StoreFragment.2
                @Override // java.lang.Runnable
                public void run() {
                    Intent deviceAuthorizationIntent = WebViewUtil.getDeviceAuthorizationIntent(StoreFragment.this.mParentActivity);
                    if (deviceAuthorizationIntent != null) {
                        StoreFragment.this.mParentActivity.startActivityForResult(deviceAuthorizationIntent, StoreFragment.DEVICE_AUTHORIZATION_FLOW_CODE);
                    }
                }
            });
        } else {
            showDetailPage(this.mParentActivity, this.mShowDetailPageItemType == ItemWrapper$ItemType.ALBUM, this.mShowDetailPageAsin);
        }
    }

    void startSignIn(Message request) {
        IMetricsRecorder metricsRecorder = BaseMetricsRecorder.getMetricsRecorder(getContext().getApplicationContext());
        metricsRecorder.getFirstTimeUseMetrics().recordFirstSignInAttempt(FirstTimeUseMetrics.Source.STORE);
        metricsRecorder.getLoginMetrics().recordLoginAttempt(LoginMetrics.Source.STORE);
        this.mSignInMessage = request;
        startActivityForResult(new Intent(this.mParentActivity, (Class<?>) SignInFlowLauncherActivity.class), SignInFlowLauncherActivity.REQUEST_CODE);
    }

    void onWebViewClientReportedError(int errorCode) {
        StoreWebView storeWebView = this.mWebView;
        if (storeWebView != null) {
            if (errorCode == -6 || errorCode == -2) {
                storeWebView.setNetworkAvailable(false);
                this.mWebView.loadBlankAndClearHistory();
                showConnectionErrorDialog();
            }
        }
    }

    boolean isClearingCacheAndReloading() {
        return this.mClearingCacheAndReloading;
    }

    void onFinishedClearingCacheAndReloading() {
        this.mClearingCacheAndReloading = false;
    }

    void clearCacheAndReloadHome() {
        if (this.mWebView == null || !sDevModeCapabilities.isStoreDebugModeEnabled()) {
            return;
        }
        this.mWebView.stopLoading();
        this.mWebView.clearCache(true);
        this.mWebView.clearView();
        this.mClearingCacheAndReloading = true;
        this.mWebView.loadBlankAndClearHistory();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void attachWebView() {
        if (this.mWebView != null || getView() == null) {
            return;
        }
        if (getView() == null) {
            Log.error(TAG, "The store page cannot be displayed because, the root view for StoreHostWebView is null");
            return;
        }
        this.mWebContainer = (FrameLayout) getView().findViewById(R.id.StoreHostWebView);
        this.mWebView = StoreWebView.getInstance(this.mParentActivity);
        if (sDevModeCapabilities.isStoreDebugModeEnabled() || Log.getNonCriticalLoggingEnabled()) {
            StoreUtil.setWebViewLoggingEnabled(this.mParentActivity);
        }
        this.mWebView.attachActivity(this.mParentActivity);
        this.mWebView.setMessageHandler(this.mMessageHandler);
        this.mWebView.setWebViewClient(this.mWebViewClient);
        if (this.mWebView.getParent() != null && (this.mWebView.getParent() instanceof ViewGroup)) {
            ((ViewGroup) this.mWebView.getParent()).removeView(this.mWebView);
        }
        this.mWebContainer.addView(this.mWebView);
        if (!ConnectivityUtil.hasAnyInternetConnection()) {
            this.mWebView.loadBlankAndClearHistory();
            showConnectionErrorDialog();
            return;
        }
        StoreWebView.State state = this.mWebView.getState();
        if (this.mHasConnectionError || state == StoreWebView.State.Failed || state == StoreWebView.State.NotLoaded) {
            this.mHasConnectionError = false;
            this.mWebView.reload();
        }
    }

    private void detachWebView() {
        FrameLayout frameLayout = this.mWebContainer;
        if (frameLayout != null) {
            frameLayout.removeView(this.mWebView);
        }
        StoreWebView storeWebView = this.mWebView;
        if (storeWebView != null) {
            storeWebView.detachActivity(this.mParentActivity);
        }
        this.mWebView = null;
    }

    private void setLeftButtonForStore() {
        if (BottomNavigationBarContainer.getInstance().isEnabled()) {
            this.mActionBarProvider.requestHomeButtonAsStoreClose();
        } else {
            this.mActionBarProvider.restoreHomeButton();
        }
    }

    private static void showDetailPage(Activity activity, boolean isAlbumPurchase, String asin) {
        long jLongFromCursor = DbUtil.longFromCursor(activity.getContentResolver().query(MediaProvider.Tracks.getContentUri("cirrus"), new String[]{"album_id"}, (isAlbumPurchase ? "album_asin" : "asin") + " = ?", new String[]{asin}, null));
        if (jLongFromCursor == -1) {
            startLastestPurchasesLibraryActivityAndSync(activity);
            return;
        }
        Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(activity, MediaProvider.Albums.getContentUri("cirrus", jLongFromCursor));
        intentForContentUri.putExtra("com.amazon.mp3.EXTRA_STARTED_FROM_PURCHASE", true);
        intentForContentUri.putExtra("com.amazon.mp3.EXTRA_IS_ALBUM_PURCHASE", isAlbumPurchase);
        activity.startActivity(intentForContentUri);
    }

    private static void startLastestPurchasesLibraryActivityAndSync(Activity activity) {
        activity.startActivity(LibraryActivityFactory.getIntentForContentUri(activity, MediaProvider.SmartPlaylists.Tracks.getContentUri("cirrus", 0L)));
        startSync(activity);
    }

    private static void startSync(Activity activity) {
        SyncService.startSync(activity, !AccountDetailUtil.get().canSyncRemoteContent() ? 34 : 2);
    }

    private static void setCirrusDownloadPreference(Context context, boolean autoDownload) {
        SettingsUtil.setCirrusDownloadPreference(context, autoDownload);
        AutoDownloadPurchaseReceiver.setComponentEnabled(context, autoDownload);
        if (autoDownload) {
            SettingsUtil.setLastAutoDownloadDateToLastPurchaseDate(context);
        }
    }

    private static void processDeviceAuthorizationFlowCode(Activity activity, boolean isAlbumPurchase, String asin) {
        if (DeviceAuthorizationManager.getInstance().isAuthorized()) {
            setCirrusDownloadPreference(activity, true);
            showDetailPage(activity, isAlbumPurchase, asin);
        } else {
            setSuppressPostPurchaseDialog(activity, false);
        }
    }

    private static void setSuppressPostPurchaseDialog(Context context, boolean suppress) {
        SharedPreferences.Editor editorEdit = SettingsUtil.getPrefs(context).edit();
        editorEdit.putBoolean(context.getString(R.string.setting_key_suppress_post_purchase_dialog), suppress);
        editorEdit.apply();
    }

    final class StoreAccessValidationAndLoadWebViewAsyncTask extends AsyncTask<Void, Void, Boolean> {
        StoreAccessValidationAndLoadWebViewAsyncTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Boolean doInBackground(Void... objects) {
            Log.debug(StoreFragment.TAG, "Executing validation");
            return Boolean.valueOf(ValidatorRegistry.getInstance().getValidator("Store Access").isAllowed(true));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(final Boolean isAllowed) {
            Log.debug(StoreFragment.TAG, "Received validation result:" + isAllowed);
            if (isAllowed.booleanValue()) {
                StoreFragment.this.attachWebView();
                LastViewedScreenUtil.setLastViewed(StoreFragment.this.getApplication(), LastViewedScreenUtil.LastViewedScreen.STORE);
                return;
            }
            Context application = StoreFragment.this.getApplication();
            if (LastViewedScreenUtil.getLastViewedScreen(application) == LastViewedScreenUtil.LastViewedScreen.STORE) {
                LastViewedScreenUtil.setLastViewed(application, LastViewedScreenUtil.LastViewedScreen.LIBRARY);
            }
            if (StoreFragment.this.getActivity() != null) {
                StoreFragment.this.getActivity().onBackPressed();
            }
        }
    }
}
