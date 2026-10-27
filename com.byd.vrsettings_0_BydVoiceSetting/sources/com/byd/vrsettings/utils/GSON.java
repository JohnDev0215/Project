package com.byd.vrsettings.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
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
        ArrayList arrayList = new ArrayList();
        Iterator<JsonElement> it = JsonParser.parseString(str).getAsJsonArray().iterator();
        while (it.hasNext()) {
            arrayList.add(get().fromJson(it.next(), (Class) cls));
        }
        return arrayList;
    }

    public static <T> Map<String, T> toMap(String str) {
        return (Map) get().fromJson(str, new TypeToken<Map<String, T>>() { // from class: com.byd.vrsettings.utils.GSON.1
        }.getType());
    }
}
