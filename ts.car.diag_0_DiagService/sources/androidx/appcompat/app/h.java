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
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$id;
import androidx.appcompat.R$styleable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.c.b;
import androidx.appcompat.view.menu.f;
import androidx.appcompat.widget.ActionBarContainer;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.B;
import androidx.appcompat.widget.M;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.core.view.v;
import androidx.core.view.w;
import androidx.core.view.x;
import androidx.core.view.y;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class h extends ActionBar implements ActionBarOverlayLayout.d {
    private static final Interpolator B = new AccelerateInterpolator();
    private static final Interpolator C = new DecelerateInterpolator();
    final y A;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Context f158a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f159b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ActionBarOverlayLayout f160c;
    ActionBarContainer d;
    B e;
    ActionBarContextView f;
    View g;
    M h;
    private boolean i;
    d j;
    androidx.appcompat.c.b k;
    b.a l;
    private boolean m;
    private ArrayList<ActionBar.b> n;
    private boolean o;
    private int p;
    boolean q;
    boolean r;
    boolean s;
    private boolean t;
    private boolean u;
    androidx.appcompat.c.h v;
    private boolean w;
    boolean x;
    final w y;
    final w z;

    /* JADX INFO: compiled from: caradapter-bug */
    class a extends x {
        a() {
        }

        @Override // androidx.core.view.w
        public void a(View view) {
            View view2;
            h hVar = h.this;
            if (hVar.q && (view2 = hVar.g) != null) {
                view2.setTranslationY(0.0f);
                h.this.d.setTranslationY(0.0f);
            }
            h.this.d.setVisibility(8);
            h.this.d.e(false);
            h hVar2 = h.this;
            hVar2.v = null;
            hVar2.x();
            ActionBarOverlayLayout actionBarOverlayLayout = h.this.f160c;
            if (actionBarOverlayLayout != null) {
                ViewCompat.J(actionBarOverlayLayout);
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b extends x {
        b() {
        }

        @Override // androidx.core.view.w
        public void a(View view) {
            h hVar = h.this;
            hVar.v = null;
            hVar.d.requestLayout();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class c implements y {
        c() {
        }

        @Override // androidx.core.view.y
        public void a(View view) {
            ((View) h.this.d.getParent()).invalidate();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public class d extends androidx.appcompat.c.b implements f.a {
        private final Context e;
        private final androidx.appcompat.view.menu.f f;
        private b.a g;
        private WeakReference<View> h;

        public d(Context context, b.a aVar) {
            this.e = context;
            this.g = aVar;
            androidx.appcompat.view.menu.f fVar = new androidx.appcompat.view.menu.f(context);
            fVar.S(1);
            this.f = fVar;
            fVar.R(this);
        }

        @Override // androidx.appcompat.view.menu.f.a
        public boolean a(@NonNull androidx.appcompat.view.menu.f fVar, @NonNull MenuItem menuItem) {
            b.a aVar = this.g;
            if (aVar != null) {
                return aVar.b(this, menuItem);
            }
            return false;
        }

        @Override // androidx.appcompat.view.menu.f.a
        public void b(@NonNull androidx.appcompat.view.menu.f fVar) {
            if (this.g == null) {
                return;
            }
            k();
            h.this.f.s();
        }

        @Override // androidx.appcompat.c.b
        public void c() {
            h hVar = h.this;
            if (hVar.j != this) {
                return;
            }
            if (h.w(hVar.r, hVar.s, false)) {
                this.g.d(this);
            } else {
                h hVar2 = h.this;
                hVar2.k = this;
                hVar2.l = this.g;
            }
            this.g = null;
            h.this.v(false);
            h.this.f.h();
            h.this.e.n().sendAccessibilityEvent(32);
            h hVar3 = h.this;
            hVar3.f160c.H(hVar3.x);
            h.this.j = null;
        }

        @Override // androidx.appcompat.c.b
        public View d() {
            WeakReference<View> weakReference = this.h;
            if (weakReference != null) {
                return weakReference.get();
            }
            return null;
        }

        @Override // androidx.appcompat.c.b
        public Menu e() {
            return this.f;
        }

        @Override // androidx.appcompat.c.b
        public MenuInflater f() {
            return new androidx.appcompat.c.g(this.e);
        }

        @Override // androidx.appcompat.c.b
        public CharSequence g() {
            return h.this.f.i();
        }

        @Override // androidx.appcompat.c.b
        public CharSequence i() {
            return h.this.f.j();
        }

        @Override // androidx.appcompat.c.b
        public void k() {
            if (h.this.j != this) {
                return;
            }
            this.f.d0();
            try {
                this.g.a(this, this.f);
            } finally {
                this.f.c0();
            }
        }

        @Override // androidx.appcompat.c.b
        public boolean l() {
            return h.this.f.m();
        }

        @Override // androidx.appcompat.c.b
        public void m(View view) {
            h.this.f.o(view);
            this.h = new WeakReference<>(view);
        }

        @Override // androidx.appcompat.c.b
        public void n(int i) {
            o(h.this.f158a.getResources().getString(i));
        }

        @Override // androidx.appcompat.c.b
        public void o(CharSequence charSequence) {
            h.this.f.p(charSequence);
        }

        @Override // androidx.appcompat.c.b
        public void q(int i) {
            r(h.this.f158a.getResources().getString(i));
        }

        @Override // androidx.appcompat.c.b
        public void r(CharSequence charSequence) {
            h.this.f.q(charSequence);
        }

        @Override // androidx.appcompat.c.b
        public void s(boolean z) {
            super.s(z);
            h.this.f.r(z);
        }

        public boolean t() {
            this.f.d0();
            try {
                return this.g.c(this, this.f);
            } finally {
                this.f.c0();
            }
        }
    }

    public h(Activity activity, boolean z) {
        new ArrayList();
        this.n = new ArrayList<>();
        this.p = 0;
        this.q = true;
        this.u = true;
        this.y = new a();
        this.z = new b();
        this.A = new c();
        View decorView = activity.getWindow().getDecorView();
        D(decorView);
        if (z) {
            return;
        }
        this.g = decorView.findViewById(R.id.content);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private B A(View view) {
        if (view instanceof B) {
            return (B) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).H();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Can't make a decor toolbar out of ");
        sb.append(view != 0 ? view.getClass().getSimpleName() : "null");
        throw new IllegalStateException(sb.toString());
    }

    private void C() {
        if (this.t) {
            this.t = false;
            ActionBarOverlayLayout actionBarOverlayLayout = this.f160c;
            if (actionBarOverlayLayout != null) {
                actionBarOverlayLayout.J(false);
            }
            M(false);
        }
    }

    private void D(View view) {
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) view.findViewById(R$id.decor_content_parent);
        this.f160c = actionBarOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.F(this);
        }
        this.e = A(view.findViewById(R$id.action_bar));
        this.f = (ActionBarContextView) view.findViewById(R$id.action_context_bar);
        ActionBarContainer actionBarContainer = (ActionBarContainer) view.findViewById(R$id.action_bar_container);
        this.d = actionBarContainer;
        B b2 = this.e;
        if (b2 == null || this.f == null || actionBarContainer == null) {
            throw new IllegalStateException(h.class.getSimpleName() + " can only be used with a compatible window decor layout");
        }
        this.f158a = b2.getContext();
        boolean z = (this.e.k() & 4) != 0;
        if (z) {
            this.i = true;
        }
        androidx.appcompat.c.a aVarB = androidx.appcompat.c.a.b(this.f158a);
        J(aVarB.a() || z);
        H(aVarB.e());
        TypedArray typedArrayObtainStyledAttributes = this.f158a.obtainStyledAttributes(null, R$styleable.ActionBar, R$attr.actionBarStyle, 0);
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.ActionBar_hideOnContentScroll, false)) {
            I(true);
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ActionBar_elevation, 0);
        if (dimensionPixelSize != 0) {
            G(dimensionPixelSize);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    private void H(boolean z) {
        this.o = z;
        if (z) {
            this.d.d(null);
            this.e.m(this.h);
        } else {
            this.e.m(null);
            this.d.d(this.h);
        }
        boolean z2 = B() == 2;
        M m = this.h;
        if (m != null) {
            if (z2) {
                m.setVisibility(0);
                ActionBarOverlayLayout actionBarOverlayLayout = this.f160c;
                if (actionBarOverlayLayout != null) {
                    ViewCompat.J(actionBarOverlayLayout);
                }
            } else {
                m.setVisibility(8);
            }
        }
        this.e.u(!this.o && z2);
        this.f160c.G(!this.o && z2);
    }

    private boolean K() {
        return ViewCompat.B(this.d);
    }

    private void L() {
        if (this.t) {
            return;
        }
        this.t = true;
        ActionBarOverlayLayout actionBarOverlayLayout = this.f160c;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.J(true);
        }
        M(false);
    }

    private void M(boolean z) {
        if (w(this.r, this.s, this.t)) {
            if (this.u) {
                return;
            }
            this.u = true;
            z(z);
            return;
        }
        if (this.u) {
            this.u = false;
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
        return this.e.p();
    }

    public void E(boolean z) {
        F(z ? 4 : 0, 4);
    }

    public void F(int i, int i2) {
        int iK = this.e.k();
        if ((i2 & 4) != 0) {
            this.i = true;
        }
        this.e.v((i & i2) | ((~i2) & iK));
    }

    public void G(float f) {
        ViewCompat.Q(this.d, f);
    }

    public void I(boolean z) {
        if (z && !this.f160c.z()) {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
        }
        this.x = z;
        this.f160c.H(z);
    }

    public void J(boolean z) {
        this.e.o(z);
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void a(boolean z) {
        this.q = z;
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void b() {
        if (this.s) {
            this.s = false;
            M(true);
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void c() {
        androidx.appcompat.c.h hVar = this.v;
        if (hVar != null) {
            hVar.a();
            this.v = null;
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void d(int i) {
        this.p = i;
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void e() {
        if (this.s) {
            return;
        }
        this.s = true;
        M(true);
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void f() {
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean h() {
        B b2 = this.e;
        if (b2 == null || !b2.s()) {
            return false;
        }
        this.e.collapseActionView();
        return true;
    }

    @Override // androidx.appcompat.app.ActionBar
    public void i(boolean z) {
        if (z == this.m) {
            return;
        }
        this.m = z;
        int size = this.n.size();
        for (int i = 0; i < size; i++) {
            this.n.get(i).a(z);
        }
    }

    @Override // androidx.appcompat.app.ActionBar
    public int j() {
        return this.e.k();
    }

    @Override // androidx.appcompat.app.ActionBar
    public Context k() {
        if (this.f159b == null) {
            TypedValue typedValue = new TypedValue();
            this.f158a.getTheme().resolveAttribute(R$attr.actionBarWidgetTheme, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                this.f159b = new ContextThemeWrapper(this.f158a, i);
            } else {
                this.f159b = this.f158a;
            }
        }
        return this.f159b;
    }

    @Override // androidx.appcompat.app.ActionBar
    public void m(Configuration configuration) {
        H(androidx.appcompat.c.a.b(this.f158a).e());
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean o(int i, KeyEvent keyEvent) {
        Menu menuE;
        d dVar = this.j;
        if (dVar == null || (menuE = dVar.e()) == null) {
            return false;
        }
        menuE.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
        return menuE.performShortcut(i, keyEvent, 0);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void r(boolean z) {
        if (this.i) {
            return;
        }
        E(z);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void s(boolean z) {
        androidx.appcompat.c.h hVar;
        this.w = z;
        if (z || (hVar = this.v) == null) {
            return;
        }
        hVar.a();
    }

    @Override // androidx.appcompat.app.ActionBar
    public void t(CharSequence charSequence) {
        this.e.c(charSequence);
    }

    @Override // androidx.appcompat.app.ActionBar
    public androidx.appcompat.c.b u(b.a aVar) {
        d dVar = this.j;
        if (dVar != null) {
            dVar.c();
        }
        this.f160c.H(false);
        this.f.n();
        d dVar2 = new d(this.f.getContext(), aVar);
        if (!dVar2.t()) {
            return null;
        }
        this.j = dVar2;
        dVar2.k();
        this.f.k(dVar2);
        v(true);
        this.f.sendAccessibilityEvent(32);
        return dVar2;
    }

    public void v(boolean z) {
        v vVarG;
        v vVarG2;
        if (z) {
            L();
        } else {
            C();
        }
        if (!K()) {
            if (z) {
                this.e.l(4);
                this.f.setVisibility(0);
                return;
            } else {
                this.e.l(0);
                this.f.setVisibility(8);
                return;
            }
        }
        if (z) {
            vVarG = this.e.q(4, 100L);
            vVarG2 = this.f.g(0, 200L);
        } else {
            v vVarQ = this.e.q(0, 200L);
            vVarG = this.f.g(8, 100L);
            vVarG2 = vVarQ;
        }
        androidx.appcompat.c.h hVar = new androidx.appcompat.c.h();
        hVar.d(vVarG, vVarG2);
        hVar.h();
    }

    void x() {
        b.a aVar = this.l;
        if (aVar != null) {
            aVar.d(this.k);
            this.k = null;
            this.l = null;
        }
    }

    public void y(boolean z) {
        View view;
        androidx.appcompat.c.h hVar = this.v;
        if (hVar != null) {
            hVar.a();
        }
        if (this.p != 0 || (!this.w && !z)) {
            this.y.a(null);
            return;
        }
        this.d.setAlpha(1.0f);
        this.d.e(true);
        androidx.appcompat.c.h hVar2 = new androidx.appcompat.c.h();
        float f = -this.d.getHeight();
        if (z) {
            this.d.getLocationInWindow(new int[]{0, 0});
            f -= r5[1];
        }
        v vVarB = ViewCompat.b(this.d);
        vVarB.k(f);
        vVarB.i(this.A);
        hVar2.c(vVarB);
        if (this.q && (view = this.g) != null) {
            v vVarB2 = ViewCompat.b(view);
            vVarB2.k(f);
            hVar2.c(vVarB2);
        }
        hVar2.f(B);
        hVar2.e(250L);
        hVar2.g(this.y);
        this.v = hVar2;
        hVar2.h();
    }

    public void z(boolean z) {
        View view;
        View view2;
        androidx.appcompat.c.h hVar = this.v;
        if (hVar != null) {
            hVar.a();
        }
        this.d.setVisibility(0);
        if (this.p == 0 && (this.w || z)) {
            this.d.setTranslationY(0.0f);
            float f = -this.d.getHeight();
            if (z) {
                this.d.getLocationInWindow(new int[]{0, 0});
                f -= r5[1];
            }
            this.d.setTranslationY(f);
            androidx.appcompat.c.h hVar2 = new androidx.appcompat.c.h();
            v vVarB = ViewCompat.b(this.d);
            vVarB.k(0.0f);
            vVarB.i(this.A);
            hVar2.c(vVarB);
            if (this.q && (view2 = this.g) != null) {
                view2.setTranslationY(f);
                v vVarB2 = ViewCompat.b(this.g);
                vVarB2.k(0.0f);
                hVar2.c(vVarB2);
            }
            hVar2.f(C);
            hVar2.e(250L);
            hVar2.g(this.z);
            this.v = hVar2;
            hVar2.h();
        } else {
            this.d.setAlpha(1.0f);
            this.d.setTranslationY(0.0f);
            if (this.q && (view = this.g) != null) {
                view.setTranslationY(0.0f);
            }
            this.z.a(null);
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.f160c;
        if (actionBarOverlayLayout != null) {
            ViewCompat.J(actionBarOverlayLayout);
        }
    }

    public h(Dialog dialog) {
        new ArrayList();
        this.n = new ArrayList<>();
        this.p = 0;
        this.q = true;
        this.u = true;
        this.y = new a();
        this.z = new b();
        this.A = new c();
        D(dialog.getWindow().getDecorView());
    }
}
