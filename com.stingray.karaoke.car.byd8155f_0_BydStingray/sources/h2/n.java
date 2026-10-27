package h2;

import A.C0050y0;

/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f15220a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public C0050y0 f15221b = new C0050y0(5);
    public boolean c;
    public boolean d;

    public n(Object obj) {
        this.f15220a = obj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || n.class != obj.getClass()) {
            return false;
        }
        return this.f15220a.equals(((n) obj).f15220a);
    }

    public final int hashCode() {
        return this.f15220a.hashCode();
    }
}
