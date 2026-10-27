package com.android.inputmethod.compat;

import android.text.TextUtils;
import android.util.Log;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public final class CompatUtils {
    private static final String TAG = "CompatUtils";

    private CompatUtils() {
    }

    public static Class<?> getClass(final String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static Method getMethod(final Class<?> targetClass, final String name, final Class<?>... parameterTypes) {
        if (targetClass != null && !TextUtils.isEmpty(name)) {
            try {
                return targetClass.getMethod(name, parameterTypes);
            } catch (NoSuchMethodException | SecurityException unused) {
            }
        }
        return null;
    }

    public static Field getField(final Class<?> targetClass, final String name) {
        if (targetClass != null && !TextUtils.isEmpty(name)) {
            try {
                return targetClass.getField(name);
            } catch (NoSuchFieldException | SecurityException unused) {
            }
        }
        return null;
    }

    public static Constructor<?> getConstructor(final Class<?> targetClass, final Class<?>... types) {
        if (targetClass != null && types != null) {
            try {
                return targetClass.getConstructor(types);
            } catch (NoSuchMethodException | SecurityException unused) {
            }
        }
        return null;
    }

    public static Object newInstance(final Constructor<?> constructor, final Object... args) {
        if (constructor == null) {
            return null;
        }
        try {
            return constructor.newInstance(args);
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | InvocationTargetException e) {
            Log.e(TAG, "Exception in newInstance", e);
            return null;
        }
    }

    public static Object invoke(final Object receiver, final Object defaultValue, final Method method, final Object... args) {
        if (method == null) {
            return defaultValue;
        }
        try {
            return method.invoke(receiver, args);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            Log.e(TAG, "Exception in invoke", e);
            return defaultValue;
        }
    }

    public static Object getFieldValue(final Object receiver, final Object defaultValue, final Field field) {
        if (field == null) {
            return defaultValue;
        }
        try {
            return field.get(receiver);
        } catch (IllegalAccessException | IllegalArgumentException e) {
            Log.e(TAG, "Exception in getFieldValue", e);
            return defaultValue;
        }
    }

    public static void setFieldValue(final Object receiver, final Field field, final Object value) {
        if (field == null) {
            return;
        }
        try {
            field.set(receiver, value);
        } catch (IllegalAccessException | IllegalArgumentException e) {
            Log.e(TAG, "Exception in setFieldValue", e);
        }
    }

    public static ClassWrapper getClassWrapper(final String className) {
        return new ClassWrapper(getClass(className));
    }

    public static final class ClassWrapper {
        private final Class<?> mClass;

        public ClassWrapper(final Class<?> targetClass) {
            this.mClass = targetClass;
        }

        public boolean exists() {
            return this.mClass != null;
        }

        public <T> ToObjectMethodWrapper<T> getMethod(final String name, final T defaultValue, final Class<?>... parameterTypes) {
            return new ToObjectMethodWrapper<>(CompatUtils.getMethod(this.mClass, name, parameterTypes), defaultValue);
        }

        public ToIntMethodWrapper getPrimitiveMethod(final String name, final int defaultValue, final Class<?>... parameterTypes) {
            return new ToIntMethodWrapper(CompatUtils.getMethod(this.mClass, name, parameterTypes), defaultValue);
        }

        public ToFloatMethodWrapper getPrimitiveMethod(final String name, final float defaultValue, final Class<?>... parameterTypes) {
            return new ToFloatMethodWrapper(CompatUtils.getMethod(this.mClass, name, parameterTypes), defaultValue);
        }

        public ToBooleanMethodWrapper getPrimitiveMethod(final String name, final boolean defaultValue, final Class<?>... parameterTypes) {
            return new ToBooleanMethodWrapper(CompatUtils.getMethod(this.mClass, name, parameterTypes), defaultValue);
        }
    }

    public static final class ToObjectMethodWrapper<T> {
        private final T mDefaultValue;
        private final Method mMethod;

        public ToObjectMethodWrapper(final Method method, final T defaultValue) {
            this.mMethod = method;
            this.mDefaultValue = defaultValue;
        }

        public T invoke(Object obj, Object... objArr) {
            return (T) CompatUtils.invoke(obj, this.mDefaultValue, this.mMethod, objArr);
        }
    }

    public static final class ToIntMethodWrapper {
        private final int mDefaultValue;
        private final Method mMethod;

        public ToIntMethodWrapper(final Method method, final int defaultValue) {
            this.mMethod = method;
            this.mDefaultValue = defaultValue;
        }

        public int invoke(final Object receiver, final Object... args) {
            return ((Integer) CompatUtils.invoke(receiver, Integer.valueOf(this.mDefaultValue), this.mMethod, args)).intValue();
        }
    }

    public static final class ToFloatMethodWrapper {
        private final float mDefaultValue;
        private final Method mMethod;

        public ToFloatMethodWrapper(final Method method, final float defaultValue) {
            this.mMethod = method;
            this.mDefaultValue = defaultValue;
        }

        public float invoke(final Object receiver, final Object... args) {
            return ((Float) CompatUtils.invoke(receiver, Float.valueOf(this.mDefaultValue), this.mMethod, args)).floatValue();
        }
    }

    public static final class ToBooleanMethodWrapper {
        private final boolean mDefaultValue;
        private final Method mMethod;

        public ToBooleanMethodWrapper(final Method method, final boolean defaultValue) {
            this.mMethod = method;
            this.mDefaultValue = defaultValue;
        }

        public boolean invoke(final Object receiver, final Object... args) {
            return ((Boolean) CompatUtils.invoke(receiver, Boolean.valueOf(this.mDefaultValue), this.mMethod, args)).booleanValue();
        }
    }
}
