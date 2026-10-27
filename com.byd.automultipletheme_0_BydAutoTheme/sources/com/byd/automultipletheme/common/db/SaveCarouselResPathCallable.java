package com.byd.automultipletheme.common.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import androidx.core.app.NotificationCompat;
import com.blankj.utilcode.util.Utils;
import com.byd.automultipletheme.common.Const;
import com.byd.automultipletheme.common.LogUtils;
import java.util.concurrent.Callable;
import kotlin.Metadata;

/* JADX INFO: compiled from: DBUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\r\u0010\u0004\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/byd/automultipletheme/common/db/SaveCarouselResPathCallable;", "Ljava/util/concurrent/Callable;", "", "()V", NotificationCompat.CATEGORY_CALL, "()Ljava/lang/Integer;", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SaveCarouselResPathCallable implements Callable<Integer> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public Integer call() {
        ContentResolver contentResolver = Utils.getApp().getContentResolver();
        Uri uri = Uri.parse(DB.CAROUSEL_SAVE_URI);
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", (Integer) 1);
        contentValues.put("res_id", Integer.valueOf(Const.APP_WALLPAPER_DEFAULT_ID));
        contentValues.put(DB.CAROUSEL_SAVE_RES_PATH, Const.CAROUSEL);
        contentResolver.insert(uri, contentValues);
        LogUtils.INSTANCE.i("SaveCarouselResPathCallable");
        return 0;
    }
}
