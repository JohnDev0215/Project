package com.amazon.avod.media.playback.util;

import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.playback.sampling.SampleCodecData;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class VideoConfig extends CodecConfig {
    private final double mDisplayAspectRatio;
    private final VideoResolution mInitialVideoResolution;
    private final boolean mIsDynamic;
    private final boolean mIsHdrEnabled;
    private final float mMaxFrameRate;
    private final VideoResolution mMaxVideoResolution;
    private final SampleCodecData mSampleCodecData;
    private final QualityLevel[] mVideoQualityLevels;

    public VideoConfig(@Nonnull String str, @Nonnull VideoResolution videoResolution, @Nonnull VideoResolution videoResolution2, @Nullable SampleCodecData sampleCodecData, boolean z, boolean z2, double d) {
        this(str, videoResolution, videoResolution2, sampleCodecData, z, z2, d, 0.0f, null);
    }

    public VideoConfig(@Nonnull String str, @Nonnull VideoResolution videoResolution, @Nonnull VideoResolution videoResolution2, @Nullable SampleCodecData sampleCodecData, boolean z, boolean z2, double d, float f, @Nullable QualityLevel[] qualityLevelArr) {
        super(str);
        this.mInitialVideoResolution = (VideoResolution) Preconditions.checkNotNull(videoResolution, "initialVideoResolution");
        this.mMaxVideoResolution = (VideoResolution) Preconditions.checkNotNull(videoResolution2, "maxVideoResolution");
        this.mSampleCodecData = sampleCodecData;
        this.mIsHdrEnabled = z;
        this.mIsDynamic = z2;
        this.mDisplayAspectRatio = d;
        this.mMaxFrameRate = f;
        this.mVideoQualityLevels = qualityLevelArr;
    }

    @Nonnull
    public VideoResolution getVideoResolution() {
        return this.mInitialVideoResolution;
    }

    @Nonnull
    public VideoResolution getMaxVideoResolution() {
        return this.mMaxVideoResolution;
    }

    @Nullable
    public SampleCodecData getSampleCodecData() {
        return this.mSampleCodecData;
    }

    public boolean isHdrEnabled() {
        return this.mIsHdrEnabled;
    }

    public double getDisplayAspectRatio() {
        return this.mDisplayAspectRatio;
    }

    public float getMaxFrameRate() {
        return this.mMaxFrameRate;
    }

    @Nullable
    public QualityLevel[] getVideoQualityLevels() {
        return this.mVideoQualityLevels;
    }

    public boolean isDynamic() {
        return this.mIsDynamic;
    }

    public String toString() {
        return MoreObjects.toStringHelper(getClass()).add("FourCC", getFourCC()).add("InitialVideoResolution", getVideoResolution()).add("MaxVideoResolution", getMaxVideoResolution()).add("CodecPrivateData", getSampleCodecData() == null ? "Absent" : getSampleCodecData()).add("IsHdrEnabled", isHdrEnabled()).add("DisplayAspectRatio", getDisplayAspectRatio()).add("MaxFrameRate", getMaxFrameRate()).toString();
    }
}
