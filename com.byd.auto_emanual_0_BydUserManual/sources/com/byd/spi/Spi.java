package com.byd.spi;

import android.content.Context;
import com.byd.foundation.utils.LogUtils;
import com.byd.spi.config.SpiConfig;
import java.util.List;
import spi.a;
import spi.c;

/* JADX INFO: loaded from: classes.dex */
public class Spi {
    private static SpiConfig sConfig;

    public static void addService(Class cls, Class cls2) {
        c.a().a(cls, cls2);
    }

    public static SpiConfig getConfig() {
        SpiConfig spiConfig = sConfig;
        if (spiConfig != null) {
            return spiConfig;
        }
        throw new IllegalArgumentException("Spi config can not be null!");
    }

    public static <T> T getService(Context context, Class<T> cls) {
        return (T) c.a().a(context, cls);
    }

    public static <T> List<T> getServices(Context context, Class<T> cls) {
        return c.a().b(context, cls);
    }

    public static void init(SpiConfig spiConfig) {
        if (spiConfig == null) {
            throw new IllegalArgumentException("Spi config can not be null!");
        }
        sConfig = spiConfig;
        LogUtils.i(a.e, "spi init success, version:1.1.1");
    }

    public static void addService(Class cls, Object obj) {
        c.a().a(cls, obj);
    }
}
