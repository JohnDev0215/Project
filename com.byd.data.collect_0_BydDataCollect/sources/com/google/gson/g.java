package com.google.gson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: JsonArray.java */
/* JADX INFO: loaded from: classes.dex */
public final class g extends j implements Iterable<j> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<j> f4439b = new ArrayList();

    @Override // com.google.gson.j
    public boolean a() {
        if (this.f4439b.size() == 1) {
            return this.f4439b.get(0).a();
        }
        throw new IllegalStateException();
    }

    @Override // com.google.gson.j
    public double b() {
        if (this.f4439b.size() == 1) {
            return this.f4439b.get(0).b();
        }
        throw new IllegalStateException();
    }

    @Override // com.google.gson.j
    public int c() {
        if (this.f4439b.size() == 1) {
            return this.f4439b.get(0).c();
        }
        throw new IllegalStateException();
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof g) && ((g) obj).f4439b.equals(this.f4439b));
    }

    @Override // com.google.gson.j
    public long g() {
        if (this.f4439b.size() == 1) {
            return this.f4439b.get(0).g();
        }
        throw new IllegalStateException();
    }

    @Override // com.google.gson.j
    public String h() {
        if (this.f4439b.size() == 1) {
            return this.f4439b.get(0).h();
        }
        throw new IllegalStateException();
    }

    public int hashCode() {
        return this.f4439b.hashCode();
    }

    @Override // java.lang.Iterable
    public Iterator<j> iterator() {
        return this.f4439b.iterator();
    }

    public void m(j jVar) {
        if (jVar == null) {
            jVar = l.f4595a;
        }
        this.f4439b.add(jVar);
    }
}
