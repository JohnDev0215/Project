package com.amazon.music.platform.providers.impl.mediagatewayservice;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AuthTokenFetchingException.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/platform/providers/impl/mediagatewayservice/AuthTokenFetchingException;", "", "message", "", "(Ljava/lang/String;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class AuthTokenFetchingException extends Throwable {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AuthTokenFetchingException(String message) {
        super(message);
        Intrinsics.checkNotNullParameter(message, "message");
    }
}
