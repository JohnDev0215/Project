package androidx.compose.material3;

import E0.AbstractC0156f;
import Q.AbstractC0524p;
import Q.K;
import Q.M;
import n0.C1891t;
import n0.InterfaceC1892u;

/* JADX INFO: loaded from: classes.dex */
public final class a implements InterfaceC1892u {
    public final /* synthetic */ DelegatingThemeAwareRippleNode l;

    public a(DelegatingThemeAwareRippleNode delegatingThemeAwareRippleNode) {
        this.l = delegatingThemeAwareRippleNode;
    }

    @Override // n0.InterfaceC1892u
    public final long a() {
        DelegatingThemeAwareRippleNode delegatingThemeAwareRippleNode = this.l;
        long jA = delegatingThemeAwareRippleNode.color.a();
        if (jA != 16) {
            return jA;
        }
        K k10 = (K) AbstractC0156f.i(delegatingThemeAwareRippleNode, M.f5576b);
        if (k10 != null) {
            long j3 = k10.f5568a;
            if (j3 != 16) {
                return j3;
            }
        }
        return ((C1891t) AbstractC0156f.i(delegatingThemeAwareRippleNode, AbstractC0524p.f6016a)).f19453a;
    }
}
