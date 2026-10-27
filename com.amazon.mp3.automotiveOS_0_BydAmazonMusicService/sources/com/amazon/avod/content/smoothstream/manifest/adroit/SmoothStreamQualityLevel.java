package com.amazon.avod.content.smoothstream.manifest.adroit;

import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import java.util.Locale;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class SmoothStreamQualityLevel implements QualityLevel {
    private final int mIndex;
    private final com.amazon.avod.playback.smoothstream.QualityLevel mLevel;
    private final String mStringRepresentation;
    private final long mTimescale;

    @Override // com.amazon.avod.content.smoothstream.manifest.QualityLevel
    public float getFrameRate() {
        return 0.0f;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.QualityLevel
    public /* bridge */ /* synthetic */ int getMaxHeight() {
        return super.getMaxHeight();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.QualityLevel
    public /* bridge */ /* synthetic */ int getMaxWidth() {
        return super.getMaxWidth();
    }

    public SmoothStreamQualityLevel(com.amazon.avod.playback.smoothstream.QualityLevel qualityLevel, int i, long j) {
        this.mLevel = qualityLevel;
        this.mIndex = i;
        this.mTimescale = j;
        this.mStringRepresentation = String.format(Locale.US, "%s@%d", qualityLevel.getFourCC(), Integer.valueOf(qualityLevel.getBitrate()));
    }

    public int getIndex() {
        return this.mIndex;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.QualityLevel
    public long getTimeScale() {
        return this.mTimescale;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.QualityLevel
    public int getBitrate() {
        return this.mLevel.getBitrate();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.QualityLevel
    public String getFourCC() {
        return this.mLevel.getFourCC();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.QualityLevel
    public String getCodecData() {
        return this.mLevel.getCodecData();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.QualityLevel
    public int getNalUnitlengthField() {
        return this.mLevel.getNalUnitlengthField();
    }

    public String toString() {
        return this.mStringRepresentation;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SmoothStreamQualityLevel smoothStreamQualityLevel = (SmoothStreamQualityLevel) obj;
        if (getIndex() == smoothStreamQualityLevel.getIndex() && this.mTimescale == smoothStreamQualityLevel.mTimescale) {
            return this.mLevel.equals(smoothStreamQualityLevel.mLevel);
        }
        return false;
    }

    public int hashCode() {
        int iHashCode = ((this.mLevel.hashCode() * 31) + getIndex()) * 31;
        long j = this.mTimescale;
        return iHashCode + ((int) (j ^ (j >>> 32)));
    }
}
