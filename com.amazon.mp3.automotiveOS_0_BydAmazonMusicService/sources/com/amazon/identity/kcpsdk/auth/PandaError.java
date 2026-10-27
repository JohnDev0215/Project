package com.amazon.identity.kcpsdk.auth;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public enum PandaError {
    PandaErrorMissingValue("MissingValue", "One or more required values are missing"),
    PandaErrorInvalidValue("InvalidValue", "One or more required values are invalid"),
    PandaErrorCredentialError("CredentialError", "Invalid credentials. The provided email or phone number and password did not match our records."),
    PandaErrorServerError("ServerError", "The server has encountered a runtime error"),
    PandaErrorServiceUnavailable("ServiceUnavailable", "The service is temporarily overloaded or unavailable, try again later"),
    PandaErrorForbidden("Forbidden", "This application is not allowed to make this request."),
    PandaErrorUnknown("UnknownError", "Unknown error"),
    PandaErrorChallengeException("ChallengeException", "Additional credentials are required");

    private final String mErrorCode;
    private final String mErrorString;

    PandaError(String str, String str2) {
        this.mErrorCode = str;
        this.mErrorString = str2;
    }

    public static PandaError getPandaError(String str) {
        PandaError[] pandaErrorArrValues = values();
        for (int i = 0; i < 8; i++) {
            PandaError pandaError = pandaErrorArrValues[i];
            if (pandaError.getErrorCode().equals(str)) {
                return pandaError;
            }
        }
        return null;
    }

    public String getErrorCode() {
        return this.mErrorCode;
    }

    public String getErrorString() {
        return this.mErrorString;
    }

    @Override // java.lang.Enum
    public String toString() {
        return "[" + name() + " " + this.mErrorString + "]";
    }
}
