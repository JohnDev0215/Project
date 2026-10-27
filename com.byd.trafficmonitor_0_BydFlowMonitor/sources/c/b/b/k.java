package c.b.b;

import com.google.gson.internal.LinkedTreeMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: JsonObject.java */
/* JADX INFO: loaded from: classes.dex */
public final class k extends i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedTreeMap<String, i> f4170a = new LinkedTreeMap<>();

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof k) && ((k) obj).f4170a.equals(this.f4170a));
    }

    public int hashCode() {
        return this.f4170a.hashCode();
    }

    public void i(String str, i iVar) {
        if (iVar == null) {
            iVar = j.f4169a;
        }
        this.f4170a.put(str, iVar);
    }

    public Set<Map.Entry<String, i>> j() {
        return this.f4170a.entrySet();
    }
}
