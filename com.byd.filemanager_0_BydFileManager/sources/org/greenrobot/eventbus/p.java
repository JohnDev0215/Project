package org.greenrobot.eventbus;

/* JADX INFO: compiled from: Subscription.java */
/* JADX INFO: loaded from: classes.dex */
final class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Object f4724a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final n f4725b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    volatile boolean f4726c = true;

    p(Object obj, n nVar) {
        this.f4724a = obj;
        this.f4725b = nVar;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof p)) {
            return false;
        }
        p pVar = (p) obj;
        return this.f4724a == pVar.f4724a && this.f4725b.equals(pVar.f4725b);
    }

    public int hashCode() {
        return this.f4724a.hashCode() + this.f4725b.f.hashCode();
    }
}
