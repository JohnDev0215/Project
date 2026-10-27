package i;

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
import i.a;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import l0.a0;
import l0.t;
import l0.x;
import l0.y;
import l0.z;
import m.b;
import n.g;
import o.p;

/* JADX INFO: compiled from: WindowDecorActionBar.java */
/* JADX INFO: loaded from: classes.dex */
public class l extends i.a implements ActionBarOverlayLayout.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Interpolator f3190a = new AccelerateInterpolator();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Interpolator f3191b = new DecelerateInterpolator();
    public m.h A;
    public boolean B;
    public boolean C;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f3192c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Context f3193d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Activity f3194e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ActionBarOverlayLayout f3195f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ActionBarContainer f3196g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public p f3197h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ActionBarContextView f3198i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public View f3199j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ScrollingTabContainerView f3200k;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f3203n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public d f3204o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public m.b f3205p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public b.a f3206q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f3207r;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f3209t;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f3212w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f3213x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f3214y;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ArrayList<?> f3201l = new ArrayList<>();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f3202m = -1;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public ArrayList<a.b> f3208s = new ArrayList<>();

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f3210u = 0;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f3211v = true;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f3215z = true;
    public final y D = new a();
    public final y E = new b();
    public final a0 F = new c();

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    public class a extends z {
        public a() {
        }

        @Override // l0.y
        public void a(View view) {
            View view2;
            l lVar = l.this;
            if (lVar.f3211v && (view2 = lVar.f3199j) != null) {
                view2.setTranslationY(0.0f);
                l.this.f3196g.setTranslationY(0.0f);
            }
            l.this.f3196g.setVisibility(8);
            l.this.f3196g.setTransitioning(false);
            l lVar2 = l.this;
            lVar2.A = null;
            lVar2.x();
            ActionBarOverlayLayout actionBarOverlayLayout = l.this.f3195f;
            if (actionBarOverlayLayout != null) {
                t.g0(actionBarOverlayLayout);
            }
        }
    }

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    public class b extends z {
        public b() {
        }

        @Override // l0.y
        public void a(View view) {
            l lVar = l.this;
            lVar.A = null;
            lVar.f3196g.requestLayout();
        }
    }

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    public class c implements a0 {
        public c() {
        }

        @Override // l0.a0
        public void a(View view) {
            ((View) l.this.f3196g.getParent()).invalidate();
        }
    }

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    public class d extends m.b implements g.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Context f3219d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final n.g f3220e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public b.a f3221f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public WeakReference<View> f3222g;

        public d(Context context, b.a aVar) {
            this.f3219d = context;
            this.f3221f = aVar;
            n.g gVarW = new n.g(context).W(1);
            this.f3220e = gVarW;
            gVarW.V(this);
        }

        @Override // n.g.a
        public boolean a(n.g gVar, MenuItem menuItem) {
            b.a aVar = this.f3221f;
            if (aVar != null) {
                return aVar.b(this, menuItem);
            }
            return false;
        }

        @Override // n.g.a
        public void b(n.g gVar) {
            if (this.f3221f == null) {
                return;
            }
            k();
            l.this.f3198i.l();
        }

        @Override // m.b
        public void c() {
            l lVar = l.this;
            if (lVar.f3204o != this) {
                return;
            }
            if (l.w(lVar.f3212w, lVar.f3213x, false)) {
                this.f3221f.d(this);
            } else {
                l lVar2 = l.this;
                lVar2.f3205p = this;
                lVar2.f3206q = this.f3221f;
            }
            this.f3221f = null;
            l.this.v(false);
            l.this.f3198i.g();
            l.this.f3197h.o().sendAccessibilityEvent(32);
            l lVar3 = l.this;
            lVar3.f3195f.setHideOnContentScrollEnabled(lVar3.C);
            l.this.f3204o = null;
        }

        @Override // m.b
        public View d() {
            WeakReference<View> weakReference = this.f3222g;
            if (weakReference != null) {
                return weakReference.get();
            }
            return null;
        }

        @Override // m.b
        public Menu e() {
            return this.f3220e;
        }

        @Override // m.b
        public MenuInflater f() {
            return new m.g(this.f3219d);
        }

        @Override // m.b
        public CharSequence g() {
            return l.this.f3198i.getSubtitle();
        }

        @Override // m.b
        public CharSequence i() {
            return l.this.f3198i.getTitle();
        }

        @Override // m.b
        public void k() {
            if (l.this.f3204o != this) {
                return;
            }
            this.f3220e.h0();
            try {
                this.f3221f.a(this, this.f3220e);
            } finally {
                this.f3220e.g0();
            }
        }

        @Override // m.b
        public boolean l() {
            return l.this.f3198i.j();
        }

        @Override // m.b
        public void m(View view) {
            l.this.f3198i.setCustomView(view);
            this.f3222g = new WeakReference<>(view);
        }

        @Override // m.b
        public void n(int i3) {
            o(l.this.f3192c.getResources().getString(i3));
        }

        @Override // m.b
        public void o(CharSequence charSequence) {
            l.this.f3198i.setSubtitle(charSequence);
        }

        @Override // m.b
        public void q(int i3) {
            r(l.this.f3192c.getResources().getString(i3));
        }

        @Override // m.b
        public void r(CharSequence charSequence) {
            l.this.f3198i.setTitle(charSequence);
        }

        @Override // m.b
        public void s(boolean z3) {
            super.s(z3);
            l.this.f3198i.setTitleOptional(z3);
        }

        public boolean t() {
            this.f3220e.h0();
            try {
                return this.f3221f.c(this, this.f3220e);
            } finally {
                this.f3220e.g0();
            }
        }
    }

    public l(Activity activity, boolean z3) {
        this.f3194e = activity;
        View decorView = activity.getWindow().getDecorView();
        D(decorView);
        if (z3) {
            return;
        }
        this.f3199j = decorView.findViewById(R.id.content);
    }

    public static boolean w(boolean z3, boolean z4, boolean z5) {
        if (z5) {
            return true;
        }
        return (z3 || z4) ? false : true;
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
        return this.f3197h.r();
    }

    public final void C() {
        if (this.f3214y) {
            this.f3214y = false;
            ActionBarOverlayLayout actionBarOverlayLayout = this.f3195f;
            if (actionBarOverlayLayout != null) {
                actionBarOverlayLayout.setShowingForActionMode(false);
            }
            M(false);
        }
    }

    public final void D(View view) {
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) view.findViewById(R$id.decor_content_parent);
        this.f3195f = actionBarOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        this.f3197h = A(view.findViewById(R$id.action_bar));
        this.f3198i = (ActionBarContextView) view.findViewById(R$id.action_context_bar);
        ActionBarContainer actionBarContainer = (ActionBarContainer) view.findViewById(R$id.action_bar_container);
        this.f3196g = actionBarContainer;
        p pVar = this.f3197h;
        if (pVar == null || this.f3198i == null || actionBarContainer == null) {
            throw new IllegalStateException(l.class.getSimpleName() + " can only be used with a compatible window decor layout");
        }
        this.f3192c = pVar.q();
        boolean z3 = (this.f3197h.j() & 4) != 0;
        if (z3) {
            this.f3203n = true;
        }
        m.a aVarB = m.a.b(this.f3192c);
        J(aVarB.a() || z3);
        H(aVarB.g());
        TypedArray typedArrayObtainStyledAttributes = this.f3192c.obtainStyledAttributes(null, R$styleable.ActionBar, R$attr.actionBarStyle, 0);
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.ActionBar_hideOnContentScroll, false)) {
            I(true);
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ActionBar_elevation, 0);
        if (dimensionPixelSize != 0) {
            G(dimensionPixelSize);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public void E(boolean z3) {
        F(z3 ? 4 : 0, 4);
    }

    public void F(int i3, int i4) {
        int iJ = this.f3197h.j();
        if ((i4 & 4) != 0) {
            this.f3203n = true;
        }
        this.f3197h.x((i3 & i4) | ((~i4) & iJ));
    }

    public void G(float f3) {
        t.q0(this.f3196g, f3);
    }

    public final void H(boolean z3) {
        this.f3209t = z3;
        if (z3) {
            this.f3196g.setTabContainer(null);
            this.f3197h.n(this.f3200k);
        } else {
            this.f3197h.n(null);
            this.f3196g.setTabContainer(this.f3200k);
        }
        boolean z4 = B() == 2;
        ScrollingTabContainerView scrollingTabContainerView = this.f3200k;
        if (scrollingTabContainerView != null) {
            if (z4) {
                scrollingTabContainerView.setVisibility(0);
                ActionBarOverlayLayout actionBarOverlayLayout = this.f3195f;
                if (actionBarOverlayLayout != null) {
                    t.g0(actionBarOverlayLayout);
                }
            } else {
                scrollingTabContainerView.setVisibility(8);
            }
        }
        this.f3197h.w(!this.f3209t && z4);
        this.f3195f.setHasNonEmbeddedTabs(!this.f3209t && z4);
    }

    public void I(boolean z3) {
        if (z3 && !this.f3195f.w()) {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
        }
        this.C = z3;
        this.f3195f.setHideOnContentScrollEnabled(z3);
    }

    public void J(boolean z3) {
        this.f3197h.p(z3);
    }

    public final boolean K() {
        return t.P(this.f3196g);
    }

    public final void L() {
        if (this.f3214y) {
            return;
        }
        this.f3214y = true;
        ActionBarOverlayLayout actionBarOverlayLayout = this.f3195f;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setShowingForActionMode(true);
        }
        M(false);
    }

    public final void M(boolean z3) {
        if (w(this.f3212w, this.f3213x, this.f3214y)) {
            if (this.f3215z) {
                return;
            }
            this.f3215z = true;
            z(z3);
            return;
        }
        if (this.f3215z) {
            this.f3215z = false;
            y(z3);
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void a(boolean z3) {
        this.f3211v = z3;
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void b() {
        if (this.f3213x) {
            this.f3213x = false;
            M(true);
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void c() {
        m.h hVar = this.A;
        if (hVar != null) {
            hVar.a();
            this.A = null;
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void d(int i3) {
        this.f3210u = i3;
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void e() {
        if (this.f3213x) {
            return;
        }
        this.f3213x = true;
        M(true);
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void f() {
    }

    @Override // i.a
    public boolean h() {
        p pVar = this.f3197h;
        if (pVar == null || !pVar.u()) {
            return false;
        }
        this.f3197h.collapseActionView();
        return true;
    }

    @Override // i.a
    public void i(boolean z3) {
        if (z3 == this.f3207r) {
            return;
        }
        this.f3207r = z3;
        int size = this.f3208s.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.f3208s.get(i3).a(z3);
        }
    }

    @Override // i.a
    public int j() {
        return this.f3197h.j();
    }

    @Override // i.a
    public Context k() {
        if (this.f3193d == null) {
            TypedValue typedValue = new TypedValue();
            this.f3192c.getTheme().resolveAttribute(R$attr.actionBarWidgetTheme, typedValue, true);
            int i3 = typedValue.resourceId;
            if (i3 != 0) {
                this.f3193d = new ContextThemeWrapper(this.f3192c, i3);
            } else {
                this.f3193d = this.f3192c;
            }
        }
        return this.f3193d;
    }

    @Override // i.a
    public void m(Configuration configuration) {
        H(m.a.b(this.f3192c).g());
    }

    @Override // i.a
    public boolean o(int i3, KeyEvent keyEvent) {
        Menu menuE;
        d dVar = this.f3204o;
        if (dVar == null || (menuE = dVar.e()) == null) {
            return false;
        }
        menuE.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
        return menuE.performShortcut(i3, keyEvent, 0);
    }

    @Override // i.a
    public void r(boolean z3) {
        if (this.f3203n) {
            return;
        }
        E(z3);
    }

    @Override // i.a
    public void s(boolean z3) {
        m.h hVar;
        this.B = z3;
        if (z3 || (hVar = this.A) == null) {
            return;
        }
        hVar.a();
    }

    @Override // i.a
    public void t(CharSequence charSequence) {
        this.f3197h.setWindowTitle(charSequence);
    }

    @Override // i.a
    public m.b u(b.a aVar) {
        d dVar = this.f3204o;
        if (dVar != null) {
            dVar.c();
        }
        this.f3195f.setHideOnContentScrollEnabled(false);
        this.f3198i.k();
        d dVar2 = new d(this.f3198i.getContext(), aVar);
        if (!dVar2.t()) {
            return null;
        }
        this.f3204o = dVar2;
        dVar2.k();
        this.f3198i.h(dVar2);
        v(true);
        this.f3198i.sendAccessibilityEvent(32);
        return dVar2;
    }

    public void v(boolean z3) {
        x xVarS;
        x xVarF;
        if (z3) {
            L();
        } else {
            C();
        }
        if (!K()) {
            if (z3) {
                this.f3197h.k(4);
                this.f3198i.setVisibility(0);
                return;
            } else {
                this.f3197h.k(0);
                this.f3198i.setVisibility(8);
                return;
            }
        }
        if (z3) {
            xVarF = this.f3197h.s(4, 100L);
            xVarS = this.f3198i.f(0, 200L);
        } else {
            xVarS = this.f3197h.s(0, 200L);
            xVarF = this.f3198i.f(8, 100L);
        }
        m.h hVar = new m.h();
        hVar.d(xVarF, xVarS);
        hVar.h();
    }

    public void x() {
        b.a aVar = this.f3206q;
        if (aVar != null) {
            aVar.d(this.f3205p);
            this.f3205p = null;
            this.f3206q = null;
        }
    }

    public void y(boolean z3) {
        View view;
        m.h hVar = this.A;
        if (hVar != null) {
            hVar.a();
        }
        if (this.f3210u != 0 || (!this.B && !z3)) {
            this.D.a(null);
            return;
        }
        this.f3196g.setAlpha(1.0f);
        this.f3196g.setTransitioning(true);
        m.h hVar2 = new m.h();
        float f3 = -this.f3196g.getHeight();
        if (z3) {
            this.f3196g.getLocationInWindow(new int[]{0, 0});
            f3 -= r5[1];
        }
        x xVarK = t.c(this.f3196g).k(f3);
        xVarK.i(this.F);
        hVar2.c(xVarK);
        if (this.f3211v && (view = this.f3199j) != null) {
            hVar2.c(t.c(view).k(f3));
        }
        hVar2.f(f3190a);
        hVar2.e(250L);
        hVar2.g(this.D);
        this.A = hVar2;
        hVar2.h();
    }

    public void z(boolean z3) {
        View view;
        View view2;
        m.h hVar = this.A;
        if (hVar != null) {
            hVar.a();
        }
        this.f3196g.setVisibility(0);
        if (this.f3210u == 0 && (this.B || z3)) {
            this.f3196g.setTranslationY(0.0f);
            float f3 = -this.f3196g.getHeight();
            if (z3) {
                this.f3196g.getLocationInWindow(new int[]{0, 0});
                f3 -= r5[1];
            }
            this.f3196g.setTranslationY(f3);
            m.h hVar2 = new m.h();
            x xVarK = t.c(this.f3196g).k(0.0f);
            xVarK.i(this.F);
            hVar2.c(xVarK);
            if (this.f3211v && (view2 = this.f3199j) != null) {
                view2.setTranslationY(f3);
                hVar2.c(t.c(this.f3199j).k(0.0f));
            }
            hVar2.f(f3191b);
            hVar2.e(250L);
            hVar2.g(this.E);
            this.A = hVar2;
            hVar2.h();
        } else {
            this.f3196g.setAlpha(1.0f);
            this.f3196g.setTranslationY(0.0f);
            if (this.f3211v && (view = this.f3199j) != null) {
                view.setTranslationY(0.0f);
            }
            this.E.a(null);
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.f3195f;
        if (actionBarOverlayLayout != null) {
            t.g0(actionBarOverlayLayout);
        }
    }

    public l(Dialog dialog) {
        D(dialog.getWindow().getDecorView());
    }
}
