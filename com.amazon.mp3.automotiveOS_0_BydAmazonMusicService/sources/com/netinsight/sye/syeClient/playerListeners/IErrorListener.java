package com.netinsight.sye.syeClient.playerListeners;

import com.netinsight.sye.syeClient.generated.enums.SyePlayerError;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H&¢\u0006\u0004\b\u0007\u0010\bJ'\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH&¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/netinsight/sye/syeClient/playerListeners/IErrorListener;", "", "Lcom/netinsight/sye/syeClient/generated/enums/SyePlayerError;", "error", "", "message", "", "onError", "(Lcom/netinsight/sye/syeClient/generated/enums/SyePlayerError;Ljava/lang/String;)V", "", "retryAfterMillis", "onErrorRetry", "(Lcom/netinsight/sye/syeClient/generated/enums/SyePlayerError;Ljava/lang/String;I)V", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public interface IErrorListener {
    void onError(SyePlayerError error, String message);

    void onErrorRetry(SyePlayerError error, String message, int retryAfterMillis);
}
