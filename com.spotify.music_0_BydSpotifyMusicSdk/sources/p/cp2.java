package p;

import java.util.List;
import kotlin.collections.EmptyList;

/* JADX INFO: loaded from: classes.dex */
public final class cp2 implements ls2 {
    public static final cp2 b = new cp2(EmptyList.c);
    public final List a;

    public cp2(List list) {
        this.a = list;
    }

    @Override // p.ls2
    public final List a() {
        return ci1.H(this.a);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !v03.b(cp2.class, obj.getClass())) {
            return false;
        }
        return v03.b(this.a, ((cp2) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return this.a.isEmpty() ? "" : ou.h0(this.a, "/", "{", "}", null, 56);
    }
}
