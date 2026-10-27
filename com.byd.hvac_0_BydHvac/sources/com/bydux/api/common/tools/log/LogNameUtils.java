package com.bydux.api.common.tools.log;

import android.text.TextUtils;
import com.bydux.api.common.tools.reflect.BydReflectHelper;
import com.bydux.api.common.tools.util.ArrayUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class LogNameUtils {
    public static ArrayList<Integer> getFields(Class cls) {
        Field[] declaredFields;
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (cls != null && (declaredFields = cls.getDeclaredFields()) != null) {
            for (Field field : declaredFields) {
                if (field != null) {
                    field.setAccessible(true);
                    if (field.getType() == Integer.TYPE) {
                        try {
                            arrayList.add(Integer.valueOf(field.getInt(null)));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public static Map<String, Integer> getIntegerFieldsMap(Class cls) {
        Field[] declaredFields;
        HashMap map = new HashMap();
        if (cls != null && (declaredFields = cls.getDeclaredFields()) != null) {
            for (Field field : declaredFields) {
                if (field != null) {
                    field.setAccessible(true);
                    if (field.getType() == Integer.TYPE) {
                        try {
                            map.put(field.getName(), Integer.valueOf(field.getInt(null)));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
        }
        return map;
    }

    public static Map<Integer, String> getIntegerFieldsMapRevert(Class cls) {
        Map<String, Integer> integerFieldsMap = getIntegerFieldsMap(cls);
        HashMap map = new HashMap();
        for (Map.Entry<String, Integer> entry : integerFieldsMap.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        return map;
    }

    public static String getName(int i, Class cls) {
        return getName(i, cls, "unknown:" + i, new String[0]);
    }

    public static int getValue(Class cls, String str, int i) {
        ArrayList<Integer> fields;
        if (!TextUtils.isEmpty(str) && (fields = getFields(cls)) != null) {
            for (Integer num : fields) {
                if (str.equals(getName(num.intValue(), cls))) {
                    return num.intValue();
                }
            }
        }
        return i;
    }

    public static String toString(Object obj) {
        String str;
        String str2 = "";
        if (obj != null) {
            Field[] allDeclaredFields = BydReflectHelper.getAllDeclaredFields(obj.getClass());
            if (!ArrayUtils.isEmpty(allDeclaredFields)) {
                try {
                    for (Field field : allDeclaredFields) {
                        field.setAccessible(true);
                        try {
                            field.get(null);
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (field.getType() == Integer.TYPE) {
                                str = str2 + field.getName() + "=" + field.getInt(obj);
                            } else if (field.getType() == Long.TYPE) {
                                str = str2 + field.getName() + "=" + field.getLong(obj);
                            } else if (field.getType() == Float.TYPE) {
                                str = str2 + field.getName() + "=" + field.getFloat(obj);
                            } else if (field.getType() == String.class) {
                                str = str2 + field.getName() + "=" + field.get(obj);
                            } else if (field.getType() == Short.TYPE) {
                                str = str2 + field.getName() + "=" + ((int) field.getShort(obj));
                            } else if (field.getType() == Short.TYPE) {
                                str = str2 + field.getName() + "=" + field.getDouble(obj);
                            } else if (field.getType() == Boolean.TYPE) {
                                str = str2 + field.getName() + "=" + field.getBoolean(obj);
                            }
                            str2 = str + " ";
                        }
                    }
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return str2;
    }

    public static String getName(int i, Class cls, String str, String... strArr) {
        Field[] declaredFields;
        if (cls != null && (declaredFields = cls.getDeclaredFields()) != null) {
            for (Field field : declaredFields) {
                if (field != null) {
                    boolean z = true;
                    field.setAccessible(true);
                    if (field.getType() == Integer.TYPE) {
                        try {
                            if (field.getInt(null) == i) {
                                String name = field.getName();
                                if (strArr != null) {
                                    int i2 = 0;
                                    while (true) {
                                        if (i2 >= strArr.length) {
                                            break;
                                        }
                                        if (TextUtils.equals(name, strArr[i2])) {
                                            z = false;
                                            break;
                                        }
                                        i2++;
                                    }
                                }
                                if (z) {
                                    return name;
                                }
                            } else {
                                continue;
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        return str;
    }
}
