package com.amazon.avod.playbackclient.subtitle.internal;

import com.amazon.avod.metrics.pmet.util.ReportableString;
import com.google.common.base.Preconditions;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum SubtitlePmetMetrics {
    HORIZONTAL_SUBTITLE_WITH_BEFORE_ANNOTATION,
    HORIZONTAL_SUBTITLE_WITH_AFTER_ANNOTATION,
    VERTICAL_SUBTITLES,
    VERTICAL_SUBTITLES_WITH_BEFORE_ANNOTATION,
    VERTICAL_SUBTITLES_WITH_AFTER_ANNOTATION,
    TEXT_COMBINE,
    FONT_SHEAR,
    ROTATED_TEXT,
    MISSING_FONT_METRICS;

    private static final Set<String> ERROR_NAMES = new HashSet();

    static {
        for (SubtitlePmetMetrics subtitlePmetMetrics : values()) {
            ERROR_NAMES.add(subtitlePmetMetrics.name());
        }
    }

    public static ReportableString toReportableString(@Nonnull SubtitlePmetMetrics subtitlePmetMetrics) {
        Preconditions.checkNotNull(subtitlePmetMetrics, "metric");
        return new ReportableString(subtitlePmetMetrics.name(), ERROR_NAMES, "UNKNOWN_ERROR");
    }
}
