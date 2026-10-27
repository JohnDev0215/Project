package b.b.a;

import com.google.gson.internal.LinkedTreeMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: JsonObject.java */
/* JADX INFO: loaded from: classes2.dex */
public final class k extends i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedTreeMap<String, i> f388a = new LinkedTreeMap<>();

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof k) && ((k) obj).f388a.equals(this.f388a));
    }

    public int hashCode() {
        return this.f388a.hashCode();
    }

    public void i(String str, i iVar) {
        if (iVar == null) {
            iVar = j.f387a;
        }
        this.f388a.put(str, iVar);
    }

    public Set<Map.Entry<String, i>> j() {
        return this.f388a.entrySet();
    }
}
