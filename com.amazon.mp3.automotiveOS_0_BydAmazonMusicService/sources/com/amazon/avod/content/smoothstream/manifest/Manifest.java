package com.amazon.avod.content.smoothstream.manifest;

import com.amazon.avod.media.TimeSpan;
import java.util.List;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface Manifest {
    @Nullable
    StreamIndex getAudioStream(@Nonnull String str, @Nullable String str2);

    long getAvailabilityStartTimeMillis();

    @Nonnull
    Set<String> getAvailableAudioLanguages(String str);

    @Nonnull
    Set<String> getAvailableAudioLanguagesAcrossAudioStreams();

    TimeSpan getDuration();

    QualityLevel getHighestVideoQualityLevel();

    @Nullable
    StreamIndex getImageStream();

    @Nonnull
    TimeSpan getManifestEndTime();

    long getManifestHandle();

    @Nonnull
    TimeSpan getManifestStartTime();

    @Nonnull
    TimeSpan getMinimumUpdatePeriod();

    int getNumStreams();

    @Nullable
    ProtectionHeader getProtectionHeader();

    @Nonnull
    List<StreamIndex> getStreams();

    StreamIndex getSubtitleStream();

    @Nonnull
    TimeSpan getSuggestedFrontMargin();

    @Nonnull
    List<StreamIndex> getSupportedAudioStreams(@Nullable String str, @Nonnull List<String> list);

    @Nullable
    TimeSpan getTimeshiftBufferDuration();

    @Nullable
    StreamIndex getVideoStream();

    boolean hasMultiPeriodSupport();

    boolean hasStitchedAds();

    boolean isDynamic();

    boolean isEncrypted();

    boolean isHD();

    boolean isHdr();

    @Nullable
    Boolean isPatternTemplateManifest();

    void release();
}
