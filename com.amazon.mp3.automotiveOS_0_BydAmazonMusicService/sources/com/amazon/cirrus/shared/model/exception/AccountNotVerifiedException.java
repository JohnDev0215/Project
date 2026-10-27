package com.amazon.cirrus.shared.model.exception;

import com.amazon.cirrus.shared.model.exception.CirrusBaseException;

/* JADX INFO: loaded from: classes2.dex */
public class AccountNotVerifiedException extends CirrusBaseException {
    private static final long serialVersionUID = -1;

    public static class Builder extends CirrusBaseException.Builder {
    }

    public AccountNotVerifiedException() {
    }

    public AccountNotVerifiedException(String str) {
        super(str);
    }

    public AccountNotVerifiedException(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    public AccountNotVerifiedException(Throwable th) {
        initCause(th);
    }

    public static Builder builder() {
        return new Builder();
    }
}
