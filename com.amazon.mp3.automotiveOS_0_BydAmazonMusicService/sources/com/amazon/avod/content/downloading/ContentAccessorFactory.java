package com.amazon.avod.content.downloading;

import com.amazon.avod.content.ContentSessionConfiguration;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.config.LiveStreamingPlaybackConfig;
import com.amazon.avod.content.config.SmoothStreamingHeuristicConfig;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig;
import com.amazon.avod.content.downloading.caching.CacheContentAccessor;
import com.amazon.avod.content.downloading.progressive.DownloadContentAccessor;
import com.amazon.avod.content.downloading.streaming.StreamingContentAccessor;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.smoothstream.Mp4FragmentParser;
import com.amazon.avod.content.smoothstream.downloading.DownloadTaskFactory;
import com.amazon.avod.content.smoothstream.quality.BitrateSelectorFactory;
import com.amazon.avod.content.smoothstream.quality.defaults.DefaultQualityConfiguration;
import com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore;
import com.amazon.avod.content.urlvending.ContentUrlSelector;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.framework.memory.GrowableBuffer;
import com.amazon.avod.media.framework.network.NetworkHistoryManager;
import com.amazon.avod.media.framework.platform.FileSystem;
import com.amazon.avod.media.framework.resources.SurgingResourcePool;
import com.amazon.avod.media.playback.internal.config.MediaQualityConfig;
import com.amazon.avod.playback.capability.DeviceCapability;
import com.google.common.base.Preconditions;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ContentAccessorFactory {
    private final DownloadService mDownloadService;
    private final DownloadTaskFactory mDownloadTaskFactory;
    private final ContentManagementEventBus mEventDispatcher;
    private final FileSystem mFileSystem;
    private final ContentSessionConfiguration mSessionConfiguration;

    /* JADX WARN: Illegal instructions before constructor call */
    public ContentAccessorFactory(@Nonnull ContentManagementEventBus contentManagementEventBus, @Nonnull DefaultQualityConfiguration defaultQualityConfiguration, @Nonnull DownloadService downloadService, @Nonnull ExecutorService executorService, @Nonnull FileSystem fileSystem, @Nonnull Mp4FragmentParser mp4FragmentParser, @Nonnull SmoothStreamingContentStore smoothStreamingContentStore, @Nonnull NetworkHistoryManager networkHistoryManager, @Nonnull DeviceCapability deviceCapability, @Nonnull ContentSessionConfiguration contentSessionConfiguration, @Nonnull SmoothStreamingHeuristicConfig smoothStreamingHeuristicConfig, @Nonnull SurgingResourcePool<GrowableBuffer> surgingResourcePool, @Nullable HeuristicsPlaybackConfig heuristicsPlaybackConfig) {
        SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig = SmoothStreamingPlaybackConfig.INSTANCE;
        MediaQualityConfig mediaQualityConfig = MediaQualityConfig.INSTANCE;
        this(contentManagementEventBus, defaultQualityConfiguration, downloadService, executorService, fileSystem, mp4FragmentParser, smoothStreamingContentStore, networkHistoryManager, deviceCapability, contentSessionConfiguration, smoothStreamingHeuristicConfig, surgingResourcePool, smoothStreamingPlaybackConfig, mediaQualityConfig, LiveStreamingPlaybackConfig.getInstance(), new BitrateSelectorFactory(defaultQualityConfiguration, contentSessionConfiguration, smoothStreamingHeuristicConfig, smoothStreamingPlaybackConfig, networkHistoryManager, mediaQualityConfig, smoothStreamingContentStore, deviceCapability, heuristicsPlaybackConfig));
    }

    ContentAccessorFactory(@Nonnull ContentManagementEventBus contentManagementEventBus, @Nonnull DefaultQualityConfiguration defaultQualityConfiguration, @Nonnull DownloadService downloadService, @Nonnull ExecutorService executorService, @Nonnull FileSystem fileSystem, @Nonnull Mp4FragmentParser mp4FragmentParser, @Nonnull SmoothStreamingContentStore smoothStreamingContentStore, @Nonnull NetworkHistoryManager networkHistoryManager, @Nonnull DeviceCapability deviceCapability, @Nonnull ContentSessionConfiguration contentSessionConfiguration, @Nonnull SmoothStreamingHeuristicConfig smoothStreamingHeuristicConfig, @Nonnull SurgingResourcePool<GrowableBuffer> surgingResourcePool, @Nonnull SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig, @Nonnull MediaQualityConfig mediaQualityConfig, @Nonnull LiveStreamingPlaybackConfig liveStreamingPlaybackConfig, @Nonnull BitrateSelectorFactory bitrateSelectorFactory) {
        ContentManagementEventBus contentManagementEventBus2 = (ContentManagementEventBus) Preconditions.checkNotNull(contentManagementEventBus, "eventBus");
        this.mEventDispatcher = contentManagementEventBus2;
        this.mFileSystem = (FileSystem) Preconditions.checkNotNull(fileSystem, "fileSystem");
        this.mDownloadService = (DownloadService) Preconditions.checkNotNull(downloadService, "downloadService");
        this.mSessionConfiguration = (ContentSessionConfiguration) Preconditions.checkNotNull(contentSessionConfiguration, "sessionConfiguration");
        Preconditions.checkNotNull(defaultQualityConfiguration, "defaultConfiguration");
        Preconditions.checkNotNull(executorService, "executor");
        Preconditions.checkNotNull(mp4FragmentParser, "parser");
        Preconditions.checkNotNull(smoothStreamingContentStore, "contentStore");
        Preconditions.checkNotNull(networkHistoryManager, "networkHistoryManager");
        Preconditions.checkNotNull(deviceCapability, "deviceCapability");
        Preconditions.checkNotNull(smoothStreamingHeuristicConfig, "heuristicConfig");
        Preconditions.checkNotNull(surgingResourcePool, "bufferPool");
        Preconditions.checkNotNull(smoothStreamingPlaybackConfig, "smoothStreamingPlaybackConfig");
        Preconditions.checkNotNull(mediaQualityConfig, "mediaQualityConfig");
        Preconditions.checkNotNull(liveStreamingPlaybackConfig, "liveStreamingPlaybackConfig");
        Preconditions.checkNotNull(bitrateSelectorFactory, "bitrateSelectorFactory");
        this.mDownloadTaskFactory = new DownloadTaskFactory(bitrateSelectorFactory, defaultQualityConfiguration, downloadService, executorService, mp4FragmentParser, smoothStreamingContentStore, smoothStreamingPlaybackConfig, liveStreamingPlaybackConfig, surgingResourcePool, contentSessionConfiguration, contentManagementEventBus2);
    }

    /* JADX INFO: renamed from: com.amazon.avod.content.downloading.ContentAccessorFactory$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$content$ContentSessionType;

        static {
            int[] iArr = new int[ContentSessionType.values().length];
            $SwitchMap$com$amazon$avod$content$ContentSessionType = iArr;
            try {
                iArr[ContentSessionType.LIVE_CACHE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.STREAMING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.STREAMING_CONTINUOUS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.DOWNLOAD.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.PROGRESSIVE_PLAYBACK.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.CONTENT_CACHE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public ContentAccessor newContentAccessor(ContentSessionContext contentSessionContext, ContentUrlSelector contentUrlSelector) {
        switch (AnonymousClass1.$SwitchMap$com$amazon$avod$content$ContentSessionType[contentSessionContext.getSessionType().ordinal()]) {
            case 1:
            case 2:
            case 3:
                return new StreamingContentAccessor(getDownloadAvailabilityController(contentSessionContext), this.mEventDispatcher, this.mDownloadTaskFactory, this.mDownloadService, contentUrlSelector);
            case 4:
            case 5:
                return new DownloadContentAccessor(this.mEventDispatcher, this.mDownloadTaskFactory, this.mFileSystem, SmoothStreamingPlaybackConfig.INSTANCE, this.mDownloadService, contentUrlSelector);
            case 6:
                return new CacheContentAccessor(this.mEventDispatcher, this.mDownloadTaskFactory, SmoothStreamingPlaybackConfig.INSTANCE, this.mFileSystem, this.mDownloadService, contentUrlSelector);
            default:
                throw new IllegalArgumentException("Unrecognized sessionType");
        }
    }

    private DownloadAvailabilityController getDownloadAvailabilityController(ContentSessionContext contentSessionContext) {
        if (this.mSessionConfiguration.isHeuristicsDownloadControlsEnabled() && contentSessionContext.getHeuristics() != null && contentSessionContext.getHeuristics().getHeuristicsPlaybackConfig() != null) {
            return new StreamingContentSessionDownloadAvailabilityController(contentSessionContext.getHeuristics(), contentSessionContext, this.mEventDispatcher);
        }
        return new DefaultDownloadAvailabilityController();
    }
}
