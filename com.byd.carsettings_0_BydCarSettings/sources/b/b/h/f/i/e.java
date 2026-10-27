package b.b.h.f.i;

import b.b.h.f.g.f;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.LinkedHashMap;

/* JADX INFO: compiled from: TableUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e {
    public static void a(Class<?> cls, HashMap<String, a> map) {
        b.b.h.f.f.a aVar;
        if (Object.class.equals(cls)) {
            return;
        }
        try {
            for (Field field : cls.getDeclaredFields()) {
                int modifiers = field.getModifiers();
                if (!Modifier.isStatic(modifiers) && !Modifier.isTransient(modifiers) && (aVar = (b.b.h.f.f.a) field.getAnnotation(b.b.h.f.f.a.class)) != null && f.c(field.getType())) {
                    a aVar2 = new a(cls, field, aVar);
                    if (!map.containsKey(aVar2.d())) {
                        map.put(aVar2.d(), aVar2);
                    }
                }
            }
            a(cls.getSuperclass(), map);
        } catch (Throwable th) {
            b.b.m.c.a.c(b.b.h.b.f1850a, th.getMessage());
        }
    }

    public static synchronized LinkedHashMap<String, a> b(Class<?> cls) {
        LinkedHashMap<String, a> linkedHashMap;
        linkedHashMap = new LinkedHashMap<>();
        a(cls, linkedHashMap);
        return linkedHashMap;
    }
}
