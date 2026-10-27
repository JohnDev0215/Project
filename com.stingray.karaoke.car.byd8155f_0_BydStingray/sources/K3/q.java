package K3;

import java.util.Map;
import n7.v;

/* JADX INFO: loaded from: classes.dex */
public final class q {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final q f3583b = new q(v.l);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map f3584a;

    public q(Map map) {
        this.f3584a = map;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof q) {
            if (A7.m.a(this.f3584a, ((q) obj).f3584a)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f3584a.hashCode();
    }

    public final String toString() {
        return "Tags(tags=" + this.f3584a + ')';
    }
}
