package com.amplifyframework.auth.options;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public abstract class AuthUpdateUserAttributeOptions {

    public static abstract class Builder<T extends Builder<T>> {
        @NonNull
        public abstract AuthUpdateUserAttributeOptions build();

        public abstract T getThis();
    }

    public static DefaultAuthUpdateUserAttributeOptions defaults() {
        return new DefaultAuthUpdateUserAttributeOptions();
    }

    public static final class DefaultAuthUpdateUserAttributeOptions extends AuthUpdateUserAttributeOptions {
        private DefaultAuthUpdateUserAttributeOptions() {
        }

        public int hashCode() {
            return DefaultAuthUpdateUserAttributeOptions.class.hashCode();
        }

        public boolean equals(@Nullable Object obj) {
            return obj instanceof DefaultAuthUpdateUserAttributeOptions;
        }

        @NonNull
        public String toString() {
            return DefaultAuthUpdateUserAttributeOptions.class.getSimpleName();
        }
    }
}
