package l;

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
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import l.a;
import o0.a0;
import o0.t;
import o0.x;
import o0.y;
import o0.z;
import p.b;
import q.g;
import r.p;

/* JADX INFO: compiled from: WindowDecorActionBar.java */
/* JADX INFO: loaded from: classes.dex */
public class l extends l.a implements ActionBarOverlayLayout.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Interpolator f5984a = new AccelerateInterpolator();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Interpolator f5985b = new DecelerateInterpolator();
    public p.h A;
    public boolean B;
    public boolean C;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f5986c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Context f5987d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Activity f5988e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ActionBarOverlayLayout f5989f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ActionBarContainer f5990g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public p f5991h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ActionBarContextView f5992i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public View f5993j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ScrollingTabContainerView f5994k;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f5997n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public d f5998o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public p.b f5999p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public b.a f6000q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f6001r;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f6003t;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f6006w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f6007x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f6008y;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ArrayList<?> f5995l = new ArrayList<>();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f5996m = -1;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public ArrayList<a.b> f6002s = new ArrayList<>();

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f6004u = 0;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f6005v = true;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f6009z = true;
    public final y D = new a();
    public final y E = new b();
    public final a0 F = new c();

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    public class a extends z {
        public a() {
        }

        @Override // o0.y
        public void b(View view) {
            View view2;
            l lVar = l.this;
            if (lVar.f6005v && (view2 = lVar.f5993j) != null) {
                view2.setTranslationY(0.0f);
                l.this.f5990g.setTranslationY(0.0f);
            }
            l.this.f5990g.setVisibility(8);
            l.this.f5990g.setTransitioning(false);
            l lVar2 = l.this;
            lVar2.A = null;
            lVar2.x();
            ActionBarOverlayLayout actionBarOverlayLayout = l.this.f5989f;
            if (actionBarOverlayLayout != null) {
                t.i0(actionBarOverlayLayout);
            }
        }
    }

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    public class b extends z {
        public b() {
        }

        @Override // o0.y
        public void b(View view) {
            l lVar = l.this;
            lVar.A = null;
            lVar.f5990g.requestLayout();
        }
    }

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    public class c implements a0 {
        public c() {
        }

        @Override // o0.a0
        public void a(View view) {
            ((View) l.this.f5990g.getParent()).invalidate();
        }
    }

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    public class d extends p.b implements g.a {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final Context f6013f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final q.g f6014g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public b.a f6015h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public WeakReference<View> f6016i;

        public d(Context context, b.a aVar) {
            this.f6013f = context;
            this.f6015h = aVar;
            q.g gVarW = new q.g(context).W(1);
            this.f6014g = gVarW;
            gVarW.V(this);
        }

        @Override // q.g.a
        public boolean a(q.g gVar, MenuItem menuItem) {
            b.a aVar = this.f6015h;
            if (aVar != null) {
                return aVar.c(this, menuItem);
            }
            return false;
        }

        @Override // q.g.a
        public void b(q.g gVar) {
            if (this.f6015h == null) {
                return;
            }
            k();
            l.this.f5992i.l();
        }

        @Override // p.b
        public void c() {
            l lVar = l.this;
            if (lVar.f5998o != this) {
                return;
            }
            if (l.w(lVar.f6006w, lVar.f6007x, false)) {
                this.f6015h.b(this);
            } else {
                l lVar2 = l.this;
                lVar2.f5999p = this;
                lVar2.f6000q = this.f6015h;
            }
            this.f6015h = null;
            l.this.v(false);
            l.this.f5992i.g();
            l.this.f5991h.l().sendAccessibilityEvent(32);
            l lVar3 = l.this;
            lVar3.f5989f.setHideOnContentScrollEnabled(lVar3.C);
            l.this.f5998o = null;
        }

        @Override // p.b
        public View d() {
            WeakReference<View> weakReference = this.f6016i;
            if (weakReference != null) {
                return weakReference.get();
            }
            return null;
        }

        @Override // p.b
        public Menu e() {
            return this.f6014g;
        }

        @Override // p.b
        public MenuInflater f() {
            return new p.g(this.f6013f);
        }

        @Override // p.b
        public CharSequence g() {
            return l.this.f5992i.getSubtitle();
        }

        @Override // p.b
        public CharSequence i() {
            return l.this.f5992i.getTitle();
        }

        @Override // p.b
        public void k() {
            if (l.this.f5998o != this) {
                return;
            }
            this.f6014g.h0();
            try {
                this.f6015h.a(this, this.f6014g);
            } finally {
                this.f6014g.g0();
            }
        }

        @Override // p.b
        public boolean l() {
            return l.this.f5992i.j();
        }

        @Override // p.b
        public void m(View view) {
            l.this.f5992i.setCustomView(view);
            this.f6016i = new WeakReference<>(view);
        }

        @Override // p.b
        public void n(int i8) {
            o(l.this.f5986c.getResources().getString(i8));
        }

        @Override // p.b
        public void o(CharSequence charSequence) {
            l.this.f5992i.setSubtitle(charSequence);
        }

        @Override // p.b
        public void q(int i8) {
            r(l.this.f5986c.getResources().getString(i8));
        }

        @Override // p.b
        public void r(CharSequence charSequence) {
            l.this.f5992i.setTitle(charSequence);
        }

        @Override // p.b
        public void s(boolean z7) {
            super.s(z7);
            l.this.f5992i.setTitleOptional(z7);
        }

        public boolean t() {
            this.f6014g.h0();
            try {
                return this.f6015h.d(this, this.f6014g);
            } finally {
                this.f6014g.g0();
            }
        }
    }

    public l(Activity activity, boolean z7) {
        this.f5988e = activity;
        View decorView = activity.getWindow().getDecorView();
        D(decorView);
        if (z7) {
            return;
        }
        this.f5993j = decorView.findViewById(R.id.content);
    }

    public static boolean w(boolean z7, boolean z8, boolean z9) {
        if (z9) {
            return true;
        }
        return (z7 || z8) ? false : true;
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
        return this.f5991h.t();
    }

    public final void C() {
        if (this.f6008y) {
            this.f6008y = false;
            ActionBarOverlayLayout actionBarOverlayLayout = this.f5989f;
            if (actionBarOverlayLayout != null) {
                actionBarOverlayLayout.setShowingForActionMode(false);
            }
            M(false);
        }
    }

    public final void D(View view) {
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) view.findViewById(R$id.decor_content_parent);
        this.f5989f = actionBarOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        this.f5991h = A(view.findViewById(R$id.action_bar));
        this.f5992i = (ActionBarContextView) view.findViewById(R$id.action_context_bar);
        ActionBarContainer actionBarContainer = (ActionBarContainer) view.findViewById(R$id.action_bar_container);
        this.f5990g = actionBarContainer;
        p pVar = this.f5991h;
        if (pVar == null || this.f5992i == null || actionBarContainer == null) {
            throw new IllegalStateException(l.class.getSimpleName() + " can only be used with a compatible window decor layout");
        }
        this.f5986c = pVar.n();
        boolean z7 = (this.f5991h.q() & 4) != 0;
        if (z7) {
            this.f5997n = true;
        }
        p.a aVarB = p.a.b(this.f5986c);
        J(aVarB.a() || z7);
        H(aVarB.g());
        TypedArray typedArrayObtainStyledAttributes = this.f5986c.obtainStyledAttributes(null, R$styleable.ActionBar, R$attr.actionBarStyle, 0);
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.ActionBar_hideOnContentScroll, false)) {
            I(true);
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ActionBar_elevation, 0);
        if (dimensionPixelSize != 0) {
            G(dimensionPixelSize);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public void E(boolean z7) {
        F(z7 ? 4 : 0, 4);
    }

    public void F(int i8, int i9) {
        int iQ = this.f5991h.q();
        if ((i9 & 4) != 0) {
            this.f5997n = true;
        }
        this.f5991h.p((i8 & i9) | ((~i9) & iQ));
    }

    public void G(float f8) {
        t.s0(this.f5990g, f8);
    }

    public final void H(boolean z7) {
        this.f6003t = z7;
        if (z7) {
            this.f5990g.setTabContainer(null);
            this.f5991h.k(this.f5994k);
        } else {
            this.f5991h.k(null);
            this.f5990g.setTabContainer(this.f5994k);
        }
        boolean z8 = B() == 2;
        ScrollingTabContainerView scrollingTabContainerView = this.f5994k;
        if (scrollingTabContainerView != null) {
            if (z8) {
                scrollingTabContainerView.setVisibility(0);
                ActionBarOverlayLayout actionBarOverlayLayout = this.f5989f;
                if (actionBarOverlayLayout != null) {
                    t.i0(actionBarOverlayLayout);
                }
            } else {
                scrollingTabContainerView.setVisibility(8);
            }
        }
        this.f5991h.x(!this.f6003t && z8);
        this.f5989f.setHasNonEmbeddedTabs(!this.f6003t && z8);
    }

    public void I(boolean z7) {
        if (z7 && !this.f5989f.w()) {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
        }
        this.C = z7;
        this.f5989f.setHideOnContentScrollEnabled(z7);
    }

    public void J(boolean z7) {
        this.f5991h.m(z7);
    }

    public final boolean K() {
        return t.Q(this.f5990g);
    }

    public final void L() {
        if (this.f6008y) {
            return;
        }
        this.f6008y = true;
        ActionBarOverlayLayout actionBarOverlayLayout = this.f5989f;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setShowingForActionMode(true);
        }
        M(false);
    }

    public final void M(boolean z7) {
        if (w(this.f6006w, this.f6007x, this.f6008y)) {
            if (this.f6009z) {
                return;
            }
            this.f6009z = true;
            z(z7);
            return;
        }
        if (this.f6009z) {
            this.f6009z = false;
            y(z7);
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void a() {
        if (this.f6007x) {
            this.f6007x = false;
            M(true);
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void b() {
        p.h hVar = this.A;
        if (hVar != null) {
            hVar.a();
            this.A = null;
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void c(int i8) {
        this.f6004u = i8;
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void d() {
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void e(boolean z7) {
        this.f6005v = z7;
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void f() {
        if (this.f6007x) {
            return;
        }
        this.f6007x = true;
        M(true);
    }

    @Override // l.a
    public boolean h() {
        p pVar = this.f5991h;
        if (pVar == null || !pVar.o()) {
            return false;
        }
        this.f5991h.collapseActionView();
        return true;
    }

    @Override // l.a
    public void i(boolean z7) {
        if (z7 == this.f6001r) {
            return;
        }
        this.f6001r = z7;
        int size = this.f6002s.size();
        for (int i8 = 0; i8 < size; i8++) {
            this.f6002s.get(i8).a(z7);
        }
    }

    @Override // l.a
    public int j() {
        return this.f5991h.q();
    }

    @Override // l.a
    public Context k() {
        if (this.f5987d == null) {
            TypedValue typedValue = new TypedValue();
            this.f5986c.getTheme().resolveAttribute(R$attr.actionBarWidgetTheme, typedValue, true);
            int i8 = typedValue.resourceId;
            if (i8 != 0) {
                this.f5987d = new ContextThemeWrapper(this.f5986c, i8);
            } else {
                this.f5987d = this.f5986c;
            }
        }
        return this.f5987d;
    }

    @Override // l.a
    public void m(Configuration configuration) {
        H(p.a.b(this.f5986c).g());
    }

    @Override // l.a
    public boolean o(int i8, KeyEvent keyEvent) {
        Menu menuE;
        d dVar = this.f5998o;
        if (dVar == null || (menuE = dVar.e()) == null) {
            return false;
        }
        menuE.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
        return menuE.performShortcut(i8, keyEvent, 0);
    }

    @Override // l.a
    public void r(boolean z7) {
        if (this.f5997n) {
            return;
        }
        E(z7);
    }

    @Override // l.a
    public void s(boolean z7) {
        p.h hVar;
        this.B = z7;
        if (z7 || (hVar = this.A) == null) {
            return;
        }
        hVar.a();
    }

    @Override // l.a
    public void t(CharSequence charSequence) {
        this.f5991h.setWindowTitle(charSequence);
    }

    @Override // l.a
    public p.b u(b.a aVar) {
        d dVar = this.f5998o;
        if (dVar != null) {
            dVar.c();
        }
        this.f5989f.setHideOnContentScrollEnabled(false);
        this.f5992i.k();
        d dVar2 = new d(this.f5992i.getContext(), aVar);
        if (!dVar2.t()) {
            return null;
        }
        this.f5998o = dVar2;
        dVar2.k();
        this.f5992i.h(dVar2);
        v(true);
        this.f5992i.sendAccessibilityEvent(32);
        return dVar2;
    }

    public void v(boolean z7) {
        x xVarU;
        x xVarF;
        if (z7) {
            L();
        } else {
            C();
        }
        if (!K()) {
            if (z7) {
                this.f5991h.j(4);
                this.f5992i.setVisibility(0);
                return;
            } else {
                this.f5991h.j(0);
                this.f5992i.setVisibility(8);
                return;
            }
        }
        if (z7) {
            xVarF = this.f5991h.u(4, 100L);
            xVarU = this.f5992i.f(0, 200L);
        } else {
            xVarU = this.f5991h.u(0, 200L);
            xVarF = this.f5992i.f(8, 100L);
        }
        p.h hVar = new p.h();
        hVar.d(xVarF, xVarU);
        hVar.h();
    }

    public void x() {
        b.a aVar = this.f6000q;
        if (aVar != null) {
            aVar.b(this.f5999p);
            this.f5999p = null;
            this.f6000q = null;
        }
    }

    public void y(boolean z7) {
        View view;
        p.h hVar = this.A;
        if (hVar != null) {
            hVar.a();
        }
        if (this.f6004u != 0 || (!this.B && !z7)) {
            this.D.b(null);
            return;
        }
        this.f5990g.setAlpha(1.0f);
        this.f5990g.setTransitioning(true);
        p.h hVar2 = new p.h();
        float f8 = -this.f5990g.getHeight();
        if (z7) {
            this.f5990g.getLocationInWindow(new int[]{0, 0});
            f8 -= r5[1];
        }
        x xVarK = t.c(this.f5990g).k(f8);
        xVarK.i(this.F);
        hVar2.c(xVarK);
        if (this.f6005v && (view = this.f5993j) != null) {
            hVar2.c(t.c(view).k(f8));
        }
        hVar2.f(f5984a);
        hVar2.e(250L);
        hVar2.g(this.D);
        this.A = hVar2;
        hVar2.h();
    }

    public void z(boolean z7) {
        View view;
        View view2;
        p.h hVar = this.A;
        if (hVar != null) {
            hVar.a();
        }
        this.f5990g.setVisibility(0);
        if (this.f6004u == 0 && (this.B || z7)) {
            this.f5990g.setTranslationY(0.0f);
            float f8 = -this.f5990g.getHeight();
            if (z7) {
                this.f5990g.getLocationInWindow(new int[]{0, 0});
                f8 -= r5[1];
            }
            this.f5990g.setTranslationY(f8);
            p.h hVar2 = new p.h();
            x xVarK = t.c(this.f5990g).k(0.0f);
            xVarK.i(this.F);
            hVar2.c(xVarK);
            if (this.f6005v && (view2 = this.f5993j) != null) {
                view2.setTranslationY(f8);
                hVar2.c(t.c(this.f5993j).k(0.0f));
            }
            hVar2.f(f5985b);
            hVar2.e(250L);
            hVar2.g(this.E);
            this.A = hVar2;
            hVar2.h();
        } else {
            this.f5990g.setAlpha(1.0f);
            this.f5990g.setTranslationY(0.0f);
            if (this.f6005v && (view = this.f5993j) != null) {
                view.setTranslationY(0.0f);
            }
            this.E.b(null);
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.f5989f;
        if (actionBarOverlayLayout != null) {
            t.i0(actionBarOverlayLayout);
        }
    }

    public l(Dialog dialog) {
        D(dialog.getWindow().getDecorView());
    }
}
