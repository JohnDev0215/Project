package com.netinsight.sye.syeClient.channels;

import com.amazon.identity.auth.device.utils.AccountConstants;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\b\bf\u0018\u00002\u00020\u0001R\u0016\u0010\u0005\u001a\u00020\u00028&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R\u0016\u0010\u0007\u001a\u00020\u00028&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004R\u0016\u0010\t\u001a\u00020\u00028&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0004¨\u0006\n"}, d2 = {"Lcom/netinsight/sye/syeClient/channels/ISyeChannel;", "", "", "getEpgId", "()Ljava/lang/String;", "epgId", "getType", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "getChannelId", "channelId", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public interface ISyeChannel {
    String getChannelId();

    String getEpgId();

    String getType();
}
