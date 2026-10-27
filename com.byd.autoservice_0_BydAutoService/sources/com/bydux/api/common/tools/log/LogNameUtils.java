package com.bydux.api.common.tools.log;

import android.text.TextUtils;
import com.bydux.api.common.tools.reflect.BydReflectHelper;
import com.bydux.api.common.tools.util.ArrayUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
                        } catch (IllegalAccessException e3) {
                            e3.printStackTrace();
                        } catch (Exception e4) {
                            e4.printStackTrace();
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
                        } catch (IllegalAccessException e3) {
                            e3.printStackTrace();
                        } catch (Exception e4) {
                            e4.printStackTrace();
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

    public static String getName(int i3, Class cls) {
        return getName(i3, cls, "unknown:" + i3, new String[0]);
    }

    public static int getValue(Class cls, String str, int i3) {
        ArrayList<Integer> fields;
        if (!TextUtils.isEmpty(str) && (fields = getFields(cls)) != null) {
            for (Integer num : fields) {
                if (str.equals(getName(num.intValue(), cls))) {
                    return num.intValue();
                }
            }
        }
        return i3;
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
                        } catch (Exception e3) {
                            e3.printStackTrace();
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
                } catch (IllegalAccessException e4) {
                    e4.printStackTrace();
                }
            }
        }
        return str2;
    }

    public static String getName(int i3, Class cls, String str, String... strArr) {
        Field[] declaredFields;
        if (cls != null && (declaredFields = cls.getDeclaredFields()) != null) {
            for (Field field : declaredFields) {
                if (field != null) {
                    boolean z3 = true;
                    field.setAccessible(true);
                    if (field.getType() == Integer.TYPE) {
                        try {
                            if (field.getInt(null) == i3) {
                                String name = field.getName();
                                if (strArr != null) {
                                    int i4 = 0;
                                    while (true) {
                                        if (i4 >= strArr.length) {
                                            break;
                                        }
                                        if (TextUtils.equals(name, strArr[i4])) {
                                            z3 = false;
                                            break;
                                        }
                                        i4++;
                                    }
                                }
                                if (z3) {
                                    return name;
                                }
                            } else {
                                continue;
                            }
                        } catch (IllegalAccessException e3) {
                            e3.printStackTrace();
                        } catch (Exception e4) {
                            e4.printStackTrace();
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
