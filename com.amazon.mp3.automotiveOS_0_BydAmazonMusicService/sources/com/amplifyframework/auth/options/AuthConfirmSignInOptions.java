package com.amplifyframework.auth.options;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public abstract class AuthConfirmSignInOptions {

    public static abstract class Builder<T extends Builder<T>> {
        @NonNull
        public abstract AuthConfirmSignInOptions build();

        public abstract T getThis();
    }

    public static DefaultAuthConfirmSignInOptions defaults() {
        return new DefaultAuthConfirmSignInOptions();
    }

    public static final class DefaultAuthConfirmSignInOptions extends AuthConfirmSignInOptions {
        private DefaultAuthConfirmSignInOptions() {
        }

        public int hashCode() {
            return DefaultAuthConfirmSignInOptions.class.hashCode();
        }

        public boolean equals(@Nullable Object obj) {
            return obj instanceof DefaultAuthConfirmSignInOptions;
        }

        @NonNull
        public String toString() {
            return DefaultAuthConfirmSignInOptions.class.getSimpleName();
        }
    }
}
