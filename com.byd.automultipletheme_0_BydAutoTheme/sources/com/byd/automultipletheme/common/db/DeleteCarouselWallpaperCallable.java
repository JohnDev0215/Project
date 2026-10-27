package com.byd.automultipletheme.common.db;

import androidx.core.app.NotificationCompat;
import com.blankj.utilcode.util.FileUtils;
import com.byd.automultipletheme.bean.CarouselPicture;
import com.byd.automultipletheme.common.LogUtils;
import com.byd.automultipletheme.common.WallpaperManagerUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DBUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0016\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\u0002\u0010\u0007J\r\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\tR\u001e\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/byd/automultipletheme/common/db/DeleteCarouselWallpaperCallable;", "Ljava/util/concurrent/Callable;", "", "wallpaperList", "Ljava/util/ArrayList;", "Lcom/byd/automultipletheme/bean/CarouselPicture;", "Lkotlin/collections/ArrayList;", "(Ljava/util/ArrayList;)V", NotificationCompat.CATEGORY_CALL, "()Ljava/lang/Integer;", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DeleteCarouselWallpaperCallable implements Callable<Integer> {
    private ArrayList<CarouselPicture> wallpaperList;

    public DeleteCarouselWallpaperCallable(ArrayList<CarouselPicture> wallpaperList) {
        Intrinsics.checkNotNullParameter(wallpaperList, "wallpaperList");
        this.wallpaperList = wallpaperList;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public Integer call() {
        int i = 0;
        for (CarouselPicture carouselPicture : this.wallpaperList) {
            i++;
        }
        String[] strArr = new String[i];
        Iterator<CarouselPicture> it = this.wallpaperList.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            String landLight = it.next().getLandLight();
            Intrinsics.checkNotNull(landLight);
            strArr[i2] = FileUtils.getFileNameNoExtension(landLight);
            i2++;
        }
        LogUtils.INSTANCE.i("DeleteCarouselList", strArr);
        try {
            WallpaperManagerUtils.getInstance().deleteBitmaps(strArr, 0, 4, true);
            WallpaperManagerUtils.getInstance().deleteBitmaps(strArr, 1, 4, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
}
