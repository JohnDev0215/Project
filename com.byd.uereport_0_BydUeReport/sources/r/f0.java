package r;

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
import q.g;
import q.m;

/* JADX INFO: compiled from: ToolbarWidgetWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class f0 implements p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Toolbar f7523a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f7524b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public View f7525c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public View f7526d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Drawable f7527e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Drawable f7528f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Drawable f7529g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f7530h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public CharSequence f7531i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public CharSequence f7532j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public CharSequence f7533k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Window.Callback f7534l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f7535m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ActionMenuPresenter f7536n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f7537o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f7538p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Drawable f7539q;

    /* JADX INFO: compiled from: ToolbarWidgetWrapper.java */
    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final q.a f7540d;

        public a() {
            this.f7540d = new q.a(f0.this.f7523a.getContext(), 0, R.id.home, 0, 0, f0.this.f7531i);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            f0 f0Var = f0.this;
            Window.Callback callback = f0Var.f7534l;
            if (callback == null || !f0Var.f7535m) {
                return;
            }
            callback.onMenuItemSelected(0, this.f7540d);
        }
    }

    /* JADX INFO: compiled from: ToolbarWidgetWrapper.java */
    public class b extends o0.z {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f7542a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f7543b;

        public b(int i8) {
            this.f7543b = i8;
        }

        @Override // o0.z, o0.y
        public void a(View view) {
            this.f7542a = true;
        }

        @Override // o0.y
        public void b(View view) {
            if (this.f7542a) {
                return;
            }
            f0.this.f7523a.setVisibility(this.f7543b);
        }

        @Override // o0.z, o0.y
        public void c(View view) {
            f0.this.f7523a.setVisibility(0);
        }
    }

    public f0(Toolbar toolbar, boolean z7) {
        this(toolbar, z7, R$string.abc_action_bar_up_description, R$drawable.abc_ic_ab_back_material);
    }

    public void A(int i8) {
        if (i8 == this.f7538p) {
            return;
        }
        this.f7538p = i8;
        if (TextUtils.isEmpty(this.f7523a.getNavigationContentDescription())) {
            C(this.f7538p);
        }
    }

    public void B(Drawable drawable) {
        this.f7528f = drawable;
        K();
    }

    public void C(int i8) {
        D(i8 == 0 ? null : n().getString(i8));
    }

    public void D(CharSequence charSequence) {
        this.f7533k = charSequence;
        I();
    }

    public void E(Drawable drawable) {
        this.f7529g = drawable;
        J();
    }

    public void F(CharSequence charSequence) {
        this.f7532j = charSequence;
        if ((this.f7524b & 8) != 0) {
            this.f7523a.setSubtitle(charSequence);
        }
    }

    public void G(CharSequence charSequence) {
        this.f7530h = true;
        H(charSequence);
    }

    public final void H(CharSequence charSequence) {
        this.f7531i = charSequence;
        if ((this.f7524b & 8) != 0) {
            this.f7523a.setTitle(charSequence);
        }
    }

    public final void I() {
        if ((this.f7524b & 4) != 0) {
            if (TextUtils.isEmpty(this.f7533k)) {
                this.f7523a.setNavigationContentDescription(this.f7538p);
            } else {
                this.f7523a.setNavigationContentDescription(this.f7533k);
            }
        }
    }

    public final void J() {
        if ((this.f7524b & 4) == 0) {
            this.f7523a.setNavigationIcon((Drawable) null);
            return;
        }
        Toolbar toolbar = this.f7523a;
        Drawable drawable = this.f7529g;
        if (drawable == null) {
            drawable = this.f7539q;
        }
        toolbar.setNavigationIcon(drawable);
    }

    public final void K() {
        Drawable drawable;
        int i8 = this.f7524b;
        if ((i8 & 2) == 0) {
            drawable = null;
        } else if ((i8 & 1) == 0 || (drawable = this.f7528f) == null) {
            drawable = this.f7527e;
        }
        this.f7523a.setLogo(drawable);
    }

    @Override // r.p
    public void a(Menu menu, m.a aVar) {
        if (this.f7536n == null) {
            ActionMenuPresenter actionMenuPresenter = new ActionMenuPresenter(this.f7523a.getContext());
            this.f7536n = actionMenuPresenter;
            actionMenuPresenter.t(R$id.action_menu_presenter);
        }
        this.f7536n.n(aVar);
        this.f7523a.I((q.g) menu, this.f7536n);
    }

    @Override // r.p
    public boolean b() {
        return this.f7523a.A();
    }

    @Override // r.p
    public void c() {
        this.f7535m = true;
    }

    @Override // r.p
    public void collapseActionView() {
        this.f7523a.e();
    }

    @Override // r.p
    public boolean d() {
        return this.f7523a.z();
    }

    @Override // r.p
    public boolean e() {
        return this.f7523a.w();
    }

    @Override // r.p
    public boolean f() {
        return this.f7523a.O();
    }

    @Override // r.p
    public boolean g() {
        return this.f7523a.d();
    }

    @Override // r.p
    public CharSequence getTitle() {
        return this.f7523a.getTitle();
    }

    @Override // r.p
    public void h() {
        this.f7523a.f();
    }

    @Override // r.p
    public void i(m.a aVar, g.a aVar2) {
        this.f7523a.J(aVar, aVar2);
    }

    @Override // r.p
    public void j(int i8) {
        this.f7523a.setVisibility(i8);
    }

    @Override // r.p
    public void k(ScrollingTabContainerView scrollingTabContainerView) {
        View view = this.f7525c;
        if (view != null) {
            ViewParent parent = view.getParent();
            Toolbar toolbar = this.f7523a;
            if (parent == toolbar) {
                toolbar.removeView(this.f7525c);
            }
        }
        this.f7525c = scrollingTabContainerView;
        if (scrollingTabContainerView == null || this.f7537o != 2) {
            return;
        }
        this.f7523a.addView(scrollingTabContainerView, 0);
        Toolbar.e eVar = (Toolbar.e) this.f7525c.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) eVar).width = -2;
        ((ViewGroup.MarginLayoutParams) eVar).height = -2;
        eVar.f5929a = 8388691;
        scrollingTabContainerView.setAllowCollapse(true);
    }

    @Override // r.p
    public ViewGroup l() {
        return this.f7523a;
    }

    @Override // r.p
    public void m(boolean z7) {
    }

    @Override // r.p
    public Context n() {
        return this.f7523a.getContext();
    }

    @Override // r.p
    public boolean o() {
        return this.f7523a.v();
    }

    @Override // r.p
    public void p(int i8) {
        View view;
        int i9 = this.f7524b ^ i8;
        this.f7524b = i8;
        if (i9 != 0) {
            if ((i9 & 4) != 0) {
                if ((i8 & 4) != 0) {
                    I();
                }
                J();
            }
            if ((i9 & 3) != 0) {
                K();
            }
            if ((i9 & 8) != 0) {
                if ((i8 & 8) != 0) {
                    this.f7523a.setTitle(this.f7531i);
                    this.f7523a.setSubtitle(this.f7532j);
                } else {
                    this.f7523a.setTitle((CharSequence) null);
                    this.f7523a.setSubtitle((CharSequence) null);
                }
            }
            if ((i9 & 16) == 0 || (view = this.f7526d) == null) {
                return;
            }
            if ((i8 & 16) != 0) {
                this.f7523a.addView(view);
            } else {
                this.f7523a.removeView(view);
            }
        }
    }

    @Override // r.p
    public int q() {
        return this.f7524b;
    }

    @Override // r.p
    public Menu r() {
        return this.f7523a.getMenu();
    }

    @Override // r.p
    public void s(int i8) {
        B(i8 != 0 ? m.a.d(n(), i8) : null);
    }

    @Override // r.p
    public void setIcon(int i8) {
        setIcon(i8 != 0 ? m.a.d(n(), i8) : null);
    }

    @Override // r.p
    public void setWindowCallback(Window.Callback callback) {
        this.f7534l = callback;
    }

    @Override // r.p
    public void setWindowTitle(CharSequence charSequence) {
        if (this.f7530h) {
            return;
        }
        H(charSequence);
    }

    @Override // r.p
    public int t() {
        return this.f7537o;
    }

    @Override // r.p
    public o0.x u(int i8, long j8) {
        return o0.t.c(this.f7523a).a(i8 == 0 ? 1.0f : 0.0f).d(j8).f(new b(i8));
    }

    @Override // r.p
    public void v() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // r.p
    public void w() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // r.p
    public void x(boolean z7) {
        this.f7523a.setCollapsible(z7);
    }

    public final int y() {
        if (this.f7523a.getNavigationIcon() == null) {
            return 11;
        }
        this.f7539q = this.f7523a.getNavigationIcon();
        return 15;
    }

    public void z(View view) {
        View view2 = this.f7526d;
        if (view2 != null && (this.f7524b & 16) != 0) {
            this.f7523a.removeView(view2);
        }
        this.f7526d = view;
        if (view == null || (this.f7524b & 16) == 0) {
            return;
        }
        this.f7523a.addView(view);
    }

    public f0(Toolbar toolbar, boolean z7, int i8, int i9) {
        Drawable drawable;
        this.f7537o = 0;
        this.f7538p = 0;
        this.f7523a = toolbar;
        this.f7531i = toolbar.getTitle();
        this.f7532j = toolbar.getSubtitle();
        this.f7530h = this.f7531i != null;
        this.f7529g = toolbar.getNavigationIcon();
        e0 e0VarV = e0.v(toolbar.getContext(), null, R$styleable.ActionBar, R$attr.actionBarStyle, 0);
        this.f7539q = e0VarV.g(R$styleable.ActionBar_homeAsUpIndicator);
        if (z7) {
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
            if (this.f7529g == null && (drawable = this.f7539q) != null) {
                E(drawable);
            }
            p(e0VarV.k(R$styleable.ActionBar_displayOptions, 0));
            int iN = e0VarV.n(R$styleable.ActionBar_customNavigationLayout, 0);
            if (iN != 0) {
                z(LayoutInflater.from(this.f7523a.getContext()).inflate(iN, (ViewGroup) this.f7523a, false));
                p(this.f7524b | 16);
            }
            int iM = e0VarV.m(R$styleable.ActionBar_height, 0);
            if (iM > 0) {
                ViewGroup.LayoutParams layoutParams = this.f7523a.getLayoutParams();
                layoutParams.height = iM;
                this.f7523a.setLayoutParams(layoutParams);
            }
            int iE = e0VarV.e(R$styleable.ActionBar_contentInsetStart, -1);
            int iE2 = e0VarV.e(R$styleable.ActionBar_contentInsetEnd, -1);
            if (iE >= 0 || iE2 >= 0) {
                this.f7523a.H(Math.max(iE, 0), Math.max(iE2, 0));
            }
            int iN2 = e0VarV.n(R$styleable.ActionBar_titleTextStyle, 0);
            if (iN2 != 0) {
                Toolbar toolbar2 = this.f7523a;
                toolbar2.L(toolbar2.getContext(), iN2);
            }
            int iN3 = e0VarV.n(R$styleable.ActionBar_subtitleTextStyle, 0);
            if (iN3 != 0) {
                Toolbar toolbar3 = this.f7523a;
                toolbar3.K(toolbar3.getContext(), iN3);
            }
            int iN4 = e0VarV.n(R$styleable.ActionBar_popupTheme, 0);
            if (iN4 != 0) {
                this.f7523a.setPopupTheme(iN4);
            }
        } else {
            this.f7524b = y();
        }
        e0VarV.w();
        A(i8);
        this.f7533k = this.f7523a.getNavigationContentDescription();
        this.f7523a.setNavigationOnClickListener(new a());
    }

    @Override // r.p
    public void setIcon(Drawable drawable) {
        this.f7527e = drawable;
        K();
    }
}
