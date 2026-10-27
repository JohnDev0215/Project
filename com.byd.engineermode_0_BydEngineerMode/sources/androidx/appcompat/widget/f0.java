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
import androidx.appcompat.view.menu.m;
import androidx.appcompat.widget.Toolbar;

/* JADX INFO: compiled from: ToolbarWidgetWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class f0 implements p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Toolbar f429a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f430b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private View f431c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private View f432d;
    private Drawable e;
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
        final androidx.appcompat.view.menu.a f433b;

        a() {
            this.f433b = new androidx.appcompat.view.menu.a(f0.this.f429a.getContext(), 0, R.id.home, 0, 0, f0.this.i);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            f0 f0Var = f0.this;
            Window.Callback callback = f0Var.l;
            if (callback == null || !f0Var.m) {
                return;
            }
            callback.onMenuItemSelected(0, this.f433b);
        }
    }

    /* JADX INFO: compiled from: ToolbarWidgetWrapper.java */
    class b extends b.g.k.z {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f435a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f436b;

        b(int i) {
            this.f436b = i;
        }

        @Override // b.g.k.y
        public void a(View view) {
            if (this.f435a) {
                return;
            }
            f0.this.f429a.setVisibility(this.f436b);
        }

        @Override // b.g.k.z, b.g.k.y
        public void b(View view) {
            f0.this.f429a.setVisibility(0);
        }

        @Override // b.g.k.z, b.g.k.y
        public void c(View view) {
            this.f435a = true;
        }
    }

    public f0(Toolbar toolbar, boolean z) {
        this(toolbar, z, b.a.h.f1503a, b.a.e.n);
    }

    private void F(CharSequence charSequence) {
        this.i = charSequence;
        if ((this.f430b & 8) != 0) {
            this.f429a.setTitle(charSequence);
        }
    }

    private void G() {
        if ((this.f430b & 4) != 0) {
            if (TextUtils.isEmpty(this.k)) {
                this.f429a.setNavigationContentDescription(this.p);
            } else {
                this.f429a.setNavigationContentDescription(this.k);
            }
        }
    }

    private void H() {
        if ((this.f430b & 4) == 0) {
            this.f429a.setNavigationIcon((Drawable) null);
            return;
        }
        Toolbar toolbar = this.f429a;
        Drawable drawable = this.g;
        if (drawable == null) {
            drawable = this.q;
        }
        toolbar.setNavigationIcon(drawable);
    }

    private void I() {
        Drawable drawable;
        int i = this.f430b;
        if ((i & 2) == 0) {
            drawable = null;
        } else if ((i & 1) == 0 || (drawable = this.f) == null) {
            drawable = this.e;
        }
        this.f429a.setLogo(drawable);
    }

    private int w() {
        if (this.f429a.getNavigationIcon() == null) {
            return 11;
        }
        this.q = this.f429a.getNavigationIcon();
        return 15;
    }

    public void A(int i) {
        B(i == 0 ? null : o().getString(i));
    }

    public void B(CharSequence charSequence) {
        this.k = charSequence;
        G();
    }

    public void C(Drawable drawable) {
        this.g = drawable;
        H();
    }

    public void D(CharSequence charSequence) {
        this.j = charSequence;
        if ((this.f430b & 8) != 0) {
            this.f429a.setSubtitle(charSequence);
        }
    }

    public void E(CharSequence charSequence) {
        this.h = true;
        F(charSequence);
    }

    @Override // androidx.appcompat.widget.p
    public void a(Menu menu, m.a aVar) {
        if (this.n == null) {
            ActionMenuPresenter actionMenuPresenter = new ActionMenuPresenter(this.f429a.getContext());
            this.n = actionMenuPresenter;
            actionMenuPresenter.t(b.a.f.g);
        }
        this.n.k(aVar);
        this.f429a.I((androidx.appcompat.view.menu.g) menu, this.n);
    }

    @Override // androidx.appcompat.widget.p
    public boolean b() {
        return this.f429a.z();
    }

    @Override // androidx.appcompat.widget.p
    public boolean c() {
        return this.f429a.A();
    }

    @Override // androidx.appcompat.widget.p
    public void collapseActionView() {
        this.f429a.e();
    }

    @Override // androidx.appcompat.widget.p
    public boolean d() {
        return this.f429a.w();
    }

    @Override // androidx.appcompat.widget.p
    public boolean e() {
        return this.f429a.N();
    }

    @Override // androidx.appcompat.widget.p
    public void f() {
        this.m = true;
    }

    @Override // androidx.appcompat.widget.p
    public boolean g() {
        return this.f429a.d();
    }

    @Override // androidx.appcompat.widget.p
    public CharSequence getTitle() {
        return this.f429a.getTitle();
    }

    @Override // androidx.appcompat.widget.p
    public void h() {
        this.f429a.f();
    }

    @Override // androidx.appcompat.widget.p
    public int i() {
        return this.f430b;
    }

    @Override // androidx.appcompat.widget.p
    public void j(int i) {
        this.f429a.setVisibility(i);
    }

    @Override // androidx.appcompat.widget.p
    public void k(int i) {
        z(i != 0 ? b.a.k.a.a.d(o(), i) : null);
    }

    @Override // androidx.appcompat.widget.p
    public void l(ScrollingTabContainerView scrollingTabContainerView) {
        View view = this.f431c;
        if (view != null) {
            ViewParent parent = view.getParent();
            Toolbar toolbar = this.f429a;
            if (parent == toolbar) {
                toolbar.removeView(this.f431c);
            }
        }
        this.f431c = scrollingTabContainerView;
        if (scrollingTabContainerView == null || this.o != 2) {
            return;
        }
        this.f429a.addView(scrollingTabContainerView, 0);
        Toolbar.e eVar = (Toolbar.e) this.f431c.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) eVar).width = -2;
        ((ViewGroup.MarginLayoutParams) eVar).height = -2;
        eVar.f141a = 8388691;
        scrollingTabContainerView.setAllowCollapse(true);
    }

    @Override // androidx.appcompat.widget.p
    public ViewGroup m() {
        return this.f429a;
    }

    @Override // androidx.appcompat.widget.p
    public void n(boolean z) {
    }

    @Override // androidx.appcompat.widget.p
    public Context o() {
        return this.f429a.getContext();
    }

    @Override // androidx.appcompat.widget.p
    public int p() {
        return this.o;
    }

    @Override // androidx.appcompat.widget.p
    public b.g.k.x q(int i, long j) {
        return b.g.k.t.c(this.f429a).a(i == 0 ? 1.0f : 0.0f).d(j).f(new b(i));
    }

    @Override // androidx.appcompat.widget.p
    public void r() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // androidx.appcompat.widget.p
    public boolean s() {
        return this.f429a.v();
    }

    @Override // androidx.appcompat.widget.p
    public void setIcon(int i) {
        setIcon(i != 0 ? b.a.k.a.a.d(o(), i) : null);
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
        F(charSequence);
    }

    @Override // androidx.appcompat.widget.p
    public void t() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // androidx.appcompat.widget.p
    public void u(boolean z) {
        this.f429a.setCollapsible(z);
    }

    @Override // androidx.appcompat.widget.p
    public void v(int i) {
        View view;
        int i2 = this.f430b ^ i;
        this.f430b = i;
        if (i2 != 0) {
            if ((i2 & 4) != 0) {
                if ((i & 4) != 0) {
                    G();
                }
                H();
            }
            if ((i2 & 3) != 0) {
                I();
            }
            if ((i2 & 8) != 0) {
                if ((i & 8) != 0) {
                    this.f429a.setTitle(this.i);
                    this.f429a.setSubtitle(this.j);
                } else {
                    this.f429a.setTitle((CharSequence) null);
                    this.f429a.setSubtitle((CharSequence) null);
                }
            }
            if ((i2 & 16) == 0 || (view = this.f432d) == null) {
                return;
            }
            if ((i & 16) != 0) {
                this.f429a.addView(view);
            } else {
                this.f429a.removeView(view);
            }
        }
    }

    public void x(View view) {
        View view2 = this.f432d;
        if (view2 != null && (this.f430b & 16) != 0) {
            this.f429a.removeView(view2);
        }
        this.f432d = view;
        if (view == null || (this.f430b & 16) == 0) {
            return;
        }
        this.f429a.addView(view);
    }

    public void y(int i) {
        if (i == this.p) {
            return;
        }
        this.p = i;
        if (TextUtils.isEmpty(this.f429a.getNavigationContentDescription())) {
            A(this.p);
        }
    }

    public void z(Drawable drawable) {
        this.f = drawable;
        I();
    }

    public f0(Toolbar toolbar, boolean z, int i, int i2) {
        Drawable drawable;
        this.o = 0;
        this.p = 0;
        this.f429a = toolbar;
        this.i = toolbar.getTitle();
        this.j = toolbar.getSubtitle();
        this.h = this.i != null;
        this.g = toolbar.getNavigationIcon();
        e0 e0VarV = e0.v(toolbar.getContext(), null, b.a.j.f1511a, b.a.a.f1480c, 0);
        this.q = e0VarV.g(b.a.j.l);
        if (z) {
            CharSequence charSequenceP = e0VarV.p(b.a.j.r);
            if (!TextUtils.isEmpty(charSequenceP)) {
                E(charSequenceP);
            }
            CharSequence charSequenceP2 = e0VarV.p(b.a.j.p);
            if (!TextUtils.isEmpty(charSequenceP2)) {
                D(charSequenceP2);
            }
            Drawable drawableG = e0VarV.g(b.a.j.n);
            if (drawableG != null) {
                z(drawableG);
            }
            Drawable drawableG2 = e0VarV.g(b.a.j.m);
            if (drawableG2 != null) {
                setIcon(drawableG2);
            }
            if (this.g == null && (drawable = this.q) != null) {
                C(drawable);
            }
            v(e0VarV.k(b.a.j.h, 0));
            int iN = e0VarV.n(b.a.j.g, 0);
            if (iN != 0) {
                x(LayoutInflater.from(this.f429a.getContext()).inflate(iN, (ViewGroup) this.f429a, false));
                v(this.f430b | 16);
            }
            int iM = e0VarV.m(b.a.j.j, 0);
            if (iM > 0) {
                ViewGroup.LayoutParams layoutParams = this.f429a.getLayoutParams();
                layoutParams.height = iM;
                this.f429a.setLayoutParams(layoutParams);
            }
            int iE = e0VarV.e(b.a.j.f, -1);
            int iE2 = e0VarV.e(b.a.j.e, -1);
            if (iE >= 0 || iE2 >= 0) {
                this.f429a.H(Math.max(iE, 0), Math.max(iE2, 0));
            }
            int iN2 = e0VarV.n(b.a.j.s, 0);
            if (iN2 != 0) {
                Toolbar toolbar2 = this.f429a;
                toolbar2.K(toolbar2.getContext(), iN2);
            }
            int iN3 = e0VarV.n(b.a.j.q, 0);
            if (iN3 != 0) {
                Toolbar toolbar3 = this.f429a;
                toolbar3.J(toolbar3.getContext(), iN3);
            }
            int iN4 = e0VarV.n(b.a.j.o, 0);
            if (iN4 != 0) {
                this.f429a.setPopupTheme(iN4);
            }
        } else {
            this.f430b = w();
        }
        e0VarV.w();
        y(i);
        this.k = this.f429a.getNavigationContentDescription();
        this.f429a.setNavigationOnClickListener(new a());
    }

    @Override // androidx.appcompat.widget.p
    public void setIcon(Drawable drawable) {
        this.e = drawable;
        I();
    }
}
