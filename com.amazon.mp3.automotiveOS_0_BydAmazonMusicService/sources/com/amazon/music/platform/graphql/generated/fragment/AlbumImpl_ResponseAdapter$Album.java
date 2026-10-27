package com.amazon.music.platform.graphql.generated.fragment;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.platform.graphql.generated.fragment.Album;
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

/* JADX INFO: compiled from: AlbumImpl_ResponseAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u0018\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J \u0010\f\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0002H\u0016R\u001d\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"com/amazon/music/platform/graphql/generated/fragment/AlbumImpl_ResponseAdapter$Album", "Lcom/apollographql/apollo3/api/Adapter;", "Lcom/amazon/music/platform/graphql/generated/fragment/Album;", "Lcom/apollographql/apollo3/api/json/JsonReader;", "reader", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "fromJson", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "writer", "value", "", "toJson", "", "", "RESPONSE_NAMES", "Ljava/util/List;", "getRESPONSE_NAMES", "()Ljava/util/List;", "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final class AlbumImpl_ResponseAdapter$Album implements Adapter<Album> {
    public static final AlbumImpl_ResponseAdapter$Album INSTANCE = new AlbumImpl_ResponseAdapter$Album();
    private static final List<String> RESPONSE_NAMES = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"id", "title", ImagesContract.URL, "releaseDate", "eligibility", "images", "artists", "contributingArtists", "duration", ContextMappingConstants.LABEL, "parentalSettings", "albumTracks", "trackCount", "copyright", "languageOfPerformance", "audioQualities"});

    private AlbumImpl_ResponseAdapter$Album() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.apollographql.apollo3.api.Adapter
    public Album fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) throws IOException {
        Intrinsics.checkNotNullParameter(reader, "reader");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        String strFromJson = null;
        String strFromJson2 = null;
        String strFromJson3 = null;
        String strFromJson4 = null;
        Album.Eligibility eligibility = null;
        List list = null;
        List list2 = null;
        Album.ContributingArtists contributingArtists = null;
        Double dFromJson = null;
        String strFromJson5 = null;
        Album.ParentalSettings parentalSettings = null;
        List list3 = null;
        Double dFromJson2 = null;
        String strFromJson6 = null;
        String strFromJson7 = null;
        List list4 = null;
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
                    eligibility = (Album.Eligibility) Adapters.m2609nullable(Adapters.m2610obj(AlbumImpl_ResponseAdapter$Eligibility.INSTANCE, true)).fromJson(reader, customScalarAdapters);
                    break;
                case 5:
                    list = (List) Adapters.m2609nullable(Adapters.m2608list(Adapters.m2609nullable(Adapters.m2610obj(AlbumImpl_ResponseAdapter$Image.INSTANCE, true)))).fromJson(reader, customScalarAdapters);
                    break;
                case 6:
                    list2 = (List) Adapters.m2609nullable(Adapters.m2608list(Adapters.m2609nullable(Adapters.m2610obj(AlbumImpl_ResponseAdapter$Artist.INSTANCE, true)))).fromJson(reader, customScalarAdapters);
                    break;
                case 7:
                    contributingArtists = (Album.ContributingArtists) Adapters.m2609nullable(Adapters.m2610obj(AlbumImpl_ResponseAdapter$ContributingArtists.INSTANCE, true)).fromJson(reader, customScalarAdapters);
                    break;
                case 8:
                    dFromJson = Adapters.NullableDoubleAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 9:
                    strFromJson5 = Adapters.NullableStringAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 10:
                    parentalSettings = (Album.ParentalSettings) Adapters.m2609nullable(Adapters.m2610obj(AlbumImpl_ResponseAdapter$ParentalSettings.INSTANCE, true)).fromJson(reader, customScalarAdapters);
                    break;
                case 11:
                    list3 = (List) Adapters.m2609nullable(Adapters.m2608list(Adapters.m2609nullable(Adapters.m2610obj(AlbumImpl_ResponseAdapter$AlbumTrack.INSTANCE, true)))).fromJson(reader, customScalarAdapters);
                    break;
                case 12:
                    dFromJson2 = Adapters.NullableDoubleAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 13:
                    strFromJson6 = Adapters.NullableStringAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 14:
                    strFromJson7 = Adapters.NullableStringAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 15:
                    list4 = (List) Adapters.m2609nullable(Adapters.m2608list(Adapters.NullableStringAdapter)).fromJson(reader, customScalarAdapters);
                    break;
                default:
                    return new Album(strFromJson, strFromJson2, strFromJson3, strFromJson4, eligibility, list, list2, contributingArtists, dFromJson, strFromJson5, parentalSettings, list3, dFromJson2, strFromJson6, strFromJson7, list4);
            }
        }
    }

    @Override // com.apollographql.apollo3.api.Adapter
    public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, Album value) throws IOException {
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
        writer.name("releaseDate");
        nullableAdapter.toJson(writer, customScalarAdapters, value.getReleaseDate());
        writer.name("eligibility");
        Adapters.m2609nullable(Adapters.m2610obj(AlbumImpl_ResponseAdapter$Eligibility.INSTANCE, true)).toJson(writer, customScalarAdapters, value.getEligibility());
        writer.name("images");
        Adapters.m2609nullable(Adapters.m2608list(Adapters.m2609nullable(Adapters.m2610obj(AlbumImpl_ResponseAdapter$Image.INSTANCE, true)))).toJson(writer, customScalarAdapters, value.getImages());
        writer.name("artists");
        Adapters.m2609nullable(Adapters.m2608list(Adapters.m2609nullable(Adapters.m2610obj(AlbumImpl_ResponseAdapter$Artist.INSTANCE, true)))).toJson(writer, customScalarAdapters, value.getArtists());
        writer.name("contributingArtists");
        Adapters.m2609nullable(Adapters.m2610obj(AlbumImpl_ResponseAdapter$ContributingArtists.INSTANCE, true)).toJson(writer, customScalarAdapters, value.getContributingArtists());
        writer.name("duration");
        NullableAdapter<Double> nullableAdapter2 = Adapters.NullableDoubleAdapter;
        nullableAdapter2.toJson(writer, customScalarAdapters, value.getDuration());
        writer.name(ContextMappingConstants.LABEL);
        nullableAdapter.toJson(writer, customScalarAdapters, value.getLabel());
        writer.name("parentalSettings");
        Adapters.m2609nullable(Adapters.m2610obj(AlbumImpl_ResponseAdapter$ParentalSettings.INSTANCE, true)).toJson(writer, customScalarAdapters, value.getParentalSettings());
        writer.name("albumTracks");
        Adapters.m2609nullable(Adapters.m2608list(Adapters.m2609nullable(Adapters.m2610obj(AlbumImpl_ResponseAdapter$AlbumTrack.INSTANCE, true)))).toJson(writer, customScalarAdapters, value.getAlbumTracks());
        writer.name("trackCount");
        nullableAdapter2.toJson(writer, customScalarAdapters, value.getTrackCount());
        writer.name("copyright");
        nullableAdapter.toJson(writer, customScalarAdapters, value.getCopyright());
        writer.name("languageOfPerformance");
        nullableAdapter.toJson(writer, customScalarAdapters, value.getLanguageOfPerformance());
        writer.name("audioQualities");
        Adapters.m2609nullable(Adapters.m2608list(nullableAdapter)).toJson(writer, customScalarAdapters, value.getAudioQualities());
    }
}
