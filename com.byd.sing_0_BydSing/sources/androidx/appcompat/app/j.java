package androidx.appcompat.app;

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
import androidx.appcompat.app.a;
import androidx.appcompat.view.menu.g;
import androidx.appcompat.widget.ActionBarContainer;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.ScrollingTabContainerView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.p;
import c.a.o.b;
import c.g.k.a0;
import c.g.k.t;
import c.g.k.x;
import c.g.k.y;
import c.g.k.z;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: compiled from: WindowDecorActionBar.java */
/* JADX INFO: loaded from: classes.dex */
public class j extends androidx.appcompat.app.a implements ActionBarOverlayLayout.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Interpolator f703a = new AccelerateInterpolator();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Interpolator f704b = new DecelerateInterpolator();
    c.a.o.h A;
    private boolean B;
    boolean C;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Context f705c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Context f706d;
    private Activity e;
    ActionBarOverlayLayout f;
    ActionBarContainer g;
    p h;
    ActionBarContextView i;
    View j;
    ScrollingTabContainerView k;
    private boolean n;
    d o;
    c.a.o.b p;
    b.a q;
    private boolean r;
    private boolean t;
    boolean w;
    boolean x;
    private boolean y;
    private ArrayList<?> l = new ArrayList<>();
    private int m = -1;
    private ArrayList<a.b> s = new ArrayList<>();
    private int u = 0;
    boolean v = true;
    private boolean z = true;
    final y D = new a();
    final y E = new b();
    final a0 F = new c();

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    class a extends z {
        a() {
        }

        @Override // c.g.k.y
        public void b(View view) {
            View view2;
            j jVar = j.this;
            if (jVar.v && (view2 = jVar.j) != null) {
                view2.setTranslationY(0.0f);
                j.this.g.setTranslationY(0.0f);
            }
            j.this.g.setVisibility(8);
            j.this.g.setTransitioning(false);
            j jVar2 = j.this;
            jVar2.A = null;
            jVar2.x();
            ActionBarOverlayLayout actionBarOverlayLayout = j.this.f;
            if (actionBarOverlayLayout != null) {
                t.g0(actionBarOverlayLayout);
            }
        }
    }

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    class b extends z {
        b() {
        }

        @Override // c.g.k.y
        public void b(View view) {
            j jVar = j.this;
            jVar.A = null;
            jVar.g.requestLayout();
        }
    }

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    class c implements a0 {
        c() {
        }

        @Override // c.g.k.a0
        public void a(View view) {
            ((View) j.this.g.getParent()).invalidate();
        }
    }

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    public class d extends c.a.o.b implements g.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final Context f710d;
        private final androidx.appcompat.view.menu.g e;
        private b.a f;
        private WeakReference<View> g;

        public d(Context context, b.a aVar) {
            this.f710d = context;
            this.f = aVar;
            androidx.appcompat.view.menu.g gVarW = new androidx.appcompat.view.menu.g(context).W(1);
            this.e = gVarW;
            gVarW.V(this);
        }

        @Override // androidx.appcompat.view.menu.g.a
        public boolean a(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
            b.a aVar = this.f;
            if (aVar != null) {
                return aVar.c(this, menuItem);
            }
            return false;
        }

        @Override // androidx.appcompat.view.menu.g.a
        public void b(androidx.appcompat.view.menu.g gVar) {
            if (this.f == null) {
                return;
            }
            k();
            j.this.i.l();
        }

        @Override // c.a.o.b
        public void c() {
            j jVar = j.this;
            if (jVar.o != this) {
                return;
            }
            if (j.w(jVar.w, jVar.x, false)) {
                this.f.b(this);
            } else {
                j jVar2 = j.this;
                jVar2.p = this;
                jVar2.q = this.f;
            }
            this.f = null;
            j.this.v(false);
            j.this.i.g();
            j.this.h.k().sendAccessibilityEvent(32);
            j jVar3 = j.this;
            jVar3.f.setHideOnContentScrollEnabled(jVar3.C);
            j.this.o = null;
        }

        @Override // c.a.o.b
        public View d() {
            WeakReference<View> weakReference = this.g;
            if (weakReference != null) {
                return weakReference.get();
            }
            return null;
        }

        @Override // c.a.o.b
        public Menu e() {
            return this.e;
        }

        @Override // c.a.o.b
        public MenuInflater f() {
            return new c.a.o.g(this.f710d);
        }

        @Override // c.a.o.b
        public CharSequence g() {
            return j.this.i.getSubtitle();
        }

        @Override // c.a.o.b
        public CharSequence i() {
            return j.this.i.getTitle();
        }

        @Override // c.a.o.b
        public void k() {
            if (j.this.o != this) {
                return;
            }
            this.e.h0();
            try {
                this.f.a(this, this.e);
            } finally {
                this.e.g0();
            }
        }

        @Override // c.a.o.b
        public boolean l() {
            return j.this.i.j();
        }

        @Override // c.a.o.b
        public void m(View view) {
            j.this.i.setCustomView(view);
            this.g = new WeakReference<>(view);
        }

        @Override // c.a.o.b
        public void n(int i) {
            o(j.this.f705c.getResources().getString(i));
        }

        @Override // c.a.o.b
        public void o(CharSequence charSequence) {
            j.this.i.setSubtitle(charSequence);
        }

        @Override // c.a.o.b
        public void q(int i) {
            r(j.this.f705c.getResources().getString(i));
        }

        @Override // c.a.o.b
        public void r(CharSequence charSequence) {
            j.this.i.setTitle(charSequence);
        }

        @Override // c.a.o.b
        public void s(boolean z) {
            super.s(z);
            j.this.i.setTitleOptional(z);
        }

        public boolean t() {
            this.e.h0();
            try {
                return this.f.d(this, this.e);
            } finally {
                this.e.g0();
            }
        }
    }

    public j(Activity activity, boolean z) {
        this.e = activity;
        View decorView = activity.getWindow().getDecorView();
        D(decorView);
        if (z) {
            return;
        }
        this.j = decorView.findViewById(R.id.content);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private p A(View view) {
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

    private void C() {
        if (this.y) {
            this.y = false;
            ActionBarOverlayLayout actionBarOverlayLayout = this.f;
            if (actionBarOverlayLayout != null) {
                actionBarOverlayLayout.setShowingForActionMode(false);
            }
            M(false);
        }
    }

    private void D(View view) {
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) view.findViewById(c.a.f.q);
        this.f = actionBarOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        this.h = A(view.findViewById(c.a.f.f1982a));
        this.i = (ActionBarContextView) view.findViewById(c.a.f.f);
        ActionBarContainer actionBarContainer = (ActionBarContainer) view.findViewById(c.a.f.f1984c);
        this.g = actionBarContainer;
        p pVar = this.h;
        if (pVar == null || this.i == null || actionBarContainer == null) {
            throw new IllegalStateException(j.class.getSimpleName() + " can only be used with a compatible window decor layout");
        }
        this.f705c = pVar.m();
        boolean z = (this.h.p() & 4) != 0;
        if (z) {
            this.n = true;
        }
        c.a.o.a aVarB = c.a.o.a.b(this.f705c);
        J(aVarB.a() || z);
        H(aVarB.g());
        TypedArray typedArrayObtainStyledAttributes = this.f705c.obtainStyledAttributes(null, c.a.j.f1998a, c.a.a.f1967c, 0);
        if (typedArrayObtainStyledAttributes.getBoolean(c.a.j.k, false)) {
            I(true);
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(c.a.j.i, 0);
        if (dimensionPixelSize != 0) {
            G(dimensionPixelSize);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    private void H(boolean z) {
        this.t = z;
        if (z) {
            this.g.setTabContainer(null);
            this.h.j(this.k);
        } else {
            this.h.j(null);
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
        this.h.v(!this.t && z2);
        this.f.setHasNonEmbeddedTabs(!this.t && z2);
    }

    private boolean K() {
        return t.P(this.g);
    }

    private void L() {
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

    private void M(boolean z) {
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

    static boolean w(boolean z, boolean z2, boolean z3) {
        if (z3) {
            return true;
        }
        return (z || z2) ? false : true;
    }

    public int B() {
        return this.h.r();
    }

    public void E(boolean z) {
        F(z ? 4 : 0, 4);
    }

    public void F(int i, int i2) {
        int iP = this.h.p();
        if ((i2 & 4) != 0) {
            this.n = true;
        }
        this.h.o((i & i2) | ((~i2) & iP));
    }

    public void G(float f) {
        t.q0(this.g, f);
    }

    public void I(boolean z) {
        if (z && !this.f.w()) {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
        }
        this.C = z;
        this.f.setHideOnContentScrollEnabled(z);
    }

    public void J(boolean z) {
        this.h.l(z);
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void a() {
        if (this.x) {
            this.x = false;
            M(true);
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void b() {
        c.a.o.h hVar = this.A;
        if (hVar != null) {
            hVar.a();
            this.A = null;
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void c(int i) {
        this.u = i;
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void d() {
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void e(boolean z) {
        this.v = z;
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void f() {
        if (this.x) {
            return;
        }
        this.x = true;
        M(true);
    }

    @Override // androidx.appcompat.app.a
    public boolean h() {
        p pVar = this.h;
        if (pVar == null || !pVar.n()) {
            return false;
        }
        this.h.collapseActionView();
        return true;
    }

    @Override // androidx.appcompat.app.a
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

    @Override // androidx.appcompat.app.a
    public int j() {
        return this.h.p();
    }

    @Override // androidx.appcompat.app.a
    public Context k() {
        if (this.f706d == null) {
            TypedValue typedValue = new TypedValue();
            this.f705c.getTheme().resolveAttribute(c.a.a.h, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                this.f706d = new ContextThemeWrapper(this.f705c, i);
            } else {
                this.f706d = this.f705c;
            }
        }
        return this.f706d;
    }

    @Override // androidx.appcompat.app.a
    public void m(Configuration configuration) {
        H(c.a.o.a.b(this.f705c).g());
    }

    @Override // androidx.appcompat.app.a
    public boolean o(int i, KeyEvent keyEvent) {
        Menu menuE;
        d dVar = this.o;
        if (dVar == null || (menuE = dVar.e()) == null) {
            return false;
        }
        menuE.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
        return menuE.performShortcut(i, keyEvent, 0);
    }

    @Override // androidx.appcompat.app.a
    public void r(boolean z) {
        if (this.n) {
            return;
        }
        E(z);
    }

    @Override // androidx.appcompat.app.a
    public void s(boolean z) {
        c.a.o.h hVar;
        this.B = z;
        if (z || (hVar = this.A) == null) {
            return;
        }
        hVar.a();
    }

    @Override // androidx.appcompat.app.a
    public void t(CharSequence charSequence) {
        this.h.setWindowTitle(charSequence);
    }

    @Override // androidx.appcompat.app.a
    public c.a.o.b u(b.a aVar) {
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
                this.h.i(4);
                this.i.setVisibility(0);
                return;
            } else {
                this.h.i(0);
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
        c.a.o.h hVar = new c.a.o.h();
        hVar.d(xVarF, xVarS);
        hVar.h();
    }

    void x() {
        b.a aVar = this.q;
        if (aVar != null) {
            aVar.b(this.p);
            this.p = null;
            this.q = null;
        }
    }

    public void y(boolean z) {
        View view;
        c.a.o.h hVar = this.A;
        if (hVar != null) {
            hVar.a();
        }
        if (this.u != 0 || (!this.B && !z)) {
            this.D.b(null);
            return;
        }
        this.g.setAlpha(1.0f);
        this.g.setTransitioning(true);
        c.a.o.h hVar2 = new c.a.o.h();
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
        hVar2.f(f703a);
        hVar2.e(250L);
        hVar2.g(this.D);
        this.A = hVar2;
        hVar2.h();
    }

    public void z(boolean z) {
        View view;
        View view2;
        c.a.o.h hVar = this.A;
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
            c.a.o.h hVar2 = new c.a.o.h();
            x xVarK = t.c(this.g).k(0.0f);
            xVarK.i(this.F);
            hVar2.c(xVarK);
            if (this.v && (view2 = this.j) != null) {
                view2.setTranslationY(f);
                hVar2.c(t.c(this.j).k(0.0f));
            }
            hVar2.f(f704b);
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
            this.E.b(null);
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.f;
        if (actionBarOverlayLayout != null) {
            t.g0(actionBarOverlayLayout);
        }
    }

    public j(Dialog dialog) {
        D(dialog.getWindow().getDecorView());
    }
}
