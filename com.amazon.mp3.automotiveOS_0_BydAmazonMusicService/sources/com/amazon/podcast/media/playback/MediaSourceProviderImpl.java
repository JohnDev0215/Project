package com.amazon.podcast.media.playback;

import PlaybackInterface.v1_0.AudioStreamFormat;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import com.amazon.podcast.Podcast;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.AdaptiveMediaSourceEventListener;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.TransferListener;

/* JADX INFO: loaded from: classes5.dex */
public final class MediaSourceProviderImpl {
    private final Context context;
    private final Handler handler;
    private final int RETRY_COUNT = 9;
    private final String DEFAULT_STREAM_FORMAT = AudioStreamFormat.PROGRESSIVE;
    private final String SEC_GPC_HEADER_NAME = "Sec-GPC";

    public MediaSourceProviderImpl(final Context context, final Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    public MediaSource getMediaSource(final Uri uri, final String audioStreamFormat) {
        Playback playback = Podcast.getPlayback();
        DefaultExtractorsFactory defaultExtractorsFactory = new DefaultExtractorsFactory();
        DefaultHttpDataSourceFactory defaultHttpDataSourceFactory = new DefaultHttpDataSourceFactory("AmazonMusic", null, 8000, 8000, true);
        if (OptOut.getOptOutHeaderValue() != null) {
            defaultHttpDataSourceFactory.setDefaultRequestProperty("Sec-GPC", OptOut.getOptOutHeaderValue());
        }
        DefaultDataSourceFactory defaultDataSourceFactory = new DefaultDataSourceFactory(this.context, (TransferListener<? super DataSource>) null, defaultHttpDataSourceFactory);
        if (audioStreamFormat == null) {
            playback.setStreamType(AudioStreamFormat.PROGRESSIVE);
            return new ExtractorMediaSource(uri, defaultDataSourceFactory, defaultExtractorsFactory, this.handler, null);
        }
        byte b = -1;
        int iHashCode = audioStreamFormat.hashCode();
        if (iHashCode != -1010458773) {
            if (iHashCode == 71631 && audioStreamFormat.equals(AudioStreamFormat.HLS)) {
                b = 0;
            }
        } else if (audioStreamFormat.equals(AudioStreamFormat.PROGRESSIVE)) {
            b = 1;
        }
        if (b == 0) {
            playback.setStreamType(AudioStreamFormat.HLS);
            return new HlsMediaSource(uri, defaultDataSourceFactory, 9, this.handler, (AdaptiveMediaSourceEventListener) null);
        }
        playback.setStreamType(AudioStreamFormat.PROGRESSIVE);
        return new ExtractorMediaSource(uri, defaultDataSourceFactory, defaultExtractorsFactory, this.handler, null);
    }
}
