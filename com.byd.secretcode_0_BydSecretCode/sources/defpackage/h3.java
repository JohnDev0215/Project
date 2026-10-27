package defpackage;

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
import defpackage.p1;
import defpackage.v1;

/* JADX INFO: compiled from: ToolbarWidgetWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class h3 implements r2 {
    public Toolbar a;
    public int b;
    public View c;
    public View d;
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
        public final j1 b;

        public a() {
            this.b = new j1(h3.this.a.getContext(), 0, R.id.home, 0, 0, h3.this.i);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            h3 h3Var = h3.this;
            Window.Callback callback = h3Var.l;
            if (callback == null || !h3Var.m) {
                return;
            }
            callback.onMenuItemSelected(0, this.b);
        }
    }

    /* JADX INFO: compiled from: ToolbarWidgetWrapper.java */
    public class b extends z9 {
        public boolean a = false;
        public final /* synthetic */ int b;

        public b(int i) {
            this.b = i;
        }

        @Override // defpackage.y9
        public void a(View view) {
            if (this.a) {
                return;
            }
            h3.this.a.setVisibility(this.b);
        }

        @Override // defpackage.z9, defpackage.y9
        public void b(View view) {
            h3.this.a.setVisibility(0);
        }

        @Override // defpackage.z9, defpackage.y9
        public void c(View view) {
            this.a = true;
        }
    }

    public h3(Toolbar toolbar, boolean z) {
        this(toolbar, z, R$string.abc_action_bar_up_description, R$drawable.abc_ic_ab_back_material);
    }

    public void A(int i) {
        if (i == this.p) {
            return;
        }
        this.p = i;
        if (TextUtils.isEmpty(this.a.getNavigationContentDescription())) {
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
        if ((this.b & 8) != 0) {
            this.a.setSubtitle(charSequence);
        }
    }

    public void G(CharSequence charSequence) {
        this.h = true;
        H(charSequence);
    }

    public final void H(CharSequence charSequence) {
        this.i = charSequence;
        if ((this.b & 8) != 0) {
            this.a.setTitle(charSequence);
        }
    }

    public final void I() {
        if ((this.b & 4) != 0) {
            if (TextUtils.isEmpty(this.k)) {
                this.a.setNavigationContentDescription(this.p);
            } else {
                this.a.setNavigationContentDescription(this.k);
            }
        }
    }

    public final void J() {
        if ((this.b & 4) == 0) {
            this.a.setNavigationIcon((Drawable) null);
            return;
        }
        Toolbar toolbar = this.a;
        Drawable drawable = this.g;
        if (drawable == null) {
            drawable = this.q;
        }
        toolbar.setNavigationIcon(drawable);
    }

    public final void K() {
        Drawable drawable;
        int i = this.b;
        if ((i & 2) == 0) {
            drawable = null;
        } else if ((i & 1) == 0 || (drawable = this.f) == null) {
            drawable = this.e;
        }
        this.a.setLogo(drawable);
    }

    @Override // defpackage.r2
    public void a(Menu menu, v1.a aVar) {
        if (this.n == null) {
            ActionMenuPresenter actionMenuPresenter = new ActionMenuPresenter(this.a.getContext());
            this.n = actionMenuPresenter;
            actionMenuPresenter.t(R$id.action_menu_presenter);
        }
        this.n.k(aVar);
        this.a.I((p1) menu, this.n);
    }

    @Override // defpackage.r2
    public boolean b() {
        return this.a.z();
    }

    @Override // defpackage.r2
    public boolean c() {
        return this.a.A();
    }

    @Override // defpackage.r2
    public void collapseActionView() {
        this.a.e();
    }

    @Override // defpackage.r2
    public boolean d() {
        return this.a.w();
    }

    @Override // defpackage.r2
    public boolean e() {
        return this.a.O();
    }

    @Override // defpackage.r2
    public void f() {
        this.m = true;
    }

    @Override // defpackage.r2
    public boolean g() {
        return this.a.d();
    }

    @Override // defpackage.r2
    public CharSequence getTitle() {
        return this.a.getTitle();
    }

    @Override // defpackage.r2
    public void h() {
        this.a.f();
    }

    @Override // defpackage.r2
    public void i(v1.a aVar, p1.a aVar2) {
        this.a.J(aVar, aVar2);
    }

    @Override // defpackage.r2
    public int j() {
        return this.b;
    }

    @Override // defpackage.r2
    public void k(int i) {
        this.a.setVisibility(i);
    }

    @Override // defpackage.r2
    public Menu l() {
        return this.a.getMenu();
    }

    @Override // defpackage.r2
    public void m(int i) {
        B(i != 0 ? u0.d(q(), i) : null);
    }

    @Override // defpackage.r2
    public void n(ScrollingTabContainerView scrollingTabContainerView) {
        View view = this.c;
        if (view != null) {
            ViewParent parent = view.getParent();
            Toolbar toolbar = this.a;
            if (parent == toolbar) {
                toolbar.removeView(this.c);
            }
        }
        this.c = scrollingTabContainerView;
        if (scrollingTabContainerView == null || this.o != 2) {
            return;
        }
        this.a.addView(scrollingTabContainerView, 0);
        Toolbar.e eVar = (Toolbar.e) this.c.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) eVar).width = -2;
        ((ViewGroup.MarginLayoutParams) eVar).height = -2;
        eVar.a = 8388691;
        scrollingTabContainerView.setAllowCollapse(true);
    }

    @Override // defpackage.r2
    public ViewGroup o() {
        return this.a;
    }

    @Override // defpackage.r2
    public void p(boolean z) {
    }

    @Override // defpackage.r2
    public Context q() {
        return this.a.getContext();
    }

    @Override // defpackage.r2
    public int r() {
        return this.o;
    }

    @Override // defpackage.r2
    public x9 s(int i, long j) {
        return t9.c(this.a).a(i == 0 ? 1.0f : 0.0f).d(j).f(new b(i));
    }

    @Override // defpackage.r2
    public void setIcon(int i) {
        setIcon(i != 0 ? u0.d(q(), i) : null);
    }

    @Override // defpackage.r2
    public void setWindowCallback(Window.Callback callback) {
        this.l = callback;
    }

    @Override // defpackage.r2
    public void setWindowTitle(CharSequence charSequence) {
        if (this.h) {
            return;
        }
        H(charSequence);
    }

    @Override // defpackage.r2
    public void t() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // defpackage.r2
    public boolean u() {
        return this.a.v();
    }

    @Override // defpackage.r2
    public void v() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // defpackage.r2
    public void w(boolean z) {
        this.a.setCollapsible(z);
    }

    @Override // defpackage.r2
    public void x(int i) {
        View view;
        int i2 = this.b ^ i;
        this.b = i;
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
                    this.a.setTitle(this.i);
                    this.a.setSubtitle(this.j);
                } else {
                    this.a.setTitle((CharSequence) null);
                    this.a.setSubtitle((CharSequence) null);
                }
            }
            if ((i2 & 16) == 0 || (view = this.d) == null) {
                return;
            }
            if ((i & 16) != 0) {
                this.a.addView(view);
            } else {
                this.a.removeView(view);
            }
        }
    }

    public final int y() {
        if (this.a.getNavigationIcon() == null) {
            return 11;
        }
        this.q = this.a.getNavigationIcon();
        return 15;
    }

    public void z(View view) {
        View view2 = this.d;
        if (view2 != null && (this.b & 16) != 0) {
            this.a.removeView(view2);
        }
        this.d = view;
        if (view == null || (this.b & 16) == 0) {
            return;
        }
        this.a.addView(view);
    }

    public h3(Toolbar toolbar, boolean z, int i, int i2) {
        Drawable drawable;
        this.o = 0;
        this.p = 0;
        this.a = toolbar;
        this.i = toolbar.getTitle();
        this.j = toolbar.getSubtitle();
        this.h = this.i != null;
        this.g = toolbar.getNavigationIcon();
        g3 g3VarV = g3.v(toolbar.getContext(), null, R$styleable.ActionBar, R$attr.actionBarStyle, 0);
        this.q = g3VarV.g(R$styleable.ActionBar_homeAsUpIndicator);
        if (z) {
            CharSequence charSequenceP = g3VarV.p(R$styleable.ActionBar_title);
            if (!TextUtils.isEmpty(charSequenceP)) {
                G(charSequenceP);
            }
            CharSequence charSequenceP2 = g3VarV.p(R$styleable.ActionBar_subtitle);
            if (!TextUtils.isEmpty(charSequenceP2)) {
                F(charSequenceP2);
            }
            Drawable drawableG = g3VarV.g(R$styleable.ActionBar_logo);
            if (drawableG != null) {
                B(drawableG);
            }
            Drawable drawableG2 = g3VarV.g(R$styleable.ActionBar_icon);
            if (drawableG2 != null) {
                setIcon(drawableG2);
            }
            if (this.g == null && (drawable = this.q) != null) {
                E(drawable);
            }
            x(g3VarV.k(R$styleable.ActionBar_displayOptions, 0));
            int iN = g3VarV.n(R$styleable.ActionBar_customNavigationLayout, 0);
            if (iN != 0) {
                z(LayoutInflater.from(this.a.getContext()).inflate(iN, (ViewGroup) this.a, false));
                x(this.b | 16);
            }
            int iM = g3VarV.m(R$styleable.ActionBar_height, 0);
            if (iM > 0) {
                ViewGroup.LayoutParams layoutParams = this.a.getLayoutParams();
                layoutParams.height = iM;
                this.a.setLayoutParams(layoutParams);
            }
            int iE = g3VarV.e(R$styleable.ActionBar_contentInsetStart, -1);
            int iE2 = g3VarV.e(R$styleable.ActionBar_contentInsetEnd, -1);
            if (iE >= 0 || iE2 >= 0) {
                this.a.H(Math.max(iE, 0), Math.max(iE2, 0));
            }
            int iN2 = g3VarV.n(R$styleable.ActionBar_titleTextStyle, 0);
            if (iN2 != 0) {
                Toolbar toolbar2 = this.a;
                toolbar2.L(toolbar2.getContext(), iN2);
            }
            int iN3 = g3VarV.n(R$styleable.ActionBar_subtitleTextStyle, 0);
            if (iN3 != 0) {
                Toolbar toolbar3 = this.a;
                toolbar3.K(toolbar3.getContext(), iN3);
            }
            int iN4 = g3VarV.n(R$styleable.ActionBar_popupTheme, 0);
            if (iN4 != 0) {
                this.a.setPopupTheme(iN4);
            }
        } else {
            this.b = y();
        }
        g3VarV.w();
        A(i);
        this.k = this.a.getNavigationContentDescription();
        this.a.setNavigationOnClickListener(new a());
    }

    @Override // defpackage.r2
    public void setIcon(Drawable drawable) {
        this.e = drawable;
        K();
    }
}
