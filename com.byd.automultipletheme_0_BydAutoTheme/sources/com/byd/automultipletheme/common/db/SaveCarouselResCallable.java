package com.byd.automultipletheme.common.db;

import androidx.core.app.NotificationCompat;
import com.blankj.utilcode.util.FileUtils;
import com.byd.automultipletheme.bean.CarouselPicture;
import com.byd.automultipletheme.common.AssetsUtils;
import com.byd.automultipletheme.common.AutoUtils;
import com.byd.automultipletheme.common.Const;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DBUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B5\u0012\u0016\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006\u0012\u0016\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\u0002\u0010\bJ\r\u0010\t\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\nR\u001e\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/byd/automultipletheme/common/db/SaveCarouselResCallable;", "Ljava/util/concurrent/Callable;", "", "wallpaperList", "Ljava/util/ArrayList;", "Lcom/byd/automultipletheme/bean/CarouselPicture;", "Lkotlin/collections/ArrayList;", "photoList", "(Ljava/util/ArrayList;Ljava/util/ArrayList;)V", NotificationCompat.CATEGORY_CALL, "()Ljava/lang/Integer;", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SaveCarouselResCallable implements Callable<Integer> {
    private ArrayList<CarouselPicture> photoList;
    private ArrayList<CarouselPicture> wallpaperList;

    public SaveCarouselResCallable(ArrayList<CarouselPicture> wallpaperList, ArrayList<CarouselPicture> photoList) {
        Intrinsics.checkNotNullParameter(wallpaperList, "wallpaperList");
        Intrinsics.checkNotNullParameter(photoList, "photoList");
        this.wallpaperList = wallpaperList;
        this.photoList = photoList;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public Integer call() {
        DBUtils.INSTANCE.deleteAllCarouselRes();
        ArrayList<CarouselPicture> arrayList = new ArrayList<>();
        for (CarouselPicture item : this.wallpaperList) {
            AssetsUtils assetsUtils = AssetsUtils.INSTANCE;
            String portLight = item.getPortLight();
            Intrinsics.checkNotNull(portLight);
            assetsUtils.copyFilesFromAssets(portLight, Const.CAROUSEL_WALLPAPER);
            AssetsUtils assetsUtils2 = AssetsUtils.INSTANCE;
            String portNight = item.getPortNight();
            Intrinsics.checkNotNull(portNight);
            assetsUtils2.copyFilesFromAssets(portNight, Const.CAROUSEL_WALLPAPER_NIGHT);
            AssetsUtils assetsUtils3 = AssetsUtils.INSTANCE;
            String landLight = item.getLandLight();
            Intrinsics.checkNotNull(landLight);
            assetsUtils3.copyFilesFromAssets(landLight, Const.CAROUSEL_WALLPAPER_LAND);
            AssetsUtils assetsUtils4 = AssetsUtils.INSTANCE;
            String landNight = item.getLandNight();
            Intrinsics.checkNotNull(landNight);
            assetsUtils4.copyFilesFromAssets(landNight, Const.CAROUSEL_WALLPAPER_LAND_NIGHT);
            AutoUtils autoUtils = AutoUtils.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(item, "item");
            arrayList.add(autoUtils.switchCarousel(item));
        }
        Iterator<CarouselPicture> it = this.photoList.iterator();
        while (it.hasNext()) {
            String pathName = AssetsUtils.INSTANCE.getPathName(it.next().getPortLight());
            String str = "/storage/emulated/0/Android/data/com.byd.automultipletheme/files/res/wallpaper/110000010/port/" + pathName;
            String str2 = "/storage/emulated/0/Android/data/com.byd.automultipletheme/files/res/wallpaper/110000010/land/" + pathName;
            if (AutoUtils.INSTANCE.getModelType() == 3) {
                if (FileUtils.isFileExists(str2)) {
                    arrayList.add(new CarouselPicture(str, str, str2, str2, false, null, 48, null));
                }
            } else if (FileUtils.isFileExists(str) && FileUtils.isFileExists(str2)) {
                arrayList.add(new CarouselPicture(str, str, str2, str2, false, null, 48, null));
            }
        }
        DBUtils.INSTANCE.saveCarouselPath();
        return Integer.valueOf(DBUtils.INSTANCE.setCarouselRes(arrayList));
    }
}
