package com.amazon.avod.media.playback.reporting;

import com.amazon.avod.playback.PlaybackEventReporter;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface EventReporterFactory {
    PlaybackEventReporter newStandaloneEventReporter(@Nullable String str);
}
