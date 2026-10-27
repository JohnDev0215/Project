package com.amazon.avod.content;

import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.smoothstream.manifest.adroit.SmoothStreamManifestParser;
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
import com.amazon.avod.playback.smoothstream.rubymanifestparser.RubyManifestParser;
import com.amazon.avod.smoothstream.dash.CVBRSupportedFragmentParser;

/* JADX INFO: loaded from: classes2.dex */
public class SmoothStreamingDashHeuristicsContentSesionFactory extends ContentSessionFactoryBase {
    public SmoothStreamingDashHeuristicsContentSesionFactory(NetworkHistoryManager networkHistoryManager, MediaSystemSharedContext mediaSystemSharedContext, DownloadService downloadService, FileSystem fileSystem, MediaProfiler mediaProfiler, SmoothStreamingContentStore smoothStreamingContentStore, PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader, RendererSchemeController rendererSchemeController, FileBackedContentStore fileBackedContentStore) {
        super(networkHistoryManager, mediaSystemSharedContext, downloadService, fileSystem, mediaProfiler, smoothStreamingContentStore, playbackNativeLibrariesLoader, rendererSchemeController, new CVBRSupportedFragmentParser(playbackNativeLibrariesLoader, SmoothStreamingPlaybackConfig.INSTANCE), new SmoothStreamManifestParser(new RubyManifestParser(playbackNativeLibrariesLoader)), new DashMiyagiHeuristicsProvider(playbackNativeLibrariesLoader), fileBackedContentStore);
    }
}
