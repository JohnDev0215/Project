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
import b.a.o.b;
import b.g.k.a0;
import b.g.k.t;
import b.g.k.x;
import b.g.k.y;
import b.g.k.z;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: compiled from: WindowDecorActionBar.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends androidx.appcompat.app.a implements ActionBarOverlayLayout.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Interpolator f221a = new AccelerateInterpolator();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Interpolator f222b = new DecelerateInterpolator();
    b.a.o.h A;
    private boolean B;
    boolean C;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Context f223c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Context f224d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Activity f225e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    ActionBarOverlayLayout f226f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    ActionBarContainer f227g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    p f228h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    ActionBarContextView f229i;
    View j;
    ScrollingTabContainerView k;
    private boolean n;
    d o;
    b.a.o.b p;
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

        @Override // b.g.k.y
        public void a(View view) {
            View view2;
            k kVar = k.this;
            if (kVar.v && (view2 = kVar.j) != null) {
                view2.setTranslationY(0.0f);
                k.this.f227g.setTranslationY(0.0f);
            }
            k.this.f227g.setVisibility(8);
            k.this.f227g.setTransitioning(false);
            k kVar2 = k.this;
            kVar2.A = null;
            kVar2.x();
            ActionBarOverlayLayout actionBarOverlayLayout = k.this.f226f;
            if (actionBarOverlayLayout != null) {
                t.g0(actionBarOverlayLayout);
            }
        }
    }

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    class b extends z {
        b() {
        }

        @Override // b.g.k.y
        public void a(View view) {
            k kVar = k.this;
            kVar.A = null;
            kVar.f227g.requestLayout();
        }
    }

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    class c implements a0 {
        c() {
        }

        @Override // b.g.k.a0
        public void a(View view) {
            ((View) k.this.f227g.getParent()).invalidate();
        }
    }

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    public class d extends b.a.o.b implements g.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final Context f233d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final androidx.appcompat.view.menu.g f234e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private b.a f235f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private WeakReference<View> f236g;

        public d(Context context, b.a aVar) {
            this.f233d = context;
            this.f235f = aVar;
            androidx.appcompat.view.menu.g gVarW = new androidx.appcompat.view.menu.g(context).W(1);
            this.f234e = gVarW;
            gVarW.V(this);
        }

        @Override // androidx.appcompat.view.menu.g.a
        public boolean a(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
            b.a aVar = this.f235f;
            if (aVar != null) {
                return aVar.b(this, menuItem);
            }
            return false;
        }

        @Override // androidx.appcompat.view.menu.g.a
        public void b(androidx.appcompat.view.menu.g gVar) {
            if (this.f235f == null) {
                return;
            }
            k();
            k.this.f229i.l();
        }

        @Override // b.a.o.b
        public void c() {
            k kVar = k.this;
            if (kVar.o != this) {
                return;
            }
            if (k.w(kVar.w, kVar.x, false)) {
                this.f235f.d(this);
            } else {
                k kVar2 = k.this;
                kVar2.p = this;
                kVar2.q = this.f235f;
            }
            this.f235f = null;
            k.this.v(false);
            k.this.f229i.g();
            k.this.f228h.p().sendAccessibilityEvent(32);
            k kVar3 = k.this;
            kVar3.f226f.setHideOnContentScrollEnabled(kVar3.C);
            k.this.o = null;
        }

        @Override // b.a.o.b
        public View d() {
            WeakReference<View> weakReference = this.f236g;
            if (weakReference != null) {
                return weakReference.get();
            }
            return null;
        }

        @Override // b.a.o.b
        public Menu e() {
            return this.f234e;
        }

        @Override // b.a.o.b
        public MenuInflater f() {
            return new b.a.o.g(this.f233d);
        }

        @Override // b.a.o.b
        public CharSequence g() {
            return k.this.f229i.getSubtitle();
        }

        @Override // b.a.o.b
        public CharSequence i() {
            return k.this.f229i.getTitle();
        }

        @Override // b.a.o.b
        public void k() {
            if (k.this.o != this) {
                return;
            }
            this.f234e.h0();
            try {
                this.f235f.a(this, this.f234e);
            } finally {
                this.f234e.g0();
            }
        }

        @Override // b.a.o.b
        public boolean l() {
            return k.this.f229i.j();
        }

        @Override // b.a.o.b
        public void m(View view) {
            k.this.f229i.setCustomView(view);
            this.f236g = new WeakReference<>(view);
        }

        @Override // b.a.o.b
        public void n(int i2) {
            o(k.this.f223c.getResources().getString(i2));
        }

        @Override // b.a.o.b
        public void o(CharSequence charSequence) {
            k.this.f229i.setSubtitle(charSequence);
        }

        @Override // b.a.o.b
        public void q(int i2) {
            r(k.this.f223c.getResources().getString(i2));
        }

        @Override // b.a.o.b
        public void r(CharSequence charSequence) {
            k.this.f229i.setTitle(charSequence);
        }

        @Override // b.a.o.b
        public void s(boolean z) {
            super.s(z);
            k.this.f229i.setTitleOptional(z);
        }

        public boolean t() {
            this.f234e.h0();
            try {
                return this.f235f.c(this, this.f234e);
            } finally {
                this.f234e.g0();
            }
        }
    }

    public k(Activity activity, boolean z) {
        this.f225e = activity;
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
            ActionBarOverlayLayout actionBarOverlayLayout = this.f226f;
            if (actionBarOverlayLayout != null) {
                actionBarOverlayLayout.setShowingForActionMode(false);
            }
            M(false);
        }
    }

    private void D(View view) {
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) view.findViewById(b.a.f.decor_content_parent);
        this.f226f = actionBarOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        this.f228h = A(view.findViewById(b.a.f.action_bar));
        this.f229i = (ActionBarContextView) view.findViewById(b.a.f.action_context_bar);
        ActionBarContainer actionBarContainer = (ActionBarContainer) view.findViewById(b.a.f.action_bar_container);
        this.f227g = actionBarContainer;
        p pVar = this.f228h;
        if (pVar == null || this.f229i == null || actionBarContainer == null) {
            throw new IllegalStateException(k.class.getSimpleName() + " can only be used with a compatible window decor layout");
        }
        this.f223c = pVar.a();
        boolean z = (this.f228h.k() & 4) != 0;
        if (z) {
            this.n = true;
        }
        b.a.o.a aVarB = b.a.o.a.b(this.f223c);
        J(aVarB.a() || z);
        H(aVarB.g());
        TypedArray typedArrayObtainStyledAttributes = this.f223c.obtainStyledAttributes(null, b.a.j.ActionBar, b.a.a.actionBarStyle, 0);
        if (typedArrayObtainStyledAttributes.getBoolean(b.a.j.ActionBar_hideOnContentScroll, false)) {
            I(true);
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.a.j.ActionBar_elevation, 0);
        if (dimensionPixelSize != 0) {
            G(dimensionPixelSize);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    private void H(boolean z) {
        this.t = z;
        if (z) {
            this.f227g.setTabContainer(null);
            this.f228h.o(this.k);
        } else {
            this.f228h.o(null);
            this.f227g.setTabContainer(this.k);
        }
        boolean z2 = B() == 2;
        ScrollingTabContainerView scrollingTabContainerView = this.k;
        if (scrollingTabContainerView != null) {
            if (z2) {
                scrollingTabContainerView.setVisibility(0);
                ActionBarOverlayLayout actionBarOverlayLayout = this.f226f;
                if (actionBarOverlayLayout != null) {
                    t.g0(actionBarOverlayLayout);
                }
            } else {
                scrollingTabContainerView.setVisibility(8);
            }
        }
        this.f228h.w(!this.t && z2);
        this.f226f.setHasNonEmbeddedTabs(!this.t && z2);
    }

    private boolean K() {
        return t.P(this.f227g);
    }

    private void L() {
        if (this.y) {
            return;
        }
        this.y = true;
        ActionBarOverlayLayout actionBarOverlayLayout = this.f226f;
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
        return this.f228h.r();
    }

    public void E(boolean z) {
        F(z ? 4 : 0, 4);
    }

    public void F(int i2, int i3) {
        int iK = this.f228h.k();
        if ((i3 & 4) != 0) {
            this.n = true;
        }
        this.f228h.x((i2 & i3) | ((~i3) & iK));
    }

    public void G(float f2) {
        t.q0(this.f227g, f2);
    }

    public void I(boolean z) {
        if (z && !this.f226f.w()) {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
        }
        this.C = z;
        this.f226f.setHideOnContentScrollEnabled(z);
    }

    public void J(boolean z) {
        this.f228h.q(z);
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
        b.a.o.h hVar = this.A;
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

    @Override // androidx.appcompat.app.a
    public boolean h() {
        p pVar = this.f228h;
        if (pVar == null || !pVar.u()) {
            return false;
        }
        this.f228h.collapseActionView();
        return true;
    }

    @Override // androidx.appcompat.app.a
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

    @Override // androidx.appcompat.app.a
    public int j() {
        return this.f228h.k();
    }

    @Override // androidx.appcompat.app.a
    public Context k() {
        if (this.f224d == null) {
            TypedValue typedValue = new TypedValue();
            this.f223c.getTheme().resolveAttribute(b.a.a.actionBarWidgetTheme, typedValue, true);
            int i2 = typedValue.resourceId;
            if (i2 != 0) {
                this.f224d = new ContextThemeWrapper(this.f223c, i2);
            } else {
                this.f224d = this.f223c;
            }
        }
        return this.f224d;
    }

    @Override // androidx.appcompat.app.a
    public void m(Configuration configuration) {
        H(b.a.o.a.b(this.f223c).g());
    }

    @Override // androidx.appcompat.app.a
    public boolean o(int i2, KeyEvent keyEvent) {
        Menu menuE;
        d dVar = this.o;
        if (dVar == null || (menuE = dVar.e()) == null) {
            return false;
        }
        menuE.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
        return menuE.performShortcut(i2, keyEvent, 0);
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
        b.a.o.h hVar;
        this.B = z;
        if (z || (hVar = this.A) == null) {
            return;
        }
        hVar.a();
    }

    @Override // androidx.appcompat.app.a
    public void t(CharSequence charSequence) {
        this.f228h.setWindowTitle(charSequence);
    }

    @Override // androidx.appcompat.app.a
    public b.a.o.b u(b.a aVar) {
        d dVar = this.o;
        if (dVar != null) {
            dVar.c();
        }
        this.f226f.setHideOnContentScrollEnabled(false);
        this.f229i.k();
        d dVar2 = new d(this.f229i.getContext(), aVar);
        if (!dVar2.t()) {
            return null;
        }
        this.o = dVar2;
        dVar2.k();
        this.f229i.h(dVar2);
        v(true);
        this.f229i.sendAccessibilityEvent(32);
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
                this.f228h.l(4);
                this.f229i.setVisibility(0);
                return;
            } else {
                this.f228h.l(0);
                this.f229i.setVisibility(8);
                return;
            }
        }
        if (z) {
            xVarF = this.f228h.s(4, 100L);
            xVarS = this.f229i.f(0, 200L);
        } else {
            xVarS = this.f228h.s(0, 200L);
            xVarF = this.f229i.f(8, 100L);
        }
        b.a.o.h hVar = new b.a.o.h();
        hVar.d(xVarF, xVarS);
        hVar.h();
    }

    void x() {
        b.a aVar = this.q;
        if (aVar != null) {
            aVar.d(this.p);
            this.p = null;
            this.q = null;
        }
    }

    public void y(boolean z) {
        View view;
        b.a.o.h hVar = this.A;
        if (hVar != null) {
            hVar.a();
        }
        if (this.u != 0 || (!this.B && !z)) {
            this.D.a(null);
            return;
        }
        this.f227g.setAlpha(1.0f);
        this.f227g.setTransitioning(true);
        b.a.o.h hVar2 = new b.a.o.h();
        float f2 = -this.f227g.getHeight();
        if (z) {
            this.f227g.getLocationInWindow(new int[]{0, 0});
            f2 -= r5[1];
        }
        x xVarK = t.c(this.f227g).k(f2);
        xVarK.i(this.F);
        hVar2.c(xVarK);
        if (this.v && (view = this.j) != null) {
            hVar2.c(t.c(view).k(f2));
        }
        hVar2.f(f221a);
        hVar2.e(250L);
        hVar2.g(this.D);
        this.A = hVar2;
        hVar2.h();
    }

    public void z(boolean z) {
        View view;
        View view2;
        b.a.o.h hVar = this.A;
        if (hVar != null) {
            hVar.a();
        }
        this.f227g.setVisibility(0);
        if (this.u == 0 && (this.B || z)) {
            this.f227g.setTranslationY(0.0f);
            float f2 = -this.f227g.getHeight();
            if (z) {
                this.f227g.getLocationInWindow(new int[]{0, 0});
                f2 -= r5[1];
            }
            this.f227g.setTranslationY(f2);
            b.a.o.h hVar2 = new b.a.o.h();
            x xVarK = t.c(this.f227g).k(0.0f);
            xVarK.i(this.F);
            hVar2.c(xVarK);
            if (this.v && (view2 = this.j) != null) {
                view2.setTranslationY(f2);
                hVar2.c(t.c(this.j).k(0.0f));
            }
            hVar2.f(f222b);
            hVar2.e(250L);
            hVar2.g(this.E);
            this.A = hVar2;
            hVar2.h();
        } else {
            this.f227g.setAlpha(1.0f);
            this.f227g.setTranslationY(0.0f);
            if (this.v && (view = this.j) != null) {
                view.setTranslationY(0.0f);
            }
            this.E.a(null);
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.f226f;
        if (actionBarOverlayLayout != null) {
            t.g0(actionBarOverlayLayout);
        }
    }

    public k(Dialog dialog) {
        D(dialog.getWindow().getDecorView());
    }
}
