package defpackage;

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
import defpackage.b1;
import defpackage.i0;
import defpackage.p1;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: compiled from: WindowDecorActionBar.java */
/* JADX INFO: loaded from: classes.dex */
public class t0 extends i0 implements ActionBarOverlayLayout.d {
    public static final Interpolator a = new AccelerateInterpolator();
    public static final Interpolator b = new DecelerateInterpolator();
    public h1 A;
    public boolean B;
    public boolean C;
    public Context c;
    public Context d;
    public Activity e;
    public ActionBarOverlayLayout f;
    public ActionBarContainer g;
    public r2 h;
    public ActionBarContextView i;
    public View j;
    public ScrollingTabContainerView k;
    public boolean n;
    public d o;
    public b1 p;
    public b1.a q;
    public boolean r;
    public boolean t;
    public boolean w;
    public boolean x;
    public boolean y;
    public ArrayList<?> l = new ArrayList<>();
    public int m = -1;
    public ArrayList<i0.b> s = new ArrayList<>();
    public int u = 0;
    public boolean v = true;
    public boolean z = true;
    public final y9 D = new a();
    public final y9 E = new b();
    public final aa F = new c();

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    public class a extends z9 {
        public a() {
        }

        @Override // defpackage.y9
        public void a(View view) {
            View view2;
            t0 t0Var = t0.this;
            if (t0Var.v && (view2 = t0Var.j) != null) {
                view2.setTranslationY(0.0f);
                t0.this.g.setTranslationY(0.0f);
            }
            t0.this.g.setVisibility(8);
            t0.this.g.setTransitioning(false);
            t0 t0Var2 = t0.this;
            t0Var2.A = null;
            t0Var2.x();
            ActionBarOverlayLayout actionBarOverlayLayout = t0.this.f;
            if (actionBarOverlayLayout != null) {
                t9.g0(actionBarOverlayLayout);
            }
        }
    }

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    public class b extends z9 {
        public b() {
        }

        @Override // defpackage.y9
        public void a(View view) {
            t0 t0Var = t0.this;
            t0Var.A = null;
            t0Var.g.requestLayout();
        }
    }

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    public class c implements aa {
        public c() {
        }

        @Override // defpackage.aa
        public void a(View view) {
            ((View) t0.this.g.getParent()).invalidate();
        }
    }

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    public class d extends b1 implements p1.a {
        public final Context d;
        public final p1 e;
        public b1.a f;
        public WeakReference<View> g;

        public d(Context context, b1.a aVar) {
            this.d = context;
            this.f = aVar;
            p1 p1VarW = new p1(context).W(1);
            this.e = p1VarW;
            p1VarW.V(this);
        }

        @Override // p1.a
        public boolean a(p1 p1Var, MenuItem menuItem) {
            b1.a aVar = this.f;
            if (aVar != null) {
                return aVar.b(this, menuItem);
            }
            return false;
        }

        @Override // p1.a
        public void b(p1 p1Var) {
            if (this.f == null) {
                return;
            }
            k();
            t0.this.i.l();
        }

        @Override // defpackage.b1
        public void c() {
            t0 t0Var = t0.this;
            if (t0Var.o != this) {
                return;
            }
            if (t0.w(t0Var.w, t0Var.x, false)) {
                this.f.d(this);
            } else {
                t0 t0Var2 = t0.this;
                t0Var2.p = this;
                t0Var2.q = this.f;
            }
            this.f = null;
            t0.this.v(false);
            t0.this.i.g();
            t0.this.h.o().sendAccessibilityEvent(32);
            t0 t0Var3 = t0.this;
            t0Var3.f.setHideOnContentScrollEnabled(t0Var3.C);
            t0.this.o = null;
        }

        @Override // defpackage.b1
        public View d() {
            WeakReference<View> weakReference = this.g;
            if (weakReference != null) {
                return weakReference.get();
            }
            return null;
        }

        @Override // defpackage.b1
        public Menu e() {
            return this.e;
        }

        @Override // defpackage.b1
        public MenuInflater f() {
            return new g1(this.d);
        }

        @Override // defpackage.b1
        public CharSequence g() {
            return t0.this.i.getSubtitle();
        }

        @Override // defpackage.b1
        public CharSequence i() {
            return t0.this.i.getTitle();
        }

        @Override // defpackage.b1
        public void k() {
            if (t0.this.o != this) {
                return;
            }
            this.e.h0();
            try {
                this.f.a(this, this.e);
            } finally {
                this.e.g0();
            }
        }

        @Override // defpackage.b1
        public boolean l() {
            return t0.this.i.j();
        }

        @Override // defpackage.b1
        public void m(View view) {
            t0.this.i.setCustomView(view);
            this.g = new WeakReference<>(view);
        }

        @Override // defpackage.b1
        public void n(int i) {
            o(t0.this.c.getResources().getString(i));
        }

        @Override // defpackage.b1
        public void o(CharSequence charSequence) {
            t0.this.i.setSubtitle(charSequence);
        }

        @Override // defpackage.b1
        public void q(int i) {
            r(t0.this.c.getResources().getString(i));
        }

        @Override // defpackage.b1
        public void r(CharSequence charSequence) {
            t0.this.i.setTitle(charSequence);
        }

        @Override // defpackage.b1
        public void s(boolean z) {
            super.s(z);
            t0.this.i.setTitleOptional(z);
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

    public t0(Activity activity, boolean z) {
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
    public final r2 A(View view) {
        if (view instanceof r2) {
            return (r2) view;
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
        r2 r2Var = this.h;
        if (r2Var == null || this.i == null || actionBarContainer == null) {
            throw new IllegalStateException(t0.class.getSimpleName() + " can only be used with a compatible window decor layout");
        }
        this.c = r2Var.q();
        boolean z = (this.h.j() & 4) != 0;
        if (z) {
            this.n = true;
        }
        a1 a1VarB = a1.b(this.c);
        J(a1VarB.a() || z);
        H(a1VarB.g());
        TypedArray typedArrayObtainStyledAttributes = this.c.obtainStyledAttributes(null, R$styleable.ActionBar, R$attr.actionBarStyle, 0);
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
        t9.q0(this.g, f);
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
                    t9.g0(actionBarOverlayLayout);
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
        return t9.P(this.g);
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
        h1 h1Var = this.A;
        if (h1Var != null) {
            h1Var.a();
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

    @Override // defpackage.i0
    public boolean h() {
        r2 r2Var = this.h;
        if (r2Var == null || !r2Var.u()) {
            return false;
        }
        this.h.collapseActionView();
        return true;
    }

    @Override // defpackage.i0
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

    @Override // defpackage.i0
    public int j() {
        return this.h.j();
    }

    @Override // defpackage.i0
    public Context k() {
        if (this.d == null) {
            TypedValue typedValue = new TypedValue();
            this.c.getTheme().resolveAttribute(R$attr.actionBarWidgetTheme, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                this.d = new ContextThemeWrapper(this.c, i);
            } else {
                this.d = this.c;
            }
        }
        return this.d;
    }

    @Override // defpackage.i0
    public void m(Configuration configuration) {
        H(a1.b(this.c).g());
    }

    @Override // defpackage.i0
    public boolean o(int i, KeyEvent keyEvent) {
        Menu menuE;
        d dVar = this.o;
        if (dVar == null || (menuE = dVar.e()) == null) {
            return false;
        }
        menuE.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
        return menuE.performShortcut(i, keyEvent, 0);
    }

    @Override // defpackage.i0
    public void r(boolean z) {
        if (this.n) {
            return;
        }
        E(z);
    }

    @Override // defpackage.i0
    public void s(boolean z) {
        h1 h1Var;
        this.B = z;
        if (z || (h1Var = this.A) == null) {
            return;
        }
        h1Var.a();
    }

    @Override // defpackage.i0
    public void t(CharSequence charSequence) {
        this.h.setWindowTitle(charSequence);
    }

    @Override // defpackage.i0
    public b1 u(b1.a aVar) {
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
        x9 x9VarS;
        x9 x9VarF;
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
            x9VarF = this.h.s(4, 100L);
            x9VarS = this.i.f(0, 200L);
        } else {
            x9VarS = this.h.s(0, 200L);
            x9VarF = this.i.f(8, 100L);
        }
        h1 h1Var = new h1();
        h1Var.d(x9VarF, x9VarS);
        h1Var.h();
    }

    public void x() {
        b1.a aVar = this.q;
        if (aVar != null) {
            aVar.d(this.p);
            this.p = null;
            this.q = null;
        }
    }

    public void y(boolean z) {
        View view;
        h1 h1Var = this.A;
        if (h1Var != null) {
            h1Var.a();
        }
        if (this.u != 0 || (!this.B && !z)) {
            this.D.a(null);
            return;
        }
        this.g.setAlpha(1.0f);
        this.g.setTransitioning(true);
        h1 h1Var2 = new h1();
        float f = -this.g.getHeight();
        if (z) {
            this.g.getLocationInWindow(new int[]{0, 0});
            f -= r5[1];
        }
        x9 x9VarK = t9.c(this.g).k(f);
        x9VarK.i(this.F);
        h1Var2.c(x9VarK);
        if (this.v && (view = this.j) != null) {
            h1Var2.c(t9.c(view).k(f));
        }
        h1Var2.f(a);
        h1Var2.e(250L);
        h1Var2.g(this.D);
        this.A = h1Var2;
        h1Var2.h();
    }

    public void z(boolean z) {
        View view;
        View view2;
        h1 h1Var = this.A;
        if (h1Var != null) {
            h1Var.a();
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
            h1 h1Var2 = new h1();
            x9 x9VarK = t9.c(this.g).k(0.0f);
            x9VarK.i(this.F);
            h1Var2.c(x9VarK);
            if (this.v && (view2 = this.j) != null) {
                view2.setTranslationY(f);
                h1Var2.c(t9.c(this.j).k(0.0f));
            }
            h1Var2.f(b);
            h1Var2.e(250L);
            h1Var2.g(this.E);
            this.A = h1Var2;
            h1Var2.h();
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
            t9.g0(actionBarOverlayLayout);
        }
    }

    public t0(Dialog dialog) {
        D(dialog.getWindow().getDecorView());
    }
}
