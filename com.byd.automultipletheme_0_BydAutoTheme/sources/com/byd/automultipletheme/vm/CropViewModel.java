package com.byd.automultipletheme.vm;

import android.graphics.Bitmap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.byd.automultipletheme.bean.CarouselPicture;
import com.byd.automultipletheme.common.AutoUtils;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CropViewModel.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\fJ\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\u000eJ\u000e\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\nR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0006R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0006R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/byd/automultipletheme/vm/CropViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "isCancelActionLiveData", "Landroidx/lifecycle/MutableLiveData;", "", "()Landroidx/lifecycle/MutableLiveData;", "isFinishActionLiveData", "localCarouselPictureLiveData", "Landroidx/lifecycle/MediatorLiveData;", "Lcom/byd/automultipletheme/bean/CarouselPicture;", "getCarouselPictureLiveData", "Landroidx/lifecycle/LiveData;", "saveLandWallpaper", "", "path", "", "cropBitmap", "Landroid/graphics/Bitmap;", "savePortWallpaper", "setCarouselPictureLiveData", "carouselPicture", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CropViewModel extends ViewModel {
    private final MutableLiveData<Boolean> isCancelActionLiveData = new MutableLiveData<>(true);
    private final MutableLiveData<Boolean> isFinishActionLiveData = new MutableLiveData<>(true);
    private final MediatorLiveData<CarouselPicture> localCarouselPictureLiveData = new MediatorLiveData<>();

    public final void savePortWallpaper() {
    }

    public final MutableLiveData<Boolean> isCancelActionLiveData() {
        return this.isCancelActionLiveData;
    }

    public final MutableLiveData<Boolean> isFinishActionLiveData() {
        return this.isFinishActionLiveData;
    }

    public final void setCarouselPictureLiveData(CarouselPicture carouselPicture) {
        Intrinsics.checkNotNullParameter(carouselPicture, "carouselPicture");
        this.localCarouselPictureLiveData.setValue(carouselPicture);
    }

    public final LiveData<CarouselPicture> getCarouselPictureLiveData() {
        return this.localCarouselPictureLiveData;
    }

    public final void saveLandWallpaper(String path, Bitmap cropBitmap) throws ExecutionException, InterruptedException {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(cropBitmap, "cropBitmap");
        AutoUtils.INSTANCE.saveBitmap(path, cropBitmap);
    }
}
