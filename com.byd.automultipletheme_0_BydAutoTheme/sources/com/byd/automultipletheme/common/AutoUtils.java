package com.byd.automultipletheme.common;

import android.app.UiStyleConfig;
import android.graphics.Bitmap;
import android.hardware.bydauto.properties.CarSettings;
import android.os.SystemProperties;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.Utils;
import com.byd.automultipletheme.bean.AutoTheme;
import com.byd.automultipletheme.bean.AutoWallpaper;
import com.byd.automultipletheme.bean.CarouselPicture;
import com.byd.automultipletheme.bean.PreviewImage;
import com.byd.automultipletheme.common.db.DBUtils;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AutoUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002J\u0006\u0010\u000e\u001a\u00020\u0006J\u0006\u0010\u000f\u001a\u00020\u0006J\b\u0010\u0010\u001a\u00020\u0006H\u0002J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u0016\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0019J\u0006\u0010\u001a\u001a\u00020\u001bJ\u0006\u0010\u001c\u001a\u00020\u001bJ\u000e\u0010\u001d\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u001f\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/byd/automultipletheme/common/AutoUtils;", "Lcom/byd/automultipletheme/common/AutoTools;", "()V", "executorService", "Ljava/util/concurrent/ExecutorService;", "index", "", "convertAutoThemeToAutoWallpaper", "Lcom/byd/automultipletheme/bean/AutoWallpaper;", "autoTheme", "Lcom/byd/automultipletheme/bean/AutoTheme;", "getCarouselPath", "", "assetsImagePath", "getIndex", "getModelType", "getModelValue", "isCarouselFile", "", "carouselPicture", "Lcom/byd/automultipletheme/bean/CarouselPicture;", "isPhotoCarouselFile", "saveBitmap", "path", "cropBitmap", "Landroid/graphics/Bitmap;", "sendMsgToMeter", "", "setAutoType", "setBYDCurrentThemeId", "setIndex", "switchCarousel", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AutoUtils extends AutoTools {
    public static final AutoUtils INSTANCE = new AutoUtils();
    private static ExecutorService executorService;
    private static int index;

    public final void sendMsgToMeter() {
    }

    private AutoUtils() {
    }

    static {
        ExecutorService executorServiceNewCachedThreadPool = Executors.newCachedThreadPool();
        Intrinsics.checkNotNullExpressionValue(executorServiceNewCachedThreadPool, "newCachedThreadPool()");
        executorService = executorServiceNewCachedThreadPool;
    }

    public final int getIndex() {
        return index;
    }

    public final void setIndex(int index2) {
        index = index2;
    }

    public final boolean setBYDCurrentThemeId(AutoTheme autoTheme) {
        Intrinsics.checkNotNullParameter(autoTheme, "autoTheme");
        boolean zPutInt = CarSettings.UserTableData.putInt(Utils.getApp().getContentResolver(), CarSettings.UserTableData.BYD_APP_THEME, autoTheme.getRsId());
        AutoWallpaper autoWallpaperConvertAutoThemeToAutoWallpaper = convertAutoThemeToAutoWallpaper(autoTheme);
        DBUtils.INSTANCE.setWallpaper(Const.CONTENT_WALLPAPER_PATH, autoWallpaperConvertAutoThemeToAutoWallpaper.getRsType(), autoWallpaperConvertAutoThemeToAutoWallpaper.getRsId(), new Function1<Integer, Unit>() { // from class: com.byd.automultipletheme.common.AutoUtils.setBYDCurrentThemeId.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                LogUtils.INSTANCE.i("setWallpaper is done!");
            }
        });
        LogUtils.INSTANCE.i(Boolean.valueOf(zPutInt));
        return true;
    }

    public final AutoWallpaper convertAutoThemeToAutoWallpaper(AutoTheme autoTheme) {
        Intrinsics.checkNotNullParameter(autoTheme, "autoTheme");
        int modelType = getModelType();
        if (modelType == 2) {
            return new AutoWallpaper(new PreviewImage(autoTheme.getImgUrl().getOcean().getWallpaper().getLand(), autoTheme.getImgUrl().getOcean().getWallpaper().getPort(), autoTheme.getImgUrl().getOcean().getWallpaper()), autoTheme.getRsId(), autoTheme.getRsName(), 2);
        }
        if (modelType == 3) {
            return new AutoWallpaper(new PreviewImage(autoTheme.getImgUrl().getDenza().getWallpaper().getLand(), autoTheme.getImgUrl().getDenza().getWallpaper().getPort(), autoTheme.getImgUrl().getDenza().getWallpaper()), autoTheme.getRsId(), autoTheme.getRsName(), 2);
        }
        return new AutoWallpaper(new PreviewImage(autoTheme.getImgUrl().getDynasty().getWallpaper().getLand(), autoTheme.getImgUrl().getDynasty().getWallpaper().getPort(), autoTheme.getImgUrl().getDynasty().getWallpaper()), autoTheme.getRsId(), autoTheme.getRsName(), 2);
    }

    public final int getModelType() {
        if (UiStyleConfig.getInstance(Utils.getApp()).getUiStyle() == 1) {
            return 3;
        }
        return Const.INSTANCE.getAutoType() != 196 ? 2 : 1;
    }

    public final void setAutoType() {
        try {
            Const r0 = Const.INSTANCE;
            String str = SystemProperties.get("persist.sys.AutoType");
            Intrinsics.checkNotNullExpressionValue(str, "get(\"persist.sys.AutoType\")");
            r0.setAutoType(Integer.parseInt(str));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final int getModelValue() {
        return UiStyleConfig.getInstance(Utils.getApp()).getUiStyle();
    }

    private final String getCarouselPath(String assetsImagePath) {
        return StringsKt.replace$default(assetsImagePath, "file:///android_asset/res/wallpaper/110000010", Const.CAROUSEL, false, 4, (Object) null);
    }

    public final boolean isCarouselFile(CarouselPicture carouselPicture) {
        Intrinsics.checkNotNullParameter(carouselPicture, "carouselPicture");
        Iterator<CarouselPicture> it = DBUtils.INSTANCE.getCarouselRes().iterator();
        boolean z = false;
        while (it.hasNext()) {
            String portLight = it.next().getPortLight();
            String portLight2 = carouselPicture.getPortLight();
            Intrinsics.checkNotNull(portLight2);
            if (Intrinsics.areEqual(portLight, getCarouselPath(portLight2))) {
                z = true;
            }
        }
        return z;
    }

    public final boolean isPhotoCarouselFile(CarouselPicture carouselPicture) {
        Intrinsics.checkNotNullParameter(carouselPicture, "carouselPicture");
        boolean z = false;
        for (CarouselPicture carouselPicture2 : DBUtils.INSTANCE.getCarouselRes()) {
            String fileName = FileUtils.getFileName(carouselPicture2.getPortLight());
            String dirName = FileUtils.getDirName(carouselPicture2.getPortLight());
            AssetsUtils.INSTANCE.getPathName(carouselPicture.getPortLight());
            if (StringsKt.contains$default((CharSequence) (dirName + fileName), (CharSequence) AssetsUtils.INSTANCE.getPathName(carouselPicture.getPortLight()), false, 2, (Object) null)) {
                z = true;
            }
        }
        return z;
    }

    public final CarouselPicture switchCarousel(CarouselPicture carouselPicture) {
        Intrinsics.checkNotNullParameter(carouselPicture, "carouselPicture");
        String portLight = carouselPicture.getPortLight();
        Intrinsics.checkNotNull(portLight);
        String carouselPath = getCarouselPath(portLight);
        String portNight = carouselPicture.getPortNight();
        Intrinsics.checkNotNull(portNight);
        String carouselPath2 = getCarouselPath(portNight);
        String landLight = carouselPicture.getLandLight();
        Intrinsics.checkNotNull(landLight);
        String carouselPath3 = getCarouselPath(landLight);
        String landNight = carouselPicture.getLandNight();
        Intrinsics.checkNotNull(landNight);
        return new CarouselPicture(carouselPath, carouselPath2, carouselPath3, getCarouselPath(landNight), false, null, 48, null);
    }

    public final boolean saveBitmap(String path, Bitmap cropBitmap) throws ExecutionException, InterruptedException {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(cropBitmap, "cropBitmap");
        Object obj = executorService.submit(new SaveCropBitmapCallable(path, cropBitmap)).get();
        Intrinsics.checkNotNullExpressionValue(obj, "executorService.submit(S…(path, cropBitmap)).get()");
        return ((Boolean) obj).booleanValue();
    }
}
