package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import androidx.appcompat.view.menu.g;
import androidx.appcompat.view.menu.m;
import androidx.appcompat.widget.Toolbar;

/* JADX INFO: compiled from: ToolbarWidgetWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class f0 implements p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Toolbar f593a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f594b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private View f595c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private View f596d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Drawable f597e;
    private Drawable f;
    private Drawable g;
    private boolean h;
    CharSequence i;
    private CharSequence j;
    private CharSequence k;
    Window.Callback l;
    boolean m;
    private ActionMenuPresenter n;
    private int o;
    private int p;
    private Drawable q;

    /* JADX INFO: compiled from: ToolbarWidgetWrapper.java */
    class a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final androidx.appcompat.view.menu.a f598b;

        a() {
            this.f598b = new androidx.appcompat.view.menu.a(f0.this.f593a.getContext(), 0, R.id.home, 0, 0, f0.this.i);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            f0 f0Var = f0.this;
            Window.Callback callback = f0Var.l;
            if (callback == null || !f0Var.m) {
                return;
            }
            callback.onMenuItemSelected(0, this.f598b);
        }
    }

    /* JADX INFO: compiled from: ToolbarWidgetWrapper.java */
    class b extends b.g.k.z {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f600a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f601b;

        b(int i) {
            this.f601b = i;
        }

        @Override // b.g.k.y
        public void a(View view) {
            if (this.f600a) {
                return;
            }
            f0.this.f593a.setVisibility(this.f601b);
        }

        @Override // b.g.k.z, b.g.k.y
        public void b(View view) {
            f0.this.f593a.setVisibility(0);
        }

        @Override // b.g.k.z, b.g.k.y
        public void c(View view) {
            this.f600a = true;
        }
    }

    public f0(Toolbar toolbar, boolean z) {
        this(toolbar, z, b.a.h.f1994a, b.a.e.n);
    }

    private void H(CharSequence charSequence) {
        this.i = charSequence;
        if ((this.f594b & 8) != 0) {
            this.f593a.setTitle(charSequence);
        }
    }

    private void I() {
        if ((this.f594b & 4) != 0) {
            if (TextUtils.isEmpty(this.k)) {
                this.f593a.setNavigationContentDescription(this.p);
            } else {
                this.f593a.setNavigationContentDescription(this.k);
            }
        }
    }

    private void J() {
        if ((this.f594b & 4) == 0) {
            this.f593a.setNavigationIcon((Drawable) null);
            return;
        }
        Toolbar toolbar = this.f593a;
        Drawable drawable = this.g;
        if (drawable == null) {
            drawable = this.q;
        }
        toolbar.setNavigationIcon(drawable);
    }

    private void K() {
        Drawable drawable;
        int i = this.f594b;
        if ((i & 2) == 0) {
            drawable = null;
        } else if ((i & 1) == 0 || (drawable = this.f) == null) {
            drawable = this.f597e;
        }
        this.f593a.setLogo(drawable);
    }

    private int y() {
        if (this.f593a.getNavigationIcon() == null) {
            return 11;
        }
        this.q = this.f593a.getNavigationIcon();
        return 15;
    }

    public void A(int i) {
        if (i == this.p) {
            return;
        }
        this.p = i;
        if (TextUtils.isEmpty(this.f593a.getNavigationContentDescription())) {
            C(this.p);
        }
    }

    public void B(Drawable drawable) {
        this.f = drawable;
        K();
    }

    public void C(int i) {
        D(i == 0 ? null : q().getString(i));
    }

    public void D(CharSequence charSequence) {
        this.k = charSequence;
        I();
    }

    public void E(Drawable drawable) {
        this.g = drawable;
        J();
    }

    public void F(CharSequence charSequence) {
        this.j = charSequence;
        if ((this.f594b & 8) != 0) {
            this.f593a.setSubtitle(charSequence);
        }
    }

    public void G(CharSequence charSequence) {
        this.h = true;
        H(charSequence);
    }

    @Override // androidx.appcompat.widget.p
    public void a(Menu menu, m.a aVar) {
        if (this.n == null) {
            ActionMenuPresenter actionMenuPresenter = new ActionMenuPresenter(this.f593a.getContext());
            this.n = actionMenuPresenter;
            actionMenuPresenter.t(b.a.f.g);
        }
        this.n.k(aVar);
        this.f593a.I((androidx.appcompat.view.menu.g) menu, this.n);
    }

    @Override // androidx.appcompat.widget.p
    public boolean b() {
        return this.f593a.z();
    }

    @Override // androidx.appcompat.widget.p
    public boolean c() {
        return this.f593a.A();
    }

    @Override // androidx.appcompat.widget.p
    public void collapseActionView() {
        this.f593a.e();
    }

    @Override // androidx.appcompat.widget.p
    public boolean d() {
        return this.f593a.w();
    }

    @Override // androidx.appcompat.widget.p
    public boolean e() {
        return this.f593a.O();
    }

    @Override // androidx.appcompat.widget.p
    public void f() {
        this.m = true;
    }

    @Override // androidx.appcompat.widget.p
    public boolean g() {
        return this.f593a.d();
    }

    @Override // androidx.appcompat.widget.p
    public CharSequence getTitle() {
        return this.f593a.getTitle();
    }

    @Override // androidx.appcompat.widget.p
    public void h() {
        this.f593a.f();
    }

    @Override // androidx.appcompat.widget.p
    public void i(m.a aVar, g.a aVar2) {
        this.f593a.J(aVar, aVar2);
    }

    @Override // androidx.appcompat.widget.p
    public int j() {
        return this.f594b;
    }

    @Override // androidx.appcompat.widget.p
    public void k(int i) {
        this.f593a.setVisibility(i);
    }

    @Override // androidx.appcompat.widget.p
    public Menu l() {
        return this.f593a.getMenu();
    }

    @Override // androidx.appcompat.widget.p
    public void m(int i) {
        B(i != 0 ? b.a.k.a.a.d(q(), i) : null);
    }

    @Override // androidx.appcompat.widget.p
    public void n(ScrollingTabContainerView scrollingTabContainerView) {
        View view = this.f595c;
        if (view != null) {
            ViewParent parent = view.getParent();
            Toolbar toolbar = this.f593a;
            if (parent == toolbar) {
                toolbar.removeView(this.f595c);
            }
        }
        this.f595c = scrollingTabContainerView;
        if (scrollingTabContainerView == null || this.o != 2) {
            return;
        }
        this.f593a.addView(scrollingTabContainerView, 0);
        Toolbar.e eVar = (Toolbar.e) this.f595c.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) eVar).width = -2;
        ((ViewGroup.MarginLayoutParams) eVar).height = -2;
        eVar.f250a = 8388691;
        scrollingTabContainerView.setAllowCollapse(true);
    }

    @Override // androidx.appcompat.widget.p
    public ViewGroup o() {
        return this.f593a;
    }

    @Override // androidx.appcompat.widget.p
    public void p(boolean z) {
    }

    @Override // androidx.appcompat.widget.p
    public Context q() {
        return this.f593a.getContext();
    }

    @Override // androidx.appcompat.widget.p
    public int r() {
        return this.o;
    }

    @Override // androidx.appcompat.widget.p
    public b.g.k.x s(int i, long j) {
        return b.g.k.t.c(this.f593a).a(i == 0 ? 1.0f : 0.0f).d(j).f(new b(i));
    }

    @Override // androidx.appcompat.widget.p
    public void setIcon(int i) {
        setIcon(i != 0 ? b.a.k.a.a.d(q(), i) : null);
    }

    @Override // androidx.appcompat.widget.p
    public void setWindowCallback(Window.Callback callback) {
        this.l = callback;
    }

    @Override // androidx.appcompat.widget.p
    public void setWindowTitle(CharSequence charSequence) {
        if (this.h) {
            return;
        }
        H(charSequence);
    }

    @Override // androidx.appcompat.widget.p
    public void t() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // androidx.appcompat.widget.p
    public boolean u() {
        return this.f593a.v();
    }

    @Override // androidx.appcompat.widget.p
    public void v() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // androidx.appcompat.widget.p
    public void w(boolean z) {
        this.f593a.setCollapsible(z);
    }

    @Override // androidx.appcompat.widget.p
    public void x(int i) {
        View view;
        int i2 = this.f594b ^ i;
        this.f594b = i;
        if (i2 != 0) {
            if ((i2 & 4) != 0) {
                if ((i & 4) != 0) {
                    I();
                }
                J();
            }
            if ((i2 & 3) != 0) {
                K();
            }
            if ((i2 & 8) != 0) {
                if ((i & 8) != 0) {
                    this.f593a.setTitle(this.i);
                    this.f593a.setSubtitle(this.j);
                } else {
                    this.f593a.setTitle((CharSequence) null);
                    this.f593a.setSubtitle((CharSequence) null);
                }
            }
            if ((i2 & 16) == 0 || (view = this.f596d) == null) {
                return;
            }
            if ((i & 16) != 0) {
                this.f593a.addView(view);
            } else {
                this.f593a.removeView(view);
            }
        }
    }

    public void z(View view) {
        View view2 = this.f596d;
        if (view2 != null && (this.f594b & 16) != 0) {
            this.f593a.removeView(view2);
        }
        this.f596d = view;
        if (view == null || (this.f594b & 16) == 0) {
            return;
        }
        this.f593a.addView(view);
    }

    public f0(Toolbar toolbar, boolean z, int i, int i2) {
        Drawable drawable;
        this.o = 0;
        this.p = 0;
        this.f593a = toolbar;
        this.i = toolbar.getTitle();
        this.j = toolbar.getSubtitle();
        this.h = this.i != null;
        this.g = toolbar.getNavigationIcon();
        e0 e0VarV = e0.v(toolbar.getContext(), null, b.a.j.f2004a, b.a.a.f1965c, 0);
        this.q = e0VarV.g(b.a.j.l);
        if (z) {
            CharSequence charSequenceP = e0VarV.p(b.a.j.r);
            if (!TextUtils.isEmpty(charSequenceP)) {
                G(charSequenceP);
            }
            CharSequence charSequenceP2 = e0VarV.p(b.a.j.p);
            if (!TextUtils.isEmpty(charSequenceP2)) {
                F(charSequenceP2);
            }
            Drawable drawableG = e0VarV.g(b.a.j.n);
            if (drawableG != null) {
                B(drawableG);
            }
            Drawable drawableG2 = e0VarV.g(b.a.j.m);
            if (drawableG2 != null) {
                setIcon(drawableG2);
            }
            if (this.g == null && (drawable = this.q) != null) {
                E(drawable);
            }
            x(e0VarV.k(b.a.j.h, 0));
            int iN = e0VarV.n(b.a.j.g, 0);
            if (iN != 0) {
                z(LayoutInflater.from(this.f593a.getContext()).inflate(iN, (ViewGroup) this.f593a, false));
                x(this.f594b | 16);
            }
            int iM = e0VarV.m(b.a.j.j, 0);
            if (iM > 0) {
                ViewGroup.LayoutParams layoutParams = this.f593a.getLayoutParams();
                layoutParams.height = iM;
                this.f593a.setLayoutParams(layoutParams);
            }
            int iE = e0VarV.e(b.a.j.f, -1);
            int iE2 = e0VarV.e(b.a.j.f2008e, -1);
            if (iE >= 0 || iE2 >= 0) {
                this.f593a.H(Math.max(iE, 0), Math.max(iE2, 0));
            }
            int iN2 = e0VarV.n(b.a.j.s, 0);
            if (iN2 != 0) {
                Toolbar toolbar2 = this.f593a;
                toolbar2.L(toolbar2.getContext(), iN2);
            }
            int iN3 = e0VarV.n(b.a.j.q, 0);
            if (iN3 != 0) {
                Toolbar toolbar3 = this.f593a;
                toolbar3.K(toolbar3.getContext(), iN3);
            }
            int iN4 = e0VarV.n(b.a.j.o, 0);
            if (iN4 != 0) {
                this.f593a.setPopupTheme(iN4);
            }
        } else {
            this.f594b = y();
        }
        e0VarV.w();
        A(i);
        this.k = this.f593a.getNavigationContentDescription();
        this.f593a.setNavigationOnClickListener(new a());
    }

    @Override // androidx.appcompat.widget.p
    public void setIcon(Drawable drawable) {
        this.f597e = drawable;
        K();
    }
}
