package com.bumptech.glide.m;

/* JADX INFO: compiled from: MultiClassKey.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Class<?> f3184a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Class<?> f3185b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Class<?> f3186c;

    public i() {
    }

    public void a(Class<?> cls, Class<?> cls2, Class<?> cls3) {
        this.f3184a = cls;
        this.f3185b = cls2;
        this.f3186c = cls3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || i.class != obj.getClass()) {
            return false;
        }
        i iVar = (i) obj;
        return this.f3184a.equals(iVar.f3184a) && this.f3185b.equals(iVar.f3185b) && k.c(this.f3186c, iVar.f3186c);
    }

    public int hashCode() {
        int iHashCode = ((this.f3184a.hashCode() * 31) + this.f3185b.hashCode()) * 31;
        Class<?> cls = this.f3186c;
        return iHashCode + (cls != null ? cls.hashCode() : 0);
    }

    public String toString() {
        return "MultiClassKey{first=" + this.f3184a + ", second=" + this.f3185b + '}';
    }

    public i(Class<?> cls, Class<?> cls2, Class<?> cls3) {
        a(cls, cls2, cls3);
    }
}
