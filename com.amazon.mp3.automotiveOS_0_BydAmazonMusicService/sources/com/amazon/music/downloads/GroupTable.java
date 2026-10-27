package com.amazon.music.downloads;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.amazon.music.downloads.notification.NotificationInfo;
import com.google.android.gms.cast.MediaTrack;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
class GroupTable {
    GroupTable() {
    }

    public void createTable(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS GroupTable ( request_id TEXT UNIQUE NOT NULL, is_background INTEGER NOT NULL, download_state TEXT NOT NULL, error_reason TEXT NOT NULL, title TEXT, description TEXT, image_uri TEXT);");
    }

    public ModifiableGroup load(String str, ItemTable itemTable, SQLiteDatabase sQLiteDatabase) {
        Cursor cursorQuery = sQLiteDatabase.query("GroupTable", null, "request_id=?", new String[]{str}, null, null, null);
        try {
            ModifiableGroup modifiableGroupLoadGroup = cursorQuery.moveToFirst() ? loadGroup(cursorQuery, itemTable, sQLiteDatabase) : null;
            cursorQuery.close();
            return modifiableGroupLoadGroup;
        } catch (Throwable unused) {
            cursorQuery.close();
            return null;
        }
    }

    public void add(String str, ModifiableGroup modifiableGroup, ItemTable itemTable, SQLiteDatabase sQLiteDatabase) {
        Map<String, ModifiableItem> itemMap = modifiableGroup.getItemMap();
        if (itemMap == null || itemMap.isEmpty()) {
            return;
        }
        sQLiteDatabase.beginTransaction();
        try {
            for (Map.Entry<String, ModifiableItem> entry : itemMap.entrySet()) {
                itemTable.add(entry.getKey(), entry.getValue(), sQLiteDatabase);
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("request_id", str);
            contentValues.put("is_background", Boolean.valueOf(modifiableGroup.isBackground()));
            contentValues.put("download_state", modifiableGroup.getDownloadState().toString());
            contentValues.put("error_reason", modifiableGroup.getErrorReason().toString());
            NotificationInfo notificationInfo = modifiableGroup.getNotificationInfo();
            if (notificationInfo != null) {
                String title = notificationInfo.getTitle();
                String description = notificationInfo.getDescription();
                String imageUri = notificationInfo.getImageUri();
                if (title != null) {
                    contentValues.put("title", notificationInfo.getTitle());
                }
                if (description != null) {
                    contentValues.put(MediaTrack.ROLE_DESCRIPTION, notificationInfo.getDescription());
                }
                if (imageUri != null) {
                    contentValues.put("image_uri", notificationInfo.getImageUri());
                }
            }
            sQLiteDatabase.insert("GroupTable", null, contentValues);
            sQLiteDatabase.setTransactionSuccessful();
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    public void remove(Collection<String> collection, SQLiteDatabase sQLiteDatabase) {
        if (collection.isEmpty()) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("request_id");
        sb.append(" IN (");
        Character[] chArr = new Character[collection.size()];
        Arrays.fill((Object[]) chArr, (Object) '?');
        sb.append(TextUtils.join(",", chArr));
        sb.append(')');
        sQLiteDatabase.delete("GroupTable", sb.toString(), (String[]) collection.toArray(new String[collection.size()]));
    }

    public void updateDownloadState(String str, DownloadState downloadState, SQLiteDatabase sQLiteDatabase) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("download_state", downloadState.toString());
        sQLiteDatabase.update("GroupTable", contentValues, "request_id=?", new String[]{str});
    }

    public void updateErrorReason(Collection<String> collection, ErrorReason errorReason, SQLiteDatabase sQLiteDatabase) {
        if (collection.isEmpty()) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("error_reason", errorReason.toString());
        StringBuilder sb = new StringBuilder();
        sb.append("request_id");
        sb.append(" IN (");
        Character[] chArr = new Character[collection.size()];
        Arrays.fill((Object[]) chArr, (Object) '?');
        sb.append(TextUtils.join(",", chArr));
        sb.append(')');
        sQLiteDatabase.update("GroupTable", contentValues, sb.toString(), (String[]) collection.toArray(new String[collection.size()]));
    }

    private ModifiableGroup loadGroup(Cursor cursor, ItemTable itemTable, SQLiteDatabase sQLiteDatabase) {
        String string = cursor.getString(cursor.getColumnIndex("request_id"));
        ModifiableGroup modifiableGroup = new ModifiableGroup(itemTable.loadForGroupId(string, sQLiteDatabase), new NotificationInfo(cursor.getString(cursor.getColumnIndex("title")), cursor.getString(cursor.getColumnIndex(MediaTrack.ROLE_DESCRIPTION)), cursor.getString(cursor.getColumnIndex("image_uri"))), cursor.getString(cursor.getColumnIndex("is_background")).equalsIgnoreCase("1"));
        modifiableGroup.setDownloadState(DownloadState.valueOf(cursor.getString(cursor.getColumnIndex("download_state"))));
        modifiableGroup.setErrorReason(ErrorReason.valueOf(cursor.getString(cursor.getColumnIndex("error_reason"))));
        return modifiableGroup;
    }
}
