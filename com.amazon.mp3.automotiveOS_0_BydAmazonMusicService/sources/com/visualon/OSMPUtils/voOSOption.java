package com.visualon.OSMPUtils;

import java.util.List;

/* JADX INFO: loaded from: classes7.dex */
public class voOSOption {
    public static final long OPTION_DECODE_DISABLE_DEBLOCK = 1;
    public static final long OPTION_DECODE_DOLBY = 1;
    public static final long OPTION_RENDERTYPE_NATIVE = 1;
    public static final long OPTION_RENDERTYPE_OPENGL = 4;
    public static final long OPTION_VIDEO_EFFECT_CLOSE_CAPTION = 1;
    public static final long OPTION_VIDEO_EFFECT_CLOSE_CAPTION_ON = 1;
    eVoOption mType;
    long mValue;

    public enum eVoOption {
        eoNone,
        eoVideoColor,
        eoVideoRender,
        eoVideoDecode,
        eoAudioDecode,
        eoVideoEffect,
        eoVideoCloseCaption,
        eoThreadCount,
        eoAudioAnimation,
        eoSocketType,
        eoThirdLibOp,
        eoNotLoadLibrary,
        eoHLSBitrate,
        eoLoop
    }

    public List<voOSOption> readConfig(String str) {
        return null;
    }

    public voOSOption(eVoOption evooption, long j) {
        eVoOption evooption2 = eVoOption.eoNone;
        this.mType = evooption;
        this.mValue = j;
    }

    public static voOSOption make(eVoOption evooption, long j) throws Exception {
        return new voOSOption(evooption, j);
    }

    public eVoOption getType() {
        return this.mType;
    }

    public long getValue() {
        return this.mValue;
    }
}
