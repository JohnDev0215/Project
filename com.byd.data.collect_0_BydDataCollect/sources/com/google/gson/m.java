package com.google.gson;

import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: JsonObject.java */
/* JADX INFO: loaded from: classes.dex */
public final class m extends j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.google.gson.internal.g<String, j> f4596a = new com.google.gson.internal.g<>();

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof m) && ((m) obj).f4596a.equals(this.f4596a));
    }

    public int hashCode() {
        return this.f4596a.hashCode();
    }

    public void m(String str, j jVar) {
        if (jVar == null) {
            jVar = l.f4595a;
        }
        this.f4596a.put(str, jVar);
    }

    public Set<Map.Entry<String, j>> n() {
        return this.f4596a.entrySet();
    }
}
