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
    Toolbar f958a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f959b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private View f960c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private View f961d;
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
        final androidx.appcompat.view.menu.a f962b;

        a() {
            this.f962b = new androidx.appcompat.view.menu.a(f0.this.f958a.getContext(), 0, R.id.home, 0, 0, f0.this.i);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            f0 f0Var = f0.this;
            Window.Callback callback = f0Var.l;
            if (callback == null || !f0Var.m) {
                return;
            }
            callback.onMenuItemSelected(0, this.f962b);
        }
    }

    /* JADX INFO: compiled from: ToolbarWidgetWrapper.java */
    class b extends c.g.k.z {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f964a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f965b;

        b(int i) {
            this.f965b = i;
        }

        @Override // c.g.k.z, c.g.k.y
        public void a(View view) {
            this.f964a = true;
        }

        @Override // c.g.k.y
        public void b(View view) {
            if (this.f964a) {
                return;
            }
            f0.this.f958a.setVisibility(this.f965b);
        }

        @Override // c.g.k.z, c.g.k.y
        public void c(View view) {
            f0.this.f958a.setVisibility(0);
        }
    }

    public f0(Toolbar toolbar, boolean z) {
        this(toolbar, z, c.a.h.f1990a, c.a.e.n);
    }

    private void F(CharSequence charSequence) {
        this.i = charSequence;
        if ((this.f959b & 8) != 0) {
            this.f958a.setTitle(charSequence);
        }
    }

    private void G() {
        if ((this.f959b & 4) != 0) {
            if (TextUtils.isEmpty(this.k)) {
                this.f958a.setNavigationContentDescription(this.p);
            } else {
                this.f958a.setNavigationContentDescription(this.k);
            }
        }
    }

    private void H() {
        if ((this.f959b & 4) == 0) {
            this.f958a.setNavigationIcon((Drawable) null);
            return;
        }
        Toolbar toolbar = this.f958a;
        Drawable drawable = this.g;
        if (drawable == null) {
            drawable = this.q;
        }
        toolbar.setNavigationIcon(drawable);
    }

    private void I() {
        Drawable drawable;
        int i = this.f959b;
        if ((i & 2) == 0) {
            drawable = null;
        } else if ((i & 1) == 0 || (drawable = this.f) == null) {
            drawable = this.e;
        }
        this.f958a.setLogo(drawable);
    }

    private int w() {
        if (this.f958a.getNavigationIcon() == null) {
            return 11;
        }
        this.q = this.f958a.getNavigationIcon();
        return 15;
    }

    public void A(int i) {
        B(i == 0 ? null : m().getString(i));
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
        if ((this.f959b & 8) != 0) {
            this.f958a.setSubtitle(charSequence);
        }
    }

    public void E(CharSequence charSequence) {
        this.h = true;
        F(charSequence);
    }

    @Override // androidx.appcompat.widget.p
    public void a(Menu menu, m.a aVar) {
        if (this.n == null) {
            ActionMenuPresenter actionMenuPresenter = new ActionMenuPresenter(this.f958a.getContext());
            this.n = actionMenuPresenter;
            actionMenuPresenter.t(c.a.f.g);
        }
        this.n.n(aVar);
        this.f958a.I((androidx.appcompat.view.menu.g) menu, this.n);
    }

    @Override // androidx.appcompat.widget.p
    public boolean b() {
        return this.f958a.A();
    }

    @Override // androidx.appcompat.widget.p
    public void c() {
        this.m = true;
    }

    @Override // androidx.appcompat.widget.p
    public void collapseActionView() {
        this.f958a.e();
    }

    @Override // androidx.appcompat.widget.p
    public boolean d() {
        return this.f958a.z();
    }

    @Override // androidx.appcompat.widget.p
    public boolean e() {
        return this.f958a.w();
    }

    @Override // androidx.appcompat.widget.p
    public boolean f() {
        return this.f958a.N();
    }

    @Override // androidx.appcompat.widget.p
    public boolean g() {
        return this.f958a.d();
    }

    @Override // androidx.appcompat.widget.p
    public CharSequence getTitle() {
        return this.f958a.getTitle();
    }

    @Override // androidx.appcompat.widget.p
    public void h() {
        this.f958a.f();
    }

    @Override // androidx.appcompat.widget.p
    public void i(int i) {
        this.f958a.setVisibility(i);
    }

    @Override // androidx.appcompat.widget.p
    public void j(ScrollingTabContainerView scrollingTabContainerView) {
        View view = this.f960c;
        if (view != null) {
            ViewParent parent = view.getParent();
            Toolbar toolbar = this.f958a;
            if (parent == toolbar) {
                toolbar.removeView(this.f960c);
            }
        }
        this.f960c = scrollingTabContainerView;
        if (scrollingTabContainerView == null || this.o != 2) {
            return;
        }
        this.f958a.addView(scrollingTabContainerView, 0);
        Toolbar.e eVar = (Toolbar.e) this.f960c.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) eVar).width = -2;
        ((ViewGroup.MarginLayoutParams) eVar).height = -2;
        eVar.f670a = 8388691;
        scrollingTabContainerView.setAllowCollapse(true);
    }

    @Override // androidx.appcompat.widget.p
    public ViewGroup k() {
        return this.f958a;
    }

    @Override // androidx.appcompat.widget.p
    public void l(boolean z) {
    }

    @Override // androidx.appcompat.widget.p
    public Context m() {
        return this.f958a.getContext();
    }

    @Override // androidx.appcompat.widget.p
    public boolean n() {
        return this.f958a.v();
    }

    @Override // androidx.appcompat.widget.p
    public void o(int i) {
        View view;
        int i2 = this.f959b ^ i;
        this.f959b = i;
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
                    this.f958a.setTitle(this.i);
                    this.f958a.setSubtitle(this.j);
                } else {
                    this.f958a.setTitle((CharSequence) null);
                    this.f958a.setSubtitle((CharSequence) null);
                }
            }
            if ((i2 & 16) == 0 || (view = this.f961d) == null) {
                return;
            }
            if ((i & 16) != 0) {
                this.f958a.addView(view);
            } else {
                this.f958a.removeView(view);
            }
        }
    }

    @Override // androidx.appcompat.widget.p
    public int p() {
        return this.f959b;
    }

    @Override // androidx.appcompat.widget.p
    public void q(int i) {
        z(i != 0 ? c.a.k.a.a.d(m(), i) : null);
    }

    @Override // androidx.appcompat.widget.p
    public int r() {
        return this.o;
    }

    @Override // androidx.appcompat.widget.p
    public c.g.k.x s(int i, long j) {
        return c.g.k.t.c(this.f958a).a(i == 0 ? 1.0f : 0.0f).d(j).f(new b(i));
    }

    @Override // androidx.appcompat.widget.p
    public void setIcon(int i) {
        setIcon(i != 0 ? c.a.k.a.a.d(m(), i) : null);
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
    public void u() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // androidx.appcompat.widget.p
    public void v(boolean z) {
        this.f958a.setCollapsible(z);
    }

    public void x(View view) {
        View view2 = this.f961d;
        if (view2 != null && (this.f959b & 16) != 0) {
            this.f958a.removeView(view2);
        }
        this.f961d = view;
        if (view == null || (this.f959b & 16) == 0) {
            return;
        }
        this.f958a.addView(view);
    }

    public void y(int i) {
        if (i == this.p) {
            return;
        }
        this.p = i;
        if (TextUtils.isEmpty(this.f958a.getNavigationContentDescription())) {
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
        this.f958a = toolbar;
        this.i = toolbar.getTitle();
        this.j = toolbar.getSubtitle();
        this.h = this.i != null;
        this.g = toolbar.getNavigationIcon();
        e0 e0VarV = e0.v(toolbar.getContext(), null, c.a.j.f1998a, c.a.a.f1967c, 0);
        this.q = e0VarV.g(c.a.j.l);
        if (z) {
            CharSequence charSequenceP = e0VarV.p(c.a.j.r);
            if (!TextUtils.isEmpty(charSequenceP)) {
                E(charSequenceP);
            }
            CharSequence charSequenceP2 = e0VarV.p(c.a.j.p);
            if (!TextUtils.isEmpty(charSequenceP2)) {
                D(charSequenceP2);
            }
            Drawable drawableG = e0VarV.g(c.a.j.n);
            if (drawableG != null) {
                z(drawableG);
            }
            Drawable drawableG2 = e0VarV.g(c.a.j.m);
            if (drawableG2 != null) {
                setIcon(drawableG2);
            }
            if (this.g == null && (drawable = this.q) != null) {
                C(drawable);
            }
            o(e0VarV.k(c.a.j.h, 0));
            int iN = e0VarV.n(c.a.j.g, 0);
            if (iN != 0) {
                x(LayoutInflater.from(this.f958a.getContext()).inflate(iN, (ViewGroup) this.f958a, false));
                o(this.f959b | 16);
            }
            int iM = e0VarV.m(c.a.j.j, 0);
            if (iM > 0) {
                ViewGroup.LayoutParams layoutParams = this.f958a.getLayoutParams();
                layoutParams.height = iM;
                this.f958a.setLayoutParams(layoutParams);
            }
            int iE = e0VarV.e(c.a.j.f, -1);
            int iE2 = e0VarV.e(c.a.j.e, -1);
            if (iE >= 0 || iE2 >= 0) {
                this.f958a.H(Math.max(iE, 0), Math.max(iE2, 0));
            }
            int iN2 = e0VarV.n(c.a.j.s, 0);
            if (iN2 != 0) {
                Toolbar toolbar2 = this.f958a;
                toolbar2.K(toolbar2.getContext(), iN2);
            }
            int iN3 = e0VarV.n(c.a.j.q, 0);
            if (iN3 != 0) {
                Toolbar toolbar3 = this.f958a;
                toolbar3.J(toolbar3.getContext(), iN3);
            }
            int iN4 = e0VarV.n(c.a.j.o, 0);
            if (iN4 != 0) {
                this.f958a.setPopupTheme(iN4);
            }
        } else {
            this.f959b = w();
        }
        e0VarV.w();
        y(i);
        this.k = this.f958a.getNavigationContentDescription();
        this.f958a.setNavigationOnClickListener(new a());
    }

    @Override // androidx.appcompat.widget.p
    public void setIcon(Drawable drawable) {
        this.e = drawable;
        I();
    }
}
