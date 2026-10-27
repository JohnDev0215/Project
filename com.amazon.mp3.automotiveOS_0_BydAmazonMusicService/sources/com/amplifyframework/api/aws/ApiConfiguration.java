package com.amplifyframework.api.aws;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
final class ApiConfiguration {
    private final String apiKey;
    private final AuthorizationType authorizationType;
    private final String endpoint;
    private final EndpointType endpointType;
    private final String region;

    @SuppressLint({"SyntheticAccessor"})
    private ApiConfiguration(Builder builder) {
        this.endpointType = builder.endpointType;
        this.endpoint = builder.endpoint;
        this.region = builder.region;
        this.authorizationType = builder.authorizationType;
        this.apiKey = builder.apiKey;
    }

    EndpointType getEndpointType() {
        return this.endpointType;
    }

    String getEndpoint() {
        return this.endpoint;
    }

    String getRegion() {
        return this.region;
    }

    AuthorizationType getAuthorizationType() {
        return this.authorizationType;
    }

    String getApiKey() {
        return this.apiKey;
    }

    static Builder builder() {
        return new Builder();
    }

    static final class Builder {
        private String apiKey;
        private AuthorizationType authorizationType;
        private String endpoint;
        private EndpointType endpointType;
        private String region;

        Builder() {
        }

        Builder endpointType(@NonNull EndpointType endpointType) {
            Objects.requireNonNull(endpointType);
            this.endpointType = endpointType;
            return this;
        }

        Builder endpoint(@NonNull String str) {
            Objects.requireNonNull(str);
            this.endpoint = str;
            return this;
        }

        Builder region(@NonNull String str) {
            Objects.requireNonNull(str);
            this.region = str;
            return this;
        }

        Builder authorizationType(@NonNull String str) {
            return authorizationType(AuthorizationType.from(str));
        }

        Builder authorizationType(@NonNull AuthorizationType authorizationType) {
            Objects.requireNonNull(authorizationType);
            this.authorizationType = authorizationType;
            return this;
        }

        Builder apiKey(@Nullable String str) {
            this.apiKey = str;
            return this;
        }

        @SuppressLint({"SyntheticAccessor"})
        ApiConfiguration build() {
            Objects.requireNonNull(this.endpoint);
            Objects.requireNonNull(this.region);
            Objects.requireNonNull(this.authorizationType);
            return new ApiConfiguration(this);
        }
    }
}
