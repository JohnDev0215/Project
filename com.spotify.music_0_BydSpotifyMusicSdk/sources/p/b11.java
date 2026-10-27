package p;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes.dex */
public final class b11 extends g00 {
    public final /* synthetic */ int a;
    public final Object b;

    public /* synthetic */ b11(int i, com.google.gson.a aVar) {
        this.a = i;
        this.b = aVar;
    }

    public static b11 c() {
        return new b11(1, null);
    }

    @Override // p.g00
    public final h00 a(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, e52 e52Var) {
        switch (this.a) {
            case 0:
                com.google.gson.a aVar = (com.google.gson.a) this.b;
                return new c11(aVar, aVar.d(new qo2(type)));
            default:
                if ((type instanceof Class) && wj1.class.isAssignableFrom((Class) type)) {
                    return new qy1();
                }
                return null;
        }
    }

    @Override // p.g00
    public final h00 b(Type type, Annotation[] annotationArr, e52 e52Var) {
        ds1 ds1Var;
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                com.google.gson.a aVar = (com.google.gson.a) obj;
                return new g90(aVar, 15, aVar.d(new qo2(type)));
            default:
                if (!(type instanceof Class)) {
                    return null;
                }
                Class cls = (Class) type;
                if (!wj1.class.isAssignableFrom(cls)) {
                    return null;
                }
                try {
                    try {
                        ds1Var = (ds1) cls.getDeclaredMethod("parser", new Class[0]).invoke(null, new Object[0]);
                    } catch (IllegalAccessException | NoSuchFieldException e) {
                        throw new IllegalArgumentException(u4.d(cls, new StringBuilder("Found a protobuf message but "), " had no parser() method or PARSER field."), e);
                    }
                    break;
                } catch (IllegalAccessException | NoSuchMethodException unused) {
                    ds1Var = (ds1) cls.getDeclaredField("PARSER").get(null);
                    break;
                } catch (InvocationTargetException e2) {
                    throw new RuntimeException(e2.getCause());
                }
                return new ww1(ds1Var, 17, (rl0) obj);
        }
    }
}
