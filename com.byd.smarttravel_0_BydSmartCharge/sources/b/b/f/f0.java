package b.b.f;

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
import androidx.appcompat.R$attr;
import androidx.appcompat.R$drawable;
import androidx.appcompat.R$id;
import androidx.appcompat.R$string;
import androidx.appcompat.R$styleable;
import androidx.appcompat.widget.ActionMenuPresenter;
import androidx.appcompat.widget.ScrollingTabContainerView;
import androidx.appcompat.widget.Toolbar;
import b.b.e.j.g;
import b.b.e.j.m;

/* JADX INFO: compiled from: ToolbarWidgetWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class f0 implements p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Toolbar f771a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f772b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public View f773c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public View f774d;
    public Drawable e;
    public Drawable f;
    public Drawable g;
    public boolean h;
    public CharSequence i;
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
        public final b.b.e.j.a f775b;

        public a() {
            this.f775b = new b.b.e.j.a(f0.this.f771a.getContext(), 0, R.id.home, 0, 0, f0.this.i);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            f0 f0Var = f0.this;
            Window.Callback callback = f0Var.l;
            if (callback == null || !f0Var.m) {
                return;
            }
            callback.onMenuItemSelected(0, this.f775b);
        }
    }

    /* JADX INFO: compiled from: ToolbarWidgetWrapper.java */
    public class b extends b.g.j.z {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f777a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f778b;

        public b(int i) {
            this.f778b = i;
        }

        @Override // b.g.j.y
        public void a(View view) {
            if (this.f777a) {
                return;
            }
            f0.this.f771a.setVisibility(this.f778b);
        }

        @Override // b.g.j.z, b.g.j.y
        public void b(View view) {
            f0.this.f771a.setVisibility(0);
        }

        @Override // b.g.j.z, b.g.j.y
        public void c(View view) {
            this.f777a = true;
        }
    }

    public f0(Toolbar toolbar, boolean z) {
        this(toolbar, z, R$string.abc_action_bar_up_description, R$drawable.abc_ic_ab_back_material);
    }

    public void A(int i) {
        if (i == this.p) {
            return;
        }
        this.p = i;
        if (TextUtils.isEmpty(this.f771a.getNavigationContentDescription())) {
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
        if ((this.f772b & 8) != 0) {
            this.f771a.setSubtitle(charSequence);
        }
    }

    public void G(CharSequence charSequence) {
        this.h = true;
        H(charSequence);
    }

    public final void H(CharSequence charSequence) {
        this.i = charSequence;
        if ((this.f772b & 8) != 0) {
            this.f771a.setTitle(charSequence);
        }
    }

    public final void I() {
        if ((this.f772b & 4) != 0) {
            if (TextUtils.isEmpty(this.k)) {
                this.f771a.setNavigationContentDescription(this.p);
            } else {
                this.f771a.setNavigationContentDescription(this.k);
            }
        }
    }

    public final void J() {
        if ((this.f772b & 4) == 0) {
            this.f771a.setNavigationIcon((Drawable) null);
            return;
        }
        Toolbar toolbar = this.f771a;
        Drawable drawable = this.g;
        if (drawable == null) {
            drawable = this.q;
        }
        toolbar.setNavigationIcon(drawable);
    }

    public final void K() {
        Drawable drawable;
        int i = this.f772b;
        if ((i & 2) == 0) {
            drawable = null;
        } else if ((i & 1) == 0 || (drawable = this.f) == null) {
            drawable = this.e;
        }
        this.f771a.setLogo(drawable);
    }

    @Override // b.b.f.p
    public void a(Menu menu, m.a aVar) {
        if (this.n == null) {
            ActionMenuPresenter actionMenuPresenter = new ActionMenuPresenter(this.f771a.getContext());
            this.n = actionMenuPresenter;
            actionMenuPresenter.t(R$id.action_menu_presenter);
        }
        this.n.k(aVar);
        this.f771a.I((b.b.e.j.g) menu, this.n);
    }

    @Override // b.b.f.p
    public boolean b() {
        return this.f771a.z();
    }

    @Override // b.b.f.p
    public boolean c() {
        return this.f771a.A();
    }

    @Override // b.b.f.p
    public void collapseActionView() {
        this.f771a.e();
    }

    @Override // b.b.f.p
    public boolean d() {
        return this.f771a.w();
    }

    @Override // b.b.f.p
    public boolean e() {
        return this.f771a.O();
    }

    @Override // b.b.f.p
    public void f() {
        this.m = true;
    }

    @Override // b.b.f.p
    public boolean g() {
        return this.f771a.d();
    }

    @Override // b.b.f.p
    public CharSequence getTitle() {
        return this.f771a.getTitle();
    }

    @Override // b.b.f.p
    public void h() {
        this.f771a.f();
    }

    @Override // b.b.f.p
    public void i(m.a aVar, g.a aVar2) {
        this.f771a.J(aVar, aVar2);
    }

    @Override // b.b.f.p
    public int j() {
        return this.f772b;
    }

    @Override // b.b.f.p
    public void k(int i) {
        this.f771a.setVisibility(i);
    }

    @Override // b.b.f.p
    public Menu l() {
        return this.f771a.getMenu();
    }

    @Override // b.b.f.p
    public void m(int i) {
        B(i != 0 ? b.b.b.a.a.d(q(), i) : null);
    }

    @Override // b.b.f.p
    public void n(ScrollingTabContainerView scrollingTabContainerView) {
        View view = this.f773c;
        if (view != null) {
            ViewParent parent = view.getParent();
            Toolbar toolbar = this.f771a;
            if (parent == toolbar) {
                toolbar.removeView(this.f773c);
            }
        }
        this.f773c = scrollingTabContainerView;
        if (scrollingTabContainerView == null || this.o != 2) {
            return;
        }
        this.f771a.addView(scrollingTabContainerView, 0);
        Toolbar.e eVar = (Toolbar.e) this.f773c.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) eVar).width = -2;
        ((ViewGroup.MarginLayoutParams) eVar).height = -2;
        eVar.f571a = 8388691;
        scrollingTabContainerView.setAllowCollapse(true);
    }

    @Override // b.b.f.p
    public ViewGroup o() {
        return this.f771a;
    }

    @Override // b.b.f.p
    public void p(boolean z) {
    }

    @Override // b.b.f.p
    public Context q() {
        return this.f771a.getContext();
    }

    @Override // b.b.f.p
    public int r() {
        return this.o;
    }

    @Override // b.b.f.p
    public b.g.j.x s(int i, long j) {
        return b.g.j.t.c(this.f771a).a(i == 0 ? 1.0f : 0.0f).d(j).f(new b(i));
    }

    @Override // b.b.f.p
    public void setIcon(int i) {
        setIcon(i != 0 ? b.b.b.a.a.d(q(), i) : null);
    }

    @Override // b.b.f.p
    public void setWindowCallback(Window.Callback callback) {
        this.l = callback;
    }

    @Override // b.b.f.p
    public void setWindowTitle(CharSequence charSequence) {
        if (this.h) {
            return;
        }
        H(charSequence);
    }

    @Override // b.b.f.p
    public void t() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // b.b.f.p
    public boolean u() {
        return this.f771a.v();
    }

    @Override // b.b.f.p
    public void v() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // b.b.f.p
    public void w(boolean z) {
        this.f771a.setCollapsible(z);
    }

    @Override // b.b.f.p
    public void x(int i) {
        View view;
        int i2 = this.f772b ^ i;
        this.f772b = i;
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
                    this.f771a.setTitle(this.i);
                    this.f771a.setSubtitle(this.j);
                } else {
                    this.f771a.setTitle((CharSequence) null);
                    this.f771a.setSubtitle((CharSequence) null);
                }
            }
            if ((i2 & 16) == 0 || (view = this.f774d) == null) {
                return;
            }
            if ((i & 16) != 0) {
                this.f771a.addView(view);
            } else {
                this.f771a.removeView(view);
            }
        }
    }

    public final int y() {
        if (this.f771a.getNavigationIcon() == null) {
            return 11;
        }
        this.q = this.f771a.getNavigationIcon();
        return 15;
    }

    public void z(View view) {
        View view2 = this.f774d;
        if (view2 != null && (this.f772b & 16) != 0) {
            this.f771a.removeView(view2);
        }
        this.f774d = view;
        if (view == null || (this.f772b & 16) == 0) {
            return;
        }
        this.f771a.addView(view);
    }

    public f0(Toolbar toolbar, boolean z, int i, int i2) {
        Drawable drawable;
        this.o = 0;
        this.p = 0;
        this.f771a = toolbar;
        this.i = toolbar.getTitle();
        this.j = toolbar.getSubtitle();
        this.h = this.i != null;
        this.g = toolbar.getNavigationIcon();
        e0 e0VarV = e0.v(toolbar.getContext(), null, R$styleable.ActionBar, R$attr.actionBarStyle, 0);
        this.q = e0VarV.g(R$styleable.ActionBar_homeAsUpIndicator);
        if (z) {
            CharSequence charSequenceP = e0VarV.p(R$styleable.ActionBar_title);
            if (!TextUtils.isEmpty(charSequenceP)) {
                G(charSequenceP);
            }
            CharSequence charSequenceP2 = e0VarV.p(R$styleable.ActionBar_subtitle);
            if (!TextUtils.isEmpty(charSequenceP2)) {
                F(charSequenceP2);
            }
            Drawable drawableG = e0VarV.g(R$styleable.ActionBar_logo);
            if (drawableG != null) {
                B(drawableG);
            }
            Drawable drawableG2 = e0VarV.g(R$styleable.ActionBar_icon);
            if (drawableG2 != null) {
                setIcon(drawableG2);
            }
            if (this.g == null && (drawable = this.q) != null) {
                E(drawable);
            }
            x(e0VarV.k(R$styleable.ActionBar_displayOptions, 0));
            int iN = e0VarV.n(R$styleable.ActionBar_customNavigationLayout, 0);
            if (iN != 0) {
                z(LayoutInflater.from(this.f771a.getContext()).inflate(iN, (ViewGroup) this.f771a, false));
                x(this.f772b | 16);
            }
            int iM = e0VarV.m(R$styleable.ActionBar_height, 0);
            if (iM > 0) {
                ViewGroup.LayoutParams layoutParams = this.f771a.getLayoutParams();
                layoutParams.height = iM;
                this.f771a.setLayoutParams(layoutParams);
            }
            int iE = e0VarV.e(R$styleable.ActionBar_contentInsetStart, -1);
            int iE2 = e0VarV.e(R$styleable.ActionBar_contentInsetEnd, -1);
            if (iE >= 0 || iE2 >= 0) {
                this.f771a.H(Math.max(iE, 0), Math.max(iE2, 0));
            }
            int iN2 = e0VarV.n(R$styleable.ActionBar_titleTextStyle, 0);
            if (iN2 != 0) {
                Toolbar toolbar2 = this.f771a;
                toolbar2.L(toolbar2.getContext(), iN2);
            }
            int iN3 = e0VarV.n(R$styleable.ActionBar_subtitleTextStyle, 0);
            if (iN3 != 0) {
                Toolbar toolbar3 = this.f771a;
                toolbar3.K(toolbar3.getContext(), iN3);
            }
            int iN4 = e0VarV.n(R$styleable.ActionBar_popupTheme, 0);
            if (iN4 != 0) {
                this.f771a.setPopupTheme(iN4);
            }
        } else {
            this.f772b = y();
        }
        e0VarV.w();
        A(i);
        this.k = this.f771a.getNavigationContentDescription();
        this.f771a.setNavigationOnClickListener(new a());
    }

    @Override // b.b.f.p
    public void setIcon(Drawable drawable) {
        this.e = drawable;
        K();
    }
}
