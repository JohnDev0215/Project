package com.amazon.avod.smoothstream.dash;

import com.amazon.avod.content.image.ImageQualityLevelInterface;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.util.DLog;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ImageQualityLevel extends DashQualityLevel implements ImageQualityLevelInterface {
    private final long mAdaptationSetHandle;
    private int mAvailabilityTimeOffsetSeconds;
    private int mChunkNumCol;
    private int mChunkNumRow;
    private int mChunkStartNumber;
    private long mImageDurationNanos;
    private String mImageMediaUrlBase;
    private boolean mIsInitialized;
    private final long mPeriodEndNanos;
    private final long mPeriodHandle;
    private final long mPeriodStartNanos;
    private long mPresentationTimeOffsetNanos;
    private long mStreamStartNanos;

    @Nonnull
    public static ImageQualityLevel createImageQualityLevel(@Nonnull DashManifestJni dashManifestJni, long j, long j2, long j3, boolean z, long j4, long j5) {
        ImageQualityLevel imageQualityLevel = new ImageQualityLevel(dashManifestJni, j, j2, j3, z, j4, j5);
        imageQualityLevel.initialize(dashManifestJni);
        return imageQualityLevel;
    }

    private ImageQualityLevel(@Nonnull DashManifestJni dashManifestJni, long j, long j2, long j3, boolean z, long j4, long j5) {
        super(dashManifestJni, j3, z);
        this.mIsInitialized = false;
        this.mPeriodHandle = j;
        this.mAdaptationSetHandle = j2;
        this.mPeriodStartNanos = j4;
        this.mPeriodEndNanos = j5;
    }

    private void initialize(@Nonnull DashManifestJni dashManifestJni) {
        EssentialProperty essentialProperty = dashManifestJni.getEssentialProperty(this.mQualityLevelHandle, 0);
        if (essentialProperty == null) {
            DLog.devf("No essential property found for image stream. Aborting initialization");
            return;
        }
        this.mImageDurationNanos = TimeSpan.nanosecondsFromTimeScale(dashManifestJni.getDurationFromSegmentTemplate(this.mPeriodHandle, this.mAdaptationSetHandle, this.mQualityLevelHandle), dashManifestJni.getTimescaleFromSegmentTemplate(this.mPeriodHandle, this.mAdaptationSetHandle, this.mQualityLevelHandle));
        this.mAvailabilityTimeOffsetSeconds = dashManifestJni.getAvailabilityTimeOffsetFromSegmentTemplate(this.mPeriodHandle, this.mAdaptationSetHandle, this.mQualityLevelHandle) / dashManifestJni.getTimescaleFromSegmentTemplate(this.mPeriodHandle, this.mAdaptationSetHandle, this.mQualityLevelHandle);
        this.mImageMediaUrlBase = dashManifestJni.getMediaFromSegmentTemplate(this.mPeriodHandle, this.mAdaptationSetHandle, this.mQualityLevelHandle);
        this.mChunkStartNumber = dashManifestJni.getStartNumberFromSegmentTemplate(this.mPeriodHandle, this.mAdaptationSetHandle, this.mQualityLevelHandle);
        long jNanosecondsFromTimeScale = TimeSpan.nanosecondsFromTimeScale(dashManifestJni.getPresentationTimeOffsetFromSegmentTemplate(this.mPeriodHandle, this.mAdaptationSetHandle, this.mQualityLevelHandle), dashManifestJni.getTimescaleFromSegmentTemplate(this.mPeriodHandle, this.mAdaptationSetHandle, this.mQualityLevelHandle));
        this.mPresentationTimeOffsetNanos = jNanosecondsFromTimeScale;
        this.mStreamStartNanos = this.mPeriodStartNanos - jNanosecondsFromTimeScale;
        try {
            String[] strArrSplit = essentialProperty.getValue().split("x", 2);
            Preconditions.checkArgument(strArrSplit.length == 2, String.format("invalid essentialproperty value: %s", essentialProperty.getValue()));
            this.mChunkNumCol = Integer.parseInt(strArrSplit[0]);
            int i = Integer.parseInt(strArrSplit[1]);
            this.mChunkNumRow = i;
            Preconditions.checkArgument(i > 0 && this.mChunkNumCol > 0, String.format("invalid essentialproperty tile dimensions: column: %s, row: %s", Integer.valueOf(this.mChunkNumCol), Integer.valueOf(this.mChunkNumRow)));
            this.mIsInitialized = true;
        } catch (IllegalArgumentException e) {
            DLog.warnf("Caught exception trying to parse tile dimensions: %s, value: %s", e, essentialProperty.getValue());
        }
    }

    @Override // com.amazon.avod.smoothstream.dash.DashQualityLevel
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ImageQualityLevel)) {
            return false;
        }
        ImageQualityLevel imageQualityLevel = (ImageQualityLevel) obj;
        return this.mPeriodStartNanos == imageQualityLevel.mPeriodStartNanos && this.mAvailabilityTimeOffsetSeconds == imageQualityLevel.mAvailabilityTimeOffsetSeconds && this.mPresentationTimeOffsetNanos == imageQualityLevel.mPresentationTimeOffsetNanos && this.mChunkNumCol == imageQualityLevel.mChunkNumCol && this.mChunkNumRow == imageQualityLevel.mChunkNumRow && this.mImageDurationNanos == imageQualityLevel.mImageDurationNanos && Objects.equal(this.mImageMediaUrlBase, imageQualityLevel.mImageMediaUrlBase) && this.mChunkStartNumber == imageQualityLevel.mChunkStartNumber;
    }

    @Override // com.amazon.avod.smoothstream.dash.DashQualityLevel
    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.mPeriodStartNanos), Integer.valueOf(this.mAvailabilityTimeOffsetSeconds), Long.valueOf(this.mPresentationTimeOffsetNanos), Integer.valueOf(this.mChunkNumCol), Integer.valueOf(this.mChunkNumRow), Long.valueOf(this.mImageDurationNanos), this.mImageMediaUrlBase, Integer.valueOf(this.mChunkStartNumber));
    }

    @Override // com.amazon.avod.smoothstream.dash.DashQualityLevel
    @Nonnull
    public String toString() {
        return String.format(Locale.ENGLISH, "%sx%s image:%swx%shx%ss", Integer.valueOf(getChunkNumCol()), Integer.valueOf(getChunkNumRow()), Integer.valueOf(getMaxWidth()), Integer.valueOf(getMaxHeight()), Long.valueOf(TimeUnit.NANOSECONDS.toSeconds(getImageDurationNanos())));
    }

    @Override // com.amazon.avod.content.image.ImageQualityLevelInterface
    public boolean isInitialized() {
        return this.mIsInitialized;
    }

    @Override // com.amazon.avod.content.image.ImageQualityLevelInterface
    public int getAvailabilityTimeOffsetSeconds() {
        return this.mAvailabilityTimeOffsetSeconds;
    }

    @Override // com.amazon.avod.content.image.ImageQualityLevelInterface
    @Nullable
    public String getImageMediaUrl(int i) {
        return buildUri(this.mImageMediaUrlBase, i);
    }

    @Override // com.amazon.avod.content.image.ImageQualityLevelInterface
    public long getImageDurationNanos() {
        return this.mImageDurationNanos;
    }

    @Override // com.amazon.avod.content.image.ImageQualityLevelInterface
    public long getThumbnailTimeInNanos(long j, int i) {
        Preconditions.checkState(this.mIsInitialized, "cannot call getThumbnailTimeInNanos before initialization");
        return ((j - ((long) this.mChunkStartNumber)) * this.mImageDurationNanos) + (((long) i) * getThumbnailDurationNanos()) + this.mStreamStartNanos;
    }

    @Override // com.amazon.avod.content.image.ImageQualityLevelInterface
    public long getChunkIndexFromNanos(long j) {
        Preconditions.checkState(this.mIsInitialized, "cannot call getChunkIndexFromNanos before initialization");
        long j2 = this.mImageDurationNanos;
        if (j2 == 0) {
            DLog.warnf("ImageDuration is 0");
            return 0L;
        }
        return ((j - this.mStreamStartNanos) / j2) + ((long) this.mChunkStartNumber);
    }

    @Override // com.amazon.avod.content.image.ImageQualityLevelInterface
    public int getThumbnailIndexFromNanos(long j) {
        Preconditions.checkState(this.mIsInitialized, "cannot call getThumbnailIndexFromNanos before initialization");
        long j2 = this.mImageDurationNanos;
        if (j2 == 0) {
            DLog.warnf("ImageDuration is 0");
            return 0;
        }
        return (int) (((j - this.mStreamStartNanos) % j2) / getThumbnailDurationNanos());
    }

    @Override // com.amazon.avod.content.image.ImageQualityLevelInterface
    public int getChunkNumCol() {
        return this.mChunkNumCol;
    }

    @Override // com.amazon.avod.content.image.ImageQualityLevelInterface
    public int getChunkNumRow() {
        return this.mChunkNumRow;
    }

    @Override // com.amazon.avod.content.image.ImageQualityLevelInterface
    public int getTotalThumbnailPerChunk() {
        return this.mChunkNumCol * this.mChunkNumRow;
    }

    @Override // com.amazon.avod.content.image.ImageQualityLevelInterface
    public int getThumbnailWidthInPixels() {
        return getMaxWidth() / this.mChunkNumCol;
    }

    @Override // com.amazon.avod.content.image.ImageQualityLevelInterface
    public int getThumbnailHeightInPixels() {
        return getMaxHeight() / this.mChunkNumRow;
    }

    @Override // com.amazon.avod.content.image.ImageQualityLevelInterface
    public long getThumbnailDurationNanos() {
        return this.mImageDurationNanos / ((long) getTotalThumbnailPerChunk());
    }
}
