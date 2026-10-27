package K7;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class Y extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3758m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Z f3759n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Y(Z z6, int i7) {
        super(0);
        this.f3758m = i7;
        this.f3759n = z6;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() throws I7.a {
        switch (this.f3758m) {
            case 0:
                return new X(this.f3759n);
            default:
                Z z6 = this.f3759n;
                Object objS = z6.s();
                try {
                    Object obj = m0.f3811s;
                    Object objG = z6.r() ? w0.c.G(z6.f3814p, z6.n()) : null;
                    if (objG == obj) {
                        objG = null;
                    }
                    z6.r();
                    AccessibleObject accessibleObject = objS instanceof AccessibleObject ? (AccessibleObject) objS : null;
                    if (accessibleObject != null) {
                        accessibleObject.setAccessible(P8.n.C(z6));
                    }
                    if (objS == null) {
                        return null;
                    }
                    if (objS instanceof Field) {
                        return ((Field) objS).get(objG);
                    }
                    if (!(objS instanceof Method)) {
                        throw new AssertionError("delegate field/method " + objS + " neither field nor method");
                    }
                    int length = ((Method) objS).getParameterTypes().length;
                    if (length == 0) {
                        return ((Method) objS).invoke(null, null);
                    }
                    if (length == 1) {
                        Method method = (Method) objS;
                        if (objG == null) {
                            Class<?> cls = ((Method) objS).getParameterTypes()[0];
                            A7.m.e(cls, "fieldOrMethod.parameterTypes[0]");
                            objG = A0.e(cls);
                        }
                        return method.invoke(null, objG);
                    }
                    if (length == 2) {
                        Method method2 = (Method) objS;
                        Class<?> cls2 = ((Method) objS).getParameterTypes()[1];
                        A7.m.e(cls2, "fieldOrMethod.parameterTypes[1]");
                        return method2.invoke(null, objG, A0.e(cls2));
                    }
                    throw new AssertionError("delegate method " + objS + " should take 0, 1, or 2 parameters");
                } catch (IllegalAccessException e10) {
                    throw new I7.a("Cannot obtain the delegate of a non-accessible property. Use \"isAccessible = true\" to make the property accessible", e10);
                }
        }
    }
}
