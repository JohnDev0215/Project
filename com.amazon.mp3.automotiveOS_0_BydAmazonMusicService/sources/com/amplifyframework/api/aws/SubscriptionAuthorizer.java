package com.amplifyframework.api.aws;

import android.net.Uri;
import com.amazonaws.DefaultRequest;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.ApiException;
import com.amplifyframework.api.aws.sigv4.ApiKeyAuthProvider;
import com.amplifyframework.api.aws.sigv4.AppSyncV4Signer;
import com.amplifyframework.api.aws.sigv4.CognitoUserPoolsAuthProvider;
import com.amplifyframework.api.aws.sigv4.DefaultCognitoUserPoolsAuthProvider;
import com.amplifyframework.api.aws.sigv4.FunctionAuthProvider;
import com.amplifyframework.api.aws.sigv4.OidcAuthProvider;
import com.amplifyframework.api.graphql.GraphQLRequest;
import com.amplifyframework.core.Amplify;
import com.google.firebase.perf.FirebasePerformance;
import java.io.ByteArrayInputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes5.dex */
final class SubscriptionAuthorizer {
    private static final String AUTH_DEPENDENCY_PLUGIN_KEY = "awsCognitoAuthPlugin";
    private final ApiAuthProviders authProviders;
    private final ApiConfiguration configuration;

    SubscriptionAuthorizer(ApiConfiguration apiConfiguration) {
        this(apiConfiguration, ApiAuthProviders.noProviderOverrides());
    }

    SubscriptionAuthorizer(ApiConfiguration apiConfiguration, ApiAuthProviders apiAuthProviders) {
        this.configuration = apiConfiguration;
        this.authProviders = apiAuthProviders;
    }

    JSONObject createHeadersForSubscription(GraphQLRequest<?> graphQLRequest, AuthorizationType authorizationType) throws ApiException {
        return createHeaders(graphQLRequest, authorizationType, false);
    }

    JSONObject createHeadersForConnection(AuthorizationType authorizationType) throws ApiException {
        return createHeaders(null, authorizationType, true);
    }

    /* JADX INFO: renamed from: com.amplifyframework.api.aws.SubscriptionAuthorizer$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amplifyframework$api$aws$AuthorizationType;

        static {
            int[] iArr = new int[AuthorizationType.values().length];
            $SwitchMap$com$amplifyframework$api$aws$AuthorizationType = iArr;
            try {
                iArr[AuthorizationType.API_KEY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amplifyframework$api$aws$AuthorizationType[AuthorizationType.AWS_IAM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amplifyframework$api$aws$AuthorizationType[AuthorizationType.AMAZON_COGNITO_USER_POOLS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amplifyframework$api$aws$AuthorizationType[AuthorizationType.OPENID_CONNECT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amplifyframework$api$aws$AuthorizationType[AuthorizationType.AWS_LAMBDA.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amplifyframework$api$aws$AuthorizationType[AuthorizationType.NONE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private JSONObject createHeaders(GraphQLRequest<?> graphQLRequest, AuthorizationType authorizationType, boolean z) throws ApiException {
        int i = AnonymousClass1.$SwitchMap$com$amplifyframework$api$aws$AuthorizationType[authorizationType.ordinal()];
        if (i == 1) {
            ApiKeyAuthProvider apiKeyAuthProvider = this.authProviders.getApiKeyAuthProvider();
            if (apiKeyAuthProvider == null) {
                final ApiConfiguration apiConfiguration = this.configuration;
                apiConfiguration.getClass();
                apiKeyAuthProvider = new ApiKeyAuthProvider() { // from class: com.amplifyframework.api.aws.SubscriptionAuthorizer$$ExternalSyntheticLambda0
                    @Override // com.amplifyframework.api.aws.sigv4.ApiKeyAuthProvider
                    public final String getAPIKey() {
                        return apiConfiguration.getApiKey();
                    }
                };
            }
            return forApiKey(apiKeyAuthProvider);
        }
        if (i == 2) {
            AWSCredentialsProvider aWSCredentialsProvider = this.authProviders.getAWSCredentialsProvider();
            if (aWSCredentialsProvider == null) {
                aWSCredentialsProvider = getAWSMobileClient();
            }
            return forIam(aWSCredentialsProvider, graphQLRequest, z);
        }
        if (i == 3) {
            CognitoUserPoolsAuthProvider cognitoUserPoolsAuthProvider = this.authProviders.getCognitoUserPoolsAuthProvider();
            if (cognitoUserPoolsAuthProvider == null) {
                cognitoUserPoolsAuthProvider = new DefaultCognitoUserPoolsAuthProvider();
            }
            return forCognitoUserPools(cognitoUserPoolsAuthProvider);
        }
        if (i == 4) {
            OidcAuthProvider oidcAuthProvider = this.authProviders.getOidcAuthProvider();
            if (oidcAuthProvider == null) {
                oidcAuthProvider = new OidcAuthProvider() { // from class: com.amplifyframework.api.aws.SubscriptionAuthorizer$$ExternalSyntheticLambda1
                    @Override // com.amplifyframework.api.aws.sigv4.AuthProvider
                    public final String getLatestAuthToken() {
                        return SubscriptionAuthorizer.lambda$createHeaders$0();
                    }
                };
            }
            return forOidc(oidcAuthProvider);
        }
        if (i == 5) {
            FunctionAuthProvider functionAuthProvider = this.authProviders.getFunctionAuthProvider();
            if (functionAuthProvider == null) {
                functionAuthProvider = new FunctionAuthProvider() { // from class: com.amplifyframework.api.aws.SubscriptionAuthorizer$$ExternalSyntheticLambda2
                    @Override // com.amplifyframework.api.aws.sigv4.AuthProvider
                    public final String getLatestAuthToken() {
                        return SubscriptionAuthorizer.lambda$createHeaders$1();
                    }
                };
            }
            return forAwsLambda(functionAuthProvider);
        }
        return new JSONObject();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$createHeaders$0() throws ApiException {
        throw new ApiException.ApiAuthException("OidcAuthProvider interface is not implemented.", "Please implement OidcAuthProvider interface to return appropriate token from the appropriate service.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$createHeaders$1() throws ApiException {
        throw new ApiException.ApiAuthException("FunctionAuthProvider interface is not implemented.", "Please implement FunctionAuthProvider interface to return appropriate token from the appropriate service.");
    }

    private JSONObject forApiKey(ApiKeyAuthProvider apiKeyAuthProvider) throws ApiException {
        try {
            return new JSONObject().put("host", getHost()).put("x-amz-date", Iso8601Timestamp.now()).put("x-api-key", apiKeyAuthProvider.getAPIKey());
        } catch (JSONException e) {
            throw new ApiException("Error constructing the authorization json for Api key.", e, AmplifyException.REPORT_BUG_TO_AWS_SUGGESTION);
        }
    }

    private JSONObject forCognitoUserPools(CognitoUserPoolsAuthProvider cognitoUserPoolsAuthProvider) throws ApiException {
        try {
            return new JSONObject().put("host", getHost()).put(HttpHeader.AUTHORIZATION, cognitoUserPoolsAuthProvider.getLatestAuthToken());
        } catch (JSONException e) {
            throw new ApiException("Error constructing the authorization json for Cognito User Pools.", e, AmplifyException.REPORT_BUG_TO_AWS_SUGGESTION);
        }
    }

    private JSONObject forOidc(OidcAuthProvider oidcAuthProvider) throws ApiException {
        try {
            return new JSONObject().put("host", getHost()).put(HttpHeader.AUTHORIZATION, oidcAuthProvider.getLatestAuthToken());
        } catch (JSONException e) {
            throw new ApiException("Error constructing the authorization json for Open ID Connect.", e, AmplifyException.REPORT_BUG_TO_AWS_SUGGESTION);
        }
    }

    private JSONObject forAwsLambda(FunctionAuthProvider functionAuthProvider) throws ApiException {
        try {
            return new JSONObject().put("host", getHost()).put(HttpHeader.AUTHORIZATION, functionAuthProvider.getLatestAuthToken());
        } catch (JSONException e) {
            throw new ApiException("Error constructing the authorization json for the AWS_LAMBDA auth type.", e, AmplifyException.REPORT_BUG_TO_AWS_SUGGESTION);
        }
    }

    private JSONObject forIam(AWSCredentialsProvider aWSCredentialsProvider, GraphQLRequest<?> graphQLRequest, boolean z) throws ApiException {
        URI requestEndpoint = getRequestEndpoint(z);
        String region = this.configuration.getRegion();
        String content = graphQLRequest != null ? graphQLRequest.getContent() : "{}";
        DefaultRequest defaultRequest = new DefaultRequest("appsync");
        defaultRequest.setEndpoint(requestEndpoint);
        defaultRequest.addHeader("accept", "application/json, text/javascript");
        defaultRequest.addHeader("content-encoding", "amz-1.0");
        defaultRequest.addHeader("content-type", "application/json; charset=UTF-8");
        defaultRequest.setHttpMethod(HttpMethodName.valueOf(FirebasePerformance.HttpMethod.POST));
        defaultRequest.setContent(new ByteArrayInputStream(content.getBytes()));
        new AppSyncV4Signer(region, z).sign(defaultRequest, aWSCredentialsProvider.getCredentials());
        return new JSONObject((Map<?, ?>) defaultRequest.getHeaders());
    }

    private String getHost() {
        return Uri.parse(this.configuration.getEndpoint()).getHost();
    }

    private URI getRequestEndpoint(boolean z) throws ApiException {
        try {
            String endpoint = this.configuration.getEndpoint();
            if (z) {
                endpoint = endpoint + "/connect";
            }
            return new URI(endpoint);
        } catch (URISyntaxException e) {
            throw new ApiException("Error constructing canonical URI for IAM request signature", e, "Verify that the API configuration contains valid GraphQL endpoint.");
        }
    }

    private AWSCredentialsProvider getAWSMobileClient() throws ApiException {
        try {
            return (AWSMobileClient) Amplify.Auth.getPlugin(AUTH_DEPENDENCY_PLUGIN_KEY).getEscapeHatch();
        } catch (IllegalStateException e) {
            throw new ApiException("AWSApiPlugin depends on AWSCognitoAuthPlugin, but it is currently missing.", e, "Before configuring Amplify, be sure to add AWSCognitoAuthPlugin same as you added AWSApiPlugin.");
        }
    }
}
