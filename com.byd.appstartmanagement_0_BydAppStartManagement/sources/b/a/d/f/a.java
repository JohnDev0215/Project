package b.a.d.f;

import java.lang.reflect.Field;

/* JADX INFO: compiled from: BydReflect.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {
    public static void a(Object obj, String str, Object obj2) {
        try {
            Field declaredField = obj.getClass().getDeclaredField(str);
            declaredField.setAccessible(true);
            declaredField.set(obj, obj2);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            b.a.d.b.a.c("BydReflect", ", Reflect field failed : " + e.toString());
        }
    }
}
