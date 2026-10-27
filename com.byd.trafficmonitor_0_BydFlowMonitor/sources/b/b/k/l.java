package b.b.k;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.appcompat.widget.ActionBarContainer;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.ScrollingTabContainerView;
import androidx.appcompat.widget.Toolbar;
import b.b.k.a;
import b.b.p.b;
import b.b.p.j.g;
import b.b.q.p;
import b.h.m.a0;
import b.h.m.t;
import b.h.m.x;
import b.h.m.y;
import b.h.m.z;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: compiled from: WindowDecorActionBar.java */
/* JADX INFO: loaded from: classes.dex */
public class l extends b.b.k.a implements ActionBarOverlayLayout.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Interpolator f1481a = new AccelerateInterpolator();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Interpolator f1482b = new DecelerateInterpolator();
    public b.b.p.h A;
    public boolean B;
    public boolean C;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f1483c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Context f1484d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Activity f1485e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ActionBarOverlayLayout f1486f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ActionBarContainer f1487g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public p f1488h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ActionBarContextView f1489i;
    public View j;
    public ScrollingTabContainerView k;
    public boolean n;
    public d o;
    public b.b.p.b p;
    public b.a q;
    public boolean r;
    public boolean t;
    public boolean w;
    public boolean x;
    public boolean y;
    public ArrayList<?> l = new ArrayList<>();
    public int m = -1;
    public ArrayList<a.b> s = new ArrayList<>();
    public int u = 0;
    public boolean v = true;
    public boolean z = true;
    public final y D = new a();
    public final y E = new b();
    public final a0 F = new c();

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    public class a extends z {
        public a() {
        }

        @Override // b.h.m.y
        public void a(View view) {
            View view2;
            l lVar = l.this;
            if (lVar.v && (view2 = lVar.j) != null) {
                view2.setTranslationY(0.0f);
                l.this.f1487g.setTranslationY(0.0f);
            }
            l.this.f1487g.setVisibility(8);
            l.this.f1487g.setTransitioning(false);
            l lVar2 = l.this;
            lVar2.A = null;
            lVar2.x();
            ActionBarOverlayLayout actionBarOverlayLayout = l.this.f1486f;
            if (actionBarOverlayLayout != null) {
                t.h0(actionBarOverlayLayout);
            }
        }
    }

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    public class b extends z {
        public b() {
        }

        @Override // b.h.m.y
        public void a(View view) {
            l lVar = l.this;
            lVar.A = null;
            lVar.f1487g.requestLayout();
        }
    }

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    public class c implements a0 {
        public c() {
        }

        @Override // b.h.m.a0
        public void a(View view) {
            ((View) l.this.f1487g.getParent()).invalidate();
        }
    }

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    public class d extends b.b.p.b implements g.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Context f1493d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final b.b.p.j.g f1494e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public b.a f1495f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public WeakReference<View> f1496g;

        public d(Context context, b.a aVar) {
            this.f1493d = context;
            this.f1495f = aVar;
            b.b.p.j.g gVarW = new b.b.p.j.g(context).W(1);
            this.f1494e = gVarW;
            gVarW.V(this);
        }

        @Override // b.b.p.j.g.a
        public boolean a(b.b.p.j.g gVar, MenuItem menuItem) {
            b.a aVar = this.f1495f;
            if (aVar != null) {
                return aVar.b(this, menuItem);
            }
            return false;
        }

        @Override // b.b.p.j.g.a
        public void b(b.b.p.j.g gVar) {
            if (this.f1495f == null) {
                return;
            }
            k();
            l.this.f1489i.l();
        }

        @Override // b.b.p.b
        public void c() {
            l lVar = l.this;
            if (lVar.o != this) {
                return;
            }
            if (l.w(lVar.w, lVar.x, false)) {
                this.f1495f.d(this);
            } else {
                l lVar2 = l.this;
                lVar2.p = this;
                lVar2.q = this.f1495f;
            }
            this.f1495f = null;
            l.this.v(false);
            l.this.f1489i.g();
            l.this.f1488h.o().sendAccessibilityEvent(32);
            l lVar3 = l.this;
            lVar3.f1486f.setHideOnContentScrollEnabled(lVar3.C);
            l.this.o = null;
        }

        @Override // b.b.p.b
        public View d() {
            WeakReference<View> weakReference = this.f1496g;
            if (weakReference != null) {
                return weakReference.get();
            }
            return null;
        }

        @Override // b.b.p.b
        public Menu e() {
            return this.f1494e;
        }

        @Override // b.b.p.b
        public MenuInflater f() {
            return new b.b.p.g(this.f1493d);
        }

        @Override // b.b.p.b
        public CharSequence g() {
            return l.this.f1489i.getSubtitle();
        }

        @Override // b.b.p.b
        public CharSequence i() {
            return l.this.f1489i.getTitle();
        }

        @Override // b.b.p.b
        public void k() {
            if (l.this.o != this) {
                return;
            }
            this.f1494e.h0();
            try {
                this.f1495f.a(this, this.f1494e);
            } finally {
                this.f1494e.g0();
            }
        }

        @Override // b.b.p.b
        public boolean l() {
            return l.this.f1489i.j();
        }

        @Override // b.b.p.b
        public void m(View view) {
            l.this.f1489i.setCustomView(view);
            this.f1496g = new WeakReference<>(view);
        }

        @Override // b.b.p.b
        public void n(int i2) {
            o(l.this.f1483c.getResources().getString(i2));
        }

        @Override // b.b.p.b
        public void o(CharSequence charSequence) {
            l.this.f1489i.setSubtitle(charSequence);
        }

        @Override // b.b.p.b
        public void q(int i2) {
            r(l.this.f1483c.getResources().getString(i2));
        }

        @Override // b.b.p.b
        public void r(CharSequence charSequence) {
            l.this.f1489i.setTitle(charSequence);
        }

        @Override // b.b.p.b
        public void s(boolean z) {
            super.s(z);
            l.this.f1489i.setTitleOptional(z);
        }

        public boolean t() {
            this.f1494e.h0();
            try {
                return this.f1495f.c(this, this.f1494e);
            } finally {
                this.f1494e.g0();
            }
        }
    }

    public l(Activity activity, boolean z) {
        this.f1485e = activity;
        View decorView = activity.getWindow().getDecorView();
        D(decorView);
        if (z) {
            return;
        }
        this.j = decorView.findViewById(R.id.content);
    }

    public static boolean w(boolean z, boolean z2, boolean z3) {
        if (z3) {
            return true;
        }
        return (z || z2) ? false : true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final p A(View view) {
        if (view instanceof p) {
            return (p) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Can't make a decor toolbar out of ");
        sb.append(view != 0 ? view.getClass().getSimpleName() : "null");
        throw new IllegalStateException(sb.toString());
    }

    public int B() {
        return this.f1488h.r();
    }

    public final void C() {
        if (this.y) {
            this.y = false;
            ActionBarOverlayLayout actionBarOverlayLayout = this.f1486f;
            if (actionBarOverlayLayout != null) {
                actionBarOverlayLayout.setShowingForActionMode(false);
            }
            M(false);
        }
    }

    public final void D(View view) {
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) view.findViewById(b.b.f.decor_content_parent);
        this.f1486f = actionBarOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        this.f1488h = A(view.findViewById(b.b.f.action_bar));
        this.f1489i = (ActionBarContextView) view.findViewById(b.b.f.action_context_bar);
        ActionBarContainer actionBarContainer = (ActionBarContainer) view.findViewById(b.b.f.action_bar_container);
        this.f1487g = actionBarContainer;
        p pVar = this.f1488h;
        if (pVar == null || this.f1489i == null || actionBarContainer == null) {
            throw new IllegalStateException(l.class.getSimpleName() + " can only be used with a compatible window decor layout");
        }
        this.f1483c = pVar.q();
        boolean z = (this.f1488h.j() & 4) != 0;
        if (z) {
            this.n = true;
        }
        b.b.p.a aVarB = b.b.p.a.b(this.f1483c);
        J(aVarB.a() || z);
        H(aVarB.g());
        TypedArray typedArrayObtainStyledAttributes = this.f1483c.obtainStyledAttributes(null, b.b.j.ActionBar, b.b.a.actionBarStyle, 0);
        if (typedArrayObtainStyledAttributes.getBoolean(b.b.j.ActionBar_hideOnContentScroll, false)) {
            I(true);
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.b.j.ActionBar_elevation, 0);
        if (dimensionPixelSize != 0) {
            G(dimensionPixelSize);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public void E(boolean z) {
        F(z ? 4 : 0, 4);
    }

    public void F(int i2, int i3) {
        int iJ = this.f1488h.j();
        if ((i3 & 4) != 0) {
            this.n = true;
        }
        this.f1488h.x((i2 & i3) | ((~i3) & iJ));
    }

    public void G(float f2) {
        t.r0(this.f1487g, f2);
    }

    public final void H(boolean z) {
        this.t = z;
        if (z) {
            this.f1487g.setTabContainer(null);
            this.f1488h.n(this.k);
        } else {
            this.f1488h.n(null);
            this.f1487g.setTabContainer(this.k);
        }
        boolean z2 = B() == 2;
        ScrollingTabContainerView scrollingTabContainerView = this.k;
        if (scrollingTabContainerView != null) {
            if (z2) {
                scrollingTabContainerView.setVisibility(0);
                ActionBarOverlayLayout actionBarOverlayLayout = this.f1486f;
                if (actionBarOverlayLayout != null) {
                    t.h0(actionBarOverlayLayout);
                }
            } else {
                scrollingTabContainerView.setVisibility(8);
            }
        }
        this.f1488h.w(!this.t && z2);
        this.f1486f.setHasNonEmbeddedTabs(!this.t && z2);
    }

    public void I(boolean z) {
        if (z && !this.f1486f.w()) {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
        }
        this.C = z;
        this.f1486f.setHideOnContentScrollEnabled(z);
    }

    public void J(boolean z) {
        this.f1488h.p(z);
    }

    public final boolean K() {
        return t.P(this.f1487g);
    }

    public final void L() {
        if (this.y) {
            return;
        }
        this.y = true;
        ActionBarOverlayLayout actionBarOverlayLayout = this.f1486f;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setShowingForActionMode(true);
        }
        M(false);
    }

    public final void M(boolean z) {
        if (w(this.w, this.x, this.y)) {
            if (this.z) {
                return;
            }
            this.z = true;
            z(z);
            return;
        }
        if (this.z) {
            this.z = false;
            y(z);
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void a(boolean z) {
        this.v = z;
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void b() {
        if (this.x) {
            this.x = false;
            M(true);
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void c() {
        b.b.p.h hVar = this.A;
        if (hVar != null) {
            hVar.a();
            this.A = null;
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void d(int i2) {
        this.u = i2;
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void e() {
        if (this.x) {
            return;
        }
        this.x = true;
        M(true);
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void f() {
    }

    @Override // b.b.k.a
    public boolean h() {
        p pVar = this.f1488h;
        if (pVar == null || !pVar.u()) {
            return false;
        }
        this.f1488h.collapseActionView();
        return true;
    }

    @Override // b.b.k.a
    public void i(boolean z) {
        if (z == this.r) {
            return;
        }
        this.r = z;
        int size = this.s.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.s.get(i2).a(z);
        }
    }

    @Override // b.b.k.a
    public int j() {
        return this.f1488h.j();
    }

    @Override // b.b.k.a
    public Context k() {
        if (this.f1484d == null) {
            TypedValue typedValue = new TypedValue();
            this.f1483c.getTheme().resolveAttribute(b.b.a.actionBarWidgetTheme, typedValue, true);
            int i2 = typedValue.resourceId;
            if (i2 != 0) {
                this.f1484d = new ContextThemeWrapper(this.f1483c, i2);
            } else {
                this.f1484d = this.f1483c;
            }
        }
        return this.f1484d;
    }

    @Override // b.b.k.a
    public void m(Configuration configuration) {
        H(b.b.p.a.b(this.f1483c).g());
    }

    @Override // b.b.k.a
    public boolean o(int i2, KeyEvent keyEvent) {
        Menu menuE;
        d dVar = this.o;
        if (dVar == null || (menuE = dVar.e()) == null) {
            return false;
        }
        menuE.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
        return menuE.performShortcut(i2, keyEvent, 0);
    }

    @Override // b.b.k.a
    public void r(boolean z) {
        if (this.n) {
            return;
        }
        E(z);
    }

    @Override // b.b.k.a
    public void s(boolean z) {
        b.b.p.h hVar;
        this.B = z;
        if (z || (hVar = this.A) == null) {
            return;
        }
        hVar.a();
    }

    @Override // b.b.k.a
    public void t(CharSequence charSequence) {
        this.f1488h.setWindowTitle(charSequence);
    }

    @Override // b.b.k.a
    public b.b.p.b u(b.a aVar) {
        d dVar = this.o;
        if (dVar != null) {
            dVar.c();
        }
        this.f1486f.setHideOnContentScrollEnabled(false);
        this.f1489i.k();
        d dVar2 = new d(this.f1489i.getContext(), aVar);
        if (!dVar2.t()) {
            return null;
        }
        this.o = dVar2;
        dVar2.k();
        this.f1489i.h(dVar2);
        v(true);
        this.f1489i.sendAccessibilityEvent(32);
        return dVar2;
    }

    public void v(boolean z) {
        x xVarS;
        x xVarF;
        if (z) {
            L();
        } else {
            C();
        }
        if (!K()) {
            if (z) {
                this.f1488h.k(4);
                this.f1489i.setVisibility(0);
                return;
            } else {
                this.f1488h.k(0);
                this.f1489i.setVisibility(8);
                return;
            }
        }
        if (z) {
            xVarF = this.f1488h.s(4, 100L);
            xVarS = this.f1489i.f(0, 200L);
        } else {
            xVarS = this.f1488h.s(0, 200L);
            xVarF = this.f1489i.f(8, 100L);
        }
        b.b.p.h hVar = new b.b.p.h();
        hVar.d(xVarF, xVarS);
        hVar.h();
    }

    public void x() {
        b.a aVar = this.q;
        if (aVar != null) {
            aVar.d(this.p);
            this.p = null;
            this.q = null;
        }
    }

    public void y(boolean z) {
        View view;
        b.b.p.h hVar = this.A;
        if (hVar != null) {
            hVar.a();
        }
        if (this.u != 0 || (!this.B && !z)) {
            this.D.a(null);
            return;
        }
        this.f1487g.setAlpha(1.0f);
        this.f1487g.setTransitioning(true);
        b.b.p.h hVar2 = new b.b.p.h();
        float f2 = -this.f1487g.getHeight();
        if (z) {
            this.f1487g.getLocationInWindow(new int[]{0, 0});
            f2 -= r5[1];
        }
        x xVarK = t.c(this.f1487g).k(f2);
        xVarK.i(this.F);
        hVar2.c(xVarK);
        if (this.v && (view = this.j) != null) {
            hVar2.c(t.c(view).k(f2));
        }
        hVar2.f(f1481a);
        hVar2.e(250L);
        hVar2.g(this.D);
        this.A = hVar2;
        hVar2.h();
    }

    public void z(boolean z) {
        View view;
        View view2;
        b.b.p.h hVar = this.A;
        if (hVar != null) {
            hVar.a();
        }
        this.f1487g.setVisibility(0);
        if (this.u == 0 && (this.B || z)) {
            this.f1487g.setTranslationY(0.0f);
            float f2 = -this.f1487g.getHeight();
            if (z) {
                this.f1487g.getLocationInWindow(new int[]{0, 0});
                f2 -= r5[1];
            }
            this.f1487g.setTranslationY(f2);
            b.b.p.h hVar2 = new b.b.p.h();
            x xVarK = t.c(this.f1487g).k(0.0f);
            xVarK.i(this.F);
            hVar2.c(xVarK);
            if (this.v && (view2 = this.j) != null) {
                view2.setTranslationY(f2);
                hVar2.c(t.c(this.j).k(0.0f));
            }
            hVar2.f(f1482b);
            hVar2.e(250L);
            hVar2.g(this.E);
            this.A = hVar2;
            hVar2.h();
        } else {
            this.f1487g.setAlpha(1.0f);
            this.f1487g.setTranslationY(0.0f);
            if (this.v && (view = this.j) != null) {
                view.setTranslationY(0.0f);
            }
            this.E.a(null);
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.f1486f;
        if (actionBarOverlayLayout != null) {
            t.h0(actionBarOverlayLayout);
        }
    }

    public l(Dialog dialog) {
        D(dialog.getWindow().getDecorView());
    }
}
