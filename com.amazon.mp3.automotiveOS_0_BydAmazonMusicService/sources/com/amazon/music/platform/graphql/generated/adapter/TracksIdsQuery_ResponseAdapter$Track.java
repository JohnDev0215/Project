package com.amazon.music.platform.graphql.generated.adapter;

import com.amazon.music.platform.graphql.generated.TracksIdsQuery;
import com.apollographql.apollo3.api.Adapter;
import com.apollographql.apollo3.api.Adapters;
import com.apollographql.apollo3.api.CustomScalarAdapters;
import com.apollographql.apollo3.api.json.JsonReader;
import com.apollographql.apollo3.api.json.JsonWriter;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TracksIdsQuery_ResponseAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u0018\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J \u0010\f\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0002H\u0016R\u001d\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"com/amazon/music/platform/graphql/generated/adapter/TracksIdsQuery_ResponseAdapter$Track", "Lcom/apollographql/apollo3/api/Adapter;", "Lcom/amazon/music/platform/graphql/generated/TracksIdsQuery$Track;", "Lcom/apollographql/apollo3/api/json/JsonReader;", "reader", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "fromJson", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "writer", "value", "", "toJson", "", "", "RESPONSE_NAMES", "Ljava/util/List;", "getRESPONSE_NAMES", "()Ljava/util/List;", "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final class TracksIdsQuery_ResponseAdapter$Track implements Adapter<TracksIdsQuery.Track> {
    public static final TracksIdsQuery_ResponseAdapter$Track INSTANCE = new TracksIdsQuery_ResponseAdapter$Track();
    private static final List<String> RESPONSE_NAMES = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"id", "album"});

    private TracksIdsQuery_ResponseAdapter$Track() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.apollographql.apollo3.api.Adapter
    public TracksIdsQuery.Track fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) throws IOException {
        Intrinsics.checkNotNullParameter(reader, "reader");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        String strFromJson = null;
        TracksIdsQuery.Album album = null;
        while (true) {
            int iSelectName = reader.selectName(RESPONSE_NAMES);
            if (iSelectName == 0) {
                strFromJson = Adapters.NullableStringAdapter.fromJson(reader, customScalarAdapters);
            } else if (iSelectName == 1) {
                album = (TracksIdsQuery.Album) Adapters.m2609nullable(Adapters.m2611obj$default(TracksIdsQuery_ResponseAdapter$Album.INSTANCE, false, 1, null)).fromJson(reader, customScalarAdapters);
            } else {
                return new TracksIdsQuery.Track(strFromJson, album);
            }
        }
    }

    @Override // com.apollographql.apollo3.api.Adapter
    public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, TracksIdsQuery.Track value) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        Intrinsics.checkNotNullParameter(value, "value");
        writer.name("id");
        Adapters.NullableStringAdapter.toJson(writer, customScalarAdapters, value.getId());
        writer.name("album");
        Adapters.m2609nullable(Adapters.m2611obj$default(TracksIdsQuery_ResponseAdapter$Album.INSTANCE, false, 1, null)).toJson(writer, customScalarAdapters, value.getAlbum());
    }
}
