package p;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class xi2 {
    public static final xi2 b = new xi2(kotlin.collections.c.h0());
    public final Map a;

    public xi2(Map map) {
        this.a = map;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof xi2) {
            if (v03.b(this.a, ((xi2) obj).a)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "Tags(tags=" + this.a + ')';
    }
}
