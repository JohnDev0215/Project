package com.byd.spi.service;

import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class ServiceMatcher {
    private static Map<Class, List<ServiceCreator>> serviceCreatorHashMap = new HashMap();

    static {
        registerServiceCreator();
    }

    public static <T> T getService(Context context, Class<T> cls) {
        List<ServiceCreator> list = serviceCreatorHashMap.get(cls);
        if (list == null || list.size() <= 0) {
            return null;
        }
        return (T) list.get(0).createService(context);
    }

    public static <T> List<T> getServices(Context context, Class<T> cls) {
        List<ServiceCreator> list = serviceCreatorHashMap.get(cls);
        if (list == null || list.size() <= 0) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Object objCreateService = list.get(i).createService(context);
            if (objCreateService != null) {
                arrayList.add(objCreateService);
            }
        }
        return arrayList;
    }

    private static void registerServiceCreator() {
    }
}
