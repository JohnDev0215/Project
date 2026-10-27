package com.amazon.cirrus.shared.model.exception;

import com.amazon.cirrus.shared.model.exception.CirrusBaseException;

/* JADX INFO: loaded from: classes2.dex */
public class AccountLockedException extends CirrusBaseException {
    private static final long serialVersionUID = -1;

    public static class Builder extends CirrusBaseException.Builder {
    }

    public AccountLockedException() {
    }

    public AccountLockedException(String str) {
        super(str);
    }

    public AccountLockedException(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    public AccountLockedException(Throwable th) {
        initCause(th);
    }

    public static Builder builder() {
        return new Builder();
    }
}
