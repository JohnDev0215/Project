package com.byd.automultipletheme.common;

import android.graphics.Bitmap;
import androidx.core.app.NotificationCompat;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WallpaperUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\n\u0010\u0006\u001a\u0004\u0018\u00010\u0002H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/byd/automultipletheme/common/WallpaperBitmapCallable;", "Ljava/util/concurrent/Callable;", "Landroid/graphics/Bitmap;", "url", "", "(Ljava/lang/String;)V", NotificationCompat.CATEGORY_CALL, "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class WallpaperBitmapCallable implements Callable<Bitmap> {
    private String url;

    public WallpaperBitmapCallable(String str) {
        this.url = str;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public Bitmap call() {
        RequestOptions requestOptionsCenterCrop = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).override(ScreenUtils.getScreenWidth(), ScreenUtils.getScreenHeight()).format(DecodeFormat.PREFER_ARGB_8888).centerCrop();
        Intrinsics.checkNotNullExpressionValue(requestOptionsCenterCrop, "RequestOptions().diskCac…            .centerCrop()");
        RequestBuilder<Bitmap> requestBuilderLoad = Glide.with(Utils.getApp()).asBitmap().load(this.url);
        Intrinsics.checkNotNullExpressionValue(requestBuilderLoad, "with(Utils.getApp()).asBitmap().load(url)");
        return requestBuilderLoad.apply((BaseRequestOptions<?>) requestOptionsCenterCrop).submit().get();
    }
}
