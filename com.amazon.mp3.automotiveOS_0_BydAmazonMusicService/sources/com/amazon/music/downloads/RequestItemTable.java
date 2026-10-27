package com.amazon.music.downloads;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.music.downloads.RequestItem;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
class RequestItemTable {
    RequestItemTable() {
    }

    public void createTable(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS RequestItemTable ( request_id TEXT UNIQUE NOT NULL, request_type TEXT NOT NULL, is_background TEXT NOT NULL, timestamp INTEGER PRIMARY KEY);");
    }

    public RequestItem generateRequestItem(String str, Group group, SQLiteDatabase sQLiteDatabase) {
        return generateRequestItem(str, RequestItem.RequestType.GROUP, group.isBackground(), sQLiteDatabase);
    }

    public RequestItem generateRequestItem(String str, Item item, SQLiteDatabase sQLiteDatabase) {
        return generateRequestItem(str, RequestItem.RequestType.ITEM, item.isBackground(), sQLiteDatabase);
    }

    public Set<RequestItem> loadRequestItems(SQLiteDatabase sQLiteDatabase) {
        Cursor cursorQuery = sQLiteDatabase.query("RequestItemTable", null, null, null, null, null, TetheredMessageKey.timestamp);
        HashSet hashSet = new HashSet();
        while (cursorQuery.moveToNext()) {
            hashSet.add(new RequestItem(cursorQuery.getString(cursorQuery.getColumnIndex("request_id")), RequestItem.RequestType.valueOf(cursorQuery.getString(cursorQuery.getColumnIndex("request_type"))), Boolean.parseBoolean(cursorQuery.getString(cursorQuery.getColumnIndex("is_background"))), cursorQuery.getInt(cursorQuery.getColumnIndex(TetheredMessageKey.timestamp))));
        }
        cursorQuery.close();
        return hashSet;
    }

    public void removeRequestItem(String str, SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.delete("RequestItemTable", "request_id=?", new String[]{str});
    }

    private RequestItem generateRequestItem(String str, RequestItem.RequestType requestType, boolean z, SQLiteDatabase sQLiteDatabase) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("request_id", str);
        contentValues.put("request_type", requestType.toString());
        contentValues.put("is_background", Boolean.toString(z));
        return new RequestItem(str, requestType, z, sQLiteDatabase.insert("RequestItemTable", TetheredMessageKey.timestamp, contentValues));
    }
}
