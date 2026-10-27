package com.visualon.OSMPUtils;

import android.os.Parcel;
import com.visualon.OSMPUtils.voMediaCodecUtil;

/* JADX INFO: loaded from: classes7.dex */
public class VOCodecCheckerMC {
    private static final String TAG = "@@@VOCodecCheckerMC";

    public VOCodecCheckerMC() {
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, "Construct!", new Object[0]);
        }
    }

    public boolean supportCodecV(String str, Object obj) throws voMediaCodecUtil.DecoderQueryException {
        voLog.i(TAG, "supportCodecV!" + str, new Object[0]);
        VOVFormat vOVFormat = VOVFormat.parse((Parcel) obj);
        if (vOVFormat != null) {
            voLog.i(TAG, "VOVFormat:" + vOVFormat.codec + vOVFormat.frameRate, new Object[0]);
        }
        return supportsFormat(str, vOVFormat);
    }

    public boolean supportCodecA(String str, Object obj) throws voMediaCodecUtil.DecoderQueryException {
        voLog.i(TAG, "supportCodecA!" + str, new Object[0]);
        VOAFormat vOAFormat = VOAFormat.parse((Parcel) obj);
        if (vOAFormat != null) {
            voLog.i(TAG, "VOAFormat:" + vOAFormat.codec + vOAFormat.channelCount, new Object[0]);
        }
        return supportsFormat(str, vOAFormat);
    }

    private boolean supportsFormat(String str, VOVFormat vOVFormat) throws voMediaCodecUtil.DecoderQueryException {
        int i;
        int i2;
        if (!voMimeTypes.isVideo(str)) {
            return false;
        }
        if (str.equals(voMimeTypes.VOVIDEO_UNKNOWN)) {
            return true;
        }
        voMediaCodecInfo decoderInfo = voMediaCodecUtil.getDecoderInfo(str, false);
        if (decoderInfo == null) {
            voLog.i(TAG, "could not find valid codec info, return false.", new Object[0]);
            return false;
        }
        if (vOVFormat == null) {
            voLog.i(TAG, "new a format by mimeType to check codec.", new Object[0]);
            vOVFormat = new VOVFormat(voMimeTypes.getDefaultCodecFromMimeType(str), 0, 0, 0.0d);
        }
        if (vOVFormat == null) {
            return false;
        }
        boolean zIsCodecSupported = decoderInfo.isCodecSupported(vOVFormat.codec);
        if (zIsCodecSupported && (i = vOVFormat.width) > 0 && (i2 = vOVFormat.height) > 0) {
            zIsCodecSupported = decoderInfo.isVideoSizeAndRateSupportedV21(i, i2, vOVFormat.frameRate);
        }
        voLog.i(TAG, "check " + str + " with " + vOVFormat.codec + ", get result " + zIsCodecSupported, new Object[0]);
        return zIsCodecSupported;
    }

    private boolean supportsFormat(String str, VOAFormat vOAFormat) throws voMediaCodecUtil.DecoderQueryException {
        int i;
        if (!voMimeTypes.isAudio(str)) {
            return false;
        }
        boolean z = true;
        if (str.equals(voMimeTypes.VOAUDIO_UNKNOWN)) {
            return true;
        }
        if (str.equals("audio/mp4a")) {
            str = voMimeTypes.VOAUDIO_AAC;
        }
        voMediaCodecInfo decoderInfo = voMediaCodecUtil.getDecoderInfo(str, false);
        if (decoderInfo == null) {
            voLog.i(TAG, "could not find valid codec info, return false.", new Object[0]);
            return false;
        }
        if (vOAFormat == null) {
            voLog.i(TAG, "new a format by mimeType to check codec.", new Object[0]);
            vOAFormat = new VOAFormat(voMimeTypes.getDefaultCodecFromMimeType(str), 0, 0);
        }
        if (vOAFormat == null) {
            return false;
        }
        int i2 = vOAFormat.sampleRate;
        if ((i2 != 0 && !decoderInfo.isAudioSampleRateSupportedV21(i2)) || ((i = vOAFormat.channelCount) != 0 && !decoderInfo.isAudioChannelCountSupportedV21(i))) {
            z = false;
        }
        voLog.i(TAG, "check " + str + " with " + vOAFormat.codec + " " + vOAFormat.sampleRate + " " + vOAFormat.channelCount + ", get result " + z, new Object[0]);
        return z;
    }
}
