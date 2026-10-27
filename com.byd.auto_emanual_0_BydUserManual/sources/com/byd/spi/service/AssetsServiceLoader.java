package com.byd.spi.service;

import android.content.Context;
import com.byd.foundation.utils.AssetsUtils;
import com.byd.foundation.utils.LogUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import spi.d;

/* JADX INFO: loaded from: classes.dex */
public class AssetsServiceLoader {
    private static final String TAG = "-AssetsServiceLoader";

    public static class Holder {
        private static final AssetsServiceLoader INSTANCE = new AssetsServiceLoader();

        private Holder() {
        }
    }

    public static AssetsServiceLoader with() {
        return Holder.INSTANCE;
    }

    public <T> T loadService(Context context, Class<T> cls) {
        try {
            return (T) d.a().a(context, Class.forName(AssetsUtils.readSingleLine(context, cls.getName())));
        } catch (Throwable th) {
            LogUtils.w("foundation.spi-AssetsServiceLoader", "spi load service from config error:" + cls.getName(), th);
            return null;
        }
    }

    public <T> List<T> loadServices(Context context, Class<T> cls) {
        List<String> lines = AssetsUtils.readLines(context, cls.getName());
        ArrayList arrayList = new ArrayList();
        d dVarA = d.a();
        Iterator<String> it = lines.iterator();
        while (it.hasNext()) {
            try {
                Object objA = dVarA.a(context, Class.forName(it.next()));
                if (objA != null) {
                    arrayList.add(objA);
                }
            } catch (Throwable th) {
                LogUtils.w("foundation.spi-AssetsServiceLoader", "spi load service from config error:" + cls.getName(), th);
            }
        }
        return arrayList;
    }

    private AssetsServiceLoader() {
    }
}
