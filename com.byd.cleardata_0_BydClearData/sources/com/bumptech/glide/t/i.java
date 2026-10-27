package com.bumptech.glide.t;

/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Class<?> f2551a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Class<?> f2552b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Class<?> f2553c;

    public i() {
    }

    public i(Class<?> cls, Class<?> cls2, Class<?> cls3) {
        a(cls, cls2, cls3);
    }

    public void a(Class<?> cls, Class<?> cls2, Class<?> cls3) {
        this.f2551a = cls;
        this.f2552b = cls2;
        this.f2553c = cls3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || i.class != obj.getClass()) {
            return false;
        }
        i iVar = (i) obj;
        return this.f2551a.equals(iVar.f2551a) && this.f2552b.equals(iVar.f2552b) && k.c(this.f2553c, iVar.f2553c);
    }

    public int hashCode() {
        int iHashCode = ((this.f2551a.hashCode() * 31) + this.f2552b.hashCode()) * 31;
        Class<?> cls = this.f2553c;
        return iHashCode + (cls != null ? cls.hashCode() : 0);
    }

    public String toString() {
        return "MultiClassKey{first=" + this.f2551a + ", second=" + this.f2552b + '}';
    }
}
