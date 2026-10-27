package com.byd.automultipletheme.common.db;

import androidx.core.app.NotificationCompat;
import com.byd.automultipletheme.bean.AutoWallpaper;
import com.byd.automultipletheme.bean.CarouselPicture;
import com.byd.automultipletheme.common.AssetsUtils;
import com.byd.automultipletheme.common.Const;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: DBUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00030\u0002j\b\u0012\u0004\u0012\u00020\u0003`\u00040\u0001B\u0005¢\u0006\u0002\u0010\u0005J\u0018\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0002j\b\u0012\u0004\u0012\u00020\u0003`\u0004H\u0016¨\u0006\u0007"}, d2 = {"Lcom/byd/automultipletheme/common/db/QueryAssetsCarouselResCallable;", "Ljava/util/concurrent/Callable;", "Ljava/util/ArrayList;", "Lcom/byd/automultipletheme/bean/CarouselPicture;", "Lkotlin/collections/ArrayList;", "()V", NotificationCompat.CATEGORY_CALL, "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class QueryAssetsCarouselResCallable implements Callable<ArrayList<CarouselPicture>> {
    @Override // java.util.concurrent.Callable
    public ArrayList<CarouselPicture> call() {
        ArrayList<CarouselPicture> arrayList = new ArrayList<>();
        AutoWallpaper autoWallpaper = AssetsUtils.INSTANCE.getAssetsAutoWallpaper().get(0);
        Intrinsics.checkNotNullExpressionValue(autoWallpaper, "AssetsUtils.getAssetsAutoWallpaper()[0]");
        AutoWallpaper autoWallpaper2 = autoWallpaper;
        if (AssetsUtils.INSTANCE.copyFilesFromAssets("res/wallpaper/110000010", Const.CAROUSEL)) {
            List<String> land = autoWallpaper2.getImgUrl().getLand();
            List<String> port = autoWallpaper2.getImgUrl().getPort();
            int size = land.size();
            for (int i = 0; i < size; i++) {
                arrayList.add(new CarouselPicture(StringsKt.replace$default(port.get(i), "file:///android_asset/res/wallpaper/110000010", Const.CAROUSEL, false, 4, (Object) null), StringsKt.replace$default(port.get(i), "file:///android_asset/res/wallpaper/110000010", Const.CAROUSEL, false, 4, (Object) null), StringsKt.replace$default(land.get(i), "file:///android_asset/res/wallpaper/110000010", Const.CAROUSEL, false, 4, (Object) null), StringsKt.replace$default(land.get(i), "file:///android_asset/res/wallpaper/110000010", Const.CAROUSEL, false, 4, (Object) null), false, null, 48, null));
            }
            DBUtils.INSTANCE.setCarouselRes(arrayList);
        }
        return arrayList;
    }
}
