package com.amazon.mp3.recentlyplayed;

import android.net.Uri;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.lyrics.item.LyricsStatus;
import com.amazon.mp3.playlist.PlaylistRequestUtil;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.browse.metadata.CatalogStatusTiers;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.StringUtil;
import com.amazon.music.recents.model.MusicItem;
import com.amazon.music.recents.model.RecentlyPlayedAlbum;
import com.amazon.music.recents.model.RecentlyPlayedArtist;
import com.amazon.music.recents.model.RecentlyPlayedCatalogPlaylist;
import com.amazon.music.recents.model.RecentlyPlayedSharedUserPlaylist;
import com.amazon.music.recents.model.RecentlyPlayedStation;
import com.amazon.music.recents.model.RecentlyPlayedTrack;
import com.amazon.music.recents.model.RecentlyPlayedUserPlaylist;

/* JADX INFO: loaded from: classes4.dex */
public class RecentItemsTransformationUtil {
    private static final String TAG = "RecentItemsTransformationUtil";

    public static RecentlyPlayedItem from(final RecentlyPlayedStation station) {
        RecentlyPlayedItem recentlyPlayedItemCreateModel;
        Log.debug(TAG, "translating station " + station.getTitle());
        if (station.getEntityIdType().equals("SEED_ID")) {
            recentlyPlayedItemCreateModel = createModel(MediaProvider.Station.getContentUriWithSeeds(station.getEntityId()), station);
        } else {
            recentlyPlayedItemCreateModel = createModel(MediaProvider.Station.getContentUri(station.getEntityId()), station);
        }
        recentlyPlayedItemCreateModel.setCollectionId(station.getEntityId());
        recentlyPlayedItemCreateModel.setCollectionAsin(station.getEntityId());
        recentlyPlayedItemCreateModel.setIsExplicit(station.getIsExplicit().booleanValue());
        recentlyPlayedItemCreateModel.setTrackLuid(station.getTrackLuid());
        recentlyPlayedItemCreateModel.setTrackAsin(station.getTrackAsin());
        return recentlyPlayedItemCreateModel;
    }

    public static RecentlyPlayedItem from(final RecentlyPlayedAlbum album) {
        Log.debug(TAG, "translating album " + album.getTitle());
        String entityId = album.getEntityId();
        String title = album.getTitle();
        if (entityId == null || title == null) {
            return null;
        }
        long jGenerateAlbumId = IdGenerator.generateAlbumId(entityId, title);
        RecentlyPlayedItem recentlyPlayedItemCreateModel = createModel(MediaProvider.removeTracksSegment(MediaProvider.Albums.Tracks.getContentUri("cirrus", jGenerateAlbumId)), album);
        recentlyPlayedItemCreateModel.setCollectionId(String.valueOf(jGenerateAlbumId));
        recentlyPlayedItemCreateModel.setCollectionAsin(entityId);
        recentlyPlayedItemCreateModel.setTrackAsin(album.getTrackAsin());
        recentlyPlayedItemCreateModel.setTrackLuid(album.getTrackLuid());
        return recentlyPlayedItemCreateModel;
    }

    public static RecentlyPlayedItem from(final RecentlyPlayedCatalogPlaylist catalogPlaylist) {
        Log.debug(TAG, "translating catalogPlaylist " + catalogPlaylist.getTitle());
        RecentlyPlayedItem recentlyPlayedItemCreateModel = createModel(MediaProvider.removeTracksSegment(MediaProvider.PrimePlaylists.Tracks.getContentUri("cirrus", catalogPlaylist.getEntityId())), catalogPlaylist);
        recentlyPlayedItemCreateModel.setTrackLuid(catalogPlaylist.getTrackLuid());
        recentlyPlayedItemCreateModel.setTrackAsin(catalogPlaylist.getTrackAsin());
        recentlyPlayedItemCreateModel.setCollectionId(catalogPlaylist.getEntityId());
        recentlyPlayedItemCreateModel.setCollectionAsin(catalogPlaylist.getEntityId());
        return recentlyPlayedItemCreateModel;
    }

    public static RecentlyPlayedItem from(final RecentlyPlayedUserPlaylist userPlaylist) {
        Log.debug(TAG, "translating userPlaylist " + userPlaylist.getTitle());
        long jGeneratePlaylistId = PlaylistRequestUtil.generatePlaylistId(userPlaylist.getEntityId());
        RecentlyPlayedItem recentlyPlayedItemCreateModel = createModel(MediaProvider.UdoPlaylists.getContentUri("cirrus", jGeneratePlaylistId), userPlaylist);
        recentlyPlayedItemCreateModel.setCollectionAsin(String.valueOf(jGeneratePlaylistId));
        recentlyPlayedItemCreateModel.setCollectionId(String.valueOf(jGeneratePlaylistId));
        recentlyPlayedItemCreateModel.setTrackAsin(userPlaylist.getTrackAsin());
        recentlyPlayedItemCreateModel.setTrackLuid(userPlaylist.getTrackLuid());
        return recentlyPlayedItemCreateModel;
    }

    public static RecentlyPlayedItem from(final RecentlyPlayedSharedUserPlaylist userPlaylist) {
        Log.debug(TAG, "translating userSharedPlaylist " + userPlaylist.getTitle());
        String entityId = userPlaylist.getEntityId();
        RecentlyPlayedItem recentlyPlayedItemCreateModel = createModel(MediaProvider.SharedUserPlaylists.getContentUri("cirrus", entityId), userPlaylist);
        recentlyPlayedItemCreateModel.setCollectionAsin(entityId);
        recentlyPlayedItemCreateModel.setCollectionId(entityId);
        recentlyPlayedItemCreateModel.setTrackAsin(userPlaylist.getTrackAsin());
        recentlyPlayedItemCreateModel.setTrackLuid(userPlaylist.getTrackLuid());
        return recentlyPlayedItemCreateModel;
    }

    public static RecentlyPlayedItem from(final RecentlyPlayedArtist artist) {
        Log.debug(TAG, "translating artist " + artist.getTitle());
        long jGenerateArtistId = IdGenerator.generateArtistId(artist.getTitle());
        RecentlyPlayedItem recentlyPlayedItemCreateModel = createModel(getContentUriForArtist(artist), artist);
        recentlyPlayedItemCreateModel.setCollectionAsin(artist.getEntityId());
        recentlyPlayedItemCreateModel.setCollectionId(String.valueOf(jGenerateArtistId));
        recentlyPlayedItemCreateModel.setTrackAsin(artist.getTrackAsin());
        recentlyPlayedItemCreateModel.setTrackLuid(artist.getTrackLuid());
        return recentlyPlayedItemCreateModel;
    }

    public static PrimeTrack toPrimeTrack(final RecentlyPlayedTrack recentlyPlayedTrack) {
        ContentOwnershipStatus contentOwnershipStatus;
        LyricsStatus lyricsStatus;
        Log.verbose(TAG, "translating track " + recentlyPlayedTrack.getDisplayName());
        Boolean bool = Boolean.TRUE;
        CatalogStatusTiers catalogStatusTiers = new CatalogStatusTiers(bool.equals(recentlyPlayedTrack.isPrime()), bool.equals(recentlyPlayedTrack.isMusicSubscription()), bool.equals(recentlyPlayedTrack.isFree()), bool.equals(recentlyPlayedTrack.isFreeOnDemandPlayable()), bool.equals(recentlyPlayedTrack.isSonicRush()), bool.equals(recentlyPlayedTrack.isSonicRushOnDemandPlayable()));
        if (recentlyPlayedTrack.isPurchased().booleanValue()) {
            contentOwnershipStatus = ContentOwnershipStatus.OWNED;
        } else {
            contentOwnershipStatus = recentlyPlayedTrack.getInLibrary().booleanValue() ? ContentOwnershipStatus.ADDED : ContentOwnershipStatus.NOT_IN_LIBRARY;
        }
        PrimeTrack primeTrack = new PrimeTrack(recentlyPlayedTrack.getDisplayName(), recentlyPlayedTrack.getAsin(), recentlyPlayedTrack.getImage(), recentlyPlayedTrack.getArtistName(), recentlyPlayedTrack.getArtistAsin(), recentlyPlayedTrack.getAlbumName(), recentlyPlayedTrack.getAlbumAsin(), null, false, contentOwnershipStatus, catalogStatusTiers);
        primeTrack.setDuration(recentlyPlayedTrack.getDurationSeconds());
        if (recentlyPlayedTrack.getHasLyrics().booleanValue()) {
            lyricsStatus = LyricsStatus.AVAILABLE;
        } else {
            lyricsStatus = LyricsStatus.NO_LYRICS;
        }
        primeTrack.setLyricsStatus(lyricsStatus);
        primeTrack.setIsExplicit(recentlyPlayedTrack.getExplicit().booleanValue());
        primeTrack.setContentEncoding(recentlyPlayedTrack.getContentEncodings());
        return primeTrack;
    }

    public static RecentTrack toRecentTrack(final RecentlyPlayedTrack recentlyPlayedTrack) {
        RecentTrack recentTrack = new RecentTrack(recentlyPlayedTrack.getDisplayName(), recentlyPlayedTrack.getArtistName(), "cirrus");
        recentTrack.setAsin(recentlyPlayedTrack.getAsin());
        recentTrack.setLuid(recentlyPlayedTrack.getObjectId());
        recentTrack.setAlbumAsin(recentlyPlayedTrack.getAlbumAsin());
        recentTrack.setLastPlayedTime(recentlyPlayedTrack.getTimestamp());
        recentTrack.setArtworkImageUrl(recentlyPlayedTrack.getImage());
        recentTrack.setRemote(true);
        recentTrack.setHasLyrics(recentlyPlayedTrack.getHasLyrics().booleanValue());
        recentTrack.setCatalogStatus(setCatalogStatus(recentlyPlayedTrack));
        recentTrack.setOwnershipStatus(setOwnershipStatus(recentlyPlayedTrack));
        recentTrack.setExplicit(recentlyPlayedTrack.getExplicit().booleanValue());
        Boolean bool = Boolean.TRUE;
        recentTrack.setCatalogStatusTiers(new CatalogStatusTiers(bool.equals(recentlyPlayedTrack.isPrime()), bool.equals(recentlyPlayedTrack.isMusicSubscription()), bool.equals(recentlyPlayedTrack.isFree()), bool.equals(recentlyPlayedTrack.isFreeOnDemandPlayable()), bool.equals(recentlyPlayedTrack.isSonicRush()), bool.equals(recentlyPlayedTrack.isSonicRushOnDemandPlayable())));
        recentTrack.setContentEncodings(recentlyPlayedTrack.getContentEncodings());
        recentTrack.setDownloadState(-1);
        return recentTrack;
    }

    private static RecentlyPlayedItem createModel(final Uri content_uri, MusicItem musicItem) {
        RecentlyPlayedItem recentlyPlayedItem = new RecentlyPlayedItem(content_uri);
        recentlyPlayedItem.setTitle(musicItem.getTitle());
        recentlyPlayedItem.setLastPlayedTime(musicItem.getTimestamp().longValue());
        recentlyPlayedItem.setIsRemote(true);
        recentlyPlayedItem.setSynced(true);
        recentlyPlayedItem.setArtworkImageUrl(musicItem.getImage());
        recentlyPlayedItem.setContentCatalogStatus(setCatalogStatus(musicItem));
        Boolean bool = Boolean.TRUE;
        recentlyPlayedItem.setCatalogStatusTiers(new CatalogStatusTiers(bool.equals(musicItem.isPrime()), bool.equals(musicItem.isMusicSubscription()), bool.equals(musicItem.isFree()), bool.equals(musicItem.isFreeOnDemandPlayable()), bool.equals(musicItem.isSonicRush()), bool.equals(musicItem.isSonicRushOnDemandPlayable()), bool.equals(musicItem.isSonicRushGolden())));
        return recentlyPlayedItem;
    }

    private static ContentCatalogStatus setCatalogStatus(MusicItem item) {
        Boolean bool = Boolean.TRUE;
        if (bool.equals(item.isMusicSubscription())) {
            return ContentCatalogStatus.HAWKFIRE;
        }
        if (bool.equals(item.isSonicRush())) {
            return ContentCatalogStatus.SONIC_RUSH;
        }
        if (bool.equals(item.isPrime())) {
            return ContentCatalogStatus.PRIME;
        }
        return ContentCatalogStatus.NON_CATALOG;
    }

    private static ContentCatalogStatus setCatalogStatus(final RecentlyPlayedTrack recentlyPlayedTrack) {
        Boolean bool = Boolean.TRUE;
        if (bool.equals(recentlyPlayedTrack.isMusicSubscription())) {
            return ContentCatalogStatus.HAWKFIRE;
        }
        if (bool.equals(recentlyPlayedTrack.isSonicRush())) {
            return ContentCatalogStatus.SONIC_RUSH;
        }
        if (bool.equals(recentlyPlayedTrack.isPrime())) {
            return ContentCatalogStatus.PRIME;
        }
        return ContentCatalogStatus.NON_CATALOG;
    }

    private static ContentOwnershipStatus setOwnershipStatus(final RecentlyPlayedTrack recentlyPlayedTrack) {
        if (recentlyPlayedTrack.getInLibrary().booleanValue()) {
            return ContentOwnershipStatus.ADDED;
        }
        return ContentOwnershipStatus.NOT_IN_LIBRARY;
    }

    private static Uri getContentUriForArtist(final RecentlyPlayedArtist artist) {
        long jGenerateArtistId = IdGenerator.generateArtistId(artist.getTitle());
        if (!StringUtil.isNullOrWhiteSpaces(artist.getTrackLuid())) {
            return MediaProvider.Artists.getContentUri("cirrus", jGenerateArtistId);
        }
        return MediaProvider.PrimeArtists.getContentUri("cirrus", jGenerateArtistId);
    }
}
