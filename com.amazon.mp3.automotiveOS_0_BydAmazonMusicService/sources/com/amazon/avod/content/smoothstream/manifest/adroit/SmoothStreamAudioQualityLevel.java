package com.amazon.avod.content.smoothstream.manifest.adroit;

import com.amazon.avod.content.smoothstream.manifest.AudioQualityLevel;
import com.amazon.avod.content.urlvending.AudioTrackUtils;
import com.amazon.avod.media.AudioFormat;
import com.amazon.avod.media.AudioStreamType;
import com.google.common.collect.Maps;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class SmoothStreamAudioQualityLevel extends SmoothStreamQualityLevel implements AudioQualityLevel {
    private final com.amazon.avod.playback.smoothstream.AudioQualityLevel mAudioQualityLevel;

    @Override // com.amazon.avod.content.smoothstream.manifest.adroit.SmoothStreamQualityLevel, com.amazon.avod.content.smoothstream.manifest.QualityLevel
    public /* bridge */ /* synthetic */ int getMaxHeight() {
        return super.getMaxHeight();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.adroit.SmoothStreamQualityLevel, com.amazon.avod.content.smoothstream.manifest.QualityLevel
    public /* bridge */ /* synthetic */ int getMaxWidth() {
        return super.getMaxWidth();
    }

    public SmoothStreamAudioQualityLevel(com.amazon.avod.playback.smoothstream.AudioQualityLevel audioQualityLevel, int i, long j) {
        super(audioQualityLevel, i, j);
        this.mAudioQualityLevel = audioQualityLevel;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.AudioQualityLevel
    @Nonnull
    public AudioFormat getAudioFormat() {
        return AudioTrackUtils.convertFourCCToAudioFormat(getFourCC(), null);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.adroit.SmoothStreamQualityLevel, com.amazon.avod.content.smoothstream.manifest.QualityLevel
    @Nonnull
    public String getFourCC() {
        com.amazon.avod.playback.smoothstream.AudioQualityLevel audioQualityLevel = this.mAudioQualityLevel;
        if (audioQualityLevel != null && audioQualityLevel.isAtmos()) {
            return AudioStreamType.ATMOS.getFourCC();
        }
        return super.getFourCC();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.AudioQualityLevel
    public int getAudioTag() {
        return this.mAudioQualityLevel.getAudioTag();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.AudioQualityLevel
    public int getNumChannels() {
        return this.mAudioQualityLevel.getNumChannels();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.AudioQualityLevel
    public int getSampleRate() {
        return this.mAudioQualityLevel.getSampleRate();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.AudioQualityLevel
    public int getBitsPerSample() {
        return this.mAudioQualityLevel.getBitsPerSample();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.adroit.SmoothStreamQualityLevel
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        SmoothStreamAudioQualityLevel smoothStreamAudioQualityLevel = (SmoothStreamAudioQualityLevel) obj;
        com.amazon.avod.playback.smoothstream.AudioQualityLevel audioQualityLevel = this.mAudioQualityLevel;
        if (audioQualityLevel != null) {
            return audioQualityLevel.equals(smoothStreamAudioQualityLevel.mAudioQualityLevel);
        }
        return smoothStreamAudioQualityLevel.mAudioQualityLevel == null;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.adroit.SmoothStreamQualityLevel
    public int hashCode() {
        int iHashCode = super.hashCode() * 31;
        com.amazon.avod.playback.smoothstream.AudioQualityLevel audioQualityLevel = this.mAudioQualityLevel;
        return iHashCode + (audioQualityLevel != null ? audioQualityLevel.hashCode() : 0);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.AudioQualityLevel
    @Nonnull
    public JSONObject toJson() {
        HashMap mapNewHashMap = Maps.newHashMap();
        mapNewHashMap.put("codec", getCodecData());
        mapNewHashMap.put("fourCC", getFourCC());
        mapNewHashMap.put("bitrate", Integer.valueOf(getBitrate()));
        mapNewHashMap.put("nalUnit", Integer.valueOf(getNalUnitlengthField()));
        mapNewHashMap.put("timeScale", Long.valueOf(getTimeScale()));
        mapNewHashMap.put("audioTag", Integer.valueOf(getAudioTag()));
        mapNewHashMap.put("audioFormat", getAudioFormat().name());
        mapNewHashMap.put("numberChannels", Integer.valueOf(getNumChannels()));
        mapNewHashMap.put("sampleRate", Integer.valueOf(getSampleRate()));
        mapNewHashMap.put("bitsPerSample", Integer.valueOf(getBitsPerSample()));
        return new JSONObject((Map<?, ?>) mapNewHashMap);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.adroit.SmoothStreamQualityLevel
    public String toString() {
        return toJson().toString();
    }
}
