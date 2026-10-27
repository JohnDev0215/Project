package c.a.a.a.a.e;

import c.a.a.a.a.b;

/* JADX INFO: compiled from: MemberUtils.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Class<?>[] f2358a = {Byte.TYPE, Short.TYPE, Character.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE};

    public static int a(Class<?>[] clsArr, Class<?>[] clsArr2, Class<?>[] clsArr3) {
        float fD = d(clsArr3, clsArr);
        float fD2 = d(clsArr3, clsArr2);
        if (fD < fD2) {
            return -1;
        }
        return fD2 < fD ? 1 : 0;
    }

    public static float b(Class<?> cls, Class<?> cls2) {
        if (cls2.isPrimitive()) {
            return c(cls, cls2);
        }
        float f2 = 0.0f;
        while (true) {
            if (cls != null && !cls2.equals(cls)) {
                if (cls2.isInterface() && b.e(cls, cls2)) {
                    f2 += 0.25f;
                    break;
                }
                f2 += 1.0f;
                cls = cls.getSuperclass();
            } else {
                break;
            }
        }
        return cls == null ? f2 + 1.5f : f2;
    }

    public static float c(Class<?> cls, Class<?> cls2) {
        float f2;
        if (cls.isPrimitive()) {
            f2 = 0.0f;
        } else {
            cls = b.m(cls);
            f2 = 0.1f;
        }
        int i = 0;
        while (cls != cls2) {
            Class<?>[] clsArr = f2358a;
            if (i >= clsArr.length) {
                break;
            }
            if (cls == clsArr[i]) {
                f2 += 0.1f;
                if (i < clsArr.length - 1) {
                    cls = clsArr[i + 1];
                }
            }
            i++;
        }
        return f2;
    }

    public static float d(Class<?>[] clsArr, Class<?>[] clsArr2) {
        float fB = 0.0f;
        for (int i = 0; i < clsArr.length; i++) {
            fB += b(clsArr[i], clsArr2[i]);
        }
        return fB;
    }
}
