package com.byd.music.f;

/* JADX INFO: compiled from: BaseEvent.java */
/* JADX INFO: loaded from: classes.dex */
public class a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected int f3917a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected T f3918b;

    public a() {
        this.f3917a = -1;
        this.f3918b = null;
    }

    public T a() {
        return this.f3918b;
    }

    public int b() {
        return this.f3917a;
    }

    public a(int i) {
        this.f3917a = -1;
        this.f3918b = null;
        this.f3917a = i;
    }

    public a(T t) {
        this.f3917a = -1;
        this.f3918b = null;
        this.f3918b = t;
    }

    public a(int i, T t) {
        this.f3917a = -1;
        this.f3918b = null;
        this.f3917a = i;
        this.f3918b = t;
    }
}
