package com.amazon.avod.download;

import com.amazon.avod.acos.StorageHelper;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.ContentSessionEventListener;
import com.amazon.avod.content.ContentSessionFactory;
import com.amazon.avod.content.urlvending.AudioTrackMetadata;
import com.amazon.avod.content.urlvending.ContentUrlFetcher;
import com.amazon.avod.content.urlvending.CuepointPlaylistInfo;
import com.amazon.avod.download.Downloadable;
import com.amazon.avod.download.PlaybackDownloadExecutorTask;
import com.amazon.avod.drm.BaseDrmSystem;
import com.amazon.avod.fileio.DiskUtils;
import com.amazon.avod.media.download.plugin.ContentFetcherPlugin;
import com.amazon.avod.media.download.plugin.ContentFetcherPluginExecutor;
import com.amazon.avod.media.drm.DrmLicenseAcquirer;
import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.media.playback.support.RendererSchemeController;
import com.amazon.avod.playback.sampling.SampleType;
import com.amazon.avod.qos.listeners.QoSEventManagerFactory;
import com.amazon.avod.userdownload.PlaybackDownloads;
import com.amazon.avod.userdownload.internal.PlaybackDownloadJob;
import com.amazon.avod.util.DLog;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.SettableFuture;
import com.google.common.util.concurrent.Uninterruptibles;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.concurrent.ExecutionException;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
class PlaybackDownloadTask implements PlaybackDownloadExecutorTask, ContentSessionEventListener {
    private ContentFetcherPluginExecutor mContentFetcherPluginExecutor;
    private ContentSession mContentSession;
    private final ContentSessionFactory mContentSessionFactory;
    private final PlaybackDownloadListenerInternal mDownloadProgressListener;
    private final PlaybackDownloadJob mItem;
    private final DrmLicenseAcquirer mLicenseAcquirer;
    private final Object mMonitor;
    private final ImmutableList<Provider<? extends ContentFetcherPlugin>> mPluginProviders;
    private final QoSEventManagerFactory mQoSEventManagerFactory;
    private final RendererSchemeController mRendererSchemeController;
    private final SettableFuture<PlaybackDownloadExecutorTask.Result> mResult;
    private final StorageHelper mStorageHelper;
    private final ContentUrlFetcher mUrlFetcher;

    @Override // com.amazon.avod.content.ContentSessionEventListener
    public void onAuxiliaryDownloadEligibilityStateChanged(boolean z) {
    }

    @Override // com.amazon.avod.content.ContentSessionEventListener
    public void onSessionEnding() {
    }

    public PlaybackDownloadTask(@Nonnull BaseDrmSystem baseDrmSystem, @Nonnull ContentSessionFactory contentSessionFactory, @Nonnull PlaybackDownloadJob playbackDownloadJob, @Nonnull QoSEventManagerFactory qoSEventManagerFactory, @Nonnull ContentUrlFetcher contentUrlFetcher, @Nonnull RendererSchemeController rendererSchemeController, @Nonnull ImmutableList<Provider<? extends ContentFetcherPlugin>> immutableList, @Nonnull PlaybackDownloadListenerInternal playbackDownloadListenerInternal) {
        this(contentSessionFactory, playbackDownloadJob, new DrmLicenseAcquirer(baseDrmSystem), qoSEventManagerFactory, contentUrlFetcher, rendererSchemeController, immutableList, playbackDownloadListenerInternal, PlaybackDownloads.getInstance().getStorageHelper());
    }

    PlaybackDownloadTask(@Nonnull ContentSessionFactory contentSessionFactory, @Nonnull PlaybackDownloadJob playbackDownloadJob, @Nonnull DrmLicenseAcquirer drmLicenseAcquirer, @Nonnull QoSEventManagerFactory qoSEventManagerFactory, @Nonnull ContentUrlFetcher contentUrlFetcher, @Nonnull RendererSchemeController rendererSchemeController, @Nonnull ImmutableList<Provider<? extends ContentFetcherPlugin>> immutableList, @Nonnull PlaybackDownloadListenerInternal playbackDownloadListenerInternal, @Nonnull StorageHelper storageHelper) {
        this.mMonitor = new Object();
        this.mResult = SettableFuture.create();
        this.mContentSessionFactory = (ContentSessionFactory) Preconditions.checkNotNull(contentSessionFactory, "sessionFactory");
        this.mItem = (PlaybackDownloadJob) Preconditions.checkNotNull(playbackDownloadJob, "workItem");
        this.mLicenseAcquirer = (DrmLicenseAcquirer) Preconditions.checkNotNull(drmLicenseAcquirer, "licenseAcquirer");
        this.mQoSEventManagerFactory = (QoSEventManagerFactory) Preconditions.checkNotNull(qoSEventManagerFactory, "qosEventManagerFactory");
        this.mUrlFetcher = (ContentUrlFetcher) Preconditions.checkNotNull(contentUrlFetcher, "urlFetcher");
        this.mRendererSchemeController = (RendererSchemeController) Preconditions.checkNotNull(rendererSchemeController, "rendererSchemeController");
        this.mPluginProviders = (ImmutableList) Preconditions.checkNotNull(immutableList, "pluginProviders");
        this.mDownloadProgressListener = (PlaybackDownloadListenerInternal) Preconditions.checkNotNull(playbackDownloadListenerInternal, "downloadProgressListener");
        this.mStorageHelper = (StorageHelper) Preconditions.checkNotNull(storageHelper, "storageHelper");
    }

    /* JADX WARN: Removed duplicated region for block: B:70:0x0248  */
    @Override // com.amazon.avod.download.PlaybackDownloadExecutorTask
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.amazon.avod.download.PlaybackDownloadExecutorTask.Result execute() {
        /*
            Method dump skipped, instruction units count: 671
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.download.PlaybackDownloadTask.execute():com.amazon.avod.download.PlaybackDownloadExecutorTask$Result");
    }

    @Override // com.amazon.avod.download.PlaybackDownloadExecutorTask
    public void cancel() {
        cancelExecutorTask();
        setResult(Downloadable.DownloadableState.QUEUED, null);
    }

    @Override // com.amazon.avod.content.ContentSessionEventListener
    public void onFatalError(ContentException contentException) {
        cancelExecutorTask();
        setResult(Downloadable.DownloadableState.ERRORED, contentException.getErrorCode().getExternalCode());
    }

    @Override // com.amazon.avod.content.ContentSessionEventListener
    public void onContentMetadataAcquired(@Nonnull String str) {
        DLog.logf("DWNLD_SDK listener.updateDrmAssetId(item=%s)", this.mItem);
        this.mDownloadProgressListener.updateDrmAssetId(this.mItem, str);
    }

    private void updateAudioTrackMetadata(@Nonnull ImmutableList<AudioTrackMetadata> immutableList) {
        DLog.logf("DWNLD_SDK listener.updateAudioTrackMetadataList(numTracks=%d, item=%s)", Integer.valueOf(immutableList.size()), this.mItem);
        this.mDownloadProgressListener.updateAudioTrackMetadataList(this.mItem, immutableList);
    }

    private void updateCuepointPlaylistInfo(@Nonnull CuepointPlaylistInfo cuepointPlaylistInfo) {
        DLog.logf("ServerInsertedPlaybackSession: Playback DWNLD listener.updateCuepointPlaylistInfo(lastCuepointUpdatedTimeCodeInMs=%d)", Long.valueOf(cuepointPlaylistInfo.getLastCuepointUpdatedTimeCodeInMs()));
        this.mDownloadProgressListener.updateCuepointPlayList(this.mItem, cuepointPlaylistInfo);
    }

    @Override // com.amazon.avod.content.ContentSessionEventListener
    public void onContentFragmentDownloaded(SampleType sampleType, long j, long j2) {
        DLog.devf("DWNLD_SDK listener.updateProgress(type=%s, item=%s)", sampleType, this.mItem);
        this.mDownloadProgressListener.updateProgress(this.mItem, sampleType, this.mContentSession, j, j2);
    }

    @Override // com.amazon.avod.content.ContentSessionEventListener
    public void onDownloadComplete() {
        long sizeRecursiveInBytes = DiskUtils.getSizeRecursiveInBytes(this.mItem.getStoragePath());
        DLog.logf("DWNLD_SDK listener.onDownloadComplete(size=%d, path=%s, item=%s)", Long.valueOf(sizeRecursiveInBytes), this.mItem.getStoragePath().getAbsolutePath(), this.mItem);
        this.mDownloadProgressListener.updateFileSize(this.mItem, sizeRecursiveInBytes);
        setResult(Downloadable.DownloadableState.COMPLETED, null);
    }

    @Override // com.amazon.avod.content.ContentSessionEventListener
    public void onDownloadStart(long j) {
        DLog.logf("DWNLD_SDK listener.onDownloadStart(size=%d, path=%s, item=%s)", Long.valueOf(j), this.mItem.getStoragePath().getAbsolutePath(), this.mItem);
        this.mDownloadProgressListener.updateFileSize(this.mItem, j);
    }

    private void setResult(@Nonnull Downloadable.DownloadableState downloadableState, @Nullable MediaErrorCode mediaErrorCode) {
        DLog.devf("DWNLD_SDK setResult(state=%s, err=%s, item=%s)", downloadableState, mediaErrorCode, this.mItem);
        this.mResult.set(new PlaybackDownloadExecutorTask.Result(downloadableState, Optional.fromNullable(mediaErrorCode)));
    }

    private void cancelExecutorTask() {
        ContentFetcherPluginExecutor contentFetcherPluginExecutor = this.mContentFetcherPluginExecutor;
        if (contentFetcherPluginExecutor != null) {
            contentFetcherPluginExecutor.cancel();
        }
    }

    private PlaybackDownloadExecutorTask.Result getResultUninterruptibly() {
        try {
            return (PlaybackDownloadExecutorTask.Result) Uninterruptibles.getUninterruptibly(this.mResult);
        } catch (ExecutionException e) {
            throw new IllegalStateException("Future cannot throw!", e);
        }
    }

    @Override // com.amazon.avod.download.PlaybackDownloadExecutorTask
    public PlaybackDownloadJob getItem() {
        return this.mItem;
    }

    private boolean isDownloadPathLocationWritable(@Nonnull File file) {
        Preconditions.checkNotNull(file, "downloadRootDirectory");
        File file2 = new File(file.getParentFile(), "test");
        try {
            if (file.getParentFile().mkdirs()) {
                DLog.logf("DWNLD_SDK Created Download test directory");
            }
            if (!file2.exists() && !file2.createNewFile()) {
                DLog.errorf("DWNLD_SDK Could not write test file to download directory");
                return false;
            }
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file2, "rw");
                try {
                    FileChannel channel = randomAccessFile.getChannel();
                    try {
                        channel.lock().release();
                        return true;
                    } finally {
                        channel.close();
                    }
                } finally {
                    randomAccessFile.close();
                }
            } finally {
                if (!file2.delete()) {
                    DLog.warnf("DWNLD_SDK Unable to delete test download file");
                }
            }
        } catch (IOException e) {
            DLog.errorf("DWNLD_SDK Cannot write to download directory due to error: %s", e.getMessage());
            return false;
        }
    }

    private StorageHelper.StorageLocation getBestEffortForStorageLocation(@Nonnull File file) {
        Preconditions.checkNotNull(file, "downloadRoot");
        Optional<StorageHelper.StorageLocation> optionalInferStorageLocationForFile = this.mStorageHelper.inferStorageLocationForFile(file);
        if (optionalInferStorageLocationForFile.isPresent()) {
            return optionalInferStorageLocationForFile.get();
        }
        return this.mStorageHelper.getInternalDownloadDir().getAbsolutePath().equals(file.getParentFile().getAbsolutePath()) ? StorageHelper.StorageLocation.INTERNAL_STORAGE : StorageHelper.StorageLocation.SD_CARD;
    }
}
