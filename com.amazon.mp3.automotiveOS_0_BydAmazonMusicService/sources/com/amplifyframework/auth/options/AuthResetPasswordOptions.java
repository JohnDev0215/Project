package com.amplifyframework.auth.options;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public abstract class AuthResetPasswordOptions {

    public static abstract class Builder<T extends Builder<T>> {
        @NonNull
        public abstract AuthResetPasswordOptions build();

        public abstract T getThis();
    }

    public static DefaultAuthResetPasswordOptions defaults() {
        return new DefaultAuthResetPasswordOptions();
    }

    public static final class DefaultAuthResetPasswordOptions extends AuthResetPasswordOptions {
        private DefaultAuthResetPasswordOptions() {
        }

        public int hashCode() {
            return DefaultAuthResetPasswordOptions.class.hashCode();
        }

        public boolean equals(@Nullable Object obj) {
            return obj instanceof DefaultAuthResetPasswordOptions;
        }

        @NonNull
        public String toString() {
            return DefaultAuthResetPasswordOptions.class.getSimpleName();
        }
    }
}
