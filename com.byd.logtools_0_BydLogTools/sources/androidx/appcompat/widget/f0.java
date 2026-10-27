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
    Toolbar f601a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f602b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private View f603c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private View f604d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Drawable f605e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Drawable f606f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Drawable f607g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f608h;
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
        final androidx.appcompat.view.menu.a f609b;

        a() {
            this.f609b = new androidx.appcompat.view.menu.a(f0.this.f601a.getContext(), 0, R.id.home, 0, 0, f0.this.i);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            f0 f0Var = f0.this;
            Window.Callback callback = f0Var.l;
            if (callback == null || !f0Var.m) {
                return;
            }
            callback.onMenuItemSelected(0, this.f609b);
        }
    }

    /* JADX INFO: compiled from: ToolbarWidgetWrapper.java */
    class b extends b.g.k.z {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f611a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f612b;

        b(int i) {
            this.f612b = i;
        }

        @Override // b.g.k.y
        public void a(View view) {
            if (this.f611a) {
                return;
            }
            f0.this.f601a.setVisibility(this.f612b);
        }

        @Override // b.g.k.z, b.g.k.y
        public void b(View view) {
            f0.this.f601a.setVisibility(0);
        }

        @Override // b.g.k.z, b.g.k.y
        public void c(View view) {
            this.f611a = true;
        }
    }

    public f0(Toolbar toolbar, boolean z) {
        this(toolbar, z, b.a.h.f2107a, b.a.e.n);
    }

    private void G(CharSequence charSequence) {
        this.i = charSequence;
        if ((this.f602b & 8) != 0) {
            this.f601a.setTitle(charSequence);
        }
    }

    private void H() {
        if ((this.f602b & 4) != 0) {
            if (TextUtils.isEmpty(this.k)) {
                this.f601a.setNavigationContentDescription(this.p);
            } else {
                this.f601a.setNavigationContentDescription(this.k);
            }
        }
    }

    private void I() {
        if ((this.f602b & 4) == 0) {
            this.f601a.setNavigationIcon((Drawable) null);
            return;
        }
        Toolbar toolbar = this.f601a;
        Drawable drawable = this.f607g;
        if (drawable == null) {
            drawable = this.q;
        }
        toolbar.setNavigationIcon(drawable);
    }

    private void J() {
        Drawable drawable;
        int i = this.f602b;
        if ((i & 2) == 0) {
            drawable = null;
        } else if ((i & 1) == 0 || (drawable = this.f606f) == null) {
            drawable = this.f605e;
        }
        this.f601a.setLogo(drawable);
    }

    private int x() {
        if (this.f601a.getNavigationIcon() == null) {
            return 11;
        }
        this.q = this.f601a.getNavigationIcon();
        return 15;
    }

    public void A(Drawable drawable) {
        this.f606f = drawable;
        J();
    }

    public void B(int i) {
        C(i == 0 ? null : getContext().getString(i));
    }

    public void C(CharSequence charSequence) {
        this.k = charSequence;
        H();
    }

    public void D(Drawable drawable) {
        this.f607g = drawable;
        I();
    }

    public void E(CharSequence charSequence) {
        this.j = charSequence;
        if ((this.f602b & 8) != 0) {
            this.f601a.setSubtitle(charSequence);
        }
    }

    public void F(CharSequence charSequence) {
        this.f608h = true;
        G(charSequence);
    }

    @Override // androidx.appcompat.widget.p
    public void a(Menu menu, m.a aVar) {
        if (this.n == null) {
            ActionMenuPresenter actionMenuPresenter = new ActionMenuPresenter(this.f601a.getContext());
            this.n = actionMenuPresenter;
            actionMenuPresenter.t(b.a.f.f2097g);
        }
        this.n.k(aVar);
        this.f601a.I((androidx.appcompat.view.menu.g) menu, this.n);
    }

    @Override // androidx.appcompat.widget.p
    public boolean b() {
        return this.f601a.z();
    }

    @Override // androidx.appcompat.widget.p
    public boolean c() {
        return this.f601a.A();
    }

    @Override // androidx.appcompat.widget.p
    public void collapseActionView() {
        this.f601a.e();
    }

    @Override // androidx.appcompat.widget.p
    public boolean d() {
        return this.f601a.w();
    }

    @Override // androidx.appcompat.widget.p
    public boolean e() {
        return this.f601a.O();
    }

    @Override // androidx.appcompat.widget.p
    public void f() {
        this.m = true;
    }

    @Override // androidx.appcompat.widget.p
    public boolean g() {
        return this.f601a.d();
    }

    @Override // androidx.appcompat.widget.p
    public Context getContext() {
        return this.f601a.getContext();
    }

    @Override // androidx.appcompat.widget.p
    public CharSequence getTitle() {
        return this.f601a.getTitle();
    }

    @Override // androidx.appcompat.widget.p
    public void h() {
        this.f601a.f();
    }

    @Override // androidx.appcompat.widget.p
    public void i(m.a aVar, g.a aVar2) {
        this.f601a.J(aVar, aVar2);
    }

    @Override // androidx.appcompat.widget.p
    public int j() {
        return this.f602b;
    }

    @Override // androidx.appcompat.widget.p
    public void k(int i) {
        this.f601a.setVisibility(i);
    }

    @Override // androidx.appcompat.widget.p
    public Menu l() {
        return this.f601a.getMenu();
    }

    @Override // androidx.appcompat.widget.p
    public void m(int i) {
        A(i != 0 ? b.a.k.a.a.d(getContext(), i) : null);
    }

    @Override // androidx.appcompat.widget.p
    public void n(ScrollingTabContainerView scrollingTabContainerView) {
        View view = this.f603c;
        if (view != null) {
            ViewParent parent = view.getParent();
            Toolbar toolbar = this.f601a;
            if (parent == toolbar) {
                toolbar.removeView(this.f603c);
            }
        }
        this.f603c = scrollingTabContainerView;
        if (scrollingTabContainerView == null || this.o != 2) {
            return;
        }
        this.f601a.addView(scrollingTabContainerView, 0);
        Toolbar.e eVar = (Toolbar.e) this.f603c.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) eVar).width = -2;
        ((ViewGroup.MarginLayoutParams) eVar).height = -2;
        eVar.f163a = 8388691;
        scrollingTabContainerView.setAllowCollapse(true);
    }

    @Override // androidx.appcompat.widget.p
    public ViewGroup o() {
        return this.f601a;
    }

    @Override // androidx.appcompat.widget.p
    public void p(boolean z) {
    }

    @Override // androidx.appcompat.widget.p
    public int q() {
        return this.o;
    }

    @Override // androidx.appcompat.widget.p
    public b.g.k.x r(int i, long j) {
        return b.g.k.t.c(this.f601a).a(i == 0 ? 1.0f : 0.0f).d(j).f(new b(i));
    }

    @Override // androidx.appcompat.widget.p
    public void s() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // androidx.appcompat.widget.p
    public void setIcon(int i) {
        setIcon(i != 0 ? b.a.k.a.a.d(getContext(), i) : null);
    }

    @Override // androidx.appcompat.widget.p
    public void setWindowCallback(Window.Callback callback) {
        this.l = callback;
    }

    @Override // androidx.appcompat.widget.p
    public void setWindowTitle(CharSequence charSequence) {
        if (this.f608h) {
            return;
        }
        G(charSequence);
    }

    @Override // androidx.appcompat.widget.p
    public boolean t() {
        return this.f601a.v();
    }

    @Override // androidx.appcompat.widget.p
    public void u() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // androidx.appcompat.widget.p
    public void v(boolean z) {
        this.f601a.setCollapsible(z);
    }

    @Override // androidx.appcompat.widget.p
    public void w(int i) {
        View view;
        int i2 = this.f602b ^ i;
        this.f602b = i;
        if (i2 != 0) {
            if ((i2 & 4) != 0) {
                if ((i & 4) != 0) {
                    H();
                }
                I();
            }
            if ((i2 & 3) != 0) {
                J();
            }
            if ((i2 & 8) != 0) {
                if ((i & 8) != 0) {
                    this.f601a.setTitle(this.i);
                    this.f601a.setSubtitle(this.j);
                } else {
                    this.f601a.setTitle((CharSequence) null);
                    this.f601a.setSubtitle((CharSequence) null);
                }
            }
            if ((i2 & 16) == 0 || (view = this.f604d) == null) {
                return;
            }
            if ((i & 16) != 0) {
                this.f601a.addView(view);
            } else {
                this.f601a.removeView(view);
            }
        }
    }

    public void y(View view) {
        View view2 = this.f604d;
        if (view2 != null && (this.f602b & 16) != 0) {
            this.f601a.removeView(view2);
        }
        this.f604d = view;
        if (view == null || (this.f602b & 16) == 0) {
            return;
        }
        this.f601a.addView(view);
    }

    public void z(int i) {
        if (i == this.p) {
            return;
        }
        this.p = i;
        if (TextUtils.isEmpty(this.f601a.getNavigationContentDescription())) {
            B(this.p);
        }
    }

    public f0(Toolbar toolbar, boolean z, int i, int i2) {
        Drawable drawable;
        this.o = 0;
        this.p = 0;
        this.f601a = toolbar;
        this.i = toolbar.getTitle();
        this.j = toolbar.getSubtitle();
        this.f608h = this.i != null;
        this.f607g = toolbar.getNavigationIcon();
        e0 e0VarV = e0.v(toolbar.getContext(), null, b.a.j.f2120a, b.a.a.f2060c, 0);
        this.q = e0VarV.g(b.a.j.l);
        if (z) {
            CharSequence charSequenceP = e0VarV.p(b.a.j.r);
            if (!TextUtils.isEmpty(charSequenceP)) {
                F(charSequenceP);
            }
            CharSequence charSequenceP2 = e0VarV.p(b.a.j.p);
            if (!TextUtils.isEmpty(charSequenceP2)) {
                E(charSequenceP2);
            }
            Drawable drawableG = e0VarV.g(b.a.j.n);
            if (drawableG != null) {
                A(drawableG);
            }
            Drawable drawableG2 = e0VarV.g(b.a.j.m);
            if (drawableG2 != null) {
                setIcon(drawableG2);
            }
            if (this.f607g == null && (drawable = this.q) != null) {
                D(drawable);
            }
            w(e0VarV.k(b.a.j.f2127h, 0));
            int iN = e0VarV.n(b.a.j.f2126g, 0);
            if (iN != 0) {
                y(LayoutInflater.from(this.f601a.getContext()).inflate(iN, (ViewGroup) this.f601a, false));
                w(this.f602b | 16);
            }
            int iM = e0VarV.m(b.a.j.j, 0);
            if (iM > 0) {
                ViewGroup.LayoutParams layoutParams = this.f601a.getLayoutParams();
                layoutParams.height = iM;
                this.f601a.setLayoutParams(layoutParams);
            }
            int iE = e0VarV.e(b.a.j.f2125f, -1);
            int iE2 = e0VarV.e(b.a.j.f2124e, -1);
            if (iE >= 0 || iE2 >= 0) {
                this.f601a.H(Math.max(iE, 0), Math.max(iE2, 0));
            }
            int iN2 = e0VarV.n(b.a.j.s, 0);
            if (iN2 != 0) {
                Toolbar toolbar2 = this.f601a;
                toolbar2.L(toolbar2.getContext(), iN2);
            }
            int iN3 = e0VarV.n(b.a.j.q, 0);
            if (iN3 != 0) {
                Toolbar toolbar3 = this.f601a;
                toolbar3.K(toolbar3.getContext(), iN3);
            }
            int iN4 = e0VarV.n(b.a.j.o, 0);
            if (iN4 != 0) {
                this.f601a.setPopupTheme(iN4);
            }
        } else {
            this.f602b = x();
        }
        e0VarV.w();
        z(i);
        this.k = this.f601a.getNavigationContentDescription();
        this.f601a.setNavigationOnClickListener(new a());
    }

    @Override // androidx.appcompat.widget.p
    public void setIcon(Drawable drawable) {
        this.f605e = drawable;
        J();
    }
}
