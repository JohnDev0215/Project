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
import b.f.m.s;
import b.f.m.w;
import b.f.m.x;
import b.f.m.y;
import b.f.m.z;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: compiled from: WindowDecorActionBar.java */
/* JADX INFO: loaded from: classes.dex */
public class j extends b.b.k.a implements ActionBarOverlayLayout.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Interpolator f817a = new AccelerateInterpolator();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Interpolator f818b = new DecelerateInterpolator();
    public b.b.p.h A;
    public boolean B;
    public boolean C;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f819c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Context f820d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Activity f821e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ActionBarOverlayLayout f822f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ActionBarContainer f823g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public p f824h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ActionBarContextView f825i;
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
    public final x D = new a();
    public final x E = new b();
    public final z F = new c();

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    public class a extends y {
        public a() {
        }

        @Override // b.f.m.x
        public void a(View view) {
            View view2;
            j jVar = j.this;
            if (jVar.v && (view2 = jVar.j) != null) {
                view2.setTranslationY(0.0f);
                j.this.f823g.setTranslationY(0.0f);
            }
            j.this.f823g.setVisibility(8);
            j.this.f823g.setTransitioning(false);
            j jVar2 = j.this;
            jVar2.A = null;
            jVar2.y();
            ActionBarOverlayLayout actionBarOverlayLayout = j.this.f822f;
            if (actionBarOverlayLayout != null) {
                s.Q(actionBarOverlayLayout);
            }
        }
    }

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    public class b extends y {
        public b() {
        }

        @Override // b.f.m.x
        public void a(View view) {
            j jVar = j.this;
            jVar.A = null;
            jVar.f823g.requestLayout();
        }
    }

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    public class c implements z {
        public c() {
        }

        @Override // b.f.m.z
        public void a(View view) {
            ((View) j.this.f823g.getParent()).invalidate();
        }
    }

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    public class d extends b.b.p.b implements g.a {
        public WeakReference<View> E3;
        public final Context s;
        public final b.b.p.j.g x;
        public b.a y;

        public d(Context context, b.a aVar) {
            this.s = context;
            this.y = aVar;
            b.b.p.j.g gVarS = new b.b.p.j.g(context).S(1);
            this.x = gVarS;
            gVarS.R(this);
        }

        @Override // b.b.p.j.g.a
        public boolean a(b.b.p.j.g gVar, MenuItem menuItem) {
            b.a aVar = this.y;
            if (aVar != null) {
                return aVar.b(this, menuItem);
            }
            return false;
        }

        @Override // b.b.p.j.g.a
        public void b(b.b.p.j.g gVar) {
            if (this.y == null) {
                return;
            }
            k();
            j.this.f825i.l();
        }

        @Override // b.b.p.b
        public void c() {
            j jVar = j.this;
            if (jVar.o != this) {
                return;
            }
            if (j.x(jVar.w, jVar.x, false)) {
                this.y.d(this);
            } else {
                j jVar2 = j.this;
                jVar2.p = this;
                jVar2.q = this.y;
            }
            this.y = null;
            j.this.w(false);
            j.this.f825i.g();
            j.this.f824h.m().sendAccessibilityEvent(32);
            j jVar3 = j.this;
            jVar3.f822f.setHideOnContentScrollEnabled(jVar3.C);
            j.this.o = null;
        }

        @Override // b.b.p.b
        public View d() {
            WeakReference<View> weakReference = this.E3;
            if (weakReference != null) {
                return weakReference.get();
            }
            return null;
        }

        @Override // b.b.p.b
        public Menu e() {
            return this.x;
        }

        @Override // b.b.p.b
        public MenuInflater f() {
            return new b.b.p.g(this.s);
        }

        @Override // b.b.p.b
        public CharSequence g() {
            return j.this.f825i.getSubtitle();
        }

        @Override // b.b.p.b
        public CharSequence i() {
            return j.this.f825i.getTitle();
        }

        @Override // b.b.p.b
        public void k() {
            if (j.this.o != this) {
                return;
            }
            this.x.d0();
            try {
                this.y.a(this, this.x);
            } finally {
                this.x.c0();
            }
        }

        @Override // b.b.p.b
        public boolean l() {
            return j.this.f825i.j();
        }

        @Override // b.b.p.b
        public void m(View view) {
            j.this.f825i.setCustomView(view);
            this.E3 = new WeakReference<>(view);
        }

        @Override // b.b.p.b
        public void n(int i2) {
            o(j.this.f819c.getResources().getString(i2));
        }

        @Override // b.b.p.b
        public void o(CharSequence charSequence) {
            j.this.f825i.setSubtitle(charSequence);
        }

        @Override // b.b.p.b
        public void q(int i2) {
            r(j.this.f819c.getResources().getString(i2));
        }

        @Override // b.b.p.b
        public void r(CharSequence charSequence) {
            j.this.f825i.setTitle(charSequence);
        }

        @Override // b.b.p.b
        public void s(boolean z) {
            super.s(z);
            j.this.f825i.setTitleOptional(z);
        }

        public boolean t() {
            this.x.d0();
            try {
                return this.y.c(this, this.x);
            } finally {
                this.x.c0();
            }
        }
    }

    public j(Activity activity, boolean z) {
        this.f821e = activity;
        View decorView = activity.getWindow().getDecorView();
        E(decorView);
        if (z) {
            return;
        }
        this.j = decorView.findViewById(R.id.content);
    }

    public static boolean x(boolean z, boolean z2, boolean z3) {
        if (z3) {
            return true;
        }
        return (z || z2) ? false : true;
    }

    public void A(boolean z) {
        View view;
        View view2;
        b.b.p.h hVar = this.A;
        if (hVar != null) {
            hVar.a();
        }
        this.f823g.setVisibility(0);
        if (this.u == 0 && (this.B || z)) {
            this.f823g.setTranslationY(0.0f);
            float f2 = -this.f823g.getHeight();
            if (z) {
                this.f823g.getLocationInWindow(new int[]{0, 0});
                f2 -= r5[1];
            }
            this.f823g.setTranslationY(f2);
            b.b.p.h hVar2 = new b.b.p.h();
            w wVarK = s.b(this.f823g).k(0.0f);
            wVarK.i(this.F);
            hVar2.c(wVarK);
            if (this.v && (view2 = this.j) != null) {
                view2.setTranslationY(f2);
                hVar2.c(s.b(this.j).k(0.0f));
            }
            hVar2.f(f818b);
            hVar2.e(250L);
            hVar2.g(this.E);
            this.A = hVar2;
            hVar2.h();
        } else {
            this.f823g.setAlpha(1.0f);
            this.f823g.setTranslationY(0.0f);
            if (this.v && (view = this.j) != null) {
                view.setTranslationY(0.0f);
            }
            this.E.a(null);
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.f822f;
        if (actionBarOverlayLayout != null) {
            s.Q(actionBarOverlayLayout);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final p B(View view) {
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

    public int C() {
        return this.f824h.p();
    }

    public final void D() {
        if (this.y) {
            this.y = false;
            ActionBarOverlayLayout actionBarOverlayLayout = this.f822f;
            if (actionBarOverlayLayout != null) {
                actionBarOverlayLayout.setShowingForActionMode(false);
            }
            N(false);
        }
    }

    public final void E(View view) {
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) view.findViewById(b.b.f.q);
        this.f822f = actionBarOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        this.f824h = B(view.findViewById(b.b.f.f740a));
        this.f825i = (ActionBarContextView) view.findViewById(b.b.f.f745f);
        ActionBarContainer actionBarContainer = (ActionBarContainer) view.findViewById(b.b.f.f742c);
        this.f823g = actionBarContainer;
        p pVar = this.f824h;
        if (pVar == null || this.f825i == null || actionBarContainer == null) {
            throw new IllegalStateException(j.class.getSimpleName() + " can only be used with a compatible window decor layout");
        }
        this.f819c = pVar.o();
        boolean z = (this.f824h.i() & 4) != 0;
        if (z) {
            this.n = true;
        }
        b.b.p.a aVarB = b.b.p.a.b(this.f819c);
        K(aVarB.a() || z);
        I(aVarB.g());
        TypedArray typedArrayObtainStyledAttributes = this.f819c.obtainStyledAttributes(null, b.b.j.f771a, b.b.a.f706c, 0);
        if (typedArrayObtainStyledAttributes.getBoolean(b.b.j.k, false)) {
            J(true);
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.b.j.f779i, 0);
        if (dimensionPixelSize != 0) {
            H(dimensionPixelSize);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public void F(boolean z) {
        G(z ? 4 : 0, 4);
    }

    public void G(int i2, int i3) {
        int i4 = this.f824h.i();
        if ((i3 & 4) != 0) {
            this.n = true;
        }
        this.f824h.v((i2 & i3) | ((~i3) & i4));
    }

    public void H(float f2) {
        s.X(this.f823g, f2);
    }

    public final void I(boolean z) {
        this.t = z;
        if (z) {
            this.f823g.setTabContainer(null);
            this.f824h.l(this.k);
        } else {
            this.f824h.l(null);
            this.f823g.setTabContainer(this.k);
        }
        boolean z2 = C() == 2;
        ScrollingTabContainerView scrollingTabContainerView = this.k;
        if (scrollingTabContainerView != null) {
            if (z2) {
                scrollingTabContainerView.setVisibility(0);
                ActionBarOverlayLayout actionBarOverlayLayout = this.f822f;
                if (actionBarOverlayLayout != null) {
                    s.Q(actionBarOverlayLayout);
                }
            } else {
                scrollingTabContainerView.setVisibility(8);
            }
        }
        this.f824h.u(!this.t && z2);
        this.f822f.setHasNonEmbeddedTabs(!this.t && z2);
    }

    public void J(boolean z) {
        if (z && !this.f822f.w()) {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
        }
        this.C = z;
        this.f822f.setHideOnContentScrollEnabled(z);
    }

    public void K(boolean z) {
        this.f824h.n(z);
    }

    public final boolean L() {
        return s.G(this.f823g);
    }

    public final void M() {
        if (this.y) {
            return;
        }
        this.y = true;
        ActionBarOverlayLayout actionBarOverlayLayout = this.f822f;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setShowingForActionMode(true);
        }
        N(false);
    }

    public final void N(boolean z) {
        if (x(this.w, this.x, this.y)) {
            if (this.z) {
                return;
            }
            this.z = true;
            A(z);
            return;
        }
        if (this.z) {
            this.z = false;
            z(z);
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
            N(true);
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
        N(true);
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void f() {
    }

    @Override // b.b.k.a
    public boolean h() {
        p pVar = this.f824h;
        if (pVar == null || !pVar.s()) {
            return false;
        }
        this.f824h.collapseActionView();
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
        return this.f824h.i();
    }

    @Override // b.b.k.a
    public Context k() {
        if (this.f820d == null) {
            TypedValue typedValue = new TypedValue();
            this.f819c.getTheme().resolveAttribute(b.b.a.f711h, typedValue, true);
            int i2 = typedValue.resourceId;
            if (i2 != 0) {
                this.f820d = new ContextThemeWrapper(this.f819c, i2);
            } else {
                this.f820d = this.f819c;
            }
        }
        return this.f820d;
    }

    @Override // b.b.k.a
    public void l() {
        if (this.w) {
            return;
        }
        this.w = true;
        N(false);
    }

    @Override // b.b.k.a
    public void n(Configuration configuration) {
        I(b.b.p.a.b(this.f819c).g());
    }

    @Override // b.b.k.a
    public boolean p(int i2, KeyEvent keyEvent) {
        Menu menuE;
        d dVar = this.o;
        if (dVar == null || (menuE = dVar.e()) == null) {
            return false;
        }
        menuE.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
        return menuE.performShortcut(i2, keyEvent, 0);
    }

    @Override // b.b.k.a
    public void s(boolean z) {
        if (this.n) {
            return;
        }
        F(z);
    }

    @Override // b.b.k.a
    public void t(boolean z) {
        b.b.p.h hVar;
        this.B = z;
        if (z || (hVar = this.A) == null) {
            return;
        }
        hVar.a();
    }

    @Override // b.b.k.a
    public void u(CharSequence charSequence) {
        this.f824h.setWindowTitle(charSequence);
    }

    @Override // b.b.k.a
    public b.b.p.b v(b.a aVar) {
        d dVar = this.o;
        if (dVar != null) {
            dVar.c();
        }
        this.f822f.setHideOnContentScrollEnabled(false);
        this.f825i.k();
        d dVar2 = new d(this.f825i.getContext(), aVar);
        if (!dVar2.t()) {
            return null;
        }
        this.o = dVar2;
        dVar2.k();
        this.f825i.h(dVar2);
        w(true);
        this.f825i.sendAccessibilityEvent(32);
        return dVar2;
    }

    public void w(boolean z) {
        w wVarQ;
        w wVarF;
        if (z) {
            M();
        } else {
            D();
        }
        if (!L()) {
            if (z) {
                this.f824h.j(4);
                this.f825i.setVisibility(0);
                return;
            } else {
                this.f824h.j(0);
                this.f825i.setVisibility(8);
                return;
            }
        }
        if (z) {
            wVarF = this.f824h.q(4, 100L);
            wVarQ = this.f825i.f(0, 200L);
        } else {
            wVarQ = this.f824h.q(0, 200L);
            wVarF = this.f825i.f(8, 100L);
        }
        b.b.p.h hVar = new b.b.p.h();
        hVar.d(wVarF, wVarQ);
        hVar.h();
    }

    public void y() {
        b.a aVar = this.q;
        if (aVar != null) {
            aVar.d(this.p);
            this.p = null;
            this.q = null;
        }
    }

    public void z(boolean z) {
        View view;
        b.b.p.h hVar = this.A;
        if (hVar != null) {
            hVar.a();
        }
        if (this.u != 0 || (!this.B && !z)) {
            this.D.a(null);
            return;
        }
        this.f823g.setAlpha(1.0f);
        this.f823g.setTransitioning(true);
        b.b.p.h hVar2 = new b.b.p.h();
        float f2 = -this.f823g.getHeight();
        if (z) {
            this.f823g.getLocationInWindow(new int[]{0, 0});
            f2 -= r5[1];
        }
        w wVarK = s.b(this.f823g).k(f2);
        wVarK.i(this.F);
        hVar2.c(wVarK);
        if (this.v && (view = this.j) != null) {
            hVar2.c(s.b(view).k(f2));
        }
        hVar2.f(f817a);
        hVar2.e(250L);
        hVar2.g(this.D);
        this.A = hVar2;
        hVar2.h();
    }

    public j(Dialog dialog) {
        E(dialog.getWindow().getDecorView());
    }
}
