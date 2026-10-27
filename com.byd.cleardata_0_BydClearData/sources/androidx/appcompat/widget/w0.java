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

/* JADX INFO: loaded from: classes.dex */
public class w0 implements c0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Toolbar f1048a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f1049b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private View f1050c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private View f1051d;
    private Drawable e;
    private Drawable f;
    private Drawable g;
    private boolean h;
    CharSequence i;
    private CharSequence j;
    private CharSequence k;
    Window.Callback l;
    boolean m;
    private c n;
    private int o;
    private int p;
    private Drawable q;

    class a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final androidx.appcompat.view.menu.a f1052b;

        a() {
            this.f1052b = new androidx.appcompat.view.menu.a(w0.this.f1048a.getContext(), 0, R.id.home, 0, 0, w0.this.i);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            w0 w0Var = w0.this;
            Window.Callback callback = w0Var.l;
            if (callback == null || !w0Var.m) {
                return;
            }
            callback.onMenuItemSelected(0, this.f1052b);
        }
    }

    class b extends a.g.k.z {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f1054a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f1055b;

        b(int i) {
            this.f1055b = i;
        }

        @Override // a.g.k.y
        public void a(View view) {
            if (this.f1054a) {
                return;
            }
            w0.this.f1048a.setVisibility(this.f1055b);
        }

        @Override // a.g.k.z, a.g.k.y
        public void b(View view) {
            w0.this.f1048a.setVisibility(0);
        }

        @Override // a.g.k.z, a.g.k.y
        public void c(View view) {
            this.f1054a = true;
        }
    }

    public w0(Toolbar toolbar, boolean z) {
        this(toolbar, z, a.a.h.f25a, a.a.e.n);
    }

    public w0(Toolbar toolbar, boolean z, int i, int i2) {
        Drawable drawable;
        this.o = 0;
        this.p = 0;
        this.f1048a = toolbar;
        this.i = toolbar.getTitle();
        this.j = toolbar.getSubtitle();
        this.h = this.i != null;
        this.g = toolbar.getNavigationIcon();
        v0 v0VarV = v0.v(toolbar.getContext(), null, a.a.j.f33a, a.a.a.f2c, 0);
        this.q = v0VarV.g(a.a.j.l);
        if (z) {
            CharSequence charSequenceP = v0VarV.p(a.a.j.r);
            if (!TextUtils.isEmpty(charSequenceP)) {
                E(charSequenceP);
            }
            CharSequence charSequenceP2 = v0VarV.p(a.a.j.p);
            if (!TextUtils.isEmpty(charSequenceP2)) {
                D(charSequenceP2);
            }
            Drawable drawableG = v0VarV.g(a.a.j.n);
            if (drawableG != null) {
                z(drawableG);
            }
            Drawable drawableG2 = v0VarV.g(a.a.j.m);
            if (drawableG2 != null) {
                setIcon(drawableG2);
            }
            if (this.g == null && (drawable = this.q) != null) {
                C(drawable);
            }
            v(v0VarV.k(a.a.j.h, 0));
            int iN = v0VarV.n(a.a.j.g, 0);
            if (iN != 0) {
                x(LayoutInflater.from(this.f1048a.getContext()).inflate(iN, (ViewGroup) this.f1048a, false));
                v(this.f1049b | 16);
            }
            int iM = v0VarV.m(a.a.j.j, 0);
            if (iM > 0) {
                ViewGroup.LayoutParams layoutParams = this.f1048a.getLayoutParams();
                layoutParams.height = iM;
                this.f1048a.setLayoutParams(layoutParams);
            }
            int iE = v0VarV.e(a.a.j.f, -1);
            int iE2 = v0VarV.e(a.a.j.e, -1);
            if (iE >= 0 || iE2 >= 0) {
                this.f1048a.H(Math.max(iE, 0), Math.max(iE2, 0));
            }
            int iN2 = v0VarV.n(a.a.j.s, 0);
            if (iN2 != 0) {
                Toolbar toolbar2 = this.f1048a;
                toolbar2.K(toolbar2.getContext(), iN2);
            }
            int iN3 = v0VarV.n(a.a.j.q, 0);
            if (iN3 != 0) {
                Toolbar toolbar3 = this.f1048a;
                toolbar3.J(toolbar3.getContext(), iN3);
            }
            int iN4 = v0VarV.n(a.a.j.o, 0);
            if (iN4 != 0) {
                this.f1048a.setPopupTheme(iN4);
            }
        } else {
            this.f1049b = w();
        }
        v0VarV.w();
        y(i);
        this.k = this.f1048a.getNavigationContentDescription();
        this.f1048a.setNavigationOnClickListener(new a());
    }

    private void F(CharSequence charSequence) {
        this.i = charSequence;
        if ((this.f1049b & 8) != 0) {
            this.f1048a.setTitle(charSequence);
        }
    }

    private void G() {
        if ((this.f1049b & 4) != 0) {
            if (TextUtils.isEmpty(this.k)) {
                this.f1048a.setNavigationContentDescription(this.p);
            } else {
                this.f1048a.setNavigationContentDescription(this.k);
            }
        }
    }

    private void H() {
        Toolbar toolbar;
        Drawable drawable;
        if ((this.f1049b & 4) != 0) {
            toolbar = this.f1048a;
            drawable = this.g;
            if (drawable == null) {
                drawable = this.q;
            }
        } else {
            toolbar = this.f1048a;
            drawable = null;
        }
        toolbar.setNavigationIcon(drawable);
    }

    private void I() {
        Drawable drawable;
        int i = this.f1049b;
        if ((i & 2) == 0) {
            drawable = null;
        } else if ((i & 1) == 0 || (drawable = this.f) == null) {
            drawable = this.e;
        }
        this.f1048a.setLogo(drawable);
    }

    private int w() {
        if (this.f1048a.getNavigationIcon() == null) {
            return 11;
        }
        this.q = this.f1048a.getNavigationIcon();
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
        if ((this.f1049b & 8) != 0) {
            this.f1048a.setSubtitle(charSequence);
        }
    }

    public void E(CharSequence charSequence) {
        this.h = true;
        F(charSequence);
    }

    @Override // androidx.appcompat.widget.c0
    public void a(Menu menu, m.a aVar) {
        if (this.n == null) {
            c cVar = new c(this.f1048a.getContext());
            this.n = cVar;
            cVar.p(a.a.f.g);
        }
        this.n.h(aVar);
        this.f1048a.I((androidx.appcompat.view.menu.g) menu, this.n);
    }

    @Override // androidx.appcompat.widget.c0
    public boolean b() {
        return this.f1048a.z();
    }

    @Override // androidx.appcompat.widget.c0
    public boolean c() {
        return this.f1048a.A();
    }

    @Override // androidx.appcompat.widget.c0
    public void collapseActionView() {
        this.f1048a.e();
    }

    @Override // androidx.appcompat.widget.c0
    public boolean d() {
        return this.f1048a.w();
    }

    @Override // androidx.appcompat.widget.c0
    public boolean e() {
        return this.f1048a.N();
    }

    @Override // androidx.appcompat.widget.c0
    public void f() {
        this.m = true;
    }

    @Override // androidx.appcompat.widget.c0
    public boolean g() {
        return this.f1048a.d();
    }

    @Override // androidx.appcompat.widget.c0
    public CharSequence getTitle() {
        return this.f1048a.getTitle();
    }

    @Override // androidx.appcompat.widget.c0
    public void h() {
        this.f1048a.f();
    }

    @Override // androidx.appcompat.widget.c0
    public int i() {
        return this.f1049b;
    }

    @Override // androidx.appcompat.widget.c0
    public void j(int i) {
        this.f1048a.setVisibility(i);
    }

    @Override // androidx.appcompat.widget.c0
    public void k(int i) {
        z(i != 0 ? a.a.k.a.a.d(o(), i) : null);
    }

    @Override // androidx.appcompat.widget.c0
    public void l(o0 o0Var) {
        View view = this.f1050c;
        if (view != null) {
            ViewParent parent = view.getParent();
            Toolbar toolbar = this.f1048a;
            if (parent == toolbar) {
                toolbar.removeView(this.f1050c);
            }
        }
        this.f1050c = o0Var;
        if (o0Var == null || this.o != 2) {
            return;
        }
        this.f1048a.addView(o0Var, 0);
        Toolbar.e eVar = (Toolbar.e) this.f1050c.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) eVar).width = -2;
        ((ViewGroup.MarginLayoutParams) eVar).height = -2;
        eVar.f710a = 8388691;
        o0Var.setAllowCollapse(true);
    }

    @Override // androidx.appcompat.widget.c0
    public ViewGroup m() {
        return this.f1048a;
    }

    @Override // androidx.appcompat.widget.c0
    public void n(boolean z) {
    }

    @Override // androidx.appcompat.widget.c0
    public Context o() {
        return this.f1048a.getContext();
    }

    @Override // androidx.appcompat.widget.c0
    public int p() {
        return this.o;
    }

    @Override // androidx.appcompat.widget.c0
    public a.g.k.x q(int i, long j) {
        return a.g.k.t.c(this.f1048a).a(i == 0 ? 1.0f : 0.0f).d(j).f(new b(i));
    }

    @Override // androidx.appcompat.widget.c0
    public void r() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // androidx.appcompat.widget.c0
    public boolean s() {
        return this.f1048a.v();
    }

    @Override // androidx.appcompat.widget.c0
    public void setIcon(int i) {
        setIcon(i != 0 ? a.a.k.a.a.d(o(), i) : null);
    }

    @Override // androidx.appcompat.widget.c0
    public void setIcon(Drawable drawable) {
        this.e = drawable;
        I();
    }

    @Override // androidx.appcompat.widget.c0
    public void setWindowCallback(Window.Callback callback) {
        this.l = callback;
    }

    @Override // androidx.appcompat.widget.c0
    public void setWindowTitle(CharSequence charSequence) {
        if (this.h) {
            return;
        }
        F(charSequence);
    }

    @Override // androidx.appcompat.widget.c0
    public void t() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // androidx.appcompat.widget.c0
    public void u(boolean z) {
        this.f1048a.setCollapsible(z);
    }

    @Override // androidx.appcompat.widget.c0
    public void v(int i) {
        View view;
        CharSequence charSequence;
        Toolbar toolbar;
        int i2 = this.f1049b ^ i;
        this.f1049b = i;
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
                    this.f1048a.setTitle(this.i);
                    toolbar = this.f1048a;
                    charSequence = this.j;
                } else {
                    charSequence = null;
                    this.f1048a.setTitle((CharSequence) null);
                    toolbar = this.f1048a;
                }
                toolbar.setSubtitle(charSequence);
            }
            if ((i2 & 16) == 0 || (view = this.f1051d) == null) {
                return;
            }
            if ((i & 16) != 0) {
                this.f1048a.addView(view);
            } else {
                this.f1048a.removeView(view);
            }
        }
    }

    public void x(View view) {
        View view2 = this.f1051d;
        if (view2 != null && (this.f1049b & 16) != 0) {
            this.f1048a.removeView(view2);
        }
        this.f1051d = view;
        if (view == null || (this.f1049b & 16) == 0) {
            return;
        }
        this.f1048a.addView(view);
    }

    public void y(int i) {
        if (i == this.p) {
            return;
        }
        this.p = i;
        if (TextUtils.isEmpty(this.f1048a.getNavigationContentDescription())) {
            A(this.p);
        }
    }

    public void z(Drawable drawable) {
        this.f = drawable;
        I();
    }
}
