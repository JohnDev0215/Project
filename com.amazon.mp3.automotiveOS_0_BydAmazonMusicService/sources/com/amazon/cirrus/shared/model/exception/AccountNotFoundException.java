package com.amazon.cirrus.shared.model.exception;

import com.amazon.cirrus.shared.model.exception.ResourceNotFoundException;

/* JADX INFO: loaded from: classes2.dex */
public class AccountNotFoundException extends ResourceNotFoundException {
    private static final long serialVersionUID = -1;

    public static class Builder extends ResourceNotFoundException.Builder {
    }

    public AccountNotFoundException() {
    }

    public AccountNotFoundException(String str) {
        super(str);
    }

    public AccountNotFoundException(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    public AccountNotFoundException(Throwable th) {
        initCause(th);
    }

    public static Builder builder() {
        return new Builder();
    }
}
