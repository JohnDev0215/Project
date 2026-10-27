package com.amplifyframework.api.aws;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.api.ApiException;
import com.amplifyframework.api.ApiPlugin;
import com.amplifyframework.api.aws.auth.ApiRequestDecoratorFactory;
import com.amplifyframework.api.aws.auth.AuthRuleRequestDecorator;
import com.amplifyframework.api.aws.operation.AWSRestOperation;
import com.amplifyframework.api.events.ApiEndpointStatusChangeEvent;
import com.amplifyframework.api.graphql.GraphQLOperation;
import com.amplifyframework.api.graphql.GraphQLRequest;
import com.amplifyframework.api.graphql.GraphQLResponse;
import com.amplifyframework.api.rest.HttpMethod;
import com.amplifyframework.api.rest.RestOperation;
import com.amplifyframework.api.rest.RestOperationRequest;
import com.amplifyframework.api.rest.RestOptions;
import com.amplifyframework.api.rest.RestResponse;
import com.amplifyframework.core.Action;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.hub.HubChannel;
import com.amplifyframework.util.Immutable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.EventListener;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Response;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes5.dex */
public final class AWSApiPlugin extends ApiPlugin<Map<String, OkHttpClient>> {
    private final Map<String, OkHttpConfigurator> apiConfigurators;
    private final Map<String, ClientDetails> apiDetails;
    private final ApiAuthProviders authProvider;
    private final ExecutorService executorService;
    private final Set<String> gqlApis;
    private final GraphQLResponse.Factory gqlResponseFactory;
    private final AuthRuleRequestDecorator requestDecorator;
    private final Set<String> restApis;

    @Override // com.amplifyframework.core.plugin.Plugin
    @NonNull
    public String getPluginKey() {
        return "awsAPIPlugin";
    }

    @Override // com.amplifyframework.core.plugin.Plugin
    @NonNull
    public String getVersion() {
        return "1.37.11";
    }

    /* synthetic */ AWSApiPlugin(Builder builder, AnonymousClass1 anonymousClass1) {
        this(builder);
    }

    public AWSApiPlugin() {
        this(builder());
    }

    @Deprecated
    public AWSApiPlugin(@NonNull ApiAuthProviders apiAuthProviders) {
        this(builder().apiAuthProviders(apiAuthProviders));
    }

    private AWSApiPlugin(@NonNull Builder builder) {
        this.apiDetails = new HashMap();
        this.gqlResponseFactory = new GsonGraphQLResponseFactory();
        ApiAuthProviders apiAuthProviders = builder.apiAuthProviders;
        this.authProvider = apiAuthProviders;
        this.restApis = new HashSet();
        this.gqlApis = new HashSet();
        this.executorService = Executors.newCachedThreadPool();
        this.requestDecorator = new AuthRuleRequestDecorator(apiAuthProviders);
        this.apiConfigurators = Immutable.of(builder.apiConfigurators);
    }

    public static Builder builder() {
        return new Builder(null);
    }

    @Override // com.amplifyframework.core.plugin.Plugin
    public void configure(JSONObject jSONObject, @NonNull Context context) throws ApiException {
        for (Map.Entry<String, ApiConfiguration> entry : AWSApiPluginConfigurationReader.readFrom(jSONObject).getApis().entrySet()) {
            String key = entry.getKey();
            ApiConfiguration value = entry.getValue();
            EndpointType endpointType = value.getEndpointType();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addNetworkInterceptor(UserAgentInterceptor.using(new AWSApiPlugin$$ExternalSyntheticLambda0()));
            builder.eventListener(new ApiConnectionEventListener());
            OkHttpConfigurator okHttpConfigurator = this.apiConfigurators.get(key);
            if (okHttpConfigurator != null) {
                okHttpConfigurator.applyConfiguration(builder);
            }
            final ApiRequestDecoratorFactory apiRequestDecoratorFactory = new ApiRequestDecoratorFactory(this.authProvider, value.getAuthorizationType(), value.getRegion(), value.getEndpointType(), value.getApiKey());
            ClientDetails clientDetails = null;
            if (EndpointType.REST.equals(endpointType)) {
                if (value.getAuthorizationType() != AuthorizationType.NONE) {
                    final AuthorizationType authorizationType = value.getAuthorizationType();
                    builder.addInterceptor(new Interceptor() { // from class: com.amplifyframework.api.aws.AWSApiPlugin$$ExternalSyntheticLambda1
                        @Override // okhttp3.Interceptor
                        public final Response intercept(Interceptor.Chain chain) {
                            return AWSApiPlugin.lambda$configure$0(apiRequestDecoratorFactory, authorizationType, chain);
                        }
                    });
                }
                ClientDetails clientDetails2 = new ClientDetails(value, builder.build(), null, apiRequestDecoratorFactory);
                this.restApis.add(key);
                clientDetails = clientDetails2;
            } else if (EndpointType.GRAPHQL.equals(endpointType)) {
                clientDetails = new ClientDetails(value, builder.build(), new SubscriptionEndpoint(value, this.gqlResponseFactory, new SubscriptionAuthorizer(value, this.authProvider)), apiRequestDecoratorFactory);
                this.gqlApis.add(key);
            }
            if (clientDetails != null) {
                this.apiDetails.put(key, clientDetails);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Response lambda$configure$0(ApiRequestDecoratorFactory apiRequestDecoratorFactory, AuthorizationType authorizationType, Interceptor.Chain chain) throws IOException {
        try {
            return chain.proceed(apiRequestDecoratorFactory.forAuthType(authorizationType).decorate(chain.request()));
        } catch (ApiException.ApiAuthException e) {
            throw new IOException("Failed to decorate request for authorization.", e);
        } catch (Exception e2) {
            throw new IOException("An error occurred while making the request.", e2);
        }
    }

    @Override // com.amplifyframework.core.plugin.Plugin
    @NonNull
    public Map<String, OkHttpClient> getEscapeHatch() {
        HashMap map = new HashMap();
        for (Map.Entry<String, ClientDetails> entry : this.apiDetails.entrySet()) {
            map.put(entry.getKey(), entry.getValue().getOkHttpClient());
        }
        return Collections.unmodifiableMap(map);
    }

    @Override // com.amplifyframework.api.graphql.GraphQLBehavior
    @Nullable
    public <R> GraphQLOperation<R> query(@NonNull GraphQLRequest<R> graphQLRequest, @NonNull Consumer<GraphQLResponse<R>> consumer, @NonNull Consumer<ApiException> consumer2) {
        try {
            return query(getSelectedApiName(EndpointType.GRAPHQL), graphQLRequest, consumer, consumer2);
        } catch (ApiException e) {
            consumer2.accept(e);
            return null;
        }
    }

    @Override // com.amplifyframework.api.graphql.GraphQLBehavior
    @Nullable
    public <R> GraphQLOperation<R> query(@NonNull String str, @NonNull GraphQLRequest<R> graphQLRequest, @NonNull Consumer<GraphQLResponse<R>> consumer, @NonNull Consumer<ApiException> consumer2) {
        try {
            GraphQLOperation<R> graphQLOperationBuildAppSyncGraphQLOperation = buildAppSyncGraphQLOperation(str, graphQLRequest, consumer, consumer2);
            graphQLOperationBuildAppSyncGraphQLOperation.start();
            return graphQLOperationBuildAppSyncGraphQLOperation;
        } catch (ApiException e) {
            consumer2.accept(e);
            return null;
        }
    }

    @Override // com.amplifyframework.api.graphql.GraphQLBehavior
    @Nullable
    public <R> GraphQLOperation<R> mutate(@NonNull GraphQLRequest<R> graphQLRequest, @NonNull Consumer<GraphQLResponse<R>> consumer, @NonNull Consumer<ApiException> consumer2) {
        try {
            return mutate(getSelectedApiName(EndpointType.GRAPHQL), graphQLRequest, consumer, consumer2);
        } catch (ApiException e) {
            consumer2.accept(e);
            return null;
        }
    }

    @Override // com.amplifyframework.api.graphql.GraphQLBehavior
    @Nullable
    public <R> GraphQLOperation<R> mutate(@NonNull String str, @NonNull GraphQLRequest<R> graphQLRequest, @NonNull Consumer<GraphQLResponse<R>> consumer, @NonNull Consumer<ApiException> consumer2) {
        try {
            GraphQLOperation<R> graphQLOperationBuildAppSyncGraphQLOperation = buildAppSyncGraphQLOperation(str, graphQLRequest, consumer, consumer2);
            graphQLOperationBuildAppSyncGraphQLOperation.start();
            return graphQLOperationBuildAppSyncGraphQLOperation;
        } catch (ApiException e) {
            consumer2.accept(e);
            return null;
        }
    }

    @Override // com.amplifyframework.api.graphql.GraphQLBehavior
    @Nullable
    public <R> GraphQLOperation<R> subscribe(@NonNull GraphQLRequest<R> graphQLRequest, @NonNull Consumer<String> consumer, @NonNull Consumer<GraphQLResponse<R>> consumer2, @NonNull Consumer<ApiException> consumer3, @NonNull Action action) {
        try {
            return subscribe(getSelectedApiName(EndpointType.GRAPHQL), graphQLRequest, consumer, consumer2, consumer3, action);
        } catch (ApiException e) {
            consumer3.accept(e);
            return null;
        }
    }

    @Override // com.amplifyframework.api.graphql.GraphQLBehavior
    @Nullable
    public <R> GraphQLOperation<R> subscribe(@NonNull String str, @NonNull GraphQLRequest<R> graphQLRequest, @NonNull Consumer<String> consumer, @NonNull Consumer<GraphQLResponse<R>> consumer2, @NonNull Consumer<ApiException> consumer3, @NonNull Action action) {
        try {
            GraphQLOperation<R> graphQLOperationBuildSubscriptionOperation = buildSubscriptionOperation(str, graphQLRequest, consumer, consumer2, consumer3, action);
            graphQLOperationBuildSubscriptionOperation.start();
            return graphQLOperationBuildSubscriptionOperation;
        } catch (ApiException e) {
            consumer3.accept(e);
            return null;
        }
    }

    private <R> AuthModeStrategyType getAuthModeStrategyType(GraphQLRequest<R> graphQLRequest) {
        if (graphQLRequest instanceof AppSyncGraphQLRequest) {
            AppSyncGraphQLRequest appSyncGraphQLRequest = (AppSyncGraphQLRequest) graphQLRequest;
            if (appSyncGraphQLRequest.getAuthorizationType() == null && appSyncGraphQLRequest.getAuthModeStrategyType() != null && appSyncGraphQLRequest.getModelSchema().hasModelLevelRules()) {
                return appSyncGraphQLRequest.getAuthModeStrategyType();
            }
        }
        return AuthModeStrategyType.DEFAULT;
    }

    @Override // com.amplifyframework.api.rest.RestBehavior
    @Nullable
    public RestOperation get(@NonNull RestOptions restOptions, @NonNull Consumer<RestResponse> consumer, @NonNull Consumer<ApiException> consumer2) {
        try {
            return get(getSelectedApiName(EndpointType.REST), restOptions, consumer, consumer2);
        } catch (ApiException e) {
            consumer2.accept(e);
            return null;
        }
    }

    @Override // com.amplifyframework.api.rest.RestBehavior
    @Nullable
    public RestOperation get(@NonNull String str, @NonNull RestOptions restOptions, @NonNull Consumer<RestResponse> consumer, @NonNull Consumer<ApiException> consumer2) {
        try {
            return createRestOperation(str, HttpMethod.GET, restOptions, consumer, consumer2);
        } catch (ApiException e) {
            consumer2.accept(e);
            return null;
        }
    }

    @Override // com.amplifyframework.api.rest.RestBehavior
    @Nullable
    public RestOperation put(@NonNull RestOptions restOptions, @NonNull Consumer<RestResponse> consumer, @NonNull Consumer<ApiException> consumer2) {
        try {
            return put(getSelectedApiName(EndpointType.REST), restOptions, consumer, consumer2);
        } catch (ApiException e) {
            consumer2.accept(e);
            return null;
        }
    }

    @Override // com.amplifyframework.api.rest.RestBehavior
    @Nullable
    public RestOperation put(@NonNull String str, @NonNull RestOptions restOptions, @NonNull Consumer<RestResponse> consumer, @NonNull Consumer<ApiException> consumer2) {
        try {
            return createRestOperation(str, HttpMethod.PUT, restOptions, consumer, consumer2);
        } catch (ApiException e) {
            consumer2.accept(e);
            return null;
        }
    }

    @Override // com.amplifyframework.api.rest.RestBehavior
    @Nullable
    public RestOperation post(@NonNull RestOptions restOptions, @NonNull Consumer<RestResponse> consumer, @NonNull Consumer<ApiException> consumer2) {
        try {
            return post(getSelectedApiName(EndpointType.REST), restOptions, consumer, consumer2);
        } catch (ApiException e) {
            consumer2.accept(e);
            return null;
        }
    }

    @Override // com.amplifyframework.api.rest.RestBehavior
    @Nullable
    public RestOperation post(@NonNull String str, @NonNull RestOptions restOptions, @NonNull Consumer<RestResponse> consumer, @NonNull Consumer<ApiException> consumer2) {
        try {
            return createRestOperation(str, HttpMethod.POST, restOptions, consumer, consumer2);
        } catch (ApiException e) {
            consumer2.accept(e);
            return null;
        }
    }

    @Override // com.amplifyframework.api.rest.RestBehavior
    @Nullable
    public RestOperation delete(@NonNull RestOptions restOptions, @NonNull Consumer<RestResponse> consumer, @NonNull Consumer<ApiException> consumer2) {
        try {
            return delete(getSelectedApiName(EndpointType.REST), restOptions, consumer, consumer2);
        } catch (ApiException e) {
            consumer2.accept(e);
            return null;
        }
    }

    @Override // com.amplifyframework.api.rest.RestBehavior
    @Nullable
    public RestOperation delete(@NonNull String str, @NonNull RestOptions restOptions, @NonNull Consumer<RestResponse> consumer, @NonNull Consumer<ApiException> consumer2) {
        try {
            return createRestOperation(str, HttpMethod.DELETE, restOptions, consumer, consumer2);
        } catch (ApiException e) {
            consumer2.accept(e);
            return null;
        }
    }

    @Override // com.amplifyframework.api.rest.RestBehavior
    @Nullable
    public RestOperation head(@NonNull RestOptions restOptions, @NonNull Consumer<RestResponse> consumer, @NonNull Consumer<ApiException> consumer2) {
        try {
            return head(getSelectedApiName(EndpointType.REST), restOptions, consumer, consumer2);
        } catch (ApiException e) {
            consumer2.accept(e);
            return null;
        }
    }

    @Override // com.amplifyframework.api.rest.RestBehavior
    @Nullable
    public RestOperation head(@NonNull String str, @NonNull RestOptions restOptions, @NonNull Consumer<RestResponse> consumer, @NonNull Consumer<ApiException> consumer2) {
        try {
            return createRestOperation(str, HttpMethod.HEAD, restOptions, consumer, consumer2);
        } catch (ApiException e) {
            consumer2.accept(e);
            return null;
        }
    }

    @Override // com.amplifyframework.api.rest.RestBehavior
    @Nullable
    public RestOperation patch(@NonNull RestOptions restOptions, @NonNull Consumer<RestResponse> consumer, @NonNull Consumer<ApiException> consumer2) {
        try {
            return patch(getSelectedApiName(EndpointType.REST), restOptions, consumer, consumer2);
        } catch (ApiException e) {
            consumer2.accept(e);
            return null;
        }
    }

    @Override // com.amplifyframework.api.rest.RestBehavior
    @Nullable
    public RestOperation patch(@NonNull String str, @NonNull RestOptions restOptions, @NonNull Consumer<RestResponse> consumer, @NonNull Consumer<ApiException> consumer2) {
        try {
            return createRestOperation(str, HttpMethod.PATCH, restOptions, consumer, consumer2);
        } catch (ApiException e) {
            consumer2.accept(e);
            return null;
        }
    }

    @VisibleForTesting
    String getSelectedApiName(EndpointType endpointType) throws ApiException {
        int i = AnonymousClass1.$SwitchMap$com$amplifyframework$api$aws$EndpointType[endpointType.ordinal()];
        if (i == 1) {
            return selectApiName(this.restApis);
        }
        if (i == 2) {
            return selectApiName(this.gqlApis);
        }
        throw new ApiException(endpointType.name() + " is not a supported endpoint type.", "Please use REST or GraphQL as endpoint type.");
    }

    private String selectApiName(Set<String> set) throws ApiException {
        if (set.isEmpty()) {
            throw new ApiException("There is no API configured for this plugin with matching endpoint type.", "Please add at least one API in amplifyconfiguration.json.");
        }
        if (set.size() > 1) {
            throw new ApiException("There is more than one API configured for this plugin with matching endpoint type.", "Please specify the name of API to invoke in the API method.");
        }
        return set.iterator().next();
    }

    private <R> GraphQLOperation<R> buildSubscriptionOperation(@NonNull String str, @NonNull GraphQLRequest<R> graphQLRequest, @NonNull Consumer<String> consumer, @NonNull Consumer<GraphQLResponse<R>> consumer2, @NonNull Consumer<ApiException> consumer3, @NonNull Action action) throws ApiException {
        ClientDetails clientDetails = this.apiDetails.get(str);
        if (clientDetails == null) {
            throw new ApiException("No client information for API named " + str, "Check your amplify configuration to make sure there is a correctly configured section for " + str);
        }
        if (AuthModeStrategyType.MULTIAUTH.equals(getAuthModeStrategyType(graphQLRequest))) {
            return MutiAuthSubscriptionOperation.builder().subscriptionEndpoint(clientDetails.getSubscriptionEndpoint()).graphQlRequest((AppSyncGraphQLRequest) graphQLRequest).responseFactory(this.gqlResponseFactory).executorService(this.executorService).onSubscriptionStart(consumer).onNextItem(consumer2).onSubscriptionError(consumer3).onSubscriptionComplete(action).requestDecorator(this.requestDecorator).build();
        }
        AuthorizationType authorizationType = clientDetails.getApiConfiguration().getAuthorizationType();
        if (graphQLRequest instanceof AppSyncGraphQLRequest) {
            AppSyncGraphQLRequest appSyncGraphQLRequest = (AppSyncGraphQLRequest) graphQLRequest;
            if (appSyncGraphQLRequest.getAuthorizationType() != null) {
                authorizationType = appSyncGraphQLRequest.getAuthorizationType();
            }
        }
        return SubscriptionOperation.builder().subscriptionEndpoint(clientDetails.getSubscriptionEndpoint()).graphQlRequest(this.requestDecorator.decorate(graphQLRequest, authorizationType)).responseFactory(this.gqlResponseFactory).executorService(this.executorService).onSubscriptionStart(consumer).onNextItem(consumer2).onSubscriptionError(consumer3).onSubscriptionComplete(action).authorizationType(authorizationType).build();
    }

    private <R> GraphQLOperation<R> buildAppSyncGraphQLOperation(@NonNull String str, @NonNull GraphQLRequest<R> graphQLRequest, @NonNull Consumer<GraphQLResponse<R>> consumer, @NonNull Consumer<ApiException> consumer2) throws ApiException {
        ClientDetails clientDetails = this.apiDetails.get(str);
        if (clientDetails == null) {
            throw new ApiException("No client information for API named " + str, "Check your amplify configuration to make sure there is a correctly configured section for " + str);
        }
        if (AuthModeStrategyType.MULTIAUTH.equals(getAuthModeStrategyType(graphQLRequest))) {
            return MultiAuthAppSyncGraphQLOperation.builder().endpoint(clientDetails.getApiConfiguration().getEndpoint()).client(clientDetails.getOkHttpClient()).request(graphQLRequest).apiRequestDecoratorFactory(clientDetails.getApiRequestDecoratorFactory()).responseFactory(this.gqlResponseFactory).onResponse(consumer).onFailure(consumer2).executorService(this.executorService).build();
        }
        return AppSyncGraphQLOperation.builder().endpoint(clientDetails.getApiConfiguration().getEndpoint()).client(clientDetails.getOkHttpClient()).request(graphQLRequest).apiRequestDecoratorFactory(clientDetails.getApiRequestDecoratorFactory()).responseFactory(this.gqlResponseFactory).executorService(this.executorService).onResponse(consumer).onFailure(consumer2).build();
    }

    private RestOperation createRestOperation(String str, HttpMethod httpMethod, RestOptions restOptions, Consumer<RestResponse> consumer, Consumer<ApiException> consumer2) throws ApiException {
        RestOperationRequest restOperationRequest;
        ClientDetails clientDetails = this.apiDetails.get(str);
        if (clientDetails == null) {
            throw new ApiException("No client information for API named " + str, "Check your amplify configuration to make sure there is a correctly configured section for " + str);
        }
        switch (AnonymousClass1.$SwitchMap$com$amplifyframework$api$rest$HttpMethod[httpMethod.ordinal()]) {
            case 1:
            case 2:
                if (restOptions.hasData()) {
                    throw new ApiException("HTTP method does not support data object! " + httpMethod, "Try sending the request without any data in the options.");
                }
                restOperationRequest = new RestOperationRequest(httpMethod, restOptions.getPath(), restOptions.getHeaders(), restOptions.getQueryParameters());
                break;
                break;
            case 3:
                if (restOptions.hasData()) {
                    restOperationRequest = new RestOperationRequest(httpMethod, restOptions.getPath(), restOptions.getData(), restOptions.getHeaders(), restOptions.getQueryParameters());
                } else {
                    restOperationRequest = new RestOperationRequest(httpMethod, restOptions.getPath(), restOptions.getHeaders(), restOptions.getQueryParameters());
                }
                break;
            case 4:
            case 5:
            case 6:
                restOperationRequest = new RestOperationRequest(httpMethod, restOptions.getPath(), restOptions.getData() == null ? new byte[0] : restOptions.getData(), restOptions.getHeaders(), restOptions.getQueryParameters());
                break;
            default:
                throw new ApiException("Unknown REST operation type: " + httpMethod, "Send support type for the request.");
        }
        AWSRestOperation aWSRestOperation = new AWSRestOperation(restOperationRequest, clientDetails.apiConfiguration.getEndpoint(), clientDetails.okHttpClient, consumer, consumer2);
        aWSRestOperation.start();
        return aWSRestOperation;
    }

    /* JADX INFO: renamed from: com.amplifyframework.api.aws.AWSApiPlugin$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amplifyframework$api$aws$EndpointType;
        static final /* synthetic */ int[] $SwitchMap$com$amplifyframework$api$rest$HttpMethod;

        static {
            int[] iArr = new int[HttpMethod.values().length];
            $SwitchMap$com$amplifyframework$api$rest$HttpMethod = iArr;
            try {
                iArr[HttpMethod.HEAD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amplifyframework$api$rest$HttpMethod[HttpMethod.GET.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amplifyframework$api$rest$HttpMethod[HttpMethod.DELETE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amplifyframework$api$rest$HttpMethod[HttpMethod.PUT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amplifyframework$api$rest$HttpMethod[HttpMethod.POST.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amplifyframework$api$rest$HttpMethod[HttpMethod.PATCH.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            int[] iArr2 = new int[EndpointType.values().length];
            $SwitchMap$com$amplifyframework$api$aws$EndpointType = iArr2;
            try {
                iArr2[EndpointType.REST.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amplifyframework$api$aws$EndpointType[EndpointType.GRAPHQL.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    static final class ClientDetails {
        private final ApiConfiguration apiConfiguration;
        private final ApiRequestDecoratorFactory apiRequestDecoratorFactory;
        private final OkHttpClient okHttpClient;
        private final SubscriptionEndpoint subscriptionEndpoint;

        ClientDetails(ApiConfiguration apiConfiguration, OkHttpClient okHttpClient, SubscriptionEndpoint subscriptionEndpoint, ApiRequestDecoratorFactory apiRequestDecoratorFactory) {
            this.apiConfiguration = apiConfiguration;
            this.okHttpClient = okHttpClient;
            this.subscriptionEndpoint = subscriptionEndpoint;
            this.apiRequestDecoratorFactory = apiRequestDecoratorFactory;
        }

        ApiConfiguration getApiConfiguration() {
            return this.apiConfiguration;
        }

        OkHttpClient getOkHttpClient() {
            return this.okHttpClient;
        }

        SubscriptionEndpoint getSubscriptionEndpoint() {
            return this.subscriptionEndpoint;
        }

        ApiRequestDecoratorFactory getApiRequestDecoratorFactory() {
            return this.apiRequestDecoratorFactory;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || ClientDetails.class != obj.getClass()) {
                return false;
            }
            ClientDetails clientDetails = (ClientDetails) obj;
            if (ObjectsCompat.equals(this.apiConfiguration, clientDetails.apiConfiguration) && ObjectsCompat.equals(this.okHttpClient, clientDetails.okHttpClient)) {
                return ObjectsCompat.equals(this.subscriptionEndpoint, clientDetails.subscriptionEndpoint);
            }
            return false;
        }

        public int hashCode() {
            ApiConfiguration apiConfiguration = this.apiConfiguration;
            int iHashCode = (apiConfiguration != null ? apiConfiguration.hashCode() : 0) * 31;
            OkHttpClient okHttpClient = this.okHttpClient;
            int iHashCode2 = (iHashCode + (okHttpClient != null ? okHttpClient.hashCode() : 0)) * 31;
            SubscriptionEndpoint subscriptionEndpoint = this.subscriptionEndpoint;
            return iHashCode2 + (subscriptionEndpoint != null ? subscriptionEndpoint.hashCode() : 0);
        }
    }

    private static final class ApiConnectionEventListener extends EventListener {
        private final AtomicReference<ApiEndpointStatusChangeEvent.ApiEndpointStatus> currentNetworkStatus = new AtomicReference<>(ApiEndpointStatusChangeEvent.ApiEndpointStatus.UNKOWN);

        ApiConnectionEventListener() {
        }

        @Override // okhttp3.EventListener
        public void connectFailed(@NonNull Call call, @NonNull InetSocketAddress inetSocketAddress, @NonNull Proxy proxy, @Nullable Protocol protocol, @NonNull IOException iOException) {
            super.connectFailed(call, inetSocketAddress, proxy, protocol, iOException);
            transitionTo(ApiEndpointStatusChangeEvent.ApiEndpointStatus.NOT_REACHABLE);
        }

        @Override // okhttp3.EventListener
        public void connectionAcquired(@NonNull Call call, @NonNull Connection connection) {
            super.connectionAcquired(call, connection);
            transitionTo(ApiEndpointStatusChangeEvent.ApiEndpointStatus.REACHABLE);
        }

        private void transitionTo(ApiEndpointStatusChangeEvent.ApiEndpointStatus apiEndpointStatus) {
            ApiEndpointStatusChangeEvent.ApiEndpointStatus andSet = this.currentNetworkStatus.getAndSet(apiEndpointStatus);
            if (andSet != apiEndpointStatus) {
                Amplify.Hub.publish(HubChannel.API, andSet.transitionTo(apiEndpointStatus).toHubEvent());
            }
        }
    }

    public static final class Builder {
        private ApiAuthProviders apiAuthProviders;
        private final Map<String, OkHttpConfigurator> apiConfigurators;

        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            this.apiAuthProviders = ApiAuthProviders.noProviderOverrides();
            this.apiConfigurators = new HashMap();
        }

        @NonNull
        public Builder apiAuthProviders(@NonNull ApiAuthProviders apiAuthProviders) {
            Objects.requireNonNull(apiAuthProviders);
            this.apiAuthProviders = apiAuthProviders;
            return this;
        }

        @NonNull
        public Builder configureClient(@NonNull String str, @NonNull OkHttpConfigurator okHttpConfigurator) {
            this.apiConfigurators.put(str, okHttpConfigurator);
            return this;
        }

        @NonNull
        public AWSApiPlugin build() {
            return new AWSApiPlugin(this, null);
        }
    }
}
