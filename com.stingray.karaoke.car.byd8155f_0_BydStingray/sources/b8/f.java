package B8;

import o8.C2057b;

/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C2057b f757a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0092d f758b;

    public f(C2057b c2057b, C0092d c0092d) {
        A7.m.f(c2057b, "classId");
        this.f757a = c2057b;
        this.f758b = c0092d;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof f) {
            if (A7.m.a(this.f757a, ((f) obj).f757a)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f757a.hashCode();
    }
}
