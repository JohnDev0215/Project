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
import androidx.appcompat.view.menu.e;
import androidx.appcompat.widget.ActionBarContainer;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.a0;
import androidx.appcompat.widget.l0;
import i.b;
import i0.b0;
import i0.u;
import i0.y;
import i0.z;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: compiled from: WindowDecorActionBar.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends androidx.appcompat.app.a implements ActionBarOverlayLayout.d {
    private static final Interpolator E = new AccelerateInterpolator();
    private static final Interpolator F = new DecelerateInterpolator();
    boolean A;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Context f539a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f540b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Activity f541c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    ActionBarOverlayLayout f542d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    ActionBarContainer f543e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    a0 f544f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    ActionBarContextView f545g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    View f546h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    l0 f547i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private boolean f550l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    d f551m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    i.b f552n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    b.a f553o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private boolean f554p;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private boolean f556r;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    boolean f559u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    boolean f560v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private boolean f561w;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    i.h f563y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private boolean f564z;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private ArrayList<Object> f548j = new ArrayList<>();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private int f549k = -1;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private ArrayList<a.b> f555q = new ArrayList<>();

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private int f557s = 0;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    boolean f558t = true;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private boolean f562x = true;
    final z B = new a();
    final z C = new b();
    final b0 D = new c();

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    class a extends i0.a0 {
        a() {
        }

        @Override // i0.z
        public void a(View view) {
            View view2;
            k kVar = k.this;
            if (kVar.f558t && (view2 = kVar.f546h) != null) {
                view2.setTranslationY(0.0f);
                k.this.f543e.setTranslationY(0.0f);
            }
            k.this.f543e.setVisibility(8);
            k.this.f543e.setTransitioning(false);
            k kVar2 = k.this;
            kVar2.f563y = null;
            kVar2.x();
            ActionBarOverlayLayout actionBarOverlayLayout = k.this.f542d;
            if (actionBarOverlayLayout != null) {
                u.h0(actionBarOverlayLayout);
            }
        }
    }

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    class b extends i0.a0 {
        b() {
        }

        @Override // i0.z
        public void a(View view) {
            k kVar = k.this;
            kVar.f563y = null;
            kVar.f543e.requestLayout();
        }
    }

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    class c implements b0 {
        c() {
        }

        @Override // i0.b0
        public void a(View view) {
            ((View) k.this.f543e.getParent()).invalidate();
        }
    }

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    public class d extends i.b implements e.a {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private final Context f568h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private final androidx.appcompat.view.menu.e f569i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private b.a f570j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private WeakReference<View> f571k;

        public d(Context context, b.a aVar) {
            this.f568h = context;
            this.f570j = aVar;
            androidx.appcompat.view.menu.e eVarS = new androidx.appcompat.view.menu.e(context).S(1);
            this.f569i = eVarS;
            eVarS.R(this);
        }

        @Override // androidx.appcompat.view.menu.e.a
        public boolean a(androidx.appcompat.view.menu.e eVar, MenuItem menuItem) {
            b.a aVar = this.f570j;
            if (aVar != null) {
                return aVar.a(this, menuItem);
            }
            return false;
        }

        @Override // androidx.appcompat.view.menu.e.a
        public void b(androidx.appcompat.view.menu.e eVar) {
            if (this.f570j == null) {
                return;
            }
            k();
            k.this.f545g.l();
        }

        @Override // i.b
        public void c() {
            k kVar = k.this;
            if (kVar.f551m != this) {
                return;
            }
            if (k.w(kVar.f559u, kVar.f560v, false)) {
                this.f570j.c(this);
            } else {
                k kVar2 = k.this;
                kVar2.f552n = this;
                kVar2.f553o = this.f570j;
            }
            this.f570j = null;
            k.this.v(false);
            k.this.f545g.g();
            k.this.f544f.m().sendAccessibilityEvent(32);
            k kVar3 = k.this;
            kVar3.f542d.setHideOnContentScrollEnabled(kVar3.A);
            k.this.f551m = null;
        }

        @Override // i.b
        public View d() {
            WeakReference<View> weakReference = this.f571k;
            if (weakReference != null) {
                return weakReference.get();
            }
            return null;
        }

        @Override // i.b
        public Menu e() {
            return this.f569i;
        }

        @Override // i.b
        public MenuInflater f() {
            return new i.g(this.f568h);
        }

        @Override // i.b
        public CharSequence g() {
            return k.this.f545g.getSubtitle();
        }

        @Override // i.b
        public CharSequence i() {
            return k.this.f545g.getTitle();
        }

        @Override // i.b
        public void k() {
            if (k.this.f551m != this) {
                return;
            }
            this.f569i.d0();
            try {
                this.f570j.b(this, this.f569i);
            } finally {
                this.f569i.c0();
            }
        }

        @Override // i.b
        public boolean l() {
            return k.this.f545g.j();
        }

        @Override // i.b
        public void m(View view) {
            k.this.f545g.setCustomView(view);
            this.f571k = new WeakReference<>(view);
        }

        @Override // i.b
        public void n(int i7) {
            o(k.this.f539a.getResources().getString(i7));
        }

        @Override // i.b
        public void o(CharSequence charSequence) {
            k.this.f545g.setSubtitle(charSequence);
        }

        @Override // i.b
        public void q(int i7) {
            r(k.this.f539a.getResources().getString(i7));
        }

        @Override // i.b
        public void r(CharSequence charSequence) {
            k.this.f545g.setTitle(charSequence);
        }

        @Override // i.b
        public void s(boolean z6) {
            super.s(z6);
            k.this.f545g.setTitleOptional(z6);
        }

        public boolean t() {
            this.f569i.d0();
            try {
                return this.f570j.d(this, this.f569i);
            } finally {
                this.f569i.c0();
            }
        }
    }

    public k(Activity activity, boolean z6) {
        this.f541c = activity;
        View decorView = activity.getWindow().getDecorView();
        D(decorView);
        if (z6) {
            return;
        }
        this.f546h = decorView.findViewById(R.id.content);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private a0 A(View view) {
        if (view instanceof a0) {
            return (a0) view;
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
        if (this.f561w) {
            this.f561w = false;
            ActionBarOverlayLayout actionBarOverlayLayout = this.f542d;
            if (actionBarOverlayLayout != null) {
                actionBarOverlayLayout.setShowingForActionMode(false);
            }
            M(false);
        }
    }

    private void D(View view) {
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) view.findViewById(e.f.decor_content_parent);
        this.f542d = actionBarOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        this.f544f = A(view.findViewById(e.f.action_bar));
        this.f545g = (ActionBarContextView) view.findViewById(e.f.action_context_bar);
        ActionBarContainer actionBarContainer = (ActionBarContainer) view.findViewById(e.f.action_bar_container);
        this.f543e = actionBarContainer;
        a0 a0Var = this.f544f;
        if (a0Var == null || this.f545g == null || actionBarContainer == null) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with a compatible window decor layout");
        }
        this.f539a = a0Var.getContext();
        boolean z6 = (this.f544f.i() & 4) != 0;
        if (z6) {
            this.f550l = true;
        }
        i.a aVarB = i.a.b(this.f539a);
        J(aVarB.a() || z6);
        H(aVarB.g());
        TypedArray typedArrayObtainStyledAttributes = this.f539a.obtainStyledAttributes(null, e.j.ActionBar, e.a.actionBarStyle, 0);
        if (typedArrayObtainStyledAttributes.getBoolean(e.j.ActionBar_hideOnContentScroll, false)) {
            I(true);
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(e.j.ActionBar_elevation, 0);
        if (dimensionPixelSize != 0) {
            G(dimensionPixelSize);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    private void H(boolean z6) {
        this.f556r = z6;
        if (z6) {
            this.f543e.setTabContainer(null);
            this.f544f.l(this.f547i);
        } else {
            this.f544f.l(null);
            this.f543e.setTabContainer(this.f547i);
        }
        boolean z7 = B() == 2;
        l0 l0Var = this.f547i;
        if (l0Var != null) {
            if (z7) {
                l0Var.setVisibility(0);
                ActionBarOverlayLayout actionBarOverlayLayout = this.f542d;
                if (actionBarOverlayLayout != null) {
                    u.h0(actionBarOverlayLayout);
                }
            } else {
                l0Var.setVisibility(8);
            }
        }
        this.f544f.t(!this.f556r && z7);
        this.f542d.setHasNonEmbeddedTabs(!this.f556r && z7);
    }

    private boolean K() {
        return u.P(this.f543e);
    }

    private void L() {
        if (this.f561w) {
            return;
        }
        this.f561w = true;
        ActionBarOverlayLayout actionBarOverlayLayout = this.f542d;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setShowingForActionMode(true);
        }
        M(false);
    }

    private void M(boolean z6) {
        if (w(this.f559u, this.f560v, this.f561w)) {
            if (this.f562x) {
                return;
            }
            this.f562x = true;
            z(z6);
            return;
        }
        if (this.f562x) {
            this.f562x = false;
            y(z6);
        }
    }

    static boolean w(boolean z6, boolean z7, boolean z8) {
        if (z8) {
            return true;
        }
        return (z6 || z7) ? false : true;
    }

    public int B() {
        return this.f544f.o();
    }

    public void E(boolean z6) {
        F(z6 ? 4 : 0, 4);
    }

    public void F(int i7, int i8) {
        int i9 = this.f544f.i();
        if ((i8 & 4) != 0) {
            this.f550l = true;
        }
        this.f544f.u((i7 & i8) | ((~i8) & i9));
    }

    public void G(float f7) {
        u.r0(this.f543e, f7);
    }

    public void I(boolean z6) {
        if (z6 && !this.f542d.w()) {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
        }
        this.A = z6;
        this.f542d.setHideOnContentScrollEnabled(z6);
    }

    public void J(boolean z6) {
        this.f544f.n(z6);
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void a(boolean z6) {
        this.f558t = z6;
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void b() {
        if (this.f560v) {
            this.f560v = false;
            M(true);
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void c() {
        i.h hVar = this.f563y;
        if (hVar != null) {
            hVar.a();
            this.f563y = null;
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void d(int i7) {
        this.f557s = i7;
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void e() {
        if (this.f560v) {
            return;
        }
        this.f560v = true;
        M(true);
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void f() {
    }

    @Override // androidx.appcompat.app.a
    public boolean h() {
        a0 a0Var = this.f544f;
        if (a0Var == null || !a0Var.r()) {
            return false;
        }
        this.f544f.collapseActionView();
        return true;
    }

    @Override // androidx.appcompat.app.a
    public void i(boolean z6) {
        if (z6 == this.f554p) {
            return;
        }
        this.f554p = z6;
        int size = this.f555q.size();
        for (int i7 = 0; i7 < size; i7++) {
            this.f555q.get(i7).a(z6);
        }
    }

    @Override // androidx.appcompat.app.a
    public int j() {
        return this.f544f.i();
    }

    @Override // androidx.appcompat.app.a
    public Context k() {
        if (this.f540b == null) {
            TypedValue typedValue = new TypedValue();
            this.f539a.getTheme().resolveAttribute(e.a.actionBarWidgetTheme, typedValue, true);
            int i7 = typedValue.resourceId;
            if (i7 != 0) {
                this.f540b = new ContextThemeWrapper(this.f539a, i7);
            } else {
                this.f540b = this.f539a;
            }
        }
        return this.f540b;
    }

    @Override // androidx.appcompat.app.a
    public void m(Configuration configuration) {
        H(i.a.b(this.f539a).g());
    }

    @Override // androidx.appcompat.app.a
    public boolean o(int i7, KeyEvent keyEvent) {
        Menu menuE;
        d dVar = this.f551m;
        if (dVar == null || (menuE = dVar.e()) == null) {
            return false;
        }
        menuE.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
        return menuE.performShortcut(i7, keyEvent, 0);
    }

    @Override // androidx.appcompat.app.a
    public void r(boolean z6) {
        if (this.f550l) {
            return;
        }
        E(z6);
    }

    @Override // androidx.appcompat.app.a
    public void s(boolean z6) {
        i.h hVar;
        this.f564z = z6;
        if (z6 || (hVar = this.f563y) == null) {
            return;
        }
        hVar.a();
    }

    @Override // androidx.appcompat.app.a
    public void t(CharSequence charSequence) {
        this.f544f.setWindowTitle(charSequence);
    }

    @Override // androidx.appcompat.app.a
    public i.b u(b.a aVar) {
        d dVar = this.f551m;
        if (dVar != null) {
            dVar.c();
        }
        this.f542d.setHideOnContentScrollEnabled(false);
        this.f545g.k();
        d dVar2 = new d(this.f545g.getContext(), aVar);
        if (!dVar2.t()) {
            return null;
        }
        this.f551m = dVar2;
        dVar2.k();
        this.f545g.h(dVar2);
        v(true);
        this.f545g.sendAccessibilityEvent(32);
        return dVar2;
    }

    public void v(boolean z6) {
        y yVarP;
        y yVarF;
        if (z6) {
            L();
        } else {
            C();
        }
        if (!K()) {
            if (z6) {
                this.f544f.j(4);
                this.f545g.setVisibility(0);
                return;
            } else {
                this.f544f.j(0);
                this.f545g.setVisibility(8);
                return;
            }
        }
        if (z6) {
            yVarF = this.f544f.p(4, 100L);
            yVarP = this.f545g.f(0, 200L);
        } else {
            yVarP = this.f544f.p(0, 200L);
            yVarF = this.f545g.f(8, 100L);
        }
        i.h hVar = new i.h();
        hVar.d(yVarF, yVarP);
        hVar.h();
    }

    void x() {
        b.a aVar = this.f553o;
        if (aVar != null) {
            aVar.c(this.f552n);
            this.f552n = null;
            this.f553o = null;
        }
    }

    public void y(boolean z6) {
        View view;
        i.h hVar = this.f563y;
        if (hVar != null) {
            hVar.a();
        }
        if (this.f557s != 0 || (!this.f564z && !z6)) {
            this.B.a(null);
            return;
        }
        this.f543e.setAlpha(1.0f);
        this.f543e.setTransitioning(true);
        i.h hVar2 = new i.h();
        float f7 = -this.f543e.getHeight();
        if (z6) {
            this.f543e.getLocationInWindow(new int[]{0, 0});
            f7 -= r5[1];
        }
        y yVarK = u.d(this.f543e).k(f7);
        yVarK.i(this.D);
        hVar2.c(yVarK);
        if (this.f558t && (view = this.f546h) != null) {
            hVar2.c(u.d(view).k(f7));
        }
        hVar2.f(E);
        hVar2.e(250L);
        hVar2.g(this.B);
        this.f563y = hVar2;
        hVar2.h();
    }

    public void z(boolean z6) {
        View view;
        View view2;
        i.h hVar = this.f563y;
        if (hVar != null) {
            hVar.a();
        }
        this.f543e.setVisibility(0);
        if (this.f557s == 0 && (this.f564z || z6)) {
            this.f543e.setTranslationY(0.0f);
            float f7 = -this.f543e.getHeight();
            if (z6) {
                this.f543e.getLocationInWindow(new int[]{0, 0});
                f7 -= r5[1];
            }
            this.f543e.setTranslationY(f7);
            i.h hVar2 = new i.h();
            y yVarK = u.d(this.f543e).k(0.0f);
            yVarK.i(this.D);
            hVar2.c(yVarK);
            if (this.f558t && (view2 = this.f546h) != null) {
                view2.setTranslationY(f7);
                hVar2.c(u.d(this.f546h).k(0.0f));
            }
            hVar2.f(F);
            hVar2.e(250L);
            hVar2.g(this.C);
            this.f563y = hVar2;
            hVar2.h();
        } else {
            this.f543e.setAlpha(1.0f);
            this.f543e.setTranslationY(0.0f);
            if (this.f558t && (view = this.f546h) != null) {
                view.setTranslationY(0.0f);
            }
            this.C.a(null);
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.f542d;
        if (actionBarOverlayLayout != null) {
            u.h0(actionBarOverlayLayout);
        }
    }

    public k(Dialog dialog) {
        D(dialog.getWindow().getDecorView());
    }
}
