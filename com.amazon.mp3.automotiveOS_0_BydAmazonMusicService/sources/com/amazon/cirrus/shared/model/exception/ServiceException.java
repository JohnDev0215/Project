package com.amazon.cirrus.shared.model.exception;

import com.amazon.cirrus.shared.model.exception.CirrusBaseException;

/* JADX INFO: loaded from: classes2.dex */
public class ServiceException extends CirrusBaseException {
    private static final long serialVersionUID = -1;

    public static class Builder extends CirrusBaseException.Builder {
    }

    public ServiceException() {
    }

    public ServiceException(String str) {
        super(str);
    }

    public ServiceException(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    public ServiceException(Throwable th) {
        initCause(th);
    }

    public static Builder builder() {
        return new Builder();
    }
}
