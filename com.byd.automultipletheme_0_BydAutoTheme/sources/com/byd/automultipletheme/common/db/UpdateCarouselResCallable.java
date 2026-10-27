package com.byd.automultipletheme.common.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import androidx.core.app.NotificationCompat;
import com.blankj.utilcode.util.Utils;
import com.byd.automultipletheme.bean.CarouselPicture;
import com.byd.automultipletheme.common.IntentString;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DBUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0016\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\u0002\u0010\u0007J\r\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\tR\u001e\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/byd/automultipletheme/common/db/UpdateCarouselResCallable;", "Ljava/util/concurrent/Callable;", "", IntentString.DATA_LIST, "Ljava/util/ArrayList;", "Lcom/byd/automultipletheme/bean/CarouselPicture;", "Lkotlin/collections/ArrayList;", "(Ljava/util/ArrayList;)V", NotificationCompat.CATEGORY_CALL, "()Ljava/lang/Integer;", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UpdateCarouselResCallable implements Callable<Integer> {
    private ArrayList<CarouselPicture> list;

    public UpdateCarouselResCallable(ArrayList<CarouselPicture> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.list = list;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public Integer call() {
        ContentResolver contentResolver = Utils.getApp().getContentResolver();
        Uri uri = Uri.parse(DB.CAROUSEL_RES_URI);
        ContentValues contentValues = new ContentValues();
        for (CarouselPicture carouselPicture : this.list) {
            contentValues.put(DB.CAROUSEL_RES_PATH0, carouselPicture.getPortLight());
            contentValues.put(DB.CAROUSEL_RES_PATH1, carouselPicture.getPortNight());
            contentValues.put(DB.CAROUSEL_RES_PATH2, carouselPicture.getLandLight());
            contentValues.put(DB.CAROUSEL_RES_PATH3, carouselPicture.getLandNight());
            contentResolver.insert(uri, contentValues);
        }
        return 0;
    }
}
