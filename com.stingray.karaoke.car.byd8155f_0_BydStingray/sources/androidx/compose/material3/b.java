package androidx.compose.material3;

import A7.o;
import C.k;
import E0.AbstractC0156f;
import P.C0490b;
import P.C0492d;
import P.h;
import P.u;
import P.z;
import Q.K;
import Q.L;
import Q.M;
import androidx.compose.material.ripple.RippleNode;
import m7.q;
import x.C0;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class b extends o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f11537m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ DelegatingThemeAwareRippleNode f11538n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ b(DelegatingThemeAwareRippleNode delegatingThemeAwareRippleNode, int i7) {
        super(0);
        this.f11537m = i7;
        this.f11538n = delegatingThemeAwareRippleNode;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        h hVar;
        DelegatingThemeAwareRippleNode delegatingThemeAwareRippleNode = this.f11538n;
        switch (this.f11537m) {
            case 0:
                K k10 = (K) AbstractC0156f.i(delegatingThemeAwareRippleNode, M.f5576b);
                return (k10 == null || (hVar = k10.f5569b) == null) ? L.f5572a : hVar;
            default:
                if (((K) AbstractC0156f.i(delegatingThemeAwareRippleNode, M.f5576b)) == null) {
                    RippleNode rippleNode = delegatingThemeAwareRippleNode.f11533D;
                    if (rippleNode != null) {
                        delegatingThemeAwareRippleNode.I0(rippleNode);
                    }
                } else if (delegatingThemeAwareRippleNode.f11533D == null) {
                    a aVar = new a(delegatingThemeAwareRippleNode);
                    b bVar = new b(delegatingThemeAwareRippleNode, 0);
                    C0 c02 = u.f5257a;
                    boolean z6 = z.f5263a;
                    boolean z10 = delegatingThemeAwareRippleNode.B;
                    float f10 = delegatingThemeAwareRippleNode.f11532C;
                    k kVar = delegatingThemeAwareRippleNode.f11531A;
                    RippleNode c0492d = z6 ? new C0492d(kVar, z10, f10, aVar, bVar) : new C0490b(kVar, z10, f10, aVar, bVar);
                    delegatingThemeAwareRippleNode.H0(c0492d);
                    delegatingThemeAwareRippleNode.f11533D = c0492d;
                }
                return q.f19267a;
        }
    }
}
