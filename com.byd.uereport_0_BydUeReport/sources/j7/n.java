package j7;

import java.lang.reflect.Method;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: SubscriberMethod.java */
/* JADX INFO: loaded from: classes2.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Method f5666a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ThreadMode f5667b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Class<?> f5668c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f5669d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f5670e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f5671f;

    public n(Method method, Class<?> cls, ThreadMode threadMode, int i8, boolean z7) {
        this.f5666a = method;
        this.f5667b = threadMode;
        this.f5668c = cls;
        this.f5669d = i8;
        this.f5670e = z7;
    }

    public final synchronized void a() {
        if (this.f5671f == null) {
            StringBuilder sb = new StringBuilder(64);
            sb.append(this.f5666a.getDeclaringClass().getName());
            sb.append('#');
            sb.append(this.f5666a.getName());
            sb.append('(');
            sb.append(this.f5668c.getName());
            this.f5671f = sb.toString();
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
        return this.f5671f.equals(nVar.f5671f);
    }

    public int hashCode() {
        return this.f5666a.hashCode();
    }
}
