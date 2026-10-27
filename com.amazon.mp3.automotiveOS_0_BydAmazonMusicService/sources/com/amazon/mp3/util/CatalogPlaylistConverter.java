package com.amazon.mp3.util;

import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.playlist.CatalogPlaylistTrack;
import com.amazon.music.storeservice.model.Album;
import com.amazon.music.storeservice.model.Artist;
import com.amazon.music.storeservice.model.PlaylistItem;
import com.amazon.music.storeservice.model.TrackItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CatalogPlaylistConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/util/CatalogPlaylistConverter;", "", "()V", "convertToPlaylistItem", "Lcom/amazon/music/storeservice/model/PlaylistItem;", "catalogPlaylist", "Lcom/amazon/mp3/playlist/CatalogPlaylist;", "convertTracksToTrackItemList", "", "Lcom/amazon/music/storeservice/model/TrackItem;", "catalogPlaylistTrackList", "", "Lcom/amazon/mp3/playlist/CatalogPlaylistTrack;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CatalogPlaylistConverter {
    public static final CatalogPlaylistConverter INSTANCE = new CatalogPlaylistConverter();

    private CatalogPlaylistConverter() {
    }

    public final PlaylistItem convertToPlaylistItem(CatalogPlaylist catalogPlaylist) {
        Intrinsics.checkNotNullParameter(catalogPlaylist, "catalogPlaylist");
        PlaylistItem.Builder builderWithTrackCount = PlaylistItem.builder().withAsin(catalogPlaylist.getAsin()).withCuratedBy(catalogPlaylist.getCuratedBy()).withBannerImage(catalogPlaylist.getBannerArtUrl()).withDescription(catalogPlaylist.getDescription()).withDuration(Integer.valueOf(catalogPlaylist.getDurationSeconds())).withIsFree(Boolean.valueOf(catalogPlaylist.isFree())).withIsFreeOnDemandPlayable(Boolean.valueOf(catalogPlaylist.isFreeOnDemand())).withIsInLibrary(Boolean.valueOf(catalogPlaylist.isInLibrary())).withIsPrime(Boolean.valueOf(catalogPlaylist.isPrime())).withOwned(Boolean.valueOf(catalogPlaylist.isAllOwned())).withTitle(catalogPlaylist.getTitle()).withTrackCount(Integer.valueOf(catalogPlaylist.getTrackCount()));
        Collection<CatalogPlaylistTrack> tracks = catalogPlaylist.getTracks();
        Intrinsics.checkNotNullExpressionValue(tracks, "catalogPlaylist.tracks");
        PlaylistItem playlistItem = builderWithTrackCount.withTracks(convertTracksToTrackItemList(tracks)).withType(catalogPlaylist.getItemTypeName()).build();
        Intrinsics.checkNotNullExpressionValue(playlistItem, "playlistItem");
        return playlistItem;
    }

    private final List<TrackItem> convertTracksToTrackItemList(Collection<? extends CatalogPlaylistTrack> catalogPlaylistTrackList) {
        ArrayList arrayList = new ArrayList();
        for (CatalogPlaylistTrack catalogPlaylistTrack : catalogPlaylistTrackList) {
            Album album = new Album();
            album.setAsin(catalogPlaylistTrack.getAlbumAsin());
            album.setImage(catalogPlaylistTrack.getAlbumArtUrl());
            album.setTitle(catalogPlaylistTrack.getAlbumName());
            Artist artist = new Artist();
            artist.setAsin(catalogPlaylistTrack.getArtistAsin());
            artist.setName(catalogPlaylistTrack.getArtistName());
            TrackItem trackItem = new TrackItem();
            trackItem.setAsin(catalogPlaylistTrack.getAsin());
            trackItem.setAlbum(album);
            trackItem.setArtist(artist);
            trackItem.setTitle(catalogPlaylistTrack.getTitle());
            trackItem.setDuration(Integer.valueOf(catalogPlaylistTrack.getDurationSeconds()));
            trackItem.setHasLyrics(Boolean.valueOf(catalogPlaylistTrack.hasLyrics()));
            trackItem.setGenreName(catalogPlaylistTrack.getGenre());
            trackItem.setPrimaryArtistName(catalogPlaylistTrack.getArtistName());
            trackItem.setAlbumArtistName(catalogPlaylistTrack.getAlbumArtistName());
            arrayList.add(trackItem);
        }
        return arrayList;
    }
}
