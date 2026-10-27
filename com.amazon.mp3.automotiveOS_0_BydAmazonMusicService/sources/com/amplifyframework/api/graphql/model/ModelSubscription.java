package com.amplifyframework.api.graphql.model;

import com.amplifyframework.api.aws.AppSyncGraphQLRequestFactory;
import com.amplifyframework.api.graphql.GraphQLRequest;
import com.amplifyframework.api.graphql.SubscriptionType;
import com.amplifyframework.core.model.Model;

/* JADX INFO: loaded from: classes5.dex */
public final class ModelSubscription {
    private ModelSubscription() {
    }

    public static <M extends Model> GraphQLRequest<M> of(Class<M> cls, SubscriptionType subscriptionType) {
        return AppSyncGraphQLRequestFactory.buildSubscription(cls, subscriptionType);
    }

    public static <M extends Model> GraphQLRequest<M> onCreate(Class<M> cls) {
        return of(cls, SubscriptionType.ON_CREATE);
    }

    public static <M extends Model> GraphQLRequest<M> onDelete(Class<M> cls) {
        return of(cls, SubscriptionType.ON_DELETE);
    }

    public static <M extends Model> GraphQLRequest<M> onUpdate(Class<M> cls) {
        return of(cls, SubscriptionType.ON_UPDATE);
    }
}
