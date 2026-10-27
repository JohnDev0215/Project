package com.byd.vrdownload.utils;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class GsonUtils {
    static Gson gson = new Gson();

    public static String toJson(String str) {
        return gson.toJson(str);
    }

    public static String toJson(Map map) {
        return gson.toJson(map);
    }

    public static <T> T fromJson(String str, Class<T> cls) {
        return (T) gson.fromJson(str, (Class) cls);
    }

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String str, Type type) {
        return (T) gson.fromJson(str, type);
    }

    public static <T> T fromJson(Reader reader, Type type) throws JsonSyntaxException, JsonIOException {
        return (T) gson.fromJson(reader, type);
    }
}
