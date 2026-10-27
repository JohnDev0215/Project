package b.a.c.u;

import java.lang.reflect.Method;

/* JADX INFO: compiled from: RpcMethod.java */
/* JADX INFO: loaded from: classes2.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object f306a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f307b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Method f308c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f309d;
    public String e;
    public Object[] f;

    public g(Object obj, Method method, Object[] objArr) {
        this(obj, method.getDeclaringClass().getName(), method.getName(), method.getReturnType().getName(), objArr);
        this.f308c = method;
    }

    public Object a() {
        return this.f306a;
    }

    public Method b() {
        return this.f308c;
    }

    public String c() {
        Method method = this.f308c;
        return method != null ? method.getName() : this.f309d;
    }

    public Object[] d() {
        return this.f;
    }

    public g(Object obj, String str, String str2, String str3, Object[] objArr) {
        this.f306a = obj;
        this.f307b = str;
        this.f309d = str2;
        this.e = str3;
        this.f = objArr;
    }
}
