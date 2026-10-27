package com.amazon.avod.media.download.plugin.reporting;

import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum SubtitleChangeCause {
    MEDIA_COMMAND("User:%s:MediaCommand"),
    SUBTITLE_MENU("User:%s:SubtitleMenu"),
    PLAYBACK_START("Automatic:%s:PlaybackStart"),
    SYSTEM_VALUE_CHANGE("Automatic:%s:SystemValueChange");

    private final String mReason;

    SubtitleChangeCause(@Nonnull String str) {
        this.mReason = (String) Preconditions.checkNotNull(str, "reason");
    }

    @Nonnull
    String getQosNote(@Nonnull SubtitleState subtitleState) {
        return String.format(Locale.US, this.mReason, subtitleState.getQosName());
    }
}
