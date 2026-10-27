package S6;

import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f8073a;

    public g(String str) {
        this.f8073a = str;
    }

    public final Object a(c cVar) {
        Object obj = cVar.f8070a.get(this);
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException(this.f8073a);
    }

    public final void b(c cVar, Object obj) {
        HashMap map = cVar.f8070a;
        if (obj == null) {
            map.remove(this);
        } else {
            map.put(this, obj);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || g.class != obj.getClass()) {
            return false;
        }
        return this.f8073a.equals(((g) obj).f8073a);
    }

    public final int hashCode() {
        return this.f8073a.hashCode();
    }

    public final String toString() {
        return android.hardware.bydauto.ac.tsimpl.a.l(new StringBuilder("Prop{name='"), this.f8073a, "'}");
    }
}
