package com.amazon.music.metrics.mts.event.types;

/* JADX INFO: loaded from: classes4.dex */
public enum DynamicMessagingErrorCode {
    AUTHENTICATION_ERROR("authenticationError"),
    SERVICE_ERROR("serviceError"),
    SERVER_TIMEOUT("serverTimeout"),
    VIEW_MODEL_MAPPING_ERROR("viewModelMappingError"),
    MESSAGE_NOT_IN_CACHE("messageNotInCache"),
    INVALID_ASSOCIATE_TAG("invalidAssociateTag"),
    HANDLE_ACTION_API_ERROR("handleActionApiError"),
    CONTEXTUAL_FALLBACK_UNAVAILABLE("contextualFallbackUnavailable"),
    CONTEXTUAL_FALLBACK("contextualFallback"),
    DEEPLINK_FAILURE("deeplinkFailure"),
    OTHER("other");

    private final String metricValue;

    DynamicMessagingErrorCode(String str) {
        this.metricValue = str;
    }

    public String getMetricValue() {
        return this.metricValue;
    }
}
