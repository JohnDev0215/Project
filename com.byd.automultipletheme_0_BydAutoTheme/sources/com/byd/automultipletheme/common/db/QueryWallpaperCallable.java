package com.byd.automultipletheme.common.db;

import android.database.Cursor;
import android.net.Uri;
import androidx.core.app.NotificationCompat;
import com.blankj.utilcode.util.Utils;
import com.byd.automultipletheme.common.Const;
import java.util.concurrent.Callable;
import kotlin.Metadata;

/* JADX INFO: compiled from: DBUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\r\u0010\u0004\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/byd/automultipletheme/common/db/QueryWallpaperCallable;", "Ljava/util/concurrent/Callable;", "", "()V", NotificationCompat.CATEGORY_CALL, "()Ljava/lang/Integer;", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class QueryWallpaperCallable implements Callable<Integer> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public Integer call() {
        String[] strArr = {Const.CarEv};
        Cursor cursorQuery = Utils.getApp().getContentResolver().query(Uri.parse(DB.WALLPAPER_EXT_URI), null, "_id= ?", strArr, null);
        int i = -1;
        if (cursorQuery != null) {
            if (cursorQuery.getCount() > 0) {
                while (cursorQuery.moveToNext()) {
                    i = cursorQuery.getInt(cursorQuery.getColumnIndex("res_id"));
                }
            }
            cursorQuery.close();
        }
        return Integer.valueOf(i);
    }
}
