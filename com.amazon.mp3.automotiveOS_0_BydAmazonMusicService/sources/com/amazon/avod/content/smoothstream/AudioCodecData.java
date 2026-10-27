package com.amazon.avod.content.smoothstream;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.smoothstream.manifest.AudioQualityLevel;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.media.AudioStreamType;
import com.amazon.avod.media.framework.util.BitUtils;
import com.amazon.avod.playback.sampling.SampleCodecData;
import com.amazon.avod.playback.sampling.SampleType;
import com.google.common.base.Strings;
import java.net.URL;
import java.nio.ByteBuffer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
class AudioCodecData implements CodecData {
    public static final int[] AAC_SAMPLING_FREQUENCY = {96000, 88200, 64000, 48000, 44100, 32000, 24000, 22050, 16000, 12000, 11025, 8000, 7350};

    @Override // com.amazon.avod.content.smoothstream.CodecData
    public long validateSample(@Nonnull ByteBuffer byteBuffer, long j, @Nullable URL url) throws ContentException {
        return j;
    }

    AudioCodecData() {
    }

    @Override // com.amazon.avod.content.smoothstream.CodecData
    @Nullable
    public SampleCodecData getSampleCodecData(QualityLevel qualityLevel) {
        return getSampleCodecData(qualityLevel, null, qualityLevel.getCodecData());
    }

    @Override // com.amazon.avod.content.smoothstream.CodecData
    @Nullable
    public SampleCodecData getSampleCodecData(SmoothStreamingURI smoothStreamingURI, Fragment fragment) {
        return getSampleCodecData(smoothStreamingURI.getQualityLevel(), fragment, fragment.getCodecPrivateData());
    }

    private SampleCodecData getSampleCodecData(QualityLevel qualityLevel, @Nullable Fragment fragment, @Nullable String str) {
        AudioQualityLevel audioQualityLevel = (AudioQualityLevel) qualityLevel;
        if (audioQualityLevel.getFourCC().equalsIgnoreCase(AudioStreamType.AACL.getFourCC()) || audioQualityLevel.getFourCC().equalsIgnoreCase(AudioStreamType.AACH.getFourCC()) || audioQualityLevel.getFourCC().equalsIgnoreCase(AudioStreamType.AACHV2.getFourCC())) {
            byte[] aACLConfig = getAACLConfig(audioQualityLevel);
            if (aACLConfig != null) {
                return new SampleCodecData(aACLConfig, new byte[0], new byte[0]);
            }
        } else {
            if (fragment != null && (audioQualityLevel.getFourCC().equalsIgnoreCase(AudioStreamType.DDP.getFourCC()) || audioQualityLevel.getFourCC().equalsIgnoreCase(AudioStreamType.ATMOS.getFourCC()))) {
                String codecPrivateData = fragment.getCodecPrivateData();
                if (Strings.isNullOrEmpty(codecPrivateData)) {
                    return null;
                }
                return SampleCodecData.fromHexString(codecPrivateData);
            }
            if (!Strings.isNullOrEmpty(str)) {
                return SampleCodecData.fromHexString(str);
            }
        }
        return null;
    }

    public static byte[] getAACLConfig(AudioQualityLevel audioQualityLevel) {
        int[] iArr;
        int i = 0;
        while (true) {
            iArr = AAC_SAMPLING_FREQUENCY;
            if (i >= iArr.length || iArr[i] == audioQualityLevel.getSampleRate()) {
                break;
            }
            i++;
        }
        if (i == iArr.length) {
            return null;
        }
        int numChannels = audioQualityLevel.getNumChannels();
        byte[] bArr = {0, 0};
        byte bCreateMaskedByte = (byte) (BitUtils.createMaskedByte(2, 0, 3, 5) | bArr[0]);
        bArr[0] = bCreateMaskedByte;
        bArr[0] = (byte) (bCreateMaskedByte | BitUtils.createMaskedByte(i, 1, 0, 3));
        byte bCreateMaskedByte2 = (byte) (BitUtils.createMaskedByte(i, 0, 7, 1) | bArr[1]);
        bArr[1] = bCreateMaskedByte2;
        bArr[1] = (byte) (BitUtils.createMaskedByte(numChannels, 0, 3, 4) | bCreateMaskedByte2);
        return bArr;
    }

    @Override // com.amazon.avod.content.smoothstream.CodecData
    @Nonnull
    public SampleType getSampleType() {
        return SampleType.AUDIO_SAMPLE;
    }
}
