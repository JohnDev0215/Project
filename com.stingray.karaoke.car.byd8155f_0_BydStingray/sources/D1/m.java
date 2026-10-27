package d1;

import E0.AbstractC0156f;
import E0.AbstractC0164n;
import X5.G;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.compose.ui.node.Owner;
import l0.AbstractC1713d;
import l0.C1727r;
import l0.InterfaceC1716g;
import l0.InterfaceC1718i;
import l0.InterfaceC1721l;

/* JADX INFO: loaded from: classes.dex */
public final class m extends g0.n implements InterfaceC1721l, ViewTreeObserver.OnGlobalFocusChangeListener, View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public View f13327y;

    @Override // g0.n
    public final void A0() {
        j.c(this).removeOnAttachStateChangeListener(this);
        this.f13327y = null;
    }

    public final C1727r H0() {
        g0.n nVar = this.l;
        if (!nVar.f14695x) {
            l9.d.h0("visitLocalDescendants called on an unattached node");
            throw null;
        }
        if ((nVar.o & 1024) != 0) {
            boolean z6 = false;
            for (g0.n nVar2 = nVar.f14688q; nVar2 != null; nVar2 = nVar2.f14688q) {
                if ((nVar2.f14686n & 1024) != 0) {
                    g0.n nVarF = nVar2;
                    V.d dVar = null;
                    while (nVarF != null) {
                        if (nVarF instanceof C1727r) {
                            C1727r c1727r = (C1727r) nVarF;
                            if (z6) {
                                return c1727r;
                            }
                            z6 = true;
                        } else if ((nVarF.f14686n & 1024) != 0 && (nVarF instanceof AbstractC0164n)) {
                            int i7 = 0;
                            for (g0.n nVar3 = ((AbstractC0164n) nVarF).f1690z; nVar3 != null; nVar3 = nVar3.f14688q) {
                                if ((nVar3.f14686n & 1024) != 0) {
                                    i7++;
                                    if (i7 == 1) {
                                        nVarF = nVar3;
                                    } else {
                                        if (dVar == null) {
                                            dVar = new V.d(new g0.n[16]);
                                        }
                                        if (nVarF != null) {
                                            dVar.b(nVarF);
                                            nVarF = null;
                                        }
                                        dVar.b(nVar3);
                                    }
                                }
                            }
                            if (i7 == 1) {
                            }
                        }
                        nVarF = AbstractC0156f.f(dVar);
                    }
                }
            }
        }
        throw new IllegalStateException("Could not find focus target of embedded view wrapper");
    }

    @Override // l0.InterfaceC1721l
    public final void o(InterfaceC1718i interfaceC1718i) {
        interfaceC1718i.b(false);
        interfaceC1718i.c(new G(1, this, m.class, "onEnter", "onEnter-3ESFkO8(I)Landroidx/compose/ui/focus/FocusRequester;", 0, 4));
        interfaceC1718i.d(new G(1, this, m.class, "onExit", "onExit-3ESFkO8(I)Landroidx/compose/ui/focus/FocusRequester;", 0, 5));
    }

    @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
    public final void onGlobalFocusChanged(View view, View view2) {
        if (AbstractC0156f.t(this).f1487t == null) {
            return;
        }
        View viewC = j.c(this);
        InterfaceC1716g focusOwner = AbstractC0156f.u(this).getFocusOwner();
        Owner ownerU = AbstractC0156f.u(this);
        boolean z6 = (view == null || view.equals(ownerU) || !j.a(viewC, view)) ? false : true;
        boolean z10 = (view2 == null || view2.equals(ownerU) || !j.a(viewC, view2)) ? false : true;
        if (z6 && z10) {
            this.f13327y = view2;
            return;
        }
        if (!z10) {
            if (!z6) {
                this.f13327y = null;
                return;
            }
            this.f13327y = null;
            if (H0().I0().a()) {
                ((androidx.compose.ui.focus.b) focusOwner).b(8, false, false);
                return;
            }
            return;
        }
        this.f13327y = view2;
        C1727r c1727rH0 = H0();
        int iOrdinal = c1727rH0.I0().ordinal();
        if (iOrdinal == 0 || iOrdinal == 1 || iOrdinal == 2) {
            return;
        }
        if (iOrdinal != 3) {
            throw new A9.k();
        }
        t4.e eVar = ((androidx.compose.ui.focus.b) focusOwner).f11571h;
        try {
            if (eVar.f22462m) {
                t4.e.b(eVar);
            }
            eVar.f22462m = true;
            AbstractC1713d.w(c1727rH0);
            t4.e.c(eVar);
        } catch (Throwable th) {
            t4.e.c(eVar);
            throw th;
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        view.getViewTreeObserver().addOnGlobalFocusChangeListener(this);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        view.getViewTreeObserver().removeOnGlobalFocusChangeListener(this);
    }

    @Override // g0.n
    public final void z0() {
        j.c(this).addOnAttachStateChangeListener(this);
    }
}
