package com.netinsight.sye.syeClient.audio;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001R\u001c\u0010\u0007\u001a\u00020\u00028&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u0003\u0010\u0004\"\u0004\b\u0005\u0010\u0006R\u001c\u0010\r\u001a\u00020\b8&@&X¦\u000e¢\u0006\f\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\u0013\u001a\u00020\u000e8&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0014"}, d2 = {"Lcom/netinsight/sye/syeClient/audio/ISyeAudioPreferences;", "", "Lcom/netinsight/sye/syeClient/audio/SyeAudioCodec;", "getCodec", "()Lcom/netinsight/sye/syeClient/audio/SyeAudioCodec;", "setCodec", "(Lcom/netinsight/sye/syeClient/audio/SyeAudioCodec;)V", "codec", "", "getLanguage", "()Ljava/lang/String;", "setLanguage", "(Ljava/lang/String;)V", "language", "", "getChannels", "()I", "setChannels", "(I)V", "channels", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public interface ISyeAudioPreferences {
    int getChannels();

    SyeAudioCodec getCodec();

    String getLanguage();

    void setChannels(int i);

    void setCodec(SyeAudioCodec syeAudioCodec);

    void setLanguage(String str);
}
