package com.netinsight.sye.syeClient.playerListeners;

import com.netinsight.sye.syeClient.generated.enums.SyePlayerError;
import com.netinsight.sye.syeClient.status.SyeFrontendInfo;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J'\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H&¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0004H&¢\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\r\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0006H&¢\u0006\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/netinsight/sye/syeClient/playerListeners/IStatusListener;", "", "Lcom/netinsight/sye/syeClient/generated/enums/SyePlayerError;", "error", "Lcom/netinsight/sye/syeClient/status/SyeFrontendInfo;", "frontendInfo", "", "message", "", "onFrontendError", "(Lcom/netinsight/sye/syeClient/generated/enums/SyePlayerError;Lcom/netinsight/sye/syeClient/status/SyeFrontendInfo;Ljava/lang/String;)V", "onFrontendSuccess", "(Lcom/netinsight/sye/syeClient/status/SyeFrontendInfo;)V", "onStreamingError", "(Lcom/netinsight/sye/syeClient/generated/enums/SyePlayerError;Ljava/lang/String;)V", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public interface IStatusListener {
    void onFrontendError(SyePlayerError error, SyeFrontendInfo frontendInfo, String message);

    void onFrontendSuccess(SyeFrontendInfo frontendInfo);

    void onStreamingError(SyePlayerError error, String message);
}
