package com.amplifyframework.api.aws;

import androidx.annotation.NonNull;
import com.amplifyframework.api.ApiException;
import com.amplifyframework.api.graphql.GraphQLOperation;
import com.amplifyframework.api.graphql.GraphQLRequest;
import com.amplifyframework.api.graphql.GraphQLResponse;
import com.amplifyframework.core.Action;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.logging.Logger;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes5.dex */
final class SubscriptionOperation<T> extends GraphQLOperation<T> {
    private static final Logger LOG = Amplify.Logging.forNamespace("amplify:aws-api");
    private final AuthorizationType authorizationType;
    private final AtomicBoolean canceled;
    private final ExecutorService executorService;
    private final Consumer<GraphQLResponse<T>> onNextItem;
    private final Action onSubscriptionComplete;
    private final Consumer<ApiException> onSubscriptionError;
    private final Consumer<String> onSubscriptionStart;
    private final SubscriptionEndpoint subscriptionEndpoint;
    private Future<?> subscriptionFuture;
    private String subscriptionId;

    private SubscriptionOperation(Builder<T> builder) {
        super(((Builder) builder).graphQlRequest, ((Builder) builder).responseFactory);
        this.subscriptionEndpoint = ((Builder) builder).subscriptionEndpoint;
        this.onSubscriptionStart = ((Builder) builder).onSubscriptionStart;
        this.onNextItem = ((Builder) builder).onNextItem;
        this.onSubscriptionError = ((Builder) builder).onSubscriptionError;
        this.onSubscriptionComplete = ((Builder) builder).onSubscriptionComplete;
        this.executorService = ((Builder) builder).executorService;
        this.canceled = new AtomicBoolean(false);
        this.authorizationType = ((Builder) builder).authorizationType;
    }

    @NonNull
    static <T> Builder<T> builder() {
        return new Builder<>();
    }

    @Override // com.amplifyframework.core.async.AmplifyOperation
    public synchronized void start() {
        if (this.canceled.get()) {
            this.onSubscriptionError.accept(new ApiException("Operation already canceled.", "Don't cancel the subscription before starting it!"));
        } else {
            this.subscriptionFuture = this.executorService.submit(new Runnable() { // from class: com.amplifyframework.api.aws.SubscriptionOperation$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$start$2();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$start$2() {
        LOG.debug("Requesting subscription: " + getRequest().getContent());
        this.subscriptionEndpoint.requestSubscription((GraphQLRequest) getRequest(), this.authorizationType, new Consumer() { // from class: com.amplifyframework.api.aws.SubscriptionOperation$$ExternalSyntheticLambda2
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$null$0((String) obj);
            }
        }, this.onNextItem, new Consumer() { // from class: com.amplifyframework.api.aws.SubscriptionOperation$$ExternalSyntheticLambda3
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$null$1((ApiException) obj);
            }
        }, this.onSubscriptionComplete);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$null$0(String str) {
        this.subscriptionId = str;
        this.onSubscriptionStart.accept(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$null$1(ApiException apiException) {
        cancel();
        this.onSubscriptionError.accept(apiException);
    }

    @Override // com.amplifyframework.core.async.Cancelable
    public synchronized void cancel() {
        if (this.subscriptionId != null && !this.canceled.get()) {
            this.canceled.set(true);
            this.executorService.execute(new Runnable() { // from class: com.amplifyframework.api.aws.SubscriptionOperation$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$cancel$3();
                }
            });
        } else {
            Future<?> future = this.subscriptionFuture;
            if (future != null && future.cancel(true)) {
                LOG.debug("Subscription attempt was canceled.");
            } else {
                LOG.debug("Nothing to cancel. Subscription not yet created, or already cancelled.");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$cancel$3() {
        try {
            LOG.debug("Cancelling subscription: " + this.subscriptionId);
            this.subscriptionEndpoint.releaseSubscription(this.subscriptionId);
        } catch (ApiException e) {
            this.onSubscriptionError.accept(e);
        }
    }

    static final class Builder<T> {
        private AuthorizationType authorizationType;
        private ExecutorService executorService;
        private GraphQLRequest<T> graphQlRequest;
        private Consumer<GraphQLResponse<T>> onNextItem;
        private Action onSubscriptionComplete;
        private Consumer<ApiException> onSubscriptionError;
        private Consumer<String> onSubscriptionStart;
        private GraphQLResponse.Factory responseFactory;
        private SubscriptionEndpoint subscriptionEndpoint;

        Builder() {
        }

        @NonNull
        public Builder<T> subscriptionEndpoint(@NonNull SubscriptionEndpoint subscriptionEndpoint) {
            Objects.requireNonNull(subscriptionEndpoint);
            this.subscriptionEndpoint = subscriptionEndpoint;
            return this;
        }

        @NonNull
        public Builder<T> graphQlRequest(@NonNull GraphQLRequest<T> graphQLRequest) {
            Objects.requireNonNull(graphQLRequest);
            this.graphQlRequest = graphQLRequest;
            return this;
        }

        @NonNull
        public Builder<T> responseFactory(@NonNull GraphQLResponse.Factory factory) {
            Objects.requireNonNull(factory);
            this.responseFactory = factory;
            return this;
        }

        @NonNull
        public Builder<T> executorService(@NonNull ExecutorService executorService) {
            Objects.requireNonNull(executorService);
            this.executorService = executorService;
            return this;
        }

        @NonNull
        public Builder<T> onSubscriptionStart(@NonNull Consumer<String> consumer) {
            Objects.requireNonNull(consumer);
            this.onSubscriptionStart = consumer;
            return this;
        }

        @NonNull
        public Builder<T> onNextItem(@NonNull Consumer<GraphQLResponse<T>> consumer) {
            Objects.requireNonNull(consumer);
            this.onNextItem = consumer;
            return this;
        }

        @NonNull
        public Builder<T> onSubscriptionError(@NonNull Consumer<ApiException> consumer) {
            Objects.requireNonNull(consumer);
            this.onSubscriptionError = consumer;
            return this;
        }

        @NonNull
        public Builder<T> onSubscriptionComplete(@NonNull Action action) {
            Objects.requireNonNull(action);
            this.onSubscriptionComplete = action;
            return this;
        }

        @NonNull
        public Builder<T> authorizationType(AuthorizationType authorizationType) {
            this.authorizationType = authorizationType;
            return this;
        }

        @NonNull
        public SubscriptionOperation<T> build() {
            return new SubscriptionOperation<>(this);
        }
    }
}
