package com.amazon.avod.userdownload.internal;

import com.amazon.avod.acos.StorageHelper;
import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.http.ServiceClient;
import com.amazon.avod.media.download.internal.PlaybackDownloadQueueConfig;
import com.amazon.avod.media.framework.MediaSystemSharedDependencies;
import com.amazon.avod.threading.ExecutorBuilder;
import com.amazon.avod.userdownload.PlaybackDownloadEventReporter;
import com.amazon.avod.userdownload.PlaybackDownloadLocationConfig;
import com.google.common.base.Preconditions;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackDownloadSharedComponents {
    private final NetworkConnectionManager mConnectionManager;
    private final PlaybackDownloadNotifier mDownloadNotifier;
    private final PlaybackDownloadLocationConfig mLocationConfig;
    private final MediaSystemSharedDependencies mMediaSystemSharedDependencies;
    private final PlaybackDownloadEventReporter mPlaybackDownloadEventReporter;
    private final PlaybackDownloadQueueConfig mQueueConfig;
    private final ServiceClient mServiceClient;
    private final StorageHelper mStorageHelper;

    public PlaybackDownloadSharedComponents(@Nonnull MediaSystemSharedDependencies mediaSystemSharedDependencies, @Nonnull NetworkConnectionManager networkConnectionManager, @Nonnull ServiceClient serviceClient, @Nonnull StorageHelper storageHelper) {
        this.mMediaSystemSharedDependencies = (MediaSystemSharedDependencies) Preconditions.checkNotNull(mediaSystemSharedDependencies, "mediaSystemSharedDependencies");
        NetworkConnectionManager networkConnectionManager2 = (NetworkConnectionManager) Preconditions.checkNotNull(networkConnectionManager, "connectionManager");
        this.mConnectionManager = networkConnectionManager2;
        this.mServiceClient = (ServiceClient) Preconditions.checkNotNull(serviceClient, "serviceClient");
        StorageHelper storageHelper2 = (StorageHelper) Preconditions.checkNotNull(storageHelper, "storageHelper");
        this.mStorageHelper = storageHelper2;
        PlaybackDownloadQueueConfig playbackDownloadQueueConfig = new PlaybackDownloadQueueConfig();
        this.mQueueConfig = playbackDownloadQueueConfig;
        this.mLocationConfig = new PlaybackDownloadLocationConfig(storageHelper2);
        this.mDownloadNotifier = new PlaybackDownloadNotifier();
        this.mPlaybackDownloadEventReporter = new PlaybackDownloadEventReporter(playbackDownloadQueueConfig, networkConnectionManager2, executorCreation(PlaybackDownloadEventReporter.class));
    }

    @Nonnull
    protected ExecutorService executorCreation(@Nonnull Class<?> cls) {
        return ExecutorBuilder.newBuilderFor(cls, new String[0]).withFixedThreadPoolSize(1).allowCoreThreadExpiry().build();
    }

    @Nonnull
    public MediaSystemSharedDependencies getMediaSystemSharedDependencies() {
        return this.mMediaSystemSharedDependencies;
    }

    @Nonnull
    public StorageHelper getStorageHelper() {
        return this.mStorageHelper;
    }

    @Nonnull
    public PlaybackDownloadLocationConfig getLocationConfig() {
        return this.mLocationConfig;
    }

    @Nonnull
    public PlaybackDownloadNotifier getPlaybackDownloadNotifier() {
        return this.mDownloadNotifier;
    }

    @Nonnull
    public PlaybackDownloadQueueConfig getQueueConfig() {
        return this.mQueueConfig;
    }

    @Nonnull
    public PlaybackDownloadEventReporter getPlaybackDownloadEventReporter() {
        return this.mPlaybackDownloadEventReporter;
    }
}
