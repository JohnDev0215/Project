package com.amazon.mp3.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.activity.GrantPermissionActivity;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.activity.settings.SettingsActivity;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.client.activity.LauncherActivity;
import com.amazon.mp3.download.library.scanner.MediaScanner;
import com.amazon.mp3.download.library.scanner.TrackScanCompletedListener;
import com.amazon.mp3.external.api.AmznUriHandler;
import com.amazon.mp3.init.ForegroundLaunchInitializationTask;
import com.amazon.mp3.init.InitializationHandler;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.playback.harley.PlayQueueSequencer;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.service.PlaybackServiceUtil;
import com.amazon.mp3.playback.service.metrics.PlaybackMetricInformation;
import com.amazon.mp3.prime.DeviceAuthorizationManager;
import com.amazon.mp3.service.error.CirrusError;
import com.amazon.mp3.service.error.CirrusErrorLogger;
import com.amazon.mp3.service.metrics.BaseMetricsRecorder;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.service.metrics.ReferralAndCampaignMetrics;
import com.amazon.mp3.service.metrics.UxMetricsLogger;
import com.amazon.mp3.store.activity.HTMLStoreActivityFactory;
import com.amazon.mp3.store.html5.util.StoreUrls;
import com.amazon.mp3.store.html5.util.StoreUtil;
import com.amazon.mp3.store.html5.webview.StoreWebView;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.MimeTypeUtil;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.mp3.util.WebViewUtil;
import com.amazon.mp3.util.health.AppHealthUtil;
import com.amazon.music.deeplink.DeeplinksManager;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.metrics.mts.event.definition.refer.InvalidReferrerException;
import com.amazon.music.metrics.mts.event.types.DirectedPlayStatus;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.google.android.gms.cast.HlsSegmentFormat;
import com.visualon.OSMPUtils.voOSType;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import rx.functions.Action1;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseIntentProxyActivity extends Activity {
    private static final String TAG = IntentProxyActivity.class.getSimpleName();
    private static Uri sLastIntentUri;
    private String mReftag;

    BaseIntentProxyActivity() {
    }

    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new InitializationHandler(new ForegroundLaunchInitializationTask(this, new AppHealthUtil(PreferenceManager.getDefaultSharedPreferences(this)))).initializeOnNewThread();
        BaseActivity.setLowMemory(getClass(), false);
        onIntentReceived(getIntent());
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        onIntentReceived(intent);
    }

    public static boolean isAmazonAppUri(final Uri uri) {
        if (uri == null) {
            return false;
        }
        String scheme = uri.getScheme();
        return "amznmp3".equals(scheme) || ("amzn".equals(scheme) && HlsSegmentFormat.MP3.equals(uri.getHost()));
    }

    public static boolean isHttpUri(final Uri uri) {
        if (uri == null) {
            return false;
        }
        String scheme = uri.getScheme();
        return "http".equals(scheme) || "https".equals(scheme);
    }

    public void startActivityWithIntentUri(final Class activityClass, final Uri uri) {
        Intent intent = new Intent(getApplicationContext(), (Class<?>) activityClass);
        intent.putExtra("com.amazon.mp3.extra.EXTERNAL_URI", uri.toString());
        intent.addFlags(32768);
        intent.addFlags(268435456);
        startActivity(intent);
    }

    private void onIntentReceived(final Intent intent) {
        if (AccountCredentialUtil.get(getApplicationContext()).isSignInComplete()) {
            bootstrapHandleNewIntent(intent);
        } else {
            handleNewIntent(intent);
        }
    }

    private void bootstrapHandleNewIntent(final Intent intent) {
        BootstrapSingletonTask.get(getApplicationContext()).registerFeatureFlagObserver().take(1).subscribe(new Action1() { // from class: com.amazon.mp3.activity.BaseIntentProxyActivity.1
            @Override // rx.functions.Action1
            public void call(final Object o) {
                BaseIntentProxyActivity.this.handleNewIntent(intent);
            }
        }, new Action1<Throwable>() { // from class: com.amazon.mp3.activity.BaseIntentProxyActivity.2
            @Override // rx.functions.Action1
            public void call(final Throwable throwable) {
                Log.warning(BaseIntentProxyActivity.TAG, "BootstrapSingletonTask error: " + throwable.getMessage());
            }
        });
    }

    private void handleViewAction(Intent intent, boolean isStoreSupported, boolean shouldCloudBeSupported) {
        Uri data = intent.getData();
        if (data != null) {
            String host = data.getHost();
            String path = data.getPath();
            String scheme = data.getScheme();
            if ("file".equals(scheme)) {
                if (MimeTypeUtil.isUnsupported(data)) {
                    onUnsupportedFileType(data);
                    return;
                } else {
                    onLocalFilePlaybackRequested(data);
                    return;
                }
            }
            if ("content".equals(scheme) && "media".equals(host)) {
                onLocalUriPlaybackRequested(data);
                return;
            }
            if (path != null && (path.contains("android/buy") || path.contains("cplanding"))) {
                if (isStoreSupported) {
                    UxMetricsLogger.registerUserAction(UxMetricsLogger.UserAction.LAUNCHER_STORE_SEARCH);
                    String referrerName = getReferrerName(data);
                    try {
                        MetricsLogger.referredByExternalApp(referrerName);
                        saveReferrer(referrerName);
                    } catch (InvalidReferrerException unused) {
                        Log.warning(TAG, "Unable to log external referrer");
                    }
                    onAnywhereBrowse(intent);
                    return;
                }
                onStoreUnsupported();
                return;
            }
            if (path != null && path.contains("android") && data.getQueryParameter("s") != null) {
                if (isStoreSupported) {
                    UxMetricsLogger.registerUserAction(UxMetricsLogger.UserAction.LAUNCHER_STORE_SEARCH);
                    addReftagAndStartActivity(HTMLStoreActivityFactory.intentForSearch(this, HTMLStoreActivityFactory.bundleForSearchString(data.getQueryParameter("s"))));
                    return;
                } else {
                    onStoreUnsupported();
                    return;
                }
            }
            if (isAmazonAppUri(data) || isHttpUri(data)) {
                if (!AccountCredentialUtil.get(this).isSignedIn()) {
                    startActivityWithIntentUri(LauncherActivity.class, data);
                    return;
                }
                if (!DeviceAuthorizationManager.getInstance().isAuthorized()) {
                    AmazonApplication.getBackgroundHandler().postAtFrontOfQueue(new Runnable() { // from class: com.amazon.mp3.activity.BaseIntentProxyActivity.3
                        @Override // java.lang.Runnable
                        public void run() {
                            Intent deviceAuthorizationIntent = WebViewUtil.getDeviceAuthorizationIntent(BaseIntentProxyActivity.this.getApplicationContext());
                            if (deviceAuthorizationIntent != null) {
                                BaseIntentProxyActivity.this.startActivity(deviceAuthorizationIntent);
                            }
                        }
                    });
                    return;
                }
                try {
                    Log.verbose(TAG, "Trying to handle deeplink via deeplinking component");
                    DeeplinksManager.get(this).handle(data);
                } catch (Exception unused2) {
                    handleLegacyUri(data, shouldCloudBeSupported, isStoreSupported);
                }
            }
        }
    }

    private void handleLegacyUri(final Uri uri, final boolean isStoreSupported, final boolean shouldCloudBeSupported) {
        Intent intentUriToIntent = AmznUriHandler.uriToIntent(this, uri, shouldCloudBeSupported, isStoreSupported);
        if (intentUriToIntent != null) {
            intentUriToIntent.putExtra("com.amazon.mp3.launch.EXTERNAL", true);
            startActivity(intentUriToIntent);
        } else {
            String string = uri.toString();
            Log.debug(TAG, "Couldn't match uri: %s", string);
            CirrusErrorLogger.getInstance().log(CirrusError.PARTNER_API_ERROR, string);
        }
    }

    private void handleExternalAction(Intent intent, boolean isStoreSupported, boolean shouldCloudBeSupported) {
        ComponentName callingActivity;
        this.mReftag = intent.getStringExtra("com.amazon.mp3.extra.REFTAG");
        String stringExtra = intent.getStringExtra("com.amazon.mp3.extra.REFERRER_NAME");
        if (stringExtra == null && (stringExtra = getCallingPackage()) == null && (callingActivity = getCallingActivity()) != null) {
            stringExtra = callingActivity.getPackageName();
        }
        String str = stringExtra;
        if (str != null && !getPackageName().equals(str)) {
            try {
                MetricsLogger.referredByExternalApp(str);
                saveReferrer(str);
            } catch (InvalidReferrerException unused) {
                Log.warning(TAG, "Unable to log external referrer");
            }
        }
        ReferralAndCampaignMetrics referralAndCampaignMetrics = BaseMetricsRecorder.getMetricsRecorder(getApplicationContext()).getReferralAndCampaignMetrics();
        referralAndCampaignMetrics.recordReferralAppOpen(str);
        handleExternalActionType(intent, isStoreSupported, shouldCloudBeSupported, str, referralAndCampaignMetrics, intent.getStringExtra("com.amazon.mp3.extra.EXTERNAL_EVENT_TYPE"));
    }

    protected void handleExternalActionType(final Intent intent, final boolean isStoreSupported, final boolean shouldCloudBeSupported, final String referrerName, final ReferralAndCampaignMetrics referralMetrics, final String type) {
        if ("com.amazon.mp3.type.SHOW_ALBUM_DETAIL".equals(type)) {
            if (isStoreSupported) {
                UxMetricsLogger.registerUserAction(UxMetricsLogger.UserAction.LAUNCHER_STORE_SEARCH);
                onShowAlbumDetail(intent);
                return;
            } else {
                onStoreUnsupported();
                return;
            }
        }
        if ("com.amazon.mp3.type.SHOW_TRACK_DETAIL".equals(type)) {
            if (isStoreSupported) {
                onShowTrackDetail(intent);
                return;
            } else {
                onStoreUnsupported();
                return;
            }
        }
        if ("com.amazon.mp3.type.SHOW_NOW_PLAYING".equals(type)) {
            onShowNowPlaying();
            return;
        }
        if ("com.amazon.mp3.type.TOP_MUSIC_BROWSE".equals(type)) {
            if (isStoreSupported) {
                onTopMusicBrowse(intent);
                return;
            } else {
                onStoreUnsupported();
                return;
            }
        }
        if ("com.amazon.mp3.type.SEARCH".equals(type)) {
            if (isStoreSupported) {
                UxMetricsLogger.registerUserAction(UxMetricsLogger.UserAction.LAUNCHER_STORE_SEARCH);
                onSearch(intent, referrerName);
                return;
            } else {
                onStoreUnsupported();
                return;
            }
        }
        if ("com.amazon.mp3.type.STORE".equals(type)) {
            if (isStoreSupported) {
                Intent startIntent = HTMLStoreActivityFactory.getStartIntent(this);
                startIntent.addFlags(32768);
                startIntent.putExtra("com.amazon.mp3.launch.EXTERNAL", true);
                addReftagAndStartActivity(startIntent);
                return;
            }
            onStoreUnsupported();
            return;
        }
        if ("com.amazon.mp3.type.LIBRARY_SEARCH".equals(type)) {
            if (shouldCloudBeSupported) {
                onLibrarySearch(intent);
            }
        } else if ("com.amazon.mp3.type.SHOW_DEVICE_LIBRARY".equals(type)) {
            startActivity(LibraryActivityFactory.getLibraryHomeStartIntent(this, "cirrus-local"));
        } else if ("com.amazon.mp3.type.SHOW_CAMPAIGN_DETAIL".equals(type)) {
            if (isStoreSupported) {
                onShowCampaignDetail(intent);
            } else {
                onStoreUnsupported();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleNewIntent(final Intent intent) {
        if (intent == null) {
            sLastIntentUri = null;
            return;
        }
        sLastIntentUri = intent.getData();
        String action = intent.getAction();
        boolean zShouldCloudBeSupported = AmazonApplication.getCapabilities().shouldCloudBeSupported();
        boolean zIsStoreSupported = AmazonApplication.getCapabilities().isStoreSupported();
        Log.info(TAG, "mp3: IntentProxyActivity action=" + action + ", eit=" + intent.getStringExtra("com.amazon.mp3.extra.EXTERNAL_EVENT_TYPE") + ", cloud=" + zShouldCloudBeSupported + ", store=" + zIsStoreSupported);
        if ("android.intent.action.MEDIA_SEARCH".equals(action)) {
            if (zIsStoreSupported) {
                onMediaSearch(intent);
                return;
            } else {
                onStoreUnsupported();
                return;
            }
        }
        if ("com.amazon.mp3.action.SHOW_SETTINGS".equals(action)) {
            onShowSettings();
            return;
        }
        if ("com.amazon.mp3.action.EXTERNAL_EVENT".equals(action) || "com.amazon.mp3.ACTION_CONTINUE_PLAYBACK_FOR_CARD".equals(action)) {
            handleExternalAction(intent, zIsStoreSupported, zShouldCloudBeSupported);
        } else if ("android.intent.action.VIEW".equals(action)) {
            handleViewAction(intent, zIsStoreSupported, zShouldCloudBeSupported);
        } else if ("com.amazon.mp3.action.LAUNCH_STORE".equals(action)) {
            handleStoreLaunchAction(intent);
        }
    }

    private void handleStoreLaunchAction(final Intent intent) {
        Intent startIntent = HTMLStoreActivityFactory.getStartIntent(this, HTMLStoreActivityFactory.bundleForRoute(intent.getStringExtra("com.amazon.mp3.extra.EXTRA_STORE_URL")));
        startIntent.addFlags(voOSType.VOOSMP_SRC_FFAUDIO_MIDI);
        startIntent.addFlags(268435456);
        startIntent.addFlags(32768);
        addReftagAndStartActivity(startIntent);
    }

    public static void saveReferrer(Context context, String referrerName) {
        SharedPreferences.Editor editorEdit = SettingsUtil.getPrefs(context).edit();
        editorEdit.putString(context.getString(R.string.setting_key_app_referrer_package), referrerName);
        editorEdit.putLong(context.getString(R.string.setting_key_app_referrer_timestamp), Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis());
        editorEdit.apply();
        if (StoreWebView.instanceCreated()) {
            StoreUtil.broadcastReferrer(context);
        }
    }

    private void saveReferrer(final String referrerName) {
        saveReferrer(this, referrerName);
    }

    private void onStoreUnsupported() {
        startActivity(StoreUnsupportedDialogActivity.getStartIntent(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onUnsupportedFileType(Uri uri) {
        startActivity(UnsupportedFileTypeActivity.getStartIntent(this, uri));
    }

    private void onLocalUriPlaybackRequested(final Uri contentUri) {
        if (PlatformUtil.isVersionOrGreater(23)) {
            GrantStoragePermissionActivity.requestIfNotGranted(this, "android.permission.READ_EXTERNAL_STORAGE", new GrantPermissionActivity.PermissionResultReceiver() { // from class: com.amazon.mp3.activity.BaseIntentProxyActivity.4
                @Override // com.amazon.mp3.activity.GrantPermissionActivity.PermissionResultReceiver
                protected void onGranted() {
                    BaseIntentProxyActivity.this.onLocalUriPlaybackGranted(contentUri);
                }

                @Override // com.amazon.mp3.activity.GrantPermissionActivity.PermissionResultReceiver
                protected void onDenied(String[] permissions) {
                    Log.warning(BaseIntentProxyActivity.TAG, "Do not have READ_EXTERNAL_STORAGE permission to play local file!");
                }
            });
        } else {
            onLocalUriPlaybackGranted(contentUri);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onLocalUriPlaybackGranted(final Uri contentUri) {
        new AsyncTask<Void, Void, String>() { // from class: com.amazon.mp3.activity.BaseIntentProxyActivity.5
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public String doInBackground(Void... arg0) throws Throwable {
                Cursor cursor = null;
                try {
                    Cursor cursorQuery = BaseIntentProxyActivity.this.getContentResolver().query(contentUri, new String[]{"_data"}, null, null, null);
                    if (cursorQuery != null) {
                        try {
                            if (cursorQuery.moveToFirst()) {
                                String string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_data"));
                                cursorQuery.close();
                                return string;
                            }
                        } catch (Throwable th) {
                            th = th;
                            cursor = cursorQuery;
                            if (cursor != null) {
                                cursor.close();
                            }
                            throw th;
                        }
                    }
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(String result) {
                if (result != null) {
                    Uri uri = Uri.parse(result);
                    if (MimeTypeUtil.isUnsupported(uri)) {
                        BaseIntentProxyActivity.this.onUnsupportedFileType(uri);
                    } else {
                        BaseIntentProxyActivity.this.onLocalFilePlaybackRequested(uri);
                    }
                }
            }
        }.execute(new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean startLocalFilePlayback(final Context context, String filePath) throws Throwable {
        Uri contentUriForTrackPath = PlaybackServiceUtil.getContentUriForTrackPath(context, filePath);
        if (contentUriForTrackPath == null) {
            return false;
        }
        PlayQueueSequencer.getInstance().start(ControlSource.EXTERNAL, contentUriForTrackPath, null, null, null, 0, false, new PlaybackMetricInformation(DirectedPlayStatus.DIRECTED, PlaybackPageType.EXTERNAL), Clock.now());
        startActivity(LibraryActivityFactory.getIntentForContentUri(this, MediaProvider.NowPlaying.CONTENT_URI));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onLocalFilePlaybackRequested(final Uri uri) {
        if (PlatformUtil.isVersionOrGreater(23)) {
            GrantStoragePermissionActivity.requestIfNotGranted(this, "android.permission.READ_EXTERNAL_STORAGE", new GrantPermissionActivity.PermissionResultReceiver() { // from class: com.amazon.mp3.activity.BaseIntentProxyActivity.6
                @Override // com.amazon.mp3.activity.GrantPermissionActivity.PermissionResultReceiver
                protected void onGranted() {
                    BaseIntentProxyActivity.this.onLocalFilePlaybackRequestGranted(uri);
                }

                @Override // com.amazon.mp3.activity.GrantPermissionActivity.PermissionResultReceiver
                protected void onDenied(String[] permissions) {
                    Log.warning(BaseIntentProxyActivity.TAG, "Do not have READ_EXTERNAL_STORAGE permission to play local file!");
                }
            });
        } else {
            onLocalFilePlaybackRequestGranted(uri);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onLocalFilePlaybackRequestGranted(Uri uri) {
        TrackScanCompletedListener trackScanCompletedListener = new TrackScanCompletedListener() { // from class: com.amazon.mp3.activity.BaseIntentProxyActivity.7
            @Override // com.amazon.mp3.download.library.scanner.TrackScanCompletedListener
            public void onScanCompleted(final String filePath, Uri uri2) {
                new Thread(new Runnable() { // from class: com.amazon.mp3.activity.BaseIntentProxyActivity.7.1
                    @Override // java.lang.Runnable
                    public void run() throws Throwable {
                        SyncService.waitForSync(this, 128);
                        AnonymousClass7 anonymousClass7 = AnonymousClass7.this;
                        BaseIntentProxyActivity.this.startLocalFilePlayback(this, filePath);
                    }
                }).start();
            }
        };
        String path = Environment.getExternalStorageDirectory().getPath();
        String path2 = uri.getPath();
        if (path2.startsWith("/sdcard") && path.startsWith("/mnt/sdcard")) {
            path2 = "/mnt/sdcard" + path2.substring(7);
        }
        if (startLocalFilePlayback(this, path2)) {
            return;
        }
        MediaScanner.scan(this, path2, trackScanCompletedListener);
    }

    private void onMediaSearch(Intent intent) {
        addReftagAndStartActivity(HTMLStoreActivityFactory.intentForMediaSearch(this, intent));
    }

    private void onShowSettings() {
        Intent startIntent = SettingsActivity.getStartIntent(this);
        startIntent.addFlags(402653184);
        startActivity(startIntent);
    }

    private void onShowAlbumDetail(Intent intent) {
        String string;
        Bundle extras = intent.getExtras();
        if (extras == null || (string = extras.getString("com.amazon.mp3.extra.ALBUM_ASIN")) == null) {
            return;
        }
        Intent startIntent = HTMLStoreActivityFactory.getStartIntent(this, HTMLStoreActivityFactory.bundleForAlbum(string, extras.getString("com.amazon.mp3.extra.AUTO_PLAY_TRACK_ASIN")));
        startIntent.addFlags(voOSType.VOOSMP_SRC_FFAUDIO_MIDI);
        startIntent.addFlags(268435456);
        startIntent.addFlags(32768);
        addReftagAndStartActivity(startIntent);
    }

    private void onShowTrackDetail(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return;
        }
        String string = extras.getString("com.amazon.mp3.extra.ALBUM_ASIN");
        String string2 = extras.getString("com.amazon.mp3.extra.TRACK_ASIN");
        if (string2 == null) {
            return;
        }
        Intent startIntent = HTMLStoreActivityFactory.getStartIntent(this, HTMLStoreActivityFactory.bundleForTrack(string2, string));
        startIntent.addFlags(voOSType.VOOSMP_SRC_FFAUDIO_MIDI);
        startIntent.addFlags(268435456);
        startIntent.addFlags(32768);
        addReftagAndStartActivity(startIntent);
    }

    private void onShowNowPlaying() {
        if (PlaybackControllerProvider.getController(ControlSource.NONE).canPlay()) {
            startActivity(LibraryActivityFactory.getIntentForContentUri(this, MediaProvider.NowPlaying.CONTENT_URI));
        }
    }

    private void onTopMusicBrowse(Intent intent) {
        Bundle bundleBundleForBestsellingAlbums;
        Bundle extras = intent.getExtras();
        if ((extras != null ? extras.getInt("com.amazon.mp3.extra.BROWSE_TYPE", -1) : -1) == 0) {
            bundleBundleForBestsellingAlbums = HTMLStoreActivityFactory.bundleForBestsellingTracks(null);
        } else {
            bundleBundleForBestsellingAlbums = HTMLStoreActivityFactory.bundleForBestsellingAlbums(null);
        }
        Intent startIntent = HTMLStoreActivityFactory.getStartIntent(this, bundleBundleForBestsellingAlbums);
        startIntent.addFlags(voOSType.VOOSMP_SRC_FFAUDIO_MIDI);
        startIntent.addFlags(268435456);
        addReftagAndStartActivity(startIntent);
    }

    private void onShowCampaignDetail(Intent intent) {
        String string;
        Bundle extras = intent.getExtras();
        if (extras == null || (string = extras.getString("com.amazon.mp3.extra.EXTRA_CAMPAIGN_ROUTE")) == null) {
            return;
        }
        Intent startIntent = HTMLStoreActivityFactory.getStartIntent(this, HTMLStoreActivityFactory.bundleForCampaignDetail(string));
        startIntent.addFlags(voOSType.VOOSMP_SRC_FFAUDIO_MIDI);
        startIntent.addFlags(268435456);
        addReftagAndStartActivity(startIntent);
    }

    public static Intent onAnywhereBrowseIntent(Context context, Uri data) {
        String queryParameter;
        String queryParameter2;
        Intent startIntent = HTMLStoreActivityFactory.getStartIntent(context);
        if (!isMarketplaceLocaleMatched(data.getHost())) {
            return startIntent;
        }
        if (data.getPath().contains("cplanding")) {
            List<String> pathSegments = data.getPathSegments();
            if (pathSegments.size() > 3) {
                queryParameter = pathSegments.get(2);
                queryParameter2 = pathSegments.get(3);
                if ("musician".equalsIgnoreCase(queryParameter)) {
                    queryParameter = "artist";
                }
            } else {
                queryParameter2 = null;
                queryParameter = null;
            }
        } else {
            queryParameter = data.getQueryParameter(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            queryParameter2 = data.getQueryParameter("ASIN");
        }
        if (queryParameter == null || queryParameter2 == null || queryParameter2.length() == 0) {
            return startIntent;
        }
        if ("track".equalsIgnoreCase(queryParameter)) {
            Intent intentIntentForSearch = HTMLStoreActivityFactory.intentForSearch(context, HTMLStoreActivityFactory.bundleForSearchString(queryParameter2, 0));
            intentIntentForSearch.addFlags(voOSType.VOOSMP_SRC_FFAUDIO_MIDI);
            intentIntentForSearch.addFlags(268435456);
            return intentIntentForSearch;
        }
        if ("song".equalsIgnoreCase(queryParameter)) {
            return HTMLStoreActivityFactory.getStartIntent(context, HTMLStoreActivityFactory.bundleForAlbum(null, queryParameter2));
        }
        if ("album".equalsIgnoreCase(queryParameter)) {
            return HTMLStoreActivityFactory.getStartIntent(context, HTMLStoreActivityFactory.bundleForAlbum(queryParameter2, data.getQueryParameter("trackASIN")));
        }
        return "artist".equalsIgnoreCase(queryParameter) ? HTMLStoreActivityFactory.intentForSearch(context, HTMLStoreActivityFactory.bundleForSearchString(queryParameter2)) : startIntent;
    }

    private void onAnywhereBrowse(Intent intent) {
        Uri data = intent.getData();
        if (!isMarketplaceLocaleMatched(data.getHost())) {
            HTMLStoreActivityFactory.startWithAnywhereLocaleMismatchDialog(this, data.getHost());
        } else {
            addReftagAndStartActivity(onAnywhereBrowseIntent(this, data));
        }
    }

    private static boolean isMarketplaceLocaleMatched(String host) {
        return StoreUrls.isStoreDomain(host) || HlsSegmentFormat.MP3.equals(host);
    }

    private void onSearch(Intent intent, String referrerName) {
        if (intent.getExtras() != null) {
            String stringExtra = intent.getStringExtra("com.amazon.mp3.extra.SEARCH_STRING");
            int intExtra = intent.getIntExtra("com.amazon.mp3.extra.SEARCH_TYPE", 0);
            if (stringExtra == null || intExtra == -1) {
                return;
            }
            Intent intentIntentForSearch = HTMLStoreActivityFactory.intentForSearch(this, HTMLStoreActivityFactory.bundleForSearchString(stringExtra, intExtra));
            intentIntentForSearch.addFlags(voOSType.VOOSMP_SRC_FFAUDIO_MIDI);
            intentIntentForSearch.addFlags(268435456);
            addReftagAndStartActivity(intentIntentForSearch);
        }
    }

    private void onLibrarySearch(Intent intent) {
        if (TextUtils.isEmpty(intent.getStringExtra("com.amazon.mp3.extra.SEARCH_STRING"))) {
            return;
        }
        startActivity(MusicHomeActivity.getStartIntent(this, LastViewedScreenUtil.LastViewedSource.CLOUD, LastViewedScreenUtil.LastViewedScreen.LIBRARY));
    }

    public static String getReferrerName(Uri uri) {
        String lastPathSegment = uri.getLastPathSegment();
        String strSubstring = (lastPathSegment == null || !lastPathSegment.startsWith("ref=")) ? null : lastPathSegment.substring(4);
        return strSubstring == null ? uri.getQueryParameter("ref") : strSubstring;
    }

    private void addReftagAndStartActivity(Intent intent) {
        intent.putExtra("com.amazon.mp3.extra.REFTAG", this.mReftag);
        intent.putExtra("com.amazon.mp3.launch.EXTERNAL", true);
        startActivity(intent);
    }
}
