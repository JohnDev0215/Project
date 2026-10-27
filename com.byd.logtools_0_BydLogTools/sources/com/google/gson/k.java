package com.google.gson;

import com.google.gson.internal.LinkedTreeMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: JsonObject.java */
/* JADX INFO: loaded from: classes.dex */
public final class k extends i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final LinkedTreeMap<String, i> f4150a = new LinkedTreeMap<>();

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof k) && ((k) obj).f4150a.equals(this.f4150a));
    }

    public void h(String str, i iVar) {
        LinkedTreeMap<String, i> linkedTreeMap = this.f4150a;
        if (iVar == null) {
            iVar = j.f4149a;
        }
        linkedTreeMap.put(str, iVar);
    }

    public int hashCode() {
        return this.f4150a.hashCode();
    }

    public Set<Map.Entry<String, i>> i() {
        return this.f4150a.entrySet();
    }
}
