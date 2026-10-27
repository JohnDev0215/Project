package com.amplifyframework.auth.options;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public abstract class AuthResendSignUpCodeOptions {

    public static abstract class Builder<T extends Builder<T>> {
        @NonNull
        public abstract AuthResendSignUpCodeOptions build();

        public abstract T getThis();
    }

    public static DefaultAuthResendSignUpCodeOptions defaults() {
        return new DefaultAuthResendSignUpCodeOptions();
    }

    public static final class DefaultAuthResendSignUpCodeOptions extends AuthResendSignUpCodeOptions {
        private DefaultAuthResendSignUpCodeOptions() {
        }

        public int hashCode() {
            return DefaultAuthResendSignUpCodeOptions.class.hashCode();
        }

        public boolean equals(@Nullable Object obj) {
            return obj instanceof DefaultAuthResendSignUpCodeOptions;
        }

        @NonNull
        public String toString() {
            return DefaultAuthResendSignUpCodeOptions.class.getSimpleName();
        }
    }
}
