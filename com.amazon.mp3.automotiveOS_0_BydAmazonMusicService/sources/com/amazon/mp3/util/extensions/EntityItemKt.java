package com.amazon.mp3.util.extensions;

import com.amazon.mp3.casting.CastingTrack;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.AllTracks;
import com.amazon.mp3.library.item.Artist;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.playlist.EntityPlaylist;
import com.amazon.mp3.playlist.SharedUserPlaylist;
import com.amazon.mp3.prime.browse.metadata.PrimeAlbum;
import com.amazon.mp3.prime.browse.metadata.PrimeArtist;
import com.amazon.mp3.prime.browse.metadata.PrimePlaylist;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.prime.station.StationTrack;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.voice.AlexaTrack;
import com.amazon.music.platform.data.entity.EntityItem;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.metadata.UserPlaylistMetadata;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EntityItem.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"displayName", "", "Lcom/amazon/music/platform/data/entity/EntityItem;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class EntityItemKt {
    public static final String displayName(EntityItem entityItem) {
        Intrinsics.checkNotNullParameter(entityItem, "<this>");
        if (entityItem instanceof MusicTrack) {
            return ((MusicTrack) entityItem).getTitle();
        }
        if (entityItem instanceof StationTrack) {
            return ((StationTrack) entityItem).getTitle();
        }
        if (entityItem instanceof CastingTrack) {
            return ((CastingTrack) entityItem).getTitle();
        }
        if (entityItem instanceof AlexaTrack) {
            return ((AlexaTrack) entityItem).getTitle();
        }
        if (entityItem instanceof AllTracks) {
            return ((AllTracks) entityItem).getTitle();
        }
        if (entityItem instanceof Album) {
            return ((Album) entityItem).getTitle();
        }
        if (entityItem instanceof Artist) {
            return ((Artist) entityItem).getName();
        }
        if (entityItem instanceof Playlist) {
            return ((Playlist) entityItem).getName();
        }
        if (entityItem instanceof CatalogPlaylist) {
            return ((CatalogPlaylist) entityItem).getTitle();
        }
        if (entityItem instanceof EntityPlaylist) {
            return ((EntityPlaylist) entityItem).getTitle();
        }
        if (entityItem instanceof SharedUserPlaylist) {
            return ((SharedUserPlaylist) entityItem).getTitle();
        }
        if (entityItem instanceof TrackMetadata) {
            return ((TrackMetadata) entityItem).getTitle();
        }
        if (entityItem instanceof PlaylistMetadata) {
            return ((PlaylistMetadata) entityItem).getTitle();
        }
        if (entityItem instanceof AlbumMetadata) {
            return ((AlbumMetadata) entityItem).getTitle();
        }
        if (entityItem instanceof UserPlaylistMetadata) {
            return ((UserPlaylistMetadata) entityItem).getTitle();
        }
        if (entityItem instanceof PrimeTrack) {
            return ((PrimeTrack) entityItem).getTitle();
        }
        if (entityItem instanceof PrimeAlbum) {
            return ((PrimeAlbum) entityItem).getTitle();
        }
        if (entityItem instanceof PrimePlaylist) {
            return ((PrimePlaylist) entityItem).getTitle();
        }
        if (entityItem instanceof PrimeArtist) {
            return ((PrimeArtist) entityItem).getTitle();
        }
        Log.warning("EntityItem", Intrinsics.stringPlus("Can't find the name of the item ", entityItem));
        return "";
    }
}
