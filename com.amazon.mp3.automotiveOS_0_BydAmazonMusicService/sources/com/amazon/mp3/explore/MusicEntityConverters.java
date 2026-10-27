package com.amazon.mp3.explore;

import ExternalActionInterface.v1_0.Album;
import ExternalActionInterface.v1_0.Artist;
import ExternalActionInterface.v1_0.PlayableContent;
import ExternalActionInterface.v1_0.Playlist;
import ExternalActionInterface.v1_0.PlaymodeEligibility;
import ExternalActionInterface.v1_0.Station;
import ExternalActionInterface.v1_0.Track;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.PrimePlaylistTracksTable;
import com.amazon.mp3.prime.browse.metadata.CatalogStatusTiers;
import com.amazon.mp3.prime.browse.metadata.PrimeAlbum;
import com.amazon.mp3.prime.browse.metadata.PrimeArtist;
import com.amazon.mp3.prime.browse.metadata.PrimePlaylist;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.music.ContentAccessType;
import com.amazon.music.models.AssetQuality;
import java.util.EnumMap;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MusicEntityConverters.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/explore/MusicEntityConverters;", "", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MusicEntityConverters {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: MusicEntityConverters.kt */
    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b¨\u0006\u001c"}, d2 = {"Lcom/amazon/mp3/explore/MusicEntityConverters$Companion;", "", "()V", "convertToMusicEntity", "playableContent", "LExternalActionInterface/v1_0/PlayableContent;", "toMusicTrack", "Lcom/amazon/mp3/library/item/MusicTrack;", "track", "LExternalActionInterface/v1_0/Track;", "toPrimeAlbum", "Lcom/amazon/mp3/prime/browse/metadata/PrimeAlbum;", "album", "LExternalActionInterface/v1_0/Album;", "toPrimeArtist", "Lcom/amazon/mp3/prime/browse/metadata/PrimeArtist;", "artist", "LExternalActionInterface/v1_0/Artist;", "toPrimePlaylist", "Lcom/amazon/mp3/prime/browse/metadata/PrimePlaylist;", Environment.PLAYLIST_PATH, "LExternalActionInterface/v1_0/Playlist;", "toPrimeTrack", "Lcom/amazon/mp3/prime/browse/metadata/PrimeTrack;", "toStation", "Lcom/amazon/mp3/search/model/Station;", "station", "LExternalActionInterface/v1_0/Station;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Object convertToMusicEntity(PlayableContent playableContent) {
            Intrinsics.checkNotNullParameter(playableContent, "playableContent");
            if (playableContent instanceof Album) {
                return toPrimeAlbum((Album) playableContent);
            }
            if (playableContent instanceof Artist) {
                return toPrimeArtist((Artist) playableContent);
            }
            if (playableContent instanceof Playlist) {
                return toPrimePlaylist((Playlist) playableContent);
            }
            if (playableContent instanceof Station) {
                return toStation((Station) playableContent);
            }
            if (playableContent instanceof Track) {
                return toPrimeTrack((Track) playableContent);
            }
            return null;
        }

        public final PrimeAlbum toPrimeAlbum(Album album) {
            Intrinsics.checkNotNullParameter(album, "album");
            Boolean boolIsPrime = album.isPrime();
            Boolean bool = Boolean.TRUE;
            boolean zAreEqual = Intrinsics.areEqual(boolIsPrime, bool);
            boolean zAreEqual2 = Intrinsics.areEqual(album.isUnlimited(), bool);
            boolean zIsFree = album.isFree();
            boolean zAreEqual3 = Intrinsics.areEqual(album.isSonicRush(), bool);
            PlaymodeEligibility playmodeEligibility = album.playmodeEligibility();
            return new PrimeAlbum(album.title(), album.asin(), album.albumArtworkUrl(), album.artistName(), album.artistAsin(), 0, null, new CatalogStatusTiers(zAreEqual, zAreEqual2, zIsFree, false, zAreEqual3, playmodeEligibility != null && playmodeEligibility.canPlayOnDemand(), false, 64, null));
        }

        public final PrimeArtist toPrimeArtist(Artist artist) {
            Intrinsics.checkNotNullParameter(artist, "artist");
            String strTitle = artist.title();
            String strAsin = artist.asin();
            boolean zIsFree = artist.isFree();
            Boolean boolIsPrime = artist.isPrime();
            Boolean bool = Boolean.TRUE;
            boolean zAreEqual = Intrinsics.areEqual(boolIsPrime, bool);
            boolean zAreEqual2 = Intrinsics.areEqual(artist.isUnlimited(), bool);
            boolean zAreEqual3 = Intrinsics.areEqual(artist.isSonicRush(), bool);
            PlaymodeEligibility playmodeEligibility = artist.playmodeEligibility();
            return new PrimeArtist(strTitle, strAsin, null, 0, 0, null, null, new CatalogStatusTiers(zAreEqual, zAreEqual2, zIsFree, false, zAreEqual3, playmodeEligibility != null && playmodeEligibility.canPlayOnDemand(), false, 64, null));
        }

        public final PrimePlaylist toPrimePlaylist(Playlist playlist) {
            Intrinsics.checkNotNullParameter(playlist, "playlist");
            String strTitle = playlist.title();
            String strAsin = playlist.asin();
            String strPlaylistArtworkUrl = playlist.playlistArtworkUrl();
            boolean zIsFree = playlist.isFree();
            Boolean boolIsPrime = playlist.isPrime();
            if (boolIsPrime == null) {
                boolIsPrime = Boolean.FALSE;
            }
            Boolean boolIsUnlimited = playlist.isUnlimited();
            if (boolIsUnlimited == null) {
                boolIsUnlimited = Boolean.FALSE;
            }
            boolean zAreEqual = Intrinsics.areEqual(playlist.isSonicRush(), Boolean.TRUE);
            PlaymodeEligibility playmodeEligibility = playlist.playmodeEligibility();
            return new PrimePlaylist(strTitle, strAsin, strPlaylistArtworkUrl, null, 0.0f, 0, 0, null, null, new CatalogStatusTiers(boolIsPrime.booleanValue(), boolIsUnlimited.booleanValue(), zIsFree, false, zAreEqual, playmodeEligibility != null && playmodeEligibility.canPlayOnDemand(), false, 64, null));
        }

        public final com.amazon.mp3.search.model.Station toStation(Station station) {
            Intrinsics.checkNotNullParameter(station, "station");
            boolean zIsFree = station.isFree();
            Boolean boolIsPrime = station.isPrime();
            if (boolIsPrime == null) {
                boolIsPrime = Boolean.FALSE;
            }
            Boolean boolIsUnlimited = station.isUnlimited();
            if (boolIsUnlimited == null) {
                boolIsUnlimited = Boolean.FALSE;
            }
            return new com.amazon.mp3.search.model.Station(new CatalogStatusTiers(boolIsPrime.booleanValue(), boolIsUnlimited.booleanValue(), zIsFree, false, false, false, false, 112, null), station.title(), station.image(), station.key(), new EnumMap(ContentAccessType.class), null, null);
        }

        public final PrimeTrack toPrimeTrack(Track track) {
            PrimePlaylistTracksTable.AssetType assetType;
            Intrinsics.checkNotNullParameter(track, "track");
            Boolean boolIsPrime = track.isPrime();
            Boolean bool = Boolean.TRUE;
            boolean zAreEqual = Intrinsics.areEqual(boolIsPrime, bool);
            boolean zAreEqual2 = Intrinsics.areEqual(track.isUnlimited(), bool);
            boolean zIsFree = track.isFree();
            boolean zAreEqual3 = Intrinsics.areEqual(track.isSonicRush(), bool);
            PlaymodeEligibility playmodeEligibility = track.playmodeEligibility();
            boolean z = true;
            PrimeTrack primeTrack = new PrimeTrack(track.title(), track.asin(), track.albumArtworkUrl(), track.artistName(), track.artistAsin(), track.albumName(), track.albumAsin(), null, track.isVideo(), ContentOwnershipStatus.NOT_IN_LIBRARY, new CatalogStatusTiers(zAreEqual, zAreEqual2, zIsFree, false, zAreEqual3, playmodeEligibility != null && playmodeEligibility.canPlayOnDemand(), false, 64, null));
            if (!track.isVideo() && !track.isExplicit()) {
                z = false;
            }
            primeTrack.setExplicit(z);
            if (track.isVideo()) {
                assetType = PrimePlaylistTracksTable.AssetType.VIDEO;
            } else {
                assetType = PrimePlaylistTracksTable.AssetType.AUDIO;
            }
            primeTrack.setAssetType(assetType);
            String strVideoPid = track.videoPid();
            if (strVideoPid != null) {
                primeTrack.setAssetQualities(CollectionsKt__CollectionsJVMKt.listOf(new AssetQuality(strVideoPid, "DEFAULT", 0)));
                primeTrack.setDuration(track.duration());
            }
            return primeTrack;
        }

        public final MusicTrack toMusicTrack(Track track) {
            Intrinsics.checkNotNullParameter(track, "track");
            Boolean boolIsPrime = track.isPrime();
            Boolean bool = Boolean.TRUE;
            boolean zAreEqual = Intrinsics.areEqual(boolIsPrime, bool);
            boolean zAreEqual2 = Intrinsics.areEqual(track.isUnlimited(), bool);
            boolean zIsFree = track.isFree();
            boolean zAreEqual3 = Intrinsics.areEqual(track.isSonicRush(), bool);
            PlaymodeEligibility playmodeEligibility = track.playmodeEligibility();
            CatalogStatusTiers catalogStatusTiers = new CatalogStatusTiers(zAreEqual, zAreEqual2, zIsFree, false, zAreEqual3, playmodeEligibility != null && playmodeEligibility.canPlayOnDemand(), false, 64, null);
            MusicTrack musicTrack = new MusicTrack();
            musicTrack.setAsin(track.asin());
            musicTrack.setTitle(track.title());
            musicTrack.setArtistName(track.artistName());
            musicTrack.setArtistAsin(track.artistAsin());
            musicTrack.setContentUri(MediaProvider.Tracks.getPrimeAdditionalTrackContentUri("cirrus", "primebrowse", track.asin()));
            musicTrack.setOwnershipStatus(ContentOwnershipStatus.NOT_IN_LIBRARY);
            musicTrack.setCatalogStatusTiers(catalogStatusTiers);
            return musicTrack;
        }
    }
}
