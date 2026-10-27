package com.byd.spi.service;

import android.content.Context;
import androidx.annotation.NonNull;
import com.byd.foundation.utils.AssetsUtils;
import com.byd.foundation.utils.LogUtils;
import com.byd.spi.factory.ServiceFactory;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class AssetsServiceLoader {
    private static final String TAG = "-AssetsServiceLoader";

    private static class Holder {
        private static final AssetsServiceLoader INSTANCE = new AssetsServiceLoader();

        private Holder() {
        }
    }

    private AssetsServiceLoader() {
    }

    public static AssetsServiceLoader with() {
        return Holder.INSTANCE;
    }

    public <T> T loadService(Context context, @NonNull Class<T> cls) {
        try {
            return (T) ServiceFactory.with().createService(context, Class.forName(AssetsUtils.readSingleLine(context, cls.getName())));
        } catch (Throwable th) {
            LogUtils.e("DiSDK-Foundation-SPI-AssetsServiceLoader", "loadService error with message:" + th.getMessage());
            return null;
        }
    }

    public <T> List<T> loadServices(Context context, @NonNull Class<T> cls) {
        List<String> lines = AssetsUtils.readLines(context, cls.getName());
        ArrayList arrayList = new ArrayList();
        ServiceFactory serviceFactoryWith = ServiceFactory.with();
        for (String str : lines) {
            try {
                Object objCreateService = serviceFactoryWith.createService(context, Class.forName(str));
                if (objCreateService != null) {
                    arrayList.add(objCreateService);
                }
            } catch (Throwable th) {
                LogUtils.e("DiSDK-Foundation-SPI-AssetsServiceLoader", "loadServices error provider is: " + str + ", error message:" + th.getMessage());
            }
        }
        return arrayList;
    }
}
