package com.amazon.cirrus.shared.model.exception;

import com.amazon.cirrus.shared.model.exception.CirrusBaseException;

/* JADX INFO: loaded from: classes2.dex */
public class ResourceExistsException extends CirrusBaseException {
    private static final long serialVersionUID = -1;

    public static class Builder extends CirrusBaseException.Builder {
    }

    public ResourceExistsException() {
    }

    public ResourceExistsException(String str) {
        super(str);
    }

    public ResourceExistsException(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    public ResourceExistsException(Throwable th) {
        initCause(th);
    }

    public static Builder builder() {
        return new Builder();
    }
}
