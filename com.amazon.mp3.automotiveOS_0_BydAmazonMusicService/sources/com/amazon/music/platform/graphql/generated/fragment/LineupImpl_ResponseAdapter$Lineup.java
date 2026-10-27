package com.amazon.music.platform.graphql.generated.fragment;

import androidx.core.app.NotificationCompat;
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

/* JADX INFO: compiled from: LineupImpl_ResponseAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u0018\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J \u0010\f\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0002H\u0016R\u001d\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"com/amazon/music/platform/graphql/generated/fragment/LineupImpl_ResponseAdapter$Lineup", "Lcom/apollographql/apollo3/api/Adapter;", "Lcom/amazon/music/platform/graphql/generated/fragment/Lineup;", "Lcom/apollographql/apollo3/api/json/JsonReader;", "reader", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "fromJson", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "writer", "value", "", "toJson", "", "", "RESPONSE_NAMES", "Ljava/util/List;", "getRESPONSE_NAMES", "()Ljava/util/List;", "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final class LineupImpl_ResponseAdapter$Lineup implements Adapter<Lineup> {
    public static final LineupImpl_ResponseAdapter$Lineup INSTANCE = new LineupImpl_ResponseAdapter$Lineup();
    private static final List<String> RESPONSE_NAMES = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"id", "scheduledStartDate", "scheduledEndDate", "actualStartDate", "actualEndDate", NotificationCompat.CATEGORY_STATUS, "endpoints", "artists"});

    private LineupImpl_ResponseAdapter$Lineup() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.apollographql.apollo3.api.Adapter
    public Lineup fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) throws IOException {
        Intrinsics.checkNotNullParameter(reader, "reader");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        String strFromJson = null;
        String strFromJson2 = null;
        String strFromJson3 = null;
        String strFromJson4 = null;
        String strFromJson5 = null;
        String strFromJson6 = null;
        List list = null;
        List list2 = null;
        while (true) {
            switch (reader.selectName(RESPONSE_NAMES)) {
                case 0:
                    strFromJson = Adapters.NullableStringAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 1:
                    strFromJson2 = Adapters.NullableStringAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 2:
                    strFromJson3 = Adapters.NullableStringAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 3:
                    strFromJson4 = Adapters.NullableStringAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 4:
                    strFromJson5 = Adapters.NullableStringAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 5:
                    strFromJson6 = Adapters.NullableStringAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 6:
                    list = (List) Adapters.m2609nullable(Adapters.m2608list(Adapters.m2609nullable(Adapters.m2611obj$default(LineupImpl_ResponseAdapter$Endpoint.INSTANCE, false, 1, null)))).fromJson(reader, customScalarAdapters);
                    break;
                case 7:
                    list2 = (List) Adapters.m2609nullable(Adapters.m2608list(Adapters.m2609nullable(Adapters.m2611obj$default(LineupImpl_ResponseAdapter$Artist.INSTANCE, false, 1, null)))).fromJson(reader, customScalarAdapters);
                    break;
                default:
                    return new Lineup(strFromJson, strFromJson2, strFromJson3, strFromJson4, strFromJson5, strFromJson6, list, list2);
            }
        }
    }

    @Override // com.apollographql.apollo3.api.Adapter
    public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, Lineup value) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        Intrinsics.checkNotNullParameter(value, "value");
        writer.name("id");
        NullableAdapter<String> nullableAdapter = Adapters.NullableStringAdapter;
        nullableAdapter.toJson(writer, customScalarAdapters, value.getId());
        writer.name("scheduledStartDate");
        nullableAdapter.toJson(writer, customScalarAdapters, value.getScheduledStartDate());
        writer.name("scheduledEndDate");
        nullableAdapter.toJson(writer, customScalarAdapters, value.getScheduledEndDate());
        writer.name("actualStartDate");
        nullableAdapter.toJson(writer, customScalarAdapters, value.getActualStartDate());
        writer.name("actualEndDate");
        nullableAdapter.toJson(writer, customScalarAdapters, value.getActualEndDate());
        writer.name(NotificationCompat.CATEGORY_STATUS);
        nullableAdapter.toJson(writer, customScalarAdapters, value.getStatus());
        writer.name("endpoints");
        Adapters.m2609nullable(Adapters.m2608list(Adapters.m2609nullable(Adapters.m2611obj$default(LineupImpl_ResponseAdapter$Endpoint.INSTANCE, false, 1, null)))).toJson(writer, customScalarAdapters, value.getEndpoints());
        writer.name("artists");
        Adapters.m2609nullable(Adapters.m2608list(Adapters.m2609nullable(Adapters.m2611obj$default(LineupImpl_ResponseAdapter$Artist.INSTANCE, false, 1, null)))).toJson(writer, customScalarAdapters, value.getArtists());
    }
}
