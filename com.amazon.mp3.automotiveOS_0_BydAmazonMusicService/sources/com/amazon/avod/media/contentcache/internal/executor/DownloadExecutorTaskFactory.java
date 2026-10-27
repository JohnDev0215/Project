package com.amazon.avod.media.contentcache.internal.executor;

import com.amazon.avod.content.ContentSessionFactory;
import com.amazon.avod.content.ContentStore;
import com.amazon.avod.content.urlvending.ContentUrlFetcher;
import com.amazon.avod.download.Downloadable;
import com.amazon.avod.drm.BaseDrmSystem;
import com.amazon.avod.media.contentcache.internal.LiveCache;
import com.amazon.avod.media.playback.support.RendererSchemeController;
import com.amazon.avod.qos.listeners.QoSEventManagerFactory;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
class DownloadExecutorTaskFactory {
    private final ContentSessionFactory mContentSessionFactory;
    private final ContentStore mContentStore;
    private final BaseDrmSystem mDrmSystem;
    private final LiveCache mLiveCache;
    private final QoSEventManagerFactory mQoSEventManagerFactory;
    private final RendererSchemeController mRendererSchemeController;
    private final ContentUrlFetcher mUrlFetcher;

    public DownloadExecutorTaskFactory(BaseDrmSystem baseDrmSystem, ContentSessionFactory contentSessionFactory, ContentStore contentStore, QoSEventManagerFactory qoSEventManagerFactory, ContentUrlFetcher contentUrlFetcher, @Nonnull RendererSchemeController rendererSchemeController, @Nonnull LiveCache liveCache) {
        this.mDrmSystem = baseDrmSystem;
        this.mContentSessionFactory = contentSessionFactory;
        this.mContentStore = contentStore;
        this.mQoSEventManagerFactory = qoSEventManagerFactory;
        this.mUrlFetcher = contentUrlFetcher;
        this.mRendererSchemeController = (RendererSchemeController) Preconditions.checkNotNull(rendererSchemeController, "rendererSchemeController");
        this.mLiveCache = (LiveCache) Preconditions.checkNotNull(liveCache, "liveCache");
    }

    /* JADX INFO: renamed from: com.amazon.avod.media.contentcache.internal.executor.DownloadExecutorTaskFactory$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$download$Downloadable$DownloadableState;

        static {
            int[] iArr = new int[Downloadable.DownloadableState.values().length];
            $SwitchMap$com$amazon$avod$download$Downloadable$DownloadableState = iArr;
            try {
                iArr[Downloadable.DownloadableState.QUEUED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$download$Downloadable$DownloadableState[Downloadable.DownloadableState.DELETE_IMMEDIATELY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$download$Downloadable$DownloadableState[Downloadable.DownloadableState.TOMBSTONED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public DownloadExecutorTask newTaskForItem(Downloadable downloadable) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$download$Downloadable$DownloadableState[downloadable.getDownloadableState().ordinal()];
        if (i == 1) {
            return new DownloadTask(this.mDrmSystem, this.mContentSessionFactory, downloadable, this.mQoSEventManagerFactory, this.mUrlFetcher, this.mRendererSchemeController, this.mLiveCache);
        }
        if (i == 2 || i == 3) {
            return new DeletionTask(this.mContentStore, downloadable);
        }
        throw new IllegalStateException("Unexpected DownloadItemState!");
    }
}
