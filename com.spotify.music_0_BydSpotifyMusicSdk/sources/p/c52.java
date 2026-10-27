package p;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class c52 implements InvocationHandler {
    public final ht1 a = ht1.c;
    public final Object[] b = new Object[0];
    public final /* synthetic */ Class c;
    public final /* synthetic */ e52 d;

    public c52(e52 e52Var, Class cls) {
        this.d = e52Var;
        this.c = cls;
    }

    @Override // java.lang.reflect.InvocationHandler
    public final Object invoke(Object obj, Method method, Object[] objArr) {
        if (method.getDeclaringClass() == Object.class) {
            return method.invoke(this, objArr);
        }
        if (objArr == null) {
            objArr = this.b;
        }
        ht1 ht1Var = this.a;
        if (ht1Var.a && method.isDefault()) {
            return ht1Var.b(method, this.c, obj, objArr);
        }
        o21 o21Var = (o21) this.d.c(method);
        return o21Var.b(new ip1(o21Var.a, objArr, o21Var.b, o21Var.c), objArr);
    }
}
