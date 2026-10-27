package com.amazon.identity.kcpsdk.common;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public enum FIRSErrorType {
    FIRSErrorTypeCustomerNotFound("customer_not_found"),
    FIRSErrorTypeDeviceAlreadyRegistered("device_already_registered"),
    FIRSErrorTypeDuplicateAccountName("duplicate_account_name"),
    FIRSErrorTypeInternalError("message"),
    FIRSErrorTypeUnrecognized("unrecognized"),
    FIRSErrorTypeInvalidAccountFound("invalid_account_found");

    private final String mErrorCode;

    FIRSErrorType(String str) {
        this.mErrorCode = str;
    }

    public String getErrorCode() {
        return this.mErrorCode;
    }
}
