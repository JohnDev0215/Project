package f.a.a;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Method f2571a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final r f2572b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Class<?> f2573c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f2574d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f2575e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f2576f;

    public o(Method method, Class<?> cls, r rVar, int i, boolean z) {
        this.f2571a = method;
        this.f2572b = rVar;
        this.f2573c = cls;
        this.f2574d = i;
        this.f2575e = z;
    }

    public final synchronized void a() {
        if (this.f2576f == null) {
            StringBuilder sb = new StringBuilder(64);
            sb.append(this.f2571a.getDeclaringClass().getName());
            sb.append('#');
            sb.append(this.f2571a.getName());
            sb.append('(');
            sb.append(this.f2573c.getName());
            this.f2576f = sb.toString();
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
        return this.f2576f.equals(oVar.f2576f);
    }

    public int hashCode() {
        return this.f2571a.hashCode();
    }
}
