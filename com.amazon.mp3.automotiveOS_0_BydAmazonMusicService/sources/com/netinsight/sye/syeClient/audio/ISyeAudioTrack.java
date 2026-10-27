package com.netinsight.sye.syeClient.audio;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001R\u0016\u0010\u0005\u001a\u00020\u00028&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R\u0016\u0010\t\u001a\u00020\u00068&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u000b\u001a\u00020\u00068&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\bR\u0016\u0010\u000f\u001a\u00020\f8&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0011\u001a\u00020\u00068&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\bR\u0016\u0010\u0015\u001a\u00020\u00128&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Lcom/netinsight/sye/syeClient/audio/ISyeAudioTrack;", "", "Lcom/netinsight/sye/syeClient/audio/SyeAudioCodec;", "getAudioCodec", "()Lcom/netinsight/sye/syeClient/audio/SyeAudioCodec;", "audioCodec", "", "getLanguageDescription", "()Ljava/lang/String;", "languageDescription", "getCodecName", "codecName", "Lcom/netinsight/sye/syeClient/audio/SyeAudioCodecProfile;", "getAudioCodecProfile", "()Lcom/netinsight/sye/syeClient/audio/SyeAudioCodecProfile;", "audioCodecProfile", "getLanguage", "language", "", "getNumChannels", "()I", "numChannels", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public interface ISyeAudioTrack {
    SyeAudioCodec getAudioCodec();

    SyeAudioCodecProfile getAudioCodecProfile();

    String getCodecName();

    String getLanguage();

    String getLanguageDescription();

    int getNumChannels();
}
