package com.amplifyframework.api.aws.auth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazonaws.auth.AWS4Signer;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amplifyframework.api.ApiException;
import com.amplifyframework.api.aws.ApiAuthProviders;
import com.amplifyframework.api.aws.AppSyncGraphQLRequest;
import com.amplifyframework.api.aws.AuthorizationType;
import com.amplifyframework.api.aws.EndpointType;
import com.amplifyframework.api.aws.auth.TokenRequestDecorator;
import com.amplifyframework.api.aws.sigv4.ApiGatewayIamSigner;
import com.amplifyframework.api.aws.sigv4.ApiKeyAuthProvider;
import com.amplifyframework.api.aws.sigv4.AppSyncV4Signer;
import com.amplifyframework.api.aws.sigv4.DefaultCognitoUserPoolsAuthProvider;
import com.amplifyframework.api.graphql.GraphQLRequest;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.logging.Logger;
import java.util.Objects;
import okhttp3.Request;

/* JADX INFO: loaded from: classes5.dex */
public final class ApiRequestDecoratorFactory {
    private static final String API_GATEWAY_SERVICE_NAME = "apigateway";
    private static final String APP_SYNC_SERVICE_NAME = "appsync";
    private static final String AUTH_DEPENDENCY_PLUGIN_KEY = "awsCognitoAuthPlugin";
    private static final Logger LOG = Amplify.Logging.forNamespace("amplify:aws-api");
    private static final RequestDecorator NO_OP_REQUEST_DECORATOR = new RequestDecorator() { // from class: com.amplifyframework.api.aws.auth.ApiRequestDecoratorFactory.1
        @Override // com.amplifyframework.api.aws.auth.RequestDecorator
        public Request decorate(Request request) {
            return request;
        }
    };
    private final ApiAuthProviders apiAuthProviders;
    private final String apiKey;
    private final AuthorizationType defaultAuthorizationType;
    private final EndpointType endpointType;
    private final String region;

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$forAuthType$0(String str) {
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$forAuthType$1(String str) {
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$forAuthType$2(String str) {
        return str;
    }

    public ApiRequestDecoratorFactory(@NonNull ApiAuthProviders apiAuthProviders, @NonNull AuthorizationType authorizationType, @NonNull String str, @NonNull EndpointType endpointType, @Nullable String str2) {
        Objects.requireNonNull(apiAuthProviders);
        this.apiAuthProviders = apiAuthProviders;
        Objects.requireNonNull(authorizationType);
        this.defaultAuthorizationType = authorizationType;
        Objects.requireNonNull(str);
        this.region = str;
        Objects.requireNonNull(endpointType);
        this.endpointType = endpointType;
        this.apiKey = str2;
    }

    public RequestDecorator fromGraphQLRequest(GraphQLRequest<?> graphQLRequest) throws ApiException {
        AuthorizationType authorizationType = this.defaultAuthorizationType;
        if (graphQLRequest instanceof AppSyncGraphQLRequest) {
            AppSyncGraphQLRequest appSyncGraphQLRequest = (AppSyncGraphQLRequest) graphQLRequest;
            if (appSyncGraphQLRequest.getAuthorizationType() != null) {
                authorizationType = appSyncGraphQLRequest.getAuthorizationType();
            }
        }
        return forAuthType(authorizationType);
    }

    /* JADX INFO: renamed from: com.amplifyframework.api.aws.auth.ApiRequestDecoratorFactory$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amplifyframework$api$aws$AuthorizationType;

        static {
            int[] iArr = new int[AuthorizationType.values().length];
            $SwitchMap$com$amplifyframework$api$aws$AuthorizationType = iArr;
            try {
                iArr[AuthorizationType.AMAZON_COGNITO_USER_POOLS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amplifyframework$api$aws$AuthorizationType[AuthorizationType.OPENID_CONNECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amplifyframework$api$aws$AuthorizationType[AuthorizationType.AWS_LAMBDA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amplifyframework$api$aws$AuthorizationType[AuthorizationType.API_KEY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amplifyframework$api$aws$AuthorizationType[AuthorizationType.AWS_IAM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amplifyframework$api$aws$AuthorizationType[AuthorizationType.NONE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public RequestDecorator forAuthType(@NonNull AuthorizationType authorizationType) throws ApiException.ApiAuthException {
        AWSCredentialsProvider defaultCredentialsProvider;
        AWS4Signer apiGatewayIamSigner;
        String str;
        int i = AnonymousClass2.$SwitchMap$com$amplifyframework$api$aws$AuthorizationType[authorizationType.ordinal()];
        if (i == 1) {
            try {
                final String latestAuthToken = (this.apiAuthProviders.getCognitoUserPoolsAuthProvider() != null ? this.apiAuthProviders.getCognitoUserPoolsAuthProvider() : new DefaultCognitoUserPoolsAuthProvider()).getLatestAuthToken();
                return new TokenRequestDecorator(new TokenRequestDecorator.TokenSupplier() { // from class: com.amplifyframework.api.aws.auth.ApiRequestDecoratorFactory$$ExternalSyntheticLambda0
                    @Override // com.amplifyframework.api.aws.auth.TokenRequestDecorator.TokenSupplier
                    public final String get() {
                        return ApiRequestDecoratorFactory.lambda$forAuthType$0(latestAuthToken);
                    }
                });
            } catch (ApiException e) {
                throw new ApiException.ApiAuthException("Failed to retrieve auth token from Cognito provider.", e, "Check the application logs for details.");
            }
        }
        if (i == 2) {
            if (this.apiAuthProviders.getOidcAuthProvider() == null) {
                throw new ApiException.ApiAuthException("Attempting to use OPENID_CONNECT authorization without an OIDC provider.", "Configure an OidcAuthProvider when initializing the API plugin.");
            }
            try {
                final String latestAuthToken2 = this.apiAuthProviders.getOidcAuthProvider().getLatestAuthToken();
                return new TokenRequestDecorator(new TokenRequestDecorator.TokenSupplier() { // from class: com.amplifyframework.api.aws.auth.ApiRequestDecoratorFactory$$ExternalSyntheticLambda1
                    @Override // com.amplifyframework.api.aws.auth.TokenRequestDecorator.TokenSupplier
                    public final String get() {
                        return ApiRequestDecoratorFactory.lambda$forAuthType$1(latestAuthToken2);
                    }
                });
            } catch (ApiException e2) {
                throw new ApiException.ApiAuthException("Failed to retrieve auth token from OIDC provider.", e2, "Check the application logs for details.");
            }
        }
        if (i == 3) {
            if (this.apiAuthProviders.getFunctionAuthProvider() == null) {
                throw new ApiException.ApiAuthException("Attempting to use AWS_LAMBDA authorization without a provider implemented.", "Configure a FunctionAuthProvider when initializing the API plugin.");
            }
            try {
                final String latestAuthToken3 = this.apiAuthProviders.getFunctionAuthProvider().getLatestAuthToken();
                return new TokenRequestDecorator(new TokenRequestDecorator.TokenSupplier() { // from class: com.amplifyframework.api.aws.auth.ApiRequestDecoratorFactory$$ExternalSyntheticLambda2
                    @Override // com.amplifyframework.api.aws.auth.TokenRequestDecorator.TokenSupplier
                    public final String get() {
                        return ApiRequestDecoratorFactory.lambda$forAuthType$2(latestAuthToken3);
                    }
                });
            } catch (ApiException e3) {
                throw new ApiException.ApiAuthException("Failed to retrieve auth token from function auth provider.", e3, "Check the application logs for details.");
            }
        }
        if (i == 4) {
            if (this.apiAuthProviders.getApiKeyAuthProvider() != null) {
                return new ApiKeyRequestDecorator(this.apiAuthProviders.getApiKeyAuthProvider());
            }
            if (this.apiKey != null) {
                return new ApiKeyRequestDecorator(new ApiKeyAuthProvider() { // from class: com.amplifyframework.api.aws.auth.ApiRequestDecoratorFactory$$ExternalSyntheticLambda3
                    @Override // com.amplifyframework.api.aws.sigv4.ApiKeyAuthProvider
                    public final String getAPIKey() {
                        return this.f$0.lambda$forAuthType$3();
                    }
                });
            }
            throw new ApiException.ApiAuthException("Attempting to use API_KEY authorization without an API key provider or an API key in the config file", "Verify that an API key is in the config file or an ApiKeyAuthProvider is setup during the API plugin initialization.");
        }
        if (i == 5) {
            if (this.apiAuthProviders.getAWSCredentialsProvider() != null) {
                defaultCredentialsProvider = this.apiAuthProviders.getAWSCredentialsProvider();
            } else {
                defaultCredentialsProvider = getDefaultCredentialsProvider();
            }
            if (this.endpointType == EndpointType.GRAPHQL) {
                apiGatewayIamSigner = new AppSyncV4Signer(this.region);
                str = APP_SYNC_SERVICE_NAME;
            } else {
                apiGatewayIamSigner = new ApiGatewayIamSigner(this.region);
                str = API_GATEWAY_SERVICE_NAME;
            }
            return new IamRequestDecorator(apiGatewayIamSigner, defaultCredentialsProvider, str);
        }
        return NO_OP_REQUEST_DECORATOR;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ String lambda$forAuthType$3() {
        return this.apiKey;
    }

    private AWSCredentialsProvider getDefaultCredentialsProvider() throws ApiException.ApiAuthException {
        try {
            return (AWSMobileClient) Amplify.Auth.getPlugin(AUTH_DEPENDENCY_PLUGIN_KEY).getEscapeHatch();
        } catch (IllegalStateException unused) {
            throw new ApiException.ApiAuthException("Attempting to use AWS_IAM authorization without an AWS credentials provider.", "Configure an AWSCredentialsProvider when initializing the API plugin or registeran instance of AWSCognitoAuthPlugin to Amplify.");
        }
    }
}
