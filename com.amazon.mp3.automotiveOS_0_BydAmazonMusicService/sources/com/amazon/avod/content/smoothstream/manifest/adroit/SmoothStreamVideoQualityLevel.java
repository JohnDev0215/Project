package com.amazon.avod.content.smoothstream.manifest.adroit;

import com.amazon.avod.content.smoothstream.manifest.VideoQualityLevel;

/* JADX INFO: loaded from: classes2.dex */
public class SmoothStreamVideoQualityLevel extends SmoothStreamQualityLevel implements VideoQualityLevel {
    private final com.amazon.avod.playback.smoothstream.VideoQualityLevel mVideoQualityLevel;

    public SmoothStreamVideoQualityLevel(com.amazon.avod.playback.smoothstream.VideoQualityLevel videoQualityLevel, int i, long j) {
        super(videoQualityLevel, i, j);
        this.mVideoQualityLevel = videoQualityLevel;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.VideoQualityLevel
    public boolean isHD() {
        return ManifestUtil.isHDQuality(this.mVideoQualityLevel);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.adroit.SmoothStreamQualityLevel, com.amazon.avod.content.smoothstream.manifest.QualityLevel
    public int getMaxWidth() {
        return this.mVideoQualityLevel.getMaxWidth();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.adroit.SmoothStreamQualityLevel, com.amazon.avod.content.smoothstream.manifest.QualityLevel
    public int getMaxHeight() {
        return this.mVideoQualityLevel.getMaxHeight();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.adroit.SmoothStreamQualityLevel
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        SmoothStreamVideoQualityLevel smoothStreamVideoQualityLevel = (SmoothStreamVideoQualityLevel) obj;
        com.amazon.avod.playback.smoothstream.VideoQualityLevel videoQualityLevel = this.mVideoQualityLevel;
        if (videoQualityLevel != null) {
            return videoQualityLevel.equals(smoothStreamVideoQualityLevel.mVideoQualityLevel);
        }
        return smoothStreamVideoQualityLevel.mVideoQualityLevel == null;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.adroit.SmoothStreamQualityLevel
    public int hashCode() {
        int iHashCode = super.hashCode() * 31;
        com.amazon.avod.playback.smoothstream.VideoQualityLevel videoQualityLevel = this.mVideoQualityLevel;
        return iHashCode + (videoQualityLevel != null ? videoQualityLevel.hashCode() : 0);
    }
}
