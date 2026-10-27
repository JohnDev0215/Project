package com.byd.automultipletheme.common.db;

import androidx.core.app.NotificationCompat;
import com.byd.automultipletheme.bean.CarouselPicture;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DBUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0016\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\u0002\u0010\u0007J\r\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\tR\u001e\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/byd/automultipletheme/common/db/SaveAllCarouselResCallable;", "Ljava/util/concurrent/Callable;", "", "wallpaperList", "Ljava/util/ArrayList;", "Lcom/byd/automultipletheme/bean/CarouselPicture;", "Lkotlin/collections/ArrayList;", "(Ljava/util/ArrayList;)V", NotificationCompat.CATEGORY_CALL, "()Ljava/lang/Integer;", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SaveAllCarouselResCallable implements Callable<Integer> {
    private ArrayList<CarouselPicture> wallpaperList;

    public SaveAllCarouselResCallable(ArrayList<CarouselPicture> wallpaperList) {
        Intrinsics.checkNotNullParameter(wallpaperList, "wallpaperList");
        this.wallpaperList = wallpaperList;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public Integer call() {
        DBUtils.INSTANCE.deleteAllCarouselRes();
        ArrayList<CarouselPicture> arrayList = new ArrayList<>();
        for (CarouselPicture item : this.wallpaperList) {
            Intrinsics.checkNotNullExpressionValue(item, "item");
            arrayList.add(item);
        }
        DBUtils.INSTANCE.saveCarouselPath();
        return Integer.valueOf(DBUtils.INSTANCE.setCarouselRes(arrayList));
    }
}
