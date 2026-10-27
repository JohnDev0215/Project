package com.amplifyframework.rx;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amplifyframework.api.ApiCategory;
import com.amplifyframework.api.ApiCategoryBehavior;
import com.amplifyframework.api.ApiException;
import com.amplifyframework.api.graphql.GraphQLRequest;
import com.amplifyframework.api.graphql.GraphQLResponse;
import com.amplifyframework.api.rest.RestOptions;
import com.amplifyframework.api.rest.RestResponse;
import com.amplifyframework.core.Action;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.async.Cancelable;
import com.amplifyframework.rx.RxAdapters;
import com.amplifyframework.rx.RxOperations;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

/* JADX INFO: loaded from: classes5.dex */
final class RxApiBinding implements RxApiCategoryBehavior {
    private final ApiCategoryBehavior api;

    RxApiBinding() {
        this(Amplify.API);
    }

    @VisibleForTesting
    RxApiBinding(ApiCategory apiCategory) {
        this.api = apiCategory;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Cancelable lambda$query$0(GraphQLRequest graphQLRequest, Consumer consumer, Consumer consumer2) {
        return this.api.query(graphQLRequest, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxGraphQlBehavior
    @NonNull
    public <T> Single<GraphQLResponse<T>> query(@NonNull final GraphQLRequest<T> graphQLRequest) {
        return toSingle(new RxAdapters.CancelableBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxApiBinding$$ExternalSyntheticLambda14
            @Override // com.amplifyframework.rx.RxAdapters.CancelableBehaviors.ResultEmitter
            public final Cancelable emitTo(Consumer consumer, Consumer consumer2) {
                return this.f$0.lambda$query$0(graphQLRequest, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Cancelable lambda$query$1(String str, GraphQLRequest graphQLRequest, Consumer consumer, Consumer consumer2) {
        return this.api.query(str, graphQLRequest, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxGraphQlBehavior
    @NonNull
    public <T> Single<GraphQLResponse<T>> query(@NonNull final String str, @NonNull final GraphQLRequest<T> graphQLRequest) {
        return toSingle(new RxAdapters.CancelableBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxApiBinding$$ExternalSyntheticLambda9
            @Override // com.amplifyframework.rx.RxAdapters.CancelableBehaviors.ResultEmitter
            public final Cancelable emitTo(Consumer consumer, Consumer consumer2) {
                return this.f$0.lambda$query$1(str, graphQLRequest, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Cancelable lambda$mutate$2(GraphQLRequest graphQLRequest, Consumer consumer, Consumer consumer2) {
        return this.api.mutate(graphQLRequest, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxGraphQlBehavior
    @NonNull
    public <T> Single<GraphQLResponse<T>> mutate(@NonNull final GraphQLRequest<T> graphQLRequest) {
        return toSingle(new RxAdapters.CancelableBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxApiBinding$$ExternalSyntheticLambda8
            @Override // com.amplifyframework.rx.RxAdapters.CancelableBehaviors.ResultEmitter
            public final Cancelable emitTo(Consumer consumer, Consumer consumer2) {
                return this.f$0.lambda$mutate$2(graphQLRequest, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Cancelable lambda$mutate$3(String str, GraphQLRequest graphQLRequest, Consumer consumer, Consumer consumer2) {
        return this.api.mutate(str, graphQLRequest, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxGraphQlBehavior
    @NonNull
    public <T> Single<GraphQLResponse<T>> mutate(@NonNull final String str, @NonNull final GraphQLRequest<T> graphQLRequest) {
        return toSingle(new RxAdapters.CancelableBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxApiBinding$$ExternalSyntheticLambda1
            @Override // com.amplifyframework.rx.RxAdapters.CancelableBehaviors.ResultEmitter
            public final Cancelable emitTo(Consumer consumer, Consumer consumer2) {
                return this.f$0.lambda$mutate$3(str, graphQLRequest, consumer, consumer2);
            }
        });
    }

    @Override // com.amplifyframework.rx.RxGraphQlBehavior
    @NonNull
    public <T> RxOperations.RxSubscriptionOperation<GraphQLResponse<T>> subscribe(@NonNull final GraphQLRequest<T> graphQLRequest) {
        return new RxOperations.RxSubscriptionOperation<>(new RxAdapters.CancelableBehaviors.StreamEmitter() { // from class: com.amplifyframework.rx.RxApiBinding$$ExternalSyntheticLambda4
            @Override // com.amplifyframework.rx.RxAdapters.CancelableBehaviors.StreamEmitter
            public final Cancelable streamTo(Consumer consumer, Consumer consumer2, Consumer consumer3, Action action) {
                return this.f$0.lambda$subscribe$4(graphQLRequest, consumer, consumer2, consumer3, action);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Cancelable lambda$subscribe$4(GraphQLRequest graphQLRequest, Consumer consumer, Consumer consumer2, Consumer consumer3, Action action) {
        return this.api.subscribe(graphQLRequest, consumer, consumer2, consumer3, action);
    }

    @Override // com.amplifyframework.rx.RxGraphQlBehavior
    @NonNull
    public <T> RxOperations.RxSubscriptionOperation<GraphQLResponse<T>> subscribe(@NonNull final String str, @NonNull final GraphQLRequest<T> graphQLRequest) {
        return new RxOperations.RxSubscriptionOperation<>(new RxAdapters.CancelableBehaviors.StreamEmitter() { // from class: com.amplifyframework.rx.RxApiBinding$$ExternalSyntheticLambda13
            @Override // com.amplifyframework.rx.RxAdapters.CancelableBehaviors.StreamEmitter
            public final Cancelable streamTo(Consumer consumer, Consumer consumer2, Consumer consumer3, Action action) {
                return this.f$0.lambda$subscribe$5(str, graphQLRequest, consumer, consumer2, consumer3, action);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Cancelable lambda$subscribe$5(String str, GraphQLRequest graphQLRequest, Consumer consumer, Consumer consumer2, Consumer consumer3, Action action) {
        return this.api.subscribe(str, graphQLRequest, consumer, consumer2, consumer3, action);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Cancelable lambda$get$6(RestOptions restOptions, Consumer consumer, Consumer consumer2) {
        return this.api.get(restOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxRestBehavior
    @NonNull
    public Single<RestResponse> get(@NonNull final RestOptions restOptions) {
        return toSingle(new RxAdapters.CancelableBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxApiBinding$$ExternalSyntheticLambda15
            @Override // com.amplifyframework.rx.RxAdapters.CancelableBehaviors.ResultEmitter
            public final Cancelable emitTo(Consumer consumer, Consumer consumer2) {
                return this.f$0.lambda$get$6(restOptions, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Cancelable lambda$get$7(String str, RestOptions restOptions, Consumer consumer, Consumer consumer2) {
        return this.api.get(str, restOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxRestBehavior
    @NonNull
    public Single<RestResponse> get(@NonNull final String str, @NonNull final RestOptions restOptions) {
        return toSingle(new RxAdapters.CancelableBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxApiBinding$$ExternalSyntheticLambda11
            @Override // com.amplifyframework.rx.RxAdapters.CancelableBehaviors.ResultEmitter
            public final Cancelable emitTo(Consumer consumer, Consumer consumer2) {
                return this.f$0.lambda$get$7(str, restOptions, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Cancelable lambda$put$8(RestOptions restOptions, Consumer consumer, Consumer consumer2) {
        return this.api.put(restOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxRestBehavior
    @NonNull
    public Single<RestResponse> put(@NonNull final RestOptions restOptions) {
        return toSingle(new RxAdapters.CancelableBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxApiBinding$$ExternalSyntheticLambda3
            @Override // com.amplifyframework.rx.RxAdapters.CancelableBehaviors.ResultEmitter
            public final Cancelable emitTo(Consumer consumer, Consumer consumer2) {
                return this.f$0.lambda$put$8(restOptions, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Cancelable lambda$put$9(String str, RestOptions restOptions, Consumer consumer, Consumer consumer2) {
        return this.api.put(str, restOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxRestBehavior
    @NonNull
    public Single<RestResponse> put(@NonNull final String str, @NonNull final RestOptions restOptions) {
        return toSingle(new RxAdapters.CancelableBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxApiBinding$$ExternalSyntheticLambda17
            @Override // com.amplifyframework.rx.RxAdapters.CancelableBehaviors.ResultEmitter
            public final Cancelable emitTo(Consumer consumer, Consumer consumer2) {
                return this.f$0.lambda$put$9(str, restOptions, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Cancelable lambda$post$10(RestOptions restOptions, Consumer consumer, Consumer consumer2) {
        return this.api.post(restOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxRestBehavior
    @NonNull
    public Single<RestResponse> post(@NonNull final RestOptions restOptions) {
        return toSingle(new RxAdapters.CancelableBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxApiBinding$$ExternalSyntheticLambda2
            @Override // com.amplifyframework.rx.RxAdapters.CancelableBehaviors.ResultEmitter
            public final Cancelable emitTo(Consumer consumer, Consumer consumer2) {
                return this.f$0.lambda$post$10(restOptions, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Cancelable lambda$post$11(String str, RestOptions restOptions, Consumer consumer, Consumer consumer2) {
        return this.api.post(str, restOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxRestBehavior
    @NonNull
    public Single<RestResponse> post(@NonNull final String str, @NonNull final RestOptions restOptions) {
        return toSingle(new RxAdapters.CancelableBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxApiBinding$$ExternalSyntheticLambda0
            @Override // com.amplifyframework.rx.RxAdapters.CancelableBehaviors.ResultEmitter
            public final Cancelable emitTo(Consumer consumer, Consumer consumer2) {
                return this.f$0.lambda$post$11(str, restOptions, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Cancelable lambda$delete$12(RestOptions restOptions, Consumer consumer, Consumer consumer2) {
        return this.api.delete(restOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxRestBehavior
    @NonNull
    public Single<RestResponse> delete(@NonNull final RestOptions restOptions) {
        return toSingle(new RxAdapters.CancelableBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxApiBinding$$ExternalSyntheticLambda16
            @Override // com.amplifyframework.rx.RxAdapters.CancelableBehaviors.ResultEmitter
            public final Cancelable emitTo(Consumer consumer, Consumer consumer2) {
                return this.f$0.lambda$delete$12(restOptions, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Cancelable lambda$delete$13(String str, RestOptions restOptions, Consumer consumer, Consumer consumer2) {
        return this.api.delete(str, restOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxRestBehavior
    @NonNull
    public Single<RestResponse> delete(@NonNull final String str, @NonNull final RestOptions restOptions) {
        return toSingle(new RxAdapters.CancelableBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxApiBinding$$ExternalSyntheticLambda10
            @Override // com.amplifyframework.rx.RxAdapters.CancelableBehaviors.ResultEmitter
            public final Cancelable emitTo(Consumer consumer, Consumer consumer2) {
                return this.f$0.lambda$delete$13(str, restOptions, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Cancelable lambda$head$14(RestOptions restOptions, Consumer consumer, Consumer consumer2) {
        return this.api.head(restOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxRestBehavior
    @NonNull
    public Single<RestResponse> head(@NonNull final RestOptions restOptions) {
        return toSingle(new RxAdapters.CancelableBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxApiBinding$$ExternalSyntheticLambda5
            @Override // com.amplifyframework.rx.RxAdapters.CancelableBehaviors.ResultEmitter
            public final Cancelable emitTo(Consumer consumer, Consumer consumer2) {
                return this.f$0.lambda$head$14(restOptions, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Cancelable lambda$head$15(String str, RestOptions restOptions, Consumer consumer, Consumer consumer2) {
        return this.api.head(str, restOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxRestBehavior
    @NonNull
    public Single<RestResponse> head(@NonNull final String str, @NonNull final RestOptions restOptions) {
        return toSingle(new RxAdapters.CancelableBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxApiBinding$$ExternalSyntheticLambda7
            @Override // com.amplifyframework.rx.RxAdapters.CancelableBehaviors.ResultEmitter
            public final Cancelable emitTo(Consumer consumer, Consumer consumer2) {
                return this.f$0.lambda$head$15(str, restOptions, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Cancelable lambda$patch$16(RestOptions restOptions, Consumer consumer, Consumer consumer2) {
        return this.api.patch(restOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxRestBehavior
    @NonNull
    public Single<RestResponse> patch(@NonNull final RestOptions restOptions) {
        return toSingle(new RxAdapters.CancelableBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxApiBinding$$ExternalSyntheticLambda6
            @Override // com.amplifyframework.rx.RxAdapters.CancelableBehaviors.ResultEmitter
            public final Cancelable emitTo(Consumer consumer, Consumer consumer2) {
                return this.f$0.lambda$patch$16(restOptions, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Cancelable lambda$patch$17(String str, RestOptions restOptions, Consumer consumer, Consumer consumer2) {
        return this.api.patch(str, restOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxRestBehavior
    @NonNull
    public Single<RestResponse> patch(@NonNull final String str, @NonNull final RestOptions restOptions) {
        return toSingle(new RxAdapters.CancelableBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxApiBinding$$ExternalSyntheticLambda12
            @Override // com.amplifyframework.rx.RxAdapters.CancelableBehaviors.ResultEmitter
            public final Cancelable emitTo(Consumer consumer, Consumer consumer2) {
                return this.f$0.lambda$patch$17(str, restOptions, consumer, consumer2);
            }
        });
    }

    private <T> Single<T> toSingle(RxAdapters.CancelableBehaviors.ResultEmitter<T, ApiException> resultEmitter) {
        return RxAdapters.CancelableBehaviors.toSingle(resultEmitter);
    }

    private <T> Observable<T> toObservable(RxAdapters.CancelableBehaviors.StreamEmitter<String, T, ApiException> streamEmitter) {
        return RxAdapters.CancelableBehaviors.toObservable(streamEmitter);
    }
}
