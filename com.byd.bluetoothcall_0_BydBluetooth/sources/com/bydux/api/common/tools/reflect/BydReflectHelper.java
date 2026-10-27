package com.bydux.api.common.tools.reflect;

import android.text.TextUtils;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.tools.util.ArrayUtils;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public class BydReflectHelper {
    public static Object createObject(String str) {
        try {
            return Class.forName(str).newInstance();
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            return null;
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
            return null;
        } catch (InstantiationException e4) {
            e4.printStackTrace();
            return null;
        }
    }

    public static Field[] getAllDeclaredFields(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        Field[] declaredFields = cls.getDeclaredFields();
        HashSet hashSet = new HashSet();
        for (int i = 0; i < declaredFields.length; i++) {
            if (!declaredFields[i].isSynthetic() && !declaredFields[i].getName().contains("$") && !"serialVersionUID".equals(declaredFields[i].getName())) {
                declaredFields[i].setAccessible(true);
                try {
                    declaredFields[i].get(null);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    hashSet.add(declaredFields[i]);
                }
            }
        }
        Field[] fieldArr = new Field[hashSet.size()];
        hashSet.toArray(fieldArr);
        return cls.getSuperclass() != null ? (Field[]) ArrayUtils.concat(fieldArr, getAllDeclaredFields(cls.getSuperclass())) : fieldArr;
    }

    public static String getClassName(Class<?> cls) {
        if (cls == null) {
            return "";
        }
        String name = cls.getName();
        if (!name.contains(".")) {
            return name;
        }
        String[] strArrSplit = name.split("\\.");
        return !ArrayUtils.isEmpty(strArrSplit) ? strArrSplit[strArrSplit.length - 1] : name;
    }

    public static Field getField(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                for (Field field : Class.forName(str).getDeclaredFields()) {
                    if (field != null && TextUtils.equals(field.getName(), str2)) {
                        return field;
                    }
                }
            } catch (ClassNotFoundException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static Object getFieldValue(String str, Object obj, String str2) {
        Field field = getField(str, str2);
        if (field == null) {
            return null;
        }
        field.setAccessible(true);
        try {
            return field.get(obj);
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Field getIntField(int i, Class cls) {
        Field[] declaredFields;
        if (cls != null && (declaredFields = cls.getDeclaredFields()) != null) {
            try {
                for (Field field : declaredFields) {
                    if (field != null) {
                        field.setAccessible(true);
                        if (field.getType() == Integer.TYPE && field.getInt(null) == i) {
                            return field;
                        }
                    }
                }
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        return null;
    }

    private static Method getMethod(Class<?> cls, String str, Class<?>... clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException e2) {
            if (cls.getSuperclass() != null) {
                return getMethod(cls.getSuperclass(), str, clsArr);
            }
            e2.printStackTrace();
            return null;
        }
    }

    private static Class<?>[] getParameterTypes(Object... objArr) {
        if (objArr == null || objArr.length == 0) {
            return new Class[0];
        }
        Class<?>[] clsArr = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            if (objArr[i] != null) {
                clsArr[i] = objArr[i].getClass();
                if (clsArr[i] == Integer.class) {
                    clsArr[i] = Integer.TYPE;
                }
            }
        }
        return clsArr;
    }

    public static Object invoke(String str, String str2, Object... objArr) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            BydLogHelper.logE("className:" + str + " methodName" + str2);
            return null;
        }
        try {
            Method method = getMethod(Class.forName(str), str2, getParameterTypes(objArr));
            if (method != null) {
                return method.invoke(null, objArr);
            }
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
        }
        return null;
    }

    public static void setFieldValue(Object obj, String str, Object obj2) {
        if (obj == null) {
            BydLogHelper.logE("object is null!");
        } else {
            setFieldValue(obj.getClass().getName(), obj, str, obj2);
        }
    }

    public static void setFieldValue(String str, Object obj, String str2, Object obj2) {
        Field field = getField(str, str2);
        if (field != null) {
            field.setAccessible(true);
            try {
                field.set(obj, obj2);
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            }
        }
    }

    public static Object createObject(String str, Class<?>[] clsArr, Object[] objArr) {
        try {
            return Class.forName(str).getConstructor(clsArr).newInstance(objArr);
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            return null;
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
            return null;
        } catch (InstantiationException e4) {
            e4.printStackTrace();
            return null;
        } catch (NoSuchMethodException e5) {
            e5.printStackTrace();
            return null;
        } catch (InvocationTargetException e6) {
            e6.printStackTrace();
            return null;
        }
    }

    public static Object invoke(Object obj, String str, Class<?>[] clsArr, Object[] objArr) {
        if (obj != null && !TextUtils.isEmpty(str)) {
            Method method = getMethod(obj.getClass(), str, clsArr);
            if (method != null) {
                try {
                    return method.invoke(obj, objArr);
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                } catch (InvocationTargetException e3) {
                    e3.printStackTrace();
                }
            }
            return null;
        }
        BydLogHelper.logE("object is null, methodName:" + str);
        return null;
    }

    public static Object createObject(String str, String str2) {
        try {
            Class<?> cls = Class.forName(str);
            Class<?>[] declaredClasses = cls.getDeclaredClasses();
            if (declaredClasses == null) {
                return null;
            }
            for (Class<?> cls2 : declaredClasses) {
                if (TextUtils.equals(str2, cls2.getName())) {
                    return cls2.getDeclaredConstructor(cls).newInstance(cls.newInstance());
                }
            }
            return null;
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            return null;
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
            return null;
        } catch (InstantiationException e4) {
            e4.printStackTrace();
            return null;
        } catch (NoSuchMethodException e5) {
            e5.printStackTrace();
            return null;
        } catch (InvocationTargetException e6) {
            e6.printStackTrace();
            return null;
        }
    }

    public static Object invoke(Object obj, String str, Object... objArr) {
        if (obj != null && !TextUtils.isEmpty(str)) {
            try {
                Method method = getMethod(obj.getClass(), str, getParameterTypes(objArr));
                if (method != null) {
                    return method.invoke(obj, objArr);
                }
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
            }
            return null;
        }
        BydLogHelper.logE("object is null, methodName:" + str);
        return null;
    }

    public static Object createObject(String str, String str2, Class<?>[] clsArr, Object[] objArr) {
        try {
            Class<?> cls = Class.forName(str);
            Class<?>[] declaredClasses = cls.getDeclaredClasses();
            if (declaredClasses == null) {
                return null;
            }
            for (Class<?> cls2 : declaredClasses) {
                if (TextUtils.equals(str2, cls2.getName())) {
                    return cls2.getDeclaredConstructor(cls).newInstance(cls.getConstructor(clsArr).newInstance(objArr));
                }
            }
            return null;
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            return null;
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
            return null;
        } catch (InstantiationException e4) {
            e4.printStackTrace();
            return null;
        } catch (NoSuchMethodException e5) {
            e5.printStackTrace();
            return null;
        } catch (InvocationTargetException e6) {
            e6.printStackTrace();
            return null;
        }
    }
}
