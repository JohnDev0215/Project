package com.amazon.cirrus.shared.model.exception;

import com.amazon.cirrus.shared.model.exception.CirrusBaseException;

/* JADX INFO: loaded from: classes2.dex */
public class InvalidParameterException extends CirrusBaseException {
    private static final long serialVersionUID = -1;

    public static class Builder extends CirrusBaseException.Builder {
    }

    public InvalidParameterException() {
    }

    public InvalidParameterException(String str) {
        super(str);
    }

    public InvalidParameterException(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    public InvalidParameterException(Throwable th) {
        initCause(th);
    }

    public static Builder builder() {
        return new Builder();
    }
}
