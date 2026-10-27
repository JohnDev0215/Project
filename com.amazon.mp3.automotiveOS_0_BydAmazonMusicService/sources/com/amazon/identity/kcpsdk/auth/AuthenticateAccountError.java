package com.amazon.identity.kcpsdk.auth;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public enum AuthenticateAccountError {
    AuthenticateAccountErrorMissingValue(PandaError.PandaErrorMissingValue),
    AuthenticateAccountErrorInvalidValue(PandaError.PandaErrorInvalidValue),
    AuthenticateAccountErrorCredentialError(PandaError.PandaErrorCredentialError),
    AuthenticateAccountErrorServerError(PandaError.PandaErrorServerError),
    AuthenticateAccountErrorServiceUnavailable(PandaError.PandaErrorServiceUnavailable),
    AuthenticateAccountErrorUnknown(PandaError.PandaErrorUnknown),
    AuthenticateAccountErrorChallengeException(PandaError.PandaErrorChallengeException),
    AuthenticateAccountErrorForbidden(PandaError.PandaErrorForbidden);

    private final PandaError mBaseError;

    AuthenticateAccountError(PandaError pandaError) {
        this.mBaseError = pandaError;
    }

    public static AuthenticateAccountError fromPandaError(PandaError pandaError) {
        AuthenticateAccountError[] authenticateAccountErrorArrValues = values();
        for (int i = 0; i < 8; i++) {
            AuthenticateAccountError authenticateAccountError = authenticateAccountErrorArrValues[i];
            if (authenticateAccountError.mBaseError == pandaError) {
                return authenticateAccountError;
            }
        }
        return null;
    }

    public String getErrorCode() {
        return this.mBaseError.getErrorCode();
    }

    @Override // java.lang.Enum
    public String toString() {
        return "[" + name() + " " + this.mBaseError.getErrorString() + "]";
    }
}
