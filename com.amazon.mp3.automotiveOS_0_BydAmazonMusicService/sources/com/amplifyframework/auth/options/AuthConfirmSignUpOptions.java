package com.amplifyframework.auth.options;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public abstract class AuthConfirmSignUpOptions {

    public static abstract class Builder<T extends Builder<T>> {
        @NonNull
        public abstract AuthConfirmSignUpOptions build();

        public abstract T getThis();
    }

    public static DefaultAuthConfirmSignUpOptions defaults() {
        return new DefaultAuthConfirmSignUpOptions();
    }

    public static final class DefaultAuthConfirmSignUpOptions extends AuthConfirmSignUpOptions {
        private DefaultAuthConfirmSignUpOptions() {
        }

        public int hashCode() {
            return DefaultAuthConfirmSignUpOptions.class.hashCode();
        }

        public boolean equals(@Nullable Object obj) {
            return obj instanceof DefaultAuthConfirmSignUpOptions;
        }

        @NonNull
        public String toString() {
            return DefaultAuthConfirmSignUpOptions.class.getSimpleName();
        }
    }
}
