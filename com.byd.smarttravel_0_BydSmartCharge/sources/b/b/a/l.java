package b.b.a;

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
import androidx.appcompat.R$attr;
import androidx.appcompat.R$id;
import androidx.appcompat.R$styleable;
import androidx.appcompat.widget.ActionBarContainer;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.ScrollingTabContainerView;
import androidx.appcompat.widget.Toolbar;
import b.b.a.a;
import b.b.e.b;
import b.b.e.j.g;
import b.b.f.p;
import b.g.j.a0;
import b.g.j.t;
import b.g.j.x;
import b.g.j.y;
import b.g.j.z;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: compiled from: WindowDecorActionBar.java */
/* JADX INFO: loaded from: classes.dex */
public class l extends b.b.a.a implements ActionBarOverlayLayout.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Interpolator f614a = new AccelerateInterpolator();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Interpolator f615b = new DecelerateInterpolator();
    public b.b.e.h A;
    public boolean B;
    public boolean C;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f616c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Context f617d;
    public Activity e;
    public ActionBarOverlayLayout f;
    public ActionBarContainer g;
    public p h;
    public ActionBarContextView i;
    public View j;
    public ScrollingTabContainerView k;
    public boolean n;
    public d o;
    public b.b.e.b p;
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

        @Override // b.g.j.y
        public void a(View view) {
            View view2;
            l lVar = l.this;
            if (lVar.v && (view2 = lVar.j) != null) {
                view2.setTranslationY(0.0f);
                l.this.g.setTranslationY(0.0f);
            }
            l.this.g.setVisibility(8);
            l.this.g.setTransitioning(false);
            l lVar2 = l.this;
            lVar2.A = null;
            lVar2.x();
            ActionBarOverlayLayout actionBarOverlayLayout = l.this.f;
            if (actionBarOverlayLayout != null) {
                t.g0(actionBarOverlayLayout);
            }
        }
    }

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    public class b extends z {
        public b() {
        }

        @Override // b.g.j.y
        public void a(View view) {
            l lVar = l.this;
            lVar.A = null;
            lVar.g.requestLayout();
        }
    }

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    public class c implements a0 {
        public c() {
        }

        @Override // b.g.j.a0
        public void a(View view) {
            ((View) l.this.g.getParent()).invalidate();
        }
    }

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    public class d extends b.b.e.b implements g.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Context f621d;
        public final b.b.e.j.g e;
        public b.a f;
        public WeakReference<View> g;

        public d(Context context, b.a aVar) {
            this.f621d = context;
            this.f = aVar;
            b.b.e.j.g gVarW = new b.b.e.j.g(context).W(1);
            this.e = gVarW;
            gVarW.V(this);
        }

        @Override // b.b.e.j.g.a
        public boolean a(b.b.e.j.g gVar, MenuItem menuItem) {
            b.a aVar = this.f;
            if (aVar != null) {
                return aVar.b(this, menuItem);
            }
            return false;
        }

        @Override // b.b.e.j.g.a
        public void b(b.b.e.j.g gVar) {
            if (this.f == null) {
                return;
            }
            k();
            l.this.i.l();
        }

        @Override // b.b.e.b
        public void c() {
            l lVar = l.this;
            if (lVar.o != this) {
                return;
            }
            if (l.w(lVar.w, lVar.x, false)) {
                this.f.d(this);
            } else {
                l lVar2 = l.this;
                lVar2.p = this;
                lVar2.q = this.f;
            }
            this.f = null;
            l.this.v(false);
            l.this.i.g();
            l.this.h.o().sendAccessibilityEvent(32);
            l lVar3 = l.this;
            lVar3.f.setHideOnContentScrollEnabled(lVar3.C);
            l.this.o = null;
        }

        @Override // b.b.e.b
        public View d() {
            WeakReference<View> weakReference = this.g;
            if (weakReference != null) {
                return weakReference.get();
            }
            return null;
        }

        @Override // b.b.e.b
        public Menu e() {
            return this.e;
        }

        @Override // b.b.e.b
        public MenuInflater f() {
            return new b.b.e.g(this.f621d);
        }

        @Override // b.b.e.b
        public CharSequence g() {
            return l.this.i.getSubtitle();
        }

        @Override // b.b.e.b
        public CharSequence i() {
            return l.this.i.getTitle();
        }

        @Override // b.b.e.b
        public void k() {
            if (l.this.o != this) {
                return;
            }
            this.e.h0();
            try {
                this.f.a(this, this.e);
            } finally {
                this.e.g0();
            }
        }

        @Override // b.b.e.b
        public boolean l() {
            return l.this.i.j();
        }

        @Override // b.b.e.b
        public void m(View view) {
            l.this.i.setCustomView(view);
            this.g = new WeakReference<>(view);
        }

        @Override // b.b.e.b
        public void n(int i) {
            o(l.this.f616c.getResources().getString(i));
        }

        @Override // b.b.e.b
        public void o(CharSequence charSequence) {
            l.this.i.setSubtitle(charSequence);
        }

        @Override // b.b.e.b
        public void q(int i) {
            r(l.this.f616c.getResources().getString(i));
        }

        @Override // b.b.e.b
        public void r(CharSequence charSequence) {
            l.this.i.setTitle(charSequence);
        }

        @Override // b.b.e.b
        public void s(boolean z) {
            super.s(z);
            l.this.i.setTitleOptional(z);
        }

        public boolean t() {
            this.e.h0();
            try {
                return this.f.c(this, this.e);
            } finally {
                this.e.g0();
            }
        }
    }

    public l(Activity activity, boolean z) {
        this.e = activity;
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
        return this.h.r();
    }

    public final void C() {
        if (this.y) {
            this.y = false;
            ActionBarOverlayLayout actionBarOverlayLayout = this.f;
            if (actionBarOverlayLayout != null) {
                actionBarOverlayLayout.setShowingForActionMode(false);
            }
            M(false);
        }
    }

    public final void D(View view) {
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) view.findViewById(R$id.decor_content_parent);
        this.f = actionBarOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        this.h = A(view.findViewById(R$id.action_bar));
        this.i = (ActionBarContextView) view.findViewById(R$id.action_context_bar);
        ActionBarContainer actionBarContainer = (ActionBarContainer) view.findViewById(R$id.action_bar_container);
        this.g = actionBarContainer;
        p pVar = this.h;
        if (pVar == null || this.i == null || actionBarContainer == null) {
            throw new IllegalStateException(l.class.getSimpleName() + " can only be used with a compatible window decor layout");
        }
        this.f616c = pVar.q();
        boolean z = (this.h.j() & 4) != 0;
        if (z) {
            this.n = true;
        }
        b.b.e.a aVarB = b.b.e.a.b(this.f616c);
        J(aVarB.a() || z);
        H(aVarB.g());
        TypedArray typedArrayObtainStyledAttributes = this.f616c.obtainStyledAttributes(null, R$styleable.ActionBar, R$attr.actionBarStyle, 0);
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.ActionBar_hideOnContentScroll, false)) {
            I(true);
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ActionBar_elevation, 0);
        if (dimensionPixelSize != 0) {
            G(dimensionPixelSize);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public void E(boolean z) {
        F(z ? 4 : 0, 4);
    }

    public void F(int i, int i2) {
        int iJ = this.h.j();
        if ((i2 & 4) != 0) {
            this.n = true;
        }
        this.h.x((i & i2) | ((~i2) & iJ));
    }

    public void G(float f) {
        t.q0(this.g, f);
    }

    public final void H(boolean z) {
        this.t = z;
        if (z) {
            this.g.setTabContainer(null);
            this.h.n(this.k);
        } else {
            this.h.n(null);
            this.g.setTabContainer(this.k);
        }
        boolean z2 = B() == 2;
        ScrollingTabContainerView scrollingTabContainerView = this.k;
        if (scrollingTabContainerView != null) {
            if (z2) {
                scrollingTabContainerView.setVisibility(0);
                ActionBarOverlayLayout actionBarOverlayLayout = this.f;
                if (actionBarOverlayLayout != null) {
                    t.g0(actionBarOverlayLayout);
                }
            } else {
                scrollingTabContainerView.setVisibility(8);
            }
        }
        this.h.w(!this.t && z2);
        this.f.setHasNonEmbeddedTabs(!this.t && z2);
    }

    public void I(boolean z) {
        if (z && !this.f.w()) {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
        }
        this.C = z;
        this.f.setHideOnContentScrollEnabled(z);
    }

    public void J(boolean z) {
        this.h.p(z);
    }

    public final boolean K() {
        return t.P(this.g);
    }

    public final void L() {
        if (this.y) {
            return;
        }
        this.y = true;
        ActionBarOverlayLayout actionBarOverlayLayout = this.f;
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
        b.b.e.h hVar = this.A;
        if (hVar != null) {
            hVar.a();
            this.A = null;
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void d(int i) {
        this.u = i;
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

    @Override // b.b.a.a
    public boolean h() {
        p pVar = this.h;
        if (pVar == null || !pVar.u()) {
            return false;
        }
        this.h.collapseActionView();
        return true;
    }

    @Override // b.b.a.a
    public void i(boolean z) {
        if (z == this.r) {
            return;
        }
        this.r = z;
        int size = this.s.size();
        for (int i = 0; i < size; i++) {
            this.s.get(i).a(z);
        }
    }

    @Override // b.b.a.a
    public int j() {
        return this.h.j();
    }

    @Override // b.b.a.a
    public Context k() {
        if (this.f617d == null) {
            TypedValue typedValue = new TypedValue();
            this.f616c.getTheme().resolveAttribute(R$attr.actionBarWidgetTheme, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                this.f617d = new ContextThemeWrapper(this.f616c, i);
            } else {
                this.f617d = this.f616c;
            }
        }
        return this.f617d;
    }

    @Override // b.b.a.a
    public void m(Configuration configuration) {
        H(b.b.e.a.b(this.f616c).g());
    }

    @Override // b.b.a.a
    public boolean o(int i, KeyEvent keyEvent) {
        Menu menuE;
        d dVar = this.o;
        if (dVar == null || (menuE = dVar.e()) == null) {
            return false;
        }
        menuE.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
        return menuE.performShortcut(i, keyEvent, 0);
    }

    @Override // b.b.a.a
    public void r(boolean z) {
        if (this.n) {
            return;
        }
        E(z);
    }

    @Override // b.b.a.a
    public void s(boolean z) {
        b.b.e.h hVar;
        this.B = z;
        if (z || (hVar = this.A) == null) {
            return;
        }
        hVar.a();
    }

    @Override // b.b.a.a
    public void t(CharSequence charSequence) {
        this.h.setWindowTitle(charSequence);
    }

    @Override // b.b.a.a
    public b.b.e.b u(b.a aVar) {
        d dVar = this.o;
        if (dVar != null) {
            dVar.c();
        }
        this.f.setHideOnContentScrollEnabled(false);
        this.i.k();
        d dVar2 = new d(this.i.getContext(), aVar);
        if (!dVar2.t()) {
            return null;
        }
        this.o = dVar2;
        dVar2.k();
        this.i.h(dVar2);
        v(true);
        this.i.sendAccessibilityEvent(32);
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
                this.h.k(4);
                this.i.setVisibility(0);
                return;
            } else {
                this.h.k(0);
                this.i.setVisibility(8);
                return;
            }
        }
        if (z) {
            xVarF = this.h.s(4, 100L);
            xVarS = this.i.f(0, 200L);
        } else {
            xVarS = this.h.s(0, 200L);
            xVarF = this.i.f(8, 100L);
        }
        b.b.e.h hVar = new b.b.e.h();
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
        b.b.e.h hVar = this.A;
        if (hVar != null) {
            hVar.a();
        }
        if (this.u != 0 || (!this.B && !z)) {
            this.D.a(null);
            return;
        }
        this.g.setAlpha(1.0f);
        this.g.setTransitioning(true);
        b.b.e.h hVar2 = new b.b.e.h();
        float f = -this.g.getHeight();
        if (z) {
            this.g.getLocationInWindow(new int[]{0, 0});
            f -= r5[1];
        }
        x xVarK = t.c(this.g).k(f);
        xVarK.i(this.F);
        hVar2.c(xVarK);
        if (this.v && (view = this.j) != null) {
            hVar2.c(t.c(view).k(f));
        }
        hVar2.f(f614a);
        hVar2.e(250L);
        hVar2.g(this.D);
        this.A = hVar2;
        hVar2.h();
    }

    public void z(boolean z) {
        View view;
        View view2;
        b.b.e.h hVar = this.A;
        if (hVar != null) {
            hVar.a();
        }
        this.g.setVisibility(0);
        if (this.u == 0 && (this.B || z)) {
            this.g.setTranslationY(0.0f);
            float f = -this.g.getHeight();
            if (z) {
                this.g.getLocationInWindow(new int[]{0, 0});
                f -= r5[1];
            }
            this.g.setTranslationY(f);
            b.b.e.h hVar2 = new b.b.e.h();
            x xVarK = t.c(this.g).k(0.0f);
            xVarK.i(this.F);
            hVar2.c(xVarK);
            if (this.v && (view2 = this.j) != null) {
                view2.setTranslationY(f);
                hVar2.c(t.c(this.j).k(0.0f));
            }
            hVar2.f(f615b);
            hVar2.e(250L);
            hVar2.g(this.E);
            this.A = hVar2;
            hVar2.h();
        } else {
            this.g.setAlpha(1.0f);
            this.g.setTranslationY(0.0f);
            if (this.v && (view = this.j) != null) {
                view.setTranslationY(0.0f);
            }
            this.E.a(null);
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.f;
        if (actionBarOverlayLayout != null) {
            t.g0(actionBarOverlayLayout);
        }
    }

    public l(Dialog dialog) {
        D(dialog.getWindow().getDecorView());
    }
}
