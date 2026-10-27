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
import androidx.annotation.RestrictTo;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$drawable;
import androidx.appcompat.R$id;
import androidx.appcompat.R$string;
import androidx.appcompat.R$styleable;
import androidx.appcompat.view.menu.l;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class V implements B {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Toolbar f332a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f333b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private View f334c;
    private View d;
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

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements View.OnClickListener {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final androidx.appcompat.view.menu.a f335c;

        a() {
            this.f335c = new androidx.appcompat.view.menu.a(V.this.f332a.getContext(), 0, R.id.home, 0, 0, V.this.i);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            V v = V.this;
            Window.Callback callback = v.l;
            if (callback == null || !v.m) {
                return;
            }
            callback.onMenuItemSelected(0, this.f335c);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b extends androidx.core.view.x {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f336a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f337b;

        b(int i) {
            this.f337b = i;
        }

        @Override // androidx.core.view.w
        public void a(View view) {
            if (this.f336a) {
                return;
            }
            V.this.f332a.setVisibility(this.f337b);
        }

        @Override // androidx.core.view.x, androidx.core.view.w
        public void b(View view) {
            V.this.f332a.setVisibility(0);
        }

        @Override // androidx.core.view.x, androidx.core.view.w
        public void c(View view) {
            this.f336a = true;
        }
    }

    public V(Toolbar toolbar, boolean z) {
        this(toolbar, z, R$string.abc_action_bar_up_description, R$drawable.abc_ic_ab_back_material);
    }

    private void G(CharSequence charSequence) {
        this.i = charSequence;
        if ((this.f333b & 8) != 0) {
            this.f332a.h0(charSequence);
        }
    }

    private void H() {
        if ((this.f333b & 4) != 0) {
            if (TextUtils.isEmpty(this.k)) {
                this.f332a.Z(this.p);
            } else {
                this.f332a.a0(this.k);
            }
        }
    }

    private void I() {
        if ((this.f333b & 4) == 0) {
            this.f332a.b0(null);
            return;
        }
        Toolbar toolbar = this.f332a;
        Drawable drawable = this.g;
        if (drawable == null) {
            drawable = this.q;
        }
        toolbar.b0(drawable);
    }

    private void J() {
        Drawable drawable;
        int i = this.f333b;
        if ((i & 2) == 0) {
            drawable = null;
        } else if ((i & 1) == 0 || (drawable = this.f) == null) {
            drawable = this.e;
        }
        this.f332a.W(drawable);
    }

    private int w() {
        if (this.f332a.C() == null) {
            return 11;
        }
        this.q = this.f332a.C();
        return 15;
    }

    public void A(Drawable drawable) {
        this.f = drawable;
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
        this.g = drawable;
        I();
    }

    public void E(CharSequence charSequence) {
        this.j = charSequence;
        if ((this.f333b & 8) != 0) {
            this.f332a.e0(charSequence);
        }
    }

    public void F(CharSequence charSequence) {
        this.h = true;
        G(charSequence);
    }

    @Override // androidx.appcompat.widget.B
    public void a(Menu menu, l.a aVar) {
        if (this.n == null) {
            ActionMenuPresenter actionMenuPresenter = new ActionMenuPresenter(this.f332a.getContext());
            this.n = actionMenuPresenter;
            actionMenuPresenter.p(R$id.action_menu_presenter);
        }
        this.n.h(aVar);
        this.f332a.Y((androidx.appcompat.view.menu.f) menu, this.n);
    }

    @Override // androidx.appcompat.widget.B
    public boolean b() {
        return this.f332a.M();
    }

    @Override // androidx.appcompat.widget.B
    public void c(CharSequence charSequence) {
        if (this.h) {
            return;
        }
        G(charSequence);
    }

    @Override // androidx.appcompat.widget.B
    public void collapseActionView() {
        this.f332a.e();
    }

    @Override // androidx.appcompat.widget.B
    public boolean d() {
        return this.f332a.N();
    }

    @Override // androidx.appcompat.widget.B
    public void e(Window.Callback callback) {
        this.l = callback;
    }

    @Override // androidx.appcompat.widget.B
    public boolean f() {
        return this.f332a.J();
    }

    @Override // androidx.appcompat.widget.B
    public boolean g() {
        return this.f332a.m0();
    }

    @Override // androidx.appcompat.widget.B
    public Context getContext() {
        return this.f332a.getContext();
    }

    @Override // androidx.appcompat.widget.B
    public void h() {
        this.m = true;
    }

    @Override // androidx.appcompat.widget.B
    public boolean i() {
        return this.f332a.d();
    }

    @Override // androidx.appcompat.widget.B
    public void j() {
        this.f332a.f();
    }

    @Override // androidx.appcompat.widget.B
    public int k() {
        return this.f333b;
    }

    @Override // androidx.appcompat.widget.B
    public void l(int i) {
        this.f332a.setVisibility(i);
    }

    @Override // androidx.appcompat.widget.B
    public void m(M m) {
        View view = this.f334c;
        if (view != null) {
            ViewParent parent = view.getParent();
            Toolbar toolbar = this.f332a;
            if (parent == toolbar) {
                toolbar.removeView(this.f334c);
            }
        }
        this.f334c = m;
        if (m == null || this.o != 2) {
            return;
        }
        this.f332a.addView(m, 0);
        Toolbar.e eVar = (Toolbar.e) this.f334c.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) eVar).width = -2;
        ((ViewGroup.MarginLayoutParams) eVar).height = -2;
        eVar.f95a = 8388691;
        m.a(true);
    }

    @Override // androidx.appcompat.widget.B
    public ViewGroup n() {
        return this.f332a;
    }

    @Override // androidx.appcompat.widget.B
    public void o(boolean z) {
    }

    @Override // androidx.appcompat.widget.B
    public int p() {
        return this.o;
    }

    @Override // androidx.appcompat.widget.B
    public androidx.core.view.v q(int i, long j) {
        androidx.core.view.v vVarB = ViewCompat.b(this.f332a);
        vVarB.a(i == 0 ? 1.0f : 0.0f);
        vVarB.d(j);
        vVarB.f(new b(i));
        return vVarB;
    }

    @Override // androidx.appcompat.widget.B
    public void r() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // androidx.appcompat.widget.B
    public boolean s() {
        return this.f332a.I();
    }

    @Override // androidx.appcompat.widget.B
    public void t() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // androidx.appcompat.widget.B
    public void u(boolean z) {
        this.f332a.U(z);
    }

    @Override // androidx.appcompat.widget.B
    public void v(int i) {
        View view;
        int i2 = this.f333b ^ i;
        this.f333b = i;
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
                    this.f332a.h0(this.i);
                    this.f332a.e0(this.j);
                } else {
                    this.f332a.h0(null);
                    this.f332a.e0(null);
                }
            }
            if ((i2 & 16) == 0 || (view = this.d) == null) {
                return;
            }
            if ((i & 16) != 0) {
                this.f332a.addView(view);
            } else {
                this.f332a.removeView(view);
            }
        }
    }

    public void x(View view) {
        View view2 = this.d;
        if (view2 != null && (this.f333b & 16) != 0) {
            this.f332a.removeView(view2);
        }
        this.d = view;
        if (view == null || (this.f333b & 16) == 0) {
            return;
        }
        this.f332a.addView(view);
    }

    public void y(int i) {
        if (i == this.p) {
            return;
        }
        this.p = i;
        if (TextUtils.isEmpty(this.f332a.B())) {
            B(this.p);
        }
    }

    public void z(Drawable drawable) {
        this.e = drawable;
        J();
    }

    public V(Toolbar toolbar, boolean z, int i, int i2) {
        Drawable drawable;
        this.o = 0;
        this.p = 0;
        this.f332a = toolbar;
        this.i = toolbar.E();
        this.j = toolbar.D();
        this.h = this.i != null;
        this.g = toolbar.C();
        U u = U.u(toolbar.getContext(), null, R$styleable.ActionBar, R$attr.actionBarStyle, 0);
        this.q = u.f(R$styleable.ActionBar_homeAsUpIndicator);
        if (z) {
            CharSequence charSequenceO = u.o(R$styleable.ActionBar_title);
            if (!TextUtils.isEmpty(charSequenceO)) {
                F(charSequenceO);
            }
            CharSequence charSequenceO2 = u.o(R$styleable.ActionBar_subtitle);
            if (!TextUtils.isEmpty(charSequenceO2)) {
                E(charSequenceO2);
            }
            Drawable drawableF = u.f(R$styleable.ActionBar_logo);
            if (drawableF != null) {
                A(drawableF);
            }
            Drawable drawableF2 = u.f(R$styleable.ActionBar_icon);
            if (drawableF2 != null) {
                z(drawableF2);
            }
            if (this.g == null && (drawable = this.q) != null) {
                D(drawable);
            }
            v(u.j(R$styleable.ActionBar_displayOptions, 0));
            int iM = u.m(R$styleable.ActionBar_customNavigationLayout, 0);
            if (iM != 0) {
                x(LayoutInflater.from(this.f332a.getContext()).inflate(iM, (ViewGroup) this.f332a, false));
                v(this.f333b | 16);
            }
            int iL = u.l(R$styleable.ActionBar_height, 0);
            if (iL > 0) {
                ViewGroup.LayoutParams layoutParams = this.f332a.getLayoutParams();
                layoutParams.height = iL;
                this.f332a.setLayoutParams(layoutParams);
            }
            int iD = u.d(R$styleable.ActionBar_contentInsetStart, -1);
            int iD2 = u.d(R$styleable.ActionBar_contentInsetEnd, -1);
            if (iD >= 0 || iD2 >= 0) {
                this.f332a.V(Math.max(iD, 0), Math.max(iD2, 0));
            }
            int iM2 = u.m(R$styleable.ActionBar_titleTextStyle, 0);
            if (iM2 != 0) {
                Toolbar toolbar2 = this.f332a;
                toolbar2.i0(toolbar2.getContext(), iM2);
            }
            int iM3 = u.m(R$styleable.ActionBar_subtitleTextStyle, 0);
            if (iM3 != 0) {
                Toolbar toolbar3 = this.f332a;
                toolbar3.f0(toolbar3.getContext(), iM3);
            }
            int iM4 = u.m(R$styleable.ActionBar_popupTheme, 0);
            if (iM4 != 0) {
                this.f332a.d0(iM4);
            }
        } else {
            this.f333b = w();
        }
        u.v();
        y(i);
        this.k = this.f332a.B();
        this.f332a.c0(new a());
    }
}
