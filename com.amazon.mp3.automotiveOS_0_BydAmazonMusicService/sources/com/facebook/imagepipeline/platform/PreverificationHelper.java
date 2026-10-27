package com.facebook.imagepipeline.platform;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import com.facebook.soloader.DoNotOptimize;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
@DoNotOptimize
class PreverificationHelper {
    PreverificationHelper() {
    }

    @DoNotOptimize
    @TargetApi(26)
    boolean shouldUseHardwareBitmapConfig(@Nullable Bitmap.Config config) {
        return config == Bitmap.Config.HARDWARE;
    }
}
