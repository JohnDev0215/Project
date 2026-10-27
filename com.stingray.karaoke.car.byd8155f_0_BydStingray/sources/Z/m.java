package z;

import T.C0735d;
import T.C0738e0;
import T.S;

/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0738e0 f24251a = C0735d.O(j.f24249a, S.f8224q);

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof m) {
            return A7.m.a((l) ((m) obj).f24251a.getValue(), (l) this.f24251a.getValue());
        }
        return false;
    }

    public final int hashCode() {
        return ((l) this.f24251a.getValue()).hashCode();
    }

    public final String toString() {
        return "ContextMenuState(status=" + ((l) this.f24251a.getValue()) + ')';
    }
}
