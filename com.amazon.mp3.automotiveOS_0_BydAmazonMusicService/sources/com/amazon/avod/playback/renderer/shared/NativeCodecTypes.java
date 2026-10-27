package com.amazon.avod.playback.renderer.shared;

import com.amazon.avod.media.AudioStreamType;
import com.amazon.avod.media.playback.support.ConfigurablePlaybackSupportEvaluator;
import com.amazon.avod.playback.PlaybackException;
import com.google.common.collect.ImmutableSet;
import java.util.Locale;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class NativeCodecTypes {
    public static final int AUDIO_STREAM_AAC = 2732;
    public static final int AUDIO_STREAM_DDP = 3544;
    public static final int VIDEO_STREAM_AVC = 612;
    public static final int VIDEO_STREAM_HEVC = 613;
    private static final Set<String> HEVC_FOUR_CCS = ImmutableSet.of("HVC1", "HEV1", ConfigurablePlaybackSupportEvaluator.PRS_HEVC_OVERRIDE);
    private static final Set<String> AVC_FOUR_CCS = ImmutableSet.of("AVC1", ConfigurablePlaybackSupportEvaluator.PRS_AVC_OVERRIDE);

    public static int valueOf(String str) throws PlaybackException {
        if (str.equalsIgnoreCase(AudioStreamType.AACL.getFourCC()) || str.equalsIgnoreCase(AudioStreamType.AACH.getFourCC()) || str.equalsIgnoreCase(AudioStreamType.AACHV2.getFourCC())) {
            return AUDIO_STREAM_AAC;
        }
        if (str.equalsIgnoreCase(AudioStreamType.DDP.getFourCC()) || str.equalsIgnoreCase(AudioStreamType.ATMOS.getFourCC())) {
            return AUDIO_STREAM_DDP;
        }
        Set<String> set = AVC_FOUR_CCS;
        Locale locale = Locale.US;
        if (set.contains(str.toUpperCase(locale))) {
            return VIDEO_STREAM_AVC;
        }
        if (HEVC_FOUR_CCS.contains(str.toUpperCase(locale))) {
            return VIDEO_STREAM_HEVC;
        }
        throw new PlaybackException(PlaybackException.PlaybackError.INTERNAL_FATAL_ERROR, "Unknown requested fourCC: " + str);
    }
}
