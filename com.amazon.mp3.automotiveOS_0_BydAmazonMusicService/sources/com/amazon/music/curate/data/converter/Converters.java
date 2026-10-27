package com.amazon.music.curate.data.converter;

import com.amazon.mp3.configuration.IntlConfiguration;
import com.amazon.mp3.environment.Environment;
import com.amazon.music.account.AccountManager;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.User;
import com.amazon.music.curate.extension.UserResultKt;
import com.amazon.music.curate.model.Playlist;
import com.amazon.music.curate.model.Track;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.musicplaylist.model.CatalogPlaylist;
import com.amazon.musicplaylist.model.CatalogPlaylistMetadata;
import com.amazon.musicplaylist.model.CatalogPlaylistTrackEntry;
import com.amazon.musicplaylist.model.LibraryPlaylistTrackEntry;
import com.amazon.musicplaylist.model.OwnedPlaylist;
import com.amazon.musicplaylist.model.PersonalizedPlaylist;
import com.amazon.musicplaylist.model.PlaylistMetadata;
import com.amazon.nimblymusicservice.RecentPlayedTrackActivity;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Converters.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/music/curate/data/converter/Converters;", "", "()V", "Companion", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class Converters {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final long TTL_SECONDS = TimeUnit.HOURS.toSeconds(12);

    /* JADX INFO: compiled from: Converters.kt */
    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0014J\u000e\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0019J\b\u0010\u001a\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/amazon/music/curate/data/converter/Converters$Companion;", "", "()V", "TTL_SECONDS", "", "convertCatalogPlayist", "Lcom/amazon/music/curate/model/Playlist;", Environment.PLAYLIST_PATH, "Lcom/amazon/musicplaylist/model/CatalogPlaylist;", "isPersonalized", "", "convertCatalogTrack", "Lcom/amazon/music/curate/model/Track;", "track", "Lcom/amazon/musicplaylist/model/CatalogPlaylistTrackEntry;", "convertLibraryTrack", "Lcom/amazon/musicplaylist/model/LibraryPlaylistTrackEntry;", "convertPersonalizedPlayist", "Lcom/amazon/musicplaylist/model/PersonalizedPlaylist;", "convertPlaylist", "Lcom/amazon/musicplaylist/model/OwnedPlaylist;", "convertPlaylistMetadata", "metadata", "Lcom/amazon/musicplaylist/model/PlaylistMetadata;", "convertRecentTrack", "Lcom/amazon/nimblymusicservice/RecentPlayedTrackActivity;", "getExpiryTime", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Track convertLibraryTrack(LibraryPlaylistTrackEntry track) {
            Intrinsics.checkNotNullParameter(track, "track");
            String str = track.getMetadata().getRequestedMetadata().get("asin");
            Intrinsics.checkNotNull(str);
            String str2 = str;
            String str3 = track.getMetadata().getRequestedMetadata().get("title");
            Intrinsics.checkNotNull(str3);
            String str4 = str3;
            String str5 = track.getMetadata().getRequestedMetadata().get("artistName");
            Intrinsics.checkNotNull(str5);
            String str6 = str5;
            String str7 = track.getMetadata().getRequestedMetadata().get("albumCoverImageFull");
            Intrinsics.checkNotNull(str7);
            String str8 = str7;
            List listEmptyList = CollectionsKt__CollectionsKt.emptyList();
            String str9 = track.getMetadata().getRequestedMetadata().get("artistAsin");
            Intrinsics.checkNotNull(str9);
            String str10 = str9;
            String str11 = track.getMetadata().getRequestedMetadata().get("albumAsin");
            Intrinsics.checkNotNull(str11);
            String str12 = str11;
            String str13 = track.getMetadata().getRequestedMetadata().get("albumName");
            Intrinsics.checkNotNull(str13);
            String str14 = str13;
            boolean z = track.getMetadata().getRequestedMetadata().get("objectId") != null;
            String str15 = track.getMetadata().getRequestedMetadata().get("duration");
            Intrinsics.checkNotNull(str15);
            int i = Integer.parseInt(str15);
            String obfuscatedId = track.getMetadata().getRequestedMetadata().get(IntlConfiguration.MARKETPLACE);
            if (obfuscatedId == null) {
                obfuscatedId = Marketplace.USA.getObfuscatedId();
            }
            String str16 = obfuscatedId;
            Intrinsics.checkNotNullExpressionValue(str16, "track.metadata.requested…ketplace.USA.obfuscatedId");
            return new Track(str2, str4, str6, str8, listEmptyList, str10, str12, str14, z, i, str16, track.getEntryId());
        }

        public final Track convertCatalogTrack(CatalogPlaylistTrackEntry track) {
            Intrinsics.checkNotNullParameter(track, "track");
            String asin = track.getMetadata().getAsin();
            Intrinsics.checkNotNullExpressionValue(asin, "track.metadata.asin");
            String title = track.getMetadata().getTitle();
            Intrinsics.checkNotNullExpressionValue(title, "track.metadata.title");
            String name = track.getMetadata().getArtists().get(0).getName();
            Intrinsics.checkNotNullExpressionValue(name, "track.metadata.artists[0].name");
            String url = track.getMetadata().getAlbumArt().getUrl();
            Intrinsics.checkNotNullExpressionValue(url, "track.metadata.albumArt.url");
            List listEmptyList = CollectionsKt__CollectionsKt.emptyList();
            String digitalAsin = track.getMetadata().getArtists().get(0).getDigitalAsin();
            Intrinsics.checkNotNullExpressionValue(digitalAsin, "track.metadata.artists[0].digitalAsin");
            String albumAsin = track.getMetadata().getAlbumAsin();
            Intrinsics.checkNotNullExpressionValue(albumAsin, "track.metadata.albumAsin");
            String albumName = track.getMetadata().getAlbumName();
            Intrinsics.checkNotNullExpressionValue(albumName, "track.metadata.albumName");
            boolean z = track.getMetadata().getObjectId() != null;
            Integer durationSeconds = track.getMetadata().getDurationSeconds();
            Intrinsics.checkNotNullExpressionValue(durationSeconds, "track.metadata.durationSeconds");
            int iIntValue = durationSeconds.intValue();
            AccountManager accountManager = AccountManagerSingleton.get();
            Intrinsics.checkNotNullExpressionValue(accountManager, "get()");
            Object objUserResult = UserResultKt.userResult(accountManager);
            if (Result.m2816isFailureimpl(objUserResult)) {
                objUserResult = null;
            }
            User user = (User) objUserResult;
            Marketplace libraryHomeMarketplace = user != null ? user.getLibraryHomeMarketplace() : null;
            if (libraryHomeMarketplace == null) {
                libraryHomeMarketplace = Marketplace.USA;
            }
            String obfuscatedId = libraryHomeMarketplace.getObfuscatedId();
            Intrinsics.checkNotNullExpressionValue(obfuscatedId, "AccountManagerSingleton.…etplace.USA).obfuscatedId");
            return new Track(asin, title, name, url, listEmptyList, digitalAsin, albumAsin, albumName, z, iIntValue, obfuscatedId, track.getEntryId());
        }

        public final Track convertRecentTrack(RecentPlayedTrackActivity track) {
            Intrinsics.checkNotNullParameter(track, "track");
            String asin = track.getAsin();
            Intrinsics.checkNotNullExpressionValue(asin, "track.asin");
            String displayName = track.getDisplayName();
            Intrinsics.checkNotNullExpressionValue(displayName, "track.displayName");
            String artistName = track.getArtistName();
            Intrinsics.checkNotNullExpressionValue(artistName, "track.artistName");
            String imageFull = track.getImageFull();
            Intrinsics.checkNotNullExpressionValue(imageFull, "track.imageFull");
            List listEmptyList = CollectionsKt__CollectionsKt.emptyList();
            String artistAsin = track.getArtistAsin();
            Intrinsics.checkNotNullExpressionValue(artistAsin, "track.artistAsin");
            String albumAsin = track.getAlbumAsin();
            Intrinsics.checkNotNullExpressionValue(albumAsin, "track.albumAsin");
            String albumName = track.getAlbumName();
            Intrinsics.checkNotNullExpressionValue(albumName, "track.albumName");
            String isInLibrary = track.getIsInLibrary();
            boolean z = isInLibrary == null ? true : Boolean.parseBoolean(isInLibrary);
            String durationSeconds = track.getDurationSeconds();
            Intrinsics.checkNotNullExpressionValue(durationSeconds, "track.durationSeconds");
            int i = Integer.parseInt(durationSeconds);
            String marketplaceId = track.getMarketplaceId();
            Intrinsics.checkNotNullExpressionValue(marketplaceId, "track.marketplaceId");
            return new Track(asin, displayName, artistName, imageFull, listEmptyList, artistAsin, albumAsin, albumName, z, i, marketplaceId, null, 2048, null);
        }

        public final Playlist convertPlaylist(OwnedPlaylist playlist) {
            Intrinsics.checkNotNullParameter(playlist, "playlist");
            String playlistId = playlist.getMetadata().getPlaylistId();
            Intrinsics.checkNotNullExpressionValue(playlistId, "playlist.metadata.playlistId");
            String title = playlist.getMetadata().getTitle();
            Intrinsics.checkNotNullExpressionValue(title, "playlist.metadata.title");
            String url = playlist.getMetadata().getFourSquareArt().getUrl();
            Intrinsics.checkNotNullExpressionValue(url, "playlist.metadata.fourSquareArt.url");
            String url2 = playlist.getMetadata().getBannerArt().getUrl();
            Intrinsics.checkNotNullExpressionValue(url2, "playlist.metadata.bannerArt.url");
            int totalTrackCount = playlist.getMetadata().getTotalTrackCount();
            Integer numValueOf = Integer.valueOf(playlist.getMetadata().getDurationSeconds());
            String version = playlist.getMetadata().getVersion();
            Intrinsics.checkNotNullExpressionValue(version, "playlist.metadata.version");
            return new Playlist(playlistId, title, url, url2, totalTrackCount, numValueOf, version, getExpiryTime(), null, null, null, null, null, null, null, null, null, null, 261888, null);
        }

        public final Playlist convertPlaylistMetadata(PlaylistMetadata metadata) {
            Intrinsics.checkNotNullParameter(metadata, "metadata");
            String playlistId = metadata.getPlaylistId();
            Intrinsics.checkNotNullExpressionValue(playlistId, "metadata.playlistId");
            String title = metadata.getTitle();
            Intrinsics.checkNotNullExpressionValue(title, "metadata.title");
            String url = metadata.getFourSquareImage().getUrl();
            Intrinsics.checkNotNullExpressionValue(url, "metadata.fourSquareImage.url");
            String url2 = metadata.getBannerImage().getUrl();
            Intrinsics.checkNotNullExpressionValue(url2, "metadata.bannerImage.url");
            int totalTrackCount = metadata.getTotalTrackCount();
            Integer numValueOf = Integer.valueOf(metadata.getDurationSeconds());
            String version = metadata.getVersion();
            Intrinsics.checkNotNullExpressionValue(version, "metadata.version");
            return new Playlist(playlistId, title, url, url2, totalTrackCount, numValueOf, version, getExpiryTime(), null, null, null, null, null, null, null, null, null, null, 261888, null);
        }

        public final Playlist convertPersonalizedPlayist(PersonalizedPlaylist playlist) {
            Intrinsics.checkNotNullParameter(playlist, "playlist");
            PlaylistMetadata metadata = playlist.getMetadata();
            String playlistId = metadata.getPlaylistId();
            Intrinsics.checkNotNullExpressionValue(playlistId, "metadata.playlistId");
            String title = metadata.getTitle();
            Intrinsics.checkNotNullExpressionValue(title, "metadata.title");
            String url = metadata.getFourSquareImage().getUrl();
            Intrinsics.checkNotNullExpressionValue(url, "metadata.fourSquareImage.url");
            String url2 = metadata.getBannerImage().getUrl();
            Intrinsics.checkNotNullExpressionValue(url2, "metadata.bannerImage.url");
            int totalTrackCount = metadata.getTotalTrackCount();
            Integer numValueOf = Integer.valueOf(metadata.getDurationSeconds());
            String version = metadata.getVersion();
            Intrinsics.checkNotNullExpressionValue(version, "metadata.version");
            return new Playlist(playlistId, title, url, url2, totalTrackCount, numValueOf, version, getExpiryTime(), Boolean.TRUE, Boolean.valueOf(metadata.isIsCustomerFollowing()), Boolean.valueOf(metadata.isIsPrime()), Boolean.valueOf(metadata.isIsMusicSubscription()), Boolean.valueOf(metadata.isIsNightwing()), Boolean.valueOf(metadata.isIsNightwingOnDemandPlayable()), null, null, null, null, 245760, null);
        }

        public final Playlist convertCatalogPlayist(CatalogPlaylist playlist, boolean isPersonalized) {
            Intrinsics.checkNotNullParameter(playlist, "playlist");
            CatalogPlaylistMetadata metadata = playlist.getMetadata();
            String asin = metadata.getAsin();
            Intrinsics.checkNotNullExpressionValue(asin, "metadata.asin");
            String title = metadata.getTitle();
            Intrinsics.checkNotNullExpressionValue(title, "metadata.title");
            String url = metadata.getFourSquareArt().getUrl();
            Intrinsics.checkNotNullExpressionValue(url, "metadata.fourSquareArt.url");
            String url2 = metadata.getBannerArt().getUrl();
            Intrinsics.checkNotNullExpressionValue(url2, "metadata.bannerArt.url");
            int totalTrackCount = metadata.getTotalTrackCount();
            Integer numValueOf = Integer.valueOf(metadata.getDurationSeconds());
            String version = metadata.getVersion();
            Intrinsics.checkNotNullExpressionValue(version, "metadata.version");
            return new Playlist(asin, title, url, url2, totalTrackCount, numValueOf, version, getExpiryTime(), Boolean.valueOf(isPersonalized), Boolean.valueOf(metadata.isIsCustomerFollowing()), Boolean.valueOf(metadata.isIsPrime()), Boolean.valueOf(metadata.isIsMusicSubscription()), Boolean.valueOf(metadata.isIsNightwing()), Boolean.valueOf(metadata.isIsNightwingOnDemandPlayable()), null, null, null, null, 245760, null);
        }

        private final long getExpiryTime() {
            return TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) + Converters.TTL_SECONDS;
        }
    }
}
