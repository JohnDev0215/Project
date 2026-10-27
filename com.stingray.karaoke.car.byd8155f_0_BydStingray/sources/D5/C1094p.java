package d5;

import c5.InterfaceC1027d;
import java.io.Serializable;
import java.util.Arrays;

/* JADX INFO: renamed from: d5.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1094p extends b0 implements Serializable {
    public final InterfaceC1027d l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final b0 f13448m;

    public C1094p(InterfaceC1027d interfaceC1027d, b0 b0Var) {
        this.l = interfaceC1027d;
        b0Var.getClass();
        this.f13448m = b0Var;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        InterfaceC1027d interfaceC1027d = this.l;
        return this.f13448m.compare(interfaceC1027d.apply(obj), interfaceC1027d.apply(obj2));
    }

    @Override // java.util.Comparator
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C1094p)) {
            return false;
        }
        C1094p c1094p = (C1094p) obj;
        return this.l.equals(c1094p.l) && this.f13448m.equals(c1094p.f13448m);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.l, this.f13448m});
    }

    public final String toString() {
        return this.f13448m + ".onResultOf(" + this.l + ")";
    }
}
