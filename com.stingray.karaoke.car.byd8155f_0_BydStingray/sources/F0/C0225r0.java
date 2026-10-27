package F0;

import D.C0130l;
import T.C0735d;
import T.C0738e0;
import T.C0754m0;
import T.C0761q;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: F0.r0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0225r0 extends AbstractC0191a {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final C0738e0 f2253t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f2254u;

    public C0225r0(h.n nVar) {
        super(nVar, null, 0);
        this.f2253t = C0735d.O(null, T.S.f8224q);
    }

    public static /* synthetic */ void getShouldCreateCompositionOnAttachedToWindow$annotations() {
    }

    @Override // F0.AbstractC0191a
    public final void a(int i7, C0761q c0761q) {
        int i10;
        c0761q.S(420213850);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(this) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i10 & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            InterfaceC2785n interfaceC2785n = (InterfaceC2785n) this.f2253t.getValue();
            if (interfaceC2785n == null) {
                c0761q.Q(358373017);
            } else {
                c0761q.Q(150107752);
                interfaceC2785n.invoke(c0761q, 0);
            }
            c0761q.p(false);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0130l(i7, 3, this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return C0225r0.class.getName();
    }

    @Override // F0.AbstractC0191a
    public boolean getShouldCreateCompositionOnAttachedToWindow() {
        return this.f2254u;
    }

    public final void setContent(InterfaceC2785n interfaceC2785n) {
        this.f2254u = true;
        this.f2253t.setValue(interfaceC2785n);
        if (isAttachedToWindow()) {
            if (this.o == null && !isAttachedToWindow()) {
                throw new IllegalStateException("createComposition requires either a parent reference or the View to be attachedto a window. Attach the View or call setParentCompositionReference.");
            }
            d();
        }
    }
}
