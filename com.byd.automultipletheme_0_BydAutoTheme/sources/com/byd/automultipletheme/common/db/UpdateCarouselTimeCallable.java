package com.byd.automultipletheme.common.db;

import android.content.ContentValues;
import android.net.Uri;
import androidx.core.app.NotificationCompat;
import com.blankj.utilcode.util.Utils;
import java.util.concurrent.Callable;
import kotlin.Metadata;

/* JADX INFO: compiled from: DBUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004J\r\u0010\u0005\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\u0006R\u000e\u0010\u0003\u001a\u00020\u0002X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/byd/automultipletheme/common/db/UpdateCarouselTimeCallable;", "Ljava/util/concurrent/Callable;", "", DB.CAROUSEL_TIME_TIME, "(I)V", NotificationCompat.CATEGORY_CALL, "()Ljava/lang/Integer;", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UpdateCarouselTimeCallable implements Callable<Integer> {
    private int time;

    public UpdateCarouselTimeCallable(int i) {
        this.time = i;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public Integer call() {
        Uri uri = Uri.parse(DB.CAROUSEL_TIME_URI);
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", (Integer) 1);
        contentValues.put(DB.CAROUSEL_TIME_TIME, Integer.valueOf(this.time));
        Utils.getApp().getContentResolver().insert(uri, contentValues);
        return 0;
    }
}
