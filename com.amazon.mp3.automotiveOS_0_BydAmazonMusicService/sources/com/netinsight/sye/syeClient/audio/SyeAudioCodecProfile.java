package com.netinsight.sye.syeClient.audio;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\b\n\u0002\b\f\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\b\u0010\tR\u0019\u0010\u0007\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000e"}, d2 = {"Lcom/netinsight/sye/syeClient/audio/SyeAudioCodecProfile;", "", "", "b", "I", "getValue", "()I", "value", "<init>", "(Ljava/lang/String;II)V", "Undefined", "AAC_LC", "EC3_Atmos", "EC3", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public enum SyeAudioCodecProfile {
    Undefined(0),
    AAC_LC(1),
    EC3_Atmos(2),
    EC3(3);


    /* JADX INFO: renamed from: b, reason: from kotlin metadata */
    public final int value;

    SyeAudioCodecProfile(int i) {
        this.value = i;
    }

    public final int getValue() {
        return this.value;
    }
}
