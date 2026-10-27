package com.amazon.avod.content.smoothstream.manifest;

import com.amazon.avod.media.AudioFormat;
import com.amazon.avod.media.VideoResolution;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public interface StreamIndex {
    @Nonnull
    AudioFormat getAudioFormat();

    String getAudioTrackId();

    long getChunkDurationInNanos(int i);

    int getChunkIndexFromNanos(long j);

    double getChunkQualityScore(@Nonnull QualityLevel qualityLevel, int i);

    long getChunkTimeInNanos(int i);

    long getChunkTimeOffsetInNanos(int i);

    QualityLevel getClosestQualityLevel(int i, int i2);

    VideoQualityLevel getClosestQualityLevelWithResolutionCap(int i, int i2, @Nonnull VideoResolution videoResolution);

    @Nullable
    byte[] getDefaultKeyId();

    double getDisplayAspectRatio();

    long getEncodeTimeMillis(long j);

    long getExpectedChunkSizeInBytes(QualityLevel qualityLevel, int i);

    long getExpectedInitChunkSizeInBytes(QualityLevel qualityLevel);

    @Nonnull
    String getFourCC();

    @Nonnull
    Map<String, String> getHeaders(@Nonnull QualityLevel qualityLevel, int i);

    int getIndex();

    @Nonnull
    Map<String, String> getInitHeaders(@Nonnull QualityLevel qualityLevel);

    String getInitUrl(@Nullable String str, QualityLevel qualityLevel, int i);

    String getLanguage();

    float getMaxFrameRate();

    int getMaxHeight();

    int getMaxWidth();

    int getNumChunks();

    @Nullable
    default String getPeriodIdWithTimestampNanos(long j) {
        return null;
    }

    QualityLevel getQualityLevel(int i, int i2);

    QualityLevel getQualityLevel(int i, QualityLevel qualityLevel);

    QualityLevel getQualityLevelGreaterThanEqual(int i, int i2);

    QualityLevel getQualityLevelLessThanEqual(int i, int i2);

    QualityLevel[] getSortedQualityLevels(int i);

    long getStreamDurationInNanos();

    long getStreamEndTimestampNanos();

    long getStreamHandle();

    long getStreamStartTimestampNanos();

    StreamType getType();

    String getUrl(@Nullable String str, QualityLevel qualityLevel, int i);

    boolean isAudio();

    boolean isHdr();

    boolean isLastDownloadableChunk(int i);

    boolean isLastPlayableChunk(int i);

    boolean isOutOfWindow(int i);

    boolean isVideo();

    void release();

    boolean requiresInitFragments();

    JSONObject toJson();
}
