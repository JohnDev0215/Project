package com.amplifyframework.api.aws;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.ApiException;
import com.amplifyframework.api.aws.auth.ApiRequestDecoratorFactory;
import com.amplifyframework.api.graphql.GraphQLOperation;
import com.amplifyframework.api.graphql.GraphQLRequest;
import com.amplifyframework.api.graphql.GraphQLResponse;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.model.auth.AuthorizationTypeIterator;
import com.amplifyframework.datastore.appsync.AppSyncExtensions;
import com.amplifyframework.logging.Logger;
import com.amplifyframework.util.Empty;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: loaded from: classes5.dex */
public final class MultiAuthAppSyncGraphQLOperation<R> extends GraphQLOperation<R> {
    private static final String CONTENT_TYPE = "application/json";
    private static final Logger LOG = Amplify.Logging.forNamespace("amplify:aws-api");
    private static final String UNAUTHORIZED_EXCEPTION = "UnauthorizedException";
    private final ApiRequestDecoratorFactory apiRequestDecoratorFactory;
    private AuthorizationTypeIterator authTypes;
    private final OkHttpClient client;
    private final String endpoint;
    private final ExecutorService executorService;
    private final Consumer<ApiException> onFailure;
    private final Consumer<GraphQLResponse<R>> onResponse;
    private Call ongoingCall;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void access$1400(MultiAuthAppSyncGraphQLOperation multiAuthAppSyncGraphQLOperation) {
        multiAuthAppSyncGraphQLOperation.dispatchRequest();
    }

    private MultiAuthAppSyncGraphQLOperation(Builder<R> builder) {
        super(((Builder) builder).request, ((Builder) builder).responseFactory);
        this.apiRequestDecoratorFactory = ((Builder) builder).apiRequestDecoratorFactory;
        this.endpoint = ((Builder) builder).endpoint;
        this.client = ((Builder) builder).client;
        this.onResponse = ((Builder) builder).onResponse;
        Consumer<ApiException> consumer = ((Builder) builder).onFailure;
        this.onFailure = consumer;
        this.executorService = ((Builder) builder).executorService;
        if (!(getRequest() instanceof AppSyncGraphQLRequest)) {
            consumer.accept(new ApiException("Multiauth only supported with AppSyncGraphQLRequest<T>.", AmplifyException.TODO_RECOVERY_SUGGESTION));
        } else {
            AppSyncGraphQLRequest appSyncGraphQLRequest = (AppSyncGraphQLRequest) getRequest();
            this.authTypes = MultiAuthModeStrategy.getInstance().authTypesFor(appSyncGraphQLRequest.getModelSchema(), appSyncGraphQLRequest.getAuthRuleOperation());
        }
    }

    @Override // com.amplifyframework.core.async.AmplifyOperation
    public void start() {
        Call call = this.ongoingCall;
        if (call == null || !call.isExecuted()) {
            this.executorService.submit(new MultiAuthAppSyncGraphQLOperation$$ExternalSyntheticLambda0(this));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchRequest() {
        if (this.authTypes.hasNext()) {
            AuthorizationType next = this.authTypes.next();
            try {
                Request requestDecorate = this.apiRequestDecoratorFactory.forAuthType(next).decorate(new Request.Builder().url(this.endpoint).addHeader("accept", CONTENT_TYPE).addHeader("content-type", CONTENT_TYPE).post(RequestBody.create(((GraphQLRequest) getRequest()).getContent(), MediaType.parse(CONTENT_TYPE))).build());
                LOG.debug("Request: " + ((GraphQLRequest) getRequest()).getContent());
                Call callNewCall = this.client.newCall(requestDecorate);
                this.ongoingCall = callNewCall;
                FirebasePerfOkHttpClient.enqueue(callNewCall, new OkHttpCallback());
                return;
            } catch (ApiException e) {
                LOG.warn("Failed to make a successful request with " + next, e);
                if ((e instanceof ApiException.ApiAuthException) && this.authTypes.hasNext()) {
                    this.executorService.submit(new MultiAuthAppSyncGraphQLOperation$$ExternalSyntheticLambda0(this));
                    return;
                } else {
                    this.onFailure.accept(e);
                    return;
                }
            }
        }
        this.onFailure.accept(new ApiException.ApiAuthException("Unable to successfully complete request with any of the compatible auth types.", "Check your application logs for detail."));
    }

    @Override // com.amplifyframework.core.async.Cancelable
    public void cancel() {
        Call call = this.ongoingCall;
        if (call != null) {
            call.cancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasAuthRelatedErrors(GraphQLResponse<R> graphQLResponse) {
        for (GraphQLResponse.Error error : graphQLResponse.getErrors()) {
            if (!Empty.check(error.getExtensions())) {
                return new AppSyncExtensions(error.getExtensions()).isUnauthorizedErrorType();
            }
        }
        return false;
    }

    static <R> Builder<R> builder() {
        return new Builder<>();
    }

    @SuppressLint({"SyntheticAccessor"})
    class OkHttpCallback implements Callback {
        OkHttpCallback() {
        }

        @Override // okhttp3.Callback
        public void onResponse(@NonNull Call call, @NonNull Response response) {
            String strString;
            ResponseBody responseBodyBody = response.body();
            if (responseBodyBody != null) {
                try {
                    strString = responseBodyBody.string();
                } catch (IOException e) {
                    MultiAuthAppSyncGraphQLOperation.this.onFailure.accept(new ApiException("Could not retrieve the response body from the returned JSON", e, AmplifyException.TODO_RECOVERY_SUGGESTION));
                    return;
                }
            } else {
                strString = null;
            }
            try {
                GraphQLResponse graphQLResponseWrapResponse = MultiAuthAppSyncGraphQLOperation.this.wrapResponse(strString);
                if (!graphQLResponseWrapResponse.hasErrors() || !MultiAuthAppSyncGraphQLOperation.this.hasAuthRelatedErrors(graphQLResponseWrapResponse) || !MultiAuthAppSyncGraphQLOperation.this.authTypes.hasNext()) {
                    MultiAuthAppSyncGraphQLOperation.this.onResponse.accept(graphQLResponseWrapResponse);
                    return;
                }
                ExecutorService executorService = MultiAuthAppSyncGraphQLOperation.this.executorService;
                final MultiAuthAppSyncGraphQLOperation multiAuthAppSyncGraphQLOperation = MultiAuthAppSyncGraphQLOperation.this;
                executorService.submit(new Runnable() { // from class: com.amplifyframework.api.aws.MultiAuthAppSyncGraphQLOperation$OkHttpCallback$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        MultiAuthAppSyncGraphQLOperation.access$1400(multiAuthAppSyncGraphQLOperation);
                    }
                });
            } catch (ApiException e2) {
                MultiAuthAppSyncGraphQLOperation.this.onFailure.accept(e2);
            }
        }

        @Override // okhttp3.Callback
        public void onFailure(@NonNull Call call, @NonNull IOException iOException) {
            MultiAuthAppSyncGraphQLOperation.this.onFailure.accept(new ApiException("OkHttp client request failed.", iOException, "See attached exception for more details."));
        }
    }

    static final class Builder<R> {
        private ApiRequestDecoratorFactory apiRequestDecoratorFactory;
        private OkHttpClient client;
        private String endpoint;
        private ExecutorService executorService;
        private Consumer<ApiException> onFailure;
        private Consumer<GraphQLResponse<R>> onResponse;
        private GraphQLRequest<R> request;
        private GraphQLResponse.Factory responseFactory;

        Builder() {
        }

        Builder<R> endpoint(@NonNull String str) {
            Objects.requireNonNull(str);
            this.endpoint = str;
            return this;
        }

        Builder<R> client(@NonNull OkHttpClient okHttpClient) {
            Objects.requireNonNull(okHttpClient);
            this.client = okHttpClient;
            return this;
        }

        Builder<R> request(@NonNull GraphQLRequest<R> graphQLRequest) {
            Objects.requireNonNull(graphQLRequest);
            this.request = graphQLRequest;
            return this;
        }

        Builder<R> responseFactory(@NonNull GraphQLResponse.Factory factory) {
            Objects.requireNonNull(factory);
            this.responseFactory = factory;
            return this;
        }

        Builder<R> onResponse(@NonNull Consumer<GraphQLResponse<R>> consumer) {
            Objects.requireNonNull(consumer);
            this.onResponse = consumer;
            return this;
        }

        Builder<R> onFailure(@NonNull Consumer<ApiException> consumer) {
            Objects.requireNonNull(consumer);
            this.onFailure = consumer;
            return this;
        }

        Builder<R> apiRequestDecoratorFactory(ApiRequestDecoratorFactory apiRequestDecoratorFactory) {
            this.apiRequestDecoratorFactory = apiRequestDecoratorFactory;
            return this;
        }

        Builder<R> executorService(ExecutorService executorService) {
            this.executorService = executorService;
            return this;
        }

        @SuppressLint({"SyntheticAccessor"})
        MultiAuthAppSyncGraphQLOperation<R> build() {
            return new MultiAuthAppSyncGraphQLOperation<>(this);
        }
    }
}
