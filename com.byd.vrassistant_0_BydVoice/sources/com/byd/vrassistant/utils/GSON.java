package com.byd.vrassistant.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class GSON {
    private static WeakReference<Gson> instance;

    private GSON() {
    }

    public static Gson get() {
        WeakReference<Gson> weakReference = instance;
        if (weakReference == null || weakReference.get() == null) {
            instance = new WeakReference<>(new Gson());
        }
        return instance.get();
    }

    public static String toJson(Object obj) {
        return get().toJson(obj);
    }

    public static <T> List<T> fromJsonList(String str, Class<T> cls) {
        return (List) new Gson().fromJson(str, new TypeToken<List<T>>() { // from class: com.byd.vrassistant.utils.GSON.1
        }.getType());
    }

    public static <T> Map<String, T> toMap(String str) {
        return (Map) get().fromJson(str, new TypeToken<Map<String, T>>() { // from class: com.byd.vrassistant.utils.GSON.2
        }.getType());
    }
}
