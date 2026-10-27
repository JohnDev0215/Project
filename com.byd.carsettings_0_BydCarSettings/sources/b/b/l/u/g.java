package b.b.l.u;

import java.lang.reflect.Method;

/* JADX INFO: compiled from: RpcMethod.java */
/* JADX INFO: loaded from: classes2.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object f2075a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f2076b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Method f2077c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f2078d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f2079e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object[] f2080f;

    public g(Object obj, Method method, Object[] objArr) {
        this(obj, method.getDeclaringClass().getName(), method.getName(), method.getReturnType().getName(), objArr);
        this.f2077c = method;
    }

    public Object a() {
        return this.f2075a;
    }

    public Method b() {
        return this.f2077c;
    }

    public String c() {
        Method method = this.f2077c;
        return method != null ? method.getName() : this.f2078d;
    }

    public Object[] d() {
        return this.f2080f;
    }

    public g(Object obj, String str, String str2, String str3, Object[] objArr) {
        this.f2075a = obj;
        this.f2076b = str;
        this.f2078d = str2;
        this.f2079e = str3;
        this.f2080f = objArr;
    }
}
