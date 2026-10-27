package com.blankj.utilcode.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: GsonUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Map<String, Gson> f2613a = new ConcurrentHashMap();

    private static Gson a() {
        return new GsonBuilder().serializeNulls().disableHtmlEscaping().create();
    }

    public static <T> T b(Gson gson, String str, Class<T> cls) {
        return (T) gson.fromJson(str, (Class) cls);
    }

    public static <T> T c(Gson gson, String str, Type type) {
        return (T) gson.fromJson(str, type);
    }

    public static <T> T d(String str, Class<T> cls) {
        return (T) b(f(), str, cls);
    }

    public static <T> T e(String str, Type type) {
        return (T) c(f(), str, type);
    }

    public static Gson f() {
        Map<String, Gson> map = f2613a;
        Gson gson = map.get("delegateGson");
        if (gson != null) {
            return gson;
        }
        Gson gson2 = map.get("defaultGson");
        if (gson2 != null) {
            return gson2;
        }
        Gson gsonA = a();
        map.put("defaultGson", gsonA);
        return gsonA;
    }

    public static Type g(Type type) {
        return TypeToken.getParameterized(List.class, type).getType();
    }

    public static String h(Gson gson, Object obj) {
        return gson.toJson(obj);
    }

    public static String i(Object obj) {
        return h(f(), obj);
    }
}
