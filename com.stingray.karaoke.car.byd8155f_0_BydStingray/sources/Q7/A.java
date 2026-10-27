package Q7;

import java.util.List;
import o8.C2057b;

/* JADX INFO: loaded from: classes.dex */
public final class A {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C2057b f7167a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f7168b;

    public A(C2057b c2057b, List list) {
        A7.m.f(c2057b, "classId");
        this.f7167a = c2057b;
        this.f7168b = list;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof A)) {
            return false;
        }
        A a10 = (A) obj;
        return A7.m.a(this.f7167a, a10.f7167a) && A7.m.a(this.f7168b, a10.f7168b);
    }

    public final int hashCode() {
        return this.f7168b.hashCode() + (this.f7167a.hashCode() * 31);
    }

    public final String toString() {
        return "ClassRequest(classId=" + this.f7167a + ", typeParametersCount=" + this.f7168b + ')';
    }
}
