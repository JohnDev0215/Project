package L7;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import n7.AbstractC1996k;

/* JADX INFO: loaded from: classes.dex */
public abstract class k extends t {
    /* JADX WARN: Illegal instructions before constructor call */
    public k(Field field, boolean z6) {
        Type genericType = field.getGenericType();
        A7.m.e(genericType, "field.genericType");
        super(field, genericType, z6 ? field.getDeclaringClass() : null, new Type[0]);
    }

    @Override // L7.e
    public Object q(Object[] objArr) {
        a(objArr);
        return ((Field) this.f4062a).get(this.c != null ? AbstractC1996k.N(objArr) : null);
    }
}
