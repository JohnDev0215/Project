package com.amazon.mp3.playlist;

import android.content.Context;
import android.database.Cursor;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.util.DbUtil;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes3.dex */
public class PlaylistRequestUtil {
    public static JSONArray idListFromCursor(Cursor c) {
        JSONArray jSONArray = new JSONArray();
        if (c != null) {
            try {
                int columnIndexOrThrow = c.getColumnIndexOrThrow("track_luid");
                while (c.moveToNext() && jSONArray.length() < PlaylistConfigurationStorage.get().getMaximumNumberOfTracksInPlaylist()) {
                    jSONArray.put(c.getString(columnIndexOrThrow));
                }
            } finally {
                DbUtil.closeCursor(c);
            }
        }
        return jSONArray;
    }

    public static long generatePlaylistId(String objectId) {
        return ((long) objectId.hashCode()) + 2147483647L;
    }

    public static void waitForPlaylistSync(Context context) {
        SyncService.waitForSync(context, 17);
    }
}
