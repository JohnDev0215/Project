package com.amazon.avod.download;

import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.content.ContentSessionFactory;
import com.amazon.avod.content.ContentStore;
import com.amazon.avod.content.urlvending.ContentUrlFetcher;
import com.amazon.avod.drm.BaseDrmSystem;
import com.amazon.avod.media.download.plugin.ContentFetcherPlugin;
import com.amazon.avod.media.playback.support.RendererSchemeController;
import com.amazon.avod.qos.listeners.QoSEventManagerFactory;
import com.amazon.avod.userdownload.internal.PlaybackDownloadJob;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import javax.annotation.Nonnull;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackQueueDownloadExecutorTaskFactory {
    private final ContentSessionFactory mContentSessionFactory;
    private final ContentStore mContentStore;
    private final BaseDrmSystem mDrmSystem;
    private final NetworkConnectionManager mNetworkConnectionManager;
    private final ImmutableList<Provider<? extends ContentFetcherPlugin>> mPluginProviders;
    private final QoSEventManagerFactory mQoSEventManagerFactory;
    private final RendererSchemeController mRendererSchemeController;
    private final ContentUrlFetcher mUrlFetcher;

    protected PlaybackQueueDownloadExecutorTaskFactory(@Nonnull NetworkConnectionManager networkConnectionManager, @Nonnull BaseDrmSystem baseDrmSystem, @Nonnull ContentSessionFactory contentSessionFactory, @Nonnull ContentStore contentStore, @Nonnull QoSEventManagerFactory qoSEventManagerFactory, @Nonnull ContentUrlFetcher contentUrlFetcher, @Nonnull RendererSchemeController rendererSchemeController, @Nonnull ImmutableList<Provider<? extends ContentFetcherPlugin>> immutableList) {
        this.mNetworkConnectionManager = (NetworkConnectionManager) Preconditions.checkNotNull(networkConnectionManager, "networkConnectionManager");
        this.mDrmSystem = (BaseDrmSystem) Preconditions.checkNotNull(baseDrmSystem, "drmSystem");
        this.mContentSessionFactory = (ContentSessionFactory) Preconditions.checkNotNull(contentSessionFactory, "sessionFactory");
        this.mContentStore = (ContentStore) Preconditions.checkNotNull(contentStore, "contentStore");
        this.mQoSEventManagerFactory = (QoSEventManagerFactory) Preconditions.checkNotNull(qoSEventManagerFactory, "qosEventManagerFactory");
        this.mUrlFetcher = (ContentUrlFetcher) Preconditions.checkNotNull(contentUrlFetcher, "urlFetcher");
        this.mRendererSchemeController = (RendererSchemeController) Preconditions.checkNotNull(rendererSchemeController, "rendererSchemeController");
        this.mPluginProviders = (ImmutableList) Preconditions.checkNotNull(immutableList, "pluginProviders");
    }

    public Optional<PlaybackDownloadExecutorTask> newDownloadTaskForItem(@Nonnull PlaybackDownloadJob playbackDownloadJob, @Nonnull PlaybackDownloadListenerInternal playbackDownloadListenerInternal) {
        if (!this.mNetworkConnectionManager.hasDataConnection()) {
            return Optional.absent();
        }
        return newDownloadTaskForItemInner(playbackDownloadJob, playbackDownloadListenerInternal);
    }

    protected Optional<PlaybackDownloadExecutorTask> newDownloadTaskForItemInner(@Nonnull PlaybackDownloadJob playbackDownloadJob, @Nonnull PlaybackDownloadListenerInternal playbackDownloadListenerInternal) {
        return Optional.of(new PlaybackDownloadTask(this.mDrmSystem, this.mContentSessionFactory, playbackDownloadJob, this.mQoSEventManagerFactory, this.mUrlFetcher, this.mRendererSchemeController, this.mPluginProviders, playbackDownloadListenerInternal));
    }

    public Optional<PlaybackDownloadExecutorTask> newDeleteTaskForItem(@Nonnull PlaybackDownloadJob playbackDownloadJob, @Nonnull PlaybackDownloadListenerInternal playbackDownloadListenerInternal) {
        return Optional.of(new PlaybackDeletionTask(this.mContentStore, playbackDownloadJob, playbackDownloadListenerInternal));
    }
}
