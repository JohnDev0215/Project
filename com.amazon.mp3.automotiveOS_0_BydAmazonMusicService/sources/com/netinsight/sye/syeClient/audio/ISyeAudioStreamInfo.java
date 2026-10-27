package com.netinsight.sye.syeClient.audio;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\bf\u0018\u00002\u00020\u0001R\u0016\u0010\u0005\u001a\u00020\u00028&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R\u0016\u0010\t\u001a\u00020\u00068&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\r\u001a\u00020\n8&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0011\u001a\u00020\u000e8&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0013\u001a\u00020\u00028&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0004R\u0016\u0010\u0015\u001a\u00020\u00068&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\b¨\u0006\u0016"}, d2 = {"Lcom/netinsight/sye/syeClient/audio/ISyeAudioStreamInfo;", "", "", "getSampleRate", "()I", "sampleRate", "", "getLanguageDescription", "()Ljava/lang/String;", "languageDescription", "Lcom/netinsight/sye/syeClient/audio/SyeAudioCodec;", "getCodec", "()Lcom/netinsight/sye/syeClient/audio/SyeAudioCodec;", "codec", "Lcom/netinsight/sye/syeClient/audio/SyeAudioCodecProfile;", "getCodecProfile", "()Lcom/netinsight/sye/syeClient/audio/SyeAudioCodecProfile;", "codecProfile", "getNumChannels", "numChannels", "getLanguage", "language", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public interface ISyeAudioStreamInfo {
    SyeAudioCodec getCodec();

    SyeAudioCodecProfile getCodecProfile();

    String getLanguage();

    String getLanguageDescription();

    int getNumChannels();

    int getSampleRate();
}
