package b.c.a;

import com.google.gson.internal.LinkedTreeMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: JsonObject.java */
/* JADX INFO: loaded from: classes3.dex */
public final class k extends i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedTreeMap<String, i> f2199a = new LinkedTreeMap<>();

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof k) && ((k) obj).f2199a.equals(this.f2199a));
    }

    public int hashCode() {
        return this.f2199a.hashCode();
    }

    public void i(String str, i iVar) {
        if (iVar == null) {
            iVar = j.f2198a;
        }
        this.f2199a.put(str, iVar);
    }

    public Set<Map.Entry<String, i>> j() {
        return this.f2199a.entrySet();
    }
}
