package W7;

import f8.InterfaceC1269e;
import java.lang.annotation.Annotation;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class w extends v implements InterfaceC1269e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Method f9899a;

    public w(Method method) {
        A7.m.f(method, "member");
        this.f9899a = method;
    }

    @Override // W7.v
    public final Member b() {
        return this.f9899a;
    }

    public final A f() {
        Type genericReturnType = this.f9899a.getGenericReturnType();
        A7.m.e(genericReturnType, "member.genericReturnType");
        boolean z6 = genericReturnType instanceof Class;
        if (z6) {
            Class cls = (Class) genericReturnType;
            if (cls.isPrimitive()) {
                return new y(cls);
            }
        }
        return ((genericReturnType instanceof GenericArrayType) || (z6 && ((Class) genericReturnType).isArray())) ? new h(genericReturnType) : genericReturnType instanceof WildcardType ? new D((WildcardType) genericReturnType) : new p(genericReturnType);
    }

    public final List g() {
        Method method = this.f9899a;
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        A7.m.e(genericParameterTypes, "member.genericParameterTypes");
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        A7.m.e(parameterAnnotations, "member.parameterAnnotations");
        return d(genericParameterTypes, parameterAnnotations, method.isVarArgs());
    }

    @Override // f8.InterfaceC1269e
    public final ArrayList t() {
        TypeVariable<Method>[] typeParameters = this.f9899a.getTypeParameters();
        A7.m.e(typeParameters, "member.typeParameters");
        ArrayList arrayList = new ArrayList(typeParameters.length);
        for (TypeVariable<Method> typeVariable : typeParameters) {
            arrayList.add(new B(typeVariable));
        }
        return arrayList;
    }
}
