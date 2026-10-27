package com.amazon.music.platform.graphql.generated.fragment;

import com.amazon.music.platform.graphql.generated.fragment.SocialProfilesUserEmbedded;
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

/* JADX INFO: compiled from: SocialProfilesUserEmbeddedImpl_ResponseAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u0018\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J \u0010\f\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0002H\u0016R\u001d\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"com/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbeddedImpl_ResponseAdapter$SocialProfilesUserEmbedded", "Lcom/apollographql/apollo3/api/Adapter;", "Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded;", "Lcom/apollographql/apollo3/api/json/JsonReader;", "reader", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "fromJson", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "writer", "value", "", "toJson", "", "", "RESPONSE_NAMES", "Ljava/util/List;", "getRESPONSE_NAMES", "()Ljava/util/List;", "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final class SocialProfilesUserEmbeddedImpl_ResponseAdapter$SocialProfilesUserEmbedded implements Adapter<SocialProfilesUserEmbedded> {
    public static final SocialProfilesUserEmbeddedImpl_ResponseAdapter$SocialProfilesUserEmbedded INSTANCE = new SocialProfilesUserEmbeddedImpl_ResponseAdapter$SocialProfilesUserEmbedded();
    private static final List<String> RESPONSE_NAMES = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"id", ImagesContract.URL, "name", "handle", "images", "visibility", "playbackVisibility", "playlists", "followers", "followedUsers", "followedArtists"});

    private SocialProfilesUserEmbeddedImpl_ResponseAdapter$SocialProfilesUserEmbedded() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.apollographql.apollo3.api.Adapter
    public SocialProfilesUserEmbedded fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) throws IOException {
        Intrinsics.checkNotNullParameter(reader, "reader");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        String strFromJson = null;
        String strFromJson2 = null;
        String strFromJson3 = null;
        String strFromJson4 = null;
        List list = null;
        String strFromJson5 = null;
        String strFromJson6 = null;
        SocialProfilesUserEmbedded.Playlists playlists = null;
        SocialProfilesUserEmbedded.Followers followers = null;
        SocialProfilesUserEmbedded.FollowedUsers followedUsers = null;
        SocialProfilesUserEmbedded.FollowedArtists followedArtists = null;
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
                    list = (List) Adapters.m2609nullable(Adapters.m2608list(Adapters.m2609nullable(Adapters.m2610obj(SocialProfilesUserEmbeddedImpl_ResponseAdapter$Image.INSTANCE, true)))).fromJson(reader, customScalarAdapters);
                    break;
                case 5:
                    strFromJson5 = Adapters.NullableStringAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 6:
                    strFromJson6 = Adapters.NullableStringAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 7:
                    playlists = (SocialProfilesUserEmbedded.Playlists) Adapters.m2609nullable(Adapters.m2611obj$default(SocialProfilesUserEmbeddedImpl_ResponseAdapter$Playlists.INSTANCE, false, 1, null)).fromJson(reader, customScalarAdapters);
                    break;
                case 8:
                    followers = (SocialProfilesUserEmbedded.Followers) Adapters.m2609nullable(Adapters.m2611obj$default(SocialProfilesUserEmbeddedImpl_ResponseAdapter$Followers.INSTANCE, false, 1, null)).fromJson(reader, customScalarAdapters);
                    break;
                case 9:
                    followedUsers = (SocialProfilesUserEmbedded.FollowedUsers) Adapters.m2609nullable(Adapters.m2611obj$default(SocialProfilesUserEmbeddedImpl_ResponseAdapter$FollowedUsers.INSTANCE, false, 1, null)).fromJson(reader, customScalarAdapters);
                    break;
                case 10:
                    followedArtists = (SocialProfilesUserEmbedded.FollowedArtists) Adapters.m2609nullable(Adapters.m2611obj$default(SocialProfilesUserEmbeddedImpl_ResponseAdapter$FollowedArtists.INSTANCE, false, 1, null)).fromJson(reader, customScalarAdapters);
                    break;
                default:
                    return new SocialProfilesUserEmbedded(strFromJson, strFromJson2, strFromJson3, strFromJson4, list, strFromJson5, strFromJson6, playlists, followers, followedUsers, followedArtists);
            }
        }
    }

    @Override // com.apollographql.apollo3.api.Adapter
    public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, SocialProfilesUserEmbedded value) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        Intrinsics.checkNotNullParameter(value, "value");
        writer.name("id");
        NullableAdapter<String> nullableAdapter = Adapters.NullableStringAdapter;
        nullableAdapter.toJson(writer, customScalarAdapters, value.getId());
        writer.name(ImagesContract.URL);
        nullableAdapter.toJson(writer, customScalarAdapters, value.getUrl());
        writer.name("name");
        nullableAdapter.toJson(writer, customScalarAdapters, value.getName());
        writer.name("handle");
        nullableAdapter.toJson(writer, customScalarAdapters, value.getHandle());
        writer.name("images");
        Adapters.m2609nullable(Adapters.m2608list(Adapters.m2609nullable(Adapters.m2610obj(SocialProfilesUserEmbeddedImpl_ResponseAdapter$Image.INSTANCE, true)))).toJson(writer, customScalarAdapters, value.getImages());
        writer.name("visibility");
        nullableAdapter.toJson(writer, customScalarAdapters, value.getVisibility());
        writer.name("playbackVisibility");
        nullableAdapter.toJson(writer, customScalarAdapters, value.getPlaybackVisibility());
        writer.name("playlists");
        Adapters.m2609nullable(Adapters.m2611obj$default(SocialProfilesUserEmbeddedImpl_ResponseAdapter$Playlists.INSTANCE, false, 1, null)).toJson(writer, customScalarAdapters, value.getPlaylists());
        writer.name("followers");
        Adapters.m2609nullable(Adapters.m2611obj$default(SocialProfilesUserEmbeddedImpl_ResponseAdapter$Followers.INSTANCE, false, 1, null)).toJson(writer, customScalarAdapters, value.getFollowers());
        writer.name("followedUsers");
        Adapters.m2609nullable(Adapters.m2611obj$default(SocialProfilesUserEmbeddedImpl_ResponseAdapter$FollowedUsers.INSTANCE, false, 1, null)).toJson(writer, customScalarAdapters, value.getFollowedUsers());
        writer.name("followedArtists");
        Adapters.m2609nullable(Adapters.m2611obj$default(SocialProfilesUserEmbeddedImpl_ResponseAdapter$FollowedArtists.INSTANCE, false, 1, null)).toJson(writer, customScalarAdapters, value.getFollowedArtists());
    }
}
