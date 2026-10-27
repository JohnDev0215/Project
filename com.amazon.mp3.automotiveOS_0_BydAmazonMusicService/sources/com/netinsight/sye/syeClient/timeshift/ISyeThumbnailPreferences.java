package com.netinsight.sye.syeClient.timeshift;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u000b\bf\u0018\u00002\u00020\u0001R\u001c\u0010\u0007\u001a\u00020\u00028&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u0003\u0010\u0004\"\u0004\b\u0005\u0010\u0006R\u001c\u0010\n\u001a\u00020\u00028&@&X¦\u000e¢\u0006\f\u001a\u0004\b\b\u0010\u0004\"\u0004\b\t\u0010\u0006R\u0016\u0010\f\u001a\u00020\u00028&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0004¨\u0006\r"}, d2 = {"Lcom/netinsight/sye/syeClient/timeshift/ISyeThumbnailPreferences;", "", "", "getNumChannelsCached", "()I", "setNumChannelsCached", "(I)V", "numChannelsCached", "getMaxMemoryMB", "setMaxMemoryMB", "maxMemoryMB", "getWidth", "width", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public interface ISyeThumbnailPreferences {
    int getMaxMemoryMB();

    int getNumChannelsCached();

    int getWidth();

    void setMaxMemoryMB(int i);

    void setNumChannelsCached(int i);
}
