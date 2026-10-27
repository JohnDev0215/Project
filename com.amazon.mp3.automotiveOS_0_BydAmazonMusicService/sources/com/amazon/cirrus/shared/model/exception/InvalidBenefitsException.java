package com.amazon.cirrus.shared.model.exception;

import com.amazon.cirrus.shared.model.exception.CirrusBaseException;

/* JADX INFO: loaded from: classes2.dex */
public class InvalidBenefitsException extends CirrusBaseException {
    private static final long serialVersionUID = -1;

    public static class Builder extends CirrusBaseException.Builder {
    }

    public InvalidBenefitsException() {
    }

    public InvalidBenefitsException(String str) {
        super(str);
    }

    public InvalidBenefitsException(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    public InvalidBenefitsException(Throwable th) {
        initCause(th);
    }

    public static Builder builder() {
        return new Builder();
    }
}
