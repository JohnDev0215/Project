package com.amplifyframework.api.aws;

import androidx.annotation.NonNull;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.ApiException;
import com.amplifyframework.api.aws.auth.AuthRuleRequestDecorator;
import com.amplifyframework.api.graphql.GraphQLOperation;
import com.amplifyframework.api.graphql.GraphQLRequest;
import com.amplifyframework.api.graphql.GraphQLResponse;
import com.amplifyframework.core.Action;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.model.auth.AuthorizationTypeIterator;
import com.amplifyframework.datastore.appsync.AppSyncExtensions;
import com.amplifyframework.logging.Logger;
import com.amplifyframework.util.Empty;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes5.dex */
final class MutiAuthSubscriptionOperation<T> extends GraphQLOperation<T> {
    private static final Logger LOG = Amplify.Logging.forNamespace("amplify:aws-api");
    private AuthorizationTypeIterator authTypes;
    private final AtomicBoolean canceled;
    private final ExecutorService executorService;
    private final Consumer<GraphQLResponse<T>> onNextItem;
    private final Action onSubscriptionComplete;
    private final Consumer<ApiException> onSubscriptionError;
    private final Consumer<String> onSubscriptionStart;
    private final AuthRuleRequestDecorator requestDecorator;
    private final SubscriptionEndpoint subscriptionEndpoint;
    private Future<?> subscriptionFuture;
    private String subscriptionId;

    private MutiAuthSubscriptionOperation(Builder<T> builder) {
        super(((Builder) builder).graphQlRequest, ((Builder) builder).responseFactory);
        this.subscriptionEndpoint = ((Builder) builder).subscriptionEndpoint;
        this.onSubscriptionStart = ((Builder) builder).onSubscriptionStart;
        this.onNextItem = ((Builder) builder).onNextItem;
        this.onSubscriptionError = ((Builder) builder).onSubscriptionError;
        this.onSubscriptionComplete = ((Builder) builder).onSubscriptionComplete;
        this.executorService = ((Builder) builder).executorService;
        this.canceled = new AtomicBoolean(false);
        this.requestDecorator = ((Builder) builder).requestDecorator;
        this.authTypes = MultiAuthModeStrategy.getInstance().authTypesFor(((Builder) builder).graphQlRequest.getModelSchema(), ((Builder) builder).graphQlRequest.getAuthRuleOperation());
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
            this.subscriptionFuture = this.executorService.submit(new MutiAuthSubscriptionOperation$$ExternalSyntheticLambda0(this));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchRequest() {
        Logger logger = LOG;
        logger.debug("Processing subscription request: " + getRequest().getContent());
        if (this.authTypes.hasNext()) {
            AuthorizationType next = this.authTypes.next();
            logger.debug("Attempting to subscribe with " + next.name());
            GraphQLRequest request = getRequest();
            if (this.authTypes.isOwnerBasedRule()) {
                try {
                    request = this.requestDecorator.decorate(request, next);
                } catch (ApiException.ApiAuthException unused) {
                    this.subscriptionFuture = this.executorService.submit(new MutiAuthSubscriptionOperation$$ExternalSyntheticLambda0(this));
                    return;
                } catch (ApiException e) {
                    LOG.warn("Unable to automatically add an owner to the request.", e);
                    emitErrorAndCancelSubscription(e);
                    return;
                }
            }
            this.subscriptionEndpoint.requestSubscription(request, next, new Consumer() { // from class: com.amplifyframework.api.aws.MutiAuthSubscriptionOperation$$ExternalSyntheticLambda1
                @Override // com.amplifyframework.core.Consumer
                public final void accept(Object obj) {
                    this.f$0.lambda$dispatchRequest$0((String) obj);
                }
            }, new Consumer() { // from class: com.amplifyframework.api.aws.MutiAuthSubscriptionOperation$$ExternalSyntheticLambda2
                @Override // com.amplifyframework.core.Consumer
                public final void accept(Object obj) {
                    this.f$0.lambda$dispatchRequest$1((GraphQLResponse) obj);
                }
            }, new Consumer() { // from class: com.amplifyframework.api.aws.MutiAuthSubscriptionOperation$$ExternalSyntheticLambda3
                @Override // com.amplifyframework.core.Consumer
                public final void accept(Object obj) {
                    this.f$0.lambda$dispatchRequest$2((ApiException) obj);
                }
            }, this.onSubscriptionComplete);
            return;
        }
        emitErrorAndCancelSubscription(new ApiException("Unable to establish subscription connection.", AmplifyException.TODO_RECOVERY_SUGGESTION));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$dispatchRequest$0(String str) {
        this.subscriptionId = str;
        this.onSubscriptionStart.accept(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$dispatchRequest$1(GraphQLResponse graphQLResponse) {
        if (graphQLResponse.hasErrors() && hasAuthRelatedErrors(graphQLResponse) && this.authTypes.hasNext()) {
            this.executorService.submit(new MutiAuthSubscriptionOperation$$ExternalSyntheticLambda0(this));
        } else {
            this.onNextItem.accept(graphQLResponse);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$dispatchRequest$2(ApiException apiException) {
        LOG.warn("A subscription error occurred.", apiException);
        if ((apiException instanceof ApiException.ApiAuthException) && this.authTypes.hasNext()) {
            this.executorService.submit(new MutiAuthSubscriptionOperation$$ExternalSyntheticLambda0(this));
        } else {
            emitErrorAndCancelSubscription(apiException);
        }
    }

    @Override // com.amplifyframework.core.async.Cancelable
    public synchronized void cancel() {
        if (this.subscriptionId != null && !this.canceled.get()) {
            this.canceled.set(true);
            this.executorService.execute(new Runnable() { // from class: com.amplifyframework.api.aws.MutiAuthSubscriptionOperation$$ExternalSyntheticLambda4
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

    private boolean hasAuthRelatedErrors(GraphQLResponse<T> graphQLResponse) {
        for (GraphQLResponse.Error error : graphQLResponse.getErrors()) {
            if (!Empty.check(error.getExtensions())) {
                return new AppSyncExtensions(error.getExtensions()).isUnauthorizedErrorType();
            }
        }
        return false;
    }

    private void emitErrorAndCancelSubscription(ApiException apiException) {
        cancel();
        this.onSubscriptionError.accept(apiException);
    }

    static final class Builder<T> {
        private ExecutorService executorService;
        private AppSyncGraphQLRequest<T> graphQlRequest;
        private Consumer<GraphQLResponse<T>> onNextItem;
        private Action onSubscriptionComplete;
        private Consumer<ApiException> onSubscriptionError;
        private Consumer<String> onSubscriptionStart;
        private AuthRuleRequestDecorator requestDecorator;
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
        public Builder<T> graphQlRequest(@NonNull AppSyncGraphQLRequest<T> appSyncGraphQLRequest) {
            Objects.requireNonNull(appSyncGraphQLRequest);
            this.graphQlRequest = appSyncGraphQLRequest;
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

        public Builder<T> requestDecorator(AuthRuleRequestDecorator authRuleRequestDecorator) {
            this.requestDecorator = authRuleRequestDecorator;
            return this;
        }

        @NonNull
        public MutiAuthSubscriptionOperation<T> build() {
            return new MutiAuthSubscriptionOperation<>(this);
        }
    }
}
