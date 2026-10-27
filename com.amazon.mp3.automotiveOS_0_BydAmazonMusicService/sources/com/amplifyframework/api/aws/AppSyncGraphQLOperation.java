package com.amplifyframework.api.aws;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.ApiException;
import com.amplifyframework.api.aws.auth.ApiRequestDecoratorFactory;
import com.amplifyframework.api.graphql.GraphQLOperation;
import com.amplifyframework.api.graphql.GraphQLRequest;
import com.amplifyframework.api.graphql.GraphQLResponse;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.logging.Logger;
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
public final class AppSyncGraphQLOperation<R> extends GraphQLOperation<R> {
    private static final String CONTENT_TYPE = "application/json";
    private static final int END_OF_CLIENT_ERROR_CODE = 499;
    private static final Logger LOG = Amplify.Logging.forNamespace("amplify:aws-api");
    private static final int START_OF_CLIENT_ERROR_CODE = 400;
    private final ApiRequestDecoratorFactory apiRequestDecoratorFactory;
    private final OkHttpClient client;
    private final String endpoint;
    private final ExecutorService executorService;
    private final Consumer<ApiException> onFailure;
    private final Consumer<GraphQLResponse<R>> onResponse;

    @Nullable
    private Call ongoingCall;

    private AppSyncGraphQLOperation(@NonNull Builder<R> builder) {
        super(((Builder) builder).request, ((Builder) builder).responseFactory);
        String str = ((Builder) builder).endpoint;
        Objects.requireNonNull(str);
        this.endpoint = str;
        OkHttpClient okHttpClient = ((Builder) builder).client;
        Objects.requireNonNull(okHttpClient);
        this.client = okHttpClient;
        ApiRequestDecoratorFactory apiRequestDecoratorFactory = ((Builder) builder).apiRequestDecoratorFactory;
        Objects.requireNonNull(apiRequestDecoratorFactory);
        this.apiRequestDecoratorFactory = apiRequestDecoratorFactory;
        ExecutorService executorService = ((Builder) builder).executorService;
        Objects.requireNonNull(executorService);
        this.executorService = executorService;
        Consumer<GraphQLResponse<R>> consumer = ((Builder) builder).onResponse;
        Objects.requireNonNull(consumer);
        this.onResponse = consumer;
        Consumer<ApiException> consumer2 = ((Builder) builder).onFailure;
        Objects.requireNonNull(consumer2);
        this.onFailure = consumer2;
    }

    @Override // com.amplifyframework.core.async.AmplifyOperation
    public void start() {
        Call call = this.ongoingCall;
        if (call == null || !(call.isExecuted() || this.ongoingCall.getCanceled())) {
            this.executorService.submit(new Runnable() { // from class: com.amplifyframework.api.aws.AppSyncGraphQLOperation$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.dispatchRequest();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchRequest() {
        try {
            LOG.debug("Request: " + ((GraphQLRequest) getRequest()).getContent());
            Call callNewCall = this.client.newCall(this.apiRequestDecoratorFactory.fromGraphQLRequest((GraphQLRequest) getRequest()).decorate(new Request.Builder().url(this.endpoint).addHeader("accept", CONTENT_TYPE).addHeader("content-type", CONTENT_TYPE).post(RequestBody.create(((GraphQLRequest) getRequest()).getContent(), MediaType.parse(CONTENT_TYPE))).build()));
            this.ongoingCall = callNewCall;
            FirebasePerfOkHttpClient.enqueue(callNewCall, new OkHttpCallback());
        } catch (Exception e) {
            Call call = this.ongoingCall;
            if (call != null) {
                call.cancel();
            }
            this.onFailure.accept(new ApiException("OkHttp client failed to make a successful request.", e, AmplifyException.TODO_RECOVERY_SUGGESTION));
        }
    }

    @Override // com.amplifyframework.core.async.Cancelable
    public synchronized void cancel() {
        Call call = this.ongoingCall;
        if (call != null) {
            call.cancel();
        }
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
                    AppSyncGraphQLOperation.this.onFailure.accept(new ApiException("Could not retrieve the response body from the returned JSON", e, AmplifyException.TODO_RECOVERY_SUGGESTION));
                    return;
                }
            } else {
                strString = null;
            }
            if (response.code() >= 400 && response.code() <= AppSyncGraphQLOperation.END_OF_CLIENT_ERROR_CODE) {
                AppSyncGraphQLOperation.this.onFailure.accept(new ApiException.NonRetryableException("OkHttp client request failed.", "Irrecoverable error"));
            }
            try {
                AppSyncGraphQLOperation.this.onResponse.accept(AppSyncGraphQLOperation.this.wrapResponse(strString));
            } catch (ApiException e2) {
                AppSyncGraphQLOperation.this.onFailure.accept(e2);
            }
        }

        @Override // okhttp3.Callback
        public void onFailure(@NonNull Call call, @NonNull IOException iOException) {
            if (call.getCanceled()) {
                return;
            }
            AppSyncGraphQLOperation.this.onFailure.accept(new ApiException("OkHttp client request failed.", iOException, "See attached exception for more details."));
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

        Builder<R> apiRequestDecoratorFactory(@NonNull ApiRequestDecoratorFactory apiRequestDecoratorFactory) {
            Objects.requireNonNull(apiRequestDecoratorFactory);
            this.apiRequestDecoratorFactory = apiRequestDecoratorFactory;
            return this;
        }

        Builder<R> executorService(@NonNull ExecutorService executorService) {
            this.executorService = executorService;
            return this;
        }

        @SuppressLint({"SyntheticAccessor"})
        AppSyncGraphQLOperation<R> build() {
            return new AppSyncGraphQLOperation<>(this);
        }
    }
}
