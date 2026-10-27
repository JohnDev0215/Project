package com.amazon.mp3.download;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.digitalmusicplayback.DownloadPriority;
import com.amazon.digitalmusicplayback.DownloadReason;
import com.amazon.digitalmusicplayback.StorageInitError;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.DownloadDialogActivity;
import com.amazon.mp3.activity.GrantPermissionActivity;
import com.amazon.mp3.activity.GrantStoragePermissionActivity;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.generator.CannotGenerateGroupException;
import com.amazon.mp3.download.generator.GroupGenerator;
import com.amazon.mp3.download.generator.ItemGenerator;
import com.amazon.mp3.download.generator.PrimePlaylistGroupGenerator;
import com.amazon.mp3.download.helper.PrimePlaylistDownloadHelper;
import com.amazon.mp3.download.helper.UdoPlaylistDownloadHelper;
import com.amazon.mp3.download.widevineMigration.WidevineDownloadStateListener;
import com.amazon.mp3.library.dialog.DownloadRetryDialog;
import com.amazon.mp3.library.dialog.listener.DownloadRetryDialogListener;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.local.DeleteListener;
import com.amazon.mp3.library.provider.source.local.DeleteUtil;
import com.amazon.mp3.library.provider.source.local.SDCardUtil;
import com.amazon.mp3.playback.harley.DownloadProperties;
import com.amazon.mp3.playback.harley.HarleyDownloadProperties;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.StorageInfo;
import com.amazon.mp3.util.extensions.CollectionKt;
import com.amazon.mp3.util.extensions.ContextKt;
import com.amazon.music.downloads.DMMDownloadCoordinator;
import com.amazon.music.downloads.DownloadManager;
import com.amazon.music.downloads.DownloadState;
import com.amazon.music.downloads.ErrorReason;
import com.amazon.music.downloads.Group;
import com.amazon.music.downloads.Item;
import com.amazon.music.downloads.MusicDownloadManager;
import com.amazon.music.downloads.PriorityInfo;
import com.amazon.music.downloads.RequestItem;
import com.amazon.music.downloads.RequestProvider;
import com.amazon.music.downloads.notification.DownloadListener;
import com.amazon.music.downloads.notification.NotificationInfo;
import com.amazon.music.externalstorage.DeviceStorageConfiguration;
import com.amazon.music.externalstorage.ExternalStorageNotSupportedException;
import com.amazon.music.externalstorage.StorageLocation;
import com.amazon.music.externalstorage.StorageLocationFileManager;
import com.amazon.music.externalstorage.StorageLocationPreference;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Actions;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes3.dex */
public class MusicDownloader implements DownloadManager {
    private static volatile MusicDownloader sMusicDownloader;
    private final Context mContext;
    private final DownloaderListenerDispatcher mDownloadListenerDispatcher;
    private MusicDownloadManager mDownloadManager;
    private final Handler mForegroundHandler;
    private boolean mIsLoadingDownloadCoordinator;
    private MusicDownloadListener mMusicDownloadListener;
    ThreadPoolExecutor mSequentialExecutor = new ThreadPoolExecutor(0, 1, 5, TimeUnit.SECONDS, new LinkedBlockingQueue());
    private final StorageLocationPreference mStorageLocationPreference;
    final Action1<Throwable> onInitError;
    private StorageInitError storageInitError;
    public WidevineDownloadStateListener widevineDownloadStateListener;
    public static final String[] PERMISSIONS_FOR_DOWNLOAD = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
    private static volatile boolean createNewInstance = false;
    private static final String TAG = MusicDownloader.class.getSimpleName();

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isStoragePathInternal(String path) {
        DownloadProperties currentDownloadProperties;
        if (path == null || (currentDownloadProperties = DownloadCoordinatorProvider.INSTANCE.getDownloadCoordinatorWrapper().getCurrentDownloadProperties()) == null) {
            return false;
        }
        try {
            return path.startsWith(((HarleyDownloadProperties) currentDownloadProperties).getStorageLocationFileManager().getStorageDirectory(StorageLocation.DEVICE, StorageLocationFileManager.Visibility.PRIVATE).getAbsolutePath());
        } catch (ExternalStorageNotSupportedException | FileNotFoundException unused) {
            return false;
        }
    }

    private MusicDownloader(Context context) {
        Action1<Throwable> action1 = new Action1<Throwable>() { // from class: com.amazon.mp3.download.MusicDownloader.1
            @Override // rx.functions.Action1
            public void call(final Throwable throwable) {
                boolean z = false;
                MusicDownloader.this.mIsLoadingDownloadCoordinator = false;
                Log.error(MusicDownloader.TAG, "An Error occurred while trying to initialize the downlaod coordinator:\n %s", CollectionKt.toString(throwable.getStackTrace(), "\n"));
                if (throwable instanceof HarleyInitErrorException) {
                    MusicDownloader.this.storageInitError = ((HarleyInitErrorException) throwable).getStorageInitError();
                    MusicDownloader musicDownloader = MusicDownloader.this;
                    boolean zIsStoragePathInternal = musicDownloader.isStoragePathInternal(musicDownloader.storageInitError.getStorageLocation());
                    boolean zEquals = Environment.getExternalStorageState().equals("mounted_ro");
                    if (!zIsStoragePathInternal && zEquals) {
                        z = true;
                    }
                    MetricsLogger.sendEvent(FlexEvent.builder("userGetInitErrorForDownloadCoordinator").withFlexStr1(MusicDownloader.this.storageInitError.getStorageError().getCode().name()).withFlexStr2("isStorageReadOnly: " + z).withFlexStr3(MusicDownloader.this.storageInitError.getStorageError().getDescriptor()).withFlexStr4(MusicDownloader.this.storageInitError.getStorageLocation()).build());
                    Log.error(MusicDownloader.TAG, "Sending out storageInitError flex event: " + MusicDownloader.this.storageInitError.toString());
                    Log.error(MusicDownloader.TAG, "isStorageInitErrorPathInternal: " + zIsStoragePathInternal);
                    Log.error(MusicDownloader.TAG, "isExternalStorageAvailable: " + StorageInfo.getInstance().isExternalStorageAvailable());
                } else {
                    MusicDownloader.this.storageInitError = null;
                }
                if (MusicDownloader.this.mStorageLocationPreference.getPreferredStorageLocation() == StorageLocation.SDCARD && SDCardUtil.isSdCardReadOnly(MusicDownloader.this.mContext)) {
                    BauhausToastUtils.showTextToast(MusicDownloader.this.mContext, R.string.sdcard_storage_error_read_only, 1);
                    Log.error(MusicDownloader.TAG, "SDCard is read only, not able to download");
                }
            }
        };
        this.onInitError = action1;
        this.mIsLoadingDownloadCoordinator = false;
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mForegroundHandler = new Handler(applicationContext.getMainLooper());
        this.mDownloadListenerDispatcher = new DownloaderListenerDispatcher(true);
        this.mDownloadManager = new MusicDownloadManager(applicationContext, NoOpDownloadCoordinatorWrapper.INSTANCE);
        this.mStorageLocationPreference = new StorageLocationPreference(context);
        this.mMusicDownloadListener = new MusicDownloadListener(context);
        if (hasStoragePermissions()) {
            loadDownloadCoordinatorObservable().subscribe(Actions.empty(), action1);
        }
        if (SettingsUtil.getBooleanPref(applicationContext, R.string.setting_key_completed_widevine_migration)) {
            return;
        }
        WidevineDownloadStateListener widevineDownloadStateListener = new WidevineDownloadStateListener(context);
        this.widevineDownloadStateListener = widevineDownloadStateListener;
        registerDownloadListener(widevineDownloadStateListener);
    }

    public boolean hasStoragePermissions() {
        return ContextKt.isPermissionGranted(this.mContext, PERMISSIONS_FOR_DOWNLOAD);
    }

    public static MusicDownloader getInstance(Context context) {
        if (sMusicDownloader == null || createNewInstance) {
            synchronized (MusicDownloader.class) {
                if (sMusicDownloader == null || createNewInstance) {
                    sMusicDownloader = new MusicDownloader(context);
                    createNewInstance = false;
                }
            }
        }
        return sMusicDownloader;
    }

    public static synchronized void setCreateNewInstanceFlag() {
        createNewInstance = true;
    }

    public void enqueueEndOfDownloadCollectionTask(final Runnable task) {
        MusicDownloadListener musicDownloadListener = this.mMusicDownloadListener;
        if (musicDownloadListener != null) {
            musicDownloadListener.enqueueEndOfCollectionTask(task);
        }
    }

    public void download(@NonNull final String requestId, @NonNull final Uri uri, final NotificationInfo info, final boolean downloadNonAddedPrimeTracks, final boolean userInitiated) {
        internalDownload(requestId, uri, info, downloadNonAddedPrimeTracks, userInitiated);
    }

    public void download(@NonNull final String requestId, @NonNull final Uri uri, final NotificationInfo info, final boolean downloadNonAddedPrimeTracks, final boolean userInitiated, final PriorityInfo priorityInfo) {
        internalDownload(requestId, uri, info, downloadNonAddedPrimeTracks, userInitiated, priorityInfo);
    }

    public void download(@NonNull final String requestId, @NonNull final Uri uri, final NotificationInfo info, final boolean downloadNonAddedPrimeTracks, final boolean userInitiated, final PriorityInfo priorityInfo, final boolean forceRefresh) {
        internalDownload(requestId, uri, info, downloadNonAddedPrimeTracks, userInitiated, priorityInfo, forceRefresh);
    }

    public void redownload(@NonNull final String requestId, @NonNull final Uri uri, final NotificationInfo info, final boolean downloadNonAddedPrimeTracks, final boolean userInitiated) {
        DeleteUtil.deleteTracksFromDevice(this.mContext, uri);
        internalDownload(requestId, uri, info, downloadNonAddedPrimeTracks, userInitiated);
    }

    public boolean isDownloading() {
        return this.mDownloadManager.isDownloading();
    }

    private void internalDownload(@NonNull final String requestId, @NonNull final Uri uri, final NotificationInfo info, final boolean downloadNonAddedPrimeTracks, final boolean userInitiated) {
        internalDownload(requestId, uri, info, downloadNonAddedPrimeTracks, userInitiated, null);
    }

    private void internalDownload(@NonNull final String requestId, @NonNull final Uri uri, final NotificationInfo info, final boolean downloadNonAddedPrimeTracks, final boolean userInitiated, final PriorityInfo priorityInfo) {
        internalDownload(requestId, uri, info, downloadNonAddedPrimeTracks, userInitiated, priorityInfo, false);
    }

    private void internalDownload(@NonNull final String requestId, @NonNull final Uri uri, final NotificationInfo info, final boolean downloadNonAddedPrimeTracks, final boolean userInitiated, final PriorityInfo priorityInfo, final boolean forceRefresh) {
        Context context = this.mContext;
        String[] strArr = PERMISSIONS_FOR_DOWNLOAD;
        final boolean z = !GrantPermissionActivity.areAllGranted(context, strArr);
        GrantStoragePermissionActivity.requestIfNotGranted(this.mContext, strArr, new GrantPermissionActivity.PermissionResultReceiver() { // from class: com.amazon.mp3.download.MusicDownloader.2
            @Override // com.amazon.mp3.activity.GrantPermissionActivity.PermissionResultReceiver
            protected void onGranted() {
                if (userInitiated) {
                    if (!ConnectivityUtil.isWifiRequiredForDownloads(MusicDownloader.this.mContext) || ConnectivityUtil.isWifiConnected(MusicDownloader.this.mContext)) {
                        BauhausToastUtils.showTextToast(MusicDownloader.this.mContext, R.string.dmusic_download_queue_started, 1);
                    } else {
                        Intent startIntent = DownloadDialogActivity.getStartIntent(MusicDownloader.this.mContext, 1);
                        startIntent.setFlags(268435456);
                        MusicDownloader.this.mContext.startActivity(startIntent);
                    }
                }
                Observable observableLoadDownloadCoordinatorObservable = MusicDownloader.this.loadDownloadCoordinatorObservable();
                MusicDownloader musicDownloader = MusicDownloader.this;
                observableLoadDownloadCoordinatorObservable.compose(musicDownloader.applyScheduler(Schedulers.from(musicDownloader.mSequentialExecutor))).subscribe(new Action1<DownloadCoordinatorWrapperInterface>() { // from class: com.amazon.mp3.download.MusicDownloader.2.1
                    @Override // rx.functions.Action1
                    public void call(final DownloadCoordinatorWrapperInterface downloadCoordinatorWrapperInterface) {
                        if (z) {
                            downloadCoordinatorWrapperInterface.updateConfigOnCurrentThread(new HarleyDownloadProperties());
                        }
                        if (!ConnectivityUtil.isWifiRequiredForDownloads(MusicDownloader.this.mContext) || ConnectivityUtil.isWifiConnected(MusicDownloader.this.mContext)) {
                            if (MusicDownloader.this.mStorageLocationPreference.getPreferredStorageLocation() != StorageLocation.SDCARD || MusicDownloader.this.canUseSdCard(downloadCoordinatorWrapperInterface)) {
                                if (MusicDownloader.this.storageInitError != null && MusicDownloader.this.mStorageLocationPreference.getPreferredStorageLocation() == StorageLocation.DEVICE) {
                                    MusicDownloader musicDownloader2 = MusicDownloader.this;
                                    if (!musicDownloader2.isStoragePathInternal(musicDownloader2.storageInitError.getStorageLocation())) {
                                        Log.warning(MusicDownloader.TAG, "primary storage is the device but init error is happening on " + MusicDownloader.this.storageInitError.getStorageLocation());
                                    }
                                }
                                AnonymousClass2 anonymousClass2 = AnonymousClass2.this;
                                MusicDownloader.this.doDownload(uri, requestId, info, priorityInfo, downloadNonAddedPrimeTracks, forceRefresh);
                            }
                        }
                    }
                }, MusicDownloader.this.onInitError);
            }

            @Override // com.amazon.mp3.activity.GrantPermissionActivity.PermissionResultReceiver
            protected void onDenied(String[] permissions) {
                Log.error(MusicDownloader.TAG, "Do not have adequate permissions to download");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean canUseSdCard(final DownloadCoordinatorWrapperInterface downloadCoordinatorWrapperInterface) {
        boolean zSecondaryStoragePresent = AmazonApplication.getCapabilities().secondaryStoragePresent(this.mContext);
        DownloadProperties currentDownloadProperties = downloadCoordinatorWrapperInterface.getCurrentDownloadProperties();
        if (zSecondaryStoragePresent && SDCardUtil.isSdCardReadOnly(this.mContext)) {
            BauhausToastUtils.showTextToast(this.mContext, R.string.sdcard_storage_error_read_only, 1);
            Log.error(TAG, "SDCard is read only, not able to download");
            return false;
        }
        if (currentDownloadProperties == null || currentDownloadProperties.getMaximumStorageSize() != 0) {
            return true;
        }
        BauhausToastUtils.showTextToast(this.mContext, R.string.sdcard_storage_error_unmounted, 1);
        Log.error(TAG, "SDCard is unmounted, not able to download.  sdcardMounted = " + zSecondaryStoragePresent);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doDownload(@NonNull final Uri uri, @NonNull final String requestId, final NotificationInfo info, final PriorityInfo priorityInfo, final boolean downloadNonAddedPrimeTracks, final boolean forceRefresh) {
        DownloadCoordinatorProvider.INSTANCE.getDownloadCoordinatorWrapper().updateConfigOnCurrentThread(new HarleyDownloadProperties());
        if (MediaProvider.PrimePlaylists.isPrimePlaylist(uri) || MediaProvider.SharedUserPlaylists.isSharedUserPlaylist(uri)) {
            downloadPrimeOrSharedPlaylist(uri, requestId, info, priorityInfo, forceRefresh);
        } else if (MediaProvider.Tracks.isTrackCollection(uri, new String[0])) {
            downloadTracksCollection(uri, downloadNonAddedPrimeTracks, requestId, info, priorityInfo);
        } else if (MediaProvider.Tracks.isTrack(uri)) {
            downloadTrack(uri, downloadNonAddedPrimeTracks, requestId, priorityInfo);
        }
    }

    private void downloadTrack(Uri uri, boolean downloadNonAddedPrimeTracks, String requestId, PriorityInfo priorityInfo) {
        String str = TAG;
        Log.debug(str, "Downloading track: " + uri);
        ItemGenerator itemGenerator = new ItemGenerator(this.mContext, getStorageLocationFileManager());
        if (priorityInfo == null) {
            priorityInfo = new PriorityInfo(DownloadPriority.USERINITIATED.name(), DownloadReason.USERINITIATED.name());
        }
        Item itemGenerate = itemGenerator.generate(uri, downloadNonAddedPrimeTracks, priorityInfo, null);
        if (itemGenerate != null && requestId != null) {
            download(requestId, itemGenerate);
            return;
        }
        Log.error(str, "Could not start item download. Uri: " + uri + " item: " + itemGenerate + " request: " + requestId);
        displayDownloadFailedToast();
    }

    private void downloadTracksCollection(Uri uri, boolean downloadNonAddedPrimeTracks, String requestId, NotificationInfo info, PriorityInfo priorityInfo) {
        String str = TAG;
        Log.debug(str, "Downloading collection: " + uri);
        GroupGenerator groupGenerator = new GroupGenerator(this.mContext, uri, info.getTitle(), info.getDescription(), info.getImageUri(), getStorageLocationFileManager());
        if (priorityInfo == null) {
            priorityInfo = new PriorityInfo(DownloadPriority.USERINITIATED.name(), DownloadReason.USERINITIATED.name());
        }
        Group groupGenerate = groupGenerator.generate(downloadNonAddedPrimeTracks, priorityInfo, null);
        if (MediaProvider.UdoPlaylists.isUdoPlaylist(uri)) {
            MetricsLogger.userPlaylistDownloaded(MediaProvider.Playlists.getPlaylistLuid(uri));
        }
        if (groupGenerate != null && requestId != null) {
            download(requestId, groupGenerate);
            return;
        }
        if (MediaProvider.UdoPlaylists.isUdoPlaylist(uri)) {
            UdoPlaylistDownloadHelper udoPlaylistDownloadHelper = new UdoPlaylistDownloadHelper(this.mContext);
            DownloadState downloadState = DownloadState.DOWNLOADED;
            udoPlaylistDownloadHelper.updateDownloadState(uri, toAppDownloadState(downloadState));
            udoPlaylistDownloadHelper.createLocalPlaylist(ContentPlaybackUtils.INSTANCE.removeTracksFromUriIfNeeded(uri));
            this.mDownloadListenerDispatcher.onStatusUpdate(downloadState, requestId, groupGenerate);
            return;
        }
        Log.error(str, "Could not start group download. Uri: " + uri + " group: " + groupGenerate + " request: " + requestId);
        displayDownloadFailedToast();
    }

    private void downloadPrimeOrSharedPlaylist(final Uri uri, final String requestId, final NotificationInfo info, final PriorityInfo priorityInfo, final boolean forceRefresh) {
        if (requestId == null || uri == null || info == null) {
            Log.error(TAG, "Could not start playlist download as requestid/uri/NotificationInfo  is null");
            displayDownloadFailedToast();
            return;
        }
        Log.debug(TAG, "Downloading Prime or Shared Playlist: " + uri);
        try {
            PrimePlaylistGroupGenerator primePlaylistGroupGenerator = new PrimePlaylistGroupGenerator(uri, this.mContext, info.getTitle(), info.getDescription(), info.getImageUri(), getStorageLocationFileManager());
            if (priorityInfo == null) {
                priorityInfo = new PriorityInfo(DownloadPriority.USERINITIATED.name(), DownloadReason.USERINITIATED.name());
            }
            download(requestId, primePlaylistGroupGenerator.generate(priorityInfo, null));
            MetricsLogger.primePlaylistDownloaded(MediaProvider.PrimePlaylists.getPlaylistAsin(uri));
        } catch (CannotGenerateGroupException e) {
            String str = TAG;
            Log.debug(str, "Group is null. Uri: " + uri + " request: " + requestId + " reason: " + e.reason.name());
            if (e.reason == CannotGenerateGroupException.Reason.AlreadyDownloaded || forceRefresh) {
                Log.debug(str, "Attempt to refresh Local Playlist: " + uri);
                refreshDownloadedPrimeOrSharedPlaylist(uri);
            }
        }
    }

    public void download(@NonNull final String requestId, @NonNull final Item item) {
        loadDownloadCoordinatorObservable().compose(applyScheduler(getDownloadScheduler())).subscribe(new Action1<DownloadCoordinatorWrapperInterface>() { // from class: com.amazon.mp3.download.MusicDownloader.3
            @Override // rx.functions.Action1
            public void call(final DownloadCoordinatorWrapperInterface downloadCoordinatorWrapperInterface) {
                MusicDownloader.this.mDownloadManager.download(requestId, item);
                MusicDownloader.this.notifyNewRequest();
            }
        }, this.onInitError);
    }

    public void download(@NonNull final String requestId, @NonNull final Group group) {
        loadDownloadCoordinatorObservable().compose(applyScheduler(getDownloadScheduler())).subscribe(new Action1<DownloadCoordinatorWrapperInterface>() { // from class: com.amazon.mp3.download.MusicDownloader.4
            @Override // rx.functions.Action1
            public void call(final DownloadCoordinatorWrapperInterface downloadCoordinatorWrapperInterface) {
                MusicDownloader.this.mDownloadManager.download(requestId, group);
                MusicDownloader.this.notifyNewRequest();
            }
        }, this.onInitError);
    }

    @Override // com.amazon.music.downloads.DownloadManager
    public void pause(@NonNull final String requestId) {
        this.mDownloadManager.pause(requestId);
    }

    @Override // com.amazon.music.downloads.DownloadManager
    public void resume(@NonNull final String requestId) {
        this.mDownloadManager.resume(requestId);
    }

    @Override // com.amazon.music.downloads.DownloadManager
    public void cancel(@NonNull final String requestId) {
        this.mDownloadManager.cancel(requestId);
    }

    @Override // com.amazon.music.downloads.DownloadManager
    @NonNull
    public RequestProvider getRequestProvider() {
        return this.mDownloadManager.getRequestProvider();
    }

    public Item getItem(@NonNull final String requestId) {
        return this.mDownloadManager.getItem(requestId);
    }

    public Group getGroup(@NonNull final String requestId) {
        return this.mDownloadManager.getGroup(requestId);
    }

    @Override // com.amazon.music.downloads.DownloadManager
    public void registerDownloadListener(@NonNull final DownloadListener listener) {
        this.mDownloadListenerDispatcher.registerListener(listener);
    }

    @Override // com.amazon.music.downloads.DownloadManager
    public void unregisterDownloadListener(@NonNull final DownloadListener listener) {
        this.mDownloadListenerDispatcher.unregisterListener(listener);
    }

    public void resetDownloadCoordinatorObject(@NonNull DMMDownloadCoordinator downloadCoordinator) {
        Log.warning(TAG, "resetting download coordinator");
        MusicDownloadManager musicDownloadManager = this.mDownloadManager;
        if (musicDownloadManager != null) {
            musicDownloadManager.resetDownloadCoordinatorObject(downloadCoordinator);
            this.mDownloadManager.registerDownloadListener(this.mDownloadListenerDispatcher);
            this.mDownloadManager.registerDownloadListener(this.mMusicDownloadListener);
        }
    }

    private void refreshDownloadedPrimeOrSharedPlaylist(final Uri uri) {
        try {
            PrimePlaylistDownloadHelper primePlaylistDownloadHelper = new PrimePlaylistDownloadHelper(this.mContext);
            primePlaylistDownloadHelper.updateDownloadState(uri, 0);
            primePlaylistDownloadHelper.createLocalPlaylist(uri);
        } catch (Exception e) {
            Log.error(TAG, "Error refreshing prime playlist download", e);
        }
    }

    private Scheduler getDownloadScheduler() {
        HandlerThread handlerThread = new HandlerThread("TriggerDownloadHandlerThread");
        handlerThread.start();
        return AndroidSchedulers.from(handlerThread.getLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Observable.Transformer<DownloadCoordinatorWrapperInterface, DownloadCoordinatorWrapperInterface> applyScheduler(final Scheduler observeOnScheduler) {
        return new Observable.Transformer<DownloadCoordinatorWrapperInterface, DownloadCoordinatorWrapperInterface>() { // from class: com.amazon.mp3.download.MusicDownloader.5
            @Override // rx.functions.Func1
            public Observable<DownloadCoordinatorWrapperInterface> call(final Observable<DownloadCoordinatorWrapperInterface> downloadCoordinatorWrapperInterfaceObservable) {
                return downloadCoordinatorWrapperInterfaceObservable.observeOn(observeOnScheduler);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyNewRequest() {
        LocalBroadcastManager.getInstance(this.mContext).sendBroadcast(new Intent("com.amazon.music.download.NEW_DOWNLOAD_REQUEST"));
    }

    public static boolean isGroup(@NonNull Uri uri, @NonNull Context context) {
        return MediaProvider.Tracks.isTrackCollection(uri, context.getResources().getString(R.string.dmusic_redownload_group_name_prime_playlists), context.getResources().getString(R.string.dmusic_redownload_group_name_tracks));
    }

    /* JADX INFO: renamed from: com.amazon.mp3.download.MusicDownloader$12, reason: invalid class name */
    static /* synthetic */ class AnonymousClass12 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$downloads$DownloadState;

        static {
            int[] iArr = new int[DownloadState.values().length];
            $SwitchMap$com$amazon$music$downloads$DownloadState = iArr;
            try {
                iArr[DownloadState.QUEUED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.PAUSED_USER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.PAUSED_POLICY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.PAUSED_PRIORITY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.CANCELED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.DOWNLOADING.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.DOWNLOADED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.ERROR.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public static int toAppDownloadState(@NonNull DownloadState downloadState) {
        switch (AnonymousClass12.$SwitchMap$com$amazon$music$downloads$DownloadState[downloadState.ordinal()]) {
            case 1:
                return 1;
            case 2:
            case 3:
            case 4:
                return 3;
            case 5:
            default:
                return 5;
            case 6:
                return 4;
            case 7:
                return 0;
            case 8:
                return 2;
        }
    }

    public void cancelAllDownloads() {
        this.mDownloadManager.getRequestProvider().getRequests(new RequestProvider.RequestProviderCallback() { // from class: com.amazon.mp3.download.MusicDownloader.6
            @Override // com.amazon.music.downloads.RequestProvider.RequestProviderCallback
            public void requestsSubmitted(final Set<RequestItem> set) {
                Iterator<RequestItem> it = set.iterator();
                while (it.hasNext()) {
                    MusicDownloader.this.mDownloadManager.cancel(it.next().getRequestId());
                }
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void showRetryDialog(android.content.Context r3, android.net.Uri r4, java.lang.String r5) {
        /*
            com.amazon.mp3.download.MusicDownloader r0 = getInstance(r3)
            boolean r1 = isGroup(r4, r3)
            r2 = 0
            if (r1 == 0) goto L1c
            com.amazon.music.downloads.Group r0 = r0.getGroup(r5)
            if (r0 == 0) goto L2b
            com.amazon.music.downloads.notification.NotificationInfo r1 = r0.getNotificationInfo()
            com.amazon.music.downloads.ErrorReason r2 = r0.getErrorReason()
            r0 = r2
            r2 = r1
            goto L2c
        L1c:
            com.amazon.music.downloads.Item r0 = r0.getItem(r5)
            if (r0 == 0) goto L2b
            com.amazon.music.downloads.notification.NotificationInfo r2 = r0.getNotificationInfo()
            com.amazon.music.downloads.ErrorReason r0 = r0.getErrorReason()
            goto L2c
        L2b:
            r0 = r2
        L2c:
            if (r2 == 0) goto L36
            com.amazon.mp3.library.dialog.listener.DownloadRetryDialogListener r1 = new com.amazon.mp3.library.dialog.listener.DownloadRetryDialogListener
            r1.<init>(r3, r5, r4, r2)
            showRetryDialog(r3, r1, r0)
        L36:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.download.MusicDownloader.showRetryDialog(android.content.Context, android.net.Uri, java.lang.String):void");
    }

    private static void showRetryDialog(final Context context, final DownloadRetryDialogListener listener, final ErrorReason error) {
        new Handler(context.getMainLooper()).post(new Runnable() { // from class: com.amazon.mp3.download.MusicDownloader$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                MusicDownloader.lambda$showRetryDialog$0(context, error, listener);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showRetryDialog$0(Context context, ErrorReason errorReason, DownloadRetryDialogListener downloadRetryDialogListener) {
        DownloadRetryDialog.create(context, errorReason, downloadRetryDialogListener).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Observable<DownloadCoordinatorWrapperInterface> loadDownloadCoordinatorObservable() {
        DownloadCoordinatorProvider downloadCoordinatorProvider = DownloadCoordinatorProvider.INSTANCE;
        if (downloadCoordinatorProvider.isInitialized()) {
            return Observable.just(downloadCoordinatorProvider.getDownloadCoordinatorWrapper());
        }
        if (this.mIsLoadingDownloadCoordinator || downloadCoordinatorProvider.isInitializing() || !hasStoragePermissions()) {
            return Observable.just(NoOpDownloadCoordinatorWrapper.INSTANCE);
        }
        this.mIsLoadingDownloadCoordinator = true;
        final AtomicInteger atomicInteger = new AtomicInteger();
        final boolean zIsInitialized = downloadCoordinatorProvider.isInitialized();
        if (!zIsInitialized) {
            Log.warning(TAG, "Initializing Download Coordinator.\n%s", CollectionKt.toString(Thread.currentThread().getStackTrace(), "\n"));
        }
        return Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.download.MusicDownloader$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$loadDownloadCoordinatorObservable$1(zIsInitialized, (Subscriber) obj);
            }
        }).retryWhen(new Func1() { // from class: com.amazon.mp3.download.MusicDownloader$$ExternalSyntheticLambda1
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return MusicDownloader.lambda$loadDownloadCoordinatorObservable$3(atomicInteger, (Observable) obj);
            }
        }).subscribeOn(Schedulers.computation());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadDownloadCoordinatorObservable$1(boolean z, Subscriber subscriber) {
        synchronized (MusicDownloader.class) {
            DownloadCoordinatorWrapperInterface downloadCoordinatorWrapper = DownloadCoordinatorProvider.INSTANCE.getDownloadCoordinatorWrapper();
            if (downloadCoordinatorWrapper instanceof NoOpDownloadCoordinatorWrapper) {
                Log.info(TAG, "Retrying to fetch DownloadCoordinator");
                subscriber.onError(new RuntimeException("Couldn't fetch DownloadCoordinator"));
            } else if (downloadCoordinatorWrapper.getInitError() != null && !hasSecondaryStorageError(downloadCoordinatorWrapper)) {
                StorageInitError initError = downloadCoordinatorWrapper.getInitError();
                Log.info(TAG, "DownloadCoordinator has initialization error: " + initError.getStorageError().getCode().name());
                subscriber.onError(new HarleyInitErrorException("DownloadCoordinator has initialization errors", initError));
            } else {
                if (!z) {
                    resetDownloadCoordinatorObject(downloadCoordinatorWrapper);
                    destroyQueueWorkerOnNoRequests();
                }
                subscriber.onNext(downloadCoordinatorWrapper);
                subscriber.onCompleted();
                this.mIsLoadingDownloadCoordinator = false;
                this.storageInitError = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Observable lambda$loadDownloadCoordinatorObservable$3(final AtomicInteger atomicInteger, Observable observable) {
        return observable.flatMap(new Func1() { // from class: com.amazon.mp3.download.MusicDownloader$$ExternalSyntheticLambda3
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return MusicDownloader.lambda$loadDownloadCoordinatorObservable$2(atomicInteger, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Observable lambda$loadDownloadCoordinatorObservable$2(AtomicInteger atomicInteger, Throwable th) {
        if (atomicInteger.incrementAndGet() < 10) {
            Log.info(TAG, "Waiting for 2 sec and will retry fetching DownloadCoordinator");
            return Observable.timer(2000L, TimeUnit.MILLISECONDS);
        }
        return Observable.error(th);
    }

    private boolean hasSecondaryStorageError(DownloadCoordinatorWrapperInterface dcWrapper) {
        return (dcWrapper.getInitError() == null || dcWrapper.getInitError().getStorageLocation() == null || dcWrapper.getCurrentDownloadProperties() == null || !dcWrapper.getInitError().getStorageLocation().equals(dcWrapper.getCurrentDownloadProperties().getAdditionalDownloadStoragePathForCatalog())) ? false : true;
    }

    private void displayDownloadFailedToast() {
        this.mForegroundHandler.post(new Runnable() { // from class: com.amazon.mp3.download.MusicDownloader.7
            @Override // java.lang.Runnable
            public void run() {
                BauhausToastUtils.showTextToast(MusicDownloader.this.mContext, R.string.dmusic_download_failed, 1);
            }
        });
    }

    public StorageLocationFileManager getStorageLocationFileManager() {
        return StorageLocationFileManager.create(this.mContext, new DeviceStorageConfiguration(this.mContext, Environment.DIRECTORY_MUSIC), Environment.DIRECTORY_MUSIC);
    }

    public String getDownloadState() {
        return this.widevineDownloadStateListener.getDownloadStateString();
    }

    public String getLastError() {
        return this.widevineDownloadStateListener.getLastErrorString();
    }

    public void unregisterMigrationListener() {
        WidevineDownloadStateListener widevineDownloadStateListener = this.widevineDownloadStateListener;
        if (widevineDownloadStateListener != null) {
            unregisterDownloadListener(widevineDownloadStateListener);
        }
    }

    public void updateHarleyConfig() {
        loadDownloadCoordinatorObservable().compose(applyScheduler(Schedulers.computation())).subscribe(new Action1<DownloadCoordinatorWrapperInterface>() { // from class: com.amazon.mp3.download.MusicDownloader.8
            @Override // rx.functions.Action1
            public void call(final DownloadCoordinatorWrapperInterface downloadCoordinatorWrapperInterface) {
                downloadCoordinatorWrapperInterface.updateConfigOnCurrentThread(new HarleyDownloadProperties());
            }
        }, this.onInitError);
    }

    public void setDownloadOnWifiOnly(final boolean downloadOnWifiChecked) {
        loadDownloadCoordinatorObservable().compose(applyScheduler(Schedulers.computation())).subscribe(new Action1<DownloadCoordinatorWrapperInterface>() { // from class: com.amazon.mp3.download.MusicDownloader.9
            @Override // rx.functions.Action1
            public void call(final DownloadCoordinatorWrapperInterface downloadCoordinatorWrapperInterface) {
                downloadCoordinatorWrapperInterface.setDownloadOnWifiOnly(downloadOnWifiChecked);
            }
        }, this.onInitError);
    }

    public void removeDownload(final String asin, @Nullable final DeleteListener deleteListener) {
        loadDownloadCoordinatorObservable().compose(applyScheduler(Schedulers.computation())).subscribe(new Action1<DownloadCoordinatorWrapperInterface>() { // from class: com.amazon.mp3.download.MusicDownloader.10
            @Override // rx.functions.Action1
            public void call(final DownloadCoordinatorWrapperInterface downloadCoordinatorWrapperInterface) {
                downloadCoordinatorWrapperInterface.removeDownload(asin, deleteListener);
            }
        }, this.onInitError);
    }

    @NonNull
    public Boolean isDownloadOnWifiOnly() {
        return Boolean.valueOf(SettingsUtil.getBooleanPref(this.mContext, R.string.setting_key_downloads_wifi_only_pref));
    }

    public boolean isTrackAvailableOffline(final String asin, final boolean isDrm) {
        return getCurrentDownloadCoordinator().isTrackAvailableOffline(asin, isDrm);
    }

    @Nullable
    public HarleyDownloadedItem getDownloadedItem(final String asin, final boolean isDrm) {
        return getCurrentDownloadCoordinator().getDownloadedItem(asin, isDrm);
    }

    public boolean isOfflineDRMLicenseAvailable() {
        return getCurrentDownloadCoordinator().isOfflineDRMLicenseAvailable();
    }

    @NonNull
    private synchronized DownloadCoordinatorWrapperInterface getCurrentDownloadCoordinator() {
        DownloadCoordinatorProvider downloadCoordinatorProvider = DownloadCoordinatorProvider.INSTANCE;
        if (downloadCoordinatorProvider.isInitialized()) {
            return downloadCoordinatorProvider.getDownloadCoordinatorWrapper();
        }
        initializeDownloadCoordinatorInBackground();
        return NoOpDownloadCoordinatorWrapper.INSTANCE;
    }

    private void initializeDownloadCoordinatorInBackground() {
        loadDownloadCoordinatorObservable().compose(applyScheduler(Schedulers.computation())).subscribe(Actions.empty(), this.onInitError);
    }

    public boolean hasSuitableConnection(final boolean isUserInitialized) {
        boolean zBooleanValue = isDownloadOnWifiOnly().booleanValue();
        boolean zIsWifiConnected = ConnectivityUtil.isWifiConnected(this.mContext);
        boolean zHasAnyInternetConnection = ConnectivityUtil.hasAnyInternetConnection(this.mContext);
        Log.debug(TAG, "isWifiOnly = %s, isWifiConnected = %s, hasAnyInternetConnection = %s, isUserInitialized = %s", Boolean.valueOf(zBooleanValue), Boolean.valueOf(zIsWifiConnected), Boolean.valueOf(zHasAnyInternetConnection), Boolean.valueOf(isUserInitialized));
        return zIsWifiConnected || (isUserInitialized && !zBooleanValue && zHasAnyInternetConnection);
    }

    private void destroyQueueWorkerOnNoRequests() {
        this.mDownloadManager.getRequestProvider().getRequests(new RequestProvider.RequestProviderCallback() { // from class: com.amazon.mp3.download.MusicDownloader.11
            @Override // com.amazon.music.downloads.RequestProvider.RequestProviderCallback
            public void requestsSubmitted(final Set<RequestItem> set) {
                if (set.isEmpty()) {
                    MusicDownloader.this.mDownloadManager.destroyQueueWorkerIfPossible();
                }
            }
        });
    }
}
