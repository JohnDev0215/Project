package com.amazon.avod.media.playback.pipeline;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import com.amazon.avod.media.playback.MediaCodecEnumerator;
import com.amazon.avod.media.playback.MediaDefaultConfiguration;
import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.util.DLog;
import com.google.common.collect.Sets;
import dagger.internal.Preconditions;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
class MediaCodecFactory {
    private static final MediaCodecFactory INSTANCE = new MediaCodecFactory();
    private final MediaCodecEnumerator mCodecEnumerator = MediaCodecEnumerator.getInstance();
    private final boolean mIsMediaCodecRecoveryEnabled = MediaDefaultConfiguration.getInstance().isMediaCodecRecoveryEnabled();
    private final Set<MediaCodec> mTrackedCodecs = Sets.newConcurrentHashSet();

    @Nonnull
    public static MediaCodecFactory getInstance() {
        return INSTANCE;
    }

    private MediaCodecFactory() {
    }

    @Nonnull
    public MediaCodec createCodec(@Nonnull MediaFormat mediaFormat, @Nullable MediaCrypto mediaCrypto) throws PlaybackException {
        String string = mediaFormat.getString("mime");
        boolean z = mediaCrypto != null && mediaCrypto.requiresSecureDecoderComponent(string);
        MediaCodecInfo supportedCodec = this.mCodecEnumerator.getSupportedCodec(string);
        if (supportedCodec == null) {
            throw new PlaybackException(PlaybackException.PlaybackError.RENDERER_INITIALIZE_FAILED, String.format("MediaCodecFactory couldn't find any codec for mimeType=%s, requiresSecureDecoder=%s", string, Boolean.valueOf(z)));
        }
        String name = supportedCodec.getName();
        if (z) {
            name = name + ".secure";
        }
        try {
            MediaCodec mediaCodecCreateByCodecName = MediaCodec.createByCodecName(name);
            DLog.logf("MediaCodecFactory instantiated codec %s for mimeType=%s, requiresSecureDecoder=%s, isHardwareAccelerated=%s", name, string, Boolean.valueOf(z), Boolean.valueOf(true ^ mediaCodecCreateByCodecName.getName().startsWith("OMX.google.")));
            if (this.mIsMediaCodecRecoveryEnabled) {
                this.mTrackedCodecs.add(mediaCodecCreateByCodecName);
            }
            return mediaCodecCreateByCodecName;
        } catch (Exception e) {
            resetAllCodecs(e);
            throw new PlaybackException(PlaybackException.PlaybackError.RENDERER_INITIALIZE_FAILED, String.format("MediaCodecFactory failed to instantiate codec %s for mimeType=%s, requiresSecureDecoder=%s, error: %s", name, string, Boolean.valueOf(z), e));
        }
    }

    public void releaseCodec(@Nonnull MediaCodec mediaCodec) {
        Preconditions.checkNotNull(mediaCodec, "codec");
        mediaCodec.release();
        DLog.logf("MediaCodecFactory released codec %s", mediaCodec);
        if (this.mIsMediaCodecRecoveryEnabled) {
            this.mTrackedCodecs.remove(mediaCodec);
        }
    }

    public void resetAllCodecs(@Nonnull Exception exc) {
        if (this.mIsMediaCodecRecoveryEnabled) {
            Preconditions.checkNotNull(exc, "trigger");
            DLog.logf("MediaCodecFactory resetAllCodecs called with trigger exception %s", exc);
            for (MediaCodec mediaCodec : this.mTrackedCodecs) {
                try {
                    mediaCodec.reset();
                    DLog.logf("MediaCodecFactory reset codec %s", mediaCodec);
                } catch (Exception e) {
                    DLog.warnf("MediaCodecFactory %s threw exception during reset %s", mediaCodec, e);
                }
            }
            this.mTrackedCodecs.clear();
        }
    }
}
