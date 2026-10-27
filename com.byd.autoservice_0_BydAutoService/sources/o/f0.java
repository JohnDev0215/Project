package o;

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
import n.g;
import n.m;

/* JADX INFO: compiled from: ToolbarWidgetWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class f0 implements p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Toolbar f4264a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4265b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public View f4266c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public View f4267d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Drawable f4268e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Drawable f4269f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Drawable f4270g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f4271h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public CharSequence f4272i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public CharSequence f4273j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public CharSequence f4274k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Window.Callback f4275l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f4276m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ActionMenuPresenter f4277n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f4278o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f4279p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Drawable f4280q;

    /* JADX INFO: compiled from: ToolbarWidgetWrapper.java */
    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final n.a f4281b;

        public a() {
            this.f4281b = new n.a(f0.this.f4264a.getContext(), 0, R.id.home, 0, 0, f0.this.f4272i);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            f0 f0Var = f0.this;
            Window.Callback callback = f0Var.f4275l;
            if (callback == null || !f0Var.f4276m) {
                return;
            }
            callback.onMenuItemSelected(0, this.f4281b);
        }
    }

    /* JADX INFO: compiled from: ToolbarWidgetWrapper.java */
    public class b extends l0.z {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f4283a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f4284b;

        public b(int i3) {
            this.f4284b = i3;
        }

        @Override // l0.y
        public void a(View view) {
            if (this.f4283a) {
                return;
            }
            f0.this.f4264a.setVisibility(this.f4284b);
        }

        @Override // l0.z, l0.y
        public void b(View view) {
            f0.this.f4264a.setVisibility(0);
        }

        @Override // l0.z, l0.y
        public void c(View view) {
            this.f4283a = true;
        }
    }

    public f0(Toolbar toolbar, boolean z3) {
        this(toolbar, z3, R$string.abc_action_bar_up_description, R$drawable.abc_ic_ab_back_material);
    }

    public void A(int i3) {
        if (i3 == this.f4279p) {
            return;
        }
        this.f4279p = i3;
        if (TextUtils.isEmpty(this.f4264a.getNavigationContentDescription())) {
            C(this.f4279p);
        }
    }

    public void B(Drawable drawable) {
        this.f4269f = drawable;
        K();
    }

    public void C(int i3) {
        D(i3 == 0 ? null : q().getString(i3));
    }

    public void D(CharSequence charSequence) {
        this.f4274k = charSequence;
        I();
    }

    public void E(Drawable drawable) {
        this.f4270g = drawable;
        J();
    }

    public void F(CharSequence charSequence) {
        this.f4273j = charSequence;
        if ((this.f4265b & 8) != 0) {
            this.f4264a.setSubtitle(charSequence);
        }
    }

    public void G(CharSequence charSequence) {
        this.f4271h = true;
        H(charSequence);
    }

    public final void H(CharSequence charSequence) {
        this.f4272i = charSequence;
        if ((this.f4265b & 8) != 0) {
            this.f4264a.setTitle(charSequence);
        }
    }

    public final void I() {
        if ((this.f4265b & 4) != 0) {
            if (TextUtils.isEmpty(this.f4274k)) {
                this.f4264a.setNavigationContentDescription(this.f4279p);
            } else {
                this.f4264a.setNavigationContentDescription(this.f4274k);
            }
        }
    }

    public final void J() {
        if ((this.f4265b & 4) == 0) {
            this.f4264a.setNavigationIcon((Drawable) null);
            return;
        }
        Toolbar toolbar = this.f4264a;
        Drawable drawable = this.f4270g;
        if (drawable == null) {
            drawable = this.f4280q;
        }
        toolbar.setNavigationIcon(drawable);
    }

    public final void K() {
        Drawable drawable;
        int i3 = this.f4265b;
        if ((i3 & 2) == 0) {
            drawable = null;
        } else if ((i3 & 1) == 0 || (drawable = this.f4269f) == null) {
            drawable = this.f4268e;
        }
        this.f4264a.setLogo(drawable);
    }

    @Override // o.p
    public void a(Menu menu, m.a aVar) {
        if (this.f4277n == null) {
            ActionMenuPresenter actionMenuPresenter = new ActionMenuPresenter(this.f4264a.getContext());
            this.f4277n = actionMenuPresenter;
            actionMenuPresenter.t(R$id.action_menu_presenter);
        }
        this.f4277n.k(aVar);
        this.f4264a.I((n.g) menu, this.f4277n);
    }

    @Override // o.p
    public boolean b() {
        return this.f4264a.z();
    }

    @Override // o.p
    public boolean c() {
        return this.f4264a.A();
    }

    @Override // o.p
    public void collapseActionView() {
        this.f4264a.e();
    }

    @Override // o.p
    public boolean d() {
        return this.f4264a.w();
    }

    @Override // o.p
    public boolean e() {
        return this.f4264a.O();
    }

    @Override // o.p
    public void f() {
        this.f4276m = true;
    }

    @Override // o.p
    public boolean g() {
        return this.f4264a.d();
    }

    @Override // o.p
    public CharSequence getTitle() {
        return this.f4264a.getTitle();
    }

    @Override // o.p
    public void h() {
        this.f4264a.f();
    }

    @Override // o.p
    public void i(m.a aVar, g.a aVar2) {
        this.f4264a.J(aVar, aVar2);
    }

    @Override // o.p
    public int j() {
        return this.f4265b;
    }

    @Override // o.p
    public void k(int i3) {
        this.f4264a.setVisibility(i3);
    }

    @Override // o.p
    public Menu l() {
        return this.f4264a.getMenu();
    }

    @Override // o.p
    public void m(int i3) {
        B(i3 != 0 ? j.a.d(q(), i3) : null);
    }

    @Override // o.p
    public void n(ScrollingTabContainerView scrollingTabContainerView) {
        View view = this.f4266c;
        if (view != null) {
            ViewParent parent = view.getParent();
            Toolbar toolbar = this.f4264a;
            if (parent == toolbar) {
                toolbar.removeView(this.f4266c);
            }
        }
        this.f4266c = scrollingTabContainerView;
        if (scrollingTabContainerView == null || this.f4278o != 2) {
            return;
        }
        this.f4264a.addView(scrollingTabContainerView, 0);
        Toolbar.e eVar = (Toolbar.e) this.f4266c.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) eVar).width = -2;
        ((ViewGroup.MarginLayoutParams) eVar).height = -2;
        eVar.f3135a = 8388691;
        scrollingTabContainerView.setAllowCollapse(true);
    }

    @Override // o.p
    public ViewGroup o() {
        return this.f4264a;
    }

    @Override // o.p
    public void p(boolean z3) {
    }

    @Override // o.p
    public Context q() {
        return this.f4264a.getContext();
    }

    @Override // o.p
    public int r() {
        return this.f4278o;
    }

    @Override // o.p
    public l0.x s(int i3, long j3) {
        return l0.t.c(this.f4264a).a(i3 == 0 ? 1.0f : 0.0f).d(j3).f(new b(i3));
    }

    @Override // o.p
    public void setIcon(int i3) {
        setIcon(i3 != 0 ? j.a.d(q(), i3) : null);
    }

    @Override // o.p
    public void setWindowCallback(Window.Callback callback) {
        this.f4275l = callback;
    }

    @Override // o.p
    public void setWindowTitle(CharSequence charSequence) {
        if (this.f4271h) {
            return;
        }
        H(charSequence);
    }

    @Override // o.p
    public void t() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // o.p
    public boolean u() {
        return this.f4264a.v();
    }

    @Override // o.p
    public void v() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // o.p
    public void w(boolean z3) {
        this.f4264a.setCollapsible(z3);
    }

    @Override // o.p
    public void x(int i3) {
        View view;
        int i4 = this.f4265b ^ i3;
        this.f4265b = i3;
        if (i4 != 0) {
            if ((i4 & 4) != 0) {
                if ((i3 & 4) != 0) {
                    I();
                }
                J();
            }
            if ((i4 & 3) != 0) {
                K();
            }
            if ((i4 & 8) != 0) {
                if ((i3 & 8) != 0) {
                    this.f4264a.setTitle(this.f4272i);
                    this.f4264a.setSubtitle(this.f4273j);
                } else {
                    this.f4264a.setTitle((CharSequence) null);
                    this.f4264a.setSubtitle((CharSequence) null);
                }
            }
            if ((i4 & 16) == 0 || (view = this.f4267d) == null) {
                return;
            }
            if ((i3 & 16) != 0) {
                this.f4264a.addView(view);
            } else {
                this.f4264a.removeView(view);
            }
        }
    }

    public final int y() {
        if (this.f4264a.getNavigationIcon() == null) {
            return 11;
        }
        this.f4280q = this.f4264a.getNavigationIcon();
        return 15;
    }

    public void z(View view) {
        View view2 = this.f4267d;
        if (view2 != null && (this.f4265b & 16) != 0) {
            this.f4264a.removeView(view2);
        }
        this.f4267d = view;
        if (view == null || (this.f4265b & 16) == 0) {
            return;
        }
        this.f4264a.addView(view);
    }

    public f0(Toolbar toolbar, boolean z3, int i3, int i4) {
        Drawable drawable;
        this.f4278o = 0;
        this.f4279p = 0;
        this.f4264a = toolbar;
        this.f4272i = toolbar.getTitle();
        this.f4273j = toolbar.getSubtitle();
        this.f4271h = this.f4272i != null;
        this.f4270g = toolbar.getNavigationIcon();
        e0 e0VarV = e0.v(toolbar.getContext(), null, R$styleable.ActionBar, R$attr.actionBarStyle, 0);
        this.f4280q = e0VarV.g(R$styleable.ActionBar_homeAsUpIndicator);
        if (z3) {
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
            if (this.f4270g == null && (drawable = this.f4280q) != null) {
                E(drawable);
            }
            x(e0VarV.k(R$styleable.ActionBar_displayOptions, 0));
            int iN = e0VarV.n(R$styleable.ActionBar_customNavigationLayout, 0);
            if (iN != 0) {
                z(LayoutInflater.from(this.f4264a.getContext()).inflate(iN, (ViewGroup) this.f4264a, false));
                x(this.f4265b | 16);
            }
            int iM = e0VarV.m(R$styleable.ActionBar_height, 0);
            if (iM > 0) {
                ViewGroup.LayoutParams layoutParams = this.f4264a.getLayoutParams();
                layoutParams.height = iM;
                this.f4264a.setLayoutParams(layoutParams);
            }
            int iE = e0VarV.e(R$styleable.ActionBar_contentInsetStart, -1);
            int iE2 = e0VarV.e(R$styleable.ActionBar_contentInsetEnd, -1);
            if (iE >= 0 || iE2 >= 0) {
                this.f4264a.H(Math.max(iE, 0), Math.max(iE2, 0));
            }
            int iN2 = e0VarV.n(R$styleable.ActionBar_titleTextStyle, 0);
            if (iN2 != 0) {
                Toolbar toolbar2 = this.f4264a;
                toolbar2.L(toolbar2.getContext(), iN2);
            }
            int iN3 = e0VarV.n(R$styleable.ActionBar_subtitleTextStyle, 0);
            if (iN3 != 0) {
                Toolbar toolbar3 = this.f4264a;
                toolbar3.K(toolbar3.getContext(), iN3);
            }
            int iN4 = e0VarV.n(R$styleable.ActionBar_popupTheme, 0);
            if (iN4 != 0) {
                this.f4264a.setPopupTheme(iN4);
            }
        } else {
            this.f4265b = y();
        }
        e0VarV.w();
        A(i3);
        this.f4274k = this.f4264a.getNavigationContentDescription();
        this.f4264a.setNavigationOnClickListener(new a());
    }

    @Override // o.p
    public void setIcon(Drawable drawable) {
        this.f4268e = drawable;
        K();
    }
}
