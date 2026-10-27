package l9;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class i implements InvocationHandler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f18868a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f18869b;
    public String c;

    public i(ArrayList arrayList) {
        this.f18868a = arrayList;
    }

    @Override // java.lang.reflect.InvocationHandler
    public final Object invoke(Object obj, Method method, Object[] objArr) {
        A7.m.f(obj, "proxy");
        A7.m.f(method, "method");
        if (objArr == null) {
            objArr = new Object[0];
        }
        String name = method.getName();
        Class<?> returnType = method.getReturnType();
        if (A7.m.a(name, "supports") && A7.m.a(Boolean.TYPE, returnType)) {
            return Boolean.TRUE;
        }
        if (A7.m.a(name, "unsupported") && A7.m.a(Void.TYPE, returnType)) {
            this.f18869b = true;
            return null;
        }
        boolean zA = A7.m.a(name, "protocols");
        ArrayList arrayList = this.f18868a;
        if (zA && objArr.length == 0) {
            return arrayList;
        }
        if ((A7.m.a(name, "selectProtocol") || A7.m.a(name, "select")) && String.class.equals(returnType) && objArr.length == 1) {
            Object obj2 = objArr[0];
            if (obj2 instanceof List) {
                A7.m.d(obj2, "null cannot be cast to non-null type kotlin.collections.List<*>");
                List list = (List) obj2;
                int size = list.size();
                if (size >= 0) {
                    int i7 = 0;
                    while (true) {
                        Object obj3 = list.get(i7);
                        A7.m.d(obj3, "null cannot be cast to non-null type kotlin.String");
                        String str = (String) obj3;
                        if (!arrayList.contains(str)) {
                            if (i7 == size) {
                                break;
                            }
                            i7++;
                        } else {
                            this.c = str;
                            return str;
                        }
                    }
                }
                String str2 = (String) arrayList.get(0);
                this.c = str2;
                return str2;
            }
        }
        if ((!A7.m.a(name, "protocolSelected") && !A7.m.a(name, "selected")) || objArr.length != 1) {
            return method.invoke(this, Arrays.copyOf(objArr, objArr.length));
        }
        Object obj4 = objArr[0];
        A7.m.d(obj4, "null cannot be cast to non-null type kotlin.String");
        this.c = (String) obj4;
        return null;
    }
}
