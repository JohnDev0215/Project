package k6;

import com.google.gson.internal.LinkedTreeMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: JsonObject.java */
/* JADX INFO: loaded from: classes2.dex */
public final class k extends i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedTreeMap<String, i> f5923a = new LinkedTreeMap<>();

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof k) && ((k) obj).f5923a.equals(this.f5923a));
    }

    public int hashCode() {
        return this.f5923a.hashCode();
    }

    public void i(String str, i iVar) {
        if (iVar == null) {
            iVar = j.f5922a;
        }
        this.f5923a.put(str, iVar);
    }

    public Set<Map.Entry<String, i>> j() {
        return this.f5923a.entrySet();
    }
}
