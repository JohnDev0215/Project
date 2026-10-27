package com.amplifyframework.core.model;

import androidx.annotation.NonNull;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.util.Empty;
import com.amplifyframework.util.Immutable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class AuthRule {
    private static final String DEFAULT_GROUPS_FIELD = "groups";
    private static final String DEFAULT_GROUP_CLAIM = "cognito:groups";
    private static final String DEFAULT_IDENTITY_CLAIM = "username";
    private static final String DEFAULT_OWNER_FIELD = "owner";
    private final AuthStrategy.Provider authProvider;
    private final AuthStrategy authStrategy;
    private final String groupClaim;
    private final List<String> groups;
    private final String groupsField;
    private final String identityClaim;
    private final List<ModelOperation> operations;
    private final String ownerField;

    /* JADX WARN: Illegal instructions before constructor call */
    public AuthRule(com.amplifyframework.core.model.annotations.AuthRule authRule) {
        AuthStrategy.Provider providerFrom;
        Builder builderAuthStrategy = builder().authStrategy(authRule.allow());
        if (Empty.check(authRule.provider())) {
            providerFrom = authRule.allow().getDefaultAuthProvider();
        } else {
            providerFrom = AuthStrategy.Provider.from(authRule.provider());
        }
        this(builderAuthStrategy.authProvider(providerFrom).ownerField(authRule.ownerField()).identityClaim(authRule.identityClaim()).groupClaim(authRule.groupClaim()).groups(Arrays.asList(authRule.groups())).groupsField(authRule.groupsField()).operations(Arrays.asList(authRule.operations())));
    }

    private AuthRule(@NonNull Builder builder) {
        this.authStrategy = builder.authStrategy;
        this.authProvider = builder.authProvider;
        this.ownerField = builder.ownerField;
        this.identityClaim = builder.identityClaim;
        this.groupClaim = builder.groupClaim;
        this.groups = builder.groups;
        this.groupsField = builder.groupsField;
        this.operations = builder.operations;
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    @NonNull
    public AuthStrategy getAuthStrategy() {
        return this.authStrategy;
    }

    @NonNull
    public AuthStrategy.Provider getAuthProvider() {
        return this.authProvider;
    }

    @NonNull
    public String getOwnerFieldOrDefault() {
        return Empty.check(this.ownerField) ? "owner" : this.ownerField;
    }

    @NonNull
    public String getIdentityClaimOrDefault() {
        return (Empty.check(this.identityClaim) || "cognito:username".equals(this.identityClaim)) ? "username" : this.identityClaim;
    }

    @NonNull
    public String getGroupsFieldOrDefault() {
        return Empty.check(this.groupsField) ? DEFAULT_GROUPS_FIELD : this.groupsField;
    }

    @NonNull
    public String getGroupClaimOrDefault() {
        return Empty.check(this.groupClaim) ? DEFAULT_GROUP_CLAIM : this.groupClaim;
    }

    @NonNull
    public List<String> getGroups() {
        return Immutable.of(this.groups);
    }

    @NonNull
    public List<ModelOperation> getOperationsOrDefault() {
        return Immutable.of(Empty.check(this.operations) ? Arrays.asList(ModelOperation.values()) : this.operations);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AuthRule.class != obj.getClass()) {
            return false;
        }
        AuthRule authRule = (AuthRule) obj;
        return this.authStrategy == authRule.authStrategy && ObjectsCompat.equals(this.ownerField, authRule.ownerField) && ObjectsCompat.equals(this.identityClaim, authRule.identityClaim) && ObjectsCompat.equals(this.groupsField, authRule.groupsField) && ObjectsCompat.equals(this.groupClaim, authRule.groupClaim) && ObjectsCompat.equals(this.groups, authRule.groups) && ObjectsCompat.equals(this.operations, authRule.operations);
    }

    public int hashCode() {
        return ObjectsCompat.hash(this.authStrategy, this.ownerField, this.identityClaim, this.groupsField, this.groupClaim, this.groups, this.operations);
    }

    public String toString() {
        return "AuthRule{authStrategy=" + this.authStrategy + ", ownerField='" + this.ownerField + "', identityClaim='" + this.identityClaim + "', groupsField='" + this.groupsField + "', groupClaim='" + this.groupClaim + "', groups=" + this.groups + "', operations=" + this.operations + "'}";
    }

    public static final class Builder {
        private AuthStrategy.Provider authProvider;
        private AuthStrategy authStrategy;
        private String groupClaim;
        private List<String> groups;
        private String groupsField;
        private String identityClaim;
        private List<ModelOperation> operations = new ArrayList();
        private String ownerField;

        @NonNull
        public Builder authStrategy(@NonNull AuthStrategy authStrategy) {
            Objects.requireNonNull(authStrategy);
            this.authStrategy = authStrategy;
            return this;
        }

        @NonNull
        public Builder authProvider(AuthStrategy.Provider provider) {
            Objects.requireNonNull(provider);
            this.authProvider = provider;
            return this;
        }

        @NonNull
        public Builder ownerField(@NonNull String str) {
            Objects.requireNonNull(str);
            this.ownerField = str;
            return this;
        }

        @NonNull
        public Builder identityClaim(@NonNull String str) {
            Objects.requireNonNull(str);
            this.identityClaim = str;
            return this;
        }

        @NonNull
        public Builder groupClaim(@NonNull String str) {
            Objects.requireNonNull(str);
            this.groupClaim = str;
            return this;
        }

        @NonNull
        public Builder groups(@NonNull List<String> list) {
            Objects.requireNonNull(list);
            this.groups = list;
            return this;
        }

        @NonNull
        public Builder groupsField(@NonNull String str) {
            Objects.requireNonNull(str);
            this.groupsField = str;
            return this;
        }

        @NonNull
        public Builder operations(@NonNull List<ModelOperation> list) {
            Objects.requireNonNull(list);
            this.operations = list;
            return this;
        }

        @NonNull
        public AuthRule build() {
            Objects.requireNonNull(this.authStrategy);
            return new AuthRule(this);
        }
    }
}
