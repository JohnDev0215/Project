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
public class g0 implements p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Toolbar f620a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f621b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private View f622c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private View f623d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Drawable f624e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Drawable f625f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Drawable f626g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f627h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    CharSequence f628i;
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
        final androidx.appcompat.view.menu.a f629b;

        a() {
            this.f629b = new androidx.appcompat.view.menu.a(g0.this.f620a.getContext(), 0, R.id.home, 0, 0, g0.this.f628i);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            g0 g0Var = g0.this;
            Window.Callback callback = g0Var.l;
            if (callback == null || !g0Var.m) {
                return;
            }
            callback.onMenuItemSelected(0, this.f629b);
        }
    }

    /* JADX INFO: compiled from: ToolbarWidgetWrapper.java */
    class b extends b.g.k.z {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f631a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f632b;

        b(int i2) {
            this.f632b = i2;
        }

        @Override // b.g.k.y
        public void a(View view) {
            if (this.f631a) {
                return;
            }
            g0.this.f620a.setVisibility(this.f632b);
        }

        @Override // b.g.k.z, b.g.k.y
        public void b(View view) {
            g0.this.f620a.setVisibility(0);
        }

        @Override // b.g.k.z, b.g.k.y
        public void c(View view) {
            this.f631a = true;
        }
    }

    public g0(Toolbar toolbar, boolean z) {
        this(toolbar, z, b.a.h.abc_action_bar_up_description, b.a.e.abc_ic_ab_back_material);
    }

    private void H(CharSequence charSequence) {
        this.f628i = charSequence;
        if ((this.f621b & 8) != 0) {
            this.f620a.setTitle(charSequence);
        }
    }

    private void I() {
        if ((this.f621b & 4) != 0) {
            if (TextUtils.isEmpty(this.k)) {
                this.f620a.setNavigationContentDescription(this.p);
            } else {
                this.f620a.setNavigationContentDescription(this.k);
            }
        }
    }

    private void J() {
        if ((this.f621b & 4) == 0) {
            this.f620a.setNavigationIcon((Drawable) null);
            return;
        }
        Toolbar toolbar = this.f620a;
        Drawable drawable = this.f626g;
        if (drawable == null) {
            drawable = this.q;
        }
        toolbar.setNavigationIcon(drawable);
    }

    private void K() {
        Drawable drawable;
        int i2 = this.f621b;
        if ((i2 & 2) == 0) {
            drawable = null;
        } else if ((i2 & 1) == 0 || (drawable = this.f625f) == null) {
            drawable = this.f624e;
        }
        this.f620a.setLogo(drawable);
    }

    private int y() {
        if (this.f620a.getNavigationIcon() == null) {
            return 11;
        }
        this.q = this.f620a.getNavigationIcon();
        return 15;
    }

    public void A(int i2) {
        if (i2 == this.p) {
            return;
        }
        this.p = i2;
        if (TextUtils.isEmpty(this.f620a.getNavigationContentDescription())) {
            C(this.p);
        }
    }

    public void B(Drawable drawable) {
        this.f625f = drawable;
        K();
    }

    public void C(int i2) {
        D(i2 == 0 ? null : a().getString(i2));
    }

    public void D(CharSequence charSequence) {
        this.k = charSequence;
        I();
    }

    public void E(Drawable drawable) {
        this.f626g = drawable;
        J();
    }

    public void F(CharSequence charSequence) {
        this.j = charSequence;
        if ((this.f621b & 8) != 0) {
            this.f620a.setSubtitle(charSequence);
        }
    }

    public void G(CharSequence charSequence) {
        this.f627h = true;
        H(charSequence);
    }

    @Override // androidx.appcompat.widget.p
    public Context a() {
        return this.f620a.getContext();
    }

    @Override // androidx.appcompat.widget.p
    public void b(Menu menu, m.a aVar) {
        if (this.n == null) {
            ActionMenuPresenter actionMenuPresenter = new ActionMenuPresenter(this.f620a.getContext());
            this.n = actionMenuPresenter;
            actionMenuPresenter.t(b.a.f.action_menu_presenter);
        }
        this.n.k(aVar);
        this.f620a.I((androidx.appcompat.view.menu.g) menu, this.n);
    }

    @Override // androidx.appcompat.widget.p
    public boolean c() {
        return this.f620a.z();
    }

    @Override // androidx.appcompat.widget.p
    public void collapseActionView() {
        this.f620a.e();
    }

    @Override // androidx.appcompat.widget.p
    public boolean d() {
        return this.f620a.A();
    }

    @Override // androidx.appcompat.widget.p
    public boolean e() {
        return this.f620a.w();
    }

    @Override // androidx.appcompat.widget.p
    public boolean f() {
        return this.f620a.O();
    }

    @Override // androidx.appcompat.widget.p
    public void g() {
        this.m = true;
    }

    @Override // androidx.appcompat.widget.p
    public CharSequence getTitle() {
        return this.f620a.getTitle();
    }

    @Override // androidx.appcompat.widget.p
    public boolean h() {
        return this.f620a.d();
    }

    @Override // androidx.appcompat.widget.p
    public void i() {
        this.f620a.f();
    }

    @Override // androidx.appcompat.widget.p
    public void j(m.a aVar, g.a aVar2) {
        this.f620a.J(aVar, aVar2);
    }

    @Override // androidx.appcompat.widget.p
    public int k() {
        return this.f621b;
    }

    @Override // androidx.appcompat.widget.p
    public void l(int i2) {
        this.f620a.setVisibility(i2);
    }

    @Override // androidx.appcompat.widget.p
    public Menu m() {
        return this.f620a.getMenu();
    }

    @Override // androidx.appcompat.widget.p
    public void n(int i2) {
        B(i2 != 0 ? b.a.k.a.a.d(a(), i2) : null);
    }

    @Override // androidx.appcompat.widget.p
    public void o(ScrollingTabContainerView scrollingTabContainerView) {
        View view = this.f622c;
        if (view != null) {
            ViewParent parent = view.getParent();
            Toolbar toolbar = this.f620a;
            if (parent == toolbar) {
                toolbar.removeView(this.f622c);
            }
        }
        this.f622c = scrollingTabContainerView;
        if (scrollingTabContainerView == null || this.o != 2) {
            return;
        }
        this.f620a.addView(scrollingTabContainerView, 0);
        Toolbar.e eVar = (Toolbar.e) this.f622c.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) eVar).width = -2;
        ((ViewGroup.MarginLayoutParams) eVar).height = -2;
        eVar.f166a = 8388691;
        scrollingTabContainerView.setAllowCollapse(true);
    }

    @Override // androidx.appcompat.widget.p
    public ViewGroup p() {
        return this.f620a;
    }

    @Override // androidx.appcompat.widget.p
    public void q(boolean z) {
    }

    @Override // androidx.appcompat.widget.p
    public int r() {
        return this.o;
    }

    @Override // androidx.appcompat.widget.p
    public b.g.k.x s(int i2, long j) {
        return b.g.k.t.c(this.f620a).a(i2 == 0 ? 1.0f : 0.0f).d(j).f(new b(i2));
    }

    @Override // androidx.appcompat.widget.p
    public void setIcon(int i2) {
        setIcon(i2 != 0 ? b.a.k.a.a.d(a(), i2) : null);
    }

    @Override // androidx.appcompat.widget.p
    public void setWindowCallback(Window.Callback callback) {
        this.l = callback;
    }

    @Override // androidx.appcompat.widget.p
    public void setWindowTitle(CharSequence charSequence) {
        if (this.f627h) {
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
        return this.f620a.v();
    }

    @Override // androidx.appcompat.widget.p
    public void v() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // androidx.appcompat.widget.p
    public void w(boolean z) {
        this.f620a.setCollapsible(z);
    }

    @Override // androidx.appcompat.widget.p
    public void x(int i2) {
        View view;
        int i3 = this.f621b ^ i2;
        this.f621b = i2;
        if (i3 != 0) {
            if ((i3 & 4) != 0) {
                if ((i2 & 4) != 0) {
                    I();
                }
                J();
            }
            if ((i3 & 3) != 0) {
                K();
            }
            if ((i3 & 8) != 0) {
                if ((i2 & 8) != 0) {
                    this.f620a.setTitle(this.f628i);
                    this.f620a.setSubtitle(this.j);
                } else {
                    this.f620a.setTitle((CharSequence) null);
                    this.f620a.setSubtitle((CharSequence) null);
                }
            }
            if ((i3 & 16) == 0 || (view = this.f623d) == null) {
                return;
            }
            if ((i2 & 16) != 0) {
                this.f620a.addView(view);
            } else {
                this.f620a.removeView(view);
            }
        }
    }

    public void z(View view) {
        View view2 = this.f623d;
        if (view2 != null && (this.f621b & 16) != 0) {
            this.f620a.removeView(view2);
        }
        this.f623d = view;
        if (view == null || (this.f621b & 16) == 0) {
            return;
        }
        this.f620a.addView(view);
    }

    public g0(Toolbar toolbar, boolean z, int i2, int i3) {
        Drawable drawable;
        this.o = 0;
        this.p = 0;
        this.f620a = toolbar;
        this.f628i = toolbar.getTitle();
        this.j = toolbar.getSubtitle();
        this.f627h = this.f628i != null;
        this.f626g = toolbar.getNavigationIcon();
        f0 f0VarV = f0.v(toolbar.getContext(), null, b.a.j.ActionBar, b.a.a.actionBarStyle, 0);
        this.q = f0VarV.g(b.a.j.ActionBar_homeAsUpIndicator);
        if (z) {
            CharSequence charSequenceP = f0VarV.p(b.a.j.ActionBar_title);
            if (!TextUtils.isEmpty(charSequenceP)) {
                G(charSequenceP);
            }
            CharSequence charSequenceP2 = f0VarV.p(b.a.j.ActionBar_subtitle);
            if (!TextUtils.isEmpty(charSequenceP2)) {
                F(charSequenceP2);
            }
            Drawable drawableG = f0VarV.g(b.a.j.ActionBar_logo);
            if (drawableG != null) {
                B(drawableG);
            }
            Drawable drawableG2 = f0VarV.g(b.a.j.ActionBar_icon);
            if (drawableG2 != null) {
                setIcon(drawableG2);
            }
            if (this.f626g == null && (drawable = this.q) != null) {
                E(drawable);
            }
            x(f0VarV.k(b.a.j.ActionBar_displayOptions, 0));
            int iN = f0VarV.n(b.a.j.ActionBar_customNavigationLayout, 0);
            if (iN != 0) {
                z(LayoutInflater.from(this.f620a.getContext()).inflate(iN, (ViewGroup) this.f620a, false));
                x(this.f621b | 16);
            }
            int iM = f0VarV.m(b.a.j.ActionBar_height, 0);
            if (iM > 0) {
                ViewGroup.LayoutParams layoutParams = this.f620a.getLayoutParams();
                layoutParams.height = iM;
                this.f620a.setLayoutParams(layoutParams);
            }
            int iE = f0VarV.e(b.a.j.ActionBar_contentInsetStart, -1);
            int iE2 = f0VarV.e(b.a.j.ActionBar_contentInsetEnd, -1);
            if (iE >= 0 || iE2 >= 0) {
                this.f620a.H(Math.max(iE, 0), Math.max(iE2, 0));
            }
            int iN2 = f0VarV.n(b.a.j.ActionBar_titleTextStyle, 0);
            if (iN2 != 0) {
                Toolbar toolbar2 = this.f620a;
                toolbar2.L(toolbar2.getContext(), iN2);
            }
            int iN3 = f0VarV.n(b.a.j.ActionBar_subtitleTextStyle, 0);
            if (iN3 != 0) {
                Toolbar toolbar3 = this.f620a;
                toolbar3.K(toolbar3.getContext(), iN3);
            }
            int iN4 = f0VarV.n(b.a.j.ActionBar_popupTheme, 0);
            if (iN4 != 0) {
                this.f620a.setPopupTheme(iN4);
            }
        } else {
            this.f621b = y();
        }
        f0VarV.w();
        A(i2);
        this.k = this.f620a.getNavigationContentDescription();
        this.f620a.setNavigationOnClickListener(new a());
    }

    @Override // androidx.appcompat.widget.p
    public void setIcon(Drawable drawable) {
        this.f624e = drawable;
        K();
    }
}
