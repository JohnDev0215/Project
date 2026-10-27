package com.amazon.avod.playback.renderer.mediacodec;

import android.media.MediaFormat;
import com.amazon.avod.media.playback.MediaDefaultConfiguration;
import com.amazon.avod.media.playback.source.BufferingMediaSourceAdapter;
import com.amazon.avod.media.playback.source.ByteBuffersMediaSourceAdapter;
import com.amazon.avod.media.playback.source.MediaSource;
import com.amazon.avod.media.playback.util.TrackAdapter;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.DataUnit;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
class BufferingMediaSourceAdapterFactory {
    private static final int DEFAULT_BUFFER_SIZE_BYTES;
    private static final int DEFAULT_MAX_SAMPLE_SIZE_BYTES;
    private static final int HEVC_BUFFER_SIZE_BYTES;
    private static final int HEVC_MAX_SAMPLE_SIZE_BYTES;
    private static final BufferingMediaSourceAdapterFactory INSTANCE = new BufferingMediaSourceAdapterFactory();
    private final MediaDefaultConfiguration mMediaDefaultConfiguration = MediaDefaultConfiguration.getInstance();

    static {
        DataUnit dataUnit = DataUnit.MEGABYTES;
        DEFAULT_BUFFER_SIZE_BYTES = (int) dataUnit.toBytes(8.0f);
        DEFAULT_MAX_SAMPLE_SIZE_BYTES = (int) dataUnit.toBytes(2.0f);
        HEVC_BUFFER_SIZE_BYTES = (int) dataUnit.toBytes(20.0f);
        HEVC_MAX_SAMPLE_SIZE_BYTES = (int) dataUnit.toBytes(5.0f);
    }

    @Nonnull
    public static BufferingMediaSourceAdapterFactory getInstance() {
        return INSTANCE;
    }

    private BufferingMediaSourceAdapterFactory() {
    }

    @Nonnull
    MediaSource newMediaSource(@Nonnull MediaFormat mediaFormat, boolean z, @Nonnull TrackAdapter trackAdapter) {
        int i;
        int i2;
        String string = mediaFormat.getString("mime");
        Preconditions.checkNotNull(mediaFormat, "mediaFormat");
        Preconditions.checkNotNull(trackAdapter, "trackAdapter");
        if (this.mMediaDefaultConfiguration.isMediaCodecZeroMemCopyMediaSourceEnabled() && this.mMediaDefaultConfiguration.isMemStoreBasedRendererZeroMemCopyAllowed()) {
            DLog.logf("Using Byte Buffer reference based media source");
            return new ByteBuffersMediaSourceAdapter(z, trackAdapter);
        }
        string.hashCode();
        switch (string) {
            case "video/dolby-vision":
            case "video/av01":
            case "video/hevc":
                i = HEVC_BUFFER_SIZE_BYTES;
                i2 = HEVC_MAX_SAMPLE_SIZE_BYTES;
                break;
            case "audio/mp4a-latm":
            case "audio/ac3":
            case "audio/raw":
            case "video/avc":
            case "audio/eac3":
                i = DEFAULT_BUFFER_SIZE_BYTES;
                i2 = DEFAULT_MAX_SAMPLE_SIZE_BYTES;
                break;
            default:
                throw new UnsupportedOperationException("newMediaSource mime type unexpected: " + string);
        }
        DLog.logf("Allocating buffer for decoder %s with size %s bytes, max sample size %s bytes", string, Integer.valueOf(i), Integer.valueOf(i2));
        return new BufferingMediaSourceAdapter(i, i2, z, trackAdapter);
    }
}
