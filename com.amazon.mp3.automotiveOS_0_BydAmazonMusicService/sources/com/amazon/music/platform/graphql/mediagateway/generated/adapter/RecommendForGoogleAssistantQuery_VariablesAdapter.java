package com.amazon.music.platform.graphql.mediagateway.generated.adapter;

import com.amazon.music.platform.graphql.mediagateway.generated.RecommendForGoogleAssistantQuery;
import com.amazon.music.platform.graphql.mediagateway.generated.type.adapter.GoogleAssistantInput_InputAdapter;
import com.apollographql.apollo3.api.Adapter;
import com.apollographql.apollo3.api.Adapters;
import com.apollographql.apollo3.api.CustomScalarAdapters;
import com.apollographql.apollo3.api.json.JsonReader;
import com.apollographql.apollo3.api.json.JsonWriter;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RecommendForGoogleAssistantQuery_VariablesAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u0002H\u0016¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/platform/graphql/mediagateway/generated/adapter/RecommendForGoogleAssistantQuery_VariablesAdapter;", "Lcom/apollographql/apollo3/api/Adapter;", "Lcom/amazon/music/platform/graphql/mediagateway/generated/RecommendForGoogleAssistantQuery;", "()V", "fromJson", "reader", "Lcom/apollographql/apollo3/api/json/JsonReader;", "customScalarAdapters", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "toJson", "", "writer", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "value", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class RecommendForGoogleAssistantQuery_VariablesAdapter implements Adapter<RecommendForGoogleAssistantQuery> {
    public static final RecommendForGoogleAssistantQuery_VariablesAdapter INSTANCE = new RecommendForGoogleAssistantQuery_VariablesAdapter();

    private RecommendForGoogleAssistantQuery_VariablesAdapter() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.apollographql.apollo3.api.Adapter
    public RecommendForGoogleAssistantQuery fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) {
        Intrinsics.checkNotNullParameter(reader, "reader");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        throw new IllegalStateException("Input type used in output position");
    }

    @Override // com.apollographql.apollo3.api.Adapter
    public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, RecommendForGoogleAssistantQuery value) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        Intrinsics.checkNotNullParameter(value, "value");
        writer.name("googleAssistantInput");
        Adapters.m2611obj$default(GoogleAssistantInput_InputAdapter.INSTANCE, false, 1, null).toJson(writer, customScalarAdapters, value.getGoogleAssistantInput());
    }
}
