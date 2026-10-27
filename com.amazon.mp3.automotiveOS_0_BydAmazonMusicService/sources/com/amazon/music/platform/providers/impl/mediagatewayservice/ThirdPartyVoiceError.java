package com.amazon.music.platform.providers.impl.mediagatewayservice;

import kotlin.Metadata;

/* JADX INFO: compiled from: ThirdPartyVoiceError.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/platform/providers/impl/mediagatewayservice/ThirdPartyVoiceError;", "", "errorCode", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getErrorCode", "()Ljava/lang/String;", "networkError", "socketClosedError", "httpError", "jsonEncodingError", "jsonDataError", "gatewayServiceError", "gatewayEmptyResponse", "authTokenFailure", "genericError", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public enum ThirdPartyVoiceError {
    networkError("NETWORK_ERROR"),
    socketClosedError("SOCKET_CLOSED_ERROR"),
    httpError("HTTP_ERROR"),
    jsonEncodingError("JSON_ENCODING_ERROR"),
    jsonDataError("JSON_DATA_ERROR"),
    gatewayServiceError("GATEWAY_SERVICE_ERROR"),
    gatewayEmptyResponse("GATEWAY_EMPTY_RESPONSE"),
    authTokenFailure("AUTH_TOKEN_FAILURE"),
    genericError("GENERIC_ERROR");

    private final String errorCode;

    ThirdPartyVoiceError(String str) {
        this.errorCode = str;
    }

    public final String getErrorCode() {
        return this.errorCode;
    }
}
