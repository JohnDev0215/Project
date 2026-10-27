package com.amplifyframework.auth.options;

import androidx.annotation.NonNull;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.util.Immutable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public class AuthWebUISignInOptions {
    private final List<String> scopes;
    private final Map<String, String> signInQueryParameters;
    private final Map<String, String> signOutQueryParameters;
    private final Map<String, String> tokenQueryParameters;

    public static final class CoreBuilder extends Builder<CoreBuilder> {
        @Override // com.amplifyframework.auth.options.AuthWebUISignInOptions.Builder
        public CoreBuilder getThis() {
            return this;
        }
    }

    protected AuthWebUISignInOptions(List<String> list, Map<String, String> map, Map<String, String> map2, Map<String, String> map3) {
        this.scopes = list;
        this.signInQueryParameters = map;
        this.signOutQueryParameters = map2;
        this.tokenQueryParameters = map3;
    }

    @NonNull
    public List<String> getScopes() {
        return this.scopes;
    }

    @NonNull
    public Map<String, String> getSignInQueryParameters() {
        return this.signInQueryParameters;
    }

    @NonNull
    public Map<String, String> getSignOutQueryParameters() {
        return this.signOutQueryParameters;
    }

    @NonNull
    public Map<String, String> getTokenQueryParameters() {
        return this.tokenQueryParameters;
    }

    @NonNull
    public static Builder<?> builder() {
        return new CoreBuilder();
    }

    public int hashCode() {
        return ObjectsCompat.hash(getScopes(), getSignInQueryParameters(), getSignOutQueryParameters(), getTokenQueryParameters());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AuthWebUISignInOptions authWebUISignInOptions = (AuthWebUISignInOptions) obj;
        return ObjectsCompat.equals(getScopes(), authWebUISignInOptions.getScopes()) && ObjectsCompat.equals(getSignInQueryParameters(), authWebUISignInOptions.getSignInQueryParameters()) && ObjectsCompat.equals(getSignOutQueryParameters(), authWebUISignInOptions.getSignOutQueryParameters()) && ObjectsCompat.equals(getTokenQueryParameters(), authWebUISignInOptions.getTokenQueryParameters());
    }

    public String toString() {
        return "AuthWebUISignInOptions{scopes=" + getScopes() + ", signInQueryParameters=" + getSignInQueryParameters() + ", signOutQueryParameters=" + getSignOutQueryParameters() + ", tokenQueryParameters=" + getTokenQueryParameters() + '}';
    }

    public static abstract class Builder<T extends Builder<T>> {
        private List<String> scopes = new ArrayList();
        private Map<String, String> signInQueryParameters = new HashMap();
        private Map<String, String> signOutQueryParameters = new HashMap();
        private Map<String, String> tokenQueryParameters = new HashMap();

        public abstract T getThis();

        public List<String> getScopes() {
            return this.scopes;
        }

        public Map<String, String> getSignInQueryParameters() {
            return this.signInQueryParameters;
        }

        public Map<String, String> getSignOutQueryParameters() {
            return this.signOutQueryParameters;
        }

        public Map<String, String> getTokenQueryParameters() {
            return this.tokenQueryParameters;
        }

        @NonNull
        public T scopes(@NonNull List<String> list) {
            Objects.requireNonNull(list);
            this.scopes.clear();
            this.scopes.addAll(list);
            return (T) getThis();
        }

        @NonNull
        public T signInQueryParameters(@NonNull Map<String, String> map) {
            Objects.requireNonNull(map);
            this.signInQueryParameters.clear();
            this.signInQueryParameters.putAll(map);
            return (T) getThis();
        }

        @NonNull
        public T signOutQueryParameters(@NonNull Map<String, String> map) {
            Objects.requireNonNull(map);
            this.signOutQueryParameters.clear();
            this.signOutQueryParameters.putAll(map);
            return (T) getThis();
        }

        @NonNull
        public T tokenQueryParameters(@NonNull Map<String, String> map) {
            Objects.requireNonNull(map);
            this.tokenQueryParameters.clear();
            this.tokenQueryParameters.putAll(map);
            return (T) getThis();
        }

        @NonNull
        public AuthWebUISignInOptions build() {
            return new AuthWebUISignInOptions(Immutable.of(this.scopes), Immutable.of(this.signInQueryParameters), Immutable.of(this.signOutQueryParameters), Immutable.of(this.tokenQueryParameters));
        }
    }
}
