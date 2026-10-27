package org.greenrobot.eventbus;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Method f3320a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final ThreadMode f3321b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final Class<?> f3322c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final int f3323d;
    final boolean e;
    String f;

    public o(Method method, Class<?> cls, ThreadMode threadMode, int i, boolean z) {
        this.f3320a = method;
        this.f3321b = threadMode;
        this.f3322c = cls;
        this.f3323d = i;
        this.e = z;
    }

    private synchronized void a() {
        if (this.f == null) {
            StringBuilder sb = new StringBuilder(64);
            sb.append(this.f3320a.getDeclaringClass().getName());
            sb.append('#');
            sb.append(this.f3320a.getName());
            sb.append('(');
            sb.append(this.f3322c.getName());
            this.f = sb.toString();
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof o)) {
            return false;
        }
        a();
        o oVar = (o) obj;
        oVar.a();
        return this.f.equals(oVar.f);
    }

    public int hashCode() {
        return this.f3320a.hashCode();
    }
}
