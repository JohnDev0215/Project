package com.google.gson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: JsonArray.java */
/* JADX INFO: loaded from: classes.dex */
public final class f extends i implements Iterable<i> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<i> f3989b = new ArrayList();

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof f) && ((f) obj).f3989b.equals(this.f3989b));
    }

    public void h(i iVar) {
        if (iVar == null) {
            iVar = j.f4149a;
        }
        this.f3989b.add(iVar);
    }

    public int hashCode() {
        return this.f3989b.hashCode();
    }

    @Override // java.lang.Iterable
    public Iterator<i> iterator() {
        return this.f3989b.iterator();
    }
}
