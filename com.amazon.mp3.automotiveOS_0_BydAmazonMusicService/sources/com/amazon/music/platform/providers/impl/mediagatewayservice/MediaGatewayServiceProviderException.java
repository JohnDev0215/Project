package com.amazon.music.platform.providers.impl.mediagatewayservice;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MediaGatewayServiceProviderException.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\b\u0010\tR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lcom/amazon/music/platform/providers/impl/mediagatewayservice/MediaGatewayServiceProviderException;", "", "", "errorCode", "Ljava/lang/String;", "getErrorCode", "()Ljava/lang/String;", "message", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final class MediaGatewayServiceProviderException extends Throwable {
    private final String errorCode;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaGatewayServiceProviderException(String errorCode, String str) {
        super(str);
        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
        this.errorCode = errorCode;
    }
}
