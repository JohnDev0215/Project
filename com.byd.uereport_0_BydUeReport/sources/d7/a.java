package d7;

import c7.b;

/* JADX INFO: compiled from: MemberUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Class<?>[] f4473a = {Byte.TYPE, Short.TYPE, Character.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE};

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
        float f8 = 0.0f;
        while (true) {
            if (cls != null && !cls2.equals(cls)) {
                if (cls2.isInterface() && b.e(cls, cls2)) {
                    f8 += 0.25f;
                    break;
                }
                f8 += 1.0f;
                cls = cls.getSuperclass();
            } else {
                break;
            }
        }
        return cls == null ? f8 + 1.5f : f8;
    }

    public static float c(Class<?> cls, Class<?> cls2) {
        float f8;
        if (cls.isPrimitive()) {
            f8 = 0.0f;
        } else {
            cls = b.m(cls);
            f8 = 0.1f;
        }
        int i8 = 0;
        while (cls != cls2) {
            Class<?>[] clsArr = f4473a;
            if (i8 >= clsArr.length) {
                break;
            }
            if (cls == clsArr[i8]) {
                f8 += 0.1f;
                if (i8 < clsArr.length - 1) {
                    cls = clsArr[i8 + 1];
                }
            }
            i8++;
        }
        return f8;
    }

    public static float d(Class<?>[] clsArr, Class<?>[] clsArr2) {
        float fB = 0.0f;
        for (int i8 = 0; i8 < clsArr.length; i8++) {
            fB += b(clsArr[i8], clsArr2[i8]);
        }
        return fB;
    }
}
