package com.google.android.exoplayer2.source.hls;

import android.net.Uri;
import android.os.Handler;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerLibraryInfo;
import com.google.android.exoplayer2.source.AdaptiveMediaSourceEventListener;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.SinglePeriodTimeline;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistParser;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
public final class HlsMediaSource implements MediaSource, HlsPlaylistTracker.PrimaryPlaylistListener {
    private final HlsDataSourceFactory dataSourceFactory;
    private final AdaptiveMediaSourceEventListener.EventDispatcher eventDispatcher;
    private final boolean ignoreResolutionInManifest;
    private final Uri manifestUri;
    private final int minLoadableRetryCount;
    private final ParsingLoadable.Parser<HlsPlaylist> playlistParser;
    private HlsPlaylistTracker playlistTracker;
    private MediaSource.Listener sourceListener;

    static {
        ExoPlayerLibraryInfo.registerModule("goog.exo.hls");
    }

    public HlsMediaSource(Uri uri, DataSource.Factory factory, Handler handler, AdaptiveMediaSourceEventListener adaptiveMediaSourceEventListener) {
        this(uri, factory, 3, handler, adaptiveMediaSourceEventListener);
    }

    public HlsMediaSource(Uri uri, DataSource.Factory factory, int i, Handler handler, AdaptiveMediaSourceEventListener adaptiveMediaSourceEventListener) {
        this(uri, new DefaultHlsDataSourceFactory(factory), i, handler, adaptiveMediaSourceEventListener);
    }

    public HlsMediaSource(Uri uri, HlsDataSourceFactory hlsDataSourceFactory, int i, Handler handler, AdaptiveMediaSourceEventListener adaptiveMediaSourceEventListener) {
        this(uri, hlsDataSourceFactory, i, handler, adaptiveMediaSourceEventListener, new HlsPlaylistParser());
    }

    public HlsMediaSource(Uri uri, HlsDataSourceFactory hlsDataSourceFactory, int i, Handler handler, AdaptiveMediaSourceEventListener adaptiveMediaSourceEventListener, ParsingLoadable.Parser<HlsPlaylist> parser) {
        this(uri, hlsDataSourceFactory, i, handler, adaptiveMediaSourceEventListener, parser, false);
    }

    public HlsMediaSource(Uri uri, HlsDataSourceFactory hlsDataSourceFactory, int i, Handler handler, AdaptiveMediaSourceEventListener adaptiveMediaSourceEventListener, ParsingLoadable.Parser<HlsPlaylist> parser, boolean z) {
        this.manifestUri = uri;
        this.dataSourceFactory = hlsDataSourceFactory;
        this.minLoadableRetryCount = i;
        this.playlistParser = parser;
        this.ignoreResolutionInManifest = z;
        this.eventDispatcher = new AdaptiveMediaSourceEventListener.EventDispatcher(handler, adaptiveMediaSourceEventListener);
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void prepareSource(ExoPlayer exoPlayer, boolean z, MediaSource.Listener listener) {
        Assertions.checkState(this.playlistTracker == null);
        HlsPlaylistTracker hlsPlaylistTracker = new HlsPlaylistTracker(this.manifestUri, this.dataSourceFactory, this.eventDispatcher, this.minLoadableRetryCount, this, this.playlistParser);
        this.playlistTracker = hlsPlaylistTracker;
        this.sourceListener = listener;
        hlsPlaylistTracker.start();
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void maybeThrowSourceInfoRefreshError() throws IOException {
        this.playlistTracker.maybeThrowPrimaryPlaylistRefreshError();
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator) {
        Assertions.checkArgument(mediaPeriodId.periodIndex == 0);
        return new HlsMediaPeriod(this.playlistTracker, this.dataSourceFactory, this.minLoadableRetryCount, this.eventDispatcher, allocator, this.ignoreResolutionInManifest);
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        ((HlsMediaPeriod) mediaPeriod).release();
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void releaseSource() {
        HlsPlaylistTracker hlsPlaylistTracker = this.playlistTracker;
        if (hlsPlaylistTracker != null) {
            hlsPlaylistTracker.release();
            this.playlistTracker = null;
        }
        this.sourceListener = null;
    }

    @Override // com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker.PrimaryPlaylistListener
    public void onPrimaryPlaylistRefreshed(HlsMediaPlaylist hlsMediaPlaylist) {
        SinglePeriodTimeline singlePeriodTimeline;
        long j;
        boolean z = hlsMediaPlaylist.hasProgramDateTime;
        long j2 = z ? 0L : -9223372036854775807L;
        long jUsToMs = z ? C.usToMs(hlsMediaPlaylist.startTimeUs) : -9223372036854775807L;
        long j3 = hlsMediaPlaylist.startOffsetUs;
        if (this.playlistTracker.isLive()) {
            long j4 = hlsMediaPlaylist.hasEndTag ? hlsMediaPlaylist.startTimeUs + hlsMediaPlaylist.durationUs : -9223372036854775807L;
            List<HlsMediaPlaylist.Segment> list = hlsMediaPlaylist.segments;
            if (j3 == -9223372036854775807L) {
                j = list.isEmpty() ? 0L : list.get(Math.max(0, list.size() - 3)).relativeStartTimeUs;
            } else {
                j = j3;
            }
            singlePeriodTimeline = new SinglePeriodTimeline(j2, jUsToMs, j4, hlsMediaPlaylist.durationUs, hlsMediaPlaylist.startTimeUs, j, true, !hlsMediaPlaylist.hasEndTag);
        } else {
            long j5 = j3 == -9223372036854775807L ? 0L : j3;
            long j6 = hlsMediaPlaylist.startTimeUs;
            long j7 = hlsMediaPlaylist.durationUs;
            singlePeriodTimeline = new SinglePeriodTimeline(j2, jUsToMs, j6 + j7, j7, j6, j5, true, false);
        }
        this.sourceListener.onSourceInfoRefreshed(singlePeriodTimeline, new HlsManifest(this.playlistTracker.getMasterPlaylist(), hlsMediaPlaylist));
    }
}
