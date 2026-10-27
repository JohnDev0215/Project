package com.amazon.mp3.store.html5.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebSettings;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.AccountRequestUtil;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.item.ItemWrapper$ItemType;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.prime.DeviceAuthorizationManager;
import com.amazon.mp3.prime.activity.PrimeActivityFactory;
import com.amazon.mp3.service.metrics.BaseMetricsRecorder;
import com.amazon.mp3.service.metrics.FirstTimeUseMetrics;
import com.amazon.mp3.service.metrics.IMetricsRecorder;
import com.amazon.mp3.service.metrics.UxMetricsLogger;
import com.amazon.mp3.store.html5.bridge.CollectionType;
import com.amazon.mp3.store.html5.bridge.Message;
import com.amazon.mp3.store.html5.bridge.ShowInLibraryBroadcast;
import com.amazon.mp3.store.html5.map.MAPCookiesCallback;
import com.amazon.mp3.store.html5.util.StoreUrls;
import com.amazon.mp3.store.html5.util.StoreUtil;
import com.amazon.mp3.store.html5.webview.StoreMessageHandler;
import com.amazon.mp3.store.html5.webview.StoreWebView;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.MediaPlaybackUtil;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.DataNotReadyException;
import com.amazon.music.account.MusicAccountNotCreatedException;
import com.google.android.gms.common.internal.ImagesContract;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
class StoreFragmentMessageHandler extends StoreMessageHandler {
    private static final int[] SYNC_DELAYS_MILLIS = {DisconnectedBufferOptions.DISCONNECTED_BUFFER_SIZE_DEFAULT, 40000};
    private static final String TAG = "StoreFragmentMessageHandler";
    private final Activity mActivity;
    private boolean mIsRegistered;
    private final StoreFragment mStoreFragment;
    private BroadcastReceiver mSyncEventReceiver;
    private SyncTimeoutRunnable mSyncTimeoutRunnable;
    private final StoreWebView mWebView;
    private final StoreFragmentWebViewClient mWebViewClient;

    public StoreFragmentMessageHandler(StoreFragment fragment, StoreFragmentWebViewClient webViewClient) {
        super(fragment.getActivity());
        this.mStoreFragment = fragment;
        FragmentActivity activity = fragment.getActivity();
        this.mActivity = activity;
        this.mWebViewClient = webViewClient;
        StoreWebView storeWebView = StoreWebView.getInstance(activity);
        this.mWebView = storeWebView;
        storeWebView.setSaveEnabled(true);
        WebSettings settings = storeWebView.getSettings();
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setCacheMode(0);
    }

    @Override // com.amazon.mp3.store.html5.webview.StoreMessageHandler
    protected void handleRequestAudioFocusRequest(Message message) {
        super.handleRequestAudioFocusRequest(message);
        this.mActivity.getWindow().addFlags(128);
    }

    @Override // com.amazon.mp3.store.html5.webview.StoreMessageHandler
    protected void handleRefineMenuVisibilityBroadcast(final Message broadcast) {
        super.handleRefineMenuVisibilityBroadcast(broadcast);
        this.mStoreFragment.getTitleView().toggleStoreMenuVisibility(broadcast.getOptions().optBoolean("visible", false));
    }

    @Override // com.amazon.mp3.store.html5.webview.StoreMessageHandler
    protected void handleAudioFocusReleasedBroadcast(Message broadcast) {
        super.handleAudioFocusReleasedBroadcast(broadcast);
        this.mActivity.getWindow().clearFlags(128);
    }

    @Override // com.amazon.mp3.store.html5.webview.StoreMessageHandler
    protected void respondToSignInRequest(Message request, boolean successful) {
        super.respondToSignInRequest(request, successful);
    }

    public void updateCredentials() {
        if (AccountCredentialUtil.get(this.mActivity).isSignedOut()) {
            StoreUtil.broadcastCredentials(this.mActivity);
        } else {
            fetchCookiesFromMap(new MAPCookiesCallback(this.mActivity.getApplicationContext()), false);
        }
    }

    @Override // com.amazon.mp3.store.html5.webview.StoreMessageHandler
    protected void handleInitializedBroadcast(Message message) {
        super.handleInitializedBroadcast(message);
        this.mWebViewClient.clearBridgeTimeout();
        StoreUtil.broadcastCredentials(this.mActivity);
        StoreUtil.broadcastReferrer(this.mActivity);
    }

    @Override // com.amazon.mp3.store.html5.webview.StoreMessageHandler
    protected void handleShowLibraryBroadcast(Message message) {
        navFromStoreToLibrary(this.mActivity);
        this.mWebView.loadUrlAndClearHistory(StoreUrls.getBaseUrl());
    }

    private static void navFromStoreToLibrary(Context context) {
        UxMetricsLogger.registerUserAction(UxMetricsLogger.UserAction.STORE_TO_LIBRARY);
        Intent startIntent = MusicHomeActivity.getStartIntent(context);
        startIntent.setFlags(537001984);
        context.startActivity(startIntent);
    }

    @Override // com.amazon.mp3.store.html5.webview.StoreMessageHandler
    protected void handleStoreOfflineBroadcast(Message message) {
        this.mStoreFragment.showConnectionErrorDialog();
    }

    @Override // com.amazon.mp3.store.html5.webview.StoreMessageHandler
    protected void handleRequestPermissionRequest(final Message request) {
        respondToRequestPermissionRequest(request, true);
    }

    @Override // com.amazon.mp3.store.html5.webview.StoreMessageHandler
    protected void handleSignInRequest(Message request) {
        if (AccountCredentialUtil.get(this.mActivity).isSignedIn()) {
            fetchCookiesFromMap(new SignInCookieCallback(this.mActivity, request, this), false);
        } else if (request.getOptions().optBoolean("nonBlocking")) {
            respondToSignInRequest(request, false);
        } else {
            this.mStoreFragment.startSignIn(request);
        }
    }

    @Override // com.amazon.mp3.store.html5.webview.StoreMessageHandler
    protected void handleSyncCloudLibraryRequest(final Message message) {
        String string;
        this.mSyncEventReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.store.html5.activity.StoreFragmentMessageHandler.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                Boolean bool;
                new Thread() { // from class: com.amazon.mp3.store.html5.activity.StoreFragmentMessageHandler.1.1
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        DeviceAuthorizationManager.getInstance().autoAuthorize(AmazonApplication.getContext());
                    }
                }.start();
                if ("com.amazon.mp3.library.service.sync.ACTION_SYNC_SERVICE_STOPPED".equals(intent.getAction())) {
                    bool = Boolean.FALSE;
                } else {
                    int intExtra = intent.getIntExtra("com.amazon.mp3.library.service.sync.EXTRA_SYNC_EVENT_TYPE", 0);
                    if (intExtra == 3) {
                        Log.debug(StoreFragmentMessageHandler.TAG, "handleSyncCloudLibraryRequest received SYNC_EVENT_FAILED");
                        bool = Boolean.FALSE;
                    } else if (intExtra != 5) {
                        bool = null;
                    } else {
                        Log.debug(StoreFragmentMessageHandler.TAG, "handleSyncCloudLibraryRequest received SYNC_EVENT_FINISHED");
                        bool = Boolean.TRUE;
                    }
                }
                if (bool != null) {
                    StoreFragmentMessageHandler.this.removeSyncTimeoutCallbacks();
                    StoreFragmentMessageHandler.this.deregisterSyncReceiver();
                    StoreFragmentMessageHandler.this.respondToSyncCloudLibraryRequest(message, bool.booleanValue());
                }
            }
        };
        try {
            string = message.getOptions().getString(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        } catch (JSONException unused) {
            Log.warning(TAG, "Error obtaining synctype");
            string = "";
        }
        int i = Environment.PLAYLIST_PATH.equals(string) ? 17 : 18;
        IntentFilter intentFilter = new IntentFilter("com.amazon.mp3.library.service.sync.ACTION_SYNC_EVENT");
        intentFilter.addAction("com.amazon.mp3.library.service.sync.ACTION_SYNC_SERVICE_STOPPED");
        registerSyncReceiver(intentFilter);
        try {
            if (AccountManagerSingleton.get().getUser().isAccountValid()) {
                i |= 32;
            } else {
                Log.debug(TAG, "User state is invalid");
            }
        } catch (DataNotReadyException | MusicAccountNotCreatedException unused2) {
            Log.debug(TAG, "User object is not accessible yet");
        }
        SyncService.startSync(AmazonApplication.getContext(), i);
        removeSyncTimeoutCallbacks();
        this.mSyncTimeoutRunnable = new SyncTimeoutRunnable(message);
        AmazonApplication.getDefaultHandler().postDelayed(this.mSyncTimeoutRunnable, 20000L);
        Runnable runnable = new Runnable() { // from class: com.amazon.mp3.store.html5.activity.StoreFragmentMessageHandler.2
            @Override // java.lang.Runnable
            public void run() {
                SyncService.startSync(AmazonApplication.getContext(), 18);
            }
        };
        int length = SYNC_DELAYS_MILLIS.length;
        for (int i2 = 0; i2 < length; i2++) {
            AmazonApplication.getDefaultHandler().postDelayed(runnable, r0[i2]);
        }
    }

    @Override // com.amazon.mp3.store.html5.webview.StoreMessageHandler
    protected void handleShowDetailPageBroadcast(Message broadcast) {
        try {
            JSONObject options = broadcast.getOptions();
            String string = options.getString("asin");
            String string2 = options.getString(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            Log.debug(TAG, "showDetailPage received for " + string2 + " asin " + string);
            this.mStoreFragment.showDetailPage(itemTypeFromString(string2), string);
        } catch (JSONException e) {
            Log.error(TAG, "asin or type missing from show detail page", e);
        }
    }

    private static ItemWrapper$ItemType itemTypeFromString(String type) {
        if ("track".equals(type)) {
            return ItemWrapper$ItemType.SONG;
        }
        if ("album".equals(type)) {
            return ItemWrapper$ItemType.ALBUM;
        }
        if (Environment.PLAYLIST_PATH.equals(type)) {
            return ItemWrapper$ItemType.PRIME_PLAYLIST;
        }
        throw new RuntimeException("Unexpected item type: " + type);
    }

    @Override // com.amazon.mp3.store.html5.webview.StoreMessageHandler
    protected void handlePlayVideoBroadcast(Message broadcast) {
        try {
            this.mActivity.startActivity(MediaPlaybackUtil.getPlayVideoIntent(broadcast.getOptions().getString(ImagesContract.URL)));
        } catch (JSONException e) {
            Log.error(TAG, "url missing from play video", e);
        }
    }

    @Override // com.amazon.mp3.store.html5.webview.StoreMessageHandler
    protected void handleSearchStateChanged(Message broadcast) {
        this.mStoreFragment.setSearchActive(broadcast.getOptions().optBoolean("focused"));
    }

    @Override // com.amazon.mp3.store.html5.webview.StoreMessageHandler
    protected void handleRefreshRobinStatusBroadcast(final Message broadcast) {
        new AsyncTask<Void, Void, Void>() { // from class: com.amazon.mp3.store.html5.activity.StoreFragmentMessageHandler.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... params) {
                try {
                    AccountRequestUtil.refreshPrimeBenefits(StoreFragmentMessageHandler.this.mActivity);
                    return null;
                } catch (Exception e) {
                    Log.warning(StoreFragmentMessageHandler.TAG, "Could not refresh prime benefits", e);
                    return null;
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Void result) {
                String string;
                String baseUrl;
                StoreUtil.broadcastCredentials(StoreFragmentMessageHandler.this.mActivity);
                try {
                    string = broadcast.getOptions().getString("route");
                } catch (JSONException e) {
                    Log.error(StoreFragmentMessageHandler.TAG, "Could not read route from broadcast", e);
                    string = null;
                }
                if (string != null) {
                    baseUrl = StoreUrls.getStoreURL(string);
                } else {
                    baseUrl = StoreUrls.getBaseUrl();
                }
                Log.verbose(StoreFragmentMessageHandler.TAG, "RefreshRobinStatus broadcast - loading url");
                Log.debugBuildOnly(StoreFragmentMessageHandler.TAG, "%s", baseUrl);
                StoreFragmentMessageHandler.this.mWebView.loadUrl(baseUrl);
            }
        }.execute(new Void[0]);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [com.amazon.mp3.store.html5.activity.StoreFragmentMessageHandler$1] */
    /* JADX WARN: Type inference failed for: r1v3, types: [android.content.Intent] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r5v4, types: [android.app.Activity] */
    @Override // com.amazon.mp3.store.html5.webview.StoreMessageHandler
    protected void handleShowInLibraryBroadcast(Message message) {
        ShowInLibraryBroadcast showInLibraryBroadcast = new ShowInLibraryBroadcast(message);
        int i = AnonymousClass4.$SwitchMap$com$amazon$mp3$store$html5$bridge$CollectionType[showInLibraryBroadcast.getCollectionType().ordinal()];
        ?? r1 = 0;
        r1 = 0;
        if (i == 1) {
            new OpenAlbumDetailTask().execute(showInLibraryBroadcast);
        } else if (i == 2) {
            Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(this.mActivity, MediaProvider.PrimePlaylists.getContentUri("cirrus", showInLibraryBroadcast.getCollecionAsin()));
            intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_ADD_TO_LIBRARY", true);
            r1 = intentForContentUri;
        }
        if (r1 != 0) {
            this.mActivity.startActivity(r1);
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.store.html5.activity.StoreFragmentMessageHandler$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$store$html5$bridge$CollectionType;

        static {
            int[] iArr = new int[CollectionType.values().length];
            $SwitchMap$com$amazon$mp3$store$html5$bridge$CollectionType = iArr;
            try {
                iArr[CollectionType.ALBUM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$store$html5$bridge$CollectionType[CollectionType.PLAYLIST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // com.amazon.mp3.store.html5.webview.StoreMessageHandler
    protected void handleUpdateViewTitlesBroadcast(Message broadcast) {
        JSONObject options = broadcast.getOptions();
        this.mStoreFragment.updateHeaderTitles(options.optString("title"), options.optString("subTitle", null), "drillOut".equals(options.optString("function", "menu")), options.optBoolean("refineMenuVisible", false));
    }

    @Override // com.amazon.mp3.store.html5.webview.StoreMessageHandler
    protected void handleShowPrimeBrowse(Message broadcast) {
        Activity activity = this.mActivity;
        activity.startActivity(PrimeActivityFactory.getPrimeSectionIntent(activity.getApplicationContext()));
    }

    private void registerSyncReceiver(IntentFilter filter) {
        if (this.mIsRegistered || this.mSyncEventReceiver == null) {
            return;
        }
        AmazonApplication.getContext().registerReceiver(this.mSyncEventReceiver, filter);
        this.mIsRegistered = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deregisterSyncReceiver() {
        if (!this.mIsRegistered || this.mSyncEventReceiver == null) {
            return;
        }
        AmazonApplication.getContext().unregisterReceiver(this.mSyncEventReceiver);
        this.mIsRegistered = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeSyncTimeoutCallbacks() {
        if (this.mSyncTimeoutRunnable != null) {
            AmazonApplication.getDefaultHandler().removeCallbacks(this.mSyncTimeoutRunnable);
        }
    }

    private class SyncTimeoutRunnable implements Runnable {
        private final Message mMessage;

        public SyncTimeoutRunnable(final Message message) {
            this.mMessage = message;
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.debug(StoreFragmentMessageHandler.TAG, "handleSyncCloudLibraryRequest expired SYNC_TIMEOUT");
            StoreFragmentMessageHandler.this.deregisterSyncReceiver();
            StoreFragmentMessageHandler.this.respondToSyncCloudLibraryRequest(this.mMessage, false);
        }
    }

    private static class SignInCookieCallback extends MAPCookiesCallback {
        private final StoreFragmentMessageHandler mMessageHandler;
        private final Message mSignInMessage;

        public SignInCookieCallback(final Context context, final Message signInMessage, final StoreFragmentMessageHandler messageHandler) {
            super(context);
            this.mSignInMessage = signInMessage;
            this.mMessageHandler = messageHandler;
        }

        @Override // com.amazon.mp3.store.html5.map.MAPCookiesCallback, com.amazon.identity.auth.device.api.Callback
        public void onError(final Bundle errorBundle) {
            super.onError(errorBundle);
            respondToSignIn(false);
        }

        @Override // com.amazon.mp3.store.html5.map.MAPCookiesCallback, com.amazon.identity.auth.device.api.Callback
        public void onSuccess(final Bundle successBundle) {
            super.onSuccess(successBundle);
            IMetricsRecorder metricsRecorder = BaseMetricsRecorder.getMetricsRecorder(this.mContext.getApplicationContext());
            metricsRecorder.getRecurrentUseMetrics().recordUserSignedIn();
            metricsRecorder.getFirstTimeUseMetrics().recordFirstSuccessfulSignIn(FirstTimeUseMetrics.Source.STORE);
            respondToSignIn(true);
        }

        private void respondToSignIn(final boolean success) {
            this.mHandler.post(new Runnable() { // from class: com.amazon.mp3.store.html5.activity.StoreFragmentMessageHandler.SignInCookieCallback.1
                @Override // java.lang.Runnable
                public void run() {
                    SignInCookieCallback.this.mMessageHandler.respondToSignInRequest(SignInCookieCallback.this.mSignInMessage, success);
                }
            });
        }
    }

    private class OpenAlbumDetailTask extends AsyncTask<ShowInLibraryBroadcast, Void, Intent> {
        private OpenAlbumDetailTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Intent doInBackground(ShowInLibraryBroadcast... params) {
            ShowInLibraryBroadcast showInLibraryBroadcast = params[0];
            Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(StoreFragmentMessageHandler.this.mActivity, MediaProvider.Albums.getContentUri("cirrus", StoreFragmentMessageHandler.this.getAlbumIdFromAsin(showInLibraryBroadcast.getCollecionAsin())));
            intentForContentUri.putExtra("com.amazon.mp3.library.activity.EXTRA_ALBUM_ASIN", showInLibraryBroadcast.getCollecionAsin());
            return intentForContentUri;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Intent result) {
            StoreFragmentMessageHandler.this.mActivity.startActivity(result);
        }
    }

    public long getAlbumIdFromAsin(String asin) {
        if (AmazonApplication.onMainThread()) {
            Log.warning(TAG, "This should be run on a background thread!");
        }
        long jLongFromCursor = DbUtil.longFromCursor(this.mActivity.getContentResolver().query(MediaProvider.Tracks.getContentUri("cirrus"), new String[]{"album_id"}, "album_asin = ?", new String[]{asin}, null));
        if (jLongFromCursor < 0) {
            return 0L;
        }
        return jLongFromCursor;
    }
}
