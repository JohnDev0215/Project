package com.amazon.mp3.library.provider.source.cirrus.dbupgrade;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.amazon.mp3.library.provider.MediaProvider;

/* JADX INFO: loaded from: classes3.dex */
class SharedPlaylistsMigrationHelper extends PrimePlaylistMigrationHelper {
    public SharedPlaylistsMigrationHelper(final Context context, final SQLiteDatabase preUnityDb, final MigrationListener migrationListener) {
        super(context, preUnityDb, migrationListener);
    }

    @Override // com.amazon.mp3.library.provider.source.cirrus.dbupgrade.PrimePlaylistMigrationHelper, com.amazon.mp3.library.provider.source.cirrus.dbupgrade.PlaylistsMigrationHelper
    Cursor getDownloadedPlaylistsCursor() {
        return this.mPreUnityPlaylistUtil.getDownloadedSharedPlaylistsCursor(this.mPreUnityDb);
    }

    @Override // com.amazon.mp3.library.provider.source.cirrus.dbupgrade.PrimePlaylistMigrationHelper, com.amazon.mp3.library.provider.source.cirrus.dbupgrade.PlaylistsMigrationHelper
    Uri getPlaylistContentUri(final String playlistAsin, final String name) {
        return MediaProvider.SharedUserPlaylists.getContentUri("cirrus", playlistAsin);
    }
}
