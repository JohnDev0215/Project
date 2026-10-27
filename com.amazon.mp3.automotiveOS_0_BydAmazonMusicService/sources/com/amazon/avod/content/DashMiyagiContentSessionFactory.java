package com.amazon.avod.content;

import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.smoothstream.storage.FileBackedContentStore;
import com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore;
import com.amazon.avod.dash.DashMiyagiHeuristicsProvider;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader;
import com.amazon.avod.media.framework.network.NetworkHistoryManager;
import com.amazon.avod.media.framework.platform.FileSystem;
import com.amazon.avod.media.framework.profiling.MediaProfiler;
import com.amazon.avod.media.playback.support.RendererSchemeController;
import com.amazon.avod.smoothstream.dash.CVBRSupportedFragmentParser;
import com.amazon.avod.smoothstream.dash.DashManifestParser;

/* JADX INFO: loaded from: classes2.dex */
public class DashMiyagiContentSessionFactory extends ContentSessionFactoryBase {
    public DashMiyagiContentSessionFactory(NetworkHistoryManager networkHistoryManager, MediaSystemSharedContext mediaSystemSharedContext, DownloadService downloadService, FileSystem fileSystem, MediaProfiler mediaProfiler, SmoothStreamingContentStore smoothStreamingContentStore, PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader, RendererSchemeController rendererSchemeController, FileBackedContentStore fileBackedContentStore) {
        super(networkHistoryManager, mediaSystemSharedContext, downloadService, fileSystem, mediaProfiler, smoothStreamingContentStore, playbackNativeLibrariesLoader, rendererSchemeController, new CVBRSupportedFragmentParser(playbackNativeLibrariesLoader, SmoothStreamingPlaybackConfig.INSTANCE), new DashManifestParser(playbackNativeLibrariesLoader), new DashMiyagiHeuristicsProvider(playbackNativeLibrariesLoader), fileBackedContentStore);
    }
}
