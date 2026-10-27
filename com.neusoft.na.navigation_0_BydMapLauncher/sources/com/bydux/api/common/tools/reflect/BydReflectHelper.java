package com.bydux.api.common.tools.reflect;

import android.text.TextUtils;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.tools.util.ArrayUtils;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;

/* JADX INFO: loaded from: classes2.dex */
public class BydReflectHelper {
    public static Object invoke(String className, String methodName, Object... params) {
        if (TextUtils.isEmpty(className) || TextUtils.isEmpty(methodName)) {
            BydLogHelper.logE("className:" + className + " methodName" + methodName);
            return null;
        }
        try {
            Method method = getMethod(Class.forName(className), methodName, getParameterTypes(params));
            if (method != null) {
                return method.invoke(null, params);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        }
        return null;
    }

    public static Object invoke(Object object, String methodName, Class<?>[] parameterTypes, Object[] params) {
        if (object == null || TextUtils.isEmpty(methodName)) {
            BydLogHelper.logE("object is null, methodName:" + methodName);
            return null;
        }
        Method method = getMethod(object.getClass(), methodName, parameterTypes);
        if (method != null) {
            try {
                return method.invoke(object, params);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static Object invoke(Object object, String methodName, Object... params) {
        if (object == null || TextUtils.isEmpty(methodName)) {
            BydLogHelper.logE("object is null, methodName:" + methodName);
            return null;
        }
        try {
            Method method = getMethod(object.getClass(), methodName, getParameterTypes(params));
            if (method != null) {
                return method.invoke(object, params);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
        }
        return null;
    }

    private static Method getMethod(Class<?> clasz, String name, Class<?>... parameterTypes) {
        try {
            return clasz.getMethod(name, parameterTypes);
        } catch (NoSuchMethodException e) {
            if (clasz.getSuperclass() != null) {
                return getMethod(clasz.getSuperclass(), name, parameterTypes);
            }
            e.printStackTrace();
            return null;
        }
    }

    private static Class<?>[] getParameterTypes(Object... params) {
        if (params == null || params.length == 0) {
            return new Class[0];
        }
        Class<?>[] clsArr = new Class[params.length];
        for (int i = 0; i < params.length; i++) {
            Object obj = params[i];
            if (obj != null) {
                Class<?> cls = obj.getClass();
                clsArr[i] = cls;
                if (cls == Integer.class) {
                    clsArr[i] = Integer.TYPE;
                }
            }
        }
        return clsArr;
    }

    public static Field getField(String className, String fieldName) {
        if (!TextUtils.isEmpty(className) && !TextUtils.isEmpty(fieldName)) {
            try {
                for (Field field : Class.forName(className).getDeclaredFields()) {
                    if (field != null && TextUtils.equals(field.getName(), fieldName)) {
                        return field;
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Field[] getAllDeclaredFields(Class<?> c) {
        if (c == null) {
            return null;
        }
        Field[] declaredFields = c.getDeclaredFields();
        HashSet hashSet = new HashSet();
        for (int i = 0; i < declaredFields.length; i++) {
            if (!declaredFields[i].isSynthetic() && !declaredFields[i].getName().contains("$") && !"serialVersionUID".equals(declaredFields[i].getName())) {
                declaredFields[i].setAccessible(true);
                try {
                    declaredFields[i].get(null);
                } catch (Exception e) {
                    e.printStackTrace();
                    hashSet.add(declaredFields[i]);
                }
            }
        }
        Field[] fieldArr = new Field[hashSet.size()];
        hashSet.toArray(fieldArr);
        return c.getSuperclass() != null ? (Field[]) ArrayUtils.concat(fieldArr, getAllDeclaredFields(c.getSuperclass())) : fieldArr;
    }

    public static Object getFieldValue(String className, Object object, String fieldName) {
        Field field = getField(className, fieldName);
        if (field == null) {
            return null;
        }
        field.setAccessible(true);
        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setFieldValue(Object object, String fieldName, Object value) {
        if (object == null) {
            BydLogHelper.logE("object is null!");
        } else {
            setFieldValue(object.getClass().getName(), object, fieldName, value);
        }
    }

    public static void setFieldValue(String className, Object object, String fieldName, Object value) {
        Field field = getField(className, fieldName);
        if (field != null) {
            field.setAccessible(true);
            try {
                field.set(object, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getClassName(Class<?> c) {
        if (c == null) {
            return "";
        }
        String name = c.getName();
        if (!name.contains(".")) {
            return name;
        }
        String[] strArrSplit = name.split("\\.");
        return !ArrayUtils.isEmpty(strArrSplit) ? strArrSplit[strArrSplit.length - 1] : name;
    }

    public static Object createObject(String className) {
        try {
            return Class.forName(className).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return null;
        } catch (InstantiationException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static Object createObject(String className, Class<?>[] parameterTypes, Object[] params) {
        try {
            return Class.forName(className).getConstructor(parameterTypes).newInstance(params);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return null;
        } catch (InstantiationException e3) {
            e3.printStackTrace();
            return null;
        } catch (NoSuchMethodException e4) {
            e4.printStackTrace();
            return null;
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
            return null;
        }
    }

    public static Object createObject(String className, String innerClassName) {
        try {
            Class<?> cls = Class.forName(className);
            Class<?>[] declaredClasses = cls.getDeclaredClasses();
            if (declaredClasses == null) {
                return null;
            }
            for (Class<?> cls2 : declaredClasses) {
                if (TextUtils.equals(innerClassName, cls2.getName())) {
                    return cls2.getDeclaredConstructor(cls).newInstance(cls.newInstance());
                }
            }
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return null;
        } catch (InstantiationException e3) {
            e3.printStackTrace();
            return null;
        } catch (NoSuchMethodException e4) {
            e4.printStackTrace();
            return null;
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
            return null;
        }
    }

    public static Object createObject(String className, String innerClassName, Class<?>[] parameterTypes, Object[] params) {
        try {
            Class<?> cls = Class.forName(className);
            Class<?>[] declaredClasses = cls.getDeclaredClasses();
            if (declaredClasses == null) {
                return null;
            }
            for (Class<?> cls2 : declaredClasses) {
                if (TextUtils.equals(innerClassName, cls2.getName())) {
                    return cls2.getDeclaredConstructor(cls).newInstance(cls.getConstructor(parameterTypes).newInstance(params));
                }
            }
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return null;
        } catch (InstantiationException e3) {
            e3.printStackTrace();
            return null;
        } catch (NoSuchMethodException e4) {
            e4.printStackTrace();
            return null;
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
            return null;
        }
    }

    public static Field getIntField(int id, Class c) {
        Field[] declaredFields;
        if (c != null && (declaredFields = c.getDeclaredFields()) != null) {
            try {
                for (Field field : declaredFields) {
                    if (field != null) {
                        field.setAccessible(true);
                        if (field.getType() == Integer.TYPE && field.getInt(null) == id) {
                            return field;
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }
}
