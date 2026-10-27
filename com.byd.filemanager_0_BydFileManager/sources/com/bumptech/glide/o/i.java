package com.bumptech.glide.o;

/* JADX INFO: compiled from: MultiClassKey.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Class<?> f3465a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Class<?> f3466b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Class<?> f3467c;

    public i() {
    }

    public void a(Class<?> cls, Class<?> cls2, Class<?> cls3) {
        this.f3465a = cls;
        this.f3466b = cls2;
        this.f3467c = cls3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || i.class != obj.getClass()) {
            return false;
        }
        i iVar = (i) obj;
        return this.f3465a.equals(iVar.f3465a) && this.f3466b.equals(iVar.f3466b) && k.c(this.f3467c, iVar.f3467c);
    }

    public int hashCode() {
        int iHashCode = ((this.f3465a.hashCode() * 31) + this.f3466b.hashCode()) * 31;
        Class<?> cls = this.f3467c;
        return iHashCode + (cls != null ? cls.hashCode() : 0);
    }

    public String toString() {
        return "MultiClassKey{first=" + this.f3465a + ", second=" + this.f3466b + '}';
    }

    public i(Class<?> cls, Class<?> cls2, Class<?> cls3) {
        a(cls, cls2, cls3);
    }
}
