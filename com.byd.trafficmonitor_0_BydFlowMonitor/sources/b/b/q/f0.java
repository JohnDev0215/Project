package b.b.q;

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
import androidx.appcompat.widget.ActionMenuPresenter;
import androidx.appcompat.widget.ScrollingTabContainerView;
import androidx.appcompat.widget.Toolbar;
import b.b.p.j.g;
import b.b.p.j.m;

/* JADX INFO: compiled from: ToolbarWidgetWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class f0 implements p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Toolbar f1733a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1734b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public View f1735c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public View f1736d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Drawable f1737e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Drawable f1738f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Drawable f1739g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1740h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public CharSequence f1741i;
    public CharSequence j;
    public CharSequence k;
    public Window.Callback l;
    public boolean m;
    public ActionMenuPresenter n;
    public int o;
    public int p;
    public Drawable q;

    /* JADX INFO: compiled from: ToolbarWidgetWrapper.java */
    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final b.b.p.j.a f1742b;

        public a() {
            this.f1742b = new b.b.p.j.a(f0.this.f1733a.getContext(), 0, R.id.home, 0, 0, f0.this.f1741i);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            f0 f0Var = f0.this;
            Window.Callback callback = f0Var.l;
            if (callback == null || !f0Var.m) {
                return;
            }
            callback.onMenuItemSelected(0, this.f1742b);
        }
    }

    /* JADX INFO: compiled from: ToolbarWidgetWrapper.java */
    public class b extends b.h.m.z {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1744a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f1745b;

        public b(int i2) {
            this.f1745b = i2;
        }

        @Override // b.h.m.y
        public void a(View view) {
            if (this.f1744a) {
                return;
            }
            f0.this.f1733a.setVisibility(this.f1745b);
        }

        @Override // b.h.m.z, b.h.m.y
        public void b(View view) {
            f0.this.f1733a.setVisibility(0);
        }

        @Override // b.h.m.z, b.h.m.y
        public void c(View view) {
            this.f1744a = true;
        }
    }

    public f0(Toolbar toolbar, boolean z) {
        this(toolbar, z, b.b.h.abc_action_bar_up_description, b.b.e.abc_ic_ab_back_material);
    }

    public void A(int i2) {
        if (i2 == this.p) {
            return;
        }
        this.p = i2;
        if (TextUtils.isEmpty(this.f1733a.getNavigationContentDescription())) {
            C(this.p);
        }
    }

    public void B(Drawable drawable) {
        this.f1738f = drawable;
        K();
    }

    public void C(int i2) {
        D(i2 == 0 ? null : q().getString(i2));
    }

    public void D(CharSequence charSequence) {
        this.k = charSequence;
        I();
    }

    public void E(Drawable drawable) {
        this.f1739g = drawable;
        J();
    }

    public void F(CharSequence charSequence) {
        this.j = charSequence;
        if ((this.f1734b & 8) != 0) {
            this.f1733a.setSubtitle(charSequence);
        }
    }

    public void G(CharSequence charSequence) {
        this.f1740h = true;
        H(charSequence);
    }

    public final void H(CharSequence charSequence) {
        this.f1741i = charSequence;
        if ((this.f1734b & 8) != 0) {
            this.f1733a.setTitle(charSequence);
        }
    }

    public final void I() {
        if ((this.f1734b & 4) != 0) {
            if (TextUtils.isEmpty(this.k)) {
                this.f1733a.setNavigationContentDescription(this.p);
            } else {
                this.f1733a.setNavigationContentDescription(this.k);
            }
        }
    }

    public final void J() {
        if ((this.f1734b & 4) == 0) {
            this.f1733a.setNavigationIcon((Drawable) null);
            return;
        }
        Toolbar toolbar = this.f1733a;
        Drawable drawable = this.f1739g;
        if (drawable == null) {
            drawable = this.q;
        }
        toolbar.setNavigationIcon(drawable);
    }

    public final void K() {
        Drawable drawable;
        int i2 = this.f1734b;
        if ((i2 & 2) == 0) {
            drawable = null;
        } else if ((i2 & 1) == 0 || (drawable = this.f1738f) == null) {
            drawable = this.f1737e;
        }
        this.f1733a.setLogo(drawable);
    }

    @Override // b.b.q.p
    public void a(Menu menu, m.a aVar) {
        if (this.n == null) {
            ActionMenuPresenter actionMenuPresenter = new ActionMenuPresenter(this.f1733a.getContext());
            this.n = actionMenuPresenter;
            actionMenuPresenter.t(b.b.f.action_menu_presenter);
        }
        this.n.k(aVar);
        this.f1733a.I((b.b.p.j.g) menu, this.n);
    }

    @Override // b.b.q.p
    public boolean b() {
        return this.f1733a.z();
    }

    @Override // b.b.q.p
    public boolean c() {
        return this.f1733a.A();
    }

    @Override // b.b.q.p
    public void collapseActionView() {
        this.f1733a.e();
    }

    @Override // b.b.q.p
    public boolean d() {
        return this.f1733a.w();
    }

    @Override // b.b.q.p
    public boolean e() {
        return this.f1733a.O();
    }

    @Override // b.b.q.p
    public void f() {
        this.m = true;
    }

    @Override // b.b.q.p
    public boolean g() {
        return this.f1733a.d();
    }

    @Override // b.b.q.p
    public CharSequence getTitle() {
        return this.f1733a.getTitle();
    }

    @Override // b.b.q.p
    public void h() {
        this.f1733a.f();
    }

    @Override // b.b.q.p
    public void i(m.a aVar, g.a aVar2) {
        this.f1733a.J(aVar, aVar2);
    }

    @Override // b.b.q.p
    public int j() {
        return this.f1734b;
    }

    @Override // b.b.q.p
    public void k(int i2) {
        this.f1733a.setVisibility(i2);
    }

    @Override // b.b.q.p
    public Menu l() {
        return this.f1733a.getMenu();
    }

    @Override // b.b.q.p
    public void m(int i2) {
        B(i2 != 0 ? b.b.l.a.a.d(q(), i2) : null);
    }

    @Override // b.b.q.p
    public void n(ScrollingTabContainerView scrollingTabContainerView) {
        View view = this.f1735c;
        if (view != null) {
            ViewParent parent = view.getParent();
            Toolbar toolbar = this.f1733a;
            if (parent == toolbar) {
                toolbar.removeView(this.f1735c);
            }
        }
        this.f1735c = scrollingTabContainerView;
        if (scrollingTabContainerView == null || this.o != 2) {
            return;
        }
        this.f1733a.addView(scrollingTabContainerView, 0);
        Toolbar.e eVar = (Toolbar.e) this.f1735c.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) eVar).width = -2;
        ((ViewGroup.MarginLayoutParams) eVar).height = -2;
        eVar.f1426a = 8388691;
        scrollingTabContainerView.setAllowCollapse(true);
    }

    @Override // b.b.q.p
    public ViewGroup o() {
        return this.f1733a;
    }

    @Override // b.b.q.p
    public void p(boolean z) {
    }

    @Override // b.b.q.p
    public Context q() {
        return this.f1733a.getContext();
    }

    @Override // b.b.q.p
    public int r() {
        return this.o;
    }

    @Override // b.b.q.p
    public b.h.m.x s(int i2, long j) {
        return b.h.m.t.c(this.f1733a).a(i2 == 0 ? 1.0f : 0.0f).d(j).f(new b(i2));
    }

    @Override // b.b.q.p
    public void setIcon(int i2) {
        setIcon(i2 != 0 ? b.b.l.a.a.d(q(), i2) : null);
    }

    @Override // b.b.q.p
    public void setWindowCallback(Window.Callback callback) {
        this.l = callback;
    }

    @Override // b.b.q.p
    public void setWindowTitle(CharSequence charSequence) {
        if (this.f1740h) {
            return;
        }
        H(charSequence);
    }

    @Override // b.b.q.p
    public void t() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // b.b.q.p
    public boolean u() {
        return this.f1733a.v();
    }

    @Override // b.b.q.p
    public void v() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // b.b.q.p
    public void w(boolean z) {
        this.f1733a.setCollapsible(z);
    }

    @Override // b.b.q.p
    public void x(int i2) {
        View view;
        int i3 = this.f1734b ^ i2;
        this.f1734b = i2;
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
                    this.f1733a.setTitle(this.f1741i);
                    this.f1733a.setSubtitle(this.j);
                } else {
                    this.f1733a.setTitle((CharSequence) null);
                    this.f1733a.setSubtitle((CharSequence) null);
                }
            }
            if ((i3 & 16) == 0 || (view = this.f1736d) == null) {
                return;
            }
            if ((i2 & 16) != 0) {
                this.f1733a.addView(view);
            } else {
                this.f1733a.removeView(view);
            }
        }
    }

    public final int y() {
        if (this.f1733a.getNavigationIcon() == null) {
            return 11;
        }
        this.q = this.f1733a.getNavigationIcon();
        return 15;
    }

    public void z(View view) {
        View view2 = this.f1736d;
        if (view2 != null && (this.f1734b & 16) != 0) {
            this.f1733a.removeView(view2);
        }
        this.f1736d = view;
        if (view == null || (this.f1734b & 16) == 0) {
            return;
        }
        this.f1733a.addView(view);
    }

    public f0(Toolbar toolbar, boolean z, int i2, int i3) {
        Drawable drawable;
        this.o = 0;
        this.p = 0;
        this.f1733a = toolbar;
        this.f1741i = toolbar.getTitle();
        this.j = toolbar.getSubtitle();
        this.f1740h = this.f1741i != null;
        this.f1739g = toolbar.getNavigationIcon();
        e0 e0VarV = e0.v(toolbar.getContext(), null, b.b.j.ActionBar, b.b.a.actionBarStyle, 0);
        this.q = e0VarV.g(b.b.j.ActionBar_homeAsUpIndicator);
        if (z) {
            CharSequence charSequenceP = e0VarV.p(b.b.j.ActionBar_title);
            if (!TextUtils.isEmpty(charSequenceP)) {
                G(charSequenceP);
            }
            CharSequence charSequenceP2 = e0VarV.p(b.b.j.ActionBar_subtitle);
            if (!TextUtils.isEmpty(charSequenceP2)) {
                F(charSequenceP2);
            }
            Drawable drawableG = e0VarV.g(b.b.j.ActionBar_logo);
            if (drawableG != null) {
                B(drawableG);
            }
            Drawable drawableG2 = e0VarV.g(b.b.j.ActionBar_icon);
            if (drawableG2 != null) {
                setIcon(drawableG2);
            }
            if (this.f1739g == null && (drawable = this.q) != null) {
                E(drawable);
            }
            x(e0VarV.k(b.b.j.ActionBar_displayOptions, 0));
            int iN = e0VarV.n(b.b.j.ActionBar_customNavigationLayout, 0);
            if (iN != 0) {
                z(LayoutInflater.from(this.f1733a.getContext()).inflate(iN, (ViewGroup) this.f1733a, false));
                x(this.f1734b | 16);
            }
            int iM = e0VarV.m(b.b.j.ActionBar_height, 0);
            if (iM > 0) {
                ViewGroup.LayoutParams layoutParams = this.f1733a.getLayoutParams();
                layoutParams.height = iM;
                this.f1733a.setLayoutParams(layoutParams);
            }
            int iE = e0VarV.e(b.b.j.ActionBar_contentInsetStart, -1);
            int iE2 = e0VarV.e(b.b.j.ActionBar_contentInsetEnd, -1);
            if (iE >= 0 || iE2 >= 0) {
                this.f1733a.H(Math.max(iE, 0), Math.max(iE2, 0));
            }
            int iN2 = e0VarV.n(b.b.j.ActionBar_titleTextStyle, 0);
            if (iN2 != 0) {
                Toolbar toolbar2 = this.f1733a;
                toolbar2.L(toolbar2.getContext(), iN2);
            }
            int iN3 = e0VarV.n(b.b.j.ActionBar_subtitleTextStyle, 0);
            if (iN3 != 0) {
                Toolbar toolbar3 = this.f1733a;
                toolbar3.K(toolbar3.getContext(), iN3);
            }
            int iN4 = e0VarV.n(b.b.j.ActionBar_popupTheme, 0);
            if (iN4 != 0) {
                this.f1733a.setPopupTheme(iN4);
            }
        } else {
            this.f1734b = y();
        }
        e0VarV.w();
        A(i2);
        this.k = this.f1733a.getNavigationContentDescription();
        this.f1733a.setNavigationOnClickListener(new a());
    }

    @Override // b.b.q.p
    public void setIcon(Drawable drawable) {
        this.f1737e = drawable;
        K();
    }
}
