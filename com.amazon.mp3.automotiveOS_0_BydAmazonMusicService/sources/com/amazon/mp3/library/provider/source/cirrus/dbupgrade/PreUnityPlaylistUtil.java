package com.amazon.mp3.library.provider.source.cirrus.dbupgrade;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.VisibleForTesting;

/* JADX INFO: loaded from: classes3.dex */
public class PreUnityPlaylistUtil {
    @VisibleForTesting(otherwise = 2)
    Cursor getDownloadedPlaylistsTracksCursor(final SQLiteDatabase preUnityDb, final long playlistId) {
        return preUnityDb.query("Track INNER JOIN PlaylistTrack AS p ON p.track_luid = luid", new String[]{"Track.luid", "Track.asin", "Track.album_asin", "Track.artist_asin", "Track.title", "Track.sort_title", "Track.album", "Track.sort_album", "Track.album_id", "Track.album_art_id", "Track.album_artist", "Track.sort_album_artist", "Track.album_artist_id", "Track.artist", "Track.sort_artist", "Track.artist_id", "Track.track_num", "Track.disc_num", "Track.date_created", "Track.extension", "Track.size", "Track.duration", "Track.bookmark", "Track.is_podcast", "Track.match_hash", "Track.marketplace", "Track.lyrics_state", "Track.status", "Track.prime_status", "Track.ownership_status", "Track.genre", "Track.genre_id", "Track.source", "Track.download_state", "Track.luid_hash", "Track.local_uri"}, "udo_playlist_id =? AND local_uri NOT NULL", new String[]{String.valueOf(playlistId)}, null, null, null, null);
    }

    @VisibleForTesting(otherwise = 2)
    Cursor getDownloadedUdoPlaylistsCursor(final SQLiteDatabase preUnityDb) {
        String[] strArr = new String[PreUnityPlaylist.COLUMNS.length];
        int i = 0;
        while (true) {
            String[] strArr2 = PreUnityPlaylist.COLUMNS;
            if (i < strArr2.length) {
                strArr[i] = "p." + strArr2[i];
                i++;
            } else {
                return preUnityDb.query("Playlist AS p INNER JOIN Playlist AS l ON p.name = l.name AND p.luid != l.luid AND p.type = l.type", strArr, "l.source =? AND p.type =?", new String[]{Integer.toString(1), "vnd.android.cursor.item/vnd.amazonmp3.udoplaylist"}, null, null, null, null);
            }
        }
    }

    @VisibleForTesting(otherwise = 2)
    Cursor getDownloadedPrimePlaylistsCursor(final SQLiteDatabase sourceDb) {
        return getDownloadedPlaylistsCursor(sourceDb, "vnd.android.cursor.item/vnd.amazonmp3.primeplaylist");
    }

    @VisibleForTesting(otherwise = 2)
    Cursor getDownloadedSharedPlaylistsCursor(final SQLiteDatabase sourceDb) {
        return getDownloadedPlaylistsCursor(sourceDb, "com.amazon.musicplaylist.model#SharedPlaylist");
    }

    @VisibleForTesting
    Cursor getDeviceSidePlaylistsCursor(final SQLiteDatabase preUnityDb) {
        return preUnityDb.rawQuery("select _id, asin, name from Playlist where name in (select name from Playlist group by name having count(*) == 1) and is_prime = 0 and is_music_subscription = 0 and luid like 'localPlaylist-%'", null);
    }

    private Cursor getDownloadedPlaylistsCursor(final SQLiteDatabase preUnityDb, final String contentItemType) {
        return preUnityDb.query("Playlist", PreUnityPlaylist.COLUMNS, "download_state =? AND type = ?", new String[]{Integer.toString(0), contentItemType}, null, null, null, null);
    }

    Cursor getDownloadedNonPlaylistTracksCursor(final SQLiteDatabase preUnityDB) {
        return preUnityDB.query("Track", PreUnityTrack.FIRE_SOURCE_COLUMNS, "local_uri NOT NULL AND NOT EXISTS ( SELECT _id FROM PlaylistTrack WHERE PlaylistTrack.track_luid = Track.luid )", null, null, null, null, null);
    }
}
