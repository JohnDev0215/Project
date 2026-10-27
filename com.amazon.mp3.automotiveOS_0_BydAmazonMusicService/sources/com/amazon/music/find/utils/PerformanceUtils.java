package com.amazon.music.find.utils;

import kotlin.Metadata;

/* JADX INFO: compiled from: PerformanceUtils.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/find/utils/PerformanceUtils;", "", "()V", "MARK_API_REQUEST_COMPLETED", "", "MARK_API_REQUEST_STARTED", "MARK_CONVERSION_COMPLETED", "MARK_CONVERSION_STARTED", "MARK_OPERATION_COMPLETED", "MARK_OPERATION_STARTED", "MARK_RENDERING_COMPLETED", "MARK_RENDERING_STARTED", "MARK_RESPONSE_COMPLETED", "MARK_RESPONSE_STARTED", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PerformanceUtils {
    public static final PerformanceUtils INSTANCE = new PerformanceUtils();
    public static final String MARK_API_REQUEST_COMPLETED = "api_response_completed";
    public static final String MARK_API_REQUEST_STARTED = "api_request_sent";
    public static final String MARK_CONVERSION_COMPLETED = "conversion_completed";
    public static final String MARK_CONVERSION_STARTED = "conversion_started";
    public static final String MARK_OPERATION_COMPLETED = "operation_completed";
    public static final String MARK_OPERATION_STARTED = "operation_started";
    public static final String MARK_RENDERING_COMPLETED = "rendering_completed";
    public static final String MARK_RENDERING_STARTED = "rendering_started";
    public static final String MARK_RESPONSE_COMPLETED = "response_completed";
    public static final String MARK_RESPONSE_STARTED = "response_started";

    private PerformanceUtils() {
    }
}
