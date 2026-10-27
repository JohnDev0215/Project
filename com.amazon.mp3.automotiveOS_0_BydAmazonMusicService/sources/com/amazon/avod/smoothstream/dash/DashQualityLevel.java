package com.amazon.avod.smoothstream.dash;

import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.urlvending.AudioTrackUtils;
import com.amazon.avod.media.AudioFormat;
import com.amazon.avod.smoothstream.DashManifestUtil;
import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class DashQualityLevel implements QualityLevel {
    private final String mAudioChannelConfiguration;
    private final int mAudioTag;
    private final String mBaseUrl;
    private final int mBitrate;
    private final int mBitsPerSample;
    private final String mCodecData;
    private final String mFourCC;
    private final String mFragmentInitialization;
    private final String mFragmentMedia;
    private final Float mFrameRate;
    private final String mInitRange;
    private final int mInitSize;
    private final boolean mIsHdr;
    private final boolean mIsSegmentTemplateBased;
    private final int mMaxHeight;
    private final int mMaxWidth;
    private final int mNalUnitlengthField;
    private final int mNumChannels;
    protected final long mQualityLevelHandle;
    private final int mSampleRate;
    private final long mTimeScale;

    public DashQualityLevel(@Nonnull DashManifestJni dashManifestJni, long j, boolean z) {
        long qualityLevelTimeScale;
        Preconditions.checkNotNull(dashManifestJni, "manifestJni");
        this.mIsSegmentTemplateBased = z;
        this.mQualityLevelHandle = j;
        this.mFragmentInitialization = dashManifestJni.getInitializationFromSegmentTemplate(j);
        this.mFragmentMedia = dashManifestJni.getMediaFromSegmentTemplate(j);
        this.mBaseUrl = dashManifestJni.getBaseUrlFromRepresentation(j);
        if (z) {
            qualityLevelTimeScale = dashManifestJni.getQualityLevelTimeScaleFromSegmentTemplate(j);
        } else {
            qualityLevelTimeScale = dashManifestJni.getQualityLevelTimeScale(j);
        }
        this.mTimeScale = qualityLevelTimeScale;
        this.mInitRange = dashManifestJni.getQualityLevelInitRange(j);
        this.mBitrate = dashManifestJni.getQualityLevelBitrate(j);
        this.mFourCC = dashManifestJni.getQualityLevelFourCC(j);
        this.mIsHdr = dashManifestJni.isHdr(j);
        this.mCodecData = dashManifestJni.getQualityLevelCodecData(j);
        this.mNalUnitlengthField = dashManifestJni.getQualityLevelNalUnitLengthField(j);
        this.mMaxWidth = dashManifestJni.getQualityLevelMaxWidth(j);
        this.mMaxHeight = dashManifestJni.getQualityLevelMaxHeight(j);
        this.mAudioTag = dashManifestJni.getQualityLevelAudioTag(j);
        this.mNumChannels = dashManifestJni.getQualityLevelNumChannels(j);
        this.mAudioChannelConfiguration = dashManifestJni.getAudioChannelConfiguration(j);
        this.mSampleRate = dashManifestJni.getQualityLevelSampleRate(j);
        this.mBitsPerSample = dashManifestJni.getQualityLevelBitsPerSample(j);
        this.mInitSize = dashManifestJni.getQualityLevelInitFragmentSize(j);
        this.mFrameRate = dashManifestJni.getQualityLevelFrameRate(j);
    }

    @Nullable
    String getBaseUrl() {
        return this.mBaseUrl;
    }

    @Nullable
    String getFragmentInitialization() {
        return this.mFragmentInitialization;
    }

    @Nullable
    String getFragmentMedia(int i) {
        return buildUri(this.mFragmentMedia, i);
    }

    @Nullable
    String buildUri(String str, int i) {
        if (str == null || !str.contains("$Number")) {
            return null;
        }
        String strSubstring = str.substring(str.indexOf("$"), str.lastIndexOf("$") + 1);
        int iIndexOf = strSubstring.indexOf(37);
        return str.replace(strSubstring, String.format(Locale.US, iIndexOf > 0 ? strSubstring.substring(iIndexOf, iIndexOf + 4) : "%d", Integer.valueOf(i)));
    }

    public String getInitRange() {
        return this.mInitRange;
    }

    public int getInitSize() {
        return this.mInitSize;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.QualityLevel
    public long getTimeScale() {
        return this.mTimeScale;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.QualityLevel
    public int getBitrate() {
        return this.mBitrate;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.QualityLevel
    public String getFourCC() {
        return this.mFourCC;
    }

    public boolean isHdr() {
        return this.mIsHdr;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.QualityLevel
    public String getCodecData() {
        return this.mCodecData;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.QualityLevel
    public int getNalUnitlengthField() {
        return this.mNalUnitlengthField;
    }

    public String toString() {
        return String.format(Locale.US, "%s@%d", getFourCC(), Integer.valueOf(getBitrate()));
    }

    public boolean isHD() {
        return DashManifestUtil.isHDQuality(getMaxHeight(), getMaxWidth());
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.QualityLevel
    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.QualityLevel
    public int getMaxHeight() {
        return this.mMaxHeight;
    }

    @Nonnull
    public AudioFormat getAudioFormat() {
        return AudioTrackUtils.convertFourCCToAudioFormat(getFourCC(), this.mAudioChannelConfiguration);
    }

    public int getAudioTag() {
        return this.mAudioTag;
    }

    public int getNumChannels() {
        return this.mNumChannels;
    }

    public int getSampleRate() {
        return this.mSampleRate;
    }

    public int getBitsPerSample() {
        return this.mBitsPerSample;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.QualityLevel
    public float getFrameRate() {
        Float f = this.mFrameRate;
        if (f != null) {
            return f.floatValue();
        }
        return 0.0f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DashQualityLevel dashQualityLevel = (DashQualityLevel) obj;
        if (this.mIsSegmentTemplateBased != dashQualityLevel.mIsSegmentTemplateBased || getTimeScale() != dashQualityLevel.getTimeScale() || getBitrate() != dashQualityLevel.getBitrate() || getNalUnitlengthField() != dashQualityLevel.getNalUnitlengthField() || getInitSize() != dashQualityLevel.getInitSize() || getMaxWidth() != dashQualityLevel.getMaxWidth() || getMaxHeight() != dashQualityLevel.getMaxHeight() || getAudioTag() != dashQualityLevel.getAudioTag() || getNumChannels() != dashQualityLevel.getNumChannels() || getSampleRate() != dashQualityLevel.getSampleRate() || getBitsPerSample() != dashQualityLevel.getBitsPerSample() || isHdr() != dashQualityLevel.isHdr()) {
            return false;
        }
        String str = this.mFragmentInitialization;
        if (str == null ? dashQualityLevel.mFragmentInitialization != null : !str.equals(dashQualityLevel.mFragmentInitialization)) {
            return false;
        }
        String str2 = this.mFragmentMedia;
        if (str2 == null ? dashQualityLevel.mFragmentMedia != null : !str2.equals(dashQualityLevel.mFragmentMedia)) {
            return false;
        }
        String str3 = this.mBaseUrl;
        if (str3 == null ? dashQualityLevel.mBaseUrl != null : !str3.equals(dashQualityLevel.mBaseUrl)) {
            return false;
        }
        if (getFourCC() == null ? dashQualityLevel.getFourCC() != null : !getFourCC().equals(dashQualityLevel.getFourCC())) {
            return false;
        }
        String str4 = this.mAudioChannelConfiguration;
        if (str4 == null ? dashQualityLevel.mAudioChannelConfiguration != null : !str4.equals(dashQualityLevel.mAudioChannelConfiguration)) {
            return false;
        }
        if (getCodecData() == null ? dashQualityLevel.getCodecData() == null : getCodecData().equals(dashQualityLevel.getCodecData())) {
            return getInitRange() != null ? getInitRange().equals(dashQualityLevel.getInitRange()) : dashQualityLevel.getInitRange() == null;
        }
        return false;
    }

    public int hashCode() {
        int timeScale = (((this.mIsSegmentTemplateBased ? 1 : 0) * 31) + ((int) (getTimeScale() ^ (getTimeScale() >>> 32)))) * 31;
        String str = this.mFragmentInitialization;
        int iHashCode = (timeScale + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.mFragmentMedia;
        int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.mBaseUrl;
        int iHashCode3 = (((((iHashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31) + getBitrate()) * 31) + (getFourCC() != null ? getFourCC().hashCode() : 0)) * 31;
        String str4 = this.mAudioChannelConfiguration;
        return ((((((((((((((((((((((iHashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31) + (getCodecData() != null ? getCodecData().hashCode() : 0)) * 31) + getNalUnitlengthField()) * 31) + getInitSize()) * 31) + getMaxWidth()) * 31) + getMaxHeight()) * 31) + getAudioTag()) * 31) + getNumChannels()) * 31) + getSampleRate()) * 31) + getBitsPerSample()) * 31) + (getInitRange() != null ? getInitRange().hashCode() : 0)) * 31) + (isHdr() ? 1 : 0);
    }
}
