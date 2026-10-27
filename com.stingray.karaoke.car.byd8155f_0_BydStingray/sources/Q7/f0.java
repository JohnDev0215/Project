package Q7;

import o7.C2053e;

/* JADX INFO: loaded from: classes.dex */
public abstract class f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f7185a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f7186b;

    public f0(String str, boolean z6) {
        this.f7185a = str;
        this.f7186b = z6;
    }

    public Integer a(f0 f0Var) {
        A7.m.f(f0Var, "visibility");
        C2053e c2053e = e0.f7184a;
        if (this == f0Var) {
            return 0;
        }
        C2053e c2053e2 = e0.f7184a;
        Integer num = (Integer) c2053e2.get(this);
        Integer num2 = (Integer) c2053e2.get(f0Var);
        if (num == null || num2 == null || num.equals(num2)) {
            return null;
        }
        return Integer.valueOf(num.intValue() - num2.intValue());
    }

    public String b() {
        return this.f7185a;
    }

    public f0 c() {
        return this;
    }

    public final String toString() {
        return b();
    }
}
