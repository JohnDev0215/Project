package com.amplifyframework.auth.result;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.auth.AuthException;

/* JADX INFO: loaded from: classes5.dex */
public final class AuthSessionResult<T> {
    private final AuthException error;
    private final Type type;
    private final T value;

    public enum Type {
        SUCCESS,
        FAILURE
    }

    private AuthSessionResult(T t, AuthException authException, Type type) {
        this.value = t;
        this.error = authException;
        this.type = type;
    }

    public static <T> AuthSessionResult<T> success(T t) {
        return new AuthSessionResult<>(t, null, Type.SUCCESS);
    }

    public static <T> AuthSessionResult<T> failure(AuthException authException) {
        return new AuthSessionResult<>(null, authException, Type.FAILURE);
    }

    @Nullable
    public T getValue() {
        return this.value;
    }

    @Nullable
    public AuthException getError() {
        return this.error;
    }

    @NonNull
    public Type getType() {
        return this.type;
    }

    public int hashCode() {
        return ObjectsCompat.hash(getValue(), getError(), getType());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AuthSessionResult)) {
            return false;
        }
        AuthSessionResult authSessionResult = (AuthSessionResult) obj;
        return ObjectsCompat.equals(getValue(), authSessionResult.getValue()) && ObjectsCompat.equals(getError(), authSessionResult.getError()) && ObjectsCompat.equals(getType(), authSessionResult.getType());
    }

    public String toString() {
        return "AuthSessionResult{value=" + getValue() + ", error=" + getError() + ", type=" + getType() + '}';
    }
}
