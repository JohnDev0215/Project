package f9;

import A7.m;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f14645a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f14646b;
    public b c;
    public long d;

    public a(String str, boolean z6) {
        m.f(str, "name");
        this.f14645a = str;
        this.f14646b = z6;
        this.d = -1L;
    }

    public abstract long a();

    public final String toString() {
        return this.f14645a;
    }
}
