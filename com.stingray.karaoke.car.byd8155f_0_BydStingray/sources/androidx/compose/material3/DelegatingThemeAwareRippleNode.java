package androidx.compose.material3;

import C.k;
import E0.AbstractC0156f;
import E0.AbstractC0164n;
import E0.InterfaceC0162l;
import E0.f0;
import Q.N;
import androidx.compose.material.ripple.RippleNode;
import kotlin.Metadata;
import n0.InterfaceC1892u;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/compose/material3/DelegatingThemeAwareRippleNode;", "LE0/n;", "LE0/l;", "LE0/f0;", "Ln0/u;", "color", "Ln0/u;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class DelegatingThemeAwareRippleNode extends AbstractC0164n implements InterfaceC0162l, f0 {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final k f11531A;
    public final boolean B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final float f11532C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public RippleNode f11533D;
    private final InterfaceC1892u color;

    public DelegatingThemeAwareRippleNode(k kVar, boolean z6, float f10, N n10) {
        this.f11531A = kVar;
        this.B = z6;
        this.f11532C = f10;
        this.color = n10;
    }

    @Override // E0.f0
    public final void L() {
        AbstractC0156f.q(this, new b(this, 1));
    }

    @Override // g0.n
    public final void z0() {
        AbstractC0156f.q(this, new b(this, 1));
    }
}
