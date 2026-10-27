package org.greenrobot.eventbus;

/* JADX INFO: loaded from: classes.dex */
final class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Object f3332a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final o f3333b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    volatile boolean f3334c = true;

    q(Object obj, o oVar) {
        this.f3332a = obj;
        this.f3333b = oVar;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof q)) {
            return false;
        }
        q qVar = (q) obj;
        return this.f3332a == qVar.f3332a && this.f3333b.equals(qVar.f3333b);
    }

    public int hashCode() {
        return this.f3332a.hashCode() + this.f3333b.f.hashCode();
    }
}
