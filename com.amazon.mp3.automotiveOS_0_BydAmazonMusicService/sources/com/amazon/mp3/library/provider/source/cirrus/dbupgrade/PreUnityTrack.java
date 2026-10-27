package com.amazon.mp3.library.provider.source.cirrus.dbupgrade;

import android.database.Cursor;
import androidx.core.app.NotificationCompat;
import com.amazon.mp3.configuration.IntlConfiguration;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.find.utils.PageUriUtils;

/* JADX INFO: loaded from: classes3.dex */
class PreUnityTrack {
    static final String[] FIRE_SOURCE_COLUMNS = {"luid", "asin", "album_asin", "artist_asin", "title", "sort_title", "album", "sort_album", "album_id", "album_art_id", "album_artist", "sort_album_artist", "album_artist_id", "artist", "sort_artist", "artist_id", "track_num", "disc_num", "date_created", "extension", "size", "duration", "bookmark", "is_podcast", "match_hash", IntlConfiguration.MARKETPLACE, "lyrics_state", NotificationCompat.CATEGORY_STATUS, "prime_status", "ownership_status", ParserUtil.GENRE_SEGMENT_NAME, "genre_id", PageUriUtils.SOURCE_QUERY_PARAM, "download_state", "luid_hash", "local_uri"};
    final String album;
    final int albumArtId;
    final String albumArtist;
    final int albumArtistId;
    final String albumAsin;
    final int albumId;
    final String artist;
    final String artistAsin;
    final int artistId;
    final String asin;
    final int bookmark;
    final long dateCreated;
    final int discNum;
    final int downloadState;
    final int duration;
    final String extension;
    final String genre;
    final int genreId;
    final int isPodcast;
    final String localUri;
    final String luid;
    final int luidHash;
    final int lyricsState;
    final String marketplace;
    final int matchHash;
    final int ownershipStatus;
    final int primeStatus;
    final int size;
    final String sortAlbum;
    final String sortAlbumArtist;
    final String sortArtist;
    final String sortTitle;
    final int source;
    final int status;
    final String title;
    final int trackNum;

    public PreUnityTrack(Cursor tracksToMigrateCursor) {
        this.luid = tracksToMigrateCursor.getString(0);
        this.asin = tracksToMigrateCursor.getString(1);
        this.albumAsin = tracksToMigrateCursor.getString(2);
        this.artistAsin = tracksToMigrateCursor.getString(3);
        this.title = tracksToMigrateCursor.getString(4);
        this.sortTitle = tracksToMigrateCursor.getString(5);
        this.album = tracksToMigrateCursor.getString(6);
        this.sortAlbum = tracksToMigrateCursor.getString(7);
        this.albumId = tracksToMigrateCursor.getInt(8);
        this.albumArtId = tracksToMigrateCursor.getInt(9);
        this.albumArtist = tracksToMigrateCursor.getString(10);
        this.sortAlbumArtist = tracksToMigrateCursor.getString(11);
        this.albumArtistId = tracksToMigrateCursor.getInt(12);
        this.artist = tracksToMigrateCursor.getString(13);
        this.sortArtist = tracksToMigrateCursor.getString(14);
        this.artistId = tracksToMigrateCursor.getInt(15);
        this.trackNum = tracksToMigrateCursor.getInt(16);
        this.discNum = tracksToMigrateCursor.getInt(17);
        this.dateCreated = tracksToMigrateCursor.getLong(18);
        this.extension = tracksToMigrateCursor.getString(19);
        this.size = tracksToMigrateCursor.getInt(20);
        this.duration = tracksToMigrateCursor.getInt(21);
        this.bookmark = tracksToMigrateCursor.getInt(22);
        this.isPodcast = tracksToMigrateCursor.getInt(23);
        this.matchHash = tracksToMigrateCursor.getInt(24);
        this.marketplace = tracksToMigrateCursor.getString(25);
        this.lyricsState = tracksToMigrateCursor.getInt(26);
        this.status = tracksToMigrateCursor.getInt(27);
        this.primeStatus = tracksToMigrateCursor.getInt(28);
        this.ownershipStatus = tracksToMigrateCursor.getInt(29);
        this.genre = tracksToMigrateCursor.getString(30);
        this.genreId = tracksToMigrateCursor.getInt(31);
        this.source = tracksToMigrateCursor.getInt(32);
        this.downloadState = tracksToMigrateCursor.getInt(33);
        this.luidHash = tracksToMigrateCursor.getInt(34);
        this.localUri = tracksToMigrateCursor.getString(35);
    }

    public String toString() {
        return "Track{luid='" + this.luid + "', asin='" + this.asin + "', albumAsin='" + this.albumAsin + "', artistAsin='" + this.artistAsin + "', title='" + this.title + "', sortTitle='" + this.sortTitle + "', album='" + this.album + "', sortAlbum='" + this.sortAlbum + "', albumId=" + this.albumId + ", albumArtId=" + this.albumArtId + ", albumArtist='" + this.albumArtist + "', sortAlbumArtist='" + this.sortAlbumArtist + "', albumArtistId=" + this.albumArtistId + ", artist='" + this.artist + "', sortArtist='" + this.sortArtist + "', artistId=" + this.artistId + ", trackNum=" + this.trackNum + ", discNum=" + this.discNum + ", dateCreated=" + this.dateCreated + ", extension='" + this.extension + "', size=" + this.size + ", duration=" + this.duration + ", bookmark=" + this.bookmark + ", isPodcast=" + this.isPodcast + ", matchHash=" + this.matchHash + ", marketplace='" + this.marketplace + "', lyricsState=" + this.lyricsState + ", status=" + this.status + ", primeStatus=" + this.primeStatus + ", ownershipStatus=" + this.ownershipStatus + ", genre='" + this.genre + "', genreId=" + this.genreId + ", source=" + this.source + ", downloadState=" + this.downloadState + ", luidHash=" + this.luidHash + ", localUri='" + this.localUri + "', isOwned: " + isOwned() + ", isInLibrary: " + isInLibrary() + ", isCatalogTrack: " + isCatalogTrack() + ", isUploadedToCloud: " + isUploadedToCloud() + ", isSideloaded: " + isSideloaded() + '}';
    }

    public boolean isSideloaded() {
        return this.asin == null && this.luid.startsWith("local-");
    }

    public boolean isUploadedToCloud() {
        return isOwned() && !this.luid.startsWith("local-");
    }

    public boolean isCatalogTrack() {
        return (this.asin == null || isOwned() || (!isInLibrary() && !isPrime())) ? false : true;
    }

    public boolean isOwned() {
        return ContentOwnershipStatus.fromValue(this.ownershipStatus).isOwned();
    }

    public boolean isInLibrary() {
        return ContentOwnershipStatus.fromValue(this.ownershipStatus).isInLibrary();
    }

    public boolean isPrime() {
        return ContentCatalogStatus.fromValue(this.primeStatus).isPrime();
    }
}
