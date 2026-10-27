package com.netinsight.sye.syeClient.view;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001:\u0001\u0006R\u0016\u0010\u0005\u001a\u00020\u00028&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0007"}, d2 = {"Lcom/netinsight/sye/syeClient/view/ISyeDisplaySettings;", "", "Lcom/netinsight/sye/syeClient/view/ISyeDisplaySettings$VideoGravity;", "getGravity", "()Lcom/netinsight/sye/syeClient/view/ISyeDisplaySettings$VideoGravity;", "gravity", "VideoGravity", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public interface ISyeDisplaySettings {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/netinsight/sye/syeClient/view/ISyeDisplaySettings$VideoGravity;", "", "<init>", "(Ljava/lang/String;I)V", "ASPECT_FIT", "ASPECT_FILL", "syeClient_release"}, k = 1, mv = {1, 4, 0})
    public enum VideoGravity {
        ASPECT_FIT,
        ASPECT_FILL
    }

    VideoGravity getGravity();
}
