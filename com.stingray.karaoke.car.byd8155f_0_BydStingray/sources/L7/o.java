package L7;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Arrays;
import n7.AbstractC1996k;

/* JADX INFO: loaded from: classes.dex */
public abstract class o extends t {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f4057e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f4058f;

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ o(Method method, boolean z6, int i7) {
        z6 = (i7 & 2) != 0 ? !Modifier.isStatic(method.getModifiers()) : z6;
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        A7.m.e(genericParameterTypes, "method.genericParameterTypes");
        this(method, z6, genericParameterTypes);
    }

    @Override // L7.t
    public void a(Object[] objArr) {
        switch (this.f4057e) {
            case 0:
                l9.d.H(this, objArr);
                if (this.f4058f && AbstractC1996k.W(objArr) == null) {
                    throw new IllegalArgumentException("null is not allowed as a value for this property.");
                }
                return;
            default:
                super.a(objArr);
                return;
        }
    }

    public Object c(Object obj, Object[] objArr) {
        A7.m.f(objArr, "args");
        return this.f4058f ? m7.q.f19267a : ((Method) this.f4062a).invoke(obj, Arrays.copyOf(objArr, objArr.length));
    }

    @Override // L7.e
    public Object q(Object[] objArr) throws IllegalAccessException {
        a(objArr);
        ((Field) this.f4062a).set(this.c != null ? AbstractC1996k.N(objArr) : null, AbstractC1996k.W(objArr));
        return m7.q.f19267a;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public o(Method method, boolean z6, Type[] typeArr) {
        Type genericReturnType = method.getGenericReturnType();
        A7.m.e(genericReturnType, "method.genericReturnType");
        super(method, genericReturnType, z6 ? method.getDeclaringClass() : null, typeArr);
        this.f4058f = genericReturnType.equals(Void.TYPE);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public o(Field field, boolean z6, boolean z10) {
        Class cls = Void.TYPE;
        A7.m.e(cls, "TYPE");
        Class<?> declaringClass = z10 ? field.getDeclaringClass() : null;
        Type genericType = field.getGenericType();
        A7.m.e(genericType, "field.genericType");
        super(field, cls, declaringClass, new Type[]{genericType});
        this.f4058f = z6;
    }
}
