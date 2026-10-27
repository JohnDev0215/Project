package p;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes.dex */
public final class z62 extends g00 {
    @Override // p.g00
    public final h00 a(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, e52 e52Var) {
        if (type == String.class || type == Boolean.TYPE || type == Boolean.class || type == Byte.TYPE || type == Byte.class || type == Character.TYPE || type == Character.class || type == Double.TYPE || type == Double.class || type == Float.TYPE || type == Float.class || type == Integer.TYPE || type == Integer.class || type == Long.TYPE || type == Long.class || type == Short.TYPE || type == Short.class) {
            return y62.c;
        }
        return null;
    }

    @Override // p.g00
    public final h00 b(Type type, Annotation[] annotationArr, e52 e52Var) {
        if (type == String.class) {
            return uh0.e;
        }
        if (type == Boolean.class || type == Boolean.TYPE) {
            return oh0.e;
        }
        if (type == Byte.class || type == Byte.TYPE) {
            return lh0.e;
        }
        if (type == Character.class || type == Character.TYPE) {
            return mh0.e;
        }
        if (type == Double.class || type == Double.TYPE) {
            return rh0.e;
        }
        if (type == Float.class || type == Float.TYPE) {
            return ph0.e;
        }
        if (type == Integer.class || type == Integer.TYPE) {
            return qh0.e;
        }
        if (type == Long.class || type == Long.TYPE) {
            return vh0.e;
        }
        if (type == Short.class || type == Short.TYPE) {
            return th0.e;
        }
        return null;
    }
}
