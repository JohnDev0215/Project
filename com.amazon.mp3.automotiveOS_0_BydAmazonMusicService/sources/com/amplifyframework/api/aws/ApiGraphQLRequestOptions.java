package com.amplifyframework.api.aws;

import androidx.annotation.NonNull;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class ApiGraphQLRequestOptions implements GraphQLRequestOptions {
    private static final String ITEMS_KEY = "items";
    private static final String NEXT_TOKEN_KEY = "nextToken";

    @Override // com.amplifyframework.api.aws.GraphQLRequestOptions
    @NonNull
    public String listField() {
        return "items";
    }

    @Override // com.amplifyframework.api.aws.GraphQLRequestOptions
    public int maxDepth() {
        return 2;
    }

    @Override // com.amplifyframework.api.aws.GraphQLRequestOptions
    @NonNull
    public List<String> paginationFields() {
        return Collections.singletonList(NEXT_TOKEN_KEY);
    }

    @Override // com.amplifyframework.api.aws.GraphQLRequestOptions
    @NonNull
    public List<String> modelMetaFields() {
        return Collections.emptyList();
    }

    @Override // com.amplifyframework.api.aws.GraphQLRequestOptions
    @NonNull
    public LeafSerializationBehavior leafSerializationBehavior() {
        return LeafSerializationBehavior.ALL_FIELDS;
    }
}
