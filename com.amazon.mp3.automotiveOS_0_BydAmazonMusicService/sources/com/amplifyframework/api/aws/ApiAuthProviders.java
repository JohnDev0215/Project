package com.amplifyframework.api.aws;

import androidx.annotation.NonNull;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amplifyframework.api.aws.sigv4.ApiKeyAuthProvider;
import com.amplifyframework.api.aws.sigv4.CognitoUserPoolsAuthProvider;
import com.amplifyframework.api.aws.sigv4.FunctionAuthProvider;
import com.amplifyframework.api.aws.sigv4.OidcAuthProvider;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class ApiAuthProviders {
    private final ApiKeyAuthProvider apiKeyAuthProvider;
    private final AWSCredentialsProvider awsCredentialsProvider;
    private final CognitoUserPoolsAuthProvider cognitoUserPoolsAuthProvider;
    private final FunctionAuthProvider functionAuthProvider;
    private final OidcAuthProvider oidcAuthProvider;

    private ApiAuthProviders(Builder builder) {
        this.apiKeyAuthProvider = builder.getApiKeyAuthProvider();
        this.awsCredentialsProvider = builder.getAWSCredentialsProvider();
        this.oidcAuthProvider = builder.getOidcAuthProvider();
        this.cognitoUserPoolsAuthProvider = builder.getCognitoUserPoolsAuthProvider();
        this.functionAuthProvider = builder.getFunctionAuthProvider();
    }

    public ApiKeyAuthProvider getApiKeyAuthProvider() {
        return this.apiKeyAuthProvider;
    }

    public AWSCredentialsProvider getAWSCredentialsProvider() {
        return this.awsCredentialsProvider;
    }

    public OidcAuthProvider getOidcAuthProvider() {
        return this.oidcAuthProvider;
    }

    public CognitoUserPoolsAuthProvider getCognitoUserPoolsAuthProvider() {
        return this.cognitoUserPoolsAuthProvider;
    }

    public FunctionAuthProvider getFunctionAuthProvider() {
        return this.functionAuthProvider;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static ApiAuthProviders noProviderOverrides() {
        return builder().build();
    }

    public static final class Builder {
        private ApiKeyAuthProvider apiKeyAuthProvider;
        private AWSCredentialsProvider awsCredentialsProvider;
        private CognitoUserPoolsAuthProvider cognitoUserPoolsAuthProvider;
        private FunctionAuthProvider functionAuthProvider;
        private OidcAuthProvider oidcAuthProvider;

        public Builder apiKeyAuthProvider(@NonNull ApiKeyAuthProvider apiKeyAuthProvider) {
            Objects.requireNonNull(apiKeyAuthProvider);
            this.apiKeyAuthProvider = apiKeyAuthProvider;
            return this;
        }

        public Builder awsCredentialsProvider(@NonNull AWSCredentialsProvider aWSCredentialsProvider) {
            Objects.requireNonNull(aWSCredentialsProvider);
            this.awsCredentialsProvider = aWSCredentialsProvider;
            return this;
        }

        public Builder cognitoUserPoolsAuthProvider(@NonNull CognitoUserPoolsAuthProvider cognitoUserPoolsAuthProvider) {
            Objects.requireNonNull(cognitoUserPoolsAuthProvider);
            this.cognitoUserPoolsAuthProvider = cognitoUserPoolsAuthProvider;
            return this;
        }

        public Builder oidcAuthProvider(@NonNull OidcAuthProvider oidcAuthProvider) {
            Objects.requireNonNull(oidcAuthProvider);
            this.oidcAuthProvider = oidcAuthProvider;
            return this;
        }

        public Builder functionAuthProvider(@NonNull FunctionAuthProvider functionAuthProvider) {
            Objects.requireNonNull(functionAuthProvider);
            this.functionAuthProvider = functionAuthProvider;
            return this;
        }

        public ApiAuthProviders build() {
            return new ApiAuthProviders(this);
        }

        ApiKeyAuthProvider getApiKeyAuthProvider() {
            return this.apiKeyAuthProvider;
        }

        AWSCredentialsProvider getAWSCredentialsProvider() {
            return this.awsCredentialsProvider;
        }

        CognitoUserPoolsAuthProvider getCognitoUserPoolsAuthProvider() {
            return this.cognitoUserPoolsAuthProvider;
        }

        OidcAuthProvider getOidcAuthProvider() {
            return this.oidcAuthProvider;
        }

        FunctionAuthProvider getFunctionAuthProvider() {
            return this.functionAuthProvider;
        }
    }
}
