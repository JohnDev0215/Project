package com.amazon.music.platform.graphql.generated.fragment;

import com.amazon.music.platform.graphql.generated.fragment.Track;
import com.apollographql.apollo3.api.Adapter;
import com.apollographql.apollo3.api.Adapters;
import com.apollographql.apollo3.api.CustomScalarAdapters;
import com.apollographql.apollo3.api.NullableAdapter;
import com.apollographql.apollo3.api.json.JsonReader;
import com.apollographql.apollo3.api.json.JsonWriter;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TrackImpl_ResponseAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u0018\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J \u0010\f\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0002H\u0016R\u001d\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"com/amazon/music/platform/graphql/generated/fragment/TrackImpl_ResponseAdapter$Track", "Lcom/apollographql/apollo3/api/Adapter;", "Lcom/amazon/music/platform/graphql/generated/fragment/Track;", "Lcom/apollographql/apollo3/api/json/JsonReader;", "reader", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "fromJson", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "writer", "value", "", "toJson", "", "", "RESPONSE_NAMES", "Ljava/util/List;", "getRESPONSE_NAMES", "()Ljava/util/List;", "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final class TrackImpl_ResponseAdapter$Track implements Adapter<Track> {
    public static final TrackImpl_ResponseAdapter$Track INSTANCE = new TrackImpl_ResponseAdapter$Track();
    private static final List<String> RESPONSE_NAMES = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"id", "title", ImagesContract.URL, "artists", "contributingArtists", "album", "duration", "eligibility", "parentalSettings", "releaseDate", "images", "mediaType", "trackNumber", "languageOfPerformance", "audioQualities"});

    private TrackImpl_ResponseAdapter$Track() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.apollographql.apollo3.api.Adapter
    public Track fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) throws IOException {
        Intrinsics.checkNotNullParameter(reader, "reader");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        String strFromJson = null;
        String strFromJson2 = null;
        String strFromJson3 = null;
        List list = null;
        Track.ContributingArtists contributingArtists = null;
        Track.Album album = null;
        Double dFromJson = null;
        Track.Eligibility eligibility = null;
        Track.ParentalSettings parentalSettings = null;
        String strFromJson4 = null;
        List list2 = null;
        String strFromJson5 = null;
        Double dFromJson2 = null;
        String strFromJson6 = null;
        List list3 = null;
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
                    list = (List) Adapters.m2609nullable(Adapters.m2608list(Adapters.m2609nullable(Adapters.m2610obj(TrackImpl_ResponseAdapter$Artist.INSTANCE, true)))).fromJson(reader, customScalarAdapters);
                    break;
                case 4:
                    contributingArtists = (Track.ContributingArtists) Adapters.m2609nullable(Adapters.m2610obj(TrackImpl_ResponseAdapter$ContributingArtists.INSTANCE, true)).fromJson(reader, customScalarAdapters);
                    break;
                case 5:
                    album = (Track.Album) Adapters.m2609nullable(Adapters.m2610obj(TrackImpl_ResponseAdapter$Album.INSTANCE, true)).fromJson(reader, customScalarAdapters);
                    break;
                case 6:
                    dFromJson = Adapters.NullableDoubleAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 7:
                    eligibility = (Track.Eligibility) Adapters.m2609nullable(Adapters.m2610obj(TrackImpl_ResponseAdapter$Eligibility.INSTANCE, true)).fromJson(reader, customScalarAdapters);
                    break;
                case 8:
                    parentalSettings = (Track.ParentalSettings) Adapters.m2609nullable(Adapters.m2610obj(TrackImpl_ResponseAdapter$ParentalSettings.INSTANCE, true)).fromJson(reader, customScalarAdapters);
                    break;
                case 9:
                    strFromJson4 = Adapters.NullableStringAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 10:
                    list2 = (List) Adapters.m2609nullable(Adapters.m2608list(Adapters.m2609nullable(Adapters.m2610obj(TrackImpl_ResponseAdapter$Image.INSTANCE, true)))).fromJson(reader, customScalarAdapters);
                    break;
                case 11:
                    strFromJson5 = Adapters.NullableStringAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 12:
                    dFromJson2 = Adapters.NullableDoubleAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 13:
                    strFromJson6 = Adapters.NullableStringAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 14:
                    list3 = (List) Adapters.m2609nullable(Adapters.m2608list(Adapters.NullableStringAdapter)).fromJson(reader, customScalarAdapters);
                    break;
                default:
                    return new Track(strFromJson, strFromJson2, strFromJson3, list, contributingArtists, album, dFromJson, eligibility, parentalSettings, strFromJson4, list2, strFromJson5, dFromJson2, strFromJson6, list3);
            }
        }
    }

    @Override // com.apollographql.apollo3.api.Adapter
    public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, Track value) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        Intrinsics.checkNotNullParameter(value, "value");
        writer.name("id");
        NullableAdapter<String> nullableAdapter = Adapters.NullableStringAdapter;
        nullableAdapter.toJson(writer, customScalarAdapters, value.getId());
        writer.name("title");
        nullableAdapter.toJson(writer, customScalarAdapters, value.getTitle());
        writer.name(ImagesContract.URL);
        nullableAdapter.toJson(writer, customScalarAdapters, value.getUrl());
        writer.name("artists");
        Adapters.m2609nullable(Adapters.m2608list(Adapters.m2609nullable(Adapters.m2610obj(TrackImpl_ResponseAdapter$Artist.INSTANCE, true)))).toJson(writer, customScalarAdapters, value.getArtists());
        writer.name("contributingArtists");
        Adapters.m2609nullable(Adapters.m2610obj(TrackImpl_ResponseAdapter$ContributingArtists.INSTANCE, true)).toJson(writer, customScalarAdapters, value.getContributingArtists());
        writer.name("album");
        Adapters.m2609nullable(Adapters.m2610obj(TrackImpl_ResponseAdapter$Album.INSTANCE, true)).toJson(writer, customScalarAdapters, value.getAlbum());
        writer.name("duration");
        NullableAdapter<Double> nullableAdapter2 = Adapters.NullableDoubleAdapter;
        nullableAdapter2.toJson(writer, customScalarAdapters, value.getDuration());
        writer.name("eligibility");
        Adapters.m2609nullable(Adapters.m2610obj(TrackImpl_ResponseAdapter$Eligibility.INSTANCE, true)).toJson(writer, customScalarAdapters, value.getEligibility());
        writer.name("parentalSettings");
        Adapters.m2609nullable(Adapters.m2610obj(TrackImpl_ResponseAdapter$ParentalSettings.INSTANCE, true)).toJson(writer, customScalarAdapters, value.getParentalSettings());
        writer.name("releaseDate");
        nullableAdapter.toJson(writer, customScalarAdapters, value.getReleaseDate());
        writer.name("images");
        Adapters.m2609nullable(Adapters.m2608list(Adapters.m2609nullable(Adapters.m2610obj(TrackImpl_ResponseAdapter$Image.INSTANCE, true)))).toJson(writer, customScalarAdapters, value.getImages());
        writer.name("mediaType");
        nullableAdapter.toJson(writer, customScalarAdapters, value.getMediaType());
        writer.name("trackNumber");
        nullableAdapter2.toJson(writer, customScalarAdapters, value.getTrackNumber());
        writer.name("languageOfPerformance");
        nullableAdapter.toJson(writer, customScalarAdapters, value.getLanguageOfPerformance());
        writer.name("audioQualities");
        Adapters.m2609nullable(Adapters.m2608list(nullableAdapter)).toJson(writer, customScalarAdapters, value.getAudioQualities());
    }
}
