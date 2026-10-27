package com.amazon.alexa.voice;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import com.amazon.alexa.voice.RemotePlaylistDataSource;
import com.amazon.alexa.voice.internal.util.ExtensionUtils;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
final class DynamicMediaSource implements MediaSource {
    private final Context context;
    private final ExtractorsFactory extractorsFactory;
    private final Handler handler;
    private MediaSource mediaSource;
    private final Uri uri;
    private final String userAgent;

    public DynamicMediaSource(Context context, Uri uri, String str, ExtractorsFactory extractorsFactory, Handler handler) {
        this.context = context;
        this.uri = uri;
        this.userAgent = str;
        this.handler = handler;
        this.extractorsFactory = extractorsFactory;
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void prepareSource(ExoPlayer exoPlayer, boolean z, MediaSource.Listener listener) {
        String extension = ExtensionUtils.getExtension(this.uri);
        if (".m3u".equalsIgnoreCase(extension) || ".m3u8".equalsIgnoreCase(extension)) {
            this.mediaSource = new HlsMediaSource(this.uri, new DefaultDataSourceFactory(this.context, this.userAgent), this.handler, null);
        } else if (".ashx".equalsIgnoreCase(extension) || ".pls".equalsIgnoreCase(extension)) {
            this.mediaSource = new ExtractorMediaSource(this.uri, new RemotePlaylistDataSource.Factory(this.userAgent), this.extractorsFactory, this.handler, null);
        } else {
            this.mediaSource = new ExtractorMediaSource(this.uri, new DefaultDataSourceFactory(this.context, this.userAgent), this.extractorsFactory, this.handler, null);
        }
        this.mediaSource.prepareSource(exoPlayer, z, listener);
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void maybeThrowSourceInfoRefreshError() throws IOException {
        this.mediaSource.maybeThrowSourceInfoRefreshError();
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator) {
        return this.mediaSource.createPeriod(mediaPeriodId, allocator);
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        this.mediaSource.releasePeriod(mediaPeriod);
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void releaseSource() {
        MediaSource mediaSource = this.mediaSource;
        if (mediaSource != null) {
            mediaSource.releaseSource();
            this.mediaSource = null;
        }
    }
}
