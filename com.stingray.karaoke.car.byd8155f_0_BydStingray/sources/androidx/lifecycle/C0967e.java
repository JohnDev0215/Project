package androidx.lifecycle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: androidx.lifecycle.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0967e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HashMap f11980a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashMap f11981b;

    public C0967e(HashMap map) {
        this.f11981b = map;
        for (Map.Entry entry : map.entrySet()) {
            EnumC0980s enumC0980s = (EnumC0980s) entry.getValue();
            List arrayList = (List) this.f11980a.get(enumC0980s);
            if (arrayList == null) {
                arrayList = new ArrayList();
                this.f11980a.put(enumC0980s, arrayList);
            }
            arrayList.add((C0968f) entry.getKey());
        }
    }

    public static void a(List list, A a10, EnumC0980s enumC0980s, InterfaceC0987z interfaceC0987z) {
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                C0968f c0968f = (C0968f) list.get(size);
                c0968f.getClass();
                try {
                    int i7 = c0968f.f11984a;
                    Method method = c0968f.f11985b;
                    if (i7 == 0) {
                        method.invoke(interfaceC0987z, null);
                    } else if (i7 == 1) {
                        method.invoke(interfaceC0987z, a10);
                    } else if (i7 == 2) {
                        method.invoke(interfaceC0987z, a10, enumC0980s);
                    }
                } catch (IllegalAccessException e10) {
                    throw new RuntimeException(e10);
                } catch (InvocationTargetException e11) {
                    throw new RuntimeException("Failed to call observer method", e11.getCause());
                }
            }
        }
    }
}
