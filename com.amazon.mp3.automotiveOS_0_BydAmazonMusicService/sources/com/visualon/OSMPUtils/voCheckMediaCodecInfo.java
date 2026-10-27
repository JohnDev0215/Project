package com.visualon.OSMPUtils;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;

/* JADX INFO: loaded from: classes7.dex */
public class voCheckMediaCodecInfo {
    private static final int DEFAULT_SUPPORT_MAX_HEIGHT = 1080;
    private static final int DEFAULT_SUPPORT_MAX_WIDTH = 1920;
    private static final String TAG = "voCheckMediaCodecInfo";
    private Integer supportMaxHeight;
    private Integer supportMaxWidth;

    public voCheckMediaCodecInfo() {
        MediaCodecInfo.CodecCapabilities capabilitiesForType;
        this.supportMaxWidth = null;
        this.supportMaxHeight = null;
        int codecCount = MediaCodecList.getCodecCount();
        for (int i = 0; i < codecCount; i++) {
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i);
            if (!codecInfoAt.isEncoder()) {
                voLog.d(TAG, "MediaCodecList : MediaCodecInfo Decoder --> " + i, new Object[0]);
                String[] supportedTypes = codecInfoAt.getSupportedTypes();
                int length = supportedTypes.length;
                for (int i2 = 0; i2 < length; i2++) {
                    String str = supportedTypes[i2];
                    if (str.equalsIgnoreCase(voMimeTypes.VOVIDEO_VP8)) {
                        capabilitiesForType = codecInfoAt.getCapabilitiesForType(voMimeTypes.VOVIDEO_VP8);
                    } else if (str.equalsIgnoreCase(voMimeTypes.VOVIDEO_VP9)) {
                        capabilitiesForType = codecInfoAt.getCapabilitiesForType(voMimeTypes.VOVIDEO_VP9);
                    } else if (str.equalsIgnoreCase("video/avc")) {
                        capabilitiesForType = codecInfoAt.getCapabilitiesForType("video/avc");
                    } else if (str.equalsIgnoreCase("video/hevc")) {
                        capabilitiesForType = codecInfoAt.getCapabilitiesForType("video/hevc");
                    } else if (str.equalsIgnoreCase(voMimeTypes.VOVIDEO_MP4V)) {
                        capabilitiesForType = codecInfoAt.getCapabilitiesForType(voMimeTypes.VOVIDEO_MP4V);
                    } else if (str.equalsIgnoreCase(voMimeTypes.VOVIDEO_H263)) {
                        capabilitiesForType = codecInfoAt.getCapabilitiesForType(voMimeTypes.VOVIDEO_H263);
                    } else if (str.equalsIgnoreCase(voMimeTypes.VOVIDEO_MPEG2)) {
                        capabilitiesForType = codecInfoAt.getCapabilitiesForType(voMimeTypes.VOVIDEO_MPEG2);
                    } else if (str.equalsIgnoreCase("video/raw")) {
                        capabilitiesForType = codecInfoAt.getCapabilitiesForType("video/raw");
                    } else if (str.equalsIgnoreCase("video/dolby-vision")) {
                        capabilitiesForType = codecInfoAt.getCapabilitiesForType("video/dolby-vision");
                    } else if (str.equalsIgnoreCase("video/scrambled")) {
                        capabilitiesForType = codecInfoAt.getCapabilitiesForType("video/scrambled");
                    }
                    if (capabilitiesForType != null) {
                        MediaCodecInfo.VideoCapabilities videoCapabilities = capabilitiesForType.getVideoCapabilities();
                        Integer num = (Integer) videoCapabilities.getSupportedWidths().getUpper();
                        if (this.supportMaxHeight == null || num.intValue() > this.supportMaxWidth.intValue()) {
                            this.supportMaxWidth = num;
                        }
                        Integer num2 = (Integer) videoCapabilities.getSupportedHeights().getUpper();
                        if (this.supportMaxHeight == null || num2.intValue() > this.supportMaxHeight.intValue()) {
                            this.supportMaxHeight = num2;
                        }
                        voLog.i(TAG, "Check VideoDecoderSupportedTypes= " + str + ", supportMaxWidth= " + this.supportMaxWidth.toString() + ", supportMaxHeight= " + this.supportMaxHeight.toString(), new Object[0]);
                    }
                }
            }
        }
    }

    public Integer getSupportMaxWidth() {
        return this.supportMaxWidth;
    }

    public Integer getSupportMaxHeight() {
        return this.supportMaxHeight;
    }
}
