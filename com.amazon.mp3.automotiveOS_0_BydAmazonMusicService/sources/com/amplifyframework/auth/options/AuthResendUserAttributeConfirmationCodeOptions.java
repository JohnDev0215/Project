package com.amplifyframework.auth.options;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public abstract class AuthResendUserAttributeConfirmationCodeOptions {

    public static abstract class Builder<T extends Builder<T>> {
        @NonNull
        public abstract AuthResendUserAttributeConfirmationCodeOptions build();

        public abstract T getThis();
    }

    public static DefaultAuthResendUserAttributeConfirmationCodeOptions defaults() {
        return new DefaultAuthResendUserAttributeConfirmationCodeOptions();
    }

    public static final class DefaultAuthResendUserAttributeConfirmationCodeOptions extends AuthResendUserAttributeConfirmationCodeOptions {
        private DefaultAuthResendUserAttributeConfirmationCodeOptions() {
        }

        public int hashCode() {
            return DefaultAuthResendUserAttributeConfirmationCodeOptions.class.hashCode();
        }

        public boolean equals(@Nullable Object obj) {
            return obj instanceof DefaultAuthResendUserAttributeConfirmationCodeOptions;
        }

        @NonNull
        public String toString() {
            return DefaultAuthResendUserAttributeConfirmationCodeOptions.class.getSimpleName();
        }
    }
}
