package com.amazon.music.downloads;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.amazon.music.downloads.Item;
import com.amazon.music.downloads.notification.NotificationInfo;
import com.google.android.gms.cast.MediaTrack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
class ItemTable {
    ItemTable() {
    }

    public void createTable(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS ItemTable ( request_id TEXT UNIQUE NOT NULL, group_request_id TEXT, asin TEXT, luid TEXT, is_owned TEXT NOT NULL, destination_uri TEXT NOT NULL, title TEXT, description TEXT, image_uri TEXT, is_background TEXT NOT NULL, download_state TEXT NOT NULL, error_reason TEXT NOT NULL, destination_directory TEXT NOT NULL, download_reason TEXT NOT NULL, download_priority TEXT NOT NULL, original_download_state INTEGER DEFAULT NULL);");
    }

    public ModifiableItem load(String str, SQLiteDatabase sQLiteDatabase) {
        Cursor cursorQuery = sQLiteDatabase.query("ItemTable", null, "request_id=?", new String[]{str}, null, null, null);
        try {
            ModifiableItem modifiableItemLoadItem = cursorQuery.moveToFirst() ? loadItem(cursorQuery) : null;
            cursorQuery.close();
            return modifiableItemLoadItem;
        } catch (Throwable unused) {
            cursorQuery.close();
            return null;
        }
    }

    public Map<String, ModifiableItem> loadForGroupId(String str, SQLiteDatabase sQLiteDatabase) {
        Cursor cursorQuery = sQLiteDatabase.query("ItemTable", null, "group_request_id=?", new String[]{str}, null, null, null);
        LinkedHashMap linkedHashMap = new LinkedHashMap(cursorQuery.getCount());
        while (cursorQuery.moveToNext()) {
            linkedHashMap.put(cursorQuery.getString(cursorQuery.getColumnIndex("request_id")), loadItem(cursorQuery));
        }
        cursorQuery.close();
        return linkedHashMap;
    }

    public void add(String str, ModifiableItem modifiableItem, SQLiteDatabase sQLiteDatabase) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("request_id", str);
        contentValues.put("asin", modifiableItem.getAsin());
        contentValues.put("luid", modifiableItem.getLuid());
        contentValues.put("is_owned", Boolean.valueOf(modifiableItem.isOwned()));
        contentValues.put("is_background", Boolean.valueOf(modifiableItem.isBackground()));
        contentValues.put("destination_uri", modifiableItem.getDestinationUri());
        NotificationInfo notificationInfo = modifiableItem.getNotificationInfo();
        if (notificationInfo != null) {
            String title = notificationInfo.getTitle();
            String description = notificationInfo.getDescription();
            String imageUri = notificationInfo.getImageUri();
            if (title != null) {
                contentValues.put("title", title);
            }
            if (description != null) {
                contentValues.put(MediaTrack.ROLE_DESCRIPTION, description);
            }
            if (imageUri != null) {
                contentValues.put("image_uri", imageUri);
            }
        }
        String groupRequestId = modifiableItem.getGroupRequestId();
        if (groupRequestId != null) {
            contentValues.put("group_request_id", groupRequestId);
        }
        contentValues.put("download_state", modifiableItem.getDownloadState().toString());
        contentValues.put("error_reason", modifiableItem.getErrorReason().toString());
        contentValues.put("download_priority", modifiableItem.getPriorityInfo().getPriority());
        contentValues.put("download_reason", modifiableItem.getPriorityInfo().getReason());
        contentValues.put("destination_directory", modifiableItem.getDestinationDirectory());
        contentValues.put("original_download_state", modifiableItem.getOriginalDownloadState());
        sQLiteDatabase.insert("ItemTable", null, contentValues);
    }

    public void remove(Collection<String> collection, SQLiteDatabase sQLiteDatabase) {
        if (collection.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList(collection);
        int size = arrayList.size();
        int i = size / ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
        if (size % ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION > 0) {
            i++;
        }
        sQLiteDatabase.beginTransaction();
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2 * ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
            int i4 = i3 + ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
            if (i4 > size) {
                i4 = size;
            }
            try {
                List listSubList = arrayList.subList(i3, i4);
                StringBuilder sb = new StringBuilder();
                sb.append("request_id");
                sb.append(" IN (");
                Character[] chArr = new Character[listSubList.size()];
                Arrays.fill((Object[]) chArr, (Object) '?');
                sb.append(TextUtils.join(",", chArr));
                sb.append(')');
                sQLiteDatabase.delete("ItemTable", sb.toString(), (String[]) listSubList.toArray(new String[listSubList.size()]));
            } finally {
                sQLiteDatabase.endTransaction();
            }
        }
        sQLiteDatabase.setTransactionSuccessful();
    }

    public void updateDownloadState(String str, DownloadState downloadState, SQLiteDatabase sQLiteDatabase) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("download_state", downloadState.toString());
        sQLiteDatabase.update("ItemTable", contentValues, "request_id=?", new String[]{str});
    }

    public void updateErrorReason(Collection<String> collection, ErrorReason errorReason, SQLiteDatabase sQLiteDatabase) {
        if (collection.isEmpty()) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("error_reason", errorReason.toString());
        ArrayList arrayList = new ArrayList(collection);
        int size = arrayList.size();
        int i = size / ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
        if (size % ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION > 0) {
            i++;
        }
        sQLiteDatabase.beginTransaction();
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2 * ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
            int i4 = i3 + ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
            if (i4 > size) {
                i4 = size;
            }
            try {
                List listSubList = arrayList.subList(i3, i4);
                StringBuilder sb = new StringBuilder();
                sb.append("request_id");
                sb.append(" IN (");
                Character[] chArr = new Character[listSubList.size()];
                Arrays.fill((Object[]) chArr, (Object) '?');
                sb.append(TextUtils.join(",", chArr));
                sb.append(')');
                sQLiteDatabase.update("ItemTable", contentValues, sb.toString(), (String[]) listSubList.toArray(new String[listSubList.size()]));
            } finally {
                sQLiteDatabase.endTransaction();
            }
        }
        sQLiteDatabase.setTransactionSuccessful();
    }

    private ModifiableItem loadItem(Cursor cursor) {
        Item.Builder builder = new Item.Builder();
        builder.addAsin(cursor.getString(cursor.getColumnIndex("asin")));
        builder.addLuid(cursor.getString(cursor.getColumnIndex("luid")));
        builder.setIsOwned(cursor.getString(cursor.getColumnIndex("is_owned")).equalsIgnoreCase("1"));
        builder.addDestinationUri(cursor.getString(cursor.getColumnIndex("destination_uri")));
        builder.addDestinationDirectory(cursor.getString(cursor.getColumnIndex("destination_directory")));
        builder.setIsBackground(cursor.getString(cursor.getColumnIndex("is_background")).equalsIgnoreCase("1"));
        builder.addNotificationInfo(new NotificationInfo(cursor.getString(cursor.getColumnIndex("title")), cursor.getString(cursor.getColumnIndex(MediaTrack.ROLE_DESCRIPTION)), cursor.getString(cursor.getColumnIndex("image_uri"))));
        builder.addPriorityInfo(new PriorityInfo(cursor.getString(cursor.getColumnIndex("download_priority")), cursor.getString(cursor.getColumnIndex("download_reason"))));
        if (!cursor.isNull(cursor.getColumnIndex("original_download_state"))) {
            builder.setOriginalDownloadState(Integer.valueOf(cursor.getInt(cursor.getColumnIndex("original_download_state"))));
        } else {
            builder.setOriginalDownloadState(null);
        }
        Item itemBuild = builder.build();
        DownloadState downloadStateValueOf = DownloadState.valueOf(cursor.getString(cursor.getColumnIndex("download_state")));
        ErrorReason errorReasonValueOf = ErrorReason.valueOf(cursor.getString(cursor.getColumnIndex("error_reason")));
        String string = cursor.getString(cursor.getColumnIndex("group_request_id"));
        ModifiableItem modifiableItem = new ModifiableItem(itemBuild);
        modifiableItem.setDownloadState(downloadStateValueOf);
        modifiableItem.setErrorReason(errorReasonValueOf);
        modifiableItem.setGroupRequestId(string);
        return modifiableItem;
    }
}
