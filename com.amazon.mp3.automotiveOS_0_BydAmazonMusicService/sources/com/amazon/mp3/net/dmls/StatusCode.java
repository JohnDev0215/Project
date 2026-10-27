package com.amazon.mp3.net.dmls;

/* JADX INFO: loaded from: classes3.dex */
public enum StatusCode {
    GRANTED,
    DENIED,
    DEGRADED,
    REDOWNLOAD,
    SUCCESS,
    RETRY,
    CUSTOMER_NOT_VALID,
    CUSTOMER_NOT_ELIGIBLE,
    CONTENT_NOT_FOUND,
    CONTENT_NOT_VALID,
    CONTENT_NOT_ELIGIBLE,
    EMPTY_CONTENT_LIST,
    CLIENT_NOT_VALID,
    CLIENT_NOT_ELIGIBLE,
    DEVICE_NOT_VALID,
    DEVICE_NOT_ELIGIBLE,
    DEVICE_NOT_AUTHORIZED,
    APPMETADATA_NOT_VALID,
    PROTOCOL_NOT_SUPPORTED,
    STREAMING_STATUS_NOT_VALID,
    MULTIPLE_CONTENT_REQUEST_NOT_SUPPORTED,
    MAXIMUM_DEVICE_LIMIT_REACHED,
    MAXIMUM_DOWNLOAD_LIMIT_REACHED,
    INVALID_REQUEST,
    BAD_REQUEST,
    MAX_CONCURRENCY_REACHED,
    AUTHENTICATION_ERROR,
    INTERNAL_ERROR,
    UNKNOWN;

    /* JADX INFO: renamed from: com.amazon.mp3.net.dmls.StatusCode$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$net$dmls$StatusCode;

        static {
            int[] iArr = new int[StatusCode.values().length];
            $SwitchMap$com$amazon$mp3$net$dmls$StatusCode = iArr;
            try {
                iArr[StatusCode.GRANTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$net$dmls$StatusCode[StatusCode.SUCCESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static boolean isError(StatusCode code) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$net$dmls$StatusCode[code.ordinal()];
        return (i == 1 || i == 2) ? false : true;
    }

    public static StatusCode getFromString(String status) {
        for (StatusCode statusCode : values()) {
            if (statusCode.toString().equals(status)) {
                return statusCode;
            }
        }
        return UNKNOWN;
    }
}
