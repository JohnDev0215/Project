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
import androidx.appcompat.view.menu.j;
import androidx.appcompat.widget.Toolbar;

/* JADX INFO: compiled from: ToolbarWidgetWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class t0 implements a0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Toolbar f1192a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f1193b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private View f1194c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private View f1195d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Drawable f1196e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Drawable f1197f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Drawable f1198g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f1199h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    CharSequence f1200i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private CharSequence f1201j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private CharSequence f1202k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    Window.Callback f1203l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    boolean f1204m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private ActionMenuPresenter f1205n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private int f1206o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private int f1207p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private Drawable f1208q;

    /* JADX INFO: compiled from: ToolbarWidgetWrapper.java */
    class a implements View.OnClickListener {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final j.a f1209e;

        a() {
            this.f1209e = new j.a(t0.this.f1192a.getContext(), 0, R.id.home, 0, 0, t0.this.f1200i);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            t0 t0Var = t0.this;
            Window.Callback callback = t0Var.f1203l;
            if (callback == null || !t0Var.f1204m) {
                return;
            }
            callback.onMenuItemSelected(0, this.f1209e);
        }
    }

    /* JADX INFO: compiled from: ToolbarWidgetWrapper.java */
    class b extends i0.a0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f1211a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f1212b;

        b(int i7) {
            this.f1212b = i7;
        }

        @Override // i0.z
        public void a(View view) {
            if (this.f1211a) {
                return;
            }
            t0.this.f1192a.setVisibility(this.f1212b);
        }

        @Override // i0.a0, i0.z
        public void b(View view) {
            t0.this.f1192a.setVisibility(0);
        }

        @Override // i0.a0, i0.z
        public void c(View view) {
            this.f1211a = true;
        }
    }

    public t0(Toolbar toolbar, boolean z6) {
        this(toolbar, z6, e.h.abc_action_bar_up_description, e.e.abc_ic_ab_back_material);
    }

    private void E(CharSequence charSequence) {
        this.f1200i = charSequence;
        if ((this.f1193b & 8) != 0) {
            this.f1192a.setTitle(charSequence);
        }
    }

    private void F() {
        if ((this.f1193b & 4) != 0) {
            if (TextUtils.isEmpty(this.f1202k)) {
                this.f1192a.setNavigationContentDescription(this.f1207p);
            } else {
                this.f1192a.setNavigationContentDescription(this.f1202k);
            }
        }
    }

    private void G() {
        if ((this.f1193b & 4) == 0) {
            this.f1192a.setNavigationIcon((Drawable) null);
            return;
        }
        Toolbar toolbar = this.f1192a;
        Drawable drawable = this.f1198g;
        if (drawable == null) {
            drawable = this.f1208q;
        }
        toolbar.setNavigationIcon(drawable);
    }

    private void H() {
        Drawable drawable;
        int i7 = this.f1193b;
        if ((i7 & 2) == 0) {
            drawable = null;
        } else if ((i7 & 1) == 0 || (drawable = this.f1197f) == null) {
            drawable = this.f1196e;
        }
        this.f1192a.setLogo(drawable);
    }

    private int v() {
        if (this.f1192a.getNavigationIcon() == null) {
            return 11;
        }
        this.f1208q = this.f1192a.getNavigationIcon();
        return 15;
    }

    public void A(CharSequence charSequence) {
        this.f1202k = charSequence;
        F();
    }

    public void B(Drawable drawable) {
        this.f1198g = drawable;
        G();
    }

    public void C(CharSequence charSequence) {
        this.f1201j = charSequence;
        if ((this.f1193b & 8) != 0) {
            this.f1192a.setSubtitle(charSequence);
        }
    }

    public void D(CharSequence charSequence) {
        this.f1199h = true;
        E(charSequence);
    }

    @Override // androidx.appcompat.widget.a0
    public void a(Menu menu, j.a aVar) {
        if (this.f1205n == null) {
            ActionMenuPresenter actionMenuPresenter = new ActionMenuPresenter(this.f1192a.getContext());
            this.f1205n = actionMenuPresenter;
            actionMenuPresenter.p(e.f.action_menu_presenter);
        }
        this.f1205n.h(aVar);
        this.f1192a.I((androidx.appcompat.view.menu.e) menu, this.f1205n);
    }

    @Override // androidx.appcompat.widget.a0
    public boolean b() {
        return this.f1192a.z();
    }

    @Override // androidx.appcompat.widget.a0
    public boolean c() {
        return this.f1192a.A();
    }

    @Override // androidx.appcompat.widget.a0
    public void collapseActionView() {
        this.f1192a.e();
    }

    @Override // androidx.appcompat.widget.a0
    public boolean d() {
        return this.f1192a.w();
    }

    @Override // androidx.appcompat.widget.a0
    public boolean e() {
        return this.f1192a.N();
    }

    @Override // androidx.appcompat.widget.a0
    public void f() {
        this.f1204m = true;
    }

    @Override // androidx.appcompat.widget.a0
    public boolean g() {
        return this.f1192a.d();
    }

    @Override // androidx.appcompat.widget.a0
    public Context getContext() {
        return this.f1192a.getContext();
    }

    @Override // androidx.appcompat.widget.a0
    public CharSequence getTitle() {
        return this.f1192a.getTitle();
    }

    @Override // androidx.appcompat.widget.a0
    public void h() {
        this.f1192a.f();
    }

    @Override // androidx.appcompat.widget.a0
    public int i() {
        return this.f1193b;
    }

    @Override // androidx.appcompat.widget.a0
    public void j(int i7) {
        this.f1192a.setVisibility(i7);
    }

    @Override // androidx.appcompat.widget.a0
    public void k(int i7) {
        y(i7 != 0 ? f.a.b(getContext(), i7) : null);
    }

    @Override // androidx.appcompat.widget.a0
    public void l(l0 l0Var) {
        View view = this.f1194c;
        if (view != null) {
            ViewParent parent = view.getParent();
            Toolbar toolbar = this.f1192a;
            if (parent == toolbar) {
                toolbar.removeView(this.f1194c);
            }
        }
        this.f1194c = l0Var;
        if (l0Var == null || this.f1206o != 2) {
            return;
        }
        this.f1192a.addView(l0Var, 0);
        Toolbar.e eVar = (Toolbar.e) this.f1194c.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) eVar).width = -2;
        ((ViewGroup.MarginLayoutParams) eVar).height = -2;
        eVar.f501a = 8388691;
        l0Var.setAllowCollapse(true);
    }

    @Override // androidx.appcompat.widget.a0
    public ViewGroup m() {
        return this.f1192a;
    }

    @Override // androidx.appcompat.widget.a0
    public void n(boolean z6) {
    }

    @Override // androidx.appcompat.widget.a0
    public int o() {
        return this.f1206o;
    }

    @Override // androidx.appcompat.widget.a0
    public i0.y p(int i7, long j7) {
        return i0.u.d(this.f1192a).a(i7 == 0 ? 1.0f : 0.0f).d(j7).f(new b(i7));
    }

    @Override // androidx.appcompat.widget.a0
    public void q() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // androidx.appcompat.widget.a0
    public boolean r() {
        return this.f1192a.v();
    }

    @Override // androidx.appcompat.widget.a0
    public void s() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // androidx.appcompat.widget.a0
    public void setIcon(int i7) {
        setIcon(i7 != 0 ? f.a.b(getContext(), i7) : null);
    }

    @Override // androidx.appcompat.widget.a0
    public void setWindowCallback(Window.Callback callback) {
        this.f1203l = callback;
    }

    @Override // androidx.appcompat.widget.a0
    public void setWindowTitle(CharSequence charSequence) {
        if (this.f1199h) {
            return;
        }
        E(charSequence);
    }

    @Override // androidx.appcompat.widget.a0
    public void t(boolean z6) {
        this.f1192a.setCollapsible(z6);
    }

    @Override // androidx.appcompat.widget.a0
    public void u(int i7) {
        View view;
        int i8 = this.f1193b ^ i7;
        this.f1193b = i7;
        if (i8 != 0) {
            if ((i8 & 4) != 0) {
                if ((i7 & 4) != 0) {
                    F();
                }
                G();
            }
            if ((i8 & 3) != 0) {
                H();
            }
            if ((i8 & 8) != 0) {
                if ((i7 & 8) != 0) {
                    this.f1192a.setTitle(this.f1200i);
                    this.f1192a.setSubtitle(this.f1201j);
                } else {
                    this.f1192a.setTitle((CharSequence) null);
                    this.f1192a.setSubtitle((CharSequence) null);
                }
            }
            if ((i8 & 16) == 0 || (view = this.f1195d) == null) {
                return;
            }
            if ((i7 & 16) != 0) {
                this.f1192a.addView(view);
            } else {
                this.f1192a.removeView(view);
            }
        }
    }

    public void w(View view) {
        View view2 = this.f1195d;
        if (view2 != null && (this.f1193b & 16) != 0) {
            this.f1192a.removeView(view2);
        }
        this.f1195d = view;
        if (view == null || (this.f1193b & 16) == 0) {
            return;
        }
        this.f1192a.addView(view);
    }

    public void x(int i7) {
        if (i7 == this.f1207p) {
            return;
        }
        this.f1207p = i7;
        if (TextUtils.isEmpty(this.f1192a.getNavigationContentDescription())) {
            z(this.f1207p);
        }
    }

    public void y(Drawable drawable) {
        this.f1197f = drawable;
        H();
    }

    public void z(int i7) {
        A(i7 == 0 ? null : getContext().getString(i7));
    }

    public t0(Toolbar toolbar, boolean z6, int i7, int i8) {
        Drawable drawable;
        this.f1206o = 0;
        this.f1207p = 0;
        this.f1192a = toolbar;
        this.f1200i = toolbar.getTitle();
        this.f1201j = toolbar.getSubtitle();
        this.f1199h = this.f1200i != null;
        this.f1198g = toolbar.getNavigationIcon();
        s0 s0VarV = s0.v(toolbar.getContext(), null, e.j.ActionBar, e.a.actionBarStyle, 0);
        this.f1208q = s0VarV.g(e.j.ActionBar_homeAsUpIndicator);
        if (z6) {
            CharSequence charSequenceP = s0VarV.p(e.j.ActionBar_title);
            if (!TextUtils.isEmpty(charSequenceP)) {
                D(charSequenceP);
            }
            CharSequence charSequenceP2 = s0VarV.p(e.j.ActionBar_subtitle);
            if (!TextUtils.isEmpty(charSequenceP2)) {
                C(charSequenceP2);
            }
            Drawable drawableG = s0VarV.g(e.j.ActionBar_logo);
            if (drawableG != null) {
                y(drawableG);
            }
            Drawable drawableG2 = s0VarV.g(e.j.ActionBar_icon);
            if (drawableG2 != null) {
                setIcon(drawableG2);
            }
            if (this.f1198g == null && (drawable = this.f1208q) != null) {
                B(drawable);
            }
            u(s0VarV.k(e.j.ActionBar_displayOptions, 0));
            int iN = s0VarV.n(e.j.ActionBar_customNavigationLayout, 0);
            if (iN != 0) {
                w(LayoutInflater.from(this.f1192a.getContext()).inflate(iN, (ViewGroup) this.f1192a, false));
                u(this.f1193b | 16);
            }
            int iM = s0VarV.m(e.j.ActionBar_height, 0);
            if (iM > 0) {
                ViewGroup.LayoutParams layoutParams = this.f1192a.getLayoutParams();
                layoutParams.height = iM;
                this.f1192a.setLayoutParams(layoutParams);
            }
            int iE = s0VarV.e(e.j.ActionBar_contentInsetStart, -1);
            int iE2 = s0VarV.e(e.j.ActionBar_contentInsetEnd, -1);
            if (iE >= 0 || iE2 >= 0) {
                this.f1192a.H(Math.max(iE, 0), Math.max(iE2, 0));
            }
            int iN2 = s0VarV.n(e.j.ActionBar_titleTextStyle, 0);
            if (iN2 != 0) {
                Toolbar toolbar2 = this.f1192a;
                toolbar2.K(toolbar2.getContext(), iN2);
            }
            int iN3 = s0VarV.n(e.j.ActionBar_subtitleTextStyle, 0);
            if (iN3 != 0) {
                Toolbar toolbar3 = this.f1192a;
                toolbar3.J(toolbar3.getContext(), iN3);
            }
            int iN4 = s0VarV.n(e.j.ActionBar_popupTheme, 0);
            if (iN4 != 0) {
                this.f1192a.setPopupTheme(iN4);
            }
        } else {
            this.f1193b = v();
        }
        s0VarV.w();
        x(i7);
        this.f1202k = this.f1192a.getNavigationContentDescription();
        this.f1192a.setNavigationOnClickListener(new a());
    }

    @Override // androidx.appcompat.widget.a0
    public void setIcon(Drawable drawable) {
        this.f1196e = drawable;
        H();
    }
}
