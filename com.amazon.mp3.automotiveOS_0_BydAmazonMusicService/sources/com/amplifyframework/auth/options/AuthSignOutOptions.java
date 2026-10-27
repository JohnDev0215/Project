package com.amplifyframework.auth.options;

import androidx.annotation.NonNull;
import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes5.dex */
public class AuthSignOutOptions {
    private final boolean globalSignOut;

    public static final class CoreBuilder extends Builder<CoreBuilder> {
        @Override // com.amplifyframework.auth.options.AuthSignOutOptions.Builder
        public CoreBuilder getThis() {
            return this;
        }
    }

    protected AuthSignOutOptions(boolean z) {
        this.globalSignOut = z;
    }

    public boolean isGlobalSignOut() {
        return this.globalSignOut;
    }

    @NonNull
    public static Builder<?> builder() {
        return new CoreBuilder();
    }

    public int hashCode() {
        return ObjectsCompat.hash(Boolean.valueOf(isGlobalSignOut()));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(Boolean.valueOf(isGlobalSignOut()), Boolean.valueOf(((AuthSignOutOptions) obj).isGlobalSignOut()));
    }

    public String toString() {
        return "AuthSignOutOptions{globalSignOut=" + isGlobalSignOut() + '}';
    }

    public static abstract class Builder<T extends Builder<T>> {
        private boolean globalSignOut = false;

        public abstract T getThis();

        public boolean isGlobalSignOut() {
            return this.globalSignOut;
        }

        public T globalSignOut(boolean z) {
            this.globalSignOut = z;
            return (T) getThis();
        }

        @NonNull
        public AuthSignOutOptions build() {
            return new AuthSignOutOptions(this.globalSignOut);
        }
    }
}
