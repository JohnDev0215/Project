package org.greenrobot.eventbus;

import java.lang.reflect.Method;

/* JADX INFO: compiled from: SubscriberMethod.java */
/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Method f4709a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final ThreadMode f4710b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final Class<?> f4711c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final int f4712d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final boolean f4713e;
    String f;

    public n(Method method, Class<?> cls, ThreadMode threadMode, int i, boolean z) {
        this.f4709a = method;
        this.f4710b = threadMode;
        this.f4711c = cls;
        this.f4712d = i;
        this.f4713e = z;
    }

    private synchronized void a() {
        if (this.f == null) {
            StringBuilder sb = new StringBuilder(64);
            sb.append(this.f4709a.getDeclaringClass().getName());
            sb.append('#');
            sb.append(this.f4709a.getName());
            sb.append('(');
            sb.append(this.f4711c.getName());
            this.f = sb.toString();
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof n)) {
            return false;
        }
        a();
        n nVar = (n) obj;
        nVar.a();
        return this.f.equals(nVar.f);
    }

    public int hashCode() {
        return this.f4709a.hashCode();
    }
}
