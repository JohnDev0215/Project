package com.amplifyframework.api.aws;

import androidx.annotation.NonNull;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public interface GraphQLRequestOptions {
    @NonNull
    LeafSerializationBehavior leafSerializationBehavior();

    @NonNull
    String listField();

    int maxDepth();

    @NonNull
    List<String> modelMetaFields();

    @NonNull
    List<String> paginationFields();
}
