package L7;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class x implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Method f4066a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f4067b;
    public final Class c;

    public x(Method method, List list) {
        this.f4066a = method;
        this.f4067b = list;
        Class<?> returnType = method.getReturnType();
        A7.m.e(returnType, "unboxMethod.returnType");
        this.c = returnType;
    }

    @Override // L7.e
    public final Type p() {
        return this.c;
    }

    @Override // L7.e
    public final List r() {
        return this.f4067b;
    }

    @Override // L7.e
    public final /* bridge */ /* synthetic */ Member s() {
        return null;
    }
}
