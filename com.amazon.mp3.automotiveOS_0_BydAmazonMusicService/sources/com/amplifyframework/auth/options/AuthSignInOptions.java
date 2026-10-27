package com.amplifyframework.auth.options;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public abstract class AuthSignInOptions {

    public static abstract class Builder<T extends Builder<T>> {
        @NonNull
        public abstract AuthSignInOptions build();

        public abstract T getThis();
    }

    public static DefaultAuthSignInOptions defaults() {
        return new DefaultAuthSignInOptions();
    }

    public static final class DefaultAuthSignInOptions extends AuthSignInOptions {
        private DefaultAuthSignInOptions() {
        }

        public int hashCode() {
            return DefaultAuthSignInOptions.class.hashCode();
        }

        public boolean equals(@Nullable Object obj) {
            return obj instanceof DefaultAuthSignInOptions;
        }

        @NonNull
        public String toString() {
            return DefaultAuthSignInOptions.class.getSimpleName();
        }
    }
}
