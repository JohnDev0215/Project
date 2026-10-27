package c.b.a;

import com.google.gson.internal.LinkedTreeMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: JsonObject.java */
/* JADX INFO: loaded from: classes.dex */
public final class m extends k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedTreeMap<String, k> f2912a = new LinkedTreeMap<>();

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof m) && ((m) obj).f2912a.equals(this.f2912a));
    }

    public int hashCode() {
        return this.f2912a.hashCode();
    }

    public void i(String str, k kVar) {
        if (kVar == null) {
            kVar = l.f2911a;
        }
        this.f2912a.put(str, kVar);
    }

    public Set<Map.Entry<String, k>> j() {
        return this.f2912a.entrySet();
    }
}
