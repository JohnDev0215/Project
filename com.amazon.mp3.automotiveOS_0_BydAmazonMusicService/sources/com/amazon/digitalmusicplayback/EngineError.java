package com.amazon.digitalmusicplayback;

/* JADX INFO: loaded from: classes2.dex */
public final class EngineError {
    final ErrorCode code;
    final String descriptor;
    final ErrorScope scope;

    public EngineError(ErrorCode errorCode, ErrorScope errorScope, String str) {
        this.code = errorCode;
        this.scope = errorScope;
        this.descriptor = str;
    }

    public ErrorCode getCode() {
        return this.code;
    }

    public ErrorScope getScope() {
        return this.scope;
    }

    public String getDescriptor() {
        return this.descriptor;
    }

    public String toString() {
        return "EngineError{code=" + this.code + ",scope=" + this.scope + ",descriptor=" + this.descriptor + "}";
    }
}
