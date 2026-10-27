package com.byd.spi;

import android.content.Context;
import androidx.annotation.NonNull;
import com.byd.foundation.utils.LogUtils;
import com.byd.spi.config.SpiConfig;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class Spi {
    private static String TAG = "SDK-Car-HiScene";
    private static SpiConfig sConfig;

    public static void addService(@NonNull Class cls, @NonNull Class cls2) {
        ServiceManager.with().addService(cls, cls2);
    }

    public static void addService(@NonNull Class cls, @NonNull Object obj) {
        ServiceManager.with().addService(cls, obj);
    }

    public static SpiConfig getConfig() {
        SpiConfig spiConfig = sConfig;
        if (spiConfig != null) {
            return spiConfig;
        }
        throw new IllegalArgumentException("Spi config can not be null!");
    }

    public static <T> T getService(@NonNull Context context, @NonNull Class<T> cls) {
        return (T) ServiceManager.with().getService(context, cls);
    }

    public static <T> List<T> getServices(@NonNull Context context, @NonNull Class<T> cls) {
        return ServiceManager.with().getServices(context, cls);
    }

    public static void init(SpiConfig spiConfig) {
        if (spiConfig == null) {
            throw new IllegalArgumentException("Spi config can not be null!");
        }
        sConfig = spiConfig;
        LogUtils.i(BuildConfig.TAG, "SPI版本信息:1.0.8");
    }
}
