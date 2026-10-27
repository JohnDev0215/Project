package com.amazon.cirrus.shared.model.exception;

import com.amazon.cirrus.shared.model.exception.CirrusBaseException;

/* JADX INFO: loaded from: classes2.dex */
public class InvalidTermsOfUseException extends CirrusBaseException {
    private static final long serialVersionUID = -1;

    public static class Builder extends CirrusBaseException.Builder {
    }

    public InvalidTermsOfUseException() {
    }

    public InvalidTermsOfUseException(String str) {
        super(str);
    }

    public InvalidTermsOfUseException(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    public InvalidTermsOfUseException(Throwable th) {
        initCause(th);
    }

    public static Builder builder() {
        return new Builder();
    }
}
