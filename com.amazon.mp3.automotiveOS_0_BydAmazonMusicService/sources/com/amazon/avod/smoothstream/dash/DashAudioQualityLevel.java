package com.amazon.avod.smoothstream.dash;

import com.amazon.avod.content.smoothstream.manifest.AudioQualityLevel;
import com.amazon.avod.media.AudioFormat;
import com.amazon.avod.util.DLog;
import com.google.common.collect.Maps;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class DashAudioQualityLevel extends DashQualityLevel implements AudioQualityLevel {
    public DashAudioQualityLevel(@Nonnull DashManifestJni dashManifestJni, long j, boolean z) {
        super(dashManifestJni, j, z);
    }

    @Override // com.amazon.avod.smoothstream.dash.DashQualityLevel, com.amazon.avod.content.smoothstream.manifest.AudioQualityLevel
    @Nonnull
    public AudioFormat getAudioFormat() {
        return super.getAudioFormat();
    }

    @Override // com.amazon.avod.smoothstream.dash.DashQualityLevel, com.amazon.avod.content.smoothstream.manifest.AudioQualityLevel
    public int getAudioTag() {
        return super.getAudioTag();
    }

    @Override // com.amazon.avod.smoothstream.dash.DashQualityLevel, com.amazon.avod.content.smoothstream.manifest.AudioQualityLevel
    public int getNumChannels() {
        int numChannels = super.getNumChannels();
        if (numChannels > 0) {
            return numChannels;
        }
        DLog.devf("Unable to find valid channel config in manifest, falling back to hard-code based on fourCC");
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$media$AudioFormat[getAudioFormat().ordinal()];
        return (i == 1 || i == 2) ? 6 : 2;
    }

    /* JADX INFO: renamed from: com.amazon.avod.smoothstream.dash.DashAudioQualityLevel$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$media$AudioFormat;

        static {
            int[] iArr = new int[AudioFormat.values().length];
            $SwitchMap$com$amazon$avod$media$AudioFormat = iArr;
            try {
                iArr[AudioFormat.AC_3_7_1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$AudioFormat[AudioFormat.AC_3_5_1.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$AudioFormat[AudioFormat.STEREO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // com.amazon.avod.smoothstream.dash.DashQualityLevel, com.amazon.avod.content.smoothstream.manifest.AudioQualityLevel
    public int getSampleRate() {
        return super.getSampleRate();
    }

    @Override // com.amazon.avod.smoothstream.dash.DashQualityLevel, com.amazon.avod.content.smoothstream.manifest.AudioQualityLevel
    public int getBitsPerSample() {
        return super.getBitsPerSample();
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

    @Override // com.amazon.avod.smoothstream.dash.DashQualityLevel
    public String toString() {
        return toJson().toString();
    }
}
