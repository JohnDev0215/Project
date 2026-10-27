package k3;

import h2.AbstractC1343A;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class O0 implements InterfaceC1677x0 {
    public final l3.T l;

    public O0(l3.T t10) {
        this.l = t10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != O0.class) {
            return false;
        }
        int i7 = AbstractC1343A.f15195a;
        return Objects.equals(this.l, ((O0) obj).l);
    }

    public final int hashCode() {
        return Objects.hash(this.l);
    }
}
