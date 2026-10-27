package com.byd.automultipletheme.common.db;

import android.content.ContentResolver;
import android.net.Uri;
import androidx.core.app.NotificationCompat;
import com.blankj.utilcode.util.Utils;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DBUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\r\u0010\u0004\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/byd/automultipletheme/common/db/DeleteAllCarouselCallable;", "Ljava/util/concurrent/Callable;", "", "()V", NotificationCompat.CATEGORY_CALL, "()Ljava/lang/Integer;", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DeleteAllCarouselCallable implements Callable<Integer> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public Integer call() {
        Uri uri = Uri.parse(DB.CAROUSEL_RES_URI);
        ContentResolver contentResolver = Utils.getApp().getContentResolver();
        Intrinsics.checkNotNullExpressionValue(contentResolver, "getApp().contentResolver");
        return Integer.valueOf(contentResolver.delete(uri, null, null));
    }
}
