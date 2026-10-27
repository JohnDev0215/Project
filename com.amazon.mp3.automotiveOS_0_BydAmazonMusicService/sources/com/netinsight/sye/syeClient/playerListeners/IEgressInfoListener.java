package com.netinsight.sye.syeClient.playerListeners;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0002H&¢\u0006\u0004\b\b\u0010\u0006J\u001f\u0010\u000b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u0002H&¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/netinsight/sye/syeClient/playerListeners/IEgressInfoListener;", "", "", "allocationTimeMillis", "", "onEgressAllocated", "(I)V", "timeToResponseMillis", "onEgressContact", "egressTTFFMillis", "totalTTFFMillis", "onTimeToFirstFrame", "(II)V", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public interface IEgressInfoListener {
    void onEgressAllocated(int allocationTimeMillis);

    void onEgressContact(int timeToResponseMillis);

    void onTimeToFirstFrame(int egressTTFFMillis, int totalTTFFMillis);
}
