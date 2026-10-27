package com.amazon.avod.content.smoothstream.downloading;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.dash.quality.heuristic.Heuristics;
import com.amazon.avod.content.downloading.DownloadEventsListener;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelector;
import com.amazon.avod.content.smoothstream.streamstate.StreamDownloaderState;
import com.amazon.avod.content.smoothstream.streamstate.StreamStateObserver;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface ConcurrentFragmentDownloader extends StreamStateObserver, FragmentDownloadController {
    void dispose();

    int getBandwidthBps();

    boolean hasDownloadErrors();

    boolean hasUpdatesForContentView();

    void initialize(ContentSessionContext contentSessionContext, StreamIndex streamIndex, StreamDownloaderState streamDownloaderState, @Nullable StreamingBitrateSelector streamingBitrateSelector, DownloadEventsListener downloadEventsListener, @Nullable Heuristics heuristics);

    void start();

    void stop();
}
