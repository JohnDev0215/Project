package com.amplifyframework.datastore.appsync;

import androidx.annotation.NonNull;
import com.amplifyframework.api.aws.GraphQLRequestOptions;
import com.amplifyframework.api.aws.LeafSerializationBehavior;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class DataStoreGraphQLRequestOptions implements GraphQLRequestOptions {
    private static final String ITEMS_KEY = "items";
    private static final String NEXT_TOKEN_KEY = "nextToken";
    private static final String STARTED_AT_KEY = "startedAt";

    @Override // com.amplifyframework.api.aws.GraphQLRequestOptions
    @NonNull
    public String listField() {
        return "items";
    }

    @Override // com.amplifyframework.api.aws.GraphQLRequestOptions
    public int maxDepth() {
        return 1;
    }

    @Override // com.amplifyframework.api.aws.GraphQLRequestOptions
    @NonNull
    public List<String> paginationFields() {
        return Arrays.asList(NEXT_TOKEN_KEY, STARTED_AT_KEY);
    }

    @Override // com.amplifyframework.api.aws.GraphQLRequestOptions
    @NonNull
    public List<String> modelMetaFields() {
        return Arrays.asList(ModelWithMetadataAdapter.VERSION_KEY, ModelWithMetadataAdapter.DELETED_KEY, ModelWithMetadataAdapter.LAST_CHANGED_AT_KEY, ModelWithMetadataAdapter.TYPE_NAME);
    }

    @Override // com.amplifyframework.api.aws.GraphQLRequestOptions
    @NonNull
    public LeafSerializationBehavior leafSerializationBehavior() {
        return LeafSerializationBehavior.JUST_ID;
    }
}
