package com.amazon.music.platform.graphql.generated.fragment;

import androidx.core.app.NotificationCompat;
import com.apollographql.apollo3.api.Adapter;
import com.apollographql.apollo3.api.Adapters;
import com.apollographql.apollo3.api.CustomScalarAdapters;
import com.apollographql.apollo3.api.NullableAdapter;
import com.apollographql.apollo3.api.json.JsonReader;
import com.apollographql.apollo3.api.json.JsonWriter;
import com.google.android.gms.cast.MediaTrack;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LiveEventImpl_ResponseAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u0018\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J \u0010\f\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0002H\u0016R\u001d\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"com/amazon/music/platform/graphql/generated/fragment/LiveEventImpl_ResponseAdapter$LiveEvent", "Lcom/apollographql/apollo3/api/Adapter;", "Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent;", "Lcom/apollographql/apollo3/api/json/JsonReader;", "reader", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "fromJson", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "writer", "value", "", "toJson", "", "", "RESPONSE_NAMES", "Ljava/util/List;", "getRESPONSE_NAMES", "()Ljava/util/List;", "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final class LiveEventImpl_ResponseAdapter$LiveEvent implements Adapter<LiveEvent> {
    public static final LiveEventImpl_ResponseAdapter$LiveEvent INSTANCE = new LiveEventImpl_ResponseAdapter$LiveEvent();
    private static final List<String> RESPONSE_NAMES = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"id", "name", "location", "venue", "presentedBy", MediaTrack.ROLE_DESCRIPTION, "startDate", "endDate", NotificationCompat.CATEGORY_STATUS, "lineupGroups", "lineupGroupNameType", "images", "albums", "merch", "playlists"});

    private LiveEventImpl_ResponseAdapter$LiveEvent() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.apollographql.apollo3.api.Adapter
    public LiveEvent fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) throws IOException {
        List list;
        Intrinsics.checkNotNullParameter(reader, "reader");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        String strFromJson = null;
        String strFromJson2 = null;
        String strFromJson3 = null;
        String strFromJson4 = null;
        String strFromJson5 = null;
        String strFromJson6 = null;
        String strFromJson7 = null;
        String strFromJson8 = null;
        String strFromJson9 = null;
        List list2 = null;
        String strFromJson10 = null;
        List list3 = null;
        List list4 = null;
        List list5 = null;
        List list6 = null;
        while (true) {
            switch (reader.selectName(RESPONSE_NAMES)) {
                case 0:
                    list = list3;
                    strFromJson = Adapters.NullableStringAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 1:
                    list = list3;
                    strFromJson2 = Adapters.NullableStringAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 2:
                    list = list3;
                    strFromJson3 = Adapters.NullableStringAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 3:
                    list = list3;
                    strFromJson4 = Adapters.NullableStringAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 4:
                    list = list3;
                    strFromJson5 = Adapters.NullableStringAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 5:
                    list = list3;
                    strFromJson6 = Adapters.NullableStringAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 6:
                    list = list3;
                    strFromJson7 = Adapters.NullableStringAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 7:
                    list = list3;
                    strFromJson8 = Adapters.NullableStringAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 8:
                    list = list3;
                    strFromJson9 = Adapters.NullableStringAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 9:
                    list = list3;
                    list2 = (List) Adapters.m2609nullable(Adapters.m2608list(Adapters.m2609nullable(Adapters.m2611obj$default(LiveEventImpl_ResponseAdapter$LineupGroup.INSTANCE, false, 1, null)))).fromJson(reader, customScalarAdapters);
                    break;
                case 10:
                    strFromJson10 = Adapters.NullableStringAdapter.fromJson(reader, customScalarAdapters);
                    continue;
                case 11:
                    list3 = (List) Adapters.m2609nullable(Adapters.m2608list(Adapters.m2609nullable(Adapters.m2610obj(LiveEventImpl_ResponseAdapter$Image.INSTANCE, true)))).fromJson(reader, customScalarAdapters);
                    continue;
                case 12:
                    list4 = (List) Adapters.m2609nullable(Adapters.m2608list(Adapters.m2609nullable(Adapters.m2610obj(LiveEventImpl_ResponseAdapter$Album.INSTANCE, true)))).fromJson(reader, customScalarAdapters);
                    continue;
                case 13:
                    list5 = (List) Adapters.m2609nullable(Adapters.m2608list(Adapters.m2609nullable(Adapters.m2610obj(LiveEventImpl_ResponseAdapter$Merch.INSTANCE, true)))).fromJson(reader, customScalarAdapters);
                    continue;
                case 14:
                    list6 = (List) Adapters.m2609nullable(Adapters.m2608list(Adapters.m2609nullable(Adapters.m2610obj(LiveEventImpl_ResponseAdapter$Playlist.INSTANCE, true)))).fromJson(reader, customScalarAdapters);
                    continue;
                default:
                    return new LiveEvent(strFromJson, strFromJson2, strFromJson3, strFromJson4, strFromJson5, strFromJson6, strFromJson7, strFromJson8, strFromJson9, list2, strFromJson10, list3, list4, list5, list6);
            }
            list3 = list;
        }
    }

    @Override // com.apollographql.apollo3.api.Adapter
    public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, LiveEvent value) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        Intrinsics.checkNotNullParameter(value, "value");
        writer.name("id");
        NullableAdapter<String> nullableAdapter = Adapters.NullableStringAdapter;
        nullableAdapter.toJson(writer, customScalarAdapters, value.getId());
        writer.name("name");
        nullableAdapter.toJson(writer, customScalarAdapters, value.getName());
        writer.name("location");
        nullableAdapter.toJson(writer, customScalarAdapters, value.getLocation());
        writer.name("venue");
        nullableAdapter.toJson(writer, customScalarAdapters, value.getVenue());
        writer.name("presentedBy");
        nullableAdapter.toJson(writer, customScalarAdapters, value.getPresentedBy());
        writer.name(MediaTrack.ROLE_DESCRIPTION);
        nullableAdapter.toJson(writer, customScalarAdapters, value.getDescription());
        writer.name("startDate");
        nullableAdapter.toJson(writer, customScalarAdapters, value.getStartDate());
        writer.name("endDate");
        nullableAdapter.toJson(writer, customScalarAdapters, value.getEndDate());
        writer.name(NotificationCompat.CATEGORY_STATUS);
        nullableAdapter.toJson(writer, customScalarAdapters, value.getStatus());
        writer.name("lineupGroups");
        Adapters.m2609nullable(Adapters.m2608list(Adapters.m2609nullable(Adapters.m2611obj$default(LiveEventImpl_ResponseAdapter$LineupGroup.INSTANCE, false, 1, null)))).toJson(writer, customScalarAdapters, value.getLineupGroups());
        writer.name("lineupGroupNameType");
        nullableAdapter.toJson(writer, customScalarAdapters, value.getLineupGroupNameType());
        writer.name("images");
        Adapters.m2609nullable(Adapters.m2608list(Adapters.m2609nullable(Adapters.m2610obj(LiveEventImpl_ResponseAdapter$Image.INSTANCE, true)))).toJson(writer, customScalarAdapters, value.getImages());
        writer.name("albums");
        Adapters.m2609nullable(Adapters.m2608list(Adapters.m2609nullable(Adapters.m2610obj(LiveEventImpl_ResponseAdapter$Album.INSTANCE, true)))).toJson(writer, customScalarAdapters, value.getAlbums());
        writer.name("merch");
        Adapters.m2609nullable(Adapters.m2608list(Adapters.m2609nullable(Adapters.m2610obj(LiveEventImpl_ResponseAdapter$Merch.INSTANCE, true)))).toJson(writer, customScalarAdapters, value.getMerch());
        writer.name("playlists");
        Adapters.m2609nullable(Adapters.m2608list(Adapters.m2609nullable(Adapters.m2610obj(LiveEventImpl_ResponseAdapter$Playlist.INSTANCE, true)))).toJson(writer, customScalarAdapters, value.getPlaylists());
    }
}
