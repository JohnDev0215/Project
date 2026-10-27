package g1;

import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f14738a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LinkedHashMap f14739b = new LinkedHashMap();

    public o(Object obj) {
        this.f14738a = obj;
    }

    public Object a() {
        return this.f14738a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof o) && A7.m.a(a(), ((o) obj).a());
    }

    public final int hashCode() {
        return a().hashCode();
    }
}
