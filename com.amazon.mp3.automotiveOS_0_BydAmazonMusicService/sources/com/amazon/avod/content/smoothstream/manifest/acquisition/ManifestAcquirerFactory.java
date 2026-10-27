package com.amazon.avod.content.smoothstream.manifest.acquisition;

import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.smoothstream.manifest.ManifestParser;
import com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore;
import com.amazon.avod.core.Framework;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.framework.profiling.MediaProfiler;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ManifestAcquirerFactory {
    public static final ManifestAcquirerFactory INSTANCE = new ManifestAcquirerFactory();

    @Nonnull
    public ManifestAcquirerInterface getManifestAcquirer(@Nonnull DownloadService downloadService, @Nonnull ContentManagementEventBus contentManagementEventBus, @Nonnull MediaProfiler mediaProfiler, @Nonnull SmoothStreamingContentStore smoothStreamingContentStore, @Nonnull ManifestParser manifestParser, @Nonnull ManifestCapturerInterface manifestCapturerInterface, boolean z) {
        if (Framework.isDebugConfigurationEnabled() && ManifestCapturerConfig.INSTANCE.getManifestReplayEnabled()) {
            return new PostManifestAcquirer(manifestParser);
        }
        return new ManifestAcquirer(downloadService, contentManagementEventBus, mediaProfiler, smoothStreamingContentStore, manifestParser, manifestCapturerInterface, z);
    }

    private ManifestAcquirerFactory() {
    }
}
