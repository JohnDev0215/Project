package com.amazon.music.platform.graphql.generated.fragment;

import com.amazon.music.platform.graphql.generated.fragment.SocialProfilesUserEmbedded;
import com.apollographql.apollo3.api.Adapter;
import com.apollographql.apollo3.api.Adapters;
import com.apollographql.apollo3.api.CustomScalarAdapters;
import com.apollographql.apollo3.api.NullableAdapter;
import com.apollographql.apollo3.api.json.JsonReader;
import com.apollographql.apollo3.api.json.JsonWriter;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SocialProfilesUserEmbeddedImpl_ResponseAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u0018\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J \u0010\f\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0002H\u0016R\u001d\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"com/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbeddedImpl_ResponseAdapter$Playlists", "Lcom/apollographql/apollo3/api/Adapter;", "Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded$Playlists;", "Lcom/apollographql/apollo3/api/json/JsonReader;", "reader", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "fromJson", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "writer", "value", "", "toJson", "", "", "RESPONSE_NAMES", "Ljava/util/List;", "getRESPONSE_NAMES", "()Ljava/util/List;", "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final class SocialProfilesUserEmbeddedImpl_ResponseAdapter$Playlists implements Adapter<SocialProfilesUserEmbedded.Playlists> {
    public static final SocialProfilesUserEmbeddedImpl_ResponseAdapter$Playlists INSTANCE = new SocialProfilesUserEmbeddedImpl_ResponseAdapter$Playlists();
    private static final List<String> RESPONSE_NAMES = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"edges", "totalCount", "edgeCount", "pageInfo"});

    private SocialProfilesUserEmbeddedImpl_ResponseAdapter$Playlists() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.apollographql.apollo3.api.Adapter
    public SocialProfilesUserEmbedded.Playlists fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) throws IOException {
        Intrinsics.checkNotNullParameter(reader, "reader");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        List list = null;
        Double dFromJson = null;
        Double dFromJson2 = null;
        SocialProfilesUserEmbedded.PageInfo pageInfo = null;
        while (true) {
            int iSelectName = reader.selectName(RESPONSE_NAMES);
            if (iSelectName == 0) {
                list = (List) Adapters.m2609nullable(Adapters.m2608list(Adapters.m2609nullable(Adapters.m2611obj$default(SocialProfilesUserEmbeddedImpl_ResponseAdapter$Edge.INSTANCE, false, 1, null)))).fromJson(reader, customScalarAdapters);
            } else if (iSelectName == 1) {
                dFromJson = Adapters.NullableDoubleAdapter.fromJson(reader, customScalarAdapters);
            } else if (iSelectName == 2) {
                dFromJson2 = Adapters.NullableDoubleAdapter.fromJson(reader, customScalarAdapters);
            } else if (iSelectName == 3) {
                pageInfo = (SocialProfilesUserEmbedded.PageInfo) Adapters.m2609nullable(Adapters.m2611obj$default(SocialProfilesUserEmbeddedImpl_ResponseAdapter$PageInfo.INSTANCE, false, 1, null)).fromJson(reader, customScalarAdapters);
            } else {
                return new SocialProfilesUserEmbedded.Playlists(list, dFromJson, dFromJson2, pageInfo);
            }
        }
    }

    @Override // com.apollographql.apollo3.api.Adapter
    public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, SocialProfilesUserEmbedded.Playlists value) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        Intrinsics.checkNotNullParameter(value, "value");
        writer.name("edges");
        Adapters.m2609nullable(Adapters.m2608list(Adapters.m2609nullable(Adapters.m2611obj$default(SocialProfilesUserEmbeddedImpl_ResponseAdapter$Edge.INSTANCE, false, 1, null)))).toJson(writer, customScalarAdapters, value.getEdges());
        writer.name("totalCount");
        NullableAdapter<Double> nullableAdapter = Adapters.NullableDoubleAdapter;
        nullableAdapter.toJson(writer, customScalarAdapters, value.getTotalCount());
        writer.name("edgeCount");
        nullableAdapter.toJson(writer, customScalarAdapters, value.getEdgeCount());
        writer.name("pageInfo");
        Adapters.m2609nullable(Adapters.m2611obj$default(SocialProfilesUserEmbeddedImpl_ResponseAdapter$PageInfo.INSTANCE, false, 1, null)).toJson(writer, customScalarAdapters, value.getPageInfo());
    }
}
