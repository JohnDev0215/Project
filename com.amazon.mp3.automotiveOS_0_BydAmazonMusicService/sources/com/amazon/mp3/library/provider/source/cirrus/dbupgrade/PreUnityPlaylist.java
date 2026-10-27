package com.amazon.mp3.library.provider.source.cirrus.dbupgrade;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/* JADX INFO: loaded from: classes3.dex */
class PreUnityPlaylist {
    static final String[] COLUMNS = {"_id", "asin", "name"};
    final String playlistAsin;
    final long playlistId;
    final String playlistName;

    public PreUnityPlaylist(final Cursor preUnityPlaylistCursor) {
        int columnIndex = preUnityPlaylistCursor.getColumnIndex("_id");
        int columnIndex2 = preUnityPlaylistCursor.getColumnIndex("asin");
        int columnIndex3 = preUnityPlaylistCursor.getColumnIndex("name");
        this.playlistId = preUnityPlaylistCursor.getLong(columnIndex);
        this.playlistAsin = preUnityPlaylistCursor.getString(columnIndex2);
        this.playlistName = preUnityPlaylistCursor.getString(columnIndex3);
    }

    int getNumberOfTracksToMigrate(final SQLiteDatabase preUnityDb) {
        Cursor downloadedPlaylistsTracksCursor = new PreUnityPlaylistUtil().getDownloadedPlaylistsTracksCursor(preUnityDb, this.playlistId);
        try {
            int count = downloadedPlaylistsTracksCursor.getCount();
            downloadedPlaylistsTracksCursor.close();
            return count;
        } catch (Throwable th) {
            if (downloadedPlaylistsTracksCursor != null) {
                try {
                    downloadedPlaylistsTracksCursor.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public String toString() {
        return "PreUnityPlaylist{playlistId=" + this.playlistId + ", playlistAsin='" + this.playlistAsin + "', playlistName='" + this.playlistName + "'}";
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PreUnityPlaylist preUnityPlaylist = (PreUnityPlaylist) o;
        if (this.playlistId != preUnityPlaylist.playlistId) {
            return false;
        }
        String str = this.playlistAsin;
        if (str == null ? preUnityPlaylist.playlistAsin != null : !str.equals(preUnityPlaylist.playlistAsin)) {
            return false;
        }
        String str2 = this.playlistName;
        String str3 = preUnityPlaylist.playlistName;
        return str2 != null ? str2.equals(str3) : str3 == null;
    }

    public int hashCode() {
        long j = this.playlistId;
        int i = ((int) (j ^ (j >>> 32))) * 31;
        String str = this.playlistAsin;
        int iHashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.playlistName;
        return iHashCode + (str2 != null ? str2.hashCode() : 0);
    }
}
