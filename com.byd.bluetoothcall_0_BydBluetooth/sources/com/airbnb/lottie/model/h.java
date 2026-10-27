package com.airbnb.lottie.model;

/* JADX INFO: compiled from: MutablePair.java */
/* JADX INFO: loaded from: classes.dex */
public class h<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    T f2993a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    T f2994b;

    private static boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public void b(T t, T t2) {
        this.f2993a = t;
        this.f2994b = t2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof b.g.j.d)) {
            return false;
        }
        b.g.j.d dVar = (b.g.j.d) obj;
        return a(dVar.f2292a, this.f2993a) && a(dVar.f2293b, this.f2994b);
    }

    public int hashCode() {
        T t = this.f2993a;
        int iHashCode = t == null ? 0 : t.hashCode();
        T t2 = this.f2994b;
        return iHashCode ^ (t2 != null ? t2.hashCode() : 0);
    }

    public String toString() {
        return "Pair{" + String.valueOf(this.f2993a) + " " + String.valueOf(this.f2994b) + "}";
    }
}
