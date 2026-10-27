package com.amazon.mp3.playlist;

import com.amazon.mp3.configuration.IntlConfiguration;
import com.amazon.mp3.library.provider.MediaProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: EntityPlaylist.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/playlist/EntityPlaylist;", "Lcom/amazon/mp3/playlist/CatalogPlaylist;", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class EntityPlaylist extends CatalogPlaylist {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String ENTITY_PLAYLIST_TYPE = "com.amazon.musicplaylist.model#EntityPlaylist";

    /* JADX INFO: compiled from: EntityPlaylist.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/playlist/EntityPlaylist$Companion;", "", "()V", "ENTITY_PLAYLIST_TYPE", "", "fromJSON", "Lcom/amazon/mp3/playlist/CatalogPlaylist;", "asin", IntlConfiguration.MARKETPLACE, "playlistObject", "Lorg/json/JSONObject;", "isEntityPlaylist", "", "playlistResponse", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final CatalogPlaylist fromJSON(JSONObject playlistObject) throws JSONException {
            Intrinsics.checkNotNullParameter(playlistObject, "playlistObject");
            CatalogPlaylist catalogPlaylistFromJSON = fromJSON(null, null, playlistObject);
            catalogPlaylistFromJSON.setAsin(catalogPlaylistFromJSON.getLuid());
            return catalogPlaylistFromJSON;
        }

        public final CatalogPlaylist fromJSON(String asin, String marketplace, JSONObject playlistObject) throws JSONException {
            Intrinsics.checkNotNullParameter(playlistObject, "playlistObject");
            CatalogPlaylist entityPlaylist = CatalogPlaylist.fromJSON(asin, marketplace, playlistObject);
            entityPlaylist.setIsEntityPlaylist(true);
            entityPlaylist.setContentUri(MediaProvider.SharedUserPlaylists.getContentUri("cirrus", entityPlaylist.getLuid()));
            Intrinsics.checkNotNullExpressionValue(entityPlaylist, "entityPlaylist");
            return entityPlaylist;
        }

        public final boolean isEntityPlaylist(JSONObject playlistResponse) {
            Intrinsics.checkNotNullParameter(playlistResponse, "playlistResponse");
            return Intrinsics.areEqual(playlistResponse.optString("__type"), EntityPlaylist.ENTITY_PLAYLIST_TYPE);
        }
    }
}
