package com.bydux.api.common.tools.log;

import android.text.TextUtils;
import com.bydux.api.common.tools.reflect.BydReflectHelper;
import com.bydux.api.common.tools.util.ArrayUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes2.dex */
public class LogNameUtils {
    public static String getName(int id, Class c) {
        return getName(id, c, "unknown:" + id, new String[0]);
    }

    public static String getName(int id, Class c, String unknownString, String... exceptArray) {
        Field[] declaredFields;
        if (c != null && (declaredFields = c.getDeclaredFields()) != null) {
            for (Field field : declaredFields) {
                if (field != null) {
                    boolean z = true;
                    field.setAccessible(true);
                    if (field.getType() == Integer.TYPE) {
                        try {
                            if (field.getInt(null) == id) {
                                String name = field.getName();
                                if (exceptArray != null) {
                                    int i = 0;
                                    while (true) {
                                        if (i >= exceptArray.length) {
                                            break;
                                        }
                                        if (TextUtils.equals(name, exceptArray[i])) {
                                            z = false;
                                            break;
                                        }
                                        i++;
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
        return unknownString;
    }

    public static Map<String, Integer> getIntegerFieldsMap(Class c) {
        Field[] declaredFields;
        HashMap map = new HashMap();
        if (c != null && (declaredFields = c.getDeclaredFields()) != null) {
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

    public static Map<Integer, String> getIntegerFieldsMapRevert(Class c) {
        Map<String, Integer> integerFieldsMap = getIntegerFieldsMap(c);
        HashMap map = new HashMap();
        for (Map.Entry<String, Integer> entry : integerFieldsMap.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        return map;
    }

    public static ArrayList<Integer> getFields(Class c) {
        Field[] declaredFields;
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (c != null && (declaredFields = c.getDeclaredFields()) != null) {
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

    public static int getValue(Class c, String name, int def) {
        ArrayList<Integer> fields;
        if (!TextUtils.isEmpty(name) && (fields = getFields(c)) != null) {
            for (Integer num : fields) {
                if (name.equals(getName(num.intValue(), c))) {
                    return num.intValue();
                }
            }
        }
        return def;
    }

    public static String toString(Object object) {
        String str;
        String str2 = "";
        if (object != null) {
            Field[] allDeclaredFields = BydReflectHelper.getAllDeclaredFields(object.getClass());
            if (!ArrayUtils.isEmpty(allDeclaredFields)) {
                try {
                    for (Field field : allDeclaredFields) {
                        field.setAccessible(true);
                        try {
                            field.get(null);
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (field.getType() == Integer.TYPE) {
                                str = str2 + field.getName() + "=" + field.getInt(object);
                            } else if (field.getType() == Long.TYPE) {
                                str = str2 + field.getName() + "=" + field.getLong(object);
                            } else if (field.getType() == Float.TYPE) {
                                str = str2 + field.getName() + "=" + field.getFloat(object);
                            } else if (field.getType() == String.class) {
                                str = str2 + field.getName() + "=" + field.get(object);
                            } else if (field.getType() == Short.TYPE) {
                                str = str2 + field.getName() + "=" + ((int) field.getShort(object));
                            } else if (field.getType() == Short.TYPE) {
                                str = str2 + field.getName() + "=" + field.getDouble(object);
                            } else if (field.getType() == Boolean.TYPE) {
                                str = str2 + field.getName() + "=" + field.getBoolean(object);
                            }
                            str2 = str + StringUtils.SPACE;
                        }
                    }
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return str2;
    }
}
