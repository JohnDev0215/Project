package com.byd.car;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import com.byd.car.internal.DiCarInternal;
import com.byd.spi.Spi;

/* JADX INFO: loaded from: classes2.dex */
public class DiCar {
    public static <T> T getCarManager(@NonNull Context context, @NonNull Class<T> cls) {
        return (T) Spi.getService(context, cls);
    }

    public static void init(DiCarConfig diCarConfig) {
        Log.i(DiCarLog.TAG_CAR, "DiCar init begin, version:2.0.0-beta.2");
        if (diCarConfig == null) {
            throw new IllegalArgumentException("DiCar config can not be null!");
        }
        DiCarInternal.init(diCarConfig);
        Log.i(DiCarLog.TAG_CAR, "DiCar init success, version:2.0.0-beta.2");
    }
}
