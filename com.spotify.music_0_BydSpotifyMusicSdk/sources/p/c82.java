package p;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class c82 extends he1 {
    public final List k;

    public c82(ArrayList arrayList) {
        this.k = arrayList;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof c82) && v03.b(this.k, ((c82) obj).k);
    }

    public final int hashCode() {
        return this.k.hashCode();
    }

    public final String toString() {
        return hf2.e(new StringBuilder("HasSomeRequiredExtras(errors="), this.k, ')');
    }
}
