package com.airbnb.lottie;

import android.graphics.Bitmap;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public interface ImageAssetDelegate {
    @Nullable
    Bitmap fetchBitmap(LottieImageAsset lottieImageAsset);
}
