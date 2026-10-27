package com.byd.automultipletheme.common;

import android.graphics.Bitmap;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WallpaperUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/byd/automultipletheme/common/WallpaperUtils;", "", "()V", "executorService", "Ljava/util/concurrent/ExecutorService;", "getBitmap", "Landroid/graphics/Bitmap;", "url", "", "getSunSetTimeType", "", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class WallpaperUtils {
    public static final WallpaperUtils INSTANCE = new WallpaperUtils();
    private static ExecutorService executorService;

    private WallpaperUtils() {
    }

    static {
        ExecutorService executorServiceNewCachedThreadPool = Executors.newCachedThreadPool();
        Intrinsics.checkNotNullExpressionValue(executorServiceNewCachedThreadPool, "newCachedThreadPool()");
        executorService = executorServiceNewCachedThreadPool;
    }

    public final Bitmap getBitmap(String url) {
        return (Bitmap) executorService.submit(new WallpaperBitmapCallable(url)).get();
    }

    public final int getSunSetTimeType() {
        switch (Calendar.getInstance().get(11)) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
                return 3;
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                return 0;
            case 11:
            case 12:
            default:
                return 1;
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
                return 2;
        }
    }
}
