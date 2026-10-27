package com.amazon.cirrus.shared.model.exception;

import com.amazon.cirrus.shared.model.exception.CirrusBaseException;

/* JADX INFO: loaded from: classes2.dex */
public class RequestThrottledException extends CirrusBaseException {
    private static final long serialVersionUID = -1;

    public static class Builder extends CirrusBaseException.Builder {
    }

    public RequestThrottledException() {
    }

    public RequestThrottledException(String str) {
        super(str);
    }

    public RequestThrottledException(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    public RequestThrottledException(Throwable th) {
        initCause(th);
    }

    public static Builder builder() {
        return new Builder();
    }
}
