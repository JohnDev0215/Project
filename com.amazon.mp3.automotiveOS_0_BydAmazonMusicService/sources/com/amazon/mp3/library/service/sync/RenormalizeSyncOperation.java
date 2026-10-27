package com.amazon.mp3.library.service.sync;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.StringUtil;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class RenormalizeSyncOperation extends SyncOperation {
    public RenormalizeSyncOperation(Context context, SQLiteDatabase db) {
        super(context, db);
    }

    @Override // com.amazon.mp3.library.service.sync.SyncOperation
    int sync() {
        Cursor cursorQuery = getContext().getContentResolver().query(MediaProvider.Artists.getContentUri("cirrus-local"), new String[]{"name", "_id"}, "sort_album_artist=?", new String[]{""}, null);
        if (cursorQuery != null) {
            try {
                int columnIndex = cursorQuery.getColumnIndex("name");
                int columnIndex2 = cursorQuery.getColumnIndex("_id");
                HashMap map = new HashMap();
                if (cursorQuery.moveToFirst()) {
                    do {
                        String string = cursorQuery.getString(columnIndex);
                        map.put(Long.valueOf(cursorQuery.getLong(columnIndex2)), StringUtil.normalizeArtistName(string));
                    } while (cursorQuery.moveToNext());
                    SQLiteDatabase database = getDatabase();
                    ContentValues contentValues = new ContentValues();
                    DbUtil.beginTransaction(database);
                    try {
                        for (Map.Entry entry : map.entrySet()) {
                            contentValues.put("sort_album_artist", (String) entry.getValue());
                            database.update("Track", contentValues, "album_artist_id=?", new String[]{((Long) entry.getKey()).toString()});
                            contentValues.clear();
                        }
                        database.setTransactionSuccessful();
                        database.endTransaction();
                    } catch (Throwable th) {
                        database.endTransaction();
                        throw th;
                    }
                }
            } finally {
                cursorQuery.close();
            }
        }
        SettingsUtil.setHasRenormalized(getContext(), true);
        return 0;
    }
}
