package com.android.systemui.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public class ReflectUtil {
    public static Field getDeclaredField(Class<?> clazz, String name) throws NoSuchFieldException {
        Field declaredField = clazz.getDeclaredField(name);
        declaredField.setAccessible(true);
        return declaredField;
    }

    public static Constructor<?> getDeclaredConstructor(Class<?> clazz, Class<?> param) throws NoSuchMethodException {
        Constructor<?> declaredConstructor = clazz.getDeclaredConstructor(param);
        declaredConstructor.setAccessible(true);
        return declaredConstructor;
    }
}
