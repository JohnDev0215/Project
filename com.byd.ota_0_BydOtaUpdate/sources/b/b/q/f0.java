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
import b.b.p.j.m;

/* JADX INFO: compiled from: ToolbarWidgetWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class f0 implements p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Toolbar f1024a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1025b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public View f1026c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public View f1027d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Drawable f1028e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Drawable f1029f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Drawable f1030g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1031h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public CharSequence f1032i;
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

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final b.b.p.j.a f1033c;

        public a() {
            this.f1033c = new b.b.p.j.a(f0.this.f1024a.getContext(), 0, R.id.home, 0, 0, f0.this.f1032i);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            f0 f0Var = f0.this;
            Window.Callback callback = f0Var.l;
            if (callback == null || !f0Var.m) {
                return;
            }
            callback.onMenuItemSelected(0, this.f1033c);
        }
    }

    /* JADX INFO: compiled from: ToolbarWidgetWrapper.java */
    public class b extends b.f.m.y {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1035a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f1036b;

        public b(int i2) {
            this.f1036b = i2;
        }

        @Override // b.f.m.x
        public void a(View view) {
            if (this.f1035a) {
                return;
            }
            f0.this.f1024a.setVisibility(this.f1036b);
        }

        @Override // b.f.m.y, b.f.m.x
        public void b(View view) {
            f0.this.f1024a.setVisibility(0);
        }

        @Override // b.f.m.y, b.f.m.x
        public void c(View view) {
            this.f1035a = true;
        }
    }

    public f0(Toolbar toolbar, boolean z) {
        this(toolbar, z, b.b.h.f758a, b.b.e.n);
    }

    public void A(int i2) {
        B(i2 == 0 ? null : o().getString(i2));
    }

    public void B(CharSequence charSequence) {
        this.k = charSequence;
        G();
    }

    public void C(Drawable drawable) {
        this.f1030g = drawable;
        H();
    }

    public void D(CharSequence charSequence) {
        this.j = charSequence;
        if ((this.f1025b & 8) != 0) {
            this.f1024a.setSubtitle(charSequence);
        }
    }

    public void E(CharSequence charSequence) {
        this.f1031h = true;
        F(charSequence);
    }

    public final void F(CharSequence charSequence) {
        this.f1032i = charSequence;
        if ((this.f1025b & 8) != 0) {
            this.f1024a.setTitle(charSequence);
        }
    }

    public final void G() {
        if ((this.f1025b & 4) != 0) {
            if (TextUtils.isEmpty(this.k)) {
                this.f1024a.setNavigationContentDescription(this.p);
            } else {
                this.f1024a.setNavigationContentDescription(this.k);
            }
        }
    }

    public final void H() {
        if ((this.f1025b & 4) == 0) {
            this.f1024a.setNavigationIcon((Drawable) null);
            return;
        }
        Toolbar toolbar = this.f1024a;
        Drawable drawable = this.f1030g;
        if (drawable == null) {
            drawable = this.q;
        }
        toolbar.setNavigationIcon(drawable);
    }

    public final void I() {
        Drawable drawable;
        int i2 = this.f1025b;
        if ((i2 & 2) == 0) {
            drawable = null;
        } else if ((i2 & 1) == 0 || (drawable = this.f1029f) == null) {
            drawable = this.f1028e;
        }
        this.f1024a.setLogo(drawable);
    }

    @Override // b.b.q.p
    public void a(Menu menu, m.a aVar) {
        if (this.n == null) {
            ActionMenuPresenter actionMenuPresenter = new ActionMenuPresenter(this.f1024a.getContext());
            this.n = actionMenuPresenter;
            actionMenuPresenter.q(b.b.f.f746g);
        }
        this.n.h(aVar);
        this.f1024a.I((b.b.p.j.g) menu, this.n);
    }

    @Override // b.b.q.p
    public boolean b() {
        return this.f1024a.z();
    }

    @Override // b.b.q.p
    public boolean c() {
        return this.f1024a.A();
    }

    @Override // b.b.q.p
    public void collapseActionView() {
        this.f1024a.e();
    }

    @Override // b.b.q.p
    public boolean d() {
        return this.f1024a.w();
    }

    @Override // b.b.q.p
    public boolean e() {
        return this.f1024a.N();
    }

    @Override // b.b.q.p
    public void f() {
        this.m = true;
    }

    @Override // b.b.q.p
    public boolean g() {
        return this.f1024a.d();
    }

    @Override // b.b.q.p
    public CharSequence getTitle() {
        return this.f1024a.getTitle();
    }

    @Override // b.b.q.p
    public void h() {
        this.f1024a.f();
    }

    @Override // b.b.q.p
    public int i() {
        return this.f1025b;
    }

    @Override // b.b.q.p
    public void j(int i2) {
        this.f1024a.setVisibility(i2);
    }

    @Override // b.b.q.p
    public void k(int i2) {
        z(i2 != 0 ? b.b.l.a.a.d(o(), i2) : null);
    }

    @Override // b.b.q.p
    public void l(ScrollingTabContainerView scrollingTabContainerView) {
        View view = this.f1026c;
        if (view != null) {
            ViewParent parent = view.getParent();
            Toolbar toolbar = this.f1024a;
            if (parent == toolbar) {
                toolbar.removeView(this.f1026c);
            }
        }
        this.f1026c = scrollingTabContainerView;
        if (scrollingTabContainerView == null || this.o != 2) {
            return;
        }
        this.f1024a.addView(scrollingTabContainerView, 0);
        Toolbar.e eVar = (Toolbar.e) this.f1026c.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) eVar).width = -2;
        ((ViewGroup.MarginLayoutParams) eVar).height = -2;
        eVar.f780a = 8388691;
        scrollingTabContainerView.setAllowCollapse(true);
    }

    @Override // b.b.q.p
    public ViewGroup m() {
        return this.f1024a;
    }

    @Override // b.b.q.p
    public void n(boolean z) {
    }

    @Override // b.b.q.p
    public Context o() {
        return this.f1024a.getContext();
    }

    @Override // b.b.q.p
    public int p() {
        return this.o;
    }

    @Override // b.b.q.p
    public b.f.m.w q(int i2, long j) {
        return b.f.m.s.b(this.f1024a).a(i2 == 0 ? 1.0f : 0.0f).d(j).f(new b(i2));
    }

    @Override // b.b.q.p
    public void r() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // b.b.q.p
    public boolean s() {
        return this.f1024a.v();
    }

    @Override // b.b.q.p
    public void setIcon(int i2) {
        setIcon(i2 != 0 ? b.b.l.a.a.d(o(), i2) : null);
    }

    @Override // b.b.q.p
    public void setWindowCallback(Window.Callback callback) {
        this.l = callback;
    }

    @Override // b.b.q.p
    public void setWindowTitle(CharSequence charSequence) {
        if (this.f1031h) {
            return;
        }
        F(charSequence);
    }

    @Override // b.b.q.p
    public void t() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // b.b.q.p
    public void u(boolean z) {
        this.f1024a.setCollapsible(z);
    }

    @Override // b.b.q.p
    public void v(int i2) {
        View view;
        int i3 = this.f1025b ^ i2;
        this.f1025b = i2;
        if (i3 != 0) {
            if ((i3 & 4) != 0) {
                if ((i2 & 4) != 0) {
                    G();
                }
                H();
            }
            if ((i3 & 3) != 0) {
                I();
            }
            if ((i3 & 8) != 0) {
                if ((i2 & 8) != 0) {
                    this.f1024a.setTitle(this.f1032i);
                    this.f1024a.setSubtitle(this.j);
                } else {
                    this.f1024a.setTitle((CharSequence) null);
                    this.f1024a.setSubtitle((CharSequence) null);
                }
            }
            if ((i3 & 16) == 0 || (view = this.f1027d) == null) {
                return;
            }
            if ((i2 & 16) != 0) {
                this.f1024a.addView(view);
            } else {
                this.f1024a.removeView(view);
            }
        }
    }

    public final int w() {
        if (this.f1024a.getNavigationIcon() == null) {
            return 11;
        }
        this.q = this.f1024a.getNavigationIcon();
        return 15;
    }

    public void x(View view) {
        View view2 = this.f1027d;
        if (view2 != null && (this.f1025b & 16) != 0) {
            this.f1024a.removeView(view2);
        }
        this.f1027d = view;
        if (view == null || (this.f1025b & 16) == 0) {
            return;
        }
        this.f1024a.addView(view);
    }

    public void y(int i2) {
        if (i2 == this.p) {
            return;
        }
        this.p = i2;
        if (TextUtils.isEmpty(this.f1024a.getNavigationContentDescription())) {
            A(this.p);
        }
    }

    public void z(Drawable drawable) {
        this.f1029f = drawable;
        I();
    }

    public f0(Toolbar toolbar, boolean z, int i2, int i3) {
        Drawable drawable;
        this.o = 0;
        this.p = 0;
        this.f1024a = toolbar;
        this.f1032i = toolbar.getTitle();
        this.j = toolbar.getSubtitle();
        this.f1031h = this.f1032i != null;
        this.f1030g = toolbar.getNavigationIcon();
        e0 e0VarU = e0.u(toolbar.getContext(), null, b.b.j.f771a, b.b.a.f706c, 0);
        this.q = e0VarU.f(b.b.j.l);
        if (z) {
            CharSequence charSequenceO = e0VarU.o(b.b.j.r);
            if (!TextUtils.isEmpty(charSequenceO)) {
                E(charSequenceO);
            }
            CharSequence charSequenceO2 = e0VarU.o(b.b.j.p);
            if (!TextUtils.isEmpty(charSequenceO2)) {
                D(charSequenceO2);
            }
            Drawable drawableF = e0VarU.f(b.b.j.n);
            if (drawableF != null) {
                z(drawableF);
            }
            Drawable drawableF2 = e0VarU.f(b.b.j.m);
            if (drawableF2 != null) {
                setIcon(drawableF2);
            }
            if (this.f1030g == null && (drawable = this.q) != null) {
                C(drawable);
            }
            v(e0VarU.j(b.b.j.f778h, 0));
            int iM = e0VarU.m(b.b.j.f777g, 0);
            if (iM != 0) {
                x(LayoutInflater.from(this.f1024a.getContext()).inflate(iM, (ViewGroup) this.f1024a, false));
                v(this.f1025b | 16);
            }
            int iL = e0VarU.l(b.b.j.j, 0);
            if (iL > 0) {
                ViewGroup.LayoutParams layoutParams = this.f1024a.getLayoutParams();
                layoutParams.height = iL;
                this.f1024a.setLayoutParams(layoutParams);
            }
            int iD = e0VarU.d(b.b.j.f776f, -1);
            int iD2 = e0VarU.d(b.b.j.f775e, -1);
            if (iD >= 0 || iD2 >= 0) {
                this.f1024a.H(Math.max(iD, 0), Math.max(iD2, 0));
            }
            int iM2 = e0VarU.m(b.b.j.s, 0);
            if (iM2 != 0) {
                Toolbar toolbar2 = this.f1024a;
                toolbar2.K(toolbar2.getContext(), iM2);
            }
            int iM3 = e0VarU.m(b.b.j.q, 0);
            if (iM3 != 0) {
                Toolbar toolbar3 = this.f1024a;
                toolbar3.J(toolbar3.getContext(), iM3);
            }
            int iM4 = e0VarU.m(b.b.j.o, 0);
            if (iM4 != 0) {
                this.f1024a.setPopupTheme(iM4);
            }
        } else {
            this.f1025b = w();
        }
        e0VarU.v();
        y(i2);
        this.k = this.f1024a.getNavigationContentDescription();
        this.f1024a.setNavigationOnClickListener(new a());
    }

    @Override // b.b.q.p
    public void setIcon(Drawable drawable) {
        this.f1028e = drawable;
        I();
    }
}
