package com.netinsight.sye.syeClient.internal;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\b`\u0018\u00002\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H&¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\n\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0004H&¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/netinsight/sye/syeClient/internal/IFetchChannelsRequestWrapper;", "", "", "errorCode", "", "message", "", "onError", "(ILjava/lang/String;)V", "jsonArrayOfChannels", "onAvailableChannels", "(Ljava/lang/String;)V", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public interface IFetchChannelsRequestWrapper {
    void onAvailableChannels(String jsonArrayOfChannels);

    void onError(int errorCode, String message);
}
