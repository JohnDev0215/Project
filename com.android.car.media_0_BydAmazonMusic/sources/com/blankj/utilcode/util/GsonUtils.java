package com.blankj.utilcode.util;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import spi.b;
import spi.g9;
import spi.j4;
import spi.k4;
import spi.o5;
import spi.vb;

/* JADX INFO: loaded from: classes2.dex */
public final class GsonUtils {
    private static final Map<String, j4> GSONS = new ConcurrentHashMap();
    private static final String KEY_DEFAULT = "defaultGson";
    private static final String KEY_DELEGATE = "delegateGson";
    private static final String KEY_LOG_UTILS = "logUtilsGson";

    private GsonUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    private static j4 createGson() {
        k4 k4Var = new k4();
        k4Var.g = true;
        k4Var.m = false;
        return k4Var.a();
    }

    public static <T> T fromJson(String str, @NonNull Class<T> cls) {
        return (T) fromJson(getGson(), str, (Class) cls);
    }

    public static Type getArrayType(@NonNull Type type) {
        return new vb(new b.a(type)).b;
    }

    public static j4 getGson(String str) {
        return GSONS.get(str);
    }

    public static j4 getGson4LogUtils() {
        Map<String, j4> map = GSONS;
        j4 j4Var = map.get(KEY_LOG_UTILS);
        if (j4Var != null) {
            return j4Var;
        }
        k4 k4Var = new k4();
        k4Var.n = true;
        k4Var.g = true;
        j4 j4VarA = k4Var.a();
        map.put(KEY_LOG_UTILS, j4VarA);
        return j4VarA;
    }

    public static Type getListType(@NonNull Type type) {
        return vb.a(List.class, type).b;
    }

    public static Type getMapType(@NonNull Type type, @NonNull Type type2) {
        return vb.a(Map.class, type, type2).b;
    }

    public static Type getSetType(@NonNull Type type) {
        return vb.a(Set.class, type).b;
    }

    public static Type getType(@NonNull Type type, @NonNull Type... typeArr) {
        return vb.a(type, typeArr).b;
    }

    public static void setGson(String str, j4 j4Var) {
        if (TextUtils.isEmpty(str) || j4Var == null) {
            return;
        }
        GSONS.put(str, j4Var);
    }

    public static void setGsonDelegate(j4 j4Var) {
        if (j4Var == null) {
            return;
        }
        GSONS.put(KEY_DELEGATE, j4Var);
    }

    public static String toJson(Object obj) {
        return toJson(getGson(), obj);
    }

    public static <T> T fromJson(String str, @NonNull Type type) {
        return (T) fromJson(getGson(), str, type);
    }

    public static j4 getGson() {
        Map<String, j4> map = GSONS;
        j4 j4Var = map.get(KEY_DELEGATE);
        if (j4Var != null) {
            return j4Var;
        }
        j4 j4Var2 = map.get(KEY_DEFAULT);
        if (j4Var2 != null) {
            return j4Var2;
        }
        j4 j4VarCreateGson = createGson();
        map.put(KEY_DEFAULT, j4VarCreateGson);
        return j4VarCreateGson;
    }

    public static String toJson(Object obj, @NonNull Type type) {
        return toJson(getGson(), obj, type);
    }

    public static <T> T fromJson(@NonNull Reader reader, @NonNull Class<T> cls) {
        return (T) fromJson(getGson(), reader, (Class) cls);
    }

    public static String toJson(@NonNull j4 j4Var, Object obj) {
        return j4Var.h(obj);
    }

    public static <T> T fromJson(@NonNull Reader reader, @NonNull Type type) {
        return (T) fromJson(getGson(), reader, type);
    }

    public static String toJson(@NonNull j4 j4Var, Object obj, @NonNull Type type) {
        return j4Var.i(obj, type);
    }

    public static <T> T fromJson(@NonNull j4 j4Var, String str, @NonNull Type type) {
        return (T) j4Var.c(str, type);
    }

    public static <T> T fromJson(@NonNull j4 j4Var, Reader reader, @NonNull Class<T> cls) {
        Objects.requireNonNull(j4Var);
        o5 o5Var = new o5(reader);
        o5Var.b = j4Var.n;
        Object objD = j4Var.d(o5Var, cls);
        j4.a(objD, o5Var);
        Map<Class<?>, Class<?>> map = g9.a;
        Objects.requireNonNull(cls);
        Class<T> cls2 = (Class) map.get(cls);
        if (cls2 != null) {
            cls = cls2;
        }
        return cls.cast(objD);
    }

    public static <T> T fromJson(@NonNull j4 j4Var, Reader reader, @NonNull Type type) {
        Objects.requireNonNull(j4Var);
        o5 o5Var = new o5(reader);
        o5Var.b = j4Var.n;
        T t = (T) j4Var.d(o5Var, type);
        j4.a(t, o5Var);
        return t;
    }

    public static <T> T fromJson(@NonNull j4 j4Var, String str, @NonNull Class<T> cls) {
        Object objC = j4Var.c(str, cls);
        Map<Class<?>, Class<?>> map = g9.a;
        Objects.requireNonNull(cls);
        Class<T> cls2 = (Class) map.get(cls);
        if (cls2 != null) {
            cls = cls2;
        }
        return cls.cast(objC);
    }
}
