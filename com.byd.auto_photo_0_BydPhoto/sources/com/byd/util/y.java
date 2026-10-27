package com.byd.util;

/* JADX INFO: loaded from: classes2.dex */
public class y<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private y<T>.b f3017a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private y<T>.b f3018b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f3019c;
    private y<T>.b d;

    public class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        T f3020a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        y<T>.b f3021b;

        private b(T t) {
            this.f3020a = t;
        }
    }

    public void a(T t) {
        y<T>.b bVar;
        if (this.f3017a == null) {
            bVar = new b(t);
            this.f3017a = bVar;
        } else {
            bVar = new b(t);
            this.f3018b.f3021b = bVar;
        }
        this.f3018b = bVar;
        this.f3019c++;
    }

    public int b() {
        return this.f3019c;
    }

    public boolean c() {
        return this.d != null;
    }

    public T d() {
        y<T>.b bVar = this.d;
        T t = bVar.f3020a;
        this.d = bVar.f3021b;
        return t;
    }

    public void e() {
        this.d = this.f3017a;
    }
}
