package L7;

import A7.D;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import n7.AbstractC1996k;

/* JADX INFO: loaded from: classes.dex */
public final class r extends o implements d {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Object f4060g;

    /* JADX WARN: Illegal instructions before constructor call */
    public r(Method method, Object obj) {
        A7.m.f(method, "method");
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        A7.m.e(genericParameterTypes, "method.genericParameterTypes");
        super(method, false, (Type[]) (genericParameterTypes.length <= 1 ? new Type[0] : AbstractC1996k.I(genericParameterTypes, 1, genericParameterTypes.length)));
        this.f4060g = obj;
    }

    @Override // L7.o, L7.e
    public final Object q(Object[] objArr) {
        l9.d.H(this, objArr);
        D d = new D(2);
        d.f(this.f4060g);
        d.j(objArr);
        ArrayList arrayList = d.l;
        return c(null, arrayList.toArray(new Object[arrayList.size()]));
    }
}
