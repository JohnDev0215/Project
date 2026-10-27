package org.greenrobot.eventbus;

/* JADX INFO: compiled from: Subscription.java */
/* JADX INFO: loaded from: classes.dex */
final class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Object f4169a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final n f4170b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    volatile boolean f4171c = true;

    p(Object obj, n nVar) {
        this.f4169a = obj;
        this.f4170b = nVar;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof p)) {
            return false;
        }
        p pVar = (p) obj;
        return this.f4169a == pVar.f4169a && this.f4170b.equals(pVar.f4170b);
    }

    public int hashCode() {
        return this.f4169a.hashCode() + this.f4170b.f.hashCode();
    }
}
