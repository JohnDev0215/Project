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
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B5\u0012\u0016\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006\u0012\u0016\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\u0002\u0010\bJ\r\u0010\t\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\nR\u001e\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/byd/automultipletheme/common/db/DeleteCarouselCallable;", "Ljava/util/concurrent/Callable;", "", "wallpaperList", "Ljava/util/ArrayList;", "Lcom/byd/automultipletheme/bean/CarouselPicture;", "Lkotlin/collections/ArrayList;", "photoList", "(Ljava/util/ArrayList;Ljava/util/ArrayList;)V", NotificationCompat.CATEGORY_CALL, "()Ljava/lang/Integer;", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DeleteCarouselCallable implements Callable<Integer> {
    private ArrayList<CarouselPicture> photoList;
    private ArrayList<CarouselPicture> wallpaperList;

    public DeleteCarouselCallable(ArrayList<CarouselPicture> wallpaperList, ArrayList<CarouselPicture> photoList) {
        Intrinsics.checkNotNullParameter(wallpaperList, "wallpaperList");
        Intrinsics.checkNotNullParameter(photoList, "photoList");
        this.wallpaperList = wallpaperList;
        this.photoList = photoList;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public Integer call() {
        int i = 0;
        for (CarouselPicture carouselPicture : this.wallpaperList) {
            i++;
        }
        for (CarouselPicture carouselPicture2 : this.photoList) {
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
        Iterator<CarouselPicture> it2 = this.photoList.iterator();
        while (it2.hasNext()) {
            String landLight2 = it2.next().getLandLight();
            Intrinsics.checkNotNull(landLight2);
            strArr[i2] = FileUtils.getFileNameNoExtension(landLight2);
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
