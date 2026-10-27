package androidx.appcompat.app;

import a.a.n.b;
import a.g.k.a0;
import a.g.k.t;
import a.g.k.x;
import a.g.k.y;
import a.g.k.z;
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
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.c0;
import androidx.appcompat.widget.o0;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class l extends androidx.appcompat.app.a implements ActionBarOverlayLayout.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Interpolator f768a = new AccelerateInterpolator();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Interpolator f769b = new DecelerateInterpolator();
    a.a.n.h A;
    private boolean B;
    boolean C;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Context f770c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Context f771d;
    private Activity e;
    ActionBarOverlayLayout f;
    ActionBarContainer g;
    c0 h;
    ActionBarContextView i;
    View j;
    o0 k;
    private boolean n;
    d o;
    a.a.n.b p;
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

    class a extends z {
        a() {
        }

        @Override // a.g.k.y
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
                t.e0(actionBarOverlayLayout);
            }
        }
    }

    class b extends z {
        b() {
        }

        @Override // a.g.k.y
        public void a(View view) {
            l lVar = l.this;
            lVar.A = null;
            lVar.g.requestLayout();
        }
    }

    class c implements a0 {
        c() {
        }

        @Override // a.g.k.a0
        public void a(View view) {
            ((View) l.this.g.getParent()).invalidate();
        }
    }

    public class d extends a.a.n.b implements g.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final Context f775d;
        private final androidx.appcompat.view.menu.g e;
        private b.a f;
        private WeakReference<View> g;

        public d(Context context, b.a aVar) {
            this.f775d = context;
            this.f = aVar;
            androidx.appcompat.view.menu.g gVarS = new androidx.appcompat.view.menu.g(context).S(1);
            this.e = gVarS;
            gVarS.R(this);
        }

        @Override // androidx.appcompat.view.menu.g.a
        public boolean a(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
            b.a aVar = this.f;
            if (aVar != null) {
                return aVar.b(this, menuItem);
            }
            return false;
        }

        @Override // androidx.appcompat.view.menu.g.a
        public void b(androidx.appcompat.view.menu.g gVar) {
            if (this.f == null) {
                return;
            }
            k();
            l.this.i.l();
        }

        @Override // a.a.n.b
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
            l.this.h.m().sendAccessibilityEvent(32);
            l lVar3 = l.this;
            lVar3.f.setHideOnContentScrollEnabled(lVar3.C);
            l.this.o = null;
        }

        @Override // a.a.n.b
        public View d() {
            WeakReference<View> weakReference = this.g;
            if (weakReference != null) {
                return weakReference.get();
            }
            return null;
        }

        @Override // a.a.n.b
        public Menu e() {
            return this.e;
        }

        @Override // a.a.n.b
        public MenuInflater f() {
            return new a.a.n.g(this.f775d);
        }

        @Override // a.a.n.b
        public CharSequence g() {
            return l.this.i.getSubtitle();
        }

        @Override // a.a.n.b
        public CharSequence i() {
            return l.this.i.getTitle();
        }

        @Override // a.a.n.b
        public void k() {
            if (l.this.o != this) {
                return;
            }
            this.e.d0();
            try {
                this.f.a(this, this.e);
            } finally {
                this.e.c0();
            }
        }

        @Override // a.a.n.b
        public boolean l() {
            return l.this.i.j();
        }

        @Override // a.a.n.b
        public void m(View view) {
            l.this.i.setCustomView(view);
            this.g = new WeakReference<>(view);
        }

        @Override // a.a.n.b
        public void n(int i) {
            o(l.this.f770c.getResources().getString(i));
        }

        @Override // a.a.n.b
        public void o(CharSequence charSequence) {
            l.this.i.setSubtitle(charSequence);
        }

        @Override // a.a.n.b
        public void q(int i) {
            r(l.this.f770c.getResources().getString(i));
        }

        @Override // a.a.n.b
        public void r(CharSequence charSequence) {
            l.this.i.setTitle(charSequence);
        }

        @Override // a.a.n.b
        public void s(boolean z) {
            super.s(z);
            l.this.i.setTitleOptional(z);
        }

        public boolean t() {
            this.e.d0();
            try {
                return this.f.c(this, this.e);
            } finally {
                this.e.c0();
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

    public l(Dialog dialog) {
        D(dialog.getWindow().getDecorView());
    }

    /* JADX WARN: Multi-variable type inference failed */
    private c0 A(View view) {
        if (view instanceof c0) {
            return (c0) view;
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
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) view.findViewById(a.a.f.p);
        this.f = actionBarOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        this.h = A(view.findViewById(a.a.f.f17a));
        this.i = (ActionBarContextView) view.findViewById(a.a.f.f);
        ActionBarContainer actionBarContainer = (ActionBarContainer) view.findViewById(a.a.f.f19c);
        this.g = actionBarContainer;
        c0 c0Var = this.h;
        if (c0Var == null || this.i == null || actionBarContainer == null) {
            throw new IllegalStateException(l.class.getSimpleName() + " can only be used with a compatible window decor layout");
        }
        this.f770c = c0Var.o();
        boolean z = (this.h.i() & 4) != 0;
        if (z) {
            this.n = true;
        }
        a.a.n.a aVarB = a.a.n.a.b(this.f770c);
        J(aVarB.a() || z);
        H(aVarB.g());
        TypedArray typedArrayObtainStyledAttributes = this.f770c.obtainStyledAttributes(null, a.a.j.f33a, a.a.a.f2c, 0);
        if (typedArrayObtainStyledAttributes.getBoolean(a.a.j.k, false)) {
            I(true);
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(a.a.j.i, 0);
        if (dimensionPixelSize != 0) {
            G(dimensionPixelSize);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    private void H(boolean z) {
        this.t = z;
        if (z) {
            this.g.setTabContainer(null);
            this.h.l(this.k);
        } else {
            this.h.l(null);
            this.g.setTabContainer(this.k);
        }
        boolean z2 = B() == 2;
        o0 o0Var = this.k;
        if (o0Var != null) {
            if (z2) {
                o0Var.setVisibility(0);
                ActionBarOverlayLayout actionBarOverlayLayout = this.f;
                if (actionBarOverlayLayout != null) {
                    t.e0(actionBarOverlayLayout);
                }
            } else {
                o0Var.setVisibility(8);
            }
        }
        this.h.u(!this.t && z2);
        this.f.setHasNonEmbeddedTabs(!this.t && z2);
    }

    private boolean K() {
        return t.N(this.g);
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
        return this.h.p();
    }

    public void E(boolean z) {
        F(z ? 4 : 0, 4);
    }

    public void F(int i, int i2) {
        int i3 = this.h.i();
        if ((i2 & 4) != 0) {
            this.n = true;
        }
        this.h.v((i & i2) | ((~i2) & i3));
    }

    public void G(float f) {
        t.o0(this.g, f);
    }

    public void I(boolean z) {
        if (z && !this.f.w()) {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
        }
        this.C = z;
        this.f.setHideOnContentScrollEnabled(z);
    }

    public void J(boolean z) {
        this.h.n(z);
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
        a.a.n.h hVar = this.A;
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

    @Override // androidx.appcompat.app.a
    public boolean h() {
        c0 c0Var = this.h;
        if (c0Var == null || !c0Var.s()) {
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
        return this.h.i();
    }

    @Override // androidx.appcompat.app.a
    public Context k() {
        if (this.f771d == null) {
            TypedValue typedValue = new TypedValue();
            this.f770c.getTheme().resolveAttribute(a.a.a.g, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                this.f771d = new ContextThemeWrapper(this.f770c, i);
            } else {
                this.f771d = this.f770c;
            }
        }
        return this.f771d;
    }

    @Override // androidx.appcompat.app.a
    public void m(Configuration configuration) {
        H(a.a.n.a.b(this.f770c).g());
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
        a.a.n.h hVar;
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
    public a.a.n.b u(b.a aVar) {
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
        x xVarQ;
        x xVarF;
        if (z) {
            L();
        } else {
            C();
        }
        if (!K()) {
            if (z) {
                this.h.j(4);
                this.i.setVisibility(0);
                return;
            } else {
                this.h.j(0);
                this.i.setVisibility(8);
                return;
            }
        }
        if (z) {
            xVarF = this.h.q(4, 100L);
            xVarQ = this.i.f(0, 200L);
        } else {
            xVarQ = this.h.q(0, 200L);
            xVarF = this.i.f(8, 100L);
        }
        a.a.n.h hVar = new a.a.n.h();
        hVar.d(xVarF, xVarQ);
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
        a.a.n.h hVar = this.A;
        if (hVar != null) {
            hVar.a();
        }
        if (this.u != 0 || (!this.B && !z)) {
            this.D.a(null);
            return;
        }
        this.g.setAlpha(1.0f);
        this.g.setTransitioning(true);
        a.a.n.h hVar2 = new a.a.n.h();
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
        hVar2.f(f768a);
        hVar2.e(250L);
        hVar2.g(this.D);
        this.A = hVar2;
        hVar2.h();
    }

    public void z(boolean z) {
        View view;
        View view2;
        a.a.n.h hVar = this.A;
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
            a.a.n.h hVar2 = new a.a.n.h();
            x xVarK = t.c(this.g).k(0.0f);
            xVarK.i(this.F);
            hVar2.c(xVarK);
            if (this.v && (view2 = this.j) != null) {
                view2.setTranslationY(f);
                hVar2.c(t.c(this.j).k(0.0f));
            }
            hVar2.f(f769b);
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
            t.e0(actionBarOverlayLayout);
        }
    }
}
