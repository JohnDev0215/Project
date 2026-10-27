package com.amazon.music.platform.graphql.mediagateway.generated.adapter;

import com.amazon.music.platform.graphql.mediagateway.generated.RecommendForGoogleAssistantQuery;
import com.amplifyframework.datastore.appsync.ModelWithMetadataAdapter;
import com.apollographql.apollo3.api.Adapter;
import com.apollographql.apollo3.api.Adapters;
import com.apollographql.apollo3.api.BooleanExpressions;
import com.apollographql.apollo3.api.CustomScalarAdapters;
import com.apollographql.apollo3.api.json.JsonReader;
import com.apollographql.apollo3.api.json.JsonWriter;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RecommendForGoogleAssistantQuery_ResponseAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u0018\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J \u0010\f\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0002H\u0016R\u001d\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"com/amazon/music/platform/graphql/mediagateway/generated/adapter/RecommendForGoogleAssistantQuery_ResponseAdapter$MediaContent", "Lcom/apollographql/apollo3/api/Adapter;", "Lcom/amazon/music/platform/graphql/mediagateway/generated/RecommendForGoogleAssistantQuery$MediaContent;", "Lcom/apollographql/apollo3/api/json/JsonReader;", "reader", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "fromJson", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "writer", "value", "", "toJson", "", "", "RESPONSE_NAMES", "Ljava/util/List;", "getRESPONSE_NAMES", "()Ljava/util/List;", "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final class RecommendForGoogleAssistantQuery_ResponseAdapter$MediaContent implements Adapter<RecommendForGoogleAssistantQuery.MediaContent> {
    public static final RecommendForGoogleAssistantQuery_ResponseAdapter$MediaContent INSTANCE = new RecommendForGoogleAssistantQuery_ResponseAdapter$MediaContent();
    private static final List<String> RESPONSE_NAMES = CollectionsKt__CollectionsJVMKt.listOf(ModelWithMetadataAdapter.TYPE_NAME);

    private RecommendForGoogleAssistantQuery_ResponseAdapter$MediaContent() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.apollographql.apollo3.api.Adapter
    public RecommendForGoogleAssistantQuery.MediaContent fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) throws IOException {
        Intrinsics.checkNotNullParameter(reader, "reader");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        String strFromJson = null;
        while (reader.selectName(RESPONSE_NAMES) == 0) {
            strFromJson = Adapters.StringAdapter.fromJson(reader, customScalarAdapters);
        }
        if (!(strFromJson != null)) {
            throw new IllegalStateException("__typename was not found".toString());
        }
        reader.rewind();
        RecommendForGoogleAssistantQuery.OnMediaItem onMediaItemFromJson = BooleanExpressions.evaluate(BooleanExpressions.possibleTypes("MediaItem"), customScalarAdapters.variables(), strFromJson) ? RecommendForGoogleAssistantQuery_ResponseAdapter$OnMediaItem.INSTANCE.fromJson(reader, customScalarAdapters) : null;
        reader.rewind();
        return new RecommendForGoogleAssistantQuery.MediaContent(strFromJson, onMediaItemFromJson, BooleanExpressions.evaluate(BooleanExpressions.possibleTypes("MediaContainer"), customScalarAdapters.variables(), strFromJson) ? RecommendForGoogleAssistantQuery_ResponseAdapter$OnMediaContainer.INSTANCE.fromJson(reader, customScalarAdapters) : null);
    }

    @Override // com.apollographql.apollo3.api.Adapter
    public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, RecommendForGoogleAssistantQuery.MediaContent value) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        Intrinsics.checkNotNullParameter(value, "value");
        writer.name(ModelWithMetadataAdapter.TYPE_NAME);
        Adapters.StringAdapter.toJson(writer, customScalarAdapters, value.get__typename());
        if (value.getOnMediaItem() != null) {
            RecommendForGoogleAssistantQuery_ResponseAdapter$OnMediaItem.INSTANCE.toJson(writer, customScalarAdapters, value.getOnMediaItem());
        }
        if (value.getOnMediaContainer() != null) {
            RecommendForGoogleAssistantQuery_ResponseAdapter$OnMediaContainer.INSTANCE.toJson(writer, customScalarAdapters, value.getOnMediaContainer());
        }
    }
}
