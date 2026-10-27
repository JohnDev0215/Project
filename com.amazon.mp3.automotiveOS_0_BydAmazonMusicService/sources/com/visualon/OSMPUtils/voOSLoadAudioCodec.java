package com.visualon.OSMPUtils;

import com.visualon.OSMPUtils.voOSType;

/* JADX INFO: loaded from: classes7.dex */
public class voOSLoadAudioCodec {
    voOSType.VOOSMP_AUDIO_CODINGTYPE mCodec;
    boolean mEnable;

    public voOSLoadAudioCodec(voOSType.VOOSMP_AUDIO_CODINGTYPE voosmp_audio_codingtype, boolean z) {
        this.mCodec = voosmp_audio_codingtype;
        this.mEnable = z;
    }

    public voOSType.VOOSMP_AUDIO_CODINGTYPE getCodec() {
        return this.mCodec;
    }

    public boolean isEnable() {
        return this.mEnable;
    }
}
