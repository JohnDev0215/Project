package com.amazon.avod.media.playback.reporting.aloysius.util;

import com.amazon.avod.media.playback.VideoPlayer;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusDiagnosticsReporter;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.amazon.avod.playback.PlaybackExperienceController;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AloysiusUtil {
    @Nullable
    public static PlaybackMediaEventReporters getMediaEventReporters(@Nullable VideoPlayer videoPlayer) {
        if (videoPlayer == null) {
            return null;
        }
        return getMediaEventReporters(videoPlayer.getPlaybackExperienceController());
    }

    @Nullable
    public static PlaybackMediaEventReporters getMediaEventReporters(@Nullable PlaybackExperienceController playbackExperienceController) {
        if (playbackExperienceController == null) {
            return null;
        }
        return playbackExperienceController.getAloysiusReporter();
    }

    @Nullable
    public static AloysiusDiagnosticsReporter getAloysiusDiagnosticsReporter(@Nullable VideoPlayer videoPlayer) {
        PlaybackMediaEventReporters mediaEventReporters = getMediaEventReporters(videoPlayer);
        if (mediaEventReporters == null) {
            return null;
        }
        return mediaEventReporters.getDiagnosticsReporter();
    }

    @Nullable
    public static AloysiusDiagnosticsReporter getAloysiusDiagnosticsReporter(@Nullable PlaybackMediaEventReporters playbackMediaEventReporters) {
        if (playbackMediaEventReporters == null) {
            return null;
        }
        return playbackMediaEventReporters.getDiagnosticsReporter();
    }
}
