package com.amazon.avod.smoothstream;

import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.media.AudioStreamType;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.VideoStreamType;
import com.amazon.avod.smoothstream.dash.DashManifestJni;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableSet;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class DashManifestUtil {
    private static final Set<String> CODEC_PRIVATE_DATA_OPTIONAL_FOURCC = ImmutableSet.of(AudioStreamType.AACL.getFourCC(), AudioStreamType.AACH.getFourCC(), AudioStreamType.AACHV2.getFourCC(), VideoStreamType.AV01.getFourCC());

    public static boolean isHDQuality(int i, int i2) {
        return i >= 720 || i2 >= 1280;
    }

    public static boolean isCodecPrivateDataOptional(@Nonnull String str) {
        Preconditions.checkNotNull(str, "codecFourCC");
        return CODEC_PRIVATE_DATA_OPTIONAL_FOURCC.contains(str);
    }

    public static boolean isLastChunk(int i, int i2) {
        Preconditions.checkArgument(i > 0, "chunkCount");
        Preconditions.checkArgument(i2 >= 0, "chunkIndex");
        Preconditions.checkArgument(i2 < i, "Chunk index should be less than the number of chunks.");
        return i - 1 == i2;
    }

    public static int getAbsoluteChunkIndexFromNanos(long j, @Nonnull DashManifestJni dashManifestJni, long j2, long j3, int i, long j4, boolean z) {
        return convertRelativeToAbsoluteIndex(dashManifestJni, j2, getChunkIndexFromNanos(j, dashManifestJni, j2, j3, i, j4, z), z);
    }

    public static int convertRelativeToAbsoluteIndex(@Nonnull DashManifestJni dashManifestJni, long j, int i, boolean z) {
        return z ? i + dashManifestJni.getQualityLevelChunkStartNumber(dashManifestJni.getStreamQualityLevel(j, 0)) : i;
    }

    public static int convertAbsoluteToRelativeIndex(@Nonnull DashManifestJni dashManifestJni, long j, int i, boolean z) {
        return z ? i - dashManifestJni.getQualityLevelChunkStartNumber(dashManifestJni.getStreamQualityLevel(j, 0)) : i;
    }

    static int getChunkIndexFromNanos(long j, @Nonnull DashManifestJni dashManifestJni, long j2, long j3, int i, long j4, boolean z) {
        long streamChunkTimestamp;
        long streamChunkDurationFromSegmentDurations;
        long streamChunkTimestamp2;
        long streamChunkDurationFromSegmentDurations2;
        Preconditions.checkArgument(j > 0, "timeScale");
        Preconditions.checkArgument(i > 0, "chunkCount");
        Preconditions.checkArgument(j4 >= 0, "targetTimeNanos");
        Preconditions.checkNotNull(dashManifestJni, "manifestDashJni");
        int i2 = i - 1;
        boolean zIsSegmentTemplateBased = dashManifestJni.isSegmentTemplateBased(j2);
        if (zIsSegmentTemplateBased) {
            streamChunkTimestamp = dashManifestJni.getStreamChunkTimeStampFromSegmentTemplate(j2, i2);
        } else {
            streamChunkTimestamp = dashManifestJni.getStreamChunkTimestamp(j3, i2);
        }
        long jNanosecondsFromTimeScale = TimeSpan.nanosecondsFromTimeScale(streamChunkTimestamp, j);
        if (zIsSegmentTemplateBased) {
            streamChunkDurationFromSegmentDurations = dashManifestJni.getStreamChunkDurationFromSegmentTemplate(j2, i2);
        } else {
            streamChunkDurationFromSegmentDurations = dashManifestJni.getStreamChunkDurationFromSegmentDurations(j3, i2);
        }
        long jNanosecondsFromTimeScale2 = TimeSpan.nanosecondsFromTimeScale(streamChunkDurationFromSegmentDurations, j);
        long j5 = jNanosecondsFromTimeScale + jNanosecondsFromTimeScale2;
        if (j4 >= j5) {
            return i2 + (z ? (int) ((j4 - j5) / jNanosecondsFromTimeScale2) : 0);
        }
        while (i < i2) {
            int i3 = ((i2 - i) / 2) + i;
            if (zIsSegmentTemplateBased) {
                streamChunkTimestamp2 = dashManifestJni.getStreamChunkTimeStampFromSegmentTemplate(j2, i3);
            } else {
                streamChunkTimestamp2 = dashManifestJni.getStreamChunkTimestamp(j3, i3);
            }
            long jNanosecondsFromTimeScale3 = TimeSpan.nanosecondsFromTimeScale(streamChunkTimestamp2, j);
            if (zIsSegmentTemplateBased) {
                streamChunkDurationFromSegmentDurations2 = dashManifestJni.getStreamChunkDurationFromSegmentTemplate(j2, i3);
            } else {
                streamChunkDurationFromSegmentDurations2 = dashManifestJni.getStreamChunkDurationFromSegmentDurations(j3, i3);
            }
            if (TimeSpan.nanosecondsFromTimeScale(streamChunkDurationFromSegmentDurations2, j) + jNanosecondsFromTimeScale3 <= j4) {
                i = i3 + 1;
            } else {
                if (j4 >= jNanosecondsFromTimeScale3) {
                    return i3;
                }
                i2 = i3 - 1;
            }
        }
        return i;
    }

    public static long getChunksDurationInNanosFromAbsoluteIndices(long j, @Nonnull DashManifestJni dashManifestJni, long j2, long j3, int i, int i2, int i3, boolean z) {
        return getChunksDurationInNanos(j, dashManifestJni, j2, j3, i, convertAbsoluteToRelativeIndex(dashManifestJni, j2, i2, z), convertAbsoluteToRelativeIndex(dashManifestJni, j2, i3, z), z);
    }

    static long getChunksDurationInNanos(long j, @Nonnull DashManifestJni dashManifestJni, long j2, long j3, int i, int i2, int i3, boolean z) {
        long streamChunkDurationFromSegmentDurations;
        Preconditions.checkNotNull(dashManifestJni, "manifestDashJni");
        long j4 = 0;
        Preconditions.checkArgument(j > 0, "timeScale");
        Preconditions.checkArgument(i > 0, "chunkCount");
        Preconditions.checkArgument(i2 >= 0, "fromChunkIndex");
        Preconditions.checkArgument(i3 >= 0, "toChunkIndex");
        Preconditions.checkArgument(i3 >= i2);
        if (dashManifestJni.isSegmentListBased(j2)) {
            Preconditions.checkArgument(i > i3, "toChunkIndex is OOB");
        }
        boolean zIsSegmentTemplateBased = dashManifestJni.isSegmentTemplateBased(j2);
        while (i2 <= i3) {
            if (zIsSegmentTemplateBased) {
                streamChunkDurationFromSegmentDurations = dashManifestJni.getStreamChunkDurationFromSegmentTemplate(j2, i2);
            } else {
                streamChunkDurationFromSegmentDurations = dashManifestJni.getStreamChunkDurationFromSegmentDurations(j3, i2);
            }
            j4 += streamChunkDurationFromSegmentDurations;
            i2++;
        }
        return TimeSpan.nanosecondsFromTimeScale(j4, j);
    }

    public static int findClosestQualityIndex(QualityLevel[] qualityLevelArr, int i) {
        Preconditions.checkNotNull(qualityLevelArr, "qualityLevels");
        Preconditions.checkArgument(qualityLevelArr.length > 0, "qualityLevels should have at least 1 element");
        Preconditions.checkArgument(i >= 0, "bitrateBitsPerSecondToFind");
        int i2 = Integer.MAX_VALUE;
        int length = qualityLevelArr.length;
        for (int i3 = 0; i3 < qualityLevelArr.length; i3++) {
            int iAbs = Math.abs(qualityLevelArr[i3].getBitrate() - i);
            if (iAbs < i2) {
                length = i3;
                i2 = iAbs;
            }
        }
        return length;
    }

    public static int getQualityLevelIndexLessThanEqual(QualityLevel[] qualityLevelArr, int i) {
        Preconditions.checkNotNull(qualityLevelArr, "qualityLevels");
        Preconditions.checkArgument(qualityLevelArr.length > 0, "qualityLevels should have at least 1 element");
        Preconditions.checkArgument(i >= 0, "bitrateBitsPerSecondToFind");
        int i2 = Integer.MAX_VALUE;
        int i3 = Integer.MIN_VALUE;
        int i4 = -1;
        int i5 = -1;
        for (int i6 = 0; i6 < qualityLevelArr.length; i6++) {
            int bitrate = qualityLevelArr[i6].getBitrate();
            if (bitrate < i2) {
                i5 = i6;
                i2 = bitrate;
            }
            if (bitrate <= i && bitrate > i3) {
                i4 = i6;
                i3 = bitrate;
            }
        }
        return i4 < 0 ? i5 : i4;
    }

    public static int getQualityLevelIndexGreaterThanEqual(QualityLevel[] qualityLevelArr, int i) {
        Preconditions.checkNotNull(qualityLevelArr, "qualityLevels");
        Preconditions.checkArgument(qualityLevelArr.length > 0, "qualityLevels should have at least 1 element");
        Preconditions.checkArgument(i >= 0, "bitrateBitsPerSecondToFind");
        int i2 = Integer.MIN_VALUE;
        int i3 = Integer.MAX_VALUE;
        int i4 = -1;
        int i5 = -1;
        for (int i6 = 0; i6 < qualityLevelArr.length; i6++) {
            int bitrate = qualityLevelArr[i6].getBitrate();
            if (bitrate > i2) {
                i5 = i6;
                i2 = bitrate;
            }
            if (bitrate >= i && bitrate < i3) {
                i4 = i6;
                i3 = bitrate;
            }
        }
        return i4 < 0 ? i5 : i4;
    }

    public static double convertAspectRatioStringToDouble(@Nullable String str) {
        if (Strings.isNullOrEmpty(str)) {
            return -1.0d;
        }
        String[] strArrSplit = str.split(":");
        if (strArrSplit.length != 2) {
            return -1.0d;
        }
        try {
            double dDoubleValue = Double.valueOf(strArrSplit[0]).doubleValue();
            double dDoubleValue2 = Double.valueOf(strArrSplit[1]).doubleValue();
            if (dDoubleValue > 0.0d && dDoubleValue2 > 0.0d) {
                return dDoubleValue / dDoubleValue2;
            }
        } catch (NumberFormatException unused) {
        }
        return -1.0d;
    }
}
