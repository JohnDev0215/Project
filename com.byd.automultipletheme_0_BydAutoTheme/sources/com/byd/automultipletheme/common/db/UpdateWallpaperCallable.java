package com.byd.automultipletheme.common.db;

import android.content.ContentValues;
import android.net.Uri;
import androidx.core.app.NotificationCompat;
import com.blankj.utilcode.util.Utils;
import com.byd.automultipletheme.common.IntentString;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DBUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002¢\u0006\u0002\u0010\u0007J\r\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0002X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0002X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/byd/automultipletheme/common/db/UpdateWallpaperCallable;", "Ljava/util/concurrent/Callable;", "", "path", "", IntentString.DATA_TYPE, "resId", "(Ljava/lang/String;II)V", NotificationCompat.CATEGORY_CALL, "()Ljava/lang/Integer;", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UpdateWallpaperCallable implements Callable<Integer> {
    private String path;
    private int resId;
    private int type;

    public UpdateWallpaperCallable(String path, int i, int i2) {
        Intrinsics.checkNotNullParameter(path, "path");
        this.path = path;
        this.type = i;
        this.resId = i2;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public Integer call() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", (Integer) 1);
        contentValues.put(DB.WALLPAPER_EXT_PATH, this.path);
        contentValues.put(DB.WALLPAPER_EXT_TYPE, Integer.valueOf(this.type));
        contentValues.put("res_id", Integer.valueOf(this.resId));
        contentValues.put(DB.WALLPAPER_EXT_APP_VERSION, "");
        contentValues.put(DB.WALLPAPER_EXT_APP_CODE, "");
        Utils.getApp().getContentResolver().insert(Uri.parse(DB.WALLPAPER_EXT_URI), contentValues);
        return 1;
    }
}
