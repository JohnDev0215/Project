package com.netinsight.sye.syeClient.channels;

import com.netinsight.sye.syeClient.SyeSystem;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b&\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\b\u0010\tR\u001c\u0010\u0007\u001a\u00020\u00028\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lcom/netinsight/sye/syeClient/channels/FetchChannelsRequest;", "Lcom/netinsight/sye/syeClient/channels/IFetchChannelsRequest;", "Lcom/netinsight/sye/syeClient/SyeSystem;", "a", "Lcom/netinsight/sye/syeClient/SyeSystem;", "getSyeSystem", "()Lcom/netinsight/sye/syeClient/SyeSystem;", "syeSystem", "<init>", "(Lcom/netinsight/sye/syeClient/SyeSystem;)V", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public abstract class FetchChannelsRequest implements IFetchChannelsRequest {

    /* JADX INFO: renamed from: a, reason: collision with root package name and from kotlin metadata */
    public final SyeSystem syeSystem;

    public FetchChannelsRequest(SyeSystem syeSystem) {
        this.syeSystem = syeSystem;
    }

    @Override // com.netinsight.sye.syeClient.channels.IFetchChannelsRequest
    public SyeSystem getSyeSystem() {
        return this.syeSystem;
    }
}
