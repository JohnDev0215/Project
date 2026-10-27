package com.netinsight.sye.syeClient.channels;

import com.netinsight.sye.syeClient.SyeSystem;
import com.netinsight.sye.syeClient.generated.enums.SyeFetchChannelsError;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H&¢\u0006\u0004\b\u0007\u0010\bJ\"\u0010\r\u001a\u00020\u00062\u0011\u0010\f\u001a\r\u0012\t\u0012\u00070\n¢\u0006\u0002\b\u000b0\tH&¢\u0006\u0004\b\r\u0010\u000eR\u0016\u0010\u0012\u001a\u00020\u000f8&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0013"}, d2 = {"Lcom/netinsight/sye/syeClient/channels/IFetchChannelsRequest;", "", "Lcom/netinsight/sye/syeClient/generated/enums/SyeFetchChannelsError;", "error", "", "message", "", "onError", "(Lcom/netinsight/sye/syeClient/generated/enums/SyeFetchChannelsError;Ljava/lang/String;)V", "", "Lcom/netinsight/sye/syeClient/channels/ISyeChannel;", "Lkotlin/jvm/JvmSuppressWildcards;", "channels", "onAvailableChannels", "(Ljava/util/List;)V", "Lcom/netinsight/sye/syeClient/SyeSystem;", "getSyeSystem", "()Lcom/netinsight/sye/syeClient/SyeSystem;", "syeSystem", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public interface IFetchChannelsRequest {
    SyeSystem getSyeSystem();

    void onAvailableChannels(List<ISyeChannel> channels);

    void onError(SyeFetchChannelsError error, String message);
}
