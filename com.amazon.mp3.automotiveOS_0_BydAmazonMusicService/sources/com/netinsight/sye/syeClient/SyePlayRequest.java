package com.netinsight.sye.syeClient;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\t¢\u0006\u0004\b\u000f\u0010\u0010B\u0011\b\u0016\u0012\u0006\u0010\b\u001a\u00020\u0002¢\u0006\u0004\b\u000f\u0010\u0011R$\u0010\b\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00028\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007R$\u0010\f\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\t8\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0012"}, d2 = {"Lcom/netinsight/sye/syeClient/SyePlayRequest;", "", "", "<set-?>", "a", "Ljava/lang/String;", "getChannelId", "()Ljava/lang/String;", "channelId", "", "b", "Z", "isShouldClearDisplay", "()Z", "shouldClearDisplay", "<init>", "(Ljava/lang/String;Z)V", "(Ljava/lang/String;)V", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public final class SyePlayRequest {

    /* JADX INFO: renamed from: a, reason: collision with root package name and from kotlin metadata */
    public String channelId;

    /* JADX INFO: renamed from: b, reason: from kotlin metadata */
    public boolean isShouldClearDisplay;

    public SyePlayRequest(String str) {
        this.isShouldClearDisplay = true;
        this.channelId = str;
    }

    public SyePlayRequest(String str, boolean z) {
        this.channelId = str;
        this.isShouldClearDisplay = z;
    }

    public final String getChannelId() {
        return this.channelId;
    }

    /* JADX INFO: renamed from: isShouldClearDisplay, reason: from getter */
    public final boolean getIsShouldClearDisplay() {
        return this.isShouldClearDisplay;
    }
}
