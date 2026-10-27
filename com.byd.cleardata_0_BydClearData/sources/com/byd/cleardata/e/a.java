package com.byd.cleardata.e;

/* JADX INFO: loaded from: classes.dex */
public class a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected int f2609a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected T f2610b;

    public a() {
        this.f2609a = -1;
        this.f2610b = null;
    }

    public a(int i) {
        this.f2609a = -1;
        this.f2610b = null;
        this.f2609a = i;
    }

    public a(int i, T t) {
        this.f2609a = -1;
        this.f2610b = null;
        this.f2609a = i;
        this.f2610b = t;
    }

    public a(T t) {
        this.f2609a = -1;
        this.f2610b = null;
        this.f2610b = t;
    }

    public T a() {
        return this.f2610b;
    }

    public int b() {
        return this.f2609a;
    }
}
