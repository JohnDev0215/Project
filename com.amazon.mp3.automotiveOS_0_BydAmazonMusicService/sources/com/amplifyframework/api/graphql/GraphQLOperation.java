package com.amplifyframework.api.graphql;

import androidx.annotation.NonNull;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.ApiException;
import com.amplifyframework.api.ApiOperation;
import com.amplifyframework.api.graphql.GraphQLResponse;

/* JADX INFO: loaded from: classes5.dex */
public abstract class GraphQLOperation<R> extends ApiOperation<GraphQLRequest<R>> {
    private final GraphQLResponse.Factory responseFactory;

    public GraphQLOperation(@NonNull GraphQLRequest<R> graphQLRequest, @NonNull GraphQLResponse.Factory factory) {
        super(graphQLRequest);
        this.responseFactory = factory;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final GraphQLResponse<R> wrapResponse(String str) throws ApiException {
        try {
            return this.responseFactory.buildResponse((GraphQLRequest) getRequest(), str);
        } catch (ClassCastException unused) {
            throw new ApiException("Amplify encountered an error while deserializing an object", AmplifyException.TODO_RECOVERY_SUGGESTION);
        }
    }
}
