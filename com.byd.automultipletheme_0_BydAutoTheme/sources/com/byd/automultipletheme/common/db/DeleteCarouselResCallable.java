package com.byd.automultipletheme.common.db;

import android.net.Uri;
import androidx.core.app.NotificationCompat;
import com.blankj.utilcode.util.Utils;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DBUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\r\u0010\u0006\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/byd/automultipletheme/common/db/DeleteCarouselResCallable;", "Ljava/util/concurrent/Callable;", "", DB.CAROUSEL_RES_PATH0, "", "(Ljava/lang/String;)V", NotificationCompat.CATEGORY_CALL, "()Ljava/lang/Integer;", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DeleteCarouselResCallable implements Callable<Integer> {
    private String path0;

    public DeleteCarouselResCallable(String path0) {
        Intrinsics.checkNotNullParameter(path0, "path0");
        this.path0 = path0;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public Integer call() {
        return Integer.valueOf(Utils.getApp().getContentResolver().delete(Uri.parse(DB.CAROUSEL_RES_URI), "path0 = ?", new String[]{this.path0 + ""}));
    }
}
