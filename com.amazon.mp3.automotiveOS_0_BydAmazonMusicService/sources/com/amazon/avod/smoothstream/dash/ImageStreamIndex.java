package com.amazon.avod.smoothstream.dash;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.image.ImageStreamIndexInterface;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.pmet.PlaybackPmetMetricReporter;
import com.amazon.avod.util.CastUtils;
import com.amazon.avod.util.Preconditions2;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ImageStreamIndex extends DashStreamIndex implements ImageStreamIndexInterface {
    private final int mNumPeriods;
    private final long mPeriodEndTimeNanos;
    private final String mPeriodId;

    private ImageStreamIndex(@Nonnull DashManifestJni dashManifestJni, long j, long j2, long j3, long j4, int i, boolean z, long j5, @Nonnull DrmScheme drmScheme, @Nonnull SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig, @Nonnull PlaybackPmetMetricReporter playbackPmetMetricReporter, int i2) {
        super(dashManifestJni, j, j2, j4, i, z, j5, drmScheme, smoothStreamingPlaybackConfig, playbackPmetMetricReporter);
        this.mNumPeriods = i2;
        this.mPeriodEndTimeNanos = j3 != -1 ? TimeSpan.nanosecondsFromTimeScale(this.mManifestJni.getPeriodStartTime(j3), this.mManifestTimescale) : -1L;
        this.mPeriodId = this.mManifestJni.getPeriodIdString(j2);
    }

    public static ImageStreamIndex createImageStreamIndex(@Nonnull DashManifestJni dashManifestJni, long j, long j2, long j3, long j4, int i, boolean z, long j5, @Nonnull DrmScheme drmScheme, int i2) {
        return new ImageStreamIndex(dashManifestJni, j, j2, j3, j4, i, z, j5, drmScheme, SmoothStreamingPlaybackConfig.INSTANCE, PlaybackPmetMetricReporter.getInstance(), i2);
    }

    @Override // com.amazon.avod.smoothstream.dash.DashStreamIndex
    public void initializeIndex() throws ContentException {
        super.initializeIndex();
    }

    @Override // com.amazon.avod.smoothstream.dash.DashStreamIndex
    protected DashQualityLevel newTypedQualityLevel(int i) {
        return ImageQualityLevel.createImageQualityLevel(this.mManifestJni, this.mPeriodHandle, this.mAdaptationSetHandle, this.mManifestJni.getStreamQualityLevel(this.mAdaptationSetHandle, i), !this.mIsSegmentListBased, this.mPeriodStartTimeNanos, this.mPeriodEndTimeNanos);
    }

    @Override // com.amazon.avod.content.image.ImageStreamIndexInterface
    @Nonnull
    public String getImageUrl(@Nullable String str, @Nonnull QualityLevel qualityLevel, int i, @Nonnull String str2) {
        Preconditions.checkNotNull(qualityLevel, "quality");
        Preconditions.checkNotNull(str2, "periodId");
        Preconditions2.checkStateWeakly(str2.equals(this.mPeriodId), "periodId doesn't match");
        return getUrl(str, qualityLevel, i);
    }

    @Override // com.amazon.avod.smoothstream.dash.DashStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nonnull
    public String getUrl(@Nullable String str, @Nonnull QualityLevel qualityLevel, int i) {
        Preconditions.checkNotNull(qualityLevel, "quality");
        ImageQualityLevel imageQualityLevel = (ImageQualityLevel) CastUtils.castTo(qualityLevel, ImageQualityLevel.class);
        if (imageQualityLevel == null) {
            throw new IllegalArgumentException("Given quality level is not a ImageQualityLevel");
        }
        return getUrl(str, imageQualityLevel.getBaseUrl(), imageQualityLevel.getImageMediaUrl(i));
    }

    @Override // com.amazon.avod.content.image.ImageStreamIndexInterface
    @Nonnull
    public ImageQualityLevel[] getSortedImageQualityLevels(@Nonnull String str) {
        Preconditions.checkNotNull(str, "periodId");
        Preconditions2.checkStateWeakly(str.equals(this.mPeriodId), "periodId doesn't match");
        DashQualityLevel[] dashQualityLevelArr = this.mQualityLevels;
        ImageQualityLevel[] imageQualityLevelArr = (ImageQualityLevel[]) Arrays.copyOf(dashQualityLevelArr, dashQualityLevelArr.length, ImageQualityLevel[].class);
        Arrays.sort(imageQualityLevelArr, new Comparator<ImageQualityLevel>() { // from class: com.amazon.avod.smoothstream.dash.ImageStreamIndex.1
            @Override // java.util.Comparator
            public int compare(ImageQualityLevel imageQualityLevel, ImageQualityLevel imageQualityLevel2) {
                int iCompare = Ints.compare(imageQualityLevel.getTotalThumbnailPerChunk(), imageQualityLevel2.getTotalThumbnailPerChunk());
                return iCompare == 0 ? Longs.compare(imageQualityLevel.getThumbnailDurationNanos(), imageQualityLevel2.getThumbnailDurationNanos()) : iCompare;
            }
        });
        return imageQualityLevelArr;
    }

    @Override // com.amazon.avod.content.image.ImageStreamIndexInterface
    public int getNumPeriods() {
        return this.mNumPeriods;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nullable
    public String getPeriodIdWithTimestampNanos(long j) {
        if (j >= this.mPeriodStartTimeNanos) {
            return this.mPeriodId;
        }
        return null;
    }

    @Override // com.amazon.avod.content.image.ImageStreamIndexInterface
    @Nonnull
    public Set<String> getAllPeriodIds() {
        return Sets.newHashSet(this.mPeriodId);
    }
}
