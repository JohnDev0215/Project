package com.amazon.avod.media.error;

import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum DrmErrorCode implements MediaErrorCode {
    LICENSE_ERROR(0, true),
    LICENSE_EXPIRED(4093, true),
    LICENSE_EXPIRED_PLAYBACK_CLOCK(4093, true),
    LICENSE_EXPIRED_LICENSE_CLOCK(4093, true),
    LICENSE_EXPIRED_RENTAL(4091, false),
    LICENSE_EXPIRED_RENTAL_PLAYBACK_CLOCK(4091, false),
    LICENSE_EXPIRED_RENTAL_LICENSE_CLOCK(4091, false),
    LICENSE_MISMATCH_IN_SERVER_RESPONSE(0, true),
    SYSTEM_CLOCK_ERROR(0, false),
    DRM_SYSTEM_NEEDS_RESET(0, false),
    PLAYREADY_DRMNOTINIT(0, true),
    PLAYREADY_HDCPFAIL(0, true),
    PLAYREADY_LICENSENOTFOUND(0, true),
    PLAYREADY_CIPHERNOTINITIALIZED(0, true),
    OFFLINE_LICENSE_MISSING(0, true),
    ONLINE_LICENSE_MISSING(0, true),
    PLAYREADY_INVALIDARG(0, true),
    DRM_SCHEME_NOT_SUPPORTED(0, false),
    MISSING_OFFLINE_KEY_ID(0, true),
    SOFTWARE_PLAYREADY_INIT_FAILURE(4900, true),
    SOFTWARE_PLAYREADY_SESSION_CREATE_FAILURE(4901, true),
    SOFTWARE_PLAYREADY_NATIVE_LIB_TIMEOUT(4902, true),
    SOFTWARE_PLAYREADY_NATIVE_BUFFER_ALLOCATION_FAILURE(4903, true),
    SOFTWARE_PLAYREADY_NATIVE_BUFFER_OVERFLOW(4904, true),
    UNKNOWN_PLAYREADY_ERROR(4905, true),
    LICENSE_GENERATE_CHALLENGE_FAILURE(4906, true),
    LICENSE_PROCESS_RESPONSE_FAILURE(4907, true),
    LICENSE_PROCESS_RESPONSE_TIMEOUT(4908, true),
    LICENSE_QUERY_RIGHTS_FAILURE(4909, true),
    LICENSE_DELETE_FAILURE(4910, true),
    LICENSE_OPEN_SESSION_FAILURE(4911, true),
    DRM_FIELD_PROVISIONING_FAILURE(4912, true),
    LICENSE_GET_RIGHTS_NULL(4913, true),
    LICENSE_INVALID_HEADER(4914, true),
    LICENSE_INVALID_KEY_COUNT(4915, true),
    LICENSE_INVALID_TYPE(4916, true),
    LICENSE_MALFORMED_RIGHTS_MAP(4917, true),
    LICENSE_PARSING_FAILURE(4918, true),
    NO_LICENSE_AVAILABLE(4919, true),
    LICENSE_PLAYBACK_NOT_ALLOWED(4920, true),
    DRM_FRAMEWORK_BUSY(4921, true),
    DRM_AUTO_RESET(4922, false),
    LICENSE_MISSING_USER_ID(4923, false),
    LICENSE_SERVICE_CALL_FAILURE(4924, true),
    LICENSE_SERVICE_CALL_MALFORMED_RESPONSE(4925, true),
    LICENSE_SERVICE_CALL_DENIED(4926, true),
    LICENSE_SERVICE_CALL_DEGRADED(4927, true),
    LICENSE_SERVICE_CALL_ERROR(4928, true),
    LICENSE_SERVICE_CALL_STREAMING_NOT_OWNED(4929, false),
    LICENSE_OFFER_UNAVAILABLE(4930, true);

    private final boolean mIsRecoverable;
    private final int mNumber;

    DrmErrorCode(int i, boolean z) {
        this.mNumber = i;
        this.mIsRecoverable = z;
    }

    @Override // com.amazon.avod.media.error.MediaErrorCode
    @Nonnull
    public String getName() {
        return name();
    }

    @Override // com.amazon.avod.media.error.MediaErrorCode
    public int getNumber() {
        return this.mNumber;
    }

    @Override // com.amazon.avod.media.error.MediaErrorCode, com.amazon.avod.metrics.pmet.MetricParameter
    @Nonnull
    public String toReportableString() {
        return name();
    }

    public boolean isRecoverable() {
        return this.mIsRecoverable;
    }
}
