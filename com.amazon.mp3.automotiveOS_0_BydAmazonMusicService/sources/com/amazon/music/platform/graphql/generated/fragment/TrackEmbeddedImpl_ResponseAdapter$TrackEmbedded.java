package com.amazon.music.platform.graphql.generated.fragment;

import com.amazon.music.platform.graphql.generated.fragment.TrackEmbedded;
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

/* JADX INFO: compiled from: TrackEmbeddedImpl_ResponseAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u0018\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J \u0010\f\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0002H\u0016R\u001d\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"com/amazon/music/platform/graphql/generated/fragment/TrackEmbeddedImpl_ResponseAdapter$TrackEmbedded", "Lcom/apollographql/apollo3/api/Adapter;", "Lcom/amazon/music/platform/graphql/generated/fragment/TrackEmbedded;", "Lcom/apollographql/apollo3/api/json/JsonReader;", "reader", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "fromJson", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "writer", "value", "", "toJson", "", "", "RESPONSE_NAMES", "Ljava/util/List;", "getRESPONSE_NAMES", "()Ljava/util/List;", "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final class TrackEmbeddedImpl_ResponseAdapter$TrackEmbedded implements Adapter<TrackEmbedded> {
    public static final TrackEmbeddedImpl_ResponseAdapter$TrackEmbedded INSTANCE = new TrackEmbeddedImpl_ResponseAdapter$TrackEmbedded();
    private static final List<String> RESPONSE_NAMES = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"id", "title", "duration", "mediaType", "trackNumber", "languageOfPerformance", "eligibility", "parentalSettings", "contributingArtists", "images", "audioQualities"});

    private TrackEmbeddedImpl_ResponseAdapter$TrackEmbedded() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.apollographql.apollo3.api.Adapter
    public TrackEmbedded fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) throws IOException {
        Intrinsics.checkNotNullParameter(reader, "reader");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        String strFromJson = null;
        String strFromJson2 = null;
        Double dFromJson = null;
        String strFromJson3 = null;
        Double dFromJson2 = null;
        String strFromJson4 = null;
        TrackEmbedded.Eligibility eligibility = null;
        TrackEmbedded.ParentalSettings parentalSettings = null;
        TrackEmbedded.ContributingArtists contributingArtists = null;
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
                    dFromJson = Adapters.NullableDoubleAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 3:
                    strFromJson3 = Adapters.NullableStringAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 4:
                    dFromJson2 = Adapters.NullableDoubleAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 5:
                    strFromJson4 = Adapters.NullableStringAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 6:
                    eligibility = (TrackEmbedded.Eligibility) Adapters.m2609nullable(Adapters.m2610obj(TrackEmbeddedImpl_ResponseAdapter$Eligibility.INSTANCE, true)).fromJson(reader, customScalarAdapters);
                    break;
                case 7:
                    parentalSettings = (TrackEmbedded.ParentalSettings) Adapters.m2609nullable(Adapters.m2610obj(TrackEmbeddedImpl_ResponseAdapter$ParentalSettings.INSTANCE, true)).fromJson(reader, customScalarAdapters);
                    break;
                case 8:
                    contributingArtists = (TrackEmbedded.ContributingArtists) Adapters.m2609nullable(Adapters.m2610obj(TrackEmbeddedImpl_ResponseAdapter$ContributingArtists.INSTANCE, true)).fromJson(reader, customScalarAdapters);
                    break;
                case 9:
                    list = (List) Adapters.m2609nullable(Adapters.m2608list(Adapters.m2609nullable(Adapters.m2610obj(TrackEmbeddedImpl_ResponseAdapter$Image.INSTANCE, true)))).fromJson(reader, customScalarAdapters);
                    break;
                case 10:
                    list2 = (List) Adapters.m2609nullable(Adapters.m2608list(Adapters.NullableStringAdapter)).fromJson(reader, customScalarAdapters);
                    break;
                default:
                    return new TrackEmbedded(strFromJson, strFromJson2, dFromJson, strFromJson3, dFromJson2, strFromJson4, eligibility, parentalSettings, contributingArtists, list, list2);
            }
        }
    }

    @Override // com.apollographql.apollo3.api.Adapter
    public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, TrackEmbedded value) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        Intrinsics.checkNotNullParameter(value, "value");
        writer.name("id");
        NullableAdapter<String> nullableAdapter = Adapters.NullableStringAdapter;
        nullableAdapter.toJson(writer, customScalarAdapters, value.getId());
        writer.name("title");
        nullableAdapter.toJson(writer, customScalarAdapters, value.getTitle());
        writer.name("duration");
        NullableAdapter<Double> nullableAdapter2 = Adapters.NullableDoubleAdapter;
        nullableAdapter2.toJson(writer, customScalarAdapters, value.getDuration());
        writer.name("mediaType");
        nullableAdapter.toJson(writer, customScalarAdapters, value.getMediaType());
        writer.name("trackNumber");
        nullableAdapter2.toJson(writer, customScalarAdapters, value.getTrackNumber());
        writer.name("languageOfPerformance");
        nullableAdapter.toJson(writer, customScalarAdapters, value.getLanguageOfPerformance());
        writer.name("eligibility");
        Adapters.m2609nullable(Adapters.m2610obj(TrackEmbeddedImpl_ResponseAdapter$Eligibility.INSTANCE, true)).toJson(writer, customScalarAdapters, value.getEligibility());
        writer.name("parentalSettings");
        Adapters.m2609nullable(Adapters.m2610obj(TrackEmbeddedImpl_ResponseAdapter$ParentalSettings.INSTANCE, true)).toJson(writer, customScalarAdapters, value.getParentalSettings());
        writer.name("contributingArtists");
        Adapters.m2609nullable(Adapters.m2610obj(TrackEmbeddedImpl_ResponseAdapter$ContributingArtists.INSTANCE, true)).toJson(writer, customScalarAdapters, value.getContributingArtists());
        writer.name("images");
        Adapters.m2609nullable(Adapters.m2608list(Adapters.m2609nullable(Adapters.m2610obj(TrackEmbeddedImpl_ResponseAdapter$Image.INSTANCE, true)))).toJson(writer, customScalarAdapters, value.getImages());
        writer.name("audioQualities");
        Adapters.m2609nullable(Adapters.m2608list(nullableAdapter)).toJson(writer, customScalarAdapters, value.getAudioQualities());
    }
}
