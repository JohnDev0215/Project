package com.amplifyframework.auth.options;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public abstract class AuthConfirmResetPasswordOptions {

    public static abstract class Builder<T extends Builder<T>> {
        @NonNull
        public abstract AuthConfirmResetPasswordOptions build();

        public abstract T getThis();
    }

    public static DefaultAuthConfirmResetPasswordOptions defaults() {
        return new DefaultAuthConfirmResetPasswordOptions();
    }

    public static final class DefaultAuthConfirmResetPasswordOptions extends AuthConfirmResetPasswordOptions {
        private DefaultAuthConfirmResetPasswordOptions() {
        }

        public int hashCode() {
            return DefaultAuthConfirmResetPasswordOptions.class.hashCode();
        }

        public boolean equals(@Nullable Object obj) {
            return obj instanceof DefaultAuthConfirmResetPasswordOptions;
        }

        @NonNull
        public String toString() {
            return DefaultAuthConfirmResetPasswordOptions.class.getSimpleName();
        }
    }
}
