package com.amplifyframework.auth.options;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public abstract class AuthUpdateUserAttributesOptions {

    public static abstract class Builder<T extends Builder<T>> {
        @NonNull
        public abstract AuthUpdateUserAttributesOptions build();

        public abstract T getThis();
    }

    public static DefaultAuthUpdateUserAttributesOptions defaults() {
        return new DefaultAuthUpdateUserAttributesOptions();
    }

    public static final class DefaultAuthUpdateUserAttributesOptions extends AuthUpdateUserAttributesOptions {
        private DefaultAuthUpdateUserAttributesOptions() {
        }

        public int hashCode() {
            return DefaultAuthUpdateUserAttributesOptions.class.hashCode();
        }

        public boolean equals(@Nullable Object obj) {
            return obj instanceof DefaultAuthUpdateUserAttributesOptions;
        }

        @NonNull
        public String toString() {
            return DefaultAuthUpdateUserAttributesOptions.class.getSimpleName();
        }
    }
}
